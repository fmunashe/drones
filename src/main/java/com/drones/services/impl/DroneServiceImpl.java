package com.drones.services.impl;

import com.drones.dto.*;
import com.drones.enums.DroneEventStatus;
import com.drones.enums.State;
import com.drones.exception.DroneIllegalStateException;
import com.drones.exception.DroneNotFoundException;
import com.drones.exception.DroneOverloadException;
import com.drones.models.Drone;
import com.drones.models.DroneEvent;
import com.drones.repositories.DroneRepository;
import com.drones.services.DroneEventService;
import com.drones.services.DroneService;
import com.drones.services.MedicationService;
import com.drones.util.DroneUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Validated
public class DroneServiceImpl implements DroneService {


    private final ModelMapper modelMapper;

    private final DroneRepository droneRepository;

    private final MedicationService medicationService;

    private final DroneEventService droneEventService;

    public DroneServiceImpl(ModelMapper modelMapper,
                            DroneRepository droneRepository,
                            MedicationService medicationService, DroneEventService droneEventService) {
        this.modelMapper = modelMapper;
        this.droneRepository = droneRepository;
        this.medicationService = medicationService;
        this.droneEventService = droneEventService;
    }


    @Override
    public DroneDto registerDrone(@Valid DroneDto droneDto) {

        if (!droneDto.getState().equals(State.IDLE)) {
            log.error("Could not register a drone in given state: {}", droneDto.getState());
            throw new DroneIllegalStateException("Can only register a drone in IDLE state.");
        }
        Drone drone = modelMapper.map(droneDto, Drone.class);
        Drone savedDrone = droneRepository.save(drone);
        log.info("Registered drone: {}", savedDrone);
        return modelMapper.map(savedDrone, DroneDto.class);
    }

    @Override
    public LoadDroneResponseDto loadDrone(LoadDroneDtoRequest loadDroneDtoRequest) {

        MedicationDto medication = medicationService.getMedicationByCode(loadDroneDtoRequest.getMedicationCode());
        DroneDto droneDto = findDroneBySerialNumber(loadDroneDtoRequest.getDroneSerialNumber());

        LoadDroneResponseDto responseDto = LoadDroneResponseDto.builder()
                .medicationCode(medication.getCode())
                .droneSerialNumber(droneDto.getSerialNumber())
                .build();

        DroneUtils.validateDroneForLoading(modelMapper.map(droneDto, Drone.class));

        if (medication.getWeight() > droneDto.getWeightLimit()) {
            throw new DroneOverloadException("Medication weight exceeds drone capacity.");
        }

        Optional<DroneEvent> activeEventOptional = droneEventService
                .findDroneEventByDroneSerialNumberAndStatus(droneDto.getSerialNumber(), DroneEventStatus.OPEN);

        if (activeEventOptional.isPresent()) {
            DroneEvent activeEvent = activeEventOptional.get();
            log.info("The drone is currently loading.");
            double availableDroneCapacity = droneDto.getWeightLimit() - activeEvent.getCurrentLoad();
            if (availableDroneCapacity > medication.getWeight()) {

                List<String> carriedMedication = activeEvent.getMedication();
                carriedMedication.add(medication.getCode());
                activeEvent.setMedication(carriedMedication);

                activeEvent.setCurrentLoad(activeEvent.getCurrentLoad() + medication.getWeight());

                droneEventService.save(activeEvent);
            } else {
                throw new DroneOverloadException("Medication weight exceeds available drone capacity.");
            }
            responseDto.setResult("Success");
            return responseDto;
        }

        DroneEvent droneEvent = DroneUtils.buildDroneEvent(droneDto, medication);

        droneEventService.save(droneEvent);
        updateDroneState(droneDto.getSerialNumber(), State.LOADING);
        responseDto.setResult("Success");
        log.info("Saved drone event.");
        return responseDto;
    }


    @Override
    public DroneDto findDroneBySerialNumber(String serialNumber) {
        Drone drone = droneRepository
                .findDroneBySerialNumber(serialNumber)
                .orElseThrow(() -> new DroneNotFoundException("Could not find the drone: " + serialNumber));
        return modelMapper.map(drone, DroneDto.class);
    }

    @Override
    public void updateDroneState(String droneSerialNumber, State droneState) {
        Drone drone = droneRepository
                .findDroneBySerialNumber(droneSerialNumber)
                .orElseThrow(() -> new DroneNotFoundException("Could not find the drone: " + droneSerialNumber));
        if (drone.getBatteryCapacity() < 25 && droneState.equals(State.LOADING)) {
            throw new DroneIllegalStateException("Drone can't load when battery is below 25%.");
        }
        drone.setState(droneState);
        droneRepository.save(drone);
        log.info("Changed the state of the drone: {}", drone);
        modelMapper.map(drone, DroneDto.class);
    }


    @Override
    public List<DroneDto> fetchDronesAvailableForLoading() {
        List<State> temp = Arrays.asList(
                State.LOADING,
                State.IDLE
        );
        return droneRepository
                .findAll()
                .stream()
                .filter(drone -> temp.contains(drone.getState()))
                .map(drone -> modelMapper.map(drone, DroneDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public double checkDroneBatteryLevel(String droneSerialNumber) {
        return findDroneBySerialNumber(droneSerialNumber)
                .getBatteryCapacity();
    }

    @Override
    public DroneReport getMedicationOnADrone(String droneSerialNumber) {
        return droneEventService.findDroneMedicationByDroneSerialNo(droneSerialNumber);
    }
}
