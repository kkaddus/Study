<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import ="com.dkunc.cmn.vo.LoginVO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <tiles:importAttribute name="javascripts" /> --%>
<tiles:importAttribute name="stylesheets" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="content-language" content="ko">
<title>UOnFramework Sample</title>
<c:forEach var="css" items="${stylesheets}">
	<link rel="stylesheet" type="text/css" href="${css}" />
</c:forEach>
</head>
<body>
<noscript><p>21자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</p></noscript>
<!-- login status start -->
<tiles:insertAttribute name="top" />
<!-- //login status end -->
<!-- wrap start -->
<div id="wrap">

	<!-- header start -->
	<tiles:insertAttribute name="header" />
	<!-- //header end -->

	<!-- 타이틀이미지, 로그인 시작 -->
	<tiles:insertAttribute name="contents" />

	<!-- footer 시작 -->
	<tiles:insertAttribute name="footer" />
	<!-- //footer 끝 -->
</div>
<!-- //wrap end -->
</body>
</html>