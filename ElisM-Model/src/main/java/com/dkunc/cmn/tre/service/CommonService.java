package com.dkunc.cmn.tre.service;

import java.util.HashMap;
import java.util.List;

import com.dkunc.adm.ath.dpt.vo.DeptInfoVO;
import com.dkunc.adm.ath.dpt.vo.SearchDeptVO;
import com.dkunc.adm.ath.mnu.vo.MenuInfoVO;
import com.dkunc.adm.ath.mnu.vo.SearchMenuVO;
import com.dkunc.cmn.lgn.vo.UserInfoVO;

/**
 * 코드관리에 관한 인터페이스클래스를 정의한다.
 * @author 강호진
 * @since 2017.03.20
 * @version 1.0
 * @see
 *
 */
public interface CommonService {
    /**
     * 부서 정보를 가져온다.
     * @param deptInfoVO 검색조건
     * @return 부서 정보
     * @throws Exception
     */
	public List<DeptInfoVO> selectDeptInfolazyTree(SearchDeptVO searchDeptVO) throws Exception;
	
    /**
     * 부서에 해당되는 사용자 리스트 가져오기
     * @param deptCd 중복체크대상 아이디
     * @return List
     */
	public List<UserInfoVO> selectUserInfolazyTree(String deptCd) throws Exception;
	
    /**
     * Menu 정보를 가져온다.
     * @param menuInfoVO 검색조건
     * @return Menu 정보
     * @throws Exception
     */
	public List<MenuInfoVO> selectMenuInfolazyTree(SearchMenuVO searchMenuVO) throws Exception;

    /**
     * Role에 해당하는 전체 저장된 Menu 정보를 가져온다.
     * @param roleCd 검색조건
     * @return List
     * @throws Exception
     */
	public List selectRoleMenuInfoTree(HashMap paramMap) throws Exception;
}
