package com.jpa.vaccinationapp.patient.service;

import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.certificate.Certificate;
import com.jpa.vaccinationapp.patient.Login;
import com.jpa.vaccinationapp.patient.Patient;
import com.jpa.vaccinationapp.patient.PatientException;
import com.jpa.vaccinationapp.vaccinationCenter.VaccinationCenter;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import com.jpa.vaccinationapp.vaccine.VaccineException;

import java.util.Date;
import java.util.List;

public interface PatientService {
    Patient createPatient(Patient patient) throws PatientException;
    Patient logIn(Login login) throws PatientException;
    Patient displayPatientInfo(Integer patientId) throws PatientException;
    Patient updatePatientInfo(Patient patient) throws PatientException;
    Patient deletePatientInfo(Integer patientId) throws PatientException;
    List<VaccinationCenter> getAllCentres() throws PatientException;
    List<Vaccine> getAllVaccines() throws VaccineException;
    List<Patient> getAllPatients();
    List<Appointment> getPatientAppointmentDetails(Integer patientId) throws PatientException;
    List<Appointment> getPatientAppointmentDetailsByVaccine(Integer patientId,Vaccine vaccine) throws PatientException;
    List<Appointment> getPatientAppointmentDetailsByDate(Integer patientId, Date date) throws PatientException;
    List<Appointment> getPatientAppointmentDetailsByCentre(Integer patientId, VaccinationCenter vaccinationCenter) throws PatientException;
    List<Certificate> getPatientCertificates(Integer patientId) throws PatientException;
    List<Certificate> getPatientCertificatesByDate(Integer patientId, Date date) throws PatientException;
    List<Certificate> getPatientCertificatesByVaccine(Integer patientId, Vaccine vaccine) throws PatientException;
    List<Certificate> getPatientCertificatesByCentre(Integer patientId, VaccinationCenter vaccinationCenter) throws PatientException;
}
