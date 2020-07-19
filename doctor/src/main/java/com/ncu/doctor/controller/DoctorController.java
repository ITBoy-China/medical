package com.ncu.doctor.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ncu.common.utils.JwtUtil;
import com.ncu.doctor.service.DoctorService;
import com.ncu.pojo.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @GetMapping(value = "/userdoctor/{userName}")
    public Result<UserDoctor> getAllUserDoctor(@PathVariable("userName") String userName){
        UserDoctor userDoctor = doctorService.findUserDoctor(userName);
        //创建返回结果
        Result<UserDoctor> result = new Result<>();
        if(userDoctor!=null){
            //正确获取到了 默认正确 只需要传入数据即可
            result.setData(userDoctor);
        }else{
            SetErrorMessage(result);
        }
        return result;
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
    @GetMapping(value = "/drug/{drugName}")
    public Result<Drug> findPatientById(@PathVariable("drugName") String drugName){
        //根据id获取患者对象
        Drug drug = doctorService.findAboutDrug(drugName);
        //创建返回结果
        Result<Drug> result = new Result<>();
        if(drug!=null){
            //正确获取到了 默认正确 只需要传入数据即可
            result.setData(drug);
        }else{
            SetErrorMessage(result);
        }
        return result;
    }
    @GetMapping(value = "/casedisplay/{doctor}")
    public Result<List<CaseDisplay>> getAllCaseInfoAboutDoctor(@PathVariable("doctor") String doctorName){
        Result<List<CaseDisplay>> result = new Result<>();
        List<CaseDisplay> list = doctorService.findAllCaseInfoAboutDoctor(doctorName);
        if(list!=null){
            //正确获取到了
            result.setData(list);
        }else{
            //获取失败
            SetErrorMessage(result);
        }
        return result;
    }
    @GetMapping(value = "/casedisplay/{doctor}/{patientName}/{status}/{registerTime}")
    public Result<List<CaseDisplay>> getAllCaseInfoAboutInfo(@PathVariable String doctor,@PathVariable String patientName,@PathVariable String status,@PathVariable String registerTime ) throws ParseException {
        Result<List<CaseDisplay>> result = new Result<>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sf.parse(registerTime);
        List<CaseDisplay> list = doctorService.findCaseInfoAboutInfo(doctor,patientName,status,date);
        if(list!=null){
            //正确获取到了
            result.setData(list);
        }else{
            //获取失败
            SetErrorMessage(result);
        }
        return result;
    }

    @GetMapping(value = "/casedisplay/out/{outPatientId}")
    public Result <CaseDisplay> getAllCaseInfoByOut(@PathVariable("outPatientId") String outPatientId){
        Result<CaseDisplay> result = new Result<>();
        CaseDisplay caseDisplay = doctorService.findAllCaseInfoByOut(outPatientId);
        if(caseDisplay!=null){
            //正确获取到了
            result.setData(caseDisplay);
        }else{
            //获取失败
            SetErrorMessage(result);
        }
        return result;
    }
    @RequestMapping(value = "/casedisplay/status/{id}",method = RequestMethod.PUT)
    public Result<String> updatePatientInfo(@PathVariable("id")String id){
        Result<String> result = new Result<>();
        OutPatient outPatient=new OutPatient();
        outPatient.setCheckTime(new Date());
        outPatient.setOutpatientId(id);
        if(doctorService.updateStatus(outPatient)!=0){
            //修改成功
            result.setData("修改成功");
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

    @GetMapping(value = "/doctor/tokens/{userName}/{userPwd}")
    public Result<String> login( @PathVariable String userName,@PathVariable String userPwd ){
        Result<String> result = new Result<>();
        //根据id查询用户
        Employee employee= doctorService.testLogin(userName,userPwd);
        if(employee!=null){
            //生成token
            try {
                String token = JwtUtil.genToken(employee.getEmployeeId(),employee.getUserName());
                result.setData(token);
            } catch (Exception e) {
                e.printStackTrace();
                SetErrorMessage(result);
                result.setCode(StatusCode.REMOTEERROR);
                result.setMessage("服务错误");
            }
        }else{
            SetErrorMessage(result);
            result.setCode(StatusCode.LOGINERROR);
            result.setMessage("卡号错误");
        }
        return result;
    }


}
