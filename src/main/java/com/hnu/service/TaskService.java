package com.hnu.service;

import com.github.pagehelper.PageInfo;
import com.hnu.model.Task;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 15:00
 * @Description:
 */
public interface TaskService {

    Task selectById(@Param("id") String id); // 根据id查询作业

    PageInfo<Task> selectBystudent(Integer pageNum, Integer pageSize,String id);


}
