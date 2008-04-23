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
<title>组织管理-修改</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script src="<gdp:context/>/js/gdp.js"></script>
<script src="<gdp:context/>/js/pub.js"></script>
<script src="<gdp:context/>/js/Validator.js"></script>
<script language="JavaScript" type="text/JavaScript">
function selectMan(){
	window.open("<gdp:context/>/organization/getFunctionary.do?action=getFunctionary","人员选择","width=340,height=300,status=no,resizable=no")
}
	//function setParams(param){
    //    k = param;
	//	document.DepartmentForm.functionary.value=k
  //  }

	function ctrLen() {
	    if( !checkLength( document.DepartmentForm.description.value, 'L<50')) {
		    alert('简介的长度必须小于50个字符!');
	    	return false;
	    }
		return true;
    }
</script>
<script language="JavaScript" type="text/JavaScript">
      //调用人员选择模块
      function  manSelectedPopupWin(){
         //是否多选,
	 //其值为"true"或"false"
       var isMutilSelected="false";
	 //条件是否可以同时符合查询
	 //其值为"true"或"false"
	 var isConfMutil="false";
	 //隐藏哪些可选的条件
	 //需要根据gever_config.properties文件中声明的
	 //manSelectedCatalogs的值来确定hiddenConf的内容
	 //并且条件之间用","隔开
	 //如"c1,c2"表示隐藏c1和c2分类条件
	 var hiddenConf="administratorlevel";
	 
     //已经选中的用户id列表,用","分开,如"123,12,32,32,"
	 //其中123，124，125，126为用户的id值
	 var selectedIds="";

	 var openUrl="<gdp:context/>/manSelectedPopupWin.do?action=selectMan&";
	     openUrl+="isMutilSelected="+isMutilSelected;
	     openUrl+="&isConfMutil="+isConfMutil;
         openUrl+="&hiddenConf="+hiddenConf;
         openUrl+="&selectedIds="+selectedIds;
         window.open(openUrl,"人员选择","width=400,height=400,status=no,resizable=no");
       }
	  

	  	function setParams(param){
        k = param;
		document.DepartmentForm.functionary.value=k
    }

      //以下方法需要调用者按照实际意义改写
      //建议将returnString传入到其所在页面的其它函数去处理，
      //以保持该调用接口的简洁性
	  //返回用户ID，它的格式是：123，345，23，234，和用户名
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
    <td colspan="4" align="center" class="TableTitleText">组织管理-修改</td>
  </tr>
  <tr>
    <td colspan="4" >&nbsp;</td>
  </tr>
  <!--
  <tr>
    <td nowrap class="InputLabelCell" >组织ID:</td>
    <td colspan="3" ><html:text property="id" readonly="true" size="30" /></td>
  </tr>
  -->
  </tr>
  <tr>
    <td nowrap class="InputLabelCell" >组织编码:</td>
    <td colspan="3" ><html:text styleClass="input2"  validatetype="notempty" msg="组织编码不能为空"   property="code" size="30" maxlength="15" /><font color="#FF0000">*</font></td>
    </td>
  </tr>
   <tr>
    <td nowrap class="InputLabelCell" >组织名称:</td>
    <td colspan="3" ><html:text styleClass="input2"  validatetype="notempty" msg="组织名称不能为空"  property="name" size="30" maxlength="25" /><font color="#FF0000">*</font></td>
  </tr>

 <tr>
    <td nowrap class="InputLabelCell" >部门负责人:</td>
    <td colspan="3" ><html:text styleClass="input2"  property="functionary" size="30" maxlength="25" /><font color="#FF0000"></font>
	<!--input name="select" type="button" class="button" onClick="javascript:selectMan();" value="选择"-->
	 <input name="select" type="button" class="button" onClick="javascript:manSelectedPopupWin();" value="选择"></td>
  </tr>

  <tr>
    <td nowrap class="InputLabelCell" >上级组织:</td>
    <td colspan="3" >
		    <logic:equal name="DepartmentForm" property="parentDepartmentName" value="null">
		    <input type="text" class="input2" value="公司" size="30" maxlength="25" readonly="true" />
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
    <td nowrap class="InputLabelCell" >简介:</td>
	 <td colspan="3" ><html:textarea  styleClass="input2" property="description" rows="4" cols="30" maxlength="50"></html:textarea>

  </tr>
 <tr>
    <td >&nbsp;</td>
    <td colspan="3" >
	  <html:submit styleClass="button" property="create" value="确定" onclick="return (validater() && ctrLen()  && toAction(this,'update'))"/>&nbsp;
      <html:reset styleClass="button" property="reset" value="重置" />&nbsp;
	  <html:button  styleClass="button" property="list" value="返回" onclick="toAction(this,'list')"/>
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

