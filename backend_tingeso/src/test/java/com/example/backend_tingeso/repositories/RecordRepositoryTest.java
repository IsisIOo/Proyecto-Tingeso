package com.example.backend_tingeso.repositories;

import com.example.backend_tingeso.entities.RecordEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
public class RecordRepositoryTest {


    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    CarRepository carRepository;

    //buscando solo 1
    @Test
    public void whenfindByPatentOne_thenReturnRecord() {
        RecordEntity record = new RecordEntity(null,
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
        recordRepository.save(record);

        RecordEntity found = recordRepository.findByPatentOne(record.getPatent());

        assertThat(found.getPatent()).isEqualTo(record.getPatent());
    }

    //buscando todos
    @Test
    public void whenfindByPatent_thenReturnRecords() {
        RecordEntity record1 = new RecordEntity(null,
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

        RecordEntity record2 = new RecordEntity(null,
                "ABC123",
                "Miercoles",
                15,
                4,
                14,
                "Reparación del Sistema Eléctrico",
                17,
                4,
                13,
                19,
                4,
                14,
                15000);

        recordRepository.save(record1);
        recordRepository.save(record2);

        List<RecordEntity> foundRecord = recordRepository.findByPatent("ABC123");

        assertThat(foundRecord).hasSize(2).extracting(RecordEntity::getPatent).containsOnly("ABC123");
    }


}
