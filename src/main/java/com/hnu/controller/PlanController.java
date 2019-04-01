package com.hnu.controller;

import com.github.pagehelper.PageInfo;
import com.hnu.Enum.ResultEnum;
import com.hnu.model.Plan;
import com.hnu.model.Student;
import com.hnu.model.Task;
import com.hnu.model.Teacher;
import com.hnu.service.impl.PlanServiceImpl;
import com.hnu.util.Result;
import com.hnu.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 21:06
 * @Description: 计划
 */
@RestController
@RequestMapping(value = "/plan")
public class PlanController {

    @Autowired
    private HttpSession session;

    @Autowired
    private PlanServiceImpl planService;

    /*
     *  老师发布计划
     */
    @RequestMapping(value = "/releasePlan")
    public Result releasePlan(Plan plan){
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        Result vo = new Result();
        if (!isLogin("teacher")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        plan.setTeacherId(teacher.getId());
        plan.setId(id);
        plan.setCreateby(teacher.getId());
        plan.setCreatetime(new Date());
        int result = planService.insertPlan(plan);
        if (result == 0){
            return ResultUtil.error(-1,"计划失败");
        }
        return ResultUtil.success();
    }

    /*
     *  老师查看自己发布的计划
     */
    @RequestMapping(value = "/teacherList")
    public Result teacherList(@RequestParam(value = "pageNum",defaultValue = "1")Integer  pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "10")Integer  pageSize){
        Result vo = new Result();
        if (!isLogin("teacher")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        PageInfo<Plan> planPageInfo = planService.findList(pageNum, pageSize, teacher.getId());
        if (planPageInfo == null){
            ResultUtil.error(-1,"还没有发布计划！");
        }
        return ResultUtil.success(planPageInfo);
    }
    /*
     *  学生查询计划列表
     */
    @RequestMapping(value = "/studentList")
    public Result studentList(@RequestParam(value = "pageNum",defaultValue = "1")Integer  pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10")Integer  pageSize){
        if (!isLogin("student")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        Student student = (Student) session.getAttribute("student");
        PageInfo<Plan> planPageInfo = planService.findByStudentId(pageNum,pageSize,student.getId());
        if (planPageInfo == null){
            ResultUtil.error(-1,"还没有发布计划！");
        }
        return ResultUtil.success(planPageInfo);
    }

    /*
     *  根据id查看计划
     */
    @RequestMapping(value = "/findById")
    public Result findById(String id){
        if ((isLogin("student")) || (isLogin("teacher"))){
            Plan plan = planService.findById(id);
            if (plan == null){
                return ResultUtil.error(-1,"查询为空，出现未知错误");
            }
            return ResultUtil.success(plan);
        }
        return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");

    }


    /*
     *  判断用户是否登陆
     */
    public boolean isLogin(String user){
        if (session.getAttribute(user) != null ){
            return true;
        }else {
            return false;
        }
    }
}
