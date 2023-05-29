package com.drones.controllers;

import com.drones.dto.DroneDto;
import com.drones.dto.DroneReport;
import com.drones.dto.LoadDroneDtoRequest;
import com.drones.dto.LoadDroneResponseDto;
import com.drones.services.DroneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/api/v1/drones")
public class DroneController {

    private final DroneService droneService;


    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }


    @PostMapping("/register")
    public ResponseEntity<DroneDto> registerDrone(@Valid @RequestBody DroneDto droneDto) {
        DroneDto registeredDrone = droneService.registerDrone(droneDto);
        return new ResponseEntity<>(registeredDrone, HttpStatus.CREATED);
    }

    @PostMapping("/load")
    public ResponseEntity<LoadDroneResponseDto> loadDrone(@Valid @RequestBody LoadDroneDtoRequest request) {
        return new ResponseEntity<>(droneService.loadDrone(request), HttpStatus.OK);
    }

    @GetMapping("/medication/{droneSerialNumber}")
    public ResponseEntity<DroneReport> getMedication(@PathVariable("droneSerialNumber") String sn) {
        return new ResponseEntity<>(droneService.getMedicationOnADrone(sn), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<DroneDto>> getDronesAvailableForLoading() {
        return new ResponseEntity<>(droneService.fetchDronesAvailableForLoading(), HttpStatus.OK);
    }

    @GetMapping("/battery/{droneSerialNumber}")
    public ResponseEntity<Double> checkBattery(@PathVariable("droneSerialNumber") String droneSerialNumber) {
        return new ResponseEntity<>(droneService.checkDroneBatteryLevel(droneSerialNumber), HttpStatus.OK);
    }


}
