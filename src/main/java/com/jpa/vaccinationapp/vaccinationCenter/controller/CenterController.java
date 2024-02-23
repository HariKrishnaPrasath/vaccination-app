package com.jpa.vaccinationapp.vaccinationCenter.controller;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.vaccinationCenter.AddressDTO;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccinationCenter.CenterException;
import com.jpa.vaccinationapp.vaccinationCenter.service.CenterService;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CenterController {
    private final CenterService centerService;
    @Autowired
    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    // creating a center
    @PostMapping("center/create")
    public Center createCenter(@RequestBody Center newCenter) throws CenterException {
        return centerService.createCenter(newCenter);
    }

    // adding vaccine to a center
    @PutMapping("center/{centerID}/vaccine")
    public Center addVaccineToCenter(@PathVariable Integer centerID, @RequestBody Vaccine newVaccine)
            throws CenterException {
        return centerService.addVaccineToCenter(centerID,newVaccine);
    }

    // removing a center
    @DeleteMapping("center/{centerID}/remove")
    public Center removeCenter(@PathVariable Integer centerID)throws CenterException{
        return centerService.removeCenter(centerID);
    }

    // getting center by name
    @GetMapping("center/getCenterByName/{centerName}")
    public List<Center> findCenterByCenterNameIsContainingIgnoreCase(@PathVariable String centerName)
            throws CenterException{
        return centerService.findCenterByCenterNameIsContainingIgnoreCase(centerName);
    }

    //getting center by ID
    @GetMapping("center/getByID/{centerID}")
    public Center findByID(@PathVariable Integer centerID)throws CenterException{
        return centerService.findByID(centerID);
    }

    //getting center by pincode
    @GetMapping("center/getByPincode/{pincode}")
    public List<Center> findByPincode(@PathVariable String pincode)throws CenterException{
        return centerService.findByPincode(pincode);
    }

    //displaying all centers
    @GetMapping("center/getAllCenter")
    public List<Center> getAllCenter(){
        return centerService.getAllCenter();
    }

    //updating the center
    @PutMapping("center/update")
    public Center updateCenter(Center center) throws CenterException {
        return centerService.updateCenter(center);
    }

    //updating the address and phone of the center
    @PutMapping("center/update/addressAndPhone")
    public Center updateCenterAddressAndPhone(@RequestBody AddressDTO addressDTO) throws CenterException {
        return centerService.updateCenterAddressAndPhone(addressDTO);
    }

}
