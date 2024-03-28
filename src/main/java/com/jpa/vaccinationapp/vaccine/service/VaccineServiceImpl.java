package com.jpa.vaccinationapp.vaccine.service;


import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.vaccine.VaccineRepository;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import com.jpa.vaccinationapp.vaccine.VaccineException;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public Vaccine updateVaccine(Integer vaccineId, Vaccine vaccine) throws VaccineException {
        Optional<Vaccine> result=vaccineRepository.findById(vaccine.getVaccineId());
        if(result.isEmpty()) throw new VaccineException("Vaccine doesn't exists!");
        return vaccineRepository.save(vaccine);//doubt
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
    public List<Vaccine> deleteExpiredVaccines() throws VaccineException {
        List<Vaccine> vaccineList = this.vaccineRepository.findAll();
        List<Vaccine> deletedVaccines = new ArrayList<>();

        LocalDate currentDate=LocalDate.now();
        System.out.println(currentDate);
        for(Vaccine vaccine : vaccineList) {
            LocalDate expiryDate = vaccine.getExpiryDate();
            if (currentDate.isAfter(expiryDate) || currentDate.isEqual(expiryDate)) {
                vaccineRepository.delete(vaccine);
                deletedVaccines.add(vaccine);
            }
        }
        if(deletedVaccines.isEmpty()){
            throw new VaccineException("No expired vaccines");
        }
        return deletedVaccines;

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
