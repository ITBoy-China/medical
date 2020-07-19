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
    //通过部门名称查询所有的所属医生姓名
    List<Employee> findAllBelongDoctor(String departName);
}
