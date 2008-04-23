/* 5     - mysqlCrebase.sql   start       建表                                   */
/* 1277 -  initData-Setup.sql start       舒适化数据：功能、操作等     */
/* 1717 -  initData-SuperUser.sql start 超级用户设置                       */

/*==============================================================*/
/* Database name:  COP                                          */
/* DBMS name:      MySQL 3.23                                   */
/* Created on:     7/25/2006 9:07:42 PM                         */
/*==============================================================*/


drop table if exists DESKTOPSETUP;

drop table if exists DO_ARRANGER;

drop table if exists DO_BBS_SBOARD;

drop table if exists DO_BBS_TOPBOARD;

drop table if exists DO_BBS_TOPIC;

drop table if exists DO_BBS_TOPICLIST;

drop table if exists DO_BBS_USER;

drop table if exists DO_BD_AWOKE;

drop table if exists DO_CALENDAR_ARRANGE;

drop table if exists DO_CARDCASE;

drop table if exists DO_CARDCASE_TYPE;

drop table if exists DO_FIVEYEAR_LAYOUT;

drop table if exists DO_IMPOWER;

drop table if exists DO_INFO_TYPE;

drop table if exists DO_INNER_MSG;

drop table if exists DO_LOG_CONTENT;

drop table if exists DO_NETMEET;

drop table if exists DO_NETMEET_CONTENT;

drop table if exists DO_PERSONAL_SETUP;

drop table if exists DO_PERSONAL_TARGET;

drop table if exists DO_TARGET_MANAGE;

drop table if exists DO_TEAM_LOG;

drop table if exists DO_TICKLER;

drop table if exists DO_VIEW_RIGHT;

drop table if exists DO_WORK_LOG;

drop table if exists DO_WORK_LOG_ADVICE;

drop table if exists DO_WORK_REPORT;

drop table if exists DO_WORLD_WINDOW;

drop table if exists GEVERID;

drop table if exists IS_ADDRESS_LIST;

drop table if exists IS_CUSTOMER;

drop table if exists IS_CUSTOMER_STATUS;

drop table if exists IS_CUSTOMER_TOUCH;

drop table if exists IS_CUSTOMER_TYPE;

drop table if exists IS_DOTYPE;

drop table if exists IS_INFO_MANAGE;

drop table if exists IS_INFO_SERVE;

drop table if exists IS_STANDARD_MODEL;

drop table if exists LOGOSETUP;

drop table if exists Mail;

drop table if exists MailAttch;

drop table if exists MailCapacity;

drop table if exists MailDirectory;

drop table if exists MailInfo;

drop table if exists POP3MAILSETUP;

drop table if exists SYS_DUTY;

drop table if exists SYS_JOB;

drop table if exists SYS_KNOWLEDGE;

drop table if exists SYS_MARRIAGE;

drop table if exists SYS_NATIONAL;

drop table if exists SYS_PARAMETER;

drop table if exists SYS_POLITY;

drop table if exists SYS_SEX;

drop table if exists SYS_UNIT_INFO;

drop table if exists T_DEPARTMENT;

drop table if exists T_DEPARTMENT_PERSON;

drop table if exists T_JOB;

drop table if exists T_JOB_PERSON;

drop table if exists T_LEVEL;

drop table if exists T_RESOPERATION;

drop table if exists T_RESOURCE;

drop table if exists T_ROLE;

drop table if exists T_ROLE_PERMISSION;

drop table if exists T_SYSID;

drop table if exists T_SYSTEM_ID;

drop table if exists T_SYSTEM_LOG;

drop table if exists T_USER;

drop table if exists T_USER_CSS;

drop table if exists T_USER_MENU;

drop table if exists T_USER_PERMISSION;

drop table if exists T_USER_ROLE;

drop table if exists T_USER_STATUS;

drop table if exists T_USER_USERTYPE;

/*==============================================================*/
/* Table: DESKTOPSETUP                                          */
/*==============================================================*/
create table DESKTOPSETUP
(
   USERID                         varchar(30)                    not null,
   DESKPICTYPE                    int,
   DESKTOPPATH                    varchar(255),
   CSSTYPE                        int,
   SHOWTYPE                       int,
   PICSHOWTYPE                    int,
   ISDEFAULT                      int,
   MENUSTYLE                      int,
   primary key (USERID)
);

/*==============================================================*/
/* Table: DO_ARRANGER                                           */
/*==============================================================*/
create table DO_ARRANGER
(
   USER_CODE                      int                            not null,
   ARRANGE                        varchar(255)                   not null,
   primary key (USER_CODE, ARRANGE)
);

/*==============================================================*/
/* Table: DO_BBS_SBOARD                                         */
/*==============================================================*/
create table DO_BBS_SBOARD
(
   SERIAL_NUM                     varchar(30)                    not null,
   TBOARD_SERIAL                  varchar(30),
   SBOARD_NAME                    varchar(100)                   not null,
   SBOARD_MASTER                  varchar(20),
   ICON_FILE                      varchar(255),
   SBOARD_NOTE                    varchar(200),
   SBOARD_STATE                   int,
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_BBS_TOPBOARD                                       */
/*==============================================================*/
create table DO_BBS_TOPBOARD
(
   SERIAL_NUM                     varchar(30)                    not null,
   TBOARD_NAME                    varchar(50)                    not null,
   ICON_FILE                      varchar(255),
   TBOARD_NOTE                    varchar(200),
   TBOARD_STATE                   int,
   MASTER                         varchar(20),
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_BBS_TOPIC                                          */
/*==============================================================*/
create table DO_BBS_TOPIC
(
   SERIAL_NUM                     varchar(30)                    not null,
   SBOARD_SERIAL                  varchar(30),
   TOPIC                          varchar(200),
   BBS_USER_CODE                  varchar(20)                    not null,
   TOPIC_TYPE                     int,
   TOPIC_USER                     varchar(2000),
   APPEAR_DATE                    date,
   TOPIC_ORDER                    int                            not null,
   HIT_TIMES                      int,
   ISBLOCK                        int                            default 0,
   IS_SHOW                        int                            default 0,
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_BBS_TOPICLIST                                      */
/*==============================================================*/
create table DO_BBS_TOPICLIST
(
   SERIAL_NUM                     varchar(30)                    not null,
   TOPIC_NUM                      varchar(30)                    not null,
   BBS_USER_CODE                  varchar(20)                    not null,
   CONTENT                        varchar(3000),
   IP_ADDRESS                     varchar(20),
   FILE_PATH                      varchar(255),
   FILE_NAME                      varchar(255),
   REPLY_DATE                     date,
   AWOKE_FLAG                     int                            not null,
   IS_SHOW                        int                            default 0,
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_BBS_USER                                           */
/*==============================================================*/
create table DO_BBS_USER
(
   BBS_USER_CODE                  varchar(20)                    not null,
   NICKNAME                       varchar(20)                    not null,
   USER_ICON                      varchar(255),
   LAST_LOG_TIME                  date,
   USER_STATE                     int                            not null,
   USER_CODE                      int                            not null,
   SEX_CODE                       varchar(4),
   E_MAIL                         varchar(30),
   HOME_PAGE                      varchar(100),
   primary key (BBS_USER_CODE)
);

/*==============================================================*/
/* Table: DO_BD_AWOKE                                           */
/*==============================================================*/
create table DO_BD_AWOKE
(
   SERIAL_NUM                     varchar(30)                    not null,
   ADVANCE_DAYS                   int,
   ADVANCE_MEMS                   varchar(800),
   FLAG                           int,
   MEMO                           varchar(100)
);

/*==============================================================*/
/* Table: DO_CALENDAR_ARRANGE                                   */
/*==============================================================*/
create table DO_CALENDAR_ARRANGE
(
   SERIAL_NUM                     varchar(30)                    not null,
   CALENDAR                       date,
   USER_CODE                      int,
   BEGIN_TIME                     date,
   END_TIME                       date,
   TASK_TYPE                      int,
   TASK_SUM                       varchar(60),
   TASK_CONTENT                   varchar(200),
   REMIND_FLAG                    int,
   AWOKE_TIME                     int,
   ARRANGE                        varchar(20),
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_CARDCASE                                           */
/*==============================================================*/
create table DO_CARDCASE
(
   SERIAL_NUM                     varchar(30)                    not null,
   TYPE_CODE                      varchar(30),
   CUSTOMER                       varchar(20),
   NICKNAME                       varchar(20),
   DUTY                           varchar(20),
   COMPANY                        varchar(100),
   ADDRESS                        varchar(60),
   POST_CODE                      varchar(20),
   E_MAIL                         varchar(30),
   PHONE                          varchar(30),
   FAX                            varchar(30),
   MOBILE                         varchar(15),
   HOME_PAGE                      varchar(100),
   REMARK                         varchar(200),
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_CARDCASE_TYPE                                      */
/*==============================================================*/
create table DO_CARDCASE_TYPE
(
   TYPE_CODE                      varchar(30)                    not null,
   USER_CODE                      int,
   TYPE_NAME                      varchar(20),
   REMARK                         varchar(60),
   primary key (TYPE_CODE)
);

/*==============================================================*/
/* Table: DO_FIVEYEAR_LAYOUT                                    */
/*==============================================================*/
create table DO_FIVEYEAR_LAYOUT
(
   SERIAL_NUM                     varchar(30)                    not null,
   CURRENT_YEAR                   CHAR(4),
   USER_CODE                      int,
   CREATE_DATE                    date,
   APPROVE                        varchar(20),
   APPROVE_DATE                   date,
   CONTENT                        blob,
   APPROVE_FLAG                   int                            default 0,
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_IMPOWER                                            */
/*==============================================================*/
create table DO_IMPOWER
(
   SERIAL_NUM                     varchar(30)                    not null,
   CREATE_TIME                    date,
   ACCEPTER                       varchar(20),
   PAYER                          varchar(20),
   BEGIN_TIME                     date,
   END_TIME                       date,
   COMMENTS                       varchar(60),
   NOTICE                         varchar(2000),
   CONTENT                        blob,
   SEND_FLAG                      int                            default 0,
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_INFO_TYPE                                          */
/*==============================================================*/
create table DO_INFO_TYPE
(
   INFO_TYPE                      varchar(30)                    not null,
   TYPE_NAME                      varchar(20)                    not null,
   primary key (INFO_TYPE)
);

/*==============================================================*/
/* Table: DO_INNER_MSG                                          */
/*==============================================================*/
create table DO_INNER_MSG
(
   SERIAL_NUM                     varchar(30)                    not null,
   USER_CODE                      varchar(30)                    not null,
   SEND_TIME                      date,
   CONTENT                        varchar(255),
   RECEIVER                       varchar(20),
   READ_FLAG                      int,
   READ_TIME                      date,
   WEB_URL                        varchar(255),
   MSG_TYPE                       varchar(1),
   MEMO                           varchar(255),
   MODEL_ID                       int,
   MSERIAL_NUM                    varchar(30),
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_LOG_CONTENT                                        */
/*==============================================================*/
create table DO_LOG_CONTENT
(
   SERIAL_NUM                     varchar(30)                    not null,
   TSERIAL_NUM                    varchar(30)                    not null,
   LOG_CONTENT                    varchar(255),
   REMARK                         varchar(60),
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_NETMEET                                            */
/*==============================================================*/
create table DO_NETMEET
(
   SERIAL_NUM                     varchar(30)                    not null,
   TITLE                          varchar(200),
   CALLER                         varchar(20),
   MASTER                         varchar(20),
   BEGIN_TIME                     date,
   END_TIME                       date,
   ATTENDS                        varchar(1000),
   ON_LINES                       varchar(1000),
   FINISH_FLAG                    int,
   RECORDER                       varchar(20),
   SUMMARY                        text,
   FILE_PATH                      varchar(255),
   FILE_NAME                      varchar(255),
   AWOKE_FLAG                     int                            not null,
   BUILDING                       int,
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_NETMEET_CONTENT                                    */
/*==============================================================*/
create table DO_NETMEET_CONTENT
(
   SERIAL_NUM                     varchar(30)                    not null,
   ORDER_ID                       int                            not null,
   USER_NAME                      varchar(20),
   CONTENT                        varchar(3000),
   USER_ICON                      varchar(255),
   FILE_PATH                      varchar(255),
   FILE_NAME                      varchar(255),
   primary key (SERIAL_NUM, ORDER_ID)
);

/*==============================================================*/
/* Table: DO_PERSONAL_SETUP                                     */
/*==============================================================*/
create table DO_PERSONAL_SETUP
(
   USER_CODE                      int                            not null,
   TEAM_MEMBER                    varchar(1000),
   primary key (USER_CODE)
);

/*==============================================================*/
/* Table: DO_PERSONAL_TARGET                                    */
/*==============================================================*/
create table DO_PERSONAL_TARGET
(
   SERIAL_NUM                     varchar(30)                    not null,
   USER_CODE                      int,
   CURRENT_YEAR                   CHAR(4),
   CURRENT_MONTH                  varchar(2),
   CURRENT_WORK                   CHAR(1),
   TARGET_TYPE                    int,
   DEPT_CODE                      varchar(30),
   POST_CODE                      varchar(30),
   CREATE_DATE                    date,
   TARGET_CONTENT                 blob,
   AUDITOR                        varchar(20),
   AUDIT_DATE                     date,
   AUDIT_FLAG                     int                            default 0,
   AUDIT_OPINION                  varchar(200),
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_TARGET_MANAGE                                      */
/*==============================================================*/
create table DO_TARGET_MANAGE
(
   SERIAL_NUM                     varchar(30)                    not null,
   CURRENT_YEAR                   CHAR(4),
   CURRENT_MONTH                  varchar(2),
   CURRENT_WORK                   CHAR(1),
   TARGET_TYPE                    CHAR(1),
   DEPT_CODE                      varchar(30),
   USER_CODE                      int,
   CREATE_DATE                    date,
   AUDITOR                        varchar(20),
   AUDIT_DATE                     date,
   AUDIT_FLAG                     int                            default 0,
   AUDIT_OPINION                  varchar(200),
   CHECK_FLAG                     int                            default 0,
   CHECKER                        varchar(20),
   CHECK_DATE                     date,
   CHECK_OPINION                  varchar(200),
   TARGET_CONTENT                 blob,
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_TEAM_LOG                                           */
/*==============================================================*/
create table DO_TEAM_LOG
(
   SERIAL_NUM                     varchar(30)                    not null,
   USER_CODE                      int,
   TEAM_TYPE                      int,
   TEAM_MEMBER                    varchar(1000),
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_TICKLER                                            */
/*==============================================================*/
create table DO_TICKLER
(
   SERIAL_NUM                     varchar(30)                    not null,
   USER_CODE                      int                            not null,
   CONTENT                        varchar(300),
   CREATE_DATE                    date,
   REMIND_FLAG                    CHAR(1),
   AWOKE_TIME                     date,
   FINISH_FLAG                    int,
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_VIEW_RIGHT                                         */
/*==============================================================*/
create table DO_VIEW_RIGHT
(
   USER_CODE                      int                            not null,
   MEMBER                         varchar(255)                   not null,
   primary key (USER_CODE, MEMBER)
);

/*==============================================================*/
/* Table: DO_WORK_LOG                                           */
/*==============================================================*/
create table DO_WORK_LOG
(
   SERIAL_NUM                     varchar(30)                    not null,
   CREATE_TIME                    date,
   DEPT_CODE                      varchar(30),
   USER_CODE                      int,
   FILL_DATE                      date,
   LOG_CONTENT                    blob,
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_WORK_LOG_ADVICE                                    */
/*==============================================================*/
create table DO_WORK_LOG_ADVICE
(
   serial_num                     VARCHAR(30)                    not null,
   op_date                        VARCHAR(20)                    not null,
   adviser                        VARCHAR(20)                    not null,
   advice                         VARCHAR(1000),
   remark                         VARCHAR(100)
);

/*==============================================================*/
/* Table: DO_WORK_REPORT                                        */
/*==============================================================*/
create table DO_WORK_REPORT
(
   SERIAL_NUM                     varchar(30)                    not null,
   CREATE_DATE                    date,
   USER_CODE                      int,
   BEGIN_TIME                     date,
   END_TIME                       date,
   RENDER                         varchar(20),
   CHECKER                        varchar(20),
   CHECK_DATE                     date,
   CONTENT                        blob,
   SEND_FLAG                      int                            default 0,
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_WORLD_WINDOW                                       */
/*==============================================================*/
create table DO_WORLD_WINDOW
(
   TITLE                          varchar(100)                   not null,
   INFO_TYPE                      varchar(30)                    not null,
   CHILD_TYPE                     varchar(20),
   URL                            varchar(100),
   primary key (TITLE, INFO_TYPE)
);

/*==============================================================*/
/* Table: GEVERID                                               */
/*==============================================================*/
create table GEVERID
(
   IDTYPE                         int                            not null,
   ID                             int                            not null,
   primary key (IDTYPE)
);

/*==============================================================*/
/* Table: IS_ADDRESS_LIST                                       */
/*==============================================================*/
create table IS_ADDRESS_LIST
(
   SERIAL_NUM                     varchar(30)                    not null,
   TYPE_FLAG                      int,
   GROUP_NAME                     varchar(20),
   USER_CODE                      int,
   MEMBER                         text,
   GROUP_CODE                     varchar(20),
   TABLE_NAME                     varchar(20),
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: IS_CUSTOMER                                           */
/*==============================================================*/
create table IS_CUSTOMER
(
   CUSTOMER_CODE                  varchar(20)                    not null,
   CUSTOMER_NAME                  varchar(60),
   PHONE                          varchar(30),
   FAX                            varchar(30),
   E_MAIL                         varchar(30),
   HOME_PAGE                      varchar(100),
   ADDRESS                        varchar(60),
   POST_CODE                      varchar(10),
   SECRET_LEVEL                   int,
   REMARK                         varchar(600),
   OPEN_BANK                      varchar(40),
   ACCOUNT                        varchar(30),
   FLAG                           int,
   TYPE_CODE                      int,
   MEMORY                         date,
   primary key (CUSTOMER_CODE)
);

/*==============================================================*/
/* Table: IS_CUSTOMER_STATUS                                    */
/*==============================================================*/
create table IS_CUSTOMER_STATUS
(
   FLAG                           int                            not null AUTO_INCREMENT,
   `STATUS`                       varchar(20),
   REMARK                         varchar(60),
   primary key (FLAG)
);

/*==============================================================*/
/* Table: IS_CUSTOMER_TOUCH                                     */
/*==============================================================*/
create table IS_CUSTOMER_TOUCH
(
   CUSTOMER                       varchar(20)                    not null,
   CUSTOMER_CODE                  varchar(20)                    not null,
   SEX_CODE                       varchar(4),
   JOB                            varchar(20),
   LOVE                           varchar(30),
   WORK_PHONE                     varchar(30),
   HOME_PHONE                     varchar(30),
   MOBILE                         varchar(15),
   E_MAIL                         varchar(30),
   ADDRESS                        varchar(60),
   REMARK                         varchar(200),
   BIRTHDAY                       date,
   primary key (CUSTOMER, CUSTOMER_CODE)
);

/*==============================================================*/
/* Table: IS_CUSTOMER_TYPE                                      */
/*==============================================================*/
create table IS_CUSTOMER_TYPE
(
   TYPE_CODE                      int                            not null,
   TYPE_NAME                      varchar(20),
   REMARK                         varchar(60),
   primary key (TYPE_CODE)
);

/*==============================================================*/
/* Table: IS_DOTYPE                                             */
/*==============================================================*/
create table IS_DOTYPE
(
   INFO_TYPE                      varchar(20)                    not null,
   TYPELEVEL                      int,
   PARENT_TYPE                    varchar(20),
   MODULEFLAG                     int,
   REMARK                         varchar(30),
   primary key (INFO_TYPE)
);

/*==============================================================*/
/* Table: IS_INFO_MANAGE                                        */
/*==============================================================*/
create table IS_INFO_MANAGE
(
   TITLE                          varchar(100)                   not null,
   INFO_TYPE                      varchar(20)                    not null,
   USER_CODE                      int                            not null,
   CREATE_DATE                    date,
   FILE_PATH                      varchar(255),
   FILE_NAME                      varchar(255),
   URL                            varchar(100),
   CONTENT                        text,
   INFO_LEVLE                     int,
   INFO_FLAG                      int,
   primary key (TITLE, INFO_TYPE)
);

/*==============================================================*/
/* Table: IS_INFO_SERVE                                         */
/*==============================================================*/
create table IS_INFO_SERVE
(
   SERIAL_NUM                     varchar(30)                    not null,
   TITLE                          varchar(100),
   INFO_TYPE                      varchar(20),
   DEPT                           varchar(2000),
   USER_CODE                      int,
   CREATE_DATE                    date,
   FILE_PATH                      varchar(255),
   FILE_NAME                      varchar(255),
   WORD_CONTENT                   text,
   CONTENT                        blob,
   HINT_FLAG                      int                            default 1,
   SEND_FLAG                      int                            default 1,
   EDITOR_TYPE                    int,
   INFO_FLAG                      int,
   SHOW_FLAG                      int,
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: IS_STANDARD_MODEL                                     */
/*==============================================================*/
create table IS_STANDARD_MODEL
(
   MODEL_CODE                     varchar(30)                    not null,
   MODEL_TYPE                     int,
   CREATE_DATE                    date,
   ISSUE_FLAG                     int,
   MODEL_NAME                     varchar(20),
   USER_CODE                      int,
   FILE_PATH                      varchar(255),
   FILE_NAME                      varchar(255),
   WORD_CONTENT                   text,
   CONTENT                        blob,
   EDITOR_TYPE                    int,
   INFO_TYPE                      varchar(20),
   primary key (MODEL_CODE)
);

/*==============================================================*/
/* Table: LOGOSETUP                                             */
/*==============================================================*/
create table LOGOSETUP
(
   LOGOPATH                       varchar(255),
   LOGOINFO                       varchar(255),
   LOGOTYPE                       int,
   BANNERPATH                     varchar(255),
   BANNERINFO                     varchar(255),
   BANNERTYPE                     int,
   MEMO                           varchar(500)
);

/*==============================================================*/
/* Table: Mail                                                  */
/*==============================================================*/
create table Mail
(
   USER_CODE                      int,
   SERIAL_NUM                     varchar(30)                    not null,
   POST_USERNAME                  varchar(100),
   POST_ADDRESS                   varchar(50),
   RECEIVE_ADDRESS                varchar(800),
   COPY_SEND                      varchar(800),
   DENSE_SEND                     varchar(800),
   SEND_DATE                      varchar(19),
   TITLE                          varchar(500),
   MAIL_DIR_ID                    varchar(30)                    not null,
   RE_FLAG                        varchar(1),
   READ_FLAG                      varchar(1),
   CONTENT                        text,
   MAIL_TYPE                      varchar(1),
   PRIORITY                       varchar(1),
   MAIL_SIZE                      double,
   OLD_MAILDIR_ID                 varchar(30),
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: MailAttch                                             */
/*==============================================================*/
create table MailAttch
(
   SERIAL_NUM                     varchar(30)                    not null,
   MAIL_ID                        varchar(30)                    not null,
   ATTCH_NAME                     varchar(500),
   FILE_PATH                      varchar(500),
   MEMO                           varchar(1),
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: MailCapacity                                          */
/*==============================================================*/
create table MailCapacity
(
   USER_CODE                      int                            not null,
   MAIL_CAPACITY                  double                         not null,
   CAPACITY_FLAG                  varchar(1)                     not null,
   LIMIT_FLAG                     varchar(1)                     not null,
   MEMO                           varchar(100),
   primary key (USER_CODE)
);

/*==============================================================*/
/* Table: MailDirectory                                         */
/*==============================================================*/
create table MailDirectory
(
   SERIAL_NUM                     varchar(30)                    not null,
   USER_CODE                      varchar(6)                     not null,
   MAIL_DIR_NAME                  varchar(30)                    not null,
   FLAG                           varchar(1)                     not null,
   MEMO                           varchar(30),
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: MailInfo                                              */
/*==============================================================*/
create table MailInfo
(
   PAGENUMBER                     int,
   USER_CODE                      int,
   LABLE_NAME                     text,
   POST_FLAG                      CHAR(1),
   SIGN_FLAG                      CHAR(1),
   RETURN_FLAG                    CHAR(1),
   SHOW_FLAG                      CHAR(1),
   MAIL_LEVEL                     CHAR(1)
);

/*==============================================================*/
/* Table: POP3MAILSETUP                                         */
/*==============================================================*/
create table POP3MAILSETUP
(
   SERIAL_NUM                     varchar(30)                    not null,
   USER_CODE                      int                            not null,
   POP3_NAME                      varchar(60)                    not null,
   POP3_ADDRESS                   varchar(60),
   POP3_ACCOUNT                   varchar(60),
   POP3_PWD                       varchar(255),
   AUTO_FLAG                      CHAR(1),
   DEL_FLAG                       CHAR(1),
   INCEPT_MAIL_DIR                varchar(60),
   SHOW_NAME                      varchar(20),
   SHOW_ADDRESS                   varchar(60),
   SMTP_SERVER                    varchar(60),
   SMTP_AUTH                      CHAR(1),
   SMTP_NAME                      varchar(60),
   SMTP_PWD                       varchar(255),
   USE_FLAG                       CHAR(1),
   primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: SYS_DUTY                                              */
/*==============================================================*/
create table SYS_DUTY
(
   DUTY_CODE                      varchar(4)                     not null,
   DUTY_NAME                      varchar(20),
   REMARKS                        varchar(60),
   primary key (DUTY_CODE)
);

/*==============================================================*/
/* Table: SYS_JOB                                               */
/*==============================================================*/
create table SYS_JOB
(
   JOB_CODE                       varchar(4)                     not null,
   JOB_NAME                       varchar(20),
   REMARKS                        varchar(60),
   primary key (JOB_CODE)
);

/*==============================================================*/
/* Table: SYS_KNOWLEDGE                                         */
/*==============================================================*/
create table SYS_KNOWLEDGE
(
   KNOWLEDGE_CODE                 varchar(4)                     not null,
   KNOWLEDGE_NAME                 varchar(20),
   REMARKS                        varchar(60),
   primary key (KNOWLEDGE_CODE)
);

/*==============================================================*/
/* Table: SYS_MARRIAGE                                          */
/*==============================================================*/
create table SYS_MARRIAGE
(
   MARRIAGE_CODE                  varchar(4)                     not null,
   MARRIAGE                       varchar(20),
   REMARKS                        varchar(60),
   primary key (MARRIAGE_CODE)
);

/*==============================================================*/
/* Table: SYS_NATIONAL                                          */
/*==============================================================*/
create table SYS_NATIONAL
(
   NATIONAL_CODE                  varchar(4)                     not null,
   NATIONAL_NAME                  varchar(20),
   REMARKS                        varchar(60),
   primary key (NATIONAL_CODE)
);

/*==============================================================*/
/* Table: SYS_PARAMETER                                         */
/*==============================================================*/
create table SYS_PARAMETER
(
   WORK_LOG_NUM                   int                            default 5
);

/*==============================================================*/
/* Table: SYS_POLITY                                            */
/*==============================================================*/
create table SYS_POLITY
(
   POLITY_CODE                    varchar(4)                     not null,
   POLITY_NAME                    varchar(20),
   REMARKS                        varchar(60),
   primary key (POLITY_CODE)
);

/*==============================================================*/
/* Table: SYS_SEX                                               */
/*==============================================================*/
create table SYS_SEX
(
   SEX_CODE                       varchar(4)                     not null,
   SEX_NAME                       varchar(20),
   REMARKS                        varchar(60),
   primary key (SEX_CODE)
);

/*==============================================================*/
/* Table: SYS_UNIT_INFO                                         */
/*==============================================================*/
create table SYS_UNIT_INFO
(
   UNIT_CODE                      varchar(20)                    not null,
   UNIT_NAME                      varchar(60),
   PHONE                          varchar(30),
   FAX                            varchar(30),
   E_MAIL                         varchar(30),
   HOME_PAGE                      varchar(100),
   REMARK                         text,
   UNIT_TYPE                      int,
   primary key (UNIT_CODE)
);

/*==============================================================*/
/* Table: T_DEPARTMENT                                          */
/*==============================================================*/
create table T_DEPARTMENT
(
   ID                             int                            not null,
   CODE                           varchar(30),
   NAME                           varchar(50),
   DESCRIPTION                    varchar(100),
   DEPARTMENTTYPE                 varchar(30),
   PARENT_ID                      int,
   ORDERID                        int,
   FUNCTIONARY                    VARCHAR(50),
   primary key (ID)
);

/*==============================================================*/
/* Table: T_DEPARTMENT_PERSON                                   */
/*==============================================================*/
create table T_DEPARTMENT_PERSON
(
   DEPARTMENT_ID                  int,
   ID                             int
);

/*==============================================================*/
/* Table: T_JOB                                                 */
/*==============================================================*/
create table T_JOB
(
   ID                             int                            not null,
   NAME                           varchar(50),
   DESCRIPTION                    varchar(100),
   DEPARTMENT_ID                  int,
   primary key (ID)
);

/*==============================================================*/
/* Table: T_JOB_PERSON                                          */
/*==============================================================*/
create table T_JOB_PERSON
(
   JOB_ID                         int,
   ID                             int
);

/*==============================================================*/
/* Table: T_LEVEL                                               */
/*==============================================================*/
create table T_LEVEL
(
   id                             int(11)                        not null,
   code                           varchar(30),
   name                           varchar(30),
   description                    varchar(100)
);

/*==============================================================*/
/* Table: T_RESOPERATION                                        */
/*==============================================================*/
create table T_RESOPERATION
(
   ID                             int                            not null,
   NAME                           varchar(50),
   DESCRIPTION                    varchar(100),
   RESOURCE_ID                    int,
   CODE                           varchar(12),
   ORDERID                        int,
   primary key (ID)
);

/*==============================================================*/
/* Table: T_RESOURCE                                            */
/*==============================================================*/
create table T_RESOURCE
(
   ID                             int                            not null,
   NAME                           varchar(50),
   DESCRIPTION                    varchar(100),
   RESOURCETYPE                   varchar(30),
   LINK                           varchar(200),
   PARENT_ID                      int,
   TARGET                         CHAR(1),
   CODE                           varchar(12),
   ORDERID                        int,
   primary key (ID)
);

/*==============================================================*/
/* Table: T_ROLE                                                */
/*==============================================================*/
create table T_ROLE
(
   ID                             int                            not null,
   NAME                           varchar(50),
   DESCRIPTION                    varchar(100),
   ROLETYPE                       varchar(30),
   primary key (ID)
);

/*==============================================================*/
/* Table: T_ROLE_PERMISSION                                     */
/*==============================================================*/
create table T_ROLE_PERMISSION
(
   ROLE_ID                        int,
   RESOURCE_ID                    int,
   RESOPERATION_ID                int
);

/*==============================================================*/
/* Table: T_SYSID                                               */
/*==============================================================*/
create table T_SYSID
(
   IDTYPE                         int                            not null,
   ID                             int                            not null,
   primary key (IDTYPE)
);

/*==============================================================*/
/* Table: T_SYSTEM_ID                                           */
/*==============================================================*/
create table T_SYSTEM_ID
(
   IDTYPE                         int                            not null,
   ID                             int                            not null,
   primary key (IDTYPE)
);

/*==============================================================*/
/* Table: T_SYSTEM_LOG                                          */
/*==============================================================*/
create table T_SYSTEM_LOG
(
   ID                             int                            not null,
   OTIME                          varchar(20)                    not null,
   USERNAME                       varchar(50),
   MODULE                         varchar(50),
   IPADDRESS                      varchar(50),
   `ACTION`                       varchar(30),
   FILTERLEVEL                    int,
   `TYPE`                         int,
   MEMO                           varchar(500)
);

/*==============================================================*/
/* Table: T_USER                                                */
/*==============================================================*/
create table T_USER
(
   ID                             int                            not null,
   USERNAME                       varchar(30)                    not null,
   `PASSWORD`                     varchar(50),
   USERTYPE                       varchar(30),
   LEVEL                          varchar(10),
   `STATUS`                       varchar(20),
   VALIDDATE                      varchar(20),
   NAME                           varchar(30),
   CODE                           varchar(30),
   GENDER                         varchar(6),
   primary key (ID)
);

/*==============================================================*/
/* Table: T_USER_CSS                                            */
/*==============================================================*/
create table T_USER_CSS
(
   id                             int(11)                        not null,
   user_id                        int(11),
   css_id                         int(11),
   primary key (id)
);

/*==============================================================*/
/* Table: T_USER_MENU                                           */
/*==============================================================*/
create table T_USER_MENU
(
   EMPID                          varchar(10)                    not null,
   NODEID                         varchar(50)                    not null,
   NODENAME                       varchar(100),
   PARENTID                       varchar(50),
   LINK                           varchar(200),
   LINKMODE                       int                            not null default 0,
   ISFOLDER                       varchar(1)                     not null default '0',
   ISHIDED                        varchar(1)                     not null default '0',
   ORDERID                        int                            not null default 0,
   ISMENU                         varchar(1)                     not null default '1',
   FLAG                           varchar(1)                     not null default '0',
   MEMO                           varchar(255),
   primary key (EMPID, NODEID)
);

/*==============================================================*/
/* Table: T_USER_PERMISSION                                     */
/*==============================================================*/
create table T_USER_PERMISSION
(
   USER_ID                        int,
   RESOURCE_ID                    int,
   RESOPERATION_ID                int
);

/*==============================================================*/
/* Table: T_USER_ROLE                                           */
/*==============================================================*/
create table T_USER_ROLE
(
   USER_ID                        int,
   ROLE_ID                        int
);

/*==============================================================*/
/* Table: T_USER_STATUS                                         */
/*==============================================================*/
create table T_USER_STATUS
(
   value                          varchar(100)                   not null,
   id                             varchar(20)                    not null,
   primary key (id)
);

/*==============================================================*/
/* Table: T_USER_USERTYPE                                       */
/*==============================================================*/
create table T_USER_USERTYPE
(
   value                          varchar(100)                   not null,
   id                             varchar(30)                    not null,
   primary key (id)
);


/*
SQLyog - Free MySQL GUI v5.15
Host - 5.0.22-community-nt : Database - cop
*********************************************************************
Server version : 5.0.22-community-nt
*/


SET NAMES utf8;

SET SQL_MODE='';
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Table structure for table `test` */

drop table if exists test;

CREATE TABLE `test` (
  `Username` varchar(20) default NULL,
  `Price` double(15,3) default NULL,
  `Amt` double(15,0) default NULL,
  `Qty` double(15,0) default NULL,
  `Col1` varchar(30) default NULL,
  `Bdate` date default NULL,
  `Etime` time default NULL,
  `id` int(11) NOT NULL auto_increment,
  `userid` varchar(10) default NULL,
  `memo` varchar(30) default NULL,
  `num` double(15,0) default NULL,
  `col4` varchar(30) default NULL,
  `sdatetime` datetime default NULL,
  `ctype` varchar(8) default NULL,
  `col2` varchar(30) default NULL,
  `col3` varchar(30) default NULL,
  `col5` varchar(30) default NULL,
  `col6` varchar(30) default NULL,
  `col7` varchar(30) default NULL,
  `col8` varchar(30) default NULL,
  `col9` varchar(30) default NULL,
  `col10` varchar(30) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

SET SQL_MODE=@OLD_SQL_MODE;


/*
initData-Setup.sql start

SQLyog - Free MySQL GUI v5.15
Host - 5.0.22-community-nt : Database - cop
*********************************************************************
Server version : 5.0.22-community-nt
*/

SET NAMES utf8;

SET SQL_MODE='';
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Data for the table `is_dotype` */

insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('BZMB',1,NULL,7,'标准模板');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('CWZX',1,NULL,6,'财务中心');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('DFFG',1,NULL,1,'地方法规');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('GG',1,NULL,2,'公告');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('GJFG',1,NULL,1,'国家法规');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('GXWZ',1,NULL,7,'共享文章');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('HYXW',1,NULL,5,'行业新闻');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('JKXX',1,NULL,0,'健康信息');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('JXZL',1,NULL,7,'技术资料');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('JXZX',1,NULL,6,'技术中心');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('LYXX',1,NULL,0,'旅游信息');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('NX',2,'PXZL',7,'内训');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('PXZL',1,NULL,7,'培训资料');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('QTPX',2,'PXZL',7,'其他培训');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('QYWH',1,NULL,7,'企业文化');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('RJKF',2,'PXZL',7,'软件开发');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('RJXX',1,NULL,0,'软件信息');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('RLZY',2,'XZZX',6,'人力资源管理');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('TZ',1,NULL,2,'通知');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('XWXX',1,NULL,0,'新闻信息');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('XZGL',2,'XZZX',6,'行政管理');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('XZZX',1,NULL,6,'行政中心');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('YDXX',1,NULL,0,'运动休闲');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('YXFWZX',1,NULL,6,'运行服务中心');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('YXZX',1,NULL,6,'营销中心');
insert into `is_dotype` (`INFO_TYPE`,`TYPELEVEL`,`PARENT_TYPE`,`MODULEFLAG`,`REMARK`) values ('ZCFG',1,NULL,1,'政策法规');

/*Data for the table `is_standard_model` */

insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20041126134848190',3,'2005-04-15',1,'月汇报',1,'/cell/template/monthsum_template.cll','gdca_month_report.cll',NULL,NULL,0,NULL);
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20041126135317133',4,'2005-04-15',1,'年汇报',1,'/cell/template/yearsum_template.cll','gdca_year_report.cll',NULL,NULL,0,NULL);
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20041126135350157',6,'2005-04-15',1,'月度目标',1,'/cell/template/monthtarget_template.cll','monthtarget_template.cll',NULL,NULL,0,NULL);
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20041126135450162',9,'2005-04-15',1,'年度总结',1,'/cell/template/yearsum_template.cll','yearsum_template.cll',NULL,NULL,0,NULL);
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20041126135506143',10,'2005-04-15',1,'五年规划',1,'/cell/template/fiveyearlayout_template.cll','fiveyearlayout_template.cll',NULL,NULL,0,NULL);
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20041126135521158',11,'2005-04-15',1,'工作汇报',1,'/cell/template/workreport.cll','workreport.cll',NULL,NULL,0,NULL);
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20041126135540147',12,'2005-04-15',1,'授权书',1,'/cell/template/impower.cll','impower.cll',NULL,NULL,0,NULL);
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20041128190036104121',7,'2005-04-15',1,'月度总结',1041,'/cell/template/monthsum_template.cll','yearsum_template.cll',NULL,NULL,0,NULL);
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20041128190050104142',8,'2005-04-15',1,'年度目标',1041,'/cell/template/yeartarget_template.cll','yeartarget_template.cll',NULL,NULL,0,NULL);
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20041203162507131',5,'2005-04-15',1,'每周目标',1,'/cell/template/weektarget_template.cll','weektarget_template.cll',NULL,NULL,0,NULL);
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20041209175237157',2,'2005-04-15',1,'周汇报',1,'/cell/template/weeksum_template.cll','workreport.cll',NULL,NULL,0,NULL);
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20050415125633158',1,'2005-04-15',1,'工作日志',1,'/cell/template/worklog_template.cll','worklog_template.cll',NULL,NULL,0,NULL);
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20060720192045138',16,'2006-07-20',0,'news测试',1,'/uploadfiles/template/200607201920451240/1153394445243.cll','news.cll',NULL,NULL,0,'GG');
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20060720193939188',13,'2006-07-20',1,'news',1,'/uploadfiles/template/200607201939391550/1153395579443.cll','news.cll',NULL,NULL,0,'TZ');
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20060721144936121',13,'2006-07-21',0,'放假通知',1,'uploadfiles\\20060721144936185.html','20060721144936185.html',NULL,NULL,1,'GG');
insert into `is_standard_model` (`MODEL_CODE`,`MODEL_TYPE`,`CREATE_DATE`,`ISSUE_FLAG`,`MODEL_NAME`,`USER_CODE`,`FILE_PATH`,`FILE_NAME`,`WORD_CONTENT`,`CONTENT`,`EDITOR_TYPE`,`INFO_TYPE`) values ('20060721150833125',13,'2006-07-21',1,'中奖名单',1,'/uploadfiles/template/200607211508331700/1153465713607.cll','is_GSDT_GG.cll',NULL,NULL,0,'GG');

/*Data for the table `sys_duty` */

insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0002','董事',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0003','总经理',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0004','副总经理',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0005','总经理助理',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0006','行政总监',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0007','主管',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0008','职员',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0009','高级销售代表',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0010','区域销售主管',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0011','行业销售主管',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0012','营销副总监',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0013','业务管理主管',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0014','业务管理专员',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0015','市场拓展主管',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0018','售前工程师',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0019','研发部经理',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0020','研发工程师',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0021','工程服务主管',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0022','运行管理经理',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0023','运行管理专员',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0024','客户服务主管',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0025','客服专员',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0026','主管',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0027','证书操作员',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0028','证书管理员',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0029','运维工程师',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0030','财务主管',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0031','出纳',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0032','司机',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0033','行政主管',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0034','人力资源主管',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0035','前台',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('0036','销售代表',NULL);
insert into `sys_duty` (`DUTY_CODE`,`DUTY_NAME`,`REMARKS`) values ('1','董事长',NULL);

/*Data for the table `sys_job` */

insert into `sys_job` (`JOB_CODE`,`JOB_NAME`,`REMARKS`) values ('GCS','工程师','工程师');

/*Data for the table `sys_knowledge` */

/*Data for the table `sys_marriage` */

insert into `sys_marriage` (`MARRIAGE_CODE`,`MARRIAGE`,`REMARKS`) values ('1','未婚',NULL);
insert into `sys_marriage` (`MARRIAGE_CODE`,`MARRIAGE`,`REMARKS`) values ('2','已婚',NULL);
insert into `sys_marriage` (`MARRIAGE_CODE`,`MARRIAGE`,`REMARKS`) values ('3','丧偶',NULL);
insert into `sys_marriage` (`MARRIAGE_CODE`,`MARRIAGE`,`REMARKS`) values ('4','离婚',NULL);
insert into `sys_marriage` (`MARRIAGE_CODE`,`MARRIAGE`,`REMARKS`) values ('99','其他',NULL);

/*Data for the table `sys_national` */

insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('1','汉族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('10','朝鲜族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('11','满族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('12','侗族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('13','瑶族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('14','白族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('15','土家族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('16','哈尼族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('17','哈萨克族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('18','傣族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('19','黎族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('2','蒙古族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('20','傈僳族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('21','佤族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('22','畲族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('23','高山族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('24','拉祜族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('25','水族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('26','东乡族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('27','纳西族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('28','景颇族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('29','柯尔克孜族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('3','回族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('30','土族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('31','达斡尔族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('32','仫佬族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('33','羌族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('34','布朗族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('35','撒拉族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('36','毛南族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('37','仡佬族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('38','锡伯族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('39','阿昌族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('4','藏族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('40','普米族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('41','塔吉克族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('42','怒族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('43','乌孜别克族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('44','俄罗斯族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('45','鄂温克族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('46','德昂族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('47','保安族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('48','裕固族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('49','京族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('5','维吾尔族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('50','塔塔尔族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('51','独龙族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('52','鄂伦春族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('53','赫哲族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('54','门巴族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('55','珞巴族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('56','基诺族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('6','苗族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('7','彝族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('8','壮族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('9','布依族',NULL);
insert into `sys_national` (`NATIONAL_CODE`,`NATIONAL_NAME`,`REMARKS`) values ('91','待识别',NULL);

/*Data for the table `sys_polity` */

insert into `sys_polity` (`POLITY_CODE`,`POLITY_NAME`,`REMARKS`) values ('02','预备党员',NULL);
insert into `sys_polity` (`POLITY_CODE`,`POLITY_NAME`,`REMARKS`) values ('03','团员',NULL);
insert into `sys_polity` (`POLITY_CODE`,`POLITY_NAME`,`REMARKS`) values ('13','群众',NULL);
insert into `sys_polity` (`POLITY_CODE`,`POLITY_NAME`,`REMARKS`) values ('34','43','43');
insert into `sys_polity` (`POLITY_CODE`,`POLITY_NAME`,`REMARKS`) values ('434','43','34');

/*Data for the table `sys_sex` */

insert into `sys_sex` (`SEX_CODE`,`SEX_NAME`,`REMARKS`) values ('1','男',NULL);
insert into `sys_sex` (`SEX_CODE`,`SEX_NAME`,`REMARKS`) values ('2','女',NULL);
insert into `sys_sex` (`SEX_CODE`,`SEX_NAME`,`REMARKS`) values ('9','未说明的性别',NULL);

/*Data for the table `sys_unit_info` */

insert into `sys_unit_info` (`UNIT_CODE`,`UNIT_NAME`,`PHONE`,`FAX`,`E_MAIL`,`HOME_PAGE`,`REMARK`,`UNIT_TYPE`) values ('天行软件','天行软件技术有限公司','','','huhj2008@126.com','','Selenium test',0);

/*Data for the table `t_resoperation` */

insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (1,'操作','包括新增、修改、删除部门及设置部门的岗位和人员等功能。',8011,'ALL',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (2,'浏览','仅仅只能查看部门的结构和信息。',8011,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (3,'操作','包括新增、修改、删除岗位及设置岗位的人员等功能。',8012,'ALL',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (4,'浏览','仅仅只能查看岗位信息。',8012,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (5,'操作','包括设置部门的人员和为人员设置岗位等功能。',8013,'ALL',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (6,'浏览','包括查看人员信息和对人员进行查询等功能。',8013,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (7,'操作','包括新增、修改、删除用户及设置用户的权限和角色等功能。',8021,'ALL',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (8,'浏览','包括查看用户信息和对用户进行查询等功能。',8021,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (9,'操作','包括新增、修改、删除角色及设置角色的权限等功能。',8022,'ALL',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (10,'浏览','仅仅只能查看角色信息。',8022,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (11,'操作','包括新增、修改、删除资源操作等功能。',8023,'ALL',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (12,'浏览','仅仅只能查看操作信息。',8023,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (13,'操作','包括新增、修改、删除资源等功能。',8024,'ALL',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (14,'浏览','仅仅只能查看资源信息。',8024,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (15,'操作','包括本模块的操作功能。',7015,'ALL',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (16,'操作','包括导出和删除操作日志信息等功能。',8030,'ALL',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (17,'浏览','包括查询和浏览操作日志信息等功能。',8030,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (18,'操作','包括日常办公操作权限',10,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (19,'浏览','包括日常办公浏览权限',10,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (20,'操作','包括日志管理操作权限',1010,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (21,'浏览','包括日志管理浏览权限',1010,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (22,'操作','包括我的日志操作权限',1011,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (23,'浏览','包括我的日志浏览权限',1011,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (24,'操作','包括日志查询操作权限',1012,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (25,'浏览','包括日志查询浏览权限',1012,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (26,'操作','包括人周目标操作权限',1031,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (27,'浏览','包括人周目标浏览权限',1031,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (28,'操作','包括人月目标操作权限',1032,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (29,'浏览','包括人月目标浏览权限',1032,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (30,'操作','包括人年目标操作权限',1033,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (31,'浏览','包括人年目标浏览权限',1033,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (32,'操作','包括目标管理操作权限',1020,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (33,'浏览','包括目标管理浏览权限',1020,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (34,'操作','包括每周目标操作权限',1021,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (35,'浏览','包括每周目标浏览权限',1021,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (36,'操作','包括月度目标操作权限',1022,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (37,'浏览','包括月度目标浏览权限',1022,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (38,'操作','包括月度总结操作权限',1023,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (39,'浏览','包括月度总结浏览权限',1023,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (40,'操作','包括年度目标操作权限',1024,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (41,'浏览','包括年度目标浏览权限',1024,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (42,'操作','包括年度总结操作权限',1025,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (43,'浏览','包括年度总结浏览权限',1025,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (44,'操作','包括五年规划操作权限',1026,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (45,'浏览','包括五年规划浏览权限',1026,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (46,'操作','包括日程安排操作权限',1040,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (47,'浏览','包括日程安排浏览权限',1040,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (48,'操作','包括工作汇报操作权限',1030,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (49,'浏览','包括工作汇报浏览权限',1030,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (50,'操作','包括授权管理操作权限',1050,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (51,'浏览','包括授权管理浏览权限',1050,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (110,'操作','包括常用工具操作权限',70,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (111,'浏览','包括常用工具浏览权限',70,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (112,'操作','包括邮件管理操作权限',7010,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (113,'浏览','包括邮件管理浏览权限',7010,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (116,'操作','包括内部短信操作权限',7012,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (117,'浏览','包括内部短信浏览权限',7012,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (118,'操作','包括备忘录操作权限',7013,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (119,'浏览','包括备忘录浏览权限',7013,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (120,'操作','包括名片夹操作权限',7014,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (121,'浏览','包括名片夹浏览权限',7014,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (122,'操作','包括菜单定制操作权限',7015,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (123,'浏览','包括菜单定制浏览权限',7015,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (124,'操作','包括万年日历操作权限',7016,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (125,'浏览','包括万年日历浏览权限',7016,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (128,'操作','包括在线交流操作权限',9210,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (129,'浏览','包括在线交流浏览权限',9210,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (130,'操作','包括基础数据操作权限',8040,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (131,'浏览','包括基础数据浏览权限',8040,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (132,'操作','包括单位信息操作权限',8041,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (133,'浏览','包括单位信息浏览权限',8041,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (134,'操作','包括政治面貌操作权限',8042,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (135,'浏览','包括政治面貌浏览权限',8042,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (136,'操作','包括职称维护操作权限',8043,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (137,'浏览','包括职称维护浏览权限',8043,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (138,'操作','包括职务维护操作权限',8044,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (139,'浏览','包括职务维护浏览权限',8044,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (140,'操作','包括学历维护操作权限',8045,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (141,'浏览','包括学历维护浏览权限',8045,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (142,'操作','包括民族维护操作权限',8046,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (143,'浏览','包括民族维护浏览权限',8046,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (144,'操作','包括婚姻信息操作权限',8047,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (145,'浏览','包括婚姻信息浏览权限',8047,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (146,'操作','包括性别维护操作权限',8048,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (147,'浏览','包括性别维护浏览权限',8048,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (148,'操作','包括论坛管理操作权限',8050,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (149,'浏览','包括论坛管理浏览权限',8050,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (150,'操作','包括论坛维护操作权限',8051,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (151,'浏览','包括论坛维护浏览权限',8051,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (152,'操作','包括人员管理操作权限',8052,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (153,'浏览','包括人员管理浏览权限',8052,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (156,'操作','包括邮箱管理操作权限',8070,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (157,'浏览','包括邮箱管理浏览权限',8070,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (160,'操作','包括信息中心操作权限',90,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (161,'浏览','包括信息中心浏览权限',90,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (162,'操作','包括组织信息操作权限',9030,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (163,'浏览','包括组织信息浏览权限',9030,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (166,'操作','包括资源共享操作权限',9080,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (167,'浏览','包括资源共享浏览权限',9080,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (168,'操作','包括政策法规操作权限',9010,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (169,'浏览','包括政策法规浏览权限',9010,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (170,'操作','包括标准模板操作权限',9150,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (171,'浏览','包括标准模板浏览权限',9150,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (176,'操作','包括信息设置操作权限',9090,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (177,'浏览','包括信息设置浏览权限',9090,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (178,'操作','包括类型设置操作权限',9091,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (179,'浏览','包括类型设置浏览权限',9091,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (180,'操作','包括群组设置操作权限',9092,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (181,'浏览','包括群组设置浏览权限',9092,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (201,'公共操作','包括名片夹公共目录操作权限',7014,'PUBOPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (230,'操作','行业新闻操作权限',9020,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (231,'浏览','行业新闻浏览权限',9020,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (232,'操作','管理制度操作权限',9040,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (233,'浏览','管理制度浏览权限',9040,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (234,'操作','文档资料操作权限',9060,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (235,'浏览','文档资料浏览权限',9060,'VIEW',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (318,'所有','操作映射',214,'ALL',NULL);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (328,'所有','行政级别',219,'ALL',NULL);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (329,'浏览','行政级别',219,'VIEW',NULL);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (330,'资源排序','资源管理',8024,'ZYPX',NULL);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (331,'部门排序','组织管理之部门排序',8011,'BMPX',NULL);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (332,'所有','数据维护',220,'ALL',NULL);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (1000,'公共操作','包括公共群组操作权限',9092,'PUBOPT',0);

insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (2080,'Operation','自动测试',8080,'OPT',0);
insert into `t_resoperation` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCE_ID`,`CODE`,`ORDERID`) values (2081,'View','自动测试',8080,'VIEW',0);

/*Data for the table `t_resource` */

insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (0,'办公平台','GDCA办公自动化平台',NULL,NULL,-1,'3','GDP-ROOT',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (10,'日常办公','日常办公','0',NULL,0,'0','LZBG',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (70,'常用工具','常用工具','0',NULL,0,'0','tool',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (80,'信息中心','信息中心','0',NULL,0,'0','XXFW',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (90,'系统管理','系统管理','link',NULL,0,'0','GDP-XTGL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (214,'操作映射','操作映射','link','/privilege/permissionMapAction.do?action=list',8020,'0','GDP-CZYS',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (219,'行政级别','行政级别','link','/privilege/levelAction.do?action=list',8010,'0','GDP-XZJB',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (220,'数据维护','资源、操作数据的导出备份','0','/basedata/BaseDataAction.do?action=toExport',90,'0','GDP-JCSJ',8);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1000,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1010,'日志管理','日志管理','0',NULL,10,'0','LZGL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1011,'我的日志','我的日志','0','/dailyoffice/worklog/listworklog.do',1010,'0','WDLZ',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1012,'日志查询','日志查询','0','/dailyoffice/worklog/frameteamworklog.jsp',1010,'0','LZCS',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1015,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1020,'目标管理','目标管理','0',NULL,10,'0','MBGL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1021,'每周目标','每周目标','0','/dailyoffice/targetmng/frameweektarget.jsp',1020,'0','MZMB',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1022,'月度目标','月度目标','0','/dailyoffice/targetmng/framemonthtarget.jsp',1020,'0','YDMB',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1023,'月度总结','月度总结','0','/dailyoffice/targetmng/framemonthsum.jsp',1020,'0','YDZJ',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1024,'年度目标','年度目标','0','/dailyoffice/targetmng/listyeartarget.do',1020,'0','NDMB',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1025,'年度总结','年度总结','0','/dailyoffice/targetmng/listyearsum.do',1020,'0','NDZJ',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1026,'五年规划','五年规划','0','/dailyoffice/targetmng/listfiveyearlayout.do',1020,'0','WRJF',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1030,'工作汇报','工作汇报','0',NULL,10,'0','GZHB',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1031,'周汇报','周汇报','0','/dailyoffice/worklog/framepersonweektarget.jsp',1030,'0','LZMB',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1032,'月汇报','月汇报','0','/dailyoffice/worklog/framepersonmonthtarget.jsp',1030,'0','LYMB',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1033,'年汇报','年','0','/dailyoffice/worklog/framepersonyeartarget.jsp',1030,'0','LNMB',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1040,'日程安排','日程安排','0','/dailyoffice/calendararrange/listcalendarbyweek.do',10,'0','LTAP',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1045,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1050,'授权管理','授权管理','0','/dailyoffice/impowermgr/listimpower.do',10,'0','SQGL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1060,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1075,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1090,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1105,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1120,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1135,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1150,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1165,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1180,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1195,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1210,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1225,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1240,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1255,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1270,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1285,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1300,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1315,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1330,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1345,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1360,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1375,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1390,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1405,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1420,'java',NULL,NULL,NULL,0,'\0','null',NULL);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1780,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1795,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1810,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1825,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1840,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1855,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1870,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1885,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1900,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1915,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1930,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1945,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1960,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1975,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (1990,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (2005,'java',NULL,NULL,NULL,0,'\0','null',10);

insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (2020,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (2035,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (2050,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (2065,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (2095,'java',NULL,NULL,NULL,0,'\0','null',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (2110,'java',NULL,NULL,NULL,0,'\0','null',10);

insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (7010,'邮件管理','邮件管理','0','/dailyoffice/mailmgr/mailframe.jsp',70,'0','YJGL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (7012,'内部短信','内部短信','0','/dailyoffice/message/framemessage.jsp',70,'0','RBDX',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (7013,'备忘录','备忘录','0','/dailyoffice/tools/ticklerframe.jsp',70,'0','BWL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (7014,'名片夹','名片夹','0','/dailyoffice/tools/cardframe.jsp',70,'0','MBJ',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (7015,'菜单定制','菜单定制','link','/jsp/menusetup/frame.jsp',70,'0','GDP-CDDZ',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (7016,'万年日历','万年日历','0','/dailyoffice/tools/wannianli.html',70,'3','WNLL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8010,'组织管理','组织管理','link',NULL,90,'0','GDP-ZZGL',0);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8011,'部门管理','部门管理','link','/jsp/organization/department/department-main.jsp',8010,'0','GDP-BMGL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8012,'岗位管理','岗位管理','link','/jsp/organization/job/job-main.jsp',8010,'0','GDP-GWGL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8013,'人员管理','人员管理','link','/jsp/organization/employee/employee-main.jsp',8010,'0','GDP-RYGL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8020,'权限管理','权限管理','link',NULL,90,'0','GDP-QXGL',1);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8021,'用户管理','用户管理','link','/privilege/userAction.do?action=list',8020,'0','GDP-YHGL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8022,'角色管理','角色管理','link','/privilege/roleAction.do?action=list',8020,'0','GDP-JSGL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8023,'操作管理','操作管理','link','/jsp/privilege/opt/opt-main.jsp',8020,'0','GDP-CZGL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8024,'资源管理','资源管理','link','/jsp/privilege/resource/res-main.jsp',8020,'0','GDP-ZYGL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8030,'操作日志','操作日志','link','/log/loglist.do?action=list',90,'0','GDP-CZRZ',9);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8040,'基础数据','基础数据','0',NULL,90,'0','GCSJ',5);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8041,'单位信息','单位信息','0','/admin/listunit.do',8040,'0','DWXX',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8042,'政治面貌','政治面貌','0','/admin/listpolity.do',8040,'0','ZZMM',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8043,'职称维护','职称维护','0','/admin/listjob.do',8040,'0','ZCWF',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8044,'职务维护','职务维护','0','/admin/listduty.do',8040,'0','ZWWF',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8045,'学历维护','学历维护','0','/admin/listknow.do',8040,'0','XLWF',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8046,'民族维护','民族维护','0','/admin/listnation.do',8040,'0','MZWF',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8047,'婚姻信息','婚姻信息','0','/admin/listmarr.do',8040,'0','FYXX',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8048,'性别维护','性别维护','0','/admin/listsex.do',8040,'0','XBWF',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8050,'论坛管理','论坛管理','0',NULL,90,'0','LTGL',3);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8051,'论坛维护','论坛维护','0','/dailyoffice/bbs/mng/topboard.do',8050,'0','LTWF',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8052,'人员管理','人员管理','0','/dailyoffice/bbs/mng/bbsuser.do',8050,'0','LYWF',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8070,'邮箱管理','邮箱管理','0','/dailyoffice/mailmgr/mailboxmgr/mailboxframe.jsp',90,'0','YSGL',2);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (8080,'Selenium','与系统集成的基于浏览器的自动化功能测试','0','/selenium/TestRunner.html?test=./copTest/TestSuite.html',90,'1','GNCS',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (9010,'政策法规','政策法规','0','/infoservice/lawframe.jsp',80,'0','ZCFG',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (9020,'行业新闻','行业新闻','0','/infoservice/tradenewsframe.jsp',80,'0','HYXW',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (9030,'公司动态','公司动态','0','/infoservice/orginfoframe.jsp',80,'0','ZZXX',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (9040,'管理制度','管理制度','0','/infoservice/managesystemframe.jsp',80,'0','GLZD',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (9060,'文档资料','文档资料','0','/infoservice/docinfoframe.jsp',80,'0','WDZL',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (9080,'资源共享','资源共享','0','/infoservice/publicinfoframe.jsp',80,'0','GGXX',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (9090,'信息设置','信息设置','0',NULL,80,'0','XXSZ',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (9091,'类型设置','类型设置','0','/infoservice/typeframe.jsp',9090,'0','LSSZ',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (9092,'群组设置','群组设置','0','/infoservice/groupframe.jsp',9090,'0','ZQSZ',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (9150,'模板设置','模板设置','0','/infoservice/templateframe.jsp',80,'0','BZMB',10);
insert into `t_resource` (`ID`,`NAME`,`DESCRIPTION`,`RESOURCETYPE`,`LINK`,`PARENT_ID`,`TARGET`,`CODE`,`ORDERID`) values (9210,'BBS论坛','BBS论坛','0','/dailyoffice/bbs/main.do',70,'0','ZXJL',10);


/* initData-SuperUser.sql start */

SET SQL_MODE=@OLD_SQL_MODE;

-- 初始化ID表，其他表需要自增ID或唯一键值时通过SQL操作从此表获得，IDtype代表有几个序列
-- 目前的问题：不知道程序中到底用了几个IDtype
delete from geverid;
INSERT INTO geverid(idtype, id)VALUES(0, 1000);
INSERT INTO geverid(idtype, id)VALUES(1, 1000);
INSERT INTO geverid(idtype, id)VALUES(2, 1000);

-- 初始化行政级别 --
delete from t_level;
INSERT INTO T_LEVEL(ID, CODE, NAME, DESCRIPTION) VALUES(1, 'GDP-NO-LEVEL', '不选择级别', '用户默认级别');

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


