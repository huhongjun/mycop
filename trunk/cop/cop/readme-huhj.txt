/*******************************************************************************
 *
 *						��Ŀ�ռ�
 *
 *******************************************************************************/
2008/1/1
	�����demoչʾ�����ݿ⡢�������
	�޸�tomcat-users.xml���û�У�����룬mavenĬ��ʹ��admin����Ϊ�յ�¼tomcat�������̨��
	
	����tomcat maven����ĵ�¼����
	
	mvn tomcat:list(��Ӧ���ǵ�ǰ���е�Tomcat�����漰Ŀ¼)
	�޸���ʾ�棬mysql��Tomcat������ʹ�þ���·��
2007/10/18
	����mysql(maven ִ�в����õ�)
	eclipse junit ȫ��ͨ��
	mvn package=������war�����ֹ��ŵ�Tomcat��
		1��QiQu���ɵĲ��������Ӧ�ó���
		2����Ԫ���Գ���
	-Dmaven.test.skip=true


2007/6/25
	����һ��bug������������Դ����Դ����һ��ʱ�����ǵ�Ȩ���ϰ�ť���ǻҵġ� 
2007/6/24
	�ο�springsideд�ĵ�
	
	��springsideΪ�����Ŀ������������������cop��ǰ�˺�ui��������ҳ���ṹ���䣬jscript������
	
2007/6/23
	����Ҫ�£�
		1��ȥ�ۣ�
			������ʽ������@author .+?$	��@author�ո��ټӺ�������һ���ַ�����β��
			��superSearch��ȡ�����������֣��������������ط������������޸ģ������ַ������⣻
			
		2��sysman��goa����ģʽ��ģ�壻
		3������spring+ibatis����ģʽ��ģ�壻
		4�������ӱ�ģ�塢����(�㼶����)�ӱ�ģ�塢���ͱ�ģ�塢����ģ��ؼ�ʹ��ģ�塢
			����-��Ա����Ա�ƶ�ģ�塿
		5��������û�и��ŵ��������������������������Ӳ��Ը��ǣ����ӹ��ܲ��Ը��ǣ�
		
		ģ�����ݼ������ɲ����ࡢ�����������ڲ��ԣ�js
		
	�������ܶ�ҳ�����ظ���Javascript���룻
	֪ʶ�㣺DOM��Frameset��IFrame
2007/6/21
	�ع����������飺
		1.�޸ľֲ�������
		2.�޸��ֶ�����ͬʱ�ع�set��get����
		3.����λ���ƶ�
		4.��ȡһ�δ����Ϊ����
		5.�����ࡢǶ����
		6.�����ڼ̳����е��ƶ� pull up/push down
		7.��ȡ�ӿ� Extract Interface
		8.Extract Local Variable �ع�ȡ��һ�α�ֱ��ʹ�õı��ʽ��Ȼ��������ʽ���ȸ�ֵ��һ���ֲ�������Ȼ����ԭ��ʹ���Ǹ����ʽ�ĵط�ʹ�����������
		9.Extract Constant
		10.Refactor > Encapsulate Field
		11.Change Method Signature �ı䷽���Ĳ������ɼ����Լ�����ֵ������
		12.Introduce Factory �Ҽ������Ĺ��캯����
	Struts Tiles�ĺô���
		1.����JSPĿ¼���ļ����ع�����Ϊ��Ŀ¼���ļ��������ö�д��xml�ļ��У�
		
2007/6/20
	MailDirDAO����ʾmysql sql����﷨����
	�ı��ļ�����-ȫ�����ͣ�*.jsp, *.tld, *.xml,*.js,*.properties,*.java,*.inc,*.jspf,*.html
	
	��һ����
		ҳ������
		�����ע-��ҳ�������������Ŷ���ҵ��
		���ݿ�����
		���䵥Ԫ���Դ��룻
2007/6/19
	1.�漰��·�����ļ����
		java
		jsp		import/ȫ������
		inc		import
		struts�����ļ�	
		tld��tag��
		web.xml��servlet��filter��listener
		properties�����ļ���ָ������ʱ���ص���
2007/6/18
	1����Ϊ��ҳ�׼��ռǣ�����
	2��tips��
	3���Ȳ�sysman��goa��
	cop.t_action_opt_map ������

	����һ�����⣺�޸����ݿ���ƻ�����
	DAO������ʵ�֣�
		ֱ�ӵ���JDBC API������SQLHelper��iBatis
2007/6/18
	myeclipse(����ˣ���Sysdeo Tomcat plugin��) �������飺��tomcat��workĿ¼����Դ�������Զ෢��һЩ���⣬��tag�ģ�Ϊ��ȷ������Ҫ����tomcat��jasper-runtime.jar		
	jsp �ļ�У��ʱ��mapList���ܽ������������java�ļ��޴���������ΪmapList�Ķ�����һ���ⲿ�����ļ��С�
				
2007.6.17
	���ǿ���client api;
	������̫�ࣻ
	��ܻ��ǲ���ģ�
	���Ϊ�Ĳ��֣�	(Ҫ����sakai�ͺ���)
		framework(sample)-����homepage������Ϊһ��web app������
		sysman��organization api��
		tools
		dailyoffice
		infoservice
	����Ҫ�㣺
		struts��غϵ�һ��
	web application:
		action/webapp.action
		dao
			impl
		form
		taglib
		util
		vo/model
	struts-config-privilege.xml·������ /info_error.jsp
	��ʷ���⣺
		struts�Ҳ���message key�����⣺��Ϊ������Ӣ�İ棬�����Ӣ�������ļ�����Ӣ�������ļ�������������һ��û�����ݣ�����������������˶��ѡ�
		����취��ɾ�����ɡ����⣬�����ļ��е��ı����裺
		
		ԭ��Ϊ���Է������frame border��ȣ����ڿɵ��ء�
		
		frameborder=1	=��0		menusetup.jsp,main.jsp
		
		�����ƻ���
			����myeclispse�½���Ŀ��Ȼ���ô��ľ�ķ�ʽ���ؽ�ϵͳ
			����jar��=��framework=��sysman=��tools=��dailyoffice=��infoservice
			�����в�����sql�ͱ�ṹ�������
			�ܷ���ibatis�������ݿ��ȡ����
2007.6.11
	�о�������Զ��eLearningʱ��Myeclipse�����İ汾
	����һ����ʱ���õ�demo��zip����
	(Mysql+Tomcat+COP.war)
	���г���������Ϣָ������һ����
	
	���������spring��iBatis

2007.5.26
	���л�����maven��MYsql(cop/copunittest)

2007.5.23 ��ס�ļ����ַ�����ʲô����eclipse���õ�	
			
2006/8/1
	CSS��ƣ�ͨ��initServlet��css-config.xml�ж�ȡ,TODO

ɾ�����û�ͨ�ò�ѯҳ��user_query��jsp��action�д���Ϊɾ
	ͨ�ò�ѯ����ͦ�м�ֵ��
Tag:
	goa:text ��δʹ��
	css/worldwindow������ݿ����
	submit��13���õ���Ӧ�ò����
	pagination��δʹ�ã���pagelink��splitpage�ϸĽ�
	menu��Ҳ��һ����ҳ���ɱ�ǩ������Ӧ��

sys-data.jsp ֱ����java�����ȡbasedata*.xml

��ģ�飽��gever.ApplicationResources
	struts-config.xml����½��menu��menselect��	=>tiles-main-defs.xml,tiles-defs.xml
	struts-config-admin.xml���������ݡ�message��		=>tiles-defs.xml(������ɾ���������ڵ�һ�������ļ���)
	struts-config-infoservice.xml��infoservice��		=>
	struts-config-dailyoffice.xml���ʼ����ճ̡���Ƭ�С�bbs�����¡���Ȩ��Ŀ�ꡢ�㱨���ܽᡢ��־��
	[���漸�����õ�tiles�������֯��һ��]
	
ģ��organization����com.gever.privilege.ApplicationResource
	struts-config-organization.xml����֯��������tiles-organization-defs.xml��û�ã�����ɾ����
	
ģ��privilege����gever.ApplicationResources
	struts-config-privilege.xml��Ȩ�޹���		����tiles-privilege-defs.xml��û�ã�����Ӧjsp�ļ�������ɾ����
												����		validator-rules.xml��struts�Դ��ġ�
														validation-privilege.xml�����á�
������������������������������������������������������������������������������������������������������������������������������														
����privilege��loginAction.do������ģ��(login.do)
	�ǳ���/privilege/loginAction.do?action=logout
	���룺	<gdp:context/>/Login.do?action=welcome
			<%=context%>/Login.do?action=login



�������⣺
	�����е���������factory ģʽ�У�
	sql.properties·��


2006/7/30
	����ѡ�񴰿�ͳһ��pub.js��function openSelectWindow(type,id,name)
	
	<html:button property="selectnotice" value="ѡ����Ա" styleClass="button"  onclick="javascript:openSelectWindow('menselect','vo.notice','vo.noticename');"/>


2006/7/26
	idManager��SequenceMan����ʹ�ò�ͬ��t_system_id,geverid

�㶫�����Ƽ����޹�˾
@author ���˱�
GDCA��Ϣƽ̨
�����칫�Զ���ƽ̨


�����������Զ��·�� http://localhost:8080/gdca/cell/template/fiveyearlayout_template.cll

mvn tomcat:list
mvn tomcat:info
mvn tomcat:deploy
mvn tomcat:resources
mvn tomcat:roles
mvn package tomcat:redeploy
mvn war:exploded tomcat:redeploy
mvn war:inplace tomcat:redeploy
mvn tomcat:redeploy
mvn tomcat:undeploy
mvn tomcat:start
mvn tomcat:sessions
mvn tomcat:stop
mvn war:exploded tomcat:exploded
mvn war:inplace tomcat:inplace=>��webappĿ¼����װwarĿ¼�����ļ���������tomcat


Set MAVEN_OPTS=-Xmx512m
mvn site

�޸���Ŀ��
	sysman 1.5
	gdca 0818

gdp1.5��Դ��Ͳ���ֱ����sysman1.5���޸ģ������ٸ���

2006/7/13 ��sysman 1.5������������gdp1��5
2006.7.4
�ĵ����죺
	1��������������
	2��

/WEB-INF/database-config.xml����ָ�����ݿⱸ���漰�����ݿ����������嵥
ͨ������database-config.xml�ļ��õ�Ҫ���������ݿ���������
 *    ��Ҫ�����ļ�λ�ã�����basedata-config.xml(�в���currentָ���Ǹ�����������)�ļ��õ�Ҫ�������ݵĲ�ͬ���ݿ������
 *    ִ�������ddl�ļ�
ddlfile���ڴ�ŵ�����sql���
TestSQLHelper ��mysql��Ҫע���������С�
Ҫ�ڶ��β��Բ�ok

2006.7.4 ֮ǰ
Դ�� SYSMAN1.5 NEW,��SYSMAN 1.2


�õ�DB2���ݿ⣬Oracle�Ľű��붡�����ı���һ�£���������汾�ĳ���һ��
��ôGDCA���õ�Sysman�����ĸ��汾�أ�

SYSMAN 1.5û��web�ļ�������new��web�ļ�һ����ʱ������


�����ݿ��йص����ò���
	system.properties
	/Shark.conf
	
	/sharkĿ¼�µ�XPDL��jawe 1.4.2�п���
	
	admin=MD5(21-23-2F-29-7A-57-A5-A7-43-89-4A-0E-4A-80-1F-C3)
	
	
	
	��GDP��Դ��ϲ���������3���ظ���ɾ����sysman��3���ظ���
	
	
��organization��privilege�¶���UserDAO�ӿ����ʵ����DefaultUserDAO��������û���ظ�

2006.2.3
	������Դ��Ĳ��֣�uniquery tag����gdp-base....jar
	ɾ����DefaultExceptionHandler����Ϊgdca�����ظ����˴�δ��
	
	
	file://d://mavenworkshop//sysman//target//sysman