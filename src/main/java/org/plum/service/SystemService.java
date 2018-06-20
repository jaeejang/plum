package org.plum.service;

import java.util.List;

import org.plum.dao.advice.SubjectMapper;
import org.plum.dao.system.BranchMapper;
import org.plum.dao.system.DictMapper;
import org.plum.initial.PlumCache;
import org.plum.model.advice.Subject;
import org.plum.model.system.Branch;
import org.plum.model.system.Dict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemService {

	private static Logger log = LoggerFactory.getLogger(SystemService.class);
	
	@Autowired
	DictMapper dictMapper;
	@Autowired
	BranchMapper branchMapper;
	@Autowired
	private SubjectMapper subjectMapper;
	
	public List<Dict> loadDicts(){
		return dictMapper.selectAll();
	}
	
	public List<Branch> loadBranch(){
		if(PlumCache.CacheBranch == null){
			List<Branch> ups = branchMapper.selectRoot();
			for (Branch branch : ups) {
				branch.setChildren(branchMapper.selectChildren(branch.getBrchno()));
			}
			PlumCache.CacheBranch = ups;
		}
		return PlumCache.CacheBranch;
	}
	
	
	
	public List<Subject> selectSubject(boolean full){
		return subjectMapper.select(full);
	}

	public Subject getSubject(int id) {
		return subjectMapper.selectByPrimaryKey(id);
	}

	public boolean saveOrUpdateSubject(Subject toSave) {
		int ret = 0;
		if(toSave.getId() == null)
			ret = subjectMapper.insert(toSave);
		else
			ret = subjectMapper.updateByPrimaryKey(toSave);
		return ret == 0 ? false : true;
	}
	
	

	public int deleteSubject(int id) {
		int ret = subjectMapper.delete(id);
		return ret;
	}
}
