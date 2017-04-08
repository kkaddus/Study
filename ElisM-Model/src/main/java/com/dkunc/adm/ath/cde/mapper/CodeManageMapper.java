package com.dkunc.adm.ath.cde.mapper;

import java.util.List;
import java.util.Map;

import com.dkunc.adm.ath.cde.vo.CodeManageVO;
import com.dkunc.cmn.SearchDefaultVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="codeManageMapper")
public interface CodeManageMapper {
	
	/**
     * 코드 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param userSearchVO 검색조건
     * @return List<CodeManageVO> 코드 목록정보
     */
	public List<CodeManageVO> selectCodeList(SearchDefaultVO searchDefaultVO);

    /**
     * 코드 총 갯수를 조회한다.
     * @param userSearchVO 검색조건
     * @return int 코드총갯수
     */
    public int selectCodeListTotCnt(SearchDefaultVO searchDefaultVO);
    
    /**
     * 입력한 코드의 중복여부를 체크하여 사용가능여부를 확인
     * @param checkId 중복체크대상 아이디
     * @return int 사용가능여부(아이디 사용회수 )
     */
    public int checkIdDplct(String checkId);
    
    /**
     * 코드Detail 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param userSearchVO 검색조건
     * @return List<CodeManageVO> 코드 Detail목록정보
     */
	public List<CodeManageVO> selectCodeDetailList(SearchDefaultVO searchDefaultVO);

    /**
     * 코드Detail 총 갯수를 조회한다.
     * @param userSearchVO 검색조건
     * @return int 코드Detail 총갯수
     */
    public int selectCodeDetailListTotCnt(SearchDefaultVO searchDefaultVO);
    
    /**
     * 입력한 Detail코드의 중복여부를 체크하여 사용가능여부를 확인
     * @param checkId 중복체크대상 아이디
     * @return int 사용가능여부(아이디 사용회수 )
     */
    public int checkDescDplct(Map checkMap);
	
}
