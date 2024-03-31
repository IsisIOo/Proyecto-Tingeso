package com.example.backend_tingeso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend_tingeso.entities.RecordEntity;
import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.services.RecordService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/record")
@CrossOrigin("*")
public class RecordController {
    @Autowired
    RecordService recordService;

    @GetMapping("/")
    public ResponseEntity<List<RecordEntity>> listExtraHours() {
        List<RecordEntity> extraHours = RecordService.getExtraHours();
        return ResponseEntity.ok(extraHours);

    }

    @GetMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteExtraHoursById(@PathVariable Long id) throws Exception {
        var isDeleted = RecordService.deleteExtraHour(id);
        return ResponseEntity.noContent().build();
    }
}