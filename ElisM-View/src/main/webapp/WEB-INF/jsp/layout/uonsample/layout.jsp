<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<tiles:importAttribute name="stylesheets" />
<tiles:importAttribute name="javascripts" />

<!-- javascript 도 동일하게 처리 -->
<html lang="ko" class="ui-layout">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="" name="title" />
<meta content="unc ui framework" name="description" />
<meta content="hj" name="author" />
<meta name="viewport"
	content="user-scalable=yes, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width, shrink-to-fit=yes" />
<title>UOnFramework Sample</title>
<c:forEach var="css" items="${stylesheets}">
	<link rel="stylesheet" type="text/css" href="${css}" />
</c:forEach>
<link rel="stylesheet" type="text/css" href="/css/uonsample/main.css">
<link rel="stylesheet" type="text/css" href="/css/uonsample/demo.css">
</head>
<c:forEach var="script" items="${javascripts}">
	<script type="text/javascript" src="${script}"></script>
</c:forEach>
<script type="text/javascript" src="/js/uonsample/controller/main1.js"></script>
<script type="text/javascript" src="/lib/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="/lib/fancytree/jquery.fancytree-all.js"></script>
<body class="ui-layout">
	<div data-ax5layout="ax5" id="mainLayout" data-config="{layout:'dock-panel',autoResize:true}" style="">
		<tiles:insertAttribute name="header" />
		
		<tiles:insertAttribute name="footer" />
		
		<tiles:insertAttribute name="left" />
		
		<tiles:insertAttribute name="contents" />
	</div>
</body>
</html>