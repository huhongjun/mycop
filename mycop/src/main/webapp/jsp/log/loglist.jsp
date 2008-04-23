<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-pager.tld" prefix="smile" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm" prefix="perm" %>
<%@ page import="com.gever.struts.pager.PageControl,java.util.*,com.gever.sysman.util.OrderList"%>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>

<html:errors />
<script type="text/javascript" src="<gdp:context/>/js/gdp.js"></script>
<jsp:include page="/jsp/jsp_css.jsp" />

<script language="JavaScript" type="text/JavaScript">
<!--
<% PageControl pc = (PageControl)request.getAttribute("pageControl");
	List  aryData = (ArrayList)pc.getData();
%>


function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
//-->
</script>
<script langauge="javascript">
<!--

function changeBgColorOnMouseOut(obj){
	obj.style.backgroundColor='';
}
function changeBgColorOnMouseOver(obj){
	obj.style.backgroundColor='#cccccc';
}
/**
 * 字符串替换函数  翁乃彬Add(2003-09-27)
 */
function replace(strSource, strFrom, strTo){
	var strDest = "";
	var intFromLen = strFrom.length;
	var intPos=0;
	while((intPos=strSource.indexOf(strFrom))!=-1){
		strDest = strDest + strSource.substring(0,intPos);
		strDest = strDest + strTo;
		strSource = strSource.substring(intPos+intFromLen);
	}
	strDest = strDest + strSource;
	return strDest;
}

function replaceText(value){
	return replace(value,"'","''");
}

function replaceChar(value){
	return replace(value,"'","");
}

function replacePer(value){
    return replace(value,"%","|");
}

function doExport(obj){
		var sqlwhere = "";
		formTJ = document.forms[0];

		var beginDate = obj.beginDate;
		var endDate = obj.endDate;
		var action = obj.action;
		var module = obj.module;
		var username = obj.username;
		var type = obj.type;
		var context = "<%=request.getContextPath()%>";

		if (obj.typeSelect == "0" ){
			if(beginDate!=null && beginDate!="")
				sqlwhere += " and OTIME>='"+replaceChar(beginDate)+"'";//大于指定开始日期

			if(endDate!=null && endDate!="")
				sqlwhere += " and OTIME<='"+replaceChar(endDate)+"'";//小于指定结束日期

			if(username!=null && username!="")
				sqlwhere += " and username like '%"+replaceText(username)+"%'";//模糊查找操作人

			if(module!=null && module!="")
				sqlwhere += " and module like '%"+replaceText(module)+"%'";//模糊查找操作人

			if(action!=null && action!="")
				sqlwhere += " and action like '%"+replaceText(action)+"%'";//模糊查找操作人

			if(type!=null && type!="" && type!="0")
				sqlwhere += " and type ="+replaceChar(type)+" ";//模糊查找模块名

		} else {

			var idRet = getIdValues() ;

			if (idRet == "false"){
				alert("您未选择记录");
				return false;
			}
			sqlwhere = " and id in ("+idRet + ") ";
		}

            alert(sqlwhere);
		//document.frames["myexport"].location.href = sUrl;
        // =============================================================================
        // 胡用添加，"%" 符号不能当作参数传递
		var sUrl = context+"/servlet/RsToFileServlet?type=syslog&fileName=syslog.log&sqlwhere=" + replacePer(sqlwhere);
        alert(sUrl);
        //==============================================================================
        if(navigator.appName == "Microsoft Internet Explorer"){
		    document.frames["myexport"].location.href = sUrl;
        } else {
            document.getElementById("myexport").contentDocument.location.href = sUrl;
        }
        //==============================================================================
	}

	var row_count = <%=aryData.size()%>;
	function getIdValues(){
		var frmObj = document.forms[0];
		var elObj,intlocal;
		var iCount = 0;
		var strSelectd ="";
		var strRet = "";

		if (row_count <0){
			return "false";
		} else if (row_count ==1 ){
			if (frmObj.elements["sid"].checked == false)
				return "false";
			else
				return frmObj.elements["sid"].value;
		}

		var idValues = "";
		var iCount = 0;
		for(var i=0;i<row_count;i++){
			if (frmObj.elements["sid"][i].checked == true){
				iCount++;
				idValues = idValues + frmObj.elements["sid"][i].value+",";

			}
		}
		if (iCount== 0){
			return "false";
		} else {
			idValues = idValues.substring(0,idValues.length-1);
		}
		return idValues;

	}
	var context = "<gdp:context/>";
    //==============================================================================
    // 胡勇修改
    // Netscape 不能使用模态对话框 showModelDialog()
    var ns = navigator.appName == "Netscape";
    var s,k,strUrl,iHeight,formTJ;
    function getParams(){
        return s;
    }
    function setParams(param){
        k = param;
    }
    //==============================================================================
    function doQuery(actionType){
        s = new Object();
		formTJ = document.forms[0];
		var pageform = document.forms["page_form"];

		var saction,stype,smodule,susername,sbeginDate,senddate;
		s.action=formTJ.elements["searchVo.action"].value;
		s.type=formTJ.elements["searchVo.type"].value;
		s.module=formTJ.elements["searchVo.module"].value;
		s.username=formTJ.elements["searchVo.username"].value;
		s.beginDate=formTJ.elements["searchVo.beginDate"].value;
		s.endDate=formTJ.elements["searchVo.endDate"].value;
		s.actionType = actionType;
        s.curform = formTJ;
		var curDate = new Date();
		strUrl =context+"/jsp/log/logquery.jsp?second="+curDate.getSeconds();
		iHeight = 280;
		switch(actionType){
		case 0:
			iHeight=260;
			break;
		case 1:
			iHeight=300;
			break;
		case 2:
			iHeight=280;
			break;
		}
        //==============================================================================
        // 胡勇修改
        // Netscape 不能使用模态对话框 showModelDialog()
        if(ns){
            window.open(strUrl,"","height="+iHeight+"px,width=425px,resizable=no,scrollbars=no,status=no,toolbar=no,menubar=no,location=no");
        } else {
            k=window.showModalDialog(strUrl,s,"dialogWidth:425px;status:no;dialogHeight:" +iHeight + "px");
            doQueryLast(actionType);
        }
        //==============================================================================
    }
	function doQueryLast(actionType){
        
		var strAction = formTJ.action;
		if (k.mtype!=""){

			if (k.typeSelect <1){
                // alert(k.action + " , " + k.module);
				formTJ.elements["searchVo.action"].value=k.action;
				formTJ.elements["searchVo.type"].value=k.type;
				formTJ.elements["searchVo.module"].value=k.module;
				formTJ.elements["searchVo.username"].value=k.username;
				formTJ.elements["searchVo.beginDate"].value=k.beginDate;

				formTJ.elements["searchVo.endDate"].value=k.endDate;
			}
			strAction = "loglist.do?action=list&page=" + "<%=pc.getCurrentPage()%>";
            formTJ.elements["typeSelect"].value = k.typeSelect;
			if (actionType == 0){
				formTJ.elements["actionFlag"].value = "query";

				formTJ.action = strAction;
				formTJ.submit();

			} else if (actionType ==1){
				if (k.isExport ==1){
					doExport(k);
                }
				formTJ.elements["actionFlag"].value = "del";

				setTimeout("doDelete('"+strAction+"')",3000);

			} else {
				doExport(k);
			}
		}
	}
	function doDelete(strAction){
		var formTJ = document.forms[0];
		formTJ.action = strAction;
		formTJ.submit();
	}
    //==============================================================================
    // 胡勇添加，增加列表排序功能
<%  String key_value = OrderList.getInstance().log_key;
    String uri = "/log/loglist.do";
%>  <%@ include file="../order_inc.inc" %>
    //==============================================================================
//-->
</script>

<body class="bodybg">
<script>
	try{
		document.body.onmousedown=function(){top.frames["right"].hideAllMenu();}
	}catch(e){ }
</script>
<gmenu:getpathtag/>
  <html:form action="/log/loglist" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td colspan="6" align="center" class="TableTitleText">操作日志列表</td>
  </tr>
  <tr>
    <td colspan="6" >&nbsp;	<html:hidden property="searchVo.beginDate"/>
		<html:hidden property="searchVo.endDate"/>	<html:hidden property="searchVo.username"/>
			<html:hidden property="searchVo.module"/>	<html:hidden property="searchVo.type"/>
				<html:hidden property="searchVo.action"/>
				<input type="hidden" name="actionFlag" value ="query"/>
				<input type="hidden" name="typeSelect" value ="1"/>
	</td>
  </tr>
  <tr>
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
      <td> <perm:button styleClass="button" type="button" property="query" onclick="doQuery(0)" value="查询" rescode="GDP-CZRZ" optcode="VIEW"/>
          </td>
          <td> <perm:button styleClass="button"  type="button" property="delete" onclick="doQuery(1)" value="删除" rescode="GDP-CZRZ" optcode="ALL"/>
          </td>
          <td><perm:button styleClass="button"  type="button"  property="search" onclick="doQuery(2)" value="导出" rescode="GDP-CZRZ" optcode="ALL"/>
          </td>
        </tr>
      </table>
	  </td>
  </tr>
  </table>

	<table width="95%"align="center" border="0" cellpadding="2" cellspacing="0"  >
        <tr height="20">
          <td width="5%"  class="Listtitle"><input type="checkbox" name="checkbox5" value="checkbox" onclick="checkAll(this,'sid')"></td>
          <td width="15%" id="<%=mapList[0]%>" class="Listtitle" onclick="order('<%=mapList[0]%>',event)" align="left"> 操作人 </td>
          <td width="15%" id="<%=mapList[1]%>" class="Listtitle" onclick="order('<%=mapList[1]%>',event)" align="left"> 机器IP  </td>
          <td width="15%" id="<%=mapList[2]%>" class="Listtitle" onclick="order('<%=mapList[2]%>',event)" height="11"> 时间 </td>
          <td width="10%" id="<%=mapList[3]%>" class="Listtitle" onclick="order('<%=mapList[3]%>',event)"> 模块 </td>
          <td width="10%" id="<%=mapList[4]%>" class="Listtitle" onclick="order('<%=mapList[4]%>',event)"> 动作 </td>
          <td width="30%" id="<%=mapList[5]%>" class="Listtitle" onclick="order('<%=mapList[5]%>',event)"> 描述 </td>
        </tr>
        <logic:iterate id="ps" name="pageControl" property="data" type="com.gever.sysman.log.vo.LogVO" indexId="index">
        <tr  height="20"  onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
          <td  class="listcellrow2"><input type="checkbox" name ="sid" value='<bean:write name="ps" property="id"/>'>&nbsp;
          </td>
          <td class="listcellrow2"><bean:write name="ps" property="username"/>&nbsp;</td>
          <td class="listcellrow2"><bean:write name="ps" property="ipaddress"/>&nbsp;</td>
          <td class="listcellrow2"><bean:write name="ps" property="otime"/>&nbsp;</td>
          <td class="listcellrow2"><bean:write name="ps" property="module"/>&nbsp;</td>
          <td class="listcellrow2"><bean:write name="ps" property="action"/>&nbsp;</td>
          <td class="listcellrow2"><bean:write name="ps" property="memo"/>&nbsp;</td>
        </tr>
        </logic:iterate>
</html:form>

  <tr>
    <td colspan="7" >
	<gmenu:allpager name="pageControl" page="/log/loglist.do?action=list"  usepic="true"/>
	</td></tr>
	</table>

<script>
//==============================================================================
// 胡勇添加，切换图片显示
var event_id = '<%=request.getParameter("srcid")%>';
if((event_id != null) && (event_id != "null")){
    swapImages();
} else {
    if(orderby != "null"){
        event_id = orderby;
        swapImages();
    }
}
//==============================================================================
</script>
</body>
<IFRAME frameBorder=1 id=myexport src="" style="display:none" >
</IFRAME>
</html>
