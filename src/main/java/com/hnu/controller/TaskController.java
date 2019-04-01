package com.hnu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.hnu.Enum.ResultEnum;
import com.hnu.model.Student;
import com.hnu.model.StudentTask;
import com.hnu.model.Task;
import com.hnu.model.Teacher;
import com.hnu.service.StudentTaskService;
import com.hnu.service.impl.StudentTaskServiceImpl;
import com.hnu.service.impl.TaskServiceImpl;
import com.hnu.util.Result;
import com.hnu.util.ResultUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 14:43
 * @Description:  作业
 */
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private StudentTaskServiceImpl studentTaskService;

    @Autowired
    private HttpSession session;


    /*
     *  老师发布作业
     */
    @RequestMapping(value = "/releaseTask")
    public Result releaseTask(Task task){
        if (!isLogin("teacher")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        int save = taskService.save(task);
        if (save == 0){
            return ResultUtil.error(-1,"发布作业失败");
        }
        return ResultUtil.success();
    }


    /*
     *  老师批改作业
     */
    @RequestMapping(value = "/relayTask")
    public Result relayTask(StudentTask studentTask){
        if (!isLogin("teacher")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        int i = taskService.relayTask(studentTask);
        if (i == 0){
            return ResultUtil.error(-1,"批改作业失败");
        }
        return ResultUtil.success();
    }

    /*
     *  老师查看列表
     */
    @RequestMapping(value = "/listByTeacher")
    public Result listByTeacher(@RequestParam(value = "pageNum",defaultValue = "1")Integer  pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "10")Integer  pageSize){

        PageInfo<Task> taskPageInfo = taskService.selectBystudent(pageNum, pageSize, ((Teacher) session.getAttribute("teacher")).getId());
        return ResultUtil.success(taskPageInfo);
    }


    /*
     *  查看作业列表(学生)
     */
    @RequestMapping(value = "/listByStudent")
    public Result listByStudent(@RequestParam(value = "pageNum",defaultValue = "1")Integer  pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "10")Integer  pageSize){

        PageInfo<Task> taskPageInfo = taskService.selectBystudent(pageNum, pageSize, ((Student) session.getAttribute("student")).getId());
        return ResultUtil.success(taskPageInfo);
    }
    /*
     *  学生查看作业  根据  学生 作业id
     */
    @RequestMapping(value = "/selectById")
    public Result selectById(@Param("id") String id){
        Result vo = new Result();
        if (!isLogin("student")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        Task task = taskService.selectById(id);
        if (task == null){
            vo.setCode(2);
            vo.setMsg("老师还未发布作业！");
            return vo;
        }
        return ResultUtil.success(task);
    }

    /*
     *  学生保存作业
     */

    public Result saveTask(StudentTask studentTask){
        Result vo = new Result();
        if (!isLogin("student")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }

        int save = studentTaskService.save(studentTask);
        if (save == 0){
            return ResultUtil.error(2,"出现未知错误，保存失败");
        }
        return ResultUtil.success();
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
