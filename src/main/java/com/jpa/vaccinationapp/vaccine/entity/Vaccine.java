package com.jpa.vaccinationapp.vaccine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Vaccine {
    @Id
    @GeneratedValue(generator = "1000")
    private Integer vaccineId;
    private String vaccineName;
    private LocalDate manufacturingDate;
    private LocalDate expiryDate;

    public Vaccine() {
    }

    public Vaccine(String vaccineName, LocalDate manufacturingDate, LocalDate expiryDate) {
        this.vaccineName = vaccineName;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
    }

    public Vaccine(Integer vaccineId, String vaccineName, LocalDate manufacturingDate, LocalDate expiryDate) {
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
    }

    public Integer getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(Integer vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
