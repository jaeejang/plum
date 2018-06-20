package org.plum.dao.advice;

import java.util.List;

import org.plum.model.advice.Subject;

public interface SubjectMapper {
    int delete(Integer id);

    int insert(Subject record);

    Subject selectByPrimaryKey(Integer id);
    
    List<Subject> select(Boolean full);

    int updateByPrimaryKey(Subject record);
}