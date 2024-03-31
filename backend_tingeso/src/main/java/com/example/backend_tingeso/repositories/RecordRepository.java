package com.example.backend_tingeso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.backend_tingeso.repositories.RecordRepository;
import com.example.backend_tingeso.entities.RecordEntity;

import java.util.List;

public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
        public List<RecordEntity> findByRut(String rut);
        @Query(value = "SELECT * FROM record WHERE record.patent = :rut AND YEAR(record.date)=:year AND MONTH(record.date)=:month", nativeQuery = true)
        List<RecordEntity> getadmissionDate(@Param("patent") String patent, @Param("year") int year, @Param("month") int month);

    }

}
