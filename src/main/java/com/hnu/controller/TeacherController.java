package com.hnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnu.Enum.Comment;
import com.hnu.Enum.ResultEnum;
import com.hnu.model.Clazz;
import com.hnu.model.Student;
import com.hnu.model.Teacher;
import com.hnu.model.TeacherClass;
import com.hnu.service.impl.TeacherServiceImpl;
import com.hnu.util.DateUtil;
import com.hnu.util.MD5Encryption;
import com.hnu.util.Result;
import com.hnu.util.ResultUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
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
        if ( !status.equals(teacher.getStatus())){
            return ResultUtil.error(3,"权限不足，请重新登录");
        }
        if (status.equals("0")){
            session.setAttribute("admin",teacher);
        }else {
            session.setAttribute("teacher",teacher);
        }
        return ResultUtil.success(teacher);
    }

    /*
     *  更改密码
     */

    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public Result updatePassword( String oldPassword ,  String newPassword){
        Result result = new Result();

        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null){
            teacher = (Teacher) session.getAttribute("admin");
        }

        if (!teacher.getPassword().equals(MD5Encryption.getMD5String(oldPassword))){
            result.setCode(2);
            result.setMsg("原密码不正确");
            return result;
        }
        // 保存密码
        newPassword = MD5Encryption.getMD5String(newPassword);
        Teacher t = new Teacher();
        t.setId(teacher.getId());
        t.setPassword(newPassword);
        int update = teacherService.updateInfo(t);
        if (update == 0){
            result.setMsg("出现未知错误，密码修改失败");
            result.setCode(4);
            return result;
        }
        return ResultUtil.success();
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
        if (teacher.getStatus().equals("0")){
            teacher.setStatus("管理员");
        }else {
            teacher.setStatus("教师");
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
        teacher = teacherService.findById(teacher.getId());
        Teacher t1 = (Teacher) session.getAttribute("admin");
        if (t1 == null){
            session.setAttribute("teacher",teacher);
        }else {
            session.setAttribute("admin",teacher);
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

    @RequestMapping(value = "/clazzList2")
    public Result clazzList2(@RequestParam(value = "pageNum",defaultValue = "1")Integer  pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "10")Integer  pageSize){
        Result<Clazz> clazzPageInfo = teacherService.classList2(pageNum, pageSize);
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

    @RequestMapping(value = "/startStudent",method = RequestMethod.POST)
    public Result startStudent(String id,String updateby){
        if (!isLogin("admin")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),ResultEnum.LOGIN_SUCCESS.getMessage());
        }
        Teacher teacher = (Teacher ) session.getAttribute("admin");
        Student student = new Student();
        student.setId(id);
        if (updateby.equals("1")){
            updateby = "0";
        }else {
            updateby = "1";
        }
        student.setUpdateby(updateby);
        student.setUpdatetime(DateUtil.date());
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
     *  查看老师列表
     */
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public Result list(){
        return teacherService.list();
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


    @RequestMapping(value = "/allotByTeacherClass" , method = RequestMethod.POST)
    public Result allotByTeacherClass(TeacherClass teacherClass){
        if (!isLogin("admin")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),ResultEnum.LOGIN_SUCCESS.getMessage());
        }
        int i = teacherService.allotByTeacherClass(teacherClass);
        if (i == 0){
            return ResultUtil.error(ResultEnum.USER_DATABASE_FAIL.getCode(),"修改老师失败");
        }
        return ResultUtil.success();
    }


    /*
     *  教师端展示首页面   数据统计
     */
    @RequestMapping(value = "/countByTeacher",method = RequestMethod.POST)
    public Result countByTeacher(){
        if (!isLogin("teacher")){
            return ResultUtil.error(ResultEnum.NO_LOGIN.getCode(),ResultEnum.LOGIN_SUCCESS.getMessage());
        }
        return teacherService.countByTeacher();
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
