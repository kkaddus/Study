<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet" type="text/css" href="/lib/fancytree/skin-win8/ui.fancytree.css">
<style>
.fancytree-plain{
	min-height: 400px !important;
}
</style>
<script type="text/javascript" src="/lib/jquery/jquery.serializejson.min.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
var treeAllData = "";
$(document.body).ready(function () {
	$("#userlazyTree").fancytree({
		source: function(event, data){
			var resultData = "";
			var paramData = {
				companyCd : "UNC",
				deptCd : "ROOT"
			};
			$.ajax({
				url: "/cmn/tre/getUserInfolazyTreeJson.do",
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
        checkbox: true,
        selectMode: 3,
        activate: function(e, data){
        	//alert(data.node.data);
        	treeAllData = data.node;
        	return;
        	$(data.node).trigger("lazyLoad");
        	data.node.toggleExpanded();
        },
		lazyLoad: function(event, data){
			var resultData = "";
			var paramData = {
	    			companyCd : data.node.data.companyCd,
	    			deptCd : data.node.data.deptCd,
	    			upDeptCd : data.node.data.deptCd
	    	};
			$.ajax({
				url: "/cmn/tre/getUserInfolazyTreeJson.do",
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
		},
		select: function(event, data){
			var selKeys = $.map(data.tree.getSelectedNodes(), function(node){
				return node.key;
			});

			var selRootNodes = data.tree.getSelectedNodes(true);
			var selRootKeys = $.map(selRootNodes, function(node){
				return node.key;
			});
		}
	});
});

function fnConfirm(mode){
	if(treeAllData.data.treeType == "D"){
		alert("부서명 :" + treeAllData.title);
		alert("부서코드 :" + treeAllData.data.deptCd);
		alert("상위부서코드 :" + treeAllData.data.upDeptCd);
	}else if(treeAllData.data.treeType == "S"){
		alert("사용자명 :" + treeAllData.data.userNm);
		alert("사용자ID :" + treeAllData.data.userId);
		alert("소속부서코드 :" + treeAllData.data.deptCd);
	}
	$("#userTreeModalClose").click();
}
</script> 
<form name="frmUserTree" id="frmUserTree" onsubmit="return false;" style="padding: 5px; text-align: center; top: 44px; left: 254px;overflow:auto;">
	<div id="userlazyTree" name="deptlazyTree" style="height: 410px;overflow: hidden;overflow-y: auto;text-align: left" data-source="ajax"></div>
	<div class="text-center">
		<a href="#" onclick="fnConfirm('${mode}');" class="btn btn-primary"><spring:message code="button.confirm" /></a>
		<a href="#" class="btn btn-default modal-close" id="userTreeModalClose"><spring:message code="button.close" /></a>
	</div>
</form>
