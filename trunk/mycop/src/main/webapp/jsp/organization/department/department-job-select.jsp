<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%String context=request.getContextPath();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>岗位选择</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js">
</script>
</head>

<body class="bodybg" style="overflow-x:hidden;overflow-y:hidden;border: none" onload="javascript:reSetSelect();">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>
<html:form  action="/DepartmentAction" onsubmit="javascript:return false;">
<table align="center">
  <tr>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td ><table border="0" cellpadding="2" cellspacing="1">
        <tr>
          <td colspan="2"><table border="0" cellpadding="2" cellspacing="1">
          <tr class="InputLabelCell">
                <td>岗位名称搜索:</td>
                <td valign="middle" colspan="2">
                <INPUT type="text" name="search" id="search" style="width:165" class="input2" onkeyup="javascript:query();">
                </td>
              </tr>
              <tr class="InputLabelCell">
                <td>可选岗位列表:</td>
                <td valign="middle">&nbsp;</td>
                <td>已选岗位列表:</td>
              </tr>
              <tr>
                <td align="center" valign="top">
				<select name="addLeftListBox" id="addLeftListBox" multiple class="input2" style="width:120px; height:200px;"
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
				<select name="addRightListBox" id="addRightListBox" multiple class="input2" style="width:120px; height:200px;" ondblclick="moveOneFrom('right')">

                  </select></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>

    <td align="center" >  <html:submit styleClass="button"  property="create" value="确定" onclick="javascript:setSubmitAble();toSelectAction(this,'addRightListBox','jobId','setDepartmentJob')"/>
              <html:button styleClass="button" property="reset" value="重置" onclick="javascript:reSetSelect();"/>
    <html:submit styleClass="button"  property="list" value="返回" onclick="javascript:setSubmitAble();toAction(this,'list')"/>
	<input type="hidden" name="jobId" id="jobId">
	<html:hidden name="DepartmentForm" property="id"/>
	<input type="hidden" name="departid" value="<%=request.getParameter("departid")%>">
	<input type="hidden" name="page" value="<%=request.getParameter("page")%>">
    </td>
    </td>
  </tr>
  <tr>
    <td align="center" >&nbsp;</td>
  </tr>
</table>
</html:form>
</body>
</html>
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>
<script language="javascript">
var pattern;
var seperator = ',';
var patternStr;
var from;
var to;
function setPattern(patternStr) {
	pattern = new RegExp(patternStr);
}
function User(id,name,username) {
	this.name = name;
	this.id = id;
        this.userName = username;
	this.isRight = false;
	//function compare_by_type(type,index) {
	function compare_by_type(type,index) {//汤启礼修改,添加一个参数index,用于标识数据存放于右边哪一个select box
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
                                //alert(this.name + " right is " + this.isRight)
				return ;
			case 'name':
				return pattern.test(this.name);
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
	<logic:iterate id="element" name="unDepartmentJobs"  indexId="index">
	userArray[index] = new User("<bean:write name="element" property="id"/>","<bean:write name="element" property="name"/>","")
	index++;
	</logic:iterate>
	<logic:iterate id="element" name="departmentJobs">
	userArray[index] = new User("<bean:write name="element" property="id"/>","<bean:write name="element" property="name"/>","");
	userArray[index].isRight = true;
	index++;
	</logic:iterate>
}
function initSelect(){
  	getFromTo();
    from.options.length = 0;
    to.options.length = 0;
    for(user in userArray) {
        var option = new Option(userArray[user].name,userArray[user].id);
        if(userArray[user].isRight==true) {
            //alert(userArray[user].name + ' dept is ' + userArray[user].dept);
            //*****************************************************************
            to.options[to.options.length] = option;
            //*****************************************************************
        } else {
            from.options[from.options.length] = option;
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
  	//alert("query")
        getFromTo();
        var queryValue = trim(document.all('search').value);
	setPattern(queryValue);

	from.options.length = 0;
	for(user in userArray) {
        //alert(userArray[user].name + " isRight is " + userArray[user].isRight);
		if(userArray[user].isMatch() && userArray[user].isRight == false) {
			var option = new Option(userArray[user].name,userArray[user].id);
            //*****************************************************************
			from.options[from.options.length] = option;
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
			to.options[to.options.length] = tempOption;
            //*****************************************************************
			from.options[i] = null;
			i--
		}
	}
}
function moveAllFrom(type) {
	getFromTo(type);
	for(var i = 0 ; i < from.options.length ; i ++) {
		tempOption = new Option(from.options[i].text,from.options[i].value);
        setRight(type);
        //*****************************************************************
		to.options[to.options.length] = tempOption;
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
//********************************************************************
//select已经添加了ID属性，此处可以改为如下一兼容Netscape
from = document.all('addLeftListBox');
to = document.all('addRightListBox');
//********************************************************************
/*************************
from = document.all['addLeftListBox'];
to = document.all['addRightListBox'];
*************************/
//********************************************************************
function getFromTo(type) {
		switch (type) {
			case 'left':
                //********************************************************************
				from = document.all('addLeftListBox');
				to = document.all('addRightListBox');
                /*************************
				from = document.all.addLeftListBox;
				to = document.all.addRightListBox;
                *************************/
                //********************************************************************
				break;
			case 'right':
				from = document.all('addRightListBox');
				to = document.all('addLeftListBox');
				break;
			default:
				from = document.all('addLeftListBox');
				to = document.all('addRightListBox');
				break;
	}
}
var form = document.forms[0];
function setSubmitAble(){
  form.onsubmit = "";
}
</script>
