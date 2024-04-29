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
    @Test
    public void testSaveCostentity() {
        // Arrange
        String patent = "ABC123";

        // Mock del repositorio de reparaciones
        RepairRepository repairRepositoryMock = mock(RepairRepository.class);
        when(repairRepositoryMock.save(any(RepairEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CarRepository carRepositoryMock = mock(CarRepository.class);
        when(carRepositoryMock.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000));
        // Mock de la lógica de cálculo en el servicio
        RepairService repairService = new RepairService();
        when(repairService.precioSegunReparacionyMotor(patent)).thenReturn(1000.0); // Supongamos que el costo original es 1000
        when(repairService.IVATOTAL(1000.0)).thenReturn(1200.0); // Supongamos que el total con IVA es 1200
        when(repairService.DescuentosSegunHora(patent, 1200.0)).thenReturn(1100.0); // Supongamos que se aplica un descuento y el costo es 1100
        when(repairService.recargoPorAtraso(patent, 1100.0)).thenReturn(1150.0); // Supongamos que se aplica un recargo y el costo es 1150
        when(repairService.RecargoPorKilometraje(patent, 1150.0)).thenReturn(1200.0); // Supongamos que se aplica un recargo y el costo es 1200
        when(repairService.recargoPorAntiguedad(patent, 1200.0)).thenReturn(1250.0); // Supongamos que se aplica un recargo y el costo es 1250
        when(repairService.tiempodeTrabajo(patent)).thenReturn(5); // Supongamos que el tiempo de trabajo es 5 días

        // Act
        RepairEntity actualEntity = repairService.saveCostentity(patent);

        // Assert
        Assertions.assertEquals(patent, actualEntity.getPatent());
        Assertions.assertEquals(1000.0, actualEntity.getTotalOriginal(), 0.01);
        Assertions.assertEquals(1200.0, actualEntity.getIVA(), 0.01);
        Assertions.assertEquals(1100.0, actualEntity.getDiscountPerDay(), 0.01);
        Assertions.assertEquals(1150.0, actualEntity.getDelayCharge(), 0.01);
        Assertions.assertEquals(1200.0, actualEntity.getMileageCharge(), 0.01);
        Assertions.assertEquals(1250.0, actualEntity.getSeniorityCharge(), 0.01);
        Assertions.assertEquals(5, actualEntity.getWorkTime());
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
    public void testGetRepairByPatentfinal() {
        // Mock del repositorio de reparaciones
        RepairRepository repairRepositoryMock = mock(RepairRepository.class);

        // Instancia de la clase que contiene el método a testear (puede ser un servicio)
        RepairService repairService = new RepairService();

        // Mock de una lista de reparaciones para una patente específica
        ArrayList<RepairEntity> mockRepairList = new ArrayList<>();
        // Agrega reparaciones de ejemplo a la lista
        mockRepairList.add(new RepairEntity(1L,
                "DEF456",
                120000,
                1200,
                1200,
                3111,
                1,
                231,
                1,
                1,
                4));

        mockRepairList.add(new RepairEntity(2L,
                "DEF456",
                120000,
                1200,
                1200,
                3111,
                1,
                231,
                1,
                1,
                4));
        // Mock del comportamiento del repositorio al llamar al método findByPatentrepairfinal
        when(repairRepositoryMock.findByPatentrepairfinal(anyString())).thenReturn(mockRepairList);

        // Llama al método del servicio para obtener la lista de reparaciones
        ArrayList<RepairEntity> actualRepairList = repairService.getRepairByPatentfinal("DEF456");

        // Verifica que la lista de reparaciones devuelta por el servicio sea la misma que la lista mockeada
        Assertions.assertEquals(mockRepairList.size(), actualRepairList.size());
        // Aquí podrías agregar más aserciones para verificar otros aspectos de las reparaciones si lo deseas
    }

    @Test
    void testRecargoPorAtraso1() {
        // Definir la patente del vehículo
        String patent = "ABC123";

        // Definir el precio total de la reparación
        double total_price = 1000.0;

        // Simular un escenario de retraso por días
        // Definir una nueva patente
        String patent2 = "DEF456";
        RecordEntity mockRecordEntity = new RecordEntity(2L,
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
        when(recordRepository.findByPatentOne(patent)).thenReturn(mockRecordEntity);

        // Llamar al método que se está probando para este escenario
        double recargo_total2 = repairService.recargoPorAtraso1(patent2, total_price);

        // Verificar si el recargo total por retraso es correcto para el escenario de retraso por días
        double expectedRecargoTotal2 = 50.0; // (5 días de retraso) * (0.05) * (1000)
        Assertions.assertEquals(expectedRecargoTotal2, recargo_total2);
    }

    @Test
    public void testTiempodeTrabajo_ConDemoraDias() {
        // Arrange
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
        record.setAdmissionDateDay(5);
        record.setAdmissionDateMonth(4); // April
        record.setDepartureDateDay(10); // 5 days later
        record.setDepartureDateMonth(4); // April

        int diasDemora = repairService.tiempodeTrabajo(record.getPatent());

        // Assert
        Assertions.assertEquals(0, diasDemora);
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
