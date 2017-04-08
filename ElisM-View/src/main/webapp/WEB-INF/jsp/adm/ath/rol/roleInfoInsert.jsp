<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javaScript" language="javascript" defer="defer">

$(document).ready(function(){
    $("#btnSubmit").click(function (){
    	boardSave ();
    });
    
    if("${mode}" == "edit") {
    	$("#roleCd").val(("${resultList.roleCd}"));
    	$("#roleCd").attr("disabled", true);
    	$("#roleNm").val(("${resultList.roleNm}"));
    	$("#roleDesc").val(("${resultList.roleDesc}"));
    } 
});


function fnInsert(mode){
	
	if(mode =="write" || mode =="edit") {
		validationCheck();	
	} else if (mode =="delete") {
		$("#mode").val("delete");	
	}  
	
	if(valCheck){
		var paramData = fn_disabledSerialize($("#frmRoleInser"));
		$.ajax({
			type : "POST",
			url : "/adm/ath/rol/insertRoleInfoJson.do",
			dataType : "json",
			data : paramData,
			success : function(data, textStatus, XMLHttpRequest) {
				if(data.result > 0){
					alert(data.resultMessage);
					$("#roleInfoRefresh").click();
					$("#insertModalClose").click();
				}else{
					alert(data.resultMessage);
				}
			},error : function(xhr, status, error) {
				alert("AJAX 오류");
	        }
		});
	}
}

</script>


<form name="frmRoleInser" id="frmRoleInser" method="post" enctype="multipart/form-data" style="padding: 5px; text-align: center; top: 44px; left: 254px;overflow:auto;">
	<!-- 검색조건 유지 -->
    <input type="hidden" id="searchCondition" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>" />
    <input type="hidden" id="searchKeyword" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>" />
    <input type="hidden" id="sbscrbSttus" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>" />
    <input type="hidden" id="pageIndex" name="pageIndex" value="0" />
    <input type="hidden" id="mode" name="mode" value="${mode}" />
	<table class="input-table table table-bordered">
		<colgroup>
			<col style="width:30%" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th>Role Code<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<input type="text" size="20" maxlength="60" id="roleCd" name="roleCd" class="form-control input-sm validationCheck" >
				</td>
			</tr>
			<tr>
				<th>Role 이름<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<input type="text" size="20" maxlength="60" id="roleNm" name="roleNm" class="form-control input-sm validationCheck" >
				</td>
			</tr>
			<tr>
				<th>설명<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
				<td>
					<input type="text" size="20" maxlength="20" id="roleDesc" name="roleDesc" class="form-control input-sm validationCheck" >
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="text-center">
		<c:if test="${mode == 'write'}">
			<a href="#" onclick="fnInsert();" class="btn btn-primary"><spring:message code="button.create" /></a>
		</c:if>
		<c:if test="${mode == 'edit'}">
			<a href="#" onclick="fnInsert();" class="btn btn-primary"><spring:message code="button.update" /></a>
			<a href="#" onclick="fnInsert('delete');" class="btn btn-primary"><spring:message code="button.delete" /></a>
        </c:if>
		<a href="#" class="btn btn-default modal-close" id="insertModalClose"><spring:message code="button.close" /></a>
	</div>
</form>
<script>
function boardSave () {
	validationCheck();
	if(valCheck){
		var content = oEditors.getById["uonEditor"].getIR();
			
		$("#html").val(content);
		
		$('#multiform').ajaxForm({
			url : "/adm/ath/brd/boardSave.do",
			enctype: "multipart/form-data", // 여기에 url과 enctype은 꼭 지정해주어야 하는 부분이며 multipart로 지정해주지 않으면 controller로 파일을 보낼 수 없음
			returnType:"json",
			success: function(result){
				alert(result);
			}	
		});
		
		$('#multiform').submit();
	}
}
  
</script>