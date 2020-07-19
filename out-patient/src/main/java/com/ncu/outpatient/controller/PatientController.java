package com.ncu.outpatient.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ncu.common.utils.JwtUtil;
import com.ncu.outpatient.service.PatientDrugService;
import com.ncu.outpatient.service.PatientService;
import com.ncu.pojo.common.Patient;
import com.ncu.pojo.common.PatientDrug;
import com.ncu.pojo.common.Result;
import com.ncu.pojo.common.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author : 城南有梦
 * @date : 2020-07-07 15:40:49
 * @description:
 */
@RestController
@RequestMapping("/api")
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    PatientDrugService patientDrugService;

    @GetMapping(value = "/patients/{id}")
    public Result<Patient> findPatientById(@PathVariable("id") String id){
        //根据id获取患者对象
        Patient patient = patientService.getPatientById(id);
        //创建返回结果
        Result<Patient> result = new Result<>();
        if(patient!=null){
            //正确获取到了 默认正确 只需要传入数据即可
            result.setData(patient);
        }else{
            //没有获取到 设置flag code message data
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("执行失败");
            result.setData(null);
        }
        return result;
    }

    @GetMapping(value = "/patients")
    public Result<List<Patient>> getAllPatient(){
        Result<List<Patient>> result = new Result<>();
        List<Patient> list = patientService.getAllPatient();
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

    @PostMapping(value = "/patients")
    public Result<String> applyNewCard(@RequestBody Patient patient){
        //注入id等用户信息
        patient.setPatientId(UUID.randomUUID().toString().replace("-",""));
        //设置删除标志
        patient.setDelFlag("0");
        //设置更新时间
        patient.setUpdateTime(new Date());
        Result<String> result = new Result<>();
        //插入是否成功的判断
        if(patientService.register(patient)!=0){
            //成功插入
            result.setData(patient.getPatientId());
        }else {
            //插入失败
            result.setFlag(false);
            result.setCode(StatusCode.ERROR);
            result.setMessage("插入失败");
            result.setData(null);
        }
        return result;
    }


    @PostMapping(value = "/patients/batch")
    public Result<List<String>> applyNewCardBatch(@RequestBody JSONObject batchData){
        Result<List<String>> result = new Result<>();
        //获取数据
        JSONArray array = batchData.getJSONArray("batch_data");
        //json array转list
        List<Patient> patientList = JSONObject.parseArray(array.toJSONString(),Patient.class);
        List<String> ids = new ArrayList<>();
        //解析传过来的批量数据
        for(int i = 0;i<patientList.size();i++){
            Patient patient = patientList.get(i);
            //注入id等用户信息
            String id = UUID.randomUUID().toString().replace("-","");
            patient.setPatientId(id);
            ids.add(id);
            //设置删除标志
            patient.setDelFlag("0");
            //设置更新时间
            patient.setUpdateTime(new Date());
            patientList.set(i,patient);
        }

        if(patientService.registerBatch(patientList)!=0){
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

    @RequestMapping(value = "/patients/{id}",method = RequestMethod.DELETE)
    public Result<String> recoveryCard(@PathVariable("id")String id){
        Result<String> result = new Result<>();
        if(patientService.deletePatient(id)!=0){
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

    @RequestMapping(value = "/patients/batch",method = RequestMethod.DELETE)
    public Result<String> recoveryCardByBatch(@RequestBody JSONObject batchData){
        Result<String> result = new Result<>();
        JSONArray datas = batchData.getJSONArray("batch_data");
        List<String> ids = new ArrayList<>();
        for(int i = 0;i<datas.size();i++){
            ids.add(datas.get(i).toString());
        }
        if(patientService.deleteBatch(ids)!=0){
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

    @RequestMapping(value = "/patients/{id}",method = RequestMethod.PUT)
    public Result<String> updatePatientInfo(@RequestBody Patient patient,@PathVariable("id")String id){
        Result<String> result = new Result<>();
        patient.setUpdateTime(new Date());
        patient.setPatientId(id);
        if(patientService.updateSelective(patient)!=0){
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
        Patient patient= patientService.getPatientById(id);
        if(patient!=null){
            //生成token
            try {
                String token = JwtUtil.genToken(id,patient.getPatientName());
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
            result.setMessage("卡号错误");
            result.setData(null);
        }
        return result;
    }

    @GetMapping("/PatientDrugs/{id}")
    public Result<List<PatientDrug>> getPatientDrugs(@PathVariable("id") String id){
        Result<List<PatientDrug>> result = new Result<>();
        List<PatientDrug> rs = patientDrugService.findDrugsById(id);
        if(rs!=null){
            //获取成功
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
