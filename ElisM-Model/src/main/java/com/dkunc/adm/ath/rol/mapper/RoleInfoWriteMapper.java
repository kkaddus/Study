package com.dkunc.adm.ath.rol.mapper;

import com.dkunc.adm.ath.rol.vo.RoleInfoVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="roleInfoWriteMapper")
public interface RoleInfoWriteMapper {

	public int insertRoleInfo(RoleInfoVO vo) throws Exception;

	public int updateRoleInfo(RoleInfoVO vo) throws Exception;

	public int deleteRoleInfo(RoleInfoVO vo) throws Exception;

	public RoleInfoVO selectRoleInfo(RoleInfoVO vo) throws Exception;
}
