package com.jpa.vaccinationapp;

import com.jpa.vaccinationapp.vaccinationCenter.AddressDTO;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccinationCenter.CenterException;
import com.jpa.vaccinationapp.vaccinationCenter.CenterRepository;
import com.jpa.vaccinationapp.vaccinationCenter.service.CenterService;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import com.jpa.vaccinationapp.vaccine.VaccineException;
import com.jpa.vaccinationapp.vaccine.service.VaccineService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CenterApiTest {

    private static CenterService centerService;
    private static CenterRepository centerRepository;
    private static VaccineService vaccineService;
    private static Center center;
    private static Vaccine vaccine,vaccine2;
    @Autowired
    public CenterApiTest(CenterService centerService, CenterRepository centerRepository, VaccineService vaccineService) {
        CenterApiTest.centerService = centerService;
        CenterApiTest.centerRepository = centerRepository;
        CenterApiTest.vaccineService = vaccineService;
    }

    @BeforeAll
    static void setup() {
        // Now create the center once before all tests
        center = new Center("Alpha Health","No:485, krishnar street, polur, Arakkonam",
                "631003","Ranipet", "Tamil Nadu","9593813109",null,
                null,null);
        vaccine=new Vaccine("Covaxine", LocalDate.of(2020,11, 1),
                LocalDate.of(2024,11, 1),"for corona virus!");
        vaccine2=new Vaccine("CoviShield", LocalDate.of(2020,11, 1),
                LocalDate.of(2024,11, 1),"for corona virus!");
        try {
            center = centerService.createCenter(center);
            vaccine= vaccineService.createVaccine(vaccine);
            vaccine2 =vaccineService.createVaccine(vaccine2);
        } catch (CenterException | VaccineException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create center/vaccines before tests");
        }
    }

    @AfterAll
    static void removeCenterAtLast() {
        if(center != null) {
            centerRepository.delete(center);
        }
    }

    @Test
    @Order(1)
    void createCenterTest()  {
        Assertions.assertNotNull(center);
        Assertions.assertEquals("Alpha Health",center.getCenterName());
    }

    @Test
    @Order(11)
    void removeCenterTest()  {
        try {
            Integer centerId=center.getCenterId();
            System.out.println(center.getPincode());
            Center DeletedCenter=centerService.removeCenter(centerId);
            Assertions.assertEquals(centerId,DeletedCenter.getCenterId());
            Assertions.assertThrows(CenterException.class,()->centerService.findByID(DeletedCenter.getCenterId()));
        } catch (CenterException e) {
            //Assertions.fail(e.getMessage());
        }
    }
    @Test
    @Order(2)
    void addVaccineToCenterTest(){
        try {
            center=centerService.addVaccineToCenter(center.getCenterId(), vaccine);
            center=centerService.addVaccineToCenter(center.getCenterId(), vaccine2);
            center=centerService.findByID(center.getCenterId());
            Assertions.assertTrue(center.getVaccineMap().contains(vaccine));
            Assertions.assertTrue(center.getVaccineMap().contains(vaccine2));
            System.out.println(center);
        } catch (CenterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(8)
    void removeVaccineFromCenterTest(){
        try {
            //centerService.addVaccineToCenter(center.getCenterId(), vaccine);
            //centerService.addVaccineToCenter(center.getCenterId(), vaccine2);
            center=centerService.findByID(center.getCenterId());
            System.out.println(center.getCenterId());
            center=centerService.removeVaccineFromCentre(center.getCenterId(), vaccine.getVaccineId());
            Set<Vaccine> vaccines = center.getVaccineMap();
            System.out.println(vaccines);
            Assertions.assertFalse(vaccines.contains(vaccine));
        } catch (CenterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(3)
    void findCenterByCenterNameIsContainingIgnoreCaseTest(){
        try{
            List<Center> newCenter=centerService.findCenterByCenterNameIsContainingIgnoreCase("Alpha Heal");
            Optional<Center> y = newCenter.stream().filter(x -> x.getCenterId().equals(center.getCenterId())).findAny();
            Assertions.assertTrue(y.isPresent());
        } catch (CenterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(4)
    void findByPincodeTest(){
        try{
            List<Center> centersByPincode=centerService.findByPincode("631003");
            boolean isPresent=centersByPincode.stream().anyMatch(c->c.getCenterId().equals(center.getCenterId()));
            Assertions.assertTrue(isPresent);
        } catch (CenterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(9)
    void updateCenterAddressAndPhone(){
        try {
            Center updatedCenter=centerService.updateCenterAddressAndPhone(new AddressDTO(
                    center.getCenterId(), "No:485, church street, tiruthani",
                    "641003","tiruvallur", "Tamil Nadu","6382761675"));
            Assertions.assertEquals("641003", updatedCenter.getPincode());
        } catch (CenterException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @Order(10)
    void updateCenterTest(){
            center.setCenterId(101);
            center.setContactNumber("9500932516");
            Assertions.assertThrows(CenterException.class,()->centerService.updateCenter(center));
            Assertions.assertNotEquals("9597813109",center.getContactNumber());
    }

    @Test
    @Order(5)
    void getCenterByIDTest(){
        try {
            Center result=centerService.findByID(center.getCenterId());
            Assertions.assertEquals(center.getCenterId(),result.getCenterId());
        } catch (CenterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(6)
    void getCenterByPincode(){
        try {
            center=centerService.createCenter(center);
            List<Center> result=centerService.findByPincode(center.getPincode());
            result.forEach(c -> Assertions.assertEquals("631003", c.getPincode()));
        } catch (CenterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(7)
    void getAllVaccinesInCenterTest(){
        try {
            //centerService.addVaccineToCenter(center.getCenterId(), vaccine);
            //centerService.addVaccineToCenter(center.getCenterId(), vaccine2);
            List<Vaccine> result=centerService.getAllVaccinesFromCenter(center.getCenterId());
            Assertions.assertEquals(2,result.size());
        } catch (CenterException e) {
            Assertions.fail(e.getMessage());
        }
    }
}

