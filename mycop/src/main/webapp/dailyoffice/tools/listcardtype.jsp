<%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context = request.getContextPath();
String operate = (String)session.getAttribute("operate");
session.removeAttribute("operate");
String[] operateID=(String[])session.getAttribute("operateID");
if(operateID==null){
    operateID=new String[0];
}
session.removeAttribute("operateID");
%>


<SCRIPT LANGUAGE="JavaScript">
<!--
function refreshTree(){
    var operate='<%=operate%>';

    if("insert"==operate){
        //alert(operate);
		window.parent.frames["lefttree"].reload('0');
    }else if("update"==operate){
        //alert(operate);
		window.parent.frames["lefttree"].reload('0');
    }else if("delete"==operate){
        //alert(operate);
    	var deleteID=new Array();
		<%for(int i=0; i<operateID.length;i++){
			out.println("deleteID["+i+"]="+operateID[i]+";");
		}%>
        if(deleteID!=null && deleteID.length!=0){
        	for(var i=0;i<=deleteID.length;i++){
                	window.parent.frames["lefttree"].delNode(deleteID[i]);
        		}
        }
    }
}

//ɾ��
function doMyDelete(){
	var form = document.forms[0];
	var fid = form.fid;
	var subnum=form.subnum;
	//alert(subnum);
	var typename=form.typename;
	//alert(typename);
	var msg="";
	var count = 0;
	var length = 0;
	try{
		 length = fid.length;
	} catch(e){
		alert("û���κμ�¼��");
		return false;
	}
	if (isNaN(length))	{
		try{
			if (fid.checked)	{
				count++;
				if(subnum.value>=1){
				msg+="--���Ϊ �� "+typename+" �� ����Ƭ�в�Ϊ�գ�������պ���ɾ����\n";
				fid.checked=false;
				}
			}
		}catch(e){alert("isNaN");}
		
	}

	for(var i=0;i<length;i++){
		if(fid[i].checked){
			count++;
			if(subnum[i].value>=1){
				var name=typename[i].value;
				//alert("name"+name);
				msg+="--���Ϊ �� "+name+" �� ����Ƭ�в�Ϊ�գ�������պ���ɾ����\n";
				fid[i].checked=false;
			}
		}
	}
	if(msg!=""){
		alert(msg);
		return false;
	}
	if(count==0){
		alert("��û��ѡ���κμ�¼��");
		return false;
	}
	if(confirm("ȷ��Ҫɾ����¼��") == false){
		return false;
	}


	
	var action =  form.action;
	action =  addPara(action,"action=delete" );
	form.action = action
	form.submit();
}

function setPubtype(){
	var url="<%=context%>/dailyoffice/tools/listCardcase.do?nodeid=-1&nodename=������Ƭ��";
	window.location=url;
}
//-->
</SCRIPT>

<body  onload='refreshTree()' class="bodybg">
 <table width="50%">
  <html:form action="/dailyoffice/tools/listCardcaseType" method="post">
    <html:hidden property="sqlWhere" />
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6" align="center" class="TableTitleText">��Ƭ����б�</td>
  </tr>
  <tr>
    <td colspan="6" align="right">&nbsp;</td>
  </tr>
  <tr>
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
		  <td> <goa:button styleClass="button"  property="add" onclick="setPubtype()" value="���ù�����Ƭ��" operation="MBJ.PUBOPT"/>
          </td>
          <td> <goa:button styleClass="button"  property="add" onclick="doAdd()" value="����" operation="MBJ.OPT"/>
          </td>
		   <td>
		   <%String modifylink="toUrl('"+context+"/dailyoffice/tools/editCardcaseType.do?actionType=modify',true)";%>
		   <goa:button styleClass="button"  property="modify" onclick="<%=modifylink%>" value="�޸�" operation="MBJ.OPT"/>
          </td>
		   <td> <goa:button  styleClass="button" property="delete" value="ɾ��"  onclick="doMyDelete()" operation="MBJ.OPT"/>
		   </td>
		    <td> 
			<%String viewlink="toUrl('"+context+"/dailyoffice/tools/viewCardcaseType.do',true)";%>
			<goa:button  styleClass="button" property="view" value="�鿴"  onclick="<%=viewlink%>" operation="MBJ.VIEW"/>
			</td>
          </tr>
      </table>
	  </td>
  </tr>
  <tr>
    <td colspan="6">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
        <tr height="20">
            <td height="11" width="10%" class="Listtitle" align="center"><input alt="ѡ��" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>
      		<td height="11" width="30%" class="Listtitle" id=".type_name" onclick="onchangeOrderBy(this)"><div align="left">�������</div></td>
			<td height="11" width="60%" class="Listtitle" id=".remark" onclick="onchangeOrderBy(this)"><div align="left">��&nbsp;&nbsp;&nbsp;&nbsp;ע</div></td>
    </tr>

    <logic:iterate id="ps" name="CardcaseTypeForm" property="pageControl.data" type="com.gever.goa.dailyoffice.tools.vo.CardcaseTypeVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);"ondblclick="<%="toUrl('"+context+"/dailyoffice/tools/viewCardcaseType.do?fid=" +ps.getType_code()+"',false)"%>">
		<td align="center" class="listcellrow">
			<input type='checkbox' name='fid' value='<goa:write name="ps" property="type_code" filter="true"/>' >
			</td>
		<td class="listcellrow"><goa:write name="ps" property="type_name" filter="true"/>&nbsp;</td>
		<td class="listcellrow4"><goa:write name="ps" property="remark" filter="true"/>&nbsp;</td>
		<input type="hidden" name="typename" value='<goa:write name="ps" property="type_name" filter="true"/>'>
		<input type="hidden" name="subnum" value='<goa:write name="ps" property="subnum" filter="true"/>'>
	</tr>
	</logic:iterate>
</table>


</td>
  </tr>
  <tr>
    <td colspan="8" align="center" class="f12">
	    <html:hidden name="CardcaseTypeForm" property="orderType"/>
		<html:hidden name="CardcaseTypeForm" property="orderFld"/>
		<goa:pagelink name="CardcaseTypeForm" property="pageControl" />
		</td>
	  </tr>
	</table>
</html:form>
</table>
</body>

