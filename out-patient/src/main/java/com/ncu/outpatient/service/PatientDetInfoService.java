package com.ncu.outpatient.service;


import com.ncu.pojo.common.PatientDetInfo;

/**
 * @author : 城南有梦
 * @date : 2020-07-16 21:55:59
 * @description:
 */
public interface PatientDetInfoService {
    PatientDetInfo findOneById(String id);
}
