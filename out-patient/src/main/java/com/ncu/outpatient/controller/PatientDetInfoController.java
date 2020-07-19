package com.ncu.outpatient.controller;

import com.ncu.outpatient.service.PatientDetInfoService;
import com.ncu.pojo.common.PatientDetInfo;
import com.ncu.pojo.common.Result;
import com.ncu.pojo.common.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 城南有梦
 * @date : 2020-07-16 20:45:23
 * @description:
 * 患者详细信息
 */
@RestController
@RequestMapping("/api")
public class PatientDetInfoController {
    @Autowired
    private PatientDetInfoService patientDetInfoService;

    @GetMapping(value = "/PatientDetInfos/{id}")
    public Result<PatientDetInfo> getPatientDetInfo(@PathVariable("id") String id){
        Result<PatientDetInfo> result = new Result<>();
        PatientDetInfo patientDetInfo = patientDetInfoService.findOneById(id);
        if(patientDetInfo!=null){
            //查询到了
            result.setData(patientDetInfo);
        }else{
            //没有查询到
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("执行失败");
            result.setData(null);
        }
        return result;
    }
}
