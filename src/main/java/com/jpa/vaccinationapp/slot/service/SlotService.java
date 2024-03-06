package com.jpa.vaccinationapp.slot.service;

import com.jpa.vaccinationapp.slot.Slot;
import com.jpa.vaccinationapp.slot.SlotDTO;
import com.jpa.vaccinationapp.slot.SlotException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SlotService {
    public Slot createSlot(Slot slot) throws SlotException;
    public Slot updateSlot(Slot slot) throws SlotException;
    public Slot deleteSlot(Integer slotId) throws SlotException;
    public Slot getSlotById(Integer slotId) throws SlotException;
    public List<Slot> getAllSlots();
    public List<Slot> getSlotsByDate(LocalDate date);
    public List<Slot> getSlotsByVaccinationCentre(Integer centreId);
    public Slot changeSlotAppointments(Integer slotId,Integer slots) throws SlotException;
}
