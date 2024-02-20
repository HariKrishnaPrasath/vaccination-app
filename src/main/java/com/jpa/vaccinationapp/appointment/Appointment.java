package com.jpa.vaccinationapp.appointment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpa.vaccinationapp.certificate.Certificate;
import com.jpa.vaccinationapp.slot.Slot;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.patient.Patient;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private Integer bookingId;
    private Boolean vaccineStatus;
    @OneToOne
    private Slot slot;
    @OneToOne
    private Certificate certificate;
    @ManyToOne
    private Center center;
    @OneToOne
    @JsonIgnore
    private Patient patient;
    private LocalDate bookingDate;

    public Appointment() {
    }

    public Appointment(Integer bookingId, Boolean vaccineStatus, Slot slot, Certificate certificate,
                       Center center, Patient patient, LocalDate bookingDate) {
        this.bookingId = bookingId;
        this.vaccineStatus = vaccineStatus;
        this.slot = slot;
        this.certificate = certificate;
        this.center = center;
        this.patient = patient;
        this.bookingDate = bookingDate;
    }

    public Appointment(Boolean vaccineStatus, Slot slot, Certificate certificate,
                       Center center, Patient patient, LocalDate bookingDate) {
        this.vaccineStatus = vaccineStatus;
        this.slot = slot;
        this.certificate = certificate;
        this.center = center;
        this.patient = patient;
        this.bookingDate = bookingDate;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Boolean getVaccineStatus() {
        return vaccineStatus;
    }

    public void setVaccineStatus(Boolean vaccineStatus) {
        this.vaccineStatus = vaccineStatus;
    }

    public Slot getVaccine() {
        return slot;
    }

    public void setVaccine(Slot slot) {
        this.slot = slot;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Center getVaccinationCenter() {
        return center;
    }

    public void setVaccinationCenter(Center center) {
        this.center = center;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}
