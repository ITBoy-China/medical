package com.ncu.drug.mapper;

import com.ncu.drug.pojo.Record;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RecordMapper extends Mapper<Record> {

    int updatePayStatusByMid(@Param(value = "medicalListId") String medicalListId);
}
