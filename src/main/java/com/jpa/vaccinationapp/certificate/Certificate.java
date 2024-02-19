package com.jpa.vaccinationapp.certificate;

import com.jpa.vaccinationapp.appointment.Appointment;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Certificate {
    @Id
    @GeneratedValue(generator = "100")
    private Integer certificateId;
    @OneToOne
    private Appointment appointment;
    private LocalDate dateOfVaccination;
    private String certificateUrl;

    public Certificate() {
    }

    public Certificate(Integer certificateId, Appointment appointment,
                       LocalDate dateOfVaccination, String certificateUrl) {
        this.certificateId = certificateId;
        this.appointment = appointment;
        this.dateOfVaccination = dateOfVaccination;
        this.certificateUrl = certificateUrl;
    }

    public Certificate(Appointment appointment,
                       LocalDate dateOfVaccination, String certificateUrl) {
        this.appointment = appointment;
        this.dateOfVaccination = dateOfVaccination;
        this.certificateUrl = certificateUrl;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Appointment getBookingDetails() {
        return appointment;
    }

    public void setBookingDetails(Appointment appointment) {
        this.appointment = appointment;
    }

    public LocalDate getDateOfVaccination() {
        return dateOfVaccination;
    }

    public void setDateOfVaccination(LocalDate dateOfVaccination) {
        this.dateOfVaccination = dateOfVaccination;
    }

    public String getCertificateUrl() {
        return certificateUrl;
    }

    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }
}
