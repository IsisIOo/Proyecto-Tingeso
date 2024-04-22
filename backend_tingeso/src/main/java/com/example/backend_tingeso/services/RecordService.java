package com.example.backend_tingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.backend_tingeso.entities.RecordEntity;
import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.repositories.RecordRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {
    @Autowired
    RecordRepository recordRepository;

    public ArrayList<RecordEntity> getRecordRepository(){
        return (ArrayList<RecordEntity>) recordRepository.findAll();
    }

    public RecordEntity getOneRecordRespository(String patent){
        return (RecordEntity) recordRepository.findByPatentOne(patent);
    }

    public RecordEntity saveRecord(RecordEntity record){
        return recordRepository.save(record);
    }
    public List<RecordEntity> getRecordsByPatent(String patent) {
        return recordRepository.findByPatent(patent);
    }

    public RecordEntity setAmount(RecordEntity record, double totalAmount) {
        record.setTotalAmount(totalAmount);
        return recordRepository.save(record);
    }

    public boolean deleteRecord(Long id) throws Exception {
        try{
            recordRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
