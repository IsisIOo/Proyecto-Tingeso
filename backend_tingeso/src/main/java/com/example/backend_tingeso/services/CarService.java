package com.example.backend_tingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.backend_tingeso.repositories.CarRepository;
import com.example.backend_tingeso.entities.CarEntity;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.ArrayList;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    //obtiene todos los autos
    public ArrayList<CarEntity> getCar(){
        return (ArrayList<CarEntity>) carRepository.findAll();
    }

    /*public ArrayList<CarEntity> getCarType(@PathVariable String type){
        return (ArrayList<CarEntity>) carRepository.findByType(type);
    }*/

    public CarEntity saveCar(CarEntity car){
        return carRepository.save(car);
    }

    public CarEntity getCarByPatent(String patent){
        return carRepository.findByPatent(patent);
    }

    /* encuentra solo un elemento
    public CarEntity getCarByType(String type){
        return carRepository.findByType(type);
    }*/

    public ArrayList<CarEntity> getCarByType(@PathVariable String type){
        return (ArrayList<CarEntity>) carRepository.findByType(type);
    }

    public CarEntity updateCar(CarEntity car) {
        return carRepository.save(car);
    }

    //public EmployeeEntity getEmployeeByRut(String rut){
    //    return employeeRepository.findByRut(rut);
    //}

    public boolean deleteCar(Long id) throws Exception {
        try{
            carRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}