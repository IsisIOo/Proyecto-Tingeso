package com.example.backend_tingeso.services;

import com.example.backend_tingeso.entities.RecordEntity;
import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.repositories.RecordRepository;
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
public class RecordServiceTest {

    @Mock
    private RecordRepository recordRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private RecordService recordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRecordRepository() {
        // Mocking the behavior of RecordRepository
        List<RecordEntity> records = new ArrayList<>();
        records.add(new RecordEntity(/* add necessary parameters here */));
        records.add(new RecordEntity(/* add necessary parameters here */));
        when(recordRepository.findAll()).thenReturn(records);

        // Calling the method under test
        List<RecordEntity> result = recordService.getRecordRepository();

        // Verifying the result
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testGetOneRecordRepository() {
        // Mocking the behavior of RecordRepository
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
        when(recordRepository.findByPatentOne(patent)).thenReturn(record);

        // Calling the method under test
        RecordEntity result = recordService.getOneRecordRespository(patent);

        // Verifying the result
        Assertions.assertEquals(patent, result.getPatent());
    }

    @Test
    void testSaveRecord() {
        // Creating a mock RecordEntity
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

        // Mocking the behavior of RecordRepository
        when(recordRepository.save(any(RecordEntity.class))).thenReturn(record);

        // Calling the method under test
        RecordEntity savedRecord = recordService.saveRecord(record);

        // Verifying the result
        Assertions.assertEquals(record, savedRecord);
    }

    @Test
    void testGetRecordsByPatent() {
        // Mocking the behavior of RecordRepository
        String patent = "ABC123";
        List<RecordEntity> records = new ArrayList<>();
        records.add(new RecordEntity(/* add necessary parameters here */));
        records.add(new RecordEntity(/* add necessary parameters here */));
        when(recordRepository.findByPatent(patent)).thenReturn(records);

        // Calling the method under test
        List<RecordEntity> result = recordService.getRecordsByPatent(patent);

        // Verifying the result
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testDeleteRecord() {
        // Mocking the behavior of RecordRepository
        Long id = 1L;
        doNothing().when(recordRepository).deleteById(id);

        // Calling the method under test
        boolean result = false;
        try {
            result = recordService.deleteRecord(id);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception properly in your code
        }

        // Verifying the result
        Assertions.assertTrue(result);
    }

    @Test
    void testPrecioSegunReparacionyMotor() {
        // Mocking the behavior of CarRepository
        String patent = "ABC123";
        String motor = "gasolina";
        //encuentra el auto segun patente
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000));

        // recibe el record del auto
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

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparasion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        // Verifying the result
        Assertions.assertEquals(350000, totalPrice); // Assuming repair type contains "reparaciones del motor" and motor type is "gasolina"
    }

    @Test
    void testDescuentosSegunHora() {
        // Creating a mock RecordEntity
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
        double totalPrice = 1000.0; // Assuming total price is 1000

        // Calling the method under test
        double totalPriceWithDiscount = recordService.DescuentosSegunHora(record, totalPrice);

        // Verifying the result
        Assertions.assertEquals(1000.0, totalPriceWithDiscount); // Assuming no discount applied
    }

    @Test
    void testDescuentosSegunHora2() {
        // Creating a mock RecordEntity
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
        double totalPrice = 1000.0; // Assuming total price is 1000
        record.setAdmissionHour(10); // Set admission hour to 10 to apply discount

        // Calling the method under test
        double totalPriceWithDiscount = recordService.DescuentosSegunHora(record, totalPrice);

        // Verifying the result
        double expectedTotalPriceWithDiscount = totalPrice * 0.9; // Applying 10% discount
        Assertions.assertTrue(totalPriceWithDiscount > expectedTotalPriceWithDiscount);
    }

    @Test
    void testDescuentoSegunMarca() {
        // Mocking the behavior of CarRepository
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0; // Assuming total price is 1000

        // Calling the method under test
        double totalPriceWithDiscount = recordService.DescuentoSegunMarca(patent, totalPrice);

        // Verifying the result
        Assertions.assertEquals(1000.0, totalPriceWithDiscount); // Assuming no discount applied
    }

    @Test
    void testDescuentoSegunMarca2() {
        // Mocking the behavior of CarRepository
        String patent = "ABC123";
        String brand = "toyota";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Toyota",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0; // Assuming total price is 1000

        // Calling the method under test
        double totalPriceWithDiscount = recordService.DescuentoSegunMarca(patent, totalPrice);

        // Verifying the result
        double expectedTotalPriceWithDiscount = totalPrice * 0.9; // Applying 10% discount for Toyota brand
        Assertions.assertEquals(expectedTotalPriceWithDiscount, totalPriceWithDiscount);
    }

    @Test
    void testRecargoPorKilometraje() {
        // Mocking the behavior of CarRepository
        String patent = "ABC123";
        String type = "sedan";
        int kilometers = 4000; // Lower than the threshold for surcharge
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0; // Assuming total price is 1000

        // Calling the method under test
        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);

        // Verifying the result
        // Here you should assert that the totalPriceWithSurcharge is equal to the initial totalPrice since no surcharge is applied
        Assertions.assertEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorAntiguedadSinRecargo() {
        // Mocking the behavior of CarRepository
        String patent = "ABC123";
        int productionYear = 2020; // Assuming the car was produced 4 years ago
        String type = "sedan";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0; // Assuming total price is 1000

        // Calling the method under test
        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        // Verifying the result
        // Here you should assert that the totalPriceWithSurcharge is equal to the initial totalPrice since no surcharge is applied
        Assertions.assertTrue(totalPriceWithSurcharge > totalPrice);
    }
    @Test
    void testIVATOTAL() {
        // Definir el precio total inicial
        double totalPrice = 1000.0;

        // Llamar al método IVATOTAL del RecordService
        double totalPriceWithIVA = recordService.IVATOTAL(totalPrice);

        // Verificar que el precio total con IVA sea mayor que el precio inicial
        Assertions.assertTrue(totalPriceWithIVA > totalPrice);
    }

    @Test
    void testIVASOLO() {
        // Definir el precio total inicial
        double totalPrice = 1000.0;

        // Llamar al método IVASOLO del RecordService
        double iva = recordService.IVASOLO(totalPrice);

        // Calcular el IVA esperado
        double expectedIVA = totalPrice * 0.19;

        // Verificar que el IVA calculado sea igual al IVA esperado
        Assertions.assertEquals(expectedIVA, iva);
    }

    /*
    void testRecargoPorAntiguedad1() {
        // Definir el año de producción del automóvil
        int yearCar = 2015; // Por ejemplo, el año de producción del automóvil es 2015
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

        // Definir el precio total inicial
        double totalPrice = 1000.0;

        // Llamar al método recargoPorAntiguedad1 del RecordService
        double totalPriceYear = recordService.recargoPorAntiguedad1("ABC123", totalPrice);

        // Calcular el recargo por antigüedad esperado
        int currentYear = 2024;
        int yearsOld = currentYear - yearCar;
        double expectedTotalPriceYear = 0.09 * totalPrice; // Por ejemplo, asumiendo que el automóvil tiene 9 años de antigüedad

        // Verificar que el recargo por antigüedad calculado sea igual al esperado
        Assertions.assertEquals(expectedTotalPriceYear, totalPriceYear);
    }*/

    @Test
    public void testRecargoPorAtrasoSinRetraso() {

        // Crear un objeto de tipo RecordEntity simulado sin retraso
        RecordEntity rec = new RecordEntity(2L,
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
        rec.setDepartureDateDay(10); // Fecha de retiro del taller
        rec.setDepartureDateMonth(4); // Mes de retiro del taller
        rec.setClientDateDay(10); // Fecha de retiro del cliente
        rec.setClientDateMonth(4); // Mes de retiro del cliente

        // Definir el precio total de la reparación
        double total_price = 1000.0;

        // Llamar al método que se está probando
        double total_con_atraso = recordService.recargoPorAtraso(rec, total_price);

        // Comprobar si el total con retraso es igual al total sin cambios
        Assertions.assertEquals(total_price, total_con_atraso); // No se espera ningún cambio en el precio
    }

    @Test
    void testGetCostbyRepair() {
        // Mocking the behavior of CarRepository
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Toyota",
                "Corolla",
                "Sedan",
                2010,
                "Gasolina",
                5,
                20000));

        // Mocking the behavior of RecordRepository
        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Lunes",
                5,
                1,
                10,
                "Reparaciones del motor",
                7,
                1,
                12,
                9,
                1,
                12,
                150000);
        when(recordRepository.save(any(RecordEntity.class))).thenReturn(record);

        // Creating a mock RecordEntity
        RecordEntity rec = new RecordEntity(2L,
                "ABC123",
                "Lunes",
                5,
                1,
                10,
                "Reparaciones del motor",
                7,
                1,
                12,
                9,
                1,
                12,
                150000);

        // Defining the expected total cost
        double expectedTotalPrice = 151268.04; // Assuming the total price is 1500

        // Calling the method under test
        double totalPrice = recordService.getCostbyRepair(rec);

        // Verifying the result
        Assertions.assertTrue(totalPrice > expectedTotalPrice);
    }
}
