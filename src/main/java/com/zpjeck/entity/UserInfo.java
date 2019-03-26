package com.zpjeck.entity;

import lombok.Data;

/**
 * @Auther: Zpjeck
 * @Date: 2018/12/12 10:51
 * @Description:  用户信息表
 */
@Data
public class UserInfo {
    /* 用户信息 id */
    private String id;

    /* 用户姓名*/
    private String name;
    /* 用户id  泛指学号*/
    private String userId;
    /*  用户年龄  */
    private Integer age;
    /*  性别  */
    private String sex;
    /*  地址  */
    private String address;
    /*  电话  */
    private String phone;
    /*  身份证号  */
    private String cardId;
    /*  寝室  */
    private Integer bedroomNum;
    /*   具体床铺   */
    private String bedroom;
    /*  自定义头像  */
    private String icon;
}
