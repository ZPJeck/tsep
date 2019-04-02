package com.hnu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnu.dao.TeacherMapper;
import com.hnu.model.Clazz;
import com.hnu.model.Student;
import com.hnu.model.Teacher;
import com.hnu.model.TeacherClass;
import com.hnu.service.TeacherService;
import com.hnu.util.MD5Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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

    @Override
    public Teacher login(String number) {
        return teacherMapper.login(number);
    }

    @Override
    public int insertByStudent(Student student) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
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
        return 0;
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
    public PageInfo<Student> studentList(Integer  pageNum,Integer  pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Student> students = teacherMapper.studentList();
        return new PageInfo<>(students);
    }

    @Override
    public PageInfo<Teacher> teacherList(Integer  pageNum,Integer  pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Teacher> teachers = teacherMapper.teacherList();
        return new PageInfo<>(teachers);
    }

    @Override
    public PageInfo<Clazz> classList(Integer  pageNum,Integer  pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Clazz> clazzes = teacherMapper.classList();
        return new PageInfo<>(clazzes);
    }

    @Override
    public Clazz findByClassId(String classId) {
        return teacherMapper.findByClassId(classId);
    }

    @Override
    public Student findByStudent(String studentId) {
        return teacherMapper.findByStudent(studentId);
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
        Teacher teacher1 = (Teacher) session.getAttribute("teacher");
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
}
