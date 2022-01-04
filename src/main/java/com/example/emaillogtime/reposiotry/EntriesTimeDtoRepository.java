package com.example.emaillogtime.reposiotry;

import com.example.emaillogtime.dto.EntriesTimeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntriesTimeDtoRepository extends JpaRepository<EntriesTimeDTO, Long> {
    @Query("select l.timeWeekDto from EntriesTimeDTO l")
    Float getEntriesTimeDTO();
}
