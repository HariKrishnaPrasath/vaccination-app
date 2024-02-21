package com.jpa.vaccinationapp.vaccine.dao;

import com.jpa.vaccinationapp.vaccine.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {

     List<Vaccine> findByVaccineNameIgnoreCase(String vaccineName);
     @Modifying
     @Query("delete from Vaccine v where v.expiryDate < :currentDate")
     List<Vaccine> deleteRecordsByExpiryDateBefore(@Param("currentDate") LocalDate currentDate);



   //  List<Vaccine> findByExpiryDate();
}
