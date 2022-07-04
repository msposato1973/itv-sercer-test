package com.itv.leedstech.techtest.repository;


import com.itv.leedstech.techtest.model.Programme;
import com.itv.leedstech.techtest.model.ScheduleEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleEntryRepository extends JpaRepository<ScheduleEntry,String> {
    List<ScheduleEntry> findScheduleEntryByProgramme (Programme programme);

    @Query(value = "SELECT * FROM Schedule_Entry s WHERE s.programme = :programme and s.timestamp >= :start && s.timestamp <= :end", nativeQuery = true)
    List<ScheduleEntry> findScheduleEntrySlotsCount (@Param("programme") Programme programme, @Param("start") LocalDateTime start,@Param("end")  LocalDateTime end);
}
