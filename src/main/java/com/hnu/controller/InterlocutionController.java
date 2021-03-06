package com.hnu.controller;

import com.github.pagehelper.PageInfo;
import com.hnu.Enum.ResultEnum;
import com.hnu.model.Interlocution;
import com.hnu.model.Student;
import com.hnu.model.Teacher;
import com.hnu.service.InterlocutionService;
import com.hnu.service.impl.InterlocutionServiceImpl;
import com.hnu.util.Result;
import com.hnu.util.ResultUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: Zpjeck
 * @Date: 2019/4/1 08:59
 * @Description:  心得与问答
 */
@RestController
@RequestMapping(value = "/wd")
public class InterlocutionController {

    @Autowired
    private HttpSession session;

    @Autowired
    private InterlocutionServiceImpl interlocutionService;

    /*
     *  学生添加问答或者心得
     */
    @RequestMapping(value = "/add")
    public Result add(Interlocution interlocution){
        if (!isLogin("student")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        Student student = (Student) session.getAttribute("student");
        String id = UUID.randomUUID().toString().replaceAll("-","");
        interlocution.setId(id);
        interlocution.setClassId(student.getClassId());
        interlocution.setStudentId(student.getId());
        interlocution.setCreateby(student.getId());
        interlocution.setCreatetime(new Date());
        int result = interlocutionService.add(interlocution);
        if (result == 0){
            return ResultUtil.error(-1,"添加信息失败");
        }
        return ResultUtil.success();
    }

    /*
     *  根据id查询信息
     */
    @RequestMapping(value = "/findById")
    public Result findById(String id){
        if (id == null){
            return ResultUtil.error(-1,"查询信息失败");
        }
        Interlocution byId = interlocutionService.findById(id);
        if (byId == null ){
            return ResultUtil.error(-2,"老师未回复");
        }
        return ResultUtil.success(byId);
    }

    /*
     *  x学生分页查询
     */
    @RequestMapping(value = "/list")
    public Result list(@RequestParam(value = "page",defaultValue = "1")Integer  pageNum,
                       @RequestParam(value = "limit",defaultValue = "10")Integer  pageSize,
                       @RequestParam(value = "type",defaultValue = "0")String type){
        if (!isLogin("student")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        Student student =(Student) session.getAttribute("student");
        Result<Interlocution> list = interlocutionService.list(pageNum, pageSize, student.getId(),type);
        if (list == null){
            return ResultUtil.error(-2,"查询信息为空");
        }
        return list;
    }

    /*
     *  老师根据班级id查询信息
     */
    @RequestMapping(value = "/listByClass")
    public Result listByClass(@RequestParam(value = "page",defaultValue = "1")Integer  pageNum,
                       @RequestParam(value = "limit",defaultValue = "10")Integer  pageSize){
        if (!isLogin("teacher")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        Teacher teacher =(Teacher) session.getAttribute("teacher");


        Result<Interlocution> list = interlocutionService.listByClass(pageNum, pageSize, teacher.getId());
        if (list == null){
            return ResultUtil.error(-2,"查询信息为空");
        }
        return list;
    }

    /*
     *  教师端展示  心得列表。
     */
    @RequestMapping(value = "/xdList")
    public Result xdList(@RequestParam(value = "page",defaultValue = "1")Integer  pageNum,
                         @RequestParam(value = "limit",defaultValue = "10")Integer  pageSize,
                         String type,String classId){
        if (!isLogin("teacher")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        // 查询所有人，所班级的列表  默认按照  type查询所有班级的
        return interlocutionService.interLnList(pageNum,pageSize,type,classId);
    }


    @RequestMapping(value = "/findByIdTeacher")
    public Result findByIdTeacher(String id){
        if (id == null){
            return ResultUtil.error(-1,"查询信息失败");
        }
        Interlocution byId = interlocutionService.findByIdTeacher(id);

        return ResultUtil.success(byId);
    }

    /*
     *  老师回复学生信息
     */
    @RequestMapping(value = "/relayStudent")
    public Result relayStudent(Interlocution interlocution){
        if (!isLogin("teacher")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        int i = interlocutionService.replyStudent(interlocution);
        if (i == 0){
            return ResultUtil.error(-1,"回馈信息失败");
        }
        return ResultUtil.success();
    }

    /*
     *  学生删除信息
     */
    @RequestMapping(value = "/deleteById" ,method = RequestMethod.POST)
    public Result deleteById(String id){
        if (!isLogin("student")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        int i = interlocutionService.deleteById(id);
        if (i == 0){
            return ResultUtil.error(-1,"删除信息失败");
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
