<%@ page language="java" %>
 <%@ page import="java.util.*,com.gever.sysman.organization.model.User"%>
 <%@ page import="com.gever.web.menselect.*,com.gever.web.menselect.impl.*"%>
 <%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
 <title>��Աѡ��ҳ��</title>
 <link href="<gdp:context/>/css/css.css" rel="stylesheet" type="text/css">
 </head>
  <%//�Ƿ���Զ�ѡ
  String isMutilSelected=(String)request.getAttribute("isMutilSelected");

    //�����Ƿ����ͬʱ���ϲ�ѯ
  String isConfMutil=(String)request.getAttribute("isConfMutil");
  //�û��Ѿ�ѡ�������
  String[] selectedIds=(String[])request.getAttribute("selectedIds");
 
  if(selectedIds==null){
        selectedIds=new String[0];
  }
   //�û��б�
ArrayList userlist=(ArrayList)request.getAttribute("userlist");
   //��չ��������
ArrayList manSelectedCatalogs=(ArrayList)request.getAttribute("manSelectedCatalogs");

  %>
 <SCRIPT LANGUAGE="JavaScript">
<!--
var ns = navigator.appName == "Netscape";
if(ns) document.all = document.getElementById;
var pattern;
var seperator =',';
var tempOption;
var patternStr;


function setPattern(patternStr) {
	pattern = new RegExp(patternStr);
}
 //��չ���Ժ���
 function ExtendAttibute(name,value){
//�����,���id
   this.name=name;
//�ڸ�����ֵ
   this.value=value;
   this.getValue=function (){
        return this.value;
   }
   this.getName=function (){
     return this.name;
   }
}
function User(id,username,name) {
    this.id = id;
    this.name = name;
    this.userName = username;
    //������չ������� 
    //ΪExtendAttibute��������
    this.extendAttibutes=new Array();
    this.isRight = false;
	this.search=null;
	//function compare_by_type(type,index) {
	function compare_by_type(type) {
		switch(type) {
			case 'id':
				return pattern.test(this.id);
			case 'userName':
				return pattern.test(this.userName);
			case 'name':
				return pattern.test(this.name);
            case 'search':
				return pattern.test(this.name);
			case 'all':
				return true;	
			//Ĭ�ϲ�����չ����
			default:
				return  pattern.test(this.getExtendAttibuteValue(type));    
		              }
	           }
	this.getExtendAttibutes=function (){
              return this.extendAttibutes;
	          }

	this.setExtendAttibutes=function (extendAttibutes){
             this.extendAttibutes=extendAttibutes;
	        }

	this.getExtendAttibuteValue=function (name){
              for(var i=0;i<this.extendAttibutes.length;i++){
                 if(name==this.extendAttibutes[i].getName()){
                       return this.extendAttibutes[i].getValue()
		                 }
	                  }
	                return "";
	               } 
	this.setSelected=function (b){
		  if(b){
	                  //   if(pattern.test(";" + this.id + ";")) {
                                	this.isRight = true;
                       // 	}
		  }else{
	                   //   if(pattern.test(";" + this.id + ";")) {
                                	this.isRight = false;
                       // 	}
		  }
	}
		this.isMatch = compare_by_type;
}
-->
</SCRIPT>
<SCRIPT LANGUAGE=javaScript>
//�������ݽṹ
var userArray = new Array();
<% 
   Iterator it = userlist.iterator();
   int i=0;
    while(it.hasNext()) {
   User oneuser= (User)it.next();
  %>
   userArray[<%=i%>]=new User("<%=oneuser.getId()%>","<%=oneuser.getUserName()%>","<%=oneuser.getName()%>");
   var tmpExtendAttibutes=userArray[<%=i%>].getExtendAttibutes();
//���ÿ���û���������չ���ֵ
<%
  String curUserName=oneuser.getName();
  Iterator iter=manSelectedCatalogs.iterator();
  while(iter.hasNext()){
  MenSelectedCatalog curManSelectedCatalog=(MenSelectedCatalog)iter.next();
%>
	tmpname="<%=curManSelectedCatalog.getId()%>";
<%
//
//ע�⣬���¿���Ҫ�ж�Ϊ�յ����
//***
Object obj=curManSelectedCatalog.getManToCatalogMap().get(curUserName);
   if(obj==null){
        obj=curManSelectedCatalog.getDefaultCatalog();
          }
%>
tmpvalue="<%=obj%>";
tmpExtendAttibutes[tmpExtendAttibutes.length]=new ExtendAttibute(tmpname,tmpvalue);
<%
  }
%>
userArray[<%=i%>].setExtendAttibutes(tmpExtendAttibutes);
  <% i++; } %>

var isMutilSelected=<%=isMutilSelected%>;//���ж��Ƿ��ǵ�ѡ���������ȫ�ֱ���
//�����Ƿ���Ը��ϲ�ѯ
<%if(("true").equals(isConfMutil)){%>
var canCondMutil=true;
	<%}else{%>
var canCondMutil=false;
    <%}%>



function startQuery(type,optionValue){

if(type=="checkboxClicked"){
       getCondControls();
       if(curSelectedConds.length>1){
         //Ϊ��������
		    type="mutilcond";
	   }else if(curSelectedConds.length==1){
             type=curSelectedConds[0];
	   }else{
			var from = document.all("addLeftListBox");
			from.options.length=0;
			 return ;
	   }
}else if(type=="radioClicked"){
	
		setCondControlSelected(optionValue);
		type=optionValue;
	
}else{


       //��������״̬
		setCondControlSelected(type);
       //��Ҫ�жϵ�ѡ����ѡ
	   //

       //����������������
	   getCondControls();
       if(curSelectedConds.length>1){
         //Ϊ��������
		 type="mutilcond";
	   }
}
	   //����ѡ�񷽷�
	   initSelect(type);
}
function addToRight(option){
   var to=document.all("addRightListBox");
   for(var i=0;i<to.options.length;i++){
        if(to.options[i].value==option.value&&to.options[i].name==option.name){
             return;
		}
   }
   to.options[to.options.length] = option;
}

//�������ݽṹ����
 function initSelect(type) {
	
	   document.all("addLeftListBox").options.length = 0;
 <%if(("true").equals(isMutilSelected)){%>
 //����ĳ�������¿�����Ҫ
 //***
	   //document.all("addRightListBox").options.length = 0;
 <%}%>


if(false==isMutilSelected){//��ѡ�����
				switch (type) {
					case 'query':
						setQueryPattern();
						searchInUserArray();
						break;
						<%
					 Iterator iter3=manSelectedCatalogs.iterator();
					 while(iter3.hasNext()){
					 MenSelectedCatalog selectedCatalog=(MenSelectedCatalog)iter3.next();
					 String catalogId=selectedCatalog.getId();
						%>
					case '<%=catalogId%>':
							  //ע������ת�����ַ���
						//alert("==============");
							var catlogValue=""+document.getElementById("<%=catalogId%>").value;
						//	patternStr = seperator +catlogValue + seperator;]
						//alert("==========================catlogValue======"+catlogValue);
							patternStr = catlogValue;
								//alert("patternStr="+patternStr+":pattern.test(2)"+pattern.test("2"));
							setPattern(patternStr);
							if(catlogValue!= "0") {
							   for(var j=0;j<userArray.length;j++) {
									if(userArray[j].isMatch('<%=catalogId%>')) {
									  var option = new Option(userArray[j].name,userArray[j].id);
									  document.all("addLeftListBox").options[document.all("addLeftListBox").options.length] = option;			  
									}
							   }	
							}else{
								 for(var j=0;j<userArray.length;j++) {
									if(userArray[j].isMatch('all')) {
									  var option = new Option(userArray[j].name,userArray[j].id);
									   document.all("addLeftListBox").options[document.all("addLeftListBox").options.length] = option;			  
								  }
							   }
							}
								break;
							<% 
								}
							%>

						case 'mutilcond':
							   //��ѡ���,�������ͬʱ��ѯ��ʱ��
							   //����������ѯ
                         
						for(var j=0;j<userArray.length;j++) {
						   //alert("curSelectedConds.length="+curSelectedConds.length);
								var bOk=true;
								for(var condIndex=0;condIndex<curSelectedConds.length;condIndex++){
									if(!bOk){
									  
									}else{
										var tmpCondName=curSelectedConds[condIndex];
										if(tmpCondName=="query"){
												setQueryPattern();
												if(!userArray[j].isMatch('search')) {
													  bOk=false;
												}
										}else{
											var catlogValue=""+document.getElementById(tmpCondName).value;
											if(catlogValue!= "0") {
													 patternStr = catlogValue;
													 setPattern(patternStr);
													if(!userArray[j].isMatch(tmpCondName)) {
															   bOk=false;
													}//end of if
											}//end of if 
										}
									}//end of if
								}//end of for
								//alert(bOk);
								if(bOk){
								  //�������
									  var option = new Option(userArray[j].name,userArray[j].id);

										var from = document.all("addLeftListBox");
		
								
											from.options[from.options.length] = option;
										
								}

						}//end of for
						break;

				
				}//end of switch
	  }else{//��ѡ�����
			switch (type) {
				case 'query':	
					setQueryPattern();
					searchInUserArray();
					break;
					<%
					 Iterator iter4=manSelectedCatalogs.iterator();
					 while(iter4.hasNext()){
							 MenSelectedCatalog selectedCatalog=(MenSelectedCatalog)iter4.next();
							String catalogId2=selectedCatalog.getId();
					%>
				case '<%=catalogId2%>':	 
						  //ע������ת�����ַ���
					//alert("==============");
						var catlogValue=""+document.getElementById("<%=catalogId2%>").value;
					//patternStr = seperator +catlogValue + seperator;]
					//alert(catlogValue);
						patternStr = catlogValue;
					//alert("patternStr="+patternStr+":pattern.test(2)"+pattern.test("2"));
						setPattern(patternStr);
						if(catlogValue!= "0") {
						   for(var j=0;j<userArray.length;j++) {
								if(userArray[j].isMatch('<%=catalogId2%>')) {
								  var option = new Option(userArray[j].name,userArray[j].id);
								 
								  //***
								var from = document.all("addLeftListBox");
								var to = document.all("addRightListBox");
									
											if(userArray[j].isRight==false) {
												from.options[from.options.length] = option;
											} else {
												addToRight(option);
										    }	
										  
									}
							 }	
						}else{
							for(var j=0;j<userArray.length;j++) {
									if(userArray[j].isMatch('all')) {
									  var option = new Option(userArray[j].name,userArray[j].id);
									          if(userArray[j].isRight==false){				
											     document.all("addLeftListBox").options[document.all("addLeftListBox").options.length] = option;
											  }else{
											     addToRight(option);
											  }
									}//end of if
							}//end of for
						}//end of if
							break;
						<% 
							}
						%>	
						case 'mutilcond':
						   //��ѡ���,�������ͬʱ��ѯ��ʱ��
                           //����������ѯ
						 for(var j=0;j<userArray.length;j++){
								var bOk=true;
								for(var condIndex=0;condIndex<curSelectedConds.length;condIndex++){
									if(!bOk){
									   continue;
									}
									var tmpCondName=curSelectedConds[condIndex];
									if(tmpCondName=="query"){
											setQueryPattern();
											if(!userArray[j].isMatch('search')) {
												  bOk=false;
											}
									}else{
										var catlogValue=""+document.getElementById(tmpCondName).value;
										if(catlogValue!= "0") {
											 patternStr = catlogValue;
											 setPattern(patternStr);
												if(!userArray[j].isMatch(tmpCondName)) {
														   bOk=false;
												}//end of if
										}//end of if 
									}
								}//end of for
								if(bOk){
								  //�������
									  var option = new Option(userArray[j].name,userArray[j].id);

										var from = document.all("addLeftListBox");
										var to = document.all("addRightListBox");									
										if(userArray[j].isRight==false) {
											from.options[from.options.length] = option;
										} else {
												addToRight(option);
										}	
								}

						}//end of for
              		break;

				}//end of switch
      }//end of if ...else...
 }//end of function

function setQueryPattern() {
	setPattern(document.all("search").value);
}
function searchInRight() {
	for(var i = 0 ; i < to.options.length; i ++) {
		if(pattern.test(to.options[i].text)) {
			to.options[i].selected = true;
		}
	}
}


 </SCRIPT>
 <SCRIPT ID=clientEventHandlersJS LANGUAGE=javascript>
<!--
   function doOK(){
	   //alert(document.DepartmentForm.canSelectMan.value);
	 var strRetValue = "";
	 var strRetText = "";
    // var returnObj = new Object();
	var returnIDString="";
	var returnNameString="";
	 if(false==isMutilSelected){//��ѡ�����
	// var obj = document.all("addLeftListBox");
	 //alert(obj.item(0).text);
	 // alert(obj.item(0).value);
	 sourceSelect=document.getElementById('addLeftListBox');
	  var sel_id;
	  sel_id = sourceSelect.selectedIndex;
	  var prevOption = sourceSelect.options[sel_id];
        returnIDString=sourceSelect.options[sel_id].value;
		returnNameString = sourceSelect.options[sel_id].text;
	 }
	// alert(returnObj);
	 else{//��ѡ�����
	 	 var obj = document.all("addRightListBox");
	    for (var idx =0 ; idx<= obj.options.length-1; idx++){		 
		returnIDString  = returnIDString + obj.item(idx).value  + "," ;
		returnNameString = returnNameString + obj.item(idx).text + ",";
	    }
	// returnObj.id = strRetValue.substring(0,strRetValue.length-1);
	 //returnObj.name = strRetText;
	 }
   //alert("returnObj----------"+returnObj);
  // alert(returnIDString+"======"+returnNameString);
	 window.opener.manSelectedPopupWinReturn(returnIDString,returnNameString);
	 window.close();
   }
 function doCancel(){
 window.close();
 }
 function initOnload(){
	
	 var selectedIds=new Array();
     
	<%for(int i1=0;i1<selectedIds.length;i1++){%>
	selectedIds[selectedIds.length]="<%=selectedIds[i1]%>";
	 <%
      }//end of for
	%>

	if(selectedIds.length<=0){
       //�����ʼ��
	}else{
		for(var ui=0;ui<userArray.length;ui++){
		 
			  for(var i=0;i<selectedIds.length;i++){
			 
				  if(userArray[ui].id==selectedIds[i]){
				 
						userArray[ui].setSelected(true);
				  }//end of if     
			  }//end of for
		}//end of for
	}//end of if

	
     <%
     if(manSelectedCatalogs.size()>0)
	 {
       MenSelectedCatalog selectedCatalog=(MenSelectedCatalog)manSelectedCatalogs.get(0);
	   String catalogId=selectedCatalog.getId();
	   %>
		
	   initSelect('<%=catalogId%>');
	  // initSelect('all');
	  // if(<%=catalogId%>.equals("0")){
		//    initSelect('all');
	  // }else{
    // initSelect('<%=catalogId%>');
	//   }
	   
	   <%
	 }else{//���û�У�Ĭ����ʾȫ�� 
	   %>
<%}%>
 }
     //-->
 </SCRIPT>

<body onload="initOnload();" class="bodybg">
<center>
<script>
//�洢����ѡ��ָʾ��radio��checkbox
var condControls=new Array();
function setCondControlSelected(controlType){
	<%if(("true").equals(isConfMutil)){%>
	var canCondMutil=true;
	<%}else{%>
 	var canCondMutil=false;
    <%}%>
    for(var i=0;i<condControls.length;i++){
        if(condControls[i].getAttribute("value")==controlType){
               condControls[i].checked=true;
		}else{
             if(canCondMutil==false){
                 condControls[i].checked=false;
			 }
		}
	}
}
   var curSelectedConds=new Array();
function getCondControls(){
     curSelectedConds=new Array();
     for(var i=0;i<condControls.length;i++){
        if(condControls[i].checked==true){
		   var tmpValue=condControls[i].getAttribute("value");
           curSelectedConds[curSelectedConds.length]=tmpValue;
		}//end of if
	}//end of for
	return curSelectedConds;
}
</script>
 <table align="center" width="100%" class="InputFrameMain" border="0" class="InputFrameMain">
   <tr><td>&nbsp;</td></tr>
   <tr>
   <td>
   <fieldset>
    <legend class="f12">ѡ��</legend>
	    <table width="80%" border=0 class="f12" align=center cellpadding=0 cellspacing=0 >
   <%
    Iterator iter2=manSelectedCatalogs.iterator();
  while(iter2.hasNext()){
  MenSelectedCatalog selectedCatalog=(MenSelectedCatalog)iter2.next();
   %>
      <tr>
	 <td class='InputLabelCell' nowrap>
	 
  <%if(("true").equals(isConfMutil)){%>
      <input type="checkbox" value="<%=selectedCatalog.getId()%>" id="Checkbox_<%=selectedCatalog.getId()%>" onclick="startQuery('checkboxClicked')">
	<script>
condControls[condControls.length]=document.getElementById("Checkbox_<%=selectedCatalog.getId()%>");
   </script>
  <%}else{%>
    <input type="radio" checked value="<%=selectedCatalog.getId()%>" onclick="startQuery('radioClicked','<%=selectedCatalog.getId()%>')" id="Radio_<%=selectedCatalog.getId()%>">
	<script>
condControls[condControls.length]=document.getElementById("Radio_<%=selectedCatalog.getId()%>");
   </script>
  <%}%>

	 <%=selectedCatalog.getName()%>:
	 
	 </td>
     <td class=inputareacell align=center>
       <select id="<%=selectedCatalog.getId()%>" name="<%=selectedCatalog.getId()%>" size="1" style="width:200;display:inline" onchange="startQuery('<%=selectedCatalog.getId()%>');" styleClass="input">
            <option value="0">����</option>
     <%   
	     java.util.Map map=selectedCatalog.getCatalogs();
         java.util.Set set=map.keySet();
         java.util.Iterator iter=set.iterator();
         while(iter.hasNext()){
         Object key=(Object)iter.next();
         Object name=map.get(key);
           %>
      <option value="<%=key%>"><%=name%></option> 
		<%   
         }
        %>
		 </select>
		 </td>
        </tr>     
	  <%
         }
    %>           
          <tr>
          <td class="InputLabelCell">
		  
  <%if(("true").equals(isConfMutil)){%>
      <input type="checkbox" id="Checkbox_query" value="query"  onclick="startQuery('checkboxClicked')">
	<script>
condControls[condControls.length]=document.getElementById("Checkbox_query");
   </script>
  <%}else{%>
    <input type="radio" value="query"  id="Radio_query" onclick="startQuery('radioClicked','query')">
		<script>
condControls[condControls.length]=document.getElementById("Radio_query");
   </script>
  <%}%>

		  ��������:
		  
		  </td>
          <td align=center><INPUT type="text" id="search" name="search" style="width:200;"  onkeyup="javascript:startQuery('query');"></td>
        </tr>
</table>
		</fieldset>
 <table align="center" width="150" class="InputFrameMain" border="0">
      <td colspan="2"><table border="0" cellpadding="2" cellspacing="1">
              <tr class="InputLabelCell">
                <td>��ѡ�û��б�:</td>
	<%if(("true").equals(isMutilSelected)){%>
                <td valign="middle">&nbsp;</td>
                <td>��ѡ�û��б�:</td>
	<%}%>
              </tr> 
<% 
   //  boolean isMutilSelected=request.getParameter("isMutilSelected");//��ѡ�����
	//  if(isMutilSelected==true||isMutilSelected==null){//��ѡ�����
%>
  <tr class="InputFrameLine">
    
	 <fieldset >

	 <tr>
                <td align="center" valign="top">
                	<select ID="addLeftListBox" name="addLeftListBox" 
                    <%if(("true").equals(isMutilSelected)){%>
					multiple
					<%}else{%>
					   style="width:200;"
					<%}%>
					  size="10"
					class="input2" style="width:120px; height:200px;"
               <%if(("true").equals(isMutilSelected)){%>
				ondblclick="moveOneFrom('left')" 
               <%}else{%>		
                ondblclick="doOK()" 
                 <%}%>
				>

                  	</select></td>

 <%if(("true").equals(isMutilSelected)){%>
                <td align="center" valign="middle"> <table border="0" cellpadding="1" cellspacing="1">
                    <tr>
                      <td align="center"> <input type="button" class="button" style="width:30px;" value="&gt;" onclick="moveOneFrom('left')"></td>
                    </tr>
                    <tr>
                      <td align="center"> <input name="Submit232" type="button" class="button" style="width:30px;" value="&gt&gt;" onclick="moveAllFrom('left')"></td>
                    </tr>
				<tr align="center">
                      <td valign="middle"> </td>
                    </tr>
					<tr align="center">
                      <td valign="middle"> </td>
                    </tr>
                    <tr>
                      <td align="center"> <input name="Submit2232" type="button" class="button" style="width:30px;" value="&lt&lt;" onclick="moveAllFrom('right')"></td>
                    </tr>
                    <tr>
                      <td align="center"> <input type="button" class="button" style="width:30px;" value="&lt;" onclick="moveOneFrom('right')"></td>
                    </tr>
                  </table></td>
				  <td align="center" valign="top">
			<select id="addRightListBox" name="addRightListBox" multiple class="input2" style="width:120px; height:200px;" ondblclick="moveOneFrom('right')">
                  	</select></td>
<%}%>

              </tr>
            </table></td>
       
        </tr>
        <tr class="InputFrameButtonLine">
          <td align="center" colspan=2 height="30"> <input type="button" name="OK" type="button" value="ȷ��" class="button"  onclick="doOK()" />
            <input name="Cancel" type="button" value="ȡ��" class="button"  onclick="doCancel()" />
          </td>
        </tr>
     
 </td>
 </tr>
 </table>

 </center>
</body>
</html>
<SCRIPT LANGUAGE="javaScript">
function setRight(type) {
   //setPattern(tempOption.value);
   for(user in userArray) {
	  //2004-12-2
	  //���ǵ���ʱ���õ�isMatch��ɵ������������Ƿ�ѡ�е�״ֵ̬��
	  //��Ŀ�ĺ�isMatch��������һ�£����ԸĽ�ΪsetSelected�����ĵ���
     // userArray[user].isMatch('id_' + type);
	 if(type=="left"){
        userArray[user].setSelected(true);
	 }else{
        userArray[user].setSelected(false);
	 }
   }
}
function searchInRight() {
	for(var i = 0 ; i < to.options.length; i ++) {
		if(pattern.test(to.options[i].text)) {
			to.options[i].selected = true;
		}
	}
}

function searchInUserArray() {
	var from=document.all("addLeftListBox");
	from.options.length = 0;	
	for(user in userArray) {          
		if(userArray[user].isMatch('search')) {
			var option = new Option(userArray[user].name,userArray[user].id);
			if(isMutilSelected){
				if(userArray[user].isRight==false) {
					from.options[from.options.length] = option;
				}else{
					addToRight(option);
				}
			}else{
			   from.options[from.options.length] = option;
			}
		}
	}
}

function moveAllFrom(type) {
             var from=null;
			 var to=null;
            switch (type) {
			case 'left':
				from = document.all("addLeftListBox");
				to = document.all("addRightListBox");
				break;
			case 'right':
				from = document.all("addRightListBox")
				to = document.all("addLeftListBox");
				break;
			}
	for(var i = 0 ; i < from.options.length ; i ++) {
		tempOption = new Option(from.options[i].text,from.options[i].value);
                setRight(type);
		to.options[to.options.length] = tempOption;
		from.options[i] = null;
		i--;
	}
}

function moveOneFrom(type) {
             var from=null;
			 var to=null;
            switch (type) {
			case 'left':
				from = document.all("addLeftListBox");
				to = document.all("addRightListBox");
				break;
			case 'right':
				from = document.all("addRightListBox")
				to = document.all("addLeftListBox");
				break;
			}


	for(var i = 0 ; i < from.options.length ; i ++) {
		if(from.options[i].selected) {
			tempOption = new Option(from.options[i].text,from.options[i].value);
			setRight(type);
		    to.options[to.options.length] = tempOption;
            
			from.options[i] = null;
			//(......)
			i--
		}
	}
}


</SCRIPT>
