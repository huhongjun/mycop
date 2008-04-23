<!--
/**
 * <h2 align="center">JavaScript 通用函数</h2>
 * 通用函数。
 * 注意：common.js文件将来会被本文件所取代。所以，
 * 应当尽量使用用本文件中所提供的函数。
 *
 */






/********************************************************************************
 * constant.
 * For debuging, this value should be set <b>false</b> when issuing the product.
 */
var DEBUG = true;




/********************************************************************************
 * 检查某个控件的值是否为数字性。如果不是，弹出一个警告消息，并将焦点置于该控件上。
 * @param o 控件对象。
 * 该控件对象是一个INPUT元素。
 * @author Rick
 * @date 2001.11.8
 */
function checknum(o) {
	if (o.value == "") return true;
	if (isNaN(o.value)) {
		alert("一定要为数字");
		o.focus();
		return false;
	}
	return true;
}
/**
 * 判断参数是否为数字。
 * @return true 如果是数字，否则为false
 */
function isnum(v) {
	return /^\s*-?\s*((\d+\.?\d*)|(\d*\.\d+)|\.)\s*$/g.test(v);
}
/**
 * 判断参数是否是日期。
 * @return true 如果是日期，或和日期兼容，否则false.
 * 注意：该函数只检查日期的格式是否符合SQL标准的日期格式。形如：yyyy-MM-dd hh:mm:ss
 * 并且只对日期的范围的有效性做有限的检查。
 */
function isdate(v) {
	return /([1-2]\d{3})-((0?[1-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1]))$/g.test(v);
}
/**
 * 检测某个变量是否未定义。
 * 因为 IE5.0 和以下的浏览器不支持特殊的对象："undefined".
 * 改用此方法。
 *
 * @author Rick
 * @2001.11.2
 */
function isUndef(v) {
	return typeof(v) == "undefined";
}


/*******************************************************************************
 * 用户界面函数user interface control functions, especially for the data table.
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
 * 取得Table中所有被选行的内容。
 * @return [][]
 * 返回一个二维数组。该数组的第一个下标和被选的行，第二个下标和被选的列对应。
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
 * 返回被选行的引用。
 * @return []
 * 行对象数组。
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
 * 取得所有行的引用。
 * @return []
 * 行对象数组。
 */
function getAllRowRefs() {
	var t = window["DataTable"];
	if (!t) return [];

	while (t.tagName != "TR") t = t.firstChild;
	var rows = t.parentElement.childNodes; // window.DataTable.firstChild.childNodes;
	return rows;
}

/**
 * 改变行的style. 注意，该函数有一些假定的常量：
 * header  标题行的id
 * selected 被选行的style.
 * listTableData 非被选行的的style.
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
 * 将一个style被改变了的行复原。注意，该函数有一些假定的常量：
 * header  标题行的id
 * selected 被选行的style.
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
 * 得到当前被选行的引用。
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


// 响应表格的onClick事件,选中某条纪录
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
 * 单选一行。
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
 * 将所有的数据元素从一个form拷贝到另外一个form.
 * 当有从一个页面收集用户数据，然后在另外一个页面将它们提交到服务器的需求时，用该函数。
 *
 * @param src 源表单。
 * @param target 目标表单。 
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
 * 打印函数。
 * 如果没有发现指定的表，将会返回。
 * @param tabName 表的名称。如果未指定，则假定为DataTable.
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
 * 考察某个类型是否和字符串兼容。
 * @param type 类型
 * @return boolean 如果是，返回true, 否则为false.
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
 * 判断某个类型是否为日期或时间类型。
 * @param type 类型常量。
 * @return boolean 如果是，返回true, 否则为false.
 * @see #Types
 */
function is_date(type) {
	return type == __TYPES__.DATE;
}
/**
 * 判断某个类型是否和字符串兼容。
 * @param type 类型常量。
 * @return boolean 如果是，返回true, 否则为false.
 * @see #is_string_compliant(type)
 * @see #Types
 */
function like_string(type) {
	return is_string_compliant(type);
}
/**
 * 判断类型是否为SQL null.
 * @param type 类型常量。
 * @return 如果是，返回true, 否则为false.
 * @see #Types
 */
function is_null(type) {
	return type == __TYPES__.NULL;
}
/**
 * 判断类型是否为数字型。
 * @param type 类型常量。
 * @return 如果是，返回true, 否则为false.
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
 * 在一个新窗体中打开URL.
 *
 * @param target 
 * 	被打开的相对路径或绝对路径。
 * @param name
 *	新窗体的名称。
 * @param w
 * 	新窗体的宽。
 * @param h
 * 	新窗体的高。
 * @param set
 * 	其它设置。如是否显示工具栏等。
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
 * 日期输入控件。
 * 响应文本框的onClick事件,自动填入日期
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
 * 部门输入控件。
 * 响应文本框的onClick事件,自动部门
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
 * 新开一个窗体，模式显示一个URL.
 * @param target 要显示的绝对或相对路径。
 * @param name 模式窗体的名称。
 * @param w 窗体的宽度。
 * @param h 窗体的高度。
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
 * 有关Cookie的一些函数。
 * @ahthor Rick
 * @date 2001-11-10
 */

/**
 * 设置一个Cookie.
 * @param name Cookie的名称。
 * @param value Cookie的值。
 */
function setCookie(name, value) {
	document.cookie = name + "=" + escape(value)
}

/**
 * 取得Cookie的值。
 * @param name Cookie名称。
 * @return value Cookie的值。
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
 * 翻页函数。js 版本。<br>
 * @param rs 二维数组，该数组和数据库中的数据对应。
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
	 * 取得rs.
	 * @return rs 返回构件该对象的二维数组。
	 */
	this.getRs = function() {
		return rs;
	}
	/**
	 * 判断该对象的二维数据是否有效。当数组未初始化或非有效的二维数组时，无效。
	 * @return boolean, 是为true，否则为false. 
	 */
	this.isValidRs = function() {
		return rs && rs.length;
	}
	/**
	 * 当前页面状态。
	 * @return [] 返回一个长度为4的数组。
	 * @see #turnPage.
	 */
	this.getStatus = function() {
		return range;
	}
	
	/**
	 * 翻页。
	 * @param dir 方向，取以下值：<br>
	 * <ul>
	 * <li>-2 首页。
	 * <li>-1 前一页。
	 * <li>0 强制刷新。
	 * <li>1 后一页。
	 * <li>2 尾页。
	 * </ul>
	 * @param pageSize 页面大小，即没页显示的行数。
	 * @return [] 一个长度为4的一维数组。其中：<br>
	 * [0] 起始行。<br>
	 * [1] 终止行。<br>
	 * [2] 当前页号。<br>
	 * [3] 总页数。<br>
	 */
	this.turnPage = function(dir, size) {
		if (!this.isValidRs()) return range;

		if (size > 0) pageSize = size;
		var start = oLoc;
		var end = rs.length - 1;	

		switch (dir) {
		case 1:	
			if (oLoc == rs.length) {
				alert("已经到最后一页了。");
				return range;
			}
			start = oLoc;
			end = start + pageSize;
			break;
		case -1:
			if (range && range[0] == 0) {
				alert("已经到第一页了。");
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
 * 指定显示列和行的范围，将一个二维数组显示。
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
 * 将一个结果集(二维数组)的指定列按给定范围输出
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
 * 数据结构
 * 
 * @author Rick
 * @date 2001-11-16
 *
 */

/**
 * 集合类
 * @version 2.0
 */
function Collection() {
	var objs = [];

	/**
	 * 增加一个对象
	 */	
	this.add = function(o) {
		objs[objs.length] = o;
	};
	/**
	 * 加入一个数组中的所有元素
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
	 * 返回元素的迭代形式
	 */
	this.iterator = function() {
		return new Iterator(objs);
	};
	/**
	 * 返回元素的数组形式
	 */
	this.toArray = function() {
		return objs;
	};
	/**
	 * 集合是否为空
	 */
	this.isEmpty = function() {
		return objs.length == 0;
	};
	/**
	 * 集合大小
	 */
	this.size = function() {
		return objs.length;
	};
	/**
	 * 是否包含某个元素，参数必须实现equals(o)的方法
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
 * 迭代器
 * @param objs 对象数组
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
 * 集合类，该集合实现了删除元素的功能
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
	 * 集合容量大小
	 */
	this.capacity = function() {
		return capacity;
	}

	/**
	 * 确保集合容量不在一个给定的大小之下
	 * @param minCapacity 最小的容量
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
	 * 增加一个元素
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
	 * 是否包含某个元素
	 */
	this.contains = function(o) {
		return this.__locate__(o) != null;
	}
	/**
	 * 压缩集合容量，使之与集合内的元素个数一致。
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
	 * 删除一个元素
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
	 * 以数组的形式返回集合内所有的元素
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
	 * 返回元素的迭代形式
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
 * 节点
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
	 * 取得父节点
	 * @return 父节点
	 */
	this.getParent = function() {
		return parent;
	}
	/**
	 * 本节点的值
	 */
	this.getValue = function() {
		return value;
	}
	/**
	 * 设置父节点
	 */
	this.setParent = function(p) {
		parent = p;
	}
	/**
	 * 是否为根节点
	 */
	this.isRoot = function () {
		return parent;
	}
	/**
	 * 是否为叶
	 */
	this.isLeaf = function () {
		return this.isEmpty();
	}
	/**
	 * 取得所有子节点
	 * @return 子节点的数组
	 */
	this.children = function() {
		return this.toArray();
	}
	/**
	 * 增加一个子节点
	 */
	this.addChild = function(node) {
		this.add(node);
	}
	/**
	 * 删除一个子节点
	 */
	this.removeChild = function(node) {
		this.remove(node);
	}
	/**
	 * 本节点的级别，根节点为0级，其下的直接子节点为1级，等等，依次类推。
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
	 * 试图将一个节点放入现有的体系中
	 * @param o, 被放置节点
	 * @param from, 该节点的来源
	 * @return 如果成功，返回null, 否则返回o
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
	 * 比较两个节点间的关系
	 * @return 相等：0; 是本节点的父节点：1; 是本节点的子节点：2; 其它：3
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
		alert("此功能有待实现。");
	else
		alert("此功能在此被禁用。");
}

function callFunc() {
	if (!DEBUG) return;
	window.__func_string__ = prompt("Specify a method to call", window.__func_string__ || "");
	new Function(window.__func_string__)();
}



//==================== key event ==================================
//           使输入焦点在FORM中移动
// waring! this function will use form elements id. 
// make sure the element id is no use
//----------------- keyDownHandler() 使用方法 ---------------------
//<FORM name=form1 onkeypress="keyDownHandler(this,'save()','确定要保存吗?')" >
//this             为FORM的引用.
//'save()'         为要调用的函数 注意:要是字符串.
//'确定要保存吗?'  是保存前要显示的信息.
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
                  var sure=confirm(sureStr||'确定保存数据吗？');
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
        	          var sure=confirm(sureStr||'确定保存数据吗？');
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