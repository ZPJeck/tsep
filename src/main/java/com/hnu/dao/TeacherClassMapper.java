package com.hnu.dao;

import java.util.List;

import com.hnu.model.Clazz;
import com.hnu.model.TeacherClass;
import org.apache.ibatis.annotations.Param;

public interface TeacherClassMapper {
    Clazz findByTeacherId(String teacherId);
}