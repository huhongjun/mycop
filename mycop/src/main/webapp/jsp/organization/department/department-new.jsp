<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>组织管理-新增</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script src="<gdp:context/>/js/gdp.js"></script>
<script src="<gdp:context/>/js/Validator.js"></script>
<script language="JavaScript" type="text/JavaScript">
function selectMan(){
	window.open("<gdp:context/>/organization/getFunctionary.do?action=getFunctionary","人员选择","width=340,height=300,status=no,resizable=no")
}

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
         //已经选中的用户id列表
	 //用,分开
	 //如"123,12,32,32,"
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
    <td colspan="4" class="TableTitleText">新增组织</td>
  </tr>
  <tr>
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr>
    <td nowrap class="InputLabelCell" >组织编码:</td>
    <td colspan="3" ><html:text styleClass="input2"  property="code" validatetype="notempty" msg="组织编码不能为空"  size="30" maxlength="15" /><font color="#FF0000">*</font></td>
    </td>
  </tr>
   <tr>
    <td nowrap class="InputLabelCell" >组织名称:</td>
    <td colspan="3" ><html:text styleClass="input2"  property="name" validatetype="notempty" msg="组织名称不能为空" size="30" maxlength="25" /><font color="#FF0000">*</font></td>
  </tr>
    <!--libiao add begin-->
   <tr>
    <td nowrap class="InputLabelCell" >部门负责人:</td>
    <td colspan="3" ><html:text styleClass="input2"  property="functionary" size="30" maxlength="25" /></font>

      <!--  <input name="select" type="button" class="button" onClick="javascript:selectMan();" value="选择">
           -->
           <input name="select" type="button" class="button" onClick="javascript:manSelectedPopupWin();" value="选择">


	</td>
  </tr>
<!--libiao add end-->

  <tr>
    <td nowrap class="InputLabelCell" >上级组织:</td>
    <td colspan="3" ><html:text styleClass="input2" readonly="true" property="parentDepartmentName" size="30" maxlength="15" />
	<html:hidden property="parentDepartment"  />
	</td>
  </tr>
  <tr>
    <td nowrap class="InputLabelCell" >简介:</td>
	 <td colspan="3" >
     <html:textarea  styleClass="input2" property="description" rows="5" cols="30"></html:textarea>

  </tr>
  <tr>
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td colspan="3" >
	  <html:button  styleClass="button" property="create" value="确定" onclick="return (validater() && ctrLen() && toAction(this,'create'))"/>&nbsp;
      <html:reset styleClass="button" property="reset" value="重置" />&nbsp;
	  <html:button  styleClass="button" property="list" value="返回" onclick="toAction(this,'list')"/>
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
