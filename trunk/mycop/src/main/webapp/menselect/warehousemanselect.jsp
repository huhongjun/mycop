<%@ page language="java" %>
 <%@ page import="java.util.ArrayList"%>
 <%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<link href="<%=request.getContextPath()%>/css/goa.css" rel="stylesheet" type="text/css">
 <SCRIPT ID=clientEventHandlersJS LANGUAGE=javascript>
     <!--

function usebm() {
    		document.all("bmSelect").checked = true;
                document.all("zwSelect").checked = false;
		document.all("bmSelectList").style.display="inline"
		document.all("zwSelectList").style.display="none"
}
function usezw() {
                document.all("bmSelect").checked = false;
                document.all("zwSelect").checked = true;
		document.all("zwSelectList").style.display="inline"
		document.all("bmSelectList").style.display="none"
}

 function window_onload() {
	 window.returnValue = 'cancel=?';
     }
     function doOK(){


	 var strRetValue = "";
	 var strRetText = "";
	 var obj = document.all("rightSelect")
	    for (var idx =0 ; idx<= obj.options.length-1; idx++){
                if(obj.item(idx).selected) {
		strRetValue  = strRetValue + obj.item(idx).value  + "," ;
		strRetText = strRetText + obj.item(idx).text + " ";
                break;
                }
	    }
	 var returnObj = new Object();
	 returnObj.id = strRetValue.substring(0,strRetValue.length-1);
	 returnObj.name = strRetText;
	 var userDept = getUserInfo(returnObj.id);
	 //alert('userInfo is ' + userDept)
	 for(var i = 0 ; i < document.all.bmSelectList.options.length ; i ++) {
		 //alert('dept id is ' + document.all.bmSelectList.options[i].value);
        if(document.all.bmSelectList.options[i].value == userDept) {
			returnObj.deptId = document.all.bmSelectList.options[i].value;
			returnObj.deptName = document.all.bmSelectList.options[i].text;
            break;
        }
    }
			//alert('dept name: ' + returnObj.deptName)
		   window.returnValue = returnObj;
           window.close();
     }
     function unload() {
         if(window.returnValue != null) {
         } else {
             	var obj = new Object();
		 obj.id = '-1';
		 obj.name = ''
	 	window.returnValue = obj;
         }
     }
     function doCancel(){
		 var obj = new Object();
		 obj.id = '-1';
		 obj.name = '';
	 window.returnValue = obj;
	 window.close();
     }

function getUserInfo(id) {
	setPattern(id);
	//alert('pattern is ' + pattern)
	for(user in userArray) {
        if(userArray[user].isMatch('id')) {
			//alert("dept is " + userArray[user].dept);
			return userArray[user].dept;
        }
    }
}
     //-->
 </SCRIPT>

 <title>仓库操作人员选择页面</title>
<body class="bodybg" onload="javascript:usebm();initSelect();" onunload="javascript:unload();">
 <html:form action="/menselect/manselect" onsubmit="return false;">

 <table align="center" width="280" class="InputFrameMain" border="0">
   <tr>
   <td>
   	<fieldset style="display:none">
    <legend class="f12"style="display:none">选择</legend>
	<table align="center" width="150" class="InputFrameMain" border="0">
    <table width=280 border="0" align=center cellpadding=0 cellspacing=0 style="Margin:5px">
        <tr class=InputFrameLine>
          <td class="inputlabelcell" nowrap></td>
          <td class=inputareacell></td>
        </tr>
        <tr class=InputFrameLine >
        <td width="30%" class="inputlabelcell" nowrap>选择类别:</td>
        <td class=inputareacell align=center> <input type=radio name="bmSelect" value="1"
	onfocus="javascript:usebm();initSelectDept();"/>按部门选择&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="zwSelect" value="0" onfocus="javascript:usezw();initSelectJob();"/>按职位选择</td>
          <html:hidden property="ID"/> <html:hidden property="name"/> </tr>
        <tr class="InputFrameLine"style="display:none">
          <td class='inputlabelcell' nowrap>部门/职位:</td>
          <td class=inputareacell align=center>
          <html:select property="bmSelectList" size="1" style="width:200;display:inline" onchange="initSelect('dept')" styleClass="input">
            <html:option value="0">所有部门</html:option>
            <html:options  collection="bmList" property="code" labelProperty="content" filter="false"/>
            </html:select>
            <html:select property="zwSelectList" size="1" style="width:200;display:none" onchange="initSelect('job')" styleClass="input">
            <html:option value="0">所有职位</html:option>
            <html:options  collection="zwList" property="code" labelProperty="content"/>
            </html:select> </td>
        </tr>
        <tr class="InputFrameLine">
          <td class="InputLabelCell">人名搜索:</td>
          <td align=center><INPUT type="text" name="search" style="width:200;" onkeyup="javascript:query();"></td>
        </tr>
		 </table>
	    </fieldset>
        <tr class="InputFrameLine">
          <td colspan=2 class=inputareacell>
		  <table width=100% class=tableframe  border="0" cellpadding="0" cellspacing="0">
              <tr align=center class="f12">
                <td height="22"></td>
              </tr>
	<tr align=center >
     <td>
	   <fieldset>
         <legend class="f12">可选人员</legend>
	    <select name="rightSelect" style="width: 80%" size="11" ondblclick="javasript:doOK();">
        </select>	
	   </fieldset>
    </td>
    </tr>
	
 </table>
 </td>
        </tr>
        <tr>
          <td colspan=2 height="13"></td>
        </tr>
        <tr class="InputFrameButtonLine">
          <td align="center" colspan=2 height="20"> <input type="button" name="OK" type="button" value="确定" class="button"  onclick="doOK()" />
            <input name="Cancel" type="button" value="取消" class="button"  onclick="doCancel()" />
          </td>
        </tr>
      </table>
 </td>
 </tr>
 </table>

 </html:form>
</body>
 <SCRIPT LANGUAGE="JavaScript">
<!--
var pattern;
var seperator = ',';
var tempOption;
var patternStr;
var from;
var to;
function setPattern(patternStr) {
	pattern = new RegExp(patternStr);
}
function User(name,id,dept,job) {
	this.name = name;
	this.id = id;
	this.dept = dept;
	this.job = job;
	this.isRight = false;
	function compare_by_type(type) {
		switch(type) {
			case 'id_left':
                        	if(pattern.test(this.id)) {
                                	this.isRight = true;
                        	}
				return ;
			case 'id_right':
                        	if(pattern.test(this.id)) {
                                	this.isRight = false;
                        	}
				return ;
			case 'name':
				return pattern.test(this.name);
			case 'dept':
				return pattern.test(seperator + this.dept + seperator);
			case 'dept_all':
				return this.dept.length > 0;
			case 'job':
				return pattern.test(seperator + this.job + seperator);
			case 'job_all':
				return this.job.length > 0;
			case 'all':
				return true;
			case 'id':
				return pattern.test(this.id);
			default:
				return pattern.test(this.name);
		}

	}
	this.isMatch = compare_by_type;
}
function initSelect(type) {
	from = document.all.rightSelect;
        from.options.length = 0;
        //to.options.length = 0;
	switch (type) {
	case 'query':
		//searchInRight()
		searchInUserArray();
		break;
        case 'dept':
        	if(document.all.bmSelectList.value != 0) {
        	patternStr = seperator + document.all.bmSelectList.value + seperator;
        	setPattern(patternStr);
                //alert('pattern is ' + pattern);
        	for(user in userArray) {
                    if(userArray[user].isMatch('dept') && userArray[user].isRight==false) {
                        //alert(userArray[user].name + ' dept is ' + userArray[user].dept);
			var option = new Option(userArray[user].name,userArray[user].id);
                        from.add(option);
                    }
        	}
        	}else {
        	for(user in userArray) {
                    if(userArray[user].isMatch('dept_all') && userArray[user].isRight==false) {
                        //alert(userArray[user].name + ' dept is ' + userArray[user].dept);
			var option = new Option(userArray[user].name,userArray[user].id);
                        from.add(option);
                    }
        	}
        	}
                break;
        case 'job':
        	if(document.all.zwSelectList.value != 0) {
        	patternStr = seperator + document.all.zwSelectList.value + seperator;
        	setPattern(patternStr);
        	for(user in userArray) {
                    if(userArray[user].isMatch('job') && userArray[user].isRight==false) {
			var option = new Option(userArray[user].name,userArray[user].id);
                        from.add(option);
                    }
        	}
        	}else {
         	for(user in userArray) {
                    if(userArray[user].isMatch('job_all') && userArray[user].isRight==false) {
			var option = new Option(userArray[user].name,userArray[user].id);
                        from.add(option);
                    }
        	}
        	}
                break;
	default:
		for(user in userArray) {
			var option = new Option(userArray[user].name,userArray[user].id);
                        //alert(userArray[user].name + " isRight is " + userArray[user].isRight);

			    from.add(option);
		}
		break;
	}
}
function query() {
	setPattern(document.all.search.value);
	initSelect('query');
}
function initSelectDept() {
    for(var i = 0 ; i < document.all.bmSelectList.options.length ; i ++) {
        if(document.all.bmSelectList.options[i].value == 0) {
            document.all.bmSelectList.options[i].selected = true;
            document.all.bmSelectList.value = 0;
            break;
        }
    }
    initSelect('dept');
}
function initSelectJob() {
    for(var i = 0 ; i < document.all.zwSelectList.options.length ; i ++) {
        if(document.all.zwSelectList.options[i].value == 0) {
            document.all.zwSelectList.options[i].selected = true;
            document.all.zwSelectList.value = 0;
            return;
        }
    }
    initSelect('job');
}

function getFromTo(type) {
		switch (type) {
			case 'left':
				from = document.all.leftSelect;
				to = document.all.rightSelect;
				break;
			case 'right':
				from = document.all.rightSelect;
				to = document.all.leftSelect;
				break;
			default:
				from = document.all.leftSelect;
				to = document.all.rightSelect;
				break;
	}
}
function searchInRight() {
	for(var i = 0 ; i < to.options.length; i ++) {
		if(pattern.test(to.options[i].text)) {
			to.options[i].selected = true;
		}
	}
}
function searchInUserArray() {
	from.options.length = 0;
	for(user in userArray) {
            	//alert(userArray[user].name + " isRight is " + userArray[user].isRight);
		if(userArray[user].isMatch() && userArray[user].isRight == false) {
			var option = new Option(userArray[user].name,userArray[user].id);
			from.add(option);
		}
	}
}
<bean:write name="MenSelectForm" property="initUserInfoArray" filter="false"/>
 </SCRIPT>


