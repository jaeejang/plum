package org.plum.dao.advice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.plum.model.advice.Advice;

public interface AdviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advice record);

    List<Advice> selectByExampleWithPagination(
    		@Param("catalog")Integer catalog, 
    		@Param("leaddep")String leaddep, 
    		@Param("status")Integer status, 
    		@Param("brchno")String brchno, 
    		@Param("crtusr") String crtusr,
    		@Param("keyword")String keyword);
    
    List<Advice> selectByParamsWithPagination(@Param("params")Map<String,Object> params,@Param("keyword")String keyword);

    Advice selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Advice record);
    
    int updatePolls(Integer id);
}