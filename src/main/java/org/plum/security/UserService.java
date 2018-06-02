package org.plum.security;

import java.util.List;

import org.plum.dao.system.UserMapper;
import org.plum.model.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SimpleUserDetails userDetails;

	public User getUser(String username) {
		return userMapper.getUser(username);
	}

	public boolean saveOrUpdate(User toSave){
		User user = getUser(toSave.getUsername());
		int ret = 0;
		if(user == null){
			ret = userMapper.insert(toSave);
		}
		else{
			if(!toSave.getPassword().isEmpty()){
				toSave.setPassword(new Md5PasswordEncoder().encodePassword(toSave.getPassword(), null));
			}
			ret = userMapper.update(toSave);
		}
		return ret == 0 ? false:true;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		userDetails.setUser(username);
		return userDetails;
	}

	public int delete(String username) {
		return userMapper.delete(username);
	}

	public List<User> selectAllWithPagination() {
		return userMapper.selectAllWithPagination();
	}

}
