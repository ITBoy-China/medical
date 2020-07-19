package com.ncu.doctor.mapper;

import com.ncu.pojo.common.Drug;

public interface DrugMapper {

    Drug selectDrugByName(String drugName);

}