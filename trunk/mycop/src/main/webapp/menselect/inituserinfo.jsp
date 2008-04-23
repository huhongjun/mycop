<%@ page language="java" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gever.goa.menselect.dao.*"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%
String context = request.getContextPath();
%>

<script type="text/javascript" src="<%=request.getContextPath()%>/jscript/pub.js">
</script>
<script type="text/javascript">
var comma = ","
function getUser(name,id) {
    e = window.event;
    var s = String.fromCharCode(e.keyCode);
    var array = new Array();
    var i = 0;
    var lastCommaPos = 0;

    if(e.keyCode == 13) {
    		var oldHandler = document.all[name].onblur;
                document.all[name].onblur = function newHandler() {};
  	  var tempstr = document.all[name].value
   	  lastCommaPos = tempstr.lastIndexOf(comma);
 	  if(lastCommaPos != -1) {
               while(lastCommaPos == tempstr.length) {
                   tempstr = tempstr.substring(0,lastCommaPos);
                   lastCommaPos = tempstr.lastIndexOf(comma);
               }
    	  	tempstr = tempstr.substring(lastCommaPos+1);
    	  }
    	  setPattern(tempstr);
         // alert(pattern);
         //²éÕÒÓÃ»§
    	  array = searchInUserArray();
    	  if(array.length > 1) {
          	openPopWindow(id,name,array);
         } else if(array.length == 1){
                setSinglePerson(array[0],name,id);
    	 } else if(array.length == 0){
                 var temp = document.all[name].value;
                 if(lastCommaPos > 0) {
                 	document.all[name].value = temp.substring(0,lastCommaPos+1);
                 } else {
                     document.all[name].value = "";
                 }
    	 }
    //alert('name is ' + document.all[name].value + '\n id is ' + document.all[id].value)
         document.all[name].onblur = oldHandler;
    }
    e.cancelBubble = true;
    return;
}
function setSinglePerson(userInfo,name,id) {
    var strName = document.all[name].value;
    var lastCommaPos = strName.lastIndexOf(comma);
    var tempName;
    if(lastCommaPos == -1) {
    	tempName = userInfo.name + comma;
    } else if(lastCommaPos > -1) {
        var tempStr = strName.substring(0,lastCommaPos)
        //alert('remain str : ' + tempStr);
    	tempName = tempStr + comma + userInfo.name + comma;
    }
    	document.all[id].value = userInfo.id + comma;
    	document.all[name].value = tempName;
}
function checkValue(name,id) {
    var names = new Array();
    var namestr = document.all[name].value;
    //alert('+++++ ' + namestr);
    var namelen = document.all[name].value.length;
    var infoArray = new Array();
    var j = 0;
    var lastCommaPos = namestr.lastIndexOf(comma);
    while(lastCommaPos == namestr.length) {
    	namestr = namestr.substring(0,lastCommaPos);
   	lastCommaPos = namestr.lastIndexOf(comma);
    }
    //alert('--- namestr -- ' + namestr);
    names = namestr.split(',');
    for(user in userArray) {
    for(var i = 0 ; i < names.length ; i++) {
        if(userArray[user].isMatch('name_equal',names[i])) {
            infoArray[j] = userArray[user];
            j++;
            break;
        }
    }
}
var nameary = new Array();
var idary = new Array();
for(info in infoArray) {
    nameary[info] = infoArray[info].name;
    idary[info] = infoArray[info].id;
}
	//alert('infoArray.length is ' + infoArray.length);
    var idstr = document.all[id].value;
    namestr = nameary.join(comma);
    idstr = idary.join(comma);
    if(namestr.length > 0) {
        namestr = namestr + comma
    }
    document.all[name].value = namestr;
    document.all[id].value = idstr;
    //alert('name is ' + namestr + '\n id is ' + idstr);
}
function openPopWindow(id,name,array) {
	var url = '';
	var date = new Date();
	var rnd="rnd="+date.getTime();
        url = context+'/menselect/popupSelect.jsp' ;
	url = addPara(url,rnd);
	var userIds = array;
	//alert('--- userIds --- ' + userIds);
   var vReturnValue = window.showModalDialog(url,userIds,"dialogHeight: 270px; dialogWidth: 300px; center: Yes; help: no; resizable: no; status: no;");
   if(vReturnValue.id != null && vReturnValue.id != '-1') {
       //alert('id is ' + vReturnValue.id + '\n name is ' + vReturnValue.name);
        var namestr = document.all[name].value;
        var lastCommaPos = namestr.lastIndexOf(comma);
        var tempName;
        var tempId;
    	if(lastCommaPos == -1) {
                tempName = vReturnValue.name + comma;
                tempId = vReturnValue.id + comma;
    	} else if(lastCommaPos > -1) {
        	var tempStr = namestr.substring(0,lastCommaPos)
        	//alert('remain str : ' + tempStr);
    		tempName = tempStr + comma + vReturnValue.name + comma;
            tempId = comma + vReturnValue.id + comma;
   	}
       document.all[name].value = tempName
       document.all[id].value += tempId
   }
   window.event.cancelBubble = true;
}
</script>
<script language="javascript">
<bean:write name="MenSelectForm" property="initUserInfoArray" filter="false"/>
</script>

