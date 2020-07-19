package com.ncu.outpatient.mapper;

import com.ncu.pojo.common.Record;

public interface RecordMapper {
    int deleteByPrimaryKey(String recordId);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(String recordId);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    int updateSelective(Record record);
}