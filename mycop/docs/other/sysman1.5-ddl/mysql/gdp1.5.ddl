#-----------------------
#--
#-- Step 1: 初始化数据库
#--
#-----------------------
 
#--------------------------
#--
#-- Step 2: 初始化数据表
#--
#--------------------------
 
#-- 通用查询表结构
CREATE TABLE UNIQUERY_DATA(
	COLNAME VARCHAR(30) NOT NULL,		
	COLALIAS VARCHAR(30),				
	TBNAME VARCHAR(64) NOT NULL,		
	TBREMARK VARCHAR(64),				
	COLTYPE VARCHAR(16) ,				
	LENGTH INT,							
	SCALE SMALLINT,						
	CASTTYPE VARCHAR(16),				
	COLNO INT,							
	
	IN_HIDDEN SMALLINT NOT NULL DEFAULT 0,	
	OUT_HIDDEN SMALLINT NOT NULL DEFAULT 0,	
	IS_PK SMALLINT NOT NULL DEFAULT 0 ,		
	IS_FK SMALLINT NOT NULL DEFAULT 0 ,	
	IGNORE_FK SMALLINT NOT NULL DEFAULT 0,	
	REF_TBNAME VARCHAR(64) ,				
	REF_KEYCOL VARCHAR(30) ,				
	REF_VALCOL VARCHAR(30) ,
	PRIMARY KEY(COLNAME, TBNAME));


CREATE TABLE GEVERID
 (IDTYPE  INTEGER  NOT NULL,
  ID      INTEGER  NOT NULL
 );

CREATE TABLE T_DEPARTMENT
 (ID              INTEGER         NOT NULL,
  CODE            VARCHAR(30)     NOT NULL,
  NAME            VARCHAR(50),
  DESCRIPTION     VARCHAR(100),
  DEPARTMENTTYPE  VARCHAR(30),
  PARENT_ID       INTEGER,
  ORDERID         INTEGER	  NOT NULL DEFAULT 0,
  FUNCTIONARY     VARCHAR (50)
 );

ALTER TABLE T_DEPARTMENT
  ADD UNIQUE
   (CODE
   );  

CREATE TABLE T_DEPARTMENT_PERSON
 (DEPARTMENT_ID  INTEGER  NOT NULL,
  ID             INTEGER  NOT NULL
 );

CREATE TABLE T_JOB
 (ID             INTEGER         NOT NULL,
  NAME           VARCHAR(50),
  DESCRIPTION    VARCHAR(100),
  DEPARTMENT_ID  INTEGER
 );

CREATE TABLE T_JOB_PERSON
 (JOB_ID  INTEGER  NOT NULL,
  ID      INTEGER  NOT NULL
 );

CREATE TABLE T_PKICA
 (USER_ID  INTEGER          NOT NULL,
  KEYLABEL VARCHAR(50),
  PUBKEY   LONGTEXT,
  CERT     LONGTEXT
 );

CREATE TABLE T_RESOPERATION
 (ID           INTEGER         NOT NULL,
  NAME         VARCHAR(50),
  DESCRIPTION  VARCHAR(100),
  RESOURCE_ID  INTEGER,
  CODE         VARCHAR(100)     NOT NULL,
  ORDERID      INTEGER         NOT NULL DEFAULT 0
 );

CREATE TABLE T_RESOURCE
 (ID           INTEGER         NOT NULL,
  NAME         VARCHAR(50),
  DESCRIPTION  VARCHAR(100),
  RESOURCETYPE VARCHAR(30),
  LINK         VARCHAR(200),
  PARENT_ID    INTEGER,
  TARGET       CHARACTER(1),
  CODE         VARCHAR(100)     NOT NULL  DEFAULT '',
  ORDERID      INTEGER         NOT NULL DEFAULT 0
 );

CREATE TABLE T_ROLE
 (ID           INTEGER         NOT NULL,
  NAME         VARCHAR(50)     NOT NULL,
  DESCRIPTION  VARCHAR(100),
  ROLETYPE     VARCHAR(30)
 );
  
ALTER TABLE T_ROLE
  ADD UNIQUE
   (NAME
   );  

CREATE TABLE T_ROLE_PERMISSION
 (ROLE_ID          INTEGER,
  RESOURCE_ID      INTEGER,
  RESOPERATION_ID  INTEGER
 );

CREATE TABLE T_SYSID
 (IDTYPE  INTEGER  NOT NULL,
  ID      INTEGER  NOT NULL
 );

CREATE TABLE T_SYSTEM_ID
 (IDTYPE  INTEGER  NOT NULL,
  ID      INTEGER  NOT NULL
 );

CREATE TABLE T_SYSTEM_LOG
 (ID           BIGINT          NOT NULL,
  OTIME        VARCHAR(20)     NOT NULL,
  USERNAME     VARCHAR(50),
  MODULE       VARCHAR(50),
  IPADDRESS    VARCHAR(50),
  ACTION       VARCHAR(30),
  FILTERLEVEL  SMALLINT,
  TYPE         SMALLINT,
  MEMO         VARCHAR(500)
 );

CREATE TABLE T_USER
 (ID        INTEGER         NOT NULL,
  USERNAME  VARCHAR(30)     NOT NULL,
  PASSWORD  VARCHAR(50),
  USERTYPE  VARCHAR(30),
  LEVEL     VARCHAR(10),
  STATUS    VARCHAR(20),
  VALIDDATE VARCHAR(20),
  NAME      VARCHAR(30),
  CODE      VARCHAR(30),
  GENDER    VARCHAR(6)
 );

CREATE TABLE T_USER_MENU
 (EMPID     VARCHAR(10)     NOT NULL,
  NODEID    VARCHAR(50)     NOT NULL,
  NODENAME  VARCHAR(100),
  PARENTID  VARCHAR(50),
  LINK      VARCHAR(200),
  LINKMODE  INTEGER         NOT NULL  DEFAULT 0,
  ISFOLDER  VARCHAR(1)      NOT NULL  DEFAULT '0',
  ISHIDED   VARCHAR(1)      NOT NULL  DEFAULT '0',
  ORDERID   BIGINT          NOT NULL  DEFAULT 0,
  ISMENU    VARCHAR(1)      NOT NULL  DEFAULT '1',
  FLAG      VARCHAR(1)      NOT NULL  DEFAULT '0',
  MEMO      VARCHAR(255)
 );

CREATE TABLE T_USER_PERMISSION
 (USER_ID          INTEGER,
  RESOURCE_ID      INTEGER,
  RESOPERATION_ID  INTEGER
 );

CREATE TABLE T_USER_ROLE
 (USER_ID  INTEGER,
  ROLE_ID  INTEGER
 );
CREATE TABLE T_LEVEL
 (id           INTEGER         NOT NULL,
  code         VARCHAR(30),
  name         VARCHAR(30),
  description  VARCHAR(100)
 );    
 ALTER TABLE T_LEVEL
  ADD PRIMARY KEY
   (ID
   );     
#--modified by eddy on 20041204 -------\\\
CREATE TABLE T_ACTION_OPT_MAP (
  ID int(11) NOT NULL auto_increment,
  ACTION varchar(200) NOT NULL ,
  METHOD varchar(100) NOT NULL ,
  RES_CODE varchar(100) NOT NULL ,
  RES_OPT_CODE varchar(100) NOT NULL,
  PRIMARY KEY  (ID),
  UNIQUE (ACTION,METHOD)
);
#--modified by eddy on 20041204 -------///

CREATE TABLE T_USER_CSS (
	ID		INTEGER  NOT NULL ,
	USER_ID		INTEGER , 
	CSS_ID		INTEGER , 
	PRIMARY KEY (ID));

ALTER TABLE GEVERID
  ADD CONSTRAINT SQL040615152006870 PRIMARY KEY
   (IDTYPE
   );

ALTER TABLE T_DEPARTMENT
  ADD CONSTRAINT P_KEY_1 PRIMARY KEY
   (ID
   );

ALTER TABLE T_DEPARTMENT_PERSON
  ADD CONSTRAINT SQL040614140550340 PRIMARY KEY
   (DEPARTMENT_ID,
    ID
   );

ALTER TABLE T_JOB
  ADD CONSTRAINT P_KEY_1 PRIMARY KEY
   (ID
   );

ALTER TABLE T_JOB_PERSON
  ADD CONSTRAINT SQL040614145034210 PRIMARY KEY
   (JOB_ID,
    ID
   );

ALTER TABLE T_PKICA
  ADD CONSTRAINT P_KEY_1 PRIMARY KEY
   (USER_ID
   );

ALTER TABLE T_RESOPERATION
  ADD CONSTRAINT P_KEY_1 PRIMARY KEY
   (ID
   );

ALTER TABLE T_RESOURCE
  ADD CONSTRAINT P_KEY_1 PRIMARY KEY
   (ID
   );

ALTER TABLE T_ROLE
  ADD CONSTRAINT P_KEY_1 PRIMARY KEY
   (ID
   );

ALTER TABLE T_SYSID
  ADD CONSTRAINT SQL040614105330810 PRIMARY KEY
   (IDTYPE
   );

ALTER TABLE T_SYSTEM_ID
  ADD CONSTRAINT SQL040614105330810 PRIMARY KEY
   (IDTYPE
   );

ALTER TABLE T_USER
  ADD CONSTRAINT P_KEY_1 PRIMARY KEY
   (ID
   );

ALTER TABLE T_USER_MENU
  ADD CONSTRAINT SQL040616103947920 PRIMARY KEY
   (EMPID,
    NODEID
   );

    
    
#--------------------------
#--
#-- Step 3: 初始化数据
#--
#-------------------------- 

#-- 初始化唯一ID
INSERT INTO GEVERID(IDTYPE, ID) 
VALUES (0, 1000);
INSERT INTO GEVERID(IDTYPE, ID) 
VALUES (1, 1000);
INSERT INTO GEVERID(IDTYPE, ID) 
VALUES (2, 1000);

#-- huyoxo edit ---------------------------------------------------------------------------------------------
#-- 初始化行政级别 --
INSERT INTO T_LEVEL(ID, CODE, NAME, DESCRIPTION) VALUES(1, 'GDP-NO-LEVEL', '不选择级别', '用户默认级别');

#-- 初始化用户表
INSERT INTO T_USER(ID, USERNAME, PASSWORD, USERTYPE, LEVEL, STATUS, VALIDDATE, NAME, CODE, GENDER) 
VALUES (1, 'admin', 'D4-1D-8C-D9-8F-00-B2-04-E9-80-09-98-EC-F8-42-7E', '1', '1', '1', '2010-12-12', '系统管理员', '0000', '1');


#-- 初始化角色表
INSERT INTO T_ROLE(ID, NAME, DESCRIPTION, ROLETYPE) 
VALUES (1, '超级管理员', '超级管理员', '');


#-- 用户角色表
INSERT INTO T_USER_ROLE(USER_ID, ROLE_ID) 
VALUES (1, 1);


#-- 初始化资源表
INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (0, '系统', '系统', '', '', -1, '3', 'GDP-ROOT');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (1, '系统管理', '系统管理', 'link', '', 0, '0', 'GDP-XTGL');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (2, '组织管理', '组织管理', 'link', '', 1, '0', 'GDP-ZZGL');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (3, '权限管理', '权限管理', 'link', '', 1, '0', 'GDP-QXGL');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (5, '组织管理', '组织管理', 'link', '/jsp/organization/department/department-main.jsp', 2, '0', 'GDP-BMGL');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (6, '岗位管理', '岗位管理', 'link', '/jsp/organization/job/job-main.jsp', 2, '0', 'GDP-GWGL');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (7, '人员管理', '人员管理', 'link', '/jsp/organization/employee/employee-main.jsp', 2, '0', 'GDP-RYGL');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (8, '用户管理', '用户管理', 'link', '/privilege/userAction.do?action=list', 3, '0', 'GDP-YHGL');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (9, '角色管理', '角色管理', 'link', '/privilege/roleAction.do?action=list', 3, '0', 'GDP-JSGL');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (10, '操作管理', '操作管理', 'link', '/jsp/privilege/opt/opt-main.jsp', 3, '0', 'GDP-CZGL');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (11, '资源管理', '资源管理', 'link', '/jsp/privilege/resource/res-main.jsp', 3, '0', 'GDP-ZYGL');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (12, '菜单定制', '菜单定制', 'link', '/jsp/menusetup/frame.jsp', 1, '0', 'GDP-CDDZ');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (13, '操作日志', '操作日志', 'link', '/log/loglist.do?action=list', 1, '0', 'GDP-CZRZ');

#----- 12.11 huyoxo edit -----------------------------------------------------------------------------------------------
INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (14, '操作映射', '操作映射', 'link', '/privilege/permissionMapAction.do?action=list', 3, '0', 'GDP-CZYS');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (4, '工作流管理', '工作流管理', 'link', '', 1, '0', 'GDP-LCGL');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (15, '流程包管理', '流程包管理', 'link', '/workflow/packageMgr.do?action=list', 4, '0', 'GDP-LCBGL');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (16, '流程管理', '流程管理', 'link', '/workflow/processMgr.do?action=list', 4, '0', 'GDP-LCGL');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (17, '待办事项', '待办事项', 'link', '/workflow/WorkItemAction.do?method=list', 4, '0', 'GDP-DBSX');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (18, '我的流程', '我的流程', 'link', '/workflow/processMgr.do?action=noAdminList', 4, '0', 'GDP-WDLC');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (19, '行政级别', '行政级别', 'link', '/privilege/levelAction.do?action=list', 2, '0', 'GDP-XZJB');

INSERT INTO T_RESOURCE(ID, NAME, DESCRIPTION, RESOURCETYPE, LINK, PARENT_ID, TARGET, CODE) 
VALUES (20, '基础数据', '基础数据', 'link', '/basedata/BaseDataAction.do?action=toExport', 1, '0', 'GDP-JCSJ');
#----- 12.11 huyoxo edit -----------------------------------------------------------------------------------------------

#-- 初始化资源操作表
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (1, '所有', '包括新增、修改、删除部门及设置部门的岗位和人员等功能。', 5, 'ALL');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (2, '浏览', '仅仅只能查看部门的结构和信息。', 5, 'VIEW');

INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (3, '所有', '包括新增、修改、删除岗位及设置岗位的人员等功能。', 6, 'ALL');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (4, '浏览', '仅仅只能查看岗位信息。', 6, 'VIEW');

INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (5, '所有', '包括设置部门的人员和为人员设置岗位等功能。', 7, 'ALL');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (6, '浏览', '包括查看人员信息和对人员进行查询等功能。', 7, 'VIEW');

INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (7, '所有', '包括新增、修改、删除用户及设置用户的权限和角色等功能。', 8, 'ALL');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (8, '浏览', '包括查看用户信息和对用户进行查询等功能。', 8, 'VIEW');

INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (9, '所有', '包括新增、修改、删除角色及设置角色的权限等功能。', 9, 'ALL');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (10, '浏览', '仅仅只能查看角色信息。', 9, 'VIEW');

INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (11, '所有', '包括新增、修改、删除资源操作等功能。', 10, 'ALL');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (12, '浏览', '仅仅只能查看操作信息。', 10, 'VIEW');

INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (13, '所有', '包括新增、修改、删除资源等功能。', 11, 'ALL');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (14, '浏览', '仅仅只能查看资源信息。', 11, 'VIEW');

INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (15, '所有', '包括本模块的所有功能。', 12, 'ALL');

INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (16, '所有', '包括导出和删除操作日志信息等功能。', 13, 'ALL');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (17, '浏览', '包括查询和浏览操作日志信息等功能。', 13, 'VIEW');

#----- 12.11 huyoxo edit -----------------------------------------------------------------------------------------------
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (18, '所有', '', 14, 'ALL');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (19, '所有', '包括管理和卸载流程包等功能', 15, 'ALL');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (20, '浏览', '浏览流程包等功能', 15, 'VIEW');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (21, '所有', '包括挂起和终止流程实例等功能', 16, 'ALL');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (22, '浏览', '浏览流程实例等功能', 16, 'VIEW');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (23, '所有', '', 17, 'ALL');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (24, '浏览', '', 17, 'VIEW');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (25, '所有', '', 18, 'ALL');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (26, '浏览', '', 18, 'VIEW');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (27, '系统默认菜单', '', 12, 'MRCD');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (28, '所有', '', 19, 'ALL');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (29, '浏览', '', 19, 'VIEW');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (30, '资源排序', '', 11, 'ZYPX');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (31, '部门排序', '', 5, 'BMPX');
INSERT INTO T_RESOPERATION(ID, NAME, DESCRIPTION, RESOURCE_ID, CODE) 
VALUES (32, '所有', '', 20, 'ALL');
#----- 12.11 huyoxo edit -----------------------------------------------------------------------------------------------

#-- 初始化角色权限表
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 5, 1);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 5, 2);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 6, 3);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 6, 4);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 7, 5);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 7, 6);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 8, 7);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 8, 8);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 9, 9);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 9, 10);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 10, 11);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 10, 12);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 11, 13);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 11, 14);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 12, 15);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 13, 16);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 13, 17);

#----- 12.11 huyoxo edit -----------------------------------------------------------------------------------------------
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 14, 18);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 15, 19);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 15, 20);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 16, 21);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 16, 22);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 17, 23);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 17, 24);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 18, 25);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 18, 26);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 12, 27);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 19, 28);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 19, 29);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 11, 30);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 5, 31);
INSERT INTO T_ROLE_PERMISSION(ROLE_ID, RESOURCE_ID, RESOPERATION_ID) 
VALUES (1, 20, 32);
#----- 12.11 huyoxo edit -----------------------------------------------------------------------------------------------

#-- 初始化菜单定制表
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '0', '系统', '-1', '', 0, '1', '0', 0, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '1', '系统管理', '0', '', 0, '1', '0', 1, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '2', '组织管理', '1', '', 0, '1', '0', 2, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '3', '权限管理', '1', '', 0, '1', '0', 3, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '5', '部门管理', '2', '/jsp/organization/department/department-main.jsp', 0, '0', '0', 5, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '6', '岗位管理', '2', '/jsp/organization/job/job-main.jsp', 0, '0', '0', 6, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '7', '人员管理', '2', '/jsp/organization/employee/employee-main.jsp', 0, '0', '0', 7, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '8', '用户管理', '3', '/privilege/userAction.do?action=list', 0, '0', '0', 8, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '9', '角色管理', '3', '/privilege/roleAction.do?action=list', 0, '0', '0', 9, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '10', '操作管理', '3', '/jsp/privilege/opt/opt-main.jsp', 0, '0', '0', 10, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '11', '资源管理', '3', '/jsp/privilege/resource/res-main.jsp', 0, '0', '0', 11, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '12', '菜单定制', '1', '/jsp/menusetup/frame.jsp', 0, '0', '0', 12, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '13', '操作日志', '1', '/log/loglist.do?action=list', 0, '0', '0', 13, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '14', '操作映射', '3', '/privilege/permissionMapAction.do?action=list', 0, '0', '0', 14, '1', '1');
#----- 12.11 huyoxo edit -----------------------------------------------------------------------------------------------
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '4', '工作流管理', '1', '', 0, '1', '0', 4, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '15', '流程包管理', '4', '/workflow/packageMgr.do?action=list', 0, '0', '0', 15, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '16', '流程管理', '4', '/workflow/processMgr.do?action=list', 0, '0', '0', 16, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '17', '待办事项', '4', '/workflow/WorkItemAction.do?method=list', 0, '0', '0', 17, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '18', '我的流程', '4', '/workflow/processMgr.do?action=noAdminList', 0, '0', '0', 18, '1', '1');
INSERT INTO T_USER_MENU(EMPID, NODEID, NODENAME, PARENTID, LINK, LINKMODE, ISFOLDER, ISHIDED, ORDERID, ISMENU, FLAG) 
VALUES ('1', '19', '行政级别', '2', '/privilege/levelAction.do?action=list', 0, '0', '0', 19, '1', '1');
#----- 12.11 huyoxo edit -----------------------------------------------------------------------------------------------

#--用户管理（通用查询）

INSERT INTO UNIQUERY_DATA (COLNAME, COLALIAS, TBNAME, TBREMARK, COLTYPE, LENGTH, SCALE, CASTTYPE, COLNO, IN_HIDDEN, OUT_HIDDEN, IS_PK, IS_FK, IGNORE_FK, REF_TBNAME, REF_KEYCOL, REF_VALCOL) 
VALUES ('ID', '用户ID', 'T_USER', '用户表ID', 'INTEGER', 4, 0, null, 0, 0, 0, 1, 0, 0, null, null, null);

INSERT INTO UNIQUERY_DATA (COLNAME, COLALIAS, TBNAME, TBREMARK, COLTYPE, LENGTH, SCALE, CASTTYPE, COLNO, IN_HIDDEN, OUT_HIDDEN, IS_PK, IS_FK, IGNORE_FK, REF_TBNAME, REF_KEYCOL, REF_VALCOL) 
VALUES ('USERNAME', '帐户', 'T_USER', '帐户', 'VARCHAR', 30, 0, null, 1, 0, 0, 0, 0, 0, null, null, null);

INSERT INTO UNIQUERY_DATA (COLNAME, COLALIAS, TBNAME, TBREMARK, COLTYPE, LENGTH, SCALE, CASTTYPE, COLNO, IN_HIDDEN, OUT_HIDDEN, IS_PK, IS_FK, IGNORE_FK, REF_TBNAME, REF_KEYCOL, REF_VALCOL) 
VALUES ('PASSWORD', '密码', 'T_USER', '密码', 'VARCHAR', 50, 0, null, 2, 0, 0, 0, 0, 0, null, null, null);

INSERT INTO UNIQUERY_DATA (COLNAME, COLALIAS, TBNAME, TBREMARK, COLTYPE, LENGTH, SCALE, CASTTYPE, COLNO, IN_HIDDEN, OUT_HIDDEN, IS_PK, IS_FK, IGNORE_FK, REF_TBNAME, REF_KEYCOL, REF_VALCOL) 
VALUES ('USERTYPE', '用户类型', 'T_USER', '用户类型', 'VARCHAR', 30, 0, null, 3, 0, 0, 0, 1, 0, 'T_USER_USERTYPE', 'ID', 'VALUE');

INSERT INTO UNIQUERY_DATA (COLNAME, COLALIAS, TBNAME, TBREMARK, COLTYPE, LENGTH, SCALE, CASTTYPE, COLNO, IN_HIDDEN, OUT_HIDDEN, IS_PK, IS_FK, IGNORE_FK, REF_TBNAME, REF_KEYCOL, REF_VALCOL) 
VALUES ('LEVEL', '用户级别', 'T_USER', '用户级别', 'VARCHAR', 10, 0, null, 4, 0, 0, 0, 0, 0, null, null, null);

INSERT INTO UNIQUERY_DATA (COLNAME, COLALIAS, TBNAME, TBREMARK, COLTYPE, LENGTH, SCALE, CASTTYPE, COLNO, IN_HIDDEN, OUT_HIDDEN, IS_PK, IS_FK, IGNORE_FK, REF_TBNAME, REF_KEYCOL, REF_VALCOL) 
VALUES ('STATUS', '是否激活', 'T_USER', '激活状态', 'VARCHAR', 20, 0, null, 5, 0, 0, 0, 1, 0, 'T_USER_STATUS', 'ID', 'VALUE');

INSERT INTO UNIQUERY_DATA (COLNAME, COLALIAS, TBNAME, TBREMARK, COLTYPE, LENGTH, SCALE, CASTTYPE, COLNO, IN_HIDDEN, OUT_HIDDEN, IS_PK, IS_FK, IGNORE_FK, REF_TBNAME, REF_KEYCOL, REF_VALCOL) 
VALUES ('VALIDDATE', '有效期', 'T_USER', '有效期', 'VARCHAR', 20, 0, null, 6, 0, 0, 0, 0, 0, null, null, null);

INSERT INTO UNIQUERY_DATA (COLNAME, COLALIAS, TBNAME, TBREMARK, COLTYPE, LENGTH, SCALE, CASTTYPE, COLNO, IN_HIDDEN, OUT_HIDDEN, IS_PK, IS_FK, IGNORE_FK, REF_TBNAME, REF_KEYCOL, REF_VALCOL) 
VALUES ('NAME', '姓名', 'T_USER', '姓名', 'VARCHAR', 30, 0, null, 7, 0, 0, 0, 0, 0, null, null, null);

INSERT INTO UNIQUERY_DATA (COLNAME, COLALIAS, TBNAME, TBREMARK, COLTYPE, LENGTH, SCALE, CASTTYPE, COLNO, IN_HIDDEN, OUT_HIDDEN, IS_PK, IS_FK, IGNORE_FK, REF_TBNAME, REF_KEYCOL, REF_VALCOL) 
VALUES ('CODE', '编码', 'T_USER', '用户编码', 'VARCHAR', 30, 0, null, 8, 0, 0, 0, 0, 0, null, null, null);

INSERT INTO UNIQUERY_DATA (COLNAME, COLALIAS, TBNAME, TBREMARK, COLTYPE, LENGTH, SCALE, CASTTYPE, COLNO, IN_HIDDEN, OUT_HIDDEN, IS_PK, IS_FK, IGNORE_FK, REF_TBNAME, REF_KEYCOL, REF_VALCOL) 
VALUES ('GENDER', '性别', 'T_USER', '性别', 'VARCHAR', 6, 0, null, 9, 0, 0, 0, 0, 0, null, null, null);



#-- 用户类型对应表结构及初始化数据
CREATE TABLE T_USER_USERTYPE
 (value  VARCHAR(100),
  ID    VARCHAR(30)
 );

ALTER TABLE T_USER_USERTYPE
  ADD PRIMARY KEY
   (ID
   );

INSERT INTO T_USER_USERTYPE(ID, VALUE) 
VALUES ('2', 'CA用户');

INSERT INTO T_USER_USERTYPE(ID, VALUE) 
VALUES ('1', '普通用户');


#-- 用户是否激活对应表结构及初始化数据
CREATE TABLE T_USER_STATUS
 (value  VARCHAR(100),
  ID    VARCHAR(20)
 );

ALTER TABLE T_USER_STATUS
  ADD PRIMARY KEY
   (ID
   );

INSERT INTO T_USER_STATUS(ID, VALUE) 
VALUES ('0', '未激活');

INSERT INTO T_USER_STATUS(ID, VALUE) 
VALUES ('1', '激活');
   
INSERT INTO T_SYSTEM_ID(IDTYPE,ID) VALUES(0,1);

#--- 工作流用户权限表 ---
CREATE TABLE T_WF_PERMISSION
 (ID                  BIGINT          NOT NULL,
  USER_ID             BIGINT          NOT NULL,
  PROCESS_DEFINED_ID  VARCHAR(200)    NOT NULL,
  PACKAGE_DEFINED_ID  VARCHAR(200)    NOT NULL
 );

#--ALTER TABLE T_WF_PERMISSION
#--  LOCKSIZE ROW
#--  APPEND OFF
#--  NOT VOLATILE;

ALTER TABLE T_WF_PERMISSION
  ADD CONSTRAINT SQL041201151554930 PRIMARY KEY
   (ID
   );
