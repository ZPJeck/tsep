package com.hnu.Enum;

/**
 * @Auther: Zpjeck
 * @Date: 2018/12/19 21:16
 * @Description:
 */
public enum Comment {

    FAIL(0,"传参不能为空"),
    ;

    private Integer code;

    private String message;

    Comment(Integer code, String message) {
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
