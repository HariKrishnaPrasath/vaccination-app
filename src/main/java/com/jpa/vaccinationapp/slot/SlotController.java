package com.jpa.vaccinationapp.slot;

import com.jpa.vaccinationapp.slot.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})

public class SlotController {
    private final SlotService slotService;
    @Autowired
    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping("slot")
    public Slot createSlot(@RequestBody Slot slot) throws SlotException {
        return this.slotService.createSlot(slot);
    }
    @PutMapping("slot")
    public Slot updateSlot(@RequestBody Slot slot) throws SlotException {
        return this.slotService.updateSlot(slot);
    }   
    @DeleteMapping("slot/{id}")
    public Slot deleteSlot(@PathVariable Integer id) throws SlotException {
        return this.slotService.deleteSlot(id);
    }
    @GetMapping("slot/{id}")
    public Slot getSlot(@PathVariable Integer id) throws SlotException {
        return this.slotService.getSlotById(id);
    }
    @GetMapping("slots")
    public List<Slot> getAllSlot(){
        return this.slotService.getAllSlots();
    }
    @GetMapping("slot/date/{date}")
    public List<Slot> getSlotsByDate(@PathVariable LocalDate date){
        return this.slotService.getSlotsByDate(date);
    }
    @GetMapping("slot/center/{centerId}")
    public List<Slot> getSlotsByCenter(@PathVariable Integer centerId){
        return this.slotService.getSlotsByVaccinationCentre(centerId);
    }
    @PatchMapping("slot/window")
    public Slot changeSlotWindows(@RequestBody WindowDto windowDto) throws SlotException {
        return this.slotService.changeSlotAppointments(windowDto.getSlotId(),windowDto.getWindows());
    }
}
