package com.jpa.vaccinationapp.slot;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    @NotNull
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(style = "hh:mm a")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm a")
    private Date startTime;
    @NotNull
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(style = "hh:mm a")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm a")
    private Date endTime;
    private Integer availableSlots; // The number of available slots for this time period
    @ManyToOne
    private Center center;

    @OneToMany
    private List<Appointment> appointments;
    public Slot(Integer id, Date startTime, Date endTime,
                Integer availableSlots, Center center,
                List<Appointment> appointments,LocalDate date) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableSlots = availableSlots;
        this.center = center;
        this.appointments = appointments;
        this.date = date;
    }

    public Slot(Date startTime, Date endTime, Integer availableSlots,
                Center center,
                List<Appointment> appointments,LocalDate date) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableSlots = availableSlots;
        this.center = center;
        this.appointments = appointments;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public Integer getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
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

