package org.plum.dao.system;

import java.util.List;

import org.plum.model.system.Func;
import org.plum.model.system.FuncExample;

public interface FuncMapper {
    int deleteByPrimaryKey(Integer funid);

    int insert(Func record);

    List<Func> selectAll(FuncExample example);

    Func selectByPrimaryKey(Integer funid);

    int updateByPrimaryKey(Func record);
}