package com.dkunc.adm.ath.brd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dkunc.adm.ath.brd.service.BoardManageService;
import com.dkunc.adm.ath.brd.vo.BoardAttachInfoVO;
import com.dkunc.adm.ath.brd.vo.BoardInfoVO;
import com.dkunc.cmn.ComDefaultVO;
import com.dkunc.cmn.EgovMessageSource;
import com.dkunc.cmn.SearchDefaultVO;
import com.dkunc.cmn.service.EgovCmmUseService;
import com.dkunc.cmn.service.EgovProperties;
import com.dkunc.cmn.service.FileService;
import com.dkunc.cmn.utils.StringUtil;
import com.dkunc.cmn.vo.LoginVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller@SessionAttributes(types = ComDefaultVO.class)
public class BoardMainController {

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
	
	@Resource(name = "boardManageService")
	private BoardManageService boardManageService;

	/**
	 * 게시판 메인 페이지 호출
	 */
	@RequestMapping(value = "/adm/ath/brd/viewBoardInfo.do")
	public String viewBoardInfo(ModelMap model) throws Exception{
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	
    	return "adm/ath/brd/boardInfoList.mg";
	}
	
	
	/**
	 * 부서관리 목록 조회 및 검색한다. (pageing)
	 * @param searchDefaultVO 검색조건정보
	 * @param model 화면모델
	 * @return adm/ath/usr/EgovMberManage
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/ath/brd/getBoardInfoListJson.do", headers = "Accept=application/json")
	public String getBoardInfoListJson(@ModelAttribute("userSearchVO") SearchDefaultVO searchDefaultVO, ModelMap model) throws Exception {

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

		List<BoardInfoVO> resultList = boardManageService.selectBoardList(searchDefaultVO);
		
		
		model.addAttribute("resultList", resultList );

		int totCnt = boardManageService.selectBoardListTotCnt(searchDefaultVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "jsonView";
	}
	
	/**
	 * 게시판 글작성 모달 페이지 호출
	 */
	@RequestMapping(value = "/adm/ath/brd/ViewBoardInfoInsert.do")
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
    		
    		searchDefaultVO.setSearchCondition("tUid");
    		searchDefaultVO.setSearchKeyword(request.getParameter("tUid"));
    		List<BoardInfoVO> resultList = boardManageService.selectBoardList(searchDefaultVO);
    		
    		model.addAttribute("resultList", resultList.get(0));
    		model.addAttribute("tUid", request.getParameter("tUid"));
    	}
    	
    	model.addAttribute("mode", mode);
    	
    	return "adm/ath/brd/boardInfoInsert.md";
	}
	
	
	/**
	 * 네이버 에디터 인라인 이미지 추가 (링크 url 생성)
	 */
    @RequestMapping(value = "/adm/ath/brd/inlineImageUpload.do")
    private String InlineImageUpload( HttpServletRequest request , HttpServletResponse response, ModelMap model) throws Exception {
    	 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
         List<MultipartFile> files = multipartRequest.getFiles("Filedata");
         
         String stordFilePath = EgovProperties.getProperty("Globals.FileStoragePath");
         
         for(int i=0; i < files.size(); i ++ ) {
        	 String fileName = files.get(i).getOriginalFilename();
        	 File destination = File.createTempFile("file", fileName, new File(stordFilePath));
             files.get(i).transferTo(destination);
             
             model.addAttribute("filename" , destination.getName());
             model.addAttribute("path" ,  "http://localhost:8080/adm/ath/brd/inlineImageUrl.do?imagePath=" + destination.getName());
         }
         
        return "adm/ath/brd/result";
    }
    
    
    /**
	 * 에디터 인라인 이미지 생성 후 해당 링크 url 호출시 이미지 파일을 생성한다.
	 */
    @RequestMapping(value = { "/adm/ath/brd/inlineImageUrl.do" })
    public void getLinkImage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mode = StringUtil.nvl(request.getParameter("mode"));
        String linkImage = StringUtil.nvl(request.getParameter("imagePath"));
        // mode에 따라서 저장되어 있는 스토리지 주소를 변경해준다.
        String subDir = "";
        String stordFilePath = EgovProperties.getProperty("Globals.FileStoragePath");
        
        String boardPath = stordFilePath;
        File file = null;
        String name = null;
        String ext = "";
        String type = "image/png";
        try {
            ext = linkImage.substring(linkImage.lastIndexOf(".") + 1); // 요청한 image로 mime타입 지정
            ext = ext.toLowerCase();
            if ("jpg".equals(ext)) {
                type = "image/jpeg";
            } else {
                type = "image/" + ext;
            }
        } catch (Exception e1) {
            type = "image/png";
        }
        if (linkImage != "" && linkImage != null) {
            file = new File(boardPath + linkImage);
            if(file.exists() && file.isFile()){
                OutputStream outStream = null; //response.getOutputStream();
                FileInputStream inputStream = null; //new FileInputStream(file);
                try{
                    outStream = response.getOutputStream();
                    inputStream = new FileInputStream(file);
                    
                    MagicMatch match = Magic.getMagicMatch(file, false);
                    type = match.getMimeType();
                    name = file.getName();
                    try {
                        ext = name.substring(name.lastIndexOf(".") + 1);
                        if (StringUtils.isNotEmpty(ext)) {
                            ext = ext.toLowerCase();
                            if ("jpg".equals(ext)) {
                                type = "image/jpeg";
                            } else {
                                type = "image/" + ext;
                            }
                        } else {
                        }
                    } catch (Exception e) {
                        type = "image/png";
                    }
                    response.setHeader("Content-Type", type);
                    response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
                    byte[] buffer = new byte[1024];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }
                } catch(Exception e){
                } finally {
                    try{
                        if(null != inputStream){
                            inputStream.close();
                        }
                    } catch(Exception ex){}
                    try{
                        if(null != outStream){
                            outStream.close();
                        }
                    } catch(Exception ex2){}
                }
            }
        } 
        response.setHeader("Content-Type", type);
    }
    
    @RequestMapping(value = "/adm/ath/brd/boardSave.do")
    private String boardSave( HttpServletRequest request , HttpServletResponse response, ModelMap model, BoardInfoVO boardInfoVO) throws Exception {
    	if("write".equals(boardInfoVO.getMode())) {
    		if( fileUpload( request , response, model, boardInfoVO) ) {
        		// 게시판 데이터 insert
        		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
        		boardInfoVO.setCreateId(user.getId());
        		
        		boardManageService.insertBoardInfo(boardInfoVO);
        		model.addAttribute("result" , "success");
        	} else {
        		 model.addAttribute("result" , "fail");
        	}
    	} else if("edit".equals(boardInfoVO.getMode())) {
    		if( fileUpload( request , response, model, boardInfoVO) ) {
        		// 게시판 데이터 insert
        		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
        		boardInfoVO.setUpdateId(user.getId());
        		
        		boardManageService.updateBoardInfo(boardInfoVO);
        		model.addAttribute("result" , "success");
        	} else {
        		 model.addAttribute("result" , "fail");
        	}
    	}
    	
    	
        return "uploadView";
    }
    
    private boolean fileUpload( HttpServletRequest request , HttpServletResponse response, ModelMap model, BoardInfoVO boardInfoVO) throws Exception {
    	try {
    		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = multipartRequest.getFiles("uploadFile");
            
            Date now = new Date();
            String yyyy = new SimpleDateFormat("yyyy").format(now);
            String MM = new SimpleDateFormat("MM").format(now);
            String dd = new SimpleDateFormat("dd").format(now);
            
            String stordFilePath = EgovProperties.getProperty("Globals.FileStoragePath") +  yyyy + MM + dd + File.separator;
        
            for(int i=0; i < files.size(); i ++ ) {
           	 	String fileName = files.get(i).getOriginalFilename();
           	 	
           	 	// 파일스토리지에 해당 디렉토리가 있는지 확인
                File uploadDir = new File(stordFilePath);
                if (!uploadDir.exists() || uploadDir.isFile()) {
                    uploadDir.mkdirs();
                }
           	 	File destination = new File(stordFilePath , UUID.randomUUID().toString() + "_" + fileName);
           	 	
                files.get(i).transferTo(destination);
                
                BoardAttachInfoVO boardAttachInfoVO = new BoardAttachInfoVO();

                boardAttachInfoVO.setBoardNo(boardInfoVO.getBoardNo());
                boardAttachInfoVO.setFileSize(destination.length() / 1024); // kilobytes
                boardAttachInfoVO.setFileType(destination.getName().substring(destination.getName().lastIndexOf(".") + 1)); 
                boardAttachInfoVO.setFileName(fileName); 
                boardAttachInfoVO.setFilePath(stordFilePath  + fileName); 
                boardAttachInfoVO.setFolderName(yyyy + MM + dd); 
                boardAttachInfoVO.setDeleteFlag("N"); 
                
                boardManageService.insertBoardAttachInfo(boardAttachInfoVO);
            }
    	} catch(Exception e) {
    		return false;
    	}	
   	 
        return true;
   }
    
    @RequestMapping(value = "/adm/ath/brd/download.do", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        InetAddress Address = InetAddress.getLocalHost(); 
        String IP = Address.getHostAddress();               //서버의 IP정보를 가져옴
        String filePath = "";
        String fileName = "";
        
        String stordFilePath = EgovProperties.getProperty("Globals.FileStoragePath");
        
        filePath = stordFilePath;
        fileName = request.getParameter("fileName");
        
        String docId = request.getParameter("docId");
        String locId = request.getParameter("locId");
        
        filePath =  StringEscapeUtils.unescapeXml(filePath);
        fileName =  StringEscapeUtils.unescapeXml(fileName);
        File file = null;
        FileSystemResource realFile = null;
        
        try {
            file = new File(filePath, fileName);
            
            if(file.exists()) {
            	setDisposition(fileName, request, response);
                realFile = new FileSystemResource(file);
            }
        } catch(Exception e) {
            realFile = null;
            e.printStackTrace();
        }
        
        return realFile;
    }
    
    public static String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
            return "Trident";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }
    
    public static void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String browser = getBrowser(request);
        String dispositionPrefix = "attachment; filename=";
        String encodedFilename = null;
        if (browser.equals("MSIE")) {
            encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
            encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Firefox")) {
            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Opera")) {
            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Chrome")) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < filename.length(); i++) {
                char c = filename.charAt(i);
                if (c > '~') {
                    sb.append(URLEncoder.encode("" + c, "UTF-8"));
                } else {
                    sb.append(c);
                }
            }
            encodedFilename = "\"" + sb.toString() + "\"";
        } else {
            throw new IOException("Not supported browser");
        }
        response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
        if ("Opera".equals(browser)) {
            response.setContentType("application/octet-stream;charset=UTF-8");
        }
    }

    
}