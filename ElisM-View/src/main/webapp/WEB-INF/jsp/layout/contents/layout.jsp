<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> --%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<tiles:importAttribute name="stylesheets" />
<!-- javascript 도 동일하게 처리 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="content-language" content="ko">
<title>UOnFramework Sample</title>
<c:forEach var="css" items="${stylesheets}">
	<link rel="stylesheet" type="text/css" href="${css}" />
</c:forEach>
</head>
<body>
<!-- login status start -->
<tiles:insertAttribute name="top" />
<!-- //login status end -->
<!-- wrap start -->
<div id="wrap">
    <!-- header start -->
    <tiles:insertAttribute name="header" />
    <!-- //header end -->
    <!--  타이틀 이미지 시작 -->
    <div id="title_img_div"><img src='<tiles:getAsString name="titleImage" />' alt="" /></div>
    <!--  //타이틀 이미지 끝 -->
    <div id="bodywrap">
    	<tiles:insertAttribute name="left" />
        
        <tiles:insertAttribute name="contents" />
    </div>
    <!-- footer 시작 -->
    <tiles:insertAttribute name="footer" />
    <!-- //footer 끝 -->
</div>
<!-- //wrap end -->
</body>
</html>
