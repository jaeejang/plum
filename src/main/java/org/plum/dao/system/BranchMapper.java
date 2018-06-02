package org.plum.dao.system;

import java.util.List;

import org.plum.model.system.Branch;

public interface BranchMapper {
	List<Branch> selectAll();
	
	List<Branch> selectChildren(String brchno);
	
	List<Branch> selectRoot();
}