<%@ page language="java" %>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<title>选择主送单位</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscript/Validator.js">
</script>
<link href="<%=request.getContextPath()%>/css/goa.css" rel="stylesheet" type="text/css">
<SCRIPT ID=clientEventHandlersJS LANGUAGE=javascript>
<!--
     function doOK(){
	 var strRetValue = "";
	 var strRetText = "";
  	 var callerWindowObj = window.dialogArguments;
         //alert('name is ' + name);
	 var obj = document.all("rightSelect")
	    for (var idx =0 ; idx<= obj.options.length-1; idx++){
		strRetValue  = strRetValue + obj.item(idx).value  + "," ;
		strRetText = strRetText + obj.item(idx).text.trim() + " ";
	    }
	 var returnObj = new Object();
	 returnObj.id = strRetValue.substring(0,strRetValue.length-1);
	 returnObj.name = strRetText;
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

//-->
</SCRIPT>
<body class="bodybg" onload="javascript:initSelect();" onunload="javascript:unload();">
<form action="/menselect/menselect">
<table align="center" width="420" class="InputFrameMain" border="0">
   <tr>
   <td>
    <table width=400 border=0 align=center cellpadding=0 cellspacing=0 style="Margin:5px 5px 5px 5px">
        <tr class=InputFrameLine>
          <td class="inputlabelcell" nowrap>&nbsp;</td>
          <td class=inputareacell>&nbsp; </td>
        </tr>
        <tr class="InputFrameLine">
          <td colspan=2 class=inputareacell> <table width=100% class=tableframe  border="0" cellpadding="0" cellspacing="0">
              <tr align=center class="f12">
                <td height="22">可选部门</td>
                <td height="22">&nbsp;</td>
                <td height="22">已选部门</td>
              </tr>
              <tr align=center >
                <td>
                    <select name="leftSelect" size="8" style="width: 100%"  ondblclick="moveOneFrom('left')" multiple>
                  </select>
                </td>
                <td valign=center width=50>
                    <table width="100%" border="0" cellpadding="0" cellspacing="2">
                    <tr align="center">
                      <td> <input name="selectAll" type="button" class="button" value=" >>" onclick="moveAllFrom('left')"/>
                        <!--html:button  styleClass="butbk"  property= "selectAll"  onclick="doSelAll(0)" value=" >> " /-->
                      </td>
                    </tr>
                    <tr align="center">
                      <td> <input name="selectAll" type="button" class="button" value=" > "  onclick="moveOneFrom('left')"/>
                      </td>
                    </tr>
                    <tr align="center">
                      <td> <input name="selectAll" type="button" class="button" value=" < "  onclick="moveOneFrom('right')"/>
                      </td>
                    </tr>
                    <tr align="center">
                      <td> <input name="delAll" type="button" class="button" value="<< "  onclick="moveAllFrom('right')"/>
                      </td>
                    </tr>
                  </table></td>
                <td>
                <select name="rightSelect" style="width: 100%" size="8"  ondblclick="moveOneFrom('right')" multiple>
                  </select>
                 </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td colspan=2 height="18"></td>
        </tr>
        <tr class="InputFrameButtonLine">
          <td align="center" colspan=2 height="30">
            <input type="button" name="OK" type="button" value="确定"  class="button"  onclick="doOK()" />
            <input name="Cancel" type="button" value="取消" class="button"  onclick="doCancel()" />
          </td>
        </tr>
      </table>
 </td>
 </tr>
 </table>
</form>
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
function dept(name,id,parentDept) {
	this.name = name;
	this.id = id;
	this.parentDept = parentDept;
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
			case 'parentDept':
				return pattern.test(seperator + this.parentDept + seperator);
			default:
				return pattern.test(this.name);
		}

	}
	this.isMatch = compare_by_type;
}
function initSelect() {
	from = document.all.leftSelect;
	to = document.all.rightSelect;
        from.options.length = 0;
        to.options.length = 0;

		for(dept in deptInfoArray) {
			var option = new Option(deptInfoArray[dept].name,deptInfoArray[dept].id);
			if(deptInfoArray[dept].isRight) {
                            to.add(option);
			} else {
			    from.add(option);
			}
		}
}
/*
function initSelectDept() {
    for(var i = 0 ; i < document.all.bmSelectList.options.length ; i ++) {
        if(document.all.bmSelectList.options[i].value == 0) {
            document.all.bmSelectList.options[i].selected = true;
            document.all.bmSelectList.value = 0;
            return;
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
}*/
function moveOneFrom(type) {
	getFromTo(type)
	for(var i = 0 ; i < from.options.length ; i ++) {
		if(from.options[i].selected) {
			tempOption = new Option(from.options[i].text,from.options[i].value);
			setRight(type);
			//to.add(tempOption);
			//from.options[i] = null;
			//i--
		}
	}
    initSelect();
}
function moveAllFrom(type) {
	getFromTo(type);
	for(var i = 0 ; i < from.options.length ; i ++) {
		tempOption = new Option(from.options[i].text,from.options[i].value);
                setRight(type);
		//to.add(tempOption);
		//from.options[i] = null;
		//i--;
	}
    initSelect();
}
function setRight(type) {
   setPattern(tempOption.value);
   for(dept in deptInfoArray) {
      deptInfoArray[dept].isMatch('id_' + type);
   }
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
/*
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
}*/
<bean:write name="MenSelectForm" property="initUserInfoArray" filter="false"/>
var userId = new Array();
//var userIds = '<%=(String)request.getAttribute("userIds")%>'
//var userIds = '<bean:write name="MenSelectForm" property="userIds" filter="false"/>';
userIds = window.dialogArguments;
//alert('userIds is ' + userIds);
if(userIds.length > 0) {
    userId = userIds.split(seperator);
}
for(dept in deptInfoArray) {
    for(var i = 0 ; i < userId.length ; i++) {
        setPattern(userId[i]);
        deptInfoArray[dept].isMatch('id_left');
    }
}
 </SCRIPT>

