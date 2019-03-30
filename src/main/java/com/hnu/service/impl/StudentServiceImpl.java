package com.hnu.service.impl;

import com.hnu.model.Student;
import com.hnu.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/30 13:08
 * @Description:
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public int countBystudent(Student student) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(Student student) {
        return 0;
    }

    @Override
    public List<Student> selectBystudent(Student student) {
        return null;
    }

    @Override
    public Student selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Student student) {
        return 0;
    }

    @Override
    public Student login(Student student) {
        return null;
    }
}
