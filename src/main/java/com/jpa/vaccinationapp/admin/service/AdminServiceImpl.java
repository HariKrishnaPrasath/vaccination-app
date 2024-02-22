package com.jpa.vaccinationapp.admin.service;

import com.jpa.vaccinationapp.admin.AdminRepository;
import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccinationCenter.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    private CenterRepository vaccRepo;
    AdminException adminException=new AdminException("Admin password is Invalid");
    AdminException adminNotFoundException=new AdminException("Admin not found");
    String adminType="Super";
    @Override
    public Admin addAdmin(Admin adminDetails) throws AdminException {

            Optional<Admin> checkAdmin = adminRepo.findByEmailIgnoreCase(adminDetails.getEmail());
            if (checkAdmin.isPresent())
                throw new AdminException("Admin already exist and please check provided details");
            return this.adminRepo.save(adminDetails);
    }
    @Override
    public Admin updateAdminDetails(Admin adminDetails) throws AdminException {
            Optional<Admin> checkAdmin = adminRepo.findByEmailIgnoreCase(adminDetails.getEmail());
            if (checkAdmin.isEmpty())
                throw adminNotFoundException;
            return this.adminRepo.save(adminDetails);
    }
    @Override
    public List<Admin> getAllAdmin() throws AdminException {
            return this.adminRepo.findAll();
    }
    @Override
    public Admin getAdminById(Integer id)throws AdminException  {
            Optional<Admin> adminFound = this.adminRepo.findById(id);
            if(adminFound.isEmpty())
                throw adminNotFoundException;
            return adminFound.get();
    }

    @Override
    public Admin getAdminByEmail(String email) throws AdminException {
            Optional<Admin> adminFound = this.adminRepo.findByEmailIgnoreCase(email);
            if(adminFound.isEmpty())
                throw adminNotFoundException;
            return adminFound.get();
    }
    @Override
    public Admin deleteAdminById(Integer id) throws AdminException {
            Optional<Admin> adminFound = this.adminRepo.findById(id);
            if(adminFound.isEmpty())
                throw adminNotFoundException;
            Center vaccineCentre=vaccRepo.findByAdmin(adminFound.get());
            vaccineCentre.setAdmin(null);
            this.vaccRepo.save(vaccineCentre);
            this.adminRepo.deleteById(id);
            return adminFound.get();
    }

}
