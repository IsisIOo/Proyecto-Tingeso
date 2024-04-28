package com.example.backend_tingeso.controllers;


import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.repositories.RepairRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_tingeso.entities.RepairEntity;
import com.example.backend_tingeso.services.RepairService;
import com.example.backend_tingeso.services.CarService;
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
    @Autowired
    RepairRepository repairRepository;
    @Autowired
    CarService carService;

    //Obtener todas las reparaciones
    @GetMapping("/")
    //obtiene todas las reparaciones
    public ResponseEntity<List<RepairEntity>> listRepair() {
        List<RepairEntity> repair = repairService.getRepair();
        return ResponseEntity.ok(repair);
    }

    @GetMapping("/patentRepair/{patent}")
    public ResponseEntity<RepairEntity> getRepairBypatent(@PathVariable String patent) {
        RepairEntity repair = repairService.getRepairByPatent(patent);
        return ResponseEntity.ok(repair);
    }

    //ESTA ES LA IMPORTANTE
    @PostMapping("/{patent}")
    public ResponseEntity<RepairEntity> saveRepair(@PathVariable String patent) {
        RepairEntity repairNew = repairService.saveCostentity(patent);
        return ResponseEntity.ok(repairNew);
    }

    //calcula el costo de 1 solo record y auto
    @GetMapping("/patent/{patent}")
    public double costosTotales(@PathVariable String patent) {
        double costo = repairService.getCost(patent);
        return costo;
    }



    //DE AQUI PA ABAJO NO FUNCIONA
    //obtiene descuento y recargos individualmente
    @GetMapping("/discounts/{patent}")
    public double descuentos(@PathVariable String patent) {
        RepairEntity repairEntity = repairService.getRepairByPatent(patent);
        if (repairEntity == null) {
            System.out.println("RepairEntity es null para la patente: " + patent);
            return 0.0; // Retorna un valor predeterminado
        }
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

    //obtiene cuanto salia la reparacion sin descuentos ni nada
    @GetMapping("/repaircost/{patent}")
    public double costorepair(@PathVariable String patent) {
        RepairEntity repairEntity = repairService.getRepairByPatent(patent);
        double repair= repairEntity.getTotalOriginal();
        return repair;
    }


    //obtiene la entidad repair por su patente
    @GetMapping("/repaircostIVA/{patent}")
    public double costorepairiva(@PathVariable String patent) {
        RepairService carService;
        RepairEntity repairEntity = repairService.getRepairByPatent(patent);
        double repair= repairEntity.getTotalAmount();
        return repair;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCarById(@PathVariable Long id) throws Exception {
        var isDeleted = repairService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/{patent}")
    public ResponseEntity<List<RepairEntity>> listRepairByPatent(@PathVariable String patent) {
        List<RepairEntity> repair = repairService.getRepairByPatentfinal(patent);
        return ResponseEntity.ok(repair);
    }


    @GetMapping("/time")
    public int tiempo(String patent) {
        RepairEntity repairEntity = repairService.getRepairByPatent(patent);
        int tiempo= repairEntity.getWorkTime();
        return tiempo;
    }




}
