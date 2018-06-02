package org.plum.dao.advice;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.plum.model.advice.Advice;

public interface AdviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advice record);

    List<Advice> selectByExampleWithPagination(@Param("voter")String voter, @Param("search")String search);

    Advice selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Advice record);
    
    int updatePolls(Integer id);
}