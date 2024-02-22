package com.jpa.vaccinationapp.admin.service;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminException;


import java.util.List;

public interface AdminService {
    public Admin addAdmin(Admin adminDetails)throws AdminException;
    public Admin updateAdminDetails(Admin adminDetails)throws AdminException;
    public List<Admin> getAllAdmin() throws AdminException;
    public Admin getAdminById(Integer id)throws AdminException;
    public Admin getAdminByEmail(String email)throws AdminException;
    public Admin deleteAdminById(Integer id)throws AdminException;

}
