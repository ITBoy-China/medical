package com.ncu.outpatient.service.impl;

import com.ncu.outpatient.mapper.EmployeeMapper;
import com.ncu.outpatient.service.EmployeeService;
import com.ncu.pojo.common.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : 城南有梦
 * @date : 2020-07-17 22:16:15
 * @description:
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee findByNameAndDepart(String name, String departId) {
        return employeeMapper.findByNameAndDepart(name,departId);
    }
}
