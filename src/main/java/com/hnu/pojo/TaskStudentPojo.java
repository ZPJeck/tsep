package com.hnu.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: Zpjeck
 * @Date: 2019/4/3 21:00
 * @Description:
 */
@Data
public class TaskStudentPojo {

    private String id;

    private String title;

    private String content;

    private String answer;

    private String feedback;

    private Date updatetime;
}
