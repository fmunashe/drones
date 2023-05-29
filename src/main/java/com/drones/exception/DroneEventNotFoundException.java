package com.drones.exception;

public class DroneEventNotFoundException extends RuntimeException{
    public DroneEventNotFoundException() {
    }

    public DroneEventNotFoundException(String message) {
        super(message);
    }
}
