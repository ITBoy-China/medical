package com.ncu.outpatient.service.impl;

import com.ncu.outpatient.mapper.PatientMapper;
import com.ncu.outpatient.service.PatientService;
import com.ncu.pojo.common.Patient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : 城南有梦
 * @date : 2020-07-07 10:08:25
 * @description:
 */

@Service
public class PatientServiceImpl implements PatientService {
    @Resource
    private PatientMapper patientMapper;

    @Override
    public Patient getPatientById(String id) {
        return patientMapper.selectByPrimaryKey(id);
    }

    @Override
    public int register(Patient patient) {  return patientMapper.insert(patient);   }

    @Override
    public List<Patient> getAllPatient() {
        return patientMapper.findAll();
    }

    @Override
    public int registerBatch(List<Patient> patientList) {
        return patientMapper.insertBatch(patientList);
    }

    @Override
    public int deletePatient(String id) {
        return patientMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBatch(List<String> ids) {
        return patientMapper.deleteBatch(ids);
    }

    @Override
    public int updateSelective(Patient patient) {
        return patientMapper.updateByPrimaryKeySelective(patient);
    }
}
