package com.drones.models;

import jakarta.persistence.*;

@Table
@Entity
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String code;
    private String image;
    private double weight;
}
