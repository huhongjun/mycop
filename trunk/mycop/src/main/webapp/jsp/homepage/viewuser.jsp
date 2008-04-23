<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="com.gever.web.util.ActiveUsers,java.util.Collection,java.util.HashMap,java.util.Iterator,java.util.ArrayList,java.util.List,com.gever.goa.dailyoffice.message.vo.MessageVO,com.gever.goa.dailyoffice.message.action.MessageForm,com.gever.vo.VOInterface" %>
<%String context =request.getContextPath();%>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<%MessageForm form=new MessageForm();
VOInterface vo=form.getVo();%>
<% ActiveUsers au=ActiveUsers.getInstance();
	Collection list=(Collection)au.getOnlineUsers();
	String name=request.getParameter("paraSimpleQuery");
	//out.print(name);
	String rgp=request.getParameter("paraFlag");
	//out.print(rgp);
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

<script language="JavaScript" type="text/JavaScript">
function checkAll(it,cid){
	if(it.form.elements[cid]!=null){
		var size=it.form.elements[cid].length;
		if(size!=null){
			for(i=0;i<size;i++){
				it.form.elements[cid][i].checked=it.checked;
			}
		}else{
			it.form.elements[cid].checked=it.checked;	
		}
	}
}
function addinfo()
{
  window.location="<%=context%>/jsp/homepage/sendmessage.jsp";
}

function toAction(obj,actions,alertStr){

  if(alertStr==null){
    obj.form.action=obj.form.action+'?action='+actions;	
    obj.form.submit();}
  else{
    if(confirm(alertStr)){
    	  obj.form.action=obj.form.action+'?action='+actions;
	  obj.form.submit();
    } else {
    	return false;
    }	
  }
  return;
}
function toSimpleQuery(url){
	var mForm = document.forms[0];
	var paraSimpleQuery = mForm.nameinput.value;
	//alert(paraSimpleQuery);
	if(paraSimpleQuery!=null || paraSimpleQuery!=""){
	    var temp = url+"&paraSimpleQuery="+paraSimpleQuery;
		mForm.action = temp;
	}
	mForm.submit();
}
</script>

<html>

<head>
<link href="../../css/goa.css" rel="stylesheet" type="text/css">
<link href="<%=com.gever.goa.web.util.RequestUtils.getCSSPath(pageContext)%>" id="goastylecss" rel="stylesheet" type="text/css">
<title>在线用户列表</title></head>

<body class="bodybg">
<table width="95%" height="20" border="0" align="center" cellpadding="2" cellspacing="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><div align="center" class="TableTitleText">在线用户列表</div></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>

<table width="95%" border="0" align="center" cellpadding="2" cellspacing="0">
  <tr>
    <td><table width="340" border="0" align="right" cellpadding="2" cellspacing="0">
      <tr>
	   <form method="post" action="<%=context%>/admin/homepage/sendmessage.jsp">
        <td width="340"><div align="right"><span class="InputLabelCell">姓名：
                <input name="nameinput" type="text" class="input2">
                <input name="Submit" type="submit" class="button" value="查询" onclick="toSimpleQuery('<%=context%>/jsp/homepage/viewuser.jsp?paraFlag=true')">
				<input type="Submit" class="button"  onclick="return checkSelected()" value="发送短消息">  
</span>  </div></td>
				
		
      </tr>
    </table></td>
  </tr>
</table>
<table width="95%" border="0" align="center" cellpadding="2" cellspacing="0">

  <tr class="Listtitle">
  <td width="15%" height="23"><input type="checkbox" name="checkbox5" value="checkbox"  onclick="checkAll(this,'fid')"></td>
    <td width="15%" height="23">用户ID</td>
    <td width="15%" height="23">用户名称</td>
    <td width="15%" height="23">所在部门</td>
    <td width="25%" height="23">IP地址</td>
    <td height="23">登陆时间</td>
  </tr><% 
for (Iterator iter = list.iterator(); iter.hasNext();) {
			HashMap hm = (HashMap) iter.next();
			
		
			%>
	
 <tr>
    <td  class="listcellrow"> <input type="checkbox" name="fid" value="<%=(String)hm.get("session") %>">&nbsp;</td>
	<td class="listcellrow"><%=(String)hm.get("session") %>&nbsp;</td>
    <td class="listcellrow"><%=(String)hm.get("username") %>&nbsp;</td>
    <td class="listcellrow">
	<% if (("").equals((String)hm.get("deptname"))){
		out.print("该人员无部门");	 
		}
	 %><%=(String)hm.get("deptname")%>
	&nbsp;</td>
    <td class="listcellrow"><%=(String)hm.get("ipaddress") %>&nbsp;</td>
    <td class="listcellrow4"><%=(String)hm.get("lastdate") %>&nbsp;</td>
  </tr>
<% } %>
</table>
</form>
</body>
</html>
