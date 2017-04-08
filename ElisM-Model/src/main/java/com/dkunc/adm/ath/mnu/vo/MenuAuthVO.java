package com.dkunc.adm.ath.mnu.vo;

import com.dkunc.cmn.vo.AbstractVO;

public class MenuAuthVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	int menuId;
	String author;

	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		addAssignParameters("menuId");
		this.menuId = menuId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		addAssignParameters("author");
		this.author = author;
	}
}
