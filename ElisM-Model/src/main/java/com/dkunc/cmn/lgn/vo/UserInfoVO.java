package com.dkunc.cmn.lgn.vo;

import java.util.Date;
import java.util.List;

import com.dkunc.adm.ath.mnu.vo.MenuInfoVO;
import com.dkunc.cmn.vo.AbstractVO;

public class UserInfoVO extends AbstractVO {

	private static final long serialVersionUID = 1213862133212516113L;

	String userId;
	String userNm;
	String pwd;
	String pwdHint;
	String pwdCnsr;
	String jobCd;
	String positionCd;
	String titleCd;
	String empNo;
	Date hireDt;
	Date promotionDt;
	String deptCd;
	String deptNm;
	String email;
	String zipCd;
	String adr1;
	String adr2;
	String adr3;
	String mobileNo;
	String telNo;
	String comTelNo;
	String faxNo;
	String userOrder;
	String usageYn;
	String userSt;
	String companyCd;
	
	List<MenuInfoVO> menuList ;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		addAssignParameters("userId");
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		addAssignParameters("userNm");
		this.userNm = userNm;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		addAssignParameters("pwd");
		this.pwd = pwd;
	}
	public String getPwdHint() {
		return pwdHint;
	}
	public void setPwdHint(String pwdHint) {
		addAssignParameters("pwdHint");
		this.pwdHint = pwdHint;
	}
	public String getPwdCnsr() {
		return pwdCnsr;
	}
	public void setPwdCnsr(String pwdCnsr) {
		addAssignParameters("pwdCnsr");
		this.pwdCnsr = pwdCnsr;
	}
	public String getJobCd() {
		return jobCd;
	}
	public void setJobCd(String jobCd) {
		addAssignParameters("jobCd");
		this.jobCd = jobCd;
	}
	public String getPositionCd() {
		return positionCd;
	}
	public void setPositionCd(String positionCd) {
		addAssignParameters("positionCd");
		this.positionCd = positionCd;
	}
	public String getTitleCd() {
		return titleCd;
	}
	public void setTitleCd(String titleCd) {
		addAssignParameters("titleCd");
		this.titleCd = titleCd;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		addAssignParameters("empNo");
		this.empNo = empNo;
	}
	public Date getHireDt() {
		return hireDt;
	}
	public void setHireDt(Date hireDt) {
		addAssignParameters("hireDt");
		this.hireDt = hireDt;
	}
	public Date getPromotionDt() {
		return promotionDt;
	}
	public void setPromotionDt(Date promotionDt) {
		addAssignParameters("promotionDt");
		this.promotionDt = promotionDt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		addAssignParameters("email");
		this.email = email;
	}
	public String getZipCd() {
		return zipCd;
	}
	public void setZipCd(String zipCd) {
		addAssignParameters("zipCd");
		this.zipCd = zipCd;
	}
	public String getAdr1() {
		return adr1;
	}
	public void setAdr1(String adr1) {
		addAssignParameters("adr1");
		this.adr1 = adr1;
	}
	public String getAdr2() {
		return adr2;
	}
	public void setAdr2(String adr2) {
		addAssignParameters("adr2");
		this.adr2 = adr2;
	}
	public String getAdr3() {
		return adr3;
	}
	public void setAdr3(String adr3) {
		addAssignParameters("adr3");
		this.adr3 = adr3;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		addAssignParameters("mobileNo");
		this.mobileNo = mobileNo;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		addAssignParameters("telNo");
		this.telNo = telNo;
	}
	public String getComTelNo() {
		return comTelNo;
	}
	public void setComTelNo(String comTelNo) {
		addAssignParameters("comTelNo");
		this.comTelNo = comTelNo;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		addAssignParameters("faxNo");
		this.faxNo = faxNo;
	}
	public String getUserOrder() {
		return userOrder;
	}
	public void setUserOrder(String userOrder) {
		addAssignParameters("userOrder");
		this.userOrder = userOrder;
	}
	public String getUsageYn() {
		return usageYn;
	}
	public void setUsageYn(String usageYn) {
		addAssignParameters("usageYn");
		this.usageYn = usageYn;
	}
	public String getUserSt() {
		return userSt;
	}
	public void setUserSt(String userSt) {
		addAssignParameters("userSt");
		this.userSt = userSt;
	}
	public String getCompanyCd() {
		return companyCd;
	}
	public void setCompanyCd(String companyCd) {
		addAssignParameters("companyCd");
		this.companyCd = companyCd;
	}
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
	public List<MenuInfoVO> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MenuInfoVO> menuList) {
		this.menuList = menuList;
	}
}
