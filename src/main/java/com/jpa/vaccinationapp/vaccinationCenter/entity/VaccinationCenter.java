package com.jpa.vaccinationapp.vaccinationCenter.entity;

import com.jpa.vaccinationapp.admin.entity.Admin;
import com.jpa.vaccinationapp.bookingDetail.entity.BookingDetails;
import com.jpa.vaccinationapp.vaccine.entity.Vaccine;
import jakarta.persistence.*;

import java.util.*;


// changes made by gopinath
@Entity
public class VaccinationCenter {
    @Id
    @GeneratedValue(generator = "1000")
    private Integer centerId;
    private String centerName;
    private String address;
    private Integer vaccineAvailability;
    private String pincode;
    private String district;
    private String state;
    @ManyToMany
    private Map<Integer, Vaccine> vaccineMap;
    @OneToMany
    private Map<Integer, BookingDetails> bookingDetailsMap;
    private String contactNumber;
    @OneToOne
    private Admin admin;

    public VaccinationCenter() {
    }

    public VaccinationCenter(String centerName, String address, Integer vaccineAvailability, String pincode,
                             String district, String state, Map<Integer, Vaccine> vaccineMap, Map<Integer, BookingDetails> bookingDetailsMap, String contactNumber, Admin admin) {
        this.centerName = centerName;
        this.address = address;
        this.vaccineAvailability = vaccineAvailability;
        this.pincode = pincode;
        this.district = district;
        this.state = state;
        this.vaccineMap = vaccineMap;
        this.bookingDetailsMap = bookingDetailsMap;
        this.contactNumber = contactNumber;
        this.admin = admin;
    }

    public VaccinationCenter(Integer centerId, String centerName, String address, Integer vaccineAvailability,
                             String pincode, String district, String state, Map<Integer, Vaccine> vaccineMap, Map<Integer, BookingDetails> bookingDetailsMap, String contactNumber, Admin admin) {
        this.centerId = centerId;
        this.centerName = centerName;
        this.address = address;
        this.vaccineAvailability = vaccineAvailability;
        this.pincode = pincode;
        this.district = district;
        this.state = state;
        this.vaccineMap = vaccineMap;
        this.bookingDetailsMap = bookingDetailsMap;
        this.contactNumber = contactNumber;
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

    public Integer getVaccineAvailability() {
        return vaccineAvailability;
    }

    public void setVaccineAvailability(Integer vaccineAvailability) {
        this.vaccineAvailability = vaccineAvailability;
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

    public Map<Integer, Vaccine> getVaccineMap() {
        return vaccineMap;
    }

    public void setVaccineMap(Map<Integer, Vaccine> vaccineMap) {
        this.vaccineMap = vaccineMap;
    }

    public Map<Integer, BookingDetails> getBookingDetailsMap() {
        return bookingDetailsMap;
    }

    public void setBookingDetailsMap(Map<Integer, BookingDetails> bookingDetailsMap) {
        this.bookingDetailsMap = bookingDetailsMap;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
