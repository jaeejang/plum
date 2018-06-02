package org.plum.model.advice;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Advice {
    private Integer id;


	@Size(min = 6, max = 6)
    private String crtdt;

    private String crtusr;
    
    //fullname
    private String crtusrna;

    private String brchno;
    
    private String brchna;
    
    //brchno
    private String upbrch;
    
    //brchna
    private String upbrchna;

    @NotNull
	private Integer catalog;

    @Size(min = 10)
    private String summary;

    @NotBlank
    private String leaddep;
    
    //brchna
    private String leaddepna;

    private Date crttime;

    private Integer status;

    private Integer polls;
    
    private String voter;
    
    private String reserve1;

    private String reserve2;

    private String reserve3;

    @Size(min = 10)
    private String content;
    
    
    
    public Integer getPolls() {
		return polls;
	}

	public void setPolls(Integer polls) {
		this.polls = polls;
	}

	public String getCrtusrna() {
		return crtusrna;
	}

	public void setCrtusrna(String crtusrna) {
		this.crtusrna = crtusrna;
	}

	public String getBrchna() {
		return brchna;
	}

	public void setBrchna(String brchna) {
		this.brchna = brchna;
	}

	public String getUpbrch() {
		return upbrch;
	}

	public void setUpbrch(String upbrch) {
		this.upbrch = upbrch;
	}

	public String getUpbrchna() {
		return upbrchna;
	}

	public void setUpbrchna(String upbrchna) {
		this.upbrchna = upbrchna;
	}

	public String getLeaddepna() {
		return leaddepna;
	}

	public void setLeaddepna(String leaddepna) {
		this.leaddepna = leaddepna;
	}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCrtdt() {
        return crtdt;
    }

    public void setCrtdt(String crtdt) {
        this.crtdt = crtdt;
    }

    public String getCrtusr() {
        return crtusr;
    }

    public void setCrtusr(String crtusr) {
        this.crtusr = crtusr;
    }

    public String getBrchno() {
        return brchno;
    }

    public void setBrchno(String brchno) {
        this.brchno = brchno;
    }

    public Integer getCatalog() {
        return catalog;
    }

    public void setCatalog(Integer catalog) {
        this.catalog = catalog;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLeaddep() {
        return leaddep;
    }

    public void setLeaddep(String leaddep) {
        this.leaddep = leaddep;
    }

    public Date getCrttime() {
        return crttime;
    }

    public void setCrttime(Date crttime) {
        this.crttime = crttime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public String getVoter() {
		return voter;
	}

	public void setVoter(String voter) {
		this.voter = voter;
	}
}