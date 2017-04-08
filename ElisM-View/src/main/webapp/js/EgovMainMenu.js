/*
 * 노드 , 트리 구성 정보 선언
 */
var treeNodes			= new Array();;
var openTreeNodes	    = new Array();
var treeIcons			= new Array(6);
var treeYeobu       = false;
var chkValue        = "";
var vHtmlCode       = "";

/*
* 트리생성함수
*/
function createTree(arrName, vYeobu, checkValue) {
   var startNode, openNode;
	treeNodes = arrName;
	treeYeobu = vYeobu;
	chkValue = checkValue;
	startNode = chkValue;
	vHtmlCode = "";
	if (treeNodes.length > 0) {
		if (startNode == null) startNode = 0;
		if (openNode != 0 || openNode != null) setOpenTreeNodes(openNode);
		if (startNode !=0) {
			var _getTreeArrayId = getTreeArrayId(startNode)
		} else {
		}
		
		var recursedNodes = new Array();
		addTreeNode(startNode, recursedNodes);
		return vHtmlCode;
	}
}
/*
* 노드위치 확인
*/
function getTreeArrayId(node) {
	for (i=0; i<treeNodes.length; i++) {
		if (treeNodes[i].menuId==node) return i;
	}
	return 0;
}
/*
* 트리 노드 열기
*/
function setOpenTreeNodes(openNode) {
	for (i=0; i<treeNodes.length; i++) {
		if (treeNodes[i].menuId==openNode) {
			openTreeNodes.push(treeNodes[i].menuId);
			setOpenTreeNodes(treeNodes[i].parentMenuId);
		}
	} 
}
/*
* 트리노드 오픈 여부 확인
*/
function isTreeNodeOpen(node) {
   if (treeYeobu){ return true; }
   for (i=0; i<openTreeNodes.length; i++){
	   if (openTreeNodes[i]==node){ return true; }
   }
   return false;
}
/*
* 하위 트리노드 존재여부 확인
*/
function hasChildTreeNode(parentNode) {
	for (i=0; i< treeNodes.length; i++) {
		if (treeNodes[i].parentMenuId == parentNode) return true;
	}
	return false;
}
/*
* 트리노드 최하위 여부 확인
*/
function lastTreeSibling (node, parentNode) {
	var lastChild = 0;
	for (i=0; i< treeNodes.length; i++) {
		if (treeNodes[i].parentMenuId == parentNode)
			lastChild = treeNodes[i].menuId;
	}
	if (lastChild==node) return true;
	return false;
}
/*
* 신규 트리노드 추가
*/
function addTreeNode(parentNode, recursedNodes) {
	for (var i = 0; i < treeNodes.length; i++) {
		if (treeNodes[i].parentMenuId == parentNode) {
			var lastSibling	= lastTreeSibling(treeNodes[i].menuId, treeNodes[i].parentMenuId);
			var hasChildNode	= hasChildTreeNode(treeNodes[i].menuId);
			var isNodeOpen = isTreeNodeOpen(treeNodes[i].menuId);
			vHtmlCodeBg      ="<li><input id='group-"+i+"' type='checkbox' hidden checked/>";
			vHtmlCodeBgList  ="";
			if (lastSibling) recursedNodes.push(0);
			else recursedNodes.push(1);
			if (hasChildNode) {
				if(parentNode == "2000000"){
					vHtmlCode += "<div class=\"tab-pane two-colum active\" id='"+treeNodes[i].menuId+"' role=\"tabpanel\"><nav class=\"nav\" role=\"navigation\"><ul class=\"nav__list\">";
				}else{
					vHtmlCode += "<div class=\"tab-pane three-colum active\" id='"+treeNodes[i].menuId+"' role=\"tabpanel\"><nav class=\"nav\" role=\"navigation\"><ul class=\"nav__list\">";
				}
				vHtmlCode +=vHtmlCodeBg+"<label for='group-"+i+"'><span class='fa fa-angle-right'></span>"+treeNodes[i].menuNm+"</label><ul class='group-list'>";
			} else{
				if(recursedNodes.length==1){
				   vHtmlCode +=vHtmlCodeBg+"<label for='group-"+i+"'><span class='fa fa-angle-right'></span><a href='#'>"+treeNodes[i].menuNm+"</a></label>";
				}else{
				   vHtmlCode +=vHtmlCodeBgList+"<li><a id=\""+treeNodes[i].menuId+"\" href=\"javascript:fn_MovePage('" + i + "');\">"+treeNodes[i].menuNm+"</a></li>";
				}
			}
		
			if (hasChildNode) {
				addTreeNode(treeNodes[i].menuId, recursedNodes);
				vHtmlCode +="</ul></li></ul></nav></div>";
			}
			recursedNodes.pop();
		}
	}
}

if(!Array.prototype.push) {
	function fnArrayPush() {
		for(var i=0;i<arguments.length;i++)
			this[this.length]=arguments[i];
		return this.length;
	}
	Array.prototype.push = fnArrayPush;
}
if(!Array.prototype.pop) {
	function fnArrayPop(){
		lastElement = this[this.length-1];
		this.length = Math.max(this.length-1,0);
		return lastElement;
	}
	Array.prototype.pop = fnArrayPop;
}

