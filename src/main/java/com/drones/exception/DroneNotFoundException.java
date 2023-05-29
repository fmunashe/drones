package com.drones.exception;

public class DroneNotFoundException extends RuntimeException{
    public DroneNotFoundException() {
    }

    public DroneNotFoundException(String message) {
        super(message);
    }
}
