package com.jpa.vaccinationapp.vaccine.service;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.admin.AdminRepository;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import com.jpa.vaccinationapp.vaccine.VaccineException;
import com.jpa.vaccinationapp.vaccine.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineServiceImpl implements VaccineService{
    @Autowired
    AdminRepository adminRepo;
    @Autowired
    VaccineRepository vaccineRepo;

    @Override
    public Vaccine addVaccine(Admin adminDetails, Vaccine vaccineDetails)throws AdminException {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equalsIgnoreCase("Super")) {
            return this.vaccineRepo.save(vaccineDetails);
        }
        else {
            throw new AdminException("Admin Password is Invalid");
        }
    }

    @Override
    public Vaccine updateVaccine(Admin adminDetails, Vaccine vaccineDetails)throws VaccineException,AdminException {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equalsIgnoreCase("Super")) {
            Optional<Vaccine> vaccineFound=vaccineRepo.findById(vaccineDetails.getVaccineId());
            if(vaccineFound.isEmpty())
                throw new VaccineException("Vaccine not found please add as new Vaccine");
            return this.vaccineRepo.save(vaccineDetails);
        }
        else {
            throw new AdminException("Admin Password is Invalid");
        }
    }

    @Override
    public Vaccine deleteVaccine(Admin adminDetails, Integer vaccineId)throws VaccineException,AdminException {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equalsIgnoreCase("Super")) {
            Optional<Vaccine> vaccineFound = vaccineRepo.findById(vaccineId);
            if (vaccineFound.isEmpty())
                throw new VaccineException("Vaccine not found please add as new Vaccine");
            this.vaccineRepo.deleteById(vaccineId);
            return vaccineFound.get();

        }
        else {
            throw new AdminException("Admin Password is Invalid");
        }
    }

    @Override
    public List<Vaccine> getAllVaccine(Admin adminDetails)throws VaccineException,AdminException {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equalsIgnoreCase("Super")) {
            return this.vaccineRepo.findAll();
        }
        else {
            throw new AdminException("Admin Password is Invalid");
        }
    }

    @Override
    public Vaccine getVaccine(Admin adminDetails,Integer vaccineId)throws VaccineException,AdminException {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equalsIgnoreCase("Super")) {
            Optional<Vaccine> vaccineFound = vaccineRepo.findById(vaccineId);
            if (vaccineFound.isEmpty())
                throw new VaccineException("Vaccine not found please add as new Vaccine");
            return vaccineFound.get();
        }
        else {
            throw new AdminException("Admin Password is Invalid");
        }
    }

}
