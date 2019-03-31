package com.hnu.service.impl;

import com.hnu.dao.StudentTaskMapper;
import com.hnu.model.StudentTask;
import com.hnu.service.StudentTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 16:57
 * @Description:
 */
@Service
public class StudentTaskServiceImpl implements StudentTaskService {
    @Autowired
    private StudentTaskMapper studentTaskMapper;
    @Override
    public int save(StudentTask studentTask) {
        if (studentTask == null){
            return 0;
        }
        return studentTaskMapper.save(studentTask);
    }
}
