package com.example.backend_tingeso.repositories;

import com.example.backend_tingeso.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    @Query(value = "SELECT * FROM car WHERE car.patent = :patent", nativeQuery = true)
    CarEntity findByPatent(@Param("patent") String patent);


}
