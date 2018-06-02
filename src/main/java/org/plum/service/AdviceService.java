package org.plum.service;

import java.util.Date;
import java.util.List;

import org.plum.dao.advice.AdviceBranchMapper;
import org.plum.dao.advice.AdviceMapper;
import org.plum.dao.advice.AdviceVoteMapper;
import org.plum.initial.PlumCache;
import org.plum.model.advice.Advice;
import org.plum.model.advice.AdviceBranch;
import org.plum.model.advice.AdviceVote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdviceService {
	
	private static Logger log = LoggerFactory.getLogger(AdviceService.class);

	@Autowired
	private AdviceMapper adviceMapper;

	@Autowired
	private AdviceBranchMapper adviceBranchMapper;

	@Autowired
	private AdviceVoteMapper adviceVoteMapper;

	public Advice getAdvice(int adviceid) {
		return adviceMapper.selectByPrimaryKey(adviceid);
	}

	public boolean saveOrUpdateAdvice(Advice toSave) {
		int ret = 0;
		if(toSave.getId() == null)
			ret = adviceMapper.insert(toSave);
		else
			ret = adviceMapper.updateByPrimaryKey(toSave);
		if(ret > 0)
			PlumCache.CacheFuncs.clear();
		return ret == 0 ? false : true;
		
	}

	public List<Advice> selectAdvice(String username,String search) {
		return adviceMapper.selectByExampleWithPagination(username,search);
	}

	public int deleteAdvice(int adviceid) {
		int ret = adviceMapper.deleteByPrimaryKey(adviceid);
		if (ret > 0)
			PlumCache.CacheFuncs.clear();
		return ret;
	}
	
	
	public List<AdviceBranch> selectAdviceBranch(){
		return adviceBranchMapper.selectAll();
	}
	
	public boolean saveAdviceVote(Integer adviceId, String username){
		
		AdviceVote vote = new AdviceVote();
		vote.setAdviceId(adviceId);
		vote.setVoter(username);
		vote.setVttime(new Date());		
		int ret = adviceVoteMapper.insert(vote);
		if(ret > 0){
			 ret = adviceMapper.updatePolls(adviceId);
			 return ret > 0 ? true: false;
		}
		return false;
		
	}
}
