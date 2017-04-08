package com.dkunc.cmn.lgn.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkunc.adm.ath.mnu.vo.MenuInfoVO;
import com.dkunc.cmn.lgn.mapper.UserInfoWriteMapper;
import com.dkunc.cmn.lgn.service.LoginService;
import com.dkunc.cmn.lgn.vo.UserInfoVO;
import com.dkunc.cmn.tre.mapper.CommonMapper;
import com.dkunc.let.utl.sim.service.FileScrty;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	/*@Resource(name="loginMapper")
    private LoginMapper loginMapper;*/
	@Autowired
	UserInfoWriteMapper userInfoWriteMapper;

	@Autowired
	CommonMapper commonMapper;

	@Override
	public UserInfoVO actionLogin(UserInfoVO vo) throws Exception {
		// 1. 입력한 비밀번호를 암호화한다.
    	String enpassword = FileScrty.encryptPassword(vo.getPwd(), vo.getUserId());
    	vo.setPwd(enpassword);

    	// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
    	UserInfoVO userInfoVO = userInfoWriteMapper.actionLogin(vo);

    	// 3. 결과를 리턴한다.
    	if (userInfoVO != null && !userInfoVO.getUserId().equals("") && !userInfoVO.getPwd().equals("")) {
    		return userInfoVO;
    	} else {
    		userInfoVO = new UserInfoVO();
    	}

    	return userInfoVO;
	}

	@Override
	public UserInfoVO searchId(UserInfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean searchPassword(UserInfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List selectMyRoleMenu(HashMap vo) {
		return commonMapper.selectMyRoleMenu(vo);
	}

}
