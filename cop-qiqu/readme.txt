2008/5/14
	��ʼ���ڿμ�����
	
	ʹ��Tips��
	
	
2006/8/2
	����jagTestData.xml�Զ�����CRUD testcase��selenium testcase

2006/8/1
	<!-- û��ɾ���ļ������ /-->

	qiqu�����ļ�ʱͬʱ����һ���ļ��嵥�������յ�����ļ�
	velowebedit��project���кܶ�������
2006/7/31
	timer.xml����
	validator-rules.xml����global���壬Ӧ����struts�Դ���
	validation-privilege.xml	���ϵͳ�����form
	privilege��organization�õ����Լ���mainlayout�����Լ���header��menu��footer��body(menu����)
	һ��ÿ��tiles�ļ��Ŀ�ͷ���屾�ļ��õ���mainLayout��layoutҳ��Ӧ����������ơ�
	tiles-defs��xml�ļ�̫������̫�ӣ���Ҫ�ֽ�
	�����struts�����ļ��е�ȫ�ֶ��壨ģ��乲���𣩣��������ظ���
	struts�������ļ���Ĺ�ϵ���ļ���������
	struts-admin.xml����dailyoffice��action��employee���action����
	gever��tld��ķֹ����������ű���
	web-inf�µ�jspf������
	dtd�ļ�������
	uploadFilesĿ¼�Ĺ���
	�ж��treedata.jsp?һ����xtree.jsp?css������js�ļ���ӳ���ͳһ��ģ��
	�Ƿ��½�Ĵ��������һСģ�壿�ص�½ҳ��ͳһ
	logoͼƬͳһ�������Ƿ�ɹ�����Ϣ��ʾͳһ
	template�¶��ҳ����˺��openϵ�С���openϵ�С�
	login.jspҳ�����templateĿ¼�²��ף������ٵø���
	�еõط���jsp-css.jsp��html-css.jsp��Ϊcssӳ�����Ƭ��,���߶�Ӧ��sys_css.jsp
	[ȫ������������������һ��������dw�в鿴]
	Դ��ע�Ͳ������ɺ꣬����title��desc
	�����������ظ��ࡢ���·���
	var mForm = document.forms[0];
	var action =  mForm.action;
	action =  addPara(action,"action=goUrl(edit)" );
	mForm.action = action
	disButton();
	mForm.submit();
	


folder�ڵ�������������src��action

��TreeVO���ܲ㼶ʱ��nodeid�������κ��ֶΣ������в㼶��nodeid�����ǲ㼶�ֶ�

���ɺ���η�����
	1����Դ��/jsp/Struts�����ļ���������ӦĿ¼��
	2���޸�web.xml�ļ�������struts-config-xxxxxx.xml��tiles-defs-xxxxxx.xml�Ķ��壻


Ҫ���jagModel.xml�л������õ����⣬����qiq�ű���Ԥ����

��������һԪ�����ΪĳһԪ�ص����࣬������֮��relation������

����Щ��qiq�����Ĵ�����velocity�༭��ʱ���ܼ���������������qiq
ת������ļ�����context���ǾͿ��Կ����ˡ�



qiq��ǿ����xml�ļ������ߣ�����Xpath����������������ȡpowerdesigner��jeldoclet��xml�ļ���
��Ԫ���ݡ�


entity��root-packageֻ�����Լ�һ��İ�����Ĭ��Ϊʵ��������ʵ�ʰ�������app��root-package,��qiqu�ű�Ԥ����������һ�����ԣ����ƴ���
�д��Ľ���
	SQL�����Զ����ɣ���dao������vo����vo�Զ�����һ���sql���
	����һЩ����Ӱ��jspҳ������ɣ���maxlength��msg�ȣ�Ҳ�����ͨ��pd������

	
        //���õ�����ڵ����ʾ���Ӳ˵�
        super.setSrc("/dailyoffice/targetmng/treemonthtarget.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
        //���õ��Ҷ�ӽڵ����ӵ��б�ҳ�沢�ҽ�����ID����ȥ
        super.setAction("/dailyoffice/targetmng/listmonthtarget.do?nodeid=" + this.getNodeid());
        //����target
        super.setTarget("middle");
        
        super.setAction("/Book/listBook.do?nodeid=" + this.getNodeid());
        super.setIsfolder("0");
        super.setTarget("middle");
        
treeSQL=>BaseTreeVO����TreeData.jsp=>treexxxxxx.jsp(WebFXLoadTree - �ֹ��趨���ڵ�����)

��ϵ��ģ��:��ͨ��id��ʾ���ƣ������򵯳�����ѡ�񸸱���Ŀ

�����ϸע�ͣ�

entity pk���ֶ�ʱ��xmltree��������������
������������������������������������������������������������������������������������������������������������������������������
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