package com.jpa.vaccinationapp.appointment;

import com.jpa.vaccinationapp.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @PostMapping("/add")
    public Appointment bookAnAppointment(@RequestBody Appointment appointment) throws AppointmentException {
        return this.appointmentService.bookAnAppointment(appointment);
    }
}
