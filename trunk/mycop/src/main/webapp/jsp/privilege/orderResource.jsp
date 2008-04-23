<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%@ page import="com.gever.sysman.privilege.dao.impl.ResOrderDAOIMP"%>
<%@ page import="com.gever.sysman.privilege.dao.ResOrderDAO"%>
<%@ page import="com.gever.sysman.privilege.model.Resource,java.util.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>操作排序</title>
<jsp:include page="/jsp/jsp_css.jsp" />
</head>

<body class="bodybg">
<gmenu:getpathtag/>
<meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT">
<script>
if( navigator.appName == "Netscape"){
    document.all = document.getElementById;
}
//2004-11-22
//
document.body.onmousedown=function(){
	//try{
		parent.parent.hideAllMenu();
	//}catch(e){}
}
</script>


 <%
   ResOrderDAOIMP opDAO = new ResOrderDAOIMP();
   int id = Integer.parseInt(request.getParameter("resid"));
   String selectid=(String)request.getAttribute("selectid");
   //System.out.println("============="+selectid);
   Collection listop = new ArrayList();
   listop=opDAO.getResourceId(id);
   Iterator it = listop.iterator();
  %>


</table>
<script language="javascript">
 var resid="<%=id%>";
// alert(selectid);
 	function doBack(){
	document.location.href="<gdp:context/>/privilege/resourceAction.do?action=getListChild&resid="+resid;
	}
	function doOk(){
			//alert("asdfasdf");
parent.left_tree.location.reload();

	}
	function addOptionMoveUp()
	{
		//alert();
		sourceSelect=document.getElementById('addRightListBox');
		//alert(sourceSelect);
		var sel_id;
	  sel_id = sourceSelect.selectedIndex;
	  if (sourceSelect.length > 1 && sel_id > 0)
		  //got to have at least 2 items and not the first one is selected
	  {    var prevOption = sourceSelect.options[sel_id-1];
	     // alert(prevOption.value);
           var curOption=sourceSelect.options[sel_id];
		  // alert(curOption.value);
		      // prevOption.value;
			   //curOption.value;
		 // var action = moduleForm.action;
		  //action +="?action=moveOpeOrderid&id1="+prevOption.value+"&id2="+curOption.value+"&resid="+resid;
		//  moduleForm.action= action;
		 // moduleForm.submit();
  document.location.href="<gdp:context/>/privilege/resourceOrderAction.do?action=moveResourceOrderId&id1="+prevOption.value+"&id2="+curOption.value+"&resid="+resid;
//parent.left_tree.expand();
//parent.left_tree.expend();
	  //="<gdp:context/>privilege/resourceAction.do?action=getTreeRoot&src=resourceAction";

	  }
	}
	//下移
	function addOptionMoveDown()
	{
				sourceSelect=document.getElementById('addRightListBox');

		var sel_id;
	  sel_id = sourceSelect.selectedIndex;
	  if (sourceSelect.length > 1 && sel_id < sourceSelect.length -1 && sel_id > -1) //got to have at least 2 items and not the last one is selected
	  {

		  var nextOption = sourceSelect.options[sel_id+1];
           var curOption=sourceSelect.options[sel_id];
		 //      nextOption.value
			//   curOption.value
			//	   resid
document.location.href="<gdp:context/>/privilege/resourceOrderAction.do?action=moveResourceOrderId&id1="+nextOption.value+"&id2="+curOption.value+"&resid="+resid;
 // window.parent.href="<gdp:context/>privilege/resourceAction.do?action=getTreeRoot&src=resourceAction";

	  }
	}

</script>
<form action="/privilege/resourceOrderAction" onsubmit="return false">
<p align="right"><br>
<table width="90%" align="center" class="InputFrameMain">
<tr>
      <td align="center">
        <table width="95%">
		<tr>
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6" align="center" class="TableTitleText">资源排序</td>
  </tr>
<tr>
<td>
<table align="center">
          </tr>
          <tr>
            <td align="center" valign="top" class='InputAreaCell'>
            <select id="addRightListBox" name="addRightListBox"  multiple class="t12" style="width:120px; height:200px;" >
             <%
   while(it.hasNext()) {
   Resource p = (Resource)it.next();
    %>
         <option value="<%=p.getId()%>" <%if((selectid!=null)&&selectid.equals(String.valueOf(p.getId()))){out.print("selected");}%>><%=p.getName()%></option>
   <% }     %>
              </select> </td>
			  <td class='InputAreaCell'>
              <table border="0" cellpadding="1" cellspacing="1">
                <tr>
		           <td align="center" valign="top" class="InputLabelCell">上移</td>
		       </tr>
               <tr>
                  <td valign="top"> <img src="<gdp:context/>/jsp/homepage/images/but_cy_1.gif" width="22" height="17" onclick="addOptionMoveUp();">
                  </td>
              </tr>
				<tr>
                  <td valign="bottom"> <img src="<gdp:context/>/jsp/homepage/images/but_cy_2.gif" width="22" height="17" onclick="addOptionMoveDown(document.all('addRightListBox'))">
                  </td>
                </tr>
                <tr>
		           <td align="center" valign="top" class="InputLabelCell">下移</td>
		       </tr>
				</table>
				</td>
          </tr>
          <tr>
            <td height="22" colspan="3" align="center" valign="top" class='InputAreaCell'>
              <p align="center">
		 <html:button styleClass="button"  property="del" onclick="doOk()" value="确定"/>
		 <html:button styleClass="button"  property="back" onclick="javascript:doBack()" value="返回"/>
              </p></td>
          </tr>
          <tr>
            <td height="10" colspan="3" align="center" valign="top" class='InputAreaCell'>&nbsp;</td>
          </tr>
        </table>
  </td></tr></table>
</form>
</body>
</html>
