<%@ include file="/WEB-INF/jspf/templates/header.jspf" %>
<form method="post" action="/privilege/roleAction.do">
<table width="50%" align="center" class="EditTable">
  <tr> 
    <td class="EditTitle">�����û�</td>
  </tr>
  <tr> 
    <td> 
      <table width="100%" class="EditBody">
        <tr> 
          <td width="30%">&nbsp;</td>
          <td width="70%">&nbsp;</td>
        </tr>  
        <tr> 
          <td class="EditLabel">�ʺţ�</td>
          <td class="EditContent"><input type="text" class="Input"/></td>
        </tr>  
        <tr> 
          <td class="EditLabel">������</td>
          <td class="EditContent"><input type="text" class="Input"/></td>
        </tr>  
        <tr> 
          <td class="EditLabel">��ַ��</td>
          <td class="EditContent"><textarea class="Input"></textarea></td>
        </tr>  
        <tr> 
          <td clospan="2">&nbsp;</td>
        </tr>         
        <tr>
          <td>&nbsp;</td> 
          <td>
            <input type="submit" value="�ύ" class="Button"/>
            <input type="reset" value="����" class="Button"/>
            <input type="button" value="����" class="Button"/>
            <!-- ��Ȩ����֤�İ�ť����
	    <perm:button property="addbt" styleClass="button" onclick="toAction(this,'toCreate')" value="�½�" rescode="GDP-YHGL" optcode="ALL" />
	    <perm:button property="delbt" styleClass="button" onclick="return toAction(this,'delete','����ȷ��Ҫɾ����ѡ��¼��')" value="ɾ��" rescode="GDP-YHGL" optcode="ALL"/>
	    <perm:button property="searbt" styleClass="button" onclick="toAction(this, 'toQuery')" value="��ѯ" rescode="GDP-YHGL" optcode="VIEW"/>
            -->               
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>      
</form>
<%@ include file="/WEB-INF/jspf/templates/footer.jspf" %>

<!-- 

CSS��ʽ˵����

����		˵��
================================================
EditTable: 	�������ƣ���չ�ã�
EditTitle: 	���ڱ༭ҳ��ı��������༭ҳ���������������ҳ�棩
EditBody:`	�������ƣ���չ�ã�
EditLabel:	���ڱ༭ҳ��ı�ǩ��
EditContent:	���ڱ༭ҳ���������
Input:		�����������������input,textarea��
Button:		���ڰ�ť����ť���Ͱ���submit,reset,button��

-->
