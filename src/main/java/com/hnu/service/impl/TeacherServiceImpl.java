package com.hnu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnu.dao.ClazzMapper;
import com.hnu.dao.TeacherClassMapper;
import com.hnu.dao.TeacherMapper;
import com.hnu.model.Clazz;
import com.hnu.model.Student;
import com.hnu.model.Teacher;
import com.hnu.model.TeacherClass;
import com.hnu.service.TeacherService;
import com.hnu.util.MD5Encryption;
import com.hnu.util.Result;
import com.hnu.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: Zpjeck
 * @Date: 2019/4/1 17:36
 * @Description:
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private HttpSession session;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private TeacherClassMapper teacherClassMapper;

    @Override
    public Teacher login(String number) {
        return teacherMapper.login(number);
    }

    @Override
    public int insertByStudent(Student student) {
        Teacher teacher = (Teacher) session.getAttribute("admin");
        String id = UUID.randomUUID().toString().replaceAll("-","");
        student.setId(id);
        student.setPassword(MD5Encryption.getMD5String("123456"));
        student.setCreateby(teacher.getId());
        student.setCreatetime(new Date());
        return teacherMapper.insertByStudent(student);
    }

    @Override
    public int delectByStudentId(String studentId) {
        return teacherMapper.delectByStudentId(studentId);
    }

    @Override
    public int insertClass(Clazz clazz) {
        Teacher teacher = (Teacher)session.getAttribute("admin");
        String id = UUID.randomUUID().toString().replaceAll("-","");
        clazz.setId(id);
        clazz.setCreateby(teacher.getId());
        clazz.setCreatetime(new Date());
        return teacherMapper.insertClass(clazz);
    }

    @Override
    public int deleteByClassId(String classId) {
        return teacherMapper.deleteByClassId(classId);
    }

    @Override
    public int allotByTeacherClass(TeacherClass teacherClass) {
        Teacher teacher1 = (Teacher) session.getAttribute("admin");
        String id = UUID.randomUUID().toString().replaceAll("-","");
        teacherClass.setId(id);
        teacherClass.setCreateby(teacher1.getId());
        teacherClass.setCreatetime(new Date());
        return teacherMapper.allotByTeacherClass(teacherClass);
    }

    @Override
    public int alterTeacher(Teacher teacher) {
        return teacherMapper.alterTeacher(teacher);
    }

    @Override
    public int alertStudent(Student student) {
        return teacherMapper.alertStudent(student);
    }

    @Override
    public int updateInfo(Teacher teacher) {
        return teacherMapper.updateInfo(teacher);
    }

    @Override
    public Result<Student> studentList(Integer  pageNum,Integer  pageSize) {
        Page page = PageHelper.startPage(pageNum,pageSize);
        List<Student> studentList = teacherMapper.studentList("1");
        for (Student student : studentList){
            if (student.getSex().equals("0")){
                student.setSex("女");
            }else {
                student.setSex("男");
            }
            Clazz clazz = clazzMapper.selectByPrimaryKey(student.getClassId());
            student.setClassId(clazz.getClassName());
        }
        return ResultUtil.success(studentList,studentList.size());
    }

    @Override
    public Result<Teacher> teacherList(Integer  pageNum,Integer  pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Teacher> teachers = teacherMapper.teacherList();
        for (Teacher t : teachers){
            if(t.getSex().equals("1")){
                t.setSex("男");
            }else {
                t.setSex("女");
            }
            if (t.getStatus().equals("0")){
                t.setStatus("管理员");
            }else {
                t.setStatus("教师");
            }
        }
        return ResultUtil.success(teachers,teachers.size());
    }

    @Override
    public Result<Clazz> classList(Integer  pageNum,Integer  pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Clazz> clazzes = teacherMapper.classList();
        for (Clazz clazz : clazzes){
            Teacher teacher = teacherClassMapper.findByClassId(clazz.getId());
            if (teacher != null){
                clazz.setCreateby(teacher.getName());
            }else {
                clazz.setCreateby("<p style='color:red'>未分配指导老师</p>");
            }

        }
        return ResultUtil.success(clazzes,clazzes.size());
    }

    @Override
    public Result<Clazz> classList2(Integer  pageNum,Integer  pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Clazz> clazzes = teacherMapper.classList();
        List<Clazz> clazzList = new ArrayList<>();
        for (Clazz clazz :clazzes){
            List<TeacherClass> exitTeacherClass = teacherMapper.isExitTeacherClass(clazz.getId());
            if (exitTeacherClass.size() == 0){
                clazzList.add(clazz);
            }
        }
        return ResultUtil.success(clazzList,clazzList.size());
    }


    @Override
    public Clazz findByClassId(String classId) {

        Clazz clazz = teacherMapper.findByClassId(classId);
        Teacher teacher = teacherClassMapper.findByClassId(clazz.getId());
        if (teacher != null){
            clazz.setCreateby(teacher.getName());
        }else {
            clazz.setCreateby("no");
        }
        return clazz;
    }

    @Override
    public Student findByStudent(String studentId) {
        Student student = teacherMapper.findByStudent(studentId);
        // 根据班级id选择班级
        Clazz clazz = teacherMapper.findByClassId(student.getClassId());
        student.setClassId(clazz.getClassName());
        return student;
    }

    @Override
    public Teacher findByTeacher(String teacherId) {
        return teacherMapper.findByTeacher(teacherId);
    }

    @Override
    public int alterClass(Clazz clazz) {
        Teacher teacher = (Teacher)session.getAttribute("admin");
        clazz.setUpdateby(teacher.getId());
        clazz.setUpdatetime(new Date());
        return teacherMapper.alterClass(clazz);
    }

    @Override
    public int insertByTeacher(Teacher teacher) {
        Teacher teacher1 = (Teacher) session.getAttribute("admin");
        String id = UUID.randomUUID().toString().replaceAll("-","");
        teacher.setId(id);
        teacher.setPassword(MD5Encryption.getMD5String("123456"));
        teacher.setCreateby(teacher1.getId());
        teacher.setCreatetime(new Date());
        return teacherMapper.insertTeacher(teacher);
    }

    @Override
    public int delectByTeacherId(String teacherId) {
        return teacherMapper.deleteByTeacherId(teacherId);
    }


    @Override
    public int teacherNum() {
        return teacherMapper.teacherNum();
    }

    @Override
    public int studentNum() {
        return teacherMapper.studentNum();
    }

    @Override
    public int classNum() {
        return teacherMapper.classNum();
    }

    @Override
    public Result<Teacher> list() {

        List<Teacher> teacherList = teacherMapper.list();
        return ResultUtil.success(teacherList);
    }
}
