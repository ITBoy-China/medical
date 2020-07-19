package com.ncu.doctor.service.impl;

import com.ncu.doctor.mapper.*;
import com.ncu.doctor.service.DoctorService;
import com.ncu.pojo.common.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * com.ncu.doctor.service.impl
 *
 * @author hzh
 * Created by  2020/7/12.
 */
@Service
public class DoctorServiceImpl  implements DoctorService {

    @Resource
    private  MedicalListMapper  medicalListMapper;
    @Resource
    private CaseDisplayMapper caseDisplayMapper;
    @Resource
    private RecordMapper recordMapper;
    @Resource
    private  DepartmentMapper departmentMapper;
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private  DrugMapper drugMapper;
    @Resource
    private UserDoctorMapper userDoctorMapper;
    @Resource
    private OutPatientMapper outPatientMapper;


    @Override
    public  int addMedicalList(MedicalListInfo medicalListInfos){
        //处方单表中插入一条数据
        int count=1;

        String medicalListId=medicalListInfos.getMedicalListId();

        medicalListMapper.insert(medicalListInfos);
        //处方买药表中插入多条数据
        for(Check_Record item:medicalListInfos.getCheck_recordList()){
            item.setMedicalListId(medicalListId);
            item.setRecordId(UUID.randomUUID().toString().replace("-",""));
            item.setRecordDate(new Date());
            item.setPayStatus("0");
            count+=recordMapper.insert(item);
        }


        return count;
    }

    @Override
    public List<CaseDisplay> findAllCaseInfo(){
        return caseDisplayMapper.selectAllCaseInfo();
    }
    @Override
    public List<CaseDisplay> findAllCaseInfoAboutDoctor(String doctorName){
        return caseDisplayMapper.selectAllCaseInfoAboutDoctor(doctorName);
    }
    @Override
    public  List<Employee> findAllBelongDoctor(String departName) {
        String departId = departmentMapper.selectBelongDoctor(departName).getDepartId();
        return employeeMapper.selectByPrimaryKey(departId);
    }
    @Override
    public Employee findByPrimaryId(String employeeId){
        return employeeMapper.selectByPrimaryId(employeeId);
    }

    @Override
    public Employee testLogin(String userName,String userPwd){
        return employeeMapper.checkLogin(userName,userPwd);
    }
    @Override
    public CaseDisplay findAllCaseInfoByOut(String outPatientId){
        return caseDisplayMapper.selectAllCaseInfoByOut(outPatientId);
    }
    @Override
    public  Drug findAboutDrug(String drugName){
        return drugMapper.selectDrugByName(drugName);
    }
    @Override
    public  UserDoctor findUserDoctor(String userName){
        return userDoctorMapper.selectInfoByUser(userName);
    }
   @Override
    public List<CaseDisplay> findCaseInfoAboutInfo(String doctorName, String patientName, String status, Date registerTime){
        return caseDisplayMapper.selectAllCaseInfoAboutInfo(doctorName,patientName, status, registerTime);
    }
    @Override
    public int updateStatus(OutPatient outPatient){
        return outPatientMapper.updateStatusByPrimaryKey(outPatient);
    }
}
