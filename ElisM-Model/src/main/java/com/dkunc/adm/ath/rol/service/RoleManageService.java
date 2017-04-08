package com.dkunc.adm.ath.rol.service;

import java.util.List;

import com.dkunc.adm.ath.rol.vo.MenuRoleVO;
import com.dkunc.adm.ath.rol.vo.RoleInfoVO;
import com.dkunc.adm.ath.rol.vo.UserRoleVO;
import com.dkunc.cmn.SearchDefaultVO;

/**
 * 롤관리에 관한 인터페이스클래스를 정의한다.
 * @author 김신재
 * @since 2017.03.17
 * @version 1.0
 * @see
 *
 */
public interface RoleManageService {

	public List<RoleInfoVO> selectRoleList(SearchDefaultVO searchDefaultVO) throws Exception;

    public int selectRoleListTotCnt(SearchDefaultVO searchDefaultVO) throws Exception;
    
    public int insertRoleInfo(RoleInfoVO roleInfoVO) throws Exception;
    
    public int updateRoleInfo(RoleInfoVO roleInfoVO) throws Exception;
    
    public int deleteRoleInfo(RoleInfoVO roleInfoVO) throws Exception;
    
    public List selectRoleUserList(SearchDefaultVO searchDefaultVO) throws Exception;
    
    public int selectRoleUserListTotCnt(SearchDefaultVO searchDefaultVO) throws Exception;
    
    public int insertUserRole(UserRoleVO userRoleVO, String [] targetIdList) throws Exception;
    
    public int deleteUserRole(UserRoleVO userRoleVO) throws Exception;
    
    public int processMenuRole(String roleCd, String[] undetermined, String[] getselected) throws Exception;

}