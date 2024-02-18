package com.jpa.vaccinationapp.bookingDetail.entity;

import com.jpa.vaccinationapp.certificate.entity.Certificate;
import com.jpa.vaccinationapp.vaccinationCenter.entity.VaccinationCenter;
import com.jpa.vaccinationapp.vaccine.entity.Vaccine;
import com.jpa.vaccinationapp.patient.entity.Patient;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BookingDetails {
    @Id
    @GeneratedValue
    private Integer bookingId;
    private Boolean vaccineStatus;
    @OneToOne
    private Vaccine vaccine;
    @OneToOne
    private Certificate certificate;
    @ManyToOne
    private VaccinationCenter vaccinationCenter;
    @OneToOne
    private Patient patient;
    private LocalDate bookingDate;

    public BookingDetails() {
    }

    public BookingDetails(Integer bookingId, Boolean vaccineStatus, Vaccine vaccine, Certificate certificate,
                          VaccinationCenter vaccinationCenter, Patient patient, LocalDate bookingDate) {
        this.bookingId = bookingId;
        this.vaccineStatus = vaccineStatus;
        this.vaccine = vaccine;
        this.certificate = certificate;
        this.vaccinationCenter = vaccinationCenter;
        this.patient = patient;
        this.bookingDate = bookingDate;
    }

    public BookingDetails(Boolean vaccineStatus, Vaccine vaccine, Certificate certificate,
                          VaccinationCenter vaccinationCenter, Patient patient, LocalDate bookingDate) {
        this.vaccineStatus = vaccineStatus;
        this.vaccine = vaccine;
        this.certificate = certificate;
        this.vaccinationCenter = vaccinationCenter;
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

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
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
