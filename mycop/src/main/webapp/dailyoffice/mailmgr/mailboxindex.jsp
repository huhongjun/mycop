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
//导出
function doExport() {
	var submit = true;
	var frmObj = document.forms[1];	
	frmObj.action = frmObj.action + "?action=toView" ;
	if(!confirm("是否要导出当前的地址数据？")){
		submit = false;
	}
	if(submit == true) {
		frmObj.submit();
	}
}
//导入
function doImport() {
	var submit = true;
	var frmObj = document.forms[0];	
    if(document.forms[0].elements["theFile"].value == "") {
		alert("没有选择文件，导入不成功！");
		submit = false;
	}else {
		var tempStr = document.forms[0].elements["theFile"].value;
		if(tempStr.lastIndexOf(".csv") == -1 
			&& tempStr.lastIndexOf(".txt") == -1 ){
			alert("文件类型错误，导入不成功！");
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

//删除
function doDelete(){

	var count = 0;
	var length = 0;
	try{
		 length = document.forms[1].fid.length;
	} catch(e){
		alert("没有任何记录！");
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
		alert("您没有选择任何记录！");
		return false;
	}
	if(confirm("确定要删除记录吗？") == false){
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
		<span class="TableTitleText">邮箱地址导入</span>
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
	导入前，请确认您的导入文件为outlook导出的.csv或者.txt文件格式,并且保证导入文件中有且仅有如下16项的内容：<br>
	<br>
	<font color="#006633">姓名,电子邮件地址,家庭所在街道,家庭所在城市,家庭所在地的邮政编码,
	                   家庭所在省/自治区,家庭所在国家/地区,家庭电话,公司所在街道,公司所在城市,
	                   公司所在地的邮政编码,公司所在省/自治区,公司所在国家/地区,商务电话,公司,职务</font><br>
	<br>
	以上16项为OutLook5导出时的默认项目。<br>	<br>

	请选择要导入的地址文件:
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
	<html:button styleClass="button" property="import" onclick="doImport()"  value="导入"/>
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
			<td height=14 align="left"><span class="TableTitleText">邮箱地址导出</span>
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
		<html:button property="export" styleClass="button" value="导出" onclick="doExport()"/>
		<html:button property="delete" styleClass="button" value="删除"  onclick="doDelete()"/>    
        </td>
    </tr>
	<tr height="8"></tr>
</table>   

      <table width="92%" border="0"	cellpadding="2" cellspacing="0">
        <tr height="20" class="mailtdbg2"> 
          <td  class="Listtitle">序号</td>
          <td  class="Listtitle">导出时间</td>
          <td  class="Listtitle">序号</td>
          <td  class="Listtitle">导出时间</td>
          <td  class="Listtitle">序号</td>
          <td  class="Listtitle">导出时间</td>
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

