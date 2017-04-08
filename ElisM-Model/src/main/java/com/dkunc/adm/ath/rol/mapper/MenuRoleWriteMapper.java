package com.dkunc.adm.ath.rol.mapper;

import com.dkunc.adm.ath.rol.vo.MenuRoleVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="menuRoleWriteMapper")
public interface MenuRoleWriteMapper {
	public int insertMenuRole(MenuRoleVO vo) throws Exception;

	public int updateMenuRole(MenuRoleVO vo) throws Exception;

	public int deleteMenuRole(MenuRoleVO vo) throws Exception;

	public MenuRoleVO selectMenuRole(MenuRoleVO vo) throws Exception;
}
