<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%@ page import="com.gever.struts.Constant"%>
<%
String context =request.getContextPath();
%>

<title>������</title>
	<script LANGUAGE="javascript">
	<!--
	function onChange(strType){
		document.forms[0].action = "businessCardIndex.do?actionFlag=find&type="+strType;
		document.forms[0].submit();
}	
	function showSearch(){
		var flag =false;
		flag = document.getElementById("showHeader").checked

		if (flag==true ) {
			document.getElementById("searchHeader").style.display = "block"
			bWhere.innerHTML ="������Ϣ��ѯ���� "
		} else {
			document.getElementById("searchHeader").style.display = "none"
			bWhere.innerHTML ="��ʾ��Ϣ��ѯ���� "
		}
	}
	//type,Ϊ����,��ֱ���ύ��
	function doSearch(type){
		if (isDate(document.forms[0].elements["startDate"].value) == false){
			alert("���ڲ���Ϊ��;���ڲ���,��ȷ��ʽ:'2000-01-02'");
			return false;
		}
		if (isDate(document.forms[0].elements["endDate"].value) == false){
			alert("���ڲ���Ϊ��;���ڲ���,��ȷ��ʽ:'2000-01-02'");
			return false;
		}

		var startDate = document.all("startDate").value;
		var endDate = document.all("endDate").value;
		var user = document.all("searchUser").value;
		var searchMsg = document.all("searchMsg").value;
		var sqlWhere = document.all("sqlWhere");

		var strWhere = " AND sn = '" + <%=session.getAttribute(Constant.USER_ID)%>+"'";
		if (document.all("type").value !="0"){
			strWhere += " and type ='" + document.all("type").value + "' ";
		}
		
		//ʱ�䷶Χ
		if (startDate=="" && endDate !=""){
			strWhere += " and senddate<='" +endDate +" 23:59:59' ";
		} else if (endDate=="" && startDate !=""){
			strWhere += " and senddate>='" +startDate +" 00:00:00' ";
		} else if(endDate!="" && startDate !=""){
			strWhere += " and senddate>='" +startDate + " 00:00:00' and senddate<='" + endDate + " 23:59:59' ";
		} 
		//������
		if (user !=""){
			strWhere += " and username like '%" + replace(user,"'","''") + "%' ";
		}
		//��Ϣ����
		if (searchMsg !=""){
			strWhere += " and Msg like '%" + replace(searchMsg,"'","''") + "%' ";
		}
		
		if (type == true){
			var frmObj = document.forms[0];
			document.all("sqlWhere").value = strWhere;
	//alert(strWhere);
			var strAction = frmObj.action;
			var pos = strAction.indexOf("?");
			if (pos>0){
				strAction = strAction(0,pos-1);
			}
			strAction+= "?actionFlag=search";
			frmObj.action = strAction;

			frmObj.submit();
		} else {
			return strWhere;
		}
	}
	function doExport(){
		var strWhere = doSearch(false);
		//alert(strWhere);
		sUrl = "<%=context%>/servlet/RsToFile?type=smsoutbox&fileName=���ŷ�����.txt&sqlwhere=" + strWhere ;
		//window.open(sUrl);		
		document.frames["export"].location.href = sUrl;
		//sUrl ="<%=context%>/util/downloadfile.jsp?filepath=uploadfiles/smsoutbox.txt&filename=���ŷ�����.txt";
		setTimeout("doRsExport()",500);
		//document.frames["export"].location.href = sUrl;
	
	}
	function doRsExport(){
		return true;
	}
	-->
	</script>

</head>
<body class="mailbody2">
<p>&nbsp;</p>
<table width="98%" border="0" cellpadding="0" cellspacing="1">

   <tr> 
    <td colspan="7" align="center" class="TableTitleText">���ŷ�����</td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
    <td> 
	<html:form action="/dailyoffice/smsmgr/listsmsoutbox"> 
	<table width=100%  border=0 align=center>
        <tr valign=center  class="InputLabelCell"> 
          <td width="44%"><input name="showHeader" type="checkbox" id="showHeader4" onClick="javascript:showSearch()" checked> 
            <span id="bWhere"> ���ز�ѯ����</span></td>
          <td width="56%" align=right>&nbsp; 
		  <goa:button  styleClass="button" property="delete" value="ɾ��"  onclick="doDelete()" operation="SJDX.OPT"/>
		  &nbsp; <goa:button  styleClass="button" property="exportall" value="����"  onclick="doExport()" operation="SJDX.OPT"/>&nbsp;  <goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="SJDX.VIEW"/>
          </td>
        </tr>
        <tbody id="searchHeader" style="display:block">
		 <tr> 
            <td colspan="2"><hr size="1"></td>
          </tr>
          <tr class="t12" valign=center> 
            <td colspan="3" align=right><div align="left"></div>
              <table width=98%  border=0 align=left>
                <tbody id="searchHeader" style="display:block">
                  <tr  class="InputLabelCell" valign=center> 
                    <td width="8%" align=right nowrap> <div align="right">��ʼ���ڣ�</div></td>
                    <td width="35%" align=left> <html:text property="startDate" readonly="false"   validatetype="date"  msg="���ڲ���Ϊ��;���ڲ���,��ȷ��ʽ:'2000-01-02'"  styleClass="input"  onlytype ="date"   style="WIDTH: 100px"/></td>
                    <td width="9%" align=right nowrap> <div align="right">��ֹ���ڣ�</div></td>
                    <td width="48%" align=left><html:text property="endDate" readonly="false"   validatetype="date"  msg="���ڲ���Ϊ��;���ڲ���,��ȷ��ʽ:'2000-01-02'"  styleClass="input"  onlytype ="date"   style="WIDTH: 100px"/></td>
                  </tr>
                  <tr  class="InputLabelCell" valign=center> 
                    <td width="8%" align=right nowrap>���ͣ�</td>
                    <td width="35%" align=left> <html:select property="type" disabled="false" size="1"> 
                      <html:option value="0"  >ȫ�����</html:option> <html:option value="1"  >�ѷ���</html:option> 
                      <html:option value="2"  >���ڷ���</html:option> <html:option value="4"  >������</html:option> 
                      <html:option value="3"  >����ʧ��</html:option> </html:select> 
                    <td width="9%" align=right>�����ˣ�</td>
                    <td align=left> <html:text property="searchUser"  maxlength="30" style="WIDTH: 100px" styleClass="input" /> 
                    </td>
                  </tr>
                  <tr height="20"  class="InputLabelCell" > 
                    <td  align=right nowrap>�������ݣ�</td>
                    <td    align=left><html:text property="searchMsg"  maxlength="70" style="WIDTH: 160px" styleClass="input" /><html:hidden name="SmsMgrForm" property="sqlWhere"/> 
                    </td>
                    <td align=right>&nbsp;&nbsp; </td>
                    <td align="left"> 
					<goa:button  styleClass="button" property="delete" value="����"  onclick="doSearch(true)" operation="SJDX.VIEW"/>
                    </td>
                  </tr>
				    <tr> 
            <td colspan="7"><hr size="1"></td>
          </tr>
                </tbody>
                
              </table></td>
          </tr>
        
      </table>
      <tr>
	  <td></td>
	  <td>
	 

  <table width="100%"border="0" cellspacing="0" cellpadding="2">

        <tr height="20">
            <td width="32" class="Listtitle" align="center" ><input alt="ѡ��" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>

      		 <td  width="7%" class="Listtitle"><div align="right">���</div></td>
          <td  width="20%" id=".senddate"  class="Listtitle" onclick="onchangeOrderBy(this)" ><div align="left">����ʱ��</div></td>
          <td  width="30%"  id=".msg" class="Listtitle" onclick="onchangeOrderBy(this)"><div align="left">��Ϣ����</div></td>
          <td  width="13%" id=".mbno" class="Listtitle" onclick="onchangeOrderBy(this)"><div align="left">�ֻ�����</div></td>
          <td  width="10%" id=".username" class="Listtitle" onclick="onchangeOrderBy(this)"><div align="left">������</div></td>
          <td   id=".type" class="Listtitle" onclick="onchangeOrderBy(this)"><div align="left">״̬</div></td>

           		  
    </tr>
   <logic:iterate id="ps" name="SmsMgrForm" property="pageControl.data" type="com.gever.goa.dailyoffice.smsmgr.vo.OutBoxVO" indexId="index">
	  <tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/dailyoffice/smsmgr/viewsmsoutbox.do?fid=" +ps.getId()+";;"+ps.getType()+ "',false)"%>">
	   <td align="center" class="listcellrow">
			<input type='checkbox' name='fid' value='<goa:write name="ps" property="id" filter="true"/>;;<goa:write name="ps" property="type" filter="true"/>'>   		  
		</td>

		<td class="listcellrow" nowrap align=right> <%=((Integer)pageContext.getAttribute("index")).intValue()+1%></td>
		<td class="listcellrow" nowrap align=left><goa:write name='ps' property='senddate' filter='true' />&nbsp;<goa:write name='ps' property='sendtime' filter='true' /></td>
		<td class="listcellrow" nowrap align=left> 
		<goa:write name='ps' property='msg' filter='true' maxlen="20"/>&nbsp;

		</td>
		<td class="listcellrow" nowrap align=left><goa:write name='ps' property='mbno' filter='true' />&nbsp;</td>
		<td class="listcellrow" nowrap align=center><goa:write name='ps' property='username' filter='true' />&nbsp;</td>
		<td class="listcellrow4" align=left> <logic:equal name="ps" property="type" value="1"> 
		  �ѷ��� </logic:equal> <logic:equal name="ps" property="type" value="2"> 
		  ���ڷ��� </logic:equal> <logic:equal name="ps" property="type" value="3"> 
		  ����ʧ�� </logic:equal> <logic:equal name="ps" property="type" value="4"> 
		  ��ʱ���� </logic:equal>&nbsp; 
		  </td>
	</tr>
	</logic:iterate>

	<tr align="right"> 
	  <td colspan="7" align="center" class="f12">
	  <html:hidden name="SmsMgrForm" property="orderType"/>
		<html:hidden name="SmsMgrForm" property="orderFld"/>
		<goa:pagelink name="SmsMgrForm" property="pageControl" />  
	  </td>
   </tr>
</table>
	  </td></tr>
</table>
	</html:form>
	<IFRAME frameBorder=1 id=export src="" style="display:none" >
</IFRAME>
</body>
</html>

