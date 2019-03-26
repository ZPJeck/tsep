package com.zpjeck.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: Zpjeck
 * @Date: 2018/12/12 13:45
 * @Description: 照片
 */
@Data
public class Pictures {
    private String id;

    private String userId;

    private String classNum;

    private String picturesFile;

    private String eventId;

    private Date  upTime;
}
