package com.jpa.vaccinationapp.patient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpa.vaccinationapp.appointment.Appointment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number format")
    private String phoneNumber;
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", message = "Must contain at least one  number and one uppercase and lowercase letter, and at least 8 or more characters")
    private String password;
    private String address;
    @NotBlank(message = "Name cant be null, it should contain chars")
    @Pattern(regexp = "[a-zA-Z ]{3,16}", message = "Name should contain min 3 & max 16 chars , no digits and special chars allowed.")
    private String patientName;
    @CreatedDate
    private LocalDate registrationDate;
    @OneToMany(mappedBy = "patient",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    @Column(nullable = true)
    private Set<Appointment> bookingDetails = new HashSet<>();

    public Patient() {
    }

    public Patient(Integer patientId, String email, String phoneNumber, String password, String address,
                   String patientName, LocalDate registrationDate, Set<Appointment> bookingDetails) {
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
                   LocalDate registrationDate, Set<Appointment> bookingDetails) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.patientName = patientName;
        this.registrationDate = registrationDate;
        this.bookingDetails = bookingDetails;
    }

    public Patient(String email, String phoneNumber, String password, String address, String patientName) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.patientName = patientName;
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

    public Set<Appointment> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(Set<Appointment> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public void addAppointment(Appointment appointment) {
        this.bookingDetails.add(appointment);
        appointment.setPatient(this);
    }
}
