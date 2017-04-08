package com.dkunc.adm.ath.dpt.vo;

import com.dkunc.cmn.vo.AbstractVO;

public class DeptInfoVO extends AbstractVO {

	private static final long serialVersionUID = -1554830660340643246L;

	String deptCd;
	String deptNm;
	String remark;
	String upDeptCd;
	String usageYn;
	String companyCd;

	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		addAssignParameters("deptCd");
		this.deptCd = deptCd;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		addAssignParameters("deptNm");
		this.deptNm = deptNm;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		addAssignParameters("remark");
		this.remark = remark;
	}
	public String getUpDeptCd() {
		return upDeptCd;
	}
	public void setUpDeptCd(String upDeptCd) {
		addAssignParameters("upDeptCd");
		this.upDeptCd = upDeptCd;
	}
	public String getUsageYn() {
		return usageYn;
	}
	public void setUsageYn(String usageYn) {
		addAssignParameters("usageYn");
		this.usageYn = usageYn;
	}
	public String getCompanyCd() {
		return companyCd;
	}
	public void setCompanyCd(String companyCd) {
		addAssignParameters("companyCd");
		this.companyCd = companyCd;
	}
}
