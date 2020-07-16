package com.ncu.pojo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * com.ncu.pojo.common
 *
 * @author hzh
 * Created by  2020/7/13.
 * CaseDisplay基于门诊表类，显示给医生病人信息和状况、需要职工表、部门表、病人表、门诊表（可能需要处方单表)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseDisplay {

    private String patientName;

    private Integer age;

    private String sex;

    private String phone;

    private String patientFeature;

    private String delFlag;

    private String doctorName;

    private String departName;

    private Date registerTime;

    private String status;



}
