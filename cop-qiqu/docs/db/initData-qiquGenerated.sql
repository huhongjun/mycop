
-- ?? ��Դ�Ͳ�����ʼ��,ִ��ǰ��ȷ���Ƿ���Ҫ�޸�idֵ
-- id���������м�¼�ظ�����Դ�����ظ�
insert into `t_resource` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values 
	    (8041,"??","??",'0',"/Book/listBook.do",8040,'0','YHXX',10);

insert into `t_resoperation` 
			    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (132,'����',"����??����Ȩ��",8041,'OPT',0);
insert into `t_resoperation` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (133,'���',"����??���Ȩ��",8041,'VIEW',0);

-- ?? ��Դ�Ͳ�����ʼ��,ִ��ǰ��ȷ���Ƿ���Ҫ�޸�idֵ
-- id���������м�¼�ظ�����Դ�����ظ�
insert into `t_resource` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values 
	    (8041,"??","??",'0',"/User/listUser.do",8040,'0','YHXX',10);

insert into `t_resoperation` 
			    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (132,'����',"����??����Ȩ��",8041,'OPT',0);
insert into `t_resoperation` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (133,'���',"����??���Ȩ��",8041,'VIEW',0);
