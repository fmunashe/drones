package com.drones.services;

import com.drones.dto.DroneReport;
import com.drones.enums.DroneEventStatus;
import com.drones.models.DroneEvent;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
public interface DroneEventService {
    Optional<DroneEvent> findDroneEventByDroneSerialNumberAndStatus(@NotBlank String droneSerialNumber, DroneEventStatus eventStatus);

    DroneEvent save(DroneEvent droneEvent);

    DroneReport findDroneMedicationByDroneSerialNo(String sn);
}
