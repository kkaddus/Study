package com.dkunc.adm.ath.rol.vo;

import com.dkunc.cmn.vo.AbstractVO;

public class RoleInfoVO extends AbstractVO {

	private static final long serialVersionUID = 6246584915071143158L;

	String roleCd;
	String roleNm;
	String roleDesc;
	public String getRoleCd() {
		return roleCd;
	}
	public void setRoleCd(String roleCd) {
		addAssignParameters("roleCd");
		this.roleCd = roleCd;
	}
	public String getRoleNm() {
		return roleNm;
	}
	public void setRoleNm(String roleNm) {
		addAssignParameters("roleNm");
		this.roleNm = roleNm;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		addAssignParameters("roleDesc");
		this.roleDesc = roleDesc;
	}


}
