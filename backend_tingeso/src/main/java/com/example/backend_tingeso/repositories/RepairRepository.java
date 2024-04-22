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
    RepairEntity findByPatentrepair(@Param("patent") String patent);

    @Query(value = "SELECT * FROM repair WHERE repair.total = :total", nativeQuery = true)
    CarEntity findByTotal(@Param("total") double total);

    @Query(value = "SELECT * FROM repair WHERE repair.discountPerDay = :discountPerDay", nativeQuery = true)
    CarEntity findByDiscountPerDay(@Param("discountPerDay") double discountPerDay);

    @Query(value = "SELECT * FROM repair WHERE repair.discountPerbonus = :discountPerbonus", nativeQuery = true)
    CarEntity findByDiscountPerbonus(@Param("discountPerbonus") double discountPerbonus);

    @Query(value = "SELECT * FROM repair WHERE repair.delayCharge = :delayCharge", nativeQuery = true)
    CarEntity findByDelayCharge(@Param("delayCharge") double delayCharge);

    @Query(value = "SELECT * FROM repair WHERE repair.mileageCharge = :mileageCharge", nativeQuery = true)
    CarEntity findByMileageCharge(@Param("mileageCharge") double mileageCharge);

    @Query(value = "SELECT * FROM repair WHERE repair.seniorityCharge = :seniorityCharge", nativeQuery = true)
    CarEntity findBySeniorityCharge(@Param("seniorityCharge") double seniorityCharge);

}
