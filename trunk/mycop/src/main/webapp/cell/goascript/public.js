//var context="/gdca";
  var context="gdp";
function savePlan()
{
		CellWeb1.SaveEdit();
		var tform = document.forms(0);
		if(saveCell())
		{
			doSave(tform);
		}
}

function savePlanExit()
{               
		CellWeb1.SaveEdit();
		var tform = document.forms(0);
		if(saveCell())
		{
			doSaveExit(tform);
		}
}

function getUrl()
{  
	//huhj
	var context="/gdp";
	var strUrl = document.location.host;
	return "http://" + strUrl + context;
}

function addZero(str)
{
	if(str.length < 2)
	{
		str = "0" + str;
	}
	return str;
}
