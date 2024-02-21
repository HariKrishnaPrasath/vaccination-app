package com.jpa.vaccinationapp.admin.service;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import com.jpa.vaccinationapp.vaccine.VaccineException;

import java.util.List;

public interface AdminService {
    public Admin addAdmin(Admin adminDetails)throws AdminException;
    public Admin updateAdminDetails(Admin adminDetails)throws AdminException;
    public List<Admin> getAllAdminById(Admin adminCheck) throws AdminException;
    public Admin getAdminById(Admin adminCheck,Integer id)throws AdminException;
    public Admin getAdminByEmail(Admin adminDetails,String email)throws AdminException;
    public Admin deleteAdminById(Admin adminDetails,Integer id)throws AdminException;
//    public Admin assignAdminToCentre(Admin adminDetails,Integer id,Integer adminId)throws AdminException;
//    public Admin releaseAdminFromCentre(Admin adminDetails,Integer id,Integer adminId)throws AdminException;
//    public Admin updateAdminCentreAssignment(Admin adminDetails,Integer id,Integer adminId)throws AdminException;

}
