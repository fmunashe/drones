package com.drones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoadDroneDtoRequest {
    @NotBlank
    @Size(max = 100, message = "SN Can't greater than 100 characters.")
    @Size(min = 5, message = "SN Can't less than 5 characters.")
    private String droneSerialNumber;
    @NotBlank(message = "Medication code is mandatory.")
    private String medicationCode;
}
