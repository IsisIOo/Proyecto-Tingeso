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
import static org.mockito.Mockito.*;


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

        when(carRepository.findAll()).thenReturn(cars);

        List<CarEntity> result = carService.getCar();

        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testGetCar0() {
        List<CarEntity> cars = new ArrayList<>();

        when(carRepository.findAll()).thenReturn(cars);

        List<CarEntity> result = carService.getCar();

        Assertions.assertEquals(0, result.size());
    }

    @Test
    void testGetCars() {
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
        cars.add(new CarEntity(2L,
                "JKL768",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000));

        when(carRepository.findAll()).thenReturn(cars);

        List<CarEntity> result = carService.getCar();

        Assertions.assertEquals(3, result.size());
    }

    @Test
    void testSaveCar() {
        CarEntity car = new CarEntity(1L,
                "DEF456",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000);

        when(carRepository.save(any(CarEntity.class))).thenReturn(car);

        CarEntity savedCar = carService.saveCar(car);

        Assertions.assertEquals(car, savedCar);
    }

    @Test
    void testGetCarByPatent() {
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

        CarEntity result = carService.getCarByPatent(patent);

        Assertions.assertEquals(patent, result.getPatent());
    }

    @Test
    void testUpdateCar() {
        CarEntity car = new CarEntity(2L,
                "DEF456",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000);

        when(carRepository.save(any(CarEntity.class))).thenReturn(car);

        CarEntity updatedCar = carService.updateCar(car);

        Assertions.assertEquals(car, updatedCar);
    }

    @Test
    void testDeleteCar() {
        Long id = 1L;
        doNothing().when(carRepository).deleteById(id);

        boolean result = false;
        try {
            result = carService.deleteCar(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(result);
    }

    @Test
    void testDeleteCarFalse() {
        Long id = 1L;
        doNothing().when(carRepository).deleteById(2L);

        boolean result = false;
        try {
            result = carService.deleteCar(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(result);
    }

    @Test
    void testUpdateCar2() {
        CarEntity car = new CarEntity(1L,
                "DEF456",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000);

        // Configuración del repositorio para devolver el auto actualizado
        when(carRepository.save(any(CarEntity.class))).thenReturn(car);

        // Actualización del auto
        CarEntity updatedCar = carService.updateCar(car);

        // Verificación de que el auto devuelto es el mismo que se pasó como parámetro
        Assertions.assertEquals(car, updatedCar);
    }

    @Test
    void testDeleteCarFailure() {
        Long id = 1L;

        // Configuración del repositorio para lanzar una excepción al eliminar el auto
        doThrow(new RuntimeException("Error al eliminar el auto")).when(carRepository).deleteById(id);

        // Verificación de que se lanza una excepción al intentar eliminar el auto
        Assertions.assertThrows(Exception.class, () -> carService.deleteCar(id));
    }
}
