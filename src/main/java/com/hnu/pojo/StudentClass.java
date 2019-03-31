package com.hnu.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 10:16
 * @Description:
 */
@Data
public class StudentClass implements Serializable {
    private String id;

    private String classId;

    private String name;

    private String number;

    private String password;

    private String phone;

    private String address;

    private String sex;

    private Integer age;

    private String portrait;

    private String className;

    private String classNum;

}
