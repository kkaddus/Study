package com.dkunc.adm.ath.mnu.mapper;

import java.util.List;

import com.dkunc.adm.ath.mnu.vo.MenuInfoVO;
import com.dkunc.adm.ath.mnu.vo.SearchMenuVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="menuInfoReadMapper")
public interface MenuInfoReadMapper {

	/**
     * 기 등록된 Menu의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param searchMenuVO 검색조건
     * @return List<UserInfoVO> 기업회원 목록정보
     */
	public List<MenuInfoVO> selectMenuList(SearchMenuVO searchMenuVO);

    /**
     * Menu 총 갯수를 조회한다.
     * @param searchMenuVO 검색조건
     * @return int 일반회원총갯수
     */
    public int selectMenuListCnt(SearchMenuVO searchMenuVO);

    /**
     * Menu상세정보를 조회한다.
     * @param searchMenuVO 검색조건
     * @return Menu정보가져오기
     */
	public List<MenuInfoVO> selectMenuInfo(SearchMenuVO searchMenuVO) throws Exception;
	
}
