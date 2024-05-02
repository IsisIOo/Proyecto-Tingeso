package com.example.backend_tingeso.repositories;

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


}
