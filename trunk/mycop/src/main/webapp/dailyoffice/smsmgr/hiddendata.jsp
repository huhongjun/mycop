

<SCRIPT LANGUAGE="JavaScript">
<!--
	var aryPks = new Array();   //������������
	var curUpdateId = "";       //��ǰ�޸ĵ�����

	//�����Ƿ�Ϊ��
	function isDataNull(){
		if (aryPks.length <= 0){ 
			return false;
		} else {
			return true;
		}
	}

	//��һ��
	function f_previous(oFid){
		if (isDataNull == false) 
			return false;
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

	//��һ��
	function f_next(oFid){
		if (isDataNull == false) 
			return false;

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

	//��һ��
	function f_first(oFid){
		if (isDataNull == false) 
			return false;
		oFid.value = aryPks[0];
	}

	//���һ��
	function f_last(oFid){
		if (isDataNull == false) 
			return false;
		oFid.value = aryPks[aryPks.length-1];
	}


	//��ʼ������
	function f_initData(myForm){
		//����Ƿ���ѡ���¼
		var count = 0;
		var length = 0;
		
		//�Ƿ����
		try{
			 length = myForm.fid.length;
		} catch(e){
			length= -1;
			
			return false;
		}
		aryPks = new Array();
		//alert(aryPks.length);
		var isOneRow = false;  //�Ƿ�����¼
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

		//���ֻ��һ����¼ʱ
		if (isOneRow == true){
			aryPks[0] = myForm.fid.value;
			return true;
		}
		
		//������¼����
		for(var idx=0; idx < length; idx++){
			aryPks[idx] = myForm.fid[idx].value;
		}
		//alert("length=" + length);
		return true;
	}

	//��ʼ��ͼƬ
	function getShowType(oFid){
		if (isDataNull == false) 
			return 0;

		var nextID = "";
		var curID = "";
		var row = -1;
		var showType = 0;

		for (var idx = 0; idx < aryPks.length ; idx++){
			if (aryPks[idx] == oFid.value){
				row = idx;
				break;
			}
		}
		//�Ƿ���ʾ< ��<<
		//alert ("row == " +row + "length" + aryPks.length)
		if (row > 0 && aryPks.length >1){
			showType = showType | 1;
			showType = showType | 2;
		}

		//�Ƿ���ʾ> ��>>
		
		if (row >=0 && (row)<aryPks.length-1 && aryPks.length >1){
			showType = showType | 4;
			showType = showType | 8;
		}
		
		return showType;

	}
//	alert("***************");
//-->
</SCRIPT>