package com.zpjeck.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: Zpjeck
 * @Date: 2018/12/12 11:11
 * @Description:
 */
@Data
public class Leave {

    private String id;

    private String userId;

    private String reason;

    private String photo;

    private String state;

    private String emergeCall;

    private Date startTime;

    private Date stopTime;

}
