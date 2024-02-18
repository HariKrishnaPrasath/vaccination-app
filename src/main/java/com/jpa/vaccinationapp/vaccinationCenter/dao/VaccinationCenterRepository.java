package com.jpa.vaccinationapp.vaccinationCenter.dao;

import com.jpa.vaccinationapp.vaccinationCenter.entity.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, Integer> {
}
