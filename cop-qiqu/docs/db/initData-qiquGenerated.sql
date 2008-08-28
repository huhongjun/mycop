
-- ?? 资源和操作初始化,执行前需确认是否需要修改id值
-- id可能与已有记录重复，资源编码重复
insert into `t_resource` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values 
	    (8041,"??","??",'0',"/Book/listBook.do",8040,'0','YHXX',10);

insert into `t_resoperation` 
			    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (132,'操作',"包括??操作权限",8041,'OPT',0);
insert into `t_resoperation` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (133,'浏览',"包括??浏览权限",8041,'VIEW',0);

-- ?? 资源和操作初始化,执行前需确认是否需要修改id值
-- id可能与已有记录重复，资源编码重复
insert into `t_resource` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values 
	    (8041,"??","??",'0',"/User/listUser.do",8040,'0','YHXX',10);

insert into `t_resoperation` 
			    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (132,'操作',"包括??操作权限",8041,'OPT',0);
insert into `t_resoperation` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (133,'浏览',"包括??浏览权限",8041,'VIEW',0);
