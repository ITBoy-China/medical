package com.ncu.doctor.service.impl;

import com.ncu.doctor.mapper.EmployeeMapper;
import com.ncu.doctor.service.EmployeeService;
import com.ncu.pojo.common.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : 城南有梦
 * @date : 2020-07-16 17:14:34
 * @description:
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee login(Employee employee) {
        return employeeMapper.queryByNameAndPwd(employee);
    }
}
