package com.dkunc.adm.ath.cde.vo;
import com.dkunc.cmn.SearchDefaultVO;

/**
 * 코드 VO클래스로서 부서관리 비지니스로직 처리용 항목을 구성한다.
 * @author 강호진
 * @since 2017.03.20
 * @version 1.0
 * @see
 *
 */
public class CodeManageVO extends SearchDefaultVO{

	/*
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 코드번호
	 */
	private String codeId;
	/**
	 * 코드이름
	 */
	private String codeNm;
	/**
	 * 사용여부
	 */
	private String usageYn;
	/**
	 * 생성자
	 */
	private String createId;
	/**
	 * 생성일
	 */
	private String createDt;
	/**
	 * 수정자
	 */
	private String updateId;

	/**
	 * 수정일
	 */
	private String updateDt;

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeNm() {
		return codeNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	public String getUsageYn() {
		return usageYn;
	}

	public void setUsageYn(String usageYn) {
		this.usageYn = usageYn;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}

	@Override
	public String toString() {
		return "CodeManageVO [codeId=" + codeId + ", codeNm=" + codeNm + ", usageYn=" + usageYn + ", createId="
				+ createId + ", createDt=" + createDt + ", updateId=" + updateId + ", updateDt=" + updateDt + "]";
	}
	
}