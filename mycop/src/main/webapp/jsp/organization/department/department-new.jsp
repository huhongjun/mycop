<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>��֯����-����</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script src="<gdp:context/>/js/gdp.js"></script>
<script src="<gdp:context/>/js/Validator.js"></script>
<script language="JavaScript" type="text/JavaScript">
function selectMan(){
	window.open("<gdp:context/>/organization/getFunctionary.do?action=getFunctionary","��Աѡ��","width=340,height=300,status=no,resizable=no")
}

    function ctrLen() {
	    if( !checkLength( document.DepartmentForm.description.value, 'L<50')) {
		    alert('���ĳ��ȱ���С��50���ַ�!');
	    	return false;
	    }
		return true;
    }
</script>
<script language="JavaScript" type="text/JavaScript">
      //������Աѡ��ģ��
      function  manSelectedPopupWin(){
         //�Ƿ��ѡ,
	 //��ֵΪ"true"��"false"
       var isMutilSelected="false";
	 //�����Ƿ����ͬʱ���ϲ�ѯ
	 //��ֵΪ"true"��"false"
	 var isConfMutil="false";
	 //������Щ��ѡ������
	 //��Ҫ����gever_config.properties�ļ���������
	 //manSelectedCatalogs��ֵ��ȷ��hiddenConf������
	 //��������֮����","����
	 //��"c1,c2"��ʾ����c1��c2��������
	 var hiddenConf="administratorlevel";
         //�Ѿ�ѡ�е��û�id�б�
	 //��,�ֿ�
	 //��"123,12,32,32,"
	 //����123��124��125��126Ϊ�û���idֵ
	 var selectedIds="";

	 var openUrl="<gdp:context/>/manSelectedPopupWin.do?action=selectMan&";
	     openUrl+="isMutilSelected="+isMutilSelected;
	     openUrl+="&isConfMutil="+isConfMutil;
         openUrl+="&hiddenConf="+hiddenConf;
         openUrl+="&selectedIds="+selectedIds;
         window.open(openUrl,"��Աѡ��","width=400,height=400,status=no,resizable=no");
       }
	  

	  	function setParams(param){
        k = param;
		document.DepartmentForm.functionary.value=k
    }

      //���·�����Ҫ�����߰���ʵ�������д
      //���齫returnString���뵽������ҳ�����������ȥ����
      //�Ա��ָõ��ýӿڵļ����
	  //�����û�ID�����ĸ�ʽ�ǣ�123��345��23��234�����û���
      function manSelectedPopupWinReturn(returnIDString,returnNameString){
       
		 setParams(returnNameString);
    }
    </script>

</head>
<body class="bodybg" onload="JavaScript:document.forms[0].code.focus();">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>
<html:form action="/DepartmentAction">
<table width="430" align="center">
  <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr align="center">
    <td colspan="4" class="TableTitleText">������֯</td>
  </tr>
  <tr>
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr>
    <td nowrap class="InputLabelCell" >��֯����:</td>
    <td colspan="3" ><html:text styleClass="input2"  property="code" validatetype="notempty" msg="��֯���벻��Ϊ��"  size="30" maxlength="15" /><font color="#FF0000">*</font></td>
    </td>
  </tr>
   <tr>
    <td nowrap class="InputLabelCell" >��֯����:</td>
    <td colspan="3" ><html:text styleClass="input2"  property="name" validatetype="notempty" msg="��֯���Ʋ���Ϊ��" size="30" maxlength="25" /><font color="#FF0000">*</font></td>
  </tr>
    <!--libiao add begin-->
   <tr>
    <td nowrap class="InputLabelCell" >���Ÿ�����:</td>
    <td colspan="3" ><html:text styleClass="input2"  property="functionary" size="30" maxlength="25" /></font>

      <!--  <input name="select" type="button" class="button" onClick="javascript:selectMan();" value="ѡ��">
           -->
           <input name="select" type="button" class="button" onClick="javascript:manSelectedPopupWin();" value="ѡ��">


	</td>
  </tr>
<!--libiao add end-->

  <tr>
    <td nowrap class="InputLabelCell" >�ϼ���֯:</td>
    <td colspan="3" ><html:text styleClass="input2" readonly="true" property="parentDepartmentName" size="30" maxlength="15" />
	<html:hidden property="parentDepartment"  />
	</td>
  </tr>
  <tr>
    <td nowrap class="InputLabelCell" >���:</td>
	 <td colspan="3" >
     <html:textarea  styleClass="input2" property="description" rows="5" cols="30"></html:textarea>

  </tr>
  <tr>
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td colspan="3" >
	  <html:button  styleClass="button" property="create" value="ȷ��" onclick="return (validater() && ctrLen() && toAction(this,'create'))"/>&nbsp;
      <html:reset styleClass="button" property="reset" value="����" />&nbsp;
	  <html:button  styleClass="button" property="list" value="����" onclick="toAction(this,'list')"/>
	  <input type="hidden" name="departid" value="<%=request.getParameter("departid")%>">
    </td>
  </tr>
</table>
</html:form>

</body>
</html>
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>
