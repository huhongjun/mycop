<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
%>

<SCRIPT LANGUAGE="JavaScript">
<!--
function setSBoard(topid){
	var url="sboard.do?topid="+topid;
	window.location=url;
}

function doDelete(){

	var count = 0;
	var length = 0;
	try{
		 length = document.forms[0].fid.length;
	} catch(e){
		alert("û���κμ�¼��");
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
		alert("��û��ѡ���κμ�¼��");
		return false;
	}
	if(confirm("�ò�������ɾ�����ڸ���̳�������¼���̳���������⣡\n��ȷ��Ҫɾ����¼��") == false){
		return false;
	}


	var mForm = document.forms[0];
	var action =  mForm.action;
	action =  addPara(action,"action=delete" );
	mForm.action = action
	disButton();
	mForm.submit();
}
//-->
</SCRIPT>
<html>
<body class="bodybg">
  <html:form action="/dailyoffice/bbs/mng/topboard" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="6" align="center" class="TableTitleText">һ����̳</td>
  </tr>
  <tr> 
    <td colspan="6" align="right">&nbsp;</td>
  </tr>
  <tr> 
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
		 
			<td align="right" class="f12">����:
            </td>
            <td>
                <html:text property="searchValue" maxlength="30" size="22" styleClass="input2">
                </html:text>
            </td>
            <td>
                <goa:button  property="search" styleClass="button" value="��ѯ" onclick="toUrl('topboard.do',false)" operation="LTWF.VIEW"/>
            </td>
          <td> <goa:button styleClass="button"  property="add" onclick="doAdd()" value="����" operation="LTWF.OPT"/>
          </td>
		   <td>
		
		   <goa:button styleClass="button"  property="copy" onclick="toUrl('edittopboard.do?actionType=modify',true)" value="�޸�" operation="LTWF.OPT"/>
          </td>
		   <td> <goa:button  styleClass="button" property="delete" value="ɾ��"  onclick="doDelete()" operation="LTWF.OPT"/>
		    <td> <goa:button  styleClass="button" property="delete" value="�鿴"  onclick="toUrl('viewtopboard.do',true)" operation="LTWF.VIEW"/>
        </tr>
      </table>
	  </td>
  </tr>
  <tr> 
    <td colspan="6">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
        <tr height="20">
            <td width="8%" class="Listtitle"><div align="center">
              <input alt="ѡ��" type="checkbox" name="selectall" onclick="selectAllChk(this)">
            </div></td>
      		<td width="22%" class="Listtitle" id=".tboard_name" onclick="onchangeOrderBy(this)"><div align="left">����</div></td>
            <td width="11%" class="Listtitle" id=".tboard_state" onclick="onchangeOrderBy(this)"><div align="left">״̬</div></td>
            <td width="46%"  class="Listtitle" id=".tboard_note" onclick="onchangeOrderBy(this)"><div align="left">��ע</div></td>
			<td  width="13%" class="Listtitle">������̳</td>
           		  
    </tr>
    <logic:iterate id="ps" name="TopBoardForm" property="pageControl.data" type="com.gever.goa.dailyoffice.bbs.vo.TopBoardVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('viewtopboard.do?fid=" +ps.getSerial_num()+"',false)"%>">
		<td align="center" class="listcellrow">
			<div align="center">
			  <input type='checkbox' name='fid' value='<bean:write name="ps" property="serial_num" filter="true"/>' >    		  
		      </div></td>
		<td class="listcellrow"><bean:write name="ps" property="tboard_name" filter="true"/></td>
<td class="listcellrow"><logic:equal name="ps" property="tboard_state" value="1" >�״̬</logic:equal>
<logic:equal name="ps" property="tboard_state" value="0" >�ر�״̬</logic:equal>
</td>
<td class="listcellrow"><bean:write name="ps" property="tboard_note" filter="true"/>&nbsp;</td>
<td class="listcellrow4"><a href="javaScript:setSBoard('<%=ps.getSerial_num()%>')" operation="LTWF.VIEW">������̳</a></td>
	
	</tr>
	</logic:iterate>
</table>


</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
		<goa:pagelink name="TopBoardForm" property="pageControl" /> 
		<html:hidden name="TopBoardForm" property="orderType"/>
<html:hidden name="TopBoardForm" property="orderFld"/>
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
