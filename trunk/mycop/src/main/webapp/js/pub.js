<!--
/**
 * <h2 align="center">JavaScript ͨ�ú���</h2>
 * ͨ�ú�����
 * ע�⣺common.js�ļ������ᱻ���ļ���ȡ�������ԣ�
 * Ӧ������ʹ���ñ��ļ������ṩ�ĺ�����
 *
 */






/********************************************************************************
 * constant.
 * For debuging, this value should be set <b>false</b> when issuing the product.
 */
var DEBUG = true;




/********************************************************************************
 * ���ĳ���ؼ���ֵ�Ƿ�Ϊ�����ԡ�������ǣ�����һ��������Ϣ�������������ڸÿؼ��ϡ�
 * @param o �ؼ�����
 * �ÿؼ�������һ��INPUTԪ�ء�
 * @author Rick
 * @date 2001.11.8
 */
function checknum(o) {
	if (o.value == "") return true;
	if (isNaN(o.value)) {
		alert("һ��ҪΪ����");
		o.focus();
		return false;
	}
	return true;
}
/**
 * �жϲ����Ƿ�Ϊ���֡�
 * @return true ��������֣�����Ϊfalse
 */
function isnum(v) {
	return /^\s*-?\s*((\d+\.?\d*)|(\d*\.\d+)|\.)\s*$/g.test(v);
}
/**
 * �жϲ����Ƿ������ڡ�
 * @return true ��������ڣ�������ڼ��ݣ�����false.
 * ע�⣺�ú���ֻ������ڵĸ�ʽ�Ƿ����SQL��׼�����ڸ�ʽ�����磺yyyy-MM-dd hh:mm:ss
 * ����ֻ�����ڵķ�Χ����Ч�������޵ļ�顣
 */
function isdate(v) {
	return /([1-2]\d{3})-((0?[1-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1]))$/g.test(v);
}
/**
 * ���ĳ�������Ƿ�δ���塣
 * ��Ϊ IE5.0 �����µ��������֧������Ķ���"undefined".
 * ���ô˷�����
 *
 * @author Rick
 * @2001.11.2
 */
function isUndef(v) {
	return typeof(v) == "undefined";
}


/*******************************************************************************
 * �û����溯��user interface control functions, especially for the data table.
 * <ol>
 * <li> getSelectedRowIndices
 * <li> getSelectedRowContents
 * <li> getSelectedRowRefs
 * </ol>
 *
 * @author Rick
 * @date 2001.11.3
 */
function getSelectedRowIndices() {
	var rowObjects = getAllRowRefs();
	var rows = [];
	var i = rows.length;
	
	for (var row = 0; row < rowObjects.length; row++) {
		var o = rowObjects[row];
		if (o.length == 0 || o.id == "header") {
			continue;
		}
		if (o.className == 'selected') {
			rows[i++] = row;
		}
	}
	return rows;
}
/**
 * ȡ��Table�����б�ѡ�е����ݡ�
 * @return [][]
 * ����һ����ά���顣������ĵ�һ���±�ͱ�ѡ���У��ڶ����±�ͱ�ѡ���ж�Ӧ��
 */
function getSelectedRowContents() {
	var rowObjects = getAllRowRefs();
	var rows = new Array(0);
	var i = -1;
	
	for (var row = 0; row < rowObjects.length; row++) {
		var o = rowObjects[row];
		if (o.length == 0 || o.id == "header") {
			continue;
		}
		if (o.className == 'selected') {
			var colObjects = o.childNodes;
			rows[++i] = new Array(colObjects.length);
			for (var col = 0; col < rows[i].length; col++) {
				rows[i][col] = colObjects[col].innerText;
			}
		}
	}
	return rows;
}
/**
 * ���ر�ѡ�е����á�
 * @return []
 * �ж������顣
 */
function getSelectedRowRefs() {
	var rows = getAllRowRefs();
	var a = [];
	
	for (var row = 0; row < rows.length; row++) {
		var o = rows[row];
		if (o.length == 0 || o.id == "header") {
			continue;
		}
		if (o.className == 'selected') {
			a[a.length] = o;
		}
	}
	return a;
}
/**
 * ȡ�������е����á�
 * @return []
 * �ж������顣
 */
function getAllRowRefs() {
	var t = window["DataTable"];
	if (!t) return [];

	while (t.tagName != "TR") t = t.firstChild;
	var rows = t.parentElement.childNodes; // window.DataTable.firstChild.childNodes;
	return rows;
}

/**
 * �ı��е�style. ע�⣬�ú�����һЩ�ٶ��ĳ�����
 * header  �����е�id
 * selected ��ѡ�е�style.
 * listTableData �Ǳ�ѡ�еĵ�style.
 *
 * Copy from changeto() in common.js
 * @param hStyle style.
 * @see common.js#changeto
 */
function changeto(hStyle){
	eSrcObject=event.srcElement;
	if (eSrcObject.tagName=="TABLE")  return;

	while(eSrcObject.tagName!="TR") eSrcObject=eSrcObject.parentElement;
	if (eSrcObject.tagName == "CAPTION") return;
	if(eSrcObject.id!='header') {
	    if(eSrcObject.className=='selected') {}
	    else eSrcObject.className='listTableData';
	} else if(eSrcObject.contains(event.srcElement)) event.srcElement.className=hStyle
}
/**
 * ��һ��style���ı��˵��и�ԭ��ע�⣬�ú�����һЩ�ٶ��ĳ�����
 * header  �����е�id
 * selected ��ѡ�е�style.
 *
 * Copy from changeback() in common.js
 * @see common.js#changeback
 * @date 2001-11-7
 */
function changeback(){
	eSrcObject=event.srcElement;
	if (eSrcObject.tagName=="TABLE")  return;

   if(event.fromElement.contains(event.toElement)||eSrcObject.contains(event.toElement)) return;
   while(eSrcObject.tagName!="TR") eSrcObject=eSrcObject.parentElement;
   if (eSrcObject.tagName == "CAPTION") return;
   if(eSrcObject.id!='header') {
       if(eSrcObject.className=='selected') {}
       else eSrcObject.className='';
   }
   else event.srcElement.className='listTableHead'
}

/**
 * �õ���ǰ��ѡ�е����á�
 * @author Rick
 * @date 2001-11-7
 */
function currentRow() {
	if (!event) return;
	var src = event.srcElement;
	if (!src) return;
	if (src.tagName == "TABLE") return;
	
	if (event.fromElement && event.fromElement.contains(event.toElement) 
		|| src.contains(event.toElement)) return;
	while (src && src.tagName != "TR") src = src.parentElement;
	if (!src) return;
	if (src.id == "header") return;
	return src;
}


// ��Ӧ����onClick�¼�,ѡ��ĳ����¼
function selectrow()
	{
try
		{
   var DataTable=window["DataTable"];
   trObject=DataTable.firstChild.childNodes;
   var fTitle=null;
   var fTitle2=null;
   for (i=0;i<trObject.length;i++)
     {
     if(trObject[i].className=='selected')
       {
       trObject[i].className='noselected';
       } 
     } 
   eSrcObject=event.srcElement
   if (eSrcObject.tagName=="TABLE")  return
    while(eSrcObject.tagName!="TR")
        eSrcObject=eSrcObject.parentElement
   if(eSrcObject.id=='header')    return
   if(eSrcObject.parentNode.parentNode.id !='DataTable') return
   if(eSrcObject.className!='selected')
         eSrcObject.className='selected'
   else eSrcObject.className='noselected'
		}
	catch(Exception)
		{
		}
}
/**
 * ��ѡһ�С�
 * @author Rick
 * @date 2001-11-7
 * 
 * @see common.js#selectRow
 */
function singleSelect() {
   var src=event.srcElement
   if (src.tagName=="TABLE") return   
    while(src.tagName!="TR")
        src=src.parentElement
   if(src.id =='header') return  
   //if(src.parentNode.parentNode.id !='DataTable') return	
   if(src.className!='selected') {
	//first deselect all of selected rows.
	var selectedRows = getSelectedRowRefs();
	for (var i = 0; i < selectedRows.length; i++) {
		selectedRows[i].className = 'noselected';
	}
        src.className='selected'
   }
   else src.className='noselected'
}


/**
 * �����е�����Ԫ�ش�һ��form����������һ��form.
 * ���д�һ��ҳ���ռ��û����ݣ�Ȼ��������һ��ҳ�潫�����ύ��������������ʱ���øú�����
 *
 * @param src Դ����
 * @param target Ŀ����� 
 *
 * @author Rick
 * @date 2001-11-10
 */
function form2form(src, target) {
	//checking routines
	if (!src || !target) return false;
	
	for (var i = 0; i < src.elements.length; i++) {
		var t = src.elements[i];
		if (t.tagName != "BUTTON" 
			&& t.tagName != "SUBMIT"
			&& t.tagName != "RESET"
			&& t.name != ""
			&& t.value != ""
			&& target[t.name]) {
			target[t.name].value = t.value;
		}		
	}
	var fhtml = target.innerHTML;
	for (var i = 0; i < src.elements.length; i++) {
		var t = src.elements[i];
		if (t.tagName != "BUTTON" 
			&& t.tagName != "SUBMIT"
			&& t.tagName != "RESET"
			&& t.name != ""
			&& t.value != ""
			&& !target[t.name]) {
			fhtml += '<input type="hidden" name="'
				+ t.name + '" value="'
				+ t.value + '">';
		}
	}
	target.innerHTML = fhtml;
}


/**
 * ��ӡ������
 * ���û�з���ָ���ı����᷵�ء�
 * @param tabName ������ơ����δָ������ٶ�ΪDataTable.
 */
function prnt(tabName) {
	tabName = tabName || "DataTable";
	if (!window[tabName]) return;
	var content = window["DataTable"].outerHTML;
	pwin = window.open("about:blank", "_blank");
	var re = /onclick|ondblclick|onmouseout|onmouseover|onmouseup|onmousedown|onmousemove/gi;
	content = content.replace(re,"_js");	
	pwin.document.write("<html><head>");
	pwin.document.write('<META http-equiv="Content-Style-Type" content="text/css">');
	pwin.document.write('<LINK rel="stylesheet" href="/accbs/theme/common.css" type="text/css">');
	pwin.document.write('</head>');
	pwin.document.write('<body>');
	pwin.document.write('<h1 align="center">' + document.title + '</h1>');
	pwin.document.write('<div align="center">');
	pwin.document.write(content);
	pwin.document.write("</div>");
	pwin.document.write("</body></html>");
	pwin.document.close();
	pwin.print();
}
//6828263






/*
 * @(#)use from Types.java 1.22 00/12/06
 *
 * Copyright 1996-2000 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 *
 * @see java.sql.Types
 * @changed-by Rick
 * @date 2001-7-7 
 */

function Types() {
	this.BIT 		=  -7;
	this.TINYINT 	=  -6;
	this.SMALLINT	=   5;
	this.INTEGER 	=   4;
	this.BIGINT 		=  -5;
	this.FLOAT 		=   6; 
	this.REAL 		=   7;
	this.DOUBLE 		=   8;
	this.NUMERIC 	=   2;
	this.DECIMAL		=   3;
	this.CHAR		=   1;
	this.VARCHAR 	=  12;
	this.LONGVARCHAR 	=  -1;
	this.DATE 		=  91;
	this.TIME 		=  92;
	this.TIMESTAMP 	=  93;
	this.BINARY		=  -2;
	this.VARBINARY 	=  -3;
	this.LONGVARBINARY 	=  -4;
	this.NULL		=   0;
	this.OTHER		= 1111;
	this.JAVA_OBJECT         = 2000;
	this.DISTINCT            = 2001;
	this.STRUCT              = 2002;
	this.ARRAY               = 2003;
	this.BLOB                = 2004;
	this.CLOB                = 2005;
	this.REF                 = 2006;
	this.DATALINK = 70;
	this.BOOLEAN = 16;
	return this;
}
var __TYPES__ = new Types();
/**
 * ����ĳ�������Ƿ���ַ������ݡ�
 * @param type ����
 * @return boolean ����ǣ�����true, ����Ϊfalse.
 * @see #Types
 */
function is_string_compliant(type){
	switch(type) {
	case __TYPES__.CHAR:
	case __TYPES__.VARCHAR:
	case __TYPES__.LONGVARCHAR:
	case __TYPES__.DATE:
	case __TYPES__.TIME:
	case __TYPES__.TIMESTAMP:
	case __TYPES__.OTHER:
	case __TYPES__.CLOB:
		return true;
	default:
		return false;
	}
}
/**
 * �ж�ĳ�������Ƿ�Ϊ���ڻ�ʱ�����͡�
 * @param type ���ͳ�����
 * @return boolean ����ǣ�����true, ����Ϊfalse.
 * @see #Types
 */
function is_date(type) {
	return type == __TYPES__.DATE;
}
/**
 * �ж�ĳ�������Ƿ���ַ������ݡ�
 * @param type ���ͳ�����
 * @return boolean ����ǣ�����true, ����Ϊfalse.
 * @see #is_string_compliant(type)
 * @see #Types
 */
function like_string(type) {
	return is_string_compliant(type);
}
/**
 * �ж������Ƿ�ΪSQL null.
 * @param type ���ͳ�����
 * @return ����ǣ�����true, ����Ϊfalse.
 * @see #Types
 */
function is_null(type) {
	return type == __TYPES__.NULL;
}
/**
 * �ж������Ƿ�Ϊ�����͡�
 * @param type ���ͳ�����
 * @return ����ǣ�����true, ����Ϊfalse.
 * @see #Types
 */
function is_numeric(type) {
	switch(type) {
	case __TYPES__.BIT:
	case __TYPES__.TINYINT:
	case __TYPES__.SMALLINT:
	case __TYPES__.INTEGER:
	case __TYPES__.BIGINT:
	case __TYPES__.FLOAT:
	case __TYPES__.REAL:
	case __TYPES__.DOUBLE:
	case __TYPES__.NUMERIC:
	case __TYPES__.DECIMAL:		
		return true;
	default:
		return false;
	}
}





/**
 * ��һ���´����д�URL.
 *
 * @param target 
 * 	���򿪵����·�������·����
 * @param name
 *	�´�������ơ�
 * @param w
 * 	�´���Ŀ�
 * @param h
 * 	�´���ĸߡ�
 * @param set
 * 	�������á����Ƿ���ʾ�������ȡ�
 * @author Rick
 * @date 2001-11-1
 */
function show(target, name, w, h, set) {
	w = w || 600;
	h = h || 400;
	name = name || "cwin";

	var Left=(screen.width)?(screen.width-w)/2:100;
	var Top=(screen.height)?(screen.height-h)/2-50:100;
	settings=
		'width=' + w +
		',height=' + h + 
		',left=' + Left + 
		',top=' + Top
	if (set) {
		settings += "," + set
	}
	return window.open(target,name,settings);
}

/**
 * ��������ؼ���
 * ��Ӧ�ı����onClick�¼�,�Զ���������
 * Copy from getdate() in common.js
 * @see common.js#getdate
 */
//**************************************************************
var obj;
function getParams(){
    return obj;
}
function getdate(objName,path,event){
  obj = objName;
  //****************************************************
  var ns=navigator.appName=="Netscape";
  windowCharacter="dialogLeft:"+event.screenX+"px;dialogTop:"+event.screenY+"px;dialogWidth:179px;dialogHeight:155px;status:no;"; 
  //**************************************************************
  if(ns){
      window.open(path+"/getdate.html","noname","height=155px, width=179px, top="+event.screenY+", left="+event.screenX+", toolbar=no, menubar=no, scrollbars=no,resizable=no,location=no, status=no");
  } else {
      tretDateValue=window.showModalDialog(path+"/getdate.html","noname",windowCharacter);
      getdateLast(objName,tretDateValue);
  }
  //**************************************************************
}
function getdateLast(objName,tretDateValue){
    if(tretDateValue) objName.value=tretDateValue
}

 /**
 * ��������ؼ���
 * ��Ӧ�ı����onClick�¼�,�Զ�����
 * Copy from getdate() in pub.js
 * @see pub.js#getdate
 */
function getDepartment(objName,path,value){
  windowCharacter="dialogLeft:"+event.screenX+"px;dialogTop:"+event.screenY+"px;dialogWidth:179px;dialogHeight:255px;status:no;"; 
  tretDateValue=window.showModalDialog(path+'&value='+value,"noname",windowCharacter);
  if(tretDateValue)
  objName.value=tretDateValue
}

/**
 * �¿�һ�����壬ģʽ��ʾһ��URL.
 * @param target Ҫ��ʾ�ľ��Ի����·����
 * @param name ģʽ��������ơ�
 * @param w ����Ŀ�ȡ�
 * @param h ����ĸ߶ȡ�
 */
function showModal(target, name, w, h, set) {
	w = w || 600;
	h = h || 400;
	name = name || "noname";

	var left=(screen.width)?(screen.width-w)/2:100;
	var top=(screen.height)?(screen.height-h)/2-50:100;

	var settings="dialogLeft:"
		+ left
		+ "px;dialogTop:"
		+ top
		+ "px;dialogWidth:" + w 
		+ "px;dialogHeight:" + h 
		+ "px;status:no;"; 
	return window.showModalDialog(target, name, settings);
}




/**********************************************************************************
 * �й�Cookie��һЩ������
 * @ahthor Rick
 * @date 2001-11-10
 */

/**
 * ����һ��Cookie.
 * @param name Cookie�����ơ�
 * @param value Cookie��ֵ��
 */
function setCookie(name, value) {
	document.cookie = name + "=" + escape(value)
}

/**
 * ȡ��Cookie��ֵ��
 * @param name Cookie���ơ�
 * @return value Cookie��ֵ��
 */
function getCookieValue(name) {
   var label = name + "=";
   var labelLen = label.length;
   var mycookie = document.cookie;
   var cLen = mycookie.length
   var i = 0
   while (i < cLen) {
      var j = i + labelLen
      if (mycookie.substring(i,j) == label) {
         var cEnd = mycookie.indexOf(";",j)
         if (cEnd == -1) {
            cEnd = mycookie.length
         }
         return unescape(mycookie.substring(j,cEnd))
      }
      i++
   }
   return ""
}


/*******************************************************************************************
 * ��ҳ������js �汾��<br>
 * @param rs ��ά���飬����������ݿ��е����ݶ�Ӧ��
 * @author Rick
 * @date 2001-11-10
 *
 */
function Book(rs) {
	//old values
	var DEFAULT_PAGE_SIZE = 9;
	var oPageSize = 0;
	var pageSize = DEFAULT_PAGE_SIZE;
	var oDir = 1;
	var oLoc = 0;

	var range = [];

	this.setPageSize = function(size) {
		pageSize = size;
	}
	/**
	 * ȡ��rs.
	 * @return rs ���ع����ö���Ķ�ά���顣
	 */
	this.getRs = function() {
		return rs;
	}
	/**
	 * �жϸö���Ķ�ά�����Ƿ���Ч��������δ��ʼ�������Ч�Ķ�ά����ʱ����Ч��
	 * @return boolean, ��Ϊtrue������Ϊfalse. 
	 */
	this.isValidRs = function() {
		return rs && rs.length;
	}
	/**
	 * ��ǰҳ��״̬��
	 * @return [] ����һ������Ϊ4�����顣
	 * @see #turnPage.
	 */
	this.getStatus = function() {
		return range;
	}
	
	/**
	 * ��ҳ��
	 * @param dir ����ȡ����ֵ��<br>
	 * <ul>
	 * <li>-2 ��ҳ��
	 * <li>-1 ǰһҳ��
	 * <li>0 ǿ��ˢ�¡�
	 * <li>1 ��һҳ��
	 * <li>2 βҳ��
	 * </ul>
	 * @param pageSize ҳ���С����ûҳ��ʾ��������
	 * @return [] һ������Ϊ4��һά���顣���У�<br>
	 * [0] ��ʼ�С�<br>
	 * [1] ��ֹ�С�<br>
	 * [2] ��ǰҳ�š�<br>
	 * [3] ��ҳ����<br>
	 */
	this.turnPage = function(dir, size) {
		if (!this.isValidRs()) return range;

		if (size > 0) pageSize = size;
		var start = oLoc;
		var end = rs.length - 1;	

		switch (dir) {
		case 1:	
			if (oLoc == rs.length) {
				alert("�Ѿ������һҳ�ˡ�");
				return range;
			}
			start = oLoc;
			end = start + pageSize;
			break;
		case -1:
			if (range && range[0] == 0) {
				alert("�Ѿ�����һҳ�ˡ�");
				return range;
			}
			end = oLoc - oPageSize;
			start = end - pageSize;
			break;
		case 0: //? maybe should use location.reload()
			start = oLoc - oPageSize;
			end = oLoc;
			break;
		case -2:
			start = 0;
			end = pageSize;
			break;
		case 2:
			end = rs.length;
			start = end - pageSize;
			break;
		default: //go to the first page.
			start = 0;
			end = start + pageSize;
			break;
		}
	
		//check
		if (start > rs.length) start = rs.length - pageSize;
		if (start < 0) start = 0;

		if (end > rs.length) end = rs.length;
		if (end < 0) end = 0;

		//save status		
		oLoc = end;
		oPageSize = end - start; //actual size

		var pageCount = pageSize <= 0 ? 0 : rs.length / pageSize;
		var pageNum = pageSize <= 0 ? 0 : end / pageSize;

		range[0] = start;
		range[1] = end;
		range[2] = pageNum;
		range[3] = pageCount;
	
		return range;
	}
	return this;
}
/**
 * ָ����ʾ�к��еķ�Χ����һ����ά������ʾ��
 * @param rs 
 */
function writeRs(rs, range, columnIndices) {
	if (!range) {
		range = new Array(2);
		range[0] = 0;
		range[1] = rs.length;
	}
	if (!columnIndices) {
		columnIndices = [];
		for (var i = 0; i < rs[0].length; i++) {
			columnIndices[i] = i;
		}
	}
	for (var row = range[0]; row < range[1]; row++) {
		document.write("<tr>");
		for (var col = 0; col < columnIndices.length; col++) {
			document.write("<td>" + rs[row][columnIndices[col]] + "</td>");
		}
		document.write("</tr>");
	}
}
/**
 * ��һ�������(��ά����)��ָ���а�������Χ���
 */
function toHTML(rs, range, columnIndices) {
	if (!rs || !rs.length) return "";

	if (!range) {
		range = new Array(2);
		range[0] = 0;
		range[1] = rs.length;
	}
	if (!columnIndices) {
		columnIndices = [];
		for (var i = 0; i < rs[0].length; i++) {
			columnIndices[i] = i;
		}
	}
	var s = "";
	for (var row = range[0]; row < range[1]; row++) {
		s += "<tr>";
		for (var col = 0; col < columnIndices.length; col++) {
			s += "<td>" + rs[row][columnIndices[col]] + "</td>";
		}
		s += "</tr>";
	}
	return s;
}

/*************************************************************************************
 * ���ݽṹ
 * 
 * @author Rick
 * @date 2001-11-16
 *
 */

/**
 * ������
 * @version 2.0
 */
function Collection() {
	var objs = [];

	/**
	 * ����һ������
	 */	
	this.add = function(o) {
		objs[objs.length] = o;
	};
	/**
	 * ����һ�������е�����Ԫ��
	 */
	this.addAll = function(o) {
		for (var i = 0; i < o.length; i++) {
			objs[objs.length] = o;
		}
	};
/*
	//Removed. This method depends on the realization of the concrete element object 
	this.get = function(prop, value) {
		var iter = this.iterator();
		while(iter.hasNext()) {
			var t = iter.next();
			if (!t) continue;			
			if (t[prop] == value) {
				return t;
			}
		}
		//if can not be found, try again using default property "name"	
	};
*/
	/**
	 * ����Ԫ�صĵ�����ʽ
	 */
	this.iterator = function() {
		return new Iterator(objs);
	};
	/**
	 * ����Ԫ�ص�������ʽ
	 */
	this.toArray = function() {
		return objs;
	};
	/**
	 * �����Ƿ�Ϊ��
	 */
	this.isEmpty = function() {
		return objs.length == 0;
	};
	/**
	 * ���ϴ�С
	 */
	this.size = function() {
		return objs.length;
	};
	/**
	 * �Ƿ����ĳ��Ԫ�أ���������ʵ��equals(o)�ķ���
	 * @param o 
	 */
	this.contains = function(o) {
		var iter = this.iterator();
		while(iter.hasNext()) {
			var t = iter.next();
			if (t == o) return true;
			return typeof t.equals == "function" && t.equals(o);
		}
	};
	return this;
}


/**
 * ������
 * @param objs ��������
 */
function Iterator(objs) {
	var index = 0;
	this.hasNext = function() {
		return objs && index < objs.length;
	};	
	this.next = function() {
		return objs[index++];
	};
	return this;
}

/**
 * 
 */
function __Element__(o) {
	this.value = o || null;
	this.next = null;
	this.previous = null;
	this.isEmpty = function() {
		return this.value == null || typeof this.value == "undefined";
	}
	this.reset = function() {
		this.value = null;
		this.previous = null;
		this.next = null;
	}
}

/**
 * �����࣬�ü���ʵ����ɾ��Ԫ�صĹ���
 */
function Collection2(capacity) {
	var MIN_CAPACITY = 2;
	var DEFAULT_CAPACITY = 10;
	if (isNaN(capacity)) capacity = DEFAULT_CAPACITY;

	var size = 0;
	var entry = null;

	var header = null;
	var tail = null;
	this.__checkLink__ = function() {
		if (!DEBUG) return;
		//3 pointer
		if (header ^ tail) throw "header is not consistent with tail";
		if (header) {
			this.__doCheck__(header, "header");
			this.__doCheck__(tail, "tail");
		}
		this.__doCheck__(entry, "entry");
	}
	this.__doCheck__ = function(t, name) {
		for (var p = t, i = 0; i < capacity; i++) {
			p = p.next;
		}
		if (p != t) throw "There are something wrong with " + name + ".next."
		for (var p = t, i = 0; i < capacity; i++) {
			p = p.previous;
		}
		if (p != t) throw "There are something wrong with " + name + ".previous."
	}

	this.__init__ = function() {
		var s = entry = new __Element__();
		for (var i = 1; i < capacity; i++) {
			var t = new __Element__();
			t.previous = entry;
			entry.next = t;
			entry = t;
		}
		s.previous = entry;
		entry.next = s;
		this.__checkLink__();
	}
	this.__init__();
	this.size = function() {
		return size;
	}

	/**
	 * ����������С
	 */
	this.capacity = function() {
		return capacity;
	}

	/**
	 * ȷ��������������һ�������Ĵ�С֮��
	 * @param minCapacity ��С������
	 */
	this.ensureCapacity = function(minCapacity) {
		if (capacity < minCapacity) {
			var newCapacity = Math.round(minCapacity + capacity / 2);
			var s = new __Element__();
			for (var i = capacity + 1, t = s; i < newCapacity; i++) {
				var t2 = new __Element__();
				t2.previous = t;
				t.next = t2;
				t = t2;
			}
			this.__insert__(s, t);
			capacity = newCapacity;
			this.__checkLink__();
		}
	}
	this.__insert__ = function(p1, p2) {
		if (p2)
			this.__insert__2(p1, p2);
		else
			this.__insert__1(p1);
	}
	this.__insert__1 = function(n) {
		var p = tail || entry;
		p.next.previous = n;
		n.previous = p;
		n.next = p.next;
		p.next = n;
	}
	this.__insert__2 = function(n, n2) {
		var p = tail || entry;
		p.next.previous = n2;
		n.previous = p;
		n2.next = p.next;
		p.next = n;
	}
	this.__locate__ = function(o) {
		for (var p = header; p;p = p.next) {
				if (p.value == o ||
					(typeof p.value.equals == "function" 
					&& p.value.equals(o))) {
				return p;
			}
			if (p == tail) break;
		} 
		return null;
	}
	/**
	 * ����һ��Ԫ��
	 */
	this.add = function(o) {
		this.ensureCapacity(size + 1);
		var p = tail || entry;
		p.next.value = o;
		tail = p.next;
		if (header == null) {
			header = tail;
		}
		size++;
		this.__checkLink__();
	}
	/**
	 * �Ƿ����ĳ��Ԫ��
	 */
	this.contains = function(o) {
		return this.__locate__(o) != null;
	}
	/**
	 * ѹ������������ʹ֮�뼯���ڵ�Ԫ�ظ���һ�¡�
	 */
	this.trimToSize = function() {
		var e, s;		
		for (e = s = tail || entry; e.next != s && e.next != header; e = e.next) {
			capacity--;
		}

		var p = s.next;
		s.next = e.next;
		e.next.previous = s;

		//ensure the entry is not suspended.
		entry = e.next;

		for (var t = p; p != entry; t = p) {
			p = p.next;
			delete t;
		}
		this.__checkLink__();
	}
	/**
	 * ɾ��һ��Ԫ��
	 */
	this.remove = function(o) {
		var p = this.__locate__(o);
		if (p == null) return false;
		
		p.value = null;
		size--;
		if (p != header && p != tail) {
			p.previous.next = p.next;
			p.next.previous = p.previous;
			this.__insert__(p); //recycle
		this.__checkLink__();
			return true;
		} else if (p == header && p == tail) {
			header = tail = null;
		} else if (p == header) {
			header = header.next;
		} else { //p == tail
			tail = tail.previous;
		}
		this.__checkLink__();
		return true;
	}
	/**
	 * ���������ʽ���ؼ��������е�Ԫ��
	 */
	this.toArray = function() {
		var a = new Array(this.size());
		for (var p = header, i = 0; p != null && !p.isEmpty(); p = p.next) {
			a[i++] = p.value;
			if (p.next == header) break;
		}
		return a;
	}
	/**
	 * ����Ԫ�صĵ�����ʽ
	 */
	this.iterator = function() {
		return new Iterator(this.toArray());
	}
	this.isEmpty = function() {
		return size == 0;
	}
	return this;
}
function __Entry__(k, v) {
	var key = k;
	var value = v;

	this.getKey = function() { return key; }
	this.getValue = function() { return value; }

	this.setKey = function(k) { key = k; }
	this.setValue = function(v) { value = v; }
	return this;
}
function Map() {
	this.__base__ = Collection2;
	this.__base__();

	this.put = function(k, o) {
		this.add(new __Entry__(k, o));
	}
	this.get = function(k) {
		if (isUndef(k) || k == null) return null;
		var iter = this.iterator();
		while (iter.hasNext()) {
			var o = iter.next();
			if (o.getKey() == k) {
				return o.getValue();
			}
		}
		return null;
	}
	this.keySet = function() {
		var keys = new Collection();
		var iter = this.iterator();
		while (iter.hasNext()) {
			var o = iter.next();
			keys.add(o.getKey());
		}
		return keys;
	}
	this.values = function() {
		var vs = new Collection();
		var iter = this.iterator();
		while (iter.hasNext()) {
			var o = iter.next();
			vs.add(o.getValue());
		}
		return vs;
	}
	this.entrySet = function() {
		var entries = new Collection();
		var iter = this.iterator();
		while (iter.hasNext()) {
			var o = iter.next();
			entries.add(o);
		}
		return entries;
	}
	this.remove = function(k) {
		var iter = this.iterator();
		while (iter.hasNext()) {
			var o = iter.next();
			if (o.getKey() == k) {
				this.remove(o);
			}
		}
	}
	this.contains = function(k) {
		return this.get(k)
	}
	return this;
}
/**
 * �ڵ�
 */
function Node(value, parent) {
	this.__base__ = Collection2;
	this.__base__();

	var EQUALS = 0; //equal entity
	var IS_MY_PARENT = 1; //
	var IS_MY_CHILD = 2; 
	var OTHER = 3; //

	parent = parent || null;
	/**
	 * ȡ�ø��ڵ�
	 * @return ���ڵ�
	 */
	this.getParent = function() {
		return parent;
	}
	/**
	 * ���ڵ��ֵ
	 */
	this.getValue = function() {
		return value;
	}
	/**
	 * ���ø��ڵ�
	 */
	this.setParent = function(p) {
		parent = p;
	}
	/**
	 * �Ƿ�Ϊ���ڵ�
	 */
	this.isRoot = function () {
		return parent;
	}
	/**
	 * �Ƿ�ΪҶ
	 */
	this.isLeaf = function () {
		return this.isEmpty();
	}
	/**
	 * ȡ�������ӽڵ�
	 * @return �ӽڵ������
	 */
	this.children = function() {
		return this.toArray();
	}
	/**
	 * ����һ���ӽڵ�
	 */
	this.addChild = function(node) {
		this.add(node);
	}
	/**
	 * ɾ��һ���ӽڵ�
	 */
	this.removeChild = function(node) {
		this.remove(node);
	}
	/**
	 * ���ڵ�ļ��𣬸��ڵ�Ϊ0�������µ�ֱ���ӽڵ�Ϊ1�����ȵȣ��������ơ�
	 *
	 */
	this.level = function() {
		var l = 0;
		var p = parent;
		while (p) {
			l++;
			p = p.getParent();
		}
		return l;
	}
	/**
	 * ��ͼ��һ���ڵ�������е���ϵ��
	 * @param o, �����ýڵ�
	 * @param from, �ýڵ����Դ
	 * @return ����ɹ�������null, ���򷵻�o
	 */
	this.put = function(o, from) {
		var rs = this.compareTo(o);
		switch (rs) {
		case IS_MY_PARENT: //1 may be the parent
			if (parent == null) return o;
			if (from == parent) {
				parent.remove(this);
				parent.addChild(o);
				o.setParent(parent);
				o.addChild(this);
				this.setParent(o);
				return null;
			}
			return parent.put(o, this);
		case IS_MY_CHILD: //2
			if (from && this.contains(from)) {
				if (!parent) {
					parent.remove(from);
					parent.addChild(o);
				}
				o.addChild(from);
				from.setParent(o);
				o.setParent(this);
				return null;
			}
			var c = this.iterator();
			while (c.hasNext()) {
				var t = c.next();
				o = t.put(o, this);
				if (o == null) return null;
			}
			if (o) {
				this.addChild(o);
				o.setParent(this);
			}
			return null;
		case EQUALS: //0
			throw "Attempting to put the same node!";
			break;
		default: //OTHER
			return o;
		}
	}

	//simply delegates this operation to the value object.
	/**
	 * �Ƚ������ڵ��Ĺ�ϵ
	 * @return ��ȣ�0; �Ǳ��ڵ�ĸ��ڵ㣺1; �Ǳ��ڵ���ӽڵ㣺2; ������3
	 */
	this.compareTo = function(o) { 
		if (o == null) return false;
		if (value && typeof value.compareTo == "function") {
			return value.compareTo(o.getValue());
		}
		throw "Object does not implement the CompareTo Method";
	}
	/**
	 * toString
	 */
	this.toString = function() {
		var l = this.level();
		var str = l;
		while (l-- > 0) {
			str += "-"
		}
		str += ":" + value.toString() + "\n";
		
		var iter = this.iterator();
		while (iter.hasNext()) {
			str += iter.next().toString();
		}
		return str;
	}
	return this;
}


/*************************************************************************************/
//utils

/**
 * @for debug
 * print the trace information while debuging.
 * this function depends on a global variable: DEBUG 
 * only if DEBUG = true, this function can work. else if DEBUG = false
 * it do nothing.
 * @param o
 *   the object to debug
 */
function trace() {
	if (!DEBUG || trace.arguments.length == 0) return;
	
	if (trace.arguments.length == 1) {
		alert(trace.arguments[0]);
	} else {
		prompt(trace.arguments[0], trace.arguments[1]);
	}
}


function todo() {
	if (DEBUG)
		alert("�˹����д�ʵ�֡�");
	else
		alert("�˹����ڴ˱����á�");
}

function callFunc() {
	if (!DEBUG) return;
	window.__func_string__ = prompt("Specify a method to call", window.__func_string__ || "");
	new Function(window.__func_string__)();
}



//==================== key event ==================================
//           ʹ���뽹����FORM���ƶ�
// waring! this function will use form elements id. 
// make sure the element id is no use
//----------------- keyDownHandler() ʹ�÷��� ---------------------
//<FORM name=form1 onkeypress="keyDownHandler(this,'save()','ȷ��Ҫ������?')" >
//this             ΪFORM������.
//'save()'         ΪҪ���õĺ��� ע��:Ҫ���ַ���.
//'ȷ��Ҫ������?'  �Ǳ���ǰҪ��ʾ����Ϣ.
//==================== key event ==================================

/**
 * @Author: Tonny
 * @date: 2002-1-7
 * @changed activity:
 *   2002-1-7 Rick -- remove function InitFromItem(tagForm), add function findIndex(frm, o)
 */

/*
//removed
function  InitFromItem(tagForm)
{
   for(var i=0;i<tagForm.length;i++)
   {
         tagForm.elements[i].id=i;
   }
}
*/

function findIndex(frm, o) {
	for (var i = 0; i < frm.elements.length; i++) {
		if (frm.elements[i] == o) {
			return i;
		}
	}
	return i;
}

function  keyDownHandler(tagForm,doFuncStr,sureStr)
{
   var keyMessage;
   var e=window.event;
   evenItem=e.srcElement;
   keycode=e.keyCode;

   if ( ((evenItem.tagName=="INPUT")&&(evenItem.type=="text"))||(evenItem.tagName=="SELECT") )
   {
       if(keycode==13)
       {
          var i= findIndex(tagForm, evenItem) + 1; //parseInt(evenItem.id)+1          
          if(i>=tagForm.elements.length)
          {
                  var sure=confirm(sureStr||'ȷ������������');
                  if(sure)
                  {
                     eval(doFuncStr);
                  }
                  i=0;
          } else {
	          while(!( ((tagForm.elements[i].tagName=="INPUT")
			&& (tagForm.elements[i].type=="text"))
			|| (tagForm.elements[i].tagName=="SELECT")) )
        	    {
	               i++;
        	       if(i>=tagForm.elements.length)
	               {
        	          var sure=confirm(sureStr||'ȷ������������');
	                  if(sure)
        	          {
                	     eval(doFuncStr);
	                  }
        	          i=0;
	               }
        	    }   
		}
            tagForm.elements[i].focus();
            
            keyMessage=tagForm.elements[i].name+" index: "+i;
       }
   }
//   var keycode=e.keyCode;
}

-->