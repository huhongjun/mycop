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
	document.all("addressSelect").checked = false;
	document.all("bmSelectList").style.display="block"
	document.all("zwSelectList").style.display="none"
	document.all("addressSelectList").style.display="none"
}
function usezw() {
	document.all("bmSelect").checked = false;
	document.all("zwSelect").checked = true;
	document.all("addressSelect").checked = false;
	document.all("zwSelectList").style.display="block"
	document.all("bmSelectList").style.display="none"
	document.all("addressSelectList").style.display="none"
}
function useaddress() {
	document.all("addressSelect").checked = true;
	document.all("bmSelect").checked = false;
	document.all("zwSelect").checked = false;
	document.all("bmSelectList").style.display="none"
	document.all("zwSelectList").style.display="none"
	document.all("addressSelectList").style.display="block"
}
function window_onload() {
	 window.returnValue = 'cancel=?';
     }
     function doOK(){


	 var strRetValue = "";
	 var strRetText = "";
	 var obj = document.all("rightSelect")
	    for (var idx =0 ; idx<= obj.options.length-1; idx++){
		strRetValue  = strRetValue + obj.item(idx).value  + "," ;
		strRetText = strRetText + obj.item(idx).text + ",";
	    }
	 var returnObj = new Object();
	 returnObj.id = strRetValue.substring(0,strRetValue.length-1);
	 returnObj.name = strRetText;
	  window.returnValue = returnObj;
          //alert('window.returnValue is ' + window.returnValue)
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
 window.close();
 }


     //-->
 </SCRIPT>

 <title>��Աѡ��ҳ��</title>
<body class="bodybg" onload="javascript:usebm();initSelect();" onunload="javascript:unload();">
 <html:form action="/menselect/menselect" onsubmit="return false;">

 <table align="center" width="420" class="InputFrameMain" border="0">
   <tr>
   <td>
   <fieldset>
 <legend class="f12">ѡ��</legend>
    <table width=400 border=0 align=center cellpadding=0 cellspacing=0 style="Margin:5px 5px 5px 5px">
        <tr class=InputFrameLine>
          <td class="inputlabelcell" nowrap></td>
          <td class=inputareacell></td>
        </tr>

        <tr>
          <td width=100 class="inputlabelcell" nowrap>ѡ�����:</td>
          <td class=inputareacell align=center>
            <label for="sel1"><input type=radio id="sel1" name="bmSelect" value="1" onfocus="javascript:usebm();initSelectDept();"/>������&nbsp</label>
            <label for="sel2"><input type="radio" id="sel2"  name="zwSelect" value="0" onfocus="javascript:usezw();initSelectJob();"/>��ְλ&nbsp;</label>
            <label for="sel3"><input type="radio" id="sel3"  name="addressSelect" value="0" onfocus="javascript:useaddress();initSelectAdd();"/>��Ⱥ��</label></td>
          </tr>
        <tr class="InputFrameLine">
          <td class='inputlabelcell' nowrap>����/ְλ:</td>
          <td class=inputareacell align=center>
          <html:select property="bmSelectList" size="1" style="width:200;display:inline" onchange="initSelect('dept')" styleClass="input">
            <html:option value="0">���в���</html:option>
            <html:options  collection="bmList" property="code" labelProperty="content" filter="false"/>
            </html:select>
            <html:select property="zwSelectList" size="1" style="width:200;display:none" onchange="initSelect('job')" styleClass="input">
            <html:option value="0">����ְλ</html:option>
            <html:options  collection="zwList" property="code" labelProperty="content"/>
            </html:select>
            <html:select property="addressSelectList" size="1" style="width:200;display:none" onchange="initSelect('address')" styleClass="input">
            <html:option value="0">����Ⱥ��</html:option>
            <html:options  collection="addressList" property="member" labelProperty="group_name" filter="false"/>
            </html:select> </td>
        </tr>


        <tr class="InputFrameLine">
          <td class="InputLabelCell">��������:</td>
          <td align=center><INPUT type="text" name="search" style="width:200;"  onkeyup="javascript:query();"></td>
        </tr>
</table>
		</fieldset>

        <tr class="InputFrameLine">
          <td colspan=2 class=inputareacell> <table width=100% class=tableframe  border="0" cellpadding="0" cellspacing="0">
              <tr align=center class="f12">
                <td height="22"></td>
                <td height="22">&nbsp;</td>
                <td height="22"></td>
              </tr>
              <tr align=center >
                <td>
							  <fieldset >
<legend class="f12">��ѡ��Ա</legend>
 <table align="center" width="150" class="InputFrameMain" border="0">
                    <select name="leftSelect" size="10" style="width: 100%"  ondblclick="moveOneFrom('left')" multiple>
                  </select>
                    </table>
                </td>

				</fieldset >
                <td valign=center width=50>
                    <table width="100%" border="0" cellpadding="0" cellspacing="2">
                    <tr align="center">
                      <td> <input name="selectAll" type="button" class="button" value=" >>" onclick="moveAllFrom('left')"img="/goas/images/but_cy_6.gif"/>
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
				  <fieldset>
<legend class="f12">��ѡ��Ա</legend>
				  <table align="center" width="150" class="InputFrameMain" border="0">
                <select name="rightSelect" style="width: 100%" size="10"  ondblclick="moveOneFrom('right')" multiple>
                  </select>
 </table>
				 </fieldset>
                 </td>

              </tr>
            </table></td>
        </tr>
        <tr>
          <td colspan=2 height="18"></td>
        </tr>
        <tr class="InputFrameButtonLine">
          <td align="center" colspan=2 height="30"> <input type="button" name="OK" type="button" value="ȷ��" class="button"  onclick="doOK()" />
            <input name="Cancel" type="button" value="ȡ��" class="button"  onclick="doCancel()" />
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
			case 'id':
				return pattern.test(seperator + this.id + seperator);
			case 'id_left':
                        	if(pattern.test(seperator + this.id + seperator)) {
                                	this.isRight = true;
                        	}
				return ;
			case 'id_right':
                        	if(pattern.test(";" + this.id + ";")) {
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
			default:
				return pattern.test(this.name);
		}

	}
	this.isMatch = compare_by_type;
}
function query() {
	setPattern(document.all.search.value);
	initSelect('query');
}
function initSelect(type) {
	from = document.all.leftSelect;
	to = document.all.rightSelect;
        from.options.length = 0;
        //to.options.length = 0;
	switch (type) {
	case 'query':
		searchInRight()
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
        case 'address':
        	if(document.all.addressSelectList.value != 0) {
        	  userIds = document.all.addressSelectList.value;
		  if(userIds.length > 0) {
    			userId = userIds.split(seperator);
		  }
		  //alert(userId.length);
		  for(user in userArray) {
 		     for(var i = 0 ; i < userId.length ; i++) {
   		       setPattern(seperator+userId[i]+seperator);
                      if(userArray[user].isMatch('id') && userArray[user].isRight==false) {
						  //alert(userId[i]+"|"+userArray[user].id);
 		  	  var option = new Option(userArray[user].name,userArray[user].id);
                          from.add(option);
                      }
  		    }
		  }
        	}
                break;
	default:
		for(user in userArray) {
			var option = new Option(userArray[user].name,userArray[user].id);
                        //alert(userArray[user].name + " isRight is " + userArray[user].isRight);
			if(userArray[user].isRight) {
                            to.add(option);
			} else {
			    from.add(option);
			}
		}
		break;
	}
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
//alert("DEPT"+document.all.bmSelectList.options[0].value)
// alert("--ZW-"+document.all.zwSelectList.options[0].value)
    for(var i = 0 ; i < document.all.zwSelectList.options.length ; i ++) {
        if(document.all.zwSelectList.options[i].value == 0) {
            document.all.zwSelectList.options[i].selected = true;
            document.all.zwSelectList.value = 0;
            break;
        }
    }
    initSelect('job');
}
function initSelectAdd() {
    for(var i = 0 ; i < document.all.addressSelectList.options.length ; i ++) {
        if(document.all.addressSelectList.options[i].value == 0) {
            document.all.addressSelectList.options[i].selected = true;
            document.all.addressSelectList.value = 0;
            break;
        }
    }
    initSelect('address');
}
function moveOneFrom(type) {
	getFromTo(type)
	for(var i = 0 ; i < from.options.length ; i ++) {
		if(from.options[i].selected) {
			tempOption = new Option(from.options[i].text,from.options[i].value);
			setRight(type);
			to.add(tempOption);
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
		to.add(tempOption);
		from.options[i] = null;
		i--;
	}
}
function setRight(type) {
   setPattern(tempOption.value);
   for(user in userArray) {
      userArray[user].isMatch('id_' + type);
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
function searchInRight() {
	for(var i = 0 ; i < to.options.length; i ++) {
//	alert(pattern.test(to.options[i].text))
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
var userId = new Array();
userIds = window.dialogArguments;
//alert('userIds is ' + userIds);
if(userIds.length > 0) {
    userId = userIds.split(seperator);
}
for(user in userArray) {
    for(var i = 0 ; i < userId.length ; i++) {
        setPattern(";" + userId[i] + ";");
        //alert('pattern is ' + pattern);
        userArray[user].isMatch('id_left');
    }
}
 </SCRIPT>

