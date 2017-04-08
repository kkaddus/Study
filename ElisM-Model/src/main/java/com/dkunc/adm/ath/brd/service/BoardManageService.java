package com.dkunc.adm.ath.brd.service;

import java.util.List;

import com.dkunc.adm.ath.brd.vo.BoardAttachInfoVO;
import com.dkunc.adm.ath.brd.vo.BoardInfoVO;
import com.dkunc.cmn.SearchDefaultVO;

/**
 * 게시판 관리에 관한 인터페이스클래스를 정의한다.
 * @author 김신재
 * @since 2017.03.17
 * @version 1.0
 * @see
 *
 */
public interface BoardManageService {

    /**
     * 게시글을 작성한다.
     */
	public int insertBoardInfo(BoardInfoVO bardInfoVO) throws Exception;
	
	/**
     * 게시글 첨부파일을 추가한다.
     */
	public int insertBoardAttachInfo(BoardAttachInfoVO boardAttachInfoVO) throws Exception;
	
	
	 /**
     * 게시판 목록을 조회한다.
     * @param searchDefaultVO 검색조건
     * @return 부서정보가져오기
     */
	public List<BoardInfoVO> selectBoardList(SearchDefaultVO searchDefaultVO) throws Exception;

    /**
     * 게시글  총 갯수를 조회한다.
     * @param deptSearchVO 검색조건
     * @return 부서 정보총갯수(int)
     * @throws Exception
     */
    public int selectBoardListTotCnt(SearchDefaultVO searchDefaultVO) throws Exception;

    public int updateBoardInfo(BoardInfoVO bardInfoVO) throws Exception;
}