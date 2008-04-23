//**********************************************
if(navigator.appName=="Netscape") document.all = document.getElementById;

var width=window.screen.availWidth/2
var height=window.screen.availHeight/2;
var top=window.screen.availWidth/4;
var left=window.screen.availHeight/4;
function openBroweWindow(url) {
	window.open (url,'loginwindow', 
	'height='+height+', width='+width+', top='+top+', left='+left+', toolbar=no, menubar=no, scrollbars=no,resizable=no,location=no, status=no');
}

function closewindow(){
  window.close();
  return true;
}

function closewindow(str){
  if(confirm(str)){
    window.close();
  }
  return true;
}

//两个输入筐的值比较,主要用在PASSWORD
function equals(obj1,obj2,to,alertStr){
  var p=document.all(obj1);
  var p2=document.all(obj2);
  var to=document.all(to);
  if(p.value==p2.value){	 
	  to.value=p.value;
	  return true;
  }
  if(alertStr!=null) alert(alertStr);
  return false;
}

function reset(obj1,obj2){
   var p=document.all(obj1);
  var p2=document.all(obj2);
  p.value='';
  p2.value='';
}

//form 的提交动作
/*
	新增加一个参数"argStr"
	修改时间：2004年11月18日
	修改人：方晓（资治国指导）
*/
function toAction(obj,actions,alertStr,argStr){

  var strDel = '0';
  if(alertStr==null){
    obj.form.action=obj.form.action+'?action='+actions;	
 /*
	当argStr与argStr全部不为空的时候，那么就提交动作action就加上argstr

	修改人：方晓
	修改时间：2004-12-4
	*/
	if(argStr!=""&&argStr!=null){

      obj.form.action+=argStr;
	}
    obj.form.submit();}
  else{
// Modify By Eddy 20041117---------------------------\\\\
// 跳过没有选择任何记录而做删除操作
	if (actions.indexOf('delete') > -1){
		for(var i = 0; i < obj.form.elements.length; i++) {
			if (obj.form.elements[i].type == 'checkbox') {
			//'checkbox5' 是选择当前表单所有checkbox的复选框的名字。剔除
              if(obj.form.elements[i].checked && obj.form.elements[i].name != 'checkbox5') {
				  strDel = '1';
			  }
			}
		}
	}
	if(strDel == '0') {
         alert('你没有选择任何记录!');
		 return false;
	}
// Modify By Eddy 20041117-----------------------------////

    if(confirm(alertStr)){
    	  obj.form.action=obj.form.action+'?action='+actions;
    /*
    当argStr与argStr全部不为空的时候，那么就提交动作action就加上参数argstr
	修改人：方晓
	修改时间：2004-12-4
	*/
	if(argStr!=""&&argStr!=null){
      obj.form.action+=argStr;
	}
	  obj.form.submit();
    } else {
    	return false;
    }	
  }
}

function selectItem(item){
  document.forms[0].departid.value=item;
  //document.forms[0].deptname.value=item;
  document.forms[0].submit();
  return;
}

//整个select框的提交 
function toSelectAction(obj,sourceList,hid,actions){
  var sourceObj = document.all(sourceList);
  var subtxt = document.all(hid);
  subtxt.value='';
  actualLength = sourceObj.options.length;
  for (idx =0 ; idx <actualLength; idx++){//从源列表框的第一项开始循环
	o=',';
	if(idx==0) o='';
	subtxt.value=subtxt.value+o+sourceObj.item(idx).value;
  }
  obj.form.action=obj.form.action+'?action='+actions;
  //****************************************************************
  obj.form.submit();
}

//一个checkBOX选上，列表里所有的checkBox都选上,要用在同一个FORM里

function checkAll(it,cid){
	if(it.form.elements[cid]!=null){
		var size=it.form.elements[cid].length;
		if(size!=null){
			for(i=0;i<size;i++){
                //==================================================
                //胡勇修改，初始化数据不能删除
                if(!it.form.elements[cid][i].disabled)
                //==================================================
				    it.form.elements[cid][i].checked=it.checked;
			}
		}else{
			it.form.elements[cid].checked=it.checked;	
		}
	}
}


//选择框的操作涵数 ++++++++++++++++++++++++++++++++++++++++++++
//只选一项
 function doSelOne(sourceList,toList){

     var idx, idy;
     var bIsOk ;
	 var sourceObj = document.all(sourceList);//左边的列表框为源框
	 var destObj = document.all(toList);//右边的列表框为接收框
	 
     if (sourceObj.options.length==0) return;//如果源对象中的项为空，退出该方法
	 bIsOk=false;
     for (j=sourceObj.options.length-1 ; j >=0 ; j--){
	 if (sourceObj.options[j].selected==true){//如果某一项被选择，dLength为临时的个数
	     dLength = destObj.options.length;//取得接收列表当前项个数
	     destObj.options[dLength] = new Option(sourceObj.options[j].text,sourceObj.options[j].value);
	     sourceObj.remove(j)//接收列表该项用源列表被选择项替代，同时删除源列表中该项
	 }//if
     }//for
 }
 
 function doSelAll(sourceList,toList){//同时一次全选
     var idx, idy;
     var bIsOk ;
     var sourceObj = document.all(sourceList);
	 var destObj = document.all(toList);
     if (sourceObj.options.length==0) return;
     actualLength = sourceObj.options.length;
     for (idx =0 ; idx < sourceObj.options.length; idx++){//从源列表框的第一项开始循环
         if(sourceObj.options[idx].selected){//**************************************************************
         bIsOk= false;
         for (idy=0;idy<=destObj.options.length-1 ; idy++ ){//依次判断目标框中是否存在选中的该项
             if (sourceObj.item(idx).value == destObj.item(idy).value){
                 bIsOk = true;
                 break; 
             }
         }//for--idy
         if (!bIsOk){//如果bIsOk为false,即接收框中没有与选中项重复的列表项
             var e = new Option(sourceObj.options[idx].text,sourceObj.options[idx].value);
             //e.value=sourceObj.options[idx].value;
             //e.text=sourceObj.options[idx].text;
             destObj.options.add(e);
         }
         sourceObj.remove(idx);
         idx--;
         }//**************************************************************
     }//for--idx
     //*******************************************************************
     //解决在Netscape中sourceObj.options.remove不能被识别的问题
     //sourceObj.options.length=0;
     /*********************
     for (idx= sourceObj.options.length-1 ; idx>=0 ; idx--){
	    sourceObj.options.remove(idx)
     }//删除源框中的所有项
     **********************/
     //*******************************************************************
 } 
 
 //文本框获得焦点时运行该方法（onfocus="checkTextBoxInput()"）
function checkTextBoxInput(event){
    if(navigator.appName == "Netscape"){
	    var txtBox=event.target;
    } else {
	    var txtBox=event.srcElement;
    }
	if(txtBox.onlytype){
		
		if(txtBox.oldValue==null){
			txtBox.oldValue=txtBox.value;
		}
		/****解决输入法问题*/
		//屏蔽输入法
		if(txtBox.onlytype.toLowerCase()=="float" || txtBox.onlytype.toLowerCase()=="int" || txtBox.onlytype.toLowerCase()=="float1"){
			txtBox.style.imeMode="disabled";
		}
		/****解决输入法问题*/
		txtBox.onkeydown=setBoxInputType;
		try{txtBox.onpaste=checkPasteData;}catch(e){}
		txtBox.onblur=checkInputValue;


	}else{
		return false;
	}

}


/**********************
*控制文本框只能输入某类型值
***********************/
function setBoxInputType(){
	// alert(code);
	
	var flag=this.onlytype;

	var txt="";
	var code=event.keyCode;
	txt=this.value;

	

	if(flag.toLowerCase()=="int"){
	  if(code==190) {
	    return false;
	  }
	  // modified by linhs 2003-10-31
	  // if((code<96 || code>105) && (code<48 || code>57) && code!=190 &&  code!=46 && code!=37 &&  code!=39 &&  code!=8 && code!=9){
	  if ((code<96 || code>105) && (code<48 || code>57) && !isTheFunctionKeys(code)) {
		return false;
	  }
	}
	
	
	return true;

}
function isTheFunctionKeys(code) {
  if (code==46 || code==37 || code==39 || code==8 || code==9) {
    return true;
  } else {
    return false;
  }
}

function checkPasteData(){
	var pasteData=window.clipboardData.getData('Text');
	switch(this.onlytype.toLowerCase()){

		case "int":
			if(!isInt(pasteData)){
				alert("粘贴文本不能转化整数，该文本框要求输入整数.");
				event.returnValue=false;
			}
			break;
		case "float":
			if(!isNumeric(pasteData)){
				alert("粘贴文本不能转化数字，该文本框要求输入数字.");
				event.returnValue=false;
			}
			break;
        // ----- Added by linhs 2003-11-5 [begin] -----        
		case "alpha":
			if (!isAlpha(pasteData) && (this.value.length!=0)) {
                alert("粘贴文本不能转化英文字符串，该文本框要求输入英文字符串.");
				event.returnValue=false;
			}
		// ----- Added by linhs 2003-11-5 [begin] -----
	}

}

// 验证是否 整数
function isInt( strValue )
{
	// 数字必须是 0123456789 或者为空

	return checkExp( /^\d*$/g, strValue );

	//return  checkExp(/\ \^(-|\+)?\d+(\.\d+)?$/g, strValue );
}
function checkInputValue(){
	var value=this.value;
	switch(this.onlytype.toLowerCase()){

		case "int":
			if(!isInt(this.value)){
				alert("该文本框要求输入整数.");

				this.value=(this.oldValue==null?"":this.oldValue);
				return false;
			}
			break;
		
        // ----- Added by linhs 2003-11-5 [begin] -----        
	}

}

// 使用正则表达式，检测 s 是否满足模式 re
function checkExp( re, s )
{
	return re.test( s );
}
 //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

function clearMenu(){
	try{
		if(window.parent!=null){
			document.body.onmousedown=function(){
				window.parent.top.frames["right"].hideAllMenu();
			}
		}	
	}catch(e){
	//
	}
}