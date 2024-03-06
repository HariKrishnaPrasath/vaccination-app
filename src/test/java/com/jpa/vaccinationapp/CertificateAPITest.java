package com.jpa.vaccinationapp;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.certificate.Certificate;
import com.jpa.vaccinationapp.certificate.CertificateException;
import com.jpa.vaccinationapp.certificate.service.CertificateService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance. Lifecycle. PER_CLASS)
 class CertificateAPITest {
    @Autowired
    CertificateService certService;
    @BeforeAll
    @Transactional
    void beforeAll(){
        try {
            this.certService.addCerificate(new Certificate(101,LocalDate.of(2024,1,12),"Certificate.com","Pending"));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    @AfterAll
    void afterAll(){
        try {
            this.certService.deleteCertificate(new Certificate(101, LocalDate.of(2024,1,12),"Certificate.com","Pending"));
        }
        catch (Exception e) {
            e.getMessage();
        }
    }
    @Test
     void addCertificateTest1(){
        Certificate certTest=null;
        try {
            certTest=this.certService.addCerificate(new Certificate(102, LocalDate.of(2024,1,12),"Certificate.com","Pending"));
            Assertions.assertNotNull(certTest);
        } catch (Exception e) {
            e.getMessage();
        }
    }
    @Test
     void addCertificateTest2(){
        try {
            this.certService.addCerificate(new Certificate(104, LocalDate.of(2024,1,12),"Certificate.com","Approved"));
        } catch (Exception e) {
            Assertions.assertNotEquals("Certificate already exist",e.getMessage());
        }
    }
    @Test
     void verifyTest1(){
        Certificate certTest=null;
        try{
            certTest=this.certService.verifyAndApproveServiceByAdmin(new Certificate(101,"Approved"));
            Assertions.assertNotNull(certTest);
        }
        catch (Exception e){
            e.getMessage();
        }
    }
    @Test
     void verifyTest2(){
        Certificate certTest=null;
        try{
            certTest=this.certService.verifyAndApproveServiceByAdmin(new Certificate(105,"Approved"));
            Assertions.assertNotNull(certTest);
        }
        catch (Exception e){
            Assertions.assertEquals("Certificate not exist",e.getMessage());
        }
    }
    @Test
     void getCertificateTest1(){
        List<Certificate> certificateList;
        try {
            certificateList=this.certService.getAllCertificate();
            Assertions.assertNotNull(certificateList);
        }
        catch (Exception e){
            e.getMessage();
        }
    }
    @Test
     void getCertificateTest2(){
        Certificate certTest=null;
        try {
            certTest=this.certService.getCertificateById(101);
            Assertions.assertEquals(101,certTest.getCertificateId());
        }
        catch (Exception e){
            e.getMessage();
        }
    }
    @Test
     void getCertificateTest3(){
        Certificate certTest=null;
        try {
            certTest=this.certService.getCertificateById(101);
        }
        catch (Exception e){
            Assertions.assertEquals("Certificate not exist",e.getMessage());
        }
    }
    @Test
     void deleteCertificateTest1(){
        try {
            this.certService.addCerificate(new Certificate(103, LocalDate.of(2024,1,12),"Certificate.com","Approved"));
        }
        catch (Exception e){
            e.getMessage();
        }
        Certificate certTest=null;
        try {
            certTest=this.certService.deleteCertificate(new Certificate(103, LocalDate.of(2024,1,12),"Certificate.com","Approved"));
            Assertions.assertEquals(103,certTest.getCertificateId());
        }
        catch (Exception e){
            e.getMessage();
        }
    }
    @Test
     void deleteCertificateTest2(){
        Certificate certTest=null;
        try {
            certTest=this.certService.deleteCertificate(new Certificate(103, LocalDate.of(2024,1,12),"Certificate.com","Approved"));
        }
        catch (Exception e){
            Assertions.assertEquals("Certificate not exist",e.getMessage());
        }
    }
    @Test
     void updateCertificateTest1(){
        Certificate certTest=null;
        try {
            certTest=this.certService.deleteCertificate(new Certificate(101, LocalDate.of(2023,1,12),"Certificate.com","Approved"));
            Assertions.assertNotNull(certTest);
        }
        catch (Exception e){
            Assertions.assertEquals("Certificate not exist",e.getMessage());
        }
    }
}
