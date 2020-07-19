package com.ncu.pojo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 城南有梦
 * @date : 2020-07-16 21:52:47
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDetInfo {
    private String patientId;

    private String outpatientId;

    private String patientName;

    private String sex;

    private Integer age;

    private String doctorName;

    private String patientFeature;

    private String departName;
}
