package com.ncu.drug.service;


import com.ncu.drug.mapper.DrugMapper;
import com.ncu.drug.mapper.RecordMapper;
import com.ncu.drug.pojo.MedicalList;
import com.ncu.drug.pojo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecordService {
    @Autowired(required = false)
    RecordMapper recordMapper;



    @Resource
    private RecordMapper recordXmlMapper;

    public List<Record> findRecordById(Record record){
        return recordMapper.select(record);
    }
    public List<Record> findPayStatusByMid (Record record) {return recordMapper.select(record);}
    public int updatePayStatusByMid(String medicalListId) {return recordXmlMapper.updatePayStatusByMid(medicalListId);}
}
