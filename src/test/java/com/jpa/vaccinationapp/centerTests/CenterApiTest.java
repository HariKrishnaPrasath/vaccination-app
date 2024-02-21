package com.jpa.vaccinationapp.centerTests;

import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccinationCenter.CenterException;
import com.jpa.vaccinationapp.vaccinationCenter.CenterRepository;
import com.jpa.vaccinationapp.vaccinationCenter.service.CenterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CenterApiTest {
    @Autowired
    private CenterService centerService;
    private CenterRepository centerRepository;
    @Test
    public void createCenterTest()  {
        Center center=new Center("Alfha Health","No:485,krishnar street,polur, Arakkonam",
                "631003","Ranipet",
                "Tamil Nadu","9593813109",null,null);
        try {
            centerService.createCenter(center,null);
        } catch (CenterException e) {
            throw new RuntimeException(e);
        }
        List<Center>result=centerService.getAllCenter();
        Assertions.assertFalse(result.isEmpty());
    }

}
