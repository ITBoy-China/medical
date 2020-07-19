package com.ncu.pojo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : 城南有梦
 * @date : 2020-07-18 08:54:00
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutpatientInfo {
    private String outpatientId;

    private String patientName;

    private String name;

    private String departName;

    private Date registerTime;
}
