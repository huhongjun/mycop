<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.*"%>
<%@ page import="com.gever.sysman.privilege.dao.*"%>
<%@ page import="com.gever.sysman.privilege.model.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%
String context = request.getContextPath();
%>
<script type="text/javascript" src="<gdp:context/>/js/gdp.js"></script>
<script language="javascript">
function change(obj, self){
  if (document.all(obj).style.display == "none"){
    self.src = "<gdp:context/>/images/menu_open.gif";
    document.all(obj).style.display = "block";
  } else {
    self.src = "<gdp:context/>/images/menu_close.gif";
    document.all(obj).style.display = "none";
  }    
}

function check(obj){
  var cid = obj.getAttribute('cid');
  if (obj.checked == true){
    //recurResources(obj.cid, true);
    recurResources(cid, true);
  } else {
    //recurResources(obj.cid, false);
    recurResources(cid, false);
  }
}

//************************************************************
var ns = navigator.appName == "Netscape";
if(ns){
    HTMLDocument.prototype.getElementsById=function (strTagName,strId){
        var arrObj=new Array();
        var tagObjs=this.getElementsByTagName(strTagName); 
        for(var i=0;i<tagObjs.length;i++){
            var idValue=tagObjs[i].getAttribute("id");
            if(idValue==strId){
                arrObj[arrObj.length]=tagObjs[i];
            }
        }  
        return arrObj;
    }
}
//************************************************************


function recurResources(id, index){
  var i;
  if(ns){
      var obj=document.getElementsById("INPUT",id);
  } else {
      var obj = document.all(id);
  }
   
  if (obj != null){
      if (typeof(obj.length) != "undefined"){
        for (i=0; i<obj.length; i++){
          obj[i].checked = index;
          recurResources(obj[i].getAttribute('cid'), index);  
        }
      } else {
        obj.checked = index;
        recurResources(obj.getAttribute('cid'), index);        
      }
  }
}
function doBack(){
    if(navigator.appName == "Microsoft Internet Explorer"){
        history.back();
        return;
    }
    var moduleform = document.forms[0];
    var action="<gdp:context/>/privilege/userAction.do?action=list";

    moduleform.action = action;
    moduleform.submit();
}
</script>
<jsp:include page="/jsp/jsp_css.jsp" />
<%
class RecurResource{
  public StringBuffer output = new StringBuffer(1000);
  public String context;
  public ArrayList resources;
  public Collection userRess;
  public ArrayList user_res_opt_collect;
  public ArrayList user_role_perm_collect;
  public Map oprs;
  public int position = 0;
  public void recurResource(long resourceID){
    for (int i=0; i<resources.size(); i++){
      Resource resource = (Resource)resources.get(i);
      if (resourceID == resource.getParentID()){
        boolean isHasChildren = isHasChildren(resource.getId());
        output.append("<tr><td>");
        output.append(echoSpace(position+2));
        if (isHasChildren){
          output.append("<span><img src=\""+context+"/images/menu_open.gif\" style=\"cursor:hand\" onclick=\"javascript:change('"+"t"+resource.getId()+"', this);\"/></span>");
        } else {
          output.append("<span><img src=\""+context+"/images/menu_open.gif\"/></span>");
        }
        output.append("<input type=\"checkbox\"");
        if (isHasResourceRight(resource.getId())){
          output.append(" checked");
        }
        output.append(" id=\"r"+resource.getParentID()+"\" cid=\"r"+resource.getId()+"\" onclick=\"javascript:check(this);\">"+"<b>"+resource.getName()+"</b>");
        ArrayList oprList = (ArrayList)oprs.get(Long.toString(resource.getId()));
        if (oprList.size() > 0){
          output.append("&nbsp;");
          output.append(echoLine(5));
          output.append("&nbsp;");          
        }
        for (int j=0; j<oprList.size(); j++){
          Operation operation = (Operation)oprList.get(j);
          output.append("<input name=\"opt\"");
          if (isHasOperationRight(operation.getId())){
            output.append(" checked");
          }          
          if (isRolePermission(operation.getId())){
            //output.append(" disabled");
          }
          String cid,id;
          cid = "o" + resource.getId();
          id = "r" + resource.getId();

          output.append(" value=\""+operation.getResourceID()+"o"+operation.getId()+"\" type=\"checkbox\" cid=\""+cid+"\" id=\""+id+"\">");
          if (isRolePermission(operation.getId())){
            output.append("" + operation.getName() + "");
          } else {
            output.append(operation.getName());
          } 
          output.append("&nbsp;");
        }
        if (isHasChildren){
          output.append("<table class=\"f12\" id=\"t"+resource.getId()+"\">");
          position++;
	  recurResource(resource.getId());
	  position--;
          output.append("</table>");
        } else {
          output.append("</td></tr>");
        }
      }
    }
  }
  public String echoSpace(int count){
    StringBuffer output = new StringBuffer(20);
    for (int i=0; i<count; i++){
      output.append("&nbsp;&nbsp;");
    }
    return output.toString();
  }
  public String echoLine(int count){
    StringBuffer output = new StringBuffer(20);
    for (int i=0; i<count; i++){
      output.append("<span style=\"font-size:6px\">·</span>");
    }
    return output.toString();
  }  
  public boolean isHasChildren(long resourceID){
    for (int i=0; i<resources.size(); i++){
      Resource resource = (Resource)resources.get(i);
      if (resourceID == resource.getParentID()){
        return true;
      } 
    }
    return false;
  }
  public boolean isHasResourceRight(long resourceID){
    Iterator iResources = userRess.iterator();
    while (iResources.hasNext()){      
      Resource resource = (Resource)iResources.next();
      if (resourceID == resource.getId()){
        return true;
      }       
    }
    return false;
  }
  public boolean isHasOperationRight(long operationID){
    
    for (int i=0; i<user_res_opt_collect.size(); i++){
      UserPermission permission = (UserPermission)user_res_opt_collect.get(i);
      if (operationID == permission.getResop_id()){
        return true;
      } 
    }
    
    return false;
  }  
  public boolean isRolePermission(long operationID){
    Iterator iuser_role_perm_collect = user_role_perm_collect.iterator();
    while (iuser_role_perm_collect.hasNext()){      
      RolePermission permission = (RolePermission)iuser_role_perm_collect.next();
      if (operationID == permission.getResopid()){
        return true;
      }       
    }
    return false;  
  }
}
ArrayList ressAndOprs = (ArrayList)request.getAttribute("ressAndOprs");
ArrayList ress = (ArrayList)ressAndOprs.get(0);
Map oprs = (Map)ressAndOprs.get(1);
Collection userRess = (Collection)request.getAttribute("ress");
ArrayList user_res_opt_collect = (ArrayList)request.getAttribute("user_res_opt_collect");
ArrayList user_role_perm_collect = (ArrayList)request.getAttribute("user_role_perm_collect");
%>
<body class="bodybg">
<gmenu:getpathtag/>
<html:form action="/permissionAction.do">
<table width="90%" align="center">
  <tr>
    <td class="TableTitleText">用户权限分配</td>
  </tr>
<tr><td>
<table class="f12">
<%
RecurResource r = new RecurResource();
r.context = context;
r.resources = ress;
r.userRess = userRess;
r.user_res_opt_collect = user_res_opt_collect;
r.user_role_perm_collect = user_role_perm_collect;
r.oprs = oprs;
r.recurResource(-1);
out.println(r.output.toString());
%>
</table>
</td></tr></table>
<p align="center">
<input class="button"  type="submit" name="save" value="保存">
<input class="button"  type="reset" value="重置">
<input class="button"  type="button" value="返回" onclick="javascript:doBack();">
<input type="hidden" name="action" value="savaUserPerm" />
<input type="hidden" name="userid" id="userid" value="<%=request.getParameter("userid")%>" />
</p>
</html:form>
<br><br>
</body>