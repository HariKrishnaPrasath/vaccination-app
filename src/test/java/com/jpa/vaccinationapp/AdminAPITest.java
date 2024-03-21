package com.jpa.vaccinationapp;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.admin.Login;
import com.jpa.vaccinationapp.admin.service.AdminService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance. Lifecycle. PER_CLASS)
class AdminAPITest {
    @Autowired
    AdminService adminService;

//    @Order(1)
    @BeforeAll
    @Transactional
    void beforeAll(){
        try {
            Admin admin=this.adminService.addAdmin(new Admin("John@gmail.com","1234567890",
                    "John","Hello@123","Admin"));
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterAll
    void afterAll(){
        try {
            Admin admin=this.adminService.deleteAdminById(1);
        }
        catch (AdminException e) {
            Assertions.assertEquals("Admin not found",e.getMessage());
        }
    }
    @Test
    void createNewAdminTest1(){
        Admin admin=null;
        try {
            admin=this.adminService.addAdmin(new Admin(1002,"JohnWick@gmail.com","123456900",
                    "JohnWick","Hello@123","Super"));
            Assertions.assertNotNull(admin);
        } catch (AdminException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    void createNewAdminTest2(){
        Admin admin=null;
        try {
            admin=this.adminService.addAdmin(new Admin(1001,"John@gmail.com","1234567890",
                    "John","Hello@123","Admin"));
        }
        catch (AdminException e){
            Assertions.assertEquals("Admin already exist and please check provided details",e.getMessage());
        }
    }
    @Test
    void deleteAdminTest1()
    {
        try {
            this.adminService.deleteAdminById(-1);
        } catch (AdminException e) {
            Assertions.assertNotSame("Id can't be null or -ve",e.getMessage());
        }
    }
    @Test
    void deleteAdminTest2() throws AdminException {
        Admin admin=this.adminService.getAdminById(1);
        try{
            Assertions.assertEquals(admin.getEmail(),this.adminService.deleteAdminById(1).getEmail());
        }
        catch (AdminException e)
        {
            e.getMessage();
        }
    }
    @Test
    void getAllAdminTest1()
    {
        try {
            Assertions.assertNotNull(this.adminService.getAllAdmin());
        }
        catch (AdminException e){
            e.getMessage();
        }
    }
    @Test
    void getAdminByEmailTest1()
    {
        try {
            Assertions.assertEquals(null,this.adminService.getAdminByEmail("Senthuran@gmail.com"));
        }
        catch (AdminException e){
            e.getMessage();
        }
    }
    @Test
    void updateAdminTest1(){
        Admin admin=null;

        try {
            admin=this.adminService.updateAdminDetails(new Admin(1,"Johnm@gmail.com","1234567890",
                    "John cena","Helpo@123","Admin"));
            Assertions.assertNotNull(admin);
        } catch (AdminException e) {
            Assertions.assertEquals("Admin not found",e.getMessage());
        }
    }
    @Test
    void loginTest1(){
        Admin admin;
        try {
            admin=this.adminService.loginAdmin(new Login("John cena","Johncena@gmail.com","Hello@123"));
            Assertions.assertNotNull(admin);
        }
        catch (AdminException e){
            e.getMessage();
        }
    }
    @Test
    void loginTest2(){
        Admin admin;
        try {
            admin=this.adminService.loginAdmin(new Login("John cena","John@gmail.com","Helo@123"));
        }
        catch (AdminException e)
        {
            Assertions.assertEquals("Admin password is Invalid",e.getMessage());
        }
    }
    @Test
    void findAdminByIdTest1(){
        Admin admin;
        try {
            admin=this.adminService.getAdminById(1002);
        }
        catch (AdminException e)
        {
            Assertions.assertEquals("Admin not found",e.getMessage());
        }
    }
    @Test
    void findAdminByIdTest2(){
        Admin admin;
        try {
            admin=this.adminService.getAdminById(1);
            Assertions.assertNotNull(admin);
        }
        catch (AdminException e)
        {
            e.getMessage();
        }
    }
}
