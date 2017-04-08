package com.dkunc.adm.ath.mnu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.dkunc.adm.ath.mnu.service.MenuManageService;
import com.dkunc.adm.ath.mnu.vo.MenuInfoVO;
import com.dkunc.adm.ath.mnu.vo.SearchMenuVO;
import com.dkunc.cmn.EgovMessageSource;
import com.dkunc.cmn.lgn.vo.UserInfoVO;
import com.dkunc.cmn.service.EgovCmmUseService;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

/**
 * Menu관련 요청을  비지니스 클래스로 전달하고 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
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
@Controller
public class MenuManageController {

	/** menuManageService */
	@Resource(name = "menuManageService")
	private MenuManageService menuManageService;

	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** DefaultBeanValidator beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * Menu관리 Main Page
	 * @param model 화면모델
	 * @return adm/ath/mnu/menuInfoList
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/mnu/viewMenuInfoList.do")
	public String viewUserInfoList(@ModelAttribute("searchMenuVO") SearchMenuVO searchMenuVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
		
		return "adm/ath/mnu/menuInfoList.mg";
	}
	
	/**
	 * Menu 상세정보 JSON
	 * @param MenuInfoVO 검색조건정보
	 * @param model 화면모델
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/mnu/getMenuInfoJson.do", headers = "Accept=application/json")
	public String getMenuInfoJson(@ModelAttribute("menuInfoVO") MenuInfoVO menuInfoVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	try {
    		SearchMenuVO searchMenuVO = new SearchMenuVO();
    		searchMenuVO.setMenuId(menuInfoVO.getMenuId());
    		
    		model.addAttribute("menuInfoDetail", menuManageService.selectMenuInfo(searchMenuVO));
		} catch (Exception e) {
    		model.addAttribute("menuInfoDetail", "");
		}
    	return "jsonView";
	}
	
	/**
	 * Menu 등록/수정 처리
	 * @param menuInfoVO 검색조건정보
	 * @param MenuInfoVO Menu초기화정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/usr/viewUsprocessMenuInfoJsonerInfoInsert.do
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/mnu/processMenuInfoJson.do", headers = "Accept=application/json")
	public String processMenuInfoJson(@ModelAttribute("menuInfoVO") MenuInfoVO menuInfoVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	try {
    		if(menuInfoVO.getMode().equals("I")){
	    		SearchMenuVO searchMenuVO = new SearchMenuVO();
	    		searchMenuVO.setMenuId(menuInfoVO.getMenuId());
	    		List<MenuInfoVO> listMenuId = menuManageService.selectMenuInfo(searchMenuVO);
	    		if(listMenuId.size() > 0){
	    			model.addAttribute("result", listMenuId.size());
	    			model.addAttribute("resultMessage", "기 존재하는 Menu ID입니다.");
	    		}else{
	    			int result = menuManageService.insertMenuInfo(menuInfoVO);
	            	
	            	if(result > 0){
	        			model.addAttribute("result", result);
	        			model.addAttribute("resultMessage", "Insert Success");
	            	}else{
	        			model.addAttribute("result", result);
	        			model.addAttribute("resultMessage", "Insert Fail");
	            	}
	    		}
    		}else if(menuInfoVO.getMode().equals("D")){
    			int result = menuManageService.deleteAllMenuInfo(menuInfoVO);
            	
            	if(result > 0){
        			model.addAttribute("result", result);
        			model.addAttribute("resultMessage", "Delete Success");
            	}else{
        			model.addAttribute("result", result);
        			model.addAttribute("resultMessage", "Delete Fail");
            	}
    		}else if(menuInfoVO.getMode().equals("U")){
    			int result = menuManageService.updateMenuInfo(menuInfoVO);
            	
            	if(result > 0){
        			model.addAttribute("result", result);
        			model.addAttribute("resultMessage", "Update Success");
            	}else{
        			model.addAttribute("result", result);
        			model.addAttribute("resultMessage", "Update Fail");
            	}
    		}else{
    			model.addAttribute("result", "1");
    			model.addAttribute("resultMessage", "처리결과 없음");
    		}
		} catch (Exception e) {
			model.addAttribute("result", "Error");
			model.addAttribute("resultMessage", e.getMessage());
		}
    	
		return "jsonView";
	}
}