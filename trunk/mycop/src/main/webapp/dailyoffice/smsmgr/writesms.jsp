<%@ page contentType="text/html; charset=gb2312" %>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%@ page import="com.gever.struts.Constant,com.gever.goa.dailyoffice.smsmgr.action.*"%>
<%@ page import="com.gever.goa.dailyoffice.tools.vo.CardcaseVO"%>
<%@ page import="com.gever.goa.dailyoffice.tools.vo.CardcaseTypeVO,java.util.*"%>
<%@ page import="com.gever.goa.dailyoffice.smsmgr.vo.*"%>
<%@ page import="com.gever.goa.util.*"%>
<%@ page import="com.gever.sysman.organization.model.*"%>
<html>

<title>内容</title>


</head>
<SCRIPT LANGUAGE="JavaScript">
<!--
    //改变部门时
	function onChangeDept(){
		readData(curtype);
	}
	//当部门改变时,需更改其使用用户
	function readData(type){
		
		var userObj =  document.all("noselectuser");
		var deptObj =  document.all("selectDept");
		var destObj =  document.all("selecteduser");
		//alert(deptObj.value + " ilength ->" +lngLength);
		//先清空其人员资料
		for (var idx= userObj.options.length-1 ; idx>=0 ; idx--){
			userObj.remove(idx)
		}
		var iLength = 0;
		var bIsOk = false;
		//往人员资料中塞资料
		for (var idx =0; idx< lngLength; idx++){
			//alert(deptObj.value);
			if ((equalType(type,aryUser[idx][2],deptObj.value) || deptObj.value == "0" )&&( aryUser[idx][3]==type) )  {
				
				bIsOk=false;
				for (idy=0;idy<=destObj.options.length-1 ; idy++ ){
					if (aryUser[idx][1]+":"+aryUser[idx][0] == destObj.item(idy).value){
						bIsOk = true;
						break;
					}
				}
				if (bIsOk == false){
					iLength = userObj.options.length;
					userObj.options[iLength] = new Option(aryUser[idx][1]+":"+aryUser[idx][0],aryUser[idx][1]+":"+aryUser[idx][0]);
				}
			}
		}
 	}

	//比较部门
	function equalType(type,uValue, dValue){

		var pos = uValue.indexOf(",");
			//	alert(uValue + " = " +dValue + " pos=" + pos	);
		if (pos < 0 ){
			if(uValue == dValue )
				return true;
			else 
				return false;
		}

		var depts = uValue.split(",");
		for (var idx = 0 ; idx < depts.length; idx++){
			if (depts[idx] == dValue)
				return true;
		}
		return false;

	}

	//当改变地址簿或名片夹的时候
	function onChangeType(type){
		//alert(deptlength);
		var deptObj =  document.all("selectDept");

		//alert(deptObj.value + " ilength ->" +lngLength);
		//先清空其人员资料
		for (var idx= deptObj.options.length-1 ; idx>=0 ; idx--){
			deptObj.remove(idx)
		}
		var iLength = 0;
		deptObj.options[0] = new Option("所有人员","0");
		//往人员资料中塞资料
		//alert(deptlength);
		for (var idx =0; idx< deptlength; idx++){
			
			if ( aryDept[idx][2] == type )  {
				iLength = deptObj.options.length;
				deptObj.options[iLength] = new Option(aryDept[idx][1],aryDept[idx][0]);
			}
		}
		readData(type);
		curtype=type;
	}

	//是回复的短信时
	function toRe(isReMB){
		if (isReMB==false)
			return false;
		var userObj =  document.all("selecteduser");
		var isFind = false
		for (var idx =0; idx< lngLength; idx++){
			
			if ( aryUser[idx][1] == strMbno )  {
				//alert(aryUser[idx][1] + " = " + strMbno);
				iLength = userObj.options.length;
				userObj.options[iLength] = new Option(aryUser[idx][1]+":"+aryUser[idx][0],aryUser[idx][1]+":"+aryUser[idx][0]);
				isFind = true;
				break;
			}
		}
	    if (isFind == false)
			document.all("keyMobile").value = strMbno;


	}

	//检查单个的人员
	function doSelOne(actionType){
		var idx, idy;
		var bIsOk ;

		switch(actionType){
		case 0://为左边的列表框
			var sourceObj = document.all("noselectuser");
			var destObj = document.all("selecteduser");
			break;
		case 1://为右边的列表框
			var sourceObj = document.all("selecteduser");
			var destObj = document.all("noselectuser");
			break;
		}

		if (sourceObj.options.length==0)
			return;
		bIsOk=false;
		for (j=sourceObj.options.length-1 ; j >=0 ; j--){
			if (sourceObj.options[j].selected==true){
				dLength = destObj.options.length;
				destObj.options[dLength] = new Option(sourceObj.options[j].text,sourceObj.options[j].value);
				sourceObj.remove(j)
			}
		}

	}


	//检查所有
	function doSelAll(actionType){
		var idx, idy;
		var bIsOk ;
		switch(actionType){
		case 0://为左边的列表框
			var sourceObj = document.all("noselectuser");
			var destObj = document.all("selecteduser");
			break;
		case 1://为右边的列表框
			var sourceObj = document.all("selecteduser");
			var destObj = document.all("noselectuser");
			break;
		}
		if (sourceObj.options.length==0) return;
		for (idx =0 ; idx <sourceObj.options.length; idx++){
			bIsOk= false;
			for (idy=0;idy<=destObj.options.length-1 ; idy++ ){
				if (sourceObj.item(idx).value == destObj.item(idy).value){
					bIsOk = true;
					break;
				}
			}
			if (! bIsOk){
				var e = document.createElement("OPTION");
				e.value=sourceObj.item(idx).value;
				e.text=sourceObj.item(idx).text;
				destObj.options.add(e);
			}
		}
		for (idx= sourceObj.options.length-1 ; idx>=0 ; idx--){
			sourceObj.options.remove(idx)
		}
	}

	//改变语言时
	function onChangeLang(){
		var mylang = document.all("lang");
		var msg = document.all("msg");
		
		var bCHN = mylang[0].checked;
		var txt = msg.value;
		if (bCHN == true){
			msg.maxlength=70;
			if (txt.length>70){
				msg.value = txt.substring(0,msg.maxlength);
			}
			msg.style.ime="enabled";
		} else {
			msg.maxlength=160;
			msg.style.width="410px";
		}
		txt = msg.value;
		//showstate(txt.length,msg.maxlength);

	}

	//显示当前的状态(还可输入多少汉字)
	function showstate(number,maxlen){
		try{
			var keyMax = document.all("keyMax");
			if (maxlen-number<0)
				keyMax.value = 0;
			else
				keyMax.value =(maxlen- number) ;
		} catch(e){}
	}
	//不断检查
	function f_load(){

		var txt = document.all("msg").value;
		showstate(txt.length,document.all("msg").maxlength);
		setTimeout("f_load()",160);
		
	}
	function checkInputTextMaxLen(){
		var code=event.keyCode;
		var bCHN = document.all("lang")[0].checked;

		if(code==190 ||  code==46 || code==37 ||  code==39 ||  code==8 ||  code==40 || code==17 )
			return true;
		//alert(isAlphaNumeric(code) + " bCHN: " + bCHN)
		
		if(this.value.length>=this.lenValue) return false;
		
		
	}

	//发送前置检查
	function checkInput(){
		//得到日期

		var flag =false;
		try {
			flag = document.getElementById("bTimeSend").checked;
		} catch (e){
		}

		var sDate = document.all("sendYear").value + "-" +document.all("sendMonth").value+"-" + document.all("sendDay").value+ " " +document.all("sendHour").value + ":" +document.all("sendMinute").value +":00"
		var sMsg = "";
		//alert(sDate);
		var bIsOk = true;
		
		if (flag== true && isTime(sDate)== false){
			sMsg+="-- 定时日期输入有问题,请重新输入过\r";
			bIsOk = false;
		} else if (flag== true && isTime(sDate)== true){
			var syear =  document.all("sendYear").value;
			var smonth = ( document.all("sendMonth").value.length<2)?"0" + document.all("sendMonth").value : document.all("sendMonth").value;


			var sday =  ( document.all("sendDay").value.length<2)?"0" + document.all("sendDay").value : document.all("sendDay").value;
			var strDate = syear+"-"+smonth+"-"+sday;
			

			var curDate = new Date();
			var eDate= datetime2date(curDate);
			
			 var lngDate =date2Long(strDate);
			 var curlngDate = date2Long(eDate);

			 if (lngDate<curlngDate){
				 sMsg+="-- 定时日期比当前的日期小,请重新输入过\r";
				 bIsOk = false;
			 } else if (curlngDate==lngDate) {
				 var sMinutes =  ( document.all("sendMinute").length<2)?"0" + document.all("sendMinute").value : document.all("sendMinute").value;

				var sTime = document.all("sendHour").value *100 + +sMinutes ;
				sMinutes =  ( curDate.getMinutes()<10)?"0" +curDate.getMinutes() : curDate.getMinutes();
				var eTime = curDate.getHours() *100+ curDate.getMinutes();
				if(sTime<eTime){
					 sMsg+="-- 定时时间比当前的时间小,请重新输入过\r";
					 bIsOk = false;
				
				}
			 }

		}
		
		var mylang = document.all("lang");
		var msg = document.all("msg");
		
		var bCHN = mylang[0].checked;
		var txt = msg.value;
		if (txt==""){
			sMsg+="-- 短信内容,不允许为空\r";
			bIsOk = false;
		}
		//检查在英文短信有没有中文信息
		if (bCHN == false){
			if (isAlphaNumeric(txt) == false){
				
				sMsg+="-- 纯英文短消息,不允许输入汉字\r";
				bIsOk = false;
			}
		}
		var dObj = document.all("noselectuser");
		var sObj = document.all("selecteduser");
		//检查有没有输入或选择手机号码
		var blnSelect = false;
		if(sObj.options.length<1 && document.all("keyMobile").value==""){
			sMsg+="-- 请选择或输入要发送的人员\r";
			dObj.focus();
			bIsOk = false;
		}
		var strKeys = document.all("keyMobile").value;
		var aryKeys = strKeys.split(',');
		var countKeys =0;
		if (strKeys !="")
			 countKeys = aryKeys.length;
	

		
		var maxNumber = <bean:write name='SmsMgrForm' property='otherCount' filter='true' />
		
		var strMobiles = "";
		var curMobiles = countKeys;
		//alert("start=" +countKeys);
		for (var idx =0 ; idx<= sObj.options.length-1; idx++){
			strMobiles  = strMobiles + sObj.item(idx).value  + "," ;
			
		}
		curMobiles += sObj.options.length ;
		//alert (maxNumber + "  " + curMobiles);
		if (curMobiles >maxNumber){
			bIsOk = false;
			sMsg+="-- 您今天最多只能发送" +maxNumber + "条短信";
			if (maxNumber >0)
				sMsg +=",请重新选择相关人员!" ;
			else
				sMsg += ",不允许发送!" ;
		}
		if (bIsOk == false){
			alert(sMsg);
			return false;
		}
		var frmObj = document.forms[0];
		document.all("selectMobile").value = strMobiles;

		var strAction = frmObj.action;
		var pos = strAction.indexOf("?");
		if (pos>0){
			strAction = strAction(0,pos-1);
		}
		strAction+= "?action=doSendSms";
		frmObj.action = strAction;
		saving.style.visibility="visible";
		frmObj.submit();

	}

	function isSending(){
		
			var flag = false;
			flag =document.getElementById("bTimeSend").checked;
			
			if (flag==true ) {
				document.getElementById("sending").style.display = "block";
				//bWhere.innerHTML ="<font color='#F4FAFF'>隐藏信息查询条件 </font>"
			} else {
				document.getElementById("sending").style.display = "none";
				//bWhere.innerHTML ="<font color='#F4FAFF'>显示信息查询条件 </font>"
			}
		
		return true;

	}
//-->
</SCRIPT>
<body class="mailbody2" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<p>&nbsp;</p>
<html:form action="/dailyoffice/smsmgr/writesms" >
<table width="92%" border="0" align="center" cellpadding="2" cellspacing="1">
  <tr> 
  
    <td class="TableTitleText" colspan = "2">发短信 </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td class="t12">&nbsp;</td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
    <td class="InputLabelCell">您总共已发送短信<bean:write name='SmsMgrForm' property='sumCount' filter='true' />条 
      , 今天发送<bean:write name='SmsMgrForm' property='whereCount' filter='true' />条, 
      当前还可以发送<bean:write name='SmsMgrForm' property='otherCount' filter='true' />条!</td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
    <td class="t12"><hr size="1"></td>
  </tr>
  <td>&nbsp;</td>
  <td class="t12" align="left">
  <table width="445" border="0" align="left" cellpadding="2" cellspacing="1">
        <tr> 
        <td width="532" class="t12"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr class="InputLabelCell"> 
              <td width="51%">选择分组: 
                <select name="selectDept" onchange="javascript:onChangeDept();" style="width:200px" class="t12">
                  <option value="0" selected>所有人员</option>
                </select> </td>
              <td width="22%"><input name="radiobutton" type="radio" onclick="onChangeType('0')" value="radiobutton" checked>
                通讯簿</td>
              <td width="27%"><input type="radio" onclick="onChangeType('1')"  name="radiobutton" value="radiobutton">
                名片夹</td>
            </tr>
          </table></td>
      </tr>
      <tr> 
        <td> <table border="0" cellpadding="2" cellspacing="1">
            <tr align="center"> 
              <td> <select name="noselectuser" multiple class="t12" style="width:180px; height:120px;" ondblclick="doSelOne(0)">
                </select> </td>
              <td valign="middle"><table border="0" cellpadding="1" cellspacing="1">
                  <tr align="center"> 
                    <td valign="middle"> <input type="button" class="button" name="selectall" onclick="doSelAll(0)" value="&gt;&gt;" style="width:30px;"></td>
                  </tr>
                  <tr> 
                    <td align="center"> <input type="button" class="button"  name="selectone" onclick="doSelOne(0)"  value="&gt;" style="width:30px;"></td>
                  </tr>
                  <tr> 
                    <td align="center"> <input type="button" class="button" name="unselectone" onclick="doSelOne(1)" value="&lt;" style="width:30px;"></td>
                  </tr>
                  <tr> 
                    <td align="center"> <input type="button" class="button" name="unselectall" value="&lt;&lt;" onclick="doSelAll(1)" style="width:30px;"></td>
                  </tr>
                </table></td>
              <td> <select name="selecteduser" multiple ondblclick="doSelOne(1)" class="t12" style="width:180px; height:120px;">
                </select></td>
            </tr>
          </table></td>
      </tr>
      <tr> 
        <td class="InputLabelCell">请输入手机号码(如输入多个请用逗号隔开):<br> <html:text property="keyMobile" styleClass="input" style="width:410px;"  onlytype="mobiles"  /> 
          <html:hidden property="selectMobile"/> </tr>
      <tr> 
        <td class="InputLabelCell"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr class="InputLabelCell"> 
              <td width="48%"  class="t12" >请输入短信内容(还可输入<B><INPUT  TYPE="text" NAME="keyMax" value="" readonly style="width:30px" >)</td>
              <td width="27%"><input name="lang" type="radio" onclick="onChangeLang()" value="1" checked>含中文(70个)</td><td width="27%"><input type="radio" onclick="onChangeLang()"  name="lang" value="0">纯英文(160个)</td>
            </tr>
          </table></td>
      </tr>
      <tr> 
        <td><html:textarea property="msg" 
styleClass="bk" style="width:410px" maxlength="70" rows="4" /></td>
      </tr>
      <logic:equal name="SmsMgrForm" property="capVO.acl" value="1"> 
      <tr> 
        <td class="InputLabelCell"> <input type="checkbox" name="bTimeSend" onclick="isSending()" value="1" >
          定时发送(不选此项可立即发送) </td>
      </tr>
      </logic:equal> 
      <tbody id="sending" style="display:none">
        <tr> 
          <td align="center"> <html:text property="sendYear" styleClass="input" style="width:50px"  onlytype="int"  maxlength="4"/> 
            年 <html:text property="sendMonth" styleClass="input" style="width:30px"  onlytype="int" maxlength="2" /> 
            月 <html:text property="sendDay" styleClass="input" style="width:30px"  onlytype="int" maxlength="2"  /> 
            日 <html:text property="sendHour" styleClass="input" style="width:30px"  onlytype="int" maxlength="2"  /> 
            时 <html:text property="sendMinute" styleClass="input" style="width:30px"  onlytype="int"  maxlength="2" /> 
            分 </td>
        </tr>
      </tbody>
    </table></td>
  </tr>
  <tr> 
    <td rowspan="3">&nbsp;</td>
    <td><hr size="1"></td>
  </tr>
  <tr> 
    <td align="left">
	<table width="80%">
	<tr >
	<td align="center" height="20">	
			  
	<goa:button property="save" value="发送" styleClass="button"   onclick="return checkInput()"  operation="SJDX.OPT" />
	
	</td>
	</tr>
	</table>
	</td>
  </tr>
  <tr> 
    <td><hr size="1"></td>
  </tr>
</table>
<div id="saving" style="position:absolute; top:420; left:20; z-index:10; visibility:hidden">
	<table WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
	<tr>
	<td width="30%"></td>
	<td bgcolor="0">
	<table WIDTH="100%" height="70" BORDER="0" CELLSPACING="2" CELLPADDING="0">
	<tr><td bgcolor="#eeeeee" align="center" class="f14">&nbsp;&nbsp;正在发送短信, 请稍候...&nbsp;&nbsp;</td></tr>
	</table>
	</td>
	<td width="30%"></td>
	</tr>
	</table>
</div>
</html:form>
<SCRIPT LANGUAGE="JavaScript">
<!--
var curtype= "0";
var aryDept=new Array();
var aryUser=new Array();
		<%  //读出userIDList的资料,然后赋给javaScript中的一个数组变量当中 
	    ArrayList aryUser = (ArrayList)request.getAttribute("aryUser"); 
		ArrayList aryAddress = (ArrayList)request.getAttribute("aryAddress");
		ArrayList aryDept = (ArrayList)request.getAttribute("aryDept");
		ArrayList aryDir = (ArrayList)request.getAttribute("aryDir");
		int usersize = aryUser.size();
		int deptsize = aryDept.size();
	%>
	    var lngLength =<%= (aryUser.size() +aryAddress.size()) %> ; //得到总记录数
		var deptlength =<%= (aryDept.size() +aryDir.size()) %> ; //得到总记录数
	<%
		for (int idx = 0; idx< aryUser.size(); idx++){
			SmsUserVO uVo = new SmsUserVO();
			uVo= (SmsUserVO)aryUser.get(idx);
			out.println ("      aryUser[" + idx + "] = new Array();"); // 重新定义数组")
			//得到用户名称
			out.println ("      aryUser[" + idx + "][0] = \"" + uVo.getName() +"\";"); 
			//得到手机号
			out.println ("      aryUser[" + idx + "][1] = \"" +  uVo.getMobile() +"\";");
			//得到部门代号
			out.println ("      aryUser[" + idx + "][2] = \"" +  uVo.getDepts() +"\";");
			//得到类型
			out.println ("      aryUser[" + idx + "][3] = \"0\";");
			uVo = null; //回收垃圾箱
		}

		 //读出userIDList的资料,然后赋给javaScript中的一个数组变量当中 
		 for (int idx = 0; idx< aryAddress.size(); idx++){
			CardcaseVO cVo=new CardcaseVO();
			cVo= (CardcaseVO)aryAddress.get(idx);

			out.println ("      aryUser[" + (idx +usersize) + "] = new Array();"); // 重新定义数组")
			//得到用户名称
			out.println ("      aryUser[" + (idx +usersize) + "][0] = \"" + cVo.getCustomer() +"\";"); 
			//得到手机号
			out.println ("      aryUser[" + (idx +usersize) + "][1] = \"" +  cVo.getMobile() +"\";");
			//得到部门代号
			out.println ("      aryUser[" + (idx +usersize) + "][2] = \"" +  cVo.getType_code()+"\";");
			//得到类型
			out.println ("      aryUser[" + (idx +usersize) + "][3] = \"1\";");
			cVo = null; //回收垃圾箱
		}
		%>
	 
	<%    
		Iterator tempIt=(Iterator)aryDept.iterator();
		DepartmentStructure  deptstruct=null;
		int row = 0;
		while(tempIt.hasNext()){
			 deptstruct=new DepartmentStructure();
			 deptstruct = 	(DepartmentStructure)tempIt.next();
			 Department deptVo = deptstruct.getDepartment();
			
			 out.println ("      aryDept[" + row + "] = new Array();"); // 重新定义数组")
			 out.println ("      aryDept[" + row + "][0] = \"" + deptVo.getId() +"\";");
			 out.println ("      aryDept[" + row + "][1] = \"" + deptVo.getName() +"\";");
			 out.println ("      aryDept[" + row + "][2] = \"0\";");
			out.println ("      aryDept[" + row + "][3] = \""+deptstruct.getLayerNumber()+"\";");
			++row;
		}


		for (int idx = 0; idx< aryDir.size(); idx++){
			CardcaseTypeVO typeVo= (CardcaseTypeVO)aryDir.get(idx);
			out.println ("      aryDept[" +  (idx +deptsize) + "] = new Array();"); // 重新定义数组")
			//得到用户名称
			out.println ("      aryDept[" + (idx +deptsize) + "][0] = \"" + typeVo.getType_code() +"\";"); 
			//得到手机号
			out.println ("      aryDept[" + (idx +deptsize) + "][1] = \"" +  typeVo.getType_name() +"\";");
			//得到部门代号
					//得到类型
			out.println ("      aryDept[" + (idx +deptsize) + "][2] = \"1\";");
			out.println ("      aryDept[" + idx + "][3] = \"1\";");
			typeVo = null; //回收垃圾箱
		}%>
 var isRe = false;
 var strMbno = "";

		<%
 String strRe =(String) request.getParameter("isRe");
 String strMbno =(String) request.getParameter("mbno");
 if ("true".equals(strRe)){ %>
		isRe= true;
    	strMbno = "<%=strMbno%>";
<% }%>
	onChangeType("0");
	toRe(isRe)
	f_load();
	//-->
</SCRIPT>

