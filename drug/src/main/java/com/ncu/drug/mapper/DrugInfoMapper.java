package com.ncu.drug.mapper;

import com.ncu.drug.pojo.DrugInfo;

import java.util.List;

public interface DrugInfoMapper {
    List<DrugInfo> queryDrugInfo(String medicalListId);
}
