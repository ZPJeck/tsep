package com.zpjeck.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: Zpjeck
 * @Date: 2018/12/12 20:04
 * @Description: 提交事件
 */
@Data
public class Event {

    /* 事件表Id */
    private String id;
    /*  学生的学号 */
    private String userId;
    /*
        班级代码
     */
    private String classNum;

    /**
     * 事件的详情
     */
    private String detail;
    /**
     * 事件标题
     */
    private String title;
    /**
     * 可上传文档
     */
    private String eUrl;
    /**
     *  任务开始时间
     */
    private Date startTime;
    /**
     *  任务结束时间
     */
    private Date stopTime;

}
