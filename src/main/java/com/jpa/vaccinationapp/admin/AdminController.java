package com.jpa.vaccinationapp.admin;

import com.jpa.vaccinationapp.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("vaccinationapp/admin/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin addAdmin(@RequestBody Admin adminDetails) throws AdminException {
        return this.adminService.addAdmin(adminDetails);
    }
    @PutMapping("vaccinationapp/admin/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Admin updateAdmin(@RequestBody Admin adminDetails)throws AdminException{
        return this.adminService.updateAdminDetails(adminDetails);
    }
    @GetMapping("vaccinationapp/admin/getalladmin")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Admin> getAllAdmin()throws AdminException{
        return this.adminService.getAllAdmin();
    }
    @GetMapping("vaccinationapp/admin/getadminbyid/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Admin getAdminById(@PathVariable Integer id)throws AdminException{
        return this.adminService.getAdminById(id);
    }
    @GetMapping("vaccinationapp/admin/getbyemail/{email}")
    @ResponseStatus(HttpStatus.FOUND)
    public Admin getAdminByEmail(@PathVariable String email) throws AdminException {
        return this.adminService.getAdminByEmail(email);
    }
    @DeleteMapping("vaccinationapp/admin/deleteadmin")
    @ResponseStatus(HttpStatus.PROCESSING)
    public Admin deleteAdminByAdmin(@RequestBody Admin deleteAdminDetails) throws AdminException {
        return this.adminService.deleteAdminById(deleteAdminDetails.getAdminId());
    }
    @GetMapping("admin/login")
    public Admin adminLogin(@RequestBody Login loginDetails) throws AdminException {
        return this.adminService.loginAdmin(loginDetails);
    }
}
