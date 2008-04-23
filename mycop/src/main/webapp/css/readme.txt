参考 css-config.xml
	默认是goa.css，xmenu.css,xtree.css
	考虑将配置放到initServlet中
	
模板jsp文件中：
<link href="<%=com.gever.goa.web.util.RequestUtils.getCSSPath(pageContext)%>" 
	id="goastylecss" rel="stylesheet" type="text/css">

应该实现用xml文件配置，或有一默认目录可放入一套模板、css、图标等

如：
	res\
		css
		images
			syaman
			goa
			
other目录下是备用的css			
				