package com.jpa.vaccinationapp.patientTest;

import com.jpa.vaccinationapp.appointment.AppointmentRepository;
import com.jpa.vaccinationapp.patient.Patient;
import com.jpa.vaccinationapp.patient.PatientDto;
import com.jpa.vaccinationapp.patient.PatientException;
import java.util.List;
import com.jpa.vaccinationapp.patient.PatientRepository;
import com.jpa.vaccinationapp.patient.service.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatientServiceTest {
    @Autowired
    PatientServiceImpl patientService;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AppointmentRepository appointmentRepository;

    @Test
    void createPatientTest(){
        Patient patient = new Patient("vandu@gmail.com","9845323","vandu@123",
                "dubaikurukkusandhu","vandumurugan");
        Patient newPatient = null;
        try{
            newPatient = this.patientService.createPatient(patient);
            Assertions.assertNotNull(newPatient);
        }
        catch (PatientException e){
            Assertions.fail(e.getMessage());
        }
        finally {
            this.patientRepository.delete(newPatient);
        }
    }
    @Test
    void displayPatientInfo(){
        Patient patient = new Patient("vandu@gmail.com","9845323","vandu@123",
                "dubaikurukkusandhu","vandumurugan");
        Patient newPatient = null;
        try {
            newPatient = this.patientService.createPatient(patient);
            Assertions.assertNotNull(newPatient);
            Patient existingPatient = this.patientService.displayPatientInfo(newPatient.getPatientId());

            PatientDto newPatientDTO = new PatientDto(newPatient);
            PatientDto getPatient = new PatientDto(existingPatient);

            Assertions.assertEquals(getPatient, newPatientDTO);

        } catch (PatientException e) {
            Assertions.fail(e.getMessage());
        }
        this.patientRepository.delete(newPatient);
    }
    @Test
    void updatePatientTest(){
        Patient patient = new Patient("vandu@gmail.com","9845323","vandu@123",
                "dubaikurukkusandhu","vandumurugan");
        try{
            Patient newPatient = this.patientService.createPatient(patient);
            Assertions.assertNotNull(newPatient);
            newPatient.setPatientName("sandhumurugan");
            Patient updatedPatient = this.patientService.updatePatientInfo(newPatient);
            Assertions.assertNotNull(updatedPatient);
            Assertions.assertEquals(updatedPatient.getPatientName(),patient.getPatientName());
        } catch (PatientException e) {
            Assertions.fail(e.getMessage());
        }
        this.patientRepository.delete(patient);
    }
    @Test
    void updatePatientExceptionTest(){
        Patient patient2 = new Patient(500,"gundu@gmail.com","123456",
                "gundu@123","vadakkupatti","gundumurugan",null,null);
        Assertions.assertThrows(PatientException.class,()->this.patientService.updatePatientInfo(patient2));
    }
    @Test
    void deletePatientTest(){
        Patient patient = new Patient("vandu@gmail.com","9845323","vandu@123",
                "dubaikurukkusandhu","vandumurugan");
        try{
            Patient newPatient = this.patientService.createPatient(patient);
            Assertions.assertNotNull(newPatient);
            Patient deletedPatient = this.patientService.deletePatientInfo(newPatient.getPatientId());
            Assertions.assertNotNull(deletedPatient);
            Assertions.assertEquals(deletedPatient.getPatientId(),newPatient.getPatientId());
        }
        catch(PatientException e){
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    void deletePatientExceptionTest(){
        Assertions.assertThrows(PatientException.class,()->this.patientService.deletePatientInfo(null));
    }
    @Test
    void getAllPatientTest(){
        Patient patientOne = new Patient("vandu@gmail.com","9845323","vandu@123",
                "dubaikurukkusandhu","vandumurugan");
        Patient patientTwo = new Patient("sandhu@gmail.com","123621","sandhu@123",
                "vandaloorvadakkusandhu","sandhumurugan");
        try{
            Patient newPatientOne = this.patientService.createPatient(patientOne);
            Assertions.assertNotNull(newPatientOne);
            Patient newPatientTwo = this.patientService.createPatient(patientTwo);
            Assertions.assertNotNull(newPatientTwo);
            List<Patient> patientList = this.patientService.getAllPatients();
            Assertions.assertNotNull(patientList);
            Assertions.assertEquals(patientList.size(),2);
        } catch (PatientException e) {
            Assertions.fail(e);
        }
        this.patientRepository.deleteAll();
    }
}
