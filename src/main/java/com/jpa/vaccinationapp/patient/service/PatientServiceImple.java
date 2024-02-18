package com.jpa.vaccinationapp.patient.service;

import com.jpa.vaccinationapp.patient.dao.PatientRepository;
import com.jpa.vaccinationapp.patient.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImple implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public Patient signIN(Patient patient) {
        return this.patientRepository.save(patient);
    }
}
