package com.dkunc.adm.ath.cpn.vo;

import com.dkunc.cmn.vo.AbstractVO;

public class CompanyInfoVO extends AbstractVO {
	private static final long serialVersionUID = 587253964295779220L;
	String companyCd;
	String companyNm;
	String usageYn;
	public String getCompanyCd() {
		return companyCd;
	}
	public void setCompanyCd(String companyCd) {
		addAssignParameters("companyCd");
		this.companyCd = companyCd;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		addAssignParameters("companyNm");
		this.companyNm = companyNm;
	}
	public String getUsageYn() {
		return usageYn;
	}
	public void setUsageYn(String usageYn) {
		addAssignParameters("usageYn");
		this.usageYn = usageYn;
	}




}
