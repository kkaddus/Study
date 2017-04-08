package com.dkunc.adm.ath.mnu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dkunc.adm.ath.mnu.mapper.MenuInfoReadMapper;
import com.dkunc.adm.ath.mnu.mapper.MenuInfoWriteMapper;
import com.dkunc.adm.ath.mnu.service.MenuManageService;
import com.dkunc.adm.ath.mnu.vo.MenuInfoVO;
import com.dkunc.adm.ath.mnu.vo.SearchMenuVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * Menu관리에 관한 비지니스클래스를 정의한다.
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
@Service("menuManageService")
public class MenuManageServiceImpl extends EgovAbstractServiceImpl implements MenuManageService {

	/** menuInfoReadMapper */
	@Resource(name="menuInfoReadMapper")
	private MenuInfoReadMapper menuInfoReadMapper;
	
	/** menuInfoWriteMapper */
	@Resource(name="menuInfoWriteMapper")
	private MenuInfoWriteMapper menuInfoWriteMapper;

	/** egovUsrCnfrmIdGnrService */
	@Resource(name="egovUsrCnfrmIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 기 등록된 Menu 중 검색조건에 맞는 Menu들의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * @param searchMenuVO 검색조건
	 * @return List<MenuInfoVO> 일반회원목록정보
	 */
	@Override
	public List<MenuInfoVO> selectMenuList(SearchMenuVO searchMenuVO) {
		return menuInfoReadMapper.selectMenuList(searchMenuVO);
	}

    /**
     * Menu 총 갯수를 조회한다.
     * @param searchMenuVO 검색조건
     * @return 일반회원총갯수(int)
     */
    @Override
	public int selectMenuListCnt(SearchMenuVO searchMenuVO) {
    	return menuInfoReadMapper.selectMenuListCnt(searchMenuVO);
    }

    /**
     * Menu 정보를 조회한다.
     * @param searchMenuVO 검색조건
     * @return 일반회원총갯수(int)
     */
	@Override
	public List<MenuInfoVO> selectMenuInfo(SearchMenuVO searchMenuVO) throws Exception {
		return menuInfoReadMapper.selectMenuInfo(searchMenuVO);
	}
	
	/**
	 * Menu정보를 데이터베이스에 저장
	 * @param menuInfoVO 일반회원 등록정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	@Override
	public int insertMenuInfo(MenuInfoVO menuInfoVO) throws Exception  {
		return menuInfoWriteMapper.insertMenuInfo(menuInfoVO);
	}
	
	/**
	 * Menu정보를 데이터베이스에 삭제
	 * @param menuInfoVO  Menu 삭제정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	@Override
	public int deleteMenuInfo(MenuInfoVO menuInfoVO) throws Exception  {
		return menuInfoWriteMapper.deleteMenuInfo(menuInfoVO);
	}
	
	/**
	 * Menu정보를 데이터베이스에 수정
	 * @param menuInfoVO  Menu 수정정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	@Override
	public int updateMenuInfo(MenuInfoVO menuInfoVO) throws Exception  {
		return menuInfoWriteMapper.updateMenuInfo(menuInfoVO);
	}
	
	/**
	 * Menu 자신 및 Sub MENU를 데이터베이스에 삭제
	 * @param menuInfoVO  Menu 삭제정보
	 * @return result 등록결과
	 * @throws Exception
	 */
	@Override
	public int deleteAllMenuInfo(MenuInfoVO menuInfoVO) throws Exception  {
		return menuInfoWriteMapper.deleteAllMenuInfo(menuInfoVO);
	}
}