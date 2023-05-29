package com.drones.exception;

public class DroneOverloadException extends RuntimeException{
    public DroneOverloadException() {
    }

    public DroneOverloadException(String message) {
        super(message);
    }
}
