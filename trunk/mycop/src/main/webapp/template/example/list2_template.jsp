<%@ include file="/WEB-INF/jspf/templates/header.jspf" %>
<html>
<head>
<title><!-- ҳ������� --></title>
</head>
<body class="bodybg">
<gmenu:getpathtag/>
<table width="95%" align="center" class="ListTable">
  <tr>
    <td class="ListingTitle"><!-- ������ --></td>
  </tr>
  <form method="post" action="">
  <tr align="right">
    <td>
      <!-- ��ť������ -->
    </td>
  </tr>
  <tr>
    <td>
      <table width="100%" cellpadding="2" cellspacing="0" class="ListBody">
        <tr class="ListTitleBar">
          <td width="10%"><!-- �ֶ��� --></td>
          <td width="20%"><!-- �ֶ��� --></td>
          <td width="20%"><!-- �ֶ��� --></td>
          <td width="10%"><!-- �ֶ��� --></td>
          <td width="40%"><!-- �ֶ��� --></td>
        </tr>
	<tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
          <td class="ListContent"><!-- ������ --></td>
          <td class="ListContent"><!-- ������ --></td>
          <td class="ListContent"><!-- ������ --></td>
          <td class="ListContent"><!-- ������ --></td>
	  <td class="ListContent"><!-- ������ --></td>
	</tr>	
      </table>
    </td>
  </tr>
  </form>
  <tr>
    <td>
      <!-- ��ҳ����ͨ����һ����ǩ�� -->
    </td>
  </tr>
</table>
</body>
</html>
<%@ include file="/WEB-INF/jspf/templates/footer.jspf" %>
