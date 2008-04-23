<%  String desc = OrderList.getInstance().desc;
    String[] mapList = (String[])OrderList.getInstance().getMap().get(key_value);
    String[] sess    = (String[])session.getAttribute(key_value);
    if(sess == null) sess = new String[2];

    String query = request.getQueryString();
    if(!OrderList.getInstance().isNull(query))
        query = "?" + query;
    else
        query = "?" + "query=query";
    String url = request.getContextPath() + uri + query;
    int key_index = url.indexOf(key_value);
    if(key_index > 0)
        url = url.substring(0,key_index-1);
%>
    var imgpath = "up";
    var orderby = '<%=sess[0] == null ? "null" : sess[0]%>';
    var desc    = '<%=sess[1] == null ? "null" : sess[1]%>';
    var event_id = '<%=request.getParameter("srcid")%>'; 

    // 重新刷新列表
    function order(key, event){
        if(ns) event.srcElement = event.target;
        var href = "<%=url%>&<%=key_value%>=" + key;
        if(orderby == key) desc = desc == "true";
        href += "&<%=desc%>=" + !desc;
        href += "&srcid=" + event.srcElement.id;
        
        document.location.href = href;
    }
    // 切换列表图片
    function swapImages(){
        var tdobj = document.getElementById(event_id);
        var down = desc == "true";
        if(down) imgpath = "down";

        tdobj.innerHTML += " <img src='<gdp:context/>/images/order" + imgpath + ".gif' width='10' height='10' border='0'>"
    }