package com.jpa.vaccinationapp.bookingDetail.dao;

import com.jpa.vaccinationapp.bookingDetail.entity.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetail extends JpaRepository<BookingDetails, Integer> {
}
