package com.example.backend_tingeso.repositories;


import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.entities.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    //public CarEntity findByBrand(String brand); //encuentra los autos de una marca, peeero hice otro, no se si este es util

   // List<CarEntity> findByCategory(String category);
    //List<CarEntity> findBySalaryGreaterThan(int salary);
    //List<CarEntity> findByChildrenBetween(Integer startChildren, Integer endChildren);
    @Query(value = "SELECT * FROM car WHERE car.patent = :patent", nativeQuery = true)
    CarEntity findByPatent(@Param("patent") String patent);

    /*@Query(value = "SELECT * FROM car WHERE car.type = :type", nativeQuery = true)
    CarEntity findByType(@Param("type") String type);
    //ya no necesito esta pq esta solo encuentra 1*/

    @Query(value = "SELECT * FROM car WHERE car.type = :type", nativeQuery = true)
    ArrayList<CarEntity> findByType(@Param("type") String type);

    @Query(value = "SELECT * FROM car WHERE car.production_year  = :production_year", nativeQuery = true)
    ArrayList<CarEntity> findByProductionyear(@Param("production_year") int production_year);
    //tener cuidado con el nombre de las tablas en el where car. [aqui despues del punto]

    @Query(value = "SELECT * FROM car WHERE car.motor_type  = :motor_type", nativeQuery = true)
    ArrayList<CarEntity> findByMotortype(@Param("motor_type") String motor_type);

    @Query(value = "SELECT * FROM car WHERE car.brand = :brand", nativeQuery = true)
    ArrayList<CarEntity> findByBrand(@Param("brand") String brand);

    //recupera los historiales del auto
    @Query(value = "SELECT * FROM record WHERE record.patent = :patent", nativeQuery = true)
    RecordEntity findByPatent2(@Param("patent") String patent);

}
