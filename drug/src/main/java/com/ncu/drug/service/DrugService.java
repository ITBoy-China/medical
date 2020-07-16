package com.ncu.drug.service;


import com.ncu.drug.mapper.DrugMapper;
import com.ncu.drug.pojo.Drug;
import com.ncu.pojo.common.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DrugService {

    @Autowired
    private DrugMapper drugMapper;

    @Resource
    private DrugMapper drugXmlMapper;

    public Drug findDrugById(String id){
        return drugMapper.selectByPrimaryKey(id);
    }
    public List<Drug> getAllDrug(){  return drugMapper.selectAll(); }
    public int addDrug(Drug drug) {  return drugMapper.insertSelective(drug);   }
    public int deleteDrug(String id) {
        return drugMapper.deleteByPrimaryKey(id);
    }
    public int updateSelective(Drug drug) {
        return drugMapper.updateByPrimaryKeySelective(drug);
    }
//    没有insertbatch 自己写。

    public int addDrugBatch(List<Drug> drugList) {
    return drugXmlMapper.insertBatch(drugList);
}
    public int deleteDrugBatch(List<String> ids) {
        return drugXmlMapper.deleteBatch(ids);
    }
    public int deleteBatch(List<String> ids) {
        return drugXmlMapper.deleteBatch(ids);
    }


}
