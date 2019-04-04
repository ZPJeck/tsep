package com.hnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnu.Enum.Comment;
import com.hnu.Enum.ResultEnum;
import com.hnu.model.Clazz;
import com.hnu.model.Student;
import com.hnu.model.Teacher;
import com.hnu.service.impl.TeacherServiceImpl;
import com.hnu.util.MD5Encryption;
import com.hnu.util.Result;
import com.hnu.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
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
    @RequestMapping(value = "/userInfo",method = RequestMethod.POST)
    public Result userInfo(){
        Teacher teacher = (Teacher) session.getAttribute("admin");
        if (teacher == null){
            teacher = (Teacher) session.getAttribute("teacher");
        }
        if (teacher == null){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),ResultEnum.LOGIN_SUCCESS.getMessage());
        }
        return ResultUtil.success(teacher);
    }

    /*
     *  数据统计
     */
    @RequestMapping(value = "/countNum",method = RequestMethod.POST)
    public Result countNum(){
        if (!isLogin("admin")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),"用户未登录");
        }
        Map<String,Object> map = new HashMap<>();
        Student student = (Student) session.getAttribute("student");
        int teacherNum = teacherService.teacherNum();
        int studentNum = teacherService.studentNum();
        int classNum = teacherService.classNum();


        map.put("teacherNum",teacherNum);
        map.put("studentNum",studentNum);
        map.put("classNum",classNum);

        return ResultUtil.success(map);

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
    @RequestMapping(value = "/addClass",method = RequestMethod.POST)
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
    public Result deleteClass(String id){
        if (!isLogin("admin")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),ResultEnum.LOGIN_SUCCESS.getMessage());
        }
        int i = teacherService.deleteByClassId(id);
        if (i == 0){
            return ResultUtil.error(ResultEnum.USER_DATABASE_FAIL.getCode(),"删除班级失败");
        }
        return ResultUtil.success();
    }

    /*
     *  查看班级信息
     */
    @RequestMapping(value = "/findByClassId")
    public Result findByClassId(String id){
        Clazz clazz = teacherService.findByClassId(id);
        return ResultUtil.success(clazz);
    }
    /*
     *  修改班级信息
     */
    @RequestMapping(value = "/updateClass",method = RequestMethod.POST)
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
        Result<Clazz> clazzPageInfo = teacherService.classList(pageNum, pageSize);
        return clazzPageInfo;
    }

    /*
     *  查看学生列表
     */
    @RequestMapping(value = "/studentList")
    public Result studentList(@RequestParam(value = "pageNum",defaultValue = "1")Integer  pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10")Integer  pageSize){
        Result<Student> studentResult = teacherService.studentList(pageNum, pageSize);
        return studentResult;
    }

    /*
     *  添加学生
     */
    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
    public Result addStudent(Student student){
        if (!isLogin("admin")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),ResultEnum.LOGIN_SUCCESS.getMessage());
        }
        int i = teacherService.insertByStudent(student);
        if (i == 0){
            return ResultUtil.error(ResultEnum.USER_DATABASE_FAIL.getCode(),"添加学生失败");
        }
        return ResultUtil.success();
    }
    /*
     *  删除学生信息
     */
    @RequestMapping(value = "/deleteByStudent",method = RequestMethod.POST)
    public Result deleteByStudent(String id){
        if (!isLogin("admin")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),ResultEnum.LOGIN_SUCCESS.getMessage());
        }
        int i = teacherService.delectByStudentId(id);
        if (i == 0){
            return ResultUtil.error(ResultEnum.USER_DATABASE_FAIL.getCode(),"删除学生失败");
        }
        return ResultUtil.success();
    }
    /*
     *  根据学生id查询  数据
     */
    @RequestMapping(value = "/selectByStudentId",method = RequestMethod.POST)
    public Result selectByStudentId(String id){

        Student student = teacherService.findByStudent(id);
        if (student == null){
            return ResultUtil.error(-1,"出现未知错误，查询失败");
        }
        return ResultUtil.success(student);
    }

    /*
     *  修改学生信息。
     */
    @RequestMapping(value = "/updateStudent")
    public Result updateStudent(Student student){
        if (!isLogin("admin")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),ResultEnum.LOGIN_SUCCESS.getMessage());
        }
        int i = teacherService.alertStudent(student);
        if (i == 0){
            return ResultUtil.error(ResultEnum.USER_DATABASE_FAIL.getCode(),"修改学生失败");
        }
        return ResultUtil.success();
    }

    /*
     *  查看老师列表
     */
    @RequestMapping(value = "/teacherList")
    public Result teacherList(@RequestParam(value = "pageNum",defaultValue = "1")Integer  pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10")Integer  pageSize){
        Result<Teacher> teacherPageInfo = teacherService.teacherList(pageNum, pageSize);
        return teacherPageInfo;
    }

    /*
     *  添加老师信息
     */
    @RequestMapping(value = "/addTeacher",method = RequestMethod.POST)
    public Result addTeacher(Teacher teacher){
        if (!isLogin("admin")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),ResultEnum.LOGIN_SUCCESS.getMessage());
        }
        int i = teacherService.insertByTeacher(teacher);
        if (i == 0){
            return ResultUtil.error(ResultEnum.USER_DATABASE_FAIL.getCode(),"添加学生失败");
        }
        return ResultUtil.success();
    }

    /*
     *  删除老师信息
     */
    @RequestMapping(value = "/deleteByTeacher")
    public Result deleteByTeacher(String id){
        if (!isLogin("admin")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),ResultEnum.LOGIN_SUCCESS.getMessage());
        }
        int i = teacherService.delectByTeacherId(id);
        if (i == 0){
            return ResultUtil.error(ResultEnum.USER_DATABASE_FAIL.getCode(),"添加学生失败");
        }
        return ResultUtil.success();
    }
    /*
     *  查看老师信息
     */
    @RequestMapping(value = "/selectByTeacherId")
    public Result selectByTeacherId(String id){

        Teacher teacher = teacherService.findByTeacher(id);
        if (teacher == null){
            return ResultUtil.error(-1,"出现未知错误，查询失败");
        }
        return ResultUtil.success(teacher);
    }
    /*
     *  修改老师信息
     */
    @RequestMapping(value = "/updateTeacher")
    public Result updateTeacher(Teacher teacher){
        if (!isLogin("admin")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),ResultEnum.LOGIN_SUCCESS.getMessage());
        }
        int i = teacherService.alterTeacher(teacher);
        if (i == 0){
            return ResultUtil.error(ResultEnum.USER_DATABASE_FAIL.getCode(),"修改老师失败");
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
