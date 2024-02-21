package com.jpa.vaccinationapp.appointment.service;

import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.appointment.AppointmentException;

public interface AppointmentService {
    Appointment bookAnAppointment(Appointment appointment) throws AppointmentException;
}