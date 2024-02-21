package com.jpa.vaccinationapp.vaccinationCenter.service;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.vaccinationCenter.AddressDTO;
import com.jpa.vaccinationapp.vaccinationCenter.CenterException;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccine.Vaccine;

import java.util.List;


public interface CenterService {
    Center createCenter(Center newCenter, Admin admin) throws CenterException;
    Center removeCenter(Integer centerID, Admin admin)throws CenterException;
    Center updateCenter(Center center,Admin admin)throws CenterException;
    Center addVaccineToCenter(Integer centerID, Admin admin, Vaccine newVaccine)throws CenterException;
    Center removeVaccineFromCentre(Integer centerID, Admin admin, Vaccine vaccine) throws CenterException;
    List<Center> findByNameCaseInsensitive(String centerName) throws CenterException;
    Center findByID(Integer centerID)throws CenterException;
    List<Center> findByPincode(String pincode)throws CenterException;
    List<Center> getAllCenter();

    Center updateCenterAddressAndPhone(AddressDTO addressDTO) throws CenterException;
}
