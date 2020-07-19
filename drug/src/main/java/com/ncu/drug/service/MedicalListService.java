package com.ncu.drug.service;


import com.ncu.drug.mapper.MedicalListMapper;
import com.ncu.drug.pojo.Drug;
import com.ncu.drug.pojo.MedicalList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalListService {
    @Autowired(required = false)
    MedicalListMapper medicalListMapper;

    public List<MedicalList> findMedicalListById(MedicalList medicalList){
        return medicalListMapper.select(medicalList);
    }
}
