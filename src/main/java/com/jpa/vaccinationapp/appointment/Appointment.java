package com.jpa.vaccinationapp.appointment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpa.vaccinationapp.certificate.Certificate;
import com.jpa.vaccinationapp.slot.Slot;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.patient.Patient;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private Integer bookingId;
    private Boolean vaccineStatus;
    @OneToOne(cascade = CascadeType.ALL)
    private Slot slot;
    @OneToOne(cascade = CascadeType.ALL)
    private Certificate certificate;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Patient patient;
    @ManyToOne(cascade = CascadeType.ALL)
    private Vaccine vaccine;
    private LocalDate bookingDate;

    public Appointment() {
    }

    public Appointment(Integer bookingId, Boolean vaccineStatus, Slot slot, Certificate certificate,
                       Patient patient, LocalDate bookingDate,Vaccine vaccine) {
        this.bookingId = bookingId;
        this.vaccineStatus = vaccineStatus;
        this.slot = slot;
        this.certificate = certificate;
        this.patient = patient;
        this.bookingDate = bookingDate;
        this.vaccine = vaccine;
    }

    public Appointment(Boolean vaccineStatus, Slot slot,Vaccine vaccine, Certificate certificate, Patient patient, LocalDate bookingDate) {
        this.vaccineStatus = vaccineStatus;
        this.slot = slot;
        this.certificate = certificate;
        this.patient = patient;
        this.bookingDate = bookingDate;
        this.vaccine=vaccine;
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

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
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

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }
}
