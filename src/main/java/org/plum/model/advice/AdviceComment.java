package org.plum.model.advice;

import java.util.Date;

public class AdviceComment {
    private Integer id;

    private Integer adviceId;

    private Date crttime;

    private String crtusr;
    
    private String title;


	private String content;
	
	private String crtbrch;
	
	private String crtbrna;
	
	private Integer satisfy;

	

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(Integer adviceId) {
        this.adviceId = adviceId;
    }

    public Date getCrttime() {
        return crttime;
    }

    public void setCrttime(Date crttime) {
        this.crttime = crttime;
    }

    public String getCrtusr() {
        return crtusr;
    }

    public void setCrtusr(String crtusr) {
        this.crtusr = crtusr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public String getCrtbrch() {
		return crtbrch;
	}

	public void setCrtbrch(String crtbrch) {
		this.crtbrch = crtbrch;
	}

	public String getCrtbrna() {
		return crtbrna;
	}

	public void setCrtbrna(String crtbrna) {
		this.crtbrna = crtbrna;
	}

	public Integer getSatisfy() {
		return satisfy;
	}

	public void setSatisfy(Integer satisfy) {
		this.satisfy = satisfy;
	}
}