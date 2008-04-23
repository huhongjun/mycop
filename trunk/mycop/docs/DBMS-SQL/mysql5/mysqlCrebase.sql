
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

