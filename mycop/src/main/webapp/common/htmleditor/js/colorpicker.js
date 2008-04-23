/*************************************
颜色选择器组件
汤启礼
**************************************/
ColorPicker.instance=null;//唯一实例
//创建唯一实例
ColorPicker.createColorPicker=function(dir){
	if(dir==null) dir="";
	if(document.all.colorPickerContainer==null && ColorPicker.instance==null){
		var strObj="<div id='colorPickerContainer' class=\"colorpicker\" style=\"width:220;height:140;display:none;position:absolute;border-top: 0px solid black;border-left: 0px solid black;	border-bottom: 0px solid black;	border-right: 0px solid black;\"><iframe style=\"\" name=\"colorPicker\" id=\"colorPicker\" width=100% height=100% src=\""+dir+"colorpicker.htm\"></iframe></div>";
		document.body.insertAdjacentHTML("BeforeEnd", strObj);
		ColorPicker.instance=new ColorPicker();
	}
	return ColorPicker.instance;

}
ColorPicker.getInstance=function(){
	return ColorPicker.instance;
}

function ColorPicker(){
	this.htmlObj=document.all.colorPickerContainer;
	this.color="";
	this.func="";
}
ColorPicker.prototype.setColor=function(color){
	this.color=color;
}
ColorPicker.prototype.getColor=function(){
	return this.color;
}
ColorPicker.prototype.hide=function(){
	this.htmlObj.style.display="none";
}
ColorPicker.prototype.show=function(obj,func){
	var rect=null;
	if(obj!=null){
		rect=new Rect(obj);
	}
	var left,top;
	var borders =Rect.getBorderSizes(this.htmlObj);
	this.func=func;
	if(rect!=null){
		if (rect.top + rect.height + this.htmlObj.offsetHeight + borders.top + borders.bottom <= document.body.clientHeight + document.body.scrollTop)
			top = rect.top + rect.height;
		else if (rect.top - this.htmlObj.offsetHeight >= document.body.scrollTop)
			top = rect.top - this.htmlObj.offsetHeight;
		else if (document.body.clientHeight >= this.htmlObj.offsetHeight + borders.top + borders.bottom)
			top = document.body.clientHeight + document.body.scrollTop - this.htmlObj.offsetHeight - borders.top - borders.bottom;
		else
			top = document.body.scrollTop;

		if (rect.left + this.htmlObj.offsetWidth <= document.body.clientWidth + document.body.scrollLeft)
			left = rect.left;
		else if (document.body.clientWidth >= this.htmlObj.offsetWidth + borders.left + borders.right)
			left = document.body.clientWidth + document.body.scrollLeft - this.htmlObj.offsetWidth - borders.left - borders.right;
		else
			left = document.body.scrollLeft;
	}else{
		left=(document.body.clientWidth-220)/2;
		top=(document.body.clientHeight-140)/2;
	}
	this.htmlObj.style.pixelLeft=left;
	this.htmlObj.style.pixelTop=top;
	this.htmlObj.style.display="block";
}

//产生边界对象，用于定位
// 有三种调用方式
function Rect() {
	if (arguments.length == 1) {
		this.left = Rect.leftPos(arguments[0]);
		this.top  = Rect.topPos(arguments[0]);
		this.width  = arguments[0].offsetWidth;
		this.height = arguments[0].offsetHeight;
	}
}

Rect.topPos=function(el) {
	return Rect.doPosLoop(el, "Top");
}

Rect.leftPos=function(el) {
	return Rect.doPosLoop(el, "Left");
}

Rect.doPosLoop=function(el, val) {
	var temp = el;
	var x = temp["offset" + val];
	while (temp.tagName!="BODY") {
		temp = temp.offsetParent;
		x += temp["offset" + val];
	}
	return x;
}
// 获取表格边框大小
Rect.getBorderSizes=function(obj) {
	var o = new Object();
	
	if (document.getElementById) {
		o.top    = parseInt(obj.currentStyle.borderTopWidth);
		o.left   = parseInt(obj.currentStyle.borderLeftWidth);
		o.right  = parseInt(obj.currentStyle.borderRightWidth);
		o.bottom = parseInt(obj.currentStyle.borderBottomWidth);
	}
	return o;
}
//回调方法
function callbackPickColor(color,blFlag){
	if(blFlag){
		ColorPicker.instance.setColor(color);	
		if(ColorPicker.instance.func!=null && ColorPicker.instance.func!=""){
			eval(ColorPicker.instance.func+"(\""+color+"\")");
		}else{
			if(window.onpickcolor!=null){
				window.onpickcolor(color);
			}
		}
	}
	ColorPicker.instance.hide();
}