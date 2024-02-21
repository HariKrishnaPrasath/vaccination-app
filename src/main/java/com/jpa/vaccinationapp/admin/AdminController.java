package com.jpa.vaccinationapp.admin;

import com.jpa.vaccinationapp.admin.service.AdminService;
import com.jpa.vaccinationapp.patient.Login;
import com.jpa.vaccinationapp.patient.Patient;
import com.jpa.vaccinationapp.patient.PatientException;
import com.jpa.vaccinationapp.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private AdminService adminService;
    @PostMapping("admin/signup")
    public Admin superAdminSignUp(@RequestBody Admin superAdmin) throws AdminException {
        return this.adminService.addAdmin(superAdmin);
    }
    @PostMapping("user/signin")
    public Patient signUserUsingEmailAndPassword(@RequestBody Login login) throws PatientException {
        return this.patientService.logIn(login);
    }
    @GetMapping("admin/users")
    public List<Patient> getAllUser(){
        return this.patientService.getAllPatients();
    }

    //change
}
