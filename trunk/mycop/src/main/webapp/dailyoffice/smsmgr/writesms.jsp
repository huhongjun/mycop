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

<title>����</title>


</head>
<SCRIPT LANGUAGE="JavaScript">
<!--
    //�ı䲿��ʱ
	function onChangeDept(){
		readData(curtype);
	}
	//�����Ÿı�ʱ,�������ʹ���û�
	function readData(type){
		
		var userObj =  document.all("noselectuser");
		var deptObj =  document.all("selectDept");
		var destObj =  document.all("selecteduser");
		//alert(deptObj.value + " ilength ->" +lngLength);
		//���������Ա����
		for (var idx= userObj.options.length-1 ; idx>=0 ; idx--){
			userObj.remove(idx)
		}
		var iLength = 0;
		var bIsOk = false;
		//����Ա������������
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

	//�Ƚϲ���
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

	//���ı��ַ������Ƭ�е�ʱ��
	function onChangeType(type){
		//alert(deptlength);
		var deptObj =  document.all("selectDept");

		//alert(deptObj.value + " ilength ->" +lngLength);
		//���������Ա����
		for (var idx= deptObj.options.length-1 ; idx>=0 ; idx--){
			deptObj.remove(idx)
		}
		var iLength = 0;
		deptObj.options[0] = new Option("������Ա","0");
		//����Ա������������
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

	//�ǻظ��Ķ���ʱ
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

	//��鵥������Ա
	function doSelOne(actionType){
		var idx, idy;
		var bIsOk ;

		switch(actionType){
		case 0://Ϊ��ߵ��б��
			var sourceObj = document.all("noselectuser");
			var destObj = document.all("selecteduser");
			break;
		case 1://Ϊ�ұߵ��б��
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


	//�������
	function doSelAll(actionType){
		var idx, idy;
		var bIsOk ;
		switch(actionType){
		case 0://Ϊ��ߵ��б��
			var sourceObj = document.all("noselectuser");
			var destObj = document.all("selecteduser");
			break;
		case 1://Ϊ�ұߵ��б��
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

	//�ı�����ʱ
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

	//��ʾ��ǰ��״̬(����������ٺ���)
	function showstate(number,maxlen){
		try{
			var keyMax = document.all("keyMax");
			if (maxlen-number<0)
				keyMax.value = 0;
			else
				keyMax.value =(maxlen- number) ;
		} catch(e){}
	}
	//���ϼ��
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

	//����ǰ�ü��
	function checkInput(){
		//�õ�����

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
			sMsg+="-- ��ʱ��������������,�����������\r";
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
				 sMsg+="-- ��ʱ���ڱȵ�ǰ������С,�����������\r";
				 bIsOk = false;
			 } else if (curlngDate==lngDate) {
				 var sMinutes =  ( document.all("sendMinute").length<2)?"0" + document.all("sendMinute").value : document.all("sendMinute").value;

				var sTime = document.all("sendHour").value *100 + +sMinutes ;
				sMinutes =  ( curDate.getMinutes()<10)?"0" +curDate.getMinutes() : curDate.getMinutes();
				var eTime = curDate.getHours() *100+ curDate.getMinutes();
				if(sTime<eTime){
					 sMsg+="-- ��ʱʱ��ȵ�ǰ��ʱ��С,�����������\r";
					 bIsOk = false;
				
				}
			 }

		}
		
		var mylang = document.all("lang");
		var msg = document.all("msg");
		
		var bCHN = mylang[0].checked;
		var txt = msg.value;
		if (txt==""){
			sMsg+="-- ��������,������Ϊ��\r";
			bIsOk = false;
		}
		//�����Ӣ�Ķ�����û��������Ϣ
		if (bCHN == false){
			if (isAlphaNumeric(txt) == false){
				
				sMsg+="-- ��Ӣ�Ķ���Ϣ,���������뺺��\r";
				bIsOk = false;
			}
		}
		var dObj = document.all("noselectuser");
		var sObj = document.all("selecteduser");
		//�����û�������ѡ���ֻ�����
		var blnSelect = false;
		if(sObj.options.length<1 && document.all("keyMobile").value==""){
			sMsg+="-- ��ѡ�������Ҫ���͵���Ա\r";
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
			sMsg+="-- ���������ֻ�ܷ���" +maxNumber + "������";
			if (maxNumber >0)
				sMsg +=",������ѡ�������Ա!" ;
			else
				sMsg += ",��������!" ;
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
				//bWhere.innerHTML ="<font color='#F4FAFF'>������Ϣ��ѯ���� </font>"
			} else {
				document.getElementById("sending").style.display = "none";
				//bWhere.innerHTML ="<font color='#F4FAFF'>��ʾ��Ϣ��ѯ���� </font>"
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
  
    <td class="TableTitleText" colspan = "2">������ </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td class="t12">&nbsp;</td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
    <td class="InputLabelCell">���ܹ��ѷ��Ͷ���<bean:write name='SmsMgrForm' property='sumCount' filter='true' />�� 
      , ���췢��<bean:write name='SmsMgrForm' property='whereCount' filter='true' />��, 
      ��ǰ�����Է���<bean:write name='SmsMgrForm' property='otherCount' filter='true' />��!</td>
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
              <td width="51%">ѡ�����: 
                <select name="selectDept" onchange="javascript:onChangeDept();" style="width:200px" class="t12">
                  <option value="0" selected>������Ա</option>
                </select> </td>
              <td width="22%"><input name="radiobutton" type="radio" onclick="onChangeType('0')" value="radiobutton" checked>
                ͨѶ��</td>
              <td width="27%"><input type="radio" onclick="onChangeType('1')"  name="radiobutton" value="radiobutton">
                ��Ƭ��</td>
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
        <td class="InputLabelCell">�������ֻ�����(�����������ö��Ÿ���):<br> <html:text property="keyMobile" styleClass="input" style="width:410px;"  onlytype="mobiles"  /> 
          <html:hidden property="selectMobile"/> </tr>
      <tr> 
        <td class="InputLabelCell"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr class="InputLabelCell"> 
              <td width="48%"  class="t12" >�������������(��������<B><INPUT  TYPE="text" NAME="keyMax" value="" readonly style="width:30px" >)</td>
              <td width="27%"><input name="lang" type="radio" onclick="onChangeLang()" value="1" checked>������(70��)</td><td width="27%"><input type="radio" onclick="onChangeLang()"  name="lang" value="0">��Ӣ��(160��)</td>
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
          ��ʱ����(��ѡ�������������) </td>
      </tr>
      </logic:equal> 
      <tbody id="sending" style="display:none">
        <tr> 
          <td align="center"> <html:text property="sendYear" styleClass="input" style="width:50px"  onlytype="int"  maxlength="4"/> 
            �� <html:text property="sendMonth" styleClass="input" style="width:30px"  onlytype="int" maxlength="2" /> 
            �� <html:text property="sendDay" styleClass="input" style="width:30px"  onlytype="int" maxlength="2"  /> 
            �� <html:text property="sendHour" styleClass="input" style="width:30px"  onlytype="int" maxlength="2"  /> 
            ʱ <html:text property="sendMinute" styleClass="input" style="width:30px"  onlytype="int"  maxlength="2" /> 
            �� </td>
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
			  
	<goa:button property="save" value="����" styleClass="button"   onclick="return checkInput()"  operation="SJDX.OPT" />
	
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
	<tr><td bgcolor="#eeeeee" align="center" class="f14">&nbsp;&nbsp;���ڷ��Ͷ���, ���Ժ�...&nbsp;&nbsp;</td></tr>
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
		<%  //����userIDList������,Ȼ�󸳸�javaScript�е�һ������������� 
	    ArrayList aryUser = (ArrayList)request.getAttribute("aryUser"); 
		ArrayList aryAddress = (ArrayList)request.getAttribute("aryAddress");
		ArrayList aryDept = (ArrayList)request.getAttribute("aryDept");
		ArrayList aryDir = (ArrayList)request.getAttribute("aryDir");
		int usersize = aryUser.size();
		int deptsize = aryDept.size();
	%>
	    var lngLength =<%= (aryUser.size() +aryAddress.size()) %> ; //�õ��ܼ�¼��
		var deptlength =<%= (aryDept.size() +aryDir.size()) %> ; //�õ��ܼ�¼��
	<%
		for (int idx = 0; idx< aryUser.size(); idx++){
			SmsUserVO uVo = new SmsUserVO();
			uVo= (SmsUserVO)aryUser.get(idx);
			out.println ("      aryUser[" + idx + "] = new Array();"); // ���¶�������")
			//�õ��û�����
			out.println ("      aryUser[" + idx + "][0] = \"" + uVo.getName() +"\";"); 
			//�õ��ֻ���
			out.println ("      aryUser[" + idx + "][1] = \"" +  uVo.getMobile() +"\";");
			//�õ����Ŵ���
			out.println ("      aryUser[" + idx + "][2] = \"" +  uVo.getDepts() +"\";");
			//�õ�����
			out.println ("      aryUser[" + idx + "][3] = \"0\";");
			uVo = null; //����������
		}

		 //����userIDList������,Ȼ�󸳸�javaScript�е�һ������������� 
		 for (int idx = 0; idx< aryAddress.size(); idx++){
			CardcaseVO cVo=new CardcaseVO();
			cVo= (CardcaseVO)aryAddress.get(idx);

			out.println ("      aryUser[" + (idx +usersize) + "] = new Array();"); // ���¶�������")
			//�õ��û�����
			out.println ("      aryUser[" + (idx +usersize) + "][0] = \"" + cVo.getCustomer() +"\";"); 
			//�õ��ֻ���
			out.println ("      aryUser[" + (idx +usersize) + "][1] = \"" +  cVo.getMobile() +"\";");
			//�õ����Ŵ���
			out.println ("      aryUser[" + (idx +usersize) + "][2] = \"" +  cVo.getType_code()+"\";");
			//�õ�����
			out.println ("      aryUser[" + (idx +usersize) + "][3] = \"1\";");
			cVo = null; //����������
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
			
			 out.println ("      aryDept[" + row + "] = new Array();"); // ���¶�������")
			 out.println ("      aryDept[" + row + "][0] = \"" + deptVo.getId() +"\";");
			 out.println ("      aryDept[" + row + "][1] = \"" + deptVo.getName() +"\";");
			 out.println ("      aryDept[" + row + "][2] = \"0\";");
			out.println ("      aryDept[" + row + "][3] = \""+deptstruct.getLayerNumber()+"\";");
			++row;
		}


		for (int idx = 0; idx< aryDir.size(); idx++){
			CardcaseTypeVO typeVo= (CardcaseTypeVO)aryDir.get(idx);
			out.println ("      aryDept[" +  (idx +deptsize) + "] = new Array();"); // ���¶�������")
			//�õ��û�����
			out.println ("      aryDept[" + (idx +deptsize) + "][0] = \"" + typeVo.getType_code() +"\";"); 
			//�õ��ֻ���
			out.println ("      aryDept[" + (idx +deptsize) + "][1] = \"" +  typeVo.getType_name() +"\";");
			//�õ����Ŵ���
					//�õ�����
			out.println ("      aryDept[" + (idx +deptsize) + "][2] = \"1\";");
			out.println ("      aryDept[" + idx + "][3] = \"1\";");
			typeVo = null; //����������
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

