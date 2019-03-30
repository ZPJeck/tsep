package com.hnu.dao;

import com.hnu.model.Clazz;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClazzMapper {
    int countByclazz(Clazz clazz);

    int deleteByclazz(Clazz clazz);

    int deleteByPrimaryKey(String id);

    int insert(Clazz record);

    int insertSelective(Clazz record);

    List<Clazz> selectByclazz(Clazz clazz);

    Clazz selectByPrimaryKey(String id);

    int updateByclazzSelective(@Param("record") Clazz record);

    int updateByclazz(@Param("record") Clazz record, @Param("clazz") Clazz clazz);

    int updateByPrimaryKeySelective(Clazz record);

    int updateByPrimaryKey(Clazz record);
}