package com.example.backend_tingeso.controllers;

import com.example.backend_tingeso.entities.RecordEntity;
import com.example.backend_tingeso.services.RecordService;
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


@WebMvcTest(RecordController.class)
public class RecordControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecordService recordService;

    @Test
    public void listRecords_ShouldReturnRecords() throws Exception {
        RecordEntity record1 = new RecordEntity(1L,
                "ABC123",
                "Lunes",
                12,
                3,
                13,
                "Reparaciones del Sistema de Frenos",
                13,
                3,
                12,
                13,
                3,
                12,
                150000);

        RecordEntity record2 = new RecordEntity(2L,
                "EDF456",
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

        List<RecordEntity> recordList = new ArrayList<>(Arrays.asList(record1, record2));

        given(recordService.getRecordRepository()).willReturn((ArrayList<RecordEntity>) recordList);

        mockMvc.perform(get("/api/v1/record/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].patent", is("ABC123")))
                .andExpect(jsonPath("$[1].patent", is("EDF456")));
    }

    @Test
    public void getOneRecordByPatent_ShouldReturnRecord() throws Exception {
        RecordEntity record = new RecordEntity(1L,
                "EDF456",
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

        given(recordService.getOneRecordRespository("EDF456")).willReturn(record);

        mockMvc.perform(get("/api/v1/record/patent1/{patent}", "EDF456"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.patent", is("EDF456")));
    }

    @Test
    public void saveRecord_ShouldReturnSavedRecord() throws Exception {
        //EmployeeEntity employeeToSave = new EmployeeEntity(null, "12345678-9", "New Employee", 40000, 0, "B");
        RecordEntity savedRecord = new RecordEntity(1L,
                "XYZ123",
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

        given(recordService.saveRecord(Mockito.any(RecordEntity.class))).willReturn(savedRecord);

        String recordJson = """
            {
                "patent": "XYZ123",
                "admissionDateDayName": "Miercoles",
                "admissionDateDay": 15,
                "admissionDateMonth": 4,
                "admissionHour": 14,
                "repairType": "Reparaciones del Sistema de Frenos",
                "departureDateDay": 17,
                "departureDateMonth": 4,
                "departureHour": 12,
                "clientDateDay": 18,
                "clientDateMonth": 4,
                "clientHour": 12,
                "totalAmount": 15000
            }
            """;

        mockMvc.perform(post("/api/v1/record/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(recordJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patent", is("XYZ123")));
    }

    @Test
    public void updateRecord_ShouldReturnSavedRecord() throws Exception {
        RecordEntity updatedrecord = new RecordEntity(2L,
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

        given(recordService.saveRecord(Mockito.any(RecordEntity.class))).willReturn(updatedrecord);

        String recordJson = """
            {
                "patent": "ABC123",
                "admissionDateDayName": "Miercoles",
                "admissionDateDay": 15,
                "admissionDateMonth": 4,
                "admissionHour": 14,
                "repairType": "Reparaciones del Sistema de Frenos",
                "departureDateDay": 17,
                "departureDateMonth": 4,
                "departureHour": 12,
                "clientDateDay": 18,
                "clientDateMonth": 4,
                "clientHour": 12,
                "totalAmount": 150000
            }
            """;


        mockMvc.perform(post("/api/v1/record/newRecord/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(recordJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patent", is("ABC123")));
    }

    @Test
    public void deleteRecordById_ShouldReturn204() throws Exception {
        when(recordService.deleteRecord(1L)).thenReturn(true); // Assuming the method returns a boolean

        mockMvc.perform(delete("/api/v1/record/{id}", 1L))
                .andExpect(status().isNoContent());
    }

}
