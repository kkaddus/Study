<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="/lib/jquery/jquery.serializejson.min.js"></script>
<script type="text/javascript" src="../../../../../lib/navereditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
var oEditors = [];

$(document).ready(function(){
    //use jQuery MultiFile Plugin 
    $('#multiform input[name=uploadFile]').MultiFile({
        max: 3, //업로드 최대 파일 갯수 (지정하지 않으면 무한대)
        //accept: 'jpg|png|gif|ppt', //허용할 확장자(지정하지 않으면 모든 확장자 허용)
        maxfile: 1024, //각 파일 최대 업로드 크기
        maxsize: 3024,  //전체 파일 최대 업로드 크기
        STRING: { //Multi-lingual support : 메시지 수정 가능
            remove : "제거", //추가한 파일 제거 문구, 이미태그를 사용하면 이미지사용가능
            duplicate : "$file 은 이미 선택된 파일입니다.", 
            denied : "$ext 는(은) 업로드 할수 없는 파일확장자입니다.",
            selected:'$file 을 선택했습니다.', 
            toomuch: "업로드할 수 있는 최대크기를 초과하였습니다.($size)", 
            toomany: "업로드할 수 있는 최대 갯수는 $max개 입니다.",
            toobig: "$file 은 크기가 매우 큽니다. (max $size)"
        },
        list:"#afile3-list" //파일목록을 출력할 요소 지정가능
    });
    
    $("#btnSubmit").click(function (){
    	boardSave ();
    });
    
    if("${mode}" == "edit") {
    	$("#title").val(("${resultList.title}"));
    	
    } 
});


//추가 글꼴 목록

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "uonEditor",
	sSkinURI: "../../../lib/navereditor/SmartEditor2Skin.html",	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		//bSkipXssFilter : true,		// client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
		//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			//alert("완료!");
		}
	}, //boolean
	fOnAppLoad : function(){
		if("${mode}" == "edit") {
			oEditors.getById["uonEditor"].exec("PASTE_HTML", ['${resultList.html}']); 
	    } 
	},
	fCreator: "createSEditor2"
});

function pasteHTML(filepath){
 var sHTML = '<img src="' + filepath+'">';

 oEditors.getById["uonEditor"].exec("PASTE_HTML", [sHTML]); 

}

function showHTML() {
	var sHTML = oEditors.getById["uonEditor"].getIR();
	alert(sHTML);
}
	
function submitContents(elClickedObj) {
	oEditors.getById["uonEditor"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("uonEditor").value를 이용해서 처리하면 됩니다.
	
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}

function setDefaultFont() {
	var sDefaultFont = '궁서';
	var nFontSize = 24;
	oEditors.getById["uonEditor"].setDefaultFont(sDefaultFont, nFontSize);
}

function save() {
	alert( document.getElementById('daum').contentWindow.Editor.getContent());
	
	//$("#boardWrite").submit();
}
</script>


<form name="multiform" id="multiform" method="post" enctype="multipart/form-data" style="padding: 5px; text-align: center; top: 44px; left: 254px;overflow:auto;">
	<textarea id="html" name="html" style="height:0px;width:0px;display:none"/>
	<input type="text"  id="mode" name="mode" value="${mode}" style="height:0px;width:0px;display:none">
	<input type="text"  id="boardNo" name="boardNo" value="1" style="height:0px;width:0px;display:none">
	<c:if test="${mode == 'edit'}">
		<input type="text"  id="tUid" name="tUid" value="${tUid}" style="height:0px;width:0px;display:none">
	</c:if>
	<input type="text"  id="parentUid" name="parentUid" value="0" style="height:0px;width:0px;display:none">
	<table class="input-table table table-bordered">
		<colgroup>
			<col style="width:20%" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th>제목<img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목" width="15" height="15" /></th>
				<td>
					<input type="text" size="20" maxlength="60" id="title" name="title" class="form-control input-sm validationCheck" >
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea name="uonEditor" id="uonEditor" rows="10" cols="100" style="width: 766px; height: 412px; display: none;"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="file" class="afile3" name="uploadFile" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="afile3-list" style="border: 2px solid #c9c9c9; min-height: 50px;text-align: left;"></div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-center">
		<c:if test="${mode == 'write'}">
			<input type="button" id="btnSubmit" name="btnSubmit" value="<spring:message code="button.create" />" />
        </c:if>
        <c:if test="${mode == 'edit'}">
			<input type="button" id="btnSubmit" name="btnSubmit" value="<spring:message code="button.update" />" />
			<input type="button" id="btnSubmit" name="btnSubmit" value="<spring:message code="button.delete" />" />
        </c:if>
		
	</div>
</form>
<script>
function boardSave () {
	validationCheck();
	if(valCheck){
		var content = oEditors.getById["uonEditor"].getIR();
			
		$("#html").val(content);
		
		$('#multiform').ajaxForm({
			url : "/adm/ath/brd/boardSave.do",
			enctype: "multipart/form-data", // 여기에 url과 enctype은 꼭 지정해주어야 하는 부분이며 multipart로 지정해주지 않으면 controller로 파일을 보낼 수 없음
			returnType:"json",
			success: function(result){
				alert(result);
			}	
		});
		
		$('#multiform').submit();
	}
}
  
</script>