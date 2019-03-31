package com.hnu.dao;

import java.util.List;

import com.hnu.model.StudentTask;
import org.apache.ibatis.annotations.Param;

public interface StudentTaskMapper {
    int countBystudentTask(StudentTask studentTask);

    int deleteBystudentTask(StudentTask studentTask);

    int deleteByPrimaryKey(String id);

    int insert(StudentTask record);

    int insertSelective(StudentTask record);

    List<StudentTask> selectBystudentTask(StudentTask studentTask);

    StudentTask selectByPrimaryKey(String id);

    int updateBystudentTaskSelective(@Param("record") StudentTask record, @Param("studentTask") StudentTask studentTask);

    int updateBystudentTask(@Param("record") StudentTask record, @Param("studentTask") StudentTask studentTask);

    int updateByPrimaryKeySelective(StudentTask record);

    int updateByPrimaryKey(StudentTask record);

    int save(StudentTask studentTask);
}