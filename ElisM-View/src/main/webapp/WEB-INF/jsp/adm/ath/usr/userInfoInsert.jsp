<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="/lib/jquery/jquery.serializejson.min.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">

$(document.body).ready(function () {
    var pageIndex = "${searchUserVO.pageIndex}";
    if(pageIndex == ""){
        $("#pageIndex").val("0");
    }else{
        $("#pageIndex").val(pageIndex);
    }
});

function fnIdCheck(){
	fnModalOpen("url", "primary", "300", "160", "11000", "중복아이디 검색", "/adm/ath/usr/viewUserInfIdCheck.do", "");
}

function fnDeptInfoSearchlazyTree(){
	fnModalOpen("url", "primary", "300", "460", "11000", "부서 정보", "/cmn/tre/getDeptInfolazyTree.do?mode=D", "");
}

function fnInsert(){
	validationCheck();
	if(valCheck){
		if(checkPassWord($("#userId").val(), $("#pwd").val(), $("#pwd2").val())){
			var paramData = fn_disabledSerialize($("#frmUserInser"), "");
			$.ajax({
				type : "POST",
				url : "/adm/ath/usr/insertUserInfoJson.do",
				dataType : "json",
				data : paramData,
				success : function(data, textStatus, XMLHttpRequest) {
					if(data.result > 0){
						alert(data.resultMessage);
						$("#userInfoRefresh").click();
					}else{
						alert(data.resultMessage);
					}
				},error : function(xhr, status, error) {
					alert("AJAX 오류");
		        }
			});
		}
	}
}

function checkPassWord(ObjUserID, ObjUserPassWord, objUserPassWordRe){
	if (ObjUserPassWord != objUserPassWordRe) {
		alert("입력하신 비밀번호와 비밀번호확인이 일치하지 않습니다");
		return false;
	}
	//임시 Return 처리
	return true;
	if (ObjUserPassWord.length < 6) {
		alert("비밀번호는 문자, 숫자, 특수문자의 조합으로 6~16자리로 입력해주세요.");
		return false;
	}
	if (!ObjUserPassWord.match(/[a-zA-Z0-9]*[^a-zA-Z0-9\n]+[a-zA-Z0-9]*$/)) {
		alert("비밀번호는 문자, 숫자, 특수문자의 조합으로 6~16자리로 입력해주세요.");
		return false;
	}

	if (ObjUserPassWord.indexOf(ObjUserID) > -1) {
		alert("비밀번호에 아이디를 사용할 수 없습니다.");
		return false;
	}

	var SamePass_0 = 0; //동일문자 카운트
	var SamePass_1 = 0; //연속성(+) 카운드
	var SamePass_2 = 0; //연속성(-) 카운드

	var chr_pass_0;
	var chr_pass_1;
	var chr_pass_2;

	for (var i = 0; i < ObjUserPassWord.length; i++) {
		chr_pass_0 = ObjUserPassWord.charAt(i);
		chr_pass_1 = ObjUserPassWord.charAt(i + 1);

		//동일문자 카운트
		if (chr_pass_0 == chr_pass_1) {
			SamePass_0 = SamePass_0 + 1
		}

		chr_pass_2 = ObjUserPassWord.charAt(i + 2);
		//연속성(+) 카운드
		if (chr_pass_0.charCodeAt(0) - chr_pass_1.charCodeAt(0) == 1
				&& chr_pass_1.charCodeAt(0) - chr_pass_2.charCodeAt(0) == 1) {
			SamePass_1 = SamePass_1 + 1
		}

		//연속성(-) 카운드
		if (chr_pass_0.charCodeAt(0) - chr_pass_1.charCodeAt(0) == -1
				&& chr_pass_1.charCodeAt(0) - chr_pass_2.charCodeAt(0) == -1) {
			SamePass_2 = SamePass_2 + 1
		}
	}
	if (SamePass_0 > 1) {
		alert("동일문자를 3번 이상 사용할 수 없습니다.");
		return false;
	}

	if (SamePass_1 > 1 || SamePass_2 > 1) {
		alert("연속된 문자열(123 또는 321, abc, cba 등)을\n 3자 이상 사용 할 수 없습니다.");
		return false;
	}
	return true;
}
</script> 
<form name="frmUserInser" id="frmUserInser" onsubmit="return false;" style="padding: 5px; text-align: center; top: 44px; left: 254px;overflow:auto;">
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
				<th>일반회원 아이디<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목" width="15" height="15" /></th>
				<td>
					<div class="form-inline" style="text-align: left;">
                         <div class="input-group input-group-sm">
							<input type="text" size="20" maxlength="20" disabled="disabled" id="userId" name="userId" class="form-control input-sm validationCheck" >
                            <div class="input-group-btn"><a href="#" onclick="javascript:fnIdCheck();" class="btn btn-grey">중복아이디 검색</a></div>
                         </div>
                    </div>
				</td>
			</tr>
			<tr>
				<th>일반회원이름<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<input type="text" size="20" maxlength="60" id="userNm" name="userNm" class="form-control input-sm validationCheck" >
				</td>
			</tr>
			<tr>
				<th>비밀번호<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<input type="password" size="20" maxlength="20" id="pwd" name="pwd" class="form-control input-sm validationCheck" >
				</td>
			</tr>
			<tr>
				<th>비밀번호확인<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<input type="password" size="20" maxlength="20" id="pwd2" name="pwd2" class="form-control input-sm validationCheck" >
				</td>
			</tr>
			<tr>
				<th>부서명<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
			        <div class="form-group form-control-multiple">
						<input type="text" size="20" maxlength="20" disabled="disabled" id="deptNm" name="deptNm" class="form-control input-sm validationCheck" >
						<input type="hidden" size="20" maxlength="20" id="companyCd" name="companyCd" class="form-control input-sm" >
						<input type="hidden" size="20" maxlength="20" id="deptCd" name="deptCd" class="form-control input-sm" >
						<a class="btn btn-grey btn-sm" href="#" onclick="fnDeptInfoSearchlazyTree(); return false;">부서트리</a>
			        </div>
				</td>
			</tr>
			<tr>
				<th>직급<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<select name="jobCd" id="jobCd" title="직급" class="form-control input-sm">
						<c:forEach var="item" items="${level_result}" varStatus="status">
						    <option value="${fn:trim(item.codeDesc)}">${item.codeNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>직책<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<select name="positionCd" id="positionCd" title="직책" class="form-control input-sm">
						<c:forEach var="item" items="${position_result}" varStatus="status">
						    <option value="${fn:trim(item.codeDesc)}">${item.codeNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>직위<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<select name="titleCd" id="titleCd" title="직위" class="form-control input-sm">
						<c:forEach var="item" items="${title_result}" varStatus="status">
						    <option value="${fn:trim(item.codeDesc)}">${item.codeNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>비밀번호힌트<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<select name="pwdHint" id="pwdHint" title="비밀번호힌트" class="form-control input-sm validationCheck">
						<c:forEach var="item" items="${passwordHint_result}" varStatus="status">
						    <option value="${fn:trim(item.codeDesc)}">${item.codeNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>비밀번호정답<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<input type="text" size="50" maxlength="100" id="pwdCnsr" name="pwdCnsr" class="form-control input-sm validationCheck" > 
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
					<input type="text" size="5" maxlength="5" id="telNo" name="telNo" class="form-control input-sm" >
				</td>
			</tr>
			<tr>
				<th>팩스번호</th>
				<td>
					<input type="text" size="20" maxlength="15" id="faxNo" name="faxNo" class="form-control input-sm" >
				</td>
			</tr>
			<tr>
				<th>핸드폰번호</th>
				<td>
					<input type="text" size="20" maxlength="15" id="mobileNo" name="mobileNo" class="form-control input-sm" >
				</td>
			</tr>
			<tr>
				<th>이메일주소<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<input type="text" size="30" maxlength="50" id="email" name="email" class="form-control input-sm validationCheck" >
				</td>
			</tr>
			<tr>
				<th>승인여부<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<select name="userSt" id="userSt" title="승인여부" class="form-control input-sm validationCheck">
						<c:forEach var="item" items="${status_result}" varStatus="status">
						    <option value="${fn:trim(item.codeDesc)}">${item.codeNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>사용여부<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<select name="usageYn" id="usageYn" title="사용여부" class="form-control input-sm validationCheck">
						<c:forEach var="item" items="${usage_result}" varStatus="status">
						    <option value="${fn:trim(item.codeDesc)}">${item.codeNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-center">
		<a href="#" onclick="fnInsert();" class="btn btn-primary"><spring:message code="button.create" /></a>
		<a href="#" class="btn btn-default modal-close" id="insertModalClose"><spring:message code="button.close" /></a>
	</div>
</form>
