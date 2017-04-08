package com.dkunc.adm.ath.brd.vo;

import java.sql.Date;

import com.dkunc.cmn.vo.AbstractVO;

public class BoardAttachInfoVO extends AbstractVO{
	private static final long serialVersionUID = 1L;

	int 	boardNo;
	int 	fileIndex;
	long 	fileSize;
	String 	fileType;
	String	fileName;
	String	filePath;
	String	folderName;
	String 	deleteFlag;
	String	createId;
	Date	createDt;
	String	updateId;
	Date	updateDt;
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		addAssignParameters("boardNo");
		this.boardNo = boardNo;
	}
	public int getFileIndex() {
		return fileIndex;
	}
	public void setFileIndex(int fileIndex) {
		addAssignParameters("fileIndex");
		this.fileIndex = fileIndex;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		addAssignParameters("fileSize");
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		addAssignParameters("fileType");
		this.fileType = fileType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		addAssignParameters("fileName");
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		addAssignParameters("filePath");
		this.filePath = filePath;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		addAssignParameters("folderName");
		this.folderName = folderName;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		addAssignParameters("deleteFlag");
		this.deleteFlag = deleteFlag;
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

}
