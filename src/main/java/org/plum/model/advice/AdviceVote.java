package org.plum.model.advice;

import java.util.Date;

public class AdviceVote {
    private Integer adviceId;

    private String voter;

    private Date vttime;

    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public Date getVttime() {
        return vttime;
    }

    public void setVttime(Date vttime) {
        this.vttime = vttime;
    }

	public Integer getAdviceId() {
		return adviceId;
	}

	public void setAdviceId(Integer adviceId) {
		this.adviceId = adviceId;
	}
}