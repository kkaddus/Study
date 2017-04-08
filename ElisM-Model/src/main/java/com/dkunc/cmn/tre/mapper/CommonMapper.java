package com.dkunc.cmn.tre.mapper;

import java.util.HashMap;
import java.util.List;

import com.dkunc.adm.ath.dpt.vo.DeptInfoVO;
import com.dkunc.adm.ath.dpt.vo.SearchDeptVO;
import com.dkunc.adm.ath.mnu.vo.MenuInfoVO;
import com.dkunc.adm.ath.mnu.vo.SearchMenuVO;
import com.dkunc.cmn.lgn.vo.UserInfoVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="commonMapper")
public interface CommonMapper {

    /**
     * 부서 정보를 가져온다.
     * @param deptInfoVO 검색조건
     * @return 부서 정보
     * @throws Exception
     */
    public List<DeptInfoVO> selectDeptInfolazyTree(SearchDeptVO searchDeptVO);

    /**
     * 부서에 해당되는 사용자 리스트 가져오기
     * @param deptCd 중복체크대상 아이디
     * @return List
     */
	public List<UserInfoVO> selectUserInfolazyTree(String deptCd);

    /**
     * Menu 정보를 가져온다.
     * @param menuInfoVO 검색조건
     * @return Menu 정보
     * @throws Exception
     */
    public List<MenuInfoVO> selectMenuInfolazyTree(SearchMenuVO searchMenuVO);

    /**
     * Role에 해당하는 전체 저장된 Menu 정보를 가져온다.
     * @param roleCd 검색조건
     * @return List
     * @throws Exception
     */
    public List selectRoleMenuInfoTree(HashMap paramMap);
    
    public List selectMyRoleMenu(HashMap paramMap);
}
