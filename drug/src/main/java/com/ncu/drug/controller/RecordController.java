package com.ncu.drug.controller;


import com.ncu.drug.pojo.Drug;
import com.ncu.drug.pojo.MedicalList;
import com.ncu.drug.pojo.Record;
import com.ncu.drug.service.RecordService;
import com.ncu.pojo.common.Result;
import com.ncu.pojo.common.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecordController {
    @Autowired
    RecordService recordService;

    //通过病人id查询多个处方单ID
    @GetMapping("/records/{id}")
    public Result<List<Record>> findRecordById(@PathVariable("id") String id){
        //根据id获取处方单对象
        Record  recordParam = new Record();

        recordParam.setMedicalListId(id);

        List<Record> record = recordService.findRecordById(recordParam);
        //创建返回结果 集s
        Result<List<Record>> result = new Result<>();



        if (record != null) {
            //正确获取到了 默认正确 只需要传入数据即可
            result.setData(record);
        } else {
            //没有获取到 设置flag code message data
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("执行失败");
            result.setData(null);
        }

        return result;
    }

    @RequestMapping(value = "/records/updateStatus/{id}",method = RequestMethod.PUT)
    public Result<String> updatePayStatusByMid(@PathVariable("id")String id) {
        Result<String> result = new Result<>();
//        drug.setUpdateTime(new Date());
//        drug.setDrugId(id);
        if (recordService.updatePayStatusByMid(id) != 0) {
            //修改成功
            result.setData("修改成功");
        } else {
            //修改失败
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("修改失败");
            result.setData(null);
        }
        return result;
    }

}
