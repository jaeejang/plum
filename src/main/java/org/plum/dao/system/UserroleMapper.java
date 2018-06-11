package org.plum.dao.system;

import java.util.List;

import org.plum.model.system.Role;
import org.plum.model.system.User;
import org.plum.model.system.Userrole;

public interface UserroleMapper {
    int insert(Userrole record);

    List<Role> selectRoleByUser(User user);
    
    List<User> selectUserByRoleWithPagination(Role role);
    
    int deleteByUser(String username);
}