package com.jpa.vaccinationapp.appointment.service;

import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.appointment.AppointmentException;
import com.jpa.vaccinationapp.appointment.VaccinationStatusDTO;

import java.util.List;

public interface AppointmentService {
    Appointment bookAnAppointment(Appointment appointment) throws AppointmentException;

    Appointment getAppointmentById(Integer bookingId) throws AppointmentException;

    List<Appointment> getAllAppointments() throws AppointmentException;

    List<Appointment> getAppointmentsForVaccinationCenter(Integer centerId) throws AppointmentException;

    Appointment deleteAppointmentById(Integer bookingId) throws AppointmentException;

    List<Appointment> getAppointmentByPatient(Integer patientId) throws AppointmentException;

    Appointment updateAppointmentStatus(VaccinationStatusDTO vaccinationStatusDTO) throws AppointmentException;
}