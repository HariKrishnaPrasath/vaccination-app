package com.jpa.vaccinationapp.certificate.service;

import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.certificate.CertificateException;

import com.jpa.vaccinationapp.certificate.Certificate;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface CertificateService {
    public  Certificate addCerificate(Certificate certificateDetails)throws AdminException, CertificateException;
    public Certificate verifyAndApproveServiceByAdmin(Certificate certificateDetails)throws AdminException, CertificateException;
    public List<Certificate> getAllCertificate()throws AdminException, CertificateException;
    public Certificate getCertificateById(Integer id)throws AdminException, CertificateException;
    public Certificate deleteCertificate(Certificate certificateDetails)throws AdminException, CertificateException;
    public Certificate updateCertificateDetails(Certificate certificateDetails)throws AdminException, CertificateException;

    Appointment generateCertificate(Integer bookingId)  throws DocumentException, IOException;
}
