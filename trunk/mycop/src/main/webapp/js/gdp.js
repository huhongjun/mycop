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

//����������ֵ�Ƚ�,��Ҫ����PASSWORD
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

//form ���ύ����
/*
	������һ������"argStr"
	�޸�ʱ�䣺2004��11��18��
	�޸��ˣ����������ι�ָ����
*/
function toAction(obj,actions,alertStr,argStr){

  var strDel = '0';
  if(alertStr==null){
    obj.form.action=obj.form.action+'?action='+actions;	
 /*
	��argStr��argStrȫ����Ϊ�յ�ʱ����ô���ύ����action�ͼ���argstr

	�޸��ˣ�����
	�޸�ʱ�䣺2004-12-4
	*/
	if(argStr!=""&&argStr!=null){

      obj.form.action+=argStr;
	}
    obj.form.submit();}
  else{
// Modify By Eddy 20041117---------------------------\\\\
// ����û��ѡ���κμ�¼����ɾ������
	if (actions.indexOf('delete') > -1){
		for(var i = 0; i < obj.form.elements.length; i++) {
			if (obj.form.elements[i].type == 'checkbox') {
			//'checkbox5' ��ѡ��ǰ������checkbox�ĸ�ѡ������֡��޳�
              if(obj.form.elements[i].checked && obj.form.elements[i].name != 'checkbox5') {
				  strDel = '1';
			  }
			}
		}
	}
	if(strDel == '0') {
         alert('��û��ѡ���κμ�¼!');
		 return false;
	}
// Modify By Eddy 20041117-----------------------------////

    if(confirm(alertStr)){
    	  obj.form.action=obj.form.action+'?action='+actions;
    /*
    ��argStr��argStrȫ����Ϊ�յ�ʱ����ô���ύ����action�ͼ��ϲ���argstr
	�޸��ˣ�����
	�޸�ʱ�䣺2004-12-4
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

//����select����ύ 
function toSelectAction(obj,sourceList,hid,actions){
  var sourceObj = document.all(sourceList);
  var subtxt = document.all(hid);
  subtxt.value='';
  actualLength = sourceObj.options.length;
  for (idx =0 ; idx <actualLength; idx++){//��Դ�б��ĵ�һ�ʼѭ��
	o=',';
	if(idx==0) o='';
	subtxt.value=subtxt.value+o+sourceObj.item(idx).value;
  }
  obj.form.action=obj.form.action+'?action='+actions;
  //****************************************************************
  obj.form.submit();
}

//һ��checkBOXѡ�ϣ��б������е�checkBox��ѡ��,Ҫ����ͬһ��FORM��

function checkAll(it,cid){
	if(it.form.elements[cid]!=null){
		var size=it.form.elements[cid].length;
		if(size!=null){
			for(i=0;i<size;i++){
                //==================================================
                //�����޸ģ���ʼ�����ݲ���ɾ��
                if(!it.form.elements[cid][i].disabled)
                //==================================================
				    it.form.elements[cid][i].checked=it.checked;
			}
		}else{
			it.form.elements[cid].checked=it.checked;	
		}
	}
}


//ѡ���Ĳ������� ++++++++++++++++++++++++++++++++++++++++++++
//ֻѡһ��
 function doSelOne(sourceList,toList){

     var idx, idy;
     var bIsOk ;
	 var sourceObj = document.all(sourceList);//��ߵ��б��ΪԴ��
	 var destObj = document.all(toList);//�ұߵ��б��Ϊ���տ�
	 
     if (sourceObj.options.length==0) return;//���Դ�����е���Ϊ�գ��˳��÷���
	 bIsOk=false;
     for (j=sourceObj.options.length-1 ; j >=0 ; j--){
	 if (sourceObj.options[j].selected==true){//���ĳһ�ѡ��dLengthΪ��ʱ�ĸ���
	     dLength = destObj.options.length;//ȡ�ý����б�ǰ�����
	     destObj.options[dLength] = new Option(sourceObj.options[j].text,sourceObj.options[j].value);
	     sourceObj.remove(j)//�����б������Դ�б�ѡ���������ͬʱɾ��Դ�б��и���
	 }//if
     }//for
 }
 
 function doSelAll(sourceList,toList){//ͬʱһ��ȫѡ
     var idx, idy;
     var bIsOk ;
     var sourceObj = document.all(sourceList);
	 var destObj = document.all(toList);
     if (sourceObj.options.length==0) return;
     actualLength = sourceObj.options.length;
     for (idx =0 ; idx < sourceObj.options.length; idx++){//��Դ�б��ĵ�һ�ʼѭ��
         if(sourceObj.options[idx].selected){//**************************************************************
         bIsOk= false;
         for (idy=0;idy<=destObj.options.length-1 ; idy++ ){//�����ж�Ŀ������Ƿ����ѡ�еĸ���
             if (sourceObj.item(idx).value == destObj.item(idy).value){
                 bIsOk = true;
                 break; 
             }
         }//for--idy
         if (!bIsOk){//���bIsOkΪfalse,�����տ���û����ѡ�����ظ����б���
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
     //�����Netscape��sourceObj.options.remove���ܱ�ʶ�������
     //sourceObj.options.length=0;
     /*********************
     for (idx= sourceObj.options.length-1 ; idx>=0 ; idx--){
	    sourceObj.options.remove(idx)
     }//ɾ��Դ���е�������
     **********************/
     //*******************************************************************
 } 
 
 //�ı����ý���ʱ���и÷�����onfocus="checkTextBoxInput()"��
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
		/****������뷨����*/
		//�������뷨
		if(txtBox.onlytype.toLowerCase()=="float" || txtBox.onlytype.toLowerCase()=="int" || txtBox.onlytype.toLowerCase()=="float1"){
			txtBox.style.imeMode="disabled";
		}
		/****������뷨����*/
		txtBox.onkeydown=setBoxInputType;
		try{txtBox.onpaste=checkPasteData;}catch(e){}
		txtBox.onblur=checkInputValue;


	}else{
		return false;
	}

}


/**********************
*�����ı���ֻ������ĳ����ֵ
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
				alert("ճ���ı�����ת�����������ı���Ҫ����������.");
				event.returnValue=false;
			}
			break;
		case "float":
			if(!isNumeric(pasteData)){
				alert("ճ���ı�����ת�����֣����ı���Ҫ����������.");
				event.returnValue=false;
			}
			break;
        // ----- Added by linhs 2003-11-5 [begin] -----        
		case "alpha":
			if (!isAlpha(pasteData) && (this.value.length!=0)) {
                alert("ճ���ı�����ת��Ӣ���ַ��������ı���Ҫ������Ӣ���ַ���.");
				event.returnValue=false;
			}
		// ----- Added by linhs 2003-11-5 [begin] -----
	}

}

// ��֤�Ƿ� ����
function isInt( strValue )
{
	// ���ֱ����� 0123456789 ����Ϊ��

	return checkExp( /^\d*$/g, strValue );

	//return  checkExp(/\ \^(-|\+)?\d+(\.\d+)?$/g, strValue );
}
function checkInputValue(){
	var value=this.value;
	switch(this.onlytype.toLowerCase()){

		case "int":
			if(!isInt(this.value)){
				alert("���ı���Ҫ����������.");

				this.value=(this.oldValue==null?"":this.oldValue);
				return false;
			}
			break;
		
        // ----- Added by linhs 2003-11-5 [begin] -----        
	}

}

// ʹ��������ʽ����� s �Ƿ�����ģʽ re
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