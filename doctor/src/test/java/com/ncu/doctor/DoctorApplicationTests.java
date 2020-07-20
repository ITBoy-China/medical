package com.ncu.doctor;

import com.ncu.doctor.mapper.OutPatientMapper;
import com.ncu.doctor.mapper.UserDoctorMapper;
import com.ncu.doctor.service.DoctorService;
import com.ncu.pojo.common.OutPatient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DoctorApplicationTests {
@Autowired
    private OutPatientMapper outPatientMapper;
private OutPatient outPatient=new OutPatient();

    @Test
    void contextLoads() {
        outPatient.setOutpatientId("20200713103013-0300-1856");
        System.out.println(outPatientMapper.updateStatusByPrimaryKey(outPatient));
    }

}
