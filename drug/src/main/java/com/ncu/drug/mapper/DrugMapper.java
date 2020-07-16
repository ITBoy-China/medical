package com.ncu.drug.mapper;

import com.ncu.drug.pojo.Drug;
import com.ncu.pojo.common.Patient;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DrugMapper extends Mapper<Drug> {

    int insertBatch(@Param(value = "drugList") List<Drug> drugList);

    int deleteBatch(@Param(value = "ids") List<String> ids);
}
