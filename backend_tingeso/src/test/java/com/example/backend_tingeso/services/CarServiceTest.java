package com.example.backend_tingeso.services;

import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.repositories.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;


public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCar() {
        // Mocking the behavior of CarRepository
        List<CarEntity> cars = new ArrayList<>();
        cars.add(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000));

        cars.add(new CarEntity(2L,
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
        CarEntity car = new CarEntity(1L,
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

    @Test
    void testGetCarByPatent() {
        // Mocking the behavior of CarRepository
        String patent = "ABC123";
        CarEntity car = new CarEntity(2L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000);
        when(carRepository.findByPatent(patent)).thenReturn(car);

        // Calling the method under test
        CarEntity result = carService.getCarByPatent(patent);

        // Verifying the result
        Assertions.assertEquals(patent, result.getPatent());
    }

    @Test
    void testUpdateCar() {
        // Creating a mock CarEntity
        CarEntity car = new CarEntity(2L,
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
        CarEntity updatedCar = carService.updateCar(car);

        // Verifying the result
        Assertions.assertEquals(car, updatedCar);
    }

    @Test
    void testDeleteCar() {
        // Mocking the behavior of CarRepository
        Long id = 1L;
        doNothing().when(carRepository).deleteById(id);

        // Calling the method under test
        boolean result = false;
        try {
            result = carService.deleteCar(id);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception properly in your code
        }

        // Verifying the result
        Assertions.assertTrue(result);
    }

}
