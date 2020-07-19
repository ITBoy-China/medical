package com.ncu.outpatient.service.impl;

import com.ncu.outpatient.mapper.RecordMapper;
import com.ncu.outpatient.service.RecordService;
import com.ncu.pojo.common.Record;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : 城南有梦
 * @date : 2020-07-19 00:28:44
 * @description:
 */
@Service
public class RecordServiceImpl implements RecordService {
    @Resource
    private RecordMapper recordMapper;

    @Override
    public int updateSelective(Record record) {
        return recordMapper.updateSelective(record);
    }
}
