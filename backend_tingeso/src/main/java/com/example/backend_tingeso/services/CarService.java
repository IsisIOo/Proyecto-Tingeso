package com.example.backend_tingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.backend_tingeso.repositories.CarRepository;
import com.example.backend_tingeso.entities.CarEntity;


import java.util.ArrayList;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

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

    //public EmployeeEntity getEmployeeByRut(String rut){
    //    return employeeRepository.findByRut(rut);
    //}

    public boolean deleteCar(CarEntity car) throws Exception {
        try{
            carRepository.delete(car);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}