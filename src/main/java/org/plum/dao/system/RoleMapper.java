package org.plum.dao.system;

import java.util.List;

import org.plum.model.system.Role;

public interface RoleMapper {
	
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);
    
    Role selectByPrimaryKey(Integer roleid);
    
    int updateByPrimaryKey(Role record);
    
    List<Role> selectRoles();
}