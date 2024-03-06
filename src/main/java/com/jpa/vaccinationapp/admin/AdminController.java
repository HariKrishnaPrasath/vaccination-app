package com.jpa.vaccinationapp.admin;

import com.jpa.vaccinationapp.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Admin> getAllAdmin()throws AdminException{
        return this.adminService.getAllAdmin();
    }
    @GetMapping("vaccinationapp/admin/getadminbyid/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Admin getAdminById(@PathVariable Integer id)throws AdminException{
        return this.adminService.getAdminById(id);
    }
    @GetMapping("vaccinationapp/admin/getbyemail/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Admin getAdminByEmail(@PathVariable String email) throws AdminException {
        return this.adminService.getAdminByEmail(email);
    }
    @DeleteMapping("vaccinationapp/admin/deleteadmin/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Admin deleteAdminByAdmin(@PathVariable Integer id) throws AdminException {
        return this.adminService.deleteAdminById(id);
    }
    @GetMapping("admin/login")
    public Admin adminLogin(@RequestBody Login loginDetails) throws AdminException {
        return this.adminService.loginAdmin(loginDetails);
    }
}
