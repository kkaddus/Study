package com.dkunc.cmn.lgn.mapper;

import java.util.List;

import com.dkunc.cmn.ComDefaultCodeVO;
import com.dkunc.cmn.lgn.vo.UserInfoVO;
import com.dkunc.cmn.service.CmmnDetailCode;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="userInfoWriteMapper")
public interface UserInfoWriteMapper {

	/**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
   public UserInfoVO actionLogin(UserInfoVO vo) throws Exception;

   /**
	 * 아이디를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
   public UserInfoVO searchId(UserInfoVO vo) throws Exception;

   /**
	 * 비밀번호를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
   public UserInfoVO searchPassword(UserInfoVO vo) throws Exception;

   /**
	 * 변경된 비밀번호를 저장한다.
	 * @param vo LoginVO
	 * @exception Exception
	 */
   public void updatePassword(UserInfoVO vo) throws Exception;

   public UserInfoVO selectUserInfo(UserInfoVO vo) throws Exception;

   /**
	 * 사용자 정보를 저장한다.
	 * @param vo UserInfoVO
	 * @exception Exception
	 */
   public int insertUserInfo(UserInfoVO vo) throws Exception;

   /**
	 * 사용자 정보를 수정한다.
	 * @param vo UserInfoVO
	 * @exception Exception
	 */
   public int updateUserInfo(UserInfoVO vo) throws Exception;
}
