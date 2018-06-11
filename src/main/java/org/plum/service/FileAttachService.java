package org.plum.service;

import java.util.List;

import org.plum.dao.advice.FileAttachMapper;
import org.plum.model.advice.FileAttach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class FileAttachService {
	
	@Autowired
	FileAttachMapper fileAttatchMapper;
	
	public FileAttach getFileAttach(int id) {
		return fileAttatchMapper.selectByPrimaryKey(id);
	}
	
	
	public List<FileAttach> getFileAttach(int source, int attatch_type) {
		return fileAttatchMapper.selectBySource(source, attatch_type);
	}
		
	public boolean saveOrUpdateFileAttach(FileAttach toSave) {
		int ret = 0;
		if(toSave.getId() == null)
			ret = fileAttatchMapper.insert(toSave);
		else
			ret = fileAttatchMapper.update(toSave);
		return ret == 0 ? false : true;
	}

	public int deleteFile(int id) {
		int ret = fileAttatchMapper.delete(id);
		return ret;
	}
}
