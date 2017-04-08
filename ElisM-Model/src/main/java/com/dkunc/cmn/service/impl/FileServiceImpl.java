package com.dkunc.cmn.service.impl;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dkunc.cmn.CmmConst;
import com.dkunc.cmn.CmmConst.FILE_USAGE_TYPE;
import com.dkunc.cmn.service.FileService;
import com.dkunc.cmn.service.FileVO;
import com.dkunc.cmn.utils.DateUtil;
import com.dkunc.cmn.utils.StringUtil;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("FileService")
public class FileServiceImpl extends EgovAbstractServiceImpl implements FileService {
	public static String getPath(FILE_USAGE_TYPE type) {
        return getPathRoot() + type.getId() + File.separator; // ex) C:\opt\file\ebz\inline\ or C:\opt\file\ebz\attach\ or C:\opt\file\ebz\rfc_temp\
    }
    
    /**
     * 2015.10.06 김주신
     * 파일 첨부 파일경로 서버타입 변수 추가에 따른 기존 함수 변경       
     * @return getPathRoot("")
     */
    public static String getPathRoot() {        
        return getPathRoot("");
    }
    
    public static String getPathRoot(String serverType) {
        String fileRootPath = null;
        try {
            if (SystemUtils.IS_OS_WINDOWS)
                fileRootPath = CmmConst.WINDOWS_FILE_DIR_PREFIX + serverType + File.separator; // ex) C:\opt\file\ebz\ or C:\opt\file\ebz\
            else if (SystemUtils.IS_OS_LINUX)
                fileRootPath = CmmConst.LINUX_FILE_DIR_PREFIX + serverType + File.separator;// ex) /opt/file/ebz/ or /opt/file/ebz/
            else
                fileRootPath = CmmConst.LINUX_FILE_DIR_PREFIX + serverType + File.separator;// ex) /opt/file/ebz/ or /opt/file/ebz/
        } catch (Exception e) {
        }
        return fileRootPath;
    }
    
	@Override
    public void downloadAllNewFile(HttpServletRequest request, HttpServletResponse response, String fileName, String fileUrl) {
        String basePath = getPathRoot(); // /opt/file/ebz/
        String fullPath = basePath + StringUtil.transOsSeperator(fileUrl); // /opt/file/ebz/ + inline/sys/20150918/e9be5b12-bf27-4169-8970-96eabb9a0537.jpg
        File file = new File(fullPath);
        response.setContentLength((int) file.length());
        String browser = request.getHeader("User-Agent");
        String downName = null;
        try {
            if (browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
                downName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            } else {
                downName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.setHeader("Content-disposition", "attachment;filename=" + downName);
            response.setHeader("Content-Transfer-Encoding", "binary");
            OutputStream out = response.getOutputStream();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                FileCopyUtils.copy(fis, out);
            } catch (Exception e) {
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Exception e2) {
                    }
                }
            }
            out.flush();
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
    }
}
