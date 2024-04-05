package com.example.backend_tingeso.repositories;

import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.entities.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long>  {
    @Query(value = "SELECT * FROM repair WHERE repair.patent = :patent", nativeQuery = true)
    CarEntity findBy(@Param("patent") String patent);

}
