package com.dkunc.adm.ath.rol.mapper;

import com.dkunc.adm.ath.rol.vo.RoleHierarchyVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="roleHierarchyWriteMapper")
public interface RoleHierarchyWriteMapper {
	public int insertRoleHierachy(RoleHierarchyVO vo) throws Exception;

	public int updateRoleHierachy(RoleHierarchyVO vo) throws Exception;

	public int deleteRoleHierachy(RoleHierarchyVO vo) throws Exception;

	public RoleHierarchyVO selectRoleHierachy(RoleHierarchyVO vo) throws Exception;
}
