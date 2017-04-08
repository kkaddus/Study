package com.dkunc.adm.ath.cde;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.dkunc.adm.ath.cde.service.CodeManageService;
import com.dkunc.adm.ath.cde.vo.CodeDetailVO;
import com.dkunc.adm.ath.cde.vo.CodeMasterVO;
import com.dkunc.cmn.EgovMessageSource;
import com.dkunc.cmn.SearchDefaultVO;
import com.dkunc.cmn.service.EgovCmmUseService;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

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
public class CodeManageController {

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
	@Resource(name = "codeManageService")
	private CodeManageService codeManageService;

	/**
	 * 코드관리 Main Page(Master)
	 * @param model 화면모델
	 * @return adm/ath/cde/codeMasterList
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/cde/viewCodeMaster.do")
	public String viewCodeMaster(@ModelAttribute("userSearchVO") SearchDefaultVO searchDefaultVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		return "adm/ath/cde/codeMasterList.mg";
	}
	
	/**
	 * 코드관리 목록 조회 및 검색한다. (pageing)
	 * @param searchDefaultVO 검색조건정보
	 * @param model 화면모델
	 * @return adm/ath/usr/EgovMberManage
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/cde/getCodeMasterListJson.do", headers = "Accept=application/json")
	public String getCodeMasterListJson(@ModelAttribute("userSearchVO") SearchDefaultVO searchDefaultVO, ModelMap model) throws Exception {

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

		model.addAttribute("resultList", codeManageService.selectCodeList(searchDefaultVO));

		int totCnt = codeManageService.selectCodeListTotCnt(searchDefaultVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "jsonView";
	}
	
	/**
	 * 코드등록 페이지
	 * @param searchDefaultVO 검색조건정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/cde/codeMasterInsert.do
	 * @throws Exception
	 */
	@RequestMapping("/adm/ath/cde/viewCodeMasterInsert.do")
	public String viewCodeMasterInsert(ModelMap model) throws Exception {
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	   	if(!isAuthenticated) {
		    model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
		    return "uat/uia/EgovLoginUsr";
		}

		return "adm/ath/cde/codeMasterInsert.md";
	}
	
	/**
	 * 중복 코드 검색 페이지
	 * @param model 화면모델
	 * @return adm/ath/cde/viewCdeMasterCheck
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/cde/viewCdeMasterCheck.do")
	public String viewCdeMasterCheck(ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		model.addAttribute("checkId", "");
		model.addAttribute("usedCnt", "-1");
		return "adm/ath/cde/codeMasterCheck";
	}
	
	/**
	 * 입력한 코드의 중복여부를 체크하여 사용가능여부를 확인
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model 화면모델
	 * @return adm/ath/cde/codeMasterCheckJson
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/cde/codeMasterCheckJson.do", headers = "Accept=application/json")
	public String codeMasterCheckJson(@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		String checkId = (String) commandMap.get("checkId");
		checkId = new String(checkId.getBytes("ISO-8859-1"), "UTF-8");

		if (checkId == null || "".equals(checkId))
			return "forward:/adm/ath/cde/viewCdeMasterCheck.do";

		int usedCnt = codeManageService.checkIdDplct(checkId);
		model.addAttribute("usedCnt", usedCnt);
		model.addAttribute("checkId", checkId);

		return "jsonView";
	}
	
	/**
	 * 코드등록 처리
	 * @param codeMasterVO 코드정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/cde/viewCodeMasterInsert.do
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/cde/insertCodeMasterJson.do", headers = "Accept=application/json")
	public String insertCodeMasterJson(@ModelAttribute("codeMasterVO") CodeMasterVO codeMasterVO, ModelMap model ) throws Exception {
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "uat/uia/EgovLoginUsr";
		}
		try {
			int result = codeManageService.insertCodeMaster(codeMasterVO);
			if (result > 0) {
				model.addAttribute("result", result);
    			model.addAttribute("resultMessage", "등록이 완료되었습니다.");
			} else {
				model.addAttribute("result", result);
    			model.addAttribute("resultMessage", "등록이 실패하였습니다.");
			}
		} catch (Exception e) {
			model.addAttribute("result", "Error");
			model.addAttribute("resultMessage", e.getMessage());
		}
		return "jsonView";
	}
	
	/**
	 * 코드 view 페이지
	 * @param searchDefaultVO 검색조건정보
	 * @param mberManageVO 일반회원초기화정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/cde/codeMasterInsert.do
	 * @throws Exception
	 */
	@RequestMapping("/adm/ath/cde/getCodeMaster.do")
	public String getCodeMaster(@ModelAttribute("codeMasterVO") CodeMasterVO codeMasterVO, ModelMap model )
			throws Exception {
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	   	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
	   	
	   	model.addAttribute("codeMaster", codeManageService.selectCodeMaster(codeMasterVO));
		
		return "adm/ath/cde/codeMasterView.md";
	}
	
	/**
	 * 코드 수정
	 * @param codeMasterVO 코드정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/cde/viewCodeMasterInsert.do
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/cde/updateCodeMasterJson.do", headers = "Accept=application/json")
	public String updateCodeMasterJson(@ModelAttribute("codeMasterVO") CodeMasterVO codeMasterVO, ModelMap model ) throws Exception {
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "uat/uia/EgovLoginUsr";
		}
		try {
			int result = codeManageService.updateCodeMaster(codeMasterVO);
			if (result > 0) {
				model.addAttribute("result", result);
    			model.addAttribute("resultMessage", "수정이 완료되었습니다.");
			} else {
				model.addAttribute("result", result);
    			model.addAttribute("resultMessage", "수정이 실패하였습니다.");
			}
		} catch (Exception e) {
			model.addAttribute("result", "Error");
			model.addAttribute("resultMessage", e.getMessage());
		}
		return "jsonView";
	}
	
	/**
	 * 코드 삭제
	 * @param codeMasterVO 코드정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/cde/viewCodeMasterInsert.do
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/cde/deleteCodeMasterJson.do", headers = "Accept=application/json")
	public String deleteCodeMasterJson(@ModelAttribute("codeMasterVO") CodeMasterVO codeMasterVO, ModelMap model ) throws Exception {
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "uat/uia/EgovLoginUsr";
		}
		try {
			int result = codeManageService.deleteCodeMaster(codeMasterVO);
			if (result > 0) {
				model.addAttribute("result", result);
    			model.addAttribute("resultMessage", "삭제가 완료되었습니다.");
			} else {
				model.addAttribute("result", result);
    			model.addAttribute("resultMessage", "삭제가 실패하였습니다.");
			}
		} catch (Exception e) {
			model.addAttribute("result", "Error");
			model.addAttribute("resultMessage", e.getMessage());
		}
		return "jsonView";
	}
	
	/**
	 * 코드상세관리 Main Page(Detail)
	 * @param model 화면모델
	 * @return adm/ath/cde/codeDetailList
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/cde/viewCodeDetail.do")
	public String viewCodeDetail(@ModelAttribute("userSearchVO") SearchDefaultVO searchDefaultVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		return "adm/ath/cde/codeDetailList.mg";
	}
	
	/**
	 * 코드상세관리 목록 조회 및 검색한다. (pageing)
	 * @param searchDefaultVO 검색조건정보
	 * @param model 화면모델
	 * @return adm/ath/usr/EgovMberManage
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/cde/getCodeDetailListJson.do", headers = "Accept=application/json")
	public String getCodeDetailListJson(@ModelAttribute("userSearchVO") SearchDefaultVO searchDefaultVO, ModelMap model) throws Exception {

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

		model.addAttribute("resultList", codeManageService.selectCodeDetailList(searchDefaultVO));

		int totCnt = codeManageService.selectCodeDetailListTotCnt(searchDefaultVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "jsonView";
	}
	
	/**
	 * 코드상세등록 페이지
	 * @param searchDefaultVO 검색조건정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/cde/codeDetailInsert.do
	 * @throws Exception
	 */
	@RequestMapping("/adm/ath/cde/viewCodeDetailInsert.do")
	public String setUserInfo(ModelMap model) throws Exception {
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	   	if(!isAuthenticated) {
		    model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
		    return "uat/uia/EgovLoginUsr";
		}
	   	
	   	// 전체 코드 조회
		SearchDefaultVO searchDefaultVO = new SearchDefaultVO();
		searchDefaultVO.setRecordCountPerPage(1000);
		model.addAttribute("codeId_result", codeManageService.selectCodeList(searchDefaultVO));

		return "adm/ath/cde/codeDetailInsert.md";
	}
	
	/**
	 * 중복 코드 검색 페이지
	 * @param model 화면모델
	 * @return adm/ath/cde/viewCdeDetailCheck
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/cde/viewCdeDetailCheck.do")
	public String viewCdeDetailCheck(ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		model.addAttribute("checkDesc", "");
		model.addAttribute("usedCnt", "-1");
		return "adm/ath/cde/codeDetailCheck";
	}
	
	/**
	 * 입력한 코드의 중복여부를 체크하여 사용가능여부를 확인
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model 화면모델
	 * @return adm/ath/cde/codeDetailCheckJson
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/cde/codeDetailCheckJson.do", headers = "Accept=application/json")
	public String codeDetailCheckJson(@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	String codeId = (String) commandMap.get("codeId");
		String checkId = (String) commandMap.get("checkId");
		checkId = new String(checkId.getBytes("ISO-8859-1"), "UTF-8");

		if (checkId == null || "".equals(checkId))
			return "forward:/adm/ath/cde/viewCdeDetailCheck.do";
		
    	Map<String,String> checkMap = new HashMap<String, String>();
    	checkMap.put("codeId", codeId);
    	checkMap.put("checkId", checkId);

		int usedCnt = codeManageService.checkDescDplct(checkMap);
		model.addAttribute("usedCnt", usedCnt);
		model.addAttribute("checkId", checkId);

		return "jsonView";
	}
	
	/**
	 * 코드상세 등록 처리
	 * @param codeDetailVO 코드정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/cde/viewCodeDetailInsert.do
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/cde/insertCodeDetailJson.do", headers = "Accept=application/json")
	public String insertCodeDetailJson(@ModelAttribute("codeDetailVO") CodeDetailVO codeDetailVO, ModelMap model ) throws Exception {
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "uat/uia/EgovLoginUsr";
		}
		try {
			int result = codeManageService.insertCodeDetail(codeDetailVO);
			if (result > 0) {
				model.addAttribute("result", result);
    			model.addAttribute("resultMessage", "등록이 완료되었습니다.");
			} else {
				model.addAttribute("result", result);
    			model.addAttribute("resultMessage", "등록이 실패하였습니다.");
			}
		} catch (Exception e) {
			model.addAttribute("result", "Error");
			model.addAttribute("resultMessage", e.getMessage());
		}
		return "jsonView";
	}
	
	/**
	 * 코드 view 페이지
	 * @param searchDefaultVO 검색조건정보
	 * @param mberManageVO 일반회원초기화정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/cde/codeDetailInsert.do
	 * @throws Exception
	 */
	@RequestMapping("/adm/ath/cde/getCodeDetail.do")
	public String getCodeDetail(@ModelAttribute("codeDetailVO") CodeDetailVO codeDetailVO, ModelMap model )
			throws Exception {
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	   	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
	   	
	   	model.addAttribute("codeDetail", codeManageService.selectCodeDetail(codeDetailVO));
		
		return "adm/ath/cde/codeDetailView.md";
	}
	
	/**
	 * 코드 수정
	 * @param codeDetailVO 코드정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/cde/viewCodeDetailInsert.do
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/cde/updateCodeDetailJson.do", headers = "Accept=application/json")
	public String updateCodeDetailJson(@ModelAttribute("codeDetailVO") CodeDetailVO codeDetailVO, ModelMap model ) throws Exception {
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "uat/uia/EgovLoginUsr";
		}
		try {
			int result = codeManageService.updateCodeDetail(codeDetailVO);
			if (result > 0) {
				model.addAttribute("result", result);
    			model.addAttribute("resultMessage", "수정이 완료되었습니다.");
			} else {
				model.addAttribute("result", result);
    			model.addAttribute("resultMessage", "수정이 실패하였습니다.");
			}
		} catch (Exception e) {
			model.addAttribute("result", "Error");
			model.addAttribute("resultMessage", e.getMessage());
		}
		return "jsonView";
	}
	
	/**
	 * 코드 삭제
	 * @param codeDetailVO 코드정보
	 * @param model 화면모델
	 * @return forward:/adm/ath/cde/viewCodeDetailInsert.do
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/cde/deleteCodeDetailJson.do", headers = "Accept=application/json")
	public String deleteCodeDetailJson(@ModelAttribute("codeDetailVO") CodeDetailVO codeDetailVO, ModelMap model ) throws Exception {
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "uat/uia/EgovLoginUsr";
		}
		try {
			int result = codeManageService.deleteCodeDetail(codeDetailVO);
			if (result > 0) {
				model.addAttribute("result", result);
    			model.addAttribute("resultMessage", "삭제가 완료되었습니다.");
			} else {
				model.addAttribute("result", result);
    			model.addAttribute("resultMessage", "삭제가 실패하였습니다.");
			}
		} catch (Exception e) {
			model.addAttribute("result", "Error");
			model.addAttribute("resultMessage", e.getMessage());
		}
		return "jsonView";
	}
}