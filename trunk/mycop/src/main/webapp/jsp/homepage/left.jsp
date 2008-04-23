<%@ page contentType="text/html;charset=gb2312"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%@ page import="com.gever.struts.Constant"%>
<html>
<head>
<title>主界面左边图标栏</title>
<%
	String context =request.getContextPath();
	int iNext = 0 ;
%>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<jsp:include page="/jsp/jsp_css.jsp" />
<SCRIPT LANGUAGE="JavaScript">
<!--

var imgPreLoad1=new Image();
    imgPreLoad1.src="<%=context%>/images/ico_yyj.gif";
    imgPreLoad1.src="<%=context%>/images/ico_ydx.gif";
    imgPreLoad1.src="<%=context%>/images/but_r1_c1.gif";
    imgPreLoad1.src="<%=context%>/images/but_r8_c1.gif";
    imgPreLoad1.src="<%=context%>/images/but_r17_c1_F2.gif";
    imgPreLoad1.src="<%=context%>/images/new_mail.gif";
    imgPreLoad1.src="<%=context%>/images/new_sim.gif";
    imgPreLoad1.src="<%=context%>/images/but_r5_c1.gif";
var noReadMail =0; //未读邮件数
var noReadMsg =0;  //未读短信数

function toRight(){
    try{
        window.parent.right.document.frames[0].toRefresh(noReadMail,noReadMsg);
    }catch(e){}
}
var openStatus=false;
function closewin(){
    if (openStatus == false){
        window.parent.right.closewin(true);
    } else {
        window.parent.right.closewin(false);
    }
    openStatus =!openStatus;
}
function toRefresh(p1, p2,p3,p4){
    try{
        if (p1 != noReadMail){

            if (p1==0){
                mailImg.oSrc = "<%=context%>/images/but_r1_c1.gif";  //主要解决切换图片
                mailImg.src = "<%=context%>/images/but_r1_c1.gif";
                mailImg.alt="无新邮件";
            } else {
                mailImg.oSrc = "<%=context%>/images/new_mail.gif";
                mailImg.src = "<%=context%>/images/new_mail.gif";
                mailImg.alt="您有" +p1 +"封新邮件!";
            }
        }
    }catch(e){
    }
    try{
        if (p2 != noReadMsg){
            if (p2==0){
                msgImg.oSrc = "<%=context%>/images/but_r5_c1.gif"; //主要解决切换图片
                msgImg.src = "<%=context%>/images/but_r5_c1.gif";
                msgImg.alt="无新短信";
            } else {
                msgImg.oSrc = "<%=context%>/images/new_sim.gif";
                msgImg.src = "<%=context%>/images/new_sim.gif";
                msgImg.alt="您有" +p2 +"条新短信!";
            }
        }


    }catch(e){
    }
    noReadMail = p1;
    noReadMsg = p2;
    try{
        if (window.parent && window.parent.right) window.parent.right.document.frames[0].toRefresh(p1,p2);
    }catch(e){}
    document.getElementById("onlineImg").alt="在线人数:" +p3 +"位";
    document.UserMenuForm.worklogid.value=p4;

}
function toClosed(){
    window.parent.location.href = "<%=context%>/index.jsp";
}
function f_load(){
    var date = new Date();
    var hlFrame = document.getElementById("hiddenleft");
    var rnd="rnd="+date.getTime();
    //document.frames["hiddenleft"].location.href = "<%=context%>/admin/homepage/hiddenleft.do?"+rnd;
    hlFrame.src = "<%=context%>/admin/homepage/hiddenleft.do?"+rnd;
}


function toMyUrl(strUrl,nodeId){
    var strValue = strUrl
    // 2005-04-05 李炽明 增加
    // 解决页面导航栏冲突问题(非终极解决办法)
    // ---------------------------------------------------------------------------------
    // 先备份原nodePathName值，计算完毕后再恢复原值。
    if (window.parent&&
        window.parent.right&&
        window.parent.right.document&&
        window.parent.right.document.UserMenuForm&&
        window.parent.right.document.UserMenuForm.nodePathName){
        var nodePathNameBak = window.parent.right.document.UserMenuForm.nodePathName.value;
        window.parent.right.nodeid2Path(nodeId);
        window.parent.right.url2Path(strValue);
        document.UserMenuForm.nodePathName.value = window.parent.right.document.UserMenuForm.nodePathName.value;
        window.parent.right.document.UserMenuForm.nodePathName.value = nodePathNameBak;
    }
    // ---------------------------------------------------------------------------------
    //window.parent.frames['right'].nodeid2Path(nodeId);
    //window.parent.frames["right"].url2Path(strValue);
    var pos =0;
    var targetFlag = "";
    pos = strUrl.indexOf(":");
    if (strUrl=="none"){
        return false;
    }
    if (pos >0){
        targetFlag = strValue.substring(0,pos);
        strValue = strValue.substring(pos +1,strValue.length);
    } else {
        targetFlag = "R";

    }
    if (targetFlag=="B"){
        target = "_blank"
         doUrlCenter2(context +strValue,60,60);
    } else if (targetFlag=="X"){  //短消息特别处理
        openchromeless("<%=context%>/adminbsnmng/message/messageindex.do",'mobile',640,380,(screen.availWidth-550)/2,(screen.availHeight-210)/2,"<%=context%>/images/window-bg.gif","<%=context%>/images/window-gb.gif","<%=context%>/images/window-gb.gif","<%=context%>/images/window-gb.gif","<%=context%>/images/window-zxh.gif","<%=context%>/images/window-zxh.gif","<%=context%>/images/window-zxh.gif",'','','Mobile','black','black','','');
    } else if (targetFlag == "Y"){
        window.parent.left.location="<%=context%>/frames/left/FuncDeptManager.jsp"; window.parent.body.location="<%=context%>/zzjg/user.jsp";
    } else if (targetFlag=="W"){  //万年历特别处理
        doUrlCenter2("<%=context%>/dailyoffice/tools/wannianli.html",60,60);
    } else {
        target = "_blank"
        doUrlCenter2(context +strValue,60,60);
    }
}
	//每隔指定的时间就执行一次表达式
	window.setInterval("f_load()",50*1000);
//-->
</SCRIPT>
<script language="JavaScript" type="text/JavaScript">
<!--
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
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
</head>

<body class="bodybg" onLoad="MM_preloadImages('<gdp:context/>/images/but_r1_c1_f2.gif','<gdp:context/>/images/but_r5_c1_f2.gif','<gdp:context/>/images/but_r8_c1_f2.gif','<gdp:context/>/images/but_r11_c1_f2.gif','<gdp:context/>/images/but_r12_c1_f2.gif','<gdp:context/>/images/but_r15_c1_f2.gif','<%=request.getContextPath()%>/images/but_r17_c1_F2.gif','<%=request.getContextPath()%>/images/but_r17_c1_F2.gif','<gdp:context/>/images/%D4%DA%CF%DF%D3%C3%BB%A7_f2.gif')">
<script>
	try{
		document.body.onmousedown=function(){top.frames["right"].hideAllMenu();}	
	}catch(e){
	//
	}
</script>
<table height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr> 
    <td width="24" height="3" background="<gdp:context/>/images/bjtbl.jpg"><img src="<gdp:context/>/images/kjtbl_bg.gif" width="25" height="5"></td>
  </tr>
  <tr> 
    <td align="center" valign="top" background="<gdp:context/>/images/kjtbl_bg.gif"> 
      <table height="300" border="0" cellpadding="0" cellspacing="1">
        <tr height="80"> 
          <td align="center">&nbsp;</td>
        </tr>
        <tr> 
          <td align="center"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image32','','<gdp:context/>/images/but_r1_c1_f2.gif',1)"><img src="<gdp:context/>/images/but_r1_c1.gif" alt="" name="Image32" width="20" height="20" border="0"></a></td>
        </tr>
        <tr> 
          <td align="center"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('zjImg','','<gdp:context/>/images/sjzc_f2.gif',1)"><img id ="zjImg" src="<gdp:context/>/images/sjzc.gif" alt="资源共享" style="cursor:hand"  name="Image39"  border="0" width="20" height="20" onclick="MM_openBrWindow('<gdp:context/>/infoservice/publicinfoframe.jsp','','scrollbars=yes,resizable=yes')"></a></td>
        </tr>
        <tr> 
          <td align="center"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('kqImg','','<%=context%>/images/kq_f2.gif',1)"><img id ="kqImg" src="<%=context%>/images/kq_f1.gif" alt="考勤系统" style="cursor:hand"  name="kqImg"  border="0" width="20" height="20" onclick="kaoqing()"></a></td>
        </tr>
        <tr> 
          <td align="center"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('worklogImg','','<%=context%>/images/rj_f2.gif',1)"><img id ="worklogImg" src="<%=context%>/images/rj_f1.gif" alt="工作日志" style="cursor:hand"  name="worklogImg"  border="0" width="20" height="20" onclick="doWorkLog()"></a></td>
        </tr>
        <tr> 
          <td align="center"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image39','','<%=context%>/images/onlineuse_f2.gif',1)"><img id ="onlineImg" src="<%=context%>/images/onlineuse.gif" alt="在线人数有1位" style="cursor:hand"  name="Image39"  border="0" width="20" height="20" onclick="MM_openBrWindow('viewuser.jsp','','scrollbars=yes,resizable=yes')"></a></td>
        </tr>
	<tr>
          <td align="center"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image37','','<gdp:context/>/images/but_r17_c1_F2.gif',1)"><img src="<gdp:context/>/images/but_r17_c1.gif" alt="菜单定制" name="Image37" width="20" height="20" border="0"></a></td>
        </tr>
        
      </table>
    </td>
  </tr>
  <tr>
    <td>
      <form name="UserMenuForm" method="post" action="">
        <input type="hidden" name="nodePathName" value="天行办公自动化平台">
        <input type="hidden" value="" name="worklogid">
      </form>
    </td>
  </tr>
</table>
<IFRAME frameBorder=1 id=hiddenleft src="" >
</IFRAME>
<SCRIPT LANGUAGE="JavaScript">
<!--
    function doWorkLog(){
        var url = "<%=context%>/dailyoffice/worklog/editworklog.do";
        var fid = document.UserMenuForm.worklogid.value;
        if(fid != ""){
            url=url+"?actionType=modify&fid="+fid;
        }else{
        }

        doWorklogRight(url,60,60);
    }
    function doWorklogRight(url){
        var date = new Date();
        var rnd="rnd="+date.getTime();
        url=addPara(url,rnd);

    //该方法设置日志的导航拦
        window.parent.right.url2Path("/dailyoffice/worklog/listworklog.do");

        window.parent.right.document.frames[0].location=url;
    }
    function kaoqing(){
        alert("考勤系统正在建设中...");
    }
    f_load();
//-->
</SCRIPT>
</body>
</html>
