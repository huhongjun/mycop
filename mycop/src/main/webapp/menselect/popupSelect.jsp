<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title>
popupSelect
</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscript/pub.js">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscript/Validator.js">
</script>
<link href="<%=request.getContextPath()%>/css/goa.css" rel="stylesheet" type="text/css">
<SCRIPT ID=clientEventHandlersJS LANGUAGE=javascript>
<!--
     function doOK(){
	 var retValueArray = new Array();
	 var retTextArray = new Array();
  	 var callerWindowObj = window.dialogArguments;
         //alert('name is ' + name);
	 var obj = document.all("rightSelect")
         var index = 0;
	    for (var idx =0 ; idx<= obj.options.length-1; idx++){
                if(obj.item(idx).selected) {
			retValueArray[index]  = obj.item(idx).value;
			retTextArray[index] = obj.item(idx).text.trim();
                        index++;
                }
	    }
	 var returnObj = new Object();
           //callerWindowObj[id] = strRetValue.substring(0,strRetValue.length-1);
           //callerWindowObj[name] = strRetText;
           //callerWindowObj.update(id,name);
	 returnObj.id = retValueArray.join(",");
	 returnObj.name = retTextArray.join(",");
		   window.returnValue = returnObj;
           //callerWindowObj.update();
           window.close();
     }
     function doCancel(){
		 var obj = new Object();
		 obj.id = '-1';
		 obj.name = '';
	 window.returnValue = obj;
	 window.close();
     }
function doEnter(){
    var event = window.event;
    var keyCode = event.keyCode;
    //alert('key code is ' + keyCode);
    if(keyCode == 13) {
        //alert('you enterd !');
        if(document.all("rightSelect").value != null) {
            doOK();
        }
    } else if(keyCode == 27) {
        doCancel();
    }
}
//-->
</SCRIPT>
</head>
<body class="bodybg" onload="javascript:initSelect();">
<form action="" method="POST">
<table align="center" width="90%" class="InputFrameMain" border="0">
   <tr>
   <td>
    <table width=90% border=0 align="center" cellpadding=0 cellspacing=0 style="Margin:5px 5px 5px 5px">
        <tr class=InputFrameLine>
          <td class="inputlabelcell" nowrap>&nbsp;</td>
          <td class=inputareacell>&nbsp; </td>
        </tr>
        <tr class="InputFrameLine">
          <td colspan=2 class=inputareacell>
		  <fieldset width="90%">
			  <legend width="90%">可选人员</legend>
              <table width="90%"  align="center" class="tableframe"  border="0" cellpadding="0" cellspacing="0">
              <tr align=center class="f12">
                <td height=""></td>
              </tr>
              <tr align="center" >
                <td>
                <select name="rightSelect" style="width:90%" size="8" multiple ondblclick="javascript:doOK();" onkeyup="doEnter()">
                  </select>
                 </td>
              </tr>
			  <tr align=center class="f12">
                <td height="10"></td>
              </tr>
            </table>
			</fieldset>
			</td>
        </tr>
        <tr>
          <td colspan=2 height="18"></td>
        </tr>
        <tr class="InputFrameButtonLine">
          <td align="center" colspan=2 height="30">
            <input type="button" name="OK" type="button" value="确定" class="button"  onclick="doOK()" />
            <input name="Cancel" type="button" value="取消" class="button"  onclick="doCancel()" />
          </td>
        </tr>
      </table>
 </td>
 </tr>
 </table>
</form>
</body>
<SCRIPT LANGUAGE="JavaScript">
var userId ;
userIds = window.dialogArguments;
function initSelect() {
	to = document.all.rightSelect;
	for(user in userIds) {
		var option = new Option(userIds[user].name,userIds[user].id);
        	to.add(option);
	}
}
</SCRIPT>
