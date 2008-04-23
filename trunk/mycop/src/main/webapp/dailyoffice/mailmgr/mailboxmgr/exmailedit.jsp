<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%@ page import="java.util.Collection,java.util.HashMap,java.util.Iterator,java.util.ArrayList,java.util.List,com.gever.goa.dailyoffice.message.vo.MessageVO,com.gever.goa.dailyoffice.message.action.MessageForm,com.gever.vo.VOInterface,com.gever.goa.innermgr.hr.action.*, com.gever.goa.innermgr.hr.vo.*"%>
<%
        response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
%>
<%String context =request.getContextPath();%>

<SCRIPT LANGUAGE="JavaScript">
var names="";
var ids="";
<!--
function setMember(){
	document.getElementsByName("vo.advance_mems")[0].value=document.getElementsByName("idfiled")[0].value;
}
function toAction(whatAction){
	//汤启礼改
	if((document.all("namfield").value==null)||(document.all("namfield").value==""))
	{alert("请选择人员");
	return
	}
	var mForm=null;
	if(event!=null){
		var btn=event.srcElement;
		
		if(btn.tagName=="INPUT"){
			mForm=btn.form;
		}else{
			mForm = document.forms[0];
		}
	}else{
		mForm = document.forms[0];
	}
	//汤启礼改
	var action =  mForm.action ;
   
	action =  addPara(action,"action=" + whatAction);
		
	mForm.action = action
		
	disButton();
	//alert(action)
	mForm.submit();

}

//-->
</SCRIPT>

<html>
<body class="bodybg">
<html:form action="/dailyoffice/mailmgr/mailboxmgr" method="post">
  <table width="95%" height="84" border="0" align="center" cellpadding="2" cellspacing="0">
    <tr>
      <td height="37" class="TableTitleText"><div align="center">外发邮件权限设置</div></td>
    </tr>
	<tr>
	<td><html:hidden name="BirthdayAwokeForm" property="actionType"/></td>
	</tr>
  </table>
  <table width="95%" border="0" align="center" cellpadding="2" cellspacing="0">
    <tr>
      <td><table width="56%" border="0" align="center" cellpadding="2" cellspacing="0">
    
		 <input type="hidden" name="tag" value="1">
		 <tr>
          <td width="27%" nowrap class="InputLabelCell"><div align="right"></div></td>
          <td width="36%">
		  </td>
          <td width="37%"><div align="center">
                <html:button property="sel" styleClass="button"  onclick="javascript:openSelectWindow('menselect','idfiled','namfield');" value="选择人员"/>
          </div></td>
        </tr>
			

        <tr>
          <td nowrap class="InputLabelCell"><div align="right">人员列表：</div></td>
		  
          <td colspan="2">
		  <html:hidden property="vo.advance_mems"/>
          <textarea  name="namfield" class="input2" rows="6" cols="45%" readonly></textarea>
          <input type="hidden" name="idfiled" value="<bean:write name="BirthdayAwokeForm" property="vo.advance_mems" filter="true"/>">
		  </td>
          </tr>
		   <%  SelectMgr mgr=new SelectMgr();
	   mgr.selectDate();
	   Collection coll=(Collection)mgr.getMgr();
	  Iterator iter= coll.iterator();
	    while(iter.hasNext())
		{ MgrinfoVO vo= (MgrinfoVO)iter.next();%>
		<script>
		
		var names=names+"<%=vo.getName()+","%>";
     var ids=ids+"<%=vo.getId()+","%>";
         document.all("namfield").value=names;
		 document.all("idfiled").value=ids;
		</script>
		 
		<%}%>

      </table>
	  </td>
    </tr>
  </table>
  <table width="95%" height="28" border="0" align="center" cellpadding="2" cellspacing="0">
    <tr>
      <td align="center">
		<goa:button property="save" value="保存并返回" styleClass="button" onclick="toAction('exterMail')"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="history.back()"/>
	  </td>
    </tr>
  </table>
  </html:form>
</body>
</html>