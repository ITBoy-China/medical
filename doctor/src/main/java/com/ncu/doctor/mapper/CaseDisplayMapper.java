package com.ncu.doctor.mapper;

import com.ncu.pojo.common.CaseDisplay;

import java.util.Date;
import java.util.List;

/**
 * com.ncu.doctor.mapper
 *
 * @author hzh
 * Created by  2020/7/13.
 */
public interface CaseDisplayMapper {
    List<CaseDisplay> selectAllCaseInfo();

    List<CaseDisplay> selectAllCaseInfoAboutDoctor(String doctorName);
    List<CaseDisplay> selectAllCaseInfoAboutInfo(String doctorName, String patientName, String status, Date registerTime);


    CaseDisplay selectAllCaseInfoByOut(String outPatientId);


}
