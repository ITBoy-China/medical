package com.ncu.doctor.mapper;

import com.ncu.pojo.common.UserDoctor;
import org.apache.catalina.User;

/**
 * com.ncu.doctor.mapper
 *
 * @author hzh
 * Created by  2020/7/18.
 */
public interface UserDoctorMapper {
    UserDoctor selectInfoByUser(String userName);
}
