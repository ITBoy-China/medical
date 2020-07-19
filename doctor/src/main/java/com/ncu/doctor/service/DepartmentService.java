package com.ncu.doctor.service;

import com.ncu.pojo.common.Department;

import java.util.List;

/**
 * @author : 城南有梦
 * @date : 2020-07-17 11:13:58
 * @description:
 */
public interface DepartmentService {
    List<Department> findAllDepartments();
}
