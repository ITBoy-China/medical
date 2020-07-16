package com.ncu.pojo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * com.ncu.pojo.common
 *
 * @author hzh
 * Created by  2020/7/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalListInfo {
    private String medicalListId;

    private String doctorName;

    private String patientName;

    private String outpatientId;

    private String remark;

    private Date recordTime;

    private List<Check_Record> check_recordList;


}
