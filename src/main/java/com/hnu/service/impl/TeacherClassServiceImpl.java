package com.hnu.service.impl;

import com.hnu.dao.TeacherClassMapper;
import com.hnu.model.Clazz;
import com.hnu.model.TeacherClass;
import com.hnu.service.TeacherClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Zpjeck
 * @Date: 2019/4/1 12:07
 * @Description:
 */
@Service
public class TeacherClassServiceImpl implements TeacherClassService {

    @Autowired
    private TeacherClassMapper teacherClassMapper;


    @Override
    public Clazz findByTeacherId(String teacherId) {
        return teacherClassMapper.findByTeacherId(teacherId);
    }
}