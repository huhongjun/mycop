<%@ page contentType="text/html; charset=GB2312"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
	var aryNoPks = new Array();   //缓存主键数据
	var curUpdateId = "";       //当前修改的主键
	var aryOpenPks = new Array();   //缓存主键数据
	//数据是否为空
	function isDataNull(isOpen){
		var aryPks = new Array();
		aryPks = (!isOpen )?aryNoPks:aryOpenPks;
		
		if (aryPks.length <= 0){ 
			return false;
		} else {
			return true;
		}
	}

	//上一条
	function f_previous(oFid,isOpen){

		if (isDataNull(isOpen) == false) 
			return false;
		var aryPks = new Array();
		aryPks = (!isOpen )?aryNoPks:aryOpenPks;

		var preID = "";
		var curID = "";
		for (var idx = 0; idx < aryPks.length ; idx++){
			curID = aryPks[idx];
			if (curID == oFid.value){
				if (preID != "")
					oFid.value = preID;
				else 
					oFid.value = curID;
				break;
			}
			preID = curID;
		}
	}
	
	//下一条
	function f_next(oFid,isOpen){
		if (isDataNull(isOpen) == false) 
			return false;
		var aryPks = new Array();
		aryPks = (!isOpen )?aryNoPks:aryOpenPks;
		var nextID = "";
		var curID = "";
		for (var idx = 0; idx < aryPks.length ; idx++){
			curID = aryPks[idx];
			if (idx <aryPks.length-1)
				nextID = aryPks[idx+1];

			if (curID == oFid.value){
				if (nextID != "")
					oFid.value = nextID;
				else 
					oFid.value = curID;
				break;
			}
		}
	}

	//第一条
	function f_first(oFid,isOpen){
		if (isDataNull(isOpen) == false) 
			return false;
		var aryPks = new Array();
		aryPks = (!isOpen )?aryNoPks:aryOpenPks;
		oFid.value = aryPks[0];
	}

	//最后一条
	function f_last(oFid,isOpen){
		if (isDataNull(isOpen) == false) 
			return false;
		var aryPks = new Array();
		aryPks = (!isOpen )?aryNoPks:aryOpenPks;
		oFid.value = aryPks[aryPks.length-1];
	}


	//初始化数据
	function f_initData(myForm, bIsOpen){
		//检查是否有选择记录
		var count = 0;
		var length = 0;
		var isOpen = bIsOpen;
		var aryPks = new Array();
		//是否多条
		try{
			 length = myForm.fid.length;
		} catch(e){
			length= -1;
			if (isOpen == false)
				aryNoPks = aryPks;
			else
				aryOpenPks = aryPks;
			return false;
		}
		var aryPks = new Array();
		
		var isOneRow = false;  //是否单条记录
		if (isNaN(length))	{
			try{
				if (document.forms[0].fid.checked)	{
					isOneRow = true;
					length = 1;
				}
			} catch(e) {
			}
		} else {
			//length = -1;
		}
		
		//如果只有一条记录时
		if (isOneRow == true){
			aryPks[0] = myForm.fid.value;
			if (isOpen)
				aryNoPks = aryPks;
			else
				aryOpenPks = aryPks;
			return true;
		}
		
		
		//多条记录处理
		for(var idx=0; idx < length; idx++){
			aryPks[idx] = myForm.fid[idx].value;
		}
		if (isOpen == false)
			aryNoPks = aryPks;
		else
			aryOpenPks = aryPks;
		//
		return true;
	}


	//初始化图片
	function getShowType(oFid,isOpen){
		if (isDataNull(isOpen) == false) 
			return 0;
		var aryPks = new Array();
		aryPks = (!isOpen )?aryNoPks:aryOpenPks;
		
		//alert(aryPks.length);
		var nextID = "";
		var curID = "";
		var row = -1;
		var showType = 0;

		for (var idx = 0; idx < aryPks.length ; idx++){
			//alert(oFid.value + " = " + aryPks[idx]);
			if (aryPks[idx] == oFid.value){
				row = idx;
				break;
			}
		}
		
		//是否显示< 及<<
		//alert ("row == " +row + "length" + aryPks.length)
		if (row > 0 && aryPks.length >1){
			showType = showType | 1;
			showType = showType | 2;
		}

		//是否显示> 及>>
		
		if (row >=0 && (row)<aryPks.length-1 && aryPks.length >1){
			showType = showType | 4;
			showType = showType | 8;
		}
		//alert(showType);
		return showType;

	}
//	alert("***************");
//-->
</SCRIPT>