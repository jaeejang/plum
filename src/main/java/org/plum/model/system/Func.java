package org.plum.model.system;

public class Func {
    private Integer funid;

    private String funcname;

    private Integer superid;

    private Integer order = 0;

    private String path;
    
    private String icon;

    public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getFunid() {
        return funid;
    }

    public void setFunid(Integer funid) {
        this.funid = funid;
    }

    public String getFuncname() {
        return funcname;
    }

    public void setFuncname(String funcname) {
        this.funcname = funcname;
    }

    public Integer getSuperid() {
        return superid;
    }

    public void setSuperid(Integer superid) {
        this.superid = superid;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Func){
			return ((Func)obj).getFunid().intValue() ==  this.getFunid().intValue();
		}
		return false;
	}

}