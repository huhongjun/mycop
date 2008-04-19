<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<% 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragrma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
<head>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0"> 
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>选择学生</title>
<script language="JavaScript" type="text/JavaScript">
function addToSelect(){ 
	var selectScopes = document.all("selectScopes");
	var str = "";
	
	for(var k = 0; k < selectScopes.options.length; k ++){
		if(k==0){
			str = selectScopes.options[k].value;
		}
		else{
			str = str + selectScopes.options[k].value + ",";
		}
	}
	var scopes = rightFrame.document.all("scope");
	var scopenum = scopes.options.length;
	var selectIndexs = scopes.selectedIndex;
	for(var j = 0; j < scopenum; j ++){
		var scope = scopes.options[j];
		if(scope.selected == true){
			if(str.indexOf("," + scope.value + ",") == -1){ 
				var scopeHtml = scope.innerHTML;
				selectScopes.add(new Option(scopeHtml , scope.value));
			}
		
		}    
	}
}
 
function delSelect2(){
	var selectScopes = document.all("selectScopes");
	for(var i = 0; i < selectScopes.options.length; i ++){
		if(i >= 0 && i <= selectScopes.options.length-1 && selectScopes.options[i].selected){     
			selectScopes.options[i] = null;
			i --;
		}  
	}
}
function delSelect(){ 
	var arrValue = new Array();
	var arrText  = new Array();
	var selectScopes = document.all("selectScopes");
//selectScopes.remove(selectScopes.selectIndex);
	var i = 0;
	for(var k = 0; k < selectScopes.options.length; k ++){
		var scope = selectScopes.options[k];
		if(scope.selected == false){
			arrValue[i] = scope.value;
			arrText[i]  = scope.text;
			i++;
		} 
	} 
	for(var j = 0;j < arrValue.length; j ++){  
		var oOption = document.createElement("option");
		oOption.text = arrText[j];
		oOption.value = arrValue[j];
		selectScopes.add(oOption);
	} 
}

function goNext(){
	var selectScopes = document.all("selectScopes");
	var num = selectScopes.options.length;
	if(num == 0){
		alert("没有任何选项!");
		return;
	}
	var strValue = "";
	var ret = new Array;
	for(var i = 0; i < num; i ++){
		var info = new Array;
		var scope = selectScopes.options[i];
		info[0] = scope.value;
		strValue = strValue + "," + scope.value;
		
		info[1] = scope.text;
		ret[i] = info;
	}
	
	window.returnValue = ret;
	window.close();
} 
</script>
</head>

<body>

 <table bgcolor=#fffff border=0 align="center" width=100% height="100%"> 

    <tr bgcolor="#F0F9FF">
      <td width=50% align="center" rowspan=2>
       <iframe src="<%=request.getContextPath()%>/Quiz.do?method=leftForQuiz" height=100% width=98% frameborder="0" name="leftFrame" marginwidth="0" marginheight="0"></iframe>
	  </td>
	  <td width=50% align="left" height="40%">
       <iframe src="<%=request.getContextPath()%>/Quiz.do?method=selectUserScopeForQuiz&course=<%=request.getParameter("course") %>" height=100% width=100% frameborder="0" name="rightFrame" scrolling=no marginwidth="0" marginheight="0"></iframe>
	  </td>
     </tr>
     <tr bgcolor="#F0F9FF">
       <td width=65% align="center" height="60%">
        <table height="100%" border="0" cellpadding="0" cellspacing="0" width=100%>
          <td height=10% width="100%"  background="main_bg_b.jpg" align=center class="newstitle">
             <a href="#" onclick="addToSelect()"><img src="<%=request.getContextPath() %>/image/plan-s1.jpg" width="45" height="21" class="admin_pic_left" border="0"/></a>
          </td>
          <TR>
           <TD height=75%>
           <select name="selectScopes" id="selectScopes" size="9" class="small" style="width:100%;height=100%" multiple></select>
           </td>
          </tr>
          <tr>            
            <td  height=15%  background="main_bg_b.jpg"  width="97%" align=center valign="bottom" class="newstitle"><strong><font color="#FFFFFF"> 
            <a href="#" onclick="delSelect2()"><img src="<%=request.getContextPath() %>/image/plan-s2.jpg" width="45" height="21" class="admin_pic_left" border="0"/></a>
			<a href="#" onclick="goNext()"><img src="<%=request.getContextPath() %>/image/queding.jpg" width="45" height="21" class="admin_pic_left" border="0"/></a>
			<a href="#" onclick="window.close()"><img src="<%=request.getContextPath() %>/image/close.jpg" width="45" height="21" class="admin_pic_left" border="0"/></a>            
              </font></strong></td>
              
              
          </tr>
          
        </table>
      
	</td>
    </tr>
  </table>
</body>
</html>
