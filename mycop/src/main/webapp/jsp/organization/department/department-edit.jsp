<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%String context=request.getContextPath();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>��֯����-�޸�</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script src="<gdp:context/>/js/gdp.js"></script>
<script src="<gdp:context/>/js/pub.js"></script>
<script src="<gdp:context/>/js/Validator.js"></script>
<script language="JavaScript" type="text/JavaScript">
function selectMan(){
	window.open("<gdp:context/>/organization/getFunctionary.do?action=getFunctionary","��Աѡ��","width=340,height=300,status=no,resizable=no")
}
	//function setParams(param){
    //    k = param;
	//	document.DepartmentForm.functionary.value=k
  //  }

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
	 
     //�Ѿ�ѡ�е��û�id�б�,��","�ֿ�,��"123,12,32,32,"
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

<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>
<html:form action="/DepartmentAction">
<html:hidden property="id"/>
<table width="430" align="center">
  <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" align="center" class="TableTitleText">��֯����-�޸�</td>
  </tr>
  <tr>
    <td colspan="4" >&nbsp;</td>
  </tr>
  <!--
  <tr>
    <td nowrap class="InputLabelCell" >��֯ID:</td>
    <td colspan="3" ><html:text property="id" readonly="true" size="30" /></td>
  </tr>
  -->
  </tr>
  <tr>
    <td nowrap class="InputLabelCell" >��֯����:</td>
    <td colspan="3" ><html:text styleClass="input2"  validatetype="notempty" msg="��֯���벻��Ϊ��"   property="code" size="30" maxlength="15" /><font color="#FF0000">*</font></td>
    </td>
  </tr>
   <tr>
    <td nowrap class="InputLabelCell" >��֯����:</td>
    <td colspan="3" ><html:text styleClass="input2"  validatetype="notempty" msg="��֯���Ʋ���Ϊ��"  property="name" size="30" maxlength="25" /><font color="#FF0000">*</font></td>
  </tr>

 <tr>
    <td nowrap class="InputLabelCell" >���Ÿ�����:</td>
    <td colspan="3" ><html:text styleClass="input2"  property="functionary" size="30" maxlength="25" /><font color="#FF0000"></font>
	<!--input name="select" type="button" class="button" onClick="javascript:selectMan();" value="ѡ��"-->
	 <input name="select" type="button" class="button" onClick="javascript:manSelectedPopupWin();" value="ѡ��"></td>
  </tr>

  <tr>
    <td nowrap class="InputLabelCell" >�ϼ���֯:</td>
    <td colspan="3" >
		    <logic:equal name="DepartmentForm" property="parentDepartmentName" value="null">
		    <input type="text" class="input2" value="��˾" size="30" maxlength="25" readonly="true" />
	            </logic:equal>
		    <logic:notEqual name="DepartmentForm" property="parentDepartmentName" value="null">
		    <html:text  styleClass="input2"  property="parentDepartmentName" size="30" maxlength="25" readonly="true" />
	            </logic:notEqual>

	<html:hidden property="parentDepartment"  />
	<!--html:text property="parentDepartmentName" size="25" maxlength="25" readonly="true" onclick="getDepartment(this.form.parentDepartment,'DepartmentAction.do?action=getTreeDialog',this.value)"/-->
	</td>
    </td>
  </tr>
  <tr>
    <td nowrap class="InputLabelCell" >���:</td>
	 <td colspan="3" ><html:textarea  styleClass="input2" property="description" rows="4" cols="30" maxlength="50"></html:textarea>

  </tr>
 <tr>
    <td >&nbsp;</td>
    <td colspan="3" >
	  <html:submit styleClass="button" property="create" value="ȷ��" onclick="return (validater() && ctrLen()  && toAction(this,'update'))"/>&nbsp;
      <html:reset styleClass="button" property="reset" value="����" />&nbsp;
	  <html:button  styleClass="button" property="list" value="����" onclick="toAction(this,'list')"/>
	  <input type="hidden"  name="departid" value="<%=request.getParameter("departid")%>">
	  <input type="hidden" name="page" value="<%=request.getParameter("page")%>">
    </td>
  </tr>
</table>
</html:form>
<SCRIPT LANGUAGE="JavaScript">
<!--
 /*var pre=document.forms[0].parentDepartment.value;
 function setOldValue(){
     if(pre!=document.forms[0].parentDepartment.value)
        document.forms[0].action=document.forms[0].action+"&predeptid="+pre
 }*/
//-->
</SCRIPT>
</body>
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>

