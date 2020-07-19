package com.ncu.drug.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugInfo {
    private String medicalListId;
    private String patientName;
    private Integer age;
    private String sex;
    private Date recordTime;
    private String drugName;
    private Integer payCount;
    private String payStatus;
}
