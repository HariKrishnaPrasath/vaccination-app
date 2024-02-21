package com.jpa.vaccinationapp.appointment.service;

import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.appointment.AppointmentException;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Override
    public Appointment bookAnAppointment(Appointment appointment) throws AppointmentException {
        return null;
    }
}
