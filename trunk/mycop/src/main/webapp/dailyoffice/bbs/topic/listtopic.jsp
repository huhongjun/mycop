<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ page  import="com.gever.goa.dailyoffice.bbs.action.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context = request.getContextPath();
BBSTopicForm tForm = (BBSTopicForm)session.getAttribute("TopicForm");
%>

<SCRIPT LANGUAGE="JavaScript">
<!--
function modifyUserInfo(){
	var action='<%=context%>/dailyoffice/bbs/user.do?action=toEdit&actionType=modify&fid=';
	var fid=" ";
	var sid=" ";
	<logic:present name="TopicForm" property="userVO">
	fid='<bean:write name="TopicForm" property="userVO.bbs_user_code" />';
	sid='<bean:write name="TopicForm" property="sboardID" />';
	</logic:present>
	toUrl(action+fid+'&sboardID='+sid);
}
//-->
</SCRIPT>
<body class="bodybg">
  <html:form action="/dailyoffice/bbs/listTopic" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
  <html:hidden name="TopicForm" property="sqlWhere"/>
    <td colspan="6" align="center" class="TableTitleText">主题</td>
  </tr>
  <tr>
    <td colspan="6" align="right">&nbsp;</td>
  </tr>
  <tr>
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
            <!--td align="right" class="f12">主题:
            </td>
            <td width="20%">
                <html:text property="searchValue" maxlength="30" size="22" styleClass="input">
                </html:text>
            </td>
            <td>
                <goa:button  property="search" styleClass="button" value="搜索" onclick="doAction('doSearchByTopic')" operation="ZXJL.VIEW">
            	</goa:button>
            </td-->
          <td>
		   <goa:button styleClass="button"  property="set"
		   onclick="modifyUserInfo()" value="修改个人资料" operation="ZXJL.OPT"/>
          </td>
          <td> <goa:button styleClass="button"  property="add" onclick="doAdd()" value="发表新主题" operation="ZXJL.OPT"/>
          </td>
		   <td>
		   <!--goa:button styleClass="button"  property="modify" onclick="toUrl('/goa/dailyoffice/tools/editCardcase.do?actionType=modify',true)" value="修改" operation="ZXJL.OPT"/>
          </td>
		   <td> <goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()" operation="ZXJL.OPT"/>
		   </td>
		    <td> <goa:button  styleClass="button" property="view" value="查看"  onclick="toUrl('/goa/dailyoffice/bbs/topiclist.do',true)" operation="ZXJL.OPT"/>
			</td>
		  <!--td><goa:button styleClass="button"  property="copy"  onclick="doCopy()"value="复制" operation="ZXJL.OPT"/>
          </td-->
		  <!--td> <goa:button  styleClass="button" property="test" value="测试"  onclick="doAction('toMY')" operation="ZXJL.OPT"/>
          </td-->
          </tr>
      </table>
	  </td>
  </tr>
  <tr>
    <td colspan="6">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
	<tr>
			<td width="37%" ></td>
			<td width="10%" ></td>
      		<td width="17%" ></td>
			<td width="5%" ></td>
			<td width="5%" ></td>
			<td width="16%" ></td>
			<td width="10%" ></td>
	</tr>
        <tr height="20">
            <!--td height="11" width="5%" class="Listtitle"><div align="left"><input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)"></div></td>
      		<!--td height="11" width="10%" class="Listtitle" id=".type_code" onclick="onchangeOrderBy(this)">类别</td-->
			<td class="Listtitle" ><div align="left">主题</div></td>
			<td class="Listtitle" ><div align="left">作者</div></td>
      		<td class="Listtitle"><div align="left">发表日期</div></td>
			<td class="Listtitle"><div align="right">回复</div></td>
			<td class="Listtitle"><div align="right">人气</div></td>
			<td class="Listtitle"><div align="left">&nbsp;最后更新时间</div></td>
			<td class="Listtitle"><div align="left">最后回复人</div></td>
			<!--td width="27%" class="Listtitle"id=".e_mail" onclick="onchangeOrderBy(this)"><div>E_MAIL</div></td-->
            <!--<td width="10%" class="Listtitle" align="center">操作</td>-->
    </tr>

    <logic:iterate id="ps" name="TopicForm" property="pageControl.data" type="com.gever.goa.dailyoffice.bbs.vo.ViewTopicVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);"ondblclick="<%="toUrl('"+context+"/dailyoffice/bbs/topiclist.do?fid=" +ps.getSerial_num()+"',false)"%>">
		<td class="listcellrow"><a operation="ZXJL.VIEW" href='<%=context%>/dailyoffice/bbs/topiclist.do?fid=<bean:write name="ps" property="serial_num" filter="true"/>'><bean:write name="ps" property="topic" filter="true"/></a>&nbsp;</td>
<td class="listcellrow"><bean:write name="ps" property="nickname" filter="true"/>&nbsp;</td>
<td class="listcellrow"><bean:write name="ps" property="appear_date" filter="true"/>&nbsp;</td>
<td class="listcellrow" ><div align="right">&nbsp;<bean:write name="ps" property="reply_cnt" filter="true"/></div></td>
<td class="listcellrow" ><div align="right">&nbsp;<bean:write name="ps" property="hit_times" filter="true"/></div></td>
<td class="listcellrow" ><div align="left">&nbsp;<bean:write name="ps" property="reply_time" filter="true"/></div></td>
<td class="listcellrow4" ><bean:write name="ps" property="replyer" filter="true"/>&nbsp;</td>
	</tr>
	</logic:iterate>
	<input type='hidden' name='bbsPageType' value='topic'>
					<!--以上表明这是在topic页面-->
</table>
</td>
  </tr>
  <tr>
    <td colspan="8" align="center" class="f12">
      <html:hidden name="TopicForm" property="orderType"/>
      <html:hidden name="TopicForm" property="orderFld"/>
		<goa:pagelink name="TopicForm" property="pageControl" />
		</td>
	  </tr>
	</table>
</html:form>
</body>