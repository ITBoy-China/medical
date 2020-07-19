package com.ncu.outpatient.mapper;

import com.ncu.pojo.common.Employee;

/**
 * @author : 城南有梦
 * @date : 2020-07-17 22:15:00
 * @description:
 */
public interface EmployeeMapper {
    Employee findByNameAndDepart(String name, String departId);
}
