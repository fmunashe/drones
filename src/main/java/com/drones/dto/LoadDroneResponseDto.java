package com.drones.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoadDroneResponseDto {
    private String droneSerialNumber;
    private String medicationCode;
    private String result;
}
