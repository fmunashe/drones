package com.drones.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class DroneAudit {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String serialNumber;
    private double batteryLevel;
    private LocalDateTime dateTime;
}
