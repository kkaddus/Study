package com.dkunc.adm.ath.usr;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.dkunc.adm.ath.dpt.service.DeptManageService;
import com.dkunc.adm.ath.dpt.vo.DeptInfoVO;
import com.dkunc.adm.ath.dpt.vo.SearchDeptVO;
import com.dkunc.adm.ath.usr.service.UserManageService;
import com.dkunc.cmn.ComDefaultCodeVO;
import com.dkunc.cmn.EgovMessageSource;
import com.dkunc.cmn.SearchDefaultVO;
import com.dkunc.cmn.lgn.vo.UserInfoVO;
import com.dkunc.cmn.service.EgovCmmUseService;
import com.dkunc.adm.ath.usr.vo.SearchUserVO;
import com.dkunc.adm.ath.usr.vo.UserManageVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 일반회원관련 요청을  비지니스 클래스로 전달하고 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
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
 *   2009.04.10  조재영          최초 생성
 *
 * </pre>
 */
@Controller
public class UserManageController {

	/** userManageService */
	@Resource(name = "userManageService")
	private UserManageService userManageService;

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

	/** deptManageService */
	@Resource(name = "deptManageService")
	private DeptManageService deptManageService;
	
	/**
	 * 회원관리 Main Page
	 * @param model 화면모델
	 * @return adm/ath/usr/userInfoList
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/usr/viewUserInfoList.do")
	public String viewUserInfoList(@ModelAttribute("searchUserVO") SearchUserVO searchUserVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		//일반회원 상태코드를 코드정보로부터 조회
		vo.setCodeId("COM013");
		model.addAttribute("entrprsMberSttus_result", cmmUseService.selectCmmCodeDetail(vo));
		
		return "adm/ath/usr/userInfoList.mg";
	}

	/**
	 * 회원관리 목록 조회 및 검색한다. (pageing)
	 * @param searchDefaultVO 검색조건정보
	 * @param model 화면모델
	 * @return cmm/uss/umt/EgovMberManage
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/usr/getUserInfoListJson.do", headers = "Accept=application/json")
	public String getUserInfoListJson(@ModelAttribute("searchUserVO") SearchUserVO searchUserVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchUserVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchUserVO.getPageUnit());
		paginationInfo.setPageSize(searchUserVO.getPageSize());

		searchUserVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchUserVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchUserVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		model.addAttribute("resultList", userManageService.selectUserList(searchUserVO));

		int totCnt = userManageService.selectUserListCnt(searchUserVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "jsonView";
	}

	/**
	 * 회원등록 페이지
	 * @param searchDefaultVO 검색조건정보
	 * @param mberManageVO 일반회원초기화정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/usr/userInfoInsert.do
	 * @throws Exception
	 */
	@RequestMapping("/adm/ath/usr/viewUserInfoInsert.do")
	public String setUserInfo(@ModelAttribute("searchUserVO") SearchUserVO searchUserVO, @ModelAttribute("userInfoVO") UserInfoVO userInfoVO, Model model)
			throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		//패스워드힌트목록을 코드정보로부터 조회
		vo.setCodeId("COM022");
		model.addAttribute("passwordHint_result", cmmUseService.selectCmmCodeDetail(vo));
		//직급목록을 코드정보로부터 조회
		vo.setCodeId("LEV000");
		model.addAttribute("level_result", cmmUseService.selectCmmCodeDetail(vo));
		//직책목록을 코드정보로부터 조회
		vo.setCodeId("POS000");
		model.addAttribute("position_result", cmmUseService.selectCmmCodeDetail(vo));
		//직위목록을 코드정보로부터 조회
		vo.setCodeId("TIT000");
		model.addAttribute("title_result", cmmUseService.selectCmmCodeDetail(vo));
		//회원상태
		vo.setCodeId("COM013");
		model.addAttribute("status_result", cmmUseService.selectCmmCodeDetail(vo));
		//사용여부
		vo.setCodeId("USA000");
		model.addAttribute("usage_result", cmmUseService.selectCmmCodeDetail(vo));
		
		return "adm/ath/usr/userInfoInsert.md";
	}

	/**
	 * 회원상세 페이지
	 * @param searchDefaultVO 검색조건정보
	 * @param mberManageVO 일반회원초기화정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/usr/userInfoInsert.do
	 * @throws Exception
	 */
	@RequestMapping("/adm/ath/usr/getUserInfo.do")
	public String getUserInfo(@ModelAttribute("searchUserVO") SearchUserVO searchUserVO, @ModelAttribute("userInfoVO") UserInfoVO userInfoVO, Model model)
			throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		//패스워드힌트목록을 코드정보로부터 조회
		vo.setCodeId("COM022");
		model.addAttribute("passwordHint_result", cmmUseService.selectCmmCodeDetail(vo));
		//직급목록을 코드정보로부터 조회
		vo.setCodeId("LEV000");
		model.addAttribute("level_result", cmmUseService.selectCmmCodeDetail(vo));
		//직책목록을 코드정보로부터 조회
		vo.setCodeId("POS000");
		model.addAttribute("position_result", cmmUseService.selectCmmCodeDetail(vo));
		//직위목록을 코드정보로부터 조회
		vo.setCodeId("TIT000");
		model.addAttribute("title_result", cmmUseService.selectCmmCodeDetail(vo));
		//회원상태
		vo.setCodeId("COM013");
		model.addAttribute("status_result", cmmUseService.selectCmmCodeDetail(vo));
		//사용여부
		vo.setCodeId("USA000");
		model.addAttribute("usage_result", cmmUseService.selectCmmCodeDetail(vo));
		
    	model.addAttribute("userInfo", userManageService.selectUserInfo(userInfoVO));
		return "adm/ath/usr/userInfoView.md";
	}
	
	/**
	 * 회원등록 처리
	 * @param searchDefaultVO 검색조건정보
	 * @param mberManageVO 일반회원초기화정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/usr/viewUserInfoInsert.do
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/usr/insertUserInfoJson.do", headers = "Accept=application/json")
	//public String insertUserInfoJson(@RequestParam Map<String, Object> userMap, ModelMap model) throws Exception {
	public String insertUserInfoJson(@ModelAttribute("userInfoVO") UserInfoVO userInfoVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	try {
        	int result = userManageService.insertUserInfo(userInfoVO);
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
	 * 회원수정 처리
	 * @param searchDefaultVO 검색조건정보
	 * @param mberManageVO 일반회원초기화정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/usr/viewUserInfoInsert.do
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/usr/updateUserInfoJson.do", headers = "Accept=application/json")
	public String updateUserInfoJson(@ModelAttribute("userInfoVO") UserInfoVO userInfoVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	try {
        	int result = userManageService.updateUserInfo(userInfoVO);
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
	 * 회원 등록 ID 찾기 페이지
	 * @param model 화면모델
	 * @return adm/ath/usr/viewUserInfIdCheck
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/usr/viewUserInfIdCheck.do")
	public String viewUserInfIdCheck(ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		model.addAttribute("checkId", "");
		model.addAttribute("usedCnt", "-1");
		return "adm/ath/usr/userInfIdCheck";
	}

	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model 화면모델
	 * @return adm/ath/usr/userInfIdCheckJson
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/usr/userInfIdCheckJson.do", headers = "Accept=application/json")
	public String checkIdDplct(@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		String checkId = (String) commandMap.get("checkId");
		checkId = new String(checkId.getBytes("ISO-8859-1"), "UTF-8");

		if (checkId == null || checkId.equals(""))
			return "forward:/adm/ath/usr/viewUserInfIdCheck.do";

		int usedCnt = userManageService.checkIdDplct(checkId);
		model.addAttribute("usedCnt", usedCnt);
		model.addAttribute("checkId", checkId);

		return "jsonView";
	}
	
	@RequestMapping(value = "/adm/ath/usr/viewUserSearchList.do")
	public String viewUserSearchList(HttpServletRequest request ,@ModelAttribute("searchUserVO") SearchUserVO searchUserVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		//일반회원 상태코드를 코드정보로부터 조회
		vo.setCodeId("COM013");
		model.addAttribute("entrprsMberSttus_result", cmmUseService.selectCmmCodeDetail(vo));
		model.addAttribute("mode", request.getParameter("mode"));
		model.addAttribute("roleCd", request.getParameter("roleCd"));
		
		return "adm/ath/usr/userSearchList.md";
	}
}