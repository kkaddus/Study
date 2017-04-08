package com.dkunc.main;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dkunc.cmn.lgn.vo.UserInfoVO;
import com.dkunc.cmn.service.EgovProperties;
import com.dkunc.cmn.vo.LoginVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
/**
 * 템플릿 메인 페이지 컨트롤러 클래스(Sample 소스)
 * @author 실행환경 개발팀 JJY
 * @since 2011.08.31
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.08.31  JJY            최초 생성
 *
 * </pre>
 */
@Controller
public class UonMainController {

	/**
	 * 메인 페이지에서 각 업무 화면으로 연계하는 기능을 제공한다.
	 *
	 * @param request
	 * @param commandMap
	 * @exception Exception Exception
	 */
	@RequestMapping(value = "/cmm/forwardPage.do")
	public String forwardPageWithMenuNo(HttpServletRequest request, @RequestParam Map<String, Object> commandMap)
	  throws Exception{
		return "";
	}

	/**
	 * 템플릿 메인 페이지 조회
	 * @return 메인페이지 정보 Map [key : 항목명]
	 *
	 * @param request
	 * @param model
	 * @exception Exception Exception
	 */
	@RequestMapping(value = "/main/mainPage.do")
	public String getMgtMainPage(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", "로그인이 필요합니다.");//egovMessageSource.getMessage("fail.common.login"));
        	return "cmn/lgn/login";
    	}
		return "forward:/adm/ath/usr/viewUserInfoList.do";
		//return "test.jsp";
	}


	/**
	 * JSP 호출작업만 처리하는 공통 함수
	 */
	@RequestMapping(value = "/main/UonPageLink.do")
	public String moveToPage(@RequestParam("link") String linkPage, HttpSession session, @RequestParam(value = "currentMenuId", required = false) String currentMenuId) {
		String link = linkPage;
		// service 사용하여 리턴할 결과값 처리하는 부분은 생략하고 단순 페이지 링크만 처리함
		if (linkPage == null || linkPage.equals("")) {
			link = "cmm/egovError";
		} else {
			if (link.indexOf(",") > -1) {
				link = link.substring(0, link.indexOf(","));
			}
		}
		// 선택된 메뉴정보를 세션으로 등록한다.
		if (currentMenuId != null && !currentMenuId.equals("")) {
			session.setAttribute("currentMenuId", currentMenuId);
		}
		return link;
	}

	/**
	 * JSP 호출작업만 처리하는 공통 함수
	 */
	@RequestMapping(value = "/main/UonPageLink.action")
	public String moveToPageAction(@RequestParam("link") String linkPage) {
		String link = linkPage;
		// service 사용하여 리턴할 결과값 처리하는 부분은 생략하고 단순 페이지 링크만 처리함
		if (linkPage == null || linkPage.equals("")) {
			link = "cmm/egovError";
		}
		return link;
	}

	/**
	 * validation rule dynamic java script
	 */
	@RequestMapping("/validator.do")
	public String validate() {
		return "cmm/validator";
	}

//	/**
//	 * 템플릿 메인 페이지 조회
//	 * @return 메인페이지 정보 Map [key : 항목명]
//	 *
//	 * @param request
//	 * @param model
//	 * @exception Exception Exception
//	 */
//	@RequestMapping(value = "/index.do")
//	public String viewLogin(HttpServletRequest request, ModelMap model)
//	  throws Exception{
//
//		return "cmn/lgn/login";
//		//return "test.jsp";
//	}

	/**
     * Head메뉴를 조회한다.
     * @param menuManageVO MenuManageVO
     * @return 출력페이지정보 "main_headG", "main_head"
     * @exception Exception
     */
    @RequestMapping(value="/main/UonMainMenuHead.do")
    public String selectMainMenuHead(
    		@RequestParam(value="flag", required=false) String flag,
    		ModelMap model)
            throws Exception {

    	LoginVO user =	EgovUserDetailsHelper.isAuthenticated()? (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser():null;

    	if(flag==null){
    		return "main/inc/UonIncSubHeader"; // 업무화면의 상단메뉴 화면
    	}else if(flag.equals("MAIN")){
    		return "main/inc/UonIncHeader"; // 메인화면의 상단메뉴 화면
    	}else{
    		return "main/inc/UonIncSubHeader"; // 업무화면의 상단메뉴 화면
    	}
    }


    /**
     * 좌측메뉴를 조회한다.
     * @param menuManageVO MenuManageVO
     * @param vStartP      String
     * @return 출력페이지정보 "main_left"
     * @exception Exception
     */
    @RequestMapping(value="/main/UonMainMenuLeft.do")
    public String selectMainMenuLeft(ModelMap model, HttpServletRequest request)throws Exception {

    	//LoginVO user = UonUserDetailsHelper.isAuthenticated()? (LoginVO)UonUserDetailsHelper.getAuthenticatedUser():null;

    	//LoginVO user = (LoginVO)UonUserDetailsHelper.getAuthenticatedUser();
    	if(EgovUserDetailsHelper.isAuthenticated()){
    		//인증된 경우 처리할 사항 추가 ...
    		model.addAttribute("lastLogoutDateTime", "로그아웃 타임: 2011-11-10 11:30");
    		//최근 로그아웃 시간 등에 대한 확보 후 메인 컨텐츠로 활용
    	}
    	
    	UserInfoVO userInfoVO = (UserInfoVO) request.getSession().getAttribute("UserInfoVO");
    	
    	List menuList = userInfoVO.getMenuList();
    	
    	ObjectMapper om = new ObjectMapper();

    	model.addAttribute("myMenuList", om.writeValueAsString(menuList));
      	return "main/inc/UonIncLeftmenu";
    }

}