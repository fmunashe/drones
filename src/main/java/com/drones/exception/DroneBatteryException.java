package com.drones.exception;

public class DroneBatteryException extends RuntimeException{
    public DroneBatteryException() {
    }

    public DroneBatteryException(String message) {
        super(message);
    }
}
