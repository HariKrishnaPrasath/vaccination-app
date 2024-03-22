package com.jpa.vaccinationapp.slotTests;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.admin.AdminRepository;
import com.jpa.vaccinationapp.admin.service.AdminServiceImpl;

import com.jpa.vaccinationapp.slot.Slot;
import com.jpa.vaccinationapp.slot.SlotException;
import com.jpa.vaccinationapp.slot.SlotRepository;
import com.jpa.vaccinationapp.slot.service.SlotServiceImpl;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccinationCenter.CenterException;
import com.jpa.vaccinationapp.vaccinationCenter.CenterRepository;
import com.jpa.vaccinationapp.vaccinationCenter.service.CenterServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance. Lifecycle. PER_CLASS)
public class SlotTest {
    @Autowired
    SlotServiceImpl slotService;
    @Autowired
    SlotRepository slotRepository;
    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    CenterServiceImpl centerService;
    @Autowired
    CenterRepository centerRepository;
    @Autowired
    AdminRepository adminRepository;

    Center center;
    Admin admin;
    Slot slot;
    @BeforeAll
    @Transactional
    void beforeAll(){
        try {
            center=this.centerService.createCenter(new Center(101,"Alpha Health","No:485,krishnar street,polur, Arakkonam",
                    "631003","Ranipet", "Tamil Nadu","9593813109",null,null,null));
            admin = this.adminService.addAdmin(new Admin("admin@gmail.com", "1234567890",
                    "Smith", "Password","Admin"));
        } catch (CenterException | AdminException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @AfterAll
    void afterAll(){
        this.centerRepository.deleteAll();
        this.adminRepository.deleteAll();
    }
    @BeforeEach
    void beforeEach(){
        LocalTime currentTime = LocalTime.now();
//        LocalTime localTime = currentTime.toLocalTime();
//        Date startTime = Date.from(localTime.atDate(LocalDate.of(1970, 1, 1))
//                .atZone(ZoneId.systemDefault())
//                .toInstant());
        try {
            slot = this.slotService.createSlot(new Slot(currentTime, currentTime, 10, center,new ArrayList<>()
                    ,LocalDate.now()));
        } catch (SlotException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @AfterEach
    void afterEach(){
        this.slotRepository.delete(slot);
    }
    @Test
    void createSlotTest(){
        LocalTime currentTime = LocalTime.now();
//        LocalTime localTime = currentTime.toLocalTime();
//        Date startTime = Date.from(localTime.atDate(LocalDate.of(1970, 1, 1))
//                .atZone(ZoneId.systemDefault())
//                .toInstant());
        Slot createSlot = new Slot(currentTime,currentTime,10,center,new ArrayList<>(),LocalDate.now());
        Slot createdSlot = null;
        try {
            createdSlot = this.slotService.createSlot(createSlot);
            Assertions.assertNotNull(createdSlot);
        } catch (SlotException e) {
            Assertions.fail(e.getMessage());
        }
        this.slotRepository.delete(createdSlot);
    }
    @Test
    void updateSlotTest(){
        Slot updatedSlot;
        slot.setAvailableSlots(20);
        try {
            updatedSlot = this.slotService.updateSlot(slot);
            Assertions.assertNotNull(updatedSlot);
            Assertions.assertEquals(updatedSlot.getAvailableSlots(),20);
        } catch (SlotException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    void deleteSlotTest(){
        Slot deletedSlot;
        try {
            deletedSlot = this.slotService.deleteSlot(slot.getId());
            Assertions.assertNotNull(deletedSlot);
            Assertions.assertFalse(this.slotRepository.findById(deletedSlot.getId()).isPresent());
        } catch (SlotException e) {
            Assertions.fail(e);
        }
    }
    @Test
    void deleteSlotExceptionTest(){
            Assertions.assertThrows(SlotException.class,()->this.slotService.deleteSlot(null));
    }
    @Test
    void deleteSlotExceptionCheckTest(){
        try {
            this.slotService.deleteSlot(null);
        } catch (SlotException e) {
            Assertions.assertEquals(e.getMessage(),"slot ID must not be null");
        }
    }
    @Test
    void deleteSlotExceptionCheckingTest(){
        try {
            this.slotService.deleteSlot(5);
        } catch (SlotException e) {
            Assertions.assertEquals(e.getMessage(),"slot does not exist");
        }
    }
    @Test
    void getSlotByIdTest(){
        Slot newSlot;
        try {
            newSlot = this.slotService.getSlotById(slot.getId());
            Assertions.assertNotNull(newSlot);
            Assertions.assertEquals(newSlot.getId(),slot.getId());
        } catch (SlotException e) {
            Assertions.fail(e);
        }
    }
    @Test
    void getAllSlotTest(){
        List<Slot> slotList;
        slotList = this.slotService.getAllSlots();
        Assertions.assertNotNull(slotList);
    }
    @Test
    void getAllSlotByDateTest(){
        List<Slot> slotList;
        slotList = this.slotService.getSlotsByDate(LocalDate.now());
        Assertions.assertNotNull(slotList);
    }
    @Test
    void getAllSlotByVaccinationCenter(){
        List<Slot> slotList;
        slotList = this.slotService.getSlotsByVaccinationCentre(center.getCenterId());
        Assertions.assertNotNull(slotList);
    }
    @Test
    void changeSlotWindowTest(){
        Slot updatedSlot;
        try {
            updatedSlot = this.slotService.changeSlotAppointments(slot.getId(),30);
            Assertions.assertNotNull(updatedSlot);
            Assertions.assertEquals(30,updatedSlot.getAvailableSlots());
        } catch (SlotException e) {
            Assertions.fail(e);
        }
    }
}
