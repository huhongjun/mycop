<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<html:errors />
<%String context =request.getContextPath();%>
<SCRIPT  LANGUAGE=JAVASCRIPT src="<%=context%>/cell/goascript/public.js"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/public.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/control/function.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/code.vbs"></SCRIPT>
<SCRIPT LANGUAGE=javascript>
<!--
function window.onload() {
	//注册
	var tform = document.forms(0);
	CellWeb1.Login("广东金宇恒科技有限公司","","13040330","6040-0435-0204-6005") ;
    CellWeb1.OpenFile( getUrl() + "<bean:write  name="DoImpowerForm" property="cellName" filter="true"/>","");
	CellWeb1.EnableUndo(true);
	CellWeb1.Mergecell = true;
	CellWeb1.WorkbookReadonly = true;
	CellWeb1.border = 0;
    CellWeb1.style.left = 0;

	CellWeb1.style.top = idTRTop.style.top + idTRTop.offsetHeight;
	var lWidth = idTRTop.offsetWidth;
	if( lWidth <= 0) lWidth = 1;
	CellWeb1.style.width = lWidth;

	var lHeight = document.body.offsetHeight - 75 - parseInt(CellWeb1.style.top);
	if( lHeight <= 0 ) lHeight = 1;
	CellWeb1.style.height = lHeight;

	idTBBottom.style.top = lHeight + idTRTop.offsetHeight + 5 ;
    //idTBBottom.style.left = idTBTop.style.left ;


	divTips.style.display="none";
	CellWeb1.style.display="";
}
//-->
</SCRIPT>
<!--BUTTON-->

<OBJECT id="CellWeb1" codebase="<%=request.getContextPath()%>/cell/cellweb5.cab#Version=1,0,3,0" style="display: none; POSITION:absolute"
 classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A>
 <PARAM NAME="_Version" VALUE="65536">
 <PARAM NAME="_ExtentX" VALUE="9710">
 <PARAM NAME="_ExtentY" VALUE="4842">
 <PARAM NAME="_StockProps" VALUE="0">
</OBJECT>

<TABLE  cellpadding='0' cellspacing='0' width="100%">
<html:form action="/dailyoffice/impowermgr/viewimpower" method="post">
<html:hidden name="DoImpowerForm" property="cellcontent"/>
<html:hidden name="DoImpowerForm" property="actionType"/>
<html:hidden name="DoImpowerForm" property="vo.send_flag"/>
<html:hidden name="DoImpowerForm" property="vo.create_time"/>
<TR>
	<TD>
	<div align="center">
  <center>
    <table  id="idTRTop"  align="center" width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" colspan="2"><span class="TableTitleText">授权书内容</span></td>
      </tr>
      <tr>
	<td align="center">
			<table width="98%" border="0" cellspacing="0" cellpadding="0">
                  <tr> 
                    <td align="center"> 
					<table  width="100%"  id="idTBTop" border="0" cellpadding="0" cellspacing="2">
					<tr height="23" ><td></td></tr>   
						<tr class="listcellrow" height="23" >
                          <td width="25%"  nowrap><font class="InputLabelCell">授 权 人：</font> <bean:write name="DoImpowerForm" property="vo.acceptername"  filter="true"/></td>
                           <td width="25%"  nowrap><font class="InputLabelCell">被授权人：</font> <bean:write  name="DoImpowerForm" property="vo.payername" filter="true"/></td>
                          <td width="25%" nowrap><font class="InputLabelCell">授权期间：</font> <bean:write  name="DoImpowerForm" property="vo.begin_time" filter="true"/></td>
                          <td width="25%"  nowrap ><font class="InputLabelCell">到：</font> <bean:write  name="DoImpowerForm" property="vo.end_time" filter="true"/></td>
                        </tr>
						<tr class="listcellrow" height="23" colspan="2"> 
                          <td width="350"  nowrap style="word-break:break-all" colspan="2"><font class="InputLabelCell">通知人员：</font> <bean:write  name="DoImpowerForm" property="vo.noticename" filter="true"/></td>
                           <td nowrap style="word-break:break-all" colspan="2"><font class="InputLabelCell">授权说明：</font> <bean:write  name="DoImpowerForm" property="vo.comments" filter="true"/></td>
						</tr>
 						<tr class="listcellrow" height="23" >

                        </tr>
 						<tr class="listcellrow">
                          <td width="100%" nowrap class="InputLabelCell" colspan="4"></td>
                        </tr>           
 						<tr class="listcellrow">
                          <td width="100%" nowrap class="InputLabelCell" colspan="4"></td>
                        </tr>  
						</table>
                      <div id="divTips" style="LEFT: 109px; POSITION: relative; top: 179px;">正在加载华表模板..........</div>
                      <table id="idTBBottom" style="POSITION:absolute; left: 0px; top: 199px;" cellpadding='0' cellspacing='0' width="100%">
                        <tr> 
                          <td align="center" height="40" >
						  <html:hidden name="DoImpowerForm" property="fid" /> 
						  <% String temp="toUrl('"+context+"/dailyoffice/impowermgr/editimpower.do?actionType=modify',false)";%>
						  <goa:button property="save" value="修改" styleClass="button"  onclick="<%=temp%>"  operation="SQGL.OPT"/> 
						  <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')"  operation="SQGL.VIEW"/>
						  <input type="button" class="button" name="print" value="打印预览" onclick="mnuFilePrintPreview_click()">
		<input type="button" class="button" name="print" value="输出为Excel文件" onclick="mnuFileExportExcel_click()">
						  
						  </td>
                        </tr>
                      </TABLE>
       </td>
				</tr>
          </table>
        </td>
      </tr>
      <tr>
        <td>
</td>
      </tr>
    </table>
    </center>
</div>
	</TD>
</TR>
</html:form>
</TABLE>


