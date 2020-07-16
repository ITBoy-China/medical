package com.ncu.drug.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "drug_info")
public class Drug {
    // id
    @Id
    //开启主键自动回填
    @KeySql(useGeneratedKeys = true)
    private String drugId;

    // 药品名
    private String drugName;

    // 药品编码
    private String drugCode;

    //
    private String unit;

    //
    private String drugCatalog;

    //
    private Double price;

    //
    private Integer storage;

    //
    private String delFlag;

    //
    private String remark;

    //
//    private Date updated;

    //
//    private String note;

}
