<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%@ page import="com.gever.struts.Constant"%>
<%
String context =request.getContextPath();
%>

<script language=javascript src='/goas/adminbsnmng/message/chromeless_30.js'></script>
<html>
<title>�ֻ���Ȩ�޹���</title>
<head>
<SCRIPT LANGUAGE="JavaScript">
<!--
function checkSelect(){
	var count = 0;
	var length = 0;
	try{
		 length = document.forms[0].fid.length;
	} catch(e){
		return false;
	}
	if (isNaN(length))	{
		try{
			if (document.forms[0].fid.checked)	{
				++count;
			}
		}catch(e){}
	}

	for(var i=0;i<document.forms[0].fid.length;i++){
		if(document.forms[0].fid[i].checked)
			count++;
	}
	if(count==0){
				return false;
	} else
		return true;

}


//ѡ��
function selectAllChk(obj){
	var count=0;
	var isSelect = obj.checked
	var length  = 0;
	try{
		length = document.forms[0].fid.length;
	} catch (e){return false;}

	if (isNaN(length))	{
		try{
		document.forms[0].fid.checked=isSelect;
		}catch(e){}
	}
	//alert(length + obj.checked);
	if (length ==0)	{
		return false;
	}

	if (length == 1)	{
		document.forms[0].fid.checked=isSelect;
	} else {
		for(var i=0;i<document.forms[0].fid.length;i++){
			document.forms[0].fid[i].checked=isSelect;
		}

	}
	checkTiming(isSelect);
}

function checkTiming(isCheck){
	var frmObj = document.forms[0];
	var elObj,intlocal;
	var iCount = 0;
	var strSelectd ="";
	for(var i=0;i<frmObj.elements.length;i++){
		elObj = frmObj.elements[i];
		strObjName = elObj.name;
		//alert(strObjName);
		intlocal= strObjName.indexOf("timing");
		if (intlocal>=0){

				elObj.disabled= !isCheck;

		}
	}
	return (iCount)

}
function checkOne(obj){

	var td=obj.parentElement;
	var tr=td.parentElement;
	//alert(tr.tagName)
	var cell=tr.cells[4];
	var chk=cell.children[0];
	chk.disabled=!obj.checked;
}

function checkInput(){
	var bRet =true;
	var frmObj = document.forms[0];
	if (frmObj.elements["defaultSize"].value==""){
		bRet = false;
	}
	if(isDecimal(frmObj.elements["defaultSize"].value)==false){
		bRet = false;
	}
	if(frmObj.elements["defaultSize"].value>10000 || frmObj.elements["defaultSize"].value<=0){
		bRet = false;
	}
	if (bRet ==false)
		alert("��������Ӧ��0 - 10000֮��");
	return bRet;
}
function doSave(){
	var bRet =false;
	if( !checkInput())
		return false;
	bCheck = checkSelect();
	if (bCheck<=false) {
		alert('��û��ѡ���κ���Ա��');
		return false;
	}
	var anser = confirm("��ȷ��Ҫ���õ�ǰѡ����ֻ���������������");
	if (anser == false)
		return false;
	document.forms[0].action = document.forms[0].action + "?action=doSave";
	document.forms[0].submit();

}
function doDefault(){
	var bRet =false;
	if( !checkInput())
		return false;
	var anser = confirm("�ò������������е��ֻ���������Ϊ��ǰ���õĴ�С����ȷ����");
	if (anser == false)
		return false;
	document.forms[0].action = document.forms[0].action + "?action=doDefault";
	document.forms[0].submit();

}
//-->
</SCRIPT>


</head>
<body class="bodybg">

<html:form action="/dailyoffice/smsmgr/listsmsacl">

<table class="tableframe" width="98%" align="left" border="0" cellpadding="0" cellspacing="0">
  <tr align="center"  >
    <td height="20" colspan="5" align=center class="TableTitleText">�ֻ�������Ա�б�</td>
  </tr>
   <tr align="center"  >
    <td height="20" colspan="5" align=center class="TableTitleText">&nbsp;</td>
  </tr>
  <tr align="right"  > 
    <td height="20" colspan="7" class="f12"> <nobr>����<html:text styleClass="input2"  name="SMSCapacityForm" property="findName" /> <goa:submit  styleClass="button" property="delete" value="��ѯ"   operation="DXGL.VIEW"/>
	��������(��)<html:text styleClass="input2" name="SMSCapacityForm" onlytype="int" property="defaultSize" size="8" maxlength="8"/>&nbsp;
	  <goa:button  styleClass="button" property="delete" value="���������˶�������Ϊ��ֵ"  onclick="return doDefault()" operation="DXGL.OPT"/>
	   <% String temp="doUrlCenter('"+context+"/dailyoffice/smsmgr/modcorp.do',450,300)";%>
<goa:button  styleClass="button" property="delete" value="���ö�����ʾ��"  onclick="<%=temp%>" operation="DXGL.OPT"/>

&nbsp;
 <goa:button  styleClass="button" property="delete" value="����"  onclick="return doSave()" operation="DXGL.OPT"/>
	&nbsp;

	
      <b><nobr></nobr></b> </td>
  </tr>
  <tr height="16" class="Listtitle"> 
    <td  width="5%" align=center class="listcellTitle"> <input type="checkbox" name="chkAll" id="chkAll" onclick="javascript:selectAllChk(this)">
      </td>
    <td  width="15%" class="listcellTitle"><div align="right">���&nbsp;</div></td>
    <td  width="25%" id=".username" class="Listtitle" onclick="onchangeOrderBy(this)"><div align="left">����</div></td>
	<td  width="25%" id=".status" class="Listtitle" onclick="onchangeOrderBy(this)"><div align="left">����״̬</div></td>
    <td  width="10%" id=".acl" class="Listtitle" onclick="onchangeOrderBy(this)"><div align="center">��ʱ����</div></td>
    <td  width="20%" id=".numbers" class="Listtitle" onclick="onchangeOrderBy(this)"> <div align="right">��������</div></td>
  </tr>
     <logic:iterate id="ps" name="SMSCapacityForm" property="pageControl.data" type="com.gever.goa.dailyoffice.smsmgr.vo.SMSCapacityVO" indexId="index">


  <tr class="t12" onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);"> 
    <td  align=center nowrap class="listcellrow"> 	<input type='checkbox' name='fid'  value='<goa:write name="ps" property="userid" filter="true"/>' onclick="checkOne(this)">
	</td>
	<td  align=right nowrap class="listcellrow"> <%=((Integer)pageContext.getAttribute("index")).intValue()+1%>&nbsp;</TD>
    <td   align=left nowrap class="listcellrow"> <goa:write name='ps' property='username' filter='true' />&nbsp;</TD> <td  width="25%" align=left nowrap class="listcellrow"> 
	<logic:equal name="ps" property="status" value="1">�Ѽ���</logic:equal><logic:notEqual name="ps" property="status" value="1">������</logic:notEqual>&nbsp;</TD>
    <td  align=center nowrap class="listcellrow"> <logic:equal name="ps" property="acl" value="1"> 
      <input type="checkbox" name ='timing<goa:write name="ps" property="userid" filter="true"/>' value="1" checked 	disabled>
      </logic:equal> <logic:notEqual name="ps" property="acl" value="1"> 
      <input type="checkbox" name='timing<goa:write name="ps" property="userid" filter="true"/>' disabled  value="1">
      </logic:notEqual> </td>
    <td  nowrap align=right class="listcellrow4"><goa:write name='ps' property='numbers' filter='true' />&nbsp;</td>
  </tr>
  </logic:iterate> </td> </tr> 
  <tr> 
  <tr align="right"> 
	  <td colspan="7" align="center" class="f12">
	  <html:hidden name="SMSCapacityForm" property="nodeid"/>
	  <html:hidden name="SMSCapacityForm" property="orderType"/>
		<html:hidden name="SMSCapacityForm" property="orderFld"/>
		<goa:pagelink name="SMSCapacityForm" property="pageControl" />  
	  </td>
  
  </tr>
</table>
</html:form>
</body>
</html>
