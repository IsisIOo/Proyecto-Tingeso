package com.example.backend_tingeso.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_tingeso.entities.RepairEntity;
import com.example.backend_tingeso.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repairs")
@CrossOrigin("*")

public class RepairController {
    @Autowired
    RepairService repairService;

    //Obtener todas las reparaciones
    @GetMapping("/")
    //obtiene todas las reparaciones
    public ResponseEntity<List<RepairEntity>> listRepair() {
        List<RepairEntity> repair = repairService.getRepair();
        return ResponseEntity.ok(repair);
    }

    //ESTA ES LA IMPORTANTE
    @PostMapping("/{patent}")
    public ResponseEntity<RepairEntity> saveRepair(@PathVariable String patent) {
        RepairEntity repairNew = repairService.saveCostentity(patent);
        return ResponseEntity.ok(repairNew);
    }

    //DE AQUI PA ABAJO NO FUNCIONA
    //obtiene descuento y recargos individualmente
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRepairById(@PathVariable Long id) throws Exception {
        var isDeleted = repairService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/{patent}")
    public ResponseEntity<List<RepairEntity>> listRepairByPatent(@PathVariable String patent) {
        List<RepairEntity> repair = repairService.getRepairByPatentfinal(patent);
        return ResponseEntity.ok(repair);
    }

}
