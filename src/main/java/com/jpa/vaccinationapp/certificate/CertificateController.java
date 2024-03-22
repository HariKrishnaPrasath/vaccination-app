package com.jpa.vaccinationapp.certificate;

import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.certificate.service.CertificateService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/export-to-pdf/{bookingId}")
    public void generatePdfFile(@PathVariable Integer bookingId, HttpServletResponse response) throws DocumentException, IOException
    {
        Appointment appointment = this.certificateService.generateCertificate(bookingId);

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=certificate_" + appointment.getPatient().getPatientName() +
                currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        PDFGenerator generator = new PDFGenerator();
        generator.generate(appointment, response);
    }

}
