<%@ page contentType="image/jpeg;charset=GBK" %>
<%@ page import="javax.imageio.*" %>
<%@ page import="com.zhjedu.util.Image"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
Image image=new Image();


ImageIO.write(image.creatImage(request), "JPEG", response.getOutputStream());

%>