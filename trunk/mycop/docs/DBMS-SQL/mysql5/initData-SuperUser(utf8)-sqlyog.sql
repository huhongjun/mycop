--
-- Step 1: ����
--
-- 

-- 
--
-- Step 2: ����
--
-- 
--
-- Step 3: ��ʼ������
--
-- ---------------------

-- ��ʼ��ID����������Ҫ����ID��Ψһ��ֵʱͨ��SQL�����Ӵ˱��ã�IDtype�����м�������
-- Ŀǰ�����⣺��֪�������е������˼���IDtype
delete from geverid;
INSERT INTO geverid(idtype, id)VALUES(0, 1000);
INSERT INTO geverid(idtype, id)VALUES(1, 1000);
INSERT INTO geverid(idtype, id)VALUES(2, 1000);

-- ��ʼ���������� --
delete from t_level;
INSERT INTO T_LEVEL(ID, CODE, NAME, DESCRIPTION) VALUES(1, 'GDP-NO-LEVEL', 'not define', 'default');

-- ��ʼ���û�������ϵͳ����Ա
delete from t_user;
INSERT INTO t_user( id, username, PASSWORD, usertype, level, status, validdate, name,code, gender)
            VALUES( 1, 'admin', 'D4-1D-8C-D9-8F-00-B2-04-E9-80-09-98-EC-F8-42-7E',
                    '1', '1', '1', '2010-12-12', 'super user', '0000', '1');
                    
-- ��ʼ�����ű�����һ����ʼ���ţ���ϵͳ����Ա�û������������
delete from t_department;
INSERT INTO T_DEPARTMENT(id,code,name,description,departmenttype,parent_id) VALUES(1020,'0101','Admin department','administration','',0);
delete from t_department_person;
insert into t_department_person(department_id,id) values(1020,1);

-- ��ʼ����ɫ�����ӳ�������Ա��ɫ
delete from t_role;
INSERT INTO t_role(id, name, description, roletype) VALUES(1, 'super user', 'super user','');

-- ��ʼ���û���ɫ��ʹϵͳ����Ա�û�Ϊ��������Ա��ɫ
delete from t_user_role;
INSERT INTO t_user_role(user_id, role_id) VALUES(1, 1);

-- ��ʼ����Դ��

-- ��ʼ����Դ������

-- ��ʼ����ɫȨ�ޱ�,����������Ա��ɫ��Ȩ��
delete from t_role_permission where role_id=1;
insert into t_role_permission(role_id, resource_id, resoperation_id) select 1,resource_id,id from t_resoperation;

-- ��ʼ���˵����Ʊ�


-- �û�����ͨ�ò�ѯ��
-- �û����Ͷ�Ӧ��ṹ����ʼ������

delete from t_user_usertype;
INSERT INTO t_user_usertype(id, VALUE) VALUES('2', 'CA user');
INSERT INTO t_user_usertype(id, VALUE) VALUES('1', 'Normal user');

-- �û��Ƿ񼤻��Ӧ��ṹ����ʼ������
delete from t_user_status;
INSERT INTO t_user_status(id, VALUE) 	VALUES('0', 'deactive');
INSERT INTO t_user_status(id, VALUE) 		VALUES('1', 'active');
 
-- 
delete from t_system_id;
INSERT INTO t_system_id(idtype, id)VALUES(0, 1);