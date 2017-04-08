package com.dkunc.cmn.lgn.service;

import java.util.HashMap;
import java.util.List;

import com.dkunc.cmn.lgn.vo.UserInfoVO;

public interface LoginService {
	/**
	 * 일반 로그인을 처리한다
	 * @return LoginVO
	 *
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public UserInfoVO actionLogin(UserInfoVO vo) throws Exception;

	/**
	 * 아이디를 찾는다.
	 * @return LoginVO
	 *
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public UserInfoVO searchId(UserInfoVO vo) throws Exception;

	/**
	 * 비밀번호를 찾는다.
	 * @return boolean
	 *
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public boolean searchPassword(UserInfoVO vo) throws Exception;
	
	public List selectMyRoleMenu(HashMap vo) throws Exception;
}
