package com.example.backend_tingeso.repositories;

import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.entities.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long>  {

    //busca segun patente en repair type
    @Query(value = "SELECT * FROM cost WHERE cost.patent = :patent", nativeQuery = true)
    RepairEntity findByPatentrepair(@Param("patent") String patent);


    //lista de repairs
    @Query(value = "SELECT * FROM cost WHERE cost.patent = :patent", nativeQuery = true)
    List<RepairEntity> findByPatentrepairfinal(@Param("patent") String patent);

    @Query(value = "SELECT * FROM repair WHERE repair.total = :total", nativeQuery = true)
    RepairEntity findByTotal(@Param("total") double total);

    @Query(value = "SELECT * FROM repair WHERE repair.discountPerDay = :discountPerDay", nativeQuery = true)
    RepairEntity findByDiscountPerDay(@Param("discountPerDay") double discountPerDay);

    @Query(value = "SELECT * FROM repair WHERE repair.discountPerbonus = :discountPerbonus", nativeQuery = true)
    RepairEntity findByDiscountPerbonus(@Param("discountPerbonus") double discountPerbonus);

    @Query(value = "SELECT * FROM repair WHERE repair.delayCharge = :delayCharge", nativeQuery = true)
    RepairEntity findByDelayCharge(@Param("delayCharge") double delayCharge);

    @Query(value = "SELECT * FROM repair WHERE repair.mileageCharge = :mileageCharge", nativeQuery = true)
    RepairEntity findByMileageCharge(@Param("mileageCharge") double mileageCharge);

    @Query(value = "SELECT * FROM repair WHERE repair.seniorityCharge = :seniorityCharge", nativeQuery = true)
    RepairEntity findBySeniorityCharge(@Param("seniorityCharge") double seniorityCharge);

}
