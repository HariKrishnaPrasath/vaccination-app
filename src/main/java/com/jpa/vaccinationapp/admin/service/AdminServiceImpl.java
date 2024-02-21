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
import java.util.regex.Pattern;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminRepository adminRepo;
    @Autowired
    CenterRepository vaccRepo;
    @Override
    public Admin addAdmin(Admin adminDetails) throws AdminException {
//        String phoneNumber=adminDetails.getPhoneNumber();
//        if(phoneNumber.length()>=10 || Pattern.matches("[0-9]{10}",phoneNumber))
//            throw new AdminException("Admin Phone Number is Invalid");
//        String email=adminDetails.getEmail();
//        if(Pattern.matches("[@]{1}",email))
//            throw new AdminException("Admin email is Invalid");
//        if(Pattern.matches("[gmail,outlook]{0}",email))
//            throw new AdminException("Admin email is Invalid");
        Optional<Admin> checkAdmin=adminRepo.findByEmailIgnoreCase(adminDetails.getEmail());
        if(checkAdmin.isPresent())
            throw new AdminException("Admin already exist and please check provided details");
        return this.adminRepo.save(adminDetails);
    }
    @Override
    public Admin updateAdminDetails(Admin adminDetails) throws AdminException {
        Optional<Admin> checkAdmin=adminRepo.findByEmailIgnoreCase(adminDetails.getEmail());
        if(checkAdmin.isPresent())
            throw new AdminException("Admin already exist and please check provided details");
        return this.adminRepo.save(adminDetails);
    }
    @Override
    public List<Admin> getAllAdminById(Admin adminDetails) throws AdminException {
        Optional<Admin> adminCheck=adminRepo.findByEmailIgnoreCase(adminDetails.getEmail());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equals("Super"))
            return this.adminRepo.findAll();
        else
            throw new AdminException("Admin Password is Invalid");
    }
    @Override
    public Admin getAdminById(Admin adminDetails, Integer id)throws AdminException  {
        Optional<Admin> adminCheck=adminRepo.findByEmailIgnoreCase(adminDetails.getEmail());
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
        Optional<Admin> adminCheck=adminRepo.findByEmailIgnoreCase(adminDetails.getEmail());
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
        Optional<Admin> adminCheck=adminRepo.findByEmailIgnoreCase(adminDetails.getEmail());
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

//    @Override
//    public Admin assignAdminToCentre(Admin adminDetails,Integer id,Integer adminId) throws AdminException {
//        Optional<Admin> adminCheck=adminRepo.findByEmailIgnoreCase(adminDetails.getEmail());
//        if(adminCheck.isEmpty())
//            throw new AdminException("Admin not found");
//        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equals("Super")) {
//            Optional<Center> centerFound=this.vaccRepo.findById(id);
//            if(centerFound.isEmpty())
//                throw new AdminException("No centre found");
//            Optional<Admin> adminFound=this.adminRepo.findById(adminId);
//            if(adminFound.isEmpty())
//                throw new AdminException("No admin Found in this ID");
//            adminFound.get().setVaccinationCenter(centerFound.get());
//            centerFound.get().setAdmin(adminFound.get());
//            this.vaccRepo.save(centerFound.get());
//            return this.adminRepo.save(adminFound.get());
//        }
//        else
//        {
//            throw new AdminException("Admin password is Invalid");
//        }
//    }
//    @Override
//    public Admin releaseAdminFromCentre(Admin adminDetails, Integer id, Integer adminId) throws AdminException {
//        Optional<Admin> adminCheck = adminRepo.findByEmailIgnoreCase(adminDetails.getEmail());
//        if (adminCheck.isEmpty())
//            throw new AdminException("Admin not found");
//        if (adminCheck.get().getPassword().equals(adminDetails.getPassword()) && adminDetails.getAdminType().equals("Super")) {
//            Optional<Center> centerFound = this.vaccRepo.findById(id);
//            if (centerFound.isEmpty())
//                throw new AdminException("No centre found");
//            Optional<Admin> adminFound = this.adminRepo.findById(adminId);
//            if (adminFound.isEmpty())
//                throw new AdminException("No admin Found in this ID");
//            adminFound.get().setVaccinationCenter(null);
//            centerFound.get().setAdmin(null);
//            this.vaccRepo.save(centerFound.get());
//            this.adminRepo.save(adminFound.get());
//            return adminFound.get();
//        } else {
//            throw new AdminException("Admin password is Invalid");
//        }
//    }
//
//    @Override
//    public Admin updateAdminCentreAssignment(Admin adminDetails, Integer id, Integer adminId) throws AdminException {
//        Optional<Admin> adminCheck=adminRepo.findByEmailIgnoreCase(adminDetails.getEmail());
//        if(adminCheck.isEmpty())
//            throw new AdminException("Admin not found");
//        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equals("Super")) {
//            Optional<Center> centerFound=this.vaccRepo.findById(id);
//            if(centerFound.isEmpty())
//                throw new AdminException("No centre found");
//            Optional<Admin> adminFound=this.adminRepo.findById(adminId);
//            if(adminFound.isEmpty())
//                throw new AdminException("No admin Found in this ID");
//            adminFound.get().setVaccinationCenter(centerFound.get());
//            centerFound.get().setAdmin(adminFound.get());
//            this.vaccRepo.save(centerFound.get());
//            return this.adminRepo.save(adminFound.get());
//        }
//        else
//        {
//            throw new AdminException("Admin password is Invalid");
//        }
//    }

}
