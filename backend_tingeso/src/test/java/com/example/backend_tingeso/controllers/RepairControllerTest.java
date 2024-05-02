package com.example.backend_tingeso.controllers;

import com.example.backend_tingeso.entities.RepairEntity;
import com.example.backend_tingeso.services.RepairService;
import org.junit.jupiter.api.Test;
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

@WebMvcTest(RepairController.class)
public class RepairControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepairService repairService;

    @Test
    public void listRepairs_ShouldReturnRepairs() throws Exception {
        RepairEntity repair1 = new RepairEntity(1L,
                "ABC123",
                120000,
                1200,
                1200,
                3111,
                100,
                231,
                100,
                100,
                2);

        RepairEntity repair2 = new RepairEntity(2L,
                "DEF456",
                120000,
                1200,
                1200,
                3111,
                100,
                231,
                100,
                100,
                4);

        List<RepairEntity> repairList = new ArrayList<>(Arrays.asList(repair1, repair2));

        given(repairService.getRepair()).willReturn((ArrayList<RepairEntity>) repairList);

        mockMvc.perform(get("/api/v1/repairs/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].patent", is("ABC123")))
                .andExpect(jsonPath("$[1].patent", is("DEF456")));
    }

    @Test
    public void saveRepair_ShouldReturnSavedRepair() throws Exception {
        RepairEntity savedRepair = new RepairEntity(1L,
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

        given(repairService.saveCostentity("DEF456")).willReturn(savedRepair);

        String repairJson = """
            {
                "patent": "DEF456",
                "totalOriginal": 20000,
                "discountPerDay": 1200,
                "discountPerbonus": 1200,
                "delayCharge": 2010,
                "mileageCharge": 5400,
                "SeniorityCharge": 445,
                "IVA": 2000,
                "totalAmount": 2000,
                "workTime": 2
            }
            """;

        mockMvc.perform(post("/api/v1/repairs/{patent}", "DEF456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(repairJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patent", is("DEF456")));
    }

    @Test
    public void deleteRepairById_ShouldReturn204() throws Exception {
        when(repairService.deleteRepair(1L)).thenReturn(true); // Assuming the method returns a boolean

        mockMvc.perform(delete("/api/v1/repairs/delete/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getRepairByPatent_ShouldReturnRepair() throws Exception {
        RepairEntity repair = new RepairEntity(1L,
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

        List<RepairEntity> repairList = new ArrayList<>(Arrays.asList(repair));
        given(repairService.getRepairByPatentfinal("DEF456")).willReturn((ArrayList<RepairEntity>) repairList);

        mockMvc.perform(get("/api/v1/repairs/all/{patent}", "DEF456"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].patent", is("DEF456")));
    }

}
