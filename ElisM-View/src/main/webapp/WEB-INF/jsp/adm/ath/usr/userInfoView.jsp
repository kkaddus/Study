<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="/lib/jquery/jquery.serializejson.min.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">

$(document.body).ready(function () {
    var pageIndex = "${userSearchVO.pageIndex}";
    if(pageIndex == ""){
        $("#pageIndex").val("0");
    }else{
        $("#pageIndex").val(pageIndex);
    }
});

function fnUpdate(){
	validationCheck();
	if(valCheck){
		var paramData = fn_disabledSerialize($("#frmUserUpdate"), "json");
		var jsonString = JSON.stringify(paramData);
		$.ajax({
			type : "POST",
			url : "/adm/ath/usr/updateUserInfoJson.do",
			dataType : "json",
			data : paramData,
			success : function(datas, textStatus, XMLHttpRequest) {
				if(datas.result > 0){
					alert(datas.resultMessage);
					$("#userInfoRefresh").click();
				}else{
					alert(datas.resultMessage);
				}
			},error : function(xhr, status, error) {
				alert("AJAX 오류");
	        }
		});
	}
}
</script> 
<form name="frmUserUpdate" id="frmUserUpdate" onsubmit="return false;" style="padding: 5px; text-align: center; top: 44px; left: 254px;overflow:auto;">
    <!-- 검색조건 유지 -->
    <input type="hidden" id="searchCondition" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>" />
    <input type="hidden" id="searchKeyword" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>" />
    <input type="hidden" id="sbscrbSttus" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>" />
    <input type="hidden" id="pageIndex" name="pageIndex" value="0" />
    <input type="hidden" id="type" name="type" value="U" />
	<table class="input-table table table-bordered">
		<colgroup>
			<col style="width:30%" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th>일반회원 아이디</th>
				<td>
					<input type="text" size="20" maxlength="20" disabled="disabled" id="userId" name="userId" class="form-control input-sm" value="${userInfo.userid}">
				</td>
			</tr>
			<tr>
				<th>일반회원이름<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<input type="text" size="20" maxlength="60" id="userNm" name="userNm" class="form-control input-sm validationCheck" value="${userInfo.usernm}">
				</td>
			</tr>
			<tr>
				<th>비밀번호힌트<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<select name="pwdHint" id="pwdHint" title="비밀번호힌트" class="form-control input-sm validationCheck">
						<c:forEach var="item" items="${passwordHint_result}" varStatus="status">
							<c:choose>
								<c:when test="${fn:trim(item.codeDesc) == userInfo.pwdhint}">
									<option value="${fn:trim(item.codeDesc)}" selected>${item.codeNm}</option>
								</c:when>
								<c:otherwise>
									<option value="${fn:trim(item.codeDesc)}">${item.codeNm}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>비밀번호정답<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<input type="text" size="50" maxlength="100" id="pwdCnsr" name="pwdCnsr" class="form-control input-sm" value="${userInfo.pwdcnsr}">
				</td>
			</tr>
			<tr>
				<th>직급<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<select name="jobCd" id="jobCd" title="직급" class="form-control input-sm">
						<c:forEach var="item" items="${level_result}" varStatus="status">
							<c:choose>
								<c:when test="${fn:trim(item.codeDesc) == userInfo.jobcd}">
									<option value="${fn:trim(item.codeDesc)}" selected>${item.codeNm}</option>
								</c:when>
								<c:otherwise>
									<option value="${fn:trim(item.codeDesc)}">${item.codeNm}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>직책<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<select name="positionCd" id="positionCd" title="직책" class="form-control input-sm">
						<c:forEach var="item" items="${position_result}" varStatus="status">
							<c:choose>
								<c:when test="${fn:trim(item.codeDesc) == userInfo.positioncd}">
									<option value="${fn:trim(item.codeDesc)}" selected>${item.codeNm}</option>
								</c:when>
								<c:otherwise>
									<option value="${fn:trim(item.codeDesc)}">${item.codeNm}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>직위<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<select name="titleCd" id="titleCd" title="직위" class="form-control input-sm">
						<c:forEach var="item" items="${title_result}" varStatus="status">
							<c:choose>
								<c:when test="${fn:trim(item.codeDesc) == userInfo.titlecd}">
									<option value="${fn:trim(item.codeDesc)}" selected>${item.codeNm}</option>
								</c:when>
								<c:otherwise>
									<option value="${fn:trim(item.codeDesc)}">${item.codeNm}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
					<input type="text" size="20" maxlength="20" id="telNo" name="telNo" class="form-control input-sm" value="${userInfo.telno}">
				</td>
			</tr>
			<tr>
				<th>팩스번호</th>
				<td>
					<input type="text" size="20" maxlength="15" id="faxNo" name="faxNo" class="form-control input-sm" value="${userInfo.faxno}">
				</td>
			</tr>
			<tr>
				<th>핸드폰번호</th>
				<td>
					<input type="text" size="20" maxlength="15" id="mobileNo" name="mobileNo" class="form-control input-sm" value="${userInfo.mobileno}">
				</td>
			</tr>
			<tr>
				<th>이메일주소</th>
				<td>
					<input type="text" size="30" maxlength="50" id="email" name="email" class="form-control input-sm" value="${userInfo.email}">
				</td>
			</tr>
			<tr>
				<th>승인여부<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<select name="userSt" id="userSt" title="승인여부" class="form-control input-sm validationCheck">
						<c:forEach var="item" items="${status_result}" varStatus="status">
							<c:choose>
								<c:when test="${fn:trim(item.codeDesc) == userInfo.userst}">
									<option value="${fn:trim(item.codeDesc)}" selected>${item.codeNm}</option>
								</c:when>
								<c:otherwise>
									<option value="${fn:trim(item.codeDesc)}">${item.codeNm}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>사용여부<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<select name="usageYn" id="usageYn" title="사용여부" class="form-control input-sm validationCheck">
						<c:forEach var="item" items="${usage_result}" varStatus="status">
							<c:choose>
								<c:when test="${fn:trim(item.codeDesc) == userInfo.usageyn}">
									<option value="${fn:trim(item.codeDesc)}" selected>${item.codeNm}</option>
								</c:when>
								<c:otherwise>
									<option value="${fn:trim(item.codeDesc)}">${item.codeNm}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-center">
		<a href="#" onclick="fnUpdate();" class="btn btn-primary"><spring:message code="button.update" /></a>
		<a href="#" class="btn btn-default modal-close" id="upateModalClose"><spring:message code="button.close" /></a>
	</div>
</form>
