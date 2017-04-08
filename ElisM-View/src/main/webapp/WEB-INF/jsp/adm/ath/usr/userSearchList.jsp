<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">
var selIndex = "-1";
var selCheck = "0";

var userSearchGrid = new ax5.ui.grid();

var gridView = {
    initView: function () {
    	userSearchGrid.setConfig({
            target: $('[data-ax5grid="userSearch-grid"]'),
            frozenColumnIndex: 0,
            frozenRowIndex: 0,
            showLineNumber: false,
            showRowSelector: true,
            multipleSelect: true,
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
	         {key: "deptNm", label: "부서명", align:"center" , width:200}
	     	]
        });
        return this;
    },
    setData: function (_pageNo) {
    	userInfoListToJson(_pageNo);
    }
};

$(document.body).ready(function () {
    gridView.initView().setData();
});

function userInfoListToJson(_pageNo){
	$.ajax({
		type : "POST",
		url : "/adm/ath/usr/getUserInfoListJson.do",
		dataType : "json",
		data : {"pageIndex" : (_pageNo || 0)+1,
				"pageUnit" : 50,
				"sbscrbSttus" : $("#sbscrbSttus").val(),
				"searchCondition" : $("#searchUserCondition").val(),
				"searchKeyword" : $("#searchUserKeyword").val()
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
	        userSearchGrid.setData({
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

function fnUserSearchList(){
	if($("#searchUserKeyword").val() == ""){
		dialog.alert('<spring:message code="common.search.val" />');
	}else{
		userInfoListToJson();
	}
}

function fnAddUser() {
	if(userSearchGrid.getList("selected").length ==0 ) {
		alert("삭제할 대상을 선택해주세요.");
		return false;
	}
	
	if("userRole" == "${mode}") {
		var userAddList = userSearchGrid.getList("selected");
		
		var targetIdList = new Array(); 
		for(var i =0; i< userAddList.length; i ++) {
			targetIdList.push(userAddList[i].userId);
		}
		
		$.ajax({
			type : "POST",
			url : "/adm/ath/rol/ViewRoleUserInfoInsert.do",
			dataType : "json",
			data : {"roleCd" : "${roleCd}",
					"targetIdList" : targetIdList,
					"targetType" : "U",
					},
			beforeSend : function() {
			},
			success : function(data) {
				if(data.result >0) {
					alert(data.resultMessage);
					$("#insertModalClose").click();
				} else {
					alert(data.resultMessage);
				}
				
		        return this;
			}
		});	
	}
}


</script>
	<div class=" text-left" style="padding: 10px; ">
      <div class="search-table-wrapper">
        <div class="form-group form-control-multiple">
          <label for="searchUserCondition">검색조건</label>
          <select class="form-control input-sm" name="searchUserCondition" id="searchUserCondition">
			<option value="USER_NM" <c:if test="${empty searchUserVO.searchCondition || searchUserVO.searchCondition == 'USER_NM'}">selected="selected"</c:if>><spring:message code="cop.ncrdNm" /></option>
			<option value="USER_ID" <c:if test="${searchUserVO.searchCondition == 'USER_ID'}">selected="selected"</c:if>><spring:message code="button.id" /></option>
			<option value="EMAIL" <c:if test="${searchUserVO.searchCondition == 'EMAIL'}">selected="selected"</c:if>><spring:message code="cop.emailAdres" /></option>
		  </select> 
        </div>
        <div class="form-group form-control-multiple">
          <label for="searchUserKeyword">검색 키워드</label>
          <input type="text" name="searchUserKeyword" id="searchUserKeyword"  class="form-control input-sm" value="<c:out value="${searchUserVO.searchKeyword}"/>">
          <a class="btn btn-grey btn-sm" href="#" onclick="fnUserSearchList(); return false;"><spring:message code="button.search" /></a>
        </div>
      </div>
      <div data-ax5grid="userSearch-grid" class="margin-t-15 header-rowselsect-disabled" data-ax5grid-config="{}" style="height: 300px;"></div>
      <br>
      <div class="text-center">
			<a href="#" onclick="fnAddUser();" class="btn btn-primary">추가</a>
			<a href="#" class="btn btn-default modal-close" id="insertModalClose"><spring:message code="button.close" /></a>
		</div>
    </div>
