package com.jpa.vaccinationapp.patient.service;

import com.jpa.vaccinationapp.patient.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient signIN(Patient patient);

    List<Patient> getAllUsers();
}
