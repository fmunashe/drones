package com.drones.services;


import com.drones.dto.DroneDto;
import com.drones.dto.DroneReport;
import com.drones.dto.LoadDroneDtoRequest;
import com.drones.dto.LoadDroneResponseDto;
import com.drones.enums.State;
import jakarta.validation.Valid;

import java.util.List;

public interface DroneService {

    DroneDto registerDrone(@Valid DroneDto droneDto);
    LoadDroneResponseDto loadDrone(LoadDroneDtoRequest loadDroneDtoRequest);

    DroneDto findDroneBySerialNumber(String serialNumber);

    void updateDroneState(String droneSerialNumber, State droneState);

    List<DroneDto> fetchDronesAvailableForLoading();

    double checkDroneBatteryLevel(String droneSerialNumber);

    DroneReport getMedicationOnADrone(String droneSerialNumber);

}
