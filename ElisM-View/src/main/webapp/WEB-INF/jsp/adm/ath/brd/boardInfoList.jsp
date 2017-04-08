<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="et-layout-content" style="text-align: left;">
	<div class="padding-15" id="writeSample">
	<div class="main-title float-left" id="currentMenuNm"></div>
	<div class="clearfix"></div>
	<div class="search-table-wrapper">
		<div class="form-group form-control-multiple">
			<label for="exampleInputEmail1">검색조건</label>
			<select class="form-control input-sm" name="searchCondition" id="searchCondition">
				<option value="MBER_NM" <c:if test="${empty searchDefaultVO.searchCondition || searchDefaultVO.searchCondition == 'MBER_NM'}">selected="selected"</c:if>><spring:message code="cop.ncrdNm" /></option>
				<option value="MBER_ID" <c:if test="${searchDefaultVO.searchCondition == 'MBER_ID'}">selected="selected"</c:if>><spring:message code="button.id" /></option>
				<option value="MBER_EMAIL_ADRES" <c:if test="${searchDefaultVO.searchCondition == 'MBER_EMAIL_ADRES'}">selected="selected"</c:if>><spring:message code="cop.emailAdres" /></option>
			</select> 
		</div>
		<div class="form-group form-control-multiple">
			<label for="exampleInputEmail1">검색 키워드</label>
			<input type="text" name="searchKeyword" id="searchKeyword"  class="form-control input-sm" value="<c:out value="${searchDefaultVO.searchKeyword}"/>">
			<a class="btn btn-grey btn-sm" href="#" onclick="fnUserInfoSearchList(); return false;"><spring:message code="button.search" /></a>
		</div>
		<div class="form-group form-control-multiple no-label">
			<a class="btn btn-default btn-sm" href="#" onclick="fnBoardInfoInsert(); return false;"><spring:message code="button.create" /></a>
		</div>
	</div>
	<div data-ax5grid="first-grid" class="margin-t-15" data-ax5grid-config="{}" style="height: 300px;"></div>
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
                			tUid : this.self.select(this.dindex).list[this.dindex].tUid,
                			mode   : "edit"
            		};
                	
                	fnModalOpen("url", "primary", "800", "800", "10000", "게시물 등록", "/adm/ath/brd/ViewBoardInfoInsert.do", paramData);
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
	         {key: "title", label: "<spring:message code='cop.nttSj' />",  width: 300 , align:"center"},
	         {key: "createId", label: "<spring:message code='cop.ntcrNm' />", align:"center"},
	         {key: "tUid", label: "<spring:message code='cop.ntcrNm' />",  width: 0, align:"center"},
	         {key: "createDt", label: "<spring:message code='cop.nttCreateDt' />", align:"center", type: "date"},
	     	]
        });
        return this;
    },
    setData: function (_pageNo) {
    	boardInfoListToJson(_pageNo);
    }
};

$(document.body).ready(function () {
    gridView
            .initView()
            .setData();
    $("#currentMenuNm").html(treeNodes[<%=session.getAttribute("currentMenuNum")%>].menuNm);
});

function boardInfoListToJson(_pageNo){
	$.ajax({
		type : "POST",
		url : "/adm/ath/brd/getBoardInfoListJson.do",
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
			    list.push({title: resultList[a].title, createId: resultList[a].createId, createDt: resultList[a].createDt, html: resultList[a].html, tUid: resultList[a].tUid});
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
		userInfoListToJson();
	}
}

function fnBoardInfoInsert(){
	var paramData = {
			mode   : "write"
	};
	fnModalOpen("url", "primary", "800", "800", "10000", "게시물 등록", "/adm/ath/brd/ViewBoardInfoInsert.do", paramData);
}

function fnMainModalClose(){
	mainModal.close(mainModal);
}


function fnModalClose(){
	alert("asdasd3");
	mainModal.close(mainModal);
}
</script>