package org.plum.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.plum.dao.advice.AdviceBranchMapper;
import org.plum.dao.advice.AdviceCommentMapper;
import org.plum.dao.advice.AdviceMapper;
import org.plum.dao.advice.AdviceVoteMapper;
import org.plum.dao.advice.SubjectMapper;
import org.plum.initial.PlumCache;
import org.plum.model.advice.Advice;
import org.plum.model.advice.AdviceBranch;
import org.plum.model.advice.AdviceComment;
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
	private  AdviceBranchMapper adviceBranchMapper;

	@Autowired
	private  AdviceCommentMapper adviceCommentMapper;

	@Autowired
	private AdviceVoteMapper adviceVoteMapper;
	
	@Autowired
	private SubjectMapper subjectMapper;
	
	
	/*
	 * 专题
	 */

	public List<Advice> selectPublicTopic(Map<String,String[]> params){
		String keyword  = RequestUtils.getQueryParm(params, String.class, "keyword");
		Integer subject  = RequestUtils.getQueryParm(params, Integer.class, "subject");
		try {
			Map<String,Object>map = new LinkedHashMap<String,Object>();
			map.put("subject", subject);
			return adviceMapper.selectTopicByParamsWithPagination(map, 
					keyword !=null ? URLDecoder.decode(keyword,"utf-8").trim():"");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	public List<Advice> selectTopic(String username,Map<String,String[]> params){
		String keyword  = RequestUtils.getQueryParm(params, String.class, "keyword");

		Integer subject  = RequestUtils.getQueryParm(params, Integer.class, "subject");
		try {
			Map<String,Object>map = new LinkedHashMap<String,Object>();
			map.put("subject", subject);
			map.put("crtusr", username);
			return adviceMapper.selectTopicByParamsWithPagination(map,
					keyword !=null ? URLDecoder.decode(keyword,"utf-8").trim():"");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	/*
	 * 意见建议
	 * 
	 */
	public Advice getAdvice(int adviceid) {
		return adviceMapper.selectByPrimaryKey(adviceid);
	}

	public boolean saveOrUpdateAdvice(Advice toSave) {
		int ret = 0;
		if(toSave.getId() == null)
			ret = adviceMapper.insert(toSave);
		else
			ret = adviceMapper.updateByPrimaryKey(toSave);
		log.info("Advice {} saved", toSave.getId(), 2);
		return ret == 0 ? false : true;
		
	}

	public List<Advice> selectAdvice(String username,Map<String,String[]> params) {
		String leaddep  = RequestUtils.getQueryParm(params, String.class, "leaddep");
		String brchno  = RequestUtils.getQueryParm(params, String.class, "brchno");
		String keyword  = RequestUtils.getQueryParm(params, String.class, "keyword");
		Integer catalog  = RequestUtils.getQueryParm(params, Integer.class, "catalog");
		Integer status  = RequestUtils.getQueryParm(params, Integer.class, "status");
		
		try {
			Map<String,Object>map = new LinkedHashMap<String,Object>();
			map.put("leaddep", leaddep);
			map.put("brchno", brchno);
			map.put("catalog", catalog);
			map.put("status", status);
			if(username != null )
				map.put("crtusr", username);
			return adviceMapper.selectAdviceByParamsWithPagination(map, keyword !=null ? URLDecoder.decode(keyword,"utf-8").trim():"");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	
	public List<Advice> selectAdminAdvice(Map<String,String[]> params) {
		String leaddep  = RequestUtils.getQueryParm(params, String.class, "leaddep");
		String brchno  = RequestUtils.getQueryParm(params, String.class, "brchno");
		String keyword  = RequestUtils.getQueryParm(params, String.class, "keyword");
		Integer catalog  = RequestUtils.getQueryParm(params, Integer.class, "catalog");
		Integer status  = RequestUtils.getQueryParm(params, Integer.class, "status");
		
		try {
			Map<String,Object>map = new LinkedHashMap<String,Object>();
			map.put("leaddep", leaddep);
			map.put("brchno", brchno);
			map.put("catalog", catalog);
			map.put("status", status);
			return adviceMapper.selectAdminAdviceByParamsWithPagination(map, keyword !=null ? URLDecoder.decode(keyword,"utf-8").trim():"");
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
		try {
			Map<String,Object>map = new LinkedHashMap<String,Object>();
			map.put("leaddep", leaddep);
			map.put("brchno", brchno);
			map.put("catalog", catalog);
			return adviceMapper.selectPublicAdviceWithPagination(map, keyword !=null ? URLDecoder.decode(keyword,"utf-8").trim():"");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public int deleteAdvice(int adviceid) {
		int ret = adviceMapper.deleteByPrimaryKey(adviceid);

		log.info("Advice {} deleted, $d row(s) affected",  adviceid,ret);
		return ret;
	}
	
	
	public List<AdviceBranch> selectAdviceBranch(){
		if(PlumCache.AdviceBranches == null)
			PlumCache.AdviceBranches = adviceBranchMapper.selectAll();
		return PlumCache.AdviceBranches;
	}
	
	
	/*
	 * 投票
	 */
	 public boolean saveAdviceVote(Integer adviceId, String username, Integer type){
		
		 if(adviceVoteMapper.selectCount(adviceId, username) > 0)
			 return false;
		 else {
			AdviceVote vote = new AdviceVote();
			vote.setAdviceId(adviceId);
			vote.setVoter(username);
			vote.setVttime(Calendar.getInstance().getTime());		
			int ret = adviceVoteMapper.insert(vote);
			 adviceMapper.updatePolls(type,adviceId);
			return true;
		 }
	}
	 
	
	/*
	 * 意见反馈
	 */	
	public boolean saveOrUpdateComment(AdviceComment coment) {
		int ret = 0;
		if(coment.getId() == null) {
			ret = adviceCommentMapper.insert(coment);
			Advice advice = adviceMapper.selectByPrimaryKey(coment.getAdviceId());
			if(coment.getAdviceId() != null && advice!=null ){
				advice.setStatus(2);
				adviceMapper.updateByPrimaryKey(advice);
				log.info("Advice {} change Status {}", coment.getAdviceId(), 2);
			}
		}
		else {
			ret = adviceCommentMapper.updateByPrimaryKey(coment);
		}
		return ret == 0 ? false : true;
	}
	
	public List<AdviceComment> getCommentByAdvice(int adviceid) {
		return adviceCommentMapper.selectByAdvice(adviceid);
	} 
	

	public  AdviceComment getComment(int commentId) {
		return adviceCommentMapper.selectByPrimaryKey(commentId);
	} 
	
	

	public int deleteAdviceComment(int id) {
		int ret = adviceCommentMapper.deleteByPrimaryKey(id);
		return ret;
	}
}
