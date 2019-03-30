package com.hnu.pojo;

import lombok.Data;

/**
 * @Auther: Zpjeck
 * @Date: 2018/11/26 20:14
 * @Description:
 */
@Data
public class Result<T>{

    private Integer code;

    private String msg;

    private T data;
}
