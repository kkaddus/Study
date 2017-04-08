package com.dkunc.adm.ath.rol.vo;

import com.dkunc.cmn.vo.AbstractVO;

public class UserRoleVO extends AbstractVO {

	private static final long serialVersionUID = -3076236399993184070L;
	String roleCd;
	String targetId;
	String targetType;

	public String getRoleCd() {
		return roleCd;
	}
	public void setRoleCd(String roleCd) {
		addAssignParameters("roleCd");
		this.roleCd = roleCd;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		addAssignParameters("targetId");
		this.targetId = targetId;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		addAssignParameters("targetType");
		this.targetType = targetType;
	}


}
