package com.jpa.vaccinationapp.slot;

import com.jpa.vaccinationapp.vaccinationCenter.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {
    Slot findByCenterAndStartTimeAndEndTime(Center center, Date startTime, Date endTime);

    List<Slot> findByDate(Date date);

    List<Slot> findByCenter(Optional<Center> byId);
}
