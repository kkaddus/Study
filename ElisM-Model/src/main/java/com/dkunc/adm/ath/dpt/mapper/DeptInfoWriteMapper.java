package com.dkunc.adm.ath.dpt.mapper;

import com.dkunc.adm.ath.dpt.vo.DeptInfoVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="deptInfoWriteMapper")
public interface DeptInfoWriteMapper {

	public int insertDeptInfo(DeptInfoVO vo) throws Exception;

	public int updateDeptInfo(DeptInfoVO vo) throws Exception;

	public int deleteDeptInfo(DeptInfoVO vo) throws Exception;

	public DeptInfoVO selectDeptInfo(DeptInfoVO vo) throws Exception;

	public int deleteAllDeptInfo(DeptInfoVO vo) throws Exception;
}
