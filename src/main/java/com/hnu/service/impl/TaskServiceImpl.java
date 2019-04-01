package com.hnu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnu.dao.StudentTaskMapper;
import com.hnu.dao.TaskMapper;
import com.hnu.model.StudentTask;
import com.hnu.model.Task;
import com.hnu.model.Teacher;
import com.hnu.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 15:02
 * @Description:
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private StudentTaskMapper studentTaskMapper;

    @Autowired
    private HttpSession session;


    @Override
    public Task selectById(String id) {
        Task task = taskMapper.selectByPrimaryKey(id);
        return task;
    }

    @Override
    public PageInfo<Task> selectBystudent(Integer pageNum, Integer pageSize,String id) {
        PageHelper.startPage(pageNum, pageSize);
        // 查询数据
        List<Task> list = taskMapper.selectBystudent(id);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Task> selectByTeacher(Integer pageNum, Integer pageSize,String teacherId) {
        PageHelper.startPage(pageNum, pageSize);
        // 查询数据
        List<Task> list = taskMapper.selectBystudent(teacherId);
        return new PageInfo<>(list);
    }

    @Override
    public int save(Task task) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        task.setCreateby(teacher.getId());
        task.setCreatetime(new Date());
        return taskMapper.save(task);
    }

    @Override
    public int relayTask(StudentTask studentTask) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        studentTask.setUpdatetime(new Date());
        studentTask.setUpdateby(teacher.getId());
        return studentTaskMapper.relayTask(studentTask);
    }
}
