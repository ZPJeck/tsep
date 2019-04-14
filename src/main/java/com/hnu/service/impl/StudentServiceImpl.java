package com.hnu.service.impl;

import com.hnu.dao.StudentMapper;
import com.hnu.model.Interlocution;
import com.hnu.model.Student;
import com.hnu.model.StudentTask;
import com.hnu.pojo.StudentClass;
import com.hnu.service.StudentService;
import com.hnu.util.MD5Encryption;
import com.hnu.util.Result;
import com.hnu.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (student1 == null){
            return null;
        }
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


    @Override
    public int plan(String id) {
        return studentMapper.plan(id);
    }

    @Override
    public int wd(String id) {
        return studentMapper.wd(id);
    }

    @Override
    public int xd(String id) {
        return studentMapper.xd(id);
    }

    @Override
    public int task(String id) {
        return studentMapper.task(id);
    }

    @Override
    public Result dataAnalysis(String id) {
        int taskScore = 0;
        int xdScore = 0;
        int wdScore = 0;

        Map<String,Integer> map = new HashMap<>();
        List<StudentTask> studentTaskList = studentMapper.taskList(id);
        for (StudentTask studentTask : studentTaskList){
            if (studentTask.getScore() != null){
                taskScore += studentTask.getScore();
            }
        }
        List<Interlocution> list = studentMapper.xwdList(id);
        for (Interlocution interlocution : list){
            if (interlocution.getScore() != null){
                if ("0".equals(interlocution.getType())){
                    xdScore += interlocution.getScore();
                }else {
                    wdScore += interlocution.getScore();
                }
            }
        }
        map.put("taskScore",taskScore);
        map.put("xdScore",xdScore);
        map.put("wdScore",wdScore);

        return ResultUtil.success(map);
    }
}
