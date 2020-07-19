package com.ncu.drug.controller;

import com.ncu.drug.pojo.Drug;
import com.ncu.drug.pojo.MedicalList;
import com.ncu.drug.service.MedicalListService;
import com.ncu.pojo.common.Result;
import com.ncu.pojo.common.StatusCode;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class MedicalListController {
    @Autowired
    MedicalListService medicalListService;


    //通过病人id查询多个处方单ID
    @GetMapping("/medicallists/{id}")
    public Result<List<MedicalList>> findMedicalListById(@PathVariable("id") String id){
        //根据id获取处方单对象

        MedicalList medicalListParam = new MedicalList();
        medicalListParam.setPatientId(id);

        List<MedicalList> medicalList = medicalListService.findMedicalListById(medicalListParam);
        //创建返回结果 集
        Result<List<MedicalList>> result = new Result<>();



            if (medicalList != null) {
                //正确获取到了 默认正确 只需要传入数据即可
                result.setData(medicalList);
            } else {
                //没有获取到 设置flag code message data
                result.setFlag(false);
                result.setCode(StatusCode.ERROR);
                result.setMessage("执行失败");
                result.setData(null);
            }

        return result;
    }


}
