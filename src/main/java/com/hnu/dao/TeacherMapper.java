package com.hnu.dao;

import com.hnu.model.Clazz;
import com.hnu.model.Student;
import com.hnu.model.Teacher;
import java.util.List;

import com.hnu.model.TeacherClass;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {

    /*
     *  用户登录
     */
    Teacher login(String number);

    /*
     *  添加学生
     */
    int insertByStudent(Student student);

    /*
     *  删除学生
     */
    int delectByStudentId(@Param("id")String id);

    /*
     *  管理员添加班级
     */
    int insertClass(Clazz clazz);

    /*
     *  删除班级
     */
    int deleteByClassId(@Param("id") String classId);


    /*
     *  管理员添加教师
     */
    int insertTeacher(Teacher teacher);

    /*
     *  删除老师
     */
    int deleteByTeacherId(@Param("id") String id);

    /*
     *  为教师分配班级
     */
    int allotByTeacherClass(TeacherClass teacherClass);

    /*
     *  修改教师信息
     */
    int alterTeacher(Teacher teacher);

    /*
     *  修改学生信息
     */
    int alertStudent(Student student);
    /*
     *  修改班级信息
     */
    int alterClass(Clazz clazz);

    /*
     *  修改个人信息
     */
    int updateInfo(Teacher teacher);

    /*
     *  查询学生信息
     */
    List<Student> studentList(String flag);
    /*
     *  查询教师信息
     */
    List<Teacher> teacherList();
    /*
     *  查询所有班级
     */
    List<Clazz> classList();


    /*
     *  查询班级信息  根据id
     */
    Clazz findByClassId(@Param("id") String classId);

    /*
     *  查询学生信息  根据id
     */
    Student findByStudent(@Param("id")String studentId);


    /*
     *  查询老师信息  根据id
     */
    Teacher findByTeacher(@Param("id")String teacherId);



    /*
     *  查询老师个数
     */
    int teacherNum();

    int studentNum();

    int classNum();
}