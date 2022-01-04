package com.example.emaillogtime.reposiotry;

import com.example.emaillogtime.entity.EntriesTime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntriesTimeRepository extends JpaRepository<EntriesTime, Long> {
//    @Query("select l.hours from Entries_Time l")
//    List<Float> getAllHours();
//
//    @Query("select l.hours from Entries_Time l where l.user.userId= ?1")
//    List<Float> getAllHoursByUserId(Long userId);

//    @Query(value = "select l.hours from EntriesTime l where l.user.userId=?1 ORDER BY l.date desc Limit 0 3", nativeQuery = true)
//    List<Float> getAllHoursAndAndDateOk(Long userId);

    @Query("select l.hours from EntriesTime l where l.user.userId=?1 and l.date = current_date")
    List<Float> getAllHoursAndAndDate(Long userId);

    @Query("select l.hours from EntriesTime l where l.user.userId=?1 ORDER BY l.date desc")
    List<Float> getAllHoursAndAndDateOk(Long userId, Pageable pageable);



}
