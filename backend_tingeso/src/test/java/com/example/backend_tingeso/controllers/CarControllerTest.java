package com.example.backend_tingeso.controllers;

import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.services.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;


    //funciona
    @Test
    public void listCars_ShouldReturnCars() throws Exception {
        CarEntity car1 = new CarEntity(1L,
                "ABC123",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000);

        CarEntity car2 = new CarEntity(2L,
                "ZXC457",
                "Chevrolet",
                "Day",
                "Sedan",
                2015,
                "Diesel",
                4,
                1500);

        List<CarEntity> carList = new ArrayList<>(Arrays.asList(car1, car2));

        given(carService.getCar()).willReturn((ArrayList<CarEntity>) carList);

        mockMvc.perform(get("/api/v1/car/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].patent", is("ABC123")))
                .andExpect(jsonPath("$[1].patent", is("ZXC457")));
    }

    //funciona
    @Test
    public void getCarByPatent_ShouldReturnEmployee() throws Exception {
        CarEntity car = new CarEntity(1L,
                "TRU567",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000);

        given(carService.getCarByPatent("TRU567")).willReturn(car);

        mockMvc.perform(get("/api/v1/car/patent/{patent}", "TRU567"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.patent", is("TRU567")));
    }

    @Test
    public void saveCar_ShouldReturnSavedCar() throws Exception {
        //EmployeeEntity employeeToSave = new EmployeeEntity(null, "12345678-9", "New Employee", 40000, 0, "B");
        CarEntity savedCar = new CarEntity(1L,
                "WER432",
                "Suzuki",
                "Swift",
                "Sedan",
                2010,
                "Gasolina",
                4,
                2000);

        given(carService.saveCar(Mockito.any(CarEntity.class))).willReturn(savedCar);

        String carJson = """
            {
                "patent": "WER432",
                "brand": "Suzuki",
                "model": "Swift",
                "type": "Sedan",
                "productionYear": 2010,
                "motorType": "Gasolina",
                "numberSeats": 4,
                "kilometers": 2000
            }
            """;

        mockMvc.perform(post("/api/v1/car/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patent", is("WER432")));
    }

    //funciona
    @Test
    public void updateCar_ShouldReturnUpdatedEmployee() throws Exception {
        CarEntity updateCar = new CarEntity(1L,
                "JYU432",
                "Chevrolet",
                "Swift",
                "Sedan",
                2017,
                "Gasolina",
                4,
                2100);

        given(carService.updateCar(Mockito.any(CarEntity.class))).willReturn(updateCar);

        String carJson = """
            {
                "patent": "JYU432",
                "brand": "Chevrolet",
                "model": "Swift",
                "type": "Sedan",
                "productionYear": 2017,
                "motorType": "Gasolina",
                "numberSeats": 4,
                "kilometers": 2100
            }
            """;


        mockMvc.perform(put("/api/v1/car/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patent", is("JYU432")));
    }

    @Test
    public void deleteCarById_ShouldReturn204() throws Exception {
        when(carService.deleteCar(1L)).thenReturn(true); // Assuming the method returns a boolean

        mockMvc.perform(delete("/api/v1/car/delete/{id}", 1L))
                .andExpect(status().isNoContent());
    }

}

