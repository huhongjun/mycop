<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.gever.struts.Constant"%>
<%@ taglib uri="/WEB-INF/taglib/struts-tiles.tld" prefix="tiles" %>
<html>
<%String context =request.getContextPath();%>
<%//曾祥泳加 判断是否仍处于登陆状态
	if(!com.gever.goa.web.util.RequestUtils.isLogon(pageContext)){
		response.sendRedirect(""+context+"/jsp/error/reLogon.jsp");
	}
%>


<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<link href="<%=com.gever.goa.web.util.RequestUtils.getCSSPath(pageContext)%>" id="goastylecss" rel="stylesheet" type="text/css">
<html>
<head>
<title>菜单定制</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=context%>/common/xtreeEx/xtree.js"></script>
<script type="text/javascript" src="<%=context%>/common/xtreeEx/xmlextras.js"></script>
<script type="text/javascript" src="<%=context%>/common/xtreeEx/xloadtree.js"></script>
<link type="text/css" rel="stylesheet" href="<%=context%>/common/xtreeEx/xtree.css" />
<link type="text/css" rel="stylesheet" href="<%=context%>/css/css.css" />
</head>
<body >
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

//tree.setBehavior("classic");
var root = "0";
var context = "<%=context%>";

//重新装载
function reload(nodeid){
  var size=jnodes.length;
	if (nodeid == root){
		tree.reload();
		return;
	}

	for(i=0;i<size;i++){
	  if(jnodes[i].id==nodeid){
		 
		jnodes[i].node.reload();
		jnodes[i].node.expand();
	  }
	}
}

//暂不用
function modifyName(nodeid){
   var size=jnodes.length;
   	if (nodeid == root){
		tree.reload();
		return;
	}
   for(i=0;i<size;i++){
	  if(jnodes[i].id==nodeid){
		  try{
		      jnodes[i].node.parentNode.reload();
			//jnodes[i].node.reload();
			//jnodes[i].node.expand();
		  }catch(e){}
		
        
		//}
	  }
	}
}

//更改当前名称
function reName(nodeid,nodename){
	var size=jnodes.length;
	for(i=0;i<size;i++){
		if(jnodes[i].id==nodeid){
			jnodes[i].node.text = nodename
			jnodes[i].node.rename();
		}
	}
}

//删除当前节点
function delNode(nodeid){
	var size=jnodes.length;
	for(i=0;i<size;i++){
		if(jnodes[i].id==nodeid){
			
			jnodes[i].node.remove();
		}
	}
}

//根目录需要到右边的节点
function toRight(url){
	var date = new Date();
	var rnd="rnd="+date.getTime();
	url = addPara(url,rnd);
	
	window.parent.frames["middle"].location.href=context+(url);
}
</script>

<title><tiles:getAsString name="title"/></title>
<tiles:insert attribute="body"/>
</html>