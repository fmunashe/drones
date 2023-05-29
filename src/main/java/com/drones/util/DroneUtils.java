package com.drones.util;

import com.drones.dto.DroneDto;
import com.drones.dto.MedicationDto;
import com.drones.enums.DroneEventStatus;
import com.drones.enums.State;
import com.drones.exception.DroneBatteryException;
import com.drones.exception.DroneIllegalStateException;
import com.drones.models.Drone;
import com.drones.models.DroneEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Collections;

@Slf4j
public class DroneUtils {

    private DroneUtils(){}


    public static DroneEvent buildDroneEvent(DroneDto droneDto, MedicationDto medication){
        return DroneEvent.builder()
                .droneSerialNumber(droneDto.getSerialNumber())
                .currentLoad(medication.getWeight())
                .eventStatus(DroneEventStatus.OPEN)
                .dateTime(LocalDateTime.now())
                .medication(Collections.singletonList(medication.getCode()))
                .build();
    }


    public static boolean validateDroneForLoading(Drone drone) {

        if (drone.getBatteryCapacity() < 25) {
           throw new DroneBatteryException("Drone has low battery");
        }

        if (drone.getState().equals(State.DELIVERED) ||
                drone.getState().equals(State.RETURNING) ||
                drone.getState().equals(State.DELIVERING)) {
            throw  new DroneIllegalStateException("Drone can't load in current state.");
        }

        return true;
    }

}
