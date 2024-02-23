package com.jpa.vaccinationapp.appointment.service;

import com.jpa.vaccinationapp.admin.AdminRepository;
import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.appointment.AppointmentException;
import com.jpa.vaccinationapp.appointment.AppointmentRepository;
import com.jpa.vaccinationapp.appointment.VaccinationStatusDTO;
import com.jpa.vaccinationapp.patient.Patient;
import com.jpa.vaccinationapp.patient.PatientRepository;
import com.jpa.vaccinationapp.slot.Slot;
import com.jpa.vaccinationapp.slot.SlotRepository;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccinationCenter.CenterRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final SlotRepository slotRepository;
    private final CenterRepository centerRepository;
    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
                                  SlotRepository slotRepository, CenterRepository centerRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.slotRepository = slotRepository;
        this.centerRepository = centerRepository;
    }

    @Override
    public Appointment bookAnAppointment(Appointment appointment) throws AppointmentException {
        if (appointment == null)
            throw new AppointmentException("Appointment can't be null");
        Optional<Appointment> appointmentOptional =
                this.appointmentRepository.findByBookingDateAndPatient(appointment.getBookingDate(), appointment.getPatient());
        if (appointmentOptional.isPresent())
            throw new AppointmentException("Appointment Already exists'");
        return this.appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(Integer bookingId) throws AppointmentException {
        if (bookingId == null)
            throw new AppointmentException("Id can't be null");
        Optional<Appointment> appointmentOpt = this.appointmentRepository.findById(bookingId);
        if (appointmentOpt.isEmpty())
            throw new AppointmentException("No such appointment found for the given id");
        return appointmentOpt.get();
    }

    @Override
    public List<Appointment> getAllAppointments() throws AppointmentException {
        List<Appointment> appointmentList = this.appointmentRepository.findAll();
        if (appointmentList.isEmpty())
            throw new AppointmentException("No appointment found");
        return appointmentList;
    }

    @Override
    public List<Appointment> getAppointmentsForVaccinationCenter(Integer centerId) throws AppointmentException {
        if (centerId == null)
            throw new AppointmentException("Id can't be null");
        Optional<Center> centerOptional = this.centerRepository.findById(centerId);
        if (centerOptional.isEmpty())
            throw new AppointmentException("No such center found");
        return this.appointmentRepository.findByVaccinationCenter(centerOptional.get());
    }

    @Override
    public Appointment deleteAppointmentById(Integer bookingId) throws AppointmentException {
        if (bookingId == null)
            throw new AppointmentException("Id can't be null");
        Optional<Appointment> appointmentOptional = this.appointmentRepository.findById(bookingId);
        if (appointmentOptional.isEmpty())
            throw new AppointmentException("Appointment not found");
        this.appointmentRepository.delete(appointmentOptional.get());
        return appointmentOptional.get();
    }

    @Override
    public List<Appointment> getAppointmentByPatient(Integer patientId) throws AppointmentException {
        if(patientId == null)
            throw new AppointmentException("Patient Id can't be null");
        Optional<Patient> patient = this.patientRepository.findById(patientId);
        List<Appointment> appointmentList = this.appointmentRepository.findByPatient(patient.get());
        if (appointmentList.isEmpty())
            throw new AppointmentException("No appointments found for the given patient");
        return appointmentList;
    }

    @Override
    public Appointment updateAppointmentStatus(VaccinationStatusDTO vaccinationStatusDTO) throws AppointmentException {
        if(vaccinationStatusDTO == null)
            throw new AppointmentException("Input can't be null");
        if (vaccinationStatusDTO.getAppointmentId() == null)
            throw new AppointmentException("Appointment Id can't be null");
        Optional<Appointment> appointmentOptional =
                this.appointmentRepository.findById(vaccinationStatusDTO.getAppointmentId());
        if (appointmentOptional.isEmpty())
            throw new AppointmentException("Appointment not found");
        Appointment appointment = appointmentOptional.get();
        appointment.setVaccineStatus(vaccinationStatusDTO.getVaccinated());
        return this.appointmentRepository.save(appointment);
    }

}
