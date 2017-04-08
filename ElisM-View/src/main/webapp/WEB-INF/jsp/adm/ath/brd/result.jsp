<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="fileform" name="fileform" method="post">
	<input type="hidden" name="filename" value="${filename}">
	<input type="hidden" name="path" value="${path}">
	<input type="hidden" name="fcode" value="${path}">
</form>
</body>
</html>

<script>
function fileAttach(){ 
	f = document.fileform;
	fpath = f.path.value;
    fname = f.filename.value; 
    
    try{
    	 window.opener.parent.pasteHTML(fpath);
        // window.opener.pasteHTML(fname); 
    	
    	 window.close();
    }catch(e){ 
//         alert(e); 
    }
}
fileAttach();
this.window.close();
</script>