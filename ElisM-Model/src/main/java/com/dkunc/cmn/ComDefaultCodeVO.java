package com.dkunc.cmn;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *  클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------       --------    ---------------------------
 *   2009.3.11   이삼섭          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class ComDefaultCodeVO implements Serializable {
    /** 코드 ID */
    private String codeId = "";
    
    /** 상세코드 */
    private String codeDesc = "";
    
    /** 코드명 */
    private String codeNm = "";
    
    /** 코드설명 */
    private String codeCont = "";
    
    /** 특정테이블명 */
    private String tableNm = "";	//특정테이블에서 코드정보를추출시 사용
    
    /** 상세 조건 여부 */
    private String haveDetailCondition = "N";
    
    /** 상세 조건 */
    private String detailCondition = "";
    
    /**
     * codeId attribute를 리턴한다.
     * 
     * @return the codeId
     */
    public String getCodeId() {
	return codeId;
    }

    /**
     * codeId attribute 값을 설정한다.
     * 
     * @param codeId
     *            the codeId to set
     */
    public void setCodeId(String codeId) {
	this.codeId = codeId;
    }

    /**
     * codeDesc attribute를 리턴한다.
     * 
     * @return the codeDesc
     */
    public String getCodeDesc() {
	return codeDesc;
    }

    /**
     * code attribute 값을 설정한다.
     * 
     * @param code
     *            the code to set
     */
    public void setCodeDesc(String codeDesc) {
	this.codeDesc = codeDesc;
    }

    /**
     * codeNm attribute를 리턴한다.
     * 
     * @return the codeNm
     */
    public String getCodeNm() {
	return codeNm;
    }

    /**
     * codeNm attribute 값을 설정한다.
     * 
     * @param codeNm
     *            the codeNm to set
     */
    public void setCodeNm(String codeNm) {
	this.codeNm = codeNm;
    }

    /**
     * codeCont attribute를 리턴한다.
     * 
     * @return the codeCont
     */
    public String getCodeCont() {
	return codeCont;
    }

    /**
     * codeDc attribute 값을 설정한다.
     * 
     * @param codeDc
     *            the codeDc to set
     */
    public void setCodeCont(String codeCont) {
	this.codeCont = codeCont;
    }

    /**
     * tableNm attribute를 리턴한다.
     * 
     * @return the tableNm
     */
    public String getTableNm() {
	return tableNm;
    }

    /**
     * tableNm attribute 값을 설정한다.
     * 
     * @param tableNm
     *            the tableNm to set
     */
    public void setTableNm(String tableNm) {
	this.tableNm = tableNm;
    }

    /**
     * haveDetailCondition attribute를 리턴한다.
     * 
     * @return the haveDetailCondition
     */
    public String getHaveDetailCondition() {
	return haveDetailCondition;
    }

    /**
     * haveDetailCondition attribute 값을 설정한다.
     * 
     * @param haveDetailCondition
     *            the haveDetailCondition to set
     */
    public void setHaveDetailCondition(String haveDetailCondition) {
	this.haveDetailCondition = haveDetailCondition;
    }

    /**
     * detailCondition attribute를 리턴한다.
     * 
     * @return the detailCondition
     */
    public String getDetailCondition() {
	return detailCondition;
    }

    /**
     * detailCondition attribute 값을 설정한다.
     * 
     * @param detailCondition
     *            the detailCondition to set
     */
    public void setDetailCondition(String detailCondition) {
	this.detailCondition = detailCondition;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
