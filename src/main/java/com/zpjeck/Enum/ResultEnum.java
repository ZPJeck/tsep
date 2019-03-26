package com.zpjeck.Enum;


/**
 * @Auther: Zpjeck
 * @Date: 2018/12/17 11:55
 * @Description:
 */

public enum  ResultEnum {

    LOGIN_ERROR(0,"用户名或密码错误"),
    INCREASE_USER(2,"添加用户失败"),
    DELETE_USER(3,"删除用户失败"),
    UPDATE_USER(4,"更新用户失败"),
    select_one_User(5,""),

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
