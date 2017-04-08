package com.dkunc.adm.ath.cde.vo;
import com.dkunc.cmn.vo.AbstractVO;

/**
 * 코드 VO클래스로서 부서관리 비지니스로직 처리용 항목을 구성한다.
 * @author 강호진
 * @since 2017.03.20
 * @version 1.0
 * @see
 *
 */
public class CodeMasterVO extends AbstractVO{

	/*
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	String codeId;
	String codeNm;
	String usageYn;
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		addAssignParameters("codeId");
		this.codeId = codeId;
	}
	public String getCodeNm() {
		return codeNm;
	}
	public void setCodeNm(String codeNm) {
		addAssignParameters("codeNm");
		this.codeNm = codeNm;
	}
	public String getUsageYn() {
		return usageYn;
	}
	public void setUsageYn(String usageYn) {
		addAssignParameters("usageYn");
		this.usageYn = usageYn;
	}
}