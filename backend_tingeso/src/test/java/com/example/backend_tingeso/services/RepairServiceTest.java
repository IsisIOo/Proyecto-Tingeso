package com.example.backend_tingeso.services;

import com.example.backend_tingeso.entities.RepairEntity;
import com.example.backend_tingeso.repositories.RepairRepository;
import org.junit.jupiter.api.Test;
import com.example.backend_tingeso.entities.RecordEntity;
import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.repositories.RecordRepository;
import com.example.backend_tingeso.repositories.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

public class RepairServiceTest {
    @Mock
    private CarRepository carRepository;

    @Mock
    private RecordRepository recordRepository;

    @Mock
    private RepairRepository repairRepository;

    @InjectMocks
    private RepairService repairService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    // Método de utilidad para obtener una lista de muestras de entidades de reparación
    private List<RepairEntity> getSampleRepairEntities() {
        List<RepairEntity> repairEntities = new ArrayList<>();
        return repairEntities;
    }

    @Test
    public void testPrecioSegunReparacionyMotor() {
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000) {
        };
        when(carRepository.findByPatent(patent)).thenReturn(car);

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "Reparaciones del motor",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);
        when(recordRepository.findByPatentOne(patent)).thenReturn(record);

        double expectedPrice = 350000.0;

        double actualPrice = repairService.precioSegunReparacionyMotor(patent);

        Assertions.assertEquals(expectedPrice, actualPrice, 0.01);
    }

    @Test
    public void testDescuentosSegunHora() {
        String patent = "ABC123";
        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "Reparaciones del motor",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);
        record.setAdmissionHour(10);
        record.setAdmissionDateDayName("lunes");
        when(recordRepository.findByPatentOne(patent)).thenReturn(record);

        double total_price = 1000.0;

        double actualPrice = repairService.DescuentosSegunHora(patent, total_price);

        double expectedPrice = 900.0;
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testDescuentosSegunHora2() {
        String patent = "ABC123";
        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Lunes",
                15,
                4,
                10,
                "Reparaciones del motor",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);
        record.setAdmissionHour(10);
        record.setAdmissionDateDayName("lunes");
        when(recordRepository.findByPatentOne(patent)).thenReturn(record);

        double total_price = 1000.0;

        double actualPrice = repairService.DescuentosSegunHora(patent, total_price);

        double expectedPrice = 900.0;
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testDescuentoSegunMarca() {
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000);
        car.setBrand("Toyota");
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 70000.0;

        double actualPrice = repairService.DescuentoSegunMarca(patent, total_price);

        double expectedPrice = 0.0;
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testDescuentoSegunMarca2() {
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "ford",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000);
        car.setBrand("Ford");
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 70000.0;

        double actualPrice = repairService.DescuentoSegunMarca(patent, total_price);

        double expectedPrice = 0.0;
        Assertions.assertNotEquals(expectedPrice, actualPrice, 0.01);
    }

    @Test
    public void testDescuentoSegunMarca3() {
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "hyundai",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000);
        car.setBrand("hyundai");
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 70000.0;

        double actualPrice = repairService.DescuentoSegunMarca(patent, total_price);

        double expectedPrice = 0.0;
        Assertions.assertNotEquals(expectedPrice, actualPrice);  }

    @Test
    public void testDescuentoSegunMarca4() {
        // Arrange
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "honda",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000); // Supongamos que aquí creas una instancia de Car con la marca deseada
        car.setBrand("honda"); // Marca del carro: Toyota
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 70000.0;

        double actualPrice = repairService.DescuentoSegunMarca(patent, total_price);

        double expectedPrice = 0.0; // Precio esperado con el descuento de Toyota (1000 - 70000)
        Assertions.assertNotEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testRecargoPorKilometrajeSEDAN1() {
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000);
        car.setType("SUV");
        car.setKilometers(15000);
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 1000.0;

        double actualPrice = repairService.RecargoPorKilometraje(patent, total_price);

        double expectedPrice = 1090.0;
        Assertions.assertEquals(expectedPrice, actualPrice, 0.01);
    }

    @Test
    public void testRecargoPorKilometrajeSEDAN2() {
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                6000);
        car.setType("SUV");
        car.setKilometers(15000);
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 1000.0;

        double actualPrice = repairService.RecargoPorKilometraje(patent, total_price);

        double expectedPrice = 1090.0;
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testRecargoPorKilometrajeSEDAN3() {
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                13000);
        car.setType("SUV");
        car.setKilometers(15000);
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 1000.0;

        double actualPrice = repairService.RecargoPorKilometraje(patent, total_price);

        double expectedPrice = 1090.0;
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testRecargoPorKilometrajeSEDAN4() {
        // Arrange
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                27000);
        car.setType("SUV");
        car.setKilometers(15000);
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 1000.0;

        double actualPrice = repairService.RecargoPorKilometraje(patent, total_price);

        double expectedPrice = 1090.0;
        Assertions.assertEquals(expectedPrice, actualPrice); }

    @Test
    public void testRecargoPorKilometrajeSEDAN5() {
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                60000);
        car.setType("SUV");
        car.setKilometers(15000);
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 1000.0;

        double actualPrice = repairService.RecargoPorKilometraje(patent, total_price);

        double expectedPrice = 1090.0;
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testRecargoPorKilometrajehatchback1() {
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "hatchback",
                2010,
                "Gasolina",
                4,
                2000);
        car.setType("SUV");
        car.setKilometers(15000);
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 1000.0;

        double actualPrice = repairService.RecargoPorKilometraje(patent, total_price);

        double expectedPrice = 1090.0; // Precio esperado con el recargo del 9% para un SUV con 15000 km
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testRecargoPorKilometrajehatchback2() {
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "hatchback",
                2010,
                "Gasolina",
                4,
                6000);
        car.setType("SUV");
        car.setKilometers(15000);
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 1000.0;
        double actualPrice = repairService.RecargoPorKilometraje(patent, total_price);

        double expectedPrice = 1090.0;
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testRecargoPorKilometrajehatchback3() {

        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "hatchback",
                2010,
                "Gasolina",
                4,
                13000);
        car.setType("SUV");
        car.setKilometers(15000);
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 1000.0;

        double actualPrice = repairService.RecargoPorKilometraje(patent, total_price);

        double expectedPrice = 1090.0;
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testRecargoPorKilometrajehatchback4() {
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "hatchback",
                2010,
                "Gasolina",
                4,
                27000);
        car.setType("SUV");
        car.setKilometers(15000);
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 1000.0;


        double actualPrice = repairService.RecargoPorKilometraje(patent, total_price);

        double expectedPrice = 1090.0;
        Assertions.assertEquals(expectedPrice, actualPrice, 0.01);
    }

    @Test
    public void testRecargoPorKilometrajehatchback5() {
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "hatchback",
                2010,
                "Gasolina",
                4,
                60000);
        car.setType("SUV");
        car.setKilometers(15000);
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 1000.0;

        double actualPrice = repairService.RecargoPorKilometraje(patent, total_price);

        double expectedPrice = 1090.0; // Precio esperado con el recargo del 9% para un SUV con 15000 km
        Assertions.assertEquals(expectedPrice, actualPrice); // Utilizamos un margen de error de 0.01 para la comparación de doubles
    }


    @Test
    public void testRecargoPorAntiguedad() {
        String patent = "ABC123";
        CarEntity car = new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000);
        car.setType("SUV");
        car.setProductionYear(2010);
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 1000.0;

        double actualPrice = repairService.recargoPorAntiguedad(patent, total_price);

        double expectedPrice = 1070.0;
        Assertions.assertNotEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testIVATOTAL() {
        double total_price = 1000.0;

        double actualPrice = repairService.IVATOTAL(total_price);

        double expectedPrice = 1190.0;
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testIVASOLO() {
        double total_price = 1000.0;

        double actualIva = repairService.IVASOLO(total_price);

        double expectedIva = 190.0;
        Assertions.assertEquals(expectedIva, actualIva, 0.01);
    }
    @Test
    public void testGetRepairByPatent() {
        String patent = "ABC123";
        RepairEntity expectedEntity = new RepairEntity(1L,
                "DEF456",
                120000,
                1200,
                1200,
                3111,
                1,
                231,
                1,
                1,
                4);
        expectedEntity.setPatent(patent);
        when(repairRepository.findByPatentrepair(patent)).thenReturn(expectedEntity);

        RepairEntity actualEntity = repairService.getRepairByPatent(patent);

        Assertions.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void testRecargoPorAntiguedad1() {
        String patent = "ABC123";
        double total_price = 1000.0;
        double expectedTotalPriceYear = 0.0;

        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2019,
                "Gasolina",
                4,
                2000));

        double actualTotalPriceYear = repairService.recargoPorAntiguedad1(patent, total_price);

        Assertions.assertEquals(expectedTotalPriceYear, actualTotalPriceYear);
    }

    @Test
    public void testRecargoPorKilometraje1() {
        String patent = "ABC123";
        double total_price = 1000.0;

        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                8000));

        double actualTotalPriceKm = repairService.RecargoPorKilometraje1(patent, total_price);

        Assertions.assertEquals(30.0, actualTotalPriceKm);
    }

    @Test
    public void testDescuentosSegunHora1_WithoutDiscount() {
        String patent = "ABC123";
        double total_price = 1000.0; // Precio base

        when(recordRepository.findByPatentOne(patent)).thenReturn(new RecordEntity(2L,
                "ABC123",
                "viernes",
                15,
                4,
                14,
                "Reparaciones del Sistema de Frenos",
                17,
                4,
                12,
                18,
                4,
                12,
                150000));

        double actualTotalPriceHour = repairService.DescuentosSegunHora1(patent, total_price);

        Assertions.assertEquals(0.0, actualTotalPriceHour);
    }

    @Test
    void testDeleteRepair() {
        Long id = 1L;
        doNothing().when(repairRepository).deleteById(id);

        boolean result = false;
        try {
            result = repairService.deleteRepair(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(result);
    }

    @Test
    void testRecargoPorAtraso() {
        String patent = "ABC123";

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "Reparaciones del Sistema de Frenos",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);
        when(recordRepository.findByPatentOne(patent)).thenReturn(record);

        double total_price = 1000.0;

        double totalPriceWithSurcharge = repairService.recargoPorAtraso(patent, total_price);


        double expectedTotalPriceWithSurcharge = 1000.0;

        Assertions.assertNotEquals(totalPriceWithSurcharge, total_price);

    }
}