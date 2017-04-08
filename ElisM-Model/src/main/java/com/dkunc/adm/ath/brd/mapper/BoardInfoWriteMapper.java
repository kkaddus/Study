package com.dkunc.adm.ath.brd.mapper;

import com.dkunc.adm.ath.brd.vo.BoardInfoVO;
import com.dkunc.adm.ath.dpt.vo.DeptInfoVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="boardInfoWriteMapper")
public interface BoardInfoWriteMapper {

	public int insertBoardInfo(BoardInfoVO vo) throws Exception;

	public int updateBoardInfo(BoardInfoVO vo) throws Exception;

	public Object deleteDeptInfo(BoardInfoVO vo) throws Exception;

	public DeptInfoVO selectDeptInfo(BoardInfoVO vo) throws Exception;
	
}
