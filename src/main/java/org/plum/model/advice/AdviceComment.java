package org.plum.model.advice;

import java.util.Date;

public class AdviceComment {
    private Integer id;

    private Integer adviceId;

    private Date crttime;

    private String crtusr;
    
    private String title;


	private String content;

	

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
}