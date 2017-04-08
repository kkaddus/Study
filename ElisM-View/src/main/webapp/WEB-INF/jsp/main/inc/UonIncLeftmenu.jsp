<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.dkunc.adm.ath.mnu.vo.MenuInfoVO" %>
<%@ page import="com.dkunc.cmn.lgn.vo.UserInfoVO" %>
<%@ page import="java.util.List" %>
<script type="text/javascript" src="<c:url value="/js/EgovMainMenu.js"/>"></script>
<%
	UserInfoVO userInfoVO = (UserInfoVO) session.getAttribute("UserInfoVO");
	
	List<MenuInfoVO> menuList = userInfoVO.getMenuList();	
	
%>
<div class="sidebar" id="sidebarMode">
	<div class="sidebar-close-area" id="sidebarCloseArea"></div>
	<div class="sidebar-layout">
		<div class="sidebar-layout-header" style="text-align: left;">
			<a class="header-element" id="sidebarCloseBtn"><i class="fa fa-close"></i></a>
			<div class="header-element"></div>
		</div>
		<div class="sidebar-layout-content" style="text-align: left;">
			<div class="sidebar-layout-sub-header btn-group btn-group-justified" id="sidebarSubTabBtn" style="text-align: left;" role="tablist">
				<c:forEach var="topMenuList" items="<%=menuList%>">
					<c:if test="${topMenuList.parentMenuId == 0}" >
						<a class="btn btn-default" href="#${topMenuList.menuId}" id="${topMenuList.menuId}" aria-controls="${topMenuList.menuId}" role="tab" data-toggle="tab" onclick="javascript:goTabSubMenu(${topMenuList.menuId});"> ${topMenuList.menuNm} </a>
					</c:if>
				</c:forEach>
			</div>
			<div class="padding-5 tab-content" id="sidebarSubmenuTab">
			</div>
		</div>
	</div>
</div>
<form name="menuListForm" id="menuListForm" action ="" method="post">
    <input type="hidden" id="currentTabId" name="currentTabId" value="<%=session.getAttribute("currentTabId")%>" />
    <input type="hidden" id="currentMenuNum" name="currentMenuNum" value="<%=session.getAttribute("currentMenuNum")%>" />
    <input type="hidden" id="currentMenuId" name="currentMenuId" value="<%=session.getAttribute("currentMenuId")%>" />
    <input type="hidden" id="link" name="link" value="" />
    <div style="width:0px; height:0px;">
    </div>
</form>
<script>
    $('#sidebarOpenBtn').on('click',function(){ // Sidebar 오픈 이벤트
      $('#sidebarMode').addClass('easing');
      setTimeout(function(){
          $('#sidebarMode').addClass('active');
      },10)

    })
    var closeSidebar = function(){
      $('#sidebarMode').removeClass('active');
      setTimeout(function(){
          $('#sidebarMode').removeClass('easing');
      },100);
    };
    $('#sidebarMode').on('click','#sidebarCloseBtn', function(){  // Sidebar 닫기 이벤트
      closeSidebar();
    });
    $('#sidebarMode').on('click','#sidebarCloseArea', function(){  // Sidebar 닫기 이벤트
      closeSidebar();
    });

    $('#sidebarSubTabBtn').on('click','a',function(){ // 상단 탭 버튼 이벤트(커스터마이즈 버전)
      $('#sidebarSubTabBtn').find('.btn').removeClass('active');
      $(this).addClass('active');
    });

	var Tree = new Array;
	var menuList = '${myMenuList}';
	var sidebarsubmenuHtml = "";
	var tabCheckVal = "";
    $(document.body).ready(function () {
    	for(var i=0; i<$('#sidebarSubTabBtn a').length; i++){
    		if($('#sidebarSubTabBtn a')[i].id == document.getElementById("currentTabId").value){
    			$($('#sidebarSubTabBtn a')[i]).addClass('active');
    	        $("#sidebarSubmenuTab").html(createTree(JSON.parse(menuList), true, $('#sidebarSubTabBtn a')[i].id));
    		}else{
    			if(document.getElementById("currentTabId").value == 'null'){
        			$('#sidebarSubTabBtn a:first-child').addClass('active');
        	        $("#sidebarSubmenuTab").html(createTree(JSON.parse(menuList), true, $('#sidebarSubTabBtn a')[0].id));
        	        document.getElementById("currentTabId").value =  $('#sidebarSubTabBtn a')[0].id;
    			}
    		}
    	}
    	
    	if(document.getElementById("currentMenuId").value != 'null'){
    		$("#"+document.getElementById("currentMenuId").value).parent().addClass('active');
    	}
    });
		
	function goTabSubMenu(checkValue){
		document.getElementById("currentTabId").value = checkValue;
		var div = document.getElementById('sidebarSubmenuTab');
		while(div.firstChild){
		    div.removeChild(div.firstChild);
		}

		sidebarsubmenuHtml = createTree(JSON.parse(menuList), true, checkValue);
		$("#sidebarSubmenuTab").html(sidebarsubmenuHtml);
	}
</script>

<script type="text/javascript">
/* ********************************************************
 * 상세내역조회 함수
 ******************************************************** */
function fn_MovePage(nodeNum) {
	document.menuListForm.currentMenuNum.value = nodeNum;
	document.menuListForm.currentMenuId.value = treeNodes[nodeNum].menuId;
    document.menuListForm.action = treeNodes[nodeNum].menuUrl;
    document.menuListForm.submit();
}
</script>