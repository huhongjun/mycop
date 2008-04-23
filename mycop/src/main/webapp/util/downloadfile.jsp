<%@ page language="java" import="com.gever.goa.util.*"%>
<jsp:useBean id="myDownload" scope="page" class="com.gever.goa.web.util.DownLoadFile" />
<%

		
		myDownload.initialize(pageContext);
		String strFileName  = new String("");
		String strFilePath  = new String("");
		strFileName = request.getParameter("filename");
		strFilePath = request.getParameter("filepath");	
		// Download File
		//System.out.println("-----filename----" + strFileName);
		response.reset();
		myDownload.downloadFile(strFilePath,strFileName);

%>

