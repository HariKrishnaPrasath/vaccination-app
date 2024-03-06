package com.jpa.vaccinationapp.patient.service;

import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.certificate.Certificate;
import com.jpa.vaccinationapp.patient.Login;
import com.jpa.vaccinationapp.patient.Patient;
import com.jpa.vaccinationapp.patient.PatientException;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import com.jpa.vaccinationapp.vaccine.VaccineException;

import java.util.Date;
import java.util.List;

public interface PatientService {
    Patient createPatient(Patient patient) throws PatientException;
    //Patient logIn(Login login) throws PatientException;
    Patient displayPatientInfo(Integer patientId) throws PatientException;
    Patient updatePatientInfo(Patient patient) throws PatientException;
    Patient deletePatientInfo(Integer patientId) throws PatientException;
    List<Patient> getAllPatients();
    List<Appointment> getPatientAppointmentDetails(Integer patientId) throws PatientException;
}
