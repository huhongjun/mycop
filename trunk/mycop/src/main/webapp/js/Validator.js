/*
*����:һЩ��Ԫ�ص���֤����
*���ߣ�������
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




// Ϊ String ������һ�� trim ����
String.prototype.trim = function()
{
    // ��������ʽ��ǰ��ո��ÿ��ַ��������
    return this.replace( /(^\s*)|(\s*$)/g, "" );
}

// ʹ��������ʽ����� s �Ƿ�����ģʽ re
function checkExp( re, s )
{
	return re.test( s );
}

// ��֤�Ƿ� ��ĸ����
function isAlphaNumeric( strValue )
{
	// ֻ���� A-Z a-z 0-9 ֮�����ĸ���� ����Ϊ��
	return checkExp( /^\w*$/gi, strValue );
}

// ��֤�Ƿ� ����
function isDate( strValue )
{
	// ���ڸ�ʽ������ 2001-10-1/2001-1-10 ����Ϊ��
	if( isEmpty( strValue ) ) return true;

	if( !checkExp( /^\d{4}-[01]?\d-[0-3]?\d$/g, strValue ) ) return false;
	// ���� /^\d{4}-[1-12]-[1-31]\d$/

	var arr = strValue.split( "-" );
	var year = arr[0];
	var month = arr[1];
	var day = arr[2];

	// 1 <= �·� <= 12��1 <= ���� <= 31
	if( !( ( 1<= month ) && ( 12 >= month ) && ( 31 >= day ) && ( 1 <= day ) ) )
		return false;

	// ������
	if( !( ( year % 4 ) == 0 ) && ( month == 2) && ( day == 29 ) )
		return false;

	// 7����ǰ��˫��ÿ�²�����30��
	if( ( month <= 7 ) && ( ( month % 2 ) == 0 ) && ( day >= 31 ) )
		return false;

	// 8���Ժ�ĵ���ÿ�²�����30��
	if( ( month >= 8) && ( ( month % 2 ) == 1) && ( day >= 31 ) )
		return false;

	// 2�����29��
	if( ( month == 2) && ( day >=30 ) )
		return false;

	return true;
}
// ��֤�Ƿ� ����
function isYearMonth( strValue )
{
	// ���ڸ�ʽ������ 2001-10-1/2001-1-10 ����Ϊ��
	if( isEmpty( strValue ) ) return true;
	if( !checkExp( /^\d{4}-(([0][1-9])|([1][012]))$/g, strValue ) ) return false;
	// ���� /^\d{4}-[1-12]-[1-31]\d$/
	return true;
}
// ��֤�Ƿ� ʱ�� add by �ű̾� (2003-7-29)
function isTimeOnly( strValue )
{
	// ���ڸ�ʽ������ 2001-10-1/2001-1-10 ����Ϊ��
	if( isEmpty( strValue ) ) return false;

	if( !checkExp( /^[0-2]?\d:[0-5]?\d$/g, strValue ) ) return false;
	//����if( !checkExp( /^(0[0-9]|1[0-9]|2[0-3]):((0[0-9])|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9])$/g, strValue ) ) return false;
	// ���� /^\d{4}-[1-12]-[1-31]\d$/

	var arr=strValue.split(":");
	var hour=arr[0];
	var minute=arr[1];


	if(!((0<=hour)&&( 23 >= hour )&&( 59 >= minute ) && (0 <= minute )))
		return false;



	return true;
}

// ��֤�Ƿ����ڼ� ʱ���Ҳ���Ϊ�� add by �ű̾� (2003-7-29)
function isTime( strValue )
{
	// ���ڸ�ʽ������ 2001-10-1/2001-1-10 ����Ϊ��
	if( isEmpty( strValue ) ) return false;

	if( !checkExp( /^\d{4}-[01]?\d-[0-3]?\d+\s+[0-2]?\d:[0-6]?\d:[0-6]?\d$/g, strValue ) ) return false;
	// ���� /^\d{4}-[1-12]-[1-31]\d$/

	var arr=strValue.split(" ");
	var arr1 = arr[0].split( "-" );
	var year = arr1[0];
	var month = arr1[1];
	var day = arr1[2];
	var arr2=arr[1].split(":");
	var hour=arr2[0];
	var minute=arr2[1];
	var second=arr2[2];

	// 1 <= �·� <= 12��1 <= ���� <= 31
	if( !( ( 1<= month ) && ( 12 >= month ) && ( 31 >= day ) && ( 1 <= day ) ) )
		return false;

	// ������
	if( !( ( year % 4 ) == 0 ) && ( month == 2) && ( day == 29 ) )
		return false;

	// 7����ǰ��˫��ÿ�²�����30��
	if( ( month <= 7 ) && ( ( month % 2 ) == 0 ) && ( day >= 31 ) )
		return false;

	// 8���Ժ�ĵ���ÿ�²�����30��
	if( ( month >= 8) && ( ( month % 2 ) == 1) && ( day >= 31 ) )
		return false;

	// 2�����29��
	if( ( month == 2) && ( day >=30 ) )
		return false;

	if(!((0<=hour)&&( 23 >= hour )&& ( 59 >= minute ) && ( 0 <= minute )&& ( 59 >= second ) && ( 0 <= second )))
		return false;


	return true;
}


// ��֤�Ƿ����ڼ� ʱ���ҿ���Ϊ�� add by �ű̾� (2003-08-12)
function isTime1( strValue )
{
	// ���ڸ�ʽ������ 2001-10-1/2001-1-10 ����Ϊ��
	if( isEmpty( strValue ) ) return true;

	if( !checkExp( /^\d{4}-[01]?\d-[0-3]?\d+\s+[0-2]?\d:[0-6]?\d:[0-6]?\d$/g, strValue ) ) return false;
	// ���� /^\d{4}-[1-12]-[1-31]\d$/

	var arr=strValue.split(" ");
	var arr1 = arr[0].split( "-" );
	var year = arr1[0];
	var month = arr1[1];
	var day = arr1[2];
	var arr2=arr[1].split(":");
	var hour=arr2[0];
	var minute=arr2[1];
	var second=arr2[2];

	// 1 <= �·� <= 12��1 <= ���� <= 31
	if( !( ( 1<= month ) && ( 12 >= month ) && ( 31 >= day ) && ( 1 <= day ) ) )
		return false;

	// ������
	if( !( ( year % 4 ) == 0 ) && ( month == 2) && ( day == 29 ) )
		return false;

	// 7����ǰ��˫��ÿ�²�����30��
	if( ( month <= 7 ) && ( ( month % 2 ) == 0 ) && ( day >= 31 ) )
		return false;

	// 8���Ժ�ĵ���ÿ�²�����30��
	if( ( month >= 8) && ( ( month % 2 ) == 1) && ( day >= 31 ) )
		return false;

	// 2�����29��
	if( ( month == 2) && ( day >=30 ) )
		return false;

	if(!((1<=hour)&&( 24 >= hour )&& ( 60 >= minute ) && ( 1 <= minute )&& ( 60 >= second ) && ( 1 <= second )))
		return false;


	return true;
}

// ��֤�Ƿ���� add by �ű̾� (2003-10-30)
function isMoney1( strValue )
{
	// ���ұ����� -12,345,678.9 �ȸ�ʽ ����Ϊ��
	if( isEmpty( strValue ) ) return true;

	return checkExp( /^[+-]?\d{1,6}\.\d{2}$/g, strValue );
}

// ��֤�Ƿ� Email
function isEmail( strValue )
{
	// Email ������ x@a.b.c.d �ȸ�ʽ ����Ϊ��
	if( isEmpty( strValue ) ) return true;

	//return checkExp( /^\w+@(\w+\.)+\w+$/gi, strValue );	//2001.12.24���Գ��� ��� jxj-xxx@yysoft.comʱ����ͨ��
	//Modify By Tianjincat 2001.12.24
	var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	return checkExp( pattern, strValue );

}

// ��֤�Ƿ� Ϊ��
function isEmpty( strValue )
{
	if( strValue == "" )
		return true;
	else
		return false;
}

// ��֤�Ƿ� ����
function isNumeric( strValue )
{
	// ���ֱ����� 0123456789 ����Ϊ��

	//return checkExp( /^\d*$/g, strValue );
	return !isNaN(strValue);

	//return  checkExp(/\ \^(-|\+)?\d+(\.\d+)?$/g, strValue );
}

// ��֤�Ƿ� ����
function isInt( strValue )
{
	// ���ֱ����� 0123456789 ����Ϊ��

	return checkExp( /^\d*$/g, strValue );

	//return  checkExp(/\ \^(-|\+)?\d+(\.\d+)?$/g, strValue );
}


// ��֤�Ƿ� ����
function isMoney( strValue )
{
	// ���ұ����� -12,345,678.9 �ȸ�ʽ ����Ϊ��
	if( isEmpty( strValue ) ) return true;

	return checkExp( /^[+-]?\d+(,\d{3})*(\.\d+)?$/g, strValue );
}

// ��֤�Ƿ� �绰
function isPhone( strValue )
{
	// ��ͨ�绰	(0755)4477377-3301/(86755)6645798-665
	// Call ��	95952-351
	// �ֻ�		130/131/135/136/137/138/13912345678
	// ����Ϊ��
	if( isEmpty( strValue ) ) return true;

	return checkExp( /(^\(\d{3,5}\)\d{6,8}(-\d{2,8})?$)|(^\d+-\d+$)|(^(130|131|135|136|137|138|139)\d{8}$)/g, strValue );
}

// ��֤�Ƿ� ��������
function isPostalCode( strValue )
{
	// �������������6λ����
	return checkExp( /(^$)|(^\d{6}$)/gi, strValue )
}


// ��֤�Ƿ� URL
function isURL( strValue )
{
	// http://www.yysoft.com/ssj/default.asp?Type=1&ArticleID=789
	if( isEmpty( strValue ) ) return true;

	var pattern = /^(http|https|ftp):\/\/(\w+\.)+[a-z]{2,3}(\/\w+)*(\/\w+\.\w+)*(\?\w+=\w*(&\w+=\w*)*)*/gi;
	// var pattern = /^(http|https|ftp):(\/\/|\\\\)(\w+\.)+(net|com|cn|org|cc|tv|[0-9]{1,3})((\/|\\)[~]?(\w+(\.|\,)?\w\/)*([?]\w+[=])*\w+(\&\w+[=]\w+)*)*$/gi;
	// var pattern = ((http|https|ftp):(\/\/|\\\\)((\w)+[.]){1,}(net|com|cn|org|cc|tv|[0-9]{1,3})(((\/[\~]*|\\[\~]*)(\w)+)|[.](\w)+)*(((([?](\w)+){1}[=]*))*((\w)+){1}([\&](\w)+[\=](\w)+)*)*)/gi;

	return checkExp( pattern, strValue );

}

// ����ֶγ���
//
//	strValue	�ַ���
//	strParam	�����������磺L<10, L=5, L>117
//
function checkLength( strValue, strParam )
{
	if( isEmpty( strValue ) )	return true;

	// �������磺L<10, L=5, L>117
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


// ����������ݳ��ȵĺϷ��ԣ��ַ����Ȳ��ܴ���**���ַ���
//
//	�������
//		strName		�ֶζ���
//		strDescription	�ֶ�����
//		strLength	�ֶγ���
//
function ValidateMaxLength( strName, strDescription, strLength) {
	var strMsg = "";
	var strValue = document.all( strName ).value.trim();
	var strMaxLength = "L<" + strLength;
	if( !checkLength( strValue, strMaxLength ))
	strMsg = '"' + strDescription + '" ����С��'+ strLength + '���ַ�\n';
	return strMsg;
}

// ����������ݳ��ȵĺϷ��ԣ��ַ����Ȳ���С��**���ַ���
//
//	�������
//		strName		�ֶζ���
//		strDescription	�ֶ�����
//		strLength	�ֶγ���
//
function ValidateMinLength( strName, strDescription, strLength) {
	var strMsg = "";
	var strValue = document.all( strName ).value.trim();
	var strMaxLength = "L>" + strLength;
	if( !checkLength( strValue, strMaxLength ))
	strMsg = '"' + strDescription + '" �������'+ strLength + '���ַ�\n';
	return strMsg;
}

// ����������ݳ��ȵĺϷ��ԣ��ַ����ȵ���**���ַ���
//
//	�������
//		strName		�ֶζ���
//		strDescription	�ֶ�����
//		strLength	�ֶγ���
//
function ValidateEquLength( strName, strDescription, strLength) {
	var strMsg = "";
	var strValue = document.all( strName ).value.trim();
	var strMaxLength = "L=" + strLength;
	if( !checkLength( strValue, strMaxLength ))
	strMsg = '"' + strDescription + '" �������'+ strLength + '���ַ�\n';
	return strMsg;
}

//
//	�������
//		obj		�ֶζ���
//		strType	�ֶ�����
//
function ValidateEl( obj, strType)
{
	var strMsg ="";
	var result=true;
	var strValue = obj.value.trim();

	switch( strType.toLowerCase() )
	{
		case "alphanumeric" :	// ��ĸ����
			result=isAlphaNumeric( strValue ) ;
			break;

		case "date" :	// ����
			result=isDate( strValue );
			break;

		case "yearmonth" :	// ����
			result=isYearMonth(strValue);
			break;

		case "time":    //���ڼ�ʱ���Ҳ�����Ϊ��
			result=isTime(strValue);
			break;

		case "time1":    //���ڼ�ʱ���ҿ���Ϊ��
			result=isTime1(strValue);
			break;

		case "timeonly":  //ʱ��
			result=	isTimeOnly(strValue);
			break;

		case "email" :	// �����ʼ�
			result=isEmail(strValue);
			break;

		case "notempty" :	// �����ֵ
			result=!isEmpty( strValue );
			break;
		case "numeric" :	//����
			result=isNumeric( strValue );

			break;
		case "int" :	//����
			result=isInt( strValue );

			break;
		case "money" :	//����
			result=isMoney( strValue );
			break;

		case "money1" :	//����
			result=isMoney1( strValue );
			break;

		case "phone" :	// �绰
			result=isPhone( strValue );
			break;

		case "postalcode" :	// ��������
			result=isPostalCode( strValue );
			break;

		case "url" :	// URL
			result=isURL( strValue );
			break;
	}

	return result;
}

/**********************
*�����ı���ֻ������ĳ����ֵ
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
				alert("ճ���ı�����ת�����������ı���Ҫ����������.");
				event.returnValue=false;
			}
			break;
		case "float":
			if(!isNumeric(pasteData)){
				alert("ճ���ı�����ת�����֣����ı���Ҫ����������.");
				event.returnValue=false;
			}
		case "float1":
			if(!isNumeric(pasteData)){
				alert("ճ���ı�����ת�����֣����ı���Ҫ����������.");
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
				alert("���ı���Ҫ����������.");

				this.value=(this.oldValue==null?"":this.oldValue);
				return false;
			}
			break;
		case "float":
			if(!isNumeric(this.value)){
				alert("���ı���Ҫ����������.");
				this.value=(this.oldValue==null?"":this.oldValue);
				return false;
			}
		case "float1":
			if(!isNumeric(this.value)){
				alert("���ı���Ҫ����������.");
				this.value=(this.oldValue==null?"":this.oldValue);
				return false;
			}
			break;
	}

}

//�ı����ý���ʱ���и÷�����onfocus="checkTextBoxInput()"��
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

		/****������뷨����*/
		//�������뷨
		if(txtBox.onlytype.toLowerCase()=="float" || txtBox.onlytype.toLowerCase()=="int" || txtBox.onlytype.toLowerCase()=="float1"){
			txtBox.style.imeMode="disabled";
		}
		/****������뷨����*/

		txtBox.onkeydown=setBoxInputType;
		try{txtBox.onpaste=checkPasteData;}catch(e){}
		txtBox.onblur=checkInputValue;


	}else{
		return false;
	}

}

//////////////////////////////////////////////


/**********************
*�����ı���Ϊ��������
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


// �������ڵ�ĳ����(��,��,��),blFlag��ֵΪ0:��,1:��,2:�� ,  add by lbl
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
�����ı������󳤶�
�ı����ý���ʱ���и÷�����onfocus="setMaxLen()"��
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
	if(txtObj.maxlength!=null){			//���ȴ���
		txtObj.lenValue=txtObj.maxlength;

	}
	if(txtObj.lenValue!=null){
		if(!isInt(txtObj.lenValue)){
			alert("maxlength��������Ϊ����");
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
		alert("ճ�����������������ı������趨����󳤶�");
		return false;
	}
}
function checkTextAreaValueLen(){
	if(this.value.length>this.lenValue){
		alert("�ı������ݳ��ȳ������趨����󳤶�");
		this.value=this.value.substring(0,this.lenValue);
		return false;
	}
}
//�趨�ı�����󳤶Ƚ���


