/*
*功能:一些表单元素的验证功能
*作者：汤启礼
*/




function validater(frmObj)
{
	var msgTmp="";
	var msg="";
	var arrType;
	var arrMsg="";
	var el;
	if(frmObj==null) frmObj=document.forms[0];
	for(var i=0;i<frmObj.elements.length;i++){
		el=frmObj.elements[i];
		if(el.getAttribute("ValidateType")!=null){

			arrType=el.getAttribute("ValidateType").split(",");
			if(el.getAttribute("Msg")==null) el.getAttribute("Msg")="";
			arrMsg=el.getAttribute("Msg").split(";");
			if(arrType.length==arrMsg.length){
				for(var j=0;j<arrType.length;j++){
					if(!ValidateEl(el,arrType[j])){
						msg+="--"+arrMsg[j]+"\n";
					}
				}
			}
		}
	}
	if(msg!=""){
		alert(msg);
		return false;
	}
	return true;
}




// 为 String 类增加一个 trim 方法
String.prototype.trim = function()
{
    // 用正则表达式将前后空格用空字符串替代。
    return this.replace( /(^\s*)|(\s*$)/g, "" );
}

// 使用正则表达式，检测 s 是否满足模式 re
function checkExp( re, s )
{
	return re.test( s );
}

// 验证是否 字母数字
function isAlphaNumeric( strValue )
{
	// 只能是 A-Z a-z 0-9 之间的字母数字 或者为空
	return checkExp( /^\w*$/gi, strValue );
}

// 验证是否 日期
function isDate( strValue )
{
	// 日期格式必须是 2001-10-1/2001-1-10 或者为空
	if( isEmpty( strValue ) ) return true;

	if( !checkExp( /^\d{4}-[01]?\d-[0-3]?\d$/g, strValue ) ) return false;
	// 或者 /^\d{4}-[1-12]-[1-31]\d$/

	var arr = strValue.split( "-" );
	var year = arr[0];
	var month = arr[1];
	var day = arr[2];

	// 1 <= 月份 <= 12，1 <= 日期 <= 31
	if( !( ( 1<= month ) && ( 12 >= month ) && ( 31 >= day ) && ( 1 <= day ) ) )
		return false;

	// 润年检查
	if( !( ( year % 4 ) == 0 ) && ( month == 2) && ( day == 29 ) )
		return false;

	// 7月以前的双月每月不超过30天
	if( ( month <= 7 ) && ( ( month % 2 ) == 0 ) && ( day >= 31 ) )
		return false;

	// 8月以后的单月每月不超过30天
	if( ( month >= 8) && ( ( month % 2 ) == 1) && ( day >= 31 ) )
		return false;

	// 2月最多29天
	if( ( month == 2) && ( day >=30 ) )
		return false;

	return true;
}
// 验证是否 日期
function isYearMonth( strValue )
{
	// 日期格式必须是 2001-10-1/2001-1-10 或者为空
	if( isEmpty( strValue ) ) return true;
	if( !checkExp( /^\d{4}-(([0][1-9])|([1][012]))$/g, strValue ) ) return false;
	// 或者 /^\d{4}-[1-12]-[1-31]\d$/
	return true;
}
// 验证是否 时间 add by 张碧娟 (2003-7-29)
function isTimeOnly( strValue )
{
	// 日期格式必须是 2001-10-1/2001-1-10 或者为空
	if( isEmpty( strValue ) ) return false;

	if( !checkExp( /^[0-2]?\d:[0-5]?\d$/g, strValue ) ) return false;
	//或者if( !checkExp( /^(0[0-9]|1[0-9]|2[0-3]):((0[0-9])|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9])$/g, strValue ) ) return false;
	// 或者 /^\d{4}-[1-12]-[1-31]\d$/

	var arr=strValue.split(":");
	var hour=arr[0];
	var minute=arr[1];


	if(!((0<=hour)&&( 23 >= hour )&&( 59 >= minute ) && (0 <= minute )))
		return false;



	return true;
}

// 验证是否日期加 时间且不能为空 add by 张碧娟 (2003-7-29)
function isTime( strValue )
{
	// 日期格式必须是 2001-10-1/2001-1-10 或者为空
	if( isEmpty( strValue ) ) return false;

	if( !checkExp( /^\d{4}-[01]?\d-[0-3]?\d+\s+[0-2]?\d:[0-6]?\d:[0-6]?\d$/g, strValue ) ) return false;
	// 或者 /^\d{4}-[1-12]-[1-31]\d$/

	var arr=strValue.split(" ");
	var arr1 = arr[0].split( "-" );
	var year = arr1[0];
	var month = arr1[1];
	var day = arr1[2];
	var arr2=arr[1].split(":");
	var hour=arr2[0];
	var minute=arr2[1];
	var second=arr2[2];

	// 1 <= 月份 <= 12，1 <= 日期 <= 31
	if( !( ( 1<= month ) && ( 12 >= month ) && ( 31 >= day ) && ( 1 <= day ) ) )
		return false;

	// 润年检查
	if( !( ( year % 4 ) == 0 ) && ( month == 2) && ( day == 29 ) )
		return false;

	// 7月以前的双月每月不超过30天
	if( ( month <= 7 ) && ( ( month % 2 ) == 0 ) && ( day >= 31 ) )
		return false;

	// 8月以后的单月每月不超过30天
	if( ( month >= 8) && ( ( month % 2 ) == 1) && ( day >= 31 ) )
		return false;

	// 2月最多29天
	if( ( month == 2) && ( day >=30 ) )
		return false;

	if(!((0<=hour)&&( 23 >= hour )&& ( 59 >= minute ) && ( 0 <= minute )&& ( 59 >= second ) && ( 0 <= second )))
		return false;


	return true;
}


// 验证是否日期加 时间且可以为空 add by 张碧娟 (2003-08-12)
function isTime1( strValue )
{
	// 日期格式必须是 2001-10-1/2001-1-10 或者为空
	if( isEmpty( strValue ) ) return true;

	if( !checkExp( /^\d{4}-[01]?\d-[0-3]?\d+\s+[0-2]?\d:[0-6]?\d:[0-6]?\d$/g, strValue ) ) return false;
	// 或者 /^\d{4}-[1-12]-[1-31]\d$/

	var arr=strValue.split(" ");
	var arr1 = arr[0].split( "-" );
	var year = arr1[0];
	var month = arr1[1];
	var day = arr1[2];
	var arr2=arr[1].split(":");
	var hour=arr2[0];
	var minute=arr2[1];
	var second=arr2[2];

	// 1 <= 月份 <= 12，1 <= 日期 <= 31
	if( !( ( 1<= month ) && ( 12 >= month ) && ( 31 >= day ) && ( 1 <= day ) ) )
		return false;

	// 润年检查
	if( !( ( year % 4 ) == 0 ) && ( month == 2) && ( day == 29 ) )
		return false;

	// 7月以前的双月每月不超过30天
	if( ( month <= 7 ) && ( ( month % 2 ) == 0 ) && ( day >= 31 ) )
		return false;

	// 8月以后的单月每月不超过30天
	if( ( month >= 8) && ( ( month % 2 ) == 1) && ( day >= 31 ) )
		return false;

	// 2月最多29天
	if( ( month == 2) && ( day >=30 ) )
		return false;

	if(!((1<=hour)&&( 24 >= hour )&& ( 60 >= minute ) && ( 1 <= minute )&& ( 60 >= second ) && ( 1 <= second )))
		return false;


	return true;
}

// 验证是否货币 add by 张碧娟 (2003-10-30)
function isMoney1( strValue )
{
	// 货币必须是 -12,345,678.9 等格式 或者为空
	if( isEmpty( strValue ) ) return true;

	return checkExp( /^[+-]?\d{1,6}\.\d{2}$/g, strValue );
}

// 验证是否 Email
function isEmail( strValue )
{
	// Email 必须是 x@a.b.c.d 等格式 或者为空
	if( isEmpty( strValue ) ) return true;

	//return checkExp( /^\w+@(\w+\.)+\w+$/gi, strValue );	//2001.12.24测试出错 检查 jxj-xxx@yysoft.com时不能通过
	//Modify By Tianjincat 2001.12.24
	var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	return checkExp( pattern, strValue );

}

// 验证是否 为空
function isEmpty( strValue )
{
	if( strValue == "" )
		return true;
	else
		return false;
}

// 验证是否 数字
function isNumeric( strValue )
{
	// 数字必须是 0123456789 或者为空

	//return checkExp( /^\d*$/g, strValue );
	return !isNaN(strValue);

	//return  checkExp(/\ \^(-|\+)?\d+(\.\d+)?$/g, strValue );
}

// 验证是否 整数
function isInt( strValue )
{
	// 数字必须是 0123456789 或者为空

	return checkExp( /^\d*$/g, strValue );

	//return  checkExp(/\ \^(-|\+)?\d+(\.\d+)?$/g, strValue );
}


// 验证是否 货币
function isMoney( strValue )
{
	// 货币必须是 -12,345,678.9 等格式 或者为空
	if( isEmpty( strValue ) ) return true;

	return checkExp( /^[+-]?\d+(,\d{3})*(\.\d+)?$/g, strValue );
}

// 验证是否 电话
function isPhone( strValue )
{
	// 普通电话	(0755)4477377-3301/(86755)6645798-665
	// Call 机	95952-351
	// 手机		130/131/135/136/137/138/13912345678
	// 或者为空
	if( isEmpty( strValue ) ) return true;

	return checkExp( /(^\(\d{3,5}\)\d{6,8}(-\d{2,8})?$)|(^\d+-\d+$)|(^(130|131|135|136|137|138|139)\d{8}$)/g, strValue );
}

// 验证是否 邮政编码
function isPostalCode( strValue )
{
	// 邮政编码必须是6位数字
	return checkExp( /(^$)|(^\d{6}$)/gi, strValue )
}


// 验证是否 URL
function isURL( strValue )
{
	// http://www.yysoft.com/ssj/default.asp?Type=1&ArticleID=789
	if( isEmpty( strValue ) ) return true;

	var pattern = /^(http|https|ftp):\/\/(\w+\.)+[a-z]{2,3}(\/\w+)*(\/\w+\.\w+)*(\?\w+=\w*(&\w+=\w*)*)*/gi;
	// var pattern = /^(http|https|ftp):(\/\/|\\\\)(\w+\.)+(net|com|cn|org|cc|tv|[0-9]{1,3})((\/|\\)[~]?(\w+(\.|\,)?\w\/)*([?]\w+[=])*\w+(\&\w+[=]\w+)*)*$/gi;
	// var pattern = ((http|https|ftp):(\/\/|\\\\)((\w)+[.]){1,}(net|com|cn|org|cc|tv|[0-9]{1,3})(((\/[\~]*|\\[\~]*)(\w)+)|[.](\w)+)*(((([?](\w)+){1}[=]*))*((\w)+){1}([\&](\w)+[\=](\w)+)*)*)/gi;

	return checkExp( pattern, strValue );

}

// 检查字段长度
//
//	strValue	字符串
//	strParam	检查参数，形如：L<10, L=5, L>117
//
function checkLength( strValue, strParam )
{
	if( isEmpty( strValue ) )	return true;

	// 参数形如：L<10, L=5, L>117
	if( strParam.charAt( 0 ) != 'L' )	return false;

	var l = strValue.length;
	var ml = parseInt( strParam.substr( 2 ) );

	switch( strParam.charAt( 1 ) )
	{
		case '<' :
			if( l >= ml )
				return false;
			break;

		case '=' :
			if( l != ml )
				return false;
			break;

		case '>' :
			if( l <= ml )
				return false;
			break;

		default :
			return false
	}

	return true;
}


// 检查输入数据长度的合法性（字符长度不能大于**个字符）
//
//	输入参数
//		strName		字段对象
//		strDescription	字段描述
//		strLength	字段长度
//
function ValidateMaxLength( strName, strDescription, strLength) {
	var strMsg = "";
	var strValue = document.all( strName ).value.trim();
	var strMaxLength = "L<" + strLength;
	if( !checkLength( strValue, strMaxLength ))
	strMsg = '"' + strDescription + '" 必须小于'+ strLength + '个字符\n';
	return strMsg;
}

// 检查输入数据长度的合法性（字符长度不能小于**个字符）
//
//	输入参数
//		strName		字段对象
//		strDescription	字段描述
//		strLength	字段长度
//
function ValidateMinLength( strName, strDescription, strLength) {
	var strMsg = "";
	var strValue = document.all( strName ).value.trim();
	var strMaxLength = "L>" + strLength;
	if( !checkLength( strValue, strMaxLength ))
	strMsg = '"' + strDescription + '" 必须大于'+ strLength + '个字符\n';
	return strMsg;
}

// 检查输入数据长度的合法性（字符长度等于**个字符）
//
//	输入参数
//		strName		字段对象
//		strDescription	字段描述
//		strLength	字段长度
//
function ValidateEquLength( strName, strDescription, strLength) {
	var strMsg = "";
	var strValue = document.all( strName ).value.trim();
	var strMaxLength = "L=" + strLength;
	if( !checkLength( strValue, strMaxLength ))
	strMsg = '"' + strDescription + '" 必须等于'+ strLength + '个字符\n';
	return strMsg;
}

//
//	输入参数
//		obj		字段对象
//		strType	字段类型
//
function ValidateEl( obj, strType)
{
	var strMsg ="";
	var result=true;
	var strValue = obj.value.trim();

	switch( strType.toLowerCase() )
	{
		case "alphanumeric" :	// 字母数字
			result=isAlphaNumeric( strValue ) ;
			break;

		case "date" :	// 日期
			result=isDate( strValue );
			break;

		case "yearmonth" :	// 日期
			result=isYearMonth(strValue);
			break;

		case "time":    //日期加时间且不可以为空
			result=isTime(strValue);
			break;

		case "time1":    //日期加时间且可以为空
			result=isTime1(strValue);
			break;

		case "timeonly":  //时间
			result=	isTimeOnly(strValue);
			break;

		case "email" :	// 电子邮件
			result=isEmail(strValue);
			break;

		case "notempty" :	// 不许空值
			result=!isEmpty( strValue );
			break;
		case "numeric" :	//数字
			result=isNumeric( strValue );

			break;
		case "int" :	//整型
			result=isInt( strValue );

			break;
		case "money" :	//货币
			result=isMoney( strValue );
			break;

		case "money1" :	//货币
			result=isMoney1( strValue );
			break;

		case "phone" :	// 电话
			result=isPhone( strValue );
			break;

		case "postalcode" :	// 邮政编码
			result=isPostalCode( strValue );
			break;

		case "url" :	// URL
			result=isURL( strValue );
			break;
	}

	return result;
}

/**********************
*控制文本框只能输入某类型值
***********************/
function setBoxInputType(){
	var flag=this.onlytype;

	var txt="";
	var code=event.keyCode;
	txt=this.value;
	if(event.shiftKey) return false;
	if(flag.toLowerCase()=="int"&& (code==190 || code==110)) return false;
	if((code<96 || code>105) && code!=110 && code!=189 && code!=109 && (code<48 || code>57) && code!=190 &&  code!=46 && code!=37 &&  code!=39 &&  code!=8 && code!=9){
		return false;
	}
	if(flag.toLowerCase()=="float"){
		if(txt=="" && (code==190 || code==110)) return false;
		if(txt.indexOf(".")!=-1 && (code==190 || code==110)) return false;
		if(code==189 || code==109){
			if(txt.indexOf("-")!=-1){
				return false;
			}else{
				txt="-"+txt;
				event.srcElement.value=txt;
				return false;
			}
		}
		//if(txt.indexOf("-")!=-1 && (code==189 || code==109)) return false;
		
	}
        if(flag.toLowerCase()=="float1"){
		if(txt=="" && (code==190 || code==110)) return false;
		if(txt.indexOf(".")!=-1 && (code==190 || code==110)) return false;
		if(txt.indexOf(".")!=-1){
			var arrTmp=txt.split(".");
			if(code==8 || code==37 || code==38 || code==39 || code==40 || code==46) return true;
			if(arrTmp[1].length>1) return false;
              	//txt.length()>(txt.indexOf(".")+3)) return false;
		}
	}
	return true;

}

function checkPasteData(){
	var pasteData=window.clipboardData.getData('Text');
	switch(this.onlytype.toLowerCase()){

		case "int":
			if(!isInt(pasteData)){
				alert("粘贴文本不能转化整数，该文本框要求输入整数.");
				event.returnValue=false;
			}
			break;
		case "float":
			if(!isNumeric(pasteData)){
				alert("粘贴文本不能转化数字，该文本框要求输入数字.");
				event.returnValue=false;
			}
		case "float1":
			if(!isNumeric(pasteData)){
				alert("粘贴文本不能转化数字，该文本框要求输入数字.");
				event.returnValue=false;
			}
			break;

	}

}

function checkInputValue(){
	var value=this.value;
	switch(this.onlytype.toLowerCase()){

		case "int":
			if(!isInt(this.value)){
				alert("该文本框要求输入整数.");

				this.value=(this.oldValue==null?"":this.oldValue);
				return false;
			}
			break;
		case "float":
			if(!isNumeric(this.value)){
				alert("该文本框要求输入数字.");
				this.value=(this.oldValue==null?"":this.oldValue);
				return false;
			}
		case "float1":
			if(!isNumeric(this.value)){
				alert("该文本框要求输入数字.");
				this.value=(this.oldValue==null?"":this.oldValue);
				return false;
			}
			break;
	}

}

//文本框获得焦点时运行该方法（onfocus="checkTextBoxInput()"）
function checkTextBoxInput(event){
    if(navigator.appName == "Netscape"){
	    var txtBox=event.target;
    } else {
	    var txtBox=event.srcElement;
    }
	if(txtBox.onlytype){
		if(txtBox.onlytype.toLowerCase()=="date" || txtBox.onlytype.toLowerCase()=="time")
		{
			//txtBox.readOnly=true;
			txtBox.onclick=setTextBoxDate;
			return true;
		}
		if(txtBox.oldValue==null){
			txtBox.oldValue=txtBox.value;
		}

		/****解决输入法问题*/
		//屏蔽输入法
		if(txtBox.onlytype.toLowerCase()=="float" || txtBox.onlytype.toLowerCase()=="int" || txtBox.onlytype.toLowerCase()=="float1"){
			txtBox.style.imeMode="disabled";
		}
		/****解决输入法问题*/

		txtBox.onkeydown=setBoxInputType;
		try{txtBox.onpaste=checkPasteData;}catch(e){}
		txtBox.onblur=checkInputValue;


	}else{
		return false;
	}

}

//////////////////////////////////////////////


/**********************
*设置文本框为日期输入
***********************/
function selectDateNoTime() {
	var str=window.showModalDialog("./getdateNoTime.html",null,"font-size:10px;dialogWidth:180px;dialogHeight:180px;scrollbars=no;status=no");
	if (str == null || str == "" || str == "undefined")
		return "";
	else
		return str;
}
function selectDate() {
	var str=window.showModalDialog("./getdate.html",null,	"font-size:10px;dialogWidth:180px;dialogHeight:180px;scrollbars=no;status=no");
	if (str == null || str == "" || str == "undefined")
		return "";
	else
		return str;
 }


function setDate(blFlag)
{
	var bl=true;
	var ret=null;
	if(blFlag==null){
		bl=true;
	}else{
		bl=blFlag;
	}
	if(event.srcElement.tagName=="INPUT"){
		if(bl){
			ret=selectDateNoTime();
			if(ret!=null && ret!=""){
				event.srcElement.value=ret;
			}
		}else{
			ret=selectDate();
			if(ret!=null && ret!=""){
				event.srcElement.value=ret;
			}
		}
	}
	return ret;
}


// 设置日期的某部分(年,月,日),blFlag的值为0:年,1:月,2:日 ,  add by lbl
function setDatePart(blFlag){
  var strDate=selectDateNoTime();
  if(strDate!=''){
    var arr = strDate.split( "-" );
    var strYear = arr[0];
    var strMonth = arr[1];
    var strDay = arr[2];
    if(blFlag==0){
      event.srcElement.value=strYear;
    }
    if(blFlag==1){
      event.srcElement.value=strMonth;
    }
    if(blFlag==2){
      event.srcElement.value=strDay;
    }
    if(blFlag==3){
      event.srcElement.value=strYear+"-"+strMonth;
    }
  }
}


function setTextBoxDate()
{
	var bl=true;
	var ret;
	if(this.onlytype.toLowerCase()=="date"){
		bl=true;
	}else if(this.onlytype.toLowerCase()=="time"){
		bl=false;
	}
	if(bl){
		ret=selectDateNoTime();
		if(ret!=null && ret!=""){
			this.value=ret;
		}
	}else{
		ret=selectDate();
		if(ret!=null && ret!=""){
			this.value=ret;
		}
	}
}

//////////////////////////////
/*
设置文本域的最大长度
文本域获得焦点时运行该方法（onfocus="setMaxLen()"）
*/
//function setMaxLen(len){
function setMaxLen(event){
    try{
    var ns = navigator.appName == "Netscape";
    if(ns)
        var txtObj=event.target;
	else
        var txtObj=event.srcElement;
	txtObj.lenValue=null;

	if(txtObj.tagName!="TEXTAREA") return;
    /*************************************
    if(txtObj.maxlength==null && len==null) return;
	if(len!=null){
		txtObj.lenValue=len;
	}
    *************************************/
    if(txtObj.maxlength==null) return;
	if(txtObj.maxlength!=null){			//优先处理
		txtObj.lenValue=txtObj.maxlength;

	}
	if(txtObj.lenValue!=null){
		if(!isInt(txtObj.lenValue)){
			alert("maxlength必须设置为整数");
			return;
		}else{
			txtObj.onkeydown=checkInputTextMaxLen;
			try{txtObj.onpaste=checkPasteTextMaxLen;}catch(e){}
			txtObj.onblur=checkTextAreaValueLen;
			return;
		}
	}else{
		return;
	}
    } catch(e) { }
}

function checkInputTextMaxLen(){
	var code=event.keyCode;
	if(code==190 ||  code==46 || code==37 ||  code==39 ||  code==8 ||  code==40 || code==17 ) return true;
	if(this.value.length>=this.lenValue) return false;
}
function checkPasteTextMaxLen(){
	var pasteData=window.clipboardData.getData('Text');
	if(pasteData.length>this.lenValue){
		alert("粘贴的文字数量超过文本域所设定的最大长度");
		return false;
	}
}
function checkTextAreaValueLen(){
	if(this.value.length>this.lenValue){
		alert("文本域内容长度超出所设定的最大长度");
		this.value=this.value.substring(0,this.lenValue);
		return false;
	}
}
//设定文本域最大长度结束


