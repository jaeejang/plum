package org.plum.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.plum.dao.advice.AdviceBranchMapper;
import org.plum.dao.advice.AdviceCommentMapper;
import org.plum.dao.advice.AdviceMapper;
import org.plum.initial.PlumCache;
import org.plum.model.advice.Advice;
import org.plum.model.advice.AdviceBranch;
import org.plum.model.advice.AdviceComment;
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
	private  AdviceBranchMapper adviceBranchMapper;
	

	@Autowired
	private  AdviceCommentMapper adviceCommentMapper;

	//@Autowired
	//private AdviceVoteMapper adviceVoteMapper;
	

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

	public List<Advice> selectAdvice(String username,Map<String,String[]> params) {
		String leaddep  = RequestUtils.getQueryParm(params, String.class, "leaddep");
		String brchno  = RequestUtils.getQueryParm(params, String.class, "brchno");
		String keyword  = RequestUtils.getQueryParm(params, String.class, "keyword");
		Integer catalog  = RequestUtils.getQueryParm(params, Integer.class, "catalog");
		Integer status  = RequestUtils.getQueryParm(params, Integer.class, "status");
		
		try {
			return adviceMapper.selectByExampleWithPagination(catalog, leaddep, status, brchno, username, keyword !=null ? URLDecoder.decode(keyword,"utf-8").trim():"");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	//
	public List<Advice> selectPublicAdvice(Map<String,String[]> params){
		String leaddep  = RequestUtils.getQueryParm(params, String.class, "leaddep");
		String brchno  = RequestUtils.getQueryParm(params, String.class, "brchno");
		String keyword  = RequestUtils.getQueryParm(params, String.class, "keyword");
		Integer catalog  = RequestUtils.getQueryParm(params, Integer.class, "catalog");
		//Integer status  = RequestUtils.getQueryParm(params, Integer.class, "status");
		try {
			Map<String,Object>map = new LinkedHashMap<String,Object>();
			map.put("leaddep", leaddep);
			map.put("brchno", brchno);
			map.put("catalog", catalog);
			map.put("status", 1);
			return adviceMapper.selectByParamsWithPagination(map, keyword !=null ? URLDecoder.decode(keyword,"utf-8").trim():"");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public int deleteAdvice(int adviceid) {
		int ret = adviceMapper.deleteByPrimaryKey(adviceid);
		if (ret > 0)
			PlumCache.CacheFuncs.clear();
		return ret;
	}
	
	
	public List<AdviceBranch> selectAdviceBranch(){
		if(PlumCache.AdviceBranches == null)
			PlumCache.AdviceBranches = adviceBranchMapper.selectAll();
		return PlumCache.AdviceBranches;
	}
	
	/*
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
	*/
	
	/*
	 * 意见反馈
	 */	
	public boolean saveOrUpdateComment(AdviceComment coment) {
		int ret = 0;
		if(coment.getId() == null)
			ret = adviceCommentMapper.insert(coment);
		else
			ret = adviceCommentMapper.updateByPrimaryKey(coment);
		if(ret > 0)
			PlumCache.CacheFuncs.clear();
		return ret == 0 ? false : true;
	}
	
	public List<AdviceComment> getCommentByAdvice(int adviceid) {
		return adviceCommentMapper.selectByAdvice(adviceid);
	} 
	
	

	public int deleteAdviceComment(int id) {
		int ret = adviceCommentMapper.deleteByPrimaryKey(id);
		return ret;
	}
}
