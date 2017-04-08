package com.dkunc.adm.ath.dpt.mapper;

import java.util.List;

import com.dkunc.adm.ath.dpt.vo.DeptInfoVO;
import com.dkunc.adm.ath.dpt.vo.SearchDeptVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="deptInfoReadMapper")
public interface DeptInfoReadMapper {

    /**
     * 부서상세정보를 조회한다.
     * @param searchDeptVO 검색조건
     * @return 부서정보가져오기
     */
	public List<DeptInfoVO> selectDeptInfo(SearchDeptVO searchDeptVO) throws Exception;
}
