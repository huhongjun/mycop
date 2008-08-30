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