package org.plum.model.system;

public class Role {
    private Integer roleid;

    private String rolename;
    
    private Boolean admin;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

	public boolean getAdmin() {
		return admin == null ? false : true;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
}