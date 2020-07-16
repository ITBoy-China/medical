package com.ncu.doctor.service;

import com.ncu.pojo.common.*;

import java.util.List;

/**
 * com.ncu.doctor.service
 *
 * @author hzh
 * Created by  2020/7/12.
 */
public interface DoctorService {

//查询所有病人挂号信息
    List<CaseDisplay> findAllCaseInfo();

    //生成处方单
    int addMedicalList(MedicalListInfo medicalListInfos);
}
