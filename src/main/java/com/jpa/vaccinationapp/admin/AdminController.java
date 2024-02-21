package com.jpa.vaccinationapp.admin;

import com.jpa.vaccinationapp.admin.service.AdminService;
import com.jpa.vaccinationapp.patient.Login;
import com.jpa.vaccinationapp.patient.Patient;
import com.jpa.vaccinationapp.patient.PatientException;
import com.jpa.vaccinationapp.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @PostMapping("vaccinationapp/admin/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin addAdmin(@RequestBody Admin adminDetails,@RequestBody Admin addAdminDetails) throws AdminException {
        return this.adminService.addAdmin(addAdminDetails,adminDetails);
    }
    @PutMapping("vaccinationapp/admin/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Admin updateAdmin(@RequestBody Admin adminDetails,@RequestBody Admin addAdminDetails)throws AdminException{
        return this.adminService.updateAdminDetails(addAdminDetails,adminDetails);
    }
    @GetMapping("vaccinationapp/admin/getalladmin")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Admin> getAllAdmin(@RequestBody Admin adminDetails)throws AdminException{
        return this.adminService.getAllAdmin(adminDetails);
    }
    @GetMapping("vaccinationapp/admin/getadminbyid/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Admin getAdminById(@RequestBody Admin adminDetails,@PathVariable Integer id)throws AdminException{
        return this.adminService.getAdminById(adminDetails,id);
    }
    @GetMapping("vaccinationapp/admin/getbyemail/{email}")
    @ResponseStatus(HttpStatus.FOUND)
    public Admin getAdminByEmail(@RequestBody Admin adminDetails,@PathVariable String email) throws AdminException {
        return this.adminService.getAdminByEmail(adminDetails,email);
    }
    @DeleteMapping("vaccinationapp/admin/deleteadmin")
    @ResponseStatus(HttpStatus.PROCESSING)
    public Admin deleteAdminByAdmin(@RequestBody Admin adminDetails,@RequestBody Admin deleteAdminDetails) throws AdminException {
        return this.adminService.deleteAdminById(adminDetails,deleteAdminDetails.getAdminId());
    }
}
