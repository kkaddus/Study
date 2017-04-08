package com.dkunc.adm.ath.mnu.service;

import java.util.List;

import com.dkunc.adm.ath.mnu.vo.MenuInfoVO;
import com.dkunc.adm.ath.mnu.vo.SearchMenuVO;
import com.dkunc.cmn.lgn.vo.UserInfoVO;
import com.dkunc.let.utl.sim.service.FileScrty;

/**
 * Menu관리에 관한 인터페이스클래스를 정의한다.
 * @author KSJ
 * @since 2017.03.24
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2017.03.24  KSJ          최초 생성
 *
 * </pre>
 */
public interface MenuManageService {

	/**
	 * 기 등록된 Menu 중 검색조건에 맞는 Menu들의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * @param searchMenuVO 검색조건
	 * @return List<userInfoVO> 일반회원목록정보
	 * @throws Exception
	 */
	public List<MenuInfoVO> selectMenuList(SearchMenuVO searchMenuVO) throws Exception;

    /**
     * Menu 총 갯수를 조회한다.
     * @param searchMenuVO 검색조건
     * @return 일반회원총갯수(int)
     * @throws Exception
     */
    public int selectMenuListCnt(SearchMenuVO searchMenuVO) throws Exception;

	/**
     * Menu상세정보를 조회한다.
     * @param searchMenuVO 검색조건
     * @return Menu정보가져오기
     */
	public List<MenuInfoVO> selectMenuInfo(SearchMenuVO searchMenuVO) throws Exception;

	/**
	 * Menu정보를 데이터베이스에 저장
	 * @param menuInfoVO 일반회원 등록정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	public int insertMenuInfo(MenuInfoVO menuInfoVO) throws Exception;

	/**
	 * Menu정보를 데이터베이스에 삭제
	 * @param menuInfoVO  Menu 삭제정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	public int deleteMenuInfo(MenuInfoVO menuInfoVO) throws Exception;

	/**
	 * Menu정보를 데이터베이스에 수정
	 * @param menuInfoVO Menu 수정정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	public int updateMenuInfo(MenuInfoVO menuInfoVO) throws Exception;

	/**
	 * Menu 자신 및 Sub MENU를 데이터베이스에 삭제
	 * @param menuInfoVO  Menu 삭제정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	public int deleteAllMenuInfo(MenuInfoVO menuInfoVO) throws Exception;
}