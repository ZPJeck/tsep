package com.hnu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnu.Enum.Comment;
import com.hnu.dao.PlanMapper;
import com.hnu.model.Plan;
import com.hnu.service.PlanService;
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

    @Override
    public int insertPlan(Plan plan) {
        if (!"".equals(plan.getId())){
            return Comment.FAIL.getCode();
        }
        return planMapper.insert(plan);
    }

    @Override
    public PageInfo<Plan> findList(Integer pageNum, Integer pageSize, String teacherId) {
        PageHelper.startPage(pageNum,pageSize);
        List<Plan> list = planMapper.findList(teacherId);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Plan> findByStudentId(Integer pageNum, Integer pageSize, String studentId) {
        PageHelper.startPage(pageNum,pageSize);
        List<Plan> list = planMapper.findByStudentId(studentId);
        return new PageInfo<>(list);
    }

    @Override
    public Plan findById(String id) {
        return planMapper.selectByPrimaryKey(id);
    }
}
