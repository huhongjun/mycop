�õ��Ŀ�Դ��Ŀ��
	jsap		http://sourceforge.net/projects/jsap
	xmen
	veloedit

��Eclipse����
��һ����չQiQu��Tutorial

��BIE��ȣ����������̽ڵ����xml�ĵ���������Ϣ

����Junit����

bug
1/main tutorialд�����ļ���
2/showdialogͨ��run script��preview�ֱ�����һ��ʵ�����ر�����һ��ʱeclispe�쳣�˳�

========================================
����˵����
	<Java2XML 
		sourceFile=" ch.aloba.qiqu.extend  -sourcepath .\\src\\java " 
		outputPath="output"
	/>
	
	sourceFile - ָ��javadoc���õ�package��ָ��Դ�ļ�ɨ��·����
	outputPath - ָ�����ɵ�java���ļ���xml�ļ����·����
	
�޸Ĵ���������·���qiqu��չ��ִ��build.xml��buildLib���
Ҫ��������չ������������QiQu�����

generator-extra�����ľ��Ǻͱ���Ŀ���ƵĹ��ܣ�ΪjavaԴ������xml�ļ���
����jelDoclet��Ϊ����������eclipse�����У������Խ�tools.jar�������ŵ���jre��classes�¡�

һ����д��������Ļ���ģ���xml�ļ�ת��ƽ̨��
=========================================
velocity ģ����������model�󣬿�Ԥ����context menu��

����ܼ��´��ڴ�С��λ�ø���

���ɵ�jar���Ƿ���Զ�������

java ch.aloba.qiqu.script.run.QiQuScriptRun -s script\showDialogAll.qiq

ע������ĵ��ļ����Ǵ�Сд��ص�
gui�������ĺ��ٴμ��س���

ָ��XpathҪ�Ӹ��µĵ�Ԫָ����ʼ��ֻ��ֱ��ȡ����ֵ������Ҫ��textcontent


content assist�Զ�����xml tag���Ƿ��к�׺list���ж��ܷ���Ϊforeach�ļ�¼��

��ʱ����context���ɹ�������һ��qiqu script����
==================================
velocity.properties ����������velocity�����в���
