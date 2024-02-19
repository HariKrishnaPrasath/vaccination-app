package com.jpa.vaccinationapp.slot;

import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.vaccinationCenter.VaccinationCenter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Integer availableSlots; // The number of available slots for this time period

    @ManyToOne
    private VaccinationCenter vaccinationCenter;

    @OneToMany
<<<<<<< HEAD:src/main/java/com/jpa/vaccinationapp/slot/entity/Slot.java
    private List<BookingDetails> appointments;
=======
    private List<Appointment> appointments;
>>>>>>> a6fa418219e2c35b74a3a4ceab6f5fa956b7e1d6:src/main/java/com/jpa/vaccinationapp/slot/Slot.java

    public Slot(Long id, LocalDateTime startTime, LocalDateTime endTime,
                Integer availableSlots, VaccinationCenter vaccinationCenter,
                List<Appointment> appointments) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableSlots = availableSlots;
        this.vaccinationCenter = vaccinationCenter;
        this.appointments = appointments;
    }

    public Slot(LocalDateTime startTime, LocalDateTime endTime, Integer availableSlots,
                VaccinationCenter vaccinationCenter,
                List<Appointment> appointments) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableSlots = availableSlots;
        this.vaccinationCenter = vaccinationCenter;
        this.appointments = appointments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
    }

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Slot() {

    }
}

