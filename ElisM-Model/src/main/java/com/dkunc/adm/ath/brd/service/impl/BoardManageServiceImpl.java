package com.dkunc.adm.ath.brd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dkunc.adm.ath.brd.mapper.BoardAttachInfoWriteMapper;
import com.dkunc.adm.ath.brd.mapper.BoardInfoReadMapper;
import com.dkunc.adm.ath.brd.mapper.BoardInfoWriteMapper;
import com.dkunc.adm.ath.brd.service.BoardManageService;
import com.dkunc.adm.ath.brd.vo.BoardAttachInfoVO;
import com.dkunc.adm.ath.brd.vo.BoardInfoVO;
import com.dkunc.cmn.SearchDefaultVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 부서관리에 관한비지니스클래스를 정의한다.
 * @author 김신재
 * @since 2017.03.17
 * @version 1.0
 * @see
 *
 */
@Service("boardManageService")
public class BoardManageServiceImpl extends EgovAbstractServiceImpl implements BoardManageService {

	/** boardInfoWriteMapper */
	@Resource(name="boardInfoWriteMapper")
	private BoardInfoWriteMapper boardInfoWriteMapper;
	
	/** boardInfoWriteMapper */
	@Resource(name="boardAttachInfoWriteMapper")
	private BoardAttachInfoWriteMapper boardAttachInfoWriteMapper;
	
	@Resource(name="boardInfoReadMapper")
	private BoardInfoReadMapper boardInfoReadMapper;
	

	@Override
	public int insertBoardInfo(BoardInfoVO boardInfoVO) throws Exception  {

		int result = boardInfoWriteMapper.insertBoardInfo(boardInfoVO);
		return result;
	}
	
	@Override
	public int insertBoardAttachInfo(BoardAttachInfoVO boardAttachInfoVO) throws Exception  {

		int result = boardAttachInfoWriteMapper.insertBoardAttachInfo(boardAttachInfoVO);
		return result;
	}
	
	/**
     * 게시판 목록을 조회한다.
     * @param searchDefaultVO 검색조건
     * @return 부서정보가져오기
     */
	@Override
	public List<BoardInfoVO> selectBoardList(SearchDefaultVO searchDefaultVO) {
		return boardInfoReadMapper.selectBoardList(searchDefaultVO);
	}

    /**
     * 게시글 총 갯수를 조회한다.
     * @param searchDefaultVO 검색조건
     * @return 부서총갯수(int)
     */
    @Override
	public int selectBoardListTotCnt(SearchDefaultVO searchDefaultVO) {
    	return boardInfoReadMapper.selectBoardListTotCnt(searchDefaultVO);
    }
    
    @Override
   	public int updateBoardInfo(BoardInfoVO boardInfoVO) throws Exception {
    	int result = boardInfoWriteMapper.updateBoardInfo(boardInfoVO);
		return result;
    }
}