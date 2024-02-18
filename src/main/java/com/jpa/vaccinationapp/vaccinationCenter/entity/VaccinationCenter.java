package com.jpa.vaccinationapp.vaccinationCenter.entity;

import com.jpa.vaccinationapp.admin.entity.Admin;
import com.jpa.vaccinationapp.slot.entity.Slot;
import com.jpa.vaccinationapp.vaccine.entity.Vaccine;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class VaccinationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer centerId;
    private String centerName;
    private String address;
    private String pincode;
    private String district;
    private String state;
    private String contactNumber;
    @ManyToMany
    private Map<Integer, Vaccine> vaccineMap;
    @OneToMany//(mappedBy = "vaccinationCenter")
    private Map<Integer, Slot> slots;
    @OneToOne
    private Admin admin;

    public VaccinationCenter(){}
    public VaccinationCenter(Integer centerId, String centerName, String address, String pincode, String district,
                             String state, String contactNumber, Map<Integer, Vaccine> vaccineMap, Map<Integer, Slot> slots, Admin admin) {
        this.centerId = centerId;
        this.centerName = centerName;
        this.address = address;
        this.pincode = pincode;
        this.district = district;
        this.state = state;
        this.contactNumber = contactNumber;
        this.vaccineMap = vaccineMap;
        this.slots = slots;
        this.admin = admin;
    }

    public VaccinationCenter(String centerName, String address, String pincode, String district, String state,
                             String contactNumber, Map<Integer, Vaccine> vaccineMap, Map<Integer, Slot> slots, Admin admin) {
        this.centerName = centerName;
        this.address = address;
        this.pincode = pincode;
        this.district = district;
        this.state = state;
        this.contactNumber = contactNumber;
        this.vaccineMap = vaccineMap;
        this.slots = slots;
        this.admin = admin;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Map<Integer, Vaccine> getVaccineMap() {
        return vaccineMap;
    }

    public void setVaccineMap(Map<Integer, Vaccine> vaccineMap) {
        this.vaccineMap = vaccineMap;
    }

    public Map<Integer, Slot> getSlots() {
        return slots;
    }

    public void setSlots(Map<Integer, Slot> slots) {
        this.slots = slots;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
