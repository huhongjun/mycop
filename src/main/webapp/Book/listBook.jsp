<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/gever-goa.tld" prefix="goa" %>
<%  String context =request.getContextPath();  %>

<html>
<body class="bodybg">
  <html:form action="/Book/listBook" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="6" align="center" class="TableTitleText">ͼ���б�</td>
  </tr>
  <tr> 
    <td colspan="6" align="right">&nbsp;</td>
  </tr>
  <tr> 
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
		 
          <td> <goa:button styleClass="button"  property="add" onclick="doAdd()" value="����"  operation="TSGL.OPT"/>
          </td>
		   <td>
		
		   <goa:button styleClass="button"  property="copy" onclick="toUrl('../Book/editBook.do?actionType=modify',true)" value="�޸�" operation="TSGL.OPT"/>
          </td>
		   <td> <goa:button  styleClass="button" property="delete" value="ɾ��"  onclick="doDelete()" operation="TSGL.OPT"/>
		    <td> <goa:button  styleClass="button" property="delete" value="�鿴"  onclick="toUrl('../Book/viewBook.do',true)" operation="TSGL.VIEW"/>
		  <td><goa:button styleClass="button"  property="copy"  onclick="doCopy()"value="����" operation="TSGL.OPT"/>
          </td>
         
          </td>
		
          </td>
        </tr>
      </table>
	  </td>
  </tr>
  <tr> 
    <td colspan="6">
	<table width="100%"border="0" cellspacing="0" cellpadding="2">
        <tr height="20">
      <td width="32" class="Listtitle" align="center" ><input alt="ѡ��" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>
      		<td width="15%" class="Listtitle" id=".bookid"  onclick="onchangeOrderBy(this)"> <div align="v">����</div></td>
      		<td width="15%" class="Listtitle" id=".bookname"  onclick="onchangeOrderBy(this)"> <div align="v">����</div></td>
      		<td width="15%" class="Listtitle" id=".publisher"  onclick="onchangeOrderBy(this)"> <div align="v">������</div></td>
      		<td width="15%" class="Listtitle" id=".deptid"  onclick="onchangeOrderBy(this)"> <div align="v">��������</div></td>
    </tr>
<logic:iterate id="ps" name="BookForm" property="pageControl.data" type="com.gever.goa.book.vo.BookVO" indexId="index">
	   <tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('../Book/viewBook.do?fid=" +ps.getValue("bookid")+ "',false)"%>">
		      <td align="center" class="listcellrow">
			         <input type='checkbox' name='fid' value='<goa:write name="ps" property="bookid" filter="true"/>'>   		  
			      </td>
		
		      <td class="listcellrow"><goa:write name="ps" property="bookid" filter="true"/></td>
      <td class="listcellrow"><goa:write name="ps" property="bookname" filter="true"/></td>
      <td class="listcellrow"><goa:write name="ps" property="publisher" filter="true"/></td>
      <td class="listcellrow"><goa:write name="ps" property="deptid" filter="true"/></td>
				    </tr>
	</logic:iterate>
</table>

</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
<html:hidden name="BookForm" property="orderType"/>
<html:hidden name="BookForm" property="orderFld"/>
		<goa:pagelink name="BookForm" property="pageControl" /> 
		</td>
	  </tr>
	</table>
</html:form>

</body>
</html>
