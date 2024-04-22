package com.example.backend_tingeso.controllers;


import com.example.backend_tingeso.entities.CarEntity;
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

    @PostMapping("/")
    public ResponseEntity<RepairEntity> saveRepair(String patent) {
        RepairEntity repairNew = repairService.saveCostentity(patent);
        return ResponseEntity.ok(repairNew);
    }

    //calcula el de 1 solo
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

    @GetMapping("/recharge-delay/{patent}")
    public double recargodelay(@PathVariable String patent) {
        RepairEntity repairEntity = repairService.getRepairByPatent(patent);
        double recargo_por_dia= repairEntity.getDelayCharge();
        return recargo_por_dia;
    }

    @GetMapping("/recharge-senior/{patent}")
    public double recargoantiguedad(@PathVariable String patent) {
        RepairEntity repairEntity = repairService.getRepairByPatent(patent);
        double recargo_por_viejo= repairEntity.getSeniorityCharge();
        return recargo_por_viejo;
    }

    @GetMapping("/recharge-mile/{patent}")
    public double recargoamile(@PathVariable String patent) {
        RepairEntity repairEntity = repairService.getRepairByPatent(patent);
        double recargo_por_kilometro= repairEntity.getMileageCharge();
        return recargo_por_kilometro;
    }

    @GetMapping("/discount-dia/{patent}")
    public double descuentodia(@PathVariable String patent) {
        RepairEntity repairEntity = repairService.getRepairByPatent(patent);
        double descuentodia= repairEntity.getDiscountPerDay();
        return descuentodia;
    }

    @GetMapping("/discount-marca/{patent}")
    public double descuentomarca(@PathVariable String patent) {
        RepairEntity repairEntity = repairService.getRepairByPatent(patent);
        double descuentomarca= repairEntity.getDiscountPerbonus();
        return descuentomarca;
    }

    @GetMapping("/repaircost/{patent}")
    public double costorepair(@PathVariable String patent) {
        RepairEntity repairEntity = repairService.getRepairByPatent(patent);
        double repair= repairEntity.getTotalOriginal();
        return repair;
    }






}
