package com.hnu.dao;

import com.hnu.model.Interlocution;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InterlocutionMapper {
    int countByinterlocution(Interlocution interlocution);

    int deleteByinterlocution(Interlocution interlocution);

    int deleteByPrimaryKey(String id);

    int insert(Interlocution record);

    int insertSelective(Interlocution record);

    List<Interlocution> selectByinterlocution(Interlocution interlocution);

    Interlocution selectByPrimaryKey(String id);

    int updateByinterlocutionSelective(@Param("record") Interlocution record, @Param("interlocution") Interlocution interlocution);

    int updateByinterlocution(@Param("record") Interlocution record, @Param("interlocution") Interlocution interlocution);

    int updateByPrimaryKeySelective(Interlocution record);

    int updateByPrimaryKey(Interlocution record);
}