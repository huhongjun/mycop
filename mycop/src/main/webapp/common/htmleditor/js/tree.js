/*****************************************************************************
' �����ܣ���̬������������,����ͨ��xml���ݰ�������,���ݿ��Դ����ݿ��ȡ
' ʵ�ֽڵ����ӡ�ɾ�����ƶ�
' ��	�ߣ�������(carry)
' ����ʱ�䣺2002/01/15
'*****************************************************************************/

var doc=document
var nodeCount=0
var preDivObj=null
var currentFolderObj=null					//��ǰѡ�е�folder����
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
  //�̶�����
  this.itemname=itemname;
  // �ٶ�û��ָ��ͼ�ָ꣬��һ��ȱʡͼ��
  if( icon == null ) {
	this.folderIconType = "iGeneric.gif";
  } else {
	this.folderIconType = icon;
  }

  this.type=type					//�ڵ����ͣ��ļ��л��ļ�
  this.level=0						//����
  this.id =""						//Ψһ��id
  this.layerid=""					//���id
  this.parentId=""
  this.parent =parentobj			//���׶���
  //this.iconImg =""				//�򿪻�ر�ͼ��
  //this.nodeImg = 0				//�򿪻�ر�ͼ��
  //this.nodeImgSrc="../images/treeimage/" + this.nodeImg 
  //this.isLastNode = 0 
  this.childAccount=0				//������ӣ���ʾ�ӵ�����
  //dynamic data 
  this.isOpen =false 
  this.typeIconSrc = "../images/treeimage/" + this.folderIconType   
  this.index=0
  this.parentIsLastNode=false		//���Ƿ�Ϊ���һ����
  this.isLastNode=false				//���Ƿ�Ϊ���һ����
  this.preElement=null
  this.haveData=false				//�����Ƿ��Ѿ�����
  this.haveChild="NO"				//�Ƿ�����
  this.firstChildIndex=""			//
  this.handleFile=""				//���������ļ�
  this.tarGetFile=""				//�����ڵ�ʱ���õ��ļ�
  this.event=null					//����ʱ��ʱ�����
  //this.children = new Array 
 
  //���� 
  this.addChild = addChild								//�������
  this.drawFolder=drawFolder							//�����ļ���
  this.createIndex =createIndex							//���ض������ɵ����к�
  this.nodeClick=nodeClick								//�����ڵ�
  this.getData=getData									//��ȡ��̬����
  this.clickAction = clickAction						//�����Ĳ���
  this.moveToFolder=moveToFolder						//�ƶ��ļ���
  this.refresh=refresh									//ˢ��
  this.contextMenu=contextMenu							//�Ҽ��˵�(Ԥ��)
  this.rename=rename
  this.hideChildren=hideChildren						//�ر�����
  this.showChildren=showChildren						//������

} 

Folder.prototype.getTarget=function(){
	return this.tarGetFile;
}
Folder.prototype.getTarget=function(){
	return this.tarGetFile;
}



//�������
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

		retFolder=addFolder(this,str_NodeName,str_NodeType,str_NodeIcon,"")	//��ʼ��
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

function getData()				//��ȡ��̬����
{
   var xml_node=listChildren(this.id)
   var xml_dom=sendData(xml_node,this.handleFile)
   return(xml_dom)
}

function createIndex()			
{ 
  nodeArr[nodeCount] =this								//����һ���ڴ��ۼ�
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
		if(this.parentIsLastNode)			//���縸�����һ����
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
		//alert("�޷��ƶ��ļ���"+this.itemname+",Ŀ���ļ��к�Դ�ļ��е���ͬ!")
		return
	}
	if(targetFolderObj.id==this.parent.id)
	{
		alert("�޷��ƶ��ļ���"+this.itemname+",Ŀ���ļ��к�Դ�ļ��е���ͬ!")
		return
	}
	
	if(targetFolderObj.layerid.search(this.layerid)!=-1 && targetFolderObj.layerid.length>this.layerid.length)
	{
		alert("�޷��ƶ��ļ���"+this.itemname+",Ŀ���ļ�����Դ�ļ��е����ļ���!")
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
			this.parent.refresh()						//ˢ�¸���
			targetFolderObj.refresh()					//ˢ���Լ�
		}else{
			alert("�ƶ�ʧ��")
		}

	}
}



function contextMenu()			//�Ҽ��˵�			
{
	//ShowMainMenu()
	var currentMouseUpDiv=eval("document.all.div"+this.id)
	//alert(currentMouseUpDiv.innerText)
	try{
		showMenu(document.all.menu, new Boundaries(event.x,event.y), 'vertical')
	}catch(e){
		alert("�����ҵ��˵������ļ�")	
	}
}

function rename(newName)			//������			
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
			alert("������ʧ��")
		}

	}else{
		alert("������ʧ��")
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

function checkClick(obj)					//�ڵ㵥��
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
		//folderObj.nodeClick()					//������һ���ʵ�ֵ����ڵ����չ������
		if(preDivObj!=null)
		{
			preDivObj.className="MOUSEOUT"
		}
		obj.className="MOUSEOVER"
		preDivObj=obj
		currentFolderObj=folderObj				//��¼��ǰѡ�еĽڵ�
		folderObj.clickAction()	
	}
}
function checkMouseUp(obj)				//�ڵ��Ҽ��˵�
{
	if(event.button==2){
		var folderObj=null
		var tempObj=obj
		while(tempObj.tagName!="TABLE")
		{
			//strTagName=tempObj.parentElement.tagName
			tempObj=tempObj.parentElement
		}
		folderObj=nodeArr[parseInt(tempObj.getAttribute("FolderIndex"))]		//��ȡfolder����
		folderObj.event=event
		if(preDivObj!=null)
		{
			preDivObj.className="MOUSEOUT"
		}
		obj.className="MOUSEOVER"
		preDivObj=obj
		currentFolderObj=folderObj			    //��¼��ǰѡ�еĽڵ�
		folderObj.contextMenu()					//�Ҽ��˵�
	}
	
}
function checkMouseDown(obj)				//�ƶ�
{
	if(event.button==1){
		var folderObj=null
		var tempObj=obj
		while(tempObj.tagName!="TABLE")
		{
			//strTagName=tempObj.parentElement.tagName
			tempObj=tempObj.parentElement
		}
		folderObj=nodeArr[parseInt(tempObj.getAttribute("FolderIndex"))]		//��ȡfolder����
		folderObj.event=event
		if(preDivObj!=null)
		{
			preDivObj.className="MOUSEOUT"
		}
		obj.className="MOUSEOVER"
		preDivObj=obj
		currentFolderObj=folderObj			    //��¼��ǰѡ�еĽڵ�

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
			var moveTargetDivObj=document.elementFromPoint(evt.x,evt.y)			//��ȡĿ�����
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