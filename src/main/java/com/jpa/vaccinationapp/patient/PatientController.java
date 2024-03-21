package com.jpa.vaccinationapp.patient;

import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("patient")
    public Patient signUp(@RequestBody Patient patient) throws PatientException {
        return this.patientService.createPatient(patient);
    }

    //    @PostMapping("patient/login")
//    public Patient patientLogin(@RequestBody Login login) throws PatientException {
//        return this.patientService.logIn(login);
//    }
    @GetMapping("patient/{id}")
    public Patient displayPatientById(@PathVariable("id") Integer patientId) throws PatientException {
        return this.patientService.displayPatientInfo(patientId);
    }

    @PutMapping("patient/update")
    public Patient updatePatientInfo(@RequestBody Patient patient) throws PatientException {
        return this.patientService.updatePatientInfo(patient);
    }

    @DeleteMapping("patient/delete/{id}")
    public Patient deletePatient(@PathVariable("id") Integer patientId) throws PatientException {
        return this.patientService.deletePatientInfo(patientId);
    }

    @GetMapping("patients")
    public List<Patient> getAllPatients() {
        return this.patientService.getAllPatients();
    }

    @GetMapping("patient/appointment/{id}")
    public List<Appointment> getBookedAppointments(@PathVariable("id") Integer patientId) throws PatientException {
        return this.patientService.getPatientAppointmentDetails(patientId);
    }
}
