var pauseBeforeShow = 100;

var tmpEl, showTimeoutHandle;

var menus = new Array()
var menuCounter = 0;
var zIndexMax = 0;

var tmpArray = new Array();
var tmpCounter = 0;

var roots = new Array();

//*****************************************
//以下方法用于检查一个对象的所有属性,函数及对象,
function testObj(obj){
		var ostr="";
		for(var o in obj){
			var subObj;
          try{
		    subObj=eval("window."+o);
          }catch(e){

		  }
		  if(subObj!=null){
			  if(typeof(subObj)!="function"&&typeof(subObj)!="object"){
		            ostr+=""+o+"="+subObj+"<br>";

			  }else{
		            ostr+=""+typeof(subObj)+"&nbsp;&nbsp;"+o+"<br>";

			  }
		  }else{
		  ostr+=o+"<br>";
		  }
		}
        ostr+="<br><hr><br>";
        ostr+="CSS属性值<br>";

	//CSS属性值
		var cssObj=document.defaultView.getComputedStyle(obj, null);

		for(var i=0;i<cssObj.length;i++)
		{
		  strItem=cssObj.item(i);
		  ostr+="<br>"+strItem+"="+cssObj.getPropertyValue(strItem);
		}

	



		abc=window.open();
		abc.document.body.innerHTML=ostr;
}

//*****************************************

//产生边界对象，用于定位菜单位置
// 有三种调用方式
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
//	alert(tableEl.currentStyle);
    //alert(document.defaultView.getComputedStyle(tableEl, null));item(0)
	//alert(document.defaultView.getComputedStyle(tableEl, null).length);
 //********************************************************************************  
	////测试属性值,可以列出ns下所有css属性
	if(false&&ns){
		var cssObj=document.defaultView.getComputedStyle(tableEl, null);
		var tmp="";
		for(var i=0;i<cssObj.length;i++)
		{
		  strItem=cssObj.item(i);
		  tmp+="<br>"+strItem+"="+cssObj.getPropertyValue(strItem);
		}

		//alert("abc");
		//测试属性值
		abc=window.open();
		abc.document.body.innerHTML=tmp;
	}
//****************************************************************************************
	if (document.getElementById&&ie) {//如果为IE
	    //对于IE,此处执行错误,待解决
	    

		if(typeof(tableEl)=="string"){
					tableEl=document.getElementById(tableEl);
		}
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
function displayMenu(tableEl,flag){
    alert("displayMenu");
	var eventSrc=event.srcElement;
	/*if(currentMenuObj==null){
		currentMenuObj=tableEl;
	}else{
		if(currentMenuObj!=tableEl){
			currentMenuObj=tableEl;
			hideAllMenu();
		}
	}
	*/
	if(flag!=null){
		if(currentMenuShow){
			//		alert("hideAllMenuScriptlets()---118");

			hideAllMenuScriptlets();
			showMenu(tableEl,new Boundaries(eventSrc),"vertical")
		}
	}else{
			//	alert("hideAllMenuScriptlets()---124");

		hideAllMenuScriptlets();
		showMenu(tableEl,new Boundaries(eventSrc),"vertical");
		currentMenuShow=true;
	}	

}
//显示菜单
function showMenu(tableEl, boundaries, directionType, pauseTime) {
	 var left, top;
     var borders = getBorderSizes(tableEl);
	 //alert("showMenu");

	//***********************************************************************
	//资治国添加,解决netscape兼容性问题
    if(typeof(tableEl)=="string"){
        		tableEl=document.getElementById(tableEl);
	}
	//***********************************************************************

	if(directionType==null || directionType=="") directionType="vertical";
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
			//***********************************************************************
			//资治国添加,解决netscape兼容性问题
            //考虑到兼容NS及IE,将该部分灵活的计算方式固定为一种计算方式
			if(ns){
			
				top = boundaries.top + boundaries.height;
				left = boundaries.left;
			}
	       //***********************************************************************


		}
		else {

            //alert("显示子菜单!");
              //alert(boundaries.left+"===");
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


			//***********************************************************************
			//资治国添加,解决netscape兼容性问题
            //考虑到兼容NS及IE,将该部分灵活的计算方式固定为一种计算方式
			if(ns){
				top =  boundaries.top - borders.top;;
				left =  boundaries.left + boundaries.width;
			}
			//alert(boundaries.left);
	       //***********************************************************************


		}


	}else{

		left=event.x
		top=event.y
	}

		//alert("left="+left+"  top="+top);
	if (zIndexMax == null) {
		var a = document.all;
		var al = a.length;
		
		for (var i=0; i<al; i++) 
			zIndexMax = Math.max(zIndexMax, a[i].style.zIndex);
	}
	
	zIndexMax++;

	if (pauseTime == null)
		pauseTime = 0;

	//alert("tableEl.scriptlet"+tableEl.scriptlet);
	if (tableEl.scriptlet != null) {
        tableEl.scriptlet.style.visibility ="visible";
		tableEl.scriptlet.style.left =left;
		tableEl.scriptlet.style.top = top;
		//alert(tableEl.scriptlet.style.height);
		//alert(tableEl.scriptlet.style.width);

        //alert(tableEl.scriptlet.style.left+"left"+left+"======"+tableEl.scriptlet.style.top+"top"+top);
		//if (tableEl.menuState == null)
		//tableEl.menuState = "hidden";
		//tmpArray[tmpCounter] = tableEl;
/*
		showTimeoutHandle = window.setTimeout(
			"tmpArray[" + tmpCounter + "].scriptlet.style.zIndex = " + zIndexMax + ";" +
			"tmpArray[" + tmpCounter + "].scriptlet.style.visibility = tmpArray[" + (tmpCounter++) + "].menuState;",
			pauseTime,"javascript");
*/
//alert(zIndexMax);
         tableEl.scriptlet.style.zIndex =zIndexMax;
		//alert(tmpArray[(tmpCounter)].menuState);
//alert(tableEl.scriptlet.id);
//tmpArray[tmpCounter++].menuState;
        //alert(tableEl.menuState);         
/*以下变量需要重新计算,否则会出现无法显示的现象
bottom
top
right
left
*/

	if(false&&ns){
		var cssObj=document.defaultView.getComputedStyle(tableEl.scriptlet, null);
		var tmp="";
		for(var i=0;i<cssObj.length;i++)
		{
		  strItem=cssObj.item(i);
		  tmp+="<br>"+strItem+"="+cssObj.getPropertyValue(strItem);
		}

		//alert("abc");
		//测试属性值
		abc=window.open();
		abc.document.body.innerHTML=tmp;
	}


	}
	else {
		tmpArray[tmpCounter] = tableEl;

		try{
		window.setTimeout("addScriptletMenu(tmpArray[" + (tmpCounter++) + "], " + left + ", " + top + ", " + pauseTime + ");", 0,"javascript");
		}catch(e){
			
		}

	}


}

function adjustArrow(menuObj){
		//考虑到mozilla可能对于表示更多的箭头存在显示上的bug,此处进行调整
		//var menuObj=menus[menuNumber];
		var tableKids=menuObj.childNodes;
		//alert("TRKids.length="+TRKids[0].);
		//得到tbody
		var tBodyObj=null;
		for(var i=0;i<tableKids.length;i++){
		  if(tableKids[i].nodeType==1&&tableKids[i].nodeName=="TBODY"){
			 tBodyObj=tableKids[i];
		  }
		}
		//得到TRs
		var trObjs=new Array();
		var trObjIdex=0;
		if(tBodyObj!=null){
		  var tBodyKids=tBodyObj.childNodes;
			for(var i=0;i<tBodyKids.length;i++){
			  if(tBodyKids[i].nodeType==1&&tBodyKids[i].nodeName=="TR"){
				 trObjs[trObjIdex++]=tBodyKids[i];
			  }
			}
		}
		//得到相应的TDs
		if(trObjs.length>0){
			for(trObjIdex=0;trObjIdex<trObjs.length;trObjIdex++){
			  var trObj=trObjs[trObjIdex];
			  var trObjKids=trObj.childNodes;
			   //对于每个TR,取其第三个TD进行属性设置,以使得表示更多的箭头显示正常
				 var TDIndex=0;
				for(var i=0;i<trObjKids.length;i++){
				  if(trObjKids[i].nodeType==1&&trObjKids[i].nodeName=="TD"){
						TDIndex++;
						if(TDIndex==3){
							 if( parseInt(trObjKids[i].innerHTML)==4){
                                 if(navigator.appName == "Netscape")
                                     trObjKids[i].innerHTML='<IMG SRC="../images/jiantou.gif" WIDTH="14" HEIGHT="14" BORDER=0>';
								 else
                                     trObjKids[i].innerHTML='<font face="webdings" style="font-size:12px;" >4</font>';
								 //在linux默认系统中可能没有webdings字体,影响显示,建议使用图片
								 //暂时不变
							 }
						}
				  }
				}
			}
		}

}



function addScriptletMenu(tableEl, leftPos, topPos, pause) {
    //考虑到mozilla可能对于表示"更多"的箭头存在显示上的bug,此处进行调整
	adjustArrow(tableEl);

	//alert("left:"+leftPos+" top:"+topPos)
	var id = "_menu_" + menuCounter;
    //配置url参数
	var urlParam="?"+"menuIndex="+menuCounter;
	urlParam+="&"+"pause="+pause;
	//***********************************************************************
	//资治国添加,解决netscape兼容性问题
    //此处对客户端移植到netscape上是个大问题,需要进行改写以适应netscape的运行机制
	//
	//***********************************************************************
	//生成scriptlet对象
	var str="";
	if(ie){
	 str = '<object type="text/x-scriptlet" data="'+Menu.filepath +'"'+
	          ' style="position: absolute; left: ' + leftPos + 'px; top: ' + topPos + 'px; ' +
			  'width: ' + tableEl.offsetWidth +'px; height: ' + tableEl.offsetHeight +
			  'px; visibility: hidden; z-index: ' + zIndexMax + ';" ' +
			   'id="' + id + '"></object>';
	}
   if(ns){
	 str = '<iframe src="'+Menu.filepath + urlParam +'"'+
	          ' style="position: absolute; left: ' + leftPos + 'px; top: ' + topPos + 'px; ' +
			  'width: ' + tableEl.offsetWidth +'px; height: ' + tableEl.offsetHeight +
			  'px; visibility: hidden; z-index: ' + zIndexMax + ';" ' +
			   'id="' + id + '" border="0" scrolling="no"  framespacing="0" frameborder="NO"></iframe>';
   }
   
	//menuDynContent=document.getElementById("menuDynContent");
	//alert(document.body.insertAdjacentHTML);
	//alert("before add..."+menuDynContent.innerHTML);
	//menuDynContent.innerHTML+=str;
	//考虑到在NS环境下已做了兼容,故,此处可以用insertAdjacentHTML.
	document.body.insertAdjacentHTML("BeforeEnd", str);

	menus[menuCounter] = tableEl;
	//考虑到兼容ns,此处暂时不要调用初始化操作
	if(ie){
	  initiateMenu(menuCounter, pause);
    }
//alert("aaa");
	menuCounter++;
}
function initiateMenuNS(IFrameObj){
	//	alert("initiateMenuNS----start"+IFrameObj.document);

   //if(currentMenuObj==null) return;
   //此处menuNumber需要从iframe中以参数的形式传入,这里只测试第一个菜单的实现.
     var menuNumber=0;//menuCounter-1;
	 var pause=0;
	 var urlParam=IFrameObj.document.location.href;
     urlParam=urlParam.substring(urlParam.indexOf("?")+1);
	 paramArray=urlParam.split("&");
	 for(var paramIndex=0;paramIndex<paramArray.length;paramIndex++){
           var tmpObj=paramArray[paramIndex].split("="); 
		   if(tmpObj.length==2){
                 if(tmpObj[0]=="menuIndex"){
                     menuNumber=parseInt(tmpObj[1]);
				 }
                 if(tmpObj[0]=="pause"){
					 pause=parseInt(tmpObj[1]);
				 }
		   }
	 }
     //alert("menuNumber="+menuNumber+"   pause="+pause);

	 var el;
	 if(ie){
		el= document.all("_menu_" + menuNumber);
	 }
     if(ns){
		el= document.getElementById("_menu_" + menuNumber);
	 }

//for(var trkid in TRKids){
 // alert(trkid+"==");
//}
//alert(document.getElementById("mainContent").IDOM);
//alert(IFrameObj.document.body.innerHTML);
tableStr='<table cellspacing="0" cellpadding="0"  class="menu" id="'+menus[menuNumber].id+'">';
tableStr+=menus[menuNumber].innerHTML;
tableStr+='</table>';
//alert(tableStr);
IFrameObj.document.body.innerHTML=tableStr;
//设置objectMenu,便于子菜单的应用
IFrameObj.objectMenu=el;	
IFrameObj.openerWindow=this;//.parent;	

	/*	tmpArray[tmpCounter] = el;
		alert("test------------============"+tmpArray[tmpCounter]);
		window.setTimeout(
			"tmpArray[" + tmpCounter + "].initiateMenu(menus[" + menuNumber + "], tmpArray[" + (tmpCounter++) + "])",
			pause,"javascript");
*/
//alert(el.style["visibility"]);
//初始化后,显示该菜单
        //记住已经产生的菜单,以便下次使用的时候进行调用
		menus[menuNumber].scriptlet=el;
		el.style["visibility"]="visible";

	if(this.menu_onComplete){
		this.menu_onComplete();
	}



}
function initiateMenu(menuNumber, pause) {

	 var el;

	 if(ie){
		el= document.all("_menu_" + menuNumber);
	 }
     if(ns){
		el= document.getElementById("_menu_" + menuNumber);

	 }
    //alert(el.readyState);
	if (el == null || el.readyState != "4")
		window.setTimeout("initiateMenu(" + menuNumber + ", " + pause + ")", 50,"javascript");
	else {
//		el.initiateMenu(menus[menuNumber], el);
		tmpArray[tmpCounter] = el;
		//alert(tmpArray[tmpCounter].initiateMenu);
		window.setTimeout(
			"tmpArray[" + tmpCounter + "].initiateMenu(menus[" + menuNumber + "], tmpArray[" + (tmpCounter++) + "])",
			pause,"javascript");
	}
}

function hideAllMenu(){
	currentMenuShow=false;
			//alert("hideAllMenuScriptlets()---332");
	hideAllMenuScriptlets();
}

function hideAllMenuScriptlets() {
	//暂时不隐藏,只是return
	//return;
	//alert("238");
//IE
if(ie){
	var objs = document.all.tags("OBJECT");

	//alert("238---ok");
	for (var i=0; i<objs.length; i++) {
		if (objs[i].hideMenu)	// Test if the object is a menu
			objs[i].hideMenu();
	}
	try{
		for (var r in roots) {
			if (roots[r].className == "rootActive")  {
				roots[r].className = "root";
				getParent(roots[r], isMenuBar).shownMenu = null;
			}
		}	
	}catch(e){
	}
}
if(ns){
	//当前菜单总数,为计数器减1
	var curMenuCounter=menuCounter;
	for (var i=0; i<curMenuCounter; i++) {
		var tmpId = "_menu_" + i;
		var obj=document.getElementById(tmpId);
		obj.style["visibility"]="hidden";
	}
	//try{
		for (var r in roots) {
			if (roots[r].className == "rootActive")  {
				roots[r].className = "root";
				getParent(roots[r], isMenuBar).shownMenu = null;
			}
			//alert("roots[r].className!!!!!!!!!!!!!!!!!!!!1 ");
		}	
	//}catch(e){ }
}
	currentMenuObj=null;
}

function forceRebuild(tableEl) {
	tableEl.scriptlet = null;
}

//菜单栏代码
function menuBarOver() {
	//暂时关闭,考虑兼容NS
    return;
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
	//暂时关闭,考虑兼容NS
	return;
	var fromEl = getParent(event.fromElement, isRoot);
	var toEl = getParent(event.toElement, isRoot);
	
	if (fromEl == null || fromEl == toEl || fromEl.className == "rootActive")
		return;
	
	var el = fromEl;
	
	fromEl.className = "root";
}

function menuBarClick() {
//event参数需要处理,需要冲args中得到
//IE中默认有event对象,但NS中需要在最开始调用的时候传递


//    alert("abc");
//alert(event.target.className);
//target.parentNode.className
//target.parentNode.childNodes.length
//target.parentNode.childNodes[0].nodeName	
//target.nodeName
//target.parentNode.nodeName  
//e.target.nodeName != "#text"
  if(ns){
	event=menuBarClick.arguments[0];
  }

    var el ;
	if(ns){
     //NS
	 el = getParent(event.target, isRoot);
	}else{
          //IE
	 el = getParent(event.srcElement, isRoot);
	}
	if (el == null)
		return;
	
	var menuBar = getParent(el, isMenuBar);

	if (el.className == "rootActive") {	
		//alert("hideAllMenuScriptlets()---463");
		hideAllMenuScriptlets();	
	}
	else {
			//	alert("hideAllMenuScriptlets()---467");

		hideAllMenuScriptlets();

        var objElMenu;
        if(ns){
	     	objElMenu=document.getElementById(el.getAttribute("menu"));
		}
		if(ie){
			objElMenu=el.menu;
		}

		showMenu(objElMenu, new Boundaries(el), el.direction);

		
		menuBar.shownMenu = objElMenu;

		el.className = "rootActive";
         //alert(el.className);
		roots[el] = el;
		menuBar.oldActive = el;
        if(ie){ 
			//在ns中不能使用cancelBubble来取消事件的传递,自动将中断消息的传递
		    //如果需要继续传递消息,需要routeEvent方法
		  window.event.cancelBubble = true;
		}
	}
		//alert("3");

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
	//在ns中el.className为空是,返回的不是对象,调用indexOf方法将失败
	//在IE中el.className为空将返回"",所以不会出错
	var retCode;
    retCode=(el.className+"").indexOf("root") > -1;
	return  retCode;
}

function isMenuBar(el) {
	return el.className == "menuBar";
}

function getParent(el, f) {
	if (el == null) return null;
	else if (f(el)) return el;
	else{
	  if(ns){
	    return getParent(el.parentNode, f);
	  }
	  if(ie){
	    return getParent(el.parentElement, f);
	  }
	  //默认为IE
	    return getParent(el.parentElement, f);

	}
}

//菜单项数据储存对象
function MenuItem(){
	this.id="";
	this.icon="";
	this.text="";
	this.attributes=new Array();
}
MenuItem.prototype.getId=function(){
	return this.id;
}
MenuItem.prototype.setId=function(id){
	this.id=id;
}

MenuItem.prototype.setIcon=function(icon){
	this.icon=icon;
}
MenuItem.prototype.getText=function(){
	return this.text;
}

MenuItem.prototype.setText=function(text){
	this.text=text;
}
MenuItem.prototype.getAttribute=function(name){
	for(var i=0;i<this.attributes.length;i++){
		if(this.attributes[i].getName()==name){
			return this.attributes[i].getValue();
		}
	}
}

MenuItem.prototype.setAttribute=function(name,value){
	this.attributes[this.attributes.length]=new Attribute(name,value);
}
MenuItem.prototype.setAttributes=function(arrData){
	this.attributes.length=0;
	var tmp=arrData;
	if(tmp!=null && tmp!=""){
		var arrAttr=tmp.split("|*|");
		var j=0;
		for(var i=0;i<arrAttr.length;i++){
			if((i%2)==1){
				this.setAttribute(arrAttr[i-1],arrAttr[i]);
			}
		}
	}
}

function Attribute(name,value){
	this.name=name;
	this.value=value;
}

Attribute.prototype.getValue=function(name){
	return this.value;
}
Attribute.prototype.setValue=function(value){
	this.value=value;
}


Attribute.prototype.getName=function(){
	return this.name;
}
Attribute.prototype.setName=function(name){
	this.name=name;
}

Menu.context="";
Menu.filepath="";
function Menu(){
}

function hideAllMenuAdvance(){
	var event=hideAllMenuAdvance.arguments[0];
    var el ;
	if(ns){
     //NS
	 el = getParent(event.target, isRoot);
	}else{
          //IE
	 el = getParent(event.srcElement, isRoot);
	}
	
	if (el == null){
        hideAllMenuScriptlets();
      

	}
		
}

//当前单击的菜单项
var currentMenuItem=new MenuItem();
var currentMenuObj=null;
var currentMenuShow=false;
/*
if (document.attachEvent)		//捕抓事件
	document.attachEvent("onclick", hideAllMenuScriptlets);
else
	document.onclick = hideAllMenuScriptlets;
*/
if (document.attachEvent)		//捕抓事件
	document.attachEvent("onclick", hideAllMenuAdvance);
else
	document.onclick = hideAllMenuAdvance;
