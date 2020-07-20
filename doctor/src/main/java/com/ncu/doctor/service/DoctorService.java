package com.ncu.doctor.service;

import com.ncu.pojo.common.*;
import org.apache.catalina.User;

import java.util.Date;
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
    //根据医生姓名查询挂号信息
    List<CaseDisplay> findAllCaseInfoAboutDoctor(String doctorName);
    //根据门诊号查询相关信息
    CaseDisplay findAllCaseInfoByOut(String outPatientId);

    //生成处方单
    int addMedicalList(MedicalListInfo medicalListInfos);
    //通过部门名称查询所有的所属医生姓名
    List<Employee> findAllBelongDoctor(String departName);
    //查询医生信息
    Employee findByPrimaryId(String employeeId);
    //登录验证
    Employee testLogin(String userName,String userPwd);
    //根据药品名称查询药物信息
    Drug findAboutDrug(String drugName);

    UserDoctor findUserDoctor(String userName);

    List<CaseDisplay> findCaseInfoAboutInfo(String doctorName, String patientName, String status, Date registerTime);

    int updateStatus(OutPatient outPatient);
}
