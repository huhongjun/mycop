<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ page  import="com.gever.goa.dailyoffice.bbs.action.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<html:errors />
<%String context =request.getContextPath();
 BBSTopicForm tForm = (BBSTopicForm)session.getAttribute("TopicForm");%>

<body class="bodybg">
<html:form action="/dailyoffice/bbs/editTopic" method="post" enctype="multipart/form-data">
<logic:present name="TopicForm" property="userVO">
<div align="center">
  <center>
    <br><input type='hidden' name='bbsPageType' value='topic'>
					<!--���ϱ���������topicҳ��-->
    <span class="TableTitleText"><br>
    </span> 
	<logic:equal value="0" name="TopicForm" property="userVO.user_state">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">����������</span></td>
      </tr>
      <tr> 
        <td>
		&nbsp;<html:hidden name="TopicForm" property="actionType"/>
					
		</td>
      </tr>
      <tr> 
        <td align="center">
			<table border="0" cellpadding="20" cellspacing="0">
				<tr> 
				  <td>
				  <table width="400" height="36" border="0" cellpadding="2" cellspacing="1">
                  <tr class="listcellrow">
					    <td width="15��" height="23" nowrap class="InputLabelCell" align='right'>���⣺</td>
					    <td width="70��" height="23"><html:text styleClass="input" property="topicVO.topic" size="22" validatetype="notempty" msg="���ⲻ��Ϊ�գ�" maxlength="100"/></td>
						<td width='15%'><font color="red">*</font></td>
					</tr>
			<tr class="listcellrow">					     
                   <td width="15��" height="100" nowrap class="InputLabelCell" valign='top' align='right'>���ݣ�</td>
					     <td width="70��" ><html:textarea property="topiclistVO.content" cols="37" rows="7" styleClass="input" maxlength="1500" /></td>
					<td width='15%'>&nbsp;</td>
			</tr>
			<tr class="listcellrow">
				<td class="InputLabelCell" align='right'>������</td>
				<td class='f12'><input type="file" class="input2" name="theFile" value="" size="50%"></td>
				<td>&nbsp;</td>
			</tr>

				  </table>

	  			  </td>
				</tr>
          </table>

        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr> 
        <td align="center"> 
		<goa:button property="save" value="����" styleClass="button"  onclick="return doSaveExit(TopicForm)" operation="ZXJL.OPT"/>
<% 
String temp="toUrl('"+context+"/dailyoffice/bbs/listTopic.do?sboardID="+tForm.getSboardID()+"')";
%>		
          <goa:button property="exit" value="����" styleClass="button" onclick="<%=temp%>" operation="ZXJL.VIEW"/></td>
      </tr>
    </table>
	</logic:equal>
	    <logic:equal value="1" name="TopicForm" property="userVO.user_state">
    <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    	<tr class='f12' align='center'>
                <td>���Ѿ�������Ա��ֹ���ԣ���
                    <br>
                    ��͹���Ա��ϵ����
                </td>
        </tr>
		<tr>
			<td align='center'>
			<% 
String temp1="toUrl('"+context+"/dailyoffice/bbs/listTopic.do?sboardID="+tForm.getSboardID()+"')";
%>
			<goa:button property="exit" value="����" styleClass="button"  onclick="<%=temp1%>" operation="ZXJL.VIEW"/>
			</td>
		</tr>
    </table>
    </logic:equal>
  </center>
</div>
</logic:present>
<logic:notPresent name="TopicForm" property="userVO">
	<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    	<tr class='f12' align='center'>
                <td>��½��ʱ��
                    <br>
                    �����µ�½��
                </td>
        </tr>
	</table>
</logic:notPresent>

</html:form>
</body>
