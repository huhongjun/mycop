<%@ page language="java" %>
 <%@ page import="java.util.ArrayList"%>
 <%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/css/goa.css" rel="stylesheet" type="text/css">
<style type="text/css">
	.selected{
		background-color: #ECF5FF;
		border:1px blue;
	}
</style>
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
/*原来的代码*/
/*
function doOK(){
	var strRetValue = "";
	var strRetText = "";
	var obj = document.all("rightSelect")
	for (var idx =0 ; idx<= obj.options.length-1; idx++){
		strRetValue  = strRetValue + obj.item(idx).value  + "," ;
		strRetText = strRetText + obj.item(idx).text + " ";
	}
	var returnObj = new Object();
	returnObj.id = strRetValue.substring(0,strRetValue.length-1);
	returnObj.name = strRetText;
	window.returnValue = returnObj;
	//alert('window.returnValue is ' + window.returnValue)
	window.close();
}
*/
/*汤启礼添加*/
var returnObj=function(){
	this.value="";
	this.text="";
}
function doOK(){
	var retObj = null;;
	var strRetValue = "";
	var strRetText = "";
	var retArr=[];
	var index=0;
	for(var i=0;i<selArr.length;i++){
		retObj=new returnObj();
		//if(obj.children[i].tagName=="SELECT"){
		var opts=selArr[i].options;
		for(var j=0;j<opts.length;j++){
			retObj.value+=opts[j].value+",";
			retObj.text+=opts[j].text+",";
		}
		if(retObj.value!="") retObj.value=retObj.value.substring(0,retObj.value.length-1);
		if(retObj.text!="") retObj.text=retObj.text.substring(0,retObj.text.length-1);
		retArr[index++]=retObj;
	}
	window.returnValue = retArr;
	//alert('window.returnValue is ' + window.returnValue)
	window.close();
}
/*汤启礼添加end*/
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

 <title>人员选择页面</title>
<body class="bodybg" onload="javascript:usebm();initSelect();" onunload="javascript:unload();">
 <html:form action="/menselect/mailmanselect" onsubmit="return false;">

 <table align="center" width="420" class="InputFrameMain" border="0">
   <tr>
   <td>
   <fieldset>
 <legend class="f12">选择</legend>
    <table width=400 border=0 align=center cellpadding=0 cellspacing=0 style="Margin:5px 5px 5px 5px">
        <tr class=InputFrameLine>
          <td class="inputlabelcell" nowrap></td>
          <td class=inputareacell></td>
        </tr>

        <tr>
          <td width=100 class="inputlabelcell" nowrap>选择类别:</td>
          <td class=inputareacell align=center>
            <label for="sel1"><input type=radio id="sel1" name="bmSelect" value="1" onfocus="javascript:usebm();initSelectDept();"/>按部门&nbsp</label>
            <label for="sel2"><input type="radio" id="sel2"  name="zwSelect" value="0" onfocus="javascript:usezw();initSelectJob();"/>按职位&nbsp;</label>
            <label for="sel3"><input type="radio" id="sel3"  name="addressSelect" value="0" onfocus="javascript:useaddress();initSelectAdd();"/>按群组</label></td>
          </tr>
        <tr class="InputFrameLine">
          <td class='inputlabelcell' nowrap>部门/职位:</td>
          <td class=inputareacell align=center>
          <html:select property="bmSelectList" size="1" style="width:200;display:inline" onchange="initSelect('dept')" styleClass="input">
            <html:option value="0">所有部门</html:option>
            <html:options  collection="bmList" property="code" labelProperty="content" filter="false"/>
            </html:select>
            <html:select property="zwSelectList" size="1" style="width:200;display:none" onchange="initSelect('job')" styleClass="input">
            <html:option value="0">所有职位</html:option>
            <html:options  collection="zwList" property="code" labelProperty="content"/>
            </html:select>
            <html:select property="addressSelectList" size="1" style="width:200;display:none" onchange="initSelect('address')" styleClass="input">
     
            <html:options  collection="addressList" property="member" labelProperty="group_name" filter="false"/>
            </html:select> </td>
        </tr>


        <tr class="InputFrameLine">
          <td class="InputLabelCell">人名搜索:</td>
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
							  <fieldset  >
<legend class="f12">可选人员</legend>
 				<table align="center"  class="InputFrameMain" border="0">
                    <select name="leftSelect" size="17" style="width: 150;height:254"  ondblclick="moveOneFrom('left')" multiple>

				  </select>
                    </table>

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
				<legend class="f12">已选人员</legend>
					<div id="selContainer" >
					<span   class="f12" style="width: 150;text-align:left">收件人</span><br>
					<select name="rightSelect" id="receiver" onFocus="setCurrent(this)" style="width: 150;height:70" size="10"  ondblclick="moveOneFrom('right')" multiple>
					</select><br>
					<span  class="f12"  style="width: 150;text-align:left">抄送人</span><br>
					<select  id="copysend" style="width: 150;height:70" size="10" onFocus="setCurrent(this)"   ondblclick="moveOneFrom('right')" multiple>
					</select><br>
					<span  class="f12"  style="width: 150;text-align:left">密送人</span><br>
					<select  id="whisper" style="width: 150;height:70" size="10" onFocus="setCurrent(this)"   ondblclick="moveOneFrom('right')" multiple>
					</select>
					</div>

				</fieldset>
                 </td>

              </tr>
            </table></td>
        </tr>
        <tr>
          <td colspan=2 height="18"></td>
        </tr>
        <tr class="InputFrameButtonLine">
          <td align="center" colspan=2 height="30"> <input type="button" name="OK" type="button" value="确定" class="button"  onclick="doOK()" />
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
	this.index=0;
	//function compare_by_type(type,index) {
	function compare_by_type(type,index) {//汤启礼修改,添加一个参数index,用于标识数据存放于右边哪一个select box
		switch(type) {
			case 'id':
				return pattern.test(";" + this.id + ";");
			case 'id_left':
                        	if(pattern.test(";" + this.id + ";")) {
                                	this.isRight = true;
                        	}
							this.index=index;//汤启礼添加
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
	//to = document.all.rightSelect;
	to=selArr[0];
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
		  for(user in userArray) {
 		     for(var i = 0 ; i < userId.length ; i++) {
   		       setPattern(";" + userId[i] + ";");
                      if(userArray[user].isMatch('id') && userArray[user].isRight==false) {
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
			if(userArray[user].isRight) {
				selArr[userArray[user].index].add(option);
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
/*原来代码*/
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

/*汤启礼添加*/
/*function moveOneFrom(type) {
	getFromTo(type)
	if(type=="right"){
		for(var i = 0 ; i < from.options.length ; i ++) {
			if(from.options[i].selected) {
				tempOption = new Option(from.options[i].text,from.options[i].value);
				var canAdd=true;
				for(opt in to.options){
					if(opt.value==tempOption.value){//数据已存在
						canAdd=false;
					}
				}
				setRight(type);
				from.options[i] = null;
				i--
			}
		}
	}else if(type=="left"){
		for(var i = 0 ; i < from.options.length ; i ++) {
			if(from.options[i].selected) {
				setRight(type);
				from.options[i] = null;
				i--
			}
		}
	}
}
*/
/*汤启礼添加end*/
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
   setPattern(";" + tempOption.value + ";");
   for(user in userArray) {
      userArray[user].isMatch('id_' + type);
   }
}
/*原代码
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
*/
/*汤启礼添加*/
var currentRightSel=null;
function getFromTo(type) {
	if(currentRightSel==null){
		currentRightSel=selArr[0];
	}
	switch (type) {
		case 'left':
			from = document.all.leftSelect;
			to = currentRightSel;
			break;
		case 'right':
			from = currentRightSel;
			to = document.all.leftSelect;
			break;
		default:
			from = document.all.leftSelect;
			to = currentRightSel;
			break;
	}
}
function setCurrent(element){
	if(currentRightSel!=null){
		currentRightSel.className="";
		currentRightSel.selectedIndex=-1;
	}
	//currentRightSel=event.srcElement;
	currentRightSel=element;
	currentRightSel.className="selected";

}
/***汤启礼end****/
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
/*原来代码
var userId = new Array();
//var userIds = '<%=(String)request.getAttribute("userIds")%>'
//var userIds = '<bean:write name="MenSelectForm" property="userIds" filter="false"/>';
userIds = window.dialogArguments;
//alert('userIds is ' + userIds);

if(userIds.length > 0) {
    userId = userIds.split(seperator);
}
for(user in userArray) {
    for(var i = 0 ; i < userId.length ; i++) {
        setPattern(userId[i]);
        userArray[user].isMatch('id_left');
    }
}
*/
/*汤启礼修改*/
var userIds;
var idArr=[];
var selArr=[];//汤启礼添加，左边select box对象数组
userIds = window.dialogArguments;
//alert(userIds);
//alert(userIds.length);

//var btnName = userIds.pop();
//2004-10-11 曾祥永修改
//-------------
var btnName = popArray(userIds);
//--------------
setFocus(btnName);
var childrens=document.all.selContainer.children;
var selIndex=0;

//2004-10-11 曾祥永修改
//-----------------
var aryData = new Array();
for(var idx=0; idx< userIds.length-1; idx++){
	aryData[idx] =userIds[idx]; 
}
userIds = aryData;
//-------------------
for(var i=0;i<childrens.length;i++){
	if(childrens[i].tagName=="SELECT"){
		selArr[selIndex++]=childrens[i];
	}
}
for(var i=0;i<userIds.length;i++){
	var sid=userIds[i];//以“,”号分割的字符串
	if(sid.length > 0) {
		idArr = sid.split(seperator);
	}
	for(user in userArray) {
		if(!userArray[user].isRight){
			for(var j = 0 ; j < idArr.length ; j++) {
				setPattern(";" + idArr[j] + ";");
				userArray[user].isMatch('id_left',i);
			}
		}
	}

}

function setFocus(idName) {
	var focusObj = document.getElementById(idName);
	setCurrent(focusObj);
}
function popArray(arr){
	if(arr==null){
		return "reciver";
	}
	
	return (arr[arr.length-1]);
	
}
</SCRIPT>

