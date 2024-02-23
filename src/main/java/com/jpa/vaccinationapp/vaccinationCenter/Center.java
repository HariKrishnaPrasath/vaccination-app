package com.jpa.vaccinationapp.vaccinationCenter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.slot.Slot;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Center {
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
    @Column(nullable = true)
    private Map<Integer, Vaccine> vaccineMap;
    @OneToMany//(mappedBy = "vaccinationCenter")
    @Column(nullable = true)
    private Map<Integer, Slot> slots=new HashMap<>();
    @OneToOne
    private Admin admin;

    public Center(){}
    public Center(Integer centerId, String centerName, String address, String pincode, String district,
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

    public Center(String centerName, String address, String pincode, String district, String state,
                  String contactNumber, Map<Integer, Vaccine> vaccineMap, Map<Integer, Slot> slots) {
        this.centerName = centerName;
        this.address = address;
        this.pincode = pincode;
        this.district = district;
        this.state = state;
        this.contactNumber = contactNumber;
        this.vaccineMap = vaccineMap;
        this.slots = slots;
    }

    public Center(String centerName, String address, String pincode, String district, String state,
                  String contactNumber, Map<Integer, Vaccine> vaccineMap,Admin admin) {
        this.centerName = centerName;
        this.address = address;
        this.pincode = pincode;
        this.district = district;
        this.state = state;
        this.contactNumber = contactNumber;
        this.vaccineMap = vaccineMap;
        this.admin=admin;
    }

    public Center(String centerName, String address, String pincode, String district, String state,
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Center center = (Center) o;
        return Objects.equals(centerId, center.centerId) && Objects.equals(centerName, center.centerName) &&
                Objects.equals(address, center.address) && Objects.equals(pincode, center.pincode) &&
                Objects.equals(district, center.district) && Objects.equals(state, center.state) &&
                Objects.equals(contactNumber, center.contactNumber) && Objects.equals(vaccineMap, center.vaccineMap)
                && Objects.equals(slots, center.slots) && Objects.equals(admin, center.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(centerId, centerName, address, pincode, district, state, contactNumber,
                vaccineMap, slots, admin);
    }
}
