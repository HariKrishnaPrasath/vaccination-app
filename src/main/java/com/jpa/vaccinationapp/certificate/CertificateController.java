package com.jpa.vaccinationapp.certificate;

import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.certificate.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CertificateController {
    @Autowired
    CertificateService certificateService;
    @PostMapping("certificate/add")
    public Certificate addCertificate(@RequestBody Certificate certificateDetails) throws AdminException, CertificateException {
        return this.certificateService.addCerificate(certificateDetails);
    }
    @PatchMapping("certificate/verify")
    public Certificate verifyCertificate(@RequestBody Certificate certificateDetails) throws AdminException, CertificateException {
        return this.certificateService.verifyAndApproveServiceByAdmin(certificateDetails);
    }
    @GetMapping("certificate/getall")
    public List<Certificate> getAllCertificate() throws AdminException, CertificateException {
        return this.certificateService.getAllCertificate();
    }
    @DeleteMapping("certificate/delete/{id}")
    public Certificate deleteCertificate(@PathVariable Integer id) throws AdminException, CertificateException {
        Certificate certificateDetails=this.certificateService.getCertificateById(id);
        return this.certificateService.deleteCertificate(certificateDetails);
    }
    @PutMapping("certificate/update")
    public Certificate updateCertificate(@RequestBody Certificate certificateDetails) throws AdminException, CertificateException {
        return this.certificateService.updateCertificateDetails(certificateDetails);
    }
    @GetMapping("certificate/getbyid/{id}")
    public Certificate getCertificateById(@PathVariable Integer id) throws AdminException, CertificateException {
        return this.certificateService.getCertificateById(id);
    }

}
