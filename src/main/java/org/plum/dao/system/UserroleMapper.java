package org.plum.dao.system;

import java.util.List;

import org.plum.model.system.Role;
import org.plum.model.system.User;
import org.plum.model.system.Userrole;
import org.plum.model.system.UserroleExample;

public interface UserroleMapper {
    int insert(Userrole record);

    List<Userrole> selectByExample(UserroleExample example);
    
    List<Role> selectRoleByUser(User user);
    
    List<User> selectUserByRoleWithPagination(Role role);
}