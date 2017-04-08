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
					<option value="CODE_DESC"  code="button.codeDesc" /></option>
					<option value="CODE_ID" <c:if test="${searchDefaultVO.searchCondition == 'CODE_ID'}">selected="selected"</c:if>><spring:message code="button.codeId" /></option>
					<option value="CODE_NM" <c:if test="${searchDefaultVO.searchCondition == 'CODE_NM'}">selected="selected"</c:if>><spring:message code="button.codeNm" /></option>
					<option value="CREATE_ID" <c:if test="${searchDefaultVO.searchCondition == 'CREATE_ID'}">selected="selected"</c:if>><spring:message code="sts.register" /></option>
				</select>
			</div>
			<div class="form-group form-control-multiple">
				<label for="exampleInputEmail1">검색 키워드</label>
				<input type="text" name="searchKeyword" id="searchKeyword" class="form-control input-sm" value="<c:out value="${searchDefaultVO.searchKeyword}"/>">
				<a class="btn btn-grey btn-sm" href="#" onclick="fnUserInfoSearchList(); return false;"><spring:message code="button.search" /></a>
			</div>
			<div class="form-group form-control-multiple no-label">
				<a class="btn btn-default btn-sm" href="#" onclick="fnUserInfoInsert(); return false;"><spring:message code="button.create" /></a>
				<a class="btn btn-default btn-sm" href="#" onclick="fnUserInfoRefresh(); return false;" id="refresh" name="refresh"><spring:message code="button.refresh" /></a>
			</div>
		</div>
		<div data-ax5grid="first-grid" class="margin-t-15" data-ax5grid-config="{
								showLineNumber: false,
								showRowSelector: true,
								multipleSelect: true,
								lineNumberColumnWidth: 40,
								rowSelectorColumnWidth: 27,
								}" style="height: 300px;">
		</div>
	</div>
</div>
<script language="javascript">
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
                			codeId : this.self.select(this.dindex).list[this.dindex].codeId,
                			codeDesc : this.self.select(this.dindex).list[this.dindex].codeDesc,
            		};
                	fnModalOpen("url", "primary", "360", "160", "10000", "코드 상세", "/adm/ath/cde/getCodeDetail.do", paramData);
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
	         {key: "codeId", label: "<spring:message code='button.codeId' />", align:"center"},
	         {key: "codeDesc", label: "<spring:message code='button.codeDesc' />", align:"center"},
	         {key: "codeNm", label: "<spring:message code='button.codeNm' />", align:"center"},
	         {key: "createId", label: "<spring:message code='sts.register' />", align:"center"},
	         {key: "createDt", label: "<spring:message code='sts.regDate' />", align:"center", type: "date"}
	     	]
        });
        return this;
    },
    setData: function (_pageNo) {
    	codeInfoListToJson(_pageNo);
    }
};

$(document.body).ready(function () {
    gridView
            .initView()
            .setData();
    
    $("#searchKeyword").keydown(function(key) {
		if (key.keyCode == 13) {
			fnUserInfoSearchList();
			return false;
		}
	});
    $("#currentMenuNm").html(treeNodes[<%=session.getAttribute("currentMenuNum")%>].menuNm);
});

function codeInfoListToJson(_pageNo){
	$.ajax({
		type : "POST",
		url : "/adm/ath/cde/getCodeDetailListJson.do",
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
	        for (var i = 0, l = resultList.length; i < l; i++) {
			    list.push({codeId: resultList[i].codeId, codeDesc: resultList[i].codeDesc, codeNm: resultList[i].codeNm, createId: resultList[i].createId, createDt: resultList[i].createDt});
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
		$("#searchKeyword").val($("#searchKeyword").val().toUpperCase());
		codeInfoListToJson();
	}
}

function fnUserInfoInsert(){
	fnModalOpen("url", "primary", "360", "200", "10000", "코드상세등록", "/adm/ath/cde/viewCodeDetailInsert.do", "");
}

function fnUserInfoRefresh(){
	location.reload();
}

</script>