<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javaScript">
var myModel = new ax5.ui.binder();

$(document.body).ready(function () {
	$("#checkId").keydown(function(key) {
		if (key.keyCode == 13) {
			fnCheckCode();
		}
	});
});

function fnCheckCode(){
    if($("#checkId").val()==""){
        alert("중복조회할 코드를 입력하십시오.");
        $("#checkId").focus();
        return;
    }
    if(fnCheckNotKorean($("#checkId").val())){
    	$("#checkId").val($("#checkId").val().toUpperCase());
    	$.ajax({
    		type : "POST",
    		url : "/adm/ath/cde/codeDetailCheckJson.do",
    		dataType : "json",
    		data : {"checkId" : $("#checkId").val(),
    				"codeId" : parent.$("#codeId").val()
    				},
    		beforeSend : function() {
    		},
    		success : function(data) {
				$("#resultId").val(data.checkId);
				$("#usedCnt").val(data.usedCnt);
    			if(data.usedCnt == "0"){
        	        $("#resultdiv").html(data.checkId + "는 사용가능한 코드입니다.");
    			}else if(data.usedCnt == "-1"){
        	        $("#resultdiv").html("중복확인을 실행하십시오");
    			}else{
        	        $("#resultdiv").html(data.checkId + "는 사용할수 없는 코드입니다.");
    			}
    		}
    	});	
    }else{
        alert("한글은 사용할 수 없습니다.");
        return;
    }
}
function fnReturnCode(){
    if ($("#usedCnt").val() == "0"){
        $("#codeDesc", parent.document).val($("#resultId").val());
    	$("#idChkModalClose").click(); 
    }else if ($("#usedCnt").val() == "1"){
        alert("이미사용중인 코드입니다.");
        return;
    }else{
        alert("먼저 중복확인을 실행하십시오");
        return;
    }
}

</script>
<form name="checkForm" id="checkForm" onsubmit="return false;" style="padding: 5px; text-align: center; overflow:auto;">
	<table class="input-table table table-bordered">
		<colgroup>
			<col style="width:30%" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th>사용할 아이디</th>
				<td>
					<input type="hidden" id="resultId" name="resultId" />
					<input type="hidden" id="usedCnt" name="usedCnt" value="-1" />
					<input type="text" name="checkId" id="checkId" class="form-control input-sm" data-ax-path="checkId" size="20" maxlength="20">
				</td>
			</tr>
			<tr style="height:55px;">
				<th style="text-align: center;padding-top: 15px;">결과</th>
				<td class="v-align-middle padding-10">
					<div id="resultdiv" name="resultdiv" style="text-align: left;">중복확인을 실행하십시오</div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-center">
		<a href="#" onclick="fnCheckCode();" class="btn btn-grey">조회</a>
		<a href="#" onclick="fnReturnCode();" class="btn btn-primary">사용</a>
		<a href="#" class="btn btn-default modal-close" id="idChkModalClose"><spring:message code="button.close" /></a>
	</div>
</form>
