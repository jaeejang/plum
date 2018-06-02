package org.plum.initial;

import java.util.List;

import org.plum.model.system.Branch;
import org.plum.model.system.Dict;
import org.plum.model.system.Func;
import org.plum.tools.ui.TreeNode;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component  
public class PlumCache implements InitializingBean {
	public static List<Func> CacheFuncs;
	
	public static TreeNode CacheMenu;
	
	public static List<Dict> CacheDicts;
	
	public static List<Branch> CacheBranch;

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
