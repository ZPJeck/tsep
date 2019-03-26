package com.zpjeck.entity;

import java.util.Date;

/**
 * @Auther: Zpjeck
 * @Date: 2018/12/12 20:11
 * @Description: 文档表格
 */
public class UserFile {
    /**
     * id
     */
    private String id;
    /**
     * 任务Id
     */
    private String eventId;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 学生班级号
     */
    private String classNum;
    /**
     * 上传的文件
     */
    private String files;
    /**
     * 上传的时间
     */
    private Date upTime;
}
