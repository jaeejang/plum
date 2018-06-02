package org.plum.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.plum.model.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class SimpleUserDetails implements UserDetails {

	@Autowired
	private UserService service;

	private User user;

	public void setUser(String username) {
		user = service.getUser(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("NO USER %s", username));
		}
	}

	@Override
	public boolean isEnabled() {
		return user == null ? false : !user.getLock();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return user == null ? false : !user.getLock();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authList = new ArrayList<>();
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authList;
	}
}