package com.jpa.vaccinationapp.vaccine.service;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import com.jpa.vaccinationapp.vaccine.VaccineException;

import java.util.List;

public interface VaccineService {
    public Vaccine addVaccine(Admin adminDetails, Vaccine vaccineDetails) throws AdminException;
    public Vaccine updateVaccine(Admin adminDetails,Vaccine vaccineDetails)throws VaccineException,AdminException;
    public Vaccine deleteVaccine(Admin adminDetails,Integer vaccineId)throws VaccineException,AdminException;
    public List<Vaccine> getAllVaccine(Admin adminDetails)throws VaccineException,AdminException;
    public Vaccine getVaccine(Admin adminDetails,Integer vaccineId)throws VaccineException,AdminException;

}
