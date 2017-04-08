<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" class="ui-layout">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="" name="title" />
    <meta content="unc ui framework" name="description" />
    <meta content="hj" name="author" />
    <meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width, shrink-to-fit=yes" />
    <link rel="stylesheet" type="text/css" href="/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/lib/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/login.css">
    <script src="/lib/jquery/jquery-1.12.4.min.js"></script>
</head>
<body class="">
	<!-- <form id="frmTest" name="frmTest"> -->
    <div class="login-d-table col-xs-11 col-sm-10 col-md-6 col-lg-2">
        <div class="login-d-table-cell">
            <div class="login-box" style="margin-bottom:100px;">
                <h3 class="title"><img src="/img/uon.png" style="height:70px;" alt=""> UOn Framework</h3>
                <form id="loginForm" name="loginForm" method="post">
	                <input type="text" id="userId" name="userId" class="form-control" placeholder="ID">
	                <input type="password" id="pwd" name="pwd" class="form-control margin-t-10" placeholder="Password">
					<input type="hidden" name="message" value="${message}" />

	                <div class="margin-t-30">
	                    <a href="#" id="btnLogin" class="btn btn-primary  margin-l-10 float-right"> 로그인 </a>
	                    <div class="float-left">
	                        <div class="checkbox checkbox-primary checkbox-inline">
	                            <input type="checkbox" id="chkSaveId" name="chkSaveId" onClick="javascript:saveid(this);">
	                            <label for="chkSaveId">
	                              ID 저장
	                            </label>
	                        </div>
	                        <br>
	                    </div>
	                </div>
                </form>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
    <!-- </form> -->
</body>
<script type="text/javascript">
$(document).ready(function () {
    $("#btnLogin").click(function(e){
    	e.preventDefault();
    	if ("" == $("#userId").val()) {
            alert("아이디를 입력하세요");
        } else if ("" == $("#pwd").val()) {
            alert("비밀번호를 입력하세요");
        } else {
            document.loginForm.action="/cmn/lgn/login.do";
            document.loginForm.submit();
        }
    });
    
    $("#pwd").keydown(function(key) {
		if (key.keyCode == 13) {
			$("#btnLogin").click();
		}
	});
    fnInit();
});

function setCookie(name, value, expires) {
    document.cookie = name + "=" + escape(value) + "; path=/; expires=" + expires.toGMTString();
}

function getCookie(Name) {
    var search = Name + "="
    if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
        offset = document.cookie.indexOf(search)
        if (offset != -1) { // 쿠키가 존재하면
            offset += search.length
            // set index of beginning of value
            end = document.cookie.indexOf(";", offset)
            // 쿠키 값의 마지막 위치 인덱스 번호 설정
            if (end == -1)
                end = document.cookie.length
            return unescape(document.cookie.substring(offset, end))
        }
    }
    return "";
}

function saveid(saveIdChekbox) {

    var expdate = new Date();
    // 기본적으로 30일동안 기억하게 함. 일수를 조절하려면 * 30에서 숫자를 조절하면 됨
    if ($(saveIdChekbox).is(":checked")){
    	if ("" == $("#userId").val()) {
            alert("아이디를 입력하세요");
            $(saveIdChekbox).prop("checked", false);
            return false;
        }
    	expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
    } else {
        expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
    }
    
    debugger;
    setCookie("saveid", $("#userId").val(), expdate);
}

function getid(form) {
    var saveId = null != getCookie("saveid") && 'undefined' != getCookie("saveid") ? getCookie("saveid") : "";
    $("#userId").val(saveId);
    $("#chkSaveId").prop("checked", "" != saveId);
}

function fnInit() {
    var message = document.loginForm.message.value;
    if (message != "") {
        alert(message);
    }

    getid(document.loginForm);
}
</script>
</html>