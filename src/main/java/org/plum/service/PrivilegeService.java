package org.plum.service;

import java.util.List;

import org.plum.dao.system.FuncMapper;
import org.plum.dao.system.RoleMapper;
import org.plum.dao.system.RolefuncMapper;
import org.plum.dao.system.UserroleMapper;
import org.plum.initial.PlumCache;
import org.plum.model.system.Func;
import org.plum.model.system.FuncExample;
import org.plum.model.system.Role;
import org.plum.model.system.RoleExample;
import org.plum.model.system.Rolefunc;
import org.plum.model.system.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {

	private static Logger log = LoggerFactory.getLogger(PrivilegeService.class);

	@Autowired
	private FuncMapper funcMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RolefuncMapper rolefuncMapper;
	@Autowired
	private UserroleMapper userroleMapper;

	public Func getFunc(int funcid) {
		return funcMapper.selectByPrimaryKey(funcid);
	}

	public boolean saveOrUpdateFunc(Func toSave) {
		int ret = 0;
		if (toSave.getFunid() == null)
			ret = funcMapper.insert(toSave);
		else
			ret = funcMapper.updateByPrimaryKey(toSave);
		if (ret > 0)
			PlumCache.CacheFuncs.clear();
		return ret == 0 ? false : true;

	}

	public List<Func> selectFunc(FuncExample example) {
		return funcMapper.selectAll(example);
	}

	public int deleteFunc(int funcid) {
		int ret = funcMapper.deleteByPrimaryKey(funcid);
		if (ret > 0)
			PlumCache.CacheFuncs.clear();
		return ret;
	}

	public List<Role> selectRole(RoleExample example) {
		return roleMapper.selectAll(example);
	}

	public Role getRole(int roleid) {
		return roleMapper.selectByPrimaryKey(roleid);
	}

	public boolean saveOrUpdateRole(Role toSave) {
		int ret = 0;
		if (toSave.getRoleid() == null)
			ret = roleMapper.insert(toSave);
		else
			ret = roleMapper.updateByPrimaryKey(toSave);
		if (ret > 0)
			PlumCache.CacheFuncs.clear();
		return ret == 0 ? false : true;
	}

	public int deleteRole(int roleid) {
		int ret = roleMapper.deleteByPrimaryKey(roleid);
		if (ret > 0)
			PlumCache.CacheFuncs.clear();
		return ret;
	}

	public Integer[] getRolefuncs(int roleid) {
		List<Integer> ids = rolefuncMapper.select(roleid);
		Integer[] arr = new Integer[ids.size()];
		return rolefuncMapper.select(roleid).toArray(arr);
	}

	public List<Func> getRolefuncs(Role role) {
		return rolefuncMapper.selectRoleFuncs(role);
	}

	public boolean saveRolefunc(int roleid, int[] funcs) {
		rolefuncMapper.delete(roleid);
		for (int i = 0; i < funcs.length; i++) {
			Rolefunc rolefunc = new Rolefunc(roleid, funcs[i]);
			rolefuncMapper.insert(rolefunc);
		}
		return true;
	}

	public List<Role> selectUserRoles(User user) {
		return userroleMapper.selectRoleByUser(user);
	}
	

	public List<User> selectRoleUsers(Role role) {
		return userroleMapper.selectUserByRoleWithPagination(role);
	}
}
