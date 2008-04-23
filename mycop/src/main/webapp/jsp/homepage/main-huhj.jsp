<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%@ page language="java" %>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ page import="java.util.*,com.gever.web.homepage.vo.*"%>
<%@ page import="com.gever.sysman.privilege.util.*"%>
<% String context =request.getContextPath();%>
<% String userID = (String)session.getAttribute(Constants.USERID);%>
<title>天行协同办公主页</title>
<script>
	//***********************************************************************************
	//资治国添加,解决netscape兼容性问题
	//添加浏览器类型及版本检测代码
	//可以在此之前(如登录页)提供浏览器的版本及类型支持性判断代码
	//此后程序一般只判断是ns还是ie来做兼容性编程
	//***********************************************************
	var ns=navigator.appName=="Netscape";//是否为netscape
	var ie=navigator.appName=="Microsoft Internet Explorer";//是否为internet exporer
	var mozillaVersion=parseFloat(navigator.appVersion);//Mozilla版本号,如2.0,4.0,5.0
	var nsVersion=0;
	var ieVersion=0;
	var ua=navigator.userAgent.toLowerCase();
	if(ns){
	  var nsIndex=ua.indexOf("netscape/");
	  var tmpUa=ua.substring(nsIndex+9);
	  nsVersion=parseFloat(tmpUa);
	}
	if(ie){
	  var msieIndex=ua.indexOf("msie");
	  var tmpUa=ua.substring(msieIndex+4);
	  var msiedotIndex=tmpUa.indexOf(";");
	  ieVersion=parseFloat(tmpUa.substring(0,msiedotIndex));
	}

	//如果为NS,则增加HTMLElement的insertAdjacentElement,insertAdjacentHTML,insertAdjacentText方法,以缩小和IE的区别
	if(ns)
	{
			HTMLElement.prototype.insertAdjacentElement = function(where,parsedNode)
			{
				//为提供更大的兼容性,将where解释为大小写无关.
				where=where.toLowerCase();
			
				switch (where){
				case 'beforebegin':
					this.parentNode.insertBefore(parsedNode,this)
					break;
				case 'afterbegin':
					this.insertBefore(parsedNode,this.firstChild);
					break;
				case 'beforeend':
					this.appendChild(parsedNode);
					break;
				case 'afterend':
					if (this.nextSibling) 
		                 this.parentNode.insertBefore(parsedNode,this.nextSibling);
					else this.parentNode.appendChild(parsedNode);
					break;
				}
			}
			HTMLElement.prototype.insertAdjacentHTML = function(where,htmlStr){
				var r = this.ownerDocument.createRange();
				r.setStartBefore(this);
				var parsedHTML = r.createContextualFragment(htmlStr);
				this.insertAdjacentElement(where,parsedHTML)
			}
			HTMLElement.prototype.insertAdjacentText = function(where,txtStr){
				var parsedText = document.createTextNode(txtStr)
				this.insertAdjacentElement(where,parsedText)
			}
	}
</script>

<link type="text/css" rel="stylesheet" href="<%=context%>/common/menu/menu1.css" id="menuStyleSheet">
<jsp:include page="/jsp/jsp_css.jsp" />
<SCRIPT LANGUAGE="JavaScript" src="<%=context%>/common/menu/gmenu.js"></SCRIPT>

<% 
	ArrayList aryMenus =(ArrayList) request.getAttribute("menus");
	String firstId = "";
%>
<script language="JavaScript">

	var context = "<%=context%>";
	Menu.context=context;
	Menu.filepath = context+"/common/menu/menuContainer.htm";
	var aryMenuFirst = new Array();
	var aryMenus = new Array();
	var aryLength =<%= aryMenus.size() %> ; 
	var obj = new Object();
	
	var i = 0;

	var currentTdObj=null;
	var imgPreLoad1=new Image();
	imgPreLoad1.src="<%=context%>/jsp/homepage/images/top_r4_c2.gif";
	imgPreLoad1.src="<%=context%>/jsp/homepage/images/top_r4_c6.gif";
	imgPreLoad1.src="<%=context%>/jsp/homepage/images/top_r4_c12.gif";
	imgPreLoad1.src="<%=context%>/jsp/homepage/images/top_r4_c3.jpg";
	imgPreLoad1.src="<%=context%>/jsp/homepage/images/top_r4_c10.jpg";

	//切换第一级菜单,panel,汤启礼添加
	function switchPanel(strID,event){
        if(ns)
            var tdObj=event.target;
        else
            var tdObj=event.srcElement;
		var parObj=tdObj;
				//alert("---");
		while(parObj.tagName!="TD"){
            if(ns)
                parObj=parObj.parentNode;
			else
                parObj=parObj.parentElement;
		}
		if(currentTdObj==null){
			currentTdObj=parObj;
            if(ns){
                currentTdObj.background="images/top_r4_c3.jpg";

                if(currentTdObj.previousSibling.hasChildNodes())
                    currentTdObj.previousSibling.childNodes[0].src="images/top_r4_c2.gif";
                if(currentTdObj.nextSibling.hasChildNodes())
			        currentTdObj.nextSibling.childNodes[0].src="images/top_r4_c6.gif";
            } else {
                currentTdObj.background="images/top_r4_c3.jpg";

                if(currentTdObj.previousSibling.hasChildNodes())
			        currentTdObj.previousSibling.children[0].src="images/top_r4_c2.gif";
                if(currentTdObj.nextSibling.hasChildNodes())
			        currentTdObj.nextSibling.children[0].src="images/top_r4_c6.gif";
            }
			currentTdObj.className="menu1";
		} else {
			if(parObj!=currentTdObj){
                //*******************************************************************************
                // 胡勇添加，兼容Netscape ##########################
                // 解决菜单显示背景问题
            try{
                if(ns){
                    currentTdObj.setAttribute("background","<%=context%>/jsp/homepage/images/top_r4_c10.jpg");
                //*******************************************************************************
                // currentTdObj.previousSibling 在Netscape中为Text对象，不是要更改的节点
                // 替代方法：currentTdObj.previousSibling.previousSibling.childNodes[0]
                // 或者：此出currentTdObj.parentNode.childNodes[3] 与 currentTdObj是同一个节点
                // currentTdObj.parentNode.childNodes[1].childNodes[0] 与 currentTdObj.parentNode.childNodes[5].childNodes[0]是要更改的对象
                //    currentTdObj.parentNode.childNodes[1].childNodes[0].setAttribute("src","<%=context%>/jsp/homepage/images/top_r4_c8.gif");
				//    currentTdObj.parentNode.childNodes[5].childNodes[0].setAttribute("src","<%=context%>/jsp/homepage/images/top_r4_c12.gif");
                    var pre_target = currentTdObj.previousSibling.previousSibling;
                    var next_target = currentTdObj.nextSibling.nextSibling;
                    if(pre_target.hasChildNodes())
				        pre_target.childNodes[0].setAttribute("src","<%=context%>/jsp/homepage/images/top_r4_c8.gif");
                    if(next_target.hasChildNodes())
				        next_target.childNodes[0].setAttribute("src","<%=context%>/jsp/homepage/images/top_r4_c12.gif");
                //*******************************************************************************
                } else {
				    currentTdObj.background="<%=context%>/jsp/homepage/images/top_r4_c10.jpg";

                    if(currentTdObj.previousSibling.hasChildNodes())
				        currentTdObj.previousSibling.children[0].src="<%=context%>/jsp/homepage/images/top_r4_c8.gif";
                    if(currentTdObj.nextSibling.hasChildNodes())
				        currentTdObj.nextSibling.children[0].src="<%=context%>/jsp/homepage/images/top_r4_c12.gif";
                }
            } catch(e) {  }
				currentTdObj.className="menu2";
				currentTdObj=parObj;
            
            try{
                if(ns){
                //*******************************************************************************
                    parObj.setAttribute("background","<%=context%>/jsp/homepage/images/top_r4_c3.jpg");

                    var pre_target = parObj.previousSibling.previousSibling;
                    var next_target = parObj.nextSibling.nextSibling;
                    if(pre_target.hasChildNodes())
				        pre_target.childNodes[0].setAttribute("src","<%=context%>/jsp/homepage/images/top_r4_c2.gif");
                    if(next_target.hasChildNodes())
				        next_target.childNodes[0].setAttribute("src","<%=context%>/jsp/homepage/images/top_r4_c6.gif");
                //*******************************************************************************
                } else {
				    parObj.background="<%=context%>/jsp/homepage/images/top_r4_c3.jpg";

                    if(currentTdObj.previousSibling.hasChildNodes())
                        parObj.previousSibling.children[0].src="<%=context%>/jsp/homepage/images/top_r4_c2.gif";
                    if(currentTdObj.nextSibling.hasChildNodes())
                        parObj.nextSibling.children[0].src="<%=context%>/jsp/homepage/images/top_r4_c6.gif"; 
                }
            } catch(e) {  }
            
                // 胡勇更改代码结束 ##############
                //*******************************************************************************
                parObj.className="menu1";
			}
		}
		onchangeMenu(strID); //改变第二级菜单内容
	}

	//重新刷新
	function toRefresh(){
		document.forms[0].submit();
	}
	
	function MM_swapImgRestore() { //v3.0
	  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
	}

	function MM_preloadImages() { //v3.0
	  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
		var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
		if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
	}

	function MM_findObj(n, d) { //v4.01
	  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
		d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
	  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
	  if(!x && d.getElementById) x=d.getElementById(n); return x;
	}

	function MM_swapImage() { //v3.0
	  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
	   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
	}

	/**
	 * nodeId 当前第一级菜单
  	 * 当改变第一层菜单时,需要改变二级菜单的内容
	 */
	function onchangeMenu(nodeId){
		//alert(nodeId);
		var sobj = new Object();		
		//*****************************************************
		//资治国添加,解决netscape兼容性问题

     
		var strSpan  ="";
         if(ie){
			strSpan="<nobr><span class=\"menuBar\" id=\"menuBar\" onmouseover=\"menuBarOver()\" onmouseout=\"menuBarOut()\" onclick=\"menuBarClick()\">";
		 }
         if(ns){
			strSpan="<nobr><span class=\"menuBar\" id=\"menuBar\" onmouseover=\"menuBarOver()\" onmouseout=\"menuBarOut()\" onclick=\"menuBarClick(event)\">";
		 }

		var strTmp = "";
		for (var idx =0; idx < aryMenus.length; idx++){
			sobj = aryMenus[idx];
			if (sobj.parentID == nodeId && sobj.isFolder =="1"){
				//此处需要改进scriplet机制的依赖实现,以适应netscape7.0上的同样机制的实现.
				//***
				//***
				strTmp = "<span align='absmiddle' class=\"root\"  menu=\"gevermenu"+sobj.nodeID+"\">" + "<b>" + "·" + "</b>" + "&nbsp;" + sobj.nodeName + "</span>";	
				//alert(strTmp);
				strSpan += strTmp;
			} else if(sobj.parentID == nodeId && sobj.isFolder !="1") {
				//strSpan += " ·<a onmouseover=\"hideAllMenu();\" href=" +  sobj.link + " class=\"menu3\">" + sobj.nodeName +"</a>";
				strSpan += "<span align='absmiddle' class=\"menua\" onmouseover=\"this.className='menuaover'\"  onmouseout=\"this.className='menua'\"  onclick=\"toUrl('" +  sobj.link + "','" +sobj.nodeID +"' )\" >" + "<b>" +  "·" + "</b>" + "&nbsp;" + sobj.nodeName +"</span>";

			}
		}
		//*****************************************************
		//资治国添加,解决netscape兼容性问题
		//以下考虑netscpae的兼容性问题
		//Netscape
		if(ns){
		menusToolbar=document.getElementById("menusToolbar");
		}

		menusToolbar.innerHTML = strSpan + "</span></nobr>";

       //*************************************************************
	}
    function myload(){
		//*****************************************************
		//资治国添加,解决netscape兼容性问题
		if(ie){
			document.frames('frmcontent').document.body.onclick=function(){hideAllMenu()}
		}
		if(ns){
			document.getElementById('frmcontent').contentDocument.body.onclick=function(){hideAllMenu()};
		}
	}
</script>
</head>
<body class="bodybg" onscroll="return false" onload="javascript:myload()"> 
<div onclick="event.cancelBubble=true;return false;" id="loadPage" style="display:none"><a name="top"></a> 
  	<table height=95% align="center">
	<tr>
		<td>
		<span class="f12" id="loadtitle">开始装载数据...</span>
		<p align=left style="border:black 1px solid;width:400px">
		<img src="<%=context%>/jsp/homepage/images/loading.gif"  height=23 id=chart width=0></p>
		<div id=percent></div>
			
		</a>
		</td>
	</tr>
	</table>
</div>
<div id="mainContent" style="display:block">
<html:form action="/homepage/maintop">
<input type="hidden" name="nodePathName" value="">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr> 
    <td height="86" valign="top"> 
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="topbg">
        <tr> 
          <td width="10">&nbsp;</td>
	<!--是否是管理员,管理员可以双击进入系统设定 -->
	
     <td width="200" align="left" height="45"  class="logo"	>
	
	&nbsp;
	</td>


     <td align="right"  height="45" class="logo" >
	
		<img src="<%=context%>/jsp/homepage/images/gever_banner.gif" width="128" height="45">
	</td>
          <td  width="320" align="right"> <table width="220" height="33" border="0" cellpadding="0" cellspacing="0">
              <tr align="center" valign="top"> 
                <td height="27"><a href="<gdp:context/>/jsp/homepage/frame.jsp" target="_top" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image26','','<%=context%>/jsp/homepage/images/ico_r1_c1_f2.gif',1)"><img border="0" src="<%=context%>/jsp/homepage/images/ico_r1_c1.gif" alt="返回主页"
				 name="Image26" width="20" height="20" border="0"></a>
				</td>
               
                <td><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image28','','<%=context%>/jsp/homepage/images/ico_r1_c5_f2.gif',1)"><img src="<%=context%>/jsp/homepage/images/ico_r1_c5.gif" alt="系统信息" border="0" name="Image28" ></a></td>
               
                <td><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image30','','<%=context%>/jsp/homepage/images/ico_r1_c9_f2.gif',1)"><img src="<%=context%>/jsp/homepage/images/ico_r1_c9.gif"  alt="离线设置" name="Image30" width="20" height="20" border="0"></a></td>
			   <td>
			   <%
		       String href3="/privilege/userAction.do?action=toPassword2"+"&id="+userID;
		       %>
			   
			    <a href=<%=context%><%=href3%> target="_blank" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image31','','<%=context%>/jsp/homepage/images/login.gif',1)"><img src="<%=context%>/jsp/homepage/images/login_f2.gif"  alt="密码修改" name="Image31" width="20" height="20" border="0"></a>
			   
			   </td>
			    <td><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image29','','<%=context%>/jsp/homepage/images/ico_r1_c7_f2.gif',1)"><img src="<%=context%>/jsp/homepage/images/ico_r1_c7.gif"   alt="帮助" name="Image29" width="20" height="20" border="0"></a></td>

			    <td>
				<%
		       String href4="/privilege/loginAction.do?action=logout";
		       %>
				<a href="<%=context%><%=href4%>" target="_top" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image27','','<%=context%>/jsp/homepage/images/relogin.gif',1)"><img src="<%=context%>/jsp/homepage/images/relogin2.gif" alt="重新登陆"  name="Image27" width="20" height="20" border="0"></a></td>
              </tr>
            </table></td>
        </tr>
		</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="topbg">
        <tr> 
          <td width=20 class="menu1">&nbsp;</td>
          <td colspan="3"><table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <%
				String strNodeID = "";
				String parentID ="";
				boolean isFirst = true;
				int iCount = 0;
				
				UserMenuVO firstView = new UserMenuVO();
				for (int idx=0; idx<aryMenus.size(); idx++){
					UserMenuVO view = new UserMenuVO();
					view = (UserMenuVO)aryMenus.get(idx);
					strNodeID = view.getNodeid();
					parentID = view.getParentid();

					if ("0".equals(parentID) && isFirst == true ){
						++iCount;
						isFirst = false;
						firstView = view;
						firstId = strNodeID;
					%>
                <td ><table border="0" cellspacing="0" cellpadding="0">
                    <tr> 
                      <td ><img src="<%=context%>/jsp/homepage/images/top_r4_c2.gif" width="4" height="17"></td>
                      <td id ="myfirst" background="<%=context%>/jsp/homepage/images/top_r4_c3.jpg" style="cursor:hand" class="menu1" onclick="switchPanel('<%=strNodeID%>',event)"><%=view.getNodename() %></td>
                      <td><img src="<%=context%>/jsp/homepage/images/top_r4_c6.gif" width="4" height="17"></td>
                    </tr>
                  </table></td>
                <%
					 } else if ( isFirst == false && "0".equals(parentID)) {
							++iCount;
					%>
                <td><table border="0" cellspacing="0" cellpadding="0">
                    <tr> 
                      <td><img src="<%=context%>/jsp/homepage/images/top_r4_c8.gif" width="4" height="17"></td>
                      <td background="<%=context%>/jsp/homepage/images/top_r4_c10.jpg" class="menu2" onclick="switchPanel('<%=strNodeID%>',event)" style="cursor:hand"><%=view.getNodename()%></td>
                      <td><img src="<%=context%>/jsp/homepage/images/top_r4_c12.gif" width="4" height="17"></td>
                    </tr>
                  </table></td>
                <%
					}%>
                <SCRIPT LANGUAGE="JavaScript">
				<!--
					var obj = new Object();
					obj.nodeID = "<%=strNodeID%>";
					obj.nodeName = "<%= view.getNodename()%>";
					obj.pathName = "<%= view.getPathname()%>";
					obj.link = "<%= view.getLink()%>";
					obj.isFolder = "<%=view.getIsfolder()%>";
					obj.parentID = "<%= view.getParentid()%>";
					obj.linkmode = "<%= view.getLinkmode()%>";
					aryMenus[i++] = obj;

				//-->
				</SCRIPT>
                <%}
				%>
              </tr>
            </table></td>
        </tr>
		</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="topbg">
        <tr> 
          <td height="25" width=20 background="<%=context%>/jsp/homepage/images/top.jpg">&nbsp;</td>
          <td height="25"  background="<%=context%>/jsp/homepage/images/top.jpg"><table border="0" cellspacing="1" cellpadding="1">
              <tr class="menu1">
                <td align="left"> <span id ="menusToolbar"> </span> </td>
                  </tr>
                </table></td>
            </tr>
          </table></td>
  </tr>
  <tr>
    <td valign="top">
		<%//此处的iframe需要考虑动态调整iframe尺寸问题---状态:待解决%>
		<IFRAME id="frmcontent"  border="0" framespacing="0" frameborder="NO"  width="100%" height="100%" name="frmcontent" src=""></IFRAME>
		<script>
			function dynReSizeFrmContent(){
				var frmObj=document.getElementById("frmcontent");
				if(ns){
						   var ifmWidth=window.innerWidth;
						   var ifmHeight=window.innerHeight;
							var tBodyObj=frmObj.parentNode.parentNode.parentNode;
							//得到TRs
							var trObjs=new Array();
							var trObjIdex=0;
							if(tBodyObj!=null){
							  var tBodyKids=tBodyObj.childNodes;
								for(var i=0;i<tBodyKids.length;i++){
								  if(tBodyKids[i].nodeType==1&&tBodyKids[i].nodeName=="TR"){
									 trObjs[trObjIdex++]=tBodyKids[i];
								  }
								}
							}
							if(trObjs.length>0){
							   for(trObjIdex=0;trObjIdex<trObjs.length;trObjIdex++){
								  var trObj=trObjs[trObjIdex];
								  if(frmObj.parentNode.parentNode!=trObj){
									ifmHeight-=trObj.offsetHeight;
								  }
							   }
							} 
						frmObj.width=ifmWidth;
						frmObj.height=ifmHeight;
				}
				if(ie){
						frmObj.width="100%";
						frmObj.height="100%";
				}
			}
           dynReSizeFrmContent();
		</script>
	</td>
  </tr>
</table>
</html:form>
</div>
<gmenu:menu menuId="menu1" property="xmldata" />
<iframe id="frmLog" src=""  frameBorder=1 style="display:none" ></iframe>
<SCRIPT LANGUAGE="JavaScript">
<!--
    //*****************************************************
	//资治国添加,解决netscape兼容性问题
	//如果客户端为netscpae,则使用以下语句避免document.all错误
	//在ie中是不需要的
	if(ns){
		document.all=document.getElementById;
	}
	//****************************************************

	currentTdObj = document.all("myfirst"); //得到当前第一个节点
	mmLoadMenus();
	onchangeMenu("<%=firstId%>");
	var menuCount=0;
	var initComplete=false;

    
	
	//将菜单加入到内存当中，以免速度太慢
	for(var i=0;i<aryMenus.length;i++){
		if(aryMenus[i].isFolder=="1" && aryMenus[i].nodeID.length>2){
			alert(aryMenus[i].nodeName)
			try{
				if(document.all["gevermenu"+aryMenus[i].nodeID]!=null){
					if(document.all["gevermenu"+aryMenus[i].nodeID].className=="menu"){
						menuCount++;
						showMenu(document.all["gevermenu"+aryMenus[i].nodeID],new Boundaries(-1000,-1000));
					}
				}
			}catch(e){

			}
		}
	}

	//开始启动进度条。
	if(menuCount ==0){
		document.all("loadtitle").innerText="正在载入桌面...";
	  if(ie){
		   	document.frames[0].document.location.href="<%=context%>/jsp/homepage/right.jsp";
		 }
		 if(ns){
		   	document.getElementById("frmcontent").contentDocument.location.href="<%=context%>/jsp/homepage/right.jsp";
		 }
        //**********************************************************************************************************
	}

	var maxMenu = 0;
	var minWidth = 1;
	var isFirstMax = true;

	//计算进度条信息
	function menu_onComplete(){
		
		if (isFirstMax == true){
			maxMenu = menuCount;
			isFirstMax = false;
			minWidth = 100/maxMenu  ;
			alert(maxMenu + " width=" + minWidth)
		}
		document.all("loadtitle").innerText="正在载入菜单...";
		document.all("chart").width=4*minWidth*(maxMenu-menuCount-1);
		if(!initComplete){

			if(menuCount--<=1){
				//document.all.divtest.style.display="none";
				//在这里添加代码
				document.all("loadtitle").innerText="正在载入桌面...";
	
			if(ie){
				document.frames[0].document.location.href="<%=context%>/jsp/homepage/right.jsp";
				document.forms[0].nodePathName.value = "我的桌面";
			}
			
			if(ns){
				document.getElementById("frmcontent").contentDocument.location.href="<%=context%>/jsp/homepage/right.jsp";
				document.forms[0].nodePathName.value = "我的桌面";
			}
				initComplete=true;
	
			}
		}
		
	}

	//装载菜单完毕
	function loadDeskCallBack(){
		document.all("chart").width=400;
		document.all("loadtitle").innerText="装载完毕";
		document.getElementById("loadPage").style.display = "none";
		document.getElementById("mainContent").style.display = "block";
		
	}

	//单击菜单项
	function menu_onclick(obj){
		var url = obj.getAttribute("url");
		if (url==null || url=="")
				return false;
		alert(obj.getAttribute("pathname"));
		document.forms[0].nodePathName.value=obj.getAttribute("pathname");
		myOpenWin(url,0);
	}

	//对外的接口，传入用户
	function toUrl(url,sNodeid,mode){
		nodeid2Path(sNodeid);
		myOpenWin(url,mode)
	}

	//根据节点id给得到路径名称
	function nodeid2Path(sNodeid){
		var sobj = new Object();

		for (var idx = 0 ; idx <aryMenus.length;idx++){
			sobj = aryMenus[idx];
			if (sobj.nodeID==sNodeid){
				document.forms[0].nodePathName.value=sobj.pathName;
				break;
			}
		}
	}
	
	function addPara(url,  keyValue) {
		if (url == null) {
			return "";
		}
		var index = url.indexOf("?");
		if (index < 0) {
			return url + "?" + keyValue;
		} else {
			return url + "&" + keyValue;
		}
	}

	//打开菜单
	function myOpenWin(url,mode){
		var pos = -1;
		var bNew = false;
		var date=new Date();
		var rnd="rnd="+date.getTime();
		url=addPara(url,rnd);
		if (mode == 1) { //是window.open方式打开
				url = context + url;
				bNew = true;
				var geverwindow = window.open(url,"",'resizable=yes,scrollbars=no,status=no,toolbar=no,menubar=no,location=no')
				geverwindow.focus();
				geverwindow.moveTo(50,50);
				geverwindow.resizeTo(screen.availWidth-100,screen.availHeight-100);
		} else {
				window.open(context + url, 'frmcontent');
		}
	}

//-->
</SCRIPT>
</body>
</html>
