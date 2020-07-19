package com.ncu.outpatient.service;

import com.ncu.pojo.common.OutPatient;
import com.ncu.pojo.common.OutpatientInfo;

import java.util.List;

/**
 * @author : 城南有梦
 * @date : 2020-07-12 08:08:49
 * @description:
 * 门诊接口
 */
public interface OutPatientService {
    // 预约挂号
    int makeAppointment(OutPatient outPatient);

    // 门诊退号
    int giveUpNumber(OutPatient outPatient);

    //查询挂号但是没有就诊的挂号信息
    List<OutpatientInfo> queryByStatus(String id);
}
