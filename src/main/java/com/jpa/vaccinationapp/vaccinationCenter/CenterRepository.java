package com.jpa.vaccinationapp.vaccinationCenter;

import com.jpa.vaccinationapp.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterRepository extends JpaRepository<Center, Integer> {

    List<Center> findCenterByCenterNameIsContainingIgnoreCase(String centerName);
    List<Center> findByPincode(String pincode);
    Center findByAdmin(Admin admin);


}
