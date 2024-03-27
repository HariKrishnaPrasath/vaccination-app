package com.jpa.vaccinationapp.vaccine;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Vaccine {
    @NotNull(message = "name cant be null")
    private String vaccineName;
    @NotNull(message = "Manufacturing date cannot be null")
    @PastOrPresent(message = "Manufacturing date cannot be in the future")
    private LocalDate manufacturingDate;
    @NotNull(message = "Expiry date cannot be null")
    private LocalDate expiryDate;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vaccineId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vaccine(String vaccineName, LocalDate manufacturingDate, LocalDate expiryDate, String description,Integer vaccineId) {
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.description = description;
    }

    public Vaccine() {
    }

    public Vaccine(String vaccineName, LocalDate manufacturingDate, LocalDate expiryDate, String description) {
        this.vaccineName = vaccineName;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vaccine vaccine)) return false;
        return Objects.equals(getVaccineId(), vaccine.getVaccineId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVaccineId());
    }


}
