<%@ page language="java" import="com.gever.struts.Constant" %>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
%>
<%
		response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
%>
<%@ page import="java.util.Collection,java.util.HashMap,java.util.Iterator,java.util.ArrayList,java.util.List,com.gever.goa.dailyoffice.message.vo.MessageVO,com.gever.goa.dailyoffice.message.action.MessageForm,com.gever.vo.VOInterface,com.gever.goa.innermgr.hr.action.*,com.gever.goa.innermgr.hr.vo.*"%>
<HTML>
<HEAD>
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>左帧页面</TITLE>
<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<link href="<%=com.gever.goa.web.util.RequestUtils.getCSSPath(pageContext)%>" id="goastylecss" rel="stylesheet" type="text/css">
</HEAD>
<script language="javascript">
	function HighlightMenuItem(obj){
		obj.className = "Menu_Child_Highlight";
	}
	function NormalMenuItem(obj){
		obj.className = "Menu_Child";
	}
</script>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function exitSystem(){
		var bYesNo = confirm("是否退出邮件管理系统?");

		if (bYesNo == true ){
			window.parent.close();
		}

	}
	function reloadMain() {
		//alert("reloadMain!");
		var parentWindow = window.parent;
		var mainWindow = parentWindow.frames['middle'];
		//alert("main window location is " + mainWindow.location);
		mainWindow.location.reload();
	}
//-->
</SCRIPT>
<body class="mailbody" topmargin=0 leftmargin=0 marginwidth=0 marginheight=0>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
    <img src="<%=context%>/images/mail-logo.gif" width="130" height="89" style="cursor:hand" onclick="javascript:reloadMain();" alt="刷新">
    </td>
  </tr>
  <tr>
    <td height="31" class="f14" align="center" nowrap="nowrap"><font color="#FFFFFF"><strong>欢迎您：<%=(String) session.getAttribute(Constant.NAME)%></strong></font></td>
  </tr>

  <tr>
    <td align="center">
<table border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td background="../../images/tree4-3.gif">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><img src="../../images/tree4.gif" width="12" height="20"></td>
          <td><a href="writemail.do" target="middle" class="menu2"><img src="../../images/ico_mail_fyj.gif" width="16" height="16" border="0" align="absmiddle" >发内部邮件</a></td>
        </tr>
       <%
       	   String uid=(String) session.getAttribute(Constant.USER_ID);

       	   SelectMgr mgr=new SelectMgr();
       	   boolean judge=false;
       	    String tag="";
       	   mgr.selectDate();
       	   Collection coll=(Collection)mgr.getMgr();
       	  Iterator iter= coll.iterator();
       	    while(iter.hasNext())
              {
               MgrinfoVO vo= (MgrinfoVO)iter.next();
              String id= vo.getId();
       	 
       	   tag=vo.getTag();
       	 
       	   if(id.trim().equals(uid))
       		   {
       		   judge=true;
       		   break;
       	         
       	   }
              }
       %>
	   <%
	  
	   if((judge==true)&&(tag.equals("1"))){%>
		<tr>
          <td><img src="../../images/tree4.gif" width="12" height="20"></td>
          <td><a href="writemail.do?exteriomail=true" target="middle" class="menu2"><img src="../../images/ico_mail_fyj.gif" width="16" height="16" border="0" align="absmiddle" >发外部邮件</a></td>
        </tr>
        <%}
	   coll.clear();%>
        <tr>
          <td><img src="../../images/tree4.gif" width="12" height="20"></td>

          <td ><a href="maillist.do?mailDirId=0" target="middle" class="menu2"><img src="../../images/ico_mail_sjx.gif" width="16" height="16" border="0" align="absmiddle" >收件夹</a></td>

        </tr>
        <tr>
          <td><img src="../../images/tree4.gif" width="12" height="20"></td>

          <td><a href="maillist.do?mailDirId=1" target="middle" class="menu2"><img src="../../images/ico_mail_fjx.gif" width="16" height="16" border="0" align="absmiddle" >寄件夹</a></td>
        </tr>
        <tr>
          <td><img src="../../images/tree4.gif" width="12" height="20"></td>

          <td><a href="maillist.do?mailDirId=2" target="middle" class="menu2"><img src="../../images/ico_mail_cgx.gif" width="16" height="16" border="0" align="absmiddle" >草稿夹</a></td>
        </tr>
        <tr>
          <td><img src="../../images/tree4.gif" width="12" height="20"></td>

          <td><a href="maillist.do?mailDirId=3" target="middle" class="menu2"><img src="../../images/ico_mail_ljx.gif" width="16" height="16" border="0" align="absmiddle" >垃圾箱</a></td>
        </tr>
        <tr>
          <td><img src="../../images/tree4.gif" width="12" height="20"></td>

          <td><a href="./mailinfo/mailinfo.do" target="middle" class="menu2"><img src="../../images/ico_mail_pz.gif" width="16" height="16" border="0" align="absmiddle" >参数设置</a></td>
        </tr>
        <!--
        <tr>
          <td><img src="../../images/tree4.gif" width="12" height="20"></td>

          <td><a href="inceptexteriormaillist.do" target="middle" class="menu2" ><img src="../../images/ico_mail_pop3.gif" width="16" height="16" border="0" align="absmiddle" >接收POP3邮件</a></td>
        </tr>
        
        <tr>
          <td><img src="../../images/tree4.gif" width="12" height="20" ></td>
          <td><a href="./pop3config/pop3configlist.do" target="middle" class="menu2"><img src="../../images/ico_mail_pop3pz.gif" width="16" height="16" border="0" align="absmiddle" >POP3邮件设置</a></td>
        </tr>
        -->
        <tr>
          <td><img src="../../images/tree4.gif" width="12" height="20"></td>

          <td><a href="../../dailyoffice/tools/cardframe.jsp" target="middle"  class="menu2"><img src="../../images/ico_mail_dzb.gif" width="16" height="16" border="0" align="absmiddle">名片夹</a></td>
        </tr>
        <tr>

		<tr>
          <td><img src="../../images/tree4.gif" width="12" height="20"></td>

          <td><a href="../../infoservice/grouplist.do" target="middle" class="menu2"><img src="../../images/ico_mail_dzb.gif" width="16" height="16" border="0" align="absmiddle" >群组设置</a></td>
        </tr>
        <tr>
          <td><img src="../../images/tree4.gif" width="12" height="20"></td>

          <td><a href="maildirectorylist.do?operate=true" target="middle" class="menu2"><img src="../../images/ico_mail_wjjgl.gif" width="16" height="16" border="0" align="absmiddle"  >文件夹管理</a></td>
        </tr>
        <!--<tr>
          <td><img src="../../images/tree4-2.gif" width="12" height="20"></td>

          <td><a href="./mailimportandexport/mailboxindex.do" target="middle" class="menu2"><img src="../../images/ico_mail_drdc.gif" width="16" height="16" border="0" align="absmiddle">导入/导出</span></a></td>
        </tr>-->
        <tr>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>


</body></html>
