package com.hnu.controller;

import com.hnu.Enum.ResultEnum;
import com.hnu.model.Student;
import com.hnu.pojo.StudentClass;
import com.hnu.util.MD5Encryption;
import com.hnu.util.Result;
import com.hnu.service.impl.StudentServiceImpl;
import com.hnu.util.ResultUtil;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/30 13:11
 * @Description:
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private HttpSession session ;
    /*
     *  学生登录
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Result<Student> login(@Param("number") String number ,@Param("password") String password) {
        Result result = new Result();
        Student student = new Student();
        student.setNumber(number);
        student.setPassword(password);
        Student login = studentService.login(student);
        System.out.println(login);
        if (login == null){
            result.setCode(1);
            result.setMsg("用户名或密码错误，请重新登录");
        }else {
            result.setMsg("登录成功");
            result.setCode(0);
            result.setData(login);
            session.setAttribute("student",login);
        }
        return result;
    }

    /*
     *  查看用户个人信息
     */
    @RequestMapping(value = "/userInfo")
    public Result userInfo(){
        Result result = new Result();
        if (!isLogin()){
            return ResultUtil.error(1,"用户未登录");
        }
        StudentClass studentClass = studentService.selectByPrimaryKey(((Student)session.getAttribute("student")).getId());
        if (studentClass == null){
            result.setCode(2);
            result.setMsg("");
        }else {
            result.setCode(0);
            result.setData(studentClass);
        }
        return result;
    }

    /*
     *  学生修改密码
     */
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public Result updatePassword(@Param("password") String oldPassword , @Param("newPassword") String newPassword){
        Result result = new Result();
        if (!isLogin()){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        Student student = studentService.selectById(((Student)session.getAttribute("student")).getId());
        if (!student.getPassword().equals(MD5Encryption.getMD5String(oldPassword))){
            result.setCode(2);
            result.setMsg("原密码不正确");
            return result;
        }
        // 保存密码
        newPassword = MD5Encryption.getMD5String(newPassword);
        student.setPassword(newPassword);
        int update = studentService.updateByPrimaryKey(student);
        if (update == 0){
            result.setMsg("出现未知错误，密码修改失败");
            result.setCode(4);
            return result;
        }
        session.setAttribute("student",student);
        return ResultUtil.success();
    }
    /*
     *  更新学生  信息  （实现自我更新）
     */
    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    public Result updateInfo(Student student){
        if (!isLogin()){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        student.setId(((Student)session.getAttribute("student")).getId());
        int update = studentService.updateByPrimaryKey(student);
        if (update == 0){
            return ResultUtil.error(1,"修改个人信息失败");
        }
        session.setAttribute("student",student);
        return ResultUtil.success();
    }
    /*
     *   学生登出
     */
    @RequestMapping(value = "/logout")
    public Result logout(){
        session.removeAttribute("student");
        return ResultUtil.success();
    }

    /*
     *  统计个数
     */
    @RequestMapping(value = "/count",method = RequestMethod.POST)
    public Result count(){
        if (!isLogin()){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        Map<String,Object> map = new HashMap<>();
        Student student = (Student) session.getAttribute("student");
        int plan = studentService.plan(student.getId());
        int xd = studentService.xd(student.getId());
        int wd = studentService.wd(student.getId());
        int task = studentService.task(student.getId());
        map.put("plan",plan);
        map.put("xd",xd);
        map.put("wd",wd);
        map.put("task",task);
        return ResultUtil.success(map);
    }



    /*
     *  校验用户是否登录
     */
    public boolean isLogin(){
        Object attribute = session.getAttribute("student");
        if (attribute == null){
            return false;
        }
        return true;
    }
}
