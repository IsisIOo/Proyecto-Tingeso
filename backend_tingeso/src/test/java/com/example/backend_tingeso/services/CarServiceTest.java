package com.example.backend_tingeso.services;

import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.repositories.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    void testGetCar() {
        // Mocking the behavior of CarRepository
        List<CarEntity> cars = new ArrayList<>();
        cars.add(new CarEntity(null,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000));

        cars.add(new CarEntity(null,
                "DEF456",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000));

        // Mocking the behavior of CarRepository
        when(carRepository.findAll()).thenReturn(cars);

        // Calling the method under test
        List<CarEntity> result = carService.getCar();

        // Verifying the result
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testSaveCar() {
        // Creating a mock CarEntity
        CarEntity car = new CarEntity(null,
                "DEF456",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000);

        // Mocking the behavior of CarRepository
        when(carRepository.save(any(CarEntity.class))).thenReturn(car);

        // Calling the method under test
        CarEntity savedCar = carService.saveCar(car);

        // Verifying the result
        Assertions.assertEquals(car, savedCar);
    }

    // Similarly, you can write tests for other methods like getCarByPatent, updateCar, and deleteCar
}
