<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="/lib/jstree/dist/jstree.min.js"></script>
<link rel="stylesheet" type="text/css" href="/lib/jstree/dist/themes/default/style.min.css" />
<div class="et-layout-content" style="text-align: left;">
	<div class="padding-15" id="MenuInfoMain" >
		<div class="main-title float-left" id="currentMenuNm"></div>
		<div class="clearfix"></div>
		<div class="input-table-wrapper2">
			<div class="form-group form-control-multiple">
				<label for="exampleInputEmail1">검색조건</label>
				<select class="form-control input-sm" name="searchCondition" id="searchCondition">
					<option value="ROLE_CD" <c:if test="${empty searchDefaultVO.searchCondition || searchDefaultVO.searchCondition == 'ROLE_CD'}">selected="selected"</c:if>>코드</option>
					<option value="ROLE_NM" <c:if test="${searchDefaultVO.searchCondition == 'ROLE_NM'}">selected="selected"</c:if>>이름</option>
					<option value="ROLE_DESC" <c:if test="${searchDefaultVO.searchCondition == 'ROLE_DESC'}">selected="selected"</c:if>>설명</option>
				</select>
			</div>
			<div class="form-group form-control-multiple">
				<label for="exampleInputEmail1">검색 키워드</label>
				<input type="text" name="searchKeyword" id="searchKeyword" class="form-control input-sm" value="<c:out value="${searchDefaultVO.searchKeyword}"/>">
				<a class="btn btn-grey btn-sm" href="#" onclick="fnRoleMenuSearchList(); return false;"><spring:message code="button.search" /></a>
			</div>
			<div class="form-group form-control-multiple no-label">
				<a class="btn btn-default btn-sm" href="#" onclick="fnRoleMenuProcess(); return false;" id="userInfoTree" name="userInfoTree">저장</a>
				<a class="btn btn-default btn-sm" href="#" onclick="fnRoleInfoRefresh(); return false;" id="roleInfoRefresh" name="roleInfoRefresh"><spring:message code="button.refresh" /></a>
			</div>
        </div>
		<div class="tab-content">
			<div class="row tab-pane active">
				<div class="col-xs-6 ">
                   <div class="grid-wrap" style="height:300px;">
                   	 <div data-ax5grid="role-grid" class="margin-t-15" data-ax5grid-config="{}" style="height: 500px;"></div>
                   </div>
                 </div>
                 <div class="col-xs-6">
                 	<div id="roleMenuInfoTree" name="roleMenuInfoTree" class="margin-t-15" style="border: 1px; height: 500px; overflow: hidden; overflow-y: auto; text-align: left"></div>
                 </div>
			</div>
	   </div>
	</div>
</div>

<script language="javascript">
var roleGrid = new ax5.ui.grid();

var roleView = {
    initView: function () {
    	roleGrid.setConfig({
            target: $('[data-ax5grid="role-grid"]'),
            frozenColumnIndex: 0,
            frozenRowIndex: 0,
            showLineNumber: true,
            showRowSelector: false,
            multipleSelect: false,
            lineNumberColumnWidth: 40,
            rowSelectorColumnWidth: 28,
            sortable: true, // 모든 컬럼에 정렬 아이콘 표시
            multiSort: false, // 다중 정렬 여부
            remoteSort: false, // remoteSort에 함수를 sortable 컬럼이 클릭되었을때 실행 setColumnSort를 직접 구현. (remoteSort를 사용하면 헤더에 정렬 상태만 표시 하고 데이터 정렬은 처리 안함)
            header: {
                align: "center",
                columnHeight: 28
            },
            body: {
                align: "center",
                columnHeight: 28,
                onClick: function () {
                	this.self.select(this.dindex);
                	var roleCd =  this.self.select(this.dindex).list[this.dindex].roleCd;
                	selRoleCd = roleCd;
                	roleToMenuInfojson(roleCd);
                }
            },
            page: {
                navigationItemCount: 9,
                height: 30,
                display: true,
                firstIcon: '<i class="fa fa-step-backward" aria-hidden="true"></i>',
                prevIcon: '<i class="fa fa-caret-left" aria-hidden="true"></i>',
                nextIcon: '<i class="fa fa-caret-right" aria-hidden="true"></i>',
                lastIcon: '<i class="fa fa-step-forward" aria-hidden="true"></i>',
                onChange: function () {
                    gridView.setData(this.page.selectPage);
                }
            },
	         columns: [
	         {key: "roleCd", label: "코드",  width: 300 , align:"center"},
	         {key: "roleNm", label: "이름", align:"center"},
	         {key: "roleDesc", label: "설명", align:"center"},
	         {key: "createDt", label: "<spring:message code='cop.nttCreateDt' />", align:"center", type: "date"},
	     	]
        });
        return this;
    },
    setData: function (_pageNo) {
    	roleInfoListToJson(_pageNo);
    }
};

$(document.body).ready(function () {
	roleView.initView().setData();
    $("#currentMenuNm").html(treeNodes[<%=session.getAttribute("currentMenuNum")%>].menuNm);
});

function roleInfoListToJson(_pageNo){
	$.ajax({
		type : "POST",
		url : "/adm/ath/brd/getRoleInfoListJson.do",
		dataType : "json",
		data : {"pageIndex" : (_pageNo || 0)+1,
				"pageUnit" : 50,
				"sbscrbSttus" : $("#sbscrbSttus").val(),
				"searchCondition" : $("#searchCondition").val(),
				"searchKeyword" : $("#searchKeyword").val()				
				},
		beforeSend : function() {
		},
		success : function(data) {
	        var list = [];

			var totalCnt = data.paginationInfo.totalRecordCount;
			var resultList = data.resultList;
	        for (var a = 0, l = resultList.length; a < l; a++) {
			    list.push({roleCd: resultList[a].roleCd, roleNm: resultList[a].roleNm, createDt: new Date(resultList[a].createDt).format("yyyy-MM-dd"), roleDesc: resultList[a].roleDesc});
			}
	        roleGrid.setData({
	            list: list,
	            page: {
	                currentPage: _pageNo || 0,
	                pageSize: data.paginationInfo.pageSize,
	                totalElements: 10,
	                totalPages: data.paginationInfo.totalPageCount
	            }
	        });
			
	        return this;
		}
	});	
}

function fnRoleInfoRefresh(){
	location.reload();
}

function roleToMenuInfojson(roleCd){
	var paramData = {
			parentMnId : "ROOT",
			menuId : "",
			roleCd : roleCd
	};
	$.ajax({
		type: "GET",
		url: "/cmn/tre/getRoleMenuInfoTreeJson.do",
		data: paramData,
		dataType: "json",
		success: function(data, textStatus, XMLHttpRequest) {
			TreeSelect(data.lMenuTree);
			selTree = "TRUE";
		},
		error:function (xhr, ajaxOptions, thrownError){
			alert_error(xhr, thrownError,this);
		}
	});
}
var treeDefaultIcon = "fa fa-folder", treeUserIcon = "fa fa-user";
var selTree = "FALSE";
function TreeSelect(resultStr) {
	
	if(selTree == "TRUE"){
		$('#roleMenuInfoTree').jstree("destroy")
	}
	
	$('#roleMenuInfoTree').jstree({
		'plugins': ["checkbox", "types", "search"],
		'core': {
			"expand_selected_onload" : true ,
			"themes" : {
				'theme': "photonui",
				"responsive": false
				//, "icons":false
			},
			"strings" : {
				'Loading ...' : '트리 로딩중......'
			},
			'animation' : false,
            'worker' : false,
			'data': JSON.parse(resultStr)
		},
		"types" : {
			"default" : {
				"icon" : treeDefaultIcon
			},
			"user" : {
				"icon" : treeUserIcon
			}
		},
		get_all_checked: function(obj)
        {
             obj = !obj || obj === -1 ? this.get_container() : this._get_node(obj);
             return obj.find(".jstree-checked, .jstree-undetermined");
        }
	});
}

var selRoleCd = "";
function fnRoleMenuProcess(){
	if(selRoleCd == ""){
		alert("Role Code를 선택하세요");
		return;
	}
	var checked_ids = [], checked_ids1 = [];
	$("#roleMenuInfoTree").find(".jstree-undetermined").each(
	    function(i, element) {
	        var nodeId = $(element).closest('.jstree-node').attr("id");
	        checked_ids.push( nodeId );
	    }
	);
	if(checked_ids.length > 0 || $("#roleMenuInfoTree").jstree('get_selected').length > 0){
		var paramData = {
				roleCd : selRoleCd,
				undetermined : checked_ids,
				getselected : $("#roleMenuInfoTree").jstree('get_selected')
		};
		$.ajax({
			type: "POST",
			url: "/adm/ath/rol/processRoleMenuInfoJson.do",
			data: paramData,
			dataType: "json",
			success: function(data, textStatus, XMLHttpRequest) {
				alert(data.resultMessage);
				fnRoleInfoRefresh();
			},
			error:function (xhr, ajaxOptions, thrownError){
				alert("AJAX Error");
			}
		});
	}else{
		alert("Role에 해당하는 Menu를 선택해 주세요.");
		return;
	}
}


function fnRoleMenuSearchList(){
	if($("#searchKeyword").val() == ""){
		dialog.alert('<spring:message code="common.search.val" />');
	}else{
		roleInfoListToJson();
	}
}

function roleInfoListToJson(_pageNo){
	$.ajax({
		type : "POST",
		url : "/adm/ath/brd/getRoleInfoListJson.do",
		dataType : "json",
		data : {"pageIndex" : (_pageNo || 0)+1,
				"pageUnit" : 50,
				"sbscrbSttus" : $("#sbscrbSttus").val(),
				"searchCondition" : $("#searchCondition").val(),
				"searchKeyword" : $("#searchKeyword").val()				
				},
		beforeSend : function() {
		},
		success : function(data) {
	        var list = [];

			var totalCnt = data.paginationInfo.totalRecordCount;
			var resultList = data.resultList;
	        for (var a = 0, l = resultList.length; a < l; a++) {
			    list.push({roleCd: resultList[a].roleCd, roleNm: resultList[a].roleNm, createDt: new Date(resultList[a].createDt).format("yyyy-MM-dd"), roleDesc: resultList[a].roleDesc});
			}
	        roleGrid.setData({
	            list: list,
	            page: {
	                currentPage: _pageNo || 0,
	                pageSize: data.paginationInfo.pageSize,
	                totalElements: 10,
	                totalPages: data.paginationInfo.totalPageCount
	            }
	        });
			
	        return this;
		}
	});	
}
</script>