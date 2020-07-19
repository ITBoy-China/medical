package com.ncu.outpatient.service;

import com.ncu.pojo.common.PatientDrug;

import java.util.List;

/**
 * @author : 城南有梦
 * @date : 2020-07-17 00:05:57
 * @description:
 */
public interface PatientDrugService {
    List<PatientDrug> findDrugsById(String id);
}
