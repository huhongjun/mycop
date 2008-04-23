<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>人员选择</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript">
function back()
{
  window.close();
}

<script language="javascript">
 //只选一项
 function doSelOne(actionType){

     var idx, idy;
     var bIsOk ;

     switch(actionType){
	 case 0://为左边的列表框
	     var sourceObj = document.all("addLeftListBox");//左边的列表框为源框
	     var destObj = document.all("addRightListBox");//右边的列表框为接收框
	     break;
	 case 1://为右边的列表框
	     var sourceObj = document.all("addRightListBox");//右边的列表框为接收框
	     var destObj = document.all("addLeftListBox");//左边的列表框为源框
	     break;
     }
     
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
 
 function doSelAll(actionType){//同时一次全选
     var idx, idy;
     var bIsOk ;
     switch(actionType){
	 case 0://为左边的列表框
	     var sourceObj = document.all("addLeftListBox");
	     var destObj = document.all("addRightListBox");
	     break;
	 case 1://为右边的列表框
	     var sourceObj = document.all("addRightListBox");
	     var destObj = document.all("addLeftListBox");
	     break;
     }
     if (sourceObj.options.length==0) return;
     actualLength = sourceObj.options.length;
     for (idx =0 ; idx <actualLength; idx++){//从源列表框的第一项开始循环
	 bIsOk= false;
	 for (idy=0;idy<=destObj.options.length-1 ; idy++ ){//依次判断目标框中是否存在选中的该项
	     if (sourceObj.item(idx).value == destObj.item(idy).value){
	     	 bIsOk = true;
		 break; 
	     }
	 }//for--idy
	 if (!bIsOk){//如果bIsOk为false,即接收框中没有与选中项重复的列表项
	     var e = document.createElement("OPTION");
	     e.value=sourceObj.item(idx).value;
	     e.text=sourceObj.item(idx).text;
	     destObj.options.add(e);
	 }
     }//for--idx
     for (idx= sourceObj.options.length-1 ; idx>=0 ; idx--){
	 sourceObj.options.remove(idx)
     }//删除源框中的所有项
 } 
 
 
function addOptionMoveUp(sourceSelect)
{  
	var sel_id;
  sel_id = sourceSelect.selectedIndex;
  if (sourceSelect.length > 1 && sel_id > 0) //got to have at least 2 items and not the first one is selected
  {  var prevOption = sourceSelect.options[sel_id-1];
    var newOption = new Option(prevOption.text, prevOption.value);
    var selectedOption = sourceSelect.options[sel_id];
    sourceSelect.options[sel_id-1] = new Option(selectedOption.text, selectedOption.value);
    sourceSelect.options[sel_id] = newOption;
    sourceSelect.focus();
    sourceSelect.selectedIndex = sel_id -1;
  }
}

//下移
function addOptionMoveDown(sourceSelect)
{  var sel_id;
  sel_id = sourceSelect.selectedIndex;
  if (sourceSelect.length > 1 && sel_id < sourceSelect.length -1 && sel_id > -1) //got to have at least 2 items and not the last one is selected
  {  var nextOption = sourceSelect.options[sel_id+1];
    var newOption = new Option(nextOption.text, nextOption.value);
    var selectedOption = sourceSelect.options[sel_id];
    sourceSelect.options[sel_id+1] = new Option(selectedOption.text, selectedOption.value);
    sourceSelect.options[sel_id] = newOption;
    sourceSelect.focus();
    sourceSelect.selectedIndex = sel_id + 1;
  }
} 
</script>
</head>

<body class="bodybg" style="overflow-x:hidden;overflow-y:hidden;border: none">
<script>
	try{
		document.body.onmousedown=function(){top.frames["right"].hideAllMenu();}	
	}catch(e){
	//
	}
</script>
<table align="center">
  <tr> 
    <td >&nbsp;</td>
  </tr>
  <tr> 
    <td ><table border="0" cellpadding="2" cellspacing="1">
        <tr> 
          <td colspan="2"><table border="0" cellpadding="2" cellspacing="1">
              <tr class="InputLabelCell"> 
                <td>岗位列表:</td>
                <td valign="middle">&nbsp;</td>
                <td>选定岗位:</td>
              </tr>
              <tr> 
                <td align="center" valign="top"> <select name="select2" multiple class="input2" style="width:120px; height:150px;">
                  </select></td>
                <td align="center" valign="middle"> <table border="0" cellpadding="1" cellspacing="1">
                    <tr align="center"> 
                      <td valign="middle"> <input name="Submit32" type="submit" class="button" style="width:30px;" value="&gt;&gt;"></td>
                    </tr>
                    <tr> 
                      <td align="center"> <input name="Submit232" type="submit" class="button" style="width:30px;" value="&gt;"></td>
                    </tr>
                    <tr> 
                      <td align="center"> <input name="Submit2232" type="submit" class="button" style="width:30px;" value="&lt;"></td>
                    </tr>
                    <tr> 
                      <td align="center"> <input name="Submit22222" type="submit" class="button" style="width:30px;" value="&lt;&lt;"></td>
                    </tr>
                  </table></td>
                <td align="center" valign="top"> <select name="select" multiple class="input2" style="width:120px; height:150px;">
                  </select></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr> 
    <td align="center" > <input name="Submit" type="submit" class="button" onClick="back()" value="确定"> 
      <input name="Submit23" type="submit" class="button" onclick="back()" value="返回"> 
    </td>
  </tr>
  <tr>
    <td align="center" >&nbsp;</td>
  </tr>
</table>
</body>
</html>
