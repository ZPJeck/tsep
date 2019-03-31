package com.hnu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnu.dao.TaskMapper;
import com.hnu.model.Task;
import com.hnu.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
