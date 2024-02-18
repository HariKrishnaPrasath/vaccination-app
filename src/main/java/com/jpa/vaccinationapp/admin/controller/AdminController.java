package com.jpa.vaccinationapp.admin.controller;

import com.jpa.vaccinationapp.admin.entity.Admin;
import com.jpa.vaccinationapp.patient.entity.Patient;
import com.jpa.vaccinationapp.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private PatientService patientService;
    @PostMapping("admin/signup")
    public String superAdminSignUp(@RequestBody Admin superAdmin) {
        return null;
    }
    @PostMapping("user/signin")
    public Patient signUserUsingEmailAndPassword(@RequestBody Patient patient) {
        return this.patientService.signIN(patient);
    }
}
