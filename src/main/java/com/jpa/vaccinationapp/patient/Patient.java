package com.jpa.vaccinationapp.patient;

import com.jpa.vaccinationapp.appointment.Appointment;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Patient {
    @Id
    @GeneratedValue(generator = "100")
    private Integer patientId;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
    private String patientName;
    @CreatedDate
    private LocalDate registrationDate;
    @OneToMany
    private Map<Integer, Appointment> bookingDetails = new HashMap<>();

    public Patient() {
    }

    public Patient(Integer patientId, String email, String phoneNumber, String password, String address,
                   String patientName, LocalDate registrationDate, Map<Integer, Appointment> bookingDetails) {
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
                   LocalDate registrationDate, Map<Integer, Appointment> bookingDetails) {
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

    public Map<Integer, Appointment> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(Map<Integer, Appointment> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }
}
