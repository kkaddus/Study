package com.dkunc.adm.ath.brd.vo;

import java.sql.Date;

import com.dkunc.cmn.vo.AbstractVO;

public class BoardInfoVO extends AbstractVO{
	private static final long serialVersionUID = 1L;

	String  mode;
	int 	tUid;
	int 	boardNo;
	String 	title;
	String	html;
	String	saveFlag;
	String	deleteFlag;
	int 	attachCnt;
	int		visitCnt;
	int		parentUid;
	int		step;
	int		childUid;
	int		downloadCnt;
	String	createId;
	Date	createDt;
	String	updateId;
	Date	updateDt;
	
	public int gettUid() {
		return tUid;
	}
	public void settUid(int tUid) {
		addAssignParameters("tUid");
		this.tUid = tUid;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		addAssignParameters("boardNo");
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		addAssignParameters("title");
		this.title = title;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		addAssignParameters("html");
		this.html = html;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		addAssignParameters("saveFlag");
		this.saveFlag = saveFlag;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		addAssignParameters("deleteFlag");
		this.deleteFlag = deleteFlag;
	}
	public int getAttachCnt() {
		return attachCnt;
	}
	public void setAttachCnt(int attachCnt) {
		addAssignParameters("attachCnt");
		this.attachCnt = attachCnt;
	}
	public int getVisitCnt() {
		return visitCnt;
	}
	public void setVisitCnt(int visitCnt) {
		addAssignParameters("visitCnt");
		this.visitCnt = visitCnt;
	}
	public int getParentUid() {
		return parentUid;
	}
	public void setParentUid(int parentUid) {
		addAssignParameters("parentUid");
		this.parentUid = parentUid;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		addAssignParameters("step");
		this.step = step;
	}
	public int getChildUid() {
		return childUid;
	}
	public void setChildUid(int childUid) {
		addAssignParameters("childUid");
		this.childUid = childUid;
	}
	public int getDownloadCnt() {
		return downloadCnt;
	}
	public void setDownloadCnt(int downloadCnt) {
		addAssignParameters("downloadCnt");
		this.downloadCnt = downloadCnt;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		addAssignParameters("createId");
		this.createId = createId;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		addAssignParameters("createDt");
		this.createDt = createDt;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		addAssignParameters("updateId");
		this.updateId = updateId;
	}
	public Date getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(Date updateDt) {
		addAssignParameters("updateDt");
		this.updateDt = updateDt;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	


}
