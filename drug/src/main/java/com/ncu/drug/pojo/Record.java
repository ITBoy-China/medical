package com.ncu.drug.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "check_purchase_records")
public class Record {

    @Id
    //主键
    @KeySql(useGeneratedKeys = true)
    //开启主键自动回填
    private String recordId;

    private String employeeId;

    private String medicalListId;

    private Date recordDate;

    private String drugId;

    private Integer payCount;

    private String payStatus;



}