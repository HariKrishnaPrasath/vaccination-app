package com.jpa.vaccinationapp.slot;

import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
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
    private Center center;

    @OneToMany
    private List<Appointment> appointments;
    public Slot(Long id, LocalDateTime startTime, LocalDateTime endTime,
                Integer availableSlots, Center center,
                List<Appointment> appointments) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableSlots = availableSlots;
        this.center = center;
        this.appointments = appointments;
    }

    public Slot(LocalDateTime startTime, LocalDateTime endTime, Integer availableSlots,
                Center center,
                List<Appointment> appointments) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableSlots = availableSlots;
        this.center = center;
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

    public Center getVaccinationCenter() {
        return center;
    }

    public void setVaccinationCenter(Center center) {
        this.center = center;
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

