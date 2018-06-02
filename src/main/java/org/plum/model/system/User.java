package org.plum.model.system;

import javax.validation.constraints.Size;

public class User {

	@Size(min = 3, max = 10)
	private String username;

	@Size(min = 2, max = 10)
	private String fullname;

	private String password;

	private String brchno;

	private Boolean lock;

	private String brchna;

	private Boolean admin;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBrchno() {
		return brchno;
	}

	public void setBrchno(String brchno) {
		this.brchno = brchno;
	}

	public Boolean getLock() {
		return lock == null ? false : lock;
	}

	public void setLock(Boolean lock) {
		this.lock = lock;
	}

	public String getBrchna() {
		return this.brchna;
	}

	public boolean isAdmin() {
		return admin == null ? false : admin.booleanValue();
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

}