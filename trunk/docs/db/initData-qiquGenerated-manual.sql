
-- �û� ��Դ�Ͳ�����ʼ��,ִ��ǰ��ȷ���Ƿ���Ҫ�޸�idֵ
insert into `t_resource` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values 
	    (10041,"User","�û�",'0',"/User/listUser.do",8040,'0','YHXX',10);

insert into `t_resoperation` 
			    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (1132,'����',"�����û�����Ȩ��",10041,'OPT',0);
insert into `t_resoperation` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (1133,'���',"�����û����Ȩ��",10041,'VIEW',0);

-- ͼ�� ��Դ�Ͳ�����ʼ��,ִ��ǰ��ȷ���Ƿ���Ҫ�޸�idֵ
insert into `t_resource` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values 
	    (10042,"Book","ͼ��",'0',"/Book/listBook.do",8040,'0','YHXX',10);

insert into `t_resoperation` 
			    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (1134,'����',"����ͼ�����Ȩ��",10042,'OPT',0);
insert into `t_resoperation` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (1135,'���',"����ͼ�����Ȩ��",10042,'VIEW',0);
