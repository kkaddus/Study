package com.dkunc.adm.ath.cde.vo;

/**
 * 코드 VO클래스로서 부서관리 비지니스로직 처리용 항목을 구성한다.
 * @author 강호진
 * @since 2017.03.20
 * @version 1.0
 * @see
 *
 */
public class CodeDetailVO extends CodeMasterVO{

	/*
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	String codeDesc;
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		addAssignParameters("codeDesc");
		this.codeDesc = codeDesc;
	}
}