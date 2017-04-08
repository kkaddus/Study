package com.dkunc.adm.ath.usr.mapper;

import java.util.List;

import com.dkunc.adm.ath.usr.vo.SearchUserVO;
import com.dkunc.cmn.lgn.vo.UserInfoVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper(value="userInfoReadMapper")
public interface UserInfoReadMapper {
	/**
     * 기 등록된 특정 일반회원의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param userSearchVO 검색조건
     * @return List<UserInfoVO> 기업회원 목록정보
     */
	public List<UserInfoVO> selectUserList(SearchUserVO searchUserVO);

    /**
     * 일반회원 총 갯수를 조회한다.
     * @param userSearchVO 검색조건
     * @return int 일반회원총갯수
     */
    public int selectUserListCnt(SearchUserVO searchUserVO);

    /**
	 * 회원상세 페이지
	 * @param UserInfoVO
	 * @return EgovMap 회원상세정보
	 */
	public EgovMap selectUserInfo(UserInfoVO userInfoVO);
	
    /**
     * 화면에 조회된 일반회원의 정보를 데이터베이스에서 삭제
     * @param delId 삭제 대상 일반회원아이디
     */	
    public void deleteMber_S(String delId);

    /**
     * 기 등록된 사용자 중 검색조건에 맞는일반회원의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param mberId 상세조회대상 일반회원아이디
     * @return UserInfoVO 일반회원 상세정보
     */
    public UserInfoVO selectMber_S(String mberId);

    /**
     * 화면에 조회된일반회원의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param UserInfoVO 일반회원수정정보
     */
    public void updateMber_S(UserInfoVO userInfoVO);

    /**
     * 일반회원 약관확인
     * @param stplatId 일반회원약관아이디
     * @return List 일반회원약관정보
     */
	public List<?> selectStplat_S(String stplatId);

    /**
     * 일반회원 암호수정
     * @param passVO 기업회원수정정보(비밀번호)
     */
    public void updatePassword_S(UserInfoVO passVO);
    
    /**
     * 일반회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
     * @param UserInfoVO 일반회원암호 조회조건정보
     * @return UserInfoVO 일반회원 암호정보
     */
    public UserInfoVO selectPassword_S(UserInfoVO userInfoVO);

    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param checkId 중복체크대상 아이디
     * @return int 사용가능여부(아이디 사용회수 )
     */
    public int checkIdDplct_S(String checkId);
}
