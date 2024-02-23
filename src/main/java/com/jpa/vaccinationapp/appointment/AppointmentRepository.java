package com.jpa.vaccinationapp.appointment;

import com.jpa.vaccinationapp.patient.Patient;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Optional<Appointment> findByBookingDateAndPatient(LocalDate bookingDate, Patient patient);
    @Query("SELECT a FROM Appointment a JOIN a.slot s WHERE s.center = :center")
    List<Appointment> findByVaccinationCenter(Center center);

    List<Appointment> findByPatient(Patient patient);
}
