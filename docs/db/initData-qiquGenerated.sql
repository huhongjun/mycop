
-- ͼ�� ��Դ�Ͳ�����ʼ��,ִ��ǰ��ȷ���Ƿ���Ҫ�޸�idֵ
-- id���������м�¼�ظ�����Դ�����ظ�
insert into `t_resource` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values 
	    (8041,"ͼ��","ͼ��",'0',"/Book/listBook.do",8040,'0','YHXX',10);

insert into `t_resoperation` 
			    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (132,'����',"����ͼ�����Ȩ��",8041,'OPT',0);
insert into `t_resoperation` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (133,'���',"����ͼ�����Ȩ��",8041,'VIEW',0);

-- �û� ��Դ�Ͳ�����ʼ��,ִ��ǰ��ȷ���Ƿ���Ҫ�޸�idֵ
-- id���������м�¼�ظ�����Դ�����ظ�
insert into `t_resource` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values 
	    (8041,"�û�","�û�",'0',"/User/listUser.do",8040,'0','YHXX',10);

insert into `t_resoperation` 
			    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (132,'����',"�����û�����Ȩ��",8041,'OPT',0);
insert into `t_resoperation` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (133,'���',"�����û����Ȩ��",8041,'VIEW',0);
