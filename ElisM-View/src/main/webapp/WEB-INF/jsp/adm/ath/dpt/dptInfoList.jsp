<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="/lib/jquery/jquery.serializejson.min.js"></script>
<style>
.fancytree-plain{
	min-height: 400px !important;
}
</style>
<div class="et-layout-content" style="text-align: left;">
	<div class="padding-15" id="DeptInfoMain" >
		<div class="main-title float-left" id="currentMenuNm"></div>
		<div class="clearfix"></div>
		<div class="search-table-wrapper">
	    <form id="frmDept" name="frmDept">
		    <div class="col-xs-6">
		    	<div id="deptInfolazyTree" name="deptInfolazyTree" style="height: 410px;overflow: hidden;overflow-y: auto;text-align: left" data-source="ajax"></div>
		    </div>
	    	<input type="hidden" id="mode" name="mode">
		    <div class="col-xs-6" style="border: 1px;">
		    	<table class="input-table table table-bordered">
					<colgroup>
						<col style="width:30%" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th>회사명<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
							<td>
								<input type="hidden" size="20" maxlength="60" id="companyCd" name="companyCd" class="form-control input-sm" >
								<input type="text" size="20" maxlength="60" id="companyNm" name="companyNm" class="form-control input-sm" >
							</td>
						</tr>
						<tr>
							<th>부서명<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
							<td>
								<input type="text" size="20" maxlength="60" id="deptNm" name="deptNm" class="form-control input-sm validationCheck" >
							</td>
						</tr>
						<tr>
							<th>부서코드<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
							<td>
								<input type="text" size="20" maxlength="20" id="deptCd" name="deptCd" class="form-control input-sm validationCheck" >
							</td>
						</tr>
						<tr>
							<th>상위부서명<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
							<td>
						        <div class="form-group form-control-multiple">
									<input type="hidden" size="20" maxlength="20" id="upDeptCd" name="upDeptCd" class="form-control input-sm validationCheck" >
									<input type="text" size="20" maxlength="20" id="upDeptNm" name="upDeptNm" class="form-control input-sm" >
									<a class="btn btn-grey btn-sm" href="#" onclick="fnDeptInfoSearchlazyTree(); return false;">부서트리</a>
						        </div>
							</td>
						</tr>
						<tr>
							<th>사용여부<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
							<td>
								<input type="text" size="20" maxlength="20" id="usageYn" name="usageYn" class="form-control input-sm validationCheck" >
							</td>
						</tr>
					</tbody>
				</table>
				
		        <div class="form-group form-control-multiple no-label">
		           <a class="btn btn-default btn-sm" href="#" onclick="fnDeptInfoProcess('I'); return false;" id="menuInsert" name="menuInsert"><spring:message code="button.create" /></a>
		           <a class="btn btn-default btn-sm" href="#" onclick="fnDeptInfoProcess('U'); return false;" id="menuUpdate" name="menuUpdate"><spring:message code="button.update" /></a>
		           <a class="btn btn-default btn-sm" href="#" onclick="fnDeptInfoProcess('D'); return false;" id="menuDelete" name="menuDelete"><spring:message code="button.delete" /></a>
		         </div>
		    </div>
		</form>
		</div>
	</div>
</div>
<script type="text/javaScript" language="javascript" defer="defer">
$(document.body).ready(function () {
	$("#deptInfolazyTree").fancytree({
		source: function(event, data){
			var resultData = "";
			var paramData = {
				companyCd : "UNC",
				deptCd : "ROOT"
			};
			$.ajax({
				url: "/cmn/tre/getDeptInfolazyTreeJson.do",
				data: paramData,
				async: false,
				dataType: "json",
				success : function(datas, textStatus, XMLHttpRequest) {
					resultData = datas.lDeptTree;
				},error : function(xhr, status, error) {
					alert("AJAX 오류");
		        }
			});
			return JSON.parse(resultData);
		},
        checkbox: false,
        activate: function(e, data){
			var paramData = {
	    			companyCd : data.node.data.companyCd,
	    			deptCd : data.node.data.deptCd
	    	};
			$.ajax({
				url: "/adm/ath/dpt/getDeptInfoJson.do",
				data: paramData,
				dataType: "json",
				success : function(datas, textStatus, XMLHttpRequest) {
					$("#companyCd").val(datas.deptInfoDetail[0].companyCd);
					$("#companyNm").val(datas.deptInfoDetail[0].companyNm);
					$("#deptCd").val(datas.deptInfoDetail[0].deptCd);
					$("#deptNm").val(datas.deptInfoDetail[0].deptNm);
					if(datas.deptInfoDetail[0].upDeptCd != datas.deptInfoDetail[0].deptCd){
						$("#upDeptCd").val(datas.deptInfoDetail[0].upDeptCd);
						$("#upDeptNm").val(datas.deptInfoDetail[0].upDeptNm);
					}else{
						$("#upDeptCd").val("");
						$("#upDeptNm").val("");
					}
					$("#usageYn").val(datas.deptInfoDetail[0].usageYn);
				},error : function(xhr, status, error) {
					alert("AJAX 오류");
		        }
			});
        },
		lazyLoad: function(event, data){
			var resultData = "";
			var paramData = {
	    			companyCd : data.node.data.companyCd,
	    			deptCd : data.node.data.deptCd
	    	};
			$.ajax({
				url: "/cmn/tre/getDeptInfolazyTreeJson.do",
				data: paramData,
				async: false,
				dataType: "json",
				success : function(datas, textStatus, XMLHttpRequest) {
					resultData = datas.lDeptTree;
				},error : function(xhr, status, error) {
					alert("AJAX 오류");
		        }
			});
			
			data.result = JSON.parse(resultData);
		}
	});
	$("#currentMenuNm").html(treeNodes[<%=session.getAttribute("currentMenuNum")%>].menuNm);
});


function fnDeptInfoSearchlazyTree(){
	fnModalOpen("url", "primary", "300", "460", "11000", "부서 정보", "/cmn/tre/getDeptInfolazyTree.do?mode=", "");
}

function fnDeptInfoProcess(mode){
	if(mode != "U"){
		if($("#deptCd").val() == "UNC"){
			alert("최상위 메뉴는 추가/삭제 하실 수 없습니다.");
			return false;
		}
	}
	
	validationCheck();
	if(valCheck){
		$("#mode").val(mode);
		var paramData = fn_disabledSerialize($("#frmDept"));
		$.ajax({
			type : "POST",
			url : "/adm/ath/dpt/processDeptInfoJson.do",
			dataType : "json",
			data : paramData,
			success : function(data, textStatus, XMLHttpRequest) {
				if(data.result == "success"){
					alert(data.resultMessage);
				}else{
					alert(data.resultMessage);
				}
				location.reload();
			},error : function(xhr, status, error) {
				alert("AJAX 오류");
	        }
		});
	}
}
</script>
