package com.drones.services;


import com.drones.dto.MedicationDto;


public interface MedicationService {
    MedicationDto getMedicationByCode(String medicationCode);
}
