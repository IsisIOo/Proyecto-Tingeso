package com.example.backend_tingeso.controllers;

import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
@CrossOrigin("*")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping("/")
    public ResponseEntity<List<CarEntity>> listCar() {
        List<CarEntity> car = carService.getCar();
        return ResponseEntity.ok(car);
    }

    @GetMapping("/{patent}")
    public ResponseEntity<CarEntity> getCarBypatent(@PathVariable String patent) {
        CarEntity car = carService.getCarByPatent(patent);
        return ResponseEntity.ok(car);
    }

    @PostMapping("/")
    public ResponseEntity<CarEntity> saveCar(@RequestBody CarEntity car) {
        CarEntity carNew = carService.saveCar(car);
        return ResponseEntity.ok(carNew);
    }

    @PutMapping("/")
    public ResponseEntity<CarEntity> updateCar(@RequestBody CarEntity car){
        CarEntity carUpdated = carService.updateCar(car);
        return ResponseEntity.ok(carUpdated);
    }

    @DeleteMapping("/{patent}")
    public ResponseEntity<Boolean> delete(@PathVariable CarEntity car) throws Exception {
        var isDeleted = carService.deleteCar(car);
        return ResponseEntity.noContent().build();
    }
}
