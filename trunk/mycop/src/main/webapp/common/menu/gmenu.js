var pauseBeforeShow = 100;

var tmpEl, showTimeoutHandle;

var menus = new Array()
var menuCounter = 0;
var zIndexMax = 0;

var tmpArray = new Array();
var tmpCounter = 0;

var roots = new Array();

//*****************************************
//���·������ڼ��һ���������������,����������,
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
        ostr+="CSS����ֵ<br>";

	//CSS����ֵ
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

//�����߽�������ڶ�λ�˵�λ��
// �����ֵ��÷�ʽ
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

// ��ȡ���߿��С
function getBorderSizes(tableEl) {
	var o = new Object();
//	alert(tableEl.currentStyle);
    //alert(document.defaultView.getComputedStyle(tableEl, null));item(0)
	//alert(document.defaultView.getComputedStyle(tableEl, null).length);
 //********************************************************************************  
	////��������ֵ,�����г�ns������css����
	if(false&&ns){
		var cssObj=document.defaultView.getComputedStyle(tableEl, null);
		var tmp="";
		for(var i=0;i<cssObj.length;i++)
		{
		  strItem=cssObj.item(i);
		  tmp+="<br>"+strItem+"="+cssObj.getPropertyValue(strItem);
		}

		//alert("abc");
		//��������ֵ
		abc=window.open();
		abc.document.body.innerHTML=tmp;
	}
//****************************************************************************************
	if (document.getElementById&&ie) {//���ΪIE
	    //����IE,�˴�ִ�д���,�����
	    

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
//��ʾ�˵�
function showMenu(tableEl, boundaries, directionType, pauseTime) {
	 var left, top;
     var borders = getBorderSizes(tableEl);
	 //alert("showMenu");

	//***********************************************************************
	//���ι����,���netscape����������
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
			//���ι����,���netscape����������
            //���ǵ�����NS��IE,���ò������ļ��㷽ʽ�̶�Ϊһ�ּ��㷽ʽ
			if(ns){
			
				top = boundaries.top + boundaries.height;
				left = boundaries.left;
			}
	       //***********************************************************************


		}
		else {

            //alert("��ʾ�Ӳ˵�!");
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
			//���ι����,���netscape����������
            //���ǵ�����NS��IE,���ò������ļ��㷽ʽ�̶�Ϊһ�ּ��㷽ʽ
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
/*���±�����Ҫ���¼���,���������޷���ʾ������
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
		//��������ֵ
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
		//���ǵ�mozilla���ܶ��ڱ�ʾ����ļ�ͷ������ʾ�ϵ�bug,�˴����е���
		//var menuObj=menus[menuNumber];
		var tableKids=menuObj.childNodes;
		//alert("TRKids.length="+TRKids[0].);
		//�õ�tbody
		var tBodyObj=null;
		for(var i=0;i<tableKids.length;i++){
		  if(tableKids[i].nodeType==1&&tableKids[i].nodeName=="TBODY"){
			 tBodyObj=tableKids[i];
		  }
		}
		//�õ�TRs
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
		//�õ���Ӧ��TDs
		if(trObjs.length>0){
			for(trObjIdex=0;trObjIdex<trObjs.length;trObjIdex++){
			  var trObj=trObjs[trObjIdex];
			  var trObjKids=trObj.childNodes;
			   //����ÿ��TR,ȡ�������TD������������,��ʹ�ñ�ʾ����ļ�ͷ��ʾ����
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
								 //��linuxĬ��ϵͳ�п���û��webdings����,Ӱ����ʾ,����ʹ��ͼƬ
								 //��ʱ����
							 }
						}
				  }
				}
			}
		}

}



function addScriptletMenu(tableEl, leftPos, topPos, pause) {
    //���ǵ�mozilla���ܶ��ڱ�ʾ"����"�ļ�ͷ������ʾ�ϵ�bug,�˴����е���
	adjustArrow(tableEl);

	//alert("left:"+leftPos+" top:"+topPos)
	var id = "_menu_" + menuCounter;
    //����url����
	var urlParam="?"+"menuIndex="+menuCounter;
	urlParam+="&"+"pause="+pause;
	//***********************************************************************
	//���ι����,���netscape����������
    //�˴��Կͻ�����ֲ��netscape���Ǹ�������,��Ҫ���и�д����Ӧnetscape�����л���
	//
	//***********************************************************************
	//����scriptlet����
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
	//���ǵ���NS�����������˼���,��,�˴�������insertAdjacentHTML.
	document.body.insertAdjacentHTML("BeforeEnd", str);

	menus[menuCounter] = tableEl;
	//���ǵ�����ns,�˴���ʱ��Ҫ���ó�ʼ������
	if(ie){
	  initiateMenu(menuCounter, pause);
    }
//alert("aaa");
	menuCounter++;
}
function initiateMenuNS(IFrameObj){
	//	alert("initiateMenuNS----start"+IFrameObj.document);

   //if(currentMenuObj==null) return;
   //�˴�menuNumber��Ҫ��iframe���Բ�������ʽ����,����ֻ���Ե�һ���˵���ʵ��.
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
//����objectMenu,�����Ӳ˵���Ӧ��
IFrameObj.objectMenu=el;	
IFrameObj.openerWindow=this;//.parent;	

	/*	tmpArray[tmpCounter] = el;
		alert("test------------============"+tmpArray[tmpCounter]);
		window.setTimeout(
			"tmpArray[" + tmpCounter + "].initiateMenu(menus[" + menuNumber + "], tmpArray[" + (tmpCounter++) + "])",
			pause,"javascript");
*/
//alert(el.style["visibility"]);
//��ʼ����,��ʾ�ò˵�
        //��ס�Ѿ������Ĳ˵�,�Ա��´�ʹ�õ�ʱ����е���
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
	//��ʱ������,ֻ��return
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
	//��ǰ�˵�����,Ϊ��������1
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

//�˵�������
function menuBarOver() {
	//��ʱ�ر�,���Ǽ���NS
    return;
	var fromEl = getParent(event.fromElement, isRoot);
	var toEl = getParent(event.toElement, isRoot);
	
	if (toEl == null || fromEl == toEl || toEl.className == "rootActive")
		return;
	
	var el = toEl;
	
	if (el.direction == null)
		el.direction = "vertical";	// ����ȱʡֵ

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
	//��ʱ�ر�,���Ǽ���NS
	return;
	var fromEl = getParent(event.fromElement, isRoot);
	var toEl = getParent(event.toElement, isRoot);
	
	if (fromEl == null || fromEl == toEl || fromEl.className == "rootActive")
		return;
	
	var el = fromEl;
	
	fromEl.className = "root";
}

function menuBarClick() {
//event������Ҫ����,��Ҫ��args�еõ�
//IE��Ĭ����event����,��NS����Ҫ���ʼ���õ�ʱ�򴫵�


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
			//��ns�в���ʹ��cancelBubble��ȡ���¼��Ĵ���,�Զ����ж���Ϣ�Ĵ���
		    //�����Ҫ����������Ϣ,��ҪrouteEvent����
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
	//��ns��el.classNameΪ����,���صĲ��Ƕ���,����indexOf������ʧ��
	//��IE��el.classNameΪ�ս�����"",���Բ������
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
	  //Ĭ��ΪIE
	    return getParent(el.parentElement, f);

	}
}

//�˵������ݴ������
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

//��ǰ�����Ĳ˵���
var currentMenuItem=new MenuItem();
var currentMenuObj=null;
var currentMenuShow=false;
/*
if (document.attachEvent)		//��ץ�¼�
	document.attachEvent("onclick", hideAllMenuScriptlets);
else
	document.onclick = hideAllMenuScriptlets;
*/
if (document.attachEvent)		//��ץ�¼�
	document.attachEvent("onclick", hideAllMenuAdvance);
else
	document.onclick = hideAllMenuAdvance;
