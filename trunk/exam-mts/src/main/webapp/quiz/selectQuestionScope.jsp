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
<title>选择题目</title>
<script language="JavaScript" type="text/JavaScript">
 
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

	var scopes = rightFrame.document.all("questionid");
	var scopenum = scopes.length;
	if(scopenum == 0){
		alert("没有任何题目!");
		return;
	}
	var strValue = ""; 
	var ret = new Array;
	var j = 0;
	for(var i=0;i<scopenum;i++){	
		if(scopes[i].checked==true){
		 	var info = new Array;
			info[0] = scopes[i].value;
			strValue = strValue + "," + scopes[i].value;		
			info[1] = strValue;
			ret[j] = info;
			j=j+1;
		}
	}
	
	window.returnValue = ret;
	window.close();
	
	
} 
</script>
</head>

<body>

 <table bgcolor=#fffff border=0 align="center" width=100% height="100%"> 

    <tr bgcolor="#F0F9FF">
      <td width=35% align="center" rowspan=2>
       <iframe src="<%=request.getContextPath()%>/Quiz.do?method=leftQuestionTree&course=<%=request.getParameter("course") %>" height=100% width=98% frameborder="0" name="leftFrame" marginwidth="0" marginheight="0"></iframe>
	  </td>
	  <td width=65% align="left" height="90%">
       <iframe src="<%=request.getContextPath()%>/Quiz.do?method=listQuestion&course=<%=request.getParameter("course") %>" height=100% width=100% frameborder="0" name="rightFrame" scrolling=yes marginwidth="0" marginheight="0"></iframe>
	  </td>
     </tr>
     <tr bgcolor="#F0F9FF">
       <td width=65% align="center" height="10%">
        <table height="100%" border="0" cellpadding="0" cellspacing="0" width=100%>
          
          <tr>            
            <td  height=15%  background="main_bg_b.jpg"  width="97%" align=center valign="bottom" class="newstitle"><strong><font color="#FFFFFF"> 
            
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
