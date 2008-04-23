目录说明：[参考echcache]
	resource	存放图片、书签icon、html页面、图片、pdf等格式文件
===========
For example, /start/introduction.xml will be generated as /start/introduction.html on the final site.

===========
Creating Your Own Index Page

	Maven will generate a default index.html page for your project based on the description element in the POM. However, this is usually not very verbose, so you may like to create your own index page.
	To do this, simply create an index.xml document in the base xdocs directory in the same style as the other documents (as explained in the previous sections), and this will be used instead of the generated document. 

============
Maven 2 Site Plugin（6）

    * site:deploy is used to deploy the generated site using scp or file protocol to the site specified in the <distributionManagement> section of the pom.
    *- mvn site:attach-descriptor adds the site descriptor to the list of files to be installed/deployed. For more references of the site descriptor, here's a link http://maven.apache.org/site.html.
    * site:site is used generate a site for the project.
    * site:run starts the site up, rendering documents as requested for faster editing. It uses Jetty for the webapp server.
    * site:stage-deploy deploys the generated site in a staging or mock directory on the site specified in the <distributionManagement> section of the pom. It also supports scp and file protocol for deployment.
    * site:stage generate a site in a local staging or mock directory based on the site url specified in the <distributionManagement> section of the pom.
	
	<inputEncoding>UTF-8</inputEncoding>        
	
Maven Project Info Reports Plugin（10）

    * project-info-reports:cim is used to generate the Project Continuous Integration System report.
    * project-info-reports:dependencies is used to generate the Project Dependencies report.
    * project-info-reports:dependency-convergence is used to generate the Project Dependency Convergence report for reactor builds.
    * project-info-reports:issue-tracking is used to generate the Project Issue Tracking report.
    * project-info-reports:license is used to generate the Project License report.
    * project-info-reports:mailing-list is used to generate the Project Mailing List report.
    *- project-info-reports:index is used to generate the Project index page.
    * project-info-reports:summary is used to generate the Project information reports summary.
    * project-info-reports:scm is used to generate the Project Source Code Management report.
    * project-info-reports:project-team is used to generate the Project Team report. 

Maven 2 Changes Plugin（4）
    * [ mvn changes:announcement-mail ] send a release announcement via email.
    * [ mvn changes:announcement-generate ] generate a release announcement.
    *- [ mvn changes:changes-report ] create a report showing what has changed between different releases of the project.
    * [ mvn changes:jira-report ] creates a report on issues downloaded from JIRA.

============
index系统占用

1. maven site plugin 配置
        <configuration>   
          <locales>zh_CN,en</locales>
          <inputEncoding>UTF-8</inputEncoding>        
          <outputEncoding>UTF-8</outputEncoding>        
        </configuration>
2. 生成页面都是utf-8
	fml有中文		ok
	site.xml	有问题
	
	
