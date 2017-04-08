package com.dkunc.cmn.tre.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dkunc.adm.ath.dpt.vo.DeptInfoVO;
import com.dkunc.adm.ath.dpt.vo.SearchDeptVO;
import com.dkunc.adm.ath.mnu.vo.MenuInfoVO;
import com.dkunc.adm.ath.mnu.vo.SearchMenuVO;
import com.dkunc.cmn.lgn.vo.UserInfoVO;
import com.dkunc.cmn.tre.mapper.CommonMapper;
import com.dkunc.cmn.tre.service.CommonService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("commonService")
public class CommonServiceImpl extends EgovAbstractServiceImpl implements CommonService {

	/** codeMasterWriteMapper */
	@Resource(name="commonMapper")
	private CommonMapper commonMapper;
	

    /**
     * 부서 정보를 가져온다.
     * @param deptInfoVO 검색조건
     * @return 부서 정보
     * @throws Exception
     */
    @Override
    public List<DeptInfoVO> selectDeptInfolazyTree(SearchDeptVO searchDeptVO) {
    	return commonMapper.selectDeptInfolazyTree(searchDeptVO);
    }

    /**
     * 부서에 해당되는 사용자 리스트 가져오기
     * @param deptCd 중복체크대상 아이디
     * @return List
     */
	@Override
	public List<UserInfoVO> selectUserInfolazyTree(String deptCd) throws Exception {
		return commonMapper.selectUserInfolazyTree(deptCd);
	}

    /**
     * Menu 정보를 가져온다.
     * @param menuInfoVO 검색조건
     * @return Menu 정보
     * @throws Exception
     */
    @Override
    public List<MenuInfoVO> selectMenuInfolazyTree(SearchMenuVO searchMenuVO) {
    	return commonMapper.selectMenuInfolazyTree(searchMenuVO);
    }

    /**
     * Role에 해당하는 전체 저장된 Menu 정보를 가져온다.
     * @param roleCd 검색조건
     * @return List
     * @throws Exception
     */
    @Override
    public List selectRoleMenuInfoTree(HashMap paramMap) {
    	return commonMapper.selectRoleMenuInfoTree(paramMap);
    }

}