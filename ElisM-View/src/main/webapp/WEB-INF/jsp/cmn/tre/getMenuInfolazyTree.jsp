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
	$("#menulazyTree").fancytree({
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
        	treeSelData = data.node;
        	return;
        	$(data.node).trigger("lazyLoad");
        	data.node.toggleExpanded();
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
});

function fnConfirm(mode){
	if(mode == "U"){
		$("#parentMenuId", parent.document).val(treeSelData.data.parentMenuId);
		$("#parentMenuNm", parent.document).val(treeSelData.data.parentMenuNm);
	}else{
		$("#parentMenuId", parent.document).val(treeSelData.data.menuId);
		$("#parentMenuNm", parent.document).val(treeSelData.title);
	}
	$("#menuTreeModalClose").click();
}
</script> 
<form name="frmMenuTree" id="frmMenuTree" onsubmit="return false;" style="padding: 5px; text-align: center; top: 44px; left: 254px;overflow:auto;">
	<div id="menulazyTree" name="menulazyTree" style="height: 410px;overflow: hidden;overflow-y: auto;text-align: left" data-source="ajax"></div>
	<div class="text-center">
		<a href="#" onclick="fnConfirm('${mode}');" class="btn btn-primary"><spring:message code="button.confirm" /></a>
		<a href="#" class="btn btn-default modal-close" id="menuTreeModalClose"><spring:message code="button.close" /></a>
	</div>
</form>
