<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.gever.util.StringUtils"%>
<%
    String context = request.getContextPath();
	String rightUrl = ""+context+"/infoservice/orginfolist.do?paraFlag=2";
	String fid = request.getParameter("fid");
	if (!StringUtils.isNull(fid)){
		rightUrl = ""+context+"/infoservice/orgview.do?paraFlag=2&fid="+fid;
	}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�ޱ����ĵ�</title>
</head>


  <frameset cols="180,*" id="main" >
  <frame name="left" src="<%=context%>/infoservice/orginfotree.do?action=toTree" scrolling="auto"  target="rtop" id="left">
 
    <frame src="<%=rightUrl%>" name="middle"   id="middle">

  
  <noframes>
  <body>

  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

  </body>
  </noframes>
</html>
