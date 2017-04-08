<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
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
<body class="ui-layout">
	<div data-ax5layout="ax5" id="modalLayout" data-config="{layout:'dock-panel',autoResize:true}" style="overflow: auto;">
		<tiles:insertAttribute name="contents" />
	</div>
</body>
</html>