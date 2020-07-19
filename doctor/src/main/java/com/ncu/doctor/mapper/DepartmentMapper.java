package com.ncu.doctor.mapper;

import com.ncu.pojo.common.Department;

public interface DepartmentMapper {


    Department selectBelongDoctor(String departName);


}