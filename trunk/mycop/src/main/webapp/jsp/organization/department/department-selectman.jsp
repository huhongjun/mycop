<%@ page language="java" %>
 <%@ page import="java.util.*,com.gever.sysman.organization.model.User"%>
 <%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
 <title>人员选择页面</title>
 <jsp:include page="/jsp/jsp_css.jsp" />
 </head>

<% ArrayList userlist=(ArrayList)request.getAttribute("userNameList") ;%>

<SCRIPT ID=clientEventHandlersJS LANGUAGE=javascript>
<!--

   function doOK(){
	   //alert(document.DepartmentForm.canSelectMan.value);
	 window.opener.setParams(document.DepartmentForm.canSelectMan.value);
	 window.close();
   }
 function doCancel(){
 window.close();
 }


     //-->
 </SCRIPT>
 <SCRIPT LANGUAGE="JavaScript">
<!--

var pattern;
var seperator = ',';
var tempOption;
var patternStr;
var from;
var to;

function setPattern(patternStr) {
	pattern = new RegExp(patternStr);
	//alert(pattern);
}
function User(name,id,level) {
	this.name = name;
	this.id = id;
	this.level = level;
	function compare_by_type(type) {
		switch(type) {
			case 'id':
				return pattern.test(this.id);
			case 'name':
				return pattern.test(this.name);
			case 'level':
				
				//alert("this.leverl=="+this.level);
				//alert(pattern.test(seperator + this.level + seperator));
				return pattern.test(seperator + this.level + seperator);
			case 'level_all':
				return this.level.length > 0;
			case 'all':
				return true;
			default:
				return pattern.test(this.name);
		}

	}
	this.isMatch = compare_by_type;
}

var userArray = new Array();
<% 
   Iterator it = userlist.iterator();
   int i=0;
    while(it.hasNext()) {
   User oneuser= (User)it.next();
  %>
   userArray[<%=i%>]=new User("<%=oneuser.getName()%>","<%=oneuser.getId()%>","<%=oneuser.getLevel()%>")
<% i++; } %>

  function initSelect(type) {
	   document.DepartmentForm.canSelectMan.options.length = 0;
	switch (type) {
	case 'query':
		//searchInRight()
		searchInUserArray();
		break;
    case 'level':	      
			patternStr = seperator + document.DepartmentForm.level.value + seperator;
	        //alert(patternStr);

        	setPattern(patternStr);
			//alert("document.DepartmentForm.level.value=="+document.DepartmentForm.level.value)
        	if(document.DepartmentForm.level.value != 0) {
        	   for(var j=0;j<userArray.length;j++) {
				    if(userArray[j].isMatch('level')) {
			   var option = new Option(userArray[j].name,userArray[j].name);
			   document.DepartmentForm.canSelectMan.add(option);
			  
                    }
        	}
			
        	}else {
				//alert("this all");
        		 for(var i=0;i<userArray.length;i++) {
                    if(userArray[1].isMatch('level_all')) {
				var option = new Option(userArray[i].name,userArray[i].name);
                        document.DepartmentForm.canSelectMan.add(option);
                    }
        	}
        	}
                break;
	default:
		
	    var selectmanobj = document.DepartmentForm.canSelectMan;
		     for(var i=0;i<userArray.length;i++) {
			    var option = new Option(userArray[i].name,userArray[i].name);
			    selectmanobj.options[selectmanobj.options.length] = option;
			}
		break;
	}

}
function query() {
	setPattern(document.DepartmentForm.search.value);
	initSelect('query');
}
function searchInRight() {
	for(var i = 0 ; i < to.options.length; i ++) {
//	alert(pattern.test(to.options[i].text))
		if(pattern.test(to.options[i].text)) {
			to.options[i].selected = true;
		}
	}
}
function searchInUserArray() {
	//from.options.length = 0;
	
	 for(var i=0;i<userArray.length;i++) {
            	//alert(userArray[user].name + " isRight is " + userArray[user].isRight);
		if(userArray[i].isMatch()) {
			var option = new Option(userArray[i].name,userArray[i].name);
			 document.DepartmentForm.canSelectMan.add(option);
		}
	}
}
 </SCRIPT>

<body onload="initSelect('level_all');" class="bodybg">
<center>
 <html:form action="/DepartmentAction" onsubmit="return false;">
 <table align="center" width="100%" class="InputFrameMain" border="0" class="InputFrameMain">
   <tr><td>&nbsp;</td></tr>
   <tr>
   <td>
   <fieldset>
 <legend class="f12">选择</legend>
    <table width="80%" border=0 class="f12" align=center cellpadding=0 cellspacing=0 >
        <tr>
          <td class='InputLabelCell' nowrap>行政级别:</td>
          <td class=inputareacell align=center>
          <select  name="level" size="1" style="width:200;display:inline" onchange="initSelect('level');" styleClass="input">
            <option value="0">所有级别</option>
			<%
			ArrayList userlevel=(ArrayList)request.getAttribute("userLevel");
			 Iterator it1 = userlevel.iterator();
             while(it1.hasNext()) {
             User oneuser1= (User)it1.next();
			%>
          <option value="<%=oneuser1.getLevel()%>"><%=oneuser1.getLevel()%></option>
           <%  }     %>
		 </select>
        </tr>
        <tr>
          <td class="InputLabelCell">人名搜索:</td>
          <td align=center><INPUT type="text" name="search" style="width:200;"  onkeyup="javascript:query();"></td>
        </tr>
</table>
		</fieldset>

        <tr class="InputFrameLine">
          <td colspan=2 class=inputareacell> <table width="100%" class=tableframe  border="0" cellpadding="0" cellspacing="0">
              <tr align=center >
                <td>
							  <fieldset >
<legend class="f12">可选人员</legend>
 <table align="center" width="150" class="InputFrameMain" border="0">
    <select name="canSelectMan" size="10" style="width: 100%" multiple>
                  </select>
                    </table>
                </td>

				</fieldset >
                 </td>

              </tr>
            </table></td>
        </tr>
        <tr class="InputFrameButtonLine">
          <td align="center" colspan=2 height="30"> <input type="button" name="OK" type="button" value="确定" class="button"  onclick="doOK()" />
            <input name="Cancel" type="button" value="取消" class="button"  onclick="doCancel()" />
          </td>
        </tr>
      </table>
 </td>
 </tr>
 </table>

 </html:form>
 </center>
</body>
</html>