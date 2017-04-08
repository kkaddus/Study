package com.dkunc.adm.ath.dpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dkunc.adm.ath.dpt.mapper.DeptInfoReadMapper;
import com.dkunc.adm.ath.dpt.mapper.DeptInfoWriteMapper;
import com.dkunc.adm.ath.dpt.service.DeptManageService;
import com.dkunc.adm.ath.dpt.vo.DeptInfoVO;
import com.dkunc.adm.ath.dpt.vo.SearchDeptVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 부서관리에 관한비지니스클래스를 정의한다.
 * @author 김신재
 * @since 2017.03.17
 * @version 1.0
 * @see
 *
 */
@Service("deptManageService")
public class DeptManageServiceImpl extends EgovAbstractServiceImpl implements DeptManageService {

	/** deptInfoReadMapper */
	@Resource(name="deptInfoReadMapper")
	private DeptInfoReadMapper deptInfoReadMapper;

	/** deptInfoWriteMapper */
	@Resource(name="deptInfoWriteMapper")
	private DeptInfoWriteMapper deptInfoWriteMapper;
	
    /**
     * Dept 정보를 조회한다.
     * @param searchDeptVO 검색조건
     * @return 부서정보
     */
	@Override
	public List<DeptInfoVO> selectDeptInfo(SearchDeptVO searchDeptVO) throws Exception {
		return deptInfoReadMapper.selectDeptInfo(searchDeptVO);
	}
	
	/**
	 * Dept 정보를 데이터베이스에 저장
	 * @param deptInfoVO 일반회원 등록정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	@Override
	public int insertDeptInfo(DeptInfoVO deptInfoVO) throws Exception  {
		return deptInfoWriteMapper.insertDeptInfo(deptInfoVO);
	}
	
	/**
	 * Dept 정보를 데이터베이스에 삭제
	 * @param deptInfoVO  Dept 삭제정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	@Override
	public int deleteDeptInfo(DeptInfoVO deptInfoVO) throws Exception  {
		return deptInfoWriteMapper.deleteDeptInfo(deptInfoVO);
	}
	
	/**
	 * Dept 정보를 데이터베이스에 수정
	 * @param deptInfoVO  Dept 수정정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	@Override
	public int updateDeptInfo(DeptInfoVO deptInfoVO) throws Exception  {
		return deptInfoWriteMapper.updateDeptInfo(deptInfoVO);
	}
	
	/**
	 * Dept 자신 및 Sub Dept를 데이터베이스에 삭제
	 * @param deptInfoVO  Dept 삭제정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	@Override
	public int deleteAllDeptInfo(DeptInfoVO deptInfoVO) throws Exception  {
		return deptInfoWriteMapper.deleteAllDeptInfo(deptInfoVO);
	}
}