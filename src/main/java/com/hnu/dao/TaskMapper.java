package com.hnu.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hnu.model.Task;
import org.apache.ibatis.annotations.Param;

public interface TaskMapper {
    int countBytask(Task task);

    int deleteBytask(Task task);

    int deleteByPrimaryKey(String id);

    int insert(Task record);

    int insertSelective(Task record);

    List<Task> selectBytask(Task task);



    int updateBytaskSelective(@Param("record") Task record, @Param("task") Task task);

    int updateBytask(@Param("record") Task record, @Param("task") Task task);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    // 开始
    Task selectByPrimaryKey(String id);

    List<Task> selectBystudent(String id);

    List<Task> selectByTeacher(String teacherId);

    int save(Task task);
}