package com.dkunc.cmn.service;

import java.io.Serializable;

/**
 * 공통상세코드 모델 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *
 * </pre>
 */
public class CmmnDetailCode implements Serializable {

	private static final long serialVersionUID = -6508801327314181679L;

	/*
	 * 코드ID
	 */
    private String codeId = "";

    /*
     * 코드ID명
     */
    private String codeDesc = "";

	/*
	 * 코드명
	 */
    private String codeNm = "";

    /*
     * 코드설명
     */
    private String codeCont = "";

    /*
     * 사용여부
     */
    private String usageYn = "";

    /*
     * 최초등록자ID
     */
    private String createId = "";

    /*
     * 최종수정자ID
     */
    private String updateId   = "";

	/**
	 * codeId attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeId() {
		return codeId;
	}

	/**
	 * codeId attribute 값을 설정한다.
	 * @param codeId String
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	/**
	 * codeNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeNm() {
		return codeNm;
	}

	/**
	 * codeNm attribute 값을 설정한다.
	 * @param codeNm String
	 */
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	/**
	 * codeDesc attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeDesc() {
		return codeDesc;
	}

	/**
	 * codeDesc attribute 값을 설정한다.
	 * @param codeDesc String
	 */
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	
	/**
	 * codeCont attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeCont() {
		return codeCont;
	}

	/**
	 * codeCont attribute 값을 설정한다.
	 * @param codeCont String
	 */
	public void setCodeCont(String codeCont) {
		this.codeCont = codeCont;
	}

	/**
	 * useAt attribute 를 리턴한다.
	 * @return String
	 */
	public String getUsageYn() {
		return usageYn;
	}

	/**
	 * usageYn attribute 값을 설정한다.
	 * @param usageYn String
	 */
	public void setUsageYn(String usageYn) {
		this.usageYn = usageYn;
	}

	/**
	 * createId attribute 를 리턴한다.
	 * @return String
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * createId attribute 값을 설정한다.
	 * @param createId String
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * updateId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * updateId attribute 값을 설정한다.
	 * @param updateId String
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}


}
