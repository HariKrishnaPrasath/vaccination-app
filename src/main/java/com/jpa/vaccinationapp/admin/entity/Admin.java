package com.jpa.vaccinationapp.admin.entity;

import com.jpa.vaccinationapp.vaccinationCenter.entity.VaccinationCenter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Admin {
    @Id
    @GeneratedValue(generator = "100")
    private Integer adminId;
    private String email;
    private String phoneNumber;
    private String adminName;
    private String password;
    private String adminType;
    @OneToOne
    private VaccinationCenter vaccinationCenter;

    public Admin() {
    }

    public Admin(Integer adminId, String email, String phoneNumber, String adminName, String password, String adminType,
                 VaccinationCenter vaccinationCenter) {
        this.adminId = adminId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.adminName = adminName;
        this.password = password;
        this.adminType = adminType;
        this.vaccinationCenter = vaccinationCenter;
    }

    public Admin(String email, String phoneNumber, String adminName, String password, String adminType,
                 VaccinationCenter vaccinationCenter) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.adminName = adminName;
        this.password = password;
        this.adminType = adminType;
        this.vaccinationCenter = vaccinationCenter;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }
}
