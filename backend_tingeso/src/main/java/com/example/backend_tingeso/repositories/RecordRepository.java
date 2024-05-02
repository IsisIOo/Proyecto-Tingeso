package com.example.backend_tingeso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.backend_tingeso.entities.RecordEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
        List<RecordEntity> findByPatent(String patent); //no borrar, esta mala

        //obtener un solo registro al cual le puedo hacer un get luego
        @Query(value = "SELECT * FROM record WHERE record.patent = :patent", nativeQuery = true)
        RecordEntity findByPatentOne(@Param("patent") String patent);

}
