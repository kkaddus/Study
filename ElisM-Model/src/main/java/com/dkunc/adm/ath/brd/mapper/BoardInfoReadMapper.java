package com.dkunc.adm.ath.brd.mapper;

import java.util.List;

import com.dkunc.adm.ath.brd.vo.BoardInfoVO;
import com.dkunc.cmn.SearchDefaultVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="boardInfoReadMapper")
public interface BoardInfoReadMapper {

	public List<BoardInfoVO> selectBoardList(SearchDefaultVO searchDefaultVO);

    public int selectBoardListTotCnt(SearchDefaultVO searchDefaultVO);
	
}
