package com.ncu.pojo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * com.ncu.pojo.common
 *
 * @author hzh
 * Created by  2020/7/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDoctor {
    String employeeId;
    String userName;
    String doctorName;
    String departName;
}
