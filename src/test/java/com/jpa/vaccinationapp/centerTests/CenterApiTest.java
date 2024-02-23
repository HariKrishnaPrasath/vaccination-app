package com.jpa.vaccinationapp.centerTests;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.admin.AdminRepository;
import com.jpa.vaccinationapp.admin.service.AdminService;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccinationCenter.CenterException;
import com.jpa.vaccinationapp.vaccinationCenter.CenterRepository;
import com.jpa.vaccinationapp.vaccinationCenter.service.CenterService;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import com.jpa.vaccinationapp.vaccine.service.VaccineService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class CenterApiTest {
    @Autowired
    private CenterService centerService;
    @Autowired
    private CenterRepository centerRepository;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private VaccineService vaccineService;
    @Test
    void createCenterTest()  {
        Center center=new Center("Alpha Health","No:485,krishnar street,polur, Arakkonam",
                "631003","Ranipet",
                "Tamil Nadu","9593813109",null,null,null);
        Center result=null;
        try {
            result=centerService.createCenter(center);
            //System.out.println(result.getCenterId());
            Assertions.assertNotNull(result);
        } catch (CenterException e) {
            Assertions.fail(e.getMessage());
        }
        this.centerRepository.deleteAll();
    }

    @Test
    void removeCenterTest()  {
        Center center=new Center(101,"Alpha Health","No:485,krishnar street,polur, Arakkonam",
                "631003","Ranipet",
                "Tamil Nadu","9593813109",null,null,null);
        Admin admin= new Admin(100,"gopi@ford.com","gopinath","1@ford",
                "Super Admin");

        Center centerCheck = null,finalCenter=null;
        Admin  adminCheck=null;
        try{
            centerCheck=centerService.createCenter(center);
            adminService.addAdmin(admin,admin);
            //centerService.removeCenter(101,admin);

            finalCenter=centerService.findByID(centerCheck.getCenterId());
            System.out.println(centerCheck.getCenterId());
        } catch (CenterException e) {
            //assert centerCheck != null;
            Assertions.fail(e.getMessage());

        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        //this.centerRepository.deleteAll();
        //this.adminRepository.deleteAll();
    }

    @Test
    void addVaccineToCenterTest(){
        Center center=new Center("Alpha Health","No:485, krishnar street, polur, Arakkonam",
                "631003","Ranipet", "Tamil Nadu","9597813109",
                null,null,null);
        Vaccine vaccine=new Vaccine("Covaxine", LocalDate.of(2020,11, 1),
                LocalDate.of(2024,11, 1));
        Center resultCenter=null;
        Vaccine resultVaccine=null;
        try {
            resultCenter=centerService.createCenter(center);
            resultVaccine=vaccineService.addVaccine(null,vaccine);

        } catch (AdminException | CenterException ignored) {
            assert resultCenter != null;
            Assertions.assertFalse(resultCenter.getVaccineMap().containsValue(resultVaccine));
        }
    }

    @Test
    void findCenterByCenterNameIsContainingIgnoreCaseTest(){
        Center center=new Center("Alpha Health","No:485, krishnar street, polur, Arakkonam",
                "631003","Ranipet", "Tamil Nadu","9597813109",
                null,null,null);
        try{
            final Center resultCenter=centerService.createCenter(center);
            List<Center> newCenter=centerService.findCenterByCenterNameIsContainingIgnoreCase("Alpha Heal");
            Optional<Center> y =newCenter.stream().filter(x -> x.getCenterId().equals(resultCenter.getCenterId())).
                    findAny();
            Assertions.assertTrue(y.isPresent());

        } catch (CenterException e) {
            Assertions.fail(e.getMessage());
        }
        this.centerRepository.delete(center);
    }






}
