package org.plum.dao.advice;

import org.plum.model.advice.AdviceVote;

public interface AdviceVoteMapper {
    int insert(AdviceVote record);
    
    int selectCount(Integer advice_id);
}