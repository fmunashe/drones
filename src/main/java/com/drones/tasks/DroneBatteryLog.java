package com.drones.tasks;

import com.drones.models.DroneAudit;
import com.drones.repositories.DroneAuditRepository;
import com.drones.repositories.DroneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@EnableScheduling
public class DroneBatteryLog {

    private final DroneAuditRepository droneAuditRepository;

    private final DroneRepository droneRepository;

    public DroneBatteryLog(DroneAuditRepository droneAuditRepository, DroneRepository droneRepository) {
        this.droneAuditRepository = droneAuditRepository;
        this.droneRepository = droneRepository;
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void logBatteryLevels() {
        droneRepository
                .findAll()
                .forEach(drone -> {
                    DroneAudit droneAudit = new DroneAudit();
                    droneAudit.setBatteryLevel(drone.getBatteryCapacity());
                    droneAudit.setSerialNumber(drone.getSerialNumber());
                    droneAudit.setDateTime(LocalDateTime.now());
                    droneAuditRepository.save(droneAudit);
                    log.info("Saved logs for drone: {}", drone.getSerialNumber());
                });
    }
}
