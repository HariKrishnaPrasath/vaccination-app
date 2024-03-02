package com.jpa.vaccinationapp.vaccinationCenter.service;

import com.jpa.vaccinationapp.vaccinationCenter.AddressDTO;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccinationCenter.CenterException;
import com.jpa.vaccinationapp.vaccinationCenter.CenterRepository;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import com.jpa.vaccinationapp.vaccine.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;
    private final VaccineRepository vaccineRepository;

    @Autowired
    public CenterServiceImpl(CenterRepository centerRepository, VaccineRepository vaccineRepository){
        this.centerRepository=centerRepository;
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public Center createCenter(Center newCenter) throws CenterException {
        if(newCenter==null){
            throw new CenterException("Center can't be created without entering all valid details");
        }
        return centerRepository.save(newCenter);
    }

    @Override
    public Center removeCenter(Integer centerID) throws CenterException {
        Optional<Center>center= centerRepository.findById(centerID);
        if(center.isEmpty()){
            String message=String.format("There is no such centre with ID: %d to remove",centerID);
            throw new CenterException(message);
        }
        centerRepository.delete(center.get());
        return center.get();
    }

    @Override
    public Center updateCenter(Center center) throws CenterException {
        Optional<Center>result= centerRepository.findById(center.getCenterId());
        if(result.isEmpty()){
            String message=String.format("There is no such centre with ID: %d to update",center.getCenterId());
            throw new CenterException(message);
        }
        return centerRepository.save(result.get());
    }

    @Override
    public Center addVaccineToCenter(Integer centerID, Vaccine newVaccine) throws CenterException {
        if(newVaccine==null){
            throw new CenterException("Vaccine details can't be NULL");
        }
        Optional<Center>centerOptional= centerRepository.findById(centerID);
        // can add only if the vaccine is present in vaccine repository
        Optional<Vaccine>vaccineOptional=vaccineRepository.findById(newVaccine.getVaccineId());
        if(vaccineOptional.isEmpty()){
            String message=String.format("Entered Vaccine ID: %d not found. Can't assign the vaccine to the center, " +
                    "please check the vaccine details",newVaccine.getVaccineId());
            throw new CenterException(message);
        }
        Center center;
        if(centerOptional.isPresent()){
            center = centerOptional.get();
        }else {
            String message=String.format("There is no such centre with ID: %d to add a Vaccine", centerID);
            throw new CenterException(message);
        }
        Set<Vaccine> vaccines = center.getVaccineMap();
        boolean vaccineExists = vaccines.stream()
                .anyMatch(v -> v.getVaccineId().equals(newVaccine.getVaccineId()));
        if (vaccineExists) {
            throw new CenterException("Vaccine with ID: " + newVaccine.getVaccineId() + " already exists in center.");
        }
        vaccines.add(newVaccine);
        center=centerRepository.save(center);
        return center;
    }

    @Override
    public Center removeVaccineFromCentre(Integer centerID, Vaccine vaccine) throws CenterException {
        if(vaccine==null){
            throw new CenterException("Vaccine details can't be NULL");
        }
        if(centerID==null){
            throw new CenterException("can't proceed without the center ID!");
        }
        Optional<Center> centerOptional = centerRepository.findById(centerID);
        if(centerOptional.isEmpty()){
            String message=String.format("There is no such centre with ID: %d",centerID);
            throw new CenterException(message);
        }
        Center center = centerOptional.get();
        Set<Vaccine> vaccines = center.getVaccineMap();
        Vaccine vaccineToRemove = vaccines.stream()
                .filter(v -> v.getVaccineId().equals(vaccine.getVaccineId())).findFirst().orElseThrow(() ->
                        new CenterException("Vaccine with ID: " + vaccine.getVaccineId() + " not found in center."));
        boolean removed = vaccines.remove(vaccineToRemove);
        if (!removed) {
            throw new CenterException("Failed to remove vaccine with ID: " + vaccine.getVaccineId());
        }
        center.setVaccineMap(vaccines);
        center=centerRepository.save(center);
        return center;
    }

    @Override
    public List<Center> findCenterByCenterNameIsContainingIgnoreCase(String centerName) throws CenterException {
        if(centerName==null){
            throw new CenterException("Cannot perform search with no centre name");
        }
        var center= Optional.ofNullable(centerRepository.findCenterByCenterNameIsContainingIgnoreCase(centerName));
        if(center.isEmpty()){
            String message=String.format("There's no such centre with name: %s. Please check it and try again",
                    centerName);
            throw new CenterException(message);
        }
        return center.get();
    }

    @Override
    public Center findByID(Integer centerID) throws CenterException {
        Optional<Center> center=centerRepository.findById(centerID);
        if(center.isEmpty()){
            String message=String.format("There is no such centre with ID: %d", centerID);
            throw new CenterException(message);
        }
        return center.get();
    }

    @Override
    public List<Center> findByPincode(String pincode) throws CenterException {
        if(pincode==null){
            throw new CenterException("Cannot perform search with no pincode");
        }
        var center = Optional.ofNullable(centerRepository.findByPincode(pincode));
        if(center.isEmpty()){
            String message=String.format("There's no such centre with the pincode: %s. " +
                    "Please check it and try again", pincode);
            throw new CenterException(message);
        }
        return center.get();
    }

    @Override
    public List<Center> getAllCenter(){
        return centerRepository.findAll();
    }

    @Override
    public Center updateCenterAddressAndPhone(AddressDTO addressDTO) throws CenterException {
        Optional<Center> result=centerRepository.findById(addressDTO.getCenterId());
        if(result.isEmpty()){
            String message=String.format("There is no such centre with ID to update the address: %d",
                    addressDTO.getCenterId());
            throw new CenterException(message);
        }
        Center center=result.get();
        center.setAddress(addressDTO.getAddress());
        center.setContactNumber(addressDTO.getContactNumber());
        center.setPincode(addressDTO.getPincode());
        center.setDistrict(addressDTO.getDistrict());
        center.setState(addressDTO.getState());
        return centerRepository.save(center);
    }

    @Override
    public List<Vaccine> getAllVaccinesFromCenter(Integer centerId) throws CenterException {
        Optional<Center> result=centerRepository.findById(centerId);
        if(result.isEmpty()){
            String message=String.format("There is no such centre with ID to update the address: %d",
                    centerId);
            throw new CenterException(message);
        }
        Center center=result.get();
        if(center.getVaccineMap().isEmpty()){
            throw new CenterException("There is no any vaccines available in this center currently");
        }
        return center.getVaccineMap().stream().toList();
    }
}
