<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ page import="java.util.*"%>
<%@ page import="com.gever.struts.Constant"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>


<%
  ArrayList fileList = null;
  String userId =  (String)session.getAttribute(Constant.USER_ID);
  int listLength = 0;
  int temp = 0;
  int i=1;
  fileList = (ArrayList)session.getAttribute("fileList");
  if(fileList.size() != 0){
	  temp = fileList.size()%3;
	  if(temp != 0){
		  listLength = fileList.size() + 3-temp;
	  }else {
		  listLength = fileList.size();
	  }  
}
/*for(;j<fileList.size();j++){
	out.println(((HashMap)fileList.get(j)).get("sName"));
}
out.println(listLength);*/
%>
<SCRIPT LANGUAGE="JavaScript">
<!--
//����
function doExport() {
	var submit = true;
	var frmObj = document.forms[1];	
	frmObj.action = frmObj.action + "?action=toView" ;
	if(!confirm("�Ƿ�Ҫ������ǰ�ĵ�ַ���ݣ�")){
		submit = false;
	}
	if(submit == true) {
		frmObj.submit();
	}
}
//����
function doImport() {
	var submit = true;
	var frmObj = document.forms[0];	
    if(document.forms[0].elements["theFile"].value == "") {
		alert("û��ѡ���ļ������벻�ɹ���");
		submit = false;
	}else {
		var tempStr = document.forms[0].elements["theFile"].value;
		if(tempStr.lastIndexOf(".csv") == -1 
			&& tempStr.lastIndexOf(".txt") == -1 ){
			alert("�ļ����ʹ��󣬵��벻�ɹ���");
			submit = false;
		}
	}
	if(submit == true) {
		frmObj.action = frmObj.action + "?action=add" ;
		//alert(frmObj.action);
		frmObj.submit();
	}else if(submit == false) {
		return false;
	}
}

//ɾ��
function doDelete(){

	var count = 0;
	var length = 0;
	try{
		 length = document.forms[1].fid.length;
	} catch(e){
		alert("û���κμ�¼��");
		return false;
	}
	if (isNaN(length))	{
		try{
			if (document.forms[1].fid.checked)	{
				++count;
			}
		}catch(e){}
	}

	for(var i=0;i<document.forms[1].fid.length;i++){
		if(document.forms[1].fid[i].checked)
			count++;
	}
	if(count==0){
		alert("��û��ѡ���κμ�¼��");
		return false;
	}
	if(confirm("ȷ��Ҫɾ����¼��") == false){
		return false;
	}


	var mForm = document.forms[1];
	var action =  mForm.action;
	action =  addPara(action,"action=delete" );
	mForm.action = action
	mForm.submit();
}
//-->
</SCRIPT>
<body class="bodybg">

<p>&nbsp;</p>
<table border="0" cellpadding="0" cellspacing="0" width="92%" align="center">
<tr>
    <td width="36"><img src="../images/ico_drdc.gif"></td>
<td colspan="2">
	<table width="92%" border=0 cellspacing=0 cellpadding=0>
	<tr>
	<td height=14 align="left">
		<span class="TableTitleText">�����ַ����</span>
	</td>	
	</TR>
	<tr>
        <td><hr size="1"></td>
	</tr>
    </TABLE>	
</td>
</tr>
<tr >
<td rowspan="2" >&nbsp;</td>
<td colspan="2"  >
<table cellpadding="0" width="92%">
<tr>
<td>
<div class="f12">
	����ǰ����ȷ�����ĵ����ļ�Ϊoutlook������.csv����.txt�ļ���ʽ,���ұ�֤�����ļ������ҽ�������16������ݣ�<br>
	<br>
	<font color="#006633">����,�����ʼ���ַ,��ͥ���ڽֵ�,��ͥ���ڳ���,��ͥ���ڵص���������,
	                   ��ͥ����ʡ/������,��ͥ���ڹ���/����,��ͥ�绰,��˾���ڽֵ�,��˾���ڳ���,
	                   ��˾���ڵص���������,��˾����ʡ/������,��˾���ڹ���/����,����绰,��˾,ְ��</font><br>
	<br>
	����16��ΪOutLook5����ʱ��Ĭ����Ŀ��<br>	<br>

	��ѡ��Ҫ����ĵ�ַ�ļ�:
	 </div>
	 </td>
	 </tr>
	 </table>
</td>
</tr>
<tr>
<html:form action="/dailyoffice/mailmgr/mailimportandexport/import"  enctype="multipart/form-data">
  <td align="left" width="85%" > 
	<input type="file"   name="theFile" style="width:99%" class="input"/>
	</td>
	<td width="15%" >
	<html:button styleClass="button" property="import" onclick="doImport()"  value="����"/>
  </td>
  <td align="left" >
  </td>
  </html:form>
</tr>
</table>

<br><br><br>
<html:form action="/dailyoffice/mailmgr/mailimportandexport/export" method="post">
<table border="0" cellpadding="0" cellspacing="0" width="92%" align="center">
  <tr>
    <td width="36"><img src="../images/ico_drdc.gif"></td>
    <td>
    <table width="92%" border=0 cellspacing=0 cellpadding=0 >
			<tr>
			<td height=14 align="left"><span class="TableTitleText">�����ַ����</span>
			</td>			
			</TR>
			<tr>
              <td><hr size="1"></td>
            </tr>
	</TABLE>
   </td>
  </tr>
  <tr>
  <td>&nbsp;</td>
  <td>
  <table width="92%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td align="right" >
		<html:button property="export" styleClass="button" value="����" onclick="doExport()"/>
		<html:button property="delete" styleClass="button" value="ɾ��"  onclick="doDelete()"/>    
        </td>
    </tr>
	<tr height="8"></tr>
</table>   

      <table width="92%" border="0"	cellpadding="2" cellspacing="0">
        <tr height="20" class="mailtdbg2"> 
          <td  class="Listtitle">���</td>
          <td  class="Listtitle">����ʱ��</td>
          <td  class="Listtitle">���</td>
          <td  class="Listtitle">����ʱ��</td>
          <td  class="Listtitle">���</td>
          <td  class="Listtitle">����ʱ��</td>
        </tr>
		<tr height="20" bgcolor="#FFFFFF" onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
		<%
			for(;i<=fileList.size();i++){
				String fid=(String)((HashMap)fileList.get(i-1)).get("sFullName");
		 %><td class="listcellrow"><input type="checkbox" name="fid" value="<%=fid%>"><%=i%></td>
		<td class="listcellrow">
		<a href='<%="<%=context%>/download/"+userId+"/"+(String)((HashMap)fileList.get(i-1)).get("sFullName")%>' target="_blank"><%=(String)((HashMap)fileList.get(i-1)).get("sName")%></a></td>
		<%
		if(i%3==0){
			out.println("</tr><tr height='20' bgcolor='#FFFFFF' onmouseover='javascript:changeBgColorOnMouseOver(this);' onmouseout='javascript:changeBgColorOnMouseOut(this);'>");
		}
			 }
		 %>
		    <%int k = i-1;
                while(k % 3 != 0 ) {
                  k++;%>
          <td class="listcellrow">&nbsp;</td>
          <td class="listcellrow">&nbsp;</td>
          <%}%>
		</tr>
      </table>
  </td>
  </tr>
</table>
</html:form>
</BODY>

