package org.plum.model.system;

import java.util.List;

public class Branch {
    private String brchno;

    private String brchna;

    private String brsmna;

    private String brchup;

    private Integer brchtp;

    private Integer brtype;

    private Integer brchlv;
    
    private List<Branch> children;

    public List<Branch> getChildren() {
		return children;
	}

	public void setChildren(List<Branch> children) {
		this.children = children;
	}

	public String getBrchno() {
        return brchno;
    }

    public void setBrchno(String brchno) {
        this.brchno = brchno;
    }

    public String getBrchna() {
        return brchna;
    }

    public void setBrchna(String brchna) {
        this.brchna = brchna;
    }

    public String getBrsmna() {
        return brsmna;
    }

    public void setBrsmna(String brsmna) {
        this.brsmna = brsmna;
    }

    public String getBrchup() {
        return brchup;
    }

    public void setBrchup(String brchup) {
        this.brchup = brchup;
    }

    public Integer getBrchtp() {
        return brchtp;
    }

    public void setBrchtp(Integer brchtp) {
        this.brchtp = brchtp;
    }

    public Integer getBrtype() {
        return brtype;
    }

    public void setBrtype(Integer brtype) {
        this.brtype = brtype;
    }

    public Integer getBrchlv() {
        return brchlv;
    }

    public void setBrchlv(Integer brchlv) {
        this.brchlv = brchlv;
    }
}