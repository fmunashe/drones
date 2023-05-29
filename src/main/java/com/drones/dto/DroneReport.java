package com.drones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DroneReport {
    private String droneSerialNumber;
    private List<String> medication;
}
