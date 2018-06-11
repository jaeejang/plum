package org.plum.security;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

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

	public List<User> selectAllWithPagination(Map<String,String[]> params) {
		try {
			String keyword = params.get("keyword") != null  ? params.get("keyword")[0] :"";
			String brchno = params.get("brchno") != null ? params.get("brchno")[0] :"";
			return userMapper.selectAllWithPagination(brchno, URLDecoder.decode(keyword,"utf-8").trim());
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

}
