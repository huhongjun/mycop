<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context = request.getContextPath();%>
<SCRIPT LANGUAGE="JavaScript">
function toUrl1(url){
	if (isOpen == true){
		parent.window.opener.parent.top.frames["right"].myOpenWin(url);
		parent.window.opener.parent.top.focus();
	} else {
		parent.window.location.href="/<%=context%>"+url;
	}
}
</script>
<body class="bodybg">
 <html:form action="/dailyoffice/message/listmessage" method="post">
  <html:hidden property="sqlWhere" />
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6" align="center" class="TableTitleText">����Ϣ�б�</td>
  </tr>
  <tr>
    <td colspan="6" align="right">&nbsp;</td>
  </tr>
  <tr>
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
            <td align="right" class="InputLabelCell">����:
            </td>
            <td >
                <html:text property="contentQuery" maxlength="30" size="20" value="" styleClass="input2"/>  
            </td>
               	<td><goa:button styleClass="button"  property="query" onclick="doAction('doSearchByContent')" value="��ѯ" operation="RBDX.VIEW"/></td> 
                <td> <goa:button styleClass="button"  property="add" onclick="doAdd()" value="���Ͷ���Ϣ" operation="RBDX.OPT" /></td>
                <td><goa:button  styleClass="button"  property="delete" value="ɾ��" onclick="doDelete()" operation="RBDX.OPT" /></td>
		<td> 
		
		 <% String temp="toUrl('"+context+"/dailyoffice/message/viewmessage.do',true)";%>
		<goa:button  styleClass="button" property="view" value="�鿴"  onclick="<%=temp%>" operation="RBDX.VIEW"/>
		</td>
          </tr>
      </table>
	  </td>
  </tr>
  <tr>
    <td colspan="6">
	<%String sHref = "";%>
	<table width="100%" border="0" bordercolor="" cellpadding="2" cellspacing="0"  align="center">
        <tr height="20"> 
          <td height="11" width="5%" class="Listtitle"><div align="left">
              <input alt="ѡ��" type="checkbox" name="selectall" onclick="selectAllChk(this)">
            </div></td>
        <td width="65" nowrap id=".user_code" onclick="onchangeOrderBy(this)" class="Listtitle"><div>������</div></td>
      			<td width="90" nowrap id=".content" onclick="onchangeOrderBy(this)" class="Listtitle"><div>��Ϣ����</div></td>
			<td width="90" nowrap id=".read_flag"onclick="onchangeOrderBy(this)" class="Listtitle"><div> �Ķ���־</div></td>
			 <td width="65" nowrap id=".send_time" onclick="onchangeOrderBy(this)"        class="Listtitle"><div>����ʱ��</div></td>
      			<td width="90" nowrap id=".read_time" onclick="onchangeOrderBy(this)" class="Listtitle"><div>�Ķ�ʱ��</div></td>
			<td width="90"  nowrap class="Listtitle">��������</td>
			<td width="90" nowrap class="Listtitle">����</td>
        </tr>
        <logic:iterate id="ps" name="MessageForm" property="pageControl.data" type="com.gever.goa.dailyoffice.message.vo.MessageVO" indexId="index"> 
        <tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);"ondblclick="<%="toUrl('"+context+"/dailyoffice/message/viewmessage.do?fid=" +ps.getSerial_num()+"',false)"%>"> 
         <td  class="listcellrow">
	            	<input type='checkbox' name='fid' value='<goa:write name="ps" property="serial_num" filter="true"/>' ></td>
	           <td class="listcellrow"><goa:write  name="ps" property="user_name" filter="true"/>&nbsp;</td>
	    	    <td class="listcellrow">
		   <%String tempStr = ""+context+"/dailyoffice/message/viewmessage.do?fid="+ps.getSerial_num();%>
		   <goa:link href="<%=tempStr%>" operation="RBDX.VIEW">
		   <goa:write name="ps" property="content" filter="true" maxlen="30" />
		   </goa:link>&nbsp;</td>
	    	   <td  class="listcellrow">
			<logic:equal value="0" property="read_flag" name="ps">δ��</logic:equal>
			<logic:equal value="1" property="read_flag" name="ps">�Ѷ�</logic:equal>
			<logic:equal value="2" property="read_flag" name="ps">����Ϣ����ʾ</logic:equal>&nbsp;</td>	
			<td nowrap class="listcellrow"><goa:write name="ps" property="send_time" filter="true"/>&nbsp;</td>	
			<td nowrap class="listcellrow"><goa:write name="ps" property="read_time" filter="true"/>&nbsp;</td>
			<td nowrap class="listcellrow">	
	                <logic:equal value="1" property="msg_type" name="ps">�ڲ�����</logic:equal>
			<logic:equal value="2" property="msg_type" name="ps">�ֻ�����</logic:equal>&nbsp;</td>
		   <td class="listcellrow4">
		   <% String sUrl = "toUrl1('" + context + ps.getWeb_url()+"')";%>
		   <goa:link href="javascript::" onclick="<%=sUrl%>"operation="RBDX.VIEW">
		   <goa:write name="ps" property="memo" filter="true" />
		   </goa:link>&nbsp;</td>
        </tr>
        </logic:iterate> </table>
</td>
  </tr>
  <tr>
    <td colspan="8" align="center" class="f12">
      <html:hidden name="MessageForm" property="orderType"/>
      <html:hidden name="MessageForm" property="orderFld"/>
		<goa:pagelink name="MessageForm" property="pageControl" />
		</td>
	  </tr>
	</table>
</html:form>
</body>

