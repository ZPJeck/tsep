package com.hnu.dao;

import com.hnu.model.Teacher;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
    int countByteacher(Teacher teacher);

    int deleteByteacher(Teacher teacher);

    int deleteByPrimaryKey(String id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    List<Teacher> selectByteacher(Teacher teacher);

    Teacher selectByPrimaryKey(String id);

    int updateByteacherSelective(@Param("record") Teacher record, @Param("teacher") Teacher teacher);

    int updateByteacher(@Param("record") Teacher record, @Param("teacher") Teacher teacher);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
}