package com.dkunc.adm.ath.mnu.vo;

import com.dkunc.cmn.vo.AbstractVO;

public class MenuInfoVO extends AbstractVO{
	private static final long serialVersionUID = 1L;

	int menuId;
	String menuNm;
	int parentMenuId;
	int menuOrder;
	String menuDesc;
	int menuDepth;
	String menuUrl;
	String authPttrn;
	String usageYn;

	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		addAssignParameters("menuId");
		this.menuId = menuId;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		addAssignParameters("menuNm");
		this.menuNm = menuNm;
	}
	public int getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(int parentMenuId) {
		addAssignParameters("parentMenuId");
		this.parentMenuId = parentMenuId;
	}
	public int getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(int menuOrder) {
		addAssignParameters("menuOrder");
		this.menuOrder = menuOrder;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		addAssignParameters("menuDesc");
		this.menuDesc = menuDesc;
	}
	public int getMenuDepth() {
		return menuDepth;
	}
	public void setMenuDepth(int menuDepth) {
		addAssignParameters("menuDepth");
		this.menuDepth = menuDepth;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		addAssignParameters("menuUrl");
		this.menuUrl = menuUrl;
	}
	public String getAuthPttrn() {
		return authPttrn;
	}
	public void setAuthPttrn(String authPttrn) {
		addAssignParameters("authPttrn");
		this.authPttrn = authPttrn;
	}
	public String getUsageYn() {
		return usageYn;
	}
	public void setUsageYn(String usageYn) {
		addAssignParameters("usageYn");
		this.usageYn = usageYn;
	}
}
