package org.plum.dao.advice;

import org.apache.ibatis.annotations.Param;
import org.plum.model.advice.AdviceVote;

public interface AdviceVoteMapper {
    int insert(AdviceVote record);
    

    int selectCount(@Param("advice_id")Integer advice_id,@Param("voter")String username);
    
}