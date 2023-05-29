package com.drones.exception;

public class MedicationNotFoundException extends RuntimeException{

    public MedicationNotFoundException() {
    }

    public MedicationNotFoundException(String message) {
        super(message);
    }
}
