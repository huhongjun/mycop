@echo off

AltovaXML /xslt1 "D:\MyEclipseWork\workspace\QiQu-COP\script\MapTojag.xslt" /in "D:\MyEclipseWork\workspace\QiQu-COP\model\input\COP-mysql.pdm.xml" /out "D:\MyEclipseWork\workspace\QiQu-COP\model\input\jag.xml" %*
IF ERRORLEVEL 1 EXIT/B %ERRORLEVEL%
