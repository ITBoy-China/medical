package com.ncu.doctor.mapper;

import com.ncu.pojo.common.MedicalList;
import com.ncu.pojo.common.MedicalListInfo;
import com.ncu.pojo.common.Patient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MedicalListMapper {
    int deleteByPrimaryKey(String medicalListId);

    int insert(MedicalListInfo record);


    int insertSelective(MedicalList record);

    MedicalList selectByPrimaryKey(String medicalListId);

    int updateByPrimaryKeySelective(MedicalList record);

    int updateByPrimaryKey(MedicalList record);
}