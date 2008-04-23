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

<title>发信箱</title>
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
			bWhere.innerHTML ="隐藏信息查询条件 "
		} else {
			document.getElementById("searchHeader").style.display = "none"
			bWhere.innerHTML ="显示信息查询条件 "
		}
	}
	//type,为返回,或直接提交用
	function doSearch(type){
		if (isDate(document.forms[0].elements["startDate"].value) == false){
			alert("日期不能为空;日期不对,正确格式:'2000-01-02'");
			return false;
		}
		if (isDate(document.forms[0].elements["endDate"].value) == false){
			alert("日期不能为空;日期不对,正确格式:'2000-01-02'");
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
		
		//时间范围
		if (startDate=="" && endDate !=""){
			strWhere += " and senddate<='" +endDate +" 23:59:59' ";
		} else if (endDate=="" && startDate !=""){
			strWhere += " and senddate>='" +startDate +" 00:00:00' ";
		} else if(endDate!="" && startDate !=""){
			strWhere += " and senddate>='" +startDate + " 00:00:00' and senddate<='" + endDate + " 23:59:59' ";
		} 
		//发信人
		if (user !=""){
			strWhere += " and username like '%" + replace(user,"'","''") + "%' ";
		}
		//信息内容
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
		sUrl = "<%=context%>/servlet/RsToFile?type=smsoutbox&fileName=短信发件箱.txt&sqlwhere=" + strWhere ;
		//window.open(sUrl);		
		document.frames["export"].location.href = sUrl;
		//sUrl ="<%=context%>/util/downloadfile.jsp?filepath=uploadfiles/smsoutbox.txt&filename=短信发件箱.txt";
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
    <td colspan="7" align="center" class="TableTitleText">短信发件箱</td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
    <td> 
	<html:form action="/dailyoffice/smsmgr/listsmsoutbox"> 
	<table width=100%  border=0 align=center>
        <tr valign=center  class="InputLabelCell"> 
          <td width="44%"><input name="showHeader" type="checkbox" id="showHeader4" onClick="javascript:showSearch()" checked> 
            <span id="bWhere"> 隐藏查询条件</span></td>
          <td width="56%" align=right>&nbsp; 
		  <goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()" operation="SJDX.OPT"/>
		  &nbsp; <goa:button  styleClass="button" property="exportall" value="导出"  onclick="doExport()" operation="SJDX.OPT"/>&nbsp;  <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="SJDX.VIEW"/>
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
                    <td width="8%" align=right nowrap> <div align="right">开始日期：</div></td>
                    <td width="35%" align=left> <html:text property="startDate" readonly="false"   validatetype="date"  msg="日期不能为空;日期不对,正确格式:'2000-01-02'"  styleClass="input"  onlytype ="date"   style="WIDTH: 100px"/></td>
                    <td width="9%" align=right nowrap> <div align="right">迄止日期：</div></td>
                    <td width="48%" align=left><html:text property="endDate" readonly="false"   validatetype="date"  msg="日期不能为空;日期不对,正确格式:'2000-01-02'"  styleClass="input"  onlytype ="date"   style="WIDTH: 100px"/></td>
                  </tr>
                  <tr  class="InputLabelCell" valign=center> 
                    <td width="8%" align=right nowrap>类型：</td>
                    <td width="35%" align=left> <html:select property="type" disabled="false" size="1"> 
                      <html:option value="0"  >全部类别</html:option> <html:option value="1"  >已发送</html:option> 
                      <html:option value="2"  >正在发送</html:option> <html:option value="4"  >待发送</html:option> 
                      <html:option value="3"  >发送失败</html:option> </html:select> 
                    <td width="9%" align=right>收信人：</td>
                    <td align=left> <html:text property="searchUser"  maxlength="30" style="WIDTH: 100px" styleClass="input" /> 
                    </td>
                  </tr>
                  <tr height="20"  class="InputLabelCell" > 
                    <td  align=right nowrap>发信内容：</td>
                    <td    align=left><html:text property="searchMsg"  maxlength="70" style="WIDTH: 160px" styleClass="input" /><html:hidden name="SmsMgrForm" property="sqlWhere"/> 
                    </td>
                    <td align=right>&nbsp;&nbsp; </td>
                    <td align="left"> 
					<goa:button  styleClass="button" property="delete" value="搜索"  onclick="doSearch(true)" operation="SJDX.VIEW"/>
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
            <td width="32" class="Listtitle" align="center" ><input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>

      		 <td  width="7%" class="Listtitle"><div align="right">序号</div></td>
          <td  width="20%" id=".senddate"  class="Listtitle" onclick="onchangeOrderBy(this)" ><div align="left">发送时间</div></td>
          <td  width="30%"  id=".msg" class="Listtitle" onclick="onchangeOrderBy(this)"><div align="left">消息内容</div></td>
          <td  width="13%" id=".mbno" class="Listtitle" onclick="onchangeOrderBy(this)"><div align="left">手机号码</div></td>
          <td  width="10%" id=".username" class="Listtitle" onclick="onchangeOrderBy(this)"><div align="left">接收者</div></td>
          <td   id=".type" class="Listtitle" onclick="onchangeOrderBy(this)"><div align="left">状态</div></td>

           		  
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
		  已发送 </logic:equal> <logic:equal name="ps" property="type" value="2"> 
		  正在发送 </logic:equal> <logic:equal name="ps" property="type" value="3"> 
		  发送失败 </logic:equal> <logic:equal name="ps" property="type" value="4"> 
		  定时发送 </logic:equal>&nbsp; 
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

