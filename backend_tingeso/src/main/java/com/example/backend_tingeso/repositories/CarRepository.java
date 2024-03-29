package com.example.backend_tingeso.repositories;

import com.example.backend_tingeso.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, String> {
    public CarEntity findByBrand(String brand); //encuentra los autos de una marca

    List<CarEntity> findByCategory(String category);
    List<CarEntity> findBySalaryGreaterThan(int salary);
    List<CarEntity> findByChildrenBetween(Integer startChildren, Integer endChildren);
    @Query(value = "SELECT * FROM car WHERE car.patent = :patent", nativeQuery = true)
    CarEntity findByPatent(@Param("patent") String patent);
}
