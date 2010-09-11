1、如何发布项目
	docs/DBMS-SQL可在MySQL中重建数据库和导入系统可运行的基本数据(用户：admin/)
	
	修改DevCMD.bat;
	运行DevCMD.bat;
	在命令行窗口运行：
		mvn package -Dmaven.test.skip=true
		=》mvn自动下载相关jar包到.m2/Repository目录下
		=》编译生成war包至target目录下
	手工将war包发布到Tomcat 5.5.23的webapps目录下，运行Tomcat/bin/Startup.bat即可；
	IE：http://localhost:8080/cop

2、mvn可用命令
	mvn package -Dmaven.test.skip=true	编译生成war包，忽略单元测试
	mvn package							编译生成war包，带单元测试，需先启动数据库（copunittest数据库）
	mvn clean							删除target目录
	mvn eclipse:eclipse
	
3、开发环境
	COP:Eclipse+myeclipse 
		0、先取消自动build	
		1/设置默认字符集GB2312
			M2_REPO
		2、myeclipse：校验排除的资源
		
		3、Java编译兼容版本，JDK库配置
		0、设置自动build
		4/eclipse Error log的处理，删除
		
		Eclipse 3.5.1 Java + m2eclipse + ExploreFS + BeyondCompareUtil
	
	COP：
		struts 1.1
		servlet jsp 2.0
		servlet jstl 1.1.2
		
		Maven 2.2.1+Ant 1.8.1
		Eclipse Helios JEE
	
	

