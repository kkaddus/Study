package com.dkunc.cmn.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractVO implements Serializable {

	private static final long serialVersionUID = 1028061241484497901L;

	protected String mode;
	protected Date createDt;
	protected String createId;
	protected Date updateDt;
	protected String updateId;

	protected Set<String> assignParameters = new HashSet<String>();

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public Set<String> getAssignParameters() {
		return assignParameters;
	}

	public void setAssignParameters(Set<String> assignParameters) {
		this.assignParameters = assignParameters;
	}

	public void addAssignParameters(String parameter){
		this.assignParameters.add(parameter);
	}
	public void removeAssignParameters(String parameter){
		this.assignParameters.remove(parameter);
	}
}