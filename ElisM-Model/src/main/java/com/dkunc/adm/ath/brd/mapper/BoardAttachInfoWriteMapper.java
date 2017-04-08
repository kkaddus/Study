package com.dkunc.adm.ath.brd.mapper;

import com.dkunc.adm.ath.brd.vo.BoardAttachInfoVO;
import com.dkunc.adm.ath.brd.vo.BoardInfoVO;
import com.dkunc.adm.ath.dpt.vo.DeptInfoVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="boardAttachInfoWriteMapper")
public interface BoardAttachInfoWriteMapper {

	public int insertBoardAttachInfo(BoardAttachInfoVO vo) throws Exception;

	
}
