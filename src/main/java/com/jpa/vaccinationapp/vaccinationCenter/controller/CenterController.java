package com.jpa.vaccinationapp.vaccinationCenter.controller;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccinationCenter.CenterException;
import com.jpa.vaccinationapp.vaccinationCenter.service.CenterSerivce;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CenterController {
    @Autowired
    private CenterSerivce centerSerivce;

    // creating a center
    @PostMapping("center/create")
    public Center createCenter(@RequestBody Center newCenter, @RequestBody Admin admin) throws CenterException {
        return this.centerSerivce.createCenter(newCenter,admin);
    }

    // adding vaccine to a center
    @PutMapping("center/{centerID}/vaccine")
    public Center addVaccine(@PathVariable Integer centerID, @RequestBody Admin admin, @RequestBody Vaccine newVaccine)
            throws CenterException {
        return this.centerSerivce.addVaccineToCenter(centerID,admin,newVaccine);
    }

    // removing a center
    @DeleteMapping("center/{centerID}/remove")
    public Center removeCenter(@PathVariable Integer centerID, @RequestBody Admin admin)throws CenterException{
        return this.centerSerivce.removeCenter(centerID,admin);
    }

    // getting center by name
    @GetMapping("center/getCenterByName/{centerName}")
    public List<Center> getByNameCaseInsensitive(@PathVariable String centerName) throws CenterException{
        return this.centerSerivce.findByNameCaseInsensitive(centerName);
    }

    //getting center by ID
    @GetMapping("center/getByID/{centerID}")
    public Center findByID(@PathVariable Integer centerID)throws CenterException{
        return this.centerSerivce.findByID(centerID);
    }

    //getting center by pincode
    @GetMapping("center/getByPincode/{pincode}")
    public List<Center> findByPincode(@PathVariable String pincode)throws CenterException{
        return this.centerSerivce.findByPincode(pincode);
    }

    //displaying all centers
    @GetMapping("center/getAllCenter")
    public List<Center> getAllCenter(){
        return this.centerSerivce.getAllCenter();
    }

}
