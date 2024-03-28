package com.jpa.vaccinationapp;

import com.jpa.vaccinationapp.vaccine.Vaccine;
import com.jpa.vaccinationapp.vaccine.VaccineException;
import com.jpa.vaccinationapp.vaccine.VaccineRepository;
import com.jpa.vaccinationapp.vaccine.service.VaccineService;
import com.jpa.vaccinationapp.vaccine.service.VaccineServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class VaccineTest {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private VaccineService vaccineService;

    @Test
    public void testCreateVaccine() {
        Vaccine newVaccine = new Vaccine();
        newVaccine.setVaccineId(1);

        // Save the new vaccine to the database
        Vaccine savedVaccine = vaccineRepository.save(newVaccine);

        try {
            // Attempt to create the vaccine again
            Vaccine createdVaccine = vaccineService.createVaccine(newVaccine);

            // If the vaccine was successfully created, the test should fail
            Assertions.fail("Exception expected but not thrown");

        } catch (VaccineException e) {
            // An exception is expected because the vaccine with the same ID already exists
            Assertions.assertEquals("this vaccine already exists!", e.getMessage());
        }
    }

    @Test
    public void testUpdateVaccine() {
        int vaccineId = 1;
        Vaccine vaccineToUpdate = new Vaccine();
        vaccineToUpdate.setVaccineId(vaccineId);

        // Save the vaccine to the database
        Vaccine savedVaccine = vaccineRepository.save(vaccineToUpdate);

        try {
            // Attempt to update the vaccine
<<<<<<< HEAD
            Vaccine updatedVaccine = vaccineService.updateVaccine(vaccineId, new Vaccine());
=======
            Vaccine updatedVaccine = vaccineService.updateVaccine(vaccineId,vaccineToUpdate);
>>>>>>> main

            Assertions.assertEquals(vaccineToUpdate.getVaccineId(),updatedVaccine.getVaccineId());

        } catch (VaccineException e) {
            // An exception is expected because the vaccine to update doesn't exist
            Assertions.assertEquals("Vaccine doesn't exists!", e.getMessage());
        }
    }

    @Test
    public void testGetById() {
        Vaccine vaccine = new Vaccine();
        vaccine.setVaccineName("TestVaccine");
        vaccine.setManufacturingDate(LocalDate.now());
        vaccine.setExpiryDate(LocalDate.now().plusMonths(6));
        vaccine.setDescription("Test description");
        try {
            vaccine=vaccineService.createVaccine(vaccine);
            System.out.println(vaccine.getVaccineId());
            Assertions.assertNotNull(vaccine.getVaccineId());
            Vaccine retrievedVaccine = vaccineRepository.findById(vaccine.getVaccineId()).orElse(null);
            Assertions.assertNotNull(retrievedVaccine);
            Assertions.assertEquals(vaccine.getVaccineId(), retrievedVaccine.getVaccineId());

        } catch (VaccineException e) {
            Assertions.fail(e.getMessage());

        }
    }

    @Test
    public void testGetByName() {

        // Define the input data
        String vaccineName = "COVID-19";
        Vaccine vaccine1 = new Vaccine( vaccineName, LocalDate.now(), LocalDate.now().plusMonths(1), "Description 1",1);
        Vaccine vaccine2 = new Vaccine( vaccineName, LocalDate.now(), LocalDate.now().plusMonths(2), "Description 2",2);


        // Save the sample vaccines to the repository
        vaccineRepository.save(vaccine1);
        vaccineRepository.save(vaccine2);


        try {
            // Call the getByName method
            List<Vaccine> retrievedVaccines = vaccineService.getByName(vaccineName);

            // Verify that the retrieved list contains the expected vaccines
            Assertions.assertEquals(2, retrievedVaccines.size());
            Assertions.assertTrue(retrievedVaccines.contains(vaccine1));
            Assertions.assertTrue(retrievedVaccines.contains(vaccine2));
        } catch (VaccineException e) {
            Assertions.fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteExpiredVaccines() {
//        LocalDate currentDate = LocalDate.now();
//        LocalDate expiryDate = LocalDate.now().minusDays(1);
//
//        Vaccine expiredVaccine = new Vaccine();
//        expiredVaccine.setVaccineId(vaccineId);
//        expiredVaccine.setExpiryDate(expiryDate);
//
//        vaccineRepository.save(expiredVaccine);
//
//        try {
//            Vaccine deletedVaccine = vaccineService.deleteExpiredVaccines();
//            Assertions.assertNotNull(deletedVaccine);
//            Assertions.assertEquals(expiredVaccine, deletedVaccine);
//        } catch (VaccineException e) {
//            Assertions.fail("Exception not expected: " + e.getMessage());
//        }
    }

    @Test
    public void testDeleteById() {
        Vaccine vaccine = new Vaccine();
        vaccine.setVaccineName("TestVaccine");
        vaccine.setManufacturingDate(LocalDate.now());
        vaccine.setExpiryDate(LocalDate.now().plusMonths(6));
        vaccine.setDescription("Test description");
        try {
            vaccine=vaccineService.createVaccine(vaccine);
            System.out.println(vaccine.getVaccineId());
            Assertions.assertNotNull(vaccine.getVaccineId());
            Vaccine deletedVaccine = vaccineService.deleteById(vaccine.getVaccineId());
            Assertions.assertThrows(VaccineException.class,()->vaccineService.getById(deletedVaccine.getVaccineId()));

        } catch (VaccineException e) {
            //Assertions.fail(e.getMessage());
        }

    }

    @Test
    public void testGetAllVaccine() {
        List<Vaccine> allVaccines = vaccineRepository.findAll();

        List<Vaccine> retrievedVaccines = vaccineService.getAllVaccine();
        Assertions.assertNotNull(retrievedVaccines);
        Assertions.assertEquals(allVaccines.size(), retrievedVaccines.size());
    }

    @Test
    public void testDeleteAllExpiredVaccines() {
        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDate1 = LocalDate.now().minusDays(1);
        LocalDate expiryDate2 = LocalDate.now().minusDays(2);
        Vaccine expiredVaccine1 = new Vaccine();
        Vaccine expiredVaccine2 = new Vaccine();
        //expiredVaccine1.setVaccineId(1);
        expiredVaccine1.setVaccineName("TestVaccine");
        expiredVaccine1.setManufacturingDate(LocalDate.now().minusDays(3));
        expiredVaccine1.setDescription("Test description");
        expiredVaccine1.setExpiryDate(expiryDate1);
        //expiredVaccine2.setVaccineId(2);
        expiredVaccine2.setVaccineName("TestVaccine2");
        expiredVaccine2.setManufacturingDate(LocalDate.now().minusDays(5));
        expiredVaccine2.setDescription("Test description2");
        expiredVaccine2.setExpiryDate(expiryDate2);
        try {
            expiredVaccine1 = vaccineService.createVaccine(expiredVaccine1);
            expiredVaccine2 = vaccineService.createVaccine(expiredVaccine2);
            List<Vaccine> deletedVaccines = vaccineService.deleteExpiredVaccines();
            Assertions.assertNotNull(deletedVaccines);
            Assertions.assertEquals(2, deletedVaccines.size());
        } catch (VaccineException e) {
            Assertions.fail(e.getMessage());
        }


    }
}
