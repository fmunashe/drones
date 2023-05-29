package com.drones.services.impl;

import com.drones.dto.MedicationDto;
import com.drones.exception.MedicationNotFoundException;
import com.drones.models.Medication;
import com.drones.repositories.MedicationRepository;
import com.drones.services.MedicationService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MedicationServiceImpl implements MedicationService {

    private final ModelMapper modelMapper;

    private final MedicationRepository medicationRepository;


    public MedicationServiceImpl(ModelMapper modelMapper, MedicationRepository medicationRepository) {
        this.modelMapper = modelMapper;
        this.medicationRepository = medicationRepository;
    }

    @Override
    public MedicationDto getMedicationByCode(String medicationCode) {
        Medication medication = medicationRepository
                .findMedicationByCode(medicationCode)
                .orElseThrow(() -> new MedicationNotFoundException("Could not find medication: "+ medicationCode));
        return modelMapper.map(medication, MedicationDto.class);
    }



    @PostConstruct
    private void init() {
        Medication firstMed=Medication.builder()
                .name("Paracet")
                .code("MED001")
                .weight(.45)
                .image("med1")
                .build();

        Medication secondMed=Medication.builder()
                .name("CMP")
                .code("MED002")
                .weight(2.33)
                .image("med2")
                .build();

        Medication thirdMed=Medication.builder()
                .name("Brufen")
                .code("MED003")
                .weight(1.21)
                .image("med2")
                .build();

        medicationRepository.save(firstMed);
        medicationRepository.save(secondMed);
        medicationRepository.save(thirdMed);

    }
}
