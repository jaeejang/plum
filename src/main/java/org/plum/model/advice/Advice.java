package org.plum.model.advice;

import java.util.Date;

public class Advice {
    private Integer id;

    boolean anony = false;

	private Integer subject;
	
	private String topic;

    private String crtusr;
    
    //fullname
    private String crtusrna;

    private String brchno;
    
    private String brchna;
    
    //brchno
    private String upbrch;
    
    //brchna
    private String upbrchna;

	private Integer catalog;

    private String summary;

    private String leaddep;
    
    //brchna
    private String leaddepna;

    private Date crttime;

    private Integer status = 0;

    private Integer polls;
    
    private Integer polldown;
    
    private String voter;
    
    private String reserve1;

    private String reserve2;

    private String reserve3;

    private String content;
    
    private Boolean pub;
    
    private Date cmttime;
    
    

    public boolean getAnony() {
		return anony;
	}

	public void setAnony(boolean anony) {
		this.anony = anony;
	}

    
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

	public Integer getSubject() {
		return subject;
	}

	public void setSubject(Integer subject) {
		this.subject = subject;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Integer getPolldown() {
		return polldown;
	}

	public void setPolldown(Integer polldown) {
		this.polldown = polldown;
	}

	public Boolean getPub() {
		return pub;
	}

	public void setPub(Boolean pub) {
		this.pub = pub;
	}

	public Date getCmttime() {
		return cmttime;
	}

	public void setCmttime(Date cmttime) {
		this.cmttime = cmttime;
	}
}