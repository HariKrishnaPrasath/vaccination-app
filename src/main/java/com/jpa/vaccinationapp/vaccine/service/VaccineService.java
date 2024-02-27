package com.jpa.vaccinationapp.vaccine.service;


import com.jpa.vaccinationapp.admin.Admin;

import com.jpa.vaccinationapp.vaccine.Vaccine;
import com.jpa.vaccinationapp.vaccine.VaccineException;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VaccineService {
    Vaccine createVaccine(Vaccine newVaccine) throws VaccineException;
    Vaccine updateVaccine(Integer vaccineId) throws VaccineException;
    Vaccine getById(Integer id) throws VaccineException;
    List<Vaccine> getByName(String vaccineName) throws VaccineException;
    Vaccine deleteExpiredVaccines(Integer vaccineId) throws VaccineException;

    Vaccine deleteById(Integer vaccineId) throws VaccineException;


    List<Vaccine> getAllVaccine();
    List<Vaccine> deleteAllExpiredVaccines();

}
