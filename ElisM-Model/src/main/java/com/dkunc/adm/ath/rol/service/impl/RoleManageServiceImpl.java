package com.dkunc.adm.ath.rol.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dkunc.adm.ath.rol.mapper.MenuRoleWriteMapper;
import com.dkunc.adm.ath.rol.mapper.RoleInfoReadMapper;
import com.dkunc.adm.ath.rol.mapper.RoleInfoWriteMapper;
import com.dkunc.adm.ath.rol.mapper.UserRoleWriteMapper;
import com.dkunc.adm.ath.rol.service.RoleManageService;
import com.dkunc.adm.ath.rol.vo.MenuRoleVO;
import com.dkunc.adm.ath.rol.vo.RoleInfoVO;
import com.dkunc.adm.ath.rol.vo.UserRoleVO;
import com.dkunc.cmn.SearchDefaultVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 부서관리에 관한비지니스클래스를 정의한다.
 * @author 김신재
 * @since 2017.03.17
 * @version 1.0
 * @see
 *
 */
@Service("roleManageService")
public class RoleManageServiceImpl extends EgovAbstractServiceImpl implements RoleManageService {

	@Resource(name="roleInfoWriteMapper")
	private RoleInfoWriteMapper roleInfoWriteMapper;
	
	@Resource(name="roleInfoReadMapper")
	private RoleInfoReadMapper roleInfoReadMapper;
	
	@Resource(name="userRoleWriteMapper")
	private UserRoleWriteMapper userRoleWriteMapper;
	
	@Resource(name="menuRoleWriteMapper")
	private MenuRoleWriteMapper menuRoleWriteMapper;

	@Override
	public List<RoleInfoVO> selectRoleList(SearchDefaultVO searchDefaultVO) throws Exception {
		return roleInfoReadMapper.selectRoleList(searchDefaultVO);
	}

    @Override
	public int selectRoleListTotCnt(SearchDefaultVO searchDefaultVO) throws Exception {
    	return roleInfoReadMapper.selectRoleListTotCnt(searchDefaultVO);
    }
    
    @Override
	public int insertRoleInfo(RoleInfoVO roleInfoVO) throws Exception {
    	return roleInfoWriteMapper.insertRoleInfo(roleInfoVO);
    }
    
    @Override
	public int updateRoleInfo(RoleInfoVO roleInfoVO) throws Exception {
    	return roleInfoWriteMapper.updateRoleInfo(roleInfoVO);
    }
    
    @Override
   	public int deleteRoleInfo(RoleInfoVO roleInfoVO) throws Exception {
       	return roleInfoWriteMapper.deleteRoleInfo(roleInfoVO);
    }
    
    @Override
	public List selectRoleUserList(SearchDefaultVO searchDefaultVO) throws Exception {
		return roleInfoReadMapper.selectRoleUserList(searchDefaultVO);
	}
    
    @Override
	public int selectRoleUserListTotCnt(SearchDefaultVO searchDefaultVO) throws Exception {
    	return roleInfoReadMapper.selectRoleUserListTotCnt(searchDefaultVO);
    }
    
    @Override
	public int insertUserRole(UserRoleVO userRoleVO, String [] targetIdList) throws Exception {
    	int result = 0;
    	
    	try {
    		for(int i=0; i<targetIdList.length;i++) {
    			userRoleVO.setTargetId(targetIdList[i]);
        		result += userRoleWriteMapper.insertUserRole(userRoleVO);
        	}
        	return result;
    	} catch(Exception e ) {
    		return 0;
    	}
    	
    }
    
    @Override
   	public int deleteUserRole(UserRoleVO userRoleVO) throws Exception {
       	return userRoleWriteMapper.deleteUserRole(userRoleVO);
    }
    
    @Override
   	public int processMenuRole(String roleCd, String[] undetermined, String[] getselected) throws Exception {
    	int deResult = 0;
    	int inResult = 0;
    	MenuRoleVO vo = new MenuRoleVO();
    	try {
    		vo.setRoleCd(roleCd);
    		deResult = menuRoleWriteMapper.deleteMenuRole(vo);
    		
    		if(undetermined != null){
	    		for(int i=0; i<undetermined.length; i++){
	    			vo.setMenuId(undetermined[i]);
	    			inResult += menuRoleWriteMapper.insertMenuRole(vo);
	    		}
    		}
    		if(getselected != null){
	    		for(int i=0; i<getselected.length; i++){
	    			vo.setMenuId(getselected[i]);
	    			inResult += menuRoleWriteMapper.insertMenuRole(vo);
	    		}
    		}
    	} catch(Exception e ) {
    		return 0;
    	}

    	return inResult;
    }
}