package com.example.backend_tingeso.controllers;

import com.example.backend_tingeso.entities.RepairEntity;
import com.example.backend_tingeso.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend_tingeso.entities.RecordEntity;
import com.example.backend_tingeso.services.RecordService;
import com.example.backend_tingeso.entities.CarEntity;


import java.util.List;

@RestController
@RequestMapping("/api/v1/record")
@CrossOrigin("*")
public class RecordController {
    @Autowired
    RecordService recordService;

    //todos los weas
    @GetMapping("/")
    //este obtiene todos los registros existentes
    public ResponseEntity<List<RecordEntity>> listRecords() {
        List<RecordEntity> recordHistory = recordService.getRecordRepository();
        return ResponseEntity.ok(recordHistory);

    }

    @GetMapping("/patent1/{patent}")
    //recibe solo un registro
    public ResponseEntity<RecordEntity> getOneRecordByPatent(@PathVariable String patent) {
        RecordEntity recordHistory = recordService.getOneRecordRespository(patent);
        return ResponseEntity.ok(recordHistory);
    }

    @PostMapping("/")
    public ResponseEntity<RecordEntity> saveRecord(@RequestBody RecordEntity recordHistory) {
        RecordEntity recordHistoryNew = recordService.saveRecord(recordHistory);
        return ResponseEntity.ok(recordHistoryNew);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRecordById(@PathVariable Long id) throws Exception {
        var isDeleted = recordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/newRecord/")
    public ResponseEntity<RecordEntity> updateRecord(@RequestBody RecordEntity rec){

        //Creamos una nueva entidad con new
        RecordEntity recordHistory = new RecordEntity();

        //Conseguimos los costos para colocarlo en el auto
        double totalAmount = recordService.getCostbyRepair(rec);

        //Vamos a colocar cada uno de los componentes en el nuevo auto
        recordHistory.setPatent(rec.getPatent());
        recordHistory.setId(rec.getId());
        recordHistory.setAdmissionHour(rec.getAdmissionHour());
        recordHistory.setAdmissionDateDayName(rec.getAdmissionDateDayName());
        recordHistory.setAdmissionDateDay(rec.getAdmissionDateDay());
        recordHistory.setAdmissionDateMonth(rec.getAdmissionDateMonth());
        recordHistory.setRepairType(rec.getRepairType());
        recordHistory.setDepartureDateDay(rec.getDepartureDateDay());
        recordHistory.setDepartureDateMonth(rec.getDepartureDateMonth());
        recordHistory.setDepartureHour(rec.getDepartureHour());
        recordHistory.setClientDateDay(rec.getClientDateDay());
        recordHistory.setClientDateMonth(rec.getClientDateMonth());
        recordHistory.setClientHour(rec.getClientHour());
        recordHistory.setTotalAmount(totalAmount);

        RecordEntity recordHistoryNew = recordService.saveRecord(recordHistory);
        return ResponseEntity.ok(recordHistoryNew);
    }






}