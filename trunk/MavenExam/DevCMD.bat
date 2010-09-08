
@set AD_HOME=D:\ADeveloper
@set PATH=%AD_HOME%;%PATH%

@rem ----------- jdk1.5.0 jdk1.5.0 jdk1.5.0 ------------
@set JAVA_HOME=%AD_HOME%\java\jdk1.5.0
@set PATH=%JAVA_HOME%\bin;%PATH%

@rem ----------- ant-1.6.5 ant-1.6.5 ant-1.6.5 ------------
@set ANT_HOME=%AD_HOME%\apache-ant\apache-ant-1.8.1
@set PATH=%AD_HOME%\apache-ant\apache-ant-1.8.1\bin;%PATH%

@rem ----------- maven-2.0 maven-2.0 maven-2.0-----------
@set M2_HOME=%AD_HOME%\apache-maven\maven-2.2.1
@set M2=%AD_HOME%\apache-maven\maven-2.2.1\bin

@set PATH=%AD_HOME%\apache-maven\maven-2.2.1\bin;%PATH%
@set MAVEN_OPTS= -Xms256m -Xmx512m -XX:PermSize=64m -XX:MaxPermSize=128m

@rem ----------- SVN SVN SVN SVN SVN SVN------------
@set PATH=%PATH%;%AD_HOME%\subversion\subversion-1.6.1
@set SVN_EDITOR=notepad.exe
@set APR_ICONV_PATH=%AD_HOME%\subversion\iconv
@rem set LANG=zh_CN.UTF8

@%COMSPEC%

