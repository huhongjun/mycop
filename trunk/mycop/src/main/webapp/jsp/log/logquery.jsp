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
<title>������־</title>
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

	//��ѯ����
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

		//�Ƿ񵼳��� �ļ�
		if (formTJ.elements["isExport"].checked==true)
			s.isExport = 1;
		else
			s.isExport = 0;
		//alert("query type=" + s.typeSelect);
        // Modify By Eddy 20041117---------------------------\\\\
        // ����û��ѡ���κμ�¼����ɾ������
            
        	if (s.typeSelect == 1){
		        for(var i = 0; i < parent_form.elements.length; i++) {
        			if (parent_form.elements[i].type == 'checkbox') {
		        	//'checkbox5' ��ѡ��ǰ������checkbox�ĸ�ѡ������֡��޳�
                      if(parent_form.elements[i].checked && parent_form.elements[i].name != 'checkbox5') {
		        		  strDel = '1';
			          }
        			}
		        }
        	}
        	if(s.typeSelect == 1 && strDel == '0') {
                 alert('��û��ѡ���κμ�¼!');
        		 return false;
        	}
        // Modify By Eddy 20041117-----------------------------////

		if ( k.actionType ==1){
			if (confirm("�Ƿ����ɾ����?")==false)
				return false;
		} else if ( k.actionType ==2){
			if (confirm("�Ƿ���ĵ�����?")==false){
				return false;

			}


		}
        //====================================================================
        // �����޸�
        // Netscape ����ʹ��ģ̬�Ի��� showModelDialog()
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
	//���ô�������
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
    <td class='InputLabelCell' >ѡ������</td>

    <td class=style1 nowrap><nobr>
      <input type="radio" name="selectType" value="0" checked>������

      <input type="radio" name="selectType" value="1">
      ����ѡ��ļ�¼��<INPUT TYPE="hidden" name="actionType" value= 0></nobr></td>
  </tr></table>
  </td></tr>
	<tr class=InputFrameLine>

		  <td width=100 class='InputLabelCell' nowrap>��ʼ���ڣ�</td>
		  <td width=250 class='InputAreaCell' nowrap><input name="beginDate" class="input"  maxlength="10" readonly="true" onclick="getdate(this,'<%=request.getContextPath()%>/js/',event);"  ></td>
	  </tr>
	  <tr class=InputFrameLine>
		  <td width=100 class='InputLabelCell' nowrap>�������ڣ�</td>
		  <td width=250 class='InputAreaCell' nowrap><input  name="endDate" class="input"  maxlength="10" readonly="true"  onclick="getdate(this,'<%=request.getContextPath()%>/js/',event);" ></td>
	  </tr>
	   <tr class=InputFrameLine>
		  <td width=100 class='InputLabelCell' nowrap>�û���������</td>
		  <td width=250 class='InputAreaCell' nowrap><input type="text" name="username" class="input"  maxlength="10" style="width:100%" /></td>
	  </tr>
	  <tr class=InputFrameLine>
		  <td width=100 class='InputLabelCell' nowrap>ģ�������</td>
		  <td width=250 class='InputAreaCell' nowrap><input type="text" name="module" class="input"  maxlength="10" style="width:100%" /></td>
	  </tr>
	  <tr class=InputFrameLine>
		  <td width=100 class='InputLabelCell' nowrap>����������</td>
		  <td width=250 class='InputAreaCell' nowrap><input type="text" name="action" class="input"  maxlength="10" style="width:100%" /></td>
	  </tr>
	  <tr class=InputFrameLine>
		  <td width=100 class='InputLabelCell' nowrap>���ͣ�</td>
		  <td width=250 class='InputAreaCell' nowrap>
			  <select name="type">
	          <option value="0">��������</option>
        </select>

		</td>
	  </tr>
	  <tr class=InputFrameLine id ="exportfile" style="display:block" >
	  <td class='InputLabelCell'> �������ļ���
	  </td>
	  <td><input name ="isExport" type="checkbox" id="isExport" value='1'></td>
	  </tr>
	  <tr class=InputFrameLine  >
          <td  colspan="2" class='f12'> ��*ע:���δ��д����,Ĭ����������־���� �� </td>
        </tr>
	  <tr>
		<td align=middle colspan=2 height=20>
		 <html:button styleClass="button" property="cmdQuery" onclick="doQuery()" value="ȷ��"/>
		  <html:reset styleClass="button" property="cmdReset" value="����" />
		   <html:button styleClass="button" property="cmdClose" onclick="javascript:window.close();return false" value="�ر�"/>



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
