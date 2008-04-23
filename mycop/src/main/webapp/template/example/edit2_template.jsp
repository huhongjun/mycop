<%@ include file="/WEB-INF/jspf/templates/header2.jspf" %>
<html>
<head>
<title><!-- 页面标题区 --></title>
</head>
<body class="bodybg">
<gmenu:getpathtag/>
<form method="post" action="">
<table width="50%" align="center" class="EditTable">
  <tr> 
    <td class="EditTitle"><!-- 标题区 --></td>
  </tr>
  <tr> 
    <td> 
      <table width="100%" class="EditBody">
        <tr> 
          <td width="30%">&nbsp;</td>
          <td width="70%">&nbsp;</td>
        </tr>  
        <tr> 
          <td class="EditLabel"><!-- 标签区 --></td>
          <td class="EditContent"><!-- 内容区 --></td>
        </tr>  
        <tr> 
          <td clospan="2">&nbsp;</td>
        </tr>         
        <tr>
          <td>&nbsp;</td> 
          <td>
            <!-- 按钮操作区 -->
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>      
</form>
</body>
</html>
<%@ include file="/WEB-INF/jspf/templates/footer2.jspf" %>
