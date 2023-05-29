package com.drones.exception;

public class DroneIllegalStateException extends RuntimeException{
    public DroneIllegalStateException() {
    }

    public DroneIllegalStateException(String message) {
        super(message);
    }
}
