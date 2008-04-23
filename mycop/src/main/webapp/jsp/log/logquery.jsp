<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-pager.tld" prefix="smile" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<script src="<gdp:context/>/js/Validator.js"></script>


<html>
<style type="text/css">
<!--
.style1 {font-size: 12}
-->
</style>
<jsp:include page="/jsp/jsp_css.jsp" />
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/<gdp:context/>.js"></script>
<script language="javascript" src="<gdp:context/>/js/pub.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>操作日志</title>
<SCRIPT LANGUAGE=javascript FOR=window EVENT=onload>
<!--
    if(navigator.appName=="Netscape") document.all = document.getElementById;
    var context='<gdp:context/>';
	var s = new Object();
	s.mtype=""
	window.returnValue = s;
	//-->
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
    var ns = navigator.appName == "Netscape";

	//查询返回
	function doQuery()	{
		var s = new Object();
		var formTJ = document.forms[0];
        var strDel = '0';
		if (formTJ.elements["selectType"][0].checked==true){

			s.action=formTJ.elements["action"].value;
			s.type=formTJ.elements["type"].value;
			s.module=formTJ.elements["module"].value;
			s.username=formTJ.elements["username"].value;
			s.beginDate=formTJ.elements["beginDate"].value;
			s.endDate=formTJ.elements["endDate"].value;
            s.actionType=formTJ.elements["actionType"].value;
			s.typeSelect = 0;

		} else {

			s.typeSelect = 1;
		}

		//是否导出至 文件
		if (formTJ.elements["isExport"].checked==true)
			s.isExport = 1;
		else
			s.isExport = 0;
		//alert("query type=" + s.typeSelect);
        // Modify By Eddy 20041117---------------------------\\\\
        // 跳过没有选择任何记录而做删除操作
            
        	if (s.typeSelect == 1){
		        for(var i = 0; i < parent_form.elements.length; i++) {
        			if (parent_form.elements[i].type == 'checkbox') {
		        	//'checkbox5' 是选择当前表单所有checkbox的复选框的名字。剔除
                      if(parent_form.elements[i].checked && parent_form.elements[i].name != 'checkbox5') {
		        		  strDel = '1';
			          }
        			}
		        }
        	}
        	if(s.typeSelect == 1 && strDel == '0') {
                 alert('你没有选择任何记录!');
        		 return false;
        	}
        // Modify By Eddy 20041117-----------------------------////

		if ( k.actionType ==1){
			if (confirm("是否真的删除吗?")==false)
				return false;
		} else if ( k.actionType ==2){
			if (confirm("是否真的导出吗?")==false){
				return false;

			}


		}
        //====================================================================
        // 胡勇修改
        // Netscape 不能使用模态对话框 showModelDialog()
		if(ns){
            window.opener.setParams(s);
            window.opener.doQueryLast(k.actionType);
        } else{
            window.returnValue=s;
        }
        //====================================================================
		window.close();
	}
	function sclose(){
		window.close();
	}
	//重置窗体数据
	function doReset()	{
		formTJ.elements["beginDate"].value="";
		formTJ.elements["endDate"].value="";

		formTJ.elements["module"].value="";
		formTJ.elements["action"].value="";
		formTJ.elements["type"].value="0";
		formTJ.elements["username"].value="";

	}


//-->
</SCRIPT>
</head>

<body class="bodybg" onload="JavaScript:document.forms[0].beginDate.focus">
<!--leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">-->
<form action="" name="LogForm" method="post">
<table style="margin-left:8px" align="center" width="390" class="InputFrameMain"><tr>
  <br>
  <tr>
    <td nowrap><table border=0 align=center cellpadding=1 cellspacing=1 style="Margin:5px 5px 5px 5px">
    <tr class=InputFrameLine><td colspan="2">
    <table id="noQuery" class=InputFrameLine style="display:block">
    <tr class=InputFrameLine>
    <td class='InputLabelCell' >选择条件</td>

    <td class=style1 nowrap><nobr>
      <input type="radio" name="selectType" value="0" checked>按条件

      <input type="radio" name="selectType" value="1">
      按已选择的记录数<INPUT TYPE="hidden" name="actionType" value= 0></nobr></td>
  </tr></table>
  </td></tr>
	<tr class=InputFrameLine>

		  <td width=100 class='InputLabelCell' nowrap>开始日期：</td>
		  <td width=250 class='InputAreaCell' nowrap><input name="beginDate" class="input"  maxlength="10" readonly="true" onclick="getdate(this,'<%=request.getContextPath()%>/js/',event);"  ></td>
	  </tr>
	  <tr class=InputFrameLine>
		  <td width=100 class='InputLabelCell' nowrap>结束日期：</td>
		  <td width=250 class='InputAreaCell' nowrap><input  name="endDate" class="input"  maxlength="10" readonly="true"  onclick="getdate(this,'<%=request.getContextPath()%>/js/',event);" ></td>
	  </tr>
	   <tr class=InputFrameLine>
		  <td width=100 class='InputLabelCell' nowrap>用户名包含：</td>
		  <td width=250 class='InputAreaCell' nowrap><input type="text" name="username" class="input"  maxlength="10" style="width:100%" /></td>
	  </tr>
	  <tr class=InputFrameLine>
		  <td width=100 class='InputLabelCell' nowrap>模块包含：</td>
		  <td width=250 class='InputAreaCell' nowrap><input type="text" name="module" class="input"  maxlength="10" style="width:100%" /></td>
	  </tr>
	  <tr class=InputFrameLine>
		  <td width=100 class='InputLabelCell' nowrap>动作包含：</td>
		  <td width=250 class='InputAreaCell' nowrap><input type="text" name="action" class="input"  maxlength="10" style="width:100%" /></td>
	  </tr>
	  <tr class=InputFrameLine>
		  <td width=100 class='InputLabelCell' nowrap>类型：</td>
		  <td width=250 class='InputAreaCell' nowrap>
			  <select name="type">
	          <option value="0">所有类型</option>
        </select>

		</td>
	  </tr>
	  <tr class=InputFrameLine id ="exportfile" style="display:block" >
	  <td class='InputLabelCell'> 导出至文件：
	  </td>
	  <td><input name ="isExport" type="checkbox" id="isExport" value='1'></td>
	  </tr>
	  <tr class=InputFrameLine  >
          <td  colspan="2" class='f12'> （*注:如果未填写条件,默认是所有日志资料 ） </td>
        </tr>
	  <tr>
		<td align=middle colspan=2 height=20>
		 <html:button styleClass="button" property="cmdQuery" onclick="doQuery()" value="确定"/>
		  <html:reset styleClass="button" property="cmdReset" value="重置" />
		   <html:button styleClass="button" property="cmdClose" onclick="javascript:window.close();return false" value="关闭"/>



		</td>
	</tr>

</table>
</form>

<IFRAME frameBorder=1 id=myexport src="" style="display:none" >
</IFRAME>
</body>
</html>

<script language=javascript>
<!--

	var sBH,sMC,sGG,sSYBM,sSY_UID,sLB,sDQZT,sBZ,scope;
    //==============================================================================
    if(ns) var k = window.opener.getParams();
    else   var k = window.dialogArguments;
    //==============================================================================
    // Modify By Eddy 20041117---------------------------\\\\
    var parent_form = k.curform;
    // Modify By Eddy 20041117---------------------------////
	try{
		document.forms[0].elements["actionType"].value = k.actionType;
		if (k.actionType ==0){
			document.all("noQuery").style.display = "none";
		}
		if (k.actionType !=1){
			document.all("exportfile").style.display = "none";
		}

	}catch(e){}

//-->
</script>
