/************************/

--
-- Step 1: 建库
--
-- 

-- 
--
-- Step 2: 建表
--
-- 
--
-- Step 3: 初始化数据
--
-- ---------------------

-- 初始化ID表，其他表需要自增ID或唯一键值时通过SQL操作从此表获得，IDtype代表有几个序列
-- 目前的问题：不知道程序中到底用了几个IDtype
delete from geverid;
INSERT INTO geverid(idtype, id)VALUES(0, 1000);
INSERT INTO geverid(idtype, id)VALUES(1, 1000);
INSERT INTO geverid(idtype, id)VALUES(2, 1000);

-- 初始化行政级别 --
delete from t_level;
INSERT INTO T_LEVEL(ID, CODE, NAME, DESCRIPTION) VALUES(1, 'GDP-NO-LEVEL', '不选择级别', '用户默认级别');
select * from t_level;

-- 初始化用户表，增加系统管理员
delete from t_user;
INSERT INTO t_user( id, username, PASSWORD, usertype, level, status, validdate, name,code, gender)
            VALUES( 1, 'admin', 'D4-1D-8C-D9-8F-00-B2-04-E9-80-09-98-EC-F8-42-7E',
                    '1', '1', '1', '2010-12-12', '系统管理员', '0000', '1');
                    
-- 初始化部门表，增加一个初始部门，让系统管理员用户属于这个部门
delete from t_department;
INSERT INTO T_DEPARTMENT(id,code,name,description,departmenttype,parent_id) VALUES(1020,'0101','行政部','负责行政管理','',0);
delete from t_department_person;
insert into t_department_person(department_id,id) values(1020,1);

-- 初始化角色表，增加超级管理员角色
delete from t_role;
INSERT INTO t_role(id, name, description, roletype) VALUES(1, '超级管理员', '超级管理员','');

-- 初始化用户角色表，使系统管理员用户为超级管理员角色
delete from t_user_role;
INSERT INTO t_user_role(user_id, role_id)VALUES(1, 1);

-- 初始化资源表

-- 初始化资源操作表

-- 初始化角色权限表,给超级管理员角色赋权限
delete from t_role_permission where role_id=1;
insert into t_role_permission(role_id, resource_id, resoperation_id) select 1,resource_id,id from t_resoperation;

-- 初始化菜单定制表


-- 用户管理（通用查询）
-- 用户类型对应表结构及初始化数据

delete from t_user_usertype;
INSERT INTO t_user_usertype(id, VALUE) VALUES('2', 'CA用户');
INSERT INTO t_user_usertype(id, VALUE) VALUES('1', '普通用户');

-- 用户是否激活对应表结构及初始化数据
delete from t_user_status;
INSERT INTO t_user_status(id, VALUE) 	VALUES('0', '未激活');
INSERT INTO t_user_status(id, VALUE) 		VALUES('1', '激活');
 
-- 
delete from t_system_id;
INSERT INTO t_system_id(idtype, id)VALUES(0, 1);