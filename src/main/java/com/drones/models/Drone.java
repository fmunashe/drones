package com.drones.models;

import com.drones.enums.Model;
import com.drones.enums.State;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table
public class Drone {
    private long id;

    private String serialNumber;
    @Enumerated
    private Model model;
    private double weight;

    private int batteryCapacity;

    @Enumerated
    private State state;
}
