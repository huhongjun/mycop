var context = '/gdp';
//var context = "";
function setContext(mycontext){
	context= mycontext;
}


//做什么动作
function doAction(whatAction){
	//汤启礼改

	var mForm=null;
	if(event!=null){
		var btn=event.srcElement;

		if(btn.tagName=="INPUT"){
			mForm=btn.form;
		}else{
			mForm = document.forms[0];
		}
	}else{
		mForm = document.forms[0];
	}
	//汤启礼改
	var action =  mForm.action ;

	action =  addPara(action,"action=" + whatAction);

	mForm.action = action

	disButton();
	//alert(action)
	mForm.submit();

}
function doPage(whatAction){
	var mForm = document.forms[0];
	var action =  mForm.action ;

	action =  addPara(action,"action=" + whatAction);
	sForm.action = action
disButton();
	mForm.submit();

}
//复制
function doCopy(){
	var count=0;

	var length = document.forms[0].fid.length;
	if (isNaN(length))	{
		try{
			if (document.forms[0].fid.checked)	{
				++count;
			}
		}catch(e){}
	}

	for(var i=0;i<document.forms[0].fid.length;i++){
		if(document.forms[0].fid[i].checked)
			count++;
	}
	if(count==0){
		alert("您没有选择任何记录！");
		return false;
	}
	if(confirm("确定要复制当前已选的记录吗？") == false){
		return false;
	}
	var mForm = document.forms[0];
	var action =  mForm.action;
	action =  addPara(action,"action=copy" );
	mForm.action = action
	disButton();
	mForm.submit();
}



//检视
function toUrl(url,isCheck){

	if (isCheck == true){
		if (checkSelect()== false){
			return ;
		}
	}
	var mForm = document.forms[0];

	mForm.action =url;

	disButton();
	mForm.submit();
}


//检查是否有选择记录
function checkSelect(){
	var count = 0;
	var length = 0;
	try{
		 length = document.forms[0].fid.length;
	} catch(e){
		alert("没有任何记录！");
		return false;
	}
	if (isNaN(length))	{
		try{
			if (document.forms[0].fid.checked)	{
				++count;
			}
		}catch(e){}
	}

	for(var i=0;i<document.forms[0].fid.length;i++){
		if(document.forms[0].fid[i].checked)
			count++;
	}
	if(count==0){
		alert("您没有选择任何记录！");
		return false;
	} else if (count >1){
		alert("请选择单条记录");
		return false;
	}

	return true;
}
function checkSelected(){
	var count = 0;
	var length = 0;
	try{
		 length = document.forms[0].fid.length;
	} catch(e){
		alert("没有任何记录！");
		return false;
	}
	if (isNaN(length))	{
		try{
			if (document.forms[0].fid.checked)	{
				++count;
			}
		}catch(e){}
	}

	for(var i=0;i<document.forms[0].fid.length;i++){
		if(document.forms[0].fid[i].checked)
			count++;
	}
	if(count==0){
		alert("您没有选择任何记录！");
		return false;
	}

	return true;
}


//增加
function doAdd(){
	var mForm = document.forms[0];
	var action =  mForm.action;
	action =  addPara(action,"action=goUrl(edit)" );
	mForm.action = action
	disButton();
	mForm.submit();
}

//修改
function doUpdate(){
	var mForm = document.forms[0];
	var action =  mForm.action;

	action =  addPara(action,"actionType=modify&"+pid );
	mForm.action = action
	disButton();
	mForm.submit();
}

//删除
function doDelete(){

	var count = 0;
	var length = 0;
	try{
		 length = document.forms[0].fid.length;
	} catch(e){
		alert("没有任何记录！");
		return false;
	}
	if (isNaN(length))	{
		try{
			if (document.forms[0].fid.checked)	{
				++count;
			}
		}catch(e){}
	}

	for(var i=0;i<document.forms[0].fid.length;i++){
		if(document.forms[0].fid[i].checked)
			count++;
	}
	if(count==0){
		alert("您没有选择任何记录！");
		return false;
	}
	if(confirm("确定要删除记录吗？") == false){
		return false;
	}


	var mForm = document.forms[0];
	var action =  mForm.action;
	action =  addPara(action,"action=delete" );
	mForm.action = action
	disButton();
	mForm.submit();
}

//类似删除处理的简单action跳转-无对应页面的操作-直接回到index页面
//myAction--对应您的方法名-比如doXXXXX方法
//myAction--对应您的处理的中文叫法-比如"归档"什么的
//ADD BY CY 2004-08-10
function doSingleAction(myAction,myActionName){
	var count = 0;
	var length = 0;
	try{
		 length = document.forms[0].fid.length;
	} catch(e){
		alert("没有任何记录！");
		return false;
	}
	if (isNaN(length))	{
		try{
			if (document.forms[0].fid.checked)	{
				++count;
			}
		}catch(e){}
	}

	for(var i=0;i<document.forms[0].fid.length;i++){
		if(document.forms[0].fid[i].checked)
			count++;
	}
	if(count==0){
		alert("您没有选择任何记录！");
		return false;
	}
	if(confirm("确定要"+myActionName+"记录吗？") == false){
		return false;
	}


	var mForm = document.forms[0];
	var action =  mForm.action;
	action =  addPara(action,"action=" + myAction);
	mForm.action = action;
	disButton();
	mForm.submit();
}
//类似删除处理的简单action跳转-无对应页面的操作-直接回到index页面-且不用获取id号-可直接用doAction
//myAction--对应您的方法名-比如doXXXXX方法
//ADD BY CY 2004-08-11
function doNoSelSingleAction(myAction){
	var but=event.srcElement;
	if(confirm("确定提交吗？") == false){
		return false;
	}
	var mForm = but.form;
	var action =  mForm.action;
	action =  addPara(action,"action=" + myAction);
	mForm.action = action;
	disButton();
	mForm.submit();
}
//在修改页面上直接删除单个附件的跳转方法，跳转后页面依然返回修改页面本身
//myAction--对应您的方法名-比如doXXXXX方法
//对应该附件的路径
//ADD BY CY 2004-08-11
function doDelSingleAttach(myAction,name){
	var but=event.srcElement;
	if(confirm("确定删除该附件吗？") == false){
		return false;
	}
	var mForm = but.form;
	var action =  mForm.action;
	//alert("-------doDelSingleAttach Action-------"+action);
	action =  addPara(action,"action=" + myAction + "&attachname="+ name);
	//alert("-------new action-----"+action);
	mForm.action = action;
	disButton();
	mForm.submit();
}
function download2(filepath){

	var bIsShow = isShow(filepath);

	if (bIsShow == true){
	//
	//	if (confirm("是否通过浏览器打开该文件？") == true){
	//		return ;
	//	}

		window.event.returnValue= false;
		var url =context + filepath	;
		objNewWin = window.open(url,"newwindow"," toolbar=no,resizable=yes,scrollbars=yes,status=yes");

		try{
			objNewWin.blur();
			objNewWin.moveTo(100,100);
			objNewWin.resizeTo(screen.availWidth-200,screen.availHeight-200);
		} catch(e){}
		window.event.returnValue= false;
	}

}

function download3(filepath,filename){

	var bIsShow = isShow(filepath);

	if (bIsShow == true){

		window.event.returnValue= false;
		var url =context + "/admin/homepage/showattch.jsp?filepath="+ filepath+"&filename=" + filename;
		//alert(url);
		objNewWin = window.open(url,"newwindow"," toolbar=no,resizable=yes,scrollbars=no,status=yes");

		try{
			objNewWin.focus();
			objNewWin.moveTo(100,100);
			objNewWin.resizeTo(screen.availWidth-200,screen.availHeight-200);
		} catch(e){}
		window.event.returnValue= false;
	}

}


//下载文件
function download(filepath){

	var bIsShow = isShow(filepath);

	if (bIsShow == true){
		//if (confirm("是否通过浏览器打开该文件？") == false){
		//	return ;
		//}
		/*
		window.event.returnValue= false;
		var url =context + filepath	;
		objNewWin = window.open(url,"newwindow"," toolbar=no,resizable=yes,scrollbars=yes,status=yes");

		try{
			objNewWin.focus();
			objNewWin.moveTo(100,100);
			objNewWin.resizeTo(screen.availWidth-200,screen.availHeight-200);
		} catch(e){}
		window.event.returnValue= false;*/
		return false;
	}

}


//看看当前的文件是否是用来显示还是用来直接下载
function isShow(strFileName){
	var bRet = false;
	var strExt = "";
	strExt = getExtName(strFileName)
	if (strExt == "doc" || strExt =="ppt" ||
		strExt =="jpg" || strExt =="gif" || strExt =="bmp" ||
		 strExt =="htm" || strExt =="html"  || strExt =="xml" ||
		  strExt =="txt" || strExt=="csv" || strExt=="XLS"|| strExt=="xls" ) {
		 bRet = true;
	}

	return bRet ;
}
//得到扩展名
function getExtName( strFileName){
	if (strFileName == "")
		return "";
	var iPos =0;
	iPos =strFileName.lastIndexOf(".");
	if (iPos < 0)
		return "";
	else
		return strFileName.substring(iPos+1,strFileName.length);
}

//改变排序方式
function onchangeOrderBy(curTd){
	var curId = curTd.id;
	//alert(curId);
	var mForm = document.forms[0];
	var fld = mForm.elements["orderFld"].value;

	var orderFld = curId.substring(1,curId.length);
	var orderType = mForm.elements["orderType"].value;
	var orderFld = curId.substring(1,curId.length);
	if (fld == orderFld){
		if (orderType == "asc" )	{
			orderType="desc";
		} else {
			orderType ="asc";
		}
	}
	mForm.elements["orderFld"].value = orderFld;
	mForm.elements["orderType"].value = orderType;

	var action =  mForm.action;
	action =  addPara(action,"action=doOrderBy" );
	mForm.action = action;
	mForm.submit();
}


//初始化
function initOrderBy(context) {
	var mForm = document.forms[0];
	var tagname ="."+mForm.elements["orderFld"].value;

	var orderType = mForm.elements["orderType"].value;

	//alert("tagname = " + tagname);


	if (document.all(tagname) == null) return false;

	var tdObj = document.all(tagname);
	var divObj = null;

	for (i = 0 ;i < tdObj.all.length ;i++) {
		//alert(tdObj.all(i).tagName);
		if (tdObj.all(i).tagName == "DIV") {
			divObj = tdObj.all(i);
		}
	}

	if (divObj == null) return false;

	if (orderType == "asc") {
		divObj.innerHTML += "<img src='" + context +"/images/ordernext.gif'>";
	} else if (orderType == "desc") {
		divObj.innerHTML += "<img src='" + context +"/images/orderup.gif'>";
	}
}

//加入一个参数(如keyValue = "type=0");
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

//选择
function selectAllChk(obj){
	var count=0;
	var isSelect = obj.checked
	var length  = 0;
	try{
		length = document.forms[0].fid.length;
	} catch (e){return false;}

	if (isNaN(length))	{
		try{
		document.forms[0].fid.checked=isSelect;
		}catch(e){}
	}
	//alert(length + obj.checked);
	if (length ==0)	{
		return false;
	}

	if (length == 1)	{
		document.forms[0].fid.checked=isSelect;
	} else {
		for(var i=0;i<document.forms[0].fid.length;i++){
			document.forms[0].fid[i].checked=isSelect;
		}
	}

}

//保存
function doSave(mform){
	if (validater(mform) == false){
		return false;
	}
	var sForm = document.forms[0];
	var sAction = sForm.action;
	var bRet = true;
	try{
		bRet = checkInput() ;
		if (bRet ==false){
			return false;
		}
	}catch(e){
	}

	if (sForm.elements["actionType"].value=="modify"){
		sAction = addPara(sAction, "action=update");
	} else {
		sAction = addPara(sAction, "action=insert");
	}
	sForm.action = sAction;
	disButton();
	sForm.submit();

}


//保存
function doSaveExit(mform){
	if (validater(mform) == false){
		return false;
	}
	var sForm = document.forms[0];
	var sAction = sForm.action;
	var bRet = true;
	try{
		bRet = checkInput() ;
		if (bRet ==false){
			return false;
		}
	}catch(e){
	}

	if (sForm.elements["actionType"].value=="modify"){
		sAction = addPara(sAction, "action=modify");
	} else {
		sAction = addPara(sAction, "action=add");
	}
	sForm.action = sAction;
	disButton();
	sForm.submit();

}


function changeBgColorOnMouseOut(obj){
	obj.style.backgroundColor='';
}
function changeBgColorOnMouseOver(obj){
	obj.style.backgroundColor='#cccccc';
}
/**
 * 字符串替换函数  翁乃彬Add(2003-09-27)
 */
function replace(strSource, strFrom, strTo){
	var strDest = "";
	var intFromLen = strFrom.length;
	var intPos=0;
	while((intPos=strSource.indexOf(strFrom))!=-1){
		strDest = strDest + strSource.substring(0,intPos);
		strDest = strDest + strTo;
		strSource = strSource.substring(intPos+intFromLen);
	}
	strDest = strDest + strSource;
	return strDest;
}

//把当前时间改成yyyy-mm-dd格式
function datetime2date(dt){
	   var cd;
       cd = dt.getYear() + "-";
	   var month;
	   month = dt.getMonth() + 1;
	   if (month > 9){
		   cd += month + "-";
	   }else{
		   cd += "0" + month + "-";
	   }
	   var day;
	   day = dt.getDate();
	   if (day > 9){
          cd += day;
	   }else{
		  cd += "0" + day;
	   }
       return cd;
}

function date2datetime(dt){
	   var cd = datetime2date(dt);

		var hours = (dt.getHours() >9) ?dt.getHours(): "0"+dt.getHours();
		var minutes = (dt.getMinutes() >9) ?dt.getMinutes(): "0"+dt.getMinutes();
		var seconds = (dt.getMinutes() >9) ?dt.getMinutes(): "0"+dt.getMinutes();
		return cd+" " + hours + ":"+minutes + ":"+dt.getSeconds();
}

function timeToLong(mTime,cSplit){
	try{
		var arr = mTime.split( cSplit );
		var num = 0;
		var tmp = 0;
		var sum = 0 ;

		for (var idx = 0 ; idx <arr.length; idx++){
			num = 0;
			if ( arr[idx].length ==2 && arr[idx].substring(0,1)=="0")
				num = parseInt(arr[idx].substring(1,2));
			else
				num = parseInt(arr[idx]);

			for (var idy = idx+1; idy<arr.length; idy++){
				num = num * 100;
			}
			sum += num;
		}
		return sum;
	} catch(e) {
		return 0;
	}
}

function getDateNum(strDate,type){
	if (strDate == "")
	{
		return 0;
	}
	if (strDate.length == 0)
		return 0;
	try{
		if (type == 0){
			return timeToLong(strDate.substring(0,10),"-");
		} else if (type == 1){

			return timeToLong(strDate.substring(11,strDate.length),":");
		} else {
			var lDate=timeToLong(strDate.substring(0,10),"-")
			var lTime=timeToLong(strDate.substring(11,strDate.length),":")
			var num = 1;
			if (lTime>10000)
				num = 1000000;
			else if(lTime>100)
				num = 10000;

			else
				num =100;
			return lDate*num + lTime;
		}
	} catch(e){
		return -1;
	}
}
function datetime2Long(cDate){
	var lDate = getDateNum(cDate,0);
	var lTime = getDateNum(cDate,2);
	var lData = 0;
	var num = 1;
	if (lTime>10000)
		num = 1000000;
	else if(lTime>100)
		num = 10000;

	else
		num =100;
	return lDate*num + lTime;

}
function date2Long(strDate){
	try{
		var arr = strDate.split( "-" );
//alert(arr.length);
		if (arr.length != 3){
			return 0;
		}
		var year = arr[0];
		var month = arr[1];
		var day = arr[2];

		var iMonth =0;
		var lngDate = 0;

		/*
		if (year.length != 4 || month.length>2 || day.length>2 ||month=="" || day=="" ) {
			return 0;
		}*/
		//年
		var iYear = parseInt(arr[0]);
		//alert("iyear=" + iYear);
		//月
		if ( month.length ==2 && month.substring(0,1)=="0")
			iMonth = parseInt(month.substring(1,2));
		else
			iMonth = parseInt(month)
		//日
		if ( day.length ==2 && day.substring(0,1)=="0")
			iDay = parseInt(day.substring(1,2));
		else
			iDay = parseInt(day);

		//alert("iyear=" + iMonth);

		lngDate =(iYear*10000 + iMonth*100);
		lngDate +=iDay;

		return lngDate;
	} catch(e) {
		return 0;
	}
}
/**
 * 日期型字符串转换long函数  翁乃彬Add(2003-09-27)
 */
function ym2Long(strDate){
	try{

		//alert(strDate + " length=" + strDate.length);
		if (strDate.length!=7){
			return 0;
		}
		var iYear = parseInt(strDate.substring(0,4));

		var iMonth =0;
		if (strDate.substring(5,6)=="0")
			iMonth = parseInt(strDate.substring(6,7));
		else
			iMonth = parseInt(strDate.substring(5,7));
		var lngDate = 0;
		lngDate =(iYear*100 + iMonth);

		if ((iYear>1000 && iYear<9999) && (iMonth>0 && iMonth<13))
		{
			//alert(" a year =" +iYear + "  month =" + iMonth)
			return lngDate;
		} else

		return 0;
	} catch(e) {
		return 0;
	}
}

/**
 *用来处理简单的查询--非通用查询的方式
 */
function toSimpleQuery(url){
	var mForm = document.forms[0];
	var paraSimpleQuery = mForm.paraSimpleQuery.value;
	if(paraSimpleQuery!=null || paraSimpleQuery!=""){
	    var temp = url+"&paraSimpleQuery="+paraSimpleQuery;
		mForm.action = temp;
	}
	mForm.submit();
}
/**
 *用来处理简单的查询--非通用查询的方式--单个和多个查询框的方式
 *CY添加
 *url-点击查询按钮后要跳转到的页面连接
 *queryFlag-是否触发查询按钮动作的标志-true-触发
 */
function doQuery(url,queryFlag){
	var mForm = document.forms[0];
	var url = url+"?queryFlag="+queryFlag;
	mForm.action = url;
	mForm.submit();
}
/**
人员选择模块调用,type为类型,id为页面中隐藏的id属性名称,name为页面显示名字属性的名称
HuHJ: 临时解决办法，设context ＝ 'gdp'
*/
function openSelectWindow(type,id,name) {
	var context = '/gdp';
	var url = '';
	var date = new Date();
	var rnd="rnd="+date.getTime();
    var height = 380;
	switch(type) {
		case 'menselect':
            url = context+"/menselect/menselect.do" ;
            break;
        case 'deptselect':
			url = context+"/menselect/deptselect.do" ;
            break;
        case 'manselect':
        	height = 380;
            url = context+"/menselect/manselect.do" ;
            break;
        case 'theamselect':
		    url=context+"/menselect/theamselect.do";
	        break;
    }
	url = addPara(url,rnd);
	var userIds = document.all[id].value;
	alert('userIds:' + userIds+'url:'+url);
   	var vReturnValue = window.showModalDialog(url,userIds,"dialogHeight: " + height + "px; dialogWidth: 450px; center: Yes; help: no; resizable: no; status: no;");
   	alert('vReturnValue.id is ' + vReturnValue.id);
   	if(vReturnValue.id != '-1') {
       document.all[id].value = vReturnValue.id;
       document.all[name].value = vReturnValue.name;
   }
}

/**
(汤启礼添加)人员选择模块调用,type为类型,id为页面中隐藏的id属性名称,name为页面显示名字属性的名称
*/
function selectMailMan(type) {
	var btn=event.srcElement;
	var frm=btn.form;
	var url = '';
	var date = new Date();
	var rnd="rnd="+date.getTime();
    var height = 500;
	if(type==0 || type==null){
    	url = context+"/menselect/mailmanselect.do" ;
	}else if(type==1){
		url = context+"/menselect/mailoutmanselect.do";
	}
	url = addPara(url,rnd);
	var name=btn.elname;
	var names=name.split(",");
	if(!(names.length%2==0)){
		return false;
	}
	var values=[];
	var j=0;

	for(var i=0;i<names.length;i=i+2){
		values[j++]=frm.elements[names[i]].value;//id
		//values[j++]=frm.elements[names[i+1]].value;//名称
	}
	values[values.length]=btn.name;
	//var userIds = document.all[id].value;
    var retArr = window.showModalDialog(url,values,"dialogHeight: " + height + "px; dialogWidth: 500px; center: Yes; help: no; resizable: no; status: no;");
   //alert('vReturnValue.id is ' + vReturnValue.id);
    if(retArr != null) {
		var obj=null;
		var j=0;
		for(var i=0;i<retArr.length;i++){
			obj=retArr[i];
			frm.elements[names[j++]].value=retArr[i].value;
			frm.elements[names[j++]].value=retArr[i].text;
		}
    }
}



var pattern;
function setPattern(patternStr) {
	pattern = new RegExp(patternStr);
}
function User(name,id,dept,job) {
        this.name = name;
        this.id = id;
        this.dept = dept;
        this.job = job;
        this.isRight = false;
        function compare_by_type(type,name) {
                switch(type) {
                        case 'id_left':
                                if(pattern.test(this.id)) {
                                        this.isRight = true;
                                }
                                return ;
                        case 'id_right':
                                if(pattern.test(this.id)) {
                                        this.isRight = false;
                                }
                                return ;
                        case 'name':
                                return pattern.test(this.name);
                        case 'dept':
                                return pattern.test(seperator + this.dept + seperator);
                        case 'dept_all':
                                return this.dept.length > 0;
                        case 'job':
                                return pattern.test(seperator + this.job + seperator);
                        case 'job_all':
                                return this.job.length > 0;
                        case 'name_equal':
                                return this.name == name;
                        case 'all':
                                return true;
                        default:
                                return pattern.test(this.name);
                }

        }
        this.isMatch = compare_by_type;
}

function searchInUserArray() {
   var array = new Array();
   var i = 0;
        for(user in userArray) {
        if(userArray[user].isMatch()) {
            array[i] = userArray[user];
            i++;
        }
    }
    //alert('array.length is ' + array.length);
    return array;
/**
用于改变显示与隐藏

	function changeAuth()
	{
		var sFlag;
		sFlag = document.forms[0].elements["vo.smtp_auth"].value ;
		if (sFlag == 1){
			document.getElementById("needAuth").style.display = "block";
		}else if (sFlag == 0){
			document.getElementById("needAuth").style.display = "none";
		}
	}*/
}



/***************
*模板类所需的javascript
*
*/

function init_FormData(){

	//没有frame的情况
	var node = "nodePathName";

	var isError = false;

	//是否打开页面；优先取父窗口的值
	try{
	    // 2005-04-05 李炽明 修改
        // 解决页面导航栏冲突问题(非终极解决办法)
	    // 增加对父窗口相关对象的判断；优先取父窗口的值
	    // -----------------------------------------------------------
	    if (window.top.opener && window.top.opener.document.forms[0] && window.top.opener.document.forms[0].elements[node])
	        nodeValue = window.top.opener.document.forms[0].elements[node].value;
	    else
	    // -----------------------------------------------------------
		    nodeValue =  window.opener.parent.top.frames["right"].document.forms[0].elements[node].value;

		isFrame = false;
		isOpen = true;
		isError = false;
	} catch (e){
		isError = true;
	}

	//看看是否为主页面内嵌的数据!
	if (isError == true){
	    try{
	    	nodeValue =  window.parent.document.forms[0].elements[node].value ;
	    	isFrame = false;
	    	isOpen = false;
	    	//alert(tmp);
	    } catch (e){
	    	isError = true;
	    }
	}

	//检查是否主页页内嵌的框架页面数据
	if (isError == true){
		try{
			nodeValue =  window.parent.parent.document.forms[0].elements[node].value ;
			isFrame = true;
			isOpen = false;
			isError = false;
		} catch (e){
			isError = true;
		}
	}

	if (isError == true){
		try{
			nodeValue =  parent.window.opener.parent.top.frames["right"].document.forms[0].elements[node].value;
			isFrame = true;
			isError = false;
			isOpen = true;
			//alert(tmp);
		} catch (e){
			isError = true;
		}
	}
	if (isError == true){
		try{
			nodeValue =  parent.parent.window.opener.parent.top.frames["right"].document.forms[0].elements[node].value;
			isFrame = true;
			isError = false;
			isOpen = true;
			//alert(tmp);
		} catch (e){
			isError = true;
		}
	}
	//alert(isFrame + "= isOpen=" + isOpen + "isError=" + isError);
}
//隐蔽菜单
function hideMenus(isOpen,isFrame){
	if (isFrame && isOpen){
	} else if (isFrame== false && isOpen){
	} else if (isFrame && isOpen == false){
		document.body.onmousedown=function(){
				window.parent.parent.top.frames["right"].hideAllMenu();
			}
	} else {
		document.body.onmousedown=function(){
				window.parent.top.frames["right"].hideAllMenu();
			}

	}
}

//设置隐蔽数据
function setInitData(isOpen,isFrame){

	if (isFrame && isOpen){
		try{
			parent.window.opener.top.frames["hiddendata"].f_initData(document.forms[0], isOpen);
				} catch(e){
			parent.parent.window.opener.top.frames["hiddendata"].f_initData(document.forms[0], isOpen);
		}

	} else if (isFrame== false && isOpen){
		window.opener.parent.top.frames["hiddendata"].f_initData(document.forms[0], isOpen);
	} else if (isFrame && isOpen == false){
		window.parent.parent.top.frames["hiddendata"].f_initData(document.forms[0], isOpen);
	} else {
		window.parent.top.frames["hiddendata"].f_initData(document.forms[0], isOpen);
	}
}

//打印导航条
function getNodePathName(nodePathName,context){

	var pos = -1;
	pos = nodePathName.indexOf("-->");
	var len = "-->".length;
	if (pos > 0){
		nodePathName = nodePathName.substring(pos+len,nodePathName.length);
	}
	document.write("<table width='98%' height='25' border='0' align='center' cellpadding='0' cellspacing='0'>");
	document.write("<tr width='100%'>");
	document.write("<td width='98%' align='left' valign='middle' class='f12'>");
	//document.write('当前路径：'+nodePathName)
	document.write("<img src='" +context+"/images/ico_dqwz.gif' align='absmiddle'>");
	document.write(nodePathName)
	document.write("</td>")
	document.write("</tr>")
	document.write("</table>")
}

//初始化工具栏
function initToolBar(isOpen,isFrame){


	var fid = document.forms[0].elements["fid"];
	//alert(fid.value)
	var showType = 0;


	if (isFrame && isOpen){
		try{
		showType=parent.window.opener.top.frames["hiddendata"].getShowType(fid,isOpen);
				} catch(e){
			showType=parent.parent.window.opener.top.frames["hiddendata"].getShowType(fid,isOpen);
		}

	} else if (isFrame== false && isOpen){
		showType=window.opener.parent.top.frames["hiddendata"].getShowType(fid,isOpen);
	} else if (isFrame && isOpen == false){
		showType=window.parent.parent.top.frames["hiddendata"].getShowType(fid,isOpen);
	} else {
		showType=window.parent.top.frames["hiddendata"].getShowType(fid,isOpen);
	}

	//alert("showType="+showType)
	isShowFirst = ((showType & 1) >0)?true:false;
	isShowPrevious = ((showType & 2) >0)?true:false;
	isShowNext = ((showType & 4) >0)?true:false;
	isShowLast = ((showType & 8) >0)?true:false;

	if (isShowFirst)
		document.all("imgFirst").src  = context+"/images/First.gif";
	if (isShowPrevious)
		document.all("imgPre").src  = context+"/images/Previous.gif";
	if (isShowNext)
		document.all("imgNext").src  = context+"/images/Next.gif";
	if (isShowLast)
		document.all("imgLast").src  = context+"/images/Last.gif";
}

//浏览
function f_Browse(actionType,bIsFrame,bIsOpen){
	var oFid = document.forms[0].elements["fid"];
	//alert("value = "  + oFid.value);
	switch (actionType ){
		case "first":
			if (isShowFirst == false){
				return false;
			}

			if (bIsFrame && bIsOpen){
				try{
				parent.window.opener.top.frames["hiddendata"].f_first(oFid,isOpen);
				} catch(e){
				parent.parent.window.opener.top.frames["hiddendata"].f_first(oFid,isOpen);
				}
			} else if (bIsFrame== false && bIsOpen){
				window.opener.parent.top.frames["hiddendata"].f_first(oFid,isOpen);
			} else if (bIsFrame && bIsOpen == false){
				window.parent.parent.top.frames["hiddendata"].f_first(oFid,isOpen);
			} else {
				window.parent.top.frames["hiddendata"].f_first(oFid,isOpen);
			}

			break;
		case "previous":
			if (isShowPrevious == false){
				return false;
			}
			if (bIsFrame && bIsOpen){
				try{
				parent.window.opener.top.frames["hiddendata"].f_previous(oFid,isOpen);
				} catch(e){
				parent.parent.window.opener.top.frames["hiddendata"].f_previous(oFid,isOpen);
				}
			} else if (bIsFrame== false && bIsOpen){
				window.opener.parent.top.frames["hiddendata"].f_previous(oFid,isOpen);
			} else if (bIsFrame && bIsOpen == false){
				window.parent.parent.top.frames["hiddendata"].f_previous(oFid,isOpen);
			} else {
				window.parent.top.frames["hiddendata"].f_previous(oFid,isOpen);
			}


			break;
		case "next":
			if (isShowNext == false){
				return false;
			}
			if (bIsFrame && bIsOpen){
				try{
				parent.window.opener.top.frames["hiddendata"].f_next(oFid,isOpen);
				}catch(e){
				parent.window.opener.top.frames["hiddendata"].f_next(oFid,isOpen);
				}
			} else if (bIsFrame== false && bIsOpen){
				window.opener.parent.top.frames["hiddendata"].f_next(oFid,isOpen);
			} else if (bIsFrame && bIsOpen == false){
				window.parent.parent.top.frames["hiddendata"].f_next(oFid,isOpen);
			} else {
				window.parent.top.frames["hiddendata"].f_next(oFid,isOpen);
			}


			break;
		case "last":
			if (isShowLast == false){
				return false;
			}
			if (bIsFrame && bIsOpen){
				try{
				parent.window.opener.top.frames["hiddendata"].f_last(oFid,isOpen);
				}catch(e){
				parent.parent.window.opener.top.frames["hiddendata"].f_last(oFid,isOpen);
				}
			} else if (bIsFrame== false && bIsOpen){
				window.opener.parent.top.frames["hiddendata"].f_last(oFid,isOpen);
			} else if (bIsFrame && bIsOpen == false){
				window.parent.parent.top.frames["hiddendata"].f_last(oFid,isOpen);
			} else {
				window.parent.top.frames["hiddendata"].f_last(oFid,isOpen);
			}

			break;
	}

	document.forms[0].submit();

}
function addScope(scope){
		var htmls="<input type=\"hidden\" value=\""+scope+"\" name=\"goa_Scope\" style=\"display:\">";
		try{
		document.forms[0].insertAdjacentHTML("AfterBegin", htmls);
		}catch(e){
		}
		//alert(htmls);
	}

function doUrlCenter(url,width,height){
	var iLeft = (screen.availWidth-width)/2;
	var iTop= (screen.availHeight-height)/2;

	var sOpen = "width="+width +", height="+height +", top="+iTop+", left="+iLeft +", toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no";

	var passform = window.open(url, '',sOpen)
	passform.focus();
	//passform.moveTo((screen.availWidth-280)/2,(screen.availHeight-220)/2);
}

function doUrlCenter2(url,width,height){
		var w = screen.availWidth-width;
	var h = screen.availHeight-height;
	var iLeft = (screen.availWidth-w)/2;
	var iTop= (screen.availHeight-h)/2;
	iTop -=10;
	if (iTop<0)
	{iTop=10;
	}
	var sOpen = "width="+w +", height="+h +", top="+iTop+", left="+iLeft +", toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no";

	var passform = window.open(url, '',sOpen)
	passform.focus();
	//passform.moveTo((screen.availWidth-280)/2,(screen.availHeight-220)/2);
}

function disButton(){
	try{
    var elements = document.forms[0].elements;
    for(var idx = 0 ; idx < elements.length ; idx++) {
		//alert(elements[idx].name + " = " + elements[idx].type);
        if(elements[idx].type == "button" || elements[idx].type == "submit") {
            elements[idx].disabled = true;
        }
    }
	} catch(e){}

}

function openDeptSelect(idproperty,nameproperty){
	url = context + "/cams/sysman/deptselectopen.do"
	var date = new Date();
	var rnd="rnd="+date.getTime();
	url = addPara(url,rnd);
	var vReturnValue = window.showModalDialog(url,null,"dialogHeight: " + 520 + "px; dialogWidth: 450px; center: Yes; help: no; resizable: no; status: no;");
	//alert('has returned!')
	var alertStr = 'openDeptSelect retValue ';
	if(vReturnValue != null) {
		//alertStr += '\n id: ' + vReturnValue.id + ' \n name: ' + vReturnValue.name;
		document.all[idproperty].value=vReturnValue.id;
		document.all[nameproperty].value=vReturnValue.name;
	}
}

function openGoodsSelectDialog(url){
	var ret = showModalDialog(url,window,"dialogHeight: 300px; dialogWidth: 350px; center: yes; help: no; status: no");
	return ret;
}


function replaceSelectDIVInnerHtml(divId,selectId,message) {
	if(document.all[selectId] != null && document.all[selectId].options.length == 0) {
		var innerHtml = "<font color='red'>" + message + "</font>";
		document.all[divId].innerHTML = innerHtml;
	}
}


function checkWarehouseToSelecst(id,message) {
	var result = true;
	if(document.all[id]==null) {
		alert(message);
		result = false;
	}
	return result;
}

function openWarehouseManSelectWindow(id,name,deptId,deptName) {
	var url = '';
	var date = new Date();
	var rnd="rnd="+date.getTime();
        var height = 380;
    url = context+"/menselect/warehousemanselect.do" ;
	url = addPara(url,rnd);
	var userIds = document.all[id].value;
	//alert('--- userIds --- ' + userIds);
   var vReturnValue = window.showModalDialog(url,userIds,"dialogHeight: " + height + "px; dialogWidth: 450px; center: Yes; help: no; resizable: no; status: no;");
   //alert('vReturnValue.id is ' + vReturnValue.id);
   if(vReturnValue.id != '-1') {
	   if(typeof(vReturnValue.deptId) == 'undefined') {
			vReturnValue.deptId = '-1';
			vReturnValue.deptName = '无';
	   }
       document.all[id].value = vReturnValue.id;
       document.all[name].value = vReturnValue.name;
	   if(deptId != '') {
		   document.all[deptId].value = vReturnValue.deptId;
	   }
	   if(deptName != '') {
		   document.all[deptName].value = vReturnValue.deptName;
	   }
   }
}

/**检查输入日期格式是否正确**/
function checkIsDate(oText) {
	if(isDate(oText.value) == false) {
		alert('输入日期格式错误！');
		oText.value = '';
	}
}
/**检查输入日期顺序是否正确**/
function checkBeginEnd(beginDate,endDate) {
	if(document.all[beginDate] != null && document.all[endDate] != null
		&& "" != document.all[beginDate].value && "" != document.all[endDate].value ) {
		var beginDateStr = document.all[beginDate].value;
		var endDateStr = document.all[endDate].value;
		var begingLong = date2Long(beginDateStr);
		var endLong = date2Long(endDateStr);
		if(begingLong > endLong) {
			alert('开始日期不能大于结束日期！');
			return false;
		}
	} else {
		return true;
	}
}


function batchAddGoodsByRet(ret,tablename) {
	for(goods in ret) {
		baddRow(tablename,ret[goods].name,ret[goods].id,ret[goods].unit,ret[goods].unitName,"","","","")
	}
}

function insertGoods(oRow,goodsName,goodsId,goodsUnit,goodsUnitName,goodsNUM,beginNumber,endNumber,remark,disableStr) {
	//alert("===goodsName==="+goodsName);
	//alert("===beginNumber==="+beginNumber);
	//alert("===endNumber==="+endNumber);
	//alert('insertGoods');
		oCell = oRow.insertCell(0);
		oCell.colSpan = 7;
		oCell.innerHTML = "<table  align='center' width='100%' border='0' cellspacing='0' cellpadding='0'><tr>"
			           + "<td width='3%'  align='center' class='listcellrow'><input type='checkbox' name='index' value='"+rowsCount+"' style='width:10px'/>"
			           + "</td>"
                       + "<td width='20%' class='listcellrow'> "
					   + '<input type=\"hidden\" id=\"goodsId'+rowsCount+'" name="goodsId" value='+goodsId+'><div style="width:100px">'+goodsName + '</div>'
					   + "</td>"
					   + "<td width='20%' class='listcellrow'>"
					   + '<input type="hidden" id="goodsUnit'+rowsCount+'" name="goodsUnit" value='+goodsUnit+'>&nbsp;'+goodsUnitName
					   + "</td>"
					   + "<td width='10%' class='listcellrow'>"
					   + '<input type=\"text\" id="goodsNumber'+rowsCount+'" name=\"goodsNumber\" onlytype="int" onfocus="checkTextBoxInput()" ValidateType="notempty" Msg="请输入物品数量！物品：'+goodsName+'" maxlength="5" value="'+goodsNUM+'" '+disableStr+' class="input">'
					   + "</td>"
					   + "<td width='15%' class='listcellrow'>"
					   + '<input type=\"text\" id="beginNumber'+rowsCount+'" name=\"beginNumber\" class=\"input\" maxlength="15" value="'+beginNumber+'"'+disableStr+' >'
					   + "</td>"
					   + "<td width='15%' class='listcellrow'>"
					   + '<input type=\"text\" id="endNumber'+rowsCount+'" name=\"endNumber\" class=\"input\" maxlength="15" value="'+endNumber+'"'+disableStr+' >'
					   + "</td>"
					   + "<td width='18%' class='listcellrow'>"
					   + '<input type=\"text\" id="remark'+rowsCount+'" name="remark" class=\"input\" maxlength="50" value="'+remark+'"'+disableStr+'>'
					   + "</td></tr></table>";
}
function insertInitGoodsRow(oRow,goodsName,goodsId,goodsUnit,goodsUnitName,goodsNUM,beginNumber,endNumber,remark,disableStr) {
	//alert('insertGoods');
		oCell = oRow.insertCell(0);
		oCell.colSpan = 7;
		oCell.innerHTML = "<table  align='center' width='100%' border='0' cellspacing='0' cellpadding='0'><tr>"
					   + "<td width='3%'  align='center' class='listcellrow'>"
					   + '&nbsp'
			           + "</td>"
                       + "<td width='20%' class='listcellrow'> "
					   + '&nbsp'
					   + "</td>"
					   + "<td width='20%' class='listcellrow'>"
					   + '&nbsp'
					   + "</td>"
					   + "<td width='10%' class='listcellrow'>"
					   + '&nbsp'
					   + "</td>"
					   + "<td width='15%' class='listcellrow'>"
					   + '&nbsp'
					   + "</td>"
					   + "<td width='15%' class='listcellrow'>"
					   + '&nbsp'
					   + "</td>"
					   + "<td width='18%' class='listcellrow'>"
					   + '&nbsp'
					   + "</td></tr></table>";
}

function insertInitDutyGoodsRow(oRow) {
	//alert('insertGoods');
		oCell = oRow.insertCell(0);
		oCell.colSpan = 7;
		oCell.innerHTML = "<table  align='center' width='100%' border='0' cellspacing='0' cellpadding='0'><tr>"
					   + "<td width='3%'  align='center' class='listcellrow'>"
					   + '&nbsp'
			           + "</td>"
                       + "<td width='10%' class='listcellrow'> "
					   + '&nbsp'
					   + "</td>"
					   + "<td width='20%' class='listcellrow'>"
					   + '&nbsp'
					   + "</td>"
					   + "<td width='10%' class='listcellrow'>"
					   + '&nbsp'
					   + "</td>"
					   + "<td width='10%' class='listcellrow'>"
					   + '&nbsp'
					   + "</td>"
					   + "<td width='10%' class='listcellrow'>"
					   + '&nbsp'
					   + "</td>"
					   + "<td width='10%' class='listcellrow'>"
					   + '&nbsp'
					   + "</td>"
					   + "<td width='15%' class='listcellrow'>"
					   + '&nbsp'
					   + "</td>"
					   + "</tr></table>";
}
function deleteRows() {
	var els=document.forms[0].elements;
	var rowId = new Array();
	var rowIdIndex = 0;
			for(var i=0;i<els.length;i++){
				//alert("!!!")
				if(els[i].type=="checkbox" && els[i].checked){
					if(els[i].name == "selectall" || els[i].name == "setFormal") {
						continue;
					}
					rowId[rowIdIndex] = els[i].value;
					rowIdIndex++;					
				}
			}
			for(var j=0 ; j < rowId.length ; j++ ) {
				deleteRow('detailtable',rowId[j]);
			}
	//alert('fillBlankRow : ' + rowIdIndex); 
	 fillBlankRow(rowIdIndex);
}
function deleteRow(tableName,index) {
	var id = tableName + index;
	//alert('table name is ' + tableName)
	oTable = document.all[tableName];
	//alert('id is ' + id);
	//alert('index: ' + index)
	oTable.deleteRow(document.all[id].rowIndex);
	//rowsCount--;
}

var initRowCount = 10;

function deleteAllTableRows(tableName) {
	deleteAllTableRows(tableName,1);
}
function deleteAllTableRows(tableName,beginRow) {
	//alert('beginRow : ' + beginRow);
	var table = document.all[tableName];
	var tRows  = table.rows;
	//alert('row length: ' + tRows.length)
	var rowLength = tRows.length;
	while(tRows.length > beginRow) {
		table.deleteRow(tRows[tRows.length-1].rowIndex);
	}
}

function getRowCount(tablename) {
	var table = document.all[tablename];
	var tRows  = table.rows;
	//alert('row length: ' + tRows.length)
	var rowLength = tRows.length;
	return rowLength;
}
function fillBlankRow(rowCount) {
	//alert('fillBlankRow : ' + rowCount); 
	var count = 0;
	while(count < rowCount) {
		baddInitRow('blanktable');
		count++;
	}
}
function baddInitRow(tableName) {
		oTable = document.all[tableName];
	//alert('table name is ' + tableName)
	//alert('goods id is ' + goodsId)
	rowsCount++;
	//alert('row count: ' + rowsCount);
		oRow = oTable.insertRow();
		oRow.id = tableName + rowsCount;

	insertInitGoodsRow(oRow)
}

function fillBlankTable(tableName) {
	var intserBlankCount = initRowCount - getRowCount(tableName) + 1 ;
	deleteAllTableRows('blanktable',0); 
		//alert(intserBlankCount);
	fillBlankRow(intserBlankCount);
}
/*
适用于crm的人员，部门，项目的选择框
pc（2005-07-01）
*/
function selectInfos(flag,ID,NAME,amount,receivedamount,cust,custname){
var date=new Date();
var dates=date.getTime();
var urls=context+"/cams/crm/contract/selectContact.do?rnd="+dates+"&flag="+flag;
var returnValue=window.showModalDialog(urls,"pc","dialogHeight: 320px; dialogWidth: 270px; center: Yes; help: no; resizable: no; status: no;");
   if(returnValue!=null){
   ID.value=returnValue.id;
   NAME.value=returnValue.name;
   if(flag==1){
   amount.value=returnValue.amount;
  // alert("==returnValue.amount=="+returnValue.amount);
   receivedamount.value=returnValue.receivedamount;
  // alert("==returnValue.receivedamount=="+returnValue.receivedamount);
   cust.value=returnValue.cust;
   //alert("==returnValue.cust=="+returnValue.cust);
   custname.value=returnValue.custname;
   //alert("==returnValue.custname=="+returnValue.custname);
   }
   }
}

/*
适用于hr的人员，部门，项目的选择框
pc（2005-07-08）
*/
function selectHrInfos(flag,ID,NAME){
var date=new Date();
var dates=date.getTime();
var urls=context+"/cams/hr/member/selectHrInfo.do?action=selectHrInfos&rnd="+dates+"&flag="+flag;
var returnValue=window.showModalDialog(urls,"pc","dialogHeight: 320px; dialogWidth: 270px; center: Yes; help: no; resizable: no; status: no;");
 if(returnValue!=null){
   ID.value=returnValue.id;
   NAME.value=returnValue.name;
   }
}

/*
适用于hr工作流部分人员选择
pc（2005-07-08）
*/
function selectHrAllInfos(ID,NAME,usercode,indate,contractdatestart,contractdateend,deptid,deptname,postid,postname){
var date=new Date();
var dates=date.getTime();
var urls=context+"/cams/hr/changepensonapply/selecthrallpensoninfo.do?action=selectProbationalPerson&rnd="+dates;
var returnValue=window.showModalDialog(urls,"pc","dialogHeight: 320px; dialogWidth: 270px; center: Yes; help: no; resizable: no; status: no;");
   if(returnValue!=null){
	   ID.value=returnValue.id;
	  // alert("1");
	   NAME.value=returnValue.name;
	   try{
		   //alert(usercode);
		   usercode.value=returnValue.usercode;
	   }catch(e){
			//alert(e.description);
	   }	 
	   indate.value=returnValue.indate; 
	   contractdatestart.value=returnValue.contractdatestart;
	   contractdateend.value=returnValue.contractdateend;
	   deptid.value=returnValue.deptid;
	   deptname.value=returnValue.deptname;
	   postid.value=returnValue.postid;
	   postname.value=returnValue.postname;
   }
}

//选择下一步审批人
function selectnextname(){
	var ID=document.getElementById("vo.nextpassman");
	var NAME= document.getElementById("vo.nextpassmanname");
	selectHrInfos(3,ID,NAME);
}