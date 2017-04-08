package com.dkunc.adm.ath.rol.vo;

import com.dkunc.cmn.vo.AbstractVO;

public class MenuRoleVO extends AbstractVO {
	private static final long serialVersionUID = -6790384958913611030L;
	String menuId;
	String roleCd;
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		addAssignParameters("menuId");
		this.menuId = menuId;
	}
	public String getRoleCd() {
		return roleCd;
	}
	public void setRoleCd(String roleCd) {
		addAssignParameters("roleCd");
		this.roleCd = roleCd;
	}



}
