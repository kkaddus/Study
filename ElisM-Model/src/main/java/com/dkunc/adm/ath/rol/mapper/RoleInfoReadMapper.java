package com.dkunc.adm.ath.rol.mapper;

import java.util.List;

import com.dkunc.adm.ath.rol.vo.RoleInfoVO;
import com.dkunc.adm.ath.rol.vo.UserRoleVO;
import com.dkunc.cmn.SearchDefaultVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="roleInfoReadMapper")
public interface RoleInfoReadMapper {

	public List<RoleInfoVO> selectRoleList(SearchDefaultVO searchDefaultVO) throws Exception;

	public int selectRoleListTotCnt(SearchDefaultVO searchDefaultVO) throws Exception;

	public List selectRoleUserList(SearchDefaultVO searchDefaultVO) throws Exception;

	public int selectRoleUserListTotCnt(SearchDefaultVO searchDefaultVO) throws Exception;
	
}
