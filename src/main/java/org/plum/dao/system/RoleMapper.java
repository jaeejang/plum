package org.plum.dao.system;

import java.util.List;

import org.plum.model.system.Role;
import org.plum.model.system.RoleExample;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);
    List<Role> selectAll(RoleExample example);
    Role selectByPrimaryKey(Integer roleid);
    int updateByPrimaryKey(Role record);
}