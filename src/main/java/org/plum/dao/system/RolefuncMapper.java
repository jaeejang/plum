package org.plum.dao.system;

import java.util.List;

import org.plum.model.system.Func;
import org.plum.model.system.Role;
import org.plum.model.system.Rolefunc;

public interface RolefuncMapper {

    int insert(Rolefunc record);
    
    int delete(int roleid);
    
    List<Integer> select(int roleid);
    
    List<Func> selectRoleFuncs(Role role);
}