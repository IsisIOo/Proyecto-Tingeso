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

    @GetMapping("/patent/{patent}")
    public double costosTotales(@PathVariable String patent) {
        double costo = repairService.getCost(patent);
        double costo_iva = repairService.IVA(costo);
        return costo_iva;
    }

    @GetMapping("/discounts/{patent}")
    public double descuentos(@PathVariable String patent) {
        RepairEntity repairEntity = repairService.getRepairByPatent(patent);
        double descuento_por_dia= repairEntity.getDiscountPerDay();
        double descuento_por_marca= repairEntity.getDiscountPerbonus();
        double descuento_total= descuento_por_dia+descuento_por_marca;
        return descuento_total;
    }

    @GetMapping("/recharges/{patent}")
    public double recargos(@PathVariable String patent) {
        RepairEntity repairEntity = repairService.getRepairByPatent(patent);
        double recargo_por_dia= repairEntity.getDelayCharge();
        double recargo_por_antiguedad= repairEntity.getSeniorityCharge();
        double recargo_por_kilometro= repairEntity.getMileageCharge();
        double recargo_total= recargo_por_dia+recargo_por_antiguedad+recargo_por_kilometro;
        return recargo_total;
    }

}
