<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zhjedu.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 
String count = (String)request.getAttribute("count");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style type="text/css">
<!--
.sec1 {cursor: hand;
font-size:12px;
color: #000000;
width:107;
height:25;
background-image=url(image/x-00100.gif);
}
.style1 {font-size: 12px}
.style11 {color: #164f8e;
	font-weight: bold;
}
.style11 {color: #164f8e; font-weight: bold; font-size: 14px; }
.style12 {color: #0000FF}
.style13 {color: #3b6ba0}
.style14 {	font-size: 36px;
	color: #3B6BA0;
}
.style8 {color: #0000FF; font-size: 12px; }
.style9 {font-size: 14px}
-->
</style>
    <base href="<%=basePath%>">
    
    <title>index_s.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <table width="100%" height="452"  border="0" align="left" cellpadding="0" cellspacing="0">
  <tr>
    <td width="569" height="192" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="0" valign="bottom">&nbsp;</td>
      </tr>
    </table>
        <table width="93%" height="170"  border="0" align="center" cellpadding="1" cellspacing="0">
          <tr valign="top">
            <td width="68%" height="172"><table width="91%"  border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="30" class="ee"><table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td height="25"><span class="style9">您今日可以参加的有：</span></td>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td height="25" valign="bottom"><table width="90%" height="20"  border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="5%"><img src="assignment_s/image/2_34%5B1%5D.jpg" width="7" height="9" /> <span class="style12"></span></td>
                        <td width="95%"><span class="style8"><a href="assignment.do?method=getStatus&status=0&courseId=<%=request.getAttribute("courseId") %>" ><%=count%>个新考试</a></span></td>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td height="2"><img src="assignment_s/image/dh%5B1%5D.jpg" width="343" height="5" /></td>
                </tr>
                <!-- 
                <tr>
                  <td height="25" valign="bottom"><table width="90%" height="20"  border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="5%"><img src="assignment_s/image/2_34%5B1%5D.jpg" width="7" height="9" /> <span class="style12"></span></td>
                        <td width="95%"><span class="style8"><a href="/newusertestnode/usertestnodecurr.flow" >2个新测验</a></span></td>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td height="2"><img src="assignment_s/image/dh%5B1%5D.jpg" width="343" height="5" /></td>
                </tr>
                <tr>
                  <td height="25"><table width="90%" height="20"  border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="5%"><img src="assignment_s/image/2_34%5B1%5D.jpg" width="7" height="9" /> <span class="style12"></span></td>
                        <td width="95%"><span class="style8"><a href="/newusertestnode/usertestnodecurr.flow" >2个新考试</a></span></td>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td height="2"><img src="assignment_s/image/dh%5B1%5D.jpg" width="343" height="5" /></td>
                </tr>
                 -->
            </table></td>
            <td width="32%"><table width="125" height="156"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="156" background="image/2_15[1].jpg"><div align="center">
                      <table width="90%"  border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td height="10"><div align="center" class="style13">今天是：</div></td>
                        </tr>
                        <tr>
                          <td height="10"></td>
                        </tr>
                        <tr>
                          <td height="10"><div align="center" class="style13"><span class="style1">
                                  <script language="JavaScript" type="text/javascript"><!-- 
                                  var today = new Date();
                                  
                              document.write(today.getFullYear()+"年"+(today.getMonth() + 1)+"月"+today.getDate()+"日")
                              //--></script></span>
                          </div></td>
                        </tr>
                        <tr>
                          <td height="10"></td>
                        </tr>
                        <tr>
                          <td height="10"><div align="center" class="style13">
                              <script language="JavaScript" type="text/javascript"><!-- 
                              var x = today.getDay();
                              if(x == "0"){
                              	x = "日";
                              }
                              document.write("星期"+ x)
                              //--></script>
                          </div></td>
                        </tr>
                      </table>
                  </div></td>
                </tr>
            </table></td>
          </tr>
      </table></td>
    <td width="4" rowspan="3" background="image/d[1].jpg">　</td>
    <td width="212" rowspan="3" valign="top"><table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td><table width="95%"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td valign="bottom"><img src="assignment_s/image/2_13[1].jpg" width="225" height="34" /></td>
          </tr>
          <tr>
            <td height="80" valign="middle" background="assignment_s/image/2_36[1].jpg"><table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="20" colspan="2"><span class="style1">欢迎您，登陆在线考试系统！</span></td>
              </tr>
              <tr>
                <td width="20%" height="20"><span class="style1">用户名：</span></td>
                <td width="53%" height="20"><span class="style1"><%=UserSessionInfo.getStudentRealName(request) %></span></td>
              </tr>
              <tr>
                <td width="20%" height="20"><span class="style1">登录IP：</span></td>
                <td width="53%" height="20"><span class="style1"><%=request.getRemoteAddr() %></span></td>
              </tr>
              <tr>
                <td height="20">　</td>
                <td height="20">　</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="18" valign="top"><img src="assignment_s/image/2_39[1].jpg" width="225" height="17" /></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="141" valign="top"><table width="95%"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td valign="bottom"><img src="assignment_s/image/x-015.gif" width="223" height="250" /></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="4" background="image/dh[1].jpg"><img src="assignment_s/image/dh%5B1%5D.jpg" width="343" height="5" /></td>
  </tr>
  <tr>
    <td height="255" valign="top"><p>&nbsp;</p></td>
  </tr>
</table>
  </body>
</html>
