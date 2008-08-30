用到的开源项目：
	jsap		http://sourceforge.net/projects/jsap
	xmen
	veloedit

有Eclipse帮助
有一个扩展QiQu的Tutorial

和BIE类比，都是在流程节点间用xml文档对象作消息

引入Junit测试

bug
1/main tutorial写错了文件名
2/showdialog通过run script和preview分别启动一个实例，关闭其中一个时eclispe异常退出

========================================
参数说明：
	<Java2XML 
		sourceFile=" ch.aloba.qiqu.extend  -sourcepath .\\src\\java " 
		outputPath="output"
	/>
	
	sourceFile - 指定javadoc作用的package，指定源文件扫描路径；
	outputPath - 指定生成的java类文件的xml文件存放路径；
	
修改代码后需重新发布qiqu扩展，执行build.xml的buildLib命令。
要启用让扩展库的命令，需配置QiQu插件。

generator-extra开发的就是和本项目类似的功能－为java源码生成xml文件。
基于jelDoclet，为了能让它在eclipse下运行，不得以将tools.jar的类解包放到了jre的classes下。

一个随写随用随调的基于模板的xml文件转换平台。
=========================================
velocity 模板设置输入model后，可预览（context menu）

如果能记下窗口大小、位置更好

生成的jar包是否可以独立运行

java ch.aloba.qiqu.script.run.QiQuScriptRun -s script\showDialogAll.qiq

注意上面的的文件名是大小写相关的
gui输入中文后再次加载出错

指定Xpath要从根下的单元指定开始，只能直接取属性值，否则要用textcontent


content assist自动根据xml tag中是否有后缀list来判断能否作为foreach的记录集

有时设置context不成功，运行一次qiqu script后变好
==================================
velocity.properties 可用于设置velocity的运行参数
