var pauseBeforeShow = 100;

var tmpEl, showTimeoutHandle;

var menus = new Array()
var menuCounter = 0;
var zIndexMax = 0;

var tmpArray = new Array();
var tmpCounter = 0;

var roots = new Array();

//产生边界对象，用于定位菜单位置
// 有三种调用方式
// new Boundaries(htmlElement)
// new Boundaries(nLeft, nTop)
// new Boundaries(nLeft, nTop, nWidth, nHeight)
function Boundaries() {
	if (arguments.length == 1) {
		this.left = leftPos(arguments[0]);
		this.top  = topPos(arguments[0]);
		this.width  = arguments[0].offsetWidth;
		this.height = arguments[0].offsetHeight;
	}
	
	if (arguments.length >= 2) {
		this.left = arguments[0];
		this.top = arguments[1];
		this.width = 0;
		this.height = 0;
	}
	if (arguments.length >= 4) {
		this.width = arguments[2];
		this.height = arguments[3];
	}
}

////////////////////////////////////////////////////////
//The following "two" functions are needed to calculate the position
function topPos(el) {
	return doPosLoop(el, "Top");
}

function leftPos(el) {
	return doPosLoop(el, "Left");
}

function doPosLoop(el, val) {
	var temp = el;
	var x = temp["offset" + val];
	while (temp.tagName!="BODY") {
		temp = temp.offsetParent;
		x += temp["offset" + val];
	}
	return x;
}
////////////////////////////////////////////////////////

// 获取表格边框大小
function getBorderSizes(tableEl) {
	var o = new Object();
	
	if (document.getElementById) {
		o.top    = parseInt(tableEl.currentStyle.borderTopWidth);
		o.left   = parseInt(tableEl.currentStyle.borderLeftWidth);
		o.right  = parseInt(tableEl.currentStyle.borderRightWidth);
		o.bottom = parseInt(tableEl.currentStyle.borderBottomWidth);
	}
	else {
		var w = 0;
		for (var i=0; i<tableEl.rows[0].cells.length; i++) {
			w += tableEl.rows[0].cells[i].offsetWidth;
		}
		
		var h = 0;
		for (var i=0; i<tableEl.rows.length; i++) {
			h += tableEl.rows[i].offsetHeight;
		}
		
		o.top = tableEl.rows[0].offsetTop;
		o.left = tableEl.rows[0].offsetLeft;
		o.right = tableEl.offsetWidth - tableEl.rows[0].offsetWidth - o.left;
		o.bottom = tableEl.offsetHeight - h - o.top;
	}

	return o;
}

function Menu(id){
	var menuTable='<table cellspacing="0" cellpadding="0"  class="menu" id="menu'+id+'"></table>';
	this.id=id;
	window[id]=this;
	document.write(menuTable);
	this.table=document.all("menu"+id);

}
Menu.prototypr.add=function(menuItem){

}
Menu.prototypr.delete=function(id){
}
Menu.prototypr.show=function(id){
}
Menu.prototypr.hide=function(id){
}

function MenuItem(id,title){
	
}


//显示菜单
function showMenu(tableEl, boundaries, directionType, pauseTime) {
	var left, top;
	
	var borders = getBorderSizes(tableEl);
	if(boundaries!=null){
		if (directionType == "vertical") {
			if (boundaries.top + boundaries.height + tableEl.offsetHeight + borders.top + borders.bottom <= document.body.clientHeight + document.body.scrollTop)
				top = boundaries.top + boundaries.height;
			else if (boundaries.top - tableEl.offsetHeight >= document.body.scrollTop)
				top = boundaries.top - tableEl.offsetHeight;
			else if (document.body.clientHeight >= tableEl.offsetHeight + borders.top + borders.bottom)
				top = document.body.clientHeight + document.body.scrollTop - tableEl.offsetHeight - borders.top - borders.bottom;
			else
				top = document.body.scrollTop;

			if (boundaries.left + tableEl.offsetWidth <= document.body.clientWidth + document.body.scrollLeft)
				left = boundaries.left;
			else if (document.body.clientWidth >= tableEl.offsetWidth + borders.left + borders.right)
				left = document.body.clientWidth + document.body.scrollLeft - tableEl.offsetWidth - borders.left - borders.right;
			else
				left = document.body.scrollLeft;
		}
		else {
			if (boundaries.top + tableEl.offsetHeight - borders.top <= document.body.clientHeight + document.body.scrollTop)
				top = boundaries.top - borders.top;
			else if (boundaries.top + boundaries.height - tableEl.offsetHeight + borders.top >= 0)
				top = boundaries.top + boundaries.height - tableEl.offsetHeight + borders.top;
			else if (document.body.clientHeight >= tableEl.offsetHeight + borders.top + borders.bottom)
				top = document.body.clientHeight + document.body.scrollTop - tableEl.offsetHeight - borders.top - borders.bottom;
			else
				top = document.body.scrollTop;;

			if (boundaries.left + boundaries.width + tableEl.offsetWidth <= document.body.clientWidth + document.body.scrollLeft)
				left = boundaries.left + boundaries.width;
			else if (boundaries.left - tableEl.offsetWidth >= 0)
				left = boundaries.left - tableEl.offsetWidth;
			else if (document.body.clientWidth >= tableEl.offsetWidth + borders.left + borders.right)
				left = document.body.clientWidth  + document.body.scrollLeft - tableEl.offsetWidth - borders.left - borders.right;
			else
				left = document.body.scrollLeft;
		}


	}else{
		left=event.x
		top=event.y
	}
	if (zIndexMax == null) {
		var a = document.all;
		var al = a.length;
		
		for (var i=0; i<al; i++) 
			zIndexMax = Math.max(zIndexMax, a[i].style.zIndex);
	}
	
	zIndexMax++;

	if (pauseTime == null)
		pauseTime = 0;
	
	if (tableEl.scriptlet != null) {
		tableEl.scriptlet.style.left = left;
		tableEl.scriptlet.style.top = top;

		if (tableEl.menuState == null)
			tableEl.menuState = "hidden";

		tmpArray[tmpCounter] = tableEl;
		showTimeoutHandle = window.setTimeout(
			"tmpArray[" + tmpCounter + "].scriptlet.style.zIndex = " + zIndexMax + ";" +
			"tmpArray[" + tmpCounter + "].scriptlet.style.visibility = tmpArray[" + (tmpCounter++) + "].menuState;",
			pauseTime,"javascript");

	}
	else {
		tmpArray[tmpCounter] = tableEl;

		try{
		window.setTimeout("addScriptletMenu(tmpArray[" + (tmpCounter++) + "], " + left + ", " + top + ", " + pauseTime + ");", 0,"javascript");
		}catch(e){
			
		}

	}

}

function addScriptletMenu(tableEl, leftPos, topPos, pause) {
	//alert("left:"+leftPos+" top:"+topPos)
	var id = "_menu_" + menuCounter;

	//生成scriptlet对象
	var str = '<object type="text/x-scriptlet" data="/menu/js/menuContainer.htm" ' +
	          'style="position: absolute; left: ' + leftPos + 'px; top: ' + topPos + 'px; ' +
			  'width: ' + tableEl.offsetWidth +'px; height: ' + tableEl.offsetHeight +
			  'px; visibility: hidden; z-index: ' + zIndexMax + ';" ' +
			   'id="' + id + '"></object>';
	
	document.body.insertAdjacentHTML("BeforeEnd", str);
	
	menus[menuCounter] = tableEl;
	
	initiateMenu(menuCounter, pause);
	menuCounter++;
}

function initiateMenu(menuNumber, pause) {
	var el = document.all("_menu_" + menuNumber);
	if (el == null || el.readyState != "4")
		window.setTimeout("initiateMenu(" + menuNumber + ", " + pause + ")", 50,"javascript");
	else {
//		el.initiateMenu(menus[menuNumber], el);
		tmpArray[tmpCounter] = el;
		window.setTimeout(
			"tmpArray[" + tmpCounter + "].initiateMenu(menus[" + menuNumber + "], tmpArray[" + (tmpCounter++) + "])",
			pause,"javascript");
	}
}

function hideAllMenuScriptlets() {
	var objs = document.all.tags("OBJECT");
	
	for (var i=0; i<objs.length; i++) {
		if (objs[i].hideMenu)	// Test if the object is a menu
			objs[i].hideMenu();
	}
	
	for (var r in roots) {
		if (roots[r].className == "rootActive")  {
			roots[r].className = "root";
			getParent(roots[r], isMenuBar).shownMenu = null;
		}
	}	
}

function forceRebuild(tableEl) {
	tableEl.scriptlet = null;
}

//菜单栏代码
function menuBarOver() {
	var fromEl = getParent(event.fromElement, isRoot);
	var toEl = getParent(event.toElement, isRoot);
	
	if (toEl == null || fromEl == toEl || toEl.className == "rootActive")
		return;
	
	var el = toEl;
	
	if (el.direction == null)
		el.direction = "vertical";	// 设置缺省值

	findMenu(el);
	
	var menuBar = getParent(el, isMenuBar);
	
	if (menuBar == null) 
		el.className = "rootHighlight";
	else {
		if (menuBar.shownMenu != null) {
			if (menuBar.shownMenu.scriptlet == null)
				return;
			
			window.clearTimeout(showTimeoutHandle);
			menuBar.shownMenu.scriptlet.hideMenu();

			menuBar.oldActive.className = "root";
			
			showMenu(el.menu, new Boundaries(el), el.direction);
			menuBar.shownMenu = el.menu;
			el.className = "rootActive";
			roots[el] = el;
			menuBar.oldActive = el;
		}
		else {
			el.className = "rootHighlight";
		}		
	}
}	

function menuBarOut() {
	var fromEl = getParent(event.fromElement, isRoot);
	var toEl = getParent(event.toElement, isRoot);
	
	if (fromEl == null || fromEl == toEl || fromEl.className == "rootActive")
		return;
	
	var el = fromEl;
	
	fromEl.className = "root";
}

function menuBarClick() {
	var el = getParent(event.srcElement, isRoot);
	
	if (el == null)
		return;
		
	var menuBar = getParent(el, isMenuBar);

	if (el.className == "rootActive") {	
		hideAllMenuScriptlets();		
	}
	else {
		hideAllMenuScriptlets();
		showMenu(el.menu, new Boundaries(el), el.direction);
		menuBar.shownMenu = el.menu;
		el.className = "rootActive";
		roots[el] = el;
		menuBar.oldActive = el;
		window.event.cancelBubble = true;
	}
	

}

function findMenu(el) {
	if (typeof(el.menu) == "string") {
		el.menu = eval(el.menu);
	}
	else if (el.menu == null) {	
		var tables = el.all.tags("TABLE");
		
		for (var i=0; i<tables.length; i++) {
			if (tables[i].className == "menu") {
				el.menu = tables[i];
				break;
			}
		}
	}
}

function isRoot(el) {
	return el.className.indexOf("root") > -1;
}

function isMenuBar(el) {
	return el.className == "menuBar";
}

function getParent(el, f) {
	if (el == null) return null;
	else if (f(el)) return el;
	else return getParent(el.parentElement, f);
}

if (document.attachEvent)		//捕抓事件
	document.attachEvent("onclick", hideAllMenuScriptlets);
else
	document.onclick = hideAllMenuScriptlets;