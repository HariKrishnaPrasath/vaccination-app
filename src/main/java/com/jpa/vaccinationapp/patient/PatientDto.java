package com.jpa.vaccinationapp.patient;

import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.utils.CommonUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PatientDto {
    private Integer patientId;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
    private String patientName;
    private LocalDate registrationDate;
    private Set<Appointment> bookingDetails;

    public PatientDto() {
    }

    public PatientDto(Patient patient) {
        CommonUtils.copyAllProperties(patient, this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDto that = (PatientDto) o;
        return Objects.equals(patientId, that.patientId) && Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(password, that.password) &&
                Objects.equals(address, that.address) && Objects.equals(patientName, that.patientName) &&
                Objects.equals(registrationDate, that.registrationDate) &&
                Objects.equals(bookingDetails, that.bookingDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, email, phoneNumber, password, address, patientName, registrationDate, bookingDetails);
    }
}
