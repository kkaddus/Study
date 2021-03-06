<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="/lib/jquery/jquery.serializejson.min.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">

$(document.body).ready(function () {
	$("#codeNm").keydown(function(key) {
		if (key.keyCode == 13) {
			fnInsert();
		}
	});
});

function fnUpdate(){
	validationCheck();
	if(valCheck){
		var paramData = fn_disabledSerialize($("#frmCodeUpdate"));
		var jsonString = JSON.stringify(paramData);
		$.ajax({
			type : "POST",
			url : "/adm/ath/cde/updateCodeMasterJson.do",
			dataType : "json",
			data : paramData,
			success : function(data, textStatus, XMLHttpRequest) {
				alert(data.resultMessage);
				$("#refresh", parent.document).click();
				$("#insertModalClose").click();
			},error : function(xhr, status, error) {
				alert("AJAX 오류");
	        }
		});
	}
}
function fnDelete(){
	if(valCheck){
		var paramData = fn_disabledSerialize($("#frmCodeUpdate"));
		var jsonString = JSON.stringify(paramData);
		$.ajax({
			type : "POST",
			url : "/adm/ath/cde/deleteCodeMasterJson.do",
			dataType : "json",
			data : paramData,
			success : function(data, textStatus, XMLHttpRequest) {
				alert(data.resultMessage);
				$("#refresh", parent.document).click();
				$("#insertModalClose").click();
			},error : function(xhr, status, error) {
				alert("AJAX 오류");
	        }
		});
	}
}
</script> 
<form name="frmCodeUpdate" id="frmCodeUpdate" onsubmit="return false;" style="padding: 5px; text-align: center; top: 44px; left: 254px;overflow:auto;">
    <!-- 검색조건 유지 -->
    <input type="hidden" id="searchCondition" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>" />
    <input type="hidden" id="searchKeyword" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>" />
    <input type="hidden" id="sbscrbSttus" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>" />
    <input type="hidden" id="pageIndex" name="pageIndex" value="0" />
	<table class="input-table table table-bordered">
		<colgroup>
			<col style="width:30%" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th>코드번호<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목" width="15" height="15" /></th>
				<td>
					<input type="text" size="20" maxlength="10" disabled id="codeId" name="codeId" class="form-control input-sm validationCheck" value="${codeMaster.codeId}"/>
				</td>
			</tr>
			<tr>
				<th>코드이름<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<input type="text" size="20" maxlength="60" id="codeNm" name="codeNm" class="form-control input-sm validationCheck" value="${codeMaster.codeNm}">
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-center">
		<a href="#" onclick="fnUpdate();" class="btn btn-primary"><spring:message code="button.update" /></a>
		<a href="#" onclick="fnDelete();" class="btn btn-primary"><spring:message code="button.delete" /></a>
		<a href="#" class="btn btn-default modal-close" id="upateModalClose"><spring:message code="button.close" /></a>
	</div>
</form>
