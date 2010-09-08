1、关于COP运行演示版
	mysql  			<= mysql-noinstall-5.0.41-win32.zip
		1.配置my.ini文件，需要绝对路径d:/cop/mysql/
		2.root/root

	tomcat 			<= apache-tomcat-5.5.20.zip
		1.sysman.war包放入webapps目录下
	
	jre 1.4.2		<= 路径设置：JAVA_HOME，PATH

2、运行步骤
	1）mysql_start.bat
	2)start-tomcat-5.5.20.bat
	3)ie: http://loalhost:8080/syaman,user:admin,pwd:
	4）可以尝试登录页面的Selenium测试。

注：某种情况下登录页面出错 - 说找不到message key，可能与操作系统区域和语言设置有关。


	