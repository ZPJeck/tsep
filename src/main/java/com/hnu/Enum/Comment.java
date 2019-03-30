package com.hnu.Enum;

/**
 * @Auther: Zpjeck
 * @Date: 2018/12/19 21:16
 * @Description:
 */
public enum Comment {

    ADMIN("0","管理员"),
    MONITOR("1","班长"),
    CLASSMATE("2","同学"),
    ;

    private String code;

    private String message;

    Comment(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
