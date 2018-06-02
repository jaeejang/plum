package org.plum.dao.system;

import java.util.List;

import org.plum.model.system.User;

public interface UserMapper {
	int insert(User record);

	int update(User record);
	
	int delete(String username);

	User getUser(String username);

	List<User> selectAllWithPagination();
}