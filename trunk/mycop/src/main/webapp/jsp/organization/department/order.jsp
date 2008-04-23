<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-pager.tld" prefix="smile" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm" prefix="perm" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>


<html>
<head>
<title></title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js"></script>
<style type="text/css">
</style>
</head>

<script langauge="javascript">
var ns = navigator.appName == "Netscape";

function changeBgColorOnMouseOut(obj){
	obj.style.backgroundColor='';
}
function changeBgColorOnMouseOver(obj){
	obj.style.backgroundColor='#ffffff';
}
<% if(request.getParameter("action")!=null&&!"getListChild".equals(request.getParameter("action"))){
  if(request.getParameter("resid")!=null){	
%>
    //******************************************************************
    if(ns){
        window.parent.document.getElementById("left_tree").contentDocument.location.reload('<%=request.getParameter("resid")%>');
    } else {
        window.parent.frames["left_tree"].reload('<%=request.getParameter("resid")%>');
    }
    //******************************************************************
 <%}}%>
</script>

<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr> 
  <gmenu:getpathtag/>
    <!--<td>系统管理/资源列表</td>-->
  </tr>
</table>
<table width="95%" align="center">
  <tr> 
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="2" class="TableTitleText">部门调整</td>
  </tr>
  <tr> 
    <td colspan="2" class="">




	</td>
  </tr>

  <tr> 
  <td  colspan="2"  class="f12"> 
</table>
<table width="100%" height="125" border="0">
  <tr>
    <td width="93" rowspan="3">

<iframe width=200 height=300 id="sourceTree" src="<gdp:context/>/organization/DepartmentAction.do?action=getTreeRoot&src=DepartmentAction&bNoView=1">
</iframe>
</td>


    <td width=450 align="center">
		将部门:
		<b>
			<span id="sourceNode">
			</span>
	    </b>
		<br>
	<form name="form1" method="post" action=""> 
 <span id="exchangedBtnEnabled" style="display:none">
	<perm:button property="addbt" type="button" styleClass="button" onclick="exchangorder()" value="对换" rescode="GDP-ZYGL" optcode="ALL" />
  </span>
  <span id="exchangedBtnDisabled">
	<input type="button" value="对换" class="button"  disabled>
   </span>
      </form>

	<form name="form1" method="post" action="">
	 <span id="moveBtnEnabled" style="display:none">

	<perm:button  property="addbt" type="button" styleClass="button" onclick="moveid()" value="插入" rescode="GDP-ZYGL" optcode="ALL" />
	</span>
  <span id="moveBtnDisabled">
	<input type="button" value="插入" class="button"  disabled>
   </span>
      </form>
    <form name="form1" method="post" action="">
	   <span id="changePIDBtnEnabled" style="display:none">

	<perm:button  property="addbt" type="button" styleClass="button" onclick="exchangeparentid()" value="更换父类" rescode="GDP-ZYGL" optcode="ALL" />
   </span>

   <span id="changePIDBtnDisabled">
	<input type="button" value="更换父类" class="button"  disabled>
   </span>

      </form>
<br>	  		到部门:
		<b>
			<span id="targetNode">
			</span>
		 </b>

	  </td>
    <td width="254">
	<iframe width=200 height=300 id="targetTree" src="<gdp:context/>/organization/DepartmentAction.do?action=getTreeRoot&src=DepartmentAction&bNoView=1">
</iframe>
</td>


  </tr>
<script>
var sourceNodeId=null;
var sourceParentNodeId=null;

//*******************************************************************************
changePIDBtnEnabled = document.all("changePIDBtnEnabled");
changePIDBtnDisabled = document.all("changePIDBtnDisabled");
moveBtnEnabled = document.all("moveBtnEnabled");
moveBtnDisabled = document.all("moveBtnDisabled");
exchangedBtnEnabled = document.all("exchangedBtnEnabled");
exchangedBtnDisabled = document.all("exchangedBtnDisabled");
sourceTree = document.all("sourceTree");
targetTree = document.all("targetTree");
//*******************************************************************************

var targetNodeId=null;
var targetParentNodeId=null;
function updateNodeIds(treeID,nodeId,pranetNodeId){
	   if(treeID=="sourceNode"){
			sourceNodeId=nodeId;
			sourceParentNodeId=pranetNodeId;
	   }else{
		   //alert(targetTree.rootID+"::"+nodeId+"="+pranetNodeId);
		   if(nodeId!=null&&nodeId!=targetTree.rootID){
				targetNodeId=nodeId;
				targetParentNodeId=pranetNodeId;
		   }else{
				targetNodeId=targetTree.rootID;
				targetParentNodeId=null;

		   }
	   }
	  
       checkButtonValid();
}





function enableBtn(btnObj,btnObjDisable,bEnabled){
  if(bEnabled==true){//使能
      btnObj.style.display="block";  
	  btnObjDisable.style.display="none";  
  }else{
      btnObj.style.display="none";  
	  btnObjDisable.style.display="block";  
  }
}



function checkButtonValid(){
	
	enableBtn(changePIDBtnEnabled,changePIDBtnDisabled,false);
	enableBtn(moveBtnEnabled,moveBtnDisabled,false);
	enableBtn(exchangedBtnEnabled,exchangedBtnDisabled,false);
	//alert();
	//alert(sourceParentNodeId+":"+targetParentNodeId);
   if(sourceParentNodeId==null||(targetParentNodeId==null&&targetNodeId!=targetTree.rootID)){
   //非法
   }else{
	   
	   if(sourceParentNodeId==targetParentNodeId){
		  if(sourceNodeId==targetNodeId){
              //同一个不存在任何变换
		  }else{
             //激活“对换”及"插入"按扭
			 
			enableBtn(moveBtnEnabled,moveBtnDisabled,true);
			enableBtn(exchangedBtnEnabled,exchangedBtnDisabled,true);
			enableBtn(changePIDBtnEnabled,changePIDBtnDisabled,true);

		  }
	   }else{
		  // alert(targetTree.checkParnetIdError);
		   //检查source不能是target的父节点，或者是父节的父节点.......
               //  alert(targetTree.checkParnetIdError);
              if(targetTree.checkParnetIdError(sourceNodeId,targetNodeId)==true){
                 //激活"更改父类"按扭
				 if(sourceParentNodeId==targetNodeId){
				  }else{
				     enableBtn(changePIDBtnEnabled,changePIDBtnDisabled,true);
				  }

			  }


	   }
   }
}

function initTree(){
	try{
    //**********************************************************************
    if(ns){
        sourceTree.contentWindow.curTreeStatusObj = document.getElementById("sourceNode");
        targetTree.contentWindow.curTreeStatusObj = document.getElementById("targetNode");
    } else {
        sourceTree.curTreeStatusObj = document.getElementById("sourceNode");
        targetTree.curTreeStatusObj = document.getElementById("targetNode");
    }
    //**********************************************************************
    }catch(e){
        
	}

}
var isSyncTree=false;
function syncTree(treeID,nodeid){
	try{
		if(isSyncTree==true){
			   return;
		}
	  // alert(treeID+":"+nodeid);
	  isSyncTree=true;
	   if(treeID=="sourceNode"){
            //**********************************************************************
            if(ns) targetTree.contentWindow.syncTree(nodeid);
			else targetTree.syncTree(nodeid);
            //**********************************************************************

	   }else{
			//**********************************************************************
            if(ns) sourceTree.contentWindow.syncTree(nodeid);
			else sourceTree.syncTree(nodeid);
            //**********************************************************************
	   }
	   isSyncTree=false;
	}catch(e){
	  // alert(e.description);
	}
}
function exchangorder(){
location.href="<gdp:context/>/organization/depOrderAction.do?action=exchengId&id1="+sourceNodeId+"&id2="+targetNodeId;
}

function moveid(){
	
location.href="<gdp:context/>/organization/depOrderAction.do?action=moveId&id1="+sourceNodeId+"&id2="+targetNodeId;
}

function exchangeparentid(){
	//alert(sourceNodeId+":"+targetNodeId);
	//return;
location.href="<gdp:context/>/organization/depOrderAction.do?action=exchengParentId&id1="+sourceNodeId+"&id2="+targetNodeId;
}
</script>
</html>