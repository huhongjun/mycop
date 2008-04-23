<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%@ page import="com.gever.web.util.ActiveUsers,java.util.Collection,java.util.HashMap,java.util.Iterator,java.util.ArrayList,java.util.List,com.gever.goa.dailyoffice.message.vo.MessageVO,com.gever.goa.dailyoffice.message.action.MessageForm,com.gever.vo.VOInterface" %>

<%String context =request.getContextPath();%>
<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>


<% String[] ids = request.getParameterValues("fid");

ActiveUsers au=ActiveUsers.getInstance();
	Collection list=(Collection)au.getOnlineUsers();
	String name=request.getParameter("paraSimpleQuery");
	String rgp=request.getParameter("paraFlag");
	if("true".equals(rgp)&&!"".equals(name)){
		List arrlist= new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
				HashMap hm = (HashMap) iter.next();
				//out.print(hm.get("session"));
				if(((String)hm.get("username")).indexOf(name)!=-1){
					HashMap hmp=new HashMap();
					hmp.put("session", hm.get("session"));
					hmp.put("username",hm.get("username"));
					hmp.put("deptname",hm.get("deptname"));
					hmp.put("lastdate",hm.get("lastdate"));
					hmp.put("ipaddress",hm.get("ipaddress"));
					arrlist.add(hmp);
					list=(Collection)arrlist;
				}else{
					list=(Collection)arrlist;
				}
			}
		}
	%>
<link href="../../css/goa.css" rel="stylesheet" type="text/css">
<link href="<%=com.gever.goa.web.util.RequestUtils.getCSSPath(pageContext)%>" 
<body class="bodybg">
<html:form action="/dailyoffice/message/message" method="post"  enctype="multipart/form-data">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span>
	
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">短信息-新增</span></td>
      </tr>
      <tr> <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td></tr>
      <tr> <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td></tr>
      <tr></tr>
     <tr> 
      
      </tr>
      <tr> 
        <td>&nbsp;</td>
      </tr>
      <tr> 
        <td align="center">
			<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
				</tr> 
                  			  <tr class="listcellrow">	
                  			  <td>&nbsp;&nbsp;&nbsp;</td>
                  			  <td>&nbsp;&nbsp;&nbsp;</td>
                  			  <td>&nbsp;&nbsp;&nbsp;</td>
                  			  <td>&nbsp;&nbsp;&nbsp;</td>
                  			  <td>&nbsp;&nbsp;&nbsp;</td>
              <td nowrap class="InputLabelCell">人员选择：</td>
              <td width="86" colspan="1">
			  <textarea class="input2" cols="30" rows="3" name="receivername" maxlength="25">
			  </textarea>
			   <input type="hidden" name="receiver" value=''>
			  <%String names="";
			  String recives="";
			  for (Iterator iter = list.iterator(); iter.hasNext();) {
			  HashMap hm = (HashMap) iter.next();%>
			  <%for(int i=0;i<ids.length;i++){%>
				 <%String uid=(String)hm.get("session");
				 %>
				<% if(ids[i].equals(uid)){
					 names=names+hm.get("username")+",";
					 recives=recives+uid+",";%>
			<script>document.all("receivername").value="<%=names%>";
		document.all("receiver").value="<%=recives%>";</script>
				
                <%}}
				} %>
			 
			</td>
			<td>
			<font color="red">&nbsp;*</font>
             </td>
	         </tr>
					<tr class="listcellrow">
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;&nbsp;</td>
                  	<td>&nbsp;&nbsp;&nbsp;</td>
                  	<td>&nbsp;&nbsp;&nbsp;</td>
                  	<td>&nbsp;&nbsp;&nbsp;</td>
					 <td width="86" align="left" height="100"  nowrap class="InputLabelCell">信息内容：</td>
				    <td height="100"width="250" colspan="3"> <textarea  styleClass="input2" cols="30" rows="3" name="content" maxlength="25"></textarea></td>
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
		<goa:button property="save" value="发送" styleClass="button"   onclick="doAction('writeInfor')" operation="RBDX.OPT" />
          	<goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="RBDX.VIEW"/></td>
      </tr>
    </table>
    </center>
</div>
</html:form>
</body>

