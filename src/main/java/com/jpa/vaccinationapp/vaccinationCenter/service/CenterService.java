package com.jpa.vaccinationapp.vaccinationCenter.service;

import com.jpa.vaccinationapp.vaccinationCenter.AddressDTO;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccinationCenter.CenterException;
import com.jpa.vaccinationapp.vaccine.Vaccine;

import java.util.List;

public interface CenterService {
    Center createCenter(Center newCenter) throws CenterException;
    Center removeCenter(Integer centerID)throws CenterException;
    Center updateCenter(Center center)throws CenterException;
    Center addVaccineToCenter(Integer centerID, Vaccine newVaccine)throws CenterException;
    Center removeVaccineFromCentre(Integer centerID, Vaccine vaccine) throws CenterException;
    List<Center> findCenterByCenterNameIsContainingIgnoreCase(String centerName) throws CenterException;
    Center findByID(Integer centerID)throws CenterException;
    List<Center> findByPincode(String pincode)throws CenterException;
    List<Center> getAllCenter();
    Center updateCenterAddressAndPhone(AddressDTO addressDTO) throws CenterException;
    List<Vaccine>getAllVaccinesFromCenter(Integer centerId) throws CenterException;
}
