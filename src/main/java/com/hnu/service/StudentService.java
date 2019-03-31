package com.hnu.service;

import com.hnu.model.Student;
import com.hnu.pojo.StudentClass;

import java.util.List;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/30 13:07
 * @Description:
 */
public interface StudentService {

    /*
     *  统计学生多少人  根据  班级id
     */
    int countBystudent(Student student);

    /*
     *  删除学生  根据学生id  逻辑删除，非物理删除
     */
    int deleteByPrimaryKey(String id);

    /*
     *  添加学生
     */
    int insert(Student student);

    /*
     *  查找学生列表，根据班级id
     */
    List<Student> selectBystudent(Student student);

    /*
     *  查找某个学生 根据学生id
     */
    StudentClass selectByPrimaryKey(String id);
    /*
     *  更新学生信息
     */
    int updateByPrimaryKey(Student student);

    /*
     *  学生登录
     */
    Student login(Student student);

    Student selectById(String id);
}
