package com.hnu.dao;

import java.util.List;

import com.hnu.model.StudentTask;
import org.apache.ibatis.annotations.Param;

public interface StudentTaskMapper {


    // 开始

    int save(StudentTask studentTask);


    int relayTask(StudentTask studentTask);

}