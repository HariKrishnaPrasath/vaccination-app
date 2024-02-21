package com.jpa.vaccinationapp.certificate.service;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.admin.AdminRepository;
import com.jpa.vaccinationapp.certificate.Certificate;
import com.jpa.vaccinationapp.certificate.CertificateException;
import com.jpa.vaccinationapp.certificate.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService{
    @Autowired
    CertificateRepository certRepo;
    @Autowired
    AdminRepository adminRepo;
    @Override
    public Certificate addCerificate(Admin adminDetails, Certificate certificateDetails)
            throws AdminException, CertificateException {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equals("Super")) {
            Optional<Certificate> certificateFound=certRepo.findById(certificateDetails.getCertificateId());
            if(certificateFound.isPresent())
                throw new CertificateException("Certificate already exist");
            if(certificateFound.get().getApprovedStatus().equals("Approved"))
                throw new CertificateException("Check Certificate approval status");
            return this.certRepo.save(certificateDetails);
        }
        else {
            throw new AdminException("Admin password is Invalid");
        }
    }

    public Certificate verifyAndApproveServiceByAdmin(Admin adminDetails, Certificate certificateDetails)
            throws AdminException,CertificateException {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equals("Super")) {
            Optional<Certificate> certificateFound = certRepo.findById(certificateDetails.getCertificateId());
            if (certificateFound.isEmpty())
                throw new CertificateException("Certificate already exist");
            if(certificateFound.get().getApprovedStatus().equals("Approved"))
                throw new CertificateException("Check Certificate approval status");
            certificateFound.get().setApprovedStatus("Approved");
            return this.certRepo.save(certificateFound.get());
        }
        else{
            throw new AdminException("Admin password is Invalid");
        }
    }

    @Override
    public List<Certificate> getAllCertificate(Admin adminDetails)throws AdminException, CertificateException {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equals("Super")) {
            return this.certRepo.findAll();
        }
        else {
            throw new AdminException("Admin password is Invalid");
        }
    }

    @Override
    public Certificate getCertificateById(Admin adminDetails, Certificate certificateDetails)
            throws AdminException, CertificateException {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equals("Super")) {
            Optional<Certificate> certificateFound=certRepo.findById(certificateDetails.getCertificateId());
            if(certificateFound.isEmpty())
                throw new CertificateException("Certificate not exist");
            return certificateFound.get();
        }
        else {
            throw new AdminException("Admin password is Invalid");
        }
    }

    @Override
    public Certificate deleteCertificate(Admin adminDetails, Certificate certificateDetails)
            throws AdminException, CertificateException {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equals("Super")) {
            Optional<Certificate> certificateFound=certRepo.findById(certificateDetails.getCertificateId());
            if(certificateFound.isEmpty())
                throw new CertificateException("Certificate not exist");
            this.certRepo.delete(certificateDetails);
            return certificateFound.get();
        }
        else {
            throw new AdminException("Admin password is Invalid");
        }
    }

    @Override
    public Certificate updateCertificateDetails(Admin adminDetails, Certificate certificateDetails)
            throws AdminException, CertificateException {
        Optional<Admin> adminCheck=adminRepo.findById(adminDetails.getAdminId());
        if(adminCheck.isEmpty())
            throw new AdminException("Admin not found");
        if(adminCheck.get().getPassword().equals(adminDetails.getPassword())&&adminDetails.getAdminType().equals("Super")) {
            Optional<Certificate> certificateFound=certRepo.findById(certificateDetails.getCertificateId());
            if(certificateFound.isEmpty())
                throw new CertificateException("Certificate not exist");
            if(certificateFound.get().getApprovedStatus().equals("Approved"))
                throw new CertificateException("Check Certificate approval status");
            return this.certRepo.save(certificateDetails);
        }
        else {
            throw new AdminException("Admin password is Invalid");
        }
    }

}
