配合 /common/xtree 使用

生成xLoadTree需要的xml文件，定义struts Global forward 【TREE_DATA】
－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－    

在BaseAction中使用：
    public String doTreeData(GoaActionConfig cfg) throws DefaultException,
        Exception{

        return TREE_PAGE;
    }

    protected static final String TREE_PAGE = "TREE_DATA";
    继承的action在doTreeData中写入List供treedata.jsp使用
    	cfg.getRequest().setAttribute("treeData",
    	          isStandardModelDao.getTreeData(paraFlag, nodeID));
        return TREE_PAGE;
－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－    
最后呈现：
<script type="text/javascript">

	var context = "<%=context%>";
	//var rti;
	var tree = new WebFXLoadTree("政策法规",  context+"/infoservice/lawtree.do?action=doTreeData","javascript:toRight('/infoservice/lawlist.do?paraFlag=1')",null, null, null, null,'0');
	try{
		tree.expand();
	} catch(e) {
	}
	document.write(tree);
</script>
	说明： 第二个参数是xmlSrc，通过xmlhttp获得xml文件
－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－    
Struts配置中：
	<global-forwards>
		<forward name="GLOBALENSURE"  	path="delete.GlobalENSURE"/>
		<forward name="TREE_DATA"  	path="/tree/treedata.jsp"/>
－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－    

独立的action可以配置自己的forward
	<action path="/infoservice/templatetree" name="IsStandardModelForm"  type="com.gever.goa.infoservice.action.IsStandardModelAction"
		validate="false"   
		parameter="toTree" 
		scope="session" >
			<forward name="index" path="infoservice.templatetree" redirect="false"/>
			<forward name="TREE_DATA"  	path="/infoservice/treedata.jsp"/>
		</action>    