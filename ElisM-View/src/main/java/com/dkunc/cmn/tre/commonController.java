package com.dkunc.cmn.tre;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dkunc.adm.ath.dpt.vo.DeptInfoVO;
import com.dkunc.adm.ath.dpt.vo.SearchDeptVO;
import com.dkunc.adm.ath.mnu.vo.MenuInfoVO;
import com.dkunc.adm.ath.mnu.vo.SearchMenuVO;
import com.dkunc.cmn.EgovMessageSource;
import com.dkunc.cmn.tre.service.CommonService;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 코드관련 요청을  비지니스 클래스로 전달하고 처리된결과를  해당 웹 화면으로 전달하는  Controller를 정의한다
 * @author 강호진
 * @since 2017.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   
 *
 * </pre>
 */
@Controller
public class commonController {

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** deptManageService */
	@Resource(name = "commonService")
	private CommonService commonService;
	
	/**
	 * 부서 트리 페이지
	 * @param searchDeptVO 검색조건정보
	 * @param model 화면모델
	 * @return cmn/tre/getDeptInfolazyTree
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmn/tre/getDeptInfolazyTree.do")
	public String getDeptInfolazyTree(@ModelAttribute("deptInfoVO") DeptInfoVO searchDeptVO, HttpServletRequest request, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	model.addAttribute("mode", request.getParameter("mode"));
    	return "cmn/tre/getDeptInfolazyTree.md";
	}
	
	/**
	 * 부서 트리 JSON
	 * @param searchDeptVO 검색조건정보
	 * @param model 화면모델
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmn/tre/getDeptInfolazyTreeJson.do")
	public String getDeptInfolazyTreeJson(@ModelAttribute("deptInfoVO") DeptInfoVO deptInfoVO, ModelMap model, HttpServletResponse response) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
        StringBuilder resultStr = new StringBuilder();
    	try {
    		SearchDeptVO searchDeptVO = new SearchDeptVO();
    		searchDeptVO.setCompanyCd(deptInfoVO.getCompanyCd());
    		searchDeptVO.setDeptCd(deptInfoVO.getDeptCd());
    		searchDeptVO.setUpDeptCd(deptInfoVO.getUpDeptCd());
    		
    		
        	List lDeptTree = commonService.selectDeptInfolazyTree(searchDeptVO);

        	if(lDeptTree.size() > 0){
	    		resultStr.append("[");
	    		resultStr.append(mergelazyTree(lDeptTree));
	    		resultStr.append("]");
	    		
	    		model.addAttribute("lDeptTree", resultStr);
    		}else{
        		model.addAttribute("lDeptTree", "[]");
    		}
		} catch (Exception e) {
    		model.addAttribute("lDeptTree", "[]");
		}
    	return "jsonView";
	}

	/**
	 * 사용자 트리 페이지
	 * @param searchDeptVO 검색조건정보
	 * @param model 화면모델
	 * @return cmn/tre/getUserInfolazyTree
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmn/tre/getUserInfolazyTree.do")
	public String getUserInfolazyTree(@ModelAttribute("deptInfoVO") DeptInfoVO searchDeptVO, HttpServletRequest request, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	model.addAttribute("mode", request.getParameter("mode"));
    	return "cmn/tre/getUserInfolazyTree.md";
	}
	
	/**
	 * 사용자 트리 JSON
	 * @param searchDeptVO 검색조건정보
	 * @param model 화면모델
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmn/tre/getUserInfolazyTreeJson.do")
	public String getUserInfolazyTreeJson(@ModelAttribute("deptInfoVO") DeptInfoVO deptInfoVO, ModelMap model, HttpServletResponse response) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
        StringBuilder resultStr = new StringBuilder();
    	try {
    		SearchDeptVO searchDeptVO = new SearchDeptVO();
    		searchDeptVO.setCompanyCd(deptInfoVO.getCompanyCd());
    		searchDeptVO.setDeptCd(deptInfoVO.getDeptCd());
    		searchDeptVO.setUpDeptCd(deptInfoVO.getUpDeptCd());
    		
        	List lDeptTree = commonService.selectDeptInfolazyTree(searchDeptVO);
        	List UserInfoTree = null;
        	if(!searchDeptVO.getDeptCd().equals("ROOT")){
        		UserInfoTree = commonService.selectUserInfolazyTree(searchDeptVO.getUpDeptCd());
        	}
        	
        	resultStr.append("[");

    		if(UserInfoTree != null && UserInfoTree.size() > 0){
    			resultStr.append(mergelazyUserTree(UserInfoTree));
    		}
    		if(UserInfoTree != null && UserInfoTree.size() > 0 && lDeptTree.size() > 0){
    			resultStr.append(",");
    		}
    		if(lDeptTree.size() > 0){
    			resultStr.append(mergelazyTree(lDeptTree));
    		}
    		
        	resultStr.append("]");
        	model.addAttribute("lDeptTree", resultStr);

    	} catch (Exception e) {
    		model.addAttribute("lDeptTree", "[]");
		}
    	return "jsonView";
	}
	
	@SuppressWarnings("rawtypes")
	public String mergelazyTree(List lDeptTree) {
		StringBuilder temp = new StringBuilder();
		String  deptNm = "",
				deptCd = "",
				companyCd = "",
				upDeptCd = "",
				upDeptNm = "",
				endJson = "";
		EgovMap menuList = null;
		
		int iSize = lDeptTree.size();
		if(iSize > 0){
			for( int i = 0; i < iSize; i++ ) {
				menuList = (EgovMap) lDeptTree.get(i);
				deptNm = menuList.get("deptNm").toString();
				deptCd = menuList.get("deptCd").toString();
				companyCd = menuList.get("companyCd").toString();
				upDeptCd = menuList.get("upDeptCd").toString();
				if(upDeptCd.equals("ROOT")){
					upDeptNm = "";
				}else{
					upDeptNm = menuList.get("upDeptNm").toString();
				}
				
				if(iSize - 1 == i) {
					endJson = "";
				}else {
					endJson = ",";
				}
				try {
					temp.append("{ \"title\" : \""+ deptNm +"\", \"folder\" : true, \"lazy\": true, \"deptCd\" : \""+ deptCd +"\", \"upDeptCd\" : \""+ upDeptCd +"\", \"upDeptNm\" : \""+ upDeptNm +"\", \"companyCd\" : \""+ companyCd +"\", \"treeType\" : \"D\" } " + endJson + "");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return temp.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public String mergelazyUserTree(List UserInfoTree) {
		StringBuilder temp = new StringBuilder();
		String  userIf = "",
				userNm = "",
				userId = "",
				companyCd = "",
				deptCd = "",
				endJson = "";
		EgovMap menuList = null;
		try {
			int iSize = UserInfoTree.size();
			if(iSize > 0){
				for( int i = 0; i < iSize; i++ ) {
					menuList = (EgovMap) UserInfoTree.get(i);
					userIf = menuList.get("jobnm").toString() + " " +menuList.get("usernm").toString();
					userNm = menuList.get("usernm").toString();
					userId = menuList.get("userid").toString();
					companyCd = menuList.get("companycd").toString();
					deptCd = menuList.get("deptcd").toString();
					
					if(iSize - 1 == i) {
						endJson = "";
					}else {
						endJson = ",";
					}
					try {
						temp.append("{ \"title\" : \""+ userIf +"\", \"folder\" : false, \"lazy\": false, \"userNm\" : \""+ userNm +"\", \"userId\" : \""+ userId +"\", \"deptCd\" : \""+ deptCd +"\", \"companyCd\" : \""+ companyCd +"\", \"treeType\" : \"S\" } " + endJson + "");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return temp.toString();
	}
	
	/**
	 * MENU 트리 페이지
	 * @param searchMenuVO 검색조건정보
	 * @param model 화면모델
	 * @return adm/ath/dpt/getDeptInfolazyTree
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmn/tre/getMenuInfolazyTree.do")
	public String getMenuInfolazyTree(@ModelAttribute("menuInfoVO") MenuInfoVO searchMenuVO, HttpServletRequest request, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	model.addAttribute("mode", request.getParameter("mode"));
    	return "cmn/tre/getMenuInfolazyTree.md";
	}
	
	/**
	 * Menu 트리 JSON
	 * @param deptInfoVO 검색조건정보
	 * @param model 화면모델
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmn/tre/getMenuInfolazyTreeJson.do")
	public String getMenuInfolazyTreeJson(@ModelAttribute("menuInfoVO") MenuInfoVO menuInfoVO, ModelMap model, HttpServletResponse response) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
        StringBuilder resultStr = new StringBuilder();
    	try {
    		SearchMenuVO searchMenuVO = new SearchMenuVO();
    		searchMenuVO.setMenuId(menuInfoVO.getMenuId());
    		searchMenuVO.setParentMenuId(menuInfoVO.getParentMenuId());
    		
    		
        	List lMenuTree = commonService.selectMenuInfolazyTree(searchMenuVO);

        	if(lMenuTree.size() > 0){
	    		resultStr.append("[");
	    		resultStr.append(mergelazyMenuTree(lMenuTree));
	    		resultStr.append("]");
	    		
	    		model.addAttribute("lMenuTree", resultStr);
    		}else{
        		model.addAttribute("lMenuTree", "[]");
    		}
		} catch (Exception e) {
    		model.addAttribute("lMenuTree", "[]");
		}
    	return "jsonView";
	}
	
	@SuppressWarnings("rawtypes")
	public String mergelazyMenuTree(List lMenuTree) {
		StringBuilder temp = new StringBuilder();
		String  menuNm = "",
				menuId = "",
				companyCd = "",
				parentMenuId = "",
				parentMenuNm = "",
				endJson = "";
		EgovMap menuList = null;
		
		int iSize = lMenuTree.size();
		if(iSize > 0){
			for( int i = 0; i < iSize; i++ ) {
				menuList = (EgovMap) lMenuTree.get(i);
				menuNm = menuList.get("menuNm").toString();
				menuId = menuList.get("menuId").toString();
				parentMenuId = menuList.get("parentMenuId").toString();
				if(parentMenuId.equals("9999999")){
					parentMenuNm = "";
				}else{
					parentMenuNm = menuList.get("parentMenuNm").toString();
				}
				
				if(iSize - 1 == i) {
					endJson = "";
				}else {
					endJson = ",";
				}
				
				try {
					temp.append("{ \"title\" : \""+ menuNm +"\", \"folder\" : true, \"lazy\": true, \"menuId\" : \""+ menuId +"\", \"parentMenuId\" : \""+ parentMenuId +"\", \"parentMenuNm\" : \""+ parentMenuNm +"\", \"treeType\" : \"D\" } " + endJson + "");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return temp.toString();
	}

	/**
	 * Menu 트리 JSON
	 * @param deptInfoVO 검색조건정보
	 * @param model 화면모델
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmn/tre/getRoleMenuInfoTreeJson.do")
	public String getRoleMenuInfoTreeJson(@RequestParam(value = "parentMnId", required = false) String parentMnId, @RequestParam(value = "menuId", required = false) String menuId, @RequestParam(value = "roleCd", required = false) String roleCd, ModelMap model, HttpServletResponse response) throws Exception {
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
        StringBuilder resultStr = new StringBuilder();
    	try {
    		HashMap paramMap = new HashMap<String, Object>();
    		paramMap.put("parentMnId", parentMnId);
    		paramMap.put("menuId", menuId);
    		paramMap.put("roleCd", roleCd);

        	List lMenuTree = commonService.selectRoleMenuInfoTree(paramMap);

        	if(lMenuTree.size() > 0){
	    		resultStr.append("[");
	    		resultStr.append(mergelazyRoleMenuTree(lMenuTree));
	    		resultStr.append("]");
	    		
	    		model.addAttribute("lMenuTree", resultStr);
    		}else{
        		model.addAttribute("lMenuTree", "[]");
    		}
		} catch (Exception e) {
    		model.addAttribute("lMenuTree", "[]");
		}
    	return "jsonView";
	}
	
	@SuppressWarnings("rawtypes")
	public String mergelazyRoleMenuTree(List lMenuTree) {
		StringBuilder temp = new StringBuilder();
		String  menuNm = "",
				menuId = "",
				parentMenuId = "",
				roleCd = "",
				menuCnt = "",
				authCnt = "",
				endJson = "",
				authJson = "";
		EgovMap menuList = null;
		int iSize = lMenuTree.size();
		if(iSize > 0){
			for( int i = 0; i < iSize; i++ ) {
				menuList = (EgovMap) lMenuTree.get(i);
				menuNm = menuList.get("menunm").toString();
				menuId = menuList.get("menuid").toString();
				menuCnt = menuList.get("menucnt").toString();
				authCnt = menuList.get("authcnt").toString();
				
				try {
					if(menuList.get("parentmenuid").toString().equals("9999999")){
						parentMenuId = "#";
					}else{
						parentMenuId = menuList.get("parentmenuid").toString();
						
						if (menuList.get("rolecd") != null && menuCnt.equals(authCnt)) {
			                authJson = ", \"state\" : { \"selected\" : true }";
			            } else {
			                authJson = ", \"state\" : { \"selected\" : false }";
			            }
					}
					if(iSize - 1 == i) {
						endJson = "";
					}else {
						endJson = ",";
					}
					
					temp.append("{ \"id\" : \""+ menuId +"\", \"text\" : \""+ menuNm +"\", \"parent\" : \""+ parentMenuId +"\", \"li_attr\" : {\"isType\" : \"D\"} " + authJson + "} " + endJson + "");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return temp.toString();
	}
}