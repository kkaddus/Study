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
	<div class="padding-15" id="MenuInfoMain" >
		<div class="main-title float-left" id="currentMenuNm"></div>
		<div class="clearfix"></div>
		<div class="input-table-wrapper2">
           <div class="form-group form-control-multiple">
             <a class="btn btn-default btn-sm" href="#" onclick="fnMenuInfoRefresh(); return false;" id="menuInfoRefresh" name="menuInfoRefresh"><spring:message code="button.refresh" /></a>
           </div>
        </div>
		<div class="clearfix"></div>
		<form id="frmMenu" name="frmMenu">
	    	<input type="hidden" id="mode" name="mode">
			<div class="tab-content">
				<div class="row tab-pane active margin-t-15">
					<div class="col-xs-6 ">
	                   <div class="grid-wrap" style="height:300px;">
	                   	 <div id="menuInfolazyTree" name="menuInfolazyTree" style="height: 410px;overflow: hidden;overflow-y: auto;text-align: left" data-source="ajax"></div>
	                   </div>
	                 </div>
	                 <div class="col-xs-6">
	                   <table class="input-table table table-bordered">
							<colgroup>
								<col style="width:30%" />
								<col />
							</colgroup>
							<tbody>
								<tr>
									<th>Menu Id<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
									<td>
										<input type="text" size="20" maxlength="60" id="menuId" name="menuId" class="form-control input-sm validationCheck" >
									</td>
								</tr>
								<tr>
									<th>Menu명<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
									<td>
										<input type="text" size="20" maxlength="60" id="menuNm" name="menuNm" class="form-control input-sm validationCheck" >
									</td>
								</tr>
								<tr>
									<th>상위Menu명<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
									<td>
								        <div class="form-group form-control-multiple">
											<input type="hidden" size="20" maxlength="60" id="parentMenuId" name="parentMenuId" class="form-control input-sm validationCheck" >
											<input type="text" size="20" maxlength="60" id="parentMenuNm" name="parentMenuNm" class="form-control input-sm validationCheck" >
											<a class="btn btn-grey btn-sm" href="#" onclick="fnMenuInfoSearchlazyTree(); return false;">Menu트리</a>
								        </div>
									</td>
								</tr>
								<tr>
									<th>MENU URL<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
									<td>
										<input type="text" size="20" maxlength="256" id="menuUrl" name="menuUrl" class="form-control input-sm validationCheck" >
									</td>
								</tr>
								<tr>
									<th>AUTH 패턴<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
									<td>
										<input type="text" size="20" maxlength="256" id="authPttrn" name="authPttrn" class="form-control input-sm validationCheck" >
									</td>
								</tr>
								<tr>
									<th>MENU 설명<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
									<td>
										<input type="text" size="20" maxlength="512" id="menuDesc" name="menuDesc" class="form-control input-sm validationCheck" >
									</td>
								</tr>
								<tr>
									<th>MENU ORDER<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
									<td>
										<input type="text" size="20" maxlength="10" id="menuOrder" name="menuOrder" class="form-control input-sm validationCheck" >
									</td>
								</tr>
								<tr>
									<th>MENU DEPTH<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
									<td>
										<input type="text" size="20" maxlength="10" id="menuDepth" name="menuDepth" class="form-control input-sm validationCheck" >
									</td>
								</tr>
								<tr>
									<th>사용여부<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목"  /></th>
									<td>
										<select class="form-control input-sm" name="usageYn" id="usageYn">
											<option value="Y">사용</option>
											<option value="N">미사용</option>
										</select> 
									</td>
								</tr>
							</tbody>
						</table>
				        <div class="form-group form-control-multiple no-label">
				           <a class="btn btn-default btn-sm" href="#" onclick="fnMenuInfoProcess('I'); return false;" id="menuInsert" name="menuInsert"><spring:message code="button.create" /></a>
				           <a class="btn btn-default btn-sm" href="#" onclick="fnMenuInfoProcess('U'); return false;" id="menuUpdate" name="menuUpdate"><spring:message code="button.update" /></a>
				           <a class="btn btn-default btn-sm" href="#" onclick="fnMenuInfoProcess('D'); return false;" id="menuDelete" name="menuDelete"><spring:message code="button.delete" /></a>
				         </div>
	                 </div>
				</div>
		   </div>
		</form>
	</div>
</div>

<script type="text/javaScript" language="javascript" defer="defer">
$(document.body).ready(function () {
	menuCreate();
    $("#currentMenuNm").html(treeNodes[<%=session.getAttribute("currentMenuNum")%>].menuNm);
});

function menuCreate(){
	$("#menuInfolazyTree").fancytree({
		source: function(event, data){
			var resultData = "";
			var paramData = {
    			menuId : parseInt("0"),
    			parentMenuId : parseInt("9999999")
			};
			$.ajax({
				url: "/cmn/tre/getMenuInfolazyTreeJson.do",
				data: paramData,
				async: false,
				dataType: "json",
				success : function(datas, textStatus, XMLHttpRequest) {
					resultData = datas.lMenuTree;
				},error : function(xhr, status, error) {
					alert("AJAX 오류");
		        }
			});
			return JSON.parse(resultData);
		},
        checkbox: false,
        activate: function(e, data){
			var paramData = {
	    			menuId : data.node.data.menuId
	    	};
			$.ajax({
				url: "/adm/ath/mnu/getMenuInfoJson.do",
				data: paramData,
				dataType: "json",
				success : function(datas, textStatus, XMLHttpRequest) {
					$("#menuId").val(datas.menuInfoDetail[0].menuId);
					$("#menuNm").val(datas.menuInfoDetail[0].menuNm);
					if(datas.menuInfoDetail[0].parentMenuId != datas.menuInfoDetail[0].menuId){
						$("#parentMenuId").val(datas.menuInfoDetail[0].parentMenuId);
						$("#parentMenuNm").val(datas.menuInfoDetail[0].parentMenuNm);
					}else{
						$("#parentMenuId").val("");
						$("#parentMenuNm").val("");
					}
					$("#authPttrn").val(datas.menuInfoDetail[0].authPttrn);
					$("#menuDesc").val(datas.menuInfoDetail[0].menuDesc);
					$("#menuOrder").val(datas.menuInfoDetail[0].menuOrder);
					$("#menuUrl").val(datas.menuInfoDetail[0].menuUrl);
					$("#menuDepth").val(datas.menuInfoDetail[0].menuDepth);
					$("#usageYn").val(datas.menuInfoDetail[0].usageYn);
				},error : function(xhr, status, error) {
					alert("AJAX 오류");
		        }
			});
        },
		lazyLoad: function(event, data){
			var resultData = "";
			var paramData = {
					menuId : parseInt("9999"),
	    			parentMenuId : parseInt(data.node.data.menuId)
	    	};
			$.ajax({
				url: "/cmn/tre/getMenuInfolazyTreeJson.do",
				data: paramData,
				async: false,
				dataType: "json",
				success : function(datas, textStatus, XMLHttpRequest) {
					resultData = datas.lMenuTree;
				},error : function(xhr, status, error) {
					alert("AJAX 오류");
		        }
			});
			
			data.result = JSON.parse(resultData);
		}
	});
}
function fnMenuInfoSearchlazyTree(){
	fnModalOpen("url", "primary", "300", "460", "11000", "Menu 정보", "/cmn/tre/getMenuInfolazyTree.do?mode=", "");
}

function fnMenuInfoProcess(mode){
	if($("#menuId").val() == "0"){
		alert("최상위 메뉴는 추가/삭제 하실 수 없습니다.");
		return false;
	}
	
	validationCheck();
	if(valCheck){
		$("#mode").val(mode);
		var paramData = fn_disabledSerialize($("#frmMenu"));
		$.ajax({
			type : "POST",
			url : "/adm/ath/mnu/processMenuInfoJson.do",
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

function fnMenuInfoRefresh(){
	location.reload();
}
</script>