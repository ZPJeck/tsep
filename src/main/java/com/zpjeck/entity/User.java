package com.zpjeck.entity;


import jdk.nashorn.internal.objects.annotations.Property;
import lombok.Data;

/**
 * @Auther: Zpjeck
 * @Date: 2018/12/12 10:35
 * @Description:  用户表
 */
@Data
public class User {

    /* 用户表的id  */
    private String Id;

    /* 用户登录名称  学号，stu_id */
    private String userId;

    /* 密码 */
    private String password;

    /* 班级代号 */
    private String classNum;

    /* 班级名称 */
    private String className;

    /*  用户权限 */
    private String uLimit;


}
