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
    @Autowired
    RepairService repairService;
    @Autowired
    RecordService carService;

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

    @GetMapping("/car/{patent}")
    //este recibe una lista
    public ResponseEntity<List<RecordEntity>> getRecordsByCarPatent(@PathVariable String patent) {
        List<RecordEntity> records = recordService.getRecordsByPatent(patent);
        return ResponseEntity.ok(records);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<RecordEntity> getExtraHourById(@PathVariable Long id) {
        RecordEntity recordEntity = RecordService.getExtraHourById(id);
        return ResponseEntity.ok(extraHour);
    }

    @PostMapping("/")
    public ResponseEntity<RecordEntity> saveExtraHours(@RequestBody RecordEntity extraHour) {
        RecordEntity extraHourNew = RecordService.saveExtraHours(extraHour);
        return ResponseEntity.ok(extraHourNew);
    }

    @GetMapping("/{rut}/{year}/{month}")
    public ResponseEntity<List<RecordEntity>> listExtraHoursByRut(@PathVariable("rut") String rut, @PathVariable("year") int year, @PathVariable("month") int month) {
        List<RecordEntity> extraHours = RecordService.getExtraHoursByRutYearMonth(rut,year,month);
        return ResponseEntity.ok(extraHours);
    }

    @PutMapping("/")
    public ResponseEntity<RecordEntity> updateExtraHours(@RequestBody RecordEntity extraHours){
        RecordEntity extraHoursUpdated = RecordService.updateExtraHour(extraHours);
        return ResponseEntity.ok(extraHoursUpdated);
    }
}*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRecordById(@PathVariable Long id) throws Exception {
        var isDeleted = recordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }

    /*@PutMapping("/PUTCOSTT1/{patent}")
    public ResponseEntity<RecordEntity> setAmount(@PathVariable String patent) {
        System.out.println("hola");
        RecordEntity recordHistory = recordService.getOneRecordRespository(patent);

        System.out.println("hola");
        double totalAmount = recordService.getCost(patent);

        System.out.println("hola");
        recordHistory.setTotalAmount(totalAmount);

        System.out.println("hola");
        return ResponseEntity.ok(recordHistory);
    }*/

   /* @PutMapping("/PUTCOSTT/{patent}/")
    public ResponseEntity<RecordEntity> updateRecord(@RequestBody RecordEntity record, @PathVariable String patent) throws Exception{
        RecordEntity recordHistory = recordService.getOneRecordRespository(patent);
        Long id = recordHistory.getId();
        var isDeleted = recordService.deleteRecord(id);

        RecordEntity recordUpdated = recordService.updateRecord(record);
        return ResponseEntity.ok(recordUpdated);
    }*/

        //recupera el record que posee la patenten y le asigna el costo total de la reparacion


    //fuNCION QUE YA NO SEEEE
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