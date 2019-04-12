package com.hnu.service;

import com.github.pagehelper.PageInfo;
import com.hnu.model.StudentTask;
import com.hnu.model.Task;
import com.hnu.pojo.TaskStudentPojo;
import com.hnu.util.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 15:00
 * @Description:
 */
public interface TaskService {

    Task selectById(@Param("id") String id); // 根据id查询作业

    Result<Task> selectBystudent(Integer pageNum, Integer pageSize, String id);

    Result<Task> selectByTeacher(Integer pageNum, Integer pageSize,String teacherId);

    int save(Task task);

    int relayTask(StudentTask studentTask);

    Result<TaskStudentPojo>  findStudentByTaskId(String taskId);

    Result<Task>  listByStudentUnfinish(Integer pageNum, Integer pageSize,String id);

    Result deleteTask(String id);

    Result updateTask(Task task);

    Result<StudentTask> studentTaskList(Integer pageNum, Integer pageSize,String classId,String taskId);

    Result<StudentTask> findById(String id);
}
