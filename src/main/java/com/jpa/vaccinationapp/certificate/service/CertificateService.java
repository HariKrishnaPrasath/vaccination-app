package com.jpa.vaccinationapp.certificate.service;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.certificate.CertificateException;

import com.jpa.vaccinationapp.certificate.Certificate;

import java.util.List;

public interface CertificateService {
    public  Certificate addCerificate(Admin adminDetails,Certificate certificateDetails)throws AdminException, CertificateException;
    public Certificate verifyAndApproveServiceByAdmin(Admin adminDetails, Certificate certificateDetails)throws AdminException, CertificateException;
    public List<Certificate> getAllCertificate(Admin adminDetails)throws AdminException, CertificateException;
    public Certificate getCertificateById(Admin adminDetails,Certificate certificateDetails)throws AdminException, CertificateException;
    public Certificate deleteCertificate(Admin adminDetails,Certificate certificateDetails)throws AdminException, CertificateException;
    public Certificate updateCertificateDetails(Admin adminDetails,Certificate certificateDetails)throws AdminException, CertificateException;
}
