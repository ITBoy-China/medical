package com.ncu.drug.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ncu.common.utils.JwtUtil;
import com.ncu.drug.pojo.Drug;
import com.ncu.drug.service.DrugService;
import com.ncu.pojo.common.Patient;
import com.ncu.pojo.common.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ncu.pojo.common.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class DrugController {
    @Autowired
    private DrugService drugService;

//    @GetMapping("/drug/{id}")
//    public Drug queryById(@PathVariable String id) { return drugService.queryById(id);}

    @GetMapping("/drugs/{id}")
    public Result<Drug> findDrugById(@PathVariable("id") String id){
        //根据id获取药品对象
        Drug drug = drugService.findDrugById(id);
        //创建返回结果
        Result<Drug> result = new Result<>();
        if(drug!=null){
            //正确获取到了 默认正确 只需要传入数据即可
            result.setData(drug);
        }else{
            //没有获取到 设置flag code message data
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("执行失败");
            result.setData(null);
        }
        return result;
    }

    @GetMapping("/drugs")
    public Result<List<Drug>> getAllDrug(){
        Result<List<Drug>> result = new Result<>();
        List<Drug> list = drugService.getAllDrug();
        if(list!=null){
            //正确获取到了
            result.setData(list);
        }else{
            //获取失败
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("执行失败");
            result.setData(null);
        }
        return result;
    }
    @PostMapping("/drugs")
    public Result<String> applyNewDrug(@RequestBody Drug drug){
        //设置药品名称
        drug.setDrugName(drug.getDrugName());
        //设置药品编号
        drug.setDrugCode(drug.getDrugCode());
        //设置药品规格
        drug.setUnit(drug.getUnit());
        //设置药品目录
        drug.setDrugCatalog(drug.getDrugCode());
        //设置药品价格
        drug.setPrice((drug.getPrice()));
        //设置药品库存
        drug.setStorage(drug.getStorage());
        //设置删除标志
        drug.setDelFlag("0");
        //设置备注内容
        drug.setRemark(drug.getRemark());



//        //设置更新时间
//        patient.setUpdateTime(new Date());


        Result<String> result = new Result<>();
        //插入是否成功的判断
        if(drugService.addDrug(drug)!=0){
            //成功插入
            result.setData(drug.getDrugId());
        }else {
            //插入失败
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("插入失败");
            result.setData(null);
        }
        return result;
    }
    /**                                               从这里继续工作                             */

    //通用mapper没有insertBatch
    //需要写xml



    @PostMapping(value = "/drugs/batch")
    public Result<List<String>> addNewDrugBatch(@RequestBody JSONObject batchData){
        Result<List<String>> result = new Result<>();
        //获取数据
        JSONArray array = batchData.getJSONArray("batch_data");
        //json array转list
        List<Drug> drugList = JSONObject.parseArray(array.toJSONString(),Drug.class);
        List<String> ids = new ArrayList<>();
        //解析传过来的批量数据
        for(int i = 0;i<drugList.size();i++){

/*
            Drug drug = drugList.get(i);
            //注入id等用户信息


            String id = UUID.randomUUID().toString().replace("-","");
            patient.setPatientId(id);
            ids.add(id);
            //设置删除标志
            patient.setDelFlag("0");
            //设置更新时间
            patient.setUpdateTime(new Date());
            patientList.set(i,patient);

*/

            Drug drug = drugList.get(i);
            //drug id自增 不用设置？
            //设置药品名称
            drug.setDrugName(drug.getDrugName());
            //设置药品编号
            drug.setDrugCode(drug.getDrugCode());
            //设置药品规格
            drug.setUnit(drug.getUnit());
            //设置药品目录
            drug.setDrugCatalog(drug.getDrugCode());
            //设置药品价格
            drug.setPrice((drug.getPrice()));
            //设置药品库存
            drug.setStorage(drug.getStorage());
            //设置删除标志
            drug.setDelFlag("0");
            //设置备注内容
            drug.setRemark(drug.getRemark());

            drugList.set(i,drug);

        }

        if(drugService.addDrugBatch(drugList)!=0){
            //成功插入
            result.setData(ids);
        }else{
            //插入失败
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("插入失败");
            result.setData(null);
        }
        return result;
    }





    @RequestMapping(value = "/drugs/{id}",method = RequestMethod.DELETE)
    public Result<String> recoveryDrug(@PathVariable("id")String id){
        Result<String> result = new Result<>();
        if(drugService.deleteDrug(id)!=0){
            //删除成功
            result.setData("删除成功");
        }else{
            //删除失败
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("删除失败");
            result.setData(null);
        }
        return result;
    }



    @RequestMapping(value = "/drugs/batch",method = RequestMethod.DELETE)
    public Result<String> deleteDrugBatch(@RequestBody JSONObject batchData){
        Result<String> result = new Result<>();
        JSONArray datas = batchData.getJSONArray("batch_data");
        List<String> ids = new ArrayList<>();
        for(int i = 0;i<datas.size();i++){
            ids.add(datas.get(i).toString());
        }
        if(drugService.deleteBatch(ids)!=0){
            //删除成功
            result.setData("删除成功");
        }else{
            //删除失败
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("删除失败");
            result.setData(null);
        }
        return result;
    }



    @RequestMapping(value = "/drugs/{id}",method = RequestMethod.PUT)
    public Result<String> updatePatientInfo(@RequestBody Drug drug,@PathVariable("id")String id){
        Result<String> result = new Result<>();
//        drug.setUpdateTime(new Date());
        drug.setDrugId(id);
        if(drugService.updateSelective(drug)!=0){
            //修改成功
            result.setData("修改成功");
        }else{
            //修改失败
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("修改失败");
            result.setData(null);
        }
        return result;
    }


    @GetMapping(value = "/tokens/{id}")
    public Result<String> login(@PathVariable("id") String id){
        Result<String> result = new Result<>();
        //根据id查询用户
        Drug drug= drugService.findDrugById(id);
        if(drug!=null){
            //生成token
            try {
                String token = JwtUtil.genToken(id,drug.getDrugName());
                result.setData(token);
            } catch (Exception e) {
                e.printStackTrace();
                result.setCode(StatusCode.REMOTEERROR);
                result.setFlag(false);
                result.setMessage("服务错误");
                result.setData(null);
            }
        }else{
            result.setCode(StatusCode.LOGINERROR);
            result.setFlag(false);
            result.setMessage("药品ID错误");
            result.setData(null);
        }
        return result;
    }

}
