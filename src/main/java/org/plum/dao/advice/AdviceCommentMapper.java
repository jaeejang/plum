package org.plum.dao.advice;

import java.util.List;

import org.plum.model.advice.AdviceComment;

public interface AdviceCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdviceComment record);
    
    AdviceComment selectByPrimaryKey(Integer id);
    
    List<AdviceComment> selectByAdvice(Integer  advice_id);

    int updateByPrimaryKey(AdviceComment record);
}