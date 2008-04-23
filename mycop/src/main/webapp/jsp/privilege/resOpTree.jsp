<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.*,com.gever.sysman.privilege.model.*"%>

<%
        response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);%>
<%
//*********************************
//黎彪增加代码开始
//2004-11-18
String bNoView=(String)request.getAttribute("bNoView");
if(bNoView.equals("1")||bNoView.equals("0"))
{
}else{
  bNoView="0";
}

//*********************************
%>
<%	String context =request.getContextPath();%>
<jsp:include page="/jsp/jsp_css.jsp" />
<meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT">

<%
  Resource root=(Resource)request.getAttribute("rootNode");
  //tree root key
  String rootName=root.getName();
  String src=request.getContextPath()+"/privilege/getResOpTreeAction.do?action=getTreeChild&resid=0";
  String action="javascript:void(0)";
  String Script="";
  String nodeid="0";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
//2004-11-18
//黎彪增加
var bNoView="<%=bNoView%>";
var treeInitOK=false;
</script>
<script type="text/javascript" src="<%=context%>/common/xtreeEx/xtree.js"></script>
<script type="text/javascript" src="<%=context%>/common/xtreeEx/xmlextras.js"></script>
<script type="text/javascript" src="<%=context%>/common/xtreeEx/xloadtree.js"></script>
<script type="text/javascript" src="<%=context%>/js/gdp.js"></script>

</head>
<body class="bodybg">
<!--p><button onclick="modify('411','yes')">Reload Item</button></p-->


<script type="text/javascript">

/// XP Look
webFXTreeConfig.rootIcon		= "<%=context%>/common/xtreeEx/images/xp/folder.png";
webFXTreeConfig.openRootIcon	= "<%=context%>/common/xtreeEx/images/xp/openfolder.png";
webFXTreeConfig.folderIcon		= "<%=context%>/common/xtreeEx/images/xp/folder.png";
webFXTreeConfig.openFolderIcon	= "<%=context%>/common/xtreeEx/images/xp/openfolder.png";
webFXTreeConfig.fileIcon		= "<%=context%>/common/xtreeEx/images/xp/file.png";
webFXTreeConfig.lMinusIcon		= "<%=context%>/common/xtreeEx/images/xp/Lminus.png";
webFXTreeConfig.lPlusIcon		= "<%=context%>/common/xtreeEx/images/xp/Lplus.png";
webFXTreeConfig.tMinusIcon		= "<%=context%>/common/xtreeEx/images/xp/Tminus.png";
webFXTreeConfig.tPlusIcon		= "<%=context%>/common/xtreeEx/images/xp/Tplus.png";
webFXTreeConfig.iIcon			= "<%=context%>/common/xtreeEx/images/xp/I.png";
webFXTreeConfig.lIcon			= "<%=context%>/common/xtreeEx/images/xp/L.png";
webFXTreeConfig.tIcon			= "<%=context%>/common/xtreeEx/images/xp/T.png";
webFXTreeConfig.blankIcon       = "<%=context%>/common/xtreeEx/images/xp/blank.png"


var tree = new WebFXLoadTree("<%=rootName%>", "<%=src%>","<%=action%>","<%=Script%>", null, null, null,'<%=nodeid%>');
var rootID="<%=nodeid%>";
document.write(tree);
tree.expand();
    //alert(jnodes);
    //alert(jnodes.length);
//jnodes[jnodes.length].node=tree;
//jnodes[jnodes.length].id='<%=nodeid%>'; 

function reload(nodeid){

  var size=jnodes.length;
    for(i=0;i<size;i++){
	  if(jnodes[i].id==nodeid){
		jnodes[i].node.reload();
		jnodes[i].node.expand();
		}
	}

}
//黎彪增加代码开始2004-11-19
var curTreeStatusObj=null;
function syncTree(nodeid){

	//alert(nodeid);
	if(nodeid=="root"){
         tree.toggle();
	}else{
		try{
		  var jnode=null;
		  var size=jnodes.length;
		  for(i=0;i<size;i++){
			if(jnodes[i].id==nodeid){
				jnode=jnodes[i].node;
			}
		  }
		  if(jnode!=null){
			jnode.toggle();//();  
		  }
		}catch(e){
		  alert("syncTree : "+e.description);
		}
	}
}
function checkParnetIdError(sourceid,targetid){
	//alert(sourceid+":"+targetid);
	  var jnode=null;
	  var size=jnodes.length;
	  for(i=0;i<size;i++){
		if(jnodes[i].id==targetid){
			jnode=jnodes[i].node;
		}
	  }
    if(jnode!=null){
		 // alert(jnode.text);
		 if(jnode.parentNode.nodeid==null){
				  return true;
		 }else{
			 
			 if(jnode.parentNode.nodeid==sourceid){
				  return false;
			 }
			if(checkParnetIdError(sourceid,jnode.parentNode.nodeid)==false){
			  return false;	
			}
		 }
	}
	 return true;
}
		
//#############################黎彪增加结束2004-11-19
function selectItem(resCode,opCode){
    if(navigator.appName == "Netscape") var pForm = window.parent.centerFrame.document.forms[0];
	else var pForm = parent.centerFrame.permissionMapForm;
	pForm.rescode.value = resCode;
	pForm.resoptcode.value = opCode;

	
}

</script>
 <form action="<%=context%>/privilege/<%=request.getParameter("src")%>.do" target="main">
   <input type="hidden" name="action" value="getListChild">
   <input type="hidden" name="resid" id="resid" value="0">
 </form >
<SCRIPT LANGUAGE="JavaScript">
<!--

//2004-11-18
//黎彪增加
treeInitOK=true;
//-->
</SCRIPT> 
</body>
</html>
