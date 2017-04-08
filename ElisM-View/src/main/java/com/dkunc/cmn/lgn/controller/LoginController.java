package com.dkunc.cmn.lgn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dkunc.adm.ath.mnu.service.MenuManageService;
import com.dkunc.adm.ath.mnu.vo.MenuInfoVO;
import com.dkunc.adm.ath.mnu.vo.SearchMenuVO;
import com.dkunc.cmn.EgovMessageSource;
import com.dkunc.cmn.lgn.service.LoginService;
import com.dkunc.cmn.lgn.vo.UserInfoVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

@Controller
public class LoginController {

	/** EgovLoginService *//*
	@Resource(name = "loginService")
	private EgovLoginService loginService;*/

	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name="menuManageService")
	MenuManageService menuManageService;

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/index.do")
	public String viewLogin(HttpServletRequest request, ModelMap model) throws Exception{
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		return "cmn/lgn/login";
    	}
    	//시작 페이지 조회하여 해당 페이지로 리턴..
    	return "forward:/adm/ath/usr/viewUserInfoList.do";

	}
	@RequestMapping(value = "/cmn/lgn/login.do")
	public String actionSecurityLogin(@ModelAttribute("userInfoVO") UserInfoVO userInfoVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{

		// 1. 일반 로그인 처리
		UserInfoVO resultVO = loginService.actionLogin(userInfoVO);

        boolean loginPolicyYn = true;

        if (null != resultVO && null != resultVO.getUserId() && !resultVO.getUserId().equals("") && loginPolicyYn) {
        	SearchMenuVO searchMenuVO = new SearchMenuVO();
        	
        	HashMap vo = new HashMap();
        	
        	vo.put("userId", resultVO.getUserId());
        	
        	List menuList = loginService.selectMyRoleMenu(vo);
        	
        	
        	resultVO.setMenuList(menuList);
        	
        	request.getSession().setAttribute("UserInfoVO", resultVO);
        	
        	UserInfoVO test = (UserInfoVO) request.getSession().getAttribute("UserInfoVO");

        	UsernamePasswordAuthenticationFilter springSecurity = null;

        	ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
        	@SuppressWarnings("rawtypes")

        	Map beans = act.getBeansOfType(UsernamePasswordAuthenticationFilter.class);
        	if (beans.size() > 0) {
        		springSecurity = (UsernamePasswordAuthenticationFilter)beans.values().toArray()[0];
        	} else {
        		throw new IllegalStateException("No AuthenticationProcessingFilter");
        	}

        	springSecurity.setContinueChainBeforeSuccessfulAuthentication(false);	// false 이면 chain 처리 되지 않음.. (filter가 아닌 경우 false로...)

        	springSecurity.doFilter(new RequestWrapperForSecurity(request, resultVO.getUserId(), resultVO.getPwd()), response, null);

        	return "forward:/main/mainPage.do";	// 성공 시 페이지.. (redirect 불가)
        } else {
        	model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/index.do?login_error=1";
        }
		//return "forward:/index.do";
	}
	@RequestMapping(value="/uat/uia/actionMain.do")
	public String actionMain(ModelMap model) throws Exception {
    	// 1. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", "로그인 실패"); //egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		// 2. 메인 페이지 이동
    	return "forward:/main/mainPage.do";

	}
	
	@RequestMapping(value="/cmn/lgn/loginOut.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {
		request.getSession().setAttribute("LoginVO", null);

		return "redirect:/j_spring_security_logout";
	}
	
	/**
	 * 권한제한 화면 이동
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/accessDenied.do")
	public String accessDenied(ModelMap model) throws Exception {
    	return "cmn/error/accessDenied";
	}

}



class RequestWrapperForSecurity extends HttpServletRequestWrapper {
	private String username = null;
	private String password = null;

	public RequestWrapperForSecurity(HttpServletRequest request, String username, String password) {
		super(request);

		this.username = username;
		this.password = password;
	}

	@Override
	public String getRequestURI() {
		return ((HttpServletRequest)super.getRequest()).getContextPath() + "/j_spring_security_check";
	}

	@Override
	public String getParameter(String name) {
	    if (name.equals("j_username")) {
	    	return username;
	    }

	    if (name.equals("j_password")) {
	    	return password;
	    }

	    return super.getParameter(name);
	}
}
