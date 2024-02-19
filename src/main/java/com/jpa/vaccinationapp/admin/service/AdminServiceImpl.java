package com.jpa.vaccinationapp.admin.service;

import com.jpa.vaccinationapp.admin.AdminRepository;
import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminRepository adminRepo;
    @Override
    public Admin addAdmin(Admin adminDetails) throws AdminException {
        Optional<Admin> checkAdmin=adminRepo.findById(adminDetails.getAdminId());
        if(checkAdmin.isPresent())
            throw new AdminException("Admin already exist and please check provided details");
        String phoneNumber=adminDetails.getPhoneNumber();
        if(phoneNumber.length()>=10 || Pattern.matches("[0-9]{10}",phoneNumber))
            throw new AdminException("Admin Phone Number is Invalid");
        String email=adminDetails.getEmail();
        if(Pattern.matches("[@]{1}",email))
            throw new AdminException("Admin email is Invalid");
        if(Pattern.matches("[gmail,outlook]{0}",email))
            throw new AdminException("Admin email is Invalid");
        return this.adminRepo.save(adminDetails);
    }
    @Override
    public Admin updateAdminDetails(Admin adminDetails) throws AdminException {
        Optional<Admin> checkAdmin=adminRepo.findById(adminDetails.getAdminId());
        if(checkAdmin.isPresent())
            throw new AdminException("Admin already exist and please check provided details");
        String phoneNumber=adminDetails.getPhoneNumber();
        if(phoneNumber.length()>=10 || Pattern.matches("[0-9]{10}",phoneNumber))
            throw new AdminException("Admin Phone Number is Invalid");
        String email=adminDetails.getEmail();
        if(Pattern.matches("[@]{1}",email))
            throw new AdminException("Admin email is Invalid");
        if(Pattern.matches("[gmail,outlook]{0}",email))
            throw new AdminException("Admin email is Invalid");
        return this.adminRepo.save(adminDetails);
    }
    @Override
    public List<Admin> getAllAdminById(Admin adminDetails) throws AdminException {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equals("Super"))
            return this.adminRepo.findAll();
        else
            throw new AdminException("Admin Password is Invalid");
    }
    @Override
    public Admin getAdminById(Admin adminDetails, Integer id)throws AdminException  {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())) {
            Optional<Admin> adminFound = this.adminRepo.findById(id);
            if(adminFound.isEmpty())
                throw new AdminException("No admin is not found in this given Id");
            return adminFound.get();
        }
        else
            throw new AdminException("Admin Password is Invalid");
    }

    @Override
    public Admin getAdminByEmail(Admin adminDetails, String email) throws AdminException {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equals("Super")) {
            Optional<Admin> adminFound = this.adminRepo.findByEmailIgnoreCase(email);
            if(adminFound.isEmpty())
                throw new AdminException("No admin is not found in this given Id");
            return adminFound.get();
        }
        else
            throw new AdminException("Admin Password is Invalid");
    }

    @Override
    public Admin deleteAdminById(Admin adminDetails, Integer id) throws AdminException {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equals("Super")) {
            Optional<Admin> adminFound = this.adminRepo.findById(id);
            if(adminFound.isEmpty())
                throw new AdminException("No admin is not found in this given Id");
            this.adminRepo.deleteById(id);
            return adminFound.get();
        }
        else
            throw new AdminException("Admin Password is Invalid");
    }


}
