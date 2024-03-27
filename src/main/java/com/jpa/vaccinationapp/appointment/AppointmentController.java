package com.jpa.vaccinationapp.appointment;

import com.jpa.vaccinationapp.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/appointments")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @PostMapping("/add")
    public Appointment bookAppointment(@RequestBody Appointment appointment) throws AppointmentException {
        return this.appointmentService.bookAnAppointment(appointment);
    }
    @GetMapping("/{bookingId}")
    public Appointment getAppointmentById(@PathVariable Integer bookingId) throws AppointmentException {
        return this.appointmentService.getAppointmentById(bookingId);
    }
    @GetMapping("/")
    public List<Appointment> getAllAppointments() throws AppointmentException{
        return this.appointmentService.getAllAppointments();
    }
    @GetMapping("/vaccinationCenters/{centerId}")
    public List<Appointment>  getAppointmentsForVaccinationCenter(@PathVariable Integer centerId) throws AppointmentException {
        return this.appointmentService.getAppointmentsForVaccinationCenter(centerId);
    }
    @DeleteMapping("delete/{bookingId}")
    public Appointment deleteAppointmentById(@PathVariable Integer bookingId) throws AppointmentException{
        return this.appointmentService.deleteAppointmentById(bookingId);
    }
    @GetMapping("/id")
    public List<Appointment> getAppointmentByPatient(@RequestParam Integer patientId) throws AppointmentException {
        return this.appointmentService.getAppointmentByPatient(patientId);
    }
    @PutMapping("/vaccinationStatus")
    public Appointment updateAppointmentStatus(@RequestBody VaccinationStatusDTO vaccinationStatusDTO)
            throws AppointmentException {
        System.out.println(vaccinationStatusDTO.getIsVaccinated() + " hari " +vaccinationStatusDTO.getAppointmentId());
        return this.appointmentService.updateAppointmentStatus(vaccinationStatusDTO);
    }
}
