package com.example.backend_tingeso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.backend_tingeso.repositories.RecordRepository;
import com.example.backend_tingeso.entities.RecordEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
        List<RecordEntity> findByPatent(String patent); //no borrar


        //probar
        @Query(value = "SELECT * FROM record WHERE record.patent = :patent AND YEAR(record.date)=:year AND MONTH(record.date)=:month", nativeQuery = true)
        List<RecordEntity> getadmissionDate(@Param("patent") String patent, @Param("year") int year, @Param("month") int month);

}
