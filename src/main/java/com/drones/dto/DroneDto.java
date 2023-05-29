package com.drones.dto;

import com.drones.enums.State;
import com.drones.enums.Model;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneDto {

    @NotBlank
    @Size(max = 100, message = "SN Can't greater than 100 characters.")
    @Size(min = 5, message = "SN Can't less than 5characters.")
    private String serialNumber;

    @NotNull
    private Model model;

    @Max(500)
    @Min(0)
    private double weightLimit;

    @Max(100)
    @Min(0)
    private double batteryCapacity;

    @NotNull
    private State state;
}
