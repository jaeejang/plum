package org.plum.model.system;

public class Rolefunc {
	private Integer roleid;

	private Integer funcid;

	public Rolefunc(int roleid, int funcid) {
		this.roleid = roleid;
		this.funcid = funcid;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getFuncid() {
		return funcid;
	}

	public void setFuncid(Integer funcid) {
		this.funcid = funcid;
	}
}