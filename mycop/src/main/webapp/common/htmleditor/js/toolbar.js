/*************************************
工具栏组件
汤启礼2004-3-26
**************************************/
ToolBar.TABLE="<table class=\"toolbar\" id=#id cellPadding=0 cellspacing=0><tr></tr></table>";
ToolBar.HEIGHT=20;
ToolBar.temp=null;

function ToolBar(id,parentObj,height){
	this.id=id;
	ToolBar.temp=this;
	eval(this.id+"=ToolBar.temp");
	ToolBar.temp=null;
	if(parentObj!=null){
		var tbTable=document.createElement("TABLE");
		tbTable.className="toolbar";
		tbTable.id="toolbar"+id;
		tbTable.cellPadding=2;
		tbTable.cellSpacing=0;
		parentObj.appendChild(tbTable);
		tbTable.insertRow();
		this.htmlObj=tbTable;
	}else{
		document.write(ToolBar.TABLE.replace("#id","toolbar"+id));
		this.htmlObj=eval("document.all.toolbar"+id);
	}
	this.htmlObj.onmouseover = ToolBar.toolbarMouseOver;
	this.htmlObj.onmouseout = ToolBar.toolbarMouseOut;
	this.htmlObj.onmousedown = ToolBar.toolbarMouseDown;
	this.htmlObj.onmouseup = ToolBar.toolbarMouseUp;
	this.buttonCount=0;
	this.height=(height==null?ToolBar.HEIGHT:height)
	this.htmlObj.style.pixelHeight=this.height;
	this.action=null;
	this.buttons=[];

}
ToolBar.prototype.addButton=function(id,title,flag){
	var cell=this.htmlObj.rows[0].insertCell();
	cell.className="toolbarbutton";
	cell.type="button";
	cell.btype=1;
	//cell.style.pixelHeight=24;
	cell.id="toolbarbutton"+this.id+this.buttonCount;
	cell.bid=id;
	cell.toolBarId=this.id;
	cell.title=title;
	cell.isCheck=flag;
	if(typeof(title)=="object"){
		cell.appendChild(title);
		cell.type="";
	}else{
		cell.innerHTML=title;
		
	}
	cell.style.pixelHeight=this.height;
	var cellImg=null;
	cell.onclick=ToolBar.buttonClick;
	if(flag){
		ToolBar.addToggle(cell);
	}
	cell.index=this.buttonCount;
	//this.buttons[this.buttonCount++]=;
}
ToolBar.prototype.addImgButton=function(id,icon,title,flag){
	var cell=this.htmlObj.rows[0].insertCell();
	cell.className="toolbarbutton";
	cell.type="button";
	cell.btype=2;
	cell.isCheck=flag;
	//cell.style.pixelHeight=24;
	cell.id="toolbarbutton"+this.id+this.buttonCount;
	cell.bid=id;
	cell.toolBarId=this.id;
	cell.style.pixelHeight=this.height;
	cell.title=title;
	var cellImg=null;
	if(icon!=null && icon!=""){
		cellImg=document.createElement("IMG");
		cellImg.src=icon;
		cellImg.align="absmiddle";
		cellImg.title=title;
		cell.icon=icon;
		cell.appendChild(cellImg);
		cellImg.style.pixelWidth=this.height;
		cellImg.style.pixelHeight=this.height;

	}
	cell.onclick=ToolBar.buttonClick;
	if(flag){
		ToolBar.addToggle(cell);
	}
	cell.index=this.buttonCount;
	this.buttonCount++;
}
ToolBar.prototype.addComButton=function(id,title,icon,flag){
	var cell=this.htmlObj.rows[0].insertCell();
	cell.className="toolbarbutton";
	cell.type="button";
	cell.btype=3;
	//cell.style.pixelHeight=24;
	cell.id="toolbarbutton"+this.id+this.buttonCount;
	cell.bid=id;
	cell.index=this.buttonCount;
	cell.toolBarId=this.id;
	cell.title=title;
	cell.isCheck=flag;
	cell.style.pixelHeight=this.height;
	var cellImg=null;
	if(icon!=null && icon!=""){
		cellImg=document.createElement("IMG");
		cellImg.src=icon;
		cellImg.align="absmiddle";
		cell.appendChild(cellImg);
		cellImg.style.pixelWidth=this.height;
		cellImg.style.pixelHeight=this.height;
		cell.icon=icon;
	}
	var _p=document.createElement("P");
	_p.style.display="inline";
	_p.innerHTML=" "+title;
	cell.onclick=ToolBar.buttonClick;
	cell.appendChild(_p);
	if(flag){
		ToolBar.addToggle(cell);
	}
	this.buttonCount++;
}

/*
*隐藏工具栏所有或指定按钮
*@param arr 按钮的索引数组或按钮id数组
*/
ToolBar.prototype.addSpliter=function(){
	var cell=this.htmlObj.rows[0].insertCell();
	cell.className="toolbarspliter";
	cell.innerHTML="<span ></span>";
	cell.type="spliter";
}
/*
*添加工具栏手柄
*/
ToolBar.prototype.addHandler=function(){
	var cell=this.htmlObj.rows[0].insertCell();
	cell.className="toolbarhandler";
	cell.innerHTML="<span ></span><span></span>";
	cell.type="spliter";
}
/*
*隐藏工具栏所有或指定按钮
*@param arr 按钮的索引数组或按钮id数组
*/
ToolBar.prototype.hideButton=function(arr){
	if(typeof(arr)=="object"){
		if(arr.length>0){
			if(typeof(arr[0])=="number"){
				for(var i=0;i<arr.length;i++){
					document.all("toolbarbutton"+this.id+arr[i]).style.display="none";
				}
			}else{
				for(var i=0;i<arr.length;i++){
					for(var j=0;j<this.htmlObj.rows[0].cells.length;j++){
						if(this.htmlObj.rows[0].cells[j].bid==arr[i]){
							this.htmlObj.rows[0].cells[j].style.display="none";
							break;
						}
					}
				}
			}
		}
	}
}
/*
*显示工具栏所有或指定按钮
*@param arr 按钮的索引数组或按钮id数组
*/
ToolBar.prototype.showButton=function(arr){
	if(typeof(arr)=="object"){
		if(arr.length>0){
			if(typeof(arr[0])=="number"){
				for(var i=0;i<arr.length;i++){
					document.all("toolbarbutton"+this.id+arr[i]).style.display="block";
				}
			}else{
				for(var i=0;i<arr.length;i++){
					for(var j=0;j<this.htmlObj.rows[0].cells.length;j++){
						if(this.htmlObj.rows[0].cells[j].bid==arr[i]){
							this.htmlObj.rows[0].cells[j].style.display="block";
							break;
						}
					}
				}
			}
		}
	}
}
/*
*使工具栏所有或指定按钮不可用
*@param arr 按钮的索引数组或按钮id数组
*/
ToolBar.prototype.disabledButton=function(arr){
	if(arr!=null){
		if(typeof(arr)=="object"){
			if(arr.length>0){
				if(typeof(arr[0])=="number"){
					for(var i=0;i<arr.length;i++){
						//document.all("toolbarbutton"+this.id+arr[i]).bDisabled=true;
						//document.all("toolbarbutton"+this.id+i).className="disabled";
						ToolBar.disable(document.all("toolbarbutton"+this.id+arr[i]),false);
					}
				}else{
					for(var i=0;i<arr.length;i++){
						for(var j=0;j<this.htmlObj.rows[0].cells.length;j++){
							if(this.htmlObj.rows[0].cells[j].bid==arr[i]){
								//this.htmlObj.rows[0].cells[j].bDisabled=true;
								ToolBar.disable(this.htmlObj.rows[0].cells[j]);
								break;
							}
						}
					}
				}
			}
		}
	}else{
		for(var i=0;j<this.htmlObj.rows[0].cells.length;i++){
			if(this.htmlObj.rows[0].cells[i].type=="button"){
				ToolBar.disable(this.htmlObj.rows[0].cells[i]);
			}
		}
	}
}
/*
*使工具栏所有或指定按钮可用
*@param arr 按钮的索引数组或按钮id数组
*/
ToolBar.prototype.enabledButton=function(arr){
	if(arr!=null){
		if(typeof(arr)=="object"){
			if(arr.length>0){
				if(typeof(arr[0])=="number"){
					for(var i=0;i<arr.length;i++){
						//document.all("toolbarbutton"+this.id+arr[i]).bDisabled=false;
						//document.all("toolbarbutton"+this.id+i).className="disabled";
						ToolBar.enable(document.all("toolbarbutton"+this.id+arr[i]));
					}
				}else{
					for(var i=0;i<arr.length;i++){
						for(var j=0;j<this.htmlObj.rows[0].cells.length;j++){
							if(this.htmlObj.rows[0].cells[j].bid==arr[i]){
								//this.htmlObj.rows[0].cells[j].bDisabled=false;
								ToolBar.enable(this.htmlObj.rows[0].cells[j]);
								break;
							}
						}
					}
				}
			}
		}
	}else{
		for(var i=0;j<this.htmlObj.rows[0].cells.length;i++){
			if(this.htmlObj.rows[0].cells[i].type=="button"){
				ToolBar.enable(this.htmlObj.rows[0].cells[i]);
			}
		}
	}
}




ToolBar.prototype.hide=function(){
	this.htmlObj.style.display="none";
}
ToolBar.prototype.show=function(){
	this.htmlObj.style.display="block";
}
//设置工具栏按钮单击运行的方法名称，方法有一个参数，将会被传入单击的按钮实例
ToolBar.prototype.setAction=function(actionName){
	this.action=actionName;
}
ToolBar.prototype.getAction=function(){
	return this.action;
}
ToolBar.buttonClick=function(){
	var buttonCell=event.srcElement;
	//if(buttonCell.disabled)
	while(buttonCell.tagName!="TD" ){
		buttonCell=buttonCell.parentElement;
	}
	ToolBar.currentButton=new ToolBarButton();
	ToolBar.currentButton.setId(buttonCell.bid);
	ToolBar.currentButton.setToolbarId(buttonCell.toolBarId);
	ToolBar.currentButton.setIndex(parseInt(buttonCell.index));
	ToolBar.currentButton.setType(buttonCell.btype);
	ToolBar.currentButton.setTitle(buttonCell.title);
	ToolBar.currentButton.setIcon(buttonCell.icon);
	ToolBar.currentButton.setValue(buttonCell.value);
	ToolBar.currentButton.setCheck(buttonCell.isCheck);
	ToolBar.currentButton.setHtmlObj(buttonCell);
	var bar=ToolBar.currentButton.getToolBar();//取回按钮所属工具栏实例
	if(bar.getAction()==null){
		var eventObject=eval(buttonCell.toolBarId+"_onclick");
		if(eventObject!=null){
			try{
				var action=eval(buttonCell.toolBarId+"_onclick(ToolBar.currentButton)");
			}catch(e){
				
			}
		}
	}else{
		var eventObject=eval(bar.getAction());
		if(eventObject!=null){
			eval(bar.getAction()+"(ToolBar.currentButton)");
		}
	}
}

ToolBar.toolbarMouseOver=function() {
	var toEl = ToolBar.getReal(window.event.toElement, "type", "button");
	var fromEl = ToolBar.getReal(window.event.fromElement, "type", "button");
	if (toEl == fromEl) return;
	var el = toEl;
	
	var bDisabled = el.bDisabled;
	bDisabled = (bDisabled != null); // 
	var cToggle = el.cToggle;
	cToggle = (cToggle == null?false:cToggle); // 
	if(cToggle){
		cToggle=el.value;
	}
	
	if (el.type == "button")
		el.onselectstart = new Function("return false");
	
	if ((el.type == "button") && !bDisabled &&!cToggle) {
		ToolBar.makeRaised(el);
		//ToolBar.makeGray(el,false);
	}
}

ToolBar.toolbarMouseOut=function() {
	var toEl = ToolBar.getReal(window.event.toElement, "type", "button");
	var fromEl = ToolBar.getReal(window.event.fromElement, "type", "button");
	if (toEl == fromEl) return;
	var el = fromEl;

	var bDisabled = el.bDisabled;
	bDisabled = (bDisabled != null); 

	var cToggle = el.cToggle;
	toggle_disabled = (cToggle != null); 

	if (cToggle && el.value) {
		ToolBar.makePressed(el);
		
		//ToolBar.makeGray(el,true);
	}
	else if ((el.type == "button") && !bDisabled) {
		ToolBar.makeFlat(el);
		//ToolBar.makeGray(el,true);
	}

}

ToolBar.toolbarMouseDown=function() {
	el = ToolBar.getReal(window.event.srcElement, "type", "button");
	
	var bDisabled = el.bDisabled;
	bDisabled = (bDisabled != null); 
	
	if ((el.type == "button") && !bDisabled) {
		ToolBar.makePressed(el)
	}
}

ToolBar.toolbarMouseUp=function() {
	el = ToolBar.getReal(window.event.srcElement, "type", "button");
	
	var bDisabled = el.bDisabled;
	bDisabled = (bDisabled != null); // 
	var cToggle = el.cToggle;
	cToggle = (cToggle == null?false:cToggle); // 
	if ((el.type == "button") && !bDisabled && !cToggle) {
		ToolBar.makeRaised(el);
	}
}


ToolBar.getReal=function(el, type, value) {
	temp = el;
	while ((temp != null) && (temp.tagName != "BODY")) {
		if (eval("temp." + type) == value) {
			el = temp;
			return el;
		}
		temp = temp.parentElement;
	}
	return el;
}

ToolBar.findChildren=function(el, type, value) {
	var children = el.children;
	var tmp = new Array();
	var j=0;
	
	for (var i=0; i<children.length; i++) {
		if (eval("children[i]." + type + "==\"" + value + "\"")) {
			tmp[tmp.length] = children[i];
		}
		tmp = tmp.concat(ToolBar.findChildren(children[i], type, value));
	}
	
	return tmp;
}

ToolBar.disable=function(el) {
	ToolBar.makeFlat(el);
	if (document.readyState != "complete") {
		window.setTimeout("ToolBar.disable(" + el.id + ")", 100);	// If document not finished rendered try later.
		return;
	}
	
	var bDisabled = el.bDisabled;
	
	bDisabled = (bDisabled != null); // If bDisabled atribute is present

	if (!bDisabled) {
		el.bDisabled = true;
		
		/*if (document.getElementsByTagName) {	// IE5
			el.innerHTML =	"<span style='background: buttonshadow; filter: chroma(color=red) dropshadow(color=buttonhighlight, offx=1, offy=1); width: 100%; height: 100%; text-align: center;'>" +
							"<span style='filter: mask(color=red); width: 100%; height: 100%; text-align: center;'>" +
							el.innerHTML +
							"</span>" +
							"</span>";
		}
		else { // IE4
			el.innerHTML =	'<span style="background: buttonshadow; width: 100%; height: 100%; text-align: center;">' +
							'<span style="filter:Mask(Color=buttonface) DropShadow(Color=buttonhighlight, OffX=1, OffY=1, Positive=0); height: 100%; width: 100%%; text-align: center;">' +
							el.innerHTML +
							'</span>' +
							'</span>';
		}
		
		*/
		if (el.onclick != null) {
			el.cDisabled_onclick = el.onclick;
			el.onclick = null;
		}
	}
}

ToolBar.enable=function(el) {
	var bDisabled = el.bDisabled;
	
	bDisabled = (bDisabled != null); // If bDisabled atribute is present
	
	if (bDisabled) {
		el.bDisabled = null;
		//el.innerHTML = el.children[0].children[0].innerHTML;

		if (el.cDisabled_onclick != null) {
			el.onclick = el.cDisabled_onclick;
			el.cDisabled_onclick = null;
		}
	}
}

ToolBar.addToggle=function(el) {
	var bDisabled = el.bDisabled;
	
	bDisabled = (bDisabled != null); // 
	
	var cToggle = el.cToggle;
	
	cToggle = (cToggle != null); // 

	if (!cToggle && !bDisabled) {
		el.cToggle = true;
		
		if (el.value == null)
			el.value = 0;		// 
		
		if (el.onclick != null)
			el.cToggle_onclick = el.onclick;	// 
		else 
			el.cToggle_onclick = "";

		el.onclick = new Function("ToolBar.toggle(" + el.id +"); " + el.id + ".cToggle_onclick();");
	}
}

ToolBar.removeToggle=function(el) {
	var bDisabled = el.bDisabled;
	
	bDisabled = (bDisabled != null); // 
	
	var cToggle = el.cToggle;
	
	cToggle = (cToggle != null); //
	
	if (cToggle && !bDisabled) {
		el.cToggle = null;

		if (el.value) {
			ToolBar.toggle(el);
		}

		ToolBar.makeFlat(el);
		
		if (el.cToggle_onclick != null) {
			el.onclick = el.cToggle_onclick;
			el.cToggle_onclick = null;
		}
	}
}

ToolBar.toggle=function(el) {
	el.value = !el.value;
	window.status=el.value;
	if(!el.value) ToolBar.makeRaised(el);
}


ToolBar.makeFlat=function(el) {
	el.className="buttonflat";
}

 ToolBar.makeRaised=function(el) {
	el.className="buttonraised";
}

 ToolBar.makePressed=function(el) {
	el.className="buttonpressed";

}

 ToolBar.makeGray=function(el,b) {
	var filtval;
	if (b)
		filtval = "";//"gray()";
	else
		filtval = "";
	var imgs = ToolBar.findChildren(el, "tagName", "IMG");
		
	for (var i=0; i<imgs.length; i++) {
		imgs[i].style.filter = filtval;
	}

}

ToolBar.currentButton=null;
function ToolBarButton(){
	this.id="";
	this.toolbarId="";
	this.index=0;
	this.type=0;
	this.check=false;
	this.text="";
	this.icon="";
	this.title="";
	this.value="";
	this.htmlObj=null;
	this.toolBar=null;
}
ToolBarButton.prototype.setId=function(id){
	this.id=id;
}
ToolBarButton.prototype.getId=function(){
	return this.id;
}

ToolBarButton.prototype.setToolbarId=function(toolbarId){
	this.toolbarId=toolbarId;
	this.toolBar=eval(this.toolbarId);
}
ToolBarButton.prototype.getToolbarId=function(){
	return this.toolbarId;
}
ToolBarButton.prototype.setIndex=function(index){
	this.index=index;
}
ToolBarButton.prototype.getIndex=function(){
	return this.index;
}
ToolBarButton.prototype.setType=function(type){
	this.type=type;
}
ToolBarButton.prototype.getType=function(){
	return this.type;
}
ToolBarButton.prototype.setText=function(text){
	this.text=text;
}
ToolBarButton.prototype.getText=function(){
	return this.text;
}
ToolBarButton.prototype.setIcon=function(icon){
	this.icon=icon;
}
ToolBarButton.prototype.getIcon=function(){
	return this.icon;
}
ToolBarButton.prototype.setTitle=function(title){
	this.title=title;
}
ToolBarButton.prototype.getTitle=function(){
	return this.title;
}
ToolBarButton.prototype.setValue=function(value){
	this.value=value;
}
ToolBarButton.prototype.getValue=function(){
	return this.value;
}
ToolBarButton.prototype.setCheck=function(flag){
	return this.check=flag;
}
ToolBarButton.prototype.isCheck=function(){
	return this.check;
}
ToolBarButton.prototype.setHtmlObj=function(htmlObj){
	this.htmlObj=htmlObj;
}

ToolBarButton.prototype.getHtmlObj=function(){
	return this.htmlObj;
}
ToolBarButton.prototype.getToolBar=function(){
	return this.toolBar;
}

