package com.example.backend_tingeso.repositories;
import com.example.backend_tingeso.entities.RecordEntity;
import com.example.backend_tingeso.entities.RepairEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@ActiveProfiles("test")
public class RepairRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RepairRepository repairRepository;


    //buscando solo 1
    @Test
    public void whenfindByPatentrepair_thenReturnRepair() {
        // given
        RepairEntity repair = new RepairEntity(null,
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
        entityManager.persistAndFlush(repair);

        // when
        RepairEntity found = repairRepository.findByPatentrepair(repair.getPatent());

        // then
        assertThat(found.getPatent()).isEqualTo(repair.getPatent());
    }

    //buscando todos
    @Test
    public void whenfindByPatentrepairfinal_thenReturnRepairs() {
        // given
        RepairEntity repair1 = new RepairEntity(null,
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

        RepairEntity repair2 = new RepairEntity(null,
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

        entityManager.persist(repair1);
        entityManager.persist(repair2);
        entityManager.flush();

        // when
        List<RepairEntity> foundRepair = repairRepository.findByPatentrepairfinal("DEF456");

        // then
        assertThat(foundRepair).hasSize(2).extracting(RepairEntity::getPatent).containsOnly("DEF456");
    }


}
