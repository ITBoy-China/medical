package com.ncu.outpatient.service;

import com.ncu.pojo.common.Employee;

/**
 * @author : 城南有梦
 * @date : 2020-07-17 22:13:19
 * @description:
 */
public interface EmployeeService {
    Employee findByNameAndDepart(String name,String departId);
}
