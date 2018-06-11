package org.plum.dao.advice;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.plum.model.advice.FileAttach;

public interface FileAttachMapper {
    int delete(Integer id);

    int insert(FileAttach record);
    
    List<FileAttach> selectBySource(@Param("sourceid")int id,@Param("attachtype")int attatch_type);

    FileAttach selectByPrimaryKey(int id);
    
    int update(FileAttach record);
    
}