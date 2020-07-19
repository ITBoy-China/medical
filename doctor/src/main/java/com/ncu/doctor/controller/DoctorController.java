package com.ncu.doctor.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ncu.doctor.service.DoctorService;
import com.ncu.pojo.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * com.ncu.doctor.controller
 *
 * @author hzh
 * Created by  2020/7/12.
 */
@RestController
@RequestMapping("/api")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    public int SetErrorMessage(Result result){
        result.setFlag(false);
        result.setCode(StatusCode.ERROR);
        result.setMessage("执行失败");
        result.setData(null);
        return 0;
    }

    @GetMapping(value = "/casedisplay")
    public Result<List<CaseDisplay>> getAllCaseInfo(){
        Result<List<CaseDisplay>> result = new Result<>();
        List<CaseDisplay> list = doctorService.findAllCaseInfo();
        if(list!=null){
            //正确获取到了
            result.setData(list);
        }else{
            //获取失败
            SetErrorMessage(result);
        }
        return result;
    }

    @PostMapping(value = "/doctor/medical_list")
    public Result <String> saveMedicalList(@RequestBody MedicalListInfo medicalListInfo){
        //注入id等用户信息
        medicalListInfo.setMedicalListId(UUID.randomUUID().toString().replace("-",""));
        medicalListInfo.setRecordTime(new Date());
        Result<String> result = new Result<>();
        //插入是否成功的判断
        if(doctorService.addMedicalList(medicalListInfo)!=0){
            //成功插入
            result.setData(medicalListInfo.getMedicalListId());
        }else {
            //插入失败
            SetErrorMessage(result);
        }
        return result;
    }

    @GetMapping(value = "/doctor/depart/{depart}")

    public Result<List<String>> getAllBelongDoctor(@PathVariable("depart") String departName){
        Result<List<String>> result = new Result<>();
        List<Employee> doctors = doctorService.findAllBelongDoctor(departName);
        List<String> doctornameList = doctors.stream().map(Employee -> Employee.getName()).collect(Collectors.toList());
        if(doctornameList!=null){
            //正确获取到了
            result.setData(doctornameList);
        }else{
            //获取失败
            SetErrorMessage(result);
        }
        return result;
    }


}
