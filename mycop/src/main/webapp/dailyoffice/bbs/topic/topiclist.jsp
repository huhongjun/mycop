<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java"%>
<%@ page  import="com.gever.goa.dailyoffice.bbs.action.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
BBSTopicForm tForm = (BBSTopicForm)session.getAttribute("TopicForm");
long curPage = tForm.getPageControl().getCurrentPage();
int curRow=0;
%>

<html>
<body class="bodybg">
 <html:form action="/dailyoffice/bbs/topiclist" method="post" enctype="multipart/form-data">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> <html:hidden name="TopicForm" property="sqlWhere"/>
    <td colspan="6" align="center" class="TableTitleText">&nbsp;</td>
  </tr>
  <tr> 
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
		 
        </tr>
      </table>
	  </td>
  </tr>
  <tr> 
		
    <td colspan="6">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
    <logic:iterate id="ps" name="TopicForm" property="pageControl.data" type="com.gever.goa.dailyoffice.bbs.vo.ViewTopiclistVO" indexId="index">
	<% //判断是否第一页第一条记录；是的话就说明是话题的具体内容。。。
	curRow = ((Integer)pageContext.getAttribute("index")).intValue();
	if (curRow==0 && curPage==1){%>
		<tr>
		<td colspan="6">
		<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
			<tr class="listcellrow2">
				<td width='20%'>主&nbsp;&nbsp;&nbsp;&nbsp;题：
				</td >
				<td width='80%' align="left"><goa:write name="TopicForm" property="viewtopicVO.topic" filter="true"/>&nbsp;</td>
				</tr>				
			<tr class="listcellrow2">
				<td>作&nbsp;&nbsp;&nbsp;&nbsp;者：
				</td>
				<td><goa:write name="TopicForm" property="viewtopicVO.nickname" filter="true"/>&nbsp;
				</td>
			</tr>
			<tr class="listcellrow2">
				<td>发表时间：
				</td>
				<td><goa:write name="TopicForm" property="viewtopicVO.appear_date" filter="true"/>&nbsp;
				</td>
			</tr>
			<tr class="listcellrow2">
				<td>回复次数：
				</td>
				<td><goa:write name="TopicForm" property="viewtopicVO.reply_cnt" filter="true"/>&nbsp;
				</td>
				<td colspan='4' width='50%'>&nbsp;</td>
			</tr>
			<tr height='100'>
				<td colspan='6' class='input' valign='top'>
					<goa:write name="ps" property="content" filter="true"/>&nbsp;

				<logic:notEqual name="ps" property="file_name" value="">
				<br>
				附件：
				<br>
				<a operation="ZXJL.VIEW" href='<%=context%>/util/downloadfile.jsp?filepath=<goa:write name="ps" property="file_path" filter="true"/>&&filename=<goa:write  name="ps" property="file_name" filter="true"/>' onclick="download('<goa:write name="ps" property="file_path" filter="true"/>')">
				<goa:write  name="ps" property="file_name" filter="true"/>
				</a>
				</logic:notEqual>

				</td>
			</tr>
		</table>
		</td>
		</tr>
	<%}else{%>
	<tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" 	onmouseout="javascript:changeBgColorOnMouseOut(this);">
		<td class='f12'>回复人:</td>
		<td class="listcellrow2"><goa:write name="ps" property="nickname" filter="true"/>&nbsp;</td>
		<td class="listcellrow2" width='40%'>&nbsp;</td>
		<td class="listcellrow2">回复时间：&nbsp;</td>
		<td class="listcellrow2"><goa:write name="ps" property="reply_date" filter="true"/>&nbsp;</td>
		<td class="listcellrow2">&nbsp;</td>
	</tr>
	<tr height='100'>
		<td colspan='6' class='input'  valign='top'>
			<goa:write name="ps" property="content" filter="true"/>&nbsp;

			<logic:notEqual name="ps" property="file_name" value="">
			<br>
			附件：
			<br>
			<a operation="ZXJL.VIEW" href='<%=context%>/util/downloadfile.jsp?filepath=<goa:write name="ps" property="file_path" filter="true"/>&&filename=<goa:write  name="ps" property="file_name" filter="true"/>' onclick="download('<goa:write name="ps" property="file_path" filter="true"/>')"  >
				<goa:write  name="ps" property="file_name" filter="true"/>
				</a>
		</logic:notEqual>

		</td>
	</tr>
	<%}%>
	</logic:iterate>
</table>


</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
<input type='hidden' name='bbsPageType' value='topiclist'>
					<!--以上表明这是在topiclist页面-->
<html:hidden name="TopicForm" property="orderType"/>
<html:hidden name="TopicForm" property="orderFld"/>
		<goa:pagelink name="TopicForm" property="pageControl" /> 
		</td>
	  </tr>
	</table>
	<br>  
	<logic:present name="TopicForm" property="userVO">
        <logic:equal value="0" name="TopicForm" property="userVO.user_state">
            <!--以下是一个回帖的界面-->
	<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
		 <tr> 
        <td>
		&nbsp;<html:hidden name="TopicForm" property="actionType"/>
		
		</td>
		<td></td><td></td>
      </tr>
		<tr class="listcellrow">					     
                   <td width="15％" height="100" nowrap class="InputLabelCell" valign='top' align='right'>内容：</td>
					     <td width="70％" ><html:textarea property="topiclistVO.content" cols="37" rows="7" styleClass="input" maxlength="1500"/></td>
					<td width='15%'>&nbsp;</td>
		</tr>
		<tr class="listcellrow">
			<td class="InputLabelCell" align='right'>附件：</td>
			<td class='f12'><input type="file" class="input2" name="theFile" value="" size="50%"></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
		<td colspan='2' class='InputLabelCell'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用短消息通知话题其他用户：
		<html:checkbox property="topiclistVO.awoke_flag" value="1">
		</html:checkbox>
		</td>
		</tr>
		<tr> 
        <td align="center" colspan="2"> 
<% 
String temp="toUrl('"+context+"/dailyoffice/bbs/listTopic.do?sboardID="+tForm.getSboardID()+"&bbsPageType=topic')";
%>		
		<goa:button property="save" value="回复" styleClass="button"  onclick="return doSaveExit(TopicForm)" operation="ZXJL.OPT"/>
          <goa:button property="exit" value="返回" styleClass="button" onclick="<%=temp%>" operation="ZXJL.VIEW"/></td>
      </tr>
	</table>
        <!--以上是一个回帖的界面-->
    </logic:equal>
    <logic:equal value="1" name="TopicForm" property="userVO.user_state">
    <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    	<tr class='f12' align='center'>
                <td>你已经被管理员禁止发言！！
                    <br>
                    请和管理员联系！！
                </td>
        </tr>
		<tr>
			<td align='center'>
		<goa:button property="exit" value="返回" styleClass="button" onclick="<%="toUrl('"+context+"/dailyoffice/bbs/listTopic.do?sboardID="+tForm.getSboardID()+"&bbsPageType=topic')"%>" operation="ZXJL.VIEW"/>
			</td>
		</tr>
    </table>
    </logic:equal>
</logic:present>
<logic:notPresent name="TopicForm" property="userVO">
	<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    	<tr class='f12' align='center'>
                <td>登陆超时！
                    <br>
                    请重新登陆！
                </td>
        </tr>
	</table>
</logic:notPresent>
</html:form>

</body>
</html>