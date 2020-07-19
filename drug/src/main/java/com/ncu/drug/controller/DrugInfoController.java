package com.ncu.drug.controller;

import com.alibaba.fastjson.JSONObject;
import com.ncu.drug.pojo.DrugInfo;
import com.ncu.drug.service.DrugInfoService;
import com.ncu.pojo.common.Result;
import com.ncu.pojo.common.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DrugInfoController {
    @Autowired
    private DrugInfoService drugInfoService;

    @GetMapping("/DrugInfos/{id}")
    public Result<JSONObject> getDrugInfo(@PathVariable("id") String id){
        Result<JSONObject> result = new Result<>();
        JSONObject rs = drugInfoService.queryDruginfo(id);
        if(rs!=null){
            result.setData(rs);
        }else{
            //修改失败
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("获取失败");
            result.setData(null);
        }
        return result;
    }
}
