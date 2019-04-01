package com.hnu.service;

import com.github.pagehelper.PageInfo;
import com.hnu.model.StudentTask;
import com.hnu.model.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 15:00
 * @Description:
 */
public interface TaskService {

    Task selectById(@Param("id") String id); // 根据id查询作业

    PageInfo<Task> selectBystudent(Integer pageNum, Integer pageSize,String id);

    PageInfo<Task> selectByTeacher(Integer pageNum, Integer pageSize,String teacherId);

    int save(Task task);

    int relayTask(StudentTask studentTask);
}
