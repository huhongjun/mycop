<%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>

<head>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>我的邮件</title>
</head>
<SCRIPT LANGUAGE="JavaScript">
<!--
	window.onbeforeunload = function(){
		exitSystem();
	}
	function exitSystem(){
		//var bYesNo = confirm("是否退出邮件管理系统?");
		var bYesNo=false;
		if (bYesNo == true ){
			window.parent.close();
		}
	
	}	
//-->
</SCRIPT>
<frameset COLS="22%,78%"  style="overflow-x:hidden;overflow-y:hidden;border: none">
  <frame name="contents" src="<gdp:context/>/jsp/menusetup/lefttree.jsp?action=toRoot&nodeid=-1"   scrolling="auto"  target="rtop" id="left">
  <frameset rows="*">
    <frame src="<gdp:context/>/menusetup/right.do?action=toHome&nodeid=-1" name="middle"   id="middle">
 
  </frameset>
  
  <noframes>
  <body>

  <p>此网页使用了框架，但您的浏览器不支持框架。</p>

  </body>
  </noframes>


</html>