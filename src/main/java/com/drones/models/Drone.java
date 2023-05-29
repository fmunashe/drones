package com.drones.models;

import com.drones.enums.Model;
import com.drones.enums.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name= "drones")
@Data
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 1, max = 100)
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private Model model;
    @Min(1)
    @Max(500)
    private double weightLimit;
    @Min(0)
    @Max(100)
    private int batteryCapacity;
    @Enumerated(EnumType.STRING)
    private State state;
}
