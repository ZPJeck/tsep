package com.hnu.dao;

import com.hnu.model.Plan;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlanMapper {
    int countByplan(Plan plan);

    int deleteByplan(Plan plan);

    int deleteByPrimaryKey(String id);

    int insert(Plan record);

    int insertSelective(Plan record);

    List<Plan> selectByplan(Plan plan);

    Plan selectByPrimaryKey(String id);

    int updateByplanSelective(@Param("record") Plan record, @Param("plan") Plan plan);

    int updateByplan(@Param("record") Plan record, @Param("plan") Plan plan);

    int updateByPrimaryKeySelective(Plan record);

    int updateByPrimaryKey(Plan record);
}