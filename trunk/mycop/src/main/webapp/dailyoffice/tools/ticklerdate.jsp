<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>
<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<link href="<%=context%>/css/goa.css" rel="stylesheet" type="text/css">



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>日期/时间选择</title>
<style>
td, select, input, body{font-size:9pt}
.borderdown{
    BORDER-BOTTOM: 2px inset;
    BORDER-LEFT: 2px inset;
    BORDER-RIGHT: 2px inset;
    BORDER-TOP: 2px inset;
    cursor:default;
    background-color:white;
    }
</style>
<Script Language=JavaScript>
function genDates(){
try{
	//alert"genDates:"+formDateTime.selDate.value);
	if(formDateTime.selDate.value!="")
		clearColor(document.all(formDateTime.selDate.value))
	var objYear=formDateTime.selYear.value
		//alertobjYear);
	var objMonth=formDateTime.selMonth.value
		//alertobjMonth);
	var userDate=new Date(objYear,objMonth,1) //取得初始年份与月份
	var WeekOfFirstDay=userDate.getDay()	//取得该月的第一天是星期几
	userDate.setMonth(userDate.getMonth()+1) //设置成下一个月
	userDate.setDate(userDate.getDate()-1)
	var LastDayOfMonth=userDate.getDate()
	var j=1;
	for(var i=0;i<=41;i++){
		if(i>=WeekOfFirstDay && i<=LastDayOfMonth+WeekOfFirstDay-1){
		  document.all("day"+i).innerText=(j<10?"0"+j:j)
		  j++;
		}else document.all("day"+i).innerText=" "
	}
}catch(ee){}
}
function clearColor(originObject){
	originObject.style.color="#000000"
	if(originObject.id.substr(3) % 7 ==0)
		originObject.style.color="#FF0000"
	if(originObject.id.substr(3) % 7 ==6) 
		originObject.style.color="#339933"
	originObject.bgColor="#FFFFFF"
	formDateTime.selDate.value=""
}
function onDayClick(type){
	
	var oSource=window.event.srcElement;
	if(!oSource.id)return false;
	var z=oSource.innerText;
	try{
		if(z.length>1)if(z.substring(0,1)=="0")z=z.substring(1);
		var tmp=parseInt(z);
		if(isNaN(tmp))return false;
	}catch(exp){return false}
	if(oSource.id.indexOf("day")!=-1 && parseInt(z)>=1 && parseInt(z)<=31)
	{
		if(formDateTime.selDate.value!="" && formDateTime.selDate.value!=oSource.id)
		{  //alertformDateTime.selDate.value);
			var originObject=document.all(formDateTime.selDate.value)
			clearColor(originObject)
		}
		formDateTime.selDate.value=oSource.id
		oSource.bgColor="#011693";
		oSource.style.color="#FFFFFF"
		var vardate ;
		if(type=="datetime")
			vardate=formDateTime.selYear.value+"-"+get2BitStr(parseInt(formDateTime.selMonth.value)+1)+"-"+document.all(formDateTime.selDate.value).innerText+" "+formDateTime.value
		else
			vardate=formDateTime.selYear.value+"-"+get2BitStr(parseInt(formDateTime.selMonth.value)+1)+"-"+document.all(formDateTime.selDate.value).innerText
	}
	
   // alert(vardate);
    parent.middle.location=context+"/dailyoffice/tools/listTickler.do?date="+ vardate;
	return true;
}
function returnDateTime(){
	
	//top.dialogArguments.value=formDateTime.selDateTime.value
	//alert("select date::"+top.dialogArguments.value);

}

function get2BitStr(num){
	return (num<10?"0"+num:num);
}
</Script>
</head>
<body class="bodybg" style="margin-left:10px">
<form method="POST" action="" name="formDateTime">
<fieldset style="padding:10;width:200">
<p>&nbsp;</p>
<legend>日期/时间</legend>
<table width="22%" height="209" cellpadding=0 cellspacing=0 border="0">
    <tr>
      <td width="100%">
      <table border="0" style="border-collapse: collapse"width="180">
        <tr>
          <td width="67" height="30"><select size="1" name="selMonth" onchange="javascript:genDates()">
          <option value="0">一月</option>
          <option value="1">二月</option>
          <option value="2">三月</option>
          <option value="3">四月</option>
          <option value="4">五月</option>
          <option value="5">六月</option>
          <option value="6">七月</option>
          <option value="7">八月</option>
          <option value="8">九月</option>
          <option value="9">十月</option>
          <option value="10">十一月</option>
          <option value="11">十二月&nbsp;</option>
          </select></td>
          <td width="106" align=right>
          <input type="text" name="selYear" size="10" value="" onblur="if(this.value.length!=4) this.value=new Date().getYear();genDates()"></td>
          <td width="23">
		<table cellpadding=0 cellspacing=0>
		<tr><td><input type=button onclick="formDateTime.selYear.value++;genDates();this.blur();" style="height:11;font-size:6pt" value=" + "></td></tr>
		<tr><td><input type=button onclick="formDateTime.selYear.value--;genDates();this.blur();" style="height:11;font-size:6pt" value=" - "></td></tr>
		</table></td>
        </tr>
      </table>
      </td>
    </tr>
    <tr>
      
    </tr>
    <tr>
      <td width="100%" height="173">
	<div class="borderdown" style="width:180" onclick="javascript:if(onDayClick('date')) returnDateTime()" ><!--ondblclick="javascript:if(onDayClick())returnDateTime()"> -->
      <table border="0" style="border-collapse: collapse" width="180" height="140" id="TableOfDate">
        <tr bgcolor="#808080" align=center style="color:white">
          <td width="14%">日</td>
          <td width="14%">一</td>
          <td width="14%">二</td>
          <td width="14%">三</td>
          <td width="14%">四</td>
          <td width="15%">五</td>
          <td width="15%">六</td>
        </tr>
        <script language=javascript>
        for(var i=0;i<=5;i++){
        	document.write("<tr align=center>")
        	for(var k=0;k<=6;k++){
        		if((i*7+k) % 7==0){
        			document.write("<td width='14%' id=day"+(i*7+k)+ " style='color:#FF0000'>XX</td>")
        			continue;
        		}
      			if((i*7+k) % 7==6){      			
        			document.write("<td width='16%' id=day"+(i*7+k)+ " style='color:#339933'>XX</td>")
        			continue;
      			}
   				document.write("<td width='14%' id=day"+(i*7+k)+ "> </td>")
        	}
        	document.write("</tr>")        
        }
        delete i
        delete j
        </Script>
      </table>
      </div>      
      <div style="line-height:50%;margin-top:8;padding-left:50;margin-bottom:0">      <input type=hidden name="selDate" value="" size="20"><input type=hidden name="selDateTime" value="" size="20">
      </div></td>
    </tr>
  </table>
  </fieldset>
  <table border="0" style="padding:10;width:200">
    <tr><td align="center"><input type="button" onclick="showCurrent()" value="今天"></td></tr>
  </table>
</form>
<p>
  <Script Language=javascript>
  var tempDate;
  var ticklerdate;
  <logic:present name="TicklerForm" property="date" >  
	ticklerdate='<bean:write name="TicklerForm" property="date" />';
	if(ticklerdate!=""){
		var tmpyear=ticklerdate.substring(0,4);
		var tmpmonth=ticklerdate.substring(5,7);
		var tmpdate=ticklerdate.substring(8,10);
		tempDate=new Date(tmpyear,tmpmonth-1,tmpdate);
	}
  </logic:present>
  //alert(ticklerdate);
  if(tempDate==null){
	  //alert("tempDate==null");
	tempDate=new Date();	
  }
  init(tempDate);

function showCurrent(){
	var curDate=new Date();

	init(curDate);

	var vardate;
	vardate=curDate.getYear()+"-"+get2BitStr(curDate.getMonth()+1)+"-"+get2BitStr(curDate.getDate());
	parent.middle.location=context+"/dailyoffice/tools/listTickler.do?date="+ vardate;

	delete curDate;
}
 //初始化页面；
function init(date){
 var tempDate=date;
 formDateTime.selYear.value=tempDate.getYear();
 formDateTime.selMonth.value=tempDate.getMonth();//(tempDate.getMonth()<10?"0"+temp Date.getMonth():tempDate.getMonth())
 var nowDate=tempDate.getDate();
 delete tempDate
 genDates();
 try{
	var objYear=formDateTime.selYear.value
	var objMonth=formDateTime.selMonth.value
	var userDate=new Date(objYear,objMonth,1) //取得初始年份与月份
	var WeekOfFirstDay=userDate.getDay()	//取得该月的第一天是星期几
	userDate.setMonth(userDate.getMonth()+1) //设置成下一个月
	userDate.setDate(userDate.getDate()-1)
	var LastDayOfMonth=userDate.getDate();

	var j=1;
	for(var i=0;i<=41;i++){
		if(i>=WeekOfFirstDay && i<=LastDayOfMonth+WeekOfFirstDay-1){
			if(j==nowDate){
				document.all("day"+i).bgColor="navy";
			 	document.all("day"+i).style.color="white";
				formDateTime.selDate.value=document.all("day"+i).id;
				if(top.dialogArguments.dateTimeType=="datetime")
					formDateTime.selDateTime.value=formDateTime.selYear.value+"-"+get2BitStr(parseInt(formDateTime.selMonth.value)+1)+"-"+document.all(formDateTime.selDate.value).innerText+" "+formDateTime.value
				else
					formDateTime.selDateTime.value=formDateTime.selYear.value+"-"+get2BitStr(parseInt(formDateTime.selMonth.value)+1)+"-"+document.all(formDateTime.selDate.value).innerText
				break;
			}
			j++;
		}
	}
	if(top.dialogArguments.dateTimeType=="date")BeijingTime.style.display="none"
 }catch(ex){}
}
  </script>
</p>
<p>-&gt;1.点击日历里面的日期,可以查看该天添加的备忘录记录</p>
<p>-&gt;2.注意：添加备忘录记录时,记录添加的日期均默认为当天的系统日期</p>
</body>
</html>