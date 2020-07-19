package com.ncu.drug.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ncu.drug.mapper.DrugInfoMapper;
import com.ncu.drug.pojo.DrugInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DrugInfoService {
    @Resource
    private DrugInfoMapper drugInfoMapper;

    public JSONObject queryDruginfo(String id){
        List<DrugInfo> rs = drugInfoMapper.queryDrugInfo(id);
        HashMap<String,List<Integer>> map = new HashMap<>();
        for(int i=0;i<rs.size();i++){
            if(map.containsKey(rs.get(i).getMedicalListId())){
                map.get(rs.get(i).getMedicalListId()).add(i);
            }else {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                map.put(rs.get(i).getMedicalListId(),tmp);
            }
        }
        String name = null,sex = null;
        Integer age = null;
        if(rs.size()>0){
            name = rs.get(0).getPatientName();
            age = rs.get(0).getAge();
            sex = rs.get(0).getSex();
        }

        int index = 0;
        JSONObject result = new JSONObject();
        JSONArray arr = new JSONArray();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(String key:map.keySet()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",name);
            jsonObject.put("age",age);
            jsonObject.put("sex",sex);
            List<Integer> tmp = map.get(key);
            jsonObject.put("medicalListId",key);
            String recordTime = null;
            String payStatus = null;
            if(tmp.size()>0){
                recordTime = simpleDateFormat.format(rs.get(tmp.get(0)).getRecordTime());
                payStatus = rs.get(tmp.get(0)).getPayStatus();
                if(payStatus.equals("0")){
                    payStatus = "暂存";
                }else if(payStatus.equals("1")){
                    payStatus = "已付款";
                }else if(payStatus.equals("2")){
                    payStatus = "未付款";
                }else if(payStatus.equals("3")){
                    payStatus = "已发货";
                }
            }
            jsonObject.put("recordTime",recordTime);
            jsonObject.put("payStatus",payStatus);
            jsonObject.put("space"," ");
            JSONArray arr2 = new JSONArray();
            for(int i = 0;i<tmp.size();i++){
                JSONObject j1 = new JSONObject();
                j1.put("index",i+1);
                j1.put("drugName",rs.get(tmp.get(i)).getDrugName());
                j1.put("payCount","*"+rs.get(tmp.get(i)).getPayCount());
                arr2.add(j1);
            }
            jsonObject.put("drugNames",arr2);
            arr.add(jsonObject);
            index++;
        }
        result.put("tableData",arr);
        return result;
    }
}
