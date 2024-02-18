package com.jpa.vaccinationapp.certificate.entity;

import com.jpa.vaccinationapp.bookingDetail.entity.BookingDetails;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Certificate {
    @Id
    @GeneratedValue(generator = "100")
    private Integer certificateId;
    @OneToOne
    private BookingDetails bookingDetails;
    private LocalDate dateOfVaccination;
    private String certificateUrl;

    public Certificate() {
    }

    public Certificate(Integer certificateId, BookingDetails bookingDetails,
                       LocalDate dateOfVaccination, String certificateUrl) {
        this.certificateId = certificateId;
        this.bookingDetails = bookingDetails;
        this.dateOfVaccination = dateOfVaccination;
        this.certificateUrl = certificateUrl;
    }

    public Certificate(BookingDetails bookingDetails,
                       LocalDate dateOfVaccination, String certificateUrl) {
        this.bookingDetails = bookingDetails;
        this.dateOfVaccination = dateOfVaccination;
        this.certificateUrl = certificateUrl;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public BookingDetails getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(BookingDetails bookingDetails) {
        this.bookingDetails = bookingDetails;
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
