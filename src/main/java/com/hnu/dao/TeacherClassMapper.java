package com.hnu.dao;

import java.util.List;

import com.hnu.model.Clazz;
import com.hnu.model.Teacher;
import com.hnu.model.TeacherClass;
import org.apache.ibatis.annotations.Param;

public interface TeacherClassMapper {
    List<Clazz> findByTeacherId(@Param("teacherId") String teacherId);
    Teacher findByClassId(String classId);
}