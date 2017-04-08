/*
 * @author taeyong1.kim@dongkuk.com
 * @since 2016.01.03
 * @description
 * @caution
 * 
 */
var ntree = {
		/*
		 * 현재 선택된 Node의 객체를 추출(배열 형태로 return 함)
		 * 
		 * @param treeMapId
		 * @returns
		 */
		getCurNodeObjs : function(treeMapId) {
			var $treeMap = $(treeMapId);
			var boardIds = $treeMap.jstree("get_selected");
			if (boardIds.length == 0) {
				return [];
			} else if (boardIds.length == 1) {
				return [ $treeMap.jstree().get_node(boardIds[0]) ];
			} else {
				return boardIds.map(function(value) {
					return $treeMap.jstree().get_node(value);
				});
				/*
					
					1. 선택된 N개의 객체에 대해  
					map()함수를 이용하여 각 객체들에서 특정 값을 추출하거나 변환하고,
					이를 배열로 추출하는 get() 함수호출하면 됨. 
					
					ex)  [{value:'a'},{value:'b'},{value:'c'}].map(function(obj){ obj.value+='1'; return obj}) ▶▶▶▶▶  [{value:'a1'},{value:'b1'},{value:'c1'}]

					ex)  ['a','b','c'].map(function(value){ return { value : value+'1' }; }) ▶▶▶▶▶   ['a1','b1','c1']
					
					ex)  $("#input id selecter")).map(function(){ return this.value}).get() ▶▶▶▶▶   ['input의 value값1','input의 value값2','input의 value값3']
					
					2. 배열에 대해 각 loop시
					
					$.each(nodeObjects, function(index, value) { 
						  console.log(index + ': ' + value, this);  //nodeObj.original.bbsType
					});
					
				 */
			}
		},

		/*
		 * 현재 선택된 Node의 id 값을 추출
		 * 
		 * @param treeMapId
		 * @returns
		 */
		getCurNodeIds : function(treeMapId) {
			return $(treeMapId).jstree("get_selected");
		},

		/*
		 * 현재 선택된 하나의 Node의 Depth 값을 추출
		 * 
		 * @param treeMapId
		 * @returns
		 */
		getCurNodeDepth : function(treeMapId) {
			var $treeMap = $(treeMapId);
			var length = $(treeMapId).jstree("get_selected").length;
			if(length==0 || length>1){
				return -1;
			}else{
				return  this.getNodeDepth(treeMapId, $(treeMapId).jstree("get_selected")[0]);
			}
		},

		/*
		 * 특정한 Node의 Depth를 반환
		 * 
		 * @param treeMapId
		 * @param nodeId
		 * @returns
		 */
		getNodeDepth : function(treeMapId, nodeId){
			var nodeObj = this.getNodeObj(treeMapId, nodeId);
			return nodeObj.parents.length;
		},
			
		/*
		 * 특정 Node의 id에 해당하는 객체를 추출
		 * 
		 * @param treeMapId
		 * @returns
		 */
		getNodeObj : function(treeMapId, nodeId) {
			return $(treeMapId).jstree().get_node(nodeId);
		},


		/*
		 * 특정노드의 자식수 return
		 * 
		 * @param treeMapId
		 * @param nodeId
		 * @returns
		 */
		getChildNum : function(treeMapId, nodeId){
			var obj = this.getNodeObj(treeMapId, nodeId);
			return obj.children.length;
		},

		/*
		 * 사용법
		 * 1. 현재 선택된 노드의 Depth 찾기 
		 * ntree.getDepth(LOCAL_BOARD_TREE_ID, ntree.getCurNodeIds(LOCAL_BOARD_TREE_ID)[0], 0,0);
		 * 
		 * @param treeMapId
		 * @param nodeId
		 * @param depth	현재 자식의 depth
		 * @param maxChildDepth  자식들중 max depth
		 */
		getDepth : function(treeMapId, nodeId, depth, maxDepth){
			var obj = this.getNodeObj(treeMapId, nodeId);
			depth++;
			// console.log("depth : ",depth,"maxDepth : ",maxDepth,"obj : ",obj);
			if(depth>maxDepth){
				maxDepth = depth;
			}
			for(var i=0; i<obj.children.length; i++){
				maxDepth = this.getDepth(treeMapId, obj.children[i], depth, maxDepth);
			}
			depth--;
			return maxDepth;	
		},

		/*
		 *  ntree.getChildDepth(LOCAL_BOARD_TREE_ID, "#");
		 *  ntree.getChildDepth(LOCAL_BOARD_TREE_ID, ntree.getCurNodeIds(LOCAL_BOARD_TREE_ID)[0]);
		 *  
		 * @param treeMapId
		 * @param nodeId
		 * @returns {Number}
		 */
		getChildDepth : function(treeMapId, nodeId){
			 return this.getDepth(treeMapId, nodeId, 0, 0)-1;
		},
		/*
		 * 현재 선택된 하나의 Node의 chile depth
		 * 단, 선택하지 않거나, 2개이상을 선택할땐 -1을 return 한다.
		 * 
		 * @param treeMapId
		 * @returns {Number}
		 */
		getCurrentChildDepth : function(treeMapId){
			var length = $(treeMapId).jstree("get_selected").length;
			if(length==0 || length>1){
				return -1;
			}else{
				return  this.getChildDepth(treeMapId, $(treeMapId).jstree("get_selected")[0]);
			}
			//alert("게시판을 하나만 선택하셔야합니다.");
		},

		/*
		 * 특정 Node를 선택하고 싶을때, 선택자를 주어 선택된 상태로 보여줌.
		 * 
		 * $("#empTreeMap").jstree('select_node', "taeyong1.kim");
		 * $("#empTreeMap").jstree('select_node', "ul > li:first");
		 * 
		 * @param treeMapId
		 * @returns
		 */
		setSelectNode : function(treeMapId, nodeId_or_selector) {
			return $(treeMapId).jstree('select_node', nodeId_or_selector); //  ==  $(treeMapId).jstree(true).select_node(nodeId);
		},

		/*
		 * 특정 Node를 선택해제하고 싶을때
		 * 
		 * @param treeMapId
		 * @returns
		 */
		deselectCurrentNode : function(treeMapId) {
			return $(treeMapId).jstree("deselected_node");
		},
		
		deselectAll : function(treeMapId) {
			return $(treeMapId).jstree("deselect_all");
		},
		
		checkAll : function(treeMapId) {
			return $(treeMapId).jstree("check_all");
		},

		/*
		 * 특정 Tree를 파괴하기 ㅋ
		 * 
		 * @param treeMapId
		 * @returns
		 */
		destroyTree : function(treeMapId) {
			return $(treeMapId).jstree('destroy');
		},

		/*
		 * 
		 * Tree의 구성이 변경되었는지 여부 set
		 * 
		 * @param treeMapId
		 * @param isChanged
		 */
		setChanged : function(treeMapId, isChanged, doProcess){
			if(typeof doProcess !="undefined")
				doProcess();
			var $treeMap = $(treeMapId);
			return $treeMap.attr("data-is-changed", isChanged);
		},

		/*
		 * 
		 * Tree의 구성이 변경되었는지 여부 get
		 * 
		 * @param treeMapId
		 */
		getChanged : function(treeMapId){
			return $(treeMapId).attr("data-is-changed") === "true";
		},

		/*
		 * 
		 * 특정 노드의 text 즉 이름을 변경하고자 할때
		 * 
		 * @param treeMapId
		 * @param nodeId
		 */
		setEditMode : function(treeMapId,nodeId){
			return $(treeMapId).jstree(true).edit(nodeId);
		},

		/*
		 * 
		 * 특정 노드의 text 즉 이름을 변경하고자 할때
		 * 
		 * @param treeMapId
		 * @param nodeId
		 * @param text
		 */
		setTextForNode : function(treeMapId, nodeId, text){
			return $(treeMapId).jstree('set_text', nodeId,text);
		},

		/*
		 * 
		 * 특정 노드의 class를 주려고 할때
		 * 
		 * @param treeMapId
		 * @param nodeId
		 * @param text
		 */
		setClassForNode : function(treeMapId, nodeId, addClassName, removeClassName){
			console.log("treeMapId : ",nodeId,", nodeId : ", nodeId, ", addClassName : ",addClassName,", removeClassName : ", removeClassName);
			//var $nodeObj = this.getNodeObj(treeMapId,nodeId);
			//$nodeObj.a_attr = { "class" : addClassName };
			var selector = "#"+nodeId+"_anchor";
			if(utils.isNotEmpty(addClassName)){
				$(selector).addClass(addClassName);	
			}
			if(utils.isNotEmpty(removeClassName)){
				$(selector).removeClass(removeClassName);
			}
		},


		/*
		 * 
		 * tree를 새로고침할 경우
		 * 
		 * @param treeMapId
		 * @param nodeId
		 * @param text
		 */
		refreshTree : function(treeMapId){
			return $(treeMapId).jstree(true).refresh();
			/*
				You can use:
				$("#demo4").jstree(true).refresh_node("node_1"); // this will reload the children of the node
				If you want to update the node itself - refresh its parent:
				$("#demo4").jstree(true).refresh_node($("#demo4").jstree(true).get_node("node_1").parent);
				
			 */
		},

		/*
		 * 현재 Tree의 값을 추출
		 * (단, id, children, text, type, loaded, opened, selected, disabled 정도 밖에 추출안되므로 상세한 객체정보는 this.getNodeObj함수를 이용하여 객체정보를 가져와야)
		 * 
		 * @param treeMapId
		 * @returns
		 * 
		 * 샘플
		 
		 [ {
			"id" : "a03d332c554fbd5bd58cb5b22bc8d761",
			"text" : "새폴더",
			"icon" : "fa fa-folder",
			"li_attr" : {
				"id" : "a03d332c554fbd5bd58cb5b22bc8d761"
			},
			"a_attr" : {
				"href" : "#",
				"class" : "activeBoard",
				"id" : "a03d332c554fbd5bd58cb5b22bc8d761_anchor"
			},
			"state" : {
				"loaded" : true,
				"opened" : true,
				"selected" : false,
				"disabled" : false
			},
			"data" : {},
			"children" : [ {
				"id" : "4e7d776cd94061b20f4f17f134c74df4",
				"text" : "새폴더",
				"icon" : "fa fa-folder",
				"li_attr" : {
					"id" : "4e7d776cd94061b20f4f17f134c74df4"
				},
				"a_attr" : {
					"href" : "#",
					"class" : "inactiveBoard",
					"id" : "4e7d776cd94061b20f4f17f134c74df4_anchor"
				},
				"state" : {
					"loaded" : true,
					"opened" : true,
					"selected" : false,
					"disabled" : false
				},
				"data" : {},
				"children" : [ {
					"id" : "097227c49cb5179d7e5ec17e6753622d",
					"text" : "새 게시판",
					"icon" : "fa fa-sticky-note-o",
					"li_attr" : {
						"id" : "097227c49cb5179d7e5ec17e6753622d"
					},
					"a_attr" : {
						"href" : "#",
						"class" : "inactiveBoard",
						"id" : "097227c49cb5179d7e5ec17e6753622d_anchor"
					},
					"state" : {
						"loaded" : true,
						"opened" : false,
						"selected" : false,
						"disabled" : false
					},
					"data" : {},
					"children" : [],
					"type" : "bbs"
				},],
				"type" : "folder"
			} ],
			"type" : "folder"
		} ]

		 */
		getJsonTree : function(treeMapId) {
			return $(treeMapId).jstree("get_json");
		},
		
		getAllNodes : function(treeMapId) {
			return $(treeMapId).jstree().get_json($(treeMapId), {flat: true});
		},
		
		isOpen : function(treeMapId, nodeId){
			if(utils.isEmpty(nodeId)){
				return $(treeMapId).jstree(true).is_open(this.getCurNodeIds(treeMapId)[0]);
			}else{
				return $(treeMapId).jstree(true).is_open(nodeId); // $(treeMapId).jstree(true).is_open(nodeId);
			}
			/*
			 // event 처리하는 call back function에서...
			var open_status = $(this).jstree("is_open");
			// open_status가 true이면 노드를 닫고, fals이면 node를 연다. 
			if (open_status) {
				$(this).jstree("close_node");
			} else {
				$(this).jstree("open_node");
			}
			//--> $(this)를 사용하여 화살표로 열고 닫기 이외에, 노드를 선택했을때의 열고 닫음도 제어할 수 있다.
			*/
		},
		/*
		 * ntree.openNode("#empTreeMap","223762")
		 * 
		 * @param treeMapId
		 * @param nodeId
		 */
		openNode : function(treeMapId, nodeId){
			try {
				var parents = ntree.getNodeObj(treeMapId,nodeId).parents;
				if(utils.isNotEmpty(parents)){
					for(var i=parents.length-1; i>=0; i--){
						$(treeMapId).jstree(true).open_node(parents[i]);
					}
				}
			} catch (e) {
				console.error(e);
			}
		},
		
		/*
		 * ntree.openNodeDepth("#empTreeMap",3)
		 * 
		 * @param treeMapId
		 * @param nodeId
		 */
		openNodeDepth : function(treeMapId, nodeId, unfoldDepth, currDepth){
			
			try {
				if(unfoldDepth == currDepth){
					return true;
				}else{
					var childIds1 = ntree.getNodeObj(treeMapId,nodeId).children;
					for(var i=0; i<childIds1.length; i++){
						depth++;
						openNodeDepth(treeMapId, nodeId, depth);
						var childIds2 = ntree.getNodeObj("#empTreeMap", childIds1[i]).children;
						$(treeMapId).jstree(true).open_node(childIds[i]);
					}
					
					var parents = ntree.getNodeObj(treeMapId,nodeId).parents;
					if(utils.isNotEmpty(parents)){
						for(var i=parents.length-1; i>=0; i--){
							$(treeMapId).jstree(true).open_node(parents[i]);
						}
					}
				}
			} catch (e) {
				console.error(e);
			}
		},

		/*
		 * ntree.openNodeAll("#empTreeMap")
		 * 
		 * 특정 노드의 text 즉 이름을 변경하고자 할때
		 * 
		 * @param treeMapId
		 */
		openNodeAll : function(treeMapId){
			return $(treeMapId).jstree("open_all");
		},
		
		/*
		 * ntree.closeNode("#empTreeMap","223762")
		 * 
		 * @param treeMapId
		 * @param nodeId
		 */
		closeNode : function(treeMapId, nodeId){
			try {
				$(treeMapId).jstree(true).close_node(nodeId);
			} catch (e) {
				console.error(e);
			}
		},
		/*
		 * ntree.closeNode("#empTreeMap","223762")
		 * 
		 * @param treeMapId
		 * @param nodeId
		 */
		closeNodeAll : function(treeMapId){
			return $(treeMapId).jstree("close_all");
		},
		
		/*
		 * ntree.closeNode("#empTreeMap","223762")
		 * 
		 * @param treeMapId
		 * @param nodeId
		 */
		closeParents : function(treeMapId, nodeId){
			try {
				var parents = ntree.getNodeObj(treeMapId,nodeId).parents;
				if(utils.isNotEmpty(parents)){
					for(var i=parents.length-1; i>=0; i--){
						$(treeMapId).jstree(true).close_node(parents[i]);
					}
				}
			} catch (e) {
				console.error(e);
			}
		},
};


