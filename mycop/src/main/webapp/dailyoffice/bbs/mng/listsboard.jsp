<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
%>

<SCRIPT LANGUAGE="JavaScript">
<!--
//删除
function doDelete(){

	var count = 0;
	var length = 0;
	try{
		 length = document.forms[0].fid.length;
	} catch(e){
		alert("没有任何记录！");
		return false;
	}
	if (isNaN(length))	{
		try{
			if (document.forms[0].fid.checked)	{
				++count;
			}
		}catch(e){}
	}

	for(var i=0;i<document.forms[0].fid.length;i++){
		if(document.forms[0].fid[i].checked)
			count++;
	}
	if(count==0){
		alert("您没有选择任何记录！");
		return false;
	}
	if(confirm("该操作还将删除属于该论坛的所有主题！\n您确定要删除记录吗？") == false){
		return false;
	}


	var mForm = document.forms[0];
	var action =  mForm.action;
	action =  addPara(action,"action=delete" );
	mForm.action = action
	disButton();
	mForm.submit();
}
//-->
</SCRIPT>

<html>
<body class="bodybg">
  <html:form action="/dailyoffice/bbs/mng/sboard" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="6" align="center" class="TableTitleText">二级论坛</td>
  </tr>
  <tr> 
    <td colspan="6" align="right">&nbsp;</td>
  </tr>
  <tr> 
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
		 <td align="right" class="f12">名称:
            </td>
            <td>
                <html:text property="searchValue" maxlength="30" size="22" styleClass="input2">
                </html:text>
            </td>
            <td align="right">
                <goa:button  property="search" styleClass="button" value="查询" onclick="toUrl('sboard.do',false)" operation="LTWF.VIEW"/>
            </td>
          <td> <goa:button styleClass="button"  property="add" onclick="doAdd()" value="新增" operation="LTWF.OPT"/>
          </td>
		   <td>
		   
		   <goa:button styleClass="button"  property="copy" onclick="toUrl('editsboard.do?actionType=modify',true)" value="修改" operation="LTWF.OPT"/>
          </td>
		   <td> <goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()" operation="LTWF.OPT"/>
		   <td> <goa:button  styleClass="button" property="view" value="查看"  onclick="toUrl('viewsboard.do',true)" operation="LTWF.VIEW"/>
		   </td>
		  <td> <goa:button  styleClass="button" property="list" value="返回"  onclick="window.location='topboard.do'" operation="LTWF.VIEW"/>
		   </td>
        </tr>
      </table>
	  </td>
  </tr>
  <tr> 
    <td colspan="6">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
        <tr height="20">
            <td width="9%" class="Listtitle"><div align="center">
              <input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)">              
            </div>              <div align="left"></div></td>
      		<td width="27%" class="Listtitle"id=".sboard_name" onclick="onchangeOrderBy(this)"><div align="left">名称</div></td>
            <td width="16%" class="Listtitle"id=".sboard_state" onclick="onchangeOrderBy(this)"><div align="left">状态</div></td>
            <td width="48%"  class="Listtitle"id=".sboard_note" onclick="onchangeOrderBy(this)"><div align="left">备注</div></td>

           		  
    </tr>
    <logic:iterate id="ps" name="SBoardForm" property="pageControl.data" type="com.gever.goa.dailyoffice.bbs.vo.SBoardVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('viewsboard.do?fid=" +ps.getSerial_num()+"',false)"%>">
		<td align="center" class="listcellrow">
			<div align="center">
			  <input type='checkbox' name='fid' value='<bean:write name="ps" property="serial_num" filter="true"/>' >    		  
		      </div></td>
		<td class="listcellrow"><bean:write name="ps" property="sboard_name" filter="true"/></td>
<td class="listcellrow"><logic:equal name="ps" property="sboard_state" value="1" >活动状态</logic:equal>
<logic:equal name="ps" property="sboard_state" value="0" >关闭状态</logic:equal></td>
<td class="listcellrow4"><bean:write name="ps" property="sboard_note" filter="true"/>&nbsp;</td>
	
	</tr>
	</logic:iterate>
</table>


</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
		<goa:pagelink name="SBoardForm" property="pageControl" /> 
		<html:hidden name="SBoardForm" property="orderType"/>
        <html:hidden name="SBoardForm" property="orderFld"/>
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
