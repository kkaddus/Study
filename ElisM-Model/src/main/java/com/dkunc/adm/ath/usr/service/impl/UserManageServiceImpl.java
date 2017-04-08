package com.dkunc.adm.ath.usr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dkunc.adm.ath.usr.mapper.UserInfoReadMapper;
import com.dkunc.adm.ath.usr.service.UserManageService;
import com.dkunc.adm.ath.usr.vo.SearchUserVO;
import com.dkunc.cmn.lgn.mapper.UserInfoWriteMapper;
import com.dkunc.cmn.lgn.vo.UserInfoVO;
import com.dkunc.let.utl.sim.service.FileScrty;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 일반회원관리에 관한비지니스클래스를 정의한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  JJY            최초 생성
 *   2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 * </pre>
 */
@Service("userManageService")
public class UserManageServiceImpl extends EgovAbstractServiceImpl implements UserManageService {

	/** userInfoReadMapper */
	@Resource(name="userInfoReadMapper")
	private UserInfoReadMapper userInfoReadMapper;
	
	/** userInfoWriteMapper */
	@Resource(name="userInfoWriteMapper")
	private UserInfoWriteMapper userInfoWriteMapper;

	/** egovUsrCnfrmIdGnrService */
	@Resource(name="egovUsrCnfrmIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 사용자의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param userInfoVO 일반회원 등록정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	@Override
	public int insertUserInfo(UserInfoVO userInfoVO) throws Exception  {
		//패스워드 암호화
		String pass = FileScrty.encryptPassword(userInfoVO.getPwd(), userInfoVO.getUserId());
		userInfoVO.setPwd(pass);

		int result = userInfoWriteMapper.insertUserInfo(userInfoVO);
		return result;
	}

	/**
	 * 사용자의 기본정보를 수정화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param userInfoVO 일반회원 등록정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	@Override
	public int updateUserInfo(UserInfoVO userInfoVO) throws Exception  {
		int result = userInfoWriteMapper.updateUserInfo(userInfoVO);
		return result;
	}

	/**
	 * 기 등록된 사용자 중 검색조건에 맞는 일반회원의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * @param uniqId 상세조회대상 일반회원아이디
	 * @return userInfoVO 일반회원상세정보
	 * @throws Exception
	 */
	@Override
	public UserInfoVO selectMber(String uniqId) {
		UserInfoVO userInfoVO = userInfoReadMapper.selectMber_S(uniqId);
		return userInfoVO;
	}

	/**
	 * 기 등록된 회원 중 검색조건에 맞는 회원들의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * @param userSearchVO 검색조건
	 * @return List<userInfoVO> 일반회원목록정보
	 */
	@Override
	public List<UserInfoVO> selectUserList(SearchUserVO searchUserVO) {
		return userInfoReadMapper.selectUserList(searchUserVO);
	}

    /**
     * 일반회원 총 갯수를 조회한다.
     * @param userSearchVO 검색조건
     * @return 일반회원총갯수(int)
     */
    @Override
	public int selectUserListCnt(SearchUserVO searchUserVO) {
    	return userInfoReadMapper.selectUserListCnt(searchUserVO);
    }

	/**
	 * 회원상세 페이지
	 * @param userInfoVO
	 * @return EgovMap 회원상세정보
	 */
	@Override
	public EgovMap selectUserInfo(UserInfoVO userInfoVO) {
		return userInfoReadMapper.selectUserInfo(userInfoVO);
	}

	/**
	 * 화면에 조회된 일반회원의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * @param userInfoVO 일반회원수정정보
	 * @throws Exception
	 */
	@Override
	public void updateMber(UserInfoVO userInfoVO) throws Exception {
		//패스워드 암호화
		String pass = FileScrty.encryptPassword(userInfoVO.getPwd(), userInfoVO.getUserId());
		userInfoVO.setPwd(pass);
		userInfoReadMapper.updateMber_S(userInfoVO);
	}

	/**
	 * 화면에 조회된 사용자의 정보를 데이터베이스에서 삭제
	 * @param checkedIdForDel 삭제대상 일반회원아이디
	 * @throws Exception
	 */
	@Override
	public void deleteMber(String checkedIdForDel)  {
		String [] delId = checkedIdForDel.split(",");
		for (int i=0; i<delId.length ; i++){
			String [] id = delId[i].split(":");
			if (id[0].equals("USR03")){
		        //업무사용자(직원)삭제
			}else if(id[0].equals("USR01")){
				//일반회원삭제
				userInfoReadMapper.deleteMber_S(id[1]);
			}else if(id[0].equals("USR02")){
				//기업회원삭제
			}
		}
	}

	/**
	 * 일반회원 약관확인
	 * @param stplatId 일반회원약관아이디
	 * @return 일반회원약관정보(List)
	 * @throws Exception
	 */
	@Override
	public List<?> selectStplat(String stplatId)  {
        return userInfoReadMapper.selectStplat_S(stplatId);
	}

	/**
	 * 일반회원암호수정
	 * @param userInfoVO 일반회원수정정보(비밀번호)
	 * @throws Exception
	 */
	@Override
	public void updatePassword(UserInfoVO userInfoVO) {
		userInfoReadMapper.updatePassword_S(userInfoVO);
	}

	/**
	 * 일반회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
	 * @param passVO 일반회원암호 조회조건정보
	 * @return userInfoVO 일반회원암호정보
	 * @throws Exception
	 */
	@Override
	public UserInfoVO selectPassword(UserInfoVO passVO) {
		UserInfoVO userInfoVO = userInfoReadMapper.selectPassword_S(passVO);
		return userInfoVO;
	}

	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * @param checkId 중복여부 확인대상 아이디
	 * @return 사용가능여부(아이디 사용회수 int)
	 * @throws Exception
	 */
	@Override
	public int checkIdDplct(String checkId) {
		return userInfoReadMapper.checkIdDplct_S(checkId);
	}

}