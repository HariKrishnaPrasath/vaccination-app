package com.jpa.vaccinationapp.vaccine.service;

import com.jpa.vaccinationapp.admin.entity.Admin;
import com.jpa.vaccinationapp.vaccine.entity.Vaccine;
import com.jpa.vaccinationapp.vaccine.exception.VaccineException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VaccineService {
    Vaccine createVaccine(Vaccine newVaccine, Admin admin) throws VaccineException;
    Vaccine updateVaccine(Integer vaccineId,Admin admin) throws VaccineException;
    Vaccine getById(Integer id) throws VaccineException;
    List<Vaccine> getByName(String vaccineName) throws VaccineException;
    Vaccine deleteExpiredVaccines(Integer vaccindeId) throws VaccineException;

    Vaccine deleteById(Integer vaccineId) throws VaccineException;


    List<Vaccine> getAllVaccine();
    List<Vaccine> deleteAllExpiredVaccines();
}
