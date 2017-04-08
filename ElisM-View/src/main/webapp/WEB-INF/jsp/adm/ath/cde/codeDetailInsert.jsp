<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

function fnCodeCheck(){
	fnModalOpen("url", "primary", "300", "160", "11000", "중복코드 검색", "/adm/ath/cde/viewCdeDetailCheck.do", "");
}

function fnInsert(){
	validationCheck();
	if(valCheck){
		var paramData = fn_disabledSerialize($("#frmCodeInsert"));
		var jsonString = JSON.stringify(paramData);
		$.ajax({
			type : "POST",
			url : "/adm/ath/cde/insertCodeDetailJson.do",
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
<form name="frmCodeInsert" id="frmCodeInsert" onsubmit="return false;" style="padding: 5px; text-align: center; top: 44px; left: 254px;overflow:auto;">
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
				<th>코드번호<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<select name="codeId" id="codeId" class="form-control input-sm">
						<c:forEach var="item" items="${codeId_result}" varStatus="status">
						    <option value="${fn:trim(item.codeId)}">${item.codeNm}(${item.codeId})</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>코드상세번호<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목" width="15" height="15" /></th>
				<td>
					<div class="form-inline" style="text-align: left;">
                         <div class="input-group input-group-sm">
							<input type="text" size="20" maxlength="10" disabled id="codeDesc" name="codeDesc" class="form-control input-sm validationCheck" >
                            <div class="input-group-btn"><a href="#" onclick="javascript:fnCodeCheck();" class="btn btn-grey">중복코드 검색</a></div>
                         </div>
                    </div>
				</td>
			</tr>
			<tr>
				<th>코드이름<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<input type="text" size="20" maxlength="60" id="codeNm" name="codeNm" class="form-control input-sm validationCheck" >
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-center">
		<a href="#" onclick="fnInsert();" class="btn btn-primary"><spring:message code="button.create" /></a>
		<a href="#" class="btn btn-default modal-close" id="insertModalClose"><spring:message code="button.close" /></a>
	</div>
</form>
