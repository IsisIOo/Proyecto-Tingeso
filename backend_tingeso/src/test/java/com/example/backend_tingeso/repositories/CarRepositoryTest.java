package com.example.backend_tingeso.repositories;

import com.example.backend_tingeso.entities.CarEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindByPatent_thenReturnCar() {
        CarEntity car = new CarEntity(null,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000);
        carRepository.save(car);

        CarEntity found = carRepository.findByPatent(car.getPatent());

        assertThat(found.getPatent()).isEqualTo(car.getPatent());
    }

}
