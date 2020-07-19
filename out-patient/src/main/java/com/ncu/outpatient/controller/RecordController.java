package com.ncu.outpatient.controller;

import com.ncu.outpatient.service.RecordService;
import com.ncu.pojo.common.Record;
import com.ncu.pojo.common.Result;
import com.ncu.pojo.common.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : 城南有梦
 * @date : 2020-07-19 00:33:44
 * @description:
 */
@RestController
@RequestMapping("/api")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @RequestMapping(value = "/records",method = RequestMethod.PUT)
    public Result<String> getPaid(@RequestBody List<Record> records){
        Result<String> result = new Result<>();
        int index = 0;
        for(Record record:records){
            recordService.updateSelective(record);
            index++;
        }
        if(index==records.size()){
            result.setData("收费成功");
        }else{
            //收费失败
            result.setCode(StatusCode.ERROR);
            result.setFlag(false);
            result.setMessage("收费失败");
            result.setData(null);
        }
        return result;
    }
}
