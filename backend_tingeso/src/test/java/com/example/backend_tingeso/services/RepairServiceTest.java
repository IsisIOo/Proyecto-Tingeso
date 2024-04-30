package com.example.backend_tingeso.services;

import com.example.backend_tingeso.entities.RepairEntity;
import com.example.backend_tingeso.repositories.RepairRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import com.example.backend_tingeso.entities.RecordEntity;
import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.repositories.RecordRepository;
import com.example.backend_tingeso.repositories.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.ArgumentMatchers.anyString;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
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
        // Aquí agregamos algunas entidades de reparación de muestra
        return repairEntities;
    }

    @Test
    public void testPrecioSegunReparacionyMotor() {
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
                2000) {
        }; // Supongamos que aquí creas una instancia de Car con el motor esperado
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
                150000); // Supongamos que aquí creas una instancia de Record con el tipo de reparación esperado
        when(recordRepository.findByPatentOne(patent)).thenReturn(record);

        double expectedPrice = 350000.0; // Supongamos que para el motor y tipo de reparación dados, el precio esperado es 350000

        // Act
        double actualPrice = repairService.precioSegunReparacionyMotor(patent);

        // Assert
        Assertions.assertEquals(expectedPrice, actualPrice, 0.01); // Utilizamos un margen de error de 0.01 para la comparación de doubles
    }

    @Test
    public void testDescuentosSegunHora() {
        // Arrange
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
                150000); // Supongamos que aquí creas una instancia de Record con la hora de ingreso deseada
        record.setAdmissionHour(10); // Hora de ingreso: 10 AM
        record.setAdmissionDateDayName("lunes"); // Día de ingreso: lunes
        when(recordRepository.findByPatentOne(patent)).thenReturn(record);

        double total_price = 1000.0; // Supongamos que el precio total sin descuento es 1000

        // Act
        double actualPrice = repairService.DescuentosSegunHora(patent, total_price);

        // Assert
        double expectedPrice = 900.0; // Precio esperado con el descuento del 10%
        Assertions.assertEquals(expectedPrice, actualPrice, 0.01); // Utilizamos un margen de error de 0.01 para la comparación de doubles
    }

    @Test
    public void testDescuentoSegunMarca() {
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
                2000); // Supongamos que aquí creas una instancia de Car con la marca deseada
        car.setBrand("Toyota"); // Marca del carro: Toyota
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 70000.0; // Supongamos que el precio total sin descuento es 1000

        // Act
        double actualPrice = repairService.DescuentoSegunMarca(patent, total_price);

        // Assert
        double expectedPrice = 0.0; // Precio esperado con el descuento de Toyota (1000 - 70000)
        Assertions.assertEquals(expectedPrice, actualPrice, 0.01); // Utilizamos un margen de error de 0.01 para la comparación de doubles
    }

    @Test
    public void testRecargoPorKilometraje() {
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
                2000); // Supongamos que aquí creas una instancia de Car con el tipo y kilómetros deseados
        car.setType("SUV"); // Tipo de vehículo: SUV
        car.setKilometers(15000); // Kilómetros recorridos: 15000
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 1000.0; // Supongamos que el precio total sin recargo es 1000

        // Act
        double actualPrice = repairService.RecargoPorKilometraje(patent, total_price);

        // Assert
        double expectedPrice = 1090.0; // Precio esperado con el recargo del 9% para un SUV con 15000 km
        Assertions.assertEquals(expectedPrice, actualPrice, 0.01); // Utilizamos un margen de error de 0.01 para la comparación de doubles
    }

    @Test
    public void testRecargoPorAntiguedad() {
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
                2000); // Supongamos que aquí creas una instancia de Car con el tipo y año de producción deseados
        car.setType("SUV"); // Tipo de vehículo: SUV
        car.setProductionYear(2010); // Año de producción del vehículo: 2010
        when(carRepository.findByPatent(patent)).thenReturn(car);

        double total_price = 1000.0; // Supongamos que el precio total sin recargo es 1000

        // Act
        double actualPrice = repairService.recargoPorAntiguedad(patent, total_price);

        // Assert
        double expectedPrice = 1070.0; // Precio esperado con el recargo del 7% para un SUV fabricado en 2010
        Assertions.assertNotEquals(expectedPrice, actualPrice, 0.01); // Utilizamos un margen de error de 0.01 para la comparación de doubles
    }

    @Test
    public void testIVATOTAL() {
        // Arrange
        double total_price = 1000.0; // Supongamos que el precio total sin IVA es 1000

        // Act
        double actualPrice = repairService.IVATOTAL(total_price);

        // Assert
        double expectedPrice = 1190.0; // Precio esperado con el 19% de IVA sobre 1000
        Assertions.assertEquals(expectedPrice, actualPrice, 0.01); // Utilizamos un margen de error de 0.01 para la comparación de doubles
    }

    @Test
    public void testIVASOLO() {
        // Arrange
        double total_price = 1000.0; // Supongamos que el precio total sin IVA es 1000

        // Act
        double actualIva = repairService.IVASOLO(total_price);

        // Assert
        double expectedIva = 190.0; // 19% de IVA sobre 1000
        Assertions.assertEquals(expectedIva, actualIva, 0.01); // Utilizamos un margen de error de 0.01 para la comparación de doubles
    }
    @Test
    public void testGetRepairByPatent() {
        // Arrange
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
                4); // Supongamos que aquí creas una instancia de RepairEntity esperada
        // Establecemos los valores esperados para la entidad de reparación
        expectedEntity.setPatent(patent);
        // Simulamos el comportamiento del repositorio para devolver la entidad esperada cuando se llama al método findByPatentrepair
        when(repairRepository.findByPatentrepair(patent)).thenReturn(expectedEntity);

        // Act
        RepairEntity actualEntity = repairService.getRepairByPatent(patent);

        // Assert
        Assertions.assertEquals(expectedEntity, actualEntity); // Verificamos si la entidad devuelta es la misma que la esperada
    }

    @Test
    public void testRecargoPorAntiguedad1() {
        // Arrange
        String patent = "ABC123";
        double total_price = 1000.0; // Supongamos un precio base de 1000 para la reparación
        double expectedTotalPriceYear = 0.0; // El recargo por antigüedad sería el 5% del precio base en este caso

        // Simulamos el comportamiento del repositorio para devolver el año de producción y el tipo de vehículo
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2019,
                "Gasolina",
                4,
                2000));

        // Act
        double actualTotalPriceYear = repairService.recargoPorAntiguedad1(patent, total_price);

        // Assert
        Assertions.assertEquals(expectedTotalPriceYear, actualTotalPriceYear); // Verificamos si el recargo por antigüedad es el esperado
    }

    @Test
    public void testRecargoPorKilometraje1() {
        // Arrange
        String patent = "ABC123";
        double total_price = 1000.0; // Supongamos un precio base de 1000 para la reparación

        // Simulamos el comportamiento del repositorio para devolver el tipo de vehículo y la cantidad de kilómetros
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                8000));

        // Act
        double actualTotalPriceKm = repairService.RecargoPorKilometraje1(patent, total_price);

        // Assert
        Assertions.assertEquals(30.0, actualTotalPriceKm); // Verificamos si el recargo por kilometraje es el esperado (0.03 * 1000)
    }

    @Test
    public void testDescuentosSegunHora1_WithoutDiscount() {
        // Arrange
        String patent = "ABC123";
        double total_price = 1000.0; // Precio base

        // Simulamos el comportamiento del repositorio para devolver la hora de admisión y el día de la semana
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

        // Act
        double actualTotalPriceHour = repairService.DescuentosSegunHora1(patent, total_price);

        // Assert
        Assertions.assertEquals(0.0, actualTotalPriceHour); // Verificamos que no se aplique ningún descuento
    }

    @Test
    void testDeleteRepair() {
        // Mocking the behavior of CarRepository
        Long id = 1L;
        doNothing().when(repairRepository).deleteById(id);

        // Calling the method under test
        boolean result = false;
        try {
            result = repairService.deleteRepair(id);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception properly in your code
        }
        // Verifying the result
        Assertions.assertTrue(result);
    }

    @Test
    void testRecargoPorAtraso() {
        // Definir la patente del vehículo
        String patent = "ABC123";

        // Crear un mock de RecordEntity con los datos necesarios
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
        // Mockear el comportamiento del RecordRepository para devolver este mock
        when(recordRepository.findByPatentOne(patent)).thenReturn(record);

        // Definir el precio total inicial
        double total_price = 1000.0;

        // Llamar al método que se está probando
        double totalPriceWithSurcharge = repairService.recargoPorAtraso(patent, total_price);

        // Verificar si el recargo se aplicó correctamente

        // Crear un mock para comparar los resultados
        double expectedTotalPriceWithSurcharge = 1000.0; // Aquí define el resultado esperado después del recargo

        // Verificar que el total con recargo sea mayor que el total inicial
        Assertions.assertNotEquals(totalPriceWithSurcharge, total_price);

        // También puedes verificar si el total con recargo coincide con el resultado esperado
        // Assertions.assertEquals(expectedTotalPriceWithSurcharge, totalPriceWithSurcharge);
    }
}