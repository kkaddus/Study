package com.dkunc.adm.ath.mnu.mapper;

import com.dkunc.adm.ath.mnu.vo.MenuInfoVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="menuInfoWriteMapper")
public interface MenuInfoWriteMapper {

	public int insertMenuInfo(MenuInfoVO vo) throws Exception;

	public int updateMenuInfo(MenuInfoVO vo) throws Exception;

	public int deleteMenuInfo(MenuInfoVO vo) throws Exception;

	public int deleteAllMenuInfo(MenuInfoVO vo) throws Exception;

}
