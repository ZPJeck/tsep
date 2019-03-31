package com.hnu.dao;

import com.hnu.model.Plan;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlanMapper {
    int countByplan(Plan plan);

    int deleteByplan(Plan plan);

    int deleteByPrimaryKey(String id);
    int insertSelective(Plan record);

    List<Plan> selectByplan(Plan plan);



    int updateByplanSelective(@Param("record") Plan record, @Param("plan") Plan plan);

    int updateByplan(@Param("record") Plan record, @Param("plan") Plan plan);

    int updateByPrimaryKeySelective(Plan record);

    int updateByPrimaryKey(Plan record);


    int insert(Plan plan);

    List<Plan> findList(String teacherId);

    List<Plan> findByStudentId(String studentId);

    Plan selectByPrimaryKey(String id);

}