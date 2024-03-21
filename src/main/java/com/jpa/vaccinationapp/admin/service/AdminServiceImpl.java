package com.jpa.vaccinationapp.admin.service;

import com.jpa.vaccinationapp.admin.AdminRepository;
import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.admin.Login;
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
        this.adminRepo.save(adminDetails);
        return adminDetails;
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
        this.adminRepo.deleteById(id);
        return adminFound.get();
    }
    @Override
    public Admin loginAdmin(Login loginDetails) throws AdminException {
        Optional<Admin> optionalAdmin =this.adminRepo.findByEmailIgnoreCase(loginDetails.getEmail());
        if(optionalAdmin.isEmpty())
            throw adminNotFoundException;
        if(optionalAdmin.get().getPassword().equals(loginDetails.getPassword())){
            return optionalAdmin.get();
        }
        else
            throw adminException;
    }

}