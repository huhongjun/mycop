2008/5/14
	开始用于课件生成
	
	使用Tips：
	
	
2006/8/2
	根据jagTestData.xml自动生成CRUD testcase和selenium testcase

2006/8/1
	<!-- 没有删除文件的命令？ /-->

	qiqu生成文件时同时生成一份文件清单，可以照单清除文件
	velowebedit在project下有很多配置项
2006/7/31
	timer.xml无用
	validator-rules.xml都是global定义，应该是struts自带的
	validation-privilege.xml	针对系统管理的form
	privilege、organization用的是自己的mainlayout，有自己的header、menu、footer、body(menu共用)
	一般每个tiles文件的开头定义本文件用到的mainLayout《layout页面应有特殊的名称》
	tiles-defs。xml文件太大，内容太杂，需要分节
	整理各struts配置文件中的全局定义（模块间共享吗），看有无重复；
	struts各配置文件间的关系，文件命名规则；
	struts-admin.xml中有dailyoffice的action，employee相关action无用
	gever各tld间的分工合作、分门别类
	web-inf下的jspf有用吗？
	dtd文件必须吗？
	uploadFiles目录的管理？
	有多个treedata.jsp?一样吗？xtree.jsp?css、公共js文件的映入可统一用模板
	是否登陆的代码可做成一小模板？重登陆页面统一
	logo图片统一？操作是否成功的信息显示统一
	template下多个页面相撕《open系列、非open系列》
	login.jsp页面放在template目录下不妥？？至少得改名
	有得地方用jsp-css.jsp、html-css.jsp作为css映入代码片断,两者都应用sys_css.jsp
	[全部拷贝，批量改名，一次生成在dw中查看]
	源码注释部分做成宏，传入title和desc
	代码重政：重复类、重新分类
	var mForm = document.forms[0];
	var action =  mForm.action;
	action =  addPara(action,"action=goUrl(edit)" );
	mForm.action = action
	disButton();
	mForm.submit();
	


folder节点有两个动作：src和action

当TreeVO不管层级时，nodeid可以是任何字段，但若有层级，nodeid必须是层级字段

生成后如何发布？
	1）将源码/jsp/Struts配置文件拷贝到相应目录；
	2）修改web.xml文件，增加struts-config-xxxxxx.xml和tiles-defs-xxxxxx.xml的定义；


要解决jagModel.xml中互相引用的问题，可在qiq脚本中预处理。

包括将另一元素添加为某一元素的子类，如解决表之间relation的问题

但这些在qiq中做的处理，用velocity编辑器时不能见到，不过可以用qiq
转换后的文件来设context，那就可以看到了。



qiq是强悍的xml文件处理工具（采用Xpath技术），可用于提取powerdesigner、jeldoclet的xml文件中
的元数据。


entity的root-package只设置自己一层的包名（默认为实体名），实际包名加上app的root-package,由qiqu脚本预处理生成另一个属性－名称待定
有待改进：
	SQL语句的自动生成－》dao关联上vo，由vo自动生成一般的sql语句
	定义一些属性影响jsp页面的生成，如maxlength、msg等，也许可以通过pd来定义

	
        //设置点击父节点后显示其子菜单
        super.setSrc("/dailyoffice/targetmng/treemonthtarget.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
        //设置点击叶子节点连接到列表页面并且将部门ID传过去
        super.setAction("/dailyoffice/targetmng/listmonthtarget.do?nodeid=" + this.getNodeid());
        //设置target
        super.setTarget("middle");
        
        super.setAction("/Book/listBook.do?nodeid=" + this.getNodeid());
        super.setIsfolder("0");
        super.setTarget("middle");
        
treeSQL=>BaseTreeVO＝》TreeData.jsp=>treexxxxxx.jsp(WebFXLoadTree - 手工设定根节点名称)

关系表模板:如通过id显示名称，下拉或弹出窗口选择父表条目

添加详细注释；

entity pk多字段时与xmltree关联可能有问题
－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－
The xml format

The only valid element in the xml file is the tree item. A tree item can contain zero, one or more tree items.
Attributes

There are five valid attributes that you can provide on a tree item.
Name 	Description
text 	Required. The text label for the tree item.
xmlSrc 	Optional. The source for the xml file to load when expanded.
action 	Optional. The action (uri) associated with the tree item.
icon 	Optional. The icon image to use for this item. In case this item is a folder this will be used when the folder is closed.
openIcon 	Optional. The icon image to use for this item when it is opened. This is only used for folder items that are opened/expanded.