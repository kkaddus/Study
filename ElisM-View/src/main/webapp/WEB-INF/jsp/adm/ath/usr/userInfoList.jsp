<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="et-layout-content" style="text-align: left;">
	<div class="padding-15" id="divUserInfoList">
		<div class="main-title float-left" id="currentMenuNm"></div>
		<div class="clearfix"></div>
		<div class="search-table-wrapper">
			<div class="form-group form-control-multiple">
				<label for="sbscrbSttus">회원유형</label>
				<select name="sbscrbSttus" id="sbscrbSttus" class="form-control input-sm" onchange="javascript:userInfoListToJson();">
					<option value="T" <c:if test="${empty searchUserVO.sbscrbSttus || searchUserVO.sbscrbSttus == 'T'}">selected="selected"</c:if>><spring:message code="sts.status" /></option>
					<c:forEach var="item" items="${entrprsMberSttus_result}" varStatus="status">
						<c:choose>
							<c:when test="${fn:trim(item.codeDesc) == searchUserVO.sbscrbSttus}">
								<option value="${fn:trim(item.codeDesc)}" selected>${item.codeNm}</option>
							</c:when>
							<c:otherwise>
								<option value="${fn:trim(item.codeDesc)}">${item.codeNm}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="form-group form-control-multiple">
				<label for="searchCondition">검색조건</label>
				<select class="form-control input-sm" name="searchCondition"id="searchCondition">
					<option value="USER_NM" <c:if test="${empty searchUserVO.searchCondition || searchUserVO.searchCondition == 'USER_NM'}">selected="selected"</c:if>><spring:message code="cop.ncrdNm" /></option>
					<option value="USER_ID" <c:if test="${searchUserVO.searchCondition == 'USER_ID'}">selected="selected"</c:if>><spring:message code="button.id" /></option>
					<option value="EMAIL" <c:if test="${searchUserVO.searchCondition == 'EMAIL'}">selected="selected"</c:if>><spring:message code="cop.emailAdres" /></option>
				</select>
			</div>
			<div class="form-group form-control-multiple">
				<label for="searchKeyword">검색 키워드</label>
				<input type="text" name="searchKeyword" id="searchKeyword" class="form-control input-sm" value="<c:out value="${searchUserVO.searchKeyword}"/>"> 
				<a class="btn btn-grey btn-sm" href="#" onclick="fnUserInfoSearchList(); return false;"><spring:message code="button.search" /></a>
			</div>
			<div class="form-group form-control-multiple no-label">
				<a class="btn btn-default btn-sm" href="#" onclick="fnUserInfoInsert(); return false;"><spring:message code="button.create" /></a>
				<a class="btn btn-default btn-sm" href="#" onclick="fnUserInfoRefresh(); return false;" id="userInfoRefresh" name="userInfoRefresh"><spring:message code="button.refresh" /></a>
				<a class="btn btn-default btn-sm" href="#" onclick="fnUserInfoSearchlazyTree(); return false;" id="userInfoTree" name="userInfoTree">사용자트리</a>
			</div>
		</div>
		<div data-ax5grid="userInfo-grid" class="margin-t-15 header-rowselsect-disabled" data-ax5grid-config="{}" style="height: 300px;"></div>
	</div>
</div>
<script language="javascript">
var selIndex = "-1";
var selCheck = "0";
var gridView = {
    initView: function () {
    	firstGrid.setConfig({
            target: $('[data-ax5grid="userInfo-grid"]'),
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
                onClick: function (){
                	this.self.select(this.dindex);
                	var paramData = {
                			userId : this.self.select(this.dindex).list[this.dindex].userId
            		};
                	fnModalOpen("url", "primary", "400", "420", "10000", "사용자 상세", "/adm/ath/usr/getUserInfo.do", paramData);
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
	         {key: "userId", label: "<spring:message code='button.id' />", align:"center"},
	         {key: "userNm", label: "<spring:message code='cop.ncrdNm' />", align:"center"},
	         {key: "jobNm", label: "직위", align:"center"},
	         {key: "deptNm", label: "부서명", align:"center"},
	         {key: "email", label: "<spring:message code='cop.emailAdres' />", align:"center", width:"15%"},
	         {key: "mobileNo", label: "<spring:message code='cop.mbtlNum' />", align:"center", width:"10%"},
	         {key: "userSt", label: "<spring:message code='sts.status' />", align:"center"},
	         {key: "usageYn", label: "사용여부", align:"center"},
	         {key: "createDt", label: "<spring:message code='sts.regDate' />", align:"center", type: "date"},
	         {key: "updateDt", label: "수정일시", align:"center", type: "date"}
	     	]
        });
        return this;
    },
    setData: function (_pageNo) {
    	userInfoListToJson(_pageNo);
    }
};

$(document.body).ready(function () {
    gridView
            .initView()
            .setData();
    
    $("#currentMenuNm").html(treeNodes[<%=session.getAttribute("currentMenuNum")%>].menuNm);
});

function userInfoListToJson(_pageNo){
	$.ajax({
		type : "POST",
		url : "/adm/ath/usr/getUserInfoListJson.do",
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
			    list.push({
			    	userId: resultList[a].userid, 
			    	userNm: resultList[a].usernm,
			    	jobNm: resultList[a].jobnm,
			    	deptNm: resultList[a].deptnm,
			    	email: resultList[a].email,
			    	mobileNo: (resultList[a].mobileno || "-"), 
			    	userSt: resultList[a].userstnm,
			    	usageYn: resultList[a].usageynnm,
			    	createDt: resultList[a].createdt,
			    	updateDt: resultList[a].updatedt
			    });
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

function fnUserInfoInsert(){
	fnModalOpen("url", "primary", "400", "420", "10000", "사용자 등록", "/adm/ath/usr/viewUserInfoInsert.do", "");
}

function fnUserInfoRefresh(){
	location.reload();
}

function fnUserInfoSearchlazyTree(){
	fnModalOpen("url", "primary", "300", "460", "11000", "사용자 정보", "/cmn/tre/getUserInfolazyTree.do?mode=D", "");
}

</script>