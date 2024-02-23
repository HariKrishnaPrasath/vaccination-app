package com.jpa.vaccinationapp.patient;

import com.jpa.vaccinationapp.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    @PostMapping("patient/login")
    public Patient patientLogin(@RequestBody Login login) throws PatientException {
        return this.patientService.logIn(login);
    }
    @GetMapping("patient/{id}")
    public Patient displayPatientById(@PathVariable("id") Integer patientId) throws PatientException {
        return this.patientService.displayPatientInfo(patientId);
    }
    @PutMapping("patient/update")
    public Patient updatePatientInfo(@RequestBody Patient patient) throws PatientException {
        return  this.patientService.updatePatientInfo(patient);
    }
    @DeleteMapping("patient/delete/{id}")
    public Patient deletePatient(@PathVariable("id") Integer patientId) throws PatientException {
        return this.patientService.deletePatientInfo(patientId);
    }
    @GetMapping("patients")
    public List<Patient> getAllPatients(){
        return this.patientService.getAllPatients();
    }
//    @GetMapping("patient/centres")
//    public List<VaccinationCenter> getCentres() throws PatientException {
//        return this.patientService.getAllCentres();
//    }
//    @GetMapping("patient/vaccines")
//    public List<Vaccine> getVaccines() throws VaccineException {
//        return this.patientService.getAllVaccines();
//    }
//    @GetMapping("patient/appointment/{id}")
//    public List<Appointment> getBookedAppointments(@PathVariable("id") Integer patientId) throws PatientException {
//        return this.patientService.getPatientAppointmentDetails(patientId);
//    }
//    @GetMapping("patient/appointment/vaccine/{id}/{vaccine}")
//    public List<Appointment> getBookedAppointmentsByVaccine(@PathVariable("id") Integer patientId,@PathVariable Vaccine vaccine) throws PatientException {
//        return this.patientService.getPatientAppointmentDetailsByVaccine(patientId,vaccine);
//    }
//    @GetMapping("patient/appointment/centre/{id}/{centre}")
//    public List<Appointment> getBookedAppointmentsByCentre(@PathVariable("id") Integer patientId,@PathVariable VaccinationCenter centre) throws PatientException {
//        return this.patientService.getPatientAppointmentDetailsByCentre(patientId,centre);
//    }
//    @GetMapping("patient/appointment/date/{id}/{date}")
//    public List<Appointment> getBookedAppointmentsByDate(@PathVariable("id") Integer patientId,@PathVariable Date date) throws PatientException {
//        return this.patientService.getPatientAppointmentDetailsByDate(patientId,date);
//    }
//    @GetMapping("patient/certificate/{id}")
//    public List<Certificate> getPatientCertificates(@PathVariable("id") Integer id) throws PatientException {
//        return this.patientService.getPatientCertificates(id);
//    }
//    @GetMapping("patient/certificate/date/{id}/{date}")
//    public List<Certificate> getCertificateByDate(@PathVariable("id") Integer patientId,@PathVariable Date date) throws PatientException {
//        return this.patientService.getPatientCertificatesByDate(patientId,date);
//    }
//    @GetMapping("patient/certificate/centre/{id}/{centre}")
//    public List<Certificate> getCertificateByCentre(@PathVariable("id") Integer patientId,@PathVariable VaccinationCenter centre) throws PatientException {
//        return this.patientService.getPatientCertificatesByCentre(patientId,centre);
//    }
//    @GetMapping("patient/certificate/vaccine/{id}/{vaccine}")
//    public List<Certificate> getCertificateByVaccine(@PathVariable("id") Integer patientId,@PathVariable Vaccine vaccine) throws PatientException {
//        return this.patientService.getPatientCertificatesByVaccine(patientId,vaccine);
//    }
}
