<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ page import="java.util.ArrayList,com.gever.goa.dailyoffice.worklog.vo.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
%>

<script LANGUAGE="javascript">
<!--
function checkInputAndSearch(){	
	var sForm = document.forms[0];	
	var bDate = sForm.elements["searchBeginTime"].value;
	var eDate = sForm.elements["searchEndTime"].value;			
	var bRet = true;	
	var strMsg = "";	
	if((bDate!="" && bDate!=null) && (eDate!="" && eDate!=null)){
	var lbDate = getDateNum(bDate,0);
	var leDate = getDateNum(eDate,0);
	if (lbDate > leDate){
		bRet = false;
		strMsg +=  ("请保证结束日期在开始日期之后!\n");
	}
	}
	if (!bRet){
		alert(strMsg);
	}else{
		doQuery('<%=context%>/dailyoffice/worklog/listteamworklog.do',true);
	}
	return bRet;

}

function doLog(id){
	
		window.location.href ="<%=context%>/dailyoffice/worklog/viewworklog.do?fid=" + id;
	}
	 function switchstatus(index){
        if(document.all(index).style.display=="none"){
            document.all(index).style.display="block";
   		    document.getElementById("TP" + index.substring(2,index.length)).src="<%=context%>/images/menu_open.gif"
		} else {
            document.all(index).style.display="none"
			document.getElementById("TP"  + index.substring(2,index.length)).src="<%=context%>/images/menu_close.gif"
		}
    }

	//采用tr的方式(不是用tbody隐蔽的方式),,收缩及扩展其细项资料
	function switchTrStatus(obj,iCount){
		var idx,obj_id;
		for (idx =1 ; idx<=iCount;idx++){
			obj_id= obj + "_" +idx;
			//alert(obj_id + "count ="+iCount);
			try{
			if (document.getElementById(obj_id).style.display=="none"){
				document.getElementById(obj_id).style.display="block";
				document.getElementById("TP" + obj.substring(2,obj.length)).src="<%=context%>/images/menu_open.gif";
			} else {
				document.getElementById(obj_id).style.display="none";
				document.getElementById("TP" + obj.substring(2,obj.length)).src="<%=context%>/images/menu_close.gif";
			}
			}catch(e){}
		}
	}


function switchAllItemStatus(strMode){
	var elObj;
	var lenth= document.all.length;
	for(var i=0; i<lenth;i++){
		elObj = document.all[i];
		if (elObj.id=="" || elObj==null || elObj=="undefined" || (elObj.id).length>50)
			continue;
		if ((elObj.id).substring(0,2) == "YG") {
			var index = (elObj.id).substring(2);
			if(strMode=='block'){
				elObj.style.display='block';
				document.all("TP" + index).src="<%=context%>/images/menu_open.gif";
			}
			else if(strMode=='none'){
				elObj.style.display='none';
				document.all("TP" + index).src="<%=context%>/images/menu_close.gif";
			}
		}
		if ((elObj.id).substring(0,2) == "RZ") {
			var index = (elObj.id).substring(2,(elObj.id).indexOf("_"));
			if(strMode=='block'){
				elObj.style.display='block';
				document.all("TP" + index).src="<%=context%>/images/menu_open.gif";
			}
			else if(strMode=='none'){
				elObj.style.display='none';
				document.all("TP" + index).src="<%=context%>/images/menu_close.gif";
			}
		}
	}
}
 //-->
 </SCRIPT>
<html>
<body class="bodybg">
  <html:form action="/dailyoffice/worklog/listteamworklog" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">  
  <tr> 
  <td align="center" class="TableTitleText" colspan="3">
		  <logic:equal name="TeamWorkLogSetForm" property="teamtype" value="0">
		  领导团队
		  </logic:equal>
		  <logic:equal name="TeamWorkLogSetForm" property="teamtype" value="1">
		  关注团队
		  </logic:equal>
		  <logic:equal name="TeamWorkLogSetForm" property="teamtype" value="2">
		  我的团队
		  </logic:equal>
  </td>
  </tr>
  <tr> 
    <td colspan="8" align="right">&nbsp;</td>
  </tr>
  <tr> 
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr class="InputLabelCell">
		<td>
          开始日期:<html:text property="searchBeginTime" onlytype="date" styleClass="input2" msg="开始日期不对,正确格式:'2000-01-02'" validatetype="date"    size="10" maxlength="10"/>
    </td>
	<td>终止日期:<html:text property="searchEndTime" onlytype="date"  styleClass="input2" msg="终止日期不对,正确格式:'2000-01-02'" validatetype="date" size="10" maxlength="10"/>
          </td>
		  <td>
		  <goa:button operation=""  property="search" value="查询" onclick="checkInputAndSearch()" />			
           </td>
		   <td>
          <html:button  styleClass="button" property="openTr" value="展开"  onclick="switchAllItemStatus('block')"/>
          </td>
          <td>
          <html:button  styleClass="button" property="closeTr" value="折叠"  onclick="switchAllItemStatus('none')"/>
          </td>
		  <td>
<% 
String 
temp="toUrl('"+context+"/dailyoffice/worklog/setteamworklog.do',false)";
%>
          <html:button  styleClass="button" property="view" value="团队设置"  onclick="<%=temp%>"/>
          </td>          		  
        </tr>
      </table>
	  </td>
  </tr>
 <tr>
   <td colspan="3">
   <table width="100%" border="0" cellspacing="0" cellpadding="2">
     <tr height="20">
          
      		
      		<td width="8%" class="Listtitle" id=".name" > <div align="right">序号</div></td>
            <td width="20%" class="Listtitle" id=".fill_date"  onclick="onchangeOrderBy(this)"> <div align="left">日期</div></td>
            <td width="60%" class="Listtitle" id=".age" > <div align="left">星期</div></td>
		
           		  
    </tr>
	
	<%String strHref = "";%>
	<logic:iterate id="elementmain" name="TeamWorkLogSetForm" property="pageControl.data" indexId="index"  type="com.gever.goa.dailyoffice.worklog.vo.TeamWorkLogSetVO">

	<tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);" align="left">
    	<td nowrap  class="listcellrow"> <div align="right"><img name='TP<%=elementmain.getSerial_num()%>' src='<%=context%>/images/menu_close.gif' border=0 title="展开/收缩说明行与操作行" onClick="switchstatus('YG<%=elementmain.getSerial_num()%>')"><goa:write name="elementmain" property="serial_num" filter="true" /></div></td>

    	<td align="left" nowrap  class="listcellrow"><div align="left"><goa:write name="elementmain" property="fdate" filter="true" /></div></td>

    	<td  class="listcellrow4"><div align="left"> <goa:write name="elementmain" property="fweek" filter="true" /></div>
    	</td>
		<tbody id='YG<%=elementmain.getSerial_num()%>' style='display:none'>
		<logic:iterate id="element" name="elementmain" property="listMens" indexId="index1" type="com.gever.goa.dailyoffice.worklog.vo.WorkLogVO" >
        <tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);" align="left">
                <td class="listcellrow" nowrap>&nbsp;&nbsp;
		
                <td class="listcellrow" align="left" nowrap> <img id='TP<%=element.getSerial_num()+element.getFill_date()%>' src='<%=context%>/images/menu_close.gif' border=0 title="展开/收缩说明行与操作行" onClick="switchTrStatus(<%="'RZ"+(element.getSerial_num()+element.getFill_date()+"'," +element.getSublist().size())%>)"><goa:write name="element" property="user_name" filter="true" /></td>
                <td class="listcellrow4">&nbsp;</td>

	       
            <logic:iterate id="element2" name="element" property="sublist" indexId="index2" type="com.gever.goa.dailyoffice.worklog.vo.WorkLogContentVO">
            <%strHref = "toUrl('"+context+"/dailyoffice/worklog/viewworklog.do?fid=" +
                           element2.getSerial_num() + "',false)";%>
		<tr ID=RZ<%=element.getSerial_num()+element.getFill_date()+"_" +element2.getSerial_num()%> style="display:none" onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);" style="cursor:hand;" ondblclick="<%="toUrl('"+context+"/dailyoffice/worklog/viewworklog.do?fid=" +element.getSerial_num()+"',false)"%>" align="left">		
            	<td class="listcellrow">&nbsp;</td>
            	<td class="listcellrow">&nbsp;</td>
                    <td class="listcellrow4"  style="word-break:break-all">
                          <goa:write name='element2' property='log_content' filter='false'/></td>
                        </tr>
	
          </logic:iterate>
		</logic:iterate>
		</tbody>
  </logic:iterate>

  <tr align="right">
        <td colspan="8" align="right" >
		   <html:hidden name="TeamWorkLogSetForm" property="orderType"/>
<html:hidden name="TeamWorkLogSetForm" property="orderFld"/>
		<goa:pagelink name="TeamWorkLogSetForm" property="pageControl" /> 
	    </td>
 </tr>
	
	
	</table>
</td>
</tr>  
	</table>
</html:form>
</body>
</html>
