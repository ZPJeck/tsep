package com.hnu.service;

import com.hnu.model.Clazz;

/**
 * @Auther: Zpjeck
 * @Date: 2019/4/1 12:05
 * @Description:
 */
public interface TeacherClassService {

    /*
     *  根据老师id查询班级班级信息
     */
    Clazz findByTeacherId(String teacherId);
}
