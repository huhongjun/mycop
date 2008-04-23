<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<SCRIPT LANGUAGE="JavaScript">
<!--
function setParaInsert(paraInsert){
	document.forms[0].paraInsert.value=paraInsert;
}
//-->
</SCRIPT>

<html>
<body class="bodybg">
<html:form action="/infoservice/templatelist" method="post">

<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr>
        <td align="center" class="TableTitleText">
		<logic:equal name="IsStandardModelForm" property="nodeID" value="1">������־ģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="2">�ܻ㱨ģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="3">�»㱨ģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="4">��㱨ģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="5">ÿ��Ŀ��ģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="6">�¶�Ŀ��ģ��</logic:equal>
        <logic:equal name="IsStandardModelForm" property="nodeID" value="7">�¶��ܽ�ģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="8">���Ŀ��ģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="9">����ܽ�ģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="10">����滮ģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="11">�����㱨ģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="12">��Ȩ��ģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="infotype" value="">
		<logic:equal name="IsStandardModelForm" property="nodeID" value="13">��˾��̬ģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="14">������Ϣģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="15">�������ģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="16">��ҵ����ģ��</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="17">�����ƶ�ģ��</logic:equal>
		</logic:equal>
		<logic:greaterThan name="IsStandardModelForm" property="nodeID" value="12">
		<logic:notEqual name="IsStandardModelForm" property="infotype" value="">
		<goa:write name="IsStandardModelForm" property="infotype" filter="ture" />ģ��
		</logic:notEqual>
		</logic:greaterThan>
		</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td align="right">
	        <table>
                <tr>
                    <td class="InputLabelCell">
					ģ�����ƣ�
					</td>
					<td >
					<html:text styleClass="input2" size="10" name="IsStandardModelForm" property="paraSimpleQuery"/>
					</td>
					<td>
					<%String clk1="toSimpleQuery('"+context+"/infoservice/templatelist.do?paraFlag=0')";%>
					<goa:button styleClass="button"  property="simplequery" onclick="<%=clk1%>" value="��ѯ" operation="BZMB.VIEW"/>

					<logic:notEqual name="IsStandardModelForm" property="nodeID" value="">
					<goa:button styleClass="button"  property="add" onclick="setParaInsert(0);doAdd()" value="��������ģ��" operation="BZMB.OPT"/>
					</logic:notEqual>

                    <logic:notEqual name="IsStandardModelForm" property="infotype" value="">
					<logic:greaterThan name="IsStandardModelForm" property="nodeID" value="12" >
					<goa:button styleClass="button"  property="add" onclick="setParaInsert(1);doAdd()" value="����WORDģ��" operation="BZMB.OPT"/>
					</logic:greaterThan>
					</logic:notEqual>

		            <%String clk2="toUrl('"+context+"/infoservice/template.do?actionType=modify',true)";%>
                    <goa:button styleClass="button"  property="copy" onclick="<%=clk2%>" value="�޸�" operation="BZMB.OPT"/>
					<goa:button  styleClass="button" property="delete" value="ɾ��"  onclick="doDelete()" operation="BZMB.OPT"/>
					</td>
				</tr>
			</table>
	    </td>
    </tr>
</table>

<table width="95%" border="0" cellpadding="2" cellspacing="0"  align="center">
	<tr height="20">
		<td width="3%" align="left" class="Listtitle">
		<input alt="ѡ��" type="checkbox" name="selectall" onclick="selectAllChk(this)">
		</td>
		<td width="15%" class="Listtitle" id=".model_type" onclick="onchangeOrderBy(this)"> <div align="left">���</div></td>
		<td width="25%" class="Listtitle" id=".model_name" onclick="onchangeOrderBy(this)"> <div align="left">ģ������</div></td>
		<td width="10%" class="Listtitle" id=".issue_flag" onclick="onchangeOrderBy(this)"> <div align="left">�Ƿ��ѷ���</div></td>
		<td width="10%" class="Listtitle" id=".user_code" onclick="onchangeOrderBy(this)"> <div align="left">������</div></td>
		<td width="15%" class="Listtitle" id=".create_date" onclick="onchangeOrderBy(this)"> <div align="left">��������</div></td>
	</tr>
	<html:hidden name="IsStandardModelForm" property="paraInsert"/>
	<logic:iterate id="ps" name="IsStandardModelForm" property="pageControl.data" type="com.gever.goa.infoservice.vo.IsStandardModelVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);"  ondblclick="javascript:toUrl('<%=context%>/infoservice/templateview.do?fid=<goa:write maxlen="0" name="ps" property="model_code" filter="true"/>')">
		<td align="left" class="listcellrow">
		<input type='checkbox' name='fid' value='<goa:write maxlen="0" name="ps" property="model_code" filter="true"/>' >
		</td>
		<td class="listcellrow">
		<logic:equal name="ps" property="model_type" value="1">������־</logic:equal>
		<logic:equal name="ps" property="model_type" value="2">�ܻ㱨</logic:equal>
		<logic:equal name="ps" property="model_type" value="3">�»㱨</logic:equal>
		<logic:equal name="ps" property="model_type" value="4">��㱨</logic:equal>
		<logic:equal name="ps" property="model_type" value="5">ÿ��Ŀ��</logic:equal>
		<logic:equal name="ps" property="model_type" value="6">�¶�Ŀ��</logic:equal>
		<logic:equal name="ps" property="model_type" value="7">�¶��ܽ�</logic:equal>
		<logic:equal name="ps" property="model_type" value="8">���Ŀ��</logic:equal>
		<logic:equal name="ps" property="model_type" value="9">����ܽ�</logic:equal>
		<logic:equal name="ps" property="model_type" value="10">����滯</logic:equal>
		<logic:equal name="ps" property="model_type" value="11">�����㱨</logic:equal>
		<logic:equal name="ps" property="model_type" value="12">��Ȩ��</logic:equal>
		<logic:equal name="ps" property="model_type" value="13">��˾��̬</logic:equal>
		<logic:equal name="ps" property="model_type" value="14">������Ϣ</logic:equal>
		<logic:equal name="ps" property="model_type" value="15">�������</logic:equal>
		<logic:equal name="ps" property="model_type" value="16">��ҵ����</logic:equal>
		<logic:equal name="ps" property="model_type" value="17">�����ƶ�</logic:equal>
		</td>
		<td class="listcellrow">
		<goa:write maxlen="20" name="ps" property="model_name" filter="true"/>&nbsp;
		</td>
		<td class="listcellrow">
		<logic:equal name="ps" property="issue_flag" value="0">
			δ����
		</logic:equal>
		<logic:equal name="ps" property="issue_flag" value="1">
			�ѷ���
		</logic:equal>
		&nbsp;
		</td>
		<td class="listcellrow">
		<goa:write maxlen="12" name="ps" property="user_name" filter="true"/>&nbsp;
		</td>
		<td class="listcellrow4">
		<goa:write maxlen="12" name="ps" property="create_date" filter="true"/>&nbsp;
		</td>
	</tr>
	</logic:iterate>
</table>

<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr>
		<td align="center">
		<html:hidden name="IsStandardModelForm" property="orderType"/>
		<html:hidden name="IsStandardModelForm" property="orderFld"/>
		<goa:pagelink name="IsStandardModelForm" property="pageControl" />
		</td>
	</tr>
</table>
</html:form>
</body>
</html>
