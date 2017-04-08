package com.dkunc.cmn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface FileService {
	public void downloadAllNewFile(HttpServletRequest request, HttpServletResponse response, String fileName, String fileUrl);
}
