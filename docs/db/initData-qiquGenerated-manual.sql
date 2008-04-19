
-- 用户 资源和操作初始化,执行前需确认是否需要修改id值
insert into `t_resource` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values 
	    (10041,"User","用户",'0',"/User/listUser.do",8040,'0','YHXX',10);

insert into `t_resoperation` 
			    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (1132,'操作',"包括用户操作权限",10041,'OPT',0);
insert into `t_resoperation` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (1133,'浏览',"包括用户浏览权限",10041,'VIEW',0);

-- 图书 资源和操作初始化,执行前需确认是否需要修改id值
insert into `t_resource` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values 
	    (10042,"Book","图书",'0',"/Book/listBook.do",8040,'0','YHXX',10);

insert into `t_resoperation` 
			    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (1134,'操作',"包括图书操作权限",10042,'OPT',0);
insert into `t_resoperation` 
	    (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values 
	    (1135,'浏览',"包括图书浏览权限",10042,'VIEW',0);
