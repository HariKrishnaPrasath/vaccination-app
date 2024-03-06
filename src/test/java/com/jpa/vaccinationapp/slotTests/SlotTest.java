package com.jpa.vaccinationapp.slotTests;

import com.jpa.vaccinationapp.slot.Slot;
import com.jpa.vaccinationapp.slot.SlotException;
import com.jpa.vaccinationapp.slot.SlotRepository;
import com.jpa.vaccinationapp.slot.service.SlotServiceImpl;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.time.LocalDate;

@SpringBootTest
public class SlotTest {
    @Autowired
    SlotServiceImpl slotService;
    @Autowired
    SlotRepository slotRepository;

    @Test
    void createSlotTest(){
        Center center=new Center(101,"Alpha Health","No:485,krishnar street,polur, Arakkonam",
                "631003","Ranipet", "Tamil Nadu","9593813109",null,null,null);
        Slot slot = new Slot(Time.valueOf("10:30 AM"),Time.valueOf("12:30 PM"),10,center,null,LocalDate.now());
        Slot createdSlot;
        try {
            createdSlot = this.slotService.createSlot(slot);
            Assertions.assertNotNull(createdSlot);
        } catch (SlotException e) {
            Assertions.fail(e.getMessage());
        }
        this.slotRepository.deleteAll();
    }
}
