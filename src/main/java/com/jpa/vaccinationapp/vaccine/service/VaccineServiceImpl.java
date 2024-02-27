package com.jpa.vaccinationapp.vaccine.service;


import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.vaccine.VaccineRepository;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import com.jpa.vaccinationapp.vaccine.VaccineException;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class VaccineServiceImpl implements VaccineService{
    private final VaccineRepository vaccineRepository;
    @Autowired
    public VaccineServiceImpl(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public Vaccine createVaccine(Vaccine newVaccine) throws VaccineException {
        if (newVaccine.getVaccineId() != null){
            Optional<Vaccine> result=vaccineRepository.findById(newVaccine.getVaccineId());
            if(result.isPresent()) throw new VaccineException("this vaccine already exists!");
        }
        return vaccineRepository.save(newVaccine);
    }

    @Override
    public Vaccine updateVaccine(Integer vaccineId) throws VaccineException {
        Optional<Vaccine> result=vaccineRepository.findById(vaccineId);
        if(result.isEmpty()) throw new VaccineException("Vaccine doesn't exists!");
        return vaccineRepository.save(result.get());//doubt
    }
    @Override
    public Vaccine getById(Integer id) throws VaccineException {
        Optional<Vaccine> result=vaccineRepository.findById(id);
        if(result.isEmpty()) throw new VaccineException("No vaccine found for the given id");
        return result.get();
    }

    @Override
    public List<Vaccine> getByName(String vaccineName) throws VaccineException {
        Optional<List<Vaccine>> result=Optional.ofNullable(vaccineRepository.findByVaccineNameIgnoreCase(vaccineName));
        if(result.isEmpty()) throw new VaccineException("No vaccines found for the given name");
        return result.get();

    }



    @Override
    public Vaccine deleteExpiredVaccines(Integer vaccineId) throws VaccineException {
        Optional<Vaccine> vaccine=vaccineRepository.findById(vaccineId);
        if(vaccine.isEmpty()) throw new VaccineException("No vaccine found for the given id");
        LocalDate currentDate=LocalDate.now();
        LocalDate expiryDate=vaccine.get().getExpiryDate();
        if(currentDate.isAfter(expiryDate)) {
            vaccineRepository.delete(vaccine.get());
        }
        else{
            throw new VaccineException("the vaccine is not yet expired");
        }
        return vaccine.get();

    }
    @Override
    public Vaccine deleteById(Integer vaccineId) throws VaccineException {
        Optional<Vaccine> result=vaccineRepository.findById(vaccineId);
        if(result.isPresent()){
            Vaccine deletedVaccine=result.get();
            vaccineRepository.delete(deletedVaccine);
            return deletedVaccine;
        }
        throw new VaccineException("there is no vaccine by that id");
    }
    @Override
    public List<Vaccine> getAllVaccine(){
        return vaccineRepository.findAll();
    }

    @Override
    public List<Vaccine> deleteAllExpiredVaccines() {
        LocalDate currentDate= LocalDate.now();
        Optional<List<Vaccine>> result = Optional.ofNullable(vaccineRepository.deleteRecordsByExpiryDateBefore(currentDate));
        return result.get();
    }



}
