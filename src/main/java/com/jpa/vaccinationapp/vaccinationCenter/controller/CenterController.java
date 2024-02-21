package com.jpa.vaccinationapp.vaccinationCenter.controller;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.vaccinationCenter.AddressDTO;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccinationCenter.CenterException;
import com.jpa.vaccinationapp.vaccinationCenter.service.CenterSerivce;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CenterController {
    private final CenterSerivce centerSerivce;
    @Autowired
    public CenterController(CenterSerivce centerSerivce) {
        this.centerSerivce = centerSerivce;
    }

    // creating a center
    @PostMapping("center/create")
    public Center createCenter(@RequestBody Center newCenter) throws CenterException {
        return centerSerivce.createCenter(newCenter);
    }

    // adding vaccine to a center
    @PutMapping("center/{centerID}/vaccine")
    public Center addVaccine(@PathVariable Integer centerID, @RequestBody Admin admin, @RequestBody Vaccine newVaccine)
            throws CenterException {
        return centerSerivce.addVaccineToCenter(centerID,admin,newVaccine);
    }

    // removing a center
    @DeleteMapping("center/{centerID}/remove")
    public Center removeCenter(@PathVariable Integer centerID, @RequestBody Admin admin)throws CenterException{
        return centerSerivce.removeCenter(centerID,admin);
    }

    // getting center by name
    @GetMapping("center/getCenterByName/{centerName}")
    public List<Center> getByNameCaseInsensitive(@PathVariable String centerName) throws CenterException{
        return centerSerivce.findByNameCaseInsensitive(centerName);
    }

    //getting center by ID
    @GetMapping("center/getByID/{centerID}")
    public Center findByID(@PathVariable Integer centerID)throws CenterException{
        return centerSerivce.findByID(centerID);
    }

    //getting center by pincode
    @GetMapping("center/getByPincode/{pincode}")
    public List<Center> findByPincode(@PathVariable String pincode)throws CenterException{
        return centerSerivce.findByPincode(pincode);
    }

    //displaying all centers
    @GetMapping("center/getAllCenter")
    public List<Center> getAllCenter(){
        return centerSerivce.getAllCenter();
    }

    //updating the center
    @PutMapping("center/update")
    public Center updateCenter(Center center,Admin admin) throws CenterException {
        return centerSerivce.updateCenter(center,admin);
    }

    //updating the address and phone of the center
    @PutMapping("center/update/addressAndPhone")
    public Center updateCenterAddressAndPhone(@RequestBody AddressDTO addressDTO) throws CenterException {
        return centerSerivce.updateCenterAddressAndPhone(addressDTO);
    }

}
