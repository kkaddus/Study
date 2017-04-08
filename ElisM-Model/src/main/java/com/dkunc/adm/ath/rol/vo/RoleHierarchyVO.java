package com.dkunc.adm.ath.rol.vo;

import com.dkunc.cmn.vo.AbstractVO;

public class RoleHierarchyVO extends AbstractVO {

	private static final long serialVersionUID = -7648506815537314519L;
	String parentRole;
	String childrenRole;
	public String getParentRole() {
		return parentRole;
	}
	public void setParentRole(String parentRole) {
		addAssignParameters("parentRole");
		this.parentRole = parentRole;
	}
	public String getChildrenRole() {
		return childrenRole;
	}
	public void setChildrenRole(String childrenRole) {
		addAssignParameters("childrenRole");
		this.childrenRole = childrenRole;
	}


}
