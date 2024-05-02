package com.example.backend_tingeso.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend_tingeso.repositories.CarRepository;
import com.example.backend_tingeso.entities.CarEntity;

import java.util.*;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    //obtiene todos los autos
    public ArrayList<CarEntity> getCar(){
        return (ArrayList<CarEntity>) carRepository.findAll();
    }

    public CarEntity saveCar(CarEntity car){
        return carRepository.save(car);
    }

    public CarEntity getCarByPatent(String patent){
        return carRepository.findByPatent(patent);
    }

    public CarEntity updateCar(CarEntity car) {
        return carRepository.save(car);
    }

    public boolean deleteCar(Long id) throws Exception {
        try{
            carRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}