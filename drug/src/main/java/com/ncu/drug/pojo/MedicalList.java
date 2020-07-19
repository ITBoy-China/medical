package com.ncu.drug.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "medical_list")
public class MedicalList {

    @Id
    //主键
    @KeySql(useGeneratedKeys = true)
    //开启主键自动回填
    private String medicalListId;

    private String employeeId;

    private String patientId;

    private String outpatientId;

    private String remark;

    private Date recordTime;
}