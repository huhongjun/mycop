<%@ include file="/WEB-INF/jspf/templates/header2.jspf" %>
<html>
<head>
<title>�û�����</title>
</head>
<body class="bodybg">
<gmenu:getpathtag/>
<table width="95%" align="center" class="ListTable">
  <tr>
    <td class="ListingTitle">ϵͳ�û�</td>
  </tr>
  <form method="post" action="/listUser.do">
  <tr align="right">
    <td>
      <input type="submit" value="����" class="Button"/>
      <input type="reset" value="ɾ��" class="Button"/>
      <input type="button" value="����" class="Button"/>
      <!-- ��Ȩ����֤�İ�ť����
      <perm:button property="addbt" styleClass="button" onclick="toAction(this,'toCreate')" value="�½�" rescode="GDP-YHGL" optcode="ALL" />
      <perm:button property="delbt" styleClass="button" onclick="return toAction(this,'delete','����ȷ��Ҫɾ����ѡ��¼��')" value="ɾ��" rescode="GDP-YHGL" optcode="ALL"/>
      <perm:button property="searbt" styleClass="button" onclick="toAction(this, 'toQuery')" value="��ѯ" rescode="GDP-YHGL" optcode="VIEW"/>
      -->      
    </td>
  </tr>
  <tr>
    <td>
      <table width="100%" cellpadding="2" cellspacing="0" class="ListBody">
        <tr class="ListTitleBar">
          <td width="10%">ѡ��</td>
          <td width="20%">�ʺ�</td>
          <td width="20%">����</td>
          <td width="10%">����</td>
          <td width="40%">��ַ</td>
        </tr>
	<tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
          <td class="ListContent"><input type="checkbox"/></td>
          <td class="ListContent">001</td>
          <td class="ListContent">Jack</td>
          <td class="ListContent">20</td>
	  <td class="ListContent">Canton</td>
	</tr>
	<tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
          <td class="ListContent"><input type="checkbox"/></td>
          <td class="ListContent">002</td>
          <td class="ListContent">Kate</td>
          <td class="ListContent">20</td>
	  <td class="ListContent">Canton</td>
	</tr>
	<tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
          <td class="ListContent"><input type="checkbox"/></td>
          <td class="ListContent">003</td>
          <td class="ListContent">Rose</td>
          <td class="ListContent">20</td>
	  <td class="ListContent">Canton</td>
	</tr>
	<tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
          <td class="ListContent"><input type="checkbox"/></td>
          <td class="ListContent">004</td>
          <td class="ListContent">Zen</td>
          <td class="ListContent">20</td>
	  <td class="ListContent">Canton</td>
	</tr>		
      </table>
    </td>
  </tr>
  </form>
  <tr>
    <td>
      <!-- ��ҳ����ͨ����һ����ǩ�� -->
      <!-- GDP��ҳ��ǩ
      <gmenu:allpager name="pageControl" page="/privilege/roleAction.do?action=list"  usepic="true"/>
      -->      
    </td>
  </tr>
</table>
</body>
</html>
<%@ include file="/WEB-INF/jspf/templates/footer2.jspf" %>

<!-- 

CSS��ʽ˵����

����		˵��
================================================
ListTable: 	�������ƣ���չ�ã�
ListingTitle: 	�����б�ҳ��ı�����
ListBody:`	�������ƣ���չ�ã�
ListTitleBar:	�����б�ҳ��ı�����
ListContent:	�����б�ҳ���������
Button:		���ڰ�ť����ť���Ͱ���submit,reset,button��

-->