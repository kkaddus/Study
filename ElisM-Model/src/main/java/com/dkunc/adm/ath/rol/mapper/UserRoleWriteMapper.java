package com.dkunc.adm.ath.rol.mapper;

import com.dkunc.adm.ath.rol.vo.UserRoleVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="userRoleWriteMapper")
public interface UserRoleWriteMapper {

	public int insertUserRole(UserRoleVO vo) throws Exception;

	public int updateUserRole(UserRoleVO vo) throws Exception;

	public int deleteUserRole(UserRoleVO vo) throws Exception;

	public UserRoleVO selectRoleHierachy(UserRoleVO vo) throws Exception;
}
