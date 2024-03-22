package com.jpa.vaccinationapp.certificate.service;

import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.admin.AdminRepository;
import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.appointment.AppointmentRepository;
import com.jpa.vaccinationapp.certificate.Certificate;
import com.jpa.vaccinationapp.certificate.CertificateException;
import com.jpa.vaccinationapp.certificate.CertificateRepository;
import com.jpa.vaccinationapp.certificate.PDFGenerator;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService{
    @Autowired
    CertificateRepository certRepo;
    @Autowired
    AdminRepository adminRepo;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Override
    public Certificate addCerificate(Certificate certificateDetails)
            throws AdminException, CertificateException {
            Optional<Certificate> certificateFound=certRepo.findById(certificateDetails.getCertificateId());
            if(certificateFound.isPresent())
                throw new CertificateException("Certificate already exist");
            if(certificateFound.get().getApprovedStatus().equals("Approved"))
                throw new CertificateException("Check Certificate approval status");
            return this.certRepo.save(certificateDetails);
    }

    public Certificate verifyAndApproveServiceByAdmin(Certificate certificateDetails)
            throws AdminException,CertificateException {
            Optional<Certificate> certificateFound = certRepo.findById(certificateDetails.getCertificateId());
            if (certificateFound.isEmpty())
                throw new CertificateException("Certificate not exist");
            if(certificateFound.get().getApprovedStatus().equals("Approved"))
                throw new CertificateException("Check Certificate approval status");
            certificateFound.get().setApprovedStatus("Approved");
            return this.certRepo.save(certificateFound.get());
    }

    @Override
    public List<Certificate> getAllCertificate()throws AdminException, CertificateException {
            return this.certRepo.findAll();

    }

    @Override
    public Certificate getCertificateById(Integer id)
            throws AdminException, CertificateException {
            Optional<Certificate> certificateFound=certRepo.findById(id);
            if(certificateFound.isEmpty())
                throw new CertificateException("Certificate not exist");
            return certificateFound.get();
    }

    @Override
    public Certificate deleteCertificate(Certificate certificateDetails)
            throws AdminException, CertificateException {
            Optional<Certificate> certificateFound=certRepo.findById(certificateDetails.getCertificateId());
            if(certificateFound.isEmpty())
                throw new CertificateException("Certificate not exist");
            this.certRepo.delete(certificateDetails);
            return certificateFound.get();
    }

    @Override
    public Certificate updateCertificateDetails(Certificate certificateDetails)
            throws AdminException, CertificateException {
            Optional<Certificate> certificateFound=certRepo.findById(certificateDetails.getCertificateId());
            if(certificateFound.isEmpty())
                throw new CertificateException("Certificate not exist");
            if(certificateFound.get().getApprovedStatus().equals("Approved"))
                throw new CertificateException("Check Certificate approval status");
            return this.certRepo.save(certificateDetails);

    }

    @Override
    public Appointment generateCertificate(Integer bookingId)  throws DocumentException, IOException {
        return this.appointmentRepository.findById(bookingId).get();

    }

}
