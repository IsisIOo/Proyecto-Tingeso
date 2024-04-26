package com.example.backend_tingeso.repositories;

import com.example.backend_tingeso.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.backend_tingeso.repositories.RecordRepository;
import com.example.backend_tingeso.entities.RecordEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
        List<RecordEntity> findByPatent(String patent); //no borrar, esta mala

        //probar
        @Query(value = "SELECT * FROM record WHERE record.patent = :patent AND YEAR(record.date)=:year AND MONTH(record.date)=:month", nativeQuery = true)
        List<RecordEntity> getadmissionDate(@Param("patent") String patent, @Param("year") int year, @Param("month") int month);

        @Query(value = "SELECT * FROM record WHERE record.patent = :patent" , nativeQuery = true)
        RecordEntity findByRepairTypeAndPatent(@Param("patent") String patent);

        //obtener un solo registro al cual le puedo hacer un get luego
        @Query(value = "SELECT * FROM record WHERE record.patent = :patent", nativeQuery = true)
        RecordEntity findByPatentOne(@Param("patent") String patent);


        //para encontrar los que tienen cierto tipo de reparacion. Encuentro el auto, tomo el tipo y la patente, con la patente accedo al tipo de
        //reparacion y en ese mismo lugar obtengo el costo total de la reparacion
        @Query(value = "SELECT * FROM record WHERE record.repairType = :repairType", nativeQuery = true)
        RecordEntity findByRepairType(@Param("repairType") String repairType);

}
