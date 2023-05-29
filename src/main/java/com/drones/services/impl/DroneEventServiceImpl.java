package com.drones.services.impl;

import com.drones.dto.DroneReport;
import com.drones.enums.DroneEventStatus;
import com.drones.exception.DroneNotFoundException;
import com.drones.models.DroneEvent;
import com.drones.repositories.DroneEventRepository;
import com.drones.services.DroneEventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneEventServiceImpl implements DroneEventService {


    private final DroneEventRepository droneEventRepository;

    public DroneEventServiceImpl(DroneEventRepository droneEventRepository) {
        this.droneEventRepository = droneEventRepository;
    }


    @Override
    public Optional<DroneEvent> findDroneEventByDroneSerialNumberAndStatus(String droneSerialNumber, DroneEventStatus eventStatus) {
        return droneEventRepository
                .findDroneEventByDroneSerialNumberAndEventStatus(droneSerialNumber, eventStatus);

    }

    @Override
    public DroneEvent save(DroneEvent droneEvent) {
        return droneEventRepository.save(droneEvent);
    }

    @Override
    public DroneReport findDroneMedicationByDroneSerialNo(String sn) {
        DroneEvent droneEvnt =
                findDroneEventByDroneSerialNumberAndStatus(sn, DroneEventStatus.OPEN)
                        .orElseThrow(()->new DroneNotFoundException("Drone not found: "+sn));
        List<String> medication = droneEvnt.getMedication();
        return new DroneReport(sn,medication);
    }

}
