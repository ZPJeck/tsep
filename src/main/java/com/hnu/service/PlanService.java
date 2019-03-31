package com.hnu.service;

import com.github.pagehelper.PageInfo;
import com.hnu.model.Plan;
import com.hnu.model.Task;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 21:32
 * @Description:
 */
public interface PlanService {

    /*
     *  添加计划
     */
    int insertPlan(Plan plan);

    PageInfo<Plan> findList(Integer pageNum, Integer pageSize, String teacherId);

    PageInfo<Plan> findByStudentId(Integer pageNum, Integer pageSize, String studentId);

    Plan findById(String id);

}
