<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="et-layout-content" style="text-align: left;">
	<div class="padding-15" id="writeSample">
		<div class="main-title float-left" id="currentMenuNm"></div>
		<div class="clearfix"></div>
		<div class="search-table-wrapper">
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
				<a class="btn btn-grey btn-sm" href="#" onclick="fnUserInfoSearchList(); return false;"><spring:message code="button.search" /></a>
			</div>
			<div class="form-group form-control-multiple no-label">
				<a class="btn btn-default btn-sm" href="#" onclick="fnRoleInfoInsert(); return false;"><spring:message code="button.create" /></a>
				<a class="btn btn-default btn-sm" href="#" onclick="fnRoleInfoRefresh(); return false;" id="roleInfoRefresh" name="roleInfoRefresh"><spring:message code="button.refresh" /></a>
			</div>
		</div>
		<div data-ax5grid="first-grid" class="margin-t-15" data-ax5grid-config="{}" style="height: 300px;"></div>
	</div>
</div>
</div>
<script language="javascript">
var mainModal = new ax5.ui.modal();
var gridView = {
    initView: function () {
    	firstGrid.setConfig({
            target: $('[data-ax5grid="first-grid"]'),
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
                	var paramData = {
                			roleCd: this.self.select(this.dindex).list[this.dindex].roleCd,
                			mode   : "edit"
            		};
                	
                	fnModalOpen("url", "primary", "500", "200", "10000", "롤 정보", "/adm/ath/brd/ViewRoleInfoInsert.do", paramData);
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
    gridView
            .initView()
            .setData();
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
	        firstGrid.setData({
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

function fnUserInfoSearchList(){
	if($("#searchKeyword").val() == ""){
		dialog.alert('<spring:message code="common.search.val" />');
	}else{
		roleInfoListToJson();
	}
}

function fnRoleInfoInsert(){
	var paramData = {
			mode   : "write"
	};
	fnModalOpen("url", "primary", "500", "200", "10000", "롤 등록", "/adm/ath/brd/ViewRoleInfoInsert.do", paramData);
}

function fnMainModalClose(){
	mainModal.close(mainModal);
}

function fnRoleInfoRefresh(){
	location.reload();
}

function fnModalClose(){
	mainModal.close(mainModal);
}
</script>