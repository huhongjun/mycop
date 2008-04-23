<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%@ page import="com.gever.goa.infoservice.action.IsStandardModelForm"%>
<%String context =request.getContextPath();%>

<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/control/function.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=JAVASCRIPT src="<%=context%>/cell/control/buttons.js"></SCRIPT>
<SCRIPT  LANGUAGE=JAVASCRIPT src="<%=context%>/cell/goascript/public.js"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/public.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/code.vbs"></SCRIPT>

<!--HTMLģ����Դ-->
<link rel="stylesheet" href="<%=context%>/common/htmleditor/css/toolbar.css">
<link rel="stylesheet" href="<%=context%>/common/htmleditor/css/editor.css">
<script src="<%=context%>/common/htmleditor/js/toolbar.js"></script>
<script src="<%=context%>/common/htmleditor/js/editor.js"></script>
<script src="<%=context%>/common/htmleditor/js/colorpicker.js"></script>

<SCRIPT LANGUAGE="JavaScript">
<!--
function setTempleteContent(templeteContent){
	document.getElementsByName("vo.content")[0].value=templeteContent;
}
//-->
</SCRIPT>

<html>
<body class="bodybg">
<html:form action="/infoservice/templateview" method="post" enctype="multipart/form-data">
<span class="TableTitleText"><br></span> 

<table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
        <td align="center"><span class="TableTitleText">
		<logic:equal name="IsStandardModelForm" property="nodeID" value="1">������־ģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="2">�ܻ㱨ģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="3">�»㱨ģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="4">��㱨ģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="5">ÿ��Ŀ��ģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="6">�¶�Ŀ��ģ�����</logic:equal>
        <logic:equal name="IsStandardModelForm" property="nodeID" value="7">�¶��ܽ�ģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="8">���Ŀ��ģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="9">����ܽ�ģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="10">����滮ģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="11">�����㱨ģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="12">��Ȩ��ģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="13">��˾��̬ģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="14">������Ϣģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="15">�������ģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="16">��ҵ����ģ�����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="17">�����ƶ�ģ�����</logic:equal>
       </span></td>
    </tr>
    <tr> 
        <td>&nbsp;<html:hidden name="IsStandardModelForm" property="actionType"/></td>
    </tr>
</table>

<table  align="center" width="85%" cellspacing="0" cellpadding="0">
<tr>
<td >
<table width="95%" align="center">
	<tr class="listcellrow">
		<td width="15%">&nbsp;</td>		
		<td width="30%">&nbsp;</td>
		<td width="5%">&nbsp;</td>
		<td width="15%">&nbsp;</td>
		<td width="35%">&nbsp;</td>
	</tr>
	<html:hidden property="vo.content"/>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>ģ�����ƣ�</td>
		<td>
		<goa:write  name="IsStandardModelForm" property="vo.model_name" filter="true"/>
		</td>
		<td>&nbsp;</td>
		<td align="right" class="InputLabelCell" nowrap>ģ�����ͣ�</td>
		<td>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="1">������־</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="2">�ܻ㱨</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="3">�»㱨</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="4">��㱨</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="5">ÿ��Ŀ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="6">�¶�Ŀ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="7">�¶��ܽ�</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="8">���Ŀ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="9">����ܽ�</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="10">����滯</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="11">�����㱨</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="12">��Ȩ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="13">��֯��Ϣ</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="14">������Ϣ</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="15">�������</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="16">��ҵ����</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.model_type" value="17">�����ƶ�</logic:equal>
		</td>
	</tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">�����ˣ�</td>
		<td>
		<goa:write  name="IsStandardModelForm" property="vo.user_name" filter="true"/>
		</td>
		<td>&nbsp;</td>
		<td align="right" class="InputLabelCell" nowrap>�������ڣ�</td>
		<td>
		<goa:write  name="IsStandardModelForm" property="vo.create_date" filter="true"/>
		</td>
	</tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>�Ƿ��ѷ�����</td>
		<td>
		<logic:equal name="IsStandardModelForm" property="vo.issue_flag" value="0">
			δ����
		</logic:equal>
		<logic:equal name="IsStandardModelForm" property="vo.issue_flag" value="1">
			�ѷ���
		</logic:equal>
		</td>
	    <td>&nbsp;</td>
	    <td><div align="right" class="InputLabelCell" nowrap>�������</div></td>
	    <td><goa:write  name="IsStandardModelForm" property="vo.info_type" filter="true"/></td>
	</tr>
	<logic:equal name="IsStandardModelForm" property="vo.editor_type" value="0">
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>ģ�����أ�</td>
		<td colspan="4">
		<% 
		 IsStandardModelForm dForm = (IsStandardModelForm)session.getAttribute("IsStandardModelForm");
		 String bfilepath=dForm.getVo().getValue("file_path"); 
		 String bfilename=dForm.getVo().getValue("file_name");
		 String strUrl = "";	
               String strHref = context+"/util/downloadfile.jsp?filepath=" 
                   + bfilepath + "&filename=" + bfilename;
   
                 String downloadMethod = "download2('" + bfilepath + "','" + bfilename +"')";
		
		 %>
		  <goa:link style="cursor:hand" href="<%=strHref%>" onclick ="<%=downloadMethod%>" title="���ظ���" ><%=bfilename%>
          </goa:link>&nbsp;
		</td>
	</tr>	
	</logic:equal>
	<tr>
		<td colspan="5">&nbsp;</td>
	</tr>
</table>
</td>
</tr>
</table>
<logic:equal name="IsStandardModelForm" property="vo.editor_type" value="1">
<table class="tablebk" align="center" width="85%" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center">		
			<SCRIPT LANGUAGE="JavaScript">
			<!--
				var editor=new Editor("e1",500,300);
			//-->
			</SCRIPT>		
		</td>
	</tr>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
e1.setValue(document.getElementsByName("vo.content")[0].value);
e1.setReadOnly();
//-->
</SCRIPT>
</logic:equal>

<table width="95%" border="0" cellspacing="0" cellpadding="0">
	<tr>
        <td>&nbsp;</td>
    </tr>
    <tr> 
        <td align="center"> 
		<html:hidden name="IsStandardModelForm" property="fid" />
		<%String clk1="toUrl('"+context+"/infoservice/template.do?actionType=modify',false)";%>
		<goa:button property="save" value="�޸�" styleClass="button"  
		onclick="<%=clk1%>" operation="BZMB.OPT"/>
        <goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="BZMB.VIEW"/>
		</td>
    </tr>
</table>

</html:form>
</body>
</html>