
  function move(from, to){
    var newTo = new Array();
    var flag = true;
    for(i=0; i<from.options.length; i++) {
      if (from.options[i].selected) {
        for(j = 0; j < to.options.length; j++){
          if(to[j].value == from[i].value){
            flag = false;
          }
        }
        if(flag){
          newTo[newTo.length] = cloneOption(from.options[i]);
        }
        flag=true;
      }
    }

    for(i=0; i<to.options.length; i++) {
      newTo[newTo.length] = cloneOption(to.options[i]);

      newTo[newTo.length-1].selected = false;
    }

    to.options.length = 0;

    for(i=0; i<newTo.length; i++) {
      to.options[to.options.length] = newTo[i];
    }
  }
  function moveall(from, to){
    var newTo = new Array();
    var flag = true;
    for(i=0; i<from.options.length; i++) {
          newTo[newTo.length] = cloneOption(from.options[i]);
          newTo[newTo.length-1].selected = true;
    }
 	
    to.options.length = 0;

    for(i=0; i<newTo.length; i++) {
      to.options[to.options.length] = newTo[i];
    }
  }
  
  function cloneOption(option) {
    var out = new Option(option.text,option.value);
    out.selected = option.selected;
    out.defaultSelected = option.defaultSelected;
    return out;
  }
  function del(from){
    for(i=0; i<from.options.length; i++) {
      if (from.options[i].selected) {
        from.options[i] = null;
        i--;
      }
    }
  }
  
  function delall(from){
    for(i=from.options.length-1; i>=0; i--) {
      if (from.options[i]) {
        from.options[i] = null;

      }
    }
  }  
    function markall(from){
     var out;
    for(i=0; i<from.options.length; i++) {
        
      out=cloneOption(from.options[i]);
      out.selected=true;
      from.options[i]=out;
      
      }
    } 
  

	   
	   function CovertStrUrl(s) 
    { 
        var i; 
        var s2 = s; 

        var s3="";
        while(s2.indexOf("%25")>=0) 
        { 
            i = s2.indexOf("%25"); 
            s3 = s3+s2.substring(0, i) +'%2525' ;
            s2=  s2.substring(i + 3, s2.length); 
        } 
        s3=s3+s2;
        s2=s3;
        s3="";
        while(s2.indexOf("%26")>=0) 
        { 
            i = s2.indexOf("%26"); 
            s3 = s3+s2.substring(0, i) +'%2526' ;
            s2=  s2.substring(i + 3, s2.length); 
            alert(s2);
        }     
        s3=s3+s2;    
        s2=s3;
        s3="";
 
        while(s2.indexOf("&")>=0) 
        { 
            i = s2.indexOf("&"); 
            s3 = s3+s2.substring(0, i) +'%26' ;
            s2=  s2.substring(i + 1, s2.length); 
        } 
        s3=s3+s2;
    return s3; 
    } 


/************************************************
** GetCheckUrlData(p_sURL)
** 功能：获得URL返回值:CheckData
************************************************/
function GetCheckLasrUrlData(p_sURL)
{
     	var ret = new Array();
    
     	var xmlDoc = new ActiveXObject("Msxml2.DOMDocument");
     		var nodeList;
        xmlDoc.async = false;
    	xmlDoc.load(p_sURL);
    	
         nodeList=xmlDoc.documentElement.selectNodes("//TreeNode");
         if(null!=nodeList)
         {
           for(i=0;i<nodeList.length;i++)
		    {
		        	ret[ret.length]=nodeList.item(i).getAttributeNode("Title").text;
  		            ret[ret.length]=nodeList.item(i).getAttributeNode("CheckData").text;  


		    }
          }


/*						             
	var sReturnVal	= "";
	var oXMLHttp	= new ActiveXObject ("Microsoft.XMLHTTP");
		oXMLHttp.Open("get", p_sURL, false);
		oXMLHttp.Send("");

	sReturnVal	= oXMLHttp.responseText;
*/
	return ret ;
}
/************************************************
** GetCheckUrlData(p_sURL)
** 功能：获得URL返回值:CheckData
************************************************/
function GetCheckLasrUrlDetailData(p_sURL)
{
     	var ret = new Array();
    
     	var xmlDoc = new ActiveXObject("Msxml2.DOMDocument");
     		var nodeList;
        xmlDoc.async = false;
    	xmlDoc.load(p_sURL);
    	
         nodeList=xmlDoc.documentElement.selectNodes("//TreeNode");
         if(null!=nodeList)
         {
           for(i=0;i<nodeList.length;i++)
		    {
		        	ret[ret.length]=nodeList.item(i).getAttributeNode("Title").text;
  		            ret[ret.length]=nodeList.item(i).getAttributeNode("CheckData").text;  
  		            ret[ret.length]=nodeList.item(i).getAttributeNode("Type").text;  

		    }
          }


	return ret ;
}    

/*******
*检查
*/
function showOverallClientCheckResult(inMsg) {
    if (getBrowserStatus() < StatusOK
     || getCookieStatus()  < StatusOK
     || getPopupStatus("../htm/close_window.htm") < StatusOK
     || getFlashStatus()   < StatusOK
     || getJreStatus()     < StatusOK
    ) {
        client_check_link.innerHTML = inMsg;
        client_check_td_above.height = 20;
        client_check_td_below.height = 20;
    }
}

function getBrowserStatus() {
    var status = StatusNG;
    if (!checkBrowserName(" MSIE ") || !checkBrowserName("MSIE ") || !checkBrowserName("MSIE")) {
        status = StatusNG;
    } else {
        status = StatusOK;
    }
    return status;
}

function checkBrowserName(name) {
    var verStr = navigator.appVersion;
    var verNo = 0;
    var result = false;
    if (verStr.indexOf(name) != -1) {
        tempStr = verStr.split(name);
        verNo = parseFloat(tempStr[1]);
        if (verNo >= 6) {
            result = true;
        }
    }
    return result;
}

function getCookieStatus() {
    var status = StatusNG;
    var cookieStr = "wb_check=kcehc_bw";
    document.cookie = cookieStr;
    if (document.cookie.indexOf(cookieStr) > -1) {
        status = StatusOK;
        var date = new Date();
        date.setTime(date.getTime() - 1000);
        document.cookie = cookieStr + "; expires=" + date.toGMTString();
    }
    return status;
}

function getPopupStatus(winUrl) {
    var status = StatusNG;
    var str_feature = 'toolbar=no'
    + ',menubar=no'
    + ',scrollbars=no'
    + ',resizable=no'
    + ',status=no'
    + ',width=1'
    + ',height=1'
    + ',top=0'
    + ',left=0'
    + ',screenX=0'
    + ',screenY=0';
    var popup_win = window.open(winUrl, "wb_check", str_feature);
    if (popup_win) {
        status = StatusOK;
    }
    return status;
}

function getFlashStatus() {
    var MinVer = 7;
    var status = StatusNG;
    if (navigator.plugins && navigator.plugins.length && navigator.plugins.length > 0) {
        var flashObj = navigator.plugins["Shockwave Flash"];
        if (flashObj && flashObj.length && flashObj.length > 0) {
            var flashMimeObj = flashObj["application/x-shockwave-flash"];
            if (flashMimeObj) {
                var tempStr = flashObj.description.split(" Flash ");
                var verNo = parseFloat(tempStr[1]);
                if (verNo >= MinVer) {
                    status = StatusOK;
                } else {
                    status = StatusUP;
                }
            }
        }
    }
    if (status == StatusNG) {
        for (var i = MinVer; i > 0; i--) {
            try {
                var flashObj = new ActiveXObject("ShockwaveFlash.ShockwaveFlash." + i);
                if (i == MinVer) {
                    status = StatusOK;
                } else {
                    status = StatusUP;
                }
                break;
            } catch(e) {
                status = StatusNG;
            }
        }
    }
    return status;
}

function getJreStatus() {
    var status = StatusNG;
    try {
        status = JREDetect.getStatus();
    } catch(e) {
        status = StatusNG;
    }
    return status;
}
