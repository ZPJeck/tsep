package com.hnu.service.impl;

import com.hnu.dao.StudentMapper;
import com.hnu.model.Student;
import com.hnu.pojo.StudentClass;
import com.hnu.service.StudentService;
import com.hnu.util.MD5Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/30 13:08
 * @Description:
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

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
    public StudentClass selectByPrimaryKey(String id) {
        if (id != null && !"".equals(id)){
            StudentClass studentClass = studentMapper.selectByPrimaryKey(id);
            return studentClass;
        }
        return null;
    }

    @Override
    public int updateByPrimaryKey(Student student) {
        return studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public Student login(Student  student) {
        if ("".equals(student.getNumber())){
            return null;
        }
        Student student1 = studentMapper.login(student.getNumber());
        if (MD5Encryption.getMD5String(student.getPassword()).equals(student1.getPassword())){
            return student1;
        }else {
            return null;
        }
    }

    @Override
    public Student selectById(String id) {
        return studentMapper.selectById(id);
    }
}
