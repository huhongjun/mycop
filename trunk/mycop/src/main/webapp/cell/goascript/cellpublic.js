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
	{//如果检测通过
		CellWeb1.SaveEdit();//执行保存报表的操作
		var tform = document.forms(0);
		tform.action = context+"/cell/templateAction"+tform.op.value+".do";//取当前action
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
