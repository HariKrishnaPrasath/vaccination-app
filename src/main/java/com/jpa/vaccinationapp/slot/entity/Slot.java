package com.jpa.vaccinationapp.slot.entity;

import com.jpa.vaccinationapp.bookingDetail.dao.BookingDetail;
import com.jpa.vaccinationapp.bookingDetail.entity.BookingDetails;
import com.jpa.vaccinationapp.vaccinationCenter.entity.VaccinationCenter;
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
    private List<BookingDetails> appointments;

    public Slot(Long id, LocalDateTime startTime, LocalDateTime endTime,
                Integer availableSlots, VaccinationCenter vaccinationCenter,
                List<BookingDetails> appointments) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableSlots = availableSlots;
        this.vaccinationCenter = vaccinationCenter;
        this.appointments = appointments;
    }

    public Slot(LocalDateTime startTime, LocalDateTime endTime, Integer availableSlots,
                VaccinationCenter vaccinationCenter,
                List<BookingDetails> appointments) {
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

    public List<BookingDetails> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<BookingDetails> appointments) {
        this.appointments = appointments;
    }

    public Slot() {

    }
}

