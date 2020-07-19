package com.ncu.outpatient.mapper;

import com.ncu.pojo.common.PatientDrug;

import java.util.List;

/**
 * @author : 城南有梦
 * @date : 2020-07-17 00:08:01
 * @description:
 */
public interface PatientDrugMapper {
    List<PatientDrug> findDrugsById(String id);
}
