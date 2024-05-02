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
        List<RecordEntity> records = new ArrayList<>();
        records.add(new RecordEntity(null,
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
                150000));
        records.add(new RecordEntity(null,
                "DEF456",
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
                150000));
        when(recordRepository.findAll()).thenReturn(records);


        List<RecordEntity> result = recordService.getRecordRepository();


        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testGetOneRecordRepository() {

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


        RecordEntity result = recordService.getOneRecordRespository(patent);

        Assertions.assertEquals(patent, result.getPatent());
    }

    @Test
    void testSaveRecord() {
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

        when(recordRepository.save(any(RecordEntity.class))).thenReturn(record);

        RecordEntity savedRecord = recordService.saveRecord(record);

        Assertions.assertEquals(record, savedRecord);
    }

    @Test
    void testGetRecordsByPatent() {
        String patent = "ABC123";
        List<RecordEntity> records = new ArrayList<>();
        records.add(new RecordEntity(null,
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
                150000));
        records.add(new RecordEntity(null,
                "ABC123 ",
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
                150000));
        when(recordRepository.findByPatent(patent)).thenReturn(records);


        List<RecordEntity> result = recordService.getRecordsByPatent(patent);


        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testDeleteRecord() {
        Long id = 1L;
        doNothing().when(recordRepository).deleteById(id);

        boolean result = false;
        try {
            result = recordService.deleteRecord(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(result);
    }

    @Test
    void testPrecioSegunReparacionyMotorGASOLIN1() {
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

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorGASOLINA2() {
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

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de frenos",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorGASOLINA3() {
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

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "servicio del sistema de refrigeración",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorGASOLINA4() {
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

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones de la transmisión",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorGASOLINA5() {
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

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparación del sistema eléctrico",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorGASOLINA6() {
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

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de escape",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorGASOLINA7() {
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

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparación de neumáticos y ruedas",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorGASOLINA8() {
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

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones de la suspensión y la dirección",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorGASOLINA9() {
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

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones de la suspensión y la dirección",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorGASOLINA10() {
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

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de combustible",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorGASOLINA11() {
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

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de combustible",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotordiesel1() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "diesel",
                4,
                2000));

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

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotordiesel2() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "diesel",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de frenos",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotordiesel3() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "diesel",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "servicio del sistema de refrigeración",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotordiesel4() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "diesel",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones de la transmisión",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotordiesel5() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "diesel",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparación del sistema eléctrico",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotordiesel6() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "diesel",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de escape",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotordiesel7() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "diesel",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparación de neumáticos y ruedas",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotordiesel8() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "diesel",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones de la suspensión y la dirección",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotordiesel9() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "diesel",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones de la suspensión y la dirección",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotordiesel10() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "diesel",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de combustible",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotordiesel11() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "diesel",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de combustible",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorhíbrido1() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "híbrido",
                4,
                2000));

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

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorhíbrido2() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "híbrido",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de frenos",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorhíbrido3() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "híbrido",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "servicio del sistema de refrigeración",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorhíbrido4() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "híbrido",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones de la transmisión",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorhíbrido5() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "híbrido",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparación del sistema eléctrico",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorhíbrido6() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "híbrido",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de escape",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorhíbrido7() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "híbrido",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparación de neumáticos y ruedas",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorhíbrido8() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "híbrido",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones de la suspensión y la dirección",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorhíbrido9() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "híbrido",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones de la suspensión y la dirección",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorhíbrido10() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "híbrido",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de combustible",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotorhíbrido11() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "híbrido",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de combustible",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }
    @Test
    void testPrecioSegunReparacionyMotoreléctrico1() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "eléctrico",
                4,
                2000));

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

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotoreléctrico2() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "eléctrico",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de frenos",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotoreléctrico3() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "eléctrico",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "servicio del sistema de refrigeración",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotoreléctrico4() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "eléctrico",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones de la transmisión",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotoreléctrico5() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "eléctrico",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparación del sistema eléctrico",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotoreléctrico6() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "eléctrico",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de escape",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotoreléctrico7() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "eléctrico",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparación de neumáticos y ruedas",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotoreléctrico8() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "eléctrico",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones de la suspensión y la dirección",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotoreléctrico9() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "eléctrico",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones de la suspensión y la dirección",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotoreléctrico10() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "eléctrico",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de combustible",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testPrecioSegunReparacionyMotoreléctrico11() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "eléctrico",
                4,
                2000));

        RecordEntity record = new RecordEntity(2L,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "reparaciones del sistema de combustible",
                17,
                4,
                12,
                18,
                4,
                12,
                150000);

        // recibiendo como entrada el record del vehiculo encontrado se busca el tipo de reparacion segun su
        // tipo de motor y el tipo de reparacion que tiene ingresado, retorna costo total
        double totalPrice = recordService.precioSegunReparacionyMotor(record);

        Assertions.assertNotEquals(350000, totalPrice);
    }

    @Test
    void testDescuentosSegunHora() {
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
        double totalPrice = 1000.0;

        double totalPriceWithDiscount = recordService.DescuentosSegunHora(record, totalPrice);

        Assertions.assertEquals(1000.0, totalPriceWithDiscount);
    }

    @Test
    void testDescuentosSegunHoraSI() {
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
        double totalPrice = 1000.0;

        double totalPriceWithDiscount = recordService.DescuentosSegunHora(record, totalPrice);

        Assertions.assertNotEquals(1000.0, totalPriceWithDiscount);
    }

    @Test
    void testDescuentosSegunHora2() {
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
        double totalPrice = 1000.0;
        record.setAdmissionHour(10);

        double totalPriceWithDiscount = recordService.DescuentosSegunHora(record, totalPrice);

        double expectedTotalPriceWithDiscount = totalPrice * 0.9;
        Assertions.assertTrue(totalPriceWithDiscount > expectedTotalPriceWithDiscount);
    }

    @Test
    void testDescuentoSegunMarca() {
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
        double totalPrice = 1000.0;

        double totalPriceWithDiscount = recordService.DescuentoSegunMarca(patent, totalPrice);

        Assertions.assertEquals(1000.0, totalPriceWithDiscount);
    }

    @Test
    void testDescuentoSegunMarca2() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Toyota",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithDiscount = recordService.DescuentoSegunMarca(patent, totalPrice);

        double expectedTotalPriceWithDiscount = totalPrice * 0.9;
        Assertions.assertEquals(expectedTotalPriceWithDiscount, totalPriceWithDiscount);
    }

    @Test
    void testDescuentoSegunMarca3() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Honda",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithDiscount = recordService.DescuentoSegunMarca(patent, totalPrice);

        Assertions.assertNotEquals(1000.0, totalPriceWithDiscount);
    }

    @Test
    void testDescuentoSegunMarca5() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Ford",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithDiscount = recordService.DescuentoSegunMarca(patent, totalPrice);

        Assertions.assertNotEquals(1000.0, totalPriceWithDiscount);
    }

    @Test
    void testDescuentoSegunMarca4() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Hyundai",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithDiscount = recordService.DescuentoSegunMarca(patent, totalPrice);

        Assertions.assertNotEquals(1000.0, totalPriceWithDiscount);
    }

    @Test
    void testRecargoPorKilometrajeSEDAN1() {
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
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajeSEDAN2() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                5002));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajeSEDAN3() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                12005));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajeSEDAN4() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                25005));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajeSEDAN5() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                50000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajehatchback1() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "hatchback",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajehatchback2() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "hatchback",
                2010,
                "Gasolina",
                4,
                6000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajehatchback3() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "hatchback",
                2010,
                "Gasolina",
                4,
                13000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajehatchback4() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "hatchback",
                2010,
                "Gasolina",
                4,
                26000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajehatchback5() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "hatchback",
                2010,
                "Gasolina",
                4,
                50000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajesuv1() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "suv",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajesuv2() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "suv",
                2010,
                "Gasolina",
                4,
                6000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajesuv3() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "suv",
                2010,
                "Gasolina",
                4,
                12500));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajesuv4() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "suv",
                2010,
                "Gasolina",
                4,
                26000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajesuv5() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "suv",
                2010,
                "Gasolina",
                4,
                60000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajepickup1() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "pickup",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertEquals(totalPrice, totalPriceWithSurcharge);
    }
    @Test
    void testRecargoPorKilometrajepickup2() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "pickup",
                2010,
                "Gasolina",
                4,
                6000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajepickup3() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "pickup",
                2010,
                "Gasolina",
                4,
                13000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajepickup4() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "pickup",
                2010,
                "Gasolina",
                4,
                26000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajepickup5() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "pickup",
                2010,
                "Gasolina",
                4,
                60000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajefurgoneta1() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "furgoneta",
                2010,
                "Gasolina",
                4,
                3000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajefurgoneta2() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "furgoneta",
                2010,
                "Gasolina",
                4,
                6000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajefurgoneta3() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "furgoneta",
                2010,
                "Gasolina",
                4,
                13000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajefurgoneta4() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "furgoneta",
                2010,
                "Gasolina",
                4,
                26000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }

    @Test
    void testRecargoPorKilometrajefurgoneta5() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "furgoneta",
                2010,
                "Gasolina",
                4,
                70000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.RecargoPorKilometraje(patent, totalPrice);
        Assertions.assertNotEquals(totalPrice, totalPriceWithSurcharge);
    }
    @Test
    void testRecargoPorAntiguedadSinRecargo() {
        String patent = "ABC123";
        int productionYear = 2020;
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
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertTrue(totalPriceWithSurcharge > totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadSEDAN1() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2024,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadSEDAN2() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2014,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadSEDAN3() {
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
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadSEDAN4() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                1900,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadhatchback1() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "hatchback",
                2024,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadhatchback2() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "hatchback",
                2014,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadhatchback3() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "hatchback",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadHATCHBACK4() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "hatchback",
                1900,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadsuv1() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "suv",
                2024,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadsuv2() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "suv",
                2014,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadsuv3() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "suv",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadsuv4() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "suv",
                1900,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadpickup1() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "pickup",
                2024,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadpickup2() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "pickup",
                2014,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadpickup3() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "pickup",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadpickup4() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "pickup",
                1900,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadfurgoneta1() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "furgoneta",
                2024,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadfurgoneta2() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "furgoneta",
                2014,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadfurgoneta3() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "furgoneta",
                2010,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
    }

    @Test
    void testRecargoPorAntiguedadfurgoneta4() {
        String patent = "ABC123";
        when(carRepository.findByPatent(patent)).thenReturn(new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "furgoneta",
                1900,
                "Gasolina",
                4,
                2000));
        double totalPrice = 1000.0;

        double totalPriceWithSurcharge = recordService.recargoPorAntiguedad(patent, totalPrice);

        Assertions.assertNotEquals(totalPriceWithSurcharge, totalPrice);
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

        double iva = recordService.IVASOLO(totalPrice);

        // Calcular el IVA esperado
        double expectedIVA = totalPrice * 0.19;

        // Verificar que el IVA calculado sea igual al IVA esperado
        Assertions.assertEquals(expectedIVA, iva);
    }

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

        double expectedTotalPrice = 151268.04;

        double totalPrice = recordService.getCostbyRepair(rec);

        Assertions.assertTrue(totalPrice > expectedTotalPrice);
    }
}
