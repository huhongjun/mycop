<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>人员选择</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js">
</script>

</head>

<body class="bodybg" style="overflow-x:hidden;overflow-y:hidden;border: none" onload="reSetSelect();">



<html:form  action="/userAction">
<script>
 var rid="<%=request.getParameter("rid")%>";
 
</script>

<input name="rid" type="hidden"  value="<%=request.getParameter("rid")%>">

<table align="center">
  <tr>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td ><table border="0" cellpadding="2" cellspacing="1">
	<tr class="InputFrameLine">
          <td class='inputlabelcell' nowrap>按行政级别:</td>
          <td class=inputareacell align=center><html:select property="level"  style="width:150px;" onchange="searchInLevel()">
	                 <html:options collection="options" property="value" labelProperty="label"/>
					  </html:select> </td>
    </tr>
	
      <table border="0" cellpadding="2" cellspacing="1">
          <tr class="InputLabelCell">
                <td>按名称（帐号）搜索:</td>
                <td valign="middle">
                <INPUT type="text" id="search" name="search" style="width:80;" class="input2" onkeyup="javascript:query();">
		<!--按照帐号:<INPUT type="checkbox" name="namecode" onclick="javascript:setMethod(this);">
                </td>-->
              </tr>
        <tr>
          <td colspan="2"><table border="0" cellpadding="2" cellspacing="1">
              <tr class="InputLabelCell">
                <td>可选用户列表:</td>
                <td valign="middle">&nbsp;</td>
                <td>已选用户列表:</td>
              </tr>  

		 <tr>
                <td align="center" valign="top">
                	<select id="addLeftListBox" name="addLeftListBox" multiple class="input2" style="width:120px; height:200px;"
				ondblclick="moveOneFrom('left')" >
                  	</select></td>
                <td align="center" valign="middle"> <table border="0" cellpadding="1" cellspacing="1">
                    <tr>
                      <td align="center"> <input type="button" class="button" style="width:30px;" value="&gt;" onclick="moveOneFrom('left')"></td>
                    </tr>
                    <tr>
                      <td align="center"> <input name="Submit232" type="button" class="button" style="width:30px;" value="&gt&gt;" onclick="moveAllFrom('left')"></td>
                    </tr>
				<tr align="center">
                      <td valign="middle"> </td>
                    </tr>
					<tr align="center">
                      <td valign="middle"> </td>
                    </tr>
                    <tr>
                      <td align="center"> <input name="Submit2232" type="button" class="button" style="width:30px;" value="&lt&lt;" onclick="moveAllFrom('right')"></td>
                    </tr>
                    <tr>
                      <td align="center"> <input type="button" class="button" style="width:30px;" value="&lt;" onclick="moveOneFrom('right')"></td>
                    </tr>
                  </table></td>
				  <td align="center" valign="top">
			<select id="addRightListBox" name="addRightListBox" multiple class="input2" style="width:120px; height:200px;" ondblclick="moveOneFrom('right')">
                  	</select></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
	  </tr>

	<td align="center" >
      <html:submit  styleClass="button"  property="create" value="确定" onclick="toSelectAction(this,'addRightListBox','userId','saveRoleToUser')"/>
<script>
function toSelectAction(obj,sourceList,hid,actions){
     var sourceObj = document.all(sourceList);
     var subtxt = document.all(hid);
     subtxt.value='';
    actualLength = sourceObj.options.length;
    for (idx =0 ; idx <actualLength; idx++){//从源列表框的第一项开始循环
	o=',';
	 if(idx==0) o='';
	 subtxt.value=subtxt.value+o+sourceObj.item(idx).value;
   }
  //obj.form.action=obj.form.action+'?action='+actions+"&subtxt="+subtxt.value;
url=obj.form.action+'?action='+actions+"&"+hid+"="+subtxt.value+"&rid="+rid;
//url="<gdp:context/>"+url;

 opener.document.location=url;



 window.close();
}


 function toSelectAction1(obj,sourceList,hid,actions){
     var sourceObj = document.all(sourceList);
     var subtxt = document.all(hid);
     subtxt.value='';
    actualLength = sourceObj.options.length;
    for (idx =0 ; idx <actualLength; idx++){//从源列表框的第一项开始循环
	o=',';
	 if(idx==0) o='';
	 subtxt.value=subtxt.value+o+sourceObj.item(idx).value;
   }
  //obj.form.action=obj.form.action+'?action='+actions+"&subtxt="+subtxt.value;
url=obj.form.action+'?action='+actions+"&"+hid+"="+subtxt.value+"&rid="+rid;
//url="<gdp:context/>"+url;

 
}
</script>
 <html:submit  styleClass="button" property="list" value="取消" onclick="javascript:window.close()"/>
	
  </tr>
  <tr>
    <td align="center" >&nbsp;</td>
  </tr>
</table>
  
	<input type="hidden" id="userId" name="userId">
</html:form>


<%
if (true){
%>
<%
}
%>



<script language="javascript">
var pattern;
var seperator = ',';
var tempOption;
var patternStr;
var from;
var to;
var method = '';
function setMethod(obj){
	//var checkBox = document.all['namecode'];
        var checkBox = obj;
        method = '';
		
        if(checkBox.checked) {
          	method = 'userName';
			
			
        }
}
function setPattern(patternStr) {
	pattern = new RegExp(patternStr);
}
function ExtendAttibute(){
	//类别名,类别id
   this.name="";
    //在该类别的值
   this.value="";
}
function User(id,name,username,level) {
	this.name = name;
	this.id = id;
    this.userName = username;

	this.level=level;
    //所有扩展类别数据 
	//为ExtendAttibute对象数组
    this.extendAttibutes=new Array();
	this.isRight = false;
	//function compare_by_type(type,index) {
	function compare_by_type(type) {
		switch(type) {
			case 'id':
				return pattern.test(this.id);
			case 'id_left':
                        	if(pattern.test("," + this.id + ",")) {
                                	this.isRight = true;
                        	}
				return ;
			case 'id_right':
                        	if(pattern.test("," + this.id + ",")) {
                                	this.isRight = false;
                        	}
				return ;
			case 'userName':
				return pattern.test(this.userName);
			case 'name':
				return pattern.test(this.name);
			case 'level':
				return  pattern.test(this.level);
			case 'all':
				return true;
			default:
				return pattern.test(this.name);
			    
		}

	}
	this.isMatch = compare_by_type;
}
var userArray = new Array();
function initUserArray() {
	
	var index = 0;
	<logic:iterate id="element" name="users_selected"  indexId="index">
	userArray[index] = new User("<bean:write name="element" property="id"/>","<bean:write name="element" property="name"/>","<bean:write name="element" property="userName"/>","<bean:write name="element" property="level"/>");
	index=index+1;
	</logic:iterate>
	<logic:iterate id="element" name="users_noSelected">
	userArray[index] = new User("<bean:write name="element" property="id"/>","<bean:write name="element" property="name"/>","<bean:write name="element" property="userName"/>","<bean:write name="element" property="level"/>");
	
	userArray[index].isRight = true;
	index=index+1;
	</logic:iterate>

}
function initSelect(){
	//alert("asdfasdfasdf");
  	getFromTo();
  	//alert('initSelect');
    	from.options.length = 0;
    	to.options.length = 0;
        for(user in userArray) {
          var strName = userArray[user].name + "(" + userArray[user].userName+")";
        var option = new Option(strName,userArray[user].id);
        if(userArray[user].isRight==true) {
			
        	//alert(userArray[user].name + ' dept is ' + userArray[user].dept);
        	to.options[to.options.length]=option;
        	} else {
            //*****************************************************************
			from.options[from.options.length]=option;
            //*****************************************************************
        	}
        }
}
function reSetSelect(){
	
  	    initUserArray();
		
        initSelect();
}
function trim(obj){
  	return obj.replace( /(^\s*)|(\s*$)/g, "" );
}
function query() {
	if(navigator.appName=="Netscape")document.getElementById=document.all;
  	//alert("query method is " + method);
        getFromTo();
        var queryValue = trim(document.all("search").value);
		//alert(queryValue);
	setPattern(queryValue);

	from.options.length = 0;
	for(user in userArray) {
            	//alert(userArray[user].name + " isRight is " + userArray[user].isRight);
		if((userArray[user].isMatch("name") && userArray[user].isRight == false)||(userArray[user].isMatch("userName") && userArray[user].isRight == false)) {
         		var strName = userArray[user].name + "(" + userArray[user].userName+")";
			var option = new Option(strName,userArray[user].id);
            //*****************************************************************
			from.options[from.options.length]=option;
            //*****************************************************************
		}
		
	}
}

function searchInLevel() {
  	//alert("query method is " + method);
        getFromTo();
        var searchLevelValue = trim(document.forms[0].level.value);
		
	setPattern(searchLevelValue);

	from.options.length = 0;
	for(user in userArray) {
            	//alert(userArray[user].name + " isRight is " + userArray[user].isRight);
		if(userArray[user].isMatch("level") && userArray[user].isRight == false) {
         		var strName = userArray[user].name + "(" + userArray[user].userName+")";
			var option = new Option(strName,userArray[user].id);
            //*****************************************************************
			from.options[from.options.length]=option;
            //*****************************************************************
		}
		
	}
}
var tempOption;
function moveOneFrom(type) {
	getFromTo(type)
	for(var i = 0 ; i < from.options.length ; i ++) {
		if(from.options[i].selected) {
			tempOption = new Option(from.options[i].text,from.options[i].value);
			setRight(type);
            //*****************************************************************
			to.options[to.options.length]=tempOption;
            //*****************************************************************
			from.options[i] = null;
			i--;
		}
	}
}
function moveAllFrom(type) {
	getFromTo(type);
	for(var i = 0 ; i < from.options.length ; i ++) {
		tempOption = new Option(from.options[i].text,from.options[i].value);
                setRight(type);
        //*****************************************************************
		to.options[to.options.length]=tempOption;
        //*****************************************************************
		from.options[i] = null;
		i--;
	}
}
function setRight(type) {
   //alert('type is ' + type)
   setPattern("," + tempOption.value + ",");
   for(user in userArray) {
      userArray[user].isMatch('id_' + type);
   }
}
from = document.all('addLeftListBox');
to = document.all('addRightListBox');
function getFromTo(type) {
		switch (type) {
			case 'left':
				from = document.all("addLeftListBox");
				to = document.all("addRightListBox");
				break;
			case 'right':
				from = document.all("addRightListBox");
				to = document.all("addLeftListBox");
				break;
			default:
				from = document.all("addLeftListBox");
				to = document.all("addRightListBox");
				break;
	}
}
var form = document.forms[0];
function setSubmitAble(){
  form.onsubmit = "";
}
</script>
</body>
</html>