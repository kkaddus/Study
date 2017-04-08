package com.dkunc.cmn.interceptor;

import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import com.dkunc.cmn.vo.LoginVO;

/**
 * 인증여부 체크 인터셉터
 * @author 공통서비스 개발팀 서준식
 * @since 2011.07.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011.07.01  서준식          최초 생성
 *  2011.09.07  서준식          인증이 필요없는 URL을 패스하는 로직 추가
 *  2014.06.11  이기하          인증이 필요없는 URL을 패스하는 로직 삭제(xml로 대체)
 *  </pre>
 */

public class AuthenticInterceptor extends WebContentInterceptor {

	/**
	 * 세션에 계정정보(LoginVO)가 있는지 여부로 인증 여부를 체크한다.
	 * 계정정보(LoginVO)가 없다면, 로그인 페이지로 이동한다.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		if (loginVO.getId() != null) {
			String currentTabId = request.getParameter("currentTabId");
			String currentMenuId = request.getParameter("currentMenuId");
			String currentMenuNum = request.getParameter("currentMenuNum");
			
			// 선택된 Tab 정보를 세션으로 등록한다.
			if (currentTabId != null && !currentTabId.equals("")) {
				request.getSession().setAttribute("currentTabId", currentTabId);
			}
			// 선택된 마지막 메뉴 정보를 세션으로 등록한다.
			if (currentMenuId != null && !currentMenuId.equals("")) {
				request.getSession().setAttribute("currentMenuId", currentMenuId);
			}
			// 선택된 마지막 메뉴 NUM을 세션으로 등록한다.
			if (currentMenuNum != null && !currentMenuNum.equals("")) {
				request.getSession().setAttribute("currentMenuNum", currentMenuNum);
			}
			
			return true;
		} else {
			ModelAndView modelAndView = new ModelAndView("redirect:/uat/uia/egovLoginUsr.do");
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}

}
