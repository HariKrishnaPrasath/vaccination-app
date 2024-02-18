package com.jpa.vaccinationapp.patient.entity;

import com.jpa.vaccinationapp.bookingDetail.entity.BookingDetails;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Patient {
    @Id
    @GeneratedValue(generator = "100")
    private Integer patientId;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
    private String patientName;
    private LocalDate registrationDate;
    @OneToMany
    private Map<Integer, BookingDetails> bookingDetails;

    public Patient() {
    }

    public Patient(Integer patientId, String email, String phoneNumber, String password, String address,
                   String patientName, LocalDate registrationDate, Map<Integer, BookingDetails> bookingDetails) {
        this.patientId = patientId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.patientName = patientName;
        this.registrationDate = registrationDate;
        this.bookingDetails = bookingDetails;
    }

    public Patient(String email, String phoneNumber, String password, String address, String patientName,
                   LocalDate registrationDate, Map<Integer, BookingDetails> bookingDetails) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.patientName = patientName;
        this.registrationDate = registrationDate;
        this.bookingDetails = bookingDetails;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Map<Integer, BookingDetails> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(Map<Integer, BookingDetails> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }
}
