package com.dkunc.adm.ath.dpt.service;

import java.util.List;

import com.dkunc.adm.ath.dpt.vo.DeptInfoVO;
import com.dkunc.adm.ath.dpt.vo.SearchDeptVO;

/**
 * 부서관리에 관한 인터페이스클래스를 정의한다.
 * @author 김신재
 * @since 2017.03.17
 * @version 1.0
 * @see
 *
 */
public interface DeptManageService {

    /**
     * 부서상세정보를 조회한다.
     * @param searchDeptVO 검색조건
     * @return 부서정보가져오기
     */
	public List<DeptInfoVO> selectDeptInfo(SearchDeptVO searchDeptVO) throws Exception;


	/**
	 * 부서정보를 데이터베이스에 저장
	 * @param deptInfoVO 일반회원 등록정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	public int insertDeptInfo(DeptInfoVO deptInfoVO) throws Exception;

	/**
	 * 부서정보를 데이터베이스에 삭제
	 * @param deptInfoVO  dept 삭제정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	public int deleteDeptInfo(DeptInfoVO deptInfoVO) throws Exception;

	/**
	 * 부서정보를 데이터베이스에 수정
	 * @param deptInfoVO dept 수정정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	public int updateDeptInfo(DeptInfoVO deptInfoVO) throws Exception;

	/**
	 * Dept 자신 및 Sub Dept를 데이터베이스에 삭제
	 * @param deptInfoVO  dept 삭제정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	public int deleteAllDeptInfo(DeptInfoVO deptInfoVO) throws Exception;
}