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
var treeSelData = "";
$(document.body).ready(function () {
	$("#deptlazyTree").fancytree({
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
        	treeSelData = data.node;
        	return;
        	$(data.node).trigger("lazyLoad");
        	data.node.toggleExpanded();
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
});

function fnConfirm(mode){debugger;
	if(mode == "U"){
		$("#companyCd", parent.document).val(treeSelData.data.companyCd);
		$("#upDeptCd", parent.document).val(treeSelData.data.upDeptCd);
		$("#upDeptNm", parent.document).val(treeSelData.data.upDeptNm);
	}else{
		$("#companyCd", parent.document).val(treeSelData.data.companyCd);
		$("#upDeptCd", parent.document).val(treeSelData.data.deptCd);
		$("#upDeptNm", parent.document).val(treeSelData.title);
	}
	$("#deptTreeModalClose").click();
}
</script> 
<form name="frmDeptTree" id="frmDeptTree" onsubmit="return false;" style="padding: 5px; text-align: center; top: 44px; left: 254px;overflow:auto;">
	<div id="deptlazyTree" name="deptlazyTree" style="height: 410px;overflow: hidden;overflow-y: auto;text-align: left" data-source="ajax"></div>
	<div class="text-center">
		<a href="#" onclick="fnConfirm('${mode}');" class="btn btn-primary"><spring:message code="button.confirm" /></a>
		<a href="#" class="btn btn-default modal-close" id="deptTreeModalClose"><spring:message code="button.close" /></a>
	</div>
</form>
