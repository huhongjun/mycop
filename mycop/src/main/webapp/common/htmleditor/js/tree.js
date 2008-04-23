/*****************************************************************************
' 程序功能：动态数据树的生成,数据通过xml数据包来传输,数据可以从数据库获取
' 实现节点增加、删除、移动
' 作	者：汤启礼(carry)
' 创建时间：2002/01/15
'*****************************************************************************/

var doc=document
var nodeCount=0
var preDivObj=null
var currentFolderObj=null					//当前选中的folder对象
var nodeArr=new Array();
var curx,cury
var FOLDER_DOC_ICON="../images/treeimage/doc.gif"

var FOLDER_FOLDER_ICON="../images/treeimage/folder.gif"
var FOLDER_FOLDEROPEN_ICON="../images/treeimage/folderopen.gif"

var FOLDER_OPEN_ICON="../images/treeimage/open.gif"
var FOLDER_LASTOPEN_ICON="../images/treeimage/lastopen.gif"

var FOLDER_CLOSE_ICON="../images/treeimage/close.gif"
var FOLDER_LASTCLOSE_ICON="../images/treeimage/lastclose.gif"

var FOLDER_LASTNODE_ICON="../images/treeimage/lastnode.gif"
var FOLDER_NODE_ICON="../images/treeimage/node.gif"

var FOLDER_VERT_ICON="../images/treeimage/vertline.gif"
var FOLDER_BLANK_ICON="../images/treeimage/blank.gif"


function Folder(parentobj,itemname,type,icon) //constructor 
{ 
  //固定数据
  this.itemname=itemname;
  // 假定没有指定图标，指定一个缺省图标
  if( icon == null ) {
	this.folderIconType = "iGeneric.gif";
  } else {
	this.folderIconType = icon;
  }

  this.type=type					//节点类型，文件夹或文件
  this.level=0						//级别
  this.id =""						//唯一的id
  this.layerid=""					//层次id
  this.parentId=""
  this.parent =parentobj			//父亲对象
  //this.iconImg =""				//打开或关闭图标
  //this.nodeImg = 0				//打开或关闭图标
  //this.nodeImgSrc="../images/treeimage/" + this.nodeImg 
  //this.isLastNode = 0 
  this.childAccount=0				//如果有子，表示子的数量
  //dynamic data 
  this.isOpen =false 
  this.typeIconSrc = "../images/treeimage/" + this.folderIconType   
  this.index=0
  this.parentIsLastNode=false		//父是否为最后一个子
  this.isLastNode=false				//父是否为最后一个子
  this.preElement=null
  this.haveData=false				//数据是否已经存在
  this.haveChild="NO"				//是否有子
  this.firstChildIndex=""			//
  this.handleFile=""				//处理数据文件
  this.tarGetFile=""				//单击节点时调用的文件
  this.event=null					//单击时的时间对象
  //this.children = new Array 
 
  //方法 
  this.addChild = addChild								//添加子项
  this.drawFolder=drawFolder							//生成文件夹
  this.createIndex =createIndex							//返回对象生成的序列号
  this.nodeClick=nodeClick								//单击节点
  this.getData=getData									//获取动态数据
  this.clickAction = clickAction						//单击的操作
  this.moveToFolder=moveToFolder						//移动文件夹
  this.refresh=refresh									//刷新
  this.contextMenu=contextMenu							//右键菜单(预留)
  this.rename=rename
  this.hideChildren=hideChildren						//关闭子项
  this.showChildren=showChildren						//打开子项

} 

Folder.prototype.getTarget=function(){
	return this.tarGetFile;
}
Folder.prototype.getTarget=function(){
	return this.tarGetFile;
}



//添加子项
function addChild()
{
	var i=0
	var str_NodeId=""
	var str_NodeName=""
	var str_NodeType=""
	var str_NodeIcon=""
	var str_HandleFile=""
	var str_TargetFile=""
	var subContainer=null
	var strItem=""
	var preElement=null
	var retFolder=null
	var xmldom=this.getData()
	var elementSubs=xmldom.getElementsByTagName("NODE")
	var elementLen=elementSubs.length
	this.childAccount=elementLen
	this.isOpen=true
	this.haveData=true

	document.all("tb"+this.id).insertAdjacentHTML("AfterEnd","<table height=0 border=0 cellspacing='0' cellpadding='0' id=subtb"+this.id+"><tr><td id='tdsub"+this.id+"'></td></tr></table>")
	for(i=0;i<elementLen;i++)
	{
		itemSub=elementSubs(i).getElementsByTagName("NODEID");
        str_NodeId=itemSub(0).text;
		itemSub=elementSubs(i).getElementsByTagName("LAYERID");
        str_LayerId=itemSub(0).text;
		itemSub=elementSubs(i).getElementsByTagName("NODENAME");
        str_NodeName=itemSub(0).text;
		itemSub=elementSubs(i).getElementsByTagName("PARENT");
        str_NodeParent=itemSub(0).text;
		itemSub=elementSubs(i).getElementsByTagName("NODETYPE");
        str_NodeType=itemSub(0).text;
		itemSub=elementSubs(i).getElementsByTagName("ICON");
        str_NodeIcon=itemSub(0).text;
		itemSub=elementSubs(i).getElementsByTagName("HANDLEFILE");
        str_HandleFile=itemSub(0).text;
		itemSub=elementSubs(i).getElementsByTagName("TARGETFILE");
        str_TargetFile=itemSub(0).text;
		itemSub=elementSubs(i).getElementsByTagName("HAVECHILD");
        str_HaveChild=itemSub(0).text;

		retFolder=addFolder(this,str_NodeName,str_NodeType,str_NodeIcon,"")	//初始化
		retFolder.id=str_NodeId
		retFolder.layerid=str_LayerId
		retFolder.parentId=str_NodeParent
		retFolder.level=this.level+1
		retFolder.createIndex()
	    retFolder.handleFile=str_HandleFile
	    retFolder.tarGetFile=str_TargetFile
	    retFolder.HvaeChild=str_HaveChild

		if(i==0){
			retFolder.preElement=document.all("td"+this.id)
			this.firstChildIndex=retFolder.index	
		}else{
			retFolder.preElement=preElement
		}
		if(this.isLastNode){
			retFolder.parentIsLastNode=true
		}
		if(i==(elementLen-1))
		{
			retFolder.isLastNode=true
		}
		preElement=document.all("td"+retFolder.id)
		strItem+=retFolder.drawFolder()
	}
	document.all("subtb"+this.id).children[0].children[0].children[0].innerHTML=strItem
}

function getData()				//获取动态数据
{
   var xml_node=listChildren(this.id)
   var xml_dom=sendData(xml_node,this.handleFile)
   return(xml_dom)
}

function createIndex()			
{ 
  nodeArr[nodeCount] =this								//增加一项在此累计
  this.index=nodeCount
  nodeCount++
  return(nodeCount-1)
} 

function drawFolder() 
{ 
	var i=0
	var foldIcon=""
	var html_get_from_parent=""
	var parentTb=null
    var strItem='<table cellspacing="0" cellpadding="0" border=0 width=0 FolderIndex="'+this.index+'" id="tb'+this.id+'">'
	strItem+='<tr id="tr'+this.id+'">'
	if(this.type=="FOLDER")
	{
		if(this.isLastNode){
			foldIcon=FOLDER_LASTCLOSE_ICON
		}else{
			foldIcon=FOLDER_CLOSE_ICON
		}
	}else{
		if(this.isLastNode){
			foldIcon=FOLDER_LASTNODE_ICON
		}else{
			foldIcon=FOLDER_NODE_ICON
		}
	}
	if(this.level==0)
	{
		strItem+='<td><img onclick="checkClick(this)" src="'+foldIcon+'"></td><td><img  src="'+this.typeIconSrc+'" onclick="checkClick(this)"></td><td style="font-size:13px" NOWRAP><div  id="div'+this.id+'"  onmouseup="checkMouseUp(this)"  onmousedown="checkMouseDown(this)"  onmouseover="checkOver(this)" onmouseout="checkOut(this)" onclick="checkClick(this)" title="'+this.itemname+'">'+this.itemname+'</div></td>'
	}else{
		parentTb=document.all("tb"+this.parent.id)
		for(i=0;i<parentTb.children[0].children[0].children.length-3;i++)
		{
			html_get_from_parent=parentTb.children[0].children[0].children[i].outerHTML
			strItem+=html_get_from_parent
		}
			//alert(html_get_from_parent)
		if(this.parentIsLastNode)			//假如父是最后一个子
		{
			strItem+='<td><img src="'+FOLDER_BLANK_ICON+'"></td>'
		}else{
			strItem+='<td><img src="'+FOLDER_VERT_ICON+'"></td>'
		}
		
		strItem+='<td><img onclick="checkClick(this)" src="'+foldIcon+'"></td><td><img onclick="checkClick(this)"  src="'+this.typeIconSrc+'"></td><td style="font-size:13px" NOWRAP><div id="div'+this.id+'" onmousedown="checkMouseDown(this)" onmouseup="checkMouseUp(this)"  onmouseover="checkOver(this)" onmouseout="checkOut(this)" onclick="checkClick(this)" parent="'+this.parentId+'" imagename="' +this.itemname +'" title="'+this.itemname+'">'+this.itemname+'</div></td>'
	}
	strItem+='<tr>'
	strItem+='</table>'
	return(strItem)
}

function moveToFolder(targetFolder)
{
	window.status=this.layerid
	var targetFolderObj=targetFolder
	if(targetFolderObj.id==this.id)
	{
		//alert("无法移动文件夹"+this.itemname+",目标文件夹和源文件夹的相同!")
		return
	}
	if(targetFolderObj.id==this.parent.id)
	{
		alert("无法移动文件夹"+this.itemname+",目标文件夹和源文件夹的相同!")
		return
	}
	
	if(targetFolderObj.layerid.search(this.layerid)!=-1 && targetFolderObj.layerid.length>this.layerid.length)
	{
		alert("无法移动文件夹"+this.itemname+",目标文件夹是源文件夹的子文件夹!")
		return
	}
	//return
	var strXml="<list>"
	strXml+="<node>"
	strXml+="<id>"
	strXml+=this.id
	strXml+="</id>"
	strXml+="<layerid>"
	strXml+=this.layerid
	strXml+="</layerid>"
	strXml+="<targetid>"
	strXml+=targetFolderObj.id
	strXml+="</targetid>"
	strXml+="<targetlayerid>"
	strXml+=targetFolderObj.layerid
	strXml+="</targetlayerid>"
	strXml+="</node>"
	strXml+="</list>"
	//alert(strXml)
	//	return
	var xmlAddDom=new ActiveXObject("Microsoft.XMLDOM")
	xmlAddDom.async=false
	xmlAddDom.loadXML(strXml)
	var retAddDom=sendData(xmlAddDom,"moveToFolder.asp")
	if(typeof(retAddDom)=="object"){
		if(retAddDom.xml.search("faile")==-1)
		{
			this.parent.refresh()						//刷新父级
			targetFolderObj.refresh()					//刷新自己
		}else{
			alert("移动失败")
		}

	}
}



function contextMenu()			//右键菜单			
{
	//ShowMainMenu()
	var currentMouseUpDiv=eval("document.all.div"+this.id)
	//alert(currentMouseUpDiv.innerText)
	try{
		showMenu(document.all.menu, new Boundaries(event.x,event.y), 'vertical')
	}catch(e){
		alert("不能找到菜单生成文件")	
	}
}

function rename(newName)			//重命名			
{
	var strXml="<list>"
	strXml+="<node>"
	strXml+="<id>"
	strXml+=this.id
	strXml+="</id>"
	strXml+="<itemname>"
	strXml+=newName
	strXml+="</itemname>"
	strXml+="</node>"
	strXml+="</list>"
	//var renameCurDiv=eval("document.all.div"+this.id)
	//renameCurDiv.innerText=newName
	//alert(strXml)

	var xmlAddDom=new ActiveXObject("Microsoft.XMLDOM")
	xmlAddDom.async=false
	xmlAddDom.loadXML(strXml)
	var retAddDom=sendData(xmlAddDom,"folderRename.asp")
	if(typeof(retAddDom)=="object"){
		if(retAddDom.xml.search("faile")==-1)
		{
			var renameCurDiv=eval("document.all.div"+this.id)
			renameCurDiv.innerText=newName
			this.itemname=newName
		}else{
			alert("重命名失败")
		}

	}else{
		alert("重命名失败")
	}
}



function nodeClick()
{
	if(this.type=="DOC")
	{
		return
	}else{
		if(this.haveData)
		{
			if(this.isOpen){
				this.hideChildren()
			}else{
				this.showChildren()
			}
		}else{
			this.addChild()
			this.showChildren()
		}
	}
}

function showChildren()
{
	var iconIndex=0
	document.all("subtb"+this.id).style.display=""
	iconIndex=document.all("tb"+this.id).children[0].children[0].children.length-3
	if(this.isLastNode){
		document.all("tb"+this.id).children[0].children[0].children[iconIndex].children[0].src=FOLDER_LASTOPEN_ICON  //"+"
 
	}else{
		document.all("tb"+this.id).children[0].children[0].children[iconIndex].children[0].src=FOLDER_OPEN_ICON  //"+"
	}
	document.all("tb"+this.id).children[0].children[0].children[iconIndex+1].children[0].src=FOLDER_FOLDEROPEN_ICON
	this.isOpen=true
}
function hideChildren()
{
	var iconIndex=0
	document.all("subtb"+this.id).style.display="none"
	iconIndex=document.all("tb"+this.id).children[0].children[0].children.length-3
	if(this.isLastNode){
		document.all("tb"+this.id).children[0].children[0].children[iconIndex].children[0].src=FOLDER_LASTCLOSE_ICON  //"+"
	}else{
		document.all("tb"+this.id).children[0].children[0].children[iconIndex].children[0].src=FOLDER_CLOSE_ICON  //"+"
	}
	document.all("tb"+this.id).children[0].children[0].children[iconIndex+1].children[0].src=FOLDER_FOLDER_ICON
	this.isOpen=false
}


function addFolder(pobj,itemname,type,icon,action) 
{ 
  folder = new Folder(pobj,itemname,type,icon,action) 
  return folder 
} 

function addDoc(name,id,handlefile,isLastNode,targetfile)
{
	var rootItem=""
	var rootFolder=new Folder(null,name,"DOC","DOC.GIF")
	rootFolder.id=id
	rootFolder.isLastNode=isLastNode

	rootFolder.handleFile=handlefile
	rootFolder.tarGetFile=targetfile
	rootFolder.createIndex()

    rootItem=rootFolder.drawFolder()
	document.write(rootItem)

}

function addRoot(name,id,layerid,handlefile,isLastNode,targetfile)
{
	var rootItem=""
	var rootFolder=new Folder(null,name,"FOLDER","FOLDER.GIF")
	rootFolder.id=id
	rootFolder.layerid=layerid
	rootFolder.isLastNode=isLastNode

	rootFolder.handleFile=handlefile
	rootFolder.targetFile=targetfile

	rootFolder.createIndex()

    rootItem=rootFolder.drawFolder()
	document.write(rootItem)
}

function checkClick(obj)					//节点单击
{
	var folderObj=null
	var tempObj=obj
	while(tempObj.tagName!="TABLE")
	{
		tempObj=tempObj.parentElement
	}
	folderObj=nodeArr[parseInt(tempObj.getAttribute("FolderIndex"))]
	folderObj.event=event
	if(obj.tagName=="IMG")
	{
		folderObj.nodeClick()
	}else if(obj.tagName=="DIV"){
		//folderObj.nodeClick()					//加上这一句可实现单击节点标题展开子项
		if(preDivObj!=null)
		{
			preDivObj.className="MOUSEOUT"
		}
		obj.className="MOUSEOVER"
		preDivObj=obj
		currentFolderObj=folderObj				//记录当前选中的节点
		folderObj.clickAction()	
	}
}
function checkMouseUp(obj)				//节点右键菜单
{
	if(event.button==2){
		var folderObj=null
		var tempObj=obj
		while(tempObj.tagName!="TABLE")
		{
			//strTagName=tempObj.parentElement.tagName
			tempObj=tempObj.parentElement
		}
		folderObj=nodeArr[parseInt(tempObj.getAttribute("FolderIndex"))]		//获取folder对象
		folderObj.event=event
		if(preDivObj!=null)
		{
			preDivObj.className="MOUSEOUT"
		}
		obj.className="MOUSEOVER"
		preDivObj=obj
		currentFolderObj=folderObj			    //记录当前选中的节点
		folderObj.contextMenu()					//右键菜单
	}
	
}
function checkMouseDown(obj)				//移动
{
	if(event.button==1){
		var folderObj=null
		var tempObj=obj
		while(tempObj.tagName!="TABLE")
		{
			//strTagName=tempObj.parentElement.tagName
			tempObj=tempObj.parentElement
		}
		folderObj=nodeArr[parseInt(tempObj.getAttribute("FolderIndex"))]		//获取folder对象
		folderObj.event=event
		if(preDivObj!=null)
		{
			preDivObj.className="MOUSEOUT"
		}
		obj.className="MOUSEOVER"
		preDivObj=obj
		currentFolderObj=folderObj			    //记录当前选中的节点

		getxy(event)
		var divMoveObj=document.all.divMove
		divMoveObj.className=obj.className
		divMoveObj.innerHTML=obj.innerHTML
		divMoveObj.style.filter="Alpha(Opacity=50)"
		//document.all.divMove.style.pixelLeft=event.x-event.offsetX
		//document.all.divMove.style.pixelTop=event.y-event.offsetY
		//document.all.divMove.style.visibility=""
	}
	
}
function getxy(evt)
{
	curx=evt.offsetX
	cury=evt.offsetY
}

function moveFolder(evt)
{
	if(evt.button==1 ){
	//document.selection.clear()
		var divMoveObj=document.all.divMove
		if(divMoveObj.style.display=="none"){
			divMoveObj.style.display =""
		}
		divMoveObj.style.pixelLeft=evt.x-curx//document.all.divMove.style.pixelLeft+(evt.clientX-curx)
		divMoveObj.style.pixelTop=evt.y-cury//document.all.divMove.style.pixelTop+(evt.clientY-cury)
	}
}
function bodyMouseUp(evt)
{
	if(evt.button==1){
		var divMoveObj=document.all.divMove
		divMoveObj.style.display="none"
		divMoveObj.innerHTML=""
		divMoveObj.className=""
		try{
			var moveTargetDivObj=document.elementFromPoint(evt.x,evt.y)			//获取目标对象
			if(moveTargetDivObj.tagName!="DIV") return
			var tempObj=moveTargetDivObj
			while(tempObj.tagName!="TABLE")
			{
				tempObj=tempObj.parentElement
			}
			var moveTargetFolderObj=nodeArr[parseInt(tempObj.getAttribute("FolderIndex"))]
			currentFolderObj.moveToFolder(moveTargetFolderObj)
		}catch(e)
		{window.status=""}
	}
}



function checkOver(obj)
{
	obj.className="MOUSEOVER"
}
function checkOut(obj)
{
	if(preDivObj!=obj){
		obj.className="MOUSEOUT"
	}
}

//document.onmousemove="moveFolder(event)"

/*function sendData(objDom,targeturl)
{
    var xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP");

   	xmlHTTP.open("POST",targeturl,false);
	xmlHTTP.send(objDom);

	if(xmlHTTP.responseText.indexOf("Error:")!=-1)
	{ 
	   alert(xmlHTTP.responseText);
	   return
	}

	var xmlResponse =bytes2BSTR(xmlHTTP.responseBody);
	var objDom = new ActiveXObject("Microsoft.XMLDOM"); //define a DOM object

	objDom.async=false;
     //alert(xmlResponse);
	//var myopener=window.open("");
	//myopener.document.body.innerText=xmlResponse;
	//myopener.document.body.innerHTML=xmlResponse;

    objDom.loadXML(xmlResponse);
	if (objDom.parseError.errorCode != 0 )
	{   
		return "error";
    }
    return(objDom);
}
*/



function listChildren(key)
{
	//alert(parentKey)
	var strXML;
	strXML="";
	strXML=strXML+"<listChildren>";
	strXML=strXML+"<key>";
	strXML=strXML+key;
	strXML=strXML+"</key>";
	strXML=strXML+"</listChildren>";
	//alert(strXML)
	var objDom = new ActiveXObject("MSXML.DOMDocument"); //define a DOM object
	objDom.async=false;
	objDom.loadXML(strXML);
	return objDom;
}
function clickAction()
{
	if(this.event!=null){
		//if(this.type=="DOC")
		//{
			//parent.document.all.content.src="content.asp?parent="+this.parentId+"&nodeid=" + this.id//document.all.photo.innerHTML="<img src=..\\product\\'" + this.parentId+"\\"+ this.id+">"
		
		//if(this.tarGetFile!=""){		
		//	parent.parent.document.all.content.src=this.tarGetFile+"?name="+this.itemname+"&nodeid=" + this.id//document.all.photo.innerHTML="<img src=..\\product\\'" + this.parentId+"\\"+ this.id+">"
		//}
		//}
		if(window.nodeOnclick!=null){
			window.nodeOnclick(this);
		}
	}

}

function refresh()
{
	if(this.type=="FOLDER")
	{
		if(this.haveData){
			var subNodeid="subtb"+this.id
			//alert(subNodeid)
			document.all(subNodeid).outerHTML=""
			this.haveData=false
			this.addChild()
			this.showChildren()
		}else{
			this.nodeClick()
		}
	}
}