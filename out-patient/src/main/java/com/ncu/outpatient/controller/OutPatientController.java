package com.ncu.outpatient.controller;

import com.alibaba.fastjson.JSONObject;
import com.ncu.common.utils.IdGenerator;
import com.ncu.common.utils.impl.OutPatientIdGr;
import com.ncu.outpatient.service.DepartmentService;
import com.ncu.outpatient.service.EmployeeService;
import com.ncu.outpatient.service.OutPatientService;
import com.ncu.pojo.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : 城南有梦
 * @date : 2020-07-12 08:05:09
 * @description:
 * 门诊Controller
 */
@RestController
@RequestMapping(value = "/api")
public class OutPatientController {
    @Autowired
    private OutPatientService outPatientService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    /**
     * 病人门诊挂号
     * @param data
     * @return
     */
    @PostMapping(value = "/outpatients")
    public Result<String> makeAppointment(@RequestBody JSONObject data) {
        Result<String> result = new Result<>();
        OutPatient outPatient = new OutPatient();
        outPatient.setDepartId(data.getString("departId"));
        outPatient.setPatientId(data.getString("patientId"));
        outPatient.setRegisterPrice(Double.parseDouble(data.getString("registerPrice")));
        String name = data.getString("employeeName");
        Employee employee = employeeService.findByNameAndDepart(name,outPatient.getDepartId());
        outPatient.setEmployeeId(employee.getEmployeeId());
        IdGenerator idGenerator = new OutPatientIdGr();
        //生成用户挂号id,并写入outPatient对象
        //获取科室编号
        String code = departmentService.findById(outPatient.getDepartId()).getDepartCode();
        outPatient.setOutpatientId(idGenerator.generateId(code));
        String id = outPatient.getOutpatientId();
        //设置状态为挂号
        outPatient.setStatus("0");
        //设置挂号时间
        outPatient.setRegisterTime(new Date());
        if(outPatientService.makeAppointment(outPatient)!=0){
            //挂号成功 返回用户所挂的号
            result.setData(id);
        }else{
            //挂号失败
            result.setCode(StatusCode.ERROR);
            result.setFlag(false);
            result.setMessage("挂号失败");
            result.setData(null);
        }
        return result;
    }

    /**
     * 用户退号
     * @param outPatient
     * @param id
     * @return
     */
    @RequestMapping(value = "/outpatients/{id}",method = RequestMethod.PUT)
    public Result<String> giveUpNumber(@RequestBody OutPatient outPatient,@PathVariable("id") String id){
        Result<String> result = new Result<>();
        //设置id
        outPatient.setOutpatientId(id);
        if(outPatientService.giveUpNumber(outPatient)!=0){
            //更新成功
            result.setData("退号成功");
        }else{
            //更新失败
            result.setCode(StatusCode.ERROR);
            result.setFlag(false);
            result.setMessage("退号失败");
            result.setData(null);
        }
        return result;
    }

    @GetMapping(value = "/outpatientInfos/patients/{id}")
    public Result<List<OutpatientInfo>> getAppointments(@PathVariable("id") String id){
        Result<List<OutpatientInfo>> result = new Result<>();
        List<OutpatientInfo> rs = outPatientService.queryByStatus(id);
        if(rs!=null){
            result.setData(rs);
        }else{
            //查询失败
            result.setCode(StatusCode.ERROR);
            result.setFlag(false);
            result.setMessage("查询失败");
            result.setData(null);
        }
        return result;
    }
}
