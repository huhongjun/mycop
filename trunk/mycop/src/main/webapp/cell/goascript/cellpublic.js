var context="gdca";
function savePlan()
{
	if(check())
	{
		CellWeb1.SaveEdit();
		var tform = document.forms(0);
		tform.action = "/goas/cell/templateAction"+tform.op.value+".do";
		if(saveCell())
		{
			tform.submit();
		}
	}
}
function saveWorkLog()
{
	if(check())
	{//������ͨ��
		CellWeb1.SaveEdit();//ִ�б��汨��Ĳ���
		var tform = document.forms(0);
		tform.action = context+"/cell/templateAction"+tform.op.value+".do";//ȡ��ǰaction
		if(saveCell())
		{
			tform.submit();
		}
	}
}


function getUrl()
{
	var strUrl = document.location.host;
	//alert("strUrl+++++"+strUrl);
	//alert("curTemplatePath=="+"http://" + strUrl + "/goa");
	return "http://" + strUrl + "/"+ context;
}

function addZero(str)
{
	if(str.length < 2)
	{
		str = "0" + str;
	}
	return str;
}
