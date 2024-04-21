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
    public double queso(@PathVariable String patent) {
        double a = repairService.getCost(patent);
        return a;
    }
}
