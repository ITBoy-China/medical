package com.ncu.pojo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * com.ncu.pojo.common
 *
 * @author hzh
 * Created by  2020/7/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Check_Record {
    private String recordId;

    private String operatorName;

    private String medicalListId;

    private Date recordDate;//购药时间，根据该属性进行库存管理

    private String drugName;

    private Integer payCount;

    private String payStatus;

    private MedicalListInfo medicalListInfo;
}
