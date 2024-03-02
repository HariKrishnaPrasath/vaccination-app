package com.jpa.vaccinationapp.slot.service;

import com.jpa.vaccinationapp.slot.Slot;
import com.jpa.vaccinationapp.slot.SlotDTO;
import com.jpa.vaccinationapp.slot.SlotException;
import com.jpa.vaccinationapp.slot.SlotRepository;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccinationCenter.CenterRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class SlotServiceImpl implements SlotService{
    private final SlotRepository slotRepository;
    private final CenterRepository centerRepository;
    @Autowired
    public SlotServiceImpl(SlotRepository slotRepository, CenterRepository centerRepository) {
        this.slotRepository = slotRepository;
        this.centerRepository = centerRepository;
    }

    @Override
    @Transactional
    public Slot createSlot(Slot slot) throws SlotException {
        if(slot == null) throw new SlotException("slot must not be null");
        if(slot.getCenter() == null) throw new SlotException("no such vaccine center");
        Optional<Slot> ifSlotAlreadyPresent = Optional.ofNullable(this.slotRepository.
                findByCenterAndStartTimeAndEndTime(slot.getCenter(),
                        slot.getStartTime(), slot.getEndTime()));
        if(ifSlotAlreadyPresent.isPresent()) throw new SlotException("slot already present");
        Optional<Center> centerOptional = this.centerRepository.findById(slot.getCenter().getCenterId());
        if (centerOptional.isEmpty())
            throw new SlotException("No center is found");
        slot.setCenter(centerOptional.get());
        return this.slotRepository.save(slot);
    }

    @Override
    public Slot updateSlot(Slot slot) throws SlotException {
        if(slot == null) throw new SlotException("slot must not be null");
        Optional<Slot> existingSlot = this.slotRepository.findById(slot.getId());
        if(existingSlot.isEmpty()) throw new SlotException("slot does not exist");
        return this.slotRepository.save(slot);
    }

    @Override
    public Slot deleteSlot(Integer slotId) throws SlotException {
        if(slotId == null) throw new SlotException("slot ID must not be null");
        Optional<Slot> slot = this.slotRepository.findById(slotId);
        if(slot.isEmpty()) throw new SlotException("slot does not exist");
        this.slotRepository.delete(slot.get());
        return slot.get();
    }

    @Override
    public Slot getSlotById(Integer slotId) throws SlotException {
        if(slotId == null) throw new SlotException("slot ID must not be null");
        Optional<Slot> existingSlot = this.slotRepository.findById(slotId);
        if(existingSlot.isEmpty()) throw new SlotException("slot does not exist");
        return existingSlot.get();
    }

    @Override
    public List<Slot> getAllSlots() {

        return this.slotRepository.findAll();
    }

    @Override
    public List<Slot> getSlotsByDate(Date date) {
        return this.slotRepository.findByDate(date);
    }

    @Override
    public List<Slot> getSlotsByVaccinationCentre(Integer centreId) {
        return this.slotRepository.findByCenter(this.centerRepository.findById(centreId));
    }

    @Override
    public Slot changeSlotAppointments(Integer slotId,Integer slots) throws SlotException {
        if(slotId == null) throw new SlotException("slot ID must not be null");
        if(slots == null) throw new SlotException("slots count must not be null");
        Optional<Slot> slot = this.slotRepository.findById(slotId);
        if(slot.isEmpty()) throw new SlotException("slot does not exist");
        slot.get().setAvailableSlots(slots);
        return slot.get();
    }

}
