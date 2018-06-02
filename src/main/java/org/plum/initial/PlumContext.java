package org.plum.initial;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.plum.dao.system.DictMapper;
import org.plum.model.system.Dict;
import org.plum.model.system.Func;
import org.plum.model.system.FuncExample;
import org.plum.model.system.Role;
import org.plum.model.system.User;
import org.plum.security.SimpleUserDetails;
import org.plum.service.PrivilegeService;
import org.plum.service.SystemService;
import org.plum.tools.pagination.TooManyIterationException;
import org.plum.tools.ui.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class PlumContext {
	private TreeNode menuTree = null;

	private static Logger log = LoggerFactory.getLogger(PlumContext.class);

	private TreeNode activeNodes = null;

	private TreeNode leefNode = null;

	private List<Func> pussiantLeefFuns = null;

	@Autowired
	PrivilegeService privailegeService;
	
	@Autowired
	SystemService systemService;
	

	private User user = null;

	private HttpServletRequest request = null;

	public void setContext(HttpServletRequest request) {
		this.request = request;
	}

	/*
	 * 获取登录用户
	 */
	public User getUser() {
		Principal userPrincipal = request.getUserPrincipal();
		if (user == null && userPrincipal instanceof UsernamePasswordAuthenticationToken) {
			Object principal = ((UsernamePasswordAuthenticationToken) userPrincipal).getPrincipal();
			if (principal instanceof SimpleUserDetails) {
				SimpleUserDetails details = (SimpleUserDetails) principal;
				user = details.getUser();
				List<Role> userRoles = privailegeService.selectUserRoles(user);
				for (Role role : userRoles) {
					if (role.getAdmin()) {
						user.setAdmin(true);
						break;
					}
				}
			}
		}
		return user;
	}

	/*
	 * 获取当前用户菜单
	 */
	public TreeNode getMenuTree() {
		if (user == null)
			getUser();
		if (PlumCache.CacheFuncs == null || PlumCache.CacheFuncs.size() == 0) {
			initialCache();
		}
		if (pussiantLeefFuns == null)
			getPuissant();
		// 获取所有功能树
		try {
			menuTree = buildTree(PlumCache.CacheFuncs, pussiantLeefFuns, null, 0);
		} catch (TooManyIterationException e) {
			log.error(e.getMessage());
		}

		if (menuTree == null) {
			menuTree = new TreeNode(0, "首页", -1, 0, "/home", "home");
			menuTree.setPuissant(true);
		}
		return menuTree;
	}

	/*
	 * 获取当前功能链 home - level1 - level2
	 * 
	 */
	public TreeNode getActiveNodes() {
		return activeNodes;
	}

	/*
	 * 获取当前功能
	 */
	public TreeNode getLeef() {
		return leefNode;
	}

	/*
	 * 获取有权限功能列表
	 */
	public List<Func> getPuissant() {
		if (pussiantLeefFuns == null) {
			List<Role> roles = privailegeService.selectUserRoles(user);
			pussiantLeefFuns = new ArrayList<Func>();
			for (Role role : roles) {
				List<Func> items = privailegeService.getRolefuncs(role);
				for (Func func : items) {
					if (!pussiantLeefFuns.contains(func)) {
						pussiantLeefFuns.add(func);
					}
				}
			}
		}
		return pussiantLeefFuns;
	}

	/*
	 * 初始化菜单树
	 */
	private void initialCache() {
		FuncExample example = new FuncExample();
		example.setOrderByClause("superid,`order`");
		PlumCache.CacheFuncs = privailegeService.selectFunc(example);
	}

	// 递归功能树
	private TreeNode buildTree(List<Func> funcs, List<Func> puiss, TreeNode node, int iteration)
			throws TooManyIterationException {
		if (iteration > 99) {
			String message = String.format("node %s iterates %d too many iteration", node.getFuncname(), iteration);
			throw new TooManyIterationException(message);
		}
		if (node == null) {
			leefNode = null;
			activeNodes = null;
			node = new TreeNode(0, "首页", -1, 0, "/home", "home");
			node.setPuissant(true);
			node.setActive(true);
		}
		boolean active = false;
		for (Func func : funcs) {
			if (func.getSuperid().equals(node.getFunid())) {
				TreeNode child = new TreeNode(func);
				child = buildTree(funcs, puiss, child, iteration + 1);
				// 判断权限
				if (puiss.contains(func)) {
					child.setPuissant(true);
					node.setPuissant(true);

					if (request.getRequestURI().startsWith(request.getContextPath() + func.getPath())) {

						leefNode = child;
						
						child.setActive(true);
						active = true;
					}
				}
				node.addChild(child);
			}
		}
		if(active){
			node.setActive(true);
		}
		return node;
	}
	
	public List<Dict> getDicts(){
		if(PlumCache.CacheDicts  == null){
			PlumCache.CacheDicts =  systemService.loadDicts();
		}
		return PlumCache.CacheDicts;
	}

}
