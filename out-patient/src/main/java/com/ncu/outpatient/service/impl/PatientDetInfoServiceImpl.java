package com.ncu.outpatient.service.impl;

import com.ncu.outpatient.mapper.PatientDetInfoMapper;
import com.ncu.outpatient.service.PatientDetInfoService;
import com.ncu.pojo.common.PatientDetInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : 城南有梦
 * @date : 2020-07-16 21:56:59
 * @description:
 */
@Service
public class PatientDetInfoServiceImpl implements PatientDetInfoService {
    @Resource
    private PatientDetInfoMapper patientDetInfoMapper;

    @Override
    public PatientDetInfo findOneById(String id) {
        return patientDetInfoMapper.findOneByID(id);
    }
}
