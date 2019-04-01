package com.hnu.Enum;


/**
 * @Auther: Zpjeck
 * @Date: 2018/12/17 11:55
 * @Description:
 */

public enum  ResultEnum {

    LOGIN_ERROR(-1,"用户名或密码错误"),
    NO_LOGIN(1,"用户未登录"),
    LOGIN_SUCCESS(0,"用户已登录"),
    USER_DATABASE_FAIL(-2,"操作数据失败")
    ;

    private Integer code;

    private String message;
    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
