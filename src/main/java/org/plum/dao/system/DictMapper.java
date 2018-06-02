package org.plum.dao.system;

import java.util.List;

import org.plum.model.system.Dict;

public interface DictMapper {
    int insert(Dict record);

    List<Dict> selectAll();
    
    List<Dict> select(String type);
}