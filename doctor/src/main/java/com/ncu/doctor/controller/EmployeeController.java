package com.ncu.doctor.controller;

import com.ncu.common.utils.JwtUtil;
import com.ncu.doctor.service.DepartmentService;
import com.ncu.doctor.service.EmployeeService;
import com.ncu.pojo.common.Department;
import com.ncu.pojo.common.Employee;
import com.ncu.pojo.common.Result;
import com.ncu.pojo.common.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : 城南有梦
 * @date : 2020-07-16 17:12:42
 * @description:
 * 职工Controller
 */
@RestController
@RequestMapping(value = "/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/tokens/{name}/{password}",method = RequestMethod.GET)
    public Result<String> login(@PathVariable("name") String userName,@PathVariable("password") String password){
        Result<String> result = new Result<>();
        Employee employee = new Employee();
        employee.setUserName(userName);
        employee.setUserPwd(password);
        Employee ep = employeeService.login(employee);
        if(ep!=null){
            //获取token
            try {
                String token = JwtUtil.genToken(ep.getEmployeeId(),employee.getUserName());
                result.setData(token);
            } catch (Exception e) {
                e.printStackTrace();
                result.setCode(StatusCode.REMOTEERROR);
                result.setFlag(false);
                result.setMessage("服务错误");
                result.setData(null);
            }
        }else{
            //id为null
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("登录失败");
            result.setData(null);
        }
        return result;
    }

    @GetMapping(value = "/departments")
    public Result<List<Department>> getAllDepartments(){
        Result<List<Department>> result = new Result<>();
        List<Department> rs = departmentService.findAllDepartments();
        if(rs!=null){
            result.setData(rs);
        }else{
            //获取失败
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("执行失败");
            result.setData(null);
        }
        return result;
    }
}
