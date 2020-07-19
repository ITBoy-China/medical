package com.ncu.doctor.mapper;

import com.ncu.pojo.common.Department;

import java.util.List;

public interface DepartmentMapper {


    Department selectBelongDoctor(String departName);

    List<Department> findAllDepartments();
}