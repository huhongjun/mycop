<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.gever.struts.Constant"%>
<%@ taglib uri="/WEB-INF/taglib/struts-tiles.tld" prefix="tiles" %>
<html>
<%String context =request.getContextPath();%>

<%//����Ӿ�� �ж��Ƿ��Դ��ڵ�½״̬
	if(!com.gever.goa.web.util.RequestUtils.isLogon(pageContext)){
		response.sendRedirect(""+context+"/jsp/error/reLogon.jsp");
	}
%>

<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<link href="<%=com.gever.goa.web.util.RequestUtils.getCSSPath(pageContext)%>" id="goastylecss" rel="stylesheet" type="text/css">
<title><tiles:getAsString name="title"/></title>
<SCRIPT LANGUAGE="JavaScript">
<!--
	var nodeValue = "�ҵ�����";
	var isFrame = false;
	var isOpen = false;
	
	init_FormData();

	try{
		getNodePathName(nodeValue,"<%=context%>");
	} catch (e) {
	}

	var isShowFirst = false;
	var isShowLast = false;
	var isShowNext = false;
	var isShowPrevious = false;

	function f_brow(sAction){
		f_Browse(sAction,isOpen,isFrame);
	}
	
	
//-->
</SCRIPT>
<body class="bodybg">
<table width ="95%" >
	<tr>
	<td align="right">
	<table width="150" border="0" cellspacing="0"  cellpadding="0"> 
		<tr align="center" > 
		<td width="25%"><img src="<%=context%>/images/First_no.gif" id="imgFirst" alt="��һ��"width="18"height="13" border="0" style="cursor:'hand'" onclick="f_brow('first')"> 
		</td><td width="25%"> 
		<img id="imgPre" src="<%=context%>/images/Previous_no.gif" alt="��һ��" width="14"height="13" border="0" style="cursor:'hand'" onclick="f_brow('previous')" > </td>
		<td width="25%"> <img src="<%=context%>/images/Next_no.gif" id="imgNext" alt="��һ��" width="14"height="13" border="0" style="cursor:'hand'"  onclick="f_brow('next')" > </td>
		<td width="25%"> 
			<img src="<%=context%>/images/Last_no.gif" id="imgLast" alt="���һ��"width="18"height="13"  style="cursor:'hand'" border="0" onclick="f_brow('last')">
		</td>
		</tr>
	</table>
	</td><tr>
</table>

<tiles:insert attribute="body"/>

<SCRIPT LANGUAGE="JavaScript">
<!--
	try {
		initOrderBy("<%=context%>");
	} catch(e) {}
	//���β˵�

	hideMenus(isOpen,isFrame);
	//�������
	try{	
		if(window.parent!=null){
		
			initToolBar();
	
		}	
	} catch (e){
	}
//-->
</SCRIPT>
</html>