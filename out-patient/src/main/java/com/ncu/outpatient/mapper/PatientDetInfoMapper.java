package com.ncu.outpatient.mapper;


import com.ncu.pojo.common.PatientDetInfo;

/**
 * @author : 城南有梦
 * @date : 2020-07-16 21:57:41
 * @description:
 */
public interface PatientDetInfoMapper {
    PatientDetInfo findOneByID(String id);
}
