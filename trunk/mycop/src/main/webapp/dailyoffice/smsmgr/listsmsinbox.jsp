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

		
		var strWhere = "  and msg_type='2' AND receiver='" + <%=session.getAttribute(Constant.USER_ID)%>+"'";
		//ʱ�䷶Χ
		if (startDate=="" && endDate !=""){
			strWhere += " cast (send_time as char(20))<=" +endDate +" ";
		} else if (endDate=="" && startDate !=""){
			strWhere += " and cast (send_time as char(20))>='" +startDate +"' ";
		} else if(endDate!="" && startDate !=""){
			strWhere += " and cast (send_time as char(20))>='" +startDate + "' and cast (send_time as char(20))<='" + endDate + "' ";
		} 
		//������
		if (user !=""){
			strWhere += " and USER_CODE like '%" + replace(user,"'","''") + "%' ";
		}
		//��Ϣ����
		if (searchMsg !=""){
			strWhere += " and CONTENT like '%" + replace(searchMsg,"'","''") + "%' ";
		}
		
		if (type == true){
			var frmObj = document.forms[0];
			document.all("sqlWhere").value = strWhere;

			var strAction = frmObj.action;
			var pos = strAction.indexOf("?");
			if (pos>0){
				strAction = strAction(0,pos-1);
			}
			strAction+= "?actionFlag=search";
			frmObj.action = strAction;
			//alert (strWhere);
			frmObj.submit();
		} else {
			return strWhere;
		}
	}
	function doExport(){
		var strWhere = doSearch(false);
		//alert(strWhere);
		var	sUrl = "/goa/servlet/RsToFile?type=smsinbox&fileName=�����ռ���.txt&sqlwhere=" + strWhere ;
		//alert(sUrl);
		document.frames["export"].location.href = sUrl;
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
    <td colspan="7" align="center" class="TableTitleText">�����ռ���</td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
    <td> 
	<html:form action="/dailyoffice/smsmgr/listsmsinbox"> 
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
                    
                    <td width="9%" align=right>�����ˣ�</td>
                    <td align=left> <html:text property="searchUser"  maxlength="30" style="WIDTH: 100px" styleClass="input" /> 
                    </td>
                  
              
                    <td  align=right nowrap>�������ݣ�</td>
                    <td    align=left><html:text property="searchMsg"  maxlength="70" style="WIDTH: 160px" styleClass="input" /><html:hidden name="SmsInBoxForm" property="sqlWhere"/> 
                   
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
            <td width="20" class="Listtitle" align="center" ><input alt="ѡ��" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>

      		 <td  width="7%" class="Listtitle"><div align="right">���</div></td>
          <td  width="20%" id=".send_time"  class="Listtitle" onclick="onchangeOrderBy(this)" ><div align="left">����ʱ��</div></td>
          <td  width="30%"  id=".content" class="Listtitle" onclick="onchangeOrderBy(this)"><div align="left">��Ϣ����</div></td>
          <td  width="13%" id=".user_code" class="Listtitle" onclick="onchangeOrderBy(this)"><div align="left">�ֻ�����</div></td>
          <td  id=".user_name" class="Listtitle" onclick="onchangeOrderBy(this)"><div align="left">������</div></td>
        

           		  
    </tr>
   <logic:iterate id="ps" name="SmsInBoxForm" property="pageControl.data" type="com.gever.goa.dailyoffice.message.vo.MessageVO" indexId="index">
	  <tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('/goa/dailyoffice/smsmgr/viewsmsinbox.do?fid=" +ps.getSerial_num()+ "',false)"%>">
	   <td width="20" align="center" class="listcellrow">
			<input type='checkbox' name='fid' value='<goa:write name="ps" property="serial_num" filter="true"/>'>   		  
		</td>

		<td class="listcellrow" nowrap align=right> <%=((Integer)pageContext.getAttribute("index")).intValue()+1%></td>
		<td class="listcellrow" nowrap align=left><goa:write name='ps' property='send_time' filter='true' />&nbsp;</td>
		<td class="listcellrow" nowrap align=left> 
		
		<goa:write name='ps' property='content' filter='true' maxlen="20"/>&nbsp;

		</td>
		<td class="listcellrow" nowrap align=left>
		  <% String sUrl = new String();
			sUrl = "<a href ='writesms.do?isRe=true&mbno="  +ps.getUser_code() + "' title='�ظ�����'>" +ps.getUser_code() + "</a>";
			out.println(sUrl);
		  %>&nbsp;
		</td>
		<td class="listcellrow4" nowrap align=center>

		<goa:write name='ps' property='user_name' filter='true' />&nbsp;</td>
		
	</tr>
	</logic:iterate>

	<tr align="right"> 
	  <td colspan="7" align="center" class="f12">
	  <html:hidden name="SmsInBoxForm" property="orderType"/>
		<html:hidden name="SmsInBoxForm" property="orderFld"/>
		<goa:pagelink name="SmsInBoxForm" property="pageControl" />  
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

