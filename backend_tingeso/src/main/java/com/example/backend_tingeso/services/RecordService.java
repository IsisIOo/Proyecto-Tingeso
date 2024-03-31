package com.example.backend_tingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.backend_tingeso.entities.RecordEntity;
import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.services.RecordService;
import com.example.backend_tingeso.repositories.RecordRepository;


import java.util.ArrayList;
import java.util.List;

public class RecordService {
    @Autowired
    RecordRepository recordRepository;

    public ArrayList<RecordEntity> getadmissionDate(){
        return (ArrayList<RecordEntity>) recordRepository.findAll();
    }

    public RecordEntity saveExtraHours(RecordEntity extraHour){
        return recordRepository.save(recordRepository);
    }

    public RecordEntity getExtraHourById(Long id){
        return recordRepository.findById(id).get();
    }

    public List<RecordEntity> getAdmissionHourByPatent(String patent){
        return (List<RecordEntity>) recordRepository.findByPatent(patent);
    }

    public RecordEntity updateAdmissionHour(RecordEntity hour) {
        return recordRepository.save(hour);
    }

    public List<RecordEntity> getExtraHoursByRutYearMonth(String rut, int year, int month) {
        return (List<RecordEntity>) recordRepository.getExtraHoursByRutYearMonth(rut, year, month);
    }

    public int getTotalExtraHoursByRutYearMonth(String rut, int year, int month) {
        List<RecordEntity> extraHours = recordRepository.getExtraHoursByRutYearMonth(rut, year, month);
        int sumExtraHours = 0;
        for (RecordEntity extraHour : extraHours) {
            sumExtraHours = sumExtraHours + extraHour.getNumExtraHours();
        }
        return sumExtraHours;
    }

    public boolean deleteExtraHour(Long id) throws Exception {
        try{
            recordRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
