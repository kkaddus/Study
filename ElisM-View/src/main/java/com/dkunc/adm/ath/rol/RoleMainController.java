package com.dkunc.adm.ath.rol;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dkunc.adm.ath.rol.service.RoleManageService;
import com.dkunc.adm.ath.rol.vo.RoleInfoVO;
import com.dkunc.adm.ath.rol.vo.UserRoleVO;
import com.dkunc.cmn.ComDefaultVO;
import com.dkunc.cmn.EgovMessageSource;
import com.dkunc.cmn.SearchDefaultVO;
import com.dkunc.cmn.service.EgovCmmUseService;
import com.dkunc.cmn.service.FileService;
import com.dkunc.cmn.vo.LoginVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller@SessionAttributes(types = ComDefaultVO.class)
public class RoleMainController {

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    /** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;
	
	@Resource(name = "FileService")
	private FileService fileService;
	
	@Resource(name = "roleManageService")
	private RoleManageService roleManageService;

	/**
	 * 롤 목록  페이지 호출
	 */
	@RequestMapping(value = "/adm/ath/rol/viewRoleInfo.do")
	public String viewBoardInfo(ModelMap model) throws Exception{
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	
    	return "adm/ath/rol/roleInfoList.mg";
	}
	
	
	/**
	 * 롤 목록 조회 및 검색한다. (pageing)
	 * @param searchDefaultVO 검색조건정보
	 * @param model 화면모델
	 * @return adm/ath/usr/EgovMberManage
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/brd/getRoleInfoListJson.do", headers = "Accept=application/json")
	public String getRoleInfoListJson(@ModelAttribute("userSearchVO") SearchDefaultVO searchDefaultVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchDefaultVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchDefaultVO.getPageUnit());
		paginationInfo.setPageSize(searchDefaultVO.getPageSize());

		searchDefaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchDefaultVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchDefaultVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RoleInfoVO> resultList = roleManageService.selectRoleList(searchDefaultVO);
		
		
		model.addAttribute("resultList", resultList );

		int totCnt = roleManageService.selectRoleListTotCnt(searchDefaultVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "jsonView";
	}
	
	/**
	 * 롤 등록/수정 모달 페이지 호출
	 */
	@RequestMapping(value = "/adm/ath/brd/ViewRoleInfoInsert.do")
	public String ViewBoardInfoInsert( HttpServletRequest request , @ModelAttribute("userSearchVO") SearchDefaultVO searchDefaultVO, ModelMap model)
	  throws Exception{
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	
    	String mode = request.getParameter("mode") ;
    	if("edit".equals(request.getParameter("mode"))) {
    		
    		searchDefaultVO.setSearchCondition("edit");
    		searchDefaultVO.setSearchKeyword(request.getParameter("roleCd"));
    		
    		List<RoleInfoVO> resultList = roleManageService.selectRoleList(searchDefaultVO);
    		
    		model.addAttribute("resultList", resultList.get(0));
    		model.addAttribute("roleCd", request.getParameter("roleCd"));
    	}
    	
    	model.addAttribute("mode", mode);
    	
    	return "adm/ath/rol/roleInfoInsert.md";
	}
	
	@RequestMapping(value = "/adm/ath/rol/insertRoleInfoJson.do")
    private String boardSave( HttpServletRequest request , HttpServletResponse response, ModelMap model, RoleInfoVO roleInfoVO) throws Exception {
		String mode = request.getParameter("mode");
		
    	if("write".equals(mode)) {
    		// 게시판 데이터 insert
    		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    		roleInfoVO.setCreateId(user.getId());
    		
    		try {
            	int result = roleManageService.insertRoleInfo(roleInfoVO);
            	if(result > 0){
        			model.addAttribute("result", result);
        			model.addAttribute("resultMessage", "Success");
            	}else{
        			model.addAttribute("result", result);
        			model.addAttribute("resultMessage", "Fail");
            	}
    		} catch (Exception e) {
    			model.addAttribute("result", "Error");
    			model.addAttribute("resultMessage", e.getMessage());
    		}
    	} else if("edit".equals(mode)) {
    		// 게시판 데이터 insert
    		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    		roleInfoVO.setUpdateId(user.getId());
    		
    		try {
            	int result = roleManageService.updateRoleInfo(roleInfoVO);
            	if(result > 0){
        			model.addAttribute("result", result);
        			model.addAttribute("resultMessage", "Success");
            	}else{
        			model.addAttribute("result", result);
        			model.addAttribute("resultMessage", "Fail");
            	}
    		} catch (Exception e) {
    			model.addAttribute("result", "Error");
    			model.addAttribute("resultMessage", e.getMessage());
    		}
    	} else if("delete".equals(mode)) {
    		// 게시판 데이터 insert
    		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    		roleInfoVO.setUpdateId(user.getId());
    		
    		try {
            	int result = roleManageService.deleteRoleInfo(roleInfoVO);
            	if(result > 0){
        			model.addAttribute("result", result);
        			model.addAttribute("resultMessage", "Success");
            	}else{
        			model.addAttribute("result", result);
        			model.addAttribute("resultMessage", "Fail");
            	}
    		} catch (Exception e) {
    			model.addAttribute("result", "Error");
    			model.addAttribute("resultMessage", e.getMessage());
    		}
    	}
    	
        return "jsonView";
    }
	
	
	/**
	 * 롤별 사용자 목록  페이지 호출
	 */
	@RequestMapping(value = "/adm/ath/rol/viewRoleUserInfo.do")
	public String viewRoleUserInfo(ModelMap model) throws Exception{
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	
    	return "adm/ath/rol/roleUserInfoList.mg";
	}
	
	@RequestMapping(value = "/adm/ath/rol/getRoleUserInfoListJson.do", headers = "Accept=application/json")
	public String getRoleUserInfoListJson(HttpServletRequest request ,  @ModelAttribute("userSearchVO") SearchDefaultVO searchDefaultVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchDefaultVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchDefaultVO.getPageUnit());
		paginationInfo.setPageSize(searchDefaultVO.getPageSize());

		searchDefaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchDefaultVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchDefaultVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		searchDefaultVO.setSearchKeyword(request.getParameter("roleCd"));
		searchDefaultVO.setSearchCondition("ROLE_CD");
		
		List resultList = roleManageService.selectRoleUserList(searchDefaultVO);
		
		model.addAttribute("resultList", resultList );

		int totCnt = roleManageService.selectRoleListTotCnt(searchDefaultVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "jsonView";
	}
	
	@RequestMapping(value = "/adm/ath/rol/ViewRoleUserInfoInsert.do")
	public String ViewRoleUserInfoInsert( HttpServletRequest request ,UserRoleVO userRoleVO, ModelMap model)
	  throws Exception{
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	
    	String mode = request.getParameter("mode") ;
    	
    	String [] targetIdList = request.getParameterValues("targetIdList[]");
    	
    	String targetId = "";
    	
    	
    	for(int i=0; i < targetIdList.length ; i++) {
    		targetId += "'" + targetIdList[i] + "',";
    	}
    	
    	targetId = targetId.substring(0, targetId.length()-1);
    		
    	SearchDefaultVO searchDefaultVO = new SearchDefaultVO ();
    	
    	searchDefaultVO.setSearchCondition("TARGET_ID");
    	searchDefaultVO.setSearchKeyword(targetId);
    	searchDefaultVO.setSbscrbSttus(userRoleVO.getRoleCd());
    	
    	int userCheck = roleManageService.selectRoleUserListTotCnt(searchDefaultVO);
    	
    	if(userCheck > 0 ) {
    		model.addAttribute("result", 0);
			model.addAttribute("resultMessage", "이미 등록된 사용자가 있습니다.");
    	} else {
    		try {
             	int result = roleManageService.insertUserRole(userRoleVO, targetIdList);
            	if(result > 0){
        			model.addAttribute("result", result);
        			model.addAttribute("resultMessage", "Success");
            	}else{
        			model.addAttribute("result", result);
        			model.addAttribute("resultMessage", "Fail");
            	}
    		} catch (Exception e) {
    			model.addAttribute("result", "Error");
    			model.addAttribute("resultMessage", e.getMessage());
    		}
        	
    	}
		
    	return "jsonView";
	}
	
	
	@RequestMapping(value = "/adm/ath/rol/deleteRoleInfoJson.do")
    private String deleteRoleInfoJson( HttpServletRequest request , HttpServletResponse response, ModelMap model,UserRoleVO userRoleVO) throws Exception {
		try {
        	int result = roleManageService.deleteUserRole(userRoleVO);
        	if(result > 0){
    			model.addAttribute("result", result);
    			model.addAttribute("resultMessage", "Success");
        	}else{
    			model.addAttribute("result", result);
    			model.addAttribute("resultMessage", "Fail");
        	}
		} catch (Exception e) {
			model.addAttribute("result", "Error");
			model.addAttribute("resultMessage", e.getMessage());
		}
    	
        return "jsonView";
    }
	
	/**
	 * 롤별 메뉴 목록  페이지 호출
	 */
	@RequestMapping(value = "/adm/ath/rol/viewRoleMenuInfo.do")
	public String viewRoleMenuInfo(ModelMap model) throws Exception{
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	
    	return "adm/ath/rol/roleMenuInfoList.mg";
	}
	

	
	/**
	 * 롤별 메뉴 처리(DELETE -> INSERT)
	 */
	@RequestMapping(value = "/adm/ath/rol/processRoleMenuInfoJson.do", headers = "Accept=application/json")
	public String processRoleMenuInfoJson(HttpServletRequest request ,  ModelMap model) throws Exception {	
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	String roleCd = request.getParameter("roleCd");
    	String[] undetermined = request.getParameterValues("undetermined[]");
    	String[] getselected = request.getParameterValues("getselected[]");
    	
    	try {
        	int inResult = roleManageService.processMenuRole(roleCd, undetermined, getselected);
        	if(inResult > 0){
    			model.addAttribute("result", inResult);
    			model.addAttribute("resultMessage", "Success");
        	}else{
    			model.addAttribute("result", inResult);
    			model.addAttribute("resultMessage", "Fail");
        	}
		} catch (Exception e) {
			model.addAttribute("result", "Error");
			model.addAttribute("resultMessage", e.getMessage());
		}
    	return "jsonView";
	}
}