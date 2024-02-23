package com.jpa.vaccinationapp.patient.service;

import com.jpa.vaccinationapp.patient.Login;
import com.jpa.vaccinationapp.patient.PatientException;
import com.jpa.vaccinationapp.patient.PatientRepository;
import com.jpa.vaccinationapp.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient createPatient(Patient patient) throws PatientException {
        if (patient == null) throw new PatientException("Patient cant be null");
        Optional<Patient> patient1 = this.patientRepository.findByEmail(patient.getEmail());
        if (patient1.isPresent()) throw new PatientException("email " + patient.getEmail() + "already exists");
        return this.patientRepository.save(patient);
    }

    @Override
    public Patient logIn(Login login) throws PatientException {
        if (login == null) throw new PatientException("user and password not found");
        Optional<Patient> patient = this.patientRepository.findByEmail(login.getEmail());
        if (patient.isEmpty()) throw new PatientException("no user found");
        if (!Objects.equals(login.getPassword(), patient.get().getPassword())) throw new
                PatientException("userid or password incorrect");
        return patient.get();
    }

    @Override
    public Patient displayPatientInfo(Integer patientId) throws PatientException {
        if (patientId == null) throw new PatientException("patient ID not entered");
        Optional<Patient> patient = this.patientRepository.findById(patientId);
        if (patient.isEmpty()) throw new PatientException("user with userID" + patientId + " not found");
        return this.patientRepository.getReferenceById(patientId);
    }

    @Override
    public Patient updatePatientInfo(Patient patient) throws PatientException {
        if (patient == null) throw new PatientException("Patient cant be null");
        Optional<Patient> patient1 = this.patientRepository.findById(patient.getPatientId());
        if (patient1.isEmpty())
            throw new PatientException("user with userID :" + patient.getPatientId() + " not found");
        return this.patientRepository.save(patient);
    }

    @Override
    public Patient deletePatientInfo(Integer patientId) throws PatientException {
        if (patientId == null) throw new PatientException("Patient ID cant be null");
        Optional<Patient> patient = this.patientRepository.findById(patientId);
        if (patient.isEmpty()) throw new PatientException("user with userID" + patientId + " not found");
        this.patientRepository.delete(patient.get());
        return patient.get();
    }
//    @Override
//    public List<Center> getAllCentres(){
//        return this.vaccinationCenterRepository.findAll();
//    }
//    @Override
//    public List<Vaccine> getAllVaccines() throws VaccineException {
//        List<Vaccine> vaccineList = this.vaccineRepository.findAll();
//        if(vaccineList.isEmpty()) throw new VaccineException("no vaccines found");
//        return vaccineList;
//    }

    @Override
    public List<Patient> getAllPatients() {
        return this.patientRepository.findAll();
    }
}

    //@Override
//    public List<Appointment> getPatientAppointmentDetails(Integer patientId) throws PatientException {
//        Optional<Patient> patient = this.patientRepository.findById(patientId);
//        if(patient.isEmpty()) throw new PatientException("patient with id "+patientId+" not found");
//        if(patient.get().getBookingDetails().isEmpty()) throw new PatientException("no booking done");
//        return patient.get().getBookingDetails().values().stream().toList();
//    }

//    @Override
//    public List<Appointment> getPatientAppointmentDetailsByVaccine(Integer patientId, Vaccine vaccine) throws PatientException {
//        Optional<Patient> patient = this.patientRepository.findById(patientId);
//        if(patient.isEmpty()) throw new PatientException("patient with id "+patientId+" not found");
//        if(patient.get().getBookingDetails().isEmpty()) throw new PatientException("no booking done");
//        return patient.get().getBookingDetails().values().stream().filter((a)->a.getVaccine().equals(vaccine)).toList();
//    }
//
//    @Override
//    public List<Appointment> getPatientAppointmentDetailsByDate(Integer patientId, Date date) throws PatientException {
//        Optional<Patient> patient = this.patientRepository.findById(patientId);
//        if(patient.isEmpty()) throw new PatientException("patient with id "+patientId+" not found");
//        if(patient.get().getBookingDetails().isEmpty()) throw new PatientException("no booking done");
//        return patient.get().getBookingDetails().values().stream().filter((a)->a.getBookingDate().equals(date)).toList();
//    }
//
//    @Override
//    public List<Appointment> getPatientAppointmentDetailsByCentre(Integer patientId, Center vaccinationCenter) throws PatientException {
//        Optional<Patient> patient = this.patientRepository.findById(patientId);
//        if(patient.isEmpty()) throw new PatientException("patient with id "+patientId+" not found");
//        if(patient.get().getBookingDetails().isEmpty()) throw new PatientException("no booking done");
//        return patient.get().getBookingDetails().values().stream().filter((a)->a.getSlot().getCenter().getCenterId().equals(vaccinationCenter.getCenterId())).toList();
//    }
//
//    @Override
//    public List<Certificate> getPatientCertificates(Integer patientId) throws PatientException {
//        Optional<Patient> patient = this.patientRepository.findById(patientId);
//        if(patient.isEmpty()) throw new PatientException("patient with id "+patientId+" not found");
//        return patient.get().getBookingDetails().values().stream().map((a)->a.getCertificate()).toList();
//    }
//
//    @Override
//    public List<Certificate> getPatientCertificatesByDate(Integer patientId, Date date) throws PatientException {
//        Optional<Patient> patient = this.patientRepository.findById(patientId);
//        if(patient.isEmpty()) throw new PatientException("patient with id "+patientId+" not found");
//        return patient.get().getBookingDetails().values().stream().filter((a)->a.getBookingDate().equals(date)).map((a)->a.getCertificate()).toList();
//    }
//
//    @Override
//    public List<Certificate> getPatientCertificatesByVaccine(Integer patientId, Vaccine vaccine) throws PatientException {
//        Optional<Patient> patient = this.patientRepository.findById(patientId);
//        if(patient.isEmpty()) throw new PatientException("patient with id "+patientId+" not found");
//        return patient.get().getBookingDetails().values().stream().filter((a)->a.getVaccine().equals(vaccine)).map((a)->a.getCertificate()).toList();
//    }
//
//    @Override
//    public List<Certificate> getPatientCertificatesByCentre(Integer patientId, Center vaccinationCenter) throws PatientException {
//        Optional<Patient> patient = this.patientRepository.findById(patientId);
//        if(patient.isEmpty()) throw new PatientException("patient with id "+patientId+" not found");
//        return patient.get().getBookingDetails().values().stream().filter((a)->a.getSlot().getCenter().getCenterId().equals(vaccinationCenter.getCenterId())).map((a)->a.getCertificate()).toList();
//    }
//}
