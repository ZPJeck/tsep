package com.zpjeck.exception;

import com.zpjeck.Enum.ResultEnum;

/**
 * @Auther: Zpjeck
 * @Date: 2018/11/27 18:52
 * @Description:
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }
    public SellException(Integer code, String message){
        super(message);
        this.code = code;

    }
}
