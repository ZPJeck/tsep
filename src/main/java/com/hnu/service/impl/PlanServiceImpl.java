package com.hnu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnu.Enum.Comment;
import com.hnu.dao.PlanMapper;
import com.hnu.model.Plan;
import com.hnu.model.Teacher;
import com.hnu.service.PlanService;
import com.hnu.service.TeacherService;
import com.hnu.util.Result;
import com.hnu.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 21:36
 * @Description:
 */
@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private TeacherService teacherService;

    @Override
    public int insertPlan(Plan plan) {
        if ("".equals(plan.getId())){
            return Comment.FAIL.getCode();
        }
        return planMapper.insert(plan);
    }

    @Override
    public Result<Plan> findList(Integer pageNum, Integer pageSize, String teacherId) {

        PageHelper.startPage(pageNum,pageSize);
        List<Plan> list = planMapper.findList(teacherId);
        for (Plan plan : list){
            Teacher byTeacher = teacherService.findByTeacher(plan.getTeacherId());
            plan.setCreateby(byTeacher.getName());
        }
        PageInfo<Plan> pageInfo = new PageInfo<Plan>(list);
        long total = pageInfo.getTotal();
        int size = (int) total;
        return ResultUtil.success(list,size);
    }

    @Override
    public Result<Plan> findByStudentId(Integer pageNum, Integer pageSize, String studentId) {
        PageHelper.startPage(pageNum,pageSize);
        List<Plan> list = planMapper.findByStudentId(studentId);
        for (Plan plan : list){
            Teacher teacher = teacherService.findByTeacher(plan.getCreateby());
            plan.setCreateby(teacher.getName());
        }
        return ResultUtil.success(list,list.size());
    }

    @Override
    public Plan findById(String id) {
        Plan plan = planMapper.selectByPrimaryKey(id);
        if (plan != null) {
            Teacher teacher = teacherService.findByTeacher(plan.getCreateby());
            plan.setCreateby(teacher.getName());
        }
        return plan;
    }
}
