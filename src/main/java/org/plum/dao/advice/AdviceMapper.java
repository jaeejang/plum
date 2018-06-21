package org.plum.dao.advice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.plum.model.advice.Advice;

public interface AdviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advice record);

    List<Advice> selectPublicAdviceWithPagination(@Param("params")Map<String,Object> params,@Param("keyword")String keyword);
    
    List<Advice> selectAdviceByParamsWithPagination(@Param("params")Map<String,Object> params,@Param("keyword")String keyword);

    List<Advice> selectAdminAdviceByParamsWithPagination(@Param("params")Map<String,Object> params,@Param("keyword")String keyword);

    Advice selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Advice record);
    
    
    List<Advice> selectTopicByParamsWithPagination(@Param("params")Map<String,Object> params,@Param("keyword")String keyword);
    

    int updatePolls(@Param("type")Integer type , @Param("id")Integer id); 
}