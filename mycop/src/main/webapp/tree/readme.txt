��� /common/xtree ʹ��

����xLoadTree��Ҫ��xml�ļ�������struts Global forward ��TREE_DATA��
��������������������������������������������������������������������    

��BaseAction��ʹ�ã�
    public String doTreeData(GoaActionConfig cfg) throws DefaultException,
        Exception{

        return TREE_PAGE;
    }

    protected static final String TREE_PAGE = "TREE_DATA";
    �̳е�action��doTreeData��д��List��treedata.jspʹ��
    	cfg.getRequest().setAttribute("treeData",
    	          isStandardModelDao.getTreeData(paraFlag, nodeID));
        return TREE_PAGE;
��������������������������������������������������������������������    
�����֣�
<script type="text/javascript">

	var context = "<%=context%>";
	//var rti;
	var tree = new WebFXLoadTree("���߷���",  context+"/infoservice/lawtree.do?action=doTreeData","javascript:toRight('/infoservice/lawlist.do?paraFlag=1')",null, null, null, null,'0');
	try{
		tree.expand();
	} catch(e) {
	}
	document.write(tree);
</script>
	˵���� �ڶ���������xmlSrc��ͨ��xmlhttp���xml�ļ�
��������������������������������������������������������������������    
Struts�����У�
	<global-forwards>
		<forward name="GLOBALENSURE"  	path="delete.GlobalENSURE"/>
		<forward name="TREE_DATA"  	path="/tree/treedata.jsp"/>
��������������������������������������������������������������������    

������action���������Լ���forward
	<action path="/infoservice/templatetree" name="IsStandardModelForm"  type="com.gever.goa.infoservice.action.IsStandardModelAction"
		validate="false"   
		parameter="toTree" 
		scope="session" >
			<forward name="index" path="infoservice.templatetree" redirect="false"/>
			<forward name="TREE_DATA"  	path="/infoservice/treedata.jsp"/>
		</action>    