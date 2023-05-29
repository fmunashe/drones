package com.drones.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDto {
    @NotBlank(message = "Name is required.")
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$")
    private String name;
    @NotBlank
    private String image;
    @Min(0)
    private double weight;
    @NotBlank(message = "Code is required.")
    @Pattern(regexp = "^[A-Z0-9_]*$")
    private String code;
}
