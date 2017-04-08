package com.dkunc.adm.ath.dpt;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.dkunc.adm.ath.dpt.service.DeptManageService;
import com.dkunc.adm.ath.dpt.vo.DeptInfoVO;
import com.dkunc.adm.ath.dpt.vo.SearchDeptVO;
import com.dkunc.adm.ath.mnu.vo.MenuInfoVO;
import com.dkunc.adm.ath.mnu.vo.SearchMenuVO;
import com.dkunc.cmn.ComDefaultCodeVO;
import com.dkunc.cmn.EgovMessageSource;
import com.dkunc.cmn.SearchDefaultVO;
import com.dkunc.cmn.service.EgovCmmUseService;
import com.dkunc.cmn.vo.AbstractVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 부서관련 요청을  비지니스 클래스로 전달하고 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
 * @author 김신재
 * @since 2017.03.17
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
public class DeptManageController {

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
	 * 부서관리 Main Page
	 * @param model 화면모델
	 * @return adm/ath/usr/viewDeptInfoList
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/dpt/viewDeptInfo.do")
	public String viewDeptInfoList(@ModelAttribute("userSearchVO") SearchDefaultVO searchDefaultVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		return "adm/ath/dpt/dptInfoList.mg";
	}
	
	/**
	 * 부서 상세정보 JSON
	 * @param DeptInfoVO 검색조건정보
	 * @param model 화면모델
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/dpt/getDeptInfoJson.do", headers = "Accept=application/json")
	public String getDeptInfoJson(@ModelAttribute("deptInfoVO") DeptInfoVO deptInfoVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	try {
    		SearchDeptVO searchDeptVO = new SearchDeptVO();
    		searchDeptVO.setCompanyCd(deptInfoVO.getCompanyCd());
    		searchDeptVO.setDeptCd(deptInfoVO.getDeptCd());
    		
    		model.addAttribute("deptInfoDetail", deptManageService.selectDeptInfo(searchDeptVO));
		} catch (Exception e) {
    		model.addAttribute("deptInfoDetail", "");
		}
    	return "jsonView";
	}

	/**
	 * Dept 등록/수정 처리
	 * @param deptInfoVO 검색조건정보
	 * @param DeptInfoVO Menu초기화정보
	 * @param model 화면모델
	 * @return JSON
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/dpt/processDeptInfoJson.do", headers = "Accept=application/json")
	public String processDeptInfoJson(@ModelAttribute("deptInfoVO") DeptInfoVO deptInfoVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	try {
    		if(deptInfoVO.getMode().equals("I")){
	    		SearchDeptVO searchDeptVO = new SearchDeptVO();
	    		searchDeptVO.setDeptCd(deptInfoVO.getDeptCd());
	    		List<DeptInfoVO> listDeptId = deptManageService.selectDeptInfo(searchDeptVO);
	    		if(listDeptId.size() > 0){
	    			model.addAttribute("result", listDeptId.size());
	    			model.addAttribute("resultMessage", "기 존재하는 Dept 코드입니다.");
	    		}else{
	    			int result = deptManageService.insertDeptInfo(deptInfoVO);
	            	
	            	if(result > 0){
	        			model.addAttribute("result", result);
	        			model.addAttribute("resultMessage", "Insert Success");
	            	}else{
	        			model.addAttribute("result", result);
	        			model.addAttribute("resultMessage", "Insert Fail");
	            	}
	    		}
    		}else if(deptInfoVO.getMode().equals("D")){
    			int result = deptManageService.deleteAllDeptInfo(deptInfoVO);
            	
            	if(result > 0){
        			model.addAttribute("result", result);
        			model.addAttribute("resultMessage", "Delete Success");
            	}else{
        			model.addAttribute("result", result);
        			model.addAttribute("resultMessage", "Delete Fail");
            	}
    		}else if(deptInfoVO.getMode().equals("U")){
    			int result = deptManageService.updateDeptInfo(deptInfoVO);
            	
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