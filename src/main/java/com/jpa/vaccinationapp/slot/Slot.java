package com.jpa.vaccinationapp.slot;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "date cant be null")
    private LocalDate date;
    @NotNull(message = "Start time cannot be null")
//    @Temporal(TemporalType.TIME)
//    @DateTimeFormat(style = "hh:mm a")
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm a")
    private LocalTime startTime;
    @NotNull(message = "end time cannot be null")
//    @Temporal(TemporalType.TIME)
//    @DateTimeFormat(style = "hh:mm a")
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm a")
    private LocalTime endTime;
    @NotNull(message = "Available slots count cannot be null")
    private Integer availableSlots; // The number of available slots for this time period
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CENTER_CENTER_ID", nullable = false)
        private Center center;

    @OneToMany(mappedBy = "slot",fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private List<Appointment> appointments = new ArrayList<>();
    public Slot(Integer id, LocalTime startTime, LocalTime endTime,
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

    public Slot(LocalTime startTime, LocalTime endTime, Integer availableSlots,
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
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
//    public void addAppointment(Appointment appointment) {
//        appointments.add(appointment);
//        appointment.setSlot(this);
//    }
//
//    public void removeAppointment(Appointment appointment) {
//        appointments.remove(appointment);
//        appointment.setSlot(null);
//    }
    public Slot() {

    }

}

