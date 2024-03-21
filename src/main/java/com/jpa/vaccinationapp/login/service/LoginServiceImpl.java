package com.jpa.vaccinationapp.login.service;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminRepository;
import com.jpa.vaccinationapp.login.Login;
import com.jpa.vaccinationapp.patient.Patient;
import com.jpa.vaccinationapp.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AdminRepository adminRepository;
    public Integer checkLogin(Login login) throws LoginException {
        if(login == null) throw new LoginException("Login cant be null");
        String type=login.getUserType();
        switch (type) {
            case "admin", "super admin": {
                Optional<Admin> admin = this.adminRepository.findByEmailIgnoreCase(login.getUserName());
                if (admin.isEmpty()) throw new LoginException("admin not found");
                if(admin.get().getPassword().equals(login.getPassword()))
                    return admin.get().getAdminId();
                else throw new LoginException("username or password incorrect");
            }
            case "patient": {
                Optional<Patient> patient = this.patientRepository.findByEmailIgnoreCase(login.getUserName());
                if (patient.isEmpty()) throw new LoginException("patient not found");
                if(patient.get().getPassword().equals(login.getPassword()))
                    return patient.get().getPatientId();
                else throw new LoginException("username or password incorrect");
            }
            default:
                return -1;
        }
    }
}
