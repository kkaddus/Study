<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a class="header-element" id="sidebarOpenBtn"><i class="fa fa-bars"></i></a>
<div class="header-element"><img src="/img/logo.png" class="logo"></div>
<a class="header-element float-right" href="javascript:actionLogout();">logout</a>
<form id="selectOne" name="selectOne"></form>
<script type="text/javascript">
    function actionLogout()
    {
        document.selectOne.action = "<c:url value='/cmn/lgn/loginOut.do'/>";
        document.selectOne.submit();
    }
</script>