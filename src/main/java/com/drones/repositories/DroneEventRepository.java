package com.drones.repositories;

import com.drones.enums.DroneEventStatus;
import com.drones.models.DroneEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DroneEventRepository extends JpaRepository<DroneEvent, Long> {
    Optional<DroneEvent> findDroneEventByDroneSerialNumberAndEventStatus(String droneSerialNumber, DroneEventStatus eventStatus);
}
