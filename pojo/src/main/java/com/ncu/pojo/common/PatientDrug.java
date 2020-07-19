package com.ncu.pojo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 城南有梦
 * @date : 2020-07-17 00:02:34
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDrug {

    private String mListId;

    private String code;

    private String name;

    private String drugId;

    private Integer nums;

    private String unit;

    private Integer amount;

    private String feature;

    private Integer unitPrice;
}
