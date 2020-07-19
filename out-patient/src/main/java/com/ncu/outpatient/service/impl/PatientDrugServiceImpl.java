package com.ncu.outpatient.service.impl;

import com.ncu.outpatient.mapper.PatientDrugMapper;
import com.ncu.outpatient.service.PatientDrugService;
import com.ncu.pojo.common.PatientDrug;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : 城南有梦
 * @date : 2020-07-17 00:07:00
 * @description:
 */
@Service
public class PatientDrugServiceImpl implements PatientDrugService {
    @Resource
    private PatientDrugMapper patientDrugMapper;

    @Override
    public List<PatientDrug> findDrugsById(String id) {
        return patientDrugMapper.findDrugsById(id);
    }
}
