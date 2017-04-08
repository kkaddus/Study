<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<tiles:importAttribute name="stylesheets" />
<tiles:importAttribute name="javascripts" />

<!-- javascript 도 동일하게 처리 -->
<html lang="ko" class="et-body">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="" name="title" />
<meta content="unc ui framework" name="description" />
<meta content="hj" name="author" />
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width, shrink-to-fit=yes"/>
<title>UOnFramework Sample</title>
<c:forEach var="css" items="${stylesheets}">
	<link rel="stylesheet" type="text/css" href="${css}" />
</c:forEach>
</head>
<c:forEach var="script" items="${javascripts}">
	<script type="text/javascript" src="${script}"></script>
</c:forEach>
<script type="text/javascript" src="/js/manageCommon.js"></script>
<body class="et-body">
	<div class="et-layout">
		<tiles:insertAttribute name="header" />
		
<%-- 		<tiles:insertAttribute name="footer" /> --%>
		
		<tiles:insertAttribute name="left" />
		
		<tiles:insertAttribute name="contents" />
	</div>
</body>
</html>