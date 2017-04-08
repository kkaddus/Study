package com.dkunc.adm.ath.cde.service;

import java.util.List;
import java.util.Map;

import com.dkunc.adm.ath.cde.vo.CodeDetailVO;
import com.dkunc.adm.ath.cde.vo.CodeManageVO;
import com.dkunc.adm.ath.cde.vo.CodeMasterVO;
import com.dkunc.cmn.SearchDefaultVO;

/**
 * 코드관리에 관한 인터페이스클래스를 정의한다.
 * @author 강호진
 * @since 2017.03.20
 * @version 1.0
 * @see
 *
 */
public interface CodeManageService {

	/**
     * 코드정보를 조회한다.
     * @param searchDefaultVO 검색조건
     * @return 코드정보가져오기
     */
	public List<CodeManageVO> selectCodeList(SearchDefaultVO searchDefaultVO) throws Exception;

    /**
     * 코드 정보 총 갯수를 조회한다.
     * @param deptSearchVO 검색조건
     * @return 코드 정보총갯수(int)
     * @throws Exception
     */
    public int selectCodeListTotCnt(SearchDefaultVO searchDefaultVO) throws Exception;
    
    /**
	 * 입력한 코드의 중복여부를 체크하여 사용가능여부를 확인
	 * @param checkId 중복여부 확인대상 아이디
	 * @return 사용가능여부(아이디 사용회수 int)
	 * @throws Exception
	 */
	public int checkIdDplct(String checkId) throws Exception;
	
	/**
	 * 코드정보를 데이터베이스에 저장
	 * @param codeMasterVO 코드정보
	 * @return 등록결과
	 * @throws Exception
	 */
	public int insertCodeMaster(CodeMasterVO codeMasterVO) throws Exception;
	
	/**
	 * 코드정보 상세보기
	 * @param codeMasterVO 코드정보
	 * @return 코드정보
	 * @throws Exception
	 */
	public CodeMasterVO selectCodeMaster(CodeMasterVO codeMasterVO) throws Exception;
	
	/**
	 * 코드정보 수정
	 * @param codeMasterVO 코드정보
	 * @return 수정결과
	 * @throws Exception
	 */
	public int updateCodeMaster(CodeMasterVO codeMasterVO) throws Exception;
	
	/**
	 * 코드정보 삭제
	 * @param codeMasterVO 코드정보
	 * @return 삭제결과
	 * @throws Exception
	 */
	public int deleteCodeMaster(CodeMasterVO codeMasterVO) throws Exception;
	
	/**
     * 코드 Detail정보를 조회한다.
     * @param searchDefaultVO 검색조건
     * @return 코드 Detail정보를가져오기
     */
	public List<CodeManageVO> selectCodeDetailList(SearchDefaultVO searchDefaultVO) throws Exception;

    /**
     * 코드 Detail정보 총 갯수를 조회한다.
     * @param deptSearchVO 검색조건
     * @return 코드 Detail정보총갯수(int)
     * @throws Exception
     */
    public int selectCodeDetailListTotCnt(SearchDefaultVO searchDefaultVO) throws Exception;
    
    /**
	 * 입력한 Detail코드의 중복여부를 체크하여 사용가능여부를 확인
	 * @param checkId 중복여부 확인대상 아이디
	 * @return 사용가능여부(아이디 사용회수 int)
	 * @throws Exception
	 */
	public int checkDescDplct(Map checkMap) throws Exception;
	
	/**
	 * 코드 Detail정보를 데이터베이스에 저장
	 * @param codeDetailVO Detail코드정보
	 * @return 등록결과
	 * @throws Exception
	 */
	public int insertCodeDetail(CodeDetailVO codeDetailVO) throws Exception;
	
	/**
	 * 코드 Detail정보 상세보기
	 * @param codeDetailVO 코드 Detail정보
	 * @return 코드 Detail정보
	 * @throws Exception
	 */
	public CodeDetailVO selectCodeDetail(CodeDetailVO codeDetailVO) throws Exception;
	
	/**
	 * 코드 Detail정보 수정
	 * @param codeDetailVO 코드 Detail정보
	 * @return 수정결과
	 * @throws Exception
	 */
	public int updateCodeDetail(CodeDetailVO codeDetailVO) throws Exception;
	
	/**
	 * 코드 Detail정보 삭제
	 * @param codeDetailVO 코드 Detail정보
	 * @return 삭제결과
	 * @throws Exception
	 */
	public int deleteCodeDetail(CodeDetailVO codeDetailVO) throws Exception;
}
