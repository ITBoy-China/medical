package com.ncu.doctor.service.impl;

import com.ncu.doctor.mapper.DepartmentMapper;
import com.ncu.doctor.service.DepartmentService;
import com.ncu.pojo.common.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : 城南有梦
 * @date : 2020-07-17 11:14:59
 * @description:
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;
    @Override
    public List<Department> findAllDepartments() {
        return departmentMapper.findAllDepartments();
    }
}
