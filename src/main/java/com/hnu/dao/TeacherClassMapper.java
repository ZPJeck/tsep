package com.hnu.dao;

import java.util.List;

import com.hnu.model.TeacherClass;
import org.apache.ibatis.annotations.Param;

public interface TeacherClassMapper {
    int countByteacherClass(TeacherClass teacherClass);

    int deleteByteacherClass(TeacherClass teacherClass);

    int deleteByPrimaryKey(String id);

    int insert(TeacherClass record);

    int insertSelective(TeacherClass record);

    List<TeacherClass> selectByteacherClass(TeacherClass teacherClass);

    TeacherClass selectByPrimaryKey(String id);

    int updateByteacherClassSelective(@Param("record") TeacherClass record, @Param("teacherClass") TeacherClass teacherClass);

    int updateByteacherClass(@Param("record") TeacherClass record, @Param("teacherClass") TeacherClass teacherClass);

    int updateByPrimaryKeySelective(TeacherClass record);

    int updateByPrimaryKey(TeacherClass record);
}