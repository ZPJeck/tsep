package com.hnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnu.Enum.Comment;
import com.hnu.Enum.ResultEnum;
import com.hnu.model.Clazz;
import com.hnu.model.Teacher;
import com.hnu.service.impl.TeacherServiceImpl;
import com.hnu.util.MD5Encryption;
import com.hnu.util.Result;
import com.hnu.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Auther: Zpjeck
 * @Date: 2019/4/1 17:27
 * @Description:  教师端
 */
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/login")
    public Result login(String number ,String password,String status){
        Teacher teacher = teacherService.login(number);
        password = MD5Encryption.getMD5String(password);
        if (!password.equals(teacher.getPassword())){
            return ResultUtil.error(2,"用户名或密码错误");
        }
        if (status.equals("0")){
            session.setAttribute("admin",teacher);
        }else {
            session.setAttribute("teacher",teacher);
        }
        return ResultUtil.success(teacher);
    }

    /*
     *  用户注销
     */
    @RequestMapping(value = "/logout")
    public Result logout(){
        session.removeAttribute("teacher");
        session.removeAttribute("admin");
        return ResultUtil.success();
    }
    /*
     *  查看个人信息
     */
    @RequestMapping(value = "/userInfo")
    public Result userInfo(){
        Teacher teacher = (Teacher) session.getAttribute("admin");
        if (teacher == null){
            teacher = (Teacher) session.getAttribute("teacher");
        }
        return ResultUtil.success(teacher);
    }
    /*
     *  修改个人信息
     */
    @RequestMapping(value = "/updateInfo")
    public Result updateInfo(Teacher teacher){
        int i = teacherService.updateInfo(teacher);
        if (i == 0){
            return ResultUtil.error(ResultEnum.USER_DATABASE_FAIL.getCode(),"修改信息失败");
        }
        return ResultUtil.success();
    }

    /*
     *  添加班级
     */
    @RequestMapping(value = "/addClass")
    public Result addClass(Clazz clazz){
        if (!isLogin("admin")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),ResultEnum.LOGIN_SUCCESS.getMessage());
        }
        int i = teacherService.insertClass(clazz);
        if (i == 0){
            return ResultUtil.error(ResultEnum.USER_DATABASE_FAIL.getCode(),"添加班级失败");
        }
        return ResultUtil.success();
    }

    /*
     *  删除班级
     */
    @RequestMapping(value = "/deleteClass")
    public Result deleteClass(String classId){
        if (!isLogin("admin")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),ResultEnum.LOGIN_SUCCESS.getMessage());
        }
        int i = teacherService.deleteByClassId(classId);
        if (i == 0){
            return ResultUtil.error(ResultEnum.USER_DATABASE_FAIL.getCode(),"删除班级失败");
        }
        return ResultUtil.success();
    }

    /*
     *  查看班级信息
     */
    @RequestMapping(value = "/findByClassId")
    public Result findByClassId(String classId){
        Clazz clazz = teacherService.findByClassId(classId);
        return ResultUtil.success(clazz);
    }
    /*
     *  修改班级信息
     */
    @RequestMapping(value = "/updateClass")
    public Result updateClass(Clazz clazz){
        if (!isLogin("admin")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),ResultEnum.LOGIN_SUCCESS.getMessage());
        }
        int i = teacherService.alterClass(clazz);
        if (i == 0){
            return ResultUtil.error(ResultEnum.USER_DATABASE_FAIL.getCode(),"修改班级失败");
        }
        return ResultUtil.success();
    }

    /*
     *  查看班级列表
     */
    @RequestMapping(value = "/clazzList")
    public Result clazzList(@RequestParam(value = "pageNum",defaultValue = "1")Integer  pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "10")Integer  pageSize){
        PageInfo<Clazz> clazzPageInfo = teacherService.classList(pageNum, pageSize);
        return ResultUtil.success(clazzPageInfo);
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
