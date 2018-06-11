package org.plum.dao.system;

import java.util.List;

import org.plum.model.system.Func;

public interface FuncMapper {
    int deleteByPrimaryKey(Integer funid);

    int insert(Func record);

    Func selectByPrimaryKey(Integer funid);
    
    
    List<Func> selectFuncs();

    int updateByPrimaryKey(Func record);
}