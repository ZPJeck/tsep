package com.hnu.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hnu.model.StudentTask;
import com.hnu.model.Task;
import com.hnu.pojo.TaskStudentPojo;
import org.apache.ibatis.annotations.Param;

public interface TaskMapper {
    int countBytask(Task task);

    int deleteBytask(Task task);



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

    TaskStudentPojo findStudentByTaskId(@Param("id") String taskId);
    List<StudentTask> isExitStudentTask(@Param("studenId") String id,@Param("taskId") String taskId);

    int deleteTask(String id);

    int updateTask(Task task);
}