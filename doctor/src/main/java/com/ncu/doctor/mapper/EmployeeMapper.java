package com.ncu.doctor.mapper;

import com.ncu.pojo.common.Employee;

import java.util.List;

public interface EmployeeMapper {


   List<Employee> selectByPrimaryKey(String employeeId);

   Employee queryByNameAndPwd(Employee employee);

}