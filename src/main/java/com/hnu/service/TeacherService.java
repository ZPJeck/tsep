package com.hnu.service;

import com.github.pagehelper.PageInfo;
import com.hnu.model.Clazz;
import com.hnu.model.Student;
import com.hnu.model.Teacher;
import com.hnu.model.TeacherClass;
import com.hnu.util.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface TeacherService {

    /*
     *  用户登录
     */
    Teacher login(String number);

    /*
     *  添加老师信息
     */
    int insertByTeacher(Teacher teacher);
    /*
     *  添加学生
     */
    int insertByStudent(Student student);

    /*
     *  删除学生
     */
    int delectByStudentId(String studentId);

    /*
     *  删除老·1师
     */
    int delectByTeacherId(String teacherId);
    /*
     *  管理员添加班级
     */
    int insertClass(Clazz clazz);

    /*
     *  删除班级
     */
    int deleteByClassId(String classId);

    /*
     *  为教师分配班级
     */
    int allotByTeacherClass(TeacherClass teacherClass);


    /*
     *  修改教师信息
     */
    int alterTeacher(Teacher teacher);

    /*
     *  修改班级信息
     */
    int alterClass(Clazz clazz);
    /*
     *  修改学生信息
     */
    int alertStudent(Student student);

    /*
     *  修改个人信息
     */
    int updateInfo(Teacher teacher);

    /*
     *  查询学生信息
     */
    Result<Student> studentList(Integer  pageNum,Integer  pageSize);
    /*
     *  查询教师信息
     */
    Result<Teacher> teacherList(Integer  pageNum,Integer  pageSize);
    /*
     *  查询所有班级
     */
    Result<Clazz> classList(Integer  pageNum,Integer  pageSize);

    Result<Clazz> classList2(Integer  pageNum,Integer  pageSize);

    /*
     *  查询班级信息  根据id
     */
    Clazz findByClassId(String classId);

    /*
     *  查询学生信息  根据id
     */
    Student findByStudent(String studentId);


    /*
     *  查询班级信息  根据id
     */
    Teacher findByTeacher(String teacherId);

    Result<Teacher> list();

    /*
     *  查询老师个数
     */
    int teacherNum();

    int studentNum();

    int classNum();

    /*
     *  根据教师id 查询个人信息
     */
    Teacher findById(String id);

}
