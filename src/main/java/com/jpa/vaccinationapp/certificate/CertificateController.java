package com.jpa.vaccinationapp.certificate;

import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.certificate.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class CertificateController {
    @Autowired
    CertificateService certificateService;
    @PostMapping("certificate/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Certificate addCertificate(@RequestBody Certificate certificateDetails) throws AdminException, CertificateException {
        return this.certificateService.addCerificate(certificateDetails);
    }
    @PatchMapping("certificate/verify")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Certificate verifyCertificate(@RequestBody Certificate certificateDetails) throws AdminException, CertificateException {
        return this.certificateService.verifyAndApproveServiceByAdmin(certificateDetails);
    }
    @GetMapping("certificate/getall")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Certificate> getAllCertificate() throws AdminException, CertificateException {
        return this.certificateService.getAllCertificate();
    }
    @DeleteMapping("certificate/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Certificate deleteCertificate(@PathVariable Integer id) throws AdminException, CertificateException {
        Certificate certificateDetails=this.certificateService.getCertificateById(id);
        return this.certificateService.deleteCertificate(certificateDetails);
    }
    @PutMapping("certificate/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Certificate updateCertificate(@RequestBody Certificate certificateDetails) throws AdminException, CertificateException {
        return this.certificateService.updateCertificateDetails(certificateDetails);
    }
    @GetMapping("certificate/getbyid/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Certificate getCertificateById(@PathVariable Integer id) throws AdminException, CertificateException {
        return this.certificateService.getCertificateById(id);
    }

}
