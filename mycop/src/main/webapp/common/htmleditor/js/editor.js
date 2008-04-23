/*************************************
编辑器组件
汤启礼2004-4-14
**************************************/
var EditorTemp=null;
var perfix = "eform_data_";
Editor.currentEditor=null;
Editor.uploadFile="";//上传文件程序文件
Editor.context="";//服务器上下文
function Editor(id,width,height){
	this.id=id;
	if(width==null || width<=0){
		this.width=400;
	}else{
		this.width=width;
	}
	if(height==null || height<=0){
		this.height=500;
	}else{
		this.height=height;
	}
	this.mode=true;
	this.value="";
	this.htmlCode="";
	this.toolbarContainer=null;
	this.container=null;
	this.viewSource=false;
	this.toolbars=new Array();
	window[this.id]=this;
	this.init();	

}

Editor.prototype.getId=function(){
	return this.id;
}
Editor.prototype.getWidth=function(){
	return this.width;
}
Editor.prototype.getHeight=function(){
	return this.width;
}
Editor.prototype.getHeight=function(){
	return this.height;
}
Editor.prototype.getValue=function(){
	this.editorHidden.value=this.contentFrame.document.body.outerHTML;
	return this.editorHidden.value;
}
Editor.prototype.setValue=function(html){
	this.contentFrame.document.open()
	this.contentFrame.document.write(html);
	this.contentFrame.document.close();
	//this.contentFrame.document.body.innerHTML=html;	
}
Editor.prototype.getHtmlCode=function(){
	return this.htmlCode;
}
function addZero(eform_count){
	if(eform_count<10)
		return "000"+eform_count;
	else if(eform_count<100)
     return "00"+eform_count;
    else if(eform_count<1000)
     return "0"+eform_count;
    else
     return eform_count;
}
//byt add
Editor.prototype.putElementsToHidden=function(actionType){
	var eform_count = 0;//记录器,	
	if(this.viewSource) {
		alert("源代码查看模式下不能提交");
		return false;
	}		
	for(var i=0;i<this.contentFrame.document.all.length;i++){
		var tagTmp = this.contentFrame.document.all[i].tagName;		
		if(tagTmp=="INPUT"||tagTmp=="TEXTAREA"||tagTmp=="SELECT"){
		 this.contentFrame.document.all[i].name=perfix+(addZero(eform_count++));	
		}		 	
}//for
		window.document.bytfrm.htmls.value = this.getValue();
		if(window.document.bytfrm.templateName.value==""){
		alert("模板名不能为空");
		return false;
		}
		if(window.document.bytfrm.htmls.value==""){
		alert("模板内容不能空");
		return false;
		}		
		window.document.bytfrm.saveTemplate.value = "保存";
		
		window.document.bytfrm.action = window.document.bytfrm.action +"?action="+actionType;
		
		window.document.bytfrm.submit();	
}
Editor.prototype.getElements=function(){
	for(var i=0;i<this.contentFrame.document.all.length;i++){
		if(this.contentFrame.document.all[i].tagName=="INPUT"){
			alert('if');			
		  alert(this.contentFrame.document.all[i].title);
		  alert(this.contentFrame.document.all[i].value);
		  alert('-----if----');		
	    }else
		alert("else");
}
}
Editor.prototype.init=function(){
	document.write("<div id=\"editorcontainer"+this.id+"\" class=\"editor\" ></div>");
	this.container=eval("document.all.editorcontainer"+this.id);
	this.container.cid=this.id;
	this.container.style.display="none";
	this.container.style.pixelWidth=this.width;
	this.container.style.pixelHeight=this.height;
	var editorTable=document.createElement("TABLE");
	this.container.appendChild(editorTable);
	editorTable.cellPadding=0;
	editorTable.cellSpacing=0;
	editorTable.border=0;
	editorTable.width="100%";
	editorTable.height="100%";


	var tbRow=editorTable.insertRow();
	var tbCell=tbRow.insertCell();
	tbCell.className="toolbarcontainer";
	tbCell.valign="top";
	this.toolbarContainer=tbRow;//记录工具栏容器
	var cRow=editorTable.insertRow();
	var cCell=cRow.insertCell();
	this.initToolbar();
	this.initContent();
	this.container.onmouseover=function(){
		var editorTmp=eval(this.cid);
		Editor.currentEditor=editorTmp;
	}
	this.container.style.display="block";
}
/*
*初始化工具栏
*/
Editor.prototype.initToolbar=function(){
	var tbCell=this.container.children[0].rows[0].cells[0];
	var cCell=this.container.children[0].rows[1].cells[0];

	var tb=new ToolBar(this.id+"_editorToolbar1",tbCell);
	tb.addHandler();
	tb.addImgButton("delete",context+"/common/htmleditor/images/editor/delete.gif","删除");
	tb.addImgButton("paste",context+"/common/htmleditor/images/editor/paste.gif","粘贴");
	tb.addImgButton("cut",context+"/common/htmleditor/images/editor/cut.gif","剪切");
	tb.addImgButton("copy",context+"/common/htmleditor/images/editor/copy.gif","复制");
	tb.addImgButton("undo",context+"/common/htmleditor/images/editor/undo.gif","撤消");
	tb.addImgButton("redo",context+"/common/htmleditor/images/editor/redo.gif","恢复");
	tb.addSpliter();
	tb.addImgButton("intable",context+"/common/htmleditor/images/editor/intable.gif","插入表格");
	tb.addImgButton("inlink",context+"/common/htmleditor/images/editor/inlink.gif","插入超级连接");
	tb.addImgButton("inimg",context+"/common/htmleditor/images/editor/inimg.gif","插入图片");
	tb.addImgButton("inhr",context+"/common/htmleditor/images/editor/inhr.gif","插入水平线");
	tb.addImgButton("inicon",context+"/common/htmleditor/images/editor/smile.gif","插入图释");
	tb.addImgButton("inchar",context+"/common/htmleditor/images/editor/inchar.gif","插入字符");
	tb.addSpliter();
	tb.addImgButton("forecolor",context+"/common/htmleditor/images/editor/fontcolor.gif","文字景色");
	tb.addImgButton("backbolor",context+"/common/htmleditor/images/editor/fontbgcolor.gif","文字背景色");
	tb.addImgButton("backgroundbolor",context+"/common/htmleditor/images/editor/pagecolor.gif","网页背景色");

	//tb.addSpliter();
	tb.setAction("editorToolbar1_onclick");
	this.toolbars[0]=tb;
	tb=new ToolBar(this.id+"_editorToolbar2",tbCell);
	tb.addHandler();
	tb.addImgButton("bold",context+"/common/htmleditor/images/editor/bold.gif","加粗",true);
	tb.addImgButton("italic",context+"/common/htmleditor/images/editor/italic.gif","斜体",true);
	tb.addImgButton("underline",context+"/common/htmleditor/images/editor/underline.gif","下划线",true);
	tb.addImgButton("strikethrough",context+"/common/htmleditor/images/editor/strikethrough.gif","删除线",true);
	tb.addSpliter();
	tb.addImgButton("leftalign",context+"/common/htmleditor/images/editor/leftalign.gif","左对齐");
	tb.addImgButton("center",context+"/common/htmleditor/images/editor/center.gif","居中");
	tb.addImgButton("rightalign",context+"/common/htmleditor/images/editor/rightalign.gif","右对齐");
	tb.addSpliter();

	tb.addImgButton("numlist",context+"/common/htmleditor/images/editor/numlist.gif","编号");
	tb.addImgButton("bullist",context+"/common/htmleditor/images/editor/bullist.gif","项目符号");
	tb.addImgButton("indent",context+"/common/htmleditor/images/editor/indent.gif","减少缩进量");
	tb.addImgButton("outdent",context+"/common/htmleditor/images/editor/outdent.gif","增加缩进量");
	tb.addSpliter();
	tb.addImgButton("delformat",context+"/common/htmleditor/images/editor/removeformat.gif","移除格式");
	//tb.addImgButton("test",context+"/common/htmleditor/images/editor/outdent.gif","test");
	tb.setAction("editorToolbar2_onclick");
	this.toolbars[1]=tb;

	tb=new ToolBar(this.id+"_editorToolbar3",tbCell);
	tb.addHandler();
	var selStr='<select ID="formatSelect" class="TBGen" title="段落格式" onchange="_setFormat()" style="font: icon; width: 80px;">'
     selStr+= '<option>段落格式</option>'
      selStr+= '<option VALUE="&lt;P&gt;">普通 '
      selStr+= '<option VALUE="&lt;PRE&gt;">已编排格式 '
      selStr+= '<option VALUE="&lt;H1&gt;">标题一 '
      selStr+= '<option VALUE="&lt;H2&gt;">标题二 '
      selStr+= '<option VALUE="&lt;H3&gt;">标题三 '
      selStr+= '<option VALUE="&lt;H4&gt;">标题四 '
      selStr+= '<option VALUE="&lt;H5&gt;">标题五 '
      selStr+= '<option VALUE="&lt;H6&gt;">标题六 '
      selStr+= '<option VALUE="&lt;H7&gt;">标题七 '
    selStr+= '</select>'
	tb.addButton("pformat",selStr);

  

	var lable=document.createElement("SPAN");
	lable.innerText="字体"
	tb.addButton("fonelable",lable);
	selStr='<select language="javascript" class="TBGen" id="FontName" title="字体名" onchange="_setFontName()">'
    selStr+= '  <option class="heading" selected>字体 '
    selStr+='  <option value="宋体">宋体 '
    selStr+= ' <option value="黑体">黑体 '
    selStr+='  <option value="楷体_GB2312">楷体 '
    selStr+='  <option value="仿宋_GB2312">仿宋 '
    selStr+='  <option value="隶书">隶书 '
    selStr+='  <option value="幼圆">幼圆 '
    selStr+='  <option value="新宋体">新宋体 '
    selStr+='  <option value="细明体">细明体 '
    selStr+='  <option value="Arial">Arial '
    selStr+='  <option value="Arial Black">Arial Black '
    selStr+='  <option value="Arial Narrow">Arial Narrow '
    selStr+='  <option value="Bradley Hand	ITC">Bradley Hand ITC '
    selStr+='  <option value="Brush Script	MT">Brush Script MT '
    selStr+='  <option value="Century Gothic">Century Gothic '
    selStr+='  <option value="Comic Sans MS">Comic Sans MS '
    selStr+='  <option value="Courier">Courier '
    selStr+='  <option value="Courier New">Courier New '
    selStr+='  <option value="MS Sans Serif">MS Sans Serif '
    selStr+='  <option value="Script">Script '
    selStr+='  <option value="System">System '
    selStr+='   <option value="Times New Roman">Times New Roman '
    selStr+='  <option value="Viner Hand ITC">Viner Hand ITC '
    selStr+='  <option value="Verdana">Verdana '
    selStr+='  <option value="Wide	Latin">Wide Latin '
    selStr+='  <option value="Wingdings">Wingdings</option>'
    selStr+='</select>'
	tb.addButton("fontfalimy",selStr);
	selStr='<select language="javascript" class="TBGen" id="FontSize" title="字号大小" onchange="_setFontSize()">'
	selStr+=' <option class="heading" selected>字号 '
	selStr+=' <option value="7">一号 '
	selStr+=' <option value="6">二号 '
	selStr+=' <option value="5">三号 '
	selStr+=' <option value="4">四号 '
	selStr+=' <option value="3">五号 '
	selStr+=' <option value="2">六号 '
	selStr+=' <option value="1">七号</option>'
	selStr+='</select>'
	tb.addButton("fontsize",selStr);
	
	tb.setAction("editorToolbar3_onclick");

	/*
	tb.addSpliter();
	var strCheckbox='<input type="checkbox" name="" onclick="Editor.changeState()">'
	tb.addButton("html",strCheckbox);	
	*/
	this.toolbars[2]=tb;


	
	tbCell.style.pixelHeight=ToolBar.HEIGHT*3+14;
	cCell.style.pixelHeight=this.height-tbCell.style.pixelHeight;
}
/*
*初始化编辑框
*/
Editor.prototype.initContent=function(){
	var cCell=this.container.children[0].rows[1].cells[0];	
	var src="";//Editor.context+"/common/htmleditor/empty.htm";
	var ifrmHTML="<iframe id='editor_frame_"+this.id+"' cid='"+this.id+"' width=100% height=100% classname='editorframe'  name='editor_frame_"+this.id+"' src='"+src+"'></iframe>";
	cCell.innerHTML=ifrmHTML;
	this.contentFrame=eval("editor_frame_"+this.id);
	var contentFrameObj=eval("document.all.editor_frame_"+this.id);	
	this.contentFrame.cid=this.id;

	this.contentFrame.document.designMode="On";
	//this.contentFrame.document.open()
	//this.contentFrame.document.write("<body>gggggggggggggggggggggggg</body>");
	//this.contentFrame.document.close();
	this.contentFrame.document.onmouseup=function(){
		var editor=Editor.currentEditor;		
	};

	var strHidden="<textarea style='display:none' id='editorContent_"+this.id+"'></textarea>";
	document.write(strHidden);
	//var editorHidden=document.createElement("TEXTAREA");
	this.editorHidden=eval("document.all.editorContent_"+this.id);
	//editorHidden.style.display="none";
	//this.container.appendChild(editorHidden);

	contentFrameObj.onblur=function(){
		var editorTmp=eval(this.cid);
		if(editorTmp.mode){
			editorTmp.editorHidden.value=editorTmp.contentFrame.document.body.outerHTML;
		}else{
			editorTmp.editorHidden.value=editorTmp.contentFrame.document.body.innerText;
		}
	}
	contentFrameObj.onfocus=function(){
		var editorTmp=eval(this.cid);
		Editor.currentEditor=editorTmp;
		var doc=Editor.currentEditor.contentFrame.document;
		if(doc.body==null){
			doc.open();
			alert()
			doc.write("<body style='font-size:100'></body>");
			doc.close();
		}
		doc.body.style.fontSize=12;
	}
	//this.contentFrame.document.open();
	//this.contentFrame.document.write("<body><font size='1'>fghh</font></body>");
	//this.contentFrame.document.body.style.fontSize=1;

}
Editor.prototype.setMode=function(mode){
	this.mode=mode;
	var contentData="";
	if(!this.mode){
		contentData=this.contentFrame.document.body.innerText;
		this.contentFrame.document.body.innerHTML=contentData;
	}else{
		contentData=this.contentFrame.document.body.outerHTML;
		this.contentFrame.document.body.innerText=contentData;
	}
	this.contentFrame.focus();
}
/*
*设置编辑器状态
*@param state boolean类型 true设置为只读,false设置为可写
*@param flag boolean类型 
*/
Editor.prototype.setState=function(state,flag){
	if(state==null) state=true;
	if(flag==null) flag=true;
	if(flag==true){
		//隐藏工具拦
		this.toolbarContainer.style.display="none";
	}else{
		//显示工具拦
		this.toolbarContainer.style.display="block";
	}
	
	
	if(!state){
		//设定为只读
		this.contentFrame.document.designMode="Off";
	}else{
		this.contentFrame.document.designMode="On";
	}
}
/*
*设置编辑器状态
*@param state boolean类型 true设置为只读,false设置为可写
*@param flag boolean类型 
*/
Editor.prototype.setReadOnly=function(flag){
	this.setState(false,flag);
}

Editor.prototype.executeCommand=function(command,value){
	var ret=true;
	if(value==null){
		ret=this.contentFrame.document.execCommand(command);
	}else{
		ret=this.contentFrame.document.execCommand(command,false,value);
	}
	return ret;
}
Editor.prototype.focus=function(){
	this.contentFrame.focus();
}
Editor.prototype.insert=function(type){
	var strInsert="";
	var range = this.contentFrame.document.selection.createRange();
	if (this.contentFrame.document.selection.type == 'Control') {
		if(range.length==1){
			Editor.showDialog(type,range(0));
		}
	}else{
		strInsert=Editor.showDialog(type);
		range.pasteHTML(strInsert);
	}
}
Editor.prototype.setToolbarState=function(){
	//alert("设置工具栏状态")
}

//公共方法
Editor.changeState=function(){
	var editor=Editor.currentEditor;
	if(editor.viewSource){
		var strText=editor.contentFrame.document.body.innerText;
		editor.contentFrame.document.body.innerHTML=strText;
	}else{
		var strHtml=editor.contentFrame.document.body.innerHTML;
		editor.contentFrame.document.body.innerText=strHtml;
	}
	editor.viewSource=!editor.viewSource;
}
Editor.showDialog=function(type,obj){
   var ret="";
   var strUrl,strAttr;
   var openType="dialog";
   switch(type)
   {
		case "table":
			strUrl = context+"/common/htmleditor/js/intable.htm";
			strAttr = "status:no;dialogWidth:400px;dialogHeight:360px;help:no";
			break;
		case "icon":
			strUrl = context+"/common/htmleditor/js/inicon.htm";
			strAttr = "status:no;dialogWidth:300px;dialogHeight:350px;help:no";
			break;
		case "char":
			strUrl = context+"/common/htmleditor/js/inchar.htm";
			strAttr = "status:no;dialogWidth:450px;dialogHeight:290px;help:no";
			break;
		case "img":
			strUrl = context+"/common/htmleditor/js/inimg.htm";
			strAttr = "status:no;dialogWidth:400px;dialogHeight:200px;help:no";
			break;
		case "link":
			strUrl = context+"/common/htmleditor/js/inlink.htm";
			strAttr = "status:no;dialogWidth:400px;dialogHeight:200px;help:no";
			break;
		case "attachment":   
			strUrl = Editor.uploadFile;
			openType="window";
			strAttr = "width=400px;height=200px";
			break;
	}
	if(openType=="window"){
		window.open(strUrl,null,strAttr);
	}else{
		ret = window.showModalDialog(strUrl, obj, strAttr);
	}
	if(ret==null) ret="";
	return ret;
}
function _setFormat(){
	var formatSel=event.srcElement;
	var editor=Editor.currentEditor;
	editor.executeCommand("FormatBlock",formatSel[formatSel.selectedIndex].value);

}

function _setFontName(){
	var fontNameSel=event.srcElement;
	var editor=Editor.currentEditor;
	editor.executeCommand("fontname",fontNameSel[fontNameSel.selectedIndex].value);

}
function _setFontSize(){
	var fontSizeSel=event.srcElement;
	var editor=Editor.currentEditor;
	editor.executeCommand("fontsize",fontSizeSel[fontSizeSel.selectedIndex].value);

}

function _setForeColor(color){
	var editor=Editor.currentEditor;
	editor.executeCommand("ForeColor",color);
	editor.focus();
}
function _setBackColor(color){
	var editor=Editor.currentEditor;
	editor.executeCommand("BackColor",color);
	editor.focus();
}
function _setBackGroundColor(color){
	var editor=Editor.currentEditor;
	//editor.executeCommand("BackgroundColor",color);
	editor.contentFrame.document.body.bgColor=color;
	editor.focus();
}

function editorToolbar1_onclick(button){
	var editor=Editor.currentEditor;
	//editor.focus();
	switch(button.getIndex()){
		case 0:
			editor.executeCommand("Delete");
			/*if(button.isCheck()){
				editor.setMode(button.getValue());
			}*/
			break;
		case 1:
			editor.executeCommand("Paste");
			break;
		case 2:
			editor.executeCommand("Cut");
			break;
		case 3:
			editor.executeCommand("Copy");
			break;
		case 4:
			editor.executeCommand("Undo");
			break;
		case 5:
			editor.executeCommand("Redo");
			break;
		case 6:
			editor.focus();
			editor.insert("table");
			break;
		case 7:
			editor.focus();
			editor.insert("link");
			break;
		case 8:
			editor.focus();
			editor.insert("img");
			break;
		case 9:
			editor.focus();
			editor.executeCommand("InsertHorizontalRule");
			break;
		case 10:
			editor.focus();
			editor.insert("icon");
			break;
		case 11:
			editor.focus();
			editor.insert("char");
			break;
		/*case 12:
			editor.focus();
			editor.insert("attachment");
			break;
*/
		case 12:
			var picker=ColorPicker.createColorPicker(context+"/common/htmleditor/js/");
			picker.show(button.getHtmlObj(),"_setForeColor");	
			break;
		case 13:
			var picker=ColorPicker.createColorPicker(context+"/common/htmleditor/js/");
			picker.show(button.getHtmlObj(),"_setBackColor");	
			break;
		case 14:
			var picker=ColorPicker.createColorPicker(context+"/common/htmleditor/js/");
			picker.show(button.getHtmlObj(),"_setBackGroundColor");	
			break;

	}
}
function editorToolbar2_onclick(button){
	var editor=Editor.currentEditor;
	editor.focus();
	switch(button.getIndex()){
		case 0:
			editor.executeCommand("Bold");
			break;
		case 1:
			editor.executeCommand("Italic");
			break;
		case 2:
			editor.executeCommand("Underline");
			break;
		case 3:
			editor.executeCommand("Strikethrough");
			break;
		case 4:
			editor.executeCommand("JustifyLeft");
			break;
		case 5:
			editor.executeCommand("JustifyCenter");
			break;
		case 6:
			editor.executeCommand("JustifyRight");
			break;
		case 7:
			editor.executeCommand("InsertOrderedList");
			break;
		case 8:
			editor.executeCommand("InsertUnorderedList");
			break;
		case 9:
			editor.executeCommand("Indent");
			break;
		case 10:
			editor.executeCommand("Outdent");
			break;
		case 11:
			editor.executeCommand("RemoveFormat");
			break;
	}
	
}
function editorToolbar3_onclick(button){
	
}