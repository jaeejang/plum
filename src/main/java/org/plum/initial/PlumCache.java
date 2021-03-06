package org.plum.initial;

import java.util.List;

import org.plum.model.advice.AdviceBranch;
import org.plum.model.system.Branch;
import org.plum.model.system.Dict;
import org.plum.model.system.Func;
import org.plum.model.system.Role;
import org.plum.tools.ui.TreeNode;
import org.springframework.stereotype.Component;

@Component  
public class PlumCache {
	public static List<Func> CacheFuncs;
	public static List<Role> CacheRoles;
	
	public static TreeNode CacheMenu;
	
	public static List<Dict> CacheDicts;
	
	public static List<Branch> CacheBranch;
	
	public static List<AdviceBranch> AdviceBranches;

	public void reset() throws Exception {
		CacheFuncs = null;
		CacheRoles= null;
		CacheMenu = null;
		CacheDicts = null;
		CacheBranch = null;
		AdviceBranches = null;
	}

}
