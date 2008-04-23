/*==============================================================*/
/* Database name:  Database_1                                   */
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     2006-2-11 2:57:38                            */
/*==============================================================*/


create sequence "S_BadOutBox";

create sequence S_IM_REPAIR_APPLY;

create sequence S_IM_REPAIR_INFO;

create sequence S_IM_USEOIL_INFO;

create sequence S_INBOX;

create sequence S_IS_CUSTOMER_STATUS;

create sequence S_OUTBOX;

create sequence S_SENDEDOUTBOX;

create sequence S_TIMINGOUTBOX;

/*==============================================================*/
/* Table: BUYODD                                                */
/*==============================================================*/
create table BUYODD  (
   BUYODD_NUM           VARCHAR2(30)                    not null,
   "Handler"            VARCHAR2(20)                    not null,
   BUYER                VARCHAR2(20),
   REG_DATE             DATE,
   "remark"             VARCHAR2(200),
   constraint PK_BUYODD primary key (BUYODD_NUM)
);

/*==============================================================*/
/* Table: BUYODDLIST                                            */
/*==============================================================*/
create table BUYODDLIST  (
   BUYODD_NUM           VARCHAR2(30)                    not null,
   "sequence"           NUMBER                          not null,
   INFO_NUM             VARCHAR2(30)                    not null,
   BUY_DATE             DATE,
   BUY_NUMO             NUMBER,
   BUY_PRICE            NUMBER(18,2),
   constraint PK_BUYODDLIST primary key (BUYODD_NUM),
   constraint FK_BUYODDLI_BUY foreign key (BUYODD_NUM)
         references BUYODD (BUYODD_NUM)
);

/*==============================================================*/
/* Table: "BadOutBox"                                           */
/*==============================================================*/
create table "BadOutBox"  (
   ID                   NUMBER(6)                       not null,
   "ExpressLevel"       NUMBER,
   SN                   VARCHAR2(50),
   "Username"           VARCHAR2(50),
   "Mbno"               VARCHAR2(50),
   "Msg"                VARCHAR2(200),
   "Senddate"           VARCHAR2(50),
   "SendTime"           VARCHAR2(50),
   "Smstype"            VARCHAR2(50),
   constraint PK_BADOUTBOX primary key (ID)
);

/*==============================================================*/
/* Table: DESKTOPSETUP                                          */
/*==============================================================*/
create table DESKTOPSETUP  (
   USERID               VARCHAR2(30)                    not null,
   DESKPICTYPE          NUMBER,
   DESKTOPPATH          VARCHAR2(255),
   CSSTYPE              NUMBER,
   SHOWTYPE             NUMBER,
   PICSHOWTYPE          NUMBER,
   ISDEFAULT            NUMBER,
   MENUSTYLE            NUMBER,
   constraint PK_DESKTOPSETUP primary key (USERID)
);

/*==============================================================*/
/* Table: DO_ARRANGER                                           */
/*==============================================================*/
create table DO_ARRANGER  (
   USER_CODE            NUMBER                          not null,
   ARRANGE              VARCHAR2(255)                   not null,
   constraint PK_DO_ARRANGER primary key (USER_CODE, ARRANGE)
);

/*==============================================================*/
/* Table: DO_BBS_TOPBOARD                                       */
/*==============================================================*/
create table DO_BBS_TOPBOARD  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   TBOARD_NAME          VARCHAR2(50)                    not null,
   ICON_FILE            VARCHAR2(255),
   TBOARD_NOTE          VARCHAR2(200),
   TBOARD_STATE         NUMBER,
   MASTER               VARCHAR2(20),
   constraint PK_DO_BBS_TOPBO primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_BBS_USER                                           */
/*==============================================================*/
create table DO_BBS_USER  (
   BBS_USER_CODE        VARCHAR2(20)                    not null,
   NICKNAME             VARCHAR2(20)                    not null,
   USER_ICON            VARCHAR2(255),
   LAST_LOG_TIME        DATE,
   USER_STATE           NUMBER                          not null,
   USER_CODE            NUMBER                          not null,
   SEX_CODE             VARCHAR2(4),
   E_MAIL               VARCHAR2(30),
   HOME_PAGE            VARCHAR2(100),
   constraint SQL031112110853 primary key (BBS_USER_CODE)
);

/*==============================================================*/
/* Table: DO_BD_AWOKE                                           */
/*==============================================================*/
create table DO_BD_AWOKE  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   ADVANCE_DAYS         NUMBER,
   ADVANCE_MEMS         VARCHAR2(800),
   FLAG                 NUMBER,
   MEMO                 VARCHAR2(100),
   constraint PK_DO_BD_AWOKE unique (SERIAL_NUM)
         
   using index
       pctfree 10
       initrans 2
       maxtrans 255
       tablespace USERS
       storage
       (
           initial 128K
           minextents 1
           maxextents unlimited
       )
        logging
);

/*==============================================================*/
/* Table: DO_CALENDAR_ARRANGE                                   */
/*==============================================================*/
create table DO_CALENDAR_ARRANGE  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   CALENDAR             DATE,
   USER_CODE            NUMBER,
   BEGIN_TIME           DATE,
   END_TIME             DATE,
   TASK_TYPE            NUMBER,
   TASK_SUM             VARCHAR2(60),
   TASK_CONTENT         VARCHAR2(200),
   REMIND_FLAG          NUMBER,
   AWOKE_TIME           NUMBER,
   ARRANGE              VARCHAR2(20),
   constraint SQL030906101447660 primary key (SERIAL_NUM)
         using index
       pctfree 10
       initrans 2
       maxtrans 255
       tablespace USERS
       storage
       (
           initial 128K
           minextents 1
           maxextents unlimited
       )
        logging
)
  pctfree 10
initrans 1
maxtrans 255
storage
(
    initial 128K
    minextents 1
    maxextents unlimited
)
tablespace USERS
logging
monitoring
  noparallel;

/*==============================================================*/
/* Index: INDEX_CALENDAR                                        */
/*==============================================================*/
create unique index INDEX_CALENDAR on DO_CALENDAR_ARRANGE (
   CALENDAR ASC,
   USER_CODE ASC,
   BEGIN_TIME ASC,
   END_TIME ASC
)
pctfree 10
initrans 2
maxtrans 255
storage
(
    initial 128K
    minextents 1
    maxextents unlimited
)
tablespace USERS;

/*==============================================================*/
/* Table: DO_CARDCASE_TYPE                                      */
/*==============================================================*/
create table DO_CARDCASE_TYPE  (
   TYPE_CODE            VARCHAR2(30)                    not null,
   USER_CODE            NUMBER,
   TYPE_NAME            VARCHAR2(20),
   REMARK               VARCHAR2(60),
   constraint PK_DO_CARDCASE_ primary key (TYPE_CODE)
);

/*==============================================================*/
/* Table: DO_DIRECTORY                                          */
/*==============================================================*/
create table DO_DIRECTORY  (
   DIR_ID               NUMBER                          not null,
   DIR_NAME             VARCHAR2(50),
   PARENT_ID            NUMBER,
   DIR_RIGHT            VARCHAR2(2000),
   RIGHT_TYPE           NUMBER,
   constraint PK_DO_DIRECTORY primary key (DIR_ID)
);

/*==============================================================*/
/* Table: DO_DIRECTORY_FILE                                     */
/*==============================================================*/
create table DO_DIRECTORY_FILE  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   DIR_ID               NUMBER,
   FILE_NAME            VARCHAR2(255),
   FILE_SIZE            NUMBER(18,2),
   FILE_PATH            VARCHAR2(255),
   CREATEOR             NUMBER,
   CREATE_TIME          DATE,
   constraint PK_DO_DIRECTOR2 primary key (SERIAL_NUM),
   constraint FK_DO_DIREC_REF foreign key (DIR_ID)
         references DO_DIRECTORY (DIR_ID)
);

/*==============================================================*/
/* Table: DO_FIVEYEAR_LAYOUT                                    */
/*==============================================================*/
create table DO_FIVEYEAR_LAYOUT  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   CURRENT_YEAR         CHAR(4),
   USER_CODE            NUMBER,
   CREATE_DATE          DATE,
   APPROVE              VARCHAR2(20),
   APPROVE_DATE         DATE,
   CONTENT              LONG RAW,
   APPROVE_FLAG         NUMBER                         default 0,
   constraint PK_DO_FIVEYEAR_ primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_IMPOWER                                            */
/*==============================================================*/
create table DO_IMPOWER  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   CREATE_TIME          DATE,
   ACCEPTER             VARCHAR2(20),
   PAYER                VARCHAR2(20),
   BEGIN_TIME           DATE,
   END_TIME             DATE,
   "COMMENT"            VARCHAR2(60),
   NOTICE               VARCHAR2(2000),
   CONTENT              LONG RAW,
   SEND_FLAG            NUMBER                         default 0,
   constraint PK_DO_IMPOWER primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Index: INDEX_IMPOWER                                         */
/*==============================================================*/
create unique index INDEX_IMPOWER on DO_IMPOWER (
   CREATE_TIME ASC,
   ACCEPTER ASC,
   PAYER ASC
);

/*==============================================================*/
/* Table: DO_INFO_TYPE                                          */
/*==============================================================*/
create table DO_INFO_TYPE  (
   INFO_TYPE            VARCHAR2(30)                    not null,
   TYPE_NAME            VARCHAR2(20)                    not null,
   constraint PK_DO_INFO_TYPE primary key (INFO_TYPE)
);

/*==============================================================*/
/* Table: DO_INNER_MSG                                          */
/*==============================================================*/
create table DO_INNER_MSG  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   USER_CODE            VARCHAR2(30)                    not null,
   SEND_TIME            DATE,
   CONTENT              VARCHAR2(255),
   RECEIVER             VARCHAR2(20),
   READ_FLAG            NUMBER,
   READ_TIME            DATE,
   WEB_URL              VARCHAR2(255),
   MSG_TYPE             VARCHAR2(1),
   MEMO                 VARCHAR2(255),
   MODEL_ID             NUMBER,
   MSERIAL_NUM          VARCHAR2(30),
   constraint PK_DO_INNER_MSG primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_KNOWLEDGE                                          */
/*==============================================================*/
create table DO_KNOWLEDGE  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   FILE_NAME            VARCHAR2(255),
   FILE_SIZE            NUMBER(18,2),
   FILE_PATH            VARCHAR2(255),
   USER_CODE            NUMBER,
   APPLY_DATE           DATE,
   CHECKER              NUMBER,
   CHECK_DATE           DATE,
   CHECK_FLAG           NUMBER                         default 0,
   SAVE_PATH            VARCHAR2(255),
   SAVE_FILE            VARCHAR2(255),
   constraint PK_DO_KNOWLEDGE primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_NETMEET                                            */
/*==============================================================*/
create table DO_NETMEET  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   TITLE                VARCHAR2(200),
   CALLER               VARCHAR2(20),
   MASTER               VARCHAR2(20),
   BEGIN_TIME           DATE,
   END_TIME             DATE,
   ATTENDS              VARCHAR2(1000),
   ON_LINES             VARCHAR2(1000),
   FINISH_FLAG          NUMBER,
   RECORDER             VARCHAR2(20),
   SUMMARY              CLOB,
   FILE_PATH            VARCHAR2(255),
   FILE_NAME            VARCHAR2(255),
   AWOKE_FLAG           NUMBER                          not null,
   BUILDING             NUMBER,
   constraint PK_DO_NETMEET primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_NETMEET_CONTENT                                    */
/*==============================================================*/
create table DO_NETMEET_CONTENT  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   ORDER_ID             NUMBER                          not null,
   USER_NAME            VARCHAR2(20),
   CONTENT              VARCHAR2(3000),
   USER_ICON            VARCHAR2(255),
   FILE_PATH            VARCHAR2(255),
   FILE_NAME            VARCHAR2(255),
   constraint PK_DO_NETMEET_C primary key (SERIAL_NUM, ORDER_ID),
   constraint FK_DO_NETME_REF foreign key (SERIAL_NUM)
         references DO_NETMEET (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_PERSONAL_SETUP                                     */
/*==============================================================*/
create table DO_PERSONAL_SETUP  (
   USER_CODE            NUMBER                          not null,
   TEAM_MEMBER          VARCHAR2(1000),
   constraint PK_DO_PERSONAL2 primary key (USER_CODE)
);

/*==============================================================*/
/* Table: DO_PERSONAL_TARET                                     */
/*==============================================================*/
create table DO_PERSONAL_TARET  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   USER_CODE            NUMBER,
   CURRENT_YEAR         CHAR(4),
   CURRENT_MONTH        VARCHAR2(2),
   CURRENT_WORK         CHAR,
   TARGET_TYPE          NUMBER,
   DEPT_CODE            VARCHAR2(30),
   POST_CODE            VARCHAR2(30),
   CREATE_DATE          DATE,
   TARGET_CONTENT       LONG RAW,
   constraint PK_DO_PERSONAL_ primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Index: "Index_personal"                                      */
/*==============================================================*/
create unique index "Index_personal" on DO_PERSONAL_TARET (
   USER_CODE ASC,
   TARGET_TYPE ASC,
   CURRENT_YEAR ASC,
   CURRENT_MONTH ASC,
   CURRENT_WORK ASC
);

/*==============================================================*/
/* Table: DO_TARGET_MANAGE                                      */
/*==============================================================*/
create table DO_TARGET_MANAGE  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   CURRENT_YEAR         CHAR(4),
   CURRENT_MONTH        VARCHAR2(2),
   CURRENT_WORK         CHAR,
   TARGET_TYPE          CHAR,
   DEPT_CODE            VARCHAR2(30),
   USER_CODE            NUMBER,
   CREATE_DATE          DATE,
   AUDITOR              VARCHAR2(20),
   AUDIT_DATE           DATE,
   AUDIT_FLAG           NUMBER                         default 0,
   AUDIT_OPINION        VARCHAR2(200),
   CHECK_FLAG           NUMBER                         default 0,
   CHECKER              VARCHAR2(20),
   CHECK_DATE           DATE,
   CHECK_OPINION        VARCHAR2(200),
   TARGET_CONTENT       LONG RAW,
   constraint PK_DO_TARGET_MA primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Index: "Index_target"                                        */
/*==============================================================*/
create unique index "Index_target" on DO_TARGET_MANAGE (
   CURRENT_YEAR ASC,
   CURRENT_MONTH ASC,
   CURRENT_WORK ASC,
   TARGET_TYPE ASC,
   DEPT_CODE ASC
);

/*==============================================================*/
/* Table: DO_TEAM_LOG                                           */
/*==============================================================*/
create table DO_TEAM_LOG  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   USER_CODE            NUMBER,
   TEAM_TYPE            NUMBER,
   TEAM_MEMBER          VARCHAR2(1000),
   constraint PK_DO_TEAM_LOG primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_TICKLER                                            */
/*==============================================================*/
create table DO_TICKLER  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   USER_CODE            NUMBER                          not null,
   CONTENT              VARCHAR2(300),
   CREATE_DATE          DATE,
   REMIND_FLAG          CHAR,
   AWOKE_TIME           DATE,
   FINISH_FLAG          NUMBER,
   constraint PK_DO_TICKLER primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_VIEW_RIGHT                                         */
/*==============================================================*/
create table DO_VIEW_RIGHT  (
   USER_CODE            NUMBER                          not null,
   MEMBER               VARCHAR2(255)                   not null,
   constraint SQL030906101447 primary key (USER_CODE, MEMBER)
);

/*==============================================================*/
/* Table: DO_WORK_LOG                                           */
/*==============================================================*/
create table DO_WORK_LOG  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   CREATE_TIME          DATE,
   DEPT_CODE            VARCHAR2(30),
   USER_CODE            NUMBER,
   FILL_DATE            DATE,
   LOG_CONTENT          LONG RAW,
   constraint PK_DO_WORK_LOG primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_WORK_REPORT                                        */
/*==============================================================*/
create table DO_WORK_REPORT  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   CREATE_DATE          DATE,
   USER_CODE            NUMBER,
   BEGIN_TIME           DATE,
   END_TIME             DATE,
   RENDER               VARCHAR2(20),
   CHECKER              VARCHAR2(20),
   CHECK_DATE           DATE,
   CONTENT              LONG RAW,
   SEND_FLAG            NUMBER                         default 0,
   constraint PK_DO_WORK_REPO primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_WORLD_WINDOW                                       */
/*==============================================================*/
create table DO_WORLD_WINDOW  (
   TITLE                VARCHAR2(100)                   not null,
   INFO_TYPE            VARCHAR2(30)                    not null,
   CHILD_TYPE           VARCHAR2(20),
   URL                  VARCHAR2(100),
   constraint PK_DO_WORLD_WIN primary key (TITLE, INFO_TYPE),
   constraint FK_DO_WORLD_REF foreign key (INFO_TYPE)
         references DO_INFO_TYPE (INFO_TYPE)
);

/*==============================================================*/
/* Table: DS_LAWBASE1                                           */
/*==============================================================*/
create table DS_LAWBASE1  (
   TITLE                VARCHAR2(100)                   not null,
   SUB_TITLE            VARCHAR2(200)                   not null,
   TRADE_CODE           VARCHAR2(10),
   SOURCE_CODE          VARCHAR2(10),
   TOPIC_WORD           VARCHAR2(200),
   DISPATCH_UNIT        VARCHAR2(200),
   FILE_NO              VARCHAR2(20),
   PRINTSMAN_DATE       DATE,
   ACTUALIZE_DATE       DATE,
   FILE_PATH            VARCHAR2(255),
   CONTENT_MEMO         CLOB,
   REMARKS              VARCHAR2(60),
   constraint PK_DS_LAWBASE1 primary key (TITLE, SUB_TITLE)
);

/*==============================================================*/
/* Table: GEVERID                                               */
/*==============================================================*/
create table GEVERID  (
   IDTYPE               NUMBER                          not null,
   ID                   NUMBER                          not null,
   constraint SQL040615152006 primary key (IDTYPE)
);

/*==============================================================*/
/* Table: IM_CAR_TYPE                                           */
/*==============================================================*/
create table IM_CAR_TYPE  (
   CAR_TYPE             VARCHAR2(4)                     not null,
   CAR_NAME             VARCHAR2(20),
   constraint PK_IM_CAR_TYPE primary key (CAR_TYPE)
);

/*==============================================================*/
/* Table: IM_GOODS_APPLY                                        */
/*==============================================================*/
create table IM_GOODS_APPLY  (
   APPLY_DATE           DATE                            not null,
   USER_CODE            NUMBER                          not null,
   ORDER_NO             NUMBER                          not null,
   GOODS_CODE           VARCHAR2(20)                    not null,
   APPLY_QTY            NUMBER,
   USING                VARCHAR2(60),
   constraint PK_IM_GOODS_APP primary key (APPLY_DATE, USER_CODE, ORDER_NO)
);

/*==============================================================*/
/* Table: IM_GOODS_REGISTER                                     */
/*==============================================================*/
create table IM_GOODS_REGISTER  (
   BILL_NO              VARCHAR2(20)                    not null,
   HANDLE               VARCHAR2(20),
   REGISTER             DATE,
   REMARK               VARCHAR2(60),
   constraint PK_IM_GOODS_REG primary key (BILL_NO)
);

/*==============================================================*/
/* Table: IM_GOODS_TYPE                                         */
/*==============================================================*/
create table IM_GOODS_TYPE  (
   TYPE_CODE            VARCHAR2(20)                    not null,
   TYPE_NAME            VARCHAR2(60)                    not null,
   constraint SQL030823155956 primary key (TYPE_CODE)
);

/*==============================================================*/
/* Table: IM_MEET_TYPE                                          */
/*==============================================================*/
create table IM_MEET_TYPE  (
   TYPE_CODE            VARCHAR2(30)                    not null,
   TYPE_NAME            VARCHAR2(20),
   REMARK               VARCHAR2(60),
   constraint PK_IM_MEET_TYPE primary key (TYPE_CODE)
);

/*==============================================================*/
/* Table: IM_USECAR_APPLY                                       */
/*==============================================================*/
create table IM_USECAR_APPLY  (
   APPLY_TIME           DATE                            not null,
   USER_CODE            NUMBER                          not null,
   BEGIN_DATE           DATE,
   END_DATE             DATE,
   OUT_DIRECTION        VARCHAR2(60),
   FOLLOWER             VARCHAR2(255),
   CHECKER              VARCHAR2(20),
   CHECK_TIME           DATE,
   CHECK_RESULT         CHAR,
   OPINION              VARCHAR2(200),
   REMARK               VARCHAR2(60),
   constraint PK_IM_USECAR_AP primary key (APPLY_TIME, USER_CODE)
);

/*==============================================================*/
/* Table: INBOX                                                 */
/*==============================================================*/
create table INBOX  (
   ID                   NUMBER(6)                       not null,
   MBNO                 VARCHAR2(50)                    not null,
   MSG                  VARCHAR2(200),
   ARRIVEDATE           VARCHAR2(50),
   ARRIVETIME           VARCHAR2(50),
   COMMPORT             VARCHAR2(200),
   constraint SQL040212141830 primary key (ID)
);

/*==============================================================*/
/* Table: IS_ADDRESS_LIST                                       */
/*==============================================================*/
create table IS_ADDRESS_LIST  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   TYPE_FLAG            NUMBER,
   GROUP_NAME           VARCHAR2(20),
   USER_CODE            NUMBER,
   MEMBER               CLOB,
   GROUP_CODE           VARCHAR2(20),
   TABLE_NAME           VARCHAR2(20),
   constraint PK_IS_ADDRESS_L primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: IS_CUSTOMER_STATUS                                    */
/*==============================================================*/
create table IS_CUSTOMER_STATUS  (
   FLAG                 NUMBER(6)                       not null,
   STATUS               VARCHAR2(20),
   REMARK               VARCHAR2(60),
   constraint PK_IS_CUSTOMER3 primary key (FLAG)
);

/*==============================================================*/
/* Table: IS_CUSTOMER_TYPE                                      */
/*==============================================================*/
create table IS_CUSTOMER_TYPE  (
   TYPE_CODE            NUMBER                          not null,
   TYPE_NAME            VARCHAR2(20),
   REMARK               VARCHAR2(60),
   constraint PK_IS_CUSTOMER2 primary key (TYPE_CODE)
);

/*==============================================================*/
/* Table: IS_DOTYPE                                             */
/*==============================================================*/
create table IS_DOTYPE  (
   INFO_TYPE            VARCHAR2(20)                    not null,
   TYPELEVEL            NUMBER,
   PARENT_TYPE          VARCHAR2(20),
   MODULEFLAG           NUMBER,
   REMARK               VARCHAR2(30),
   constraint PK_IS_DOTYPE primary key (INFO_TYPE)
);

/*==============================================================*/
/* Table: IS_INFO_MANAGE                                        */
/*==============================================================*/
create table IS_INFO_MANAGE  (
   TITLE                VARCHAR2(100)                   not null,
   INFO_TYPE            VARCHAR2(20)                    not null,
   USER_CODE            NUMBER                          not null,
   CREATE_DATE          DATE,
   FILE_PATH            VARCHAR2(255),
   FILE_NAME            VARCHAR2(255),
   URL                  VARCHAR2(100),
   CONTENT              CLOB,
   INFO_LEVLE           NUMBER,
   INFO_FLAG            NUMBER,
   constraint PK_IS_INFO_MANA primary key (TITLE, INFO_TYPE),
   constraint FK_IS_INFO__REF foreign key (INFO_TYPE)
         references IS_DOTYPE (INFO_TYPE)
);

/*==============================================================*/
/* Table: IS_INFO_SERVE                                         */
/*==============================================================*/
create table IS_INFO_SERVE  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   TITLE                VARCHAR2(100),
   INFO_TYPE            VARCHAR2(20),
   DEPT                 VARCHAR2(2000),
   USER_CODE            NUMBER,
   CREATE_DATE          DATE,
   FILE_PATH            VARCHAR2(255),
   FILE_NAME            VARCHAR2(255),
   WORD_CONTENT         CLOB,
   CONTENT              LONG RAW,
   HINT_FLAG            NUMBER                         default 1,
   SEND_FLAG            NUMBER                         default 1,
   EDITOR_TYPE          NUMBER,
   INFO_FLAG            NUMBER,
   SHOW_FLAG            NUMBER,
   constraint PK_IS_INFO_SERV primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: IS_STANDARD_MODEL                                     */
/*==============================================================*/
create table IS_STANDARD_MODEL  (
   MODEL_CODE           VARCHAR2(30)                    not null,
   MODEL_TYPE           NUMBER,
   CREATE_DATE          DATE,
   ISSUE_FLAG           NUMBER,
   MODEL_NAME           VARCHAR2(20),
   USER_CODE            NUMBER,
   FILE_PATH            VARCHAR2(255),
   FILE_NAME            VARCHAR2(255),
   WORD_CONTENT         CLOB,
   CONTENT              LONG RAW,
   EDITOR_TYPE          NUMBER,
   INFO_TYPE            VARCHAR2(20),
   constraint PK_IS_STANDARD_ primary key (MODEL_CODE)
);

/*==============================================================*/
/* Table: LOGOSETUP                                             */
/*==============================================================*/
create table LOGOSETUP  (
   LOGOPATH             VARCHAR2(255),
   LOGOINFO             VARCHAR2(255),
   LOGOTYPE             NUMBER,
   BANNERPATH           VARCHAR2(255),
   BANNERINFO           VARCHAR2(255),
   BANNERTYPE           NUMBER,
   MEMO                 VARCHAR2(500)
);

/*==============================================================*/
/* Table: "Mail"                                                */
/*==============================================================*/
create table "Mail"  (
   USER_CODE            NUMBER,
   SERIAL_NUM           VARCHAR2(30)                    not null,
   POST_USERNAME        VARCHAR2(100),
   POST_ADDRESS         VARCHAR2(50),
   RECEIVE_ADDRESS      VARCHAR2(800),
   COPY_SEND            VARCHAR2(800),
   DENSE_SEND           VARCHAR2(800),
   SEND_DATE            VARCHAR2(19),
   TITLE                VARCHAR2(500),
   MAIL_DIR_ID          VARCHAR2(30)                    not null,
   RE_FLAG              VARCHAR2(1),
   READ_FLAG            VARCHAR2(1),
   CONTENT              CLOB,
   MAIL_TYPE            VARCHAR2(1),
   PRIORITY             VARCHAR2(1),
   MAIL_SIZE            NUMBER,
   OLD_MAILDIR_ID       VARCHAR2(30),
   constraint SQL030829150747 primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: "MailAttch"                                           */
/*==============================================================*/
create table "MailAttch"  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   MAIL_ID              VARCHAR2(30)                    not null,
   ATTCH_NAME           VARCHAR2(500),
   FILE_PATH            VARCHAR2(500),
   MEMO                 VARCHAR2(1),
   constraint SQL030829150948 primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: "MailCapacity"                                        */
/*==============================================================*/
create table "MailCapacity"  (
   USER_CODE            NUMBER                          not null,
   MAIL_CAPACITY        NUMBER(15,2)                    not null,
   CAPACITY_FLAG        VARCHAR2(1)                     not null,
   LIMIT_FLAG           VARCHAR2(1)                     not null,
   MEMO                 VARCHAR2(100),
   constraint SQL031111092735 primary key (USER_CODE)
);

/*==============================================================*/
/* Table: "MailDirectory"                                       */
/*==============================================================*/
create table "MailDirectory"  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   USER_CODE            VARCHAR2(6)                     not null,
   MAIL_DIR_NAME        VARCHAR2(30)                    not null,
   FLAG                 VARCHAR2(1)                     not null,
   MEMO                 VARCHAR2(30),
   constraint SQL030829150148 primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: "MailInfo"                                            */
/*==============================================================*/
create table "MailInfo"  (
   PAGENUMBER           NUMBER,
   USER_CODE            NUMBER,
   LABLE_NAME           CLOB,
   POST_FLAG            CHAR,
   SIGN_FLAG            CHAR,
   RETURN_FLAG          CHAR,
   SHOW_FLAG            CHAR,
   MAIL_LEVEL           CHAR
);

/*==============================================================*/
/* Table: OUTBOX                                                */
/*==============================================================*/
create table OUTBOX  (
   ID                   NUMBER(6)                       not null,
   EXPRESSLEVEL         NUMBER,
   SN                   VARCHAR2(50),
   USERNAME             VARCHAR2(50),
   MBNO                 VARCHAR2(50)                    not null,
   MSG                  VARCHAR2(200)                   not null,
   SENDDATE             VARCHAR2(50),
   SENDTIME             VARCHAR2(50),
   SMSTYPE              VARCHAR2(50),
   constraint SQL040211111657 primary key (ID)
);

/*==============================================================*/
/* Table: POP3MAILSETUP                                         */
/*==============================================================*/
create table POP3MAILSETUP  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   USER_CODE            NUMBER                          not null,
   POP3_NAME            VARCHAR2(60)                    not null,
   POP3_ADDRESS         VARCHAR2(60),
   POP3_ACCOUNT         VARCHAR2(60),
   POP3_PWD             VARCHAR2(255),
   AUTO_FLAG            CHAR,
   DEL_FLAG             CHAR,
   INCEPT_MAIL_DIR      VARCHAR2(60),
   SHOW_NAME            VARCHAR2(20),
   SHOW_ADDRESS         VARCHAR2(60),
   SMTP_SERVER          VARCHAR2(60),
   SMTP_AUTH            CHAR,
   SMTP_NAME            VARCHAR2(60),
   SMTP_PWD             VARCHAR2(255),
   USE_FLAG             CHAR,
   constraint SQL031018095845 primary key (SERIAL_NUM)
);

/*==============================================================*/
/* Table: SENDEDOUTBOX                                          */
/*==============================================================*/
create table SENDEDOUTBOX  (
   ID                   NUMBER(6)                       not null,
   EXPRESSLEVEL         NUMBER,
   SN                   VARCHAR2(50),
   USERNAME             VARCHAR2(50),
   MBNO                 VARCHAR2(50),
   MSG                  VARCHAR2(200),
   SENDDATE             VARCHAR2(50),
   SENDTIME             VARCHAR2(50),
   SMSTYPE              VARCHAR2(10),
   COMMPORT             VARCHAR2(50),
   constraint SQL040216132123 primary key (ID)
);

/*==============================================================*/
/* Table: "SMSCapacity"                                         */
/*==============================================================*/
create table "SMSCapacity"  (
   USERID               NUMBER                          not null,
   "NUMBER"             NUMBER                          not null,
   ACL                  NUMBER,
   SCOPE                VARCHAR2(10),
   BZ                   VARCHAR2(10),
   constraint PK_SMSCAPACITY primary key (USERID)
);

/*==============================================================*/
/* Table: SYS_DUTY                                              */
/*==============================================================*/
create table SYS_DUTY  (
   DUTY_CODE            VARCHAR2(4)                     not null,
   DUTY_NAME            VARCHAR2(20),
   REMARKS              VARCHAR2(60),
   constraint PK_SYS_DUTY primary key (DUTY_CODE)
);

/*==============================================================*/
/* Table: SYS_JOB                                               */
/*==============================================================*/
create table SYS_JOB  (
   JOB_CODE             VARCHAR2(4)                     not null,
   JOB_NAME             VARCHAR2(20),
   REMARKS              VARCHAR2(60),
   constraint PK_SYS_JOB primary key (JOB_CODE)
);

/*==============================================================*/
/* Table: SYS_KNOWLEDGE                                         */
/*==============================================================*/
create table SYS_KNOWLEDGE  (
   KNOWLEDGE_CODE       VARCHAR2(4)                     not null,
   KNOWLEDGE_NAME       VARCHAR2(20),
   REMARKS              VARCHAR2(60),
   constraint PK_SYS_KNOWLEDG primary key (KNOWLEDGE_CODE)
);

/*==============================================================*/
/* Table: SYS_MARRIAGE                                          */
/*==============================================================*/
create table SYS_MARRIAGE  (
   MARRIAGE_CODE        VARCHAR2(4)                     not null,
   MARRIAGE             VARCHAR2(20),
   REMARKS              VARCHAR2(60),
   constraint PK_SYS_MARRIAGE primary key (MARRIAGE_CODE)
);

/*==============================================================*/
/* Table: SYS_NATIONAL                                          */
/*==============================================================*/
create table SYS_NATIONAL  (
   NATIONAL_CODE        VARCHAR2(4)                     not null,
   NATIONAL_NAME        VARCHAR2(20),
   REMARKS              VARCHAR2(60),
   constraint PK_SYS_NATIONAL primary key (NATIONAL_CODE)
);

/*==============================================================*/
/* Table: SYS_PARAMETER                                         */
/*==============================================================*/
create table SYS_PARAMETER  (
   WORK_LOG_NUM         NUMBER                         default 5
);

/*==============================================================*/
/* Table: SYS_POLITY                                            */
/*==============================================================*/
create table SYS_POLITY  (
   POLITY_CODE          VARCHAR2(4)                     not null,
   POLITY_NAME          VARCHAR2(20),
   REMARKS              VARCHAR2(60),
   constraint PK_SYS_POLITY primary key (POLITY_CODE)
);

/*==============================================================*/
/* Table: SYS_SEX                                               */
/*==============================================================*/
create table SYS_SEX  (
   SEX_CODE             VARCHAR2(4)                     not null,
   SEX_NAME             VARCHAR2(20),
   REMARKS              VARCHAR2(60),
   constraint PK_SYS_SEX primary key (SEX_CODE)
);

/*==============================================================*/
/* Table: SYS_UNIT_INFO                                         */
/*==============================================================*/
create table SYS_UNIT_INFO  (
   UNIT_CODE            VARCHAR2(20)                    not null,
   UNIT_NAME            VARCHAR2(60),
   PHONE                VARCHAR2(30),
   FAX                  VARCHAR2(30),
   E_MAIL               VARCHAR2(30),
   HOME_PAGE            VARCHAR2(100),
   REMARK               CLOB,
   UNIT_TYPE            NUMBER,
   constraint PK_SYS_UNIT_INF primary key (UNIT_CODE)
);

/*==============================================================*/
/* Table: TIMINGOUTBOX                                          */
/*==============================================================*/
create table TIMINGOUTBOX  (
   ID                   NUMBER(6)                       not null,
   EXPRESSLEVEL         NUMBER,
   SN                   VARCHAR2(50),
   USERNAME             VARCHAR2(50),
   MBNO                 VARCHAR2(50)                    not null,
   MSG                  VARCHAR2(200)                   not null,
   SENDDATE             VARCHAR2(50),
   SENDTIME             VARCHAR2(50),
   SMSTYPE              VARCHAR2(50),
   SENDINGDATE          VARCHAR2(20),
   constraint SQL040211112134 primary key (ID)
);

/*==============================================================*/
/* Table: T_DEPARTMENT                                          */
/*==============================================================*/
create table T_DEPARTMENT  (
   ID                   NUMBER                          not null,
   CODE                 VARCHAR2(30),
   NAME                 VARCHAR2(50),
   DESCRIPTION          VARCHAR2(100),
   DEPARTMENTTYPE       VARCHAR2(30),
   PARENT_ID            NUMBER,
   constraint PK_T_DEPARTMENT primary key (ID)
);

/*==============================================================*/
/* Table: T_JOB                                                 */
/*==============================================================*/
create table T_JOB  (
   ID                   NUMBER                          not null,
   NAME                 VARCHAR2(50),
   DESCRIPTION          VARCHAR2(100),
   DEPARTMENT_ID        NUMBER,
   constraint PK_T_JOB primary key (ID),
   constraint FK_T_JOB_REFERE foreign key (DEPARTMENT_ID)
         references T_DEPARTMENT (ID)
);

/*==============================================================*/
/* Table: T_RESOURCE                                            */
/*==============================================================*/
create table T_RESOURCE  (
   ID                   NUMBER                          not null,
   NAME                 VARCHAR2(50),
   DESCRIPTION          VARCHAR2(100),
   RESOURCETYPE         VARCHAR2(30),
   LINK                 VARCHAR2(200),
   PARENT_ID            NUMBER,
   TARGET               CHAR,
   CODE                 VARCHAR2(12),
   constraint PK_T_RESOURCE primary key (ID)
);

/*==============================================================*/
/* Table: T_ROLE                                                */
/*==============================================================*/
create table T_ROLE  (
   ID                   NUMBER                          not null,
   NAME                 VARCHAR2(50),
   DESCRIPTION          VARCHAR2(100),
   ROLETYPE             VARCHAR2(30),
   constraint PK_T_ROLE primary key (ID)
);

/*==============================================================*/
/* Table: T_SYSID                                               */
/*==============================================================*/
create table T_SYSID  (
   IDTYPE               NUMBER                          not null,
   ID                   NUMBER                          not null,
   constraint SQL04 primary key (IDTYPE)
);

/*==============================================================*/
/* Table: T_SYSTEM_ID                                           */
/*==============================================================*/
create table T_SYSTEM_ID  (
   IDTYPE               NUMBER                          not null,
   ID                   NUMBER                          not null,
   constraint SQL040614105330 primary key (IDTYPE)
);

/*==============================================================*/
/* Table: T_SYSTEM_LOG                                          */
/*==============================================================*/
create table T_SYSTEM_LOG  (
   ID                   NUMBER                          not null,
   OTIME                VARCHAR2(20)                    not null,
   USERNAME             VARCHAR2(50),
   MODULE               VARCHAR2(50),
   IPADDRESS            VARCHAR2(50),
   ACTION               VARCHAR2(30),
   FILTERLEVEL          NUMBER,
   TYPE                 NUMBER,
   MEMO                 VARCHAR2(500)
);

/*==============================================================*/
/* Table: T_USER                                                */
/*==============================================================*/
create table T_USER  (
   ID                   NUMBER                          not null,
   USERNAME             VARCHAR2(30)                    not null,
   PASSWORD             VARCHAR2(50),
   USERTYPE             VARCHAR2(30),
   LEV                  VARCHAR2(10),
   STATUS               VARCHAR2(20),
   VALIDDATE            VARCHAR2(20),
   NAME                 VARCHAR2(30),
   CODE                 VARCHAR2(30),
   GENDER               VARCHAR2(6),
   constraint PK_T_USER primary key (ID)
);

/*==============================================================*/
/* Table: T_USER_MENU                                           */
/*==============================================================*/
create table T_USER_MENU  (
   EMPID                VARCHAR2(10)                    not null,
   NODEID               VARCHAR2(50)                    not null,
   NODENAME             VARCHAR2(100),
   PARENTID             VARCHAR2(50),
   LINK                 VARCHAR2(200),
   LINKMODE             NUMBER                         default 0 not null,
   ISFOLDER             VARCHAR2(1)                    default '0' not null,
   ISHIDED              VARCHAR2(1)                    default '0' not null,
   ORDERID              NUMBER                         default 0 not null,
   ISMENU               VARCHAR2(1)                    default '1' not null,
   FLAG                 VARCHAR2(1)                    default '0' not null,
   MEMO                 VARCHAR2(255),
   constraint SQL040616103947 primary key (EMPID, NODEID)
);

/*==============================================================*/
/* Table: T_USER_ROLE                                           */
/*==============================================================*/
create table T_USER_ROLE  (
   USER_ID              NUMBER,
   ROLE_ID              NUMBER,
   constraint FK_T_USER_R_REF foreign key (USER_ID)
         references T_USER (ID),
   constraint FK_T_USER_R_RE2 foreign key (ROLE_ID)
         references T_ROLE (ID)
);

/*==============================================================*/
/* Table: T_USER_STATUS                                         */
/*==============================================================*/
create table T_USER_STATUS  (
   "value"              VARCHAR2(100)                   not null,
   "key"                VARCHAR2(20)                    not null,
   constraint PK_T_USER_STATU primary key ("key")
);

/*==============================================================*/
/* Table: T_USER_USERTYPE                                       */
/*==============================================================*/
create table T_USER_USERTYPE  (
   "value"              VARCHAR2(100)                   not null,
   "key"                VARCHAR2(30)                    not null,
   constraint PK_T_USER_USERT primary key ("key")
);

/*==============================================================*/
/* Table: USEAGE_TYPE                                           */
/*==============================================================*/
create table USEAGE_TYPE  (
   TYPE_NUM             VARCHAR2(30)                    not null,
   USEAGE_TYPE          VARCHAR2(30),
   constraint PK_USEAGE_TYPE primary key (TYPE_NUM)
);

/*==============================================================*/
/* Table: IM_MEET_INFO                                          */
/*==============================================================*/
create table IM_MEET_INFO  (
   MEET_CODE            VARCHAR2(30)                    not null,
   MEET_NAME            VARCHAR2(20),
   TYPE_CODE            VARCHAR2(30),
   CONTAIN              NUMBER,
   EQUIP                VARCHAR2(60),
   ADDRESS              VARCHAR2(60),
   DESCRIPTION          VARCHAR2(60),
   MEET_STATUS          NUMBER,
   MANAGER              VARCHAR2(200),
   constraint PK_IM_MEET_INFO primary key (MEET_CODE),
   constraint FK_IM_MEET__REF foreign key (TYPE_CODE)
         references IM_MEET_TYPE (TYPE_CODE)
);

/*==============================================================*/
/* Table: IM_MEET_MANAGE                                        */
/*==============================================================*/
create table IM_MEET_MANAGE  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   TITLE                VARCHAR2(100),
   APPLYER              VARCHAR2(20),
   APPLY_TIME           DATE,
   BEGIN_TIME           DATE,
   END_TIME             DATE,
   MEET_CODE            VARCHAR2(30),
   MASTER               VARCHAR2(20),
   ATTENDS              VARCHAR2(1000),
   READERS              VARCHAR2(2000),
   MAIN_TOPIC           VARCHAR2(200),
   CHECK_FLAG           NUMBER                         default 0,
   CHECK_DATE           DATE,
   SUMMARY              CLOB,
   RECORDER             VARCHAR2(20),
   OPERATE_DATE         DATE,
   MEET_COST            NUMBER(18,2),
   REMARK               VARCHAR2(60),
   FLAG                 NUMBER                         default 1,
   BUILDING             NUMBER                         default 0,
   AUDITER              VARCHAR2(30),
   constraint PK_IM_MEET_MANA primary key (SERIAL_NUM),
   constraint FK_IM_MEET__RE2 foreign key (MEET_CODE)
         references IM_MEET_INFO (MEET_CODE)
);

/*==============================================================*/
/* Table: IM_MEET_NOTICE                                        */
/*==============================================================*/
create table IM_MEET_NOTICE  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   TITLE                VARCHAR2(100),
   MAIN_TOPIC           VARCHAR2(200),
   APPLYER              VARCHAR2(20),
   BEGIN_TIME           DATE,
   END_TIME             DATE,
   MEET_CODE            VARCHAR2(30),
   MASTER               VARCHAR2(20),
   ATTENDS              VARCHAR2(1000),
   MEET_PREPARE         VARCHAR2(600),
   NOTICE_CONTENT       VARCHAR2(60),
   NOTICE_TIME          DATE,
   constraint PK_IM_MEET_NOTI primary key (SERIAL_NUM),
   constraint FK_IM_MEET__RE3 foreign key (MEET_CODE)
         references IM_MEET_INFO (MEET_CODE)
);

/*==============================================================*/
/* Table: IM_NOTICE_FILE                                        */
/*==============================================================*/
create table IM_NOTICE_FILE  (
   FILE_NUM             VARCHAR2(30)                    not null,
   SERIAL_NUM           VARCHAR2(30)                    not null,
   FILE_PATH            VARCHAR2(255),
   FILE_NAME            VARCHAR2(255),
   constraint PK_IM_NOTICE_FI primary key (FILE_NUM),
   constraint FK_IM_NOTIC_REF foreign key (SERIAL_NUM)
         references IM_MEET_NOTICE (SERIAL_NUM)
);

/*==============================================================*/
/* Table: T_DEPARTMENT_PERSON                                   */
/*==============================================================*/
create table T_DEPARTMENT_PERSON  (
   DEPARTMENT_ID        NUMBER,
   ID                   NUMBER,
   constraint FK_T_DEPART_REF foreign key (ID)
         references T_USER (ID),
   constraint FK_T_DEPART_RE2 foreign key (DEPARTMENT_ID)
         references T_DEPARTMENT (ID)
);

/*==============================================================*/
/* Table: T_JOB_PERSON                                          */
/*==============================================================*/
create table T_JOB_PERSON  (
   JOB_ID               NUMBER,
   ID                   NUMBER,
   constraint FK_T_JOB_PE_REF foreign key (ID)
         references T_USER (ID),
   constraint FK_T_JOB_PE_RE2 foreign key (JOB_ID)
         references T_JOB (ID)
);

/*==============================================================*/
/* Table: T_PKICA                                               */
/*==============================================================*/
create table T_PKICA  (
   USER_ID              NUMBER                          not null,
   KEYLABEL             VARCHAR2(50),
   PUBKEY               BLOB,
   CERT                 BLOB,
   constraint PK_T_PKICA primary key (USER_ID),
   constraint FK_T_PKICA_REFE foreign key (USER_ID)
         references T_USER (ID)
);

/*==============================================================*/
/* Table: T_RESOPERATION                                        */
/*==============================================================*/
create table T_RESOPERATION  (
   ID                   NUMBER                          not null,
   NAME                 VARCHAR2(50),
   DESCRIPTION          VARCHAR2(100),
   RESOURCE_ID          NUMBER,
   CODE                 VARCHAR2(12),
   constraint PK_T_RESOPERATI primary key (ID),
   constraint FK_T_RESOPE_REF foreign key (RESOURCE_ID)
         references T_RESOURCE (ID)
);

/*==============================================================*/
/* Table: IM_SUMMARY_FILE                                       */
/*==============================================================*/
create table IM_SUMMARY_FILE  (
   FILE_NUM             VARCHAR2(30)                    not null,
   SERIAL_NUM           VARCHAR2(30)                    not null,
   FILE_PATH            VARCHAR2(255),
   FILE_NAME            VARCHAR2(255),
   constraint PK_IM_SUMMARY_F primary key (FILE_NUM),
   constraint FK_IM_SUMMA_REF foreign key (SERIAL_NUM)
         references IM_MEET_MANAGE (SERIAL_NUM)
);

/*==============================================================*/
/* Table: T_ROLE_PERMISSION                                     */
/*==============================================================*/
create table T_ROLE_PERMISSION  (
   ROLE_ID              NUMBER,
   RESOURCE_ID          NUMBER,
   RESOPERATION_ID      NUMBER,
   constraint FK_T_ROLE_P_REF foreign key (ROLE_ID)
         references T_ROLE (ID),
   constraint FK_T_ROLE_P_RE2 foreign key (RESOPERATION_ID)
         references T_RESOPERATION (ID),
   constraint FK_T_ROLE_P_RE3 foreign key (RESOURCE_ID)
         references T_RESOURCE (ID)
);

/*==============================================================*/
/* Table: IM_CAR_INFO                                           */
/*==============================================================*/
create table IM_CAR_INFO  (
   CAR_CODE             VARCHAR2(12)                    not null,
   YARD_CODE            VARCHAR2(30),
   CAR_TYPE             VARCHAR2(4),
   DRIVER               VARCHAR2(20),
   BUY_PRICE            NUMBER(18,2),
   BUY_DATE             DATE,
   BEGIN_DATE           DATE,
   ENGINE_CODE          VARCHAR2(20),
   USE_OIL              FLOAT,
   ADDRESS              VARCHAR2(60),
   CHECK_NUM            NUMBER,
   CAR_PICTURE          BLOB,
   STATUS_FLAG          CHAR,
   REMARK               VARCHAR2(60),
   constraint PK_IM_CAR_INFO primary key (CAR_CODE),
   constraint FK_IM_CAR_I_REF foreign key (CAR_TYPE)
         references IM_CAR_TYPE (CAR_TYPE)
);

/*==============================================================*/
/* Table: IM_USEOIL_INFO                                        */
/*==============================================================*/
create table IM_USEOIL_INFO  (
   CAR_CODE             VARCHAR2(12)                    not null,
   REGISTER_ID          NUMBER(6)                       not null,
   CHEER_DATE           DATE,
   YARD_NUN             NUMBER(18,2),
   MILEAGE_NUM          NUMBER(18,2),
   CHEER_QTY            FLOAT,
   MONEY                NUMBER(18,2),
   CHEER                VARCHAR2(20),
   REMARK               VARCHAR2(300),
   constraint SQL030925104626 primary key (CAR_CODE, REGISTER_ID),
   constraint FK_IM_USEOI_REF foreign key (CAR_CODE)
         references IM_CAR_INFO (CAR_CODE)
);

/*==============================================================*/
/* Index: SQL030925104626280                                    */
/*==============================================================*/
create unique index SQL030925104626280 on IM_USEOIL_INFO (
   REGISTER_ID ASC
);

/*==============================================================*/
/* Table: DO_CARDCASE                                           */
/*==============================================================*/
create table DO_CARDCASE  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   TYPE_CODE            VARCHAR2(30),
   CUSTOMER             VARCHAR2(20),
   NICKNAME             VARCHAR2(20),
   DUTY                 VARCHAR2(20),
   COMPANY              VARCHAR2(100),
   ADDRESS              VARCHAR2(60),
   POST_CODE            VARCHAR2(20),
   E_MAIL               VARCHAR2(30),
   PHONE                VARCHAR2(30),
   FAX                  VARCHAR2(30),
   MOBILE               VARCHAR2(15),
   HOME_PAGE            VARCHAR2(100),
   REMARK               VARCHAR2(200),
   constraint PK_DO_CARDCASE primary key (SERIAL_NUM),
   constraint FK_DO_CARDC_REF foreign key (TYPE_CODE)
         references DO_CARDCASE_TYPE (TYPE_CODE)
);

/*==============================================================*/
/* Table: DO_LOG_CONTENT                                        */
/*==============================================================*/
create table DO_LOG_CONTENT  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   TSERIAL_NUM          VARCHAR2(30)                    not null,
   LOG_CONTENT          VARCHAR2(255),
   REMARK               VARCHAR2(60),
   constraint PK_DO_LOG_CONTE primary key (SERIAL_NUM),
   constraint FK_DO_LOG_C_REF foreign key (TSERIAL_NUM)
         references DO_WORK_LOG (SERIAL_NUM)
);

/*==============================================================*/
/* Table: IS_CUSTOMER                                           */
/*==============================================================*/
create table IS_CUSTOMER  (
   CUSTOMER_CODE        VARCHAR2(20)                    not null,
   CUSTOMER_NAME        VARCHAR2(60),
   PHONE                VARCHAR2(30),
   FAX                  VARCHAR2(30),
   E_MAIL               VARCHAR2(30),
   HOME_PAGE            VARCHAR2(100),
   ADDRESS              VARCHAR2(60),
   POST_CODE            VARCHAR2(10),
   SECRET_LEVEL         NUMBER,
   REMARK               VARCHAR2(600),
   OPEN_BANK            VARCHAR2(40),
   ACCOUNT              VARCHAR2(30),
   FLAG                 NUMBER,
   TYPE_CODE            NUMBER,
   MEMORY               DATE,
   constraint PK_IS_CUSTOMER primary key (CUSTOMER_CODE),
   constraint FK_IS_CUSTO_RE2 foreign key (FLAG)
         references IS_CUSTOMER_STATUS (FLAG),
   constraint FK_IS_CUSTO_RE3 foreign key (TYPE_CODE)
         references IS_CUSTOMER_TYPE (TYPE_CODE)
);

/*==============================================================*/
/* Table: T_USER_PERMISSION                                     */
/*==============================================================*/
create table T_USER_PERMISSION  (
   USER_ID              NUMBER,
   RESOURCE_ID          NUMBER,
   RESOPERATION_ID      NUMBER,
   constraint FK_T_USER_P_REF foreign key (USER_ID)
         references T_USER (ID),
   constraint FK_T_USER_P_RE2 foreign key (RESOPERATION_ID)
         references T_RESOPERATION (ID),
   constraint FK_T_USER_P_RE3 foreign key (RESOURCE_ID)
         references T_RESOURCE (ID)
);

/*==============================================================*/
/* Table: DO_BBS_SBOARD                                         */
/*==============================================================*/
create table DO_BBS_SBOARD  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   TBOARD_SERIAL        VARCHAR2(30),
   SBOARD_NAME          VARCHAR2(100)                   not null,
   SBOARD_MASTER        VARCHAR2(20),
   ICON_FILE            VARCHAR2(255),
   SBOARD_NOTE          VARCHAR2(200),
   SBOARD_STATE         NUMBER,
   constraint SQL030906093643 primary key (SERIAL_NUM),
   constraint FK_DO_BBS_S_REF foreign key (TBOARD_SERIAL)
         references DO_BBS_TOPBOARD (SERIAL_NUM)
);

/*==============================================================*/
/* Table: IS_CUSTOMER_TOUCH                                     */
/*==============================================================*/
create table IS_CUSTOMER_TOUCH  (
   CUSTOMER             VARCHAR2(20)                    not null,
   CUSTOMER_CODE        VARCHAR2(20)                    not null,
   SEX_CODE             VARCHAR2(4),
   JOB                  VARCHAR2(20),
   LOVE                 VARCHAR2(30),
   WORK_PHONE           VARCHAR2(30),
   HOME_PHONE           VARCHAR2(30),
   MOBILE               VARCHAR2(15),
   E_MAIL               VARCHAR2(30),
   ADDRESS              VARCHAR2(60),
   REMARK               VARCHAR2(200),
   BIRTHDAY             DATE,
   constraint PK_IS_CUSTOMER_ primary key (CUSTOMER, CUSTOMER_CODE),
   constraint FK_IS_CUSTO_REF foreign key (CUSTOMER_CODE)
         references IS_CUSTOMER (CUSTOMER_CODE)
);

/*==============================================================*/
/* Table: IM_GOODS_STOCK                                        */
/*==============================================================*/
create table IM_GOODS_STOCK  (
   GOODS_CODE           VARCHAR2(20)                    not null,
   GOODS_NAME           VARCHAR2(30),
   TYPE_CODE            VARCHAR2(20),
   STANDARD             VARCHAR2(30),
   UNITS                VARCHAR2(10),
   PRICE                NUMBER(18,2),
   IN_QTY               NUMBER(18,2),
   OUT_QTY              NUMBER(18,2),
   UPDATE_TIME          DATE,
   ALARM_QTY            NUMBER(18,2),
   constraint SQL020926085828 primary key (GOODS_CODE),
   constraint FK_IM_GOODS_REF foreign key (TYPE_CODE)
         references IM_GOODS_TYPE (TYPE_CODE)
);

/*==============================================================*/
/* Table: USEAGEINFO                                            */
/*==============================================================*/
create table USEAGEINFO  (
   INFO_NUM             VARCHAR2(30)                    not null,
   USEAGE_NAME          VARCHAR2(30)                    not null,
   TYPE_NUM             VARCHAR2(30)                    not null,
   BUYODD_NUM           VARCHAR2(30),
   SPEC                 VARCHAR2(30),
   UNITS                VARCHAR2(30),
   CALL_NUM             NUMBER,
   AGV_PRICE            NUMBER(18,2),
   INT_NUM              NUMBER,
   OUT_NUM              NUMBER,
   CURRENT_STOCK        NUMBER,
   LAST_MODIFIED        DATE,
   constraint PK_USEAGEINFO primary key (INFO_NUM),
   constraint FK_USEAGEIN_TYP foreign key (TYPE_NUM)
         references USEAGE_TYPE (TYPE_NUM),
   constraint FK_USEAGEIN_INF foreign key (BUYODD_NUM)
         references BUYODDLIST (BUYODD_NUM)
);

/*==============================================================*/
/* Table: DO_BBS_TOPIC                                          */
/*==============================================================*/
create table DO_BBS_TOPIC  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   SBOARD_SERIAL        VARCHAR2(30),
   TOPIC                VARCHAR2(200),
   BBS_USER_CODE        VARCHAR2(20)                    not null,
   TOPIC_TYPE           NUMBER,
   TOPIC_USER           VARCHAR2(2000),
   APPEAR_DATE          DATE,
   TOPIC_ORDER          NUMBER                          not null,
   HIT_TIMES            NUMBER,
   ISBLOCK              NUMBER                         default 0,
   IS_SHOW              NUMBER                         default 0,
   constraint SQL030906101958 primary key (SERIAL_NUM),
   constraint FK_DO_BBS_T_RE3 foreign key (SBOARD_SERIAL)
         references DO_BBS_SBOARD (SERIAL_NUM)
);

/*==============================================================*/
/* Table: DO_BBS_TOPICLIST                                      */
/*==============================================================*/
create table DO_BBS_TOPICLIST  (
   SERIAL_NUM           VARCHAR2(30)                    not null,
   TOPIC_NUM            VARCHAR2(30)                    not null,
   BBS_USER_CODE        VARCHAR2(20)                    not null,
   CONTENT              VARCHAR2(3000),
   IP_ADDRESS           VARCHAR2(20),
   FILE_PATH            VARCHAR2(255),
   FILE_NAME            VARCHAR2(255),
   REPLY_DATE           DATE,
   AWOKE_FLAG           NUMBER                          not null,
   IS_SHOW              NUMBER                         default 0,
   constraint SQL03 primary key (SERIAL_NUM),
   constraint FK_DO_BBS_T_REF foreign key (TOPIC_NUM)
         references DO_BBS_TOPIC (SERIAL_NUM),
   constraint FK_DO_BBS_T_RE2 foreign key (BBS_USER_CODE)
         references DO_BBS_USER (BBS_USER_CODE)
);

/*==============================================================*/
/* Table: IM_REPAIR_APPLY                                       */
/*==============================================================*/
create table IM_REPAIR_APPLY  (
   CAR_CODE             VARCHAR2(12)                    not null,
   APPLY_ID             NUMBER(6)                       not null,
   APPLY_DATE           DATE,
   REPAIR_ITEM          VARCHAR2(60),
   REPAIR_RATE          NUMBER(18,2),
   APPRAISE_OPINION     VARCHAR2(200),
   APPRAISER            VARCHAR2(20),
   APPRAISE_DATE        DATE,
   EXAMINE_OPINION      VARCHAR2(200),
   EXAMINER             VARCHAR2(20),
   EXAMINE_DATE         DATE,
   constraint PK_IM_REPAIR_AP primary key (CAR_CODE, APPLY_ID),
   constraint FK_IM_REPAI_REF foreign key (CAR_CODE)
         references IM_CAR_INFO (CAR_CODE)
);

/*==============================================================*/
/* Table: IM_REPAIR_INFO                                        */
/*==============================================================*/
create table IM_REPAIR_INFO  (
   CAR_CODE             VARCHAR2(12)                    not null,
   REGISTER_ID          NUMBER(6)                       not null,
   REPAIR_ITEM          VARCHAR2(20),
   REPAIR_RATE          NUMBER(18,2),
   REPAIR_DATE          DATE,
   REMARK               VARCHAR2(60),
   constraint SQL020926101730 primary key (CAR_CODE, REGISTER_ID),
   constraint FK_IM_REPAI_RE2 foreign key (CAR_CODE)
         references IM_CAR_INFO (CAR_CODE)
);

/*==============================================================*/
/* Table: IM_INGOODS_DETAIL                                     */
/*==============================================================*/
create table IM_INGOODS_DETAIL  (
   BILL_NO              VARCHAR2(20)                    not null,
   ORDER_NO             NUMBER                          not null,
   GOODS_CODE           VARCHAR2(20)                    not null,
   TYPE_CODE            VARCHAR2(20)                    not null,
   STOCK_DATE           DATE,
   STOCK_QTY            NUMBER(18,2),
   STOCK_PRICE          NUMBER(18,2),
   constraint PK_IM_INGOODS_D primary key (BILL_NO, ORDER_NO, GOODS_CODE),
   constraint FK_IM_INGOO_RE3 foreign key (GOODS_CODE)
         references IM_GOODS_STOCK (GOODS_CODE),
   constraint FK_IM_INGOO_REF foreign key (TYPE_CODE)
         references IM_GOODS_TYPE (TYPE_CODE),
   constraint FK_IM_INGOO_RE2 foreign key (BILL_NO)
         references IM_GOODS_REGISTER (BILL_NO)
);

/*==============================================================*/
/* View: VIEW_BBS_TOPICLIST                                     */
/*==============================================================*/
create or replace view VIEW_BBS_TOPICLIST as
select
   nickname,
   user_icon,
   last_log_time,
   user_code,
   user_state,
   sex_code
from
   do_bbs_user u;



CREATE OR REPLACE VIEW COP.VIEW_BBS_TOPIC 
(
    SERIAL_NUM,
    SBOARD_SERIAL,
    TOPIC,
    NICKNAME,
    APPEAR_DATE,
    REPLY_CNT,
    HIT_TIMES,
    REPLY_TIME,
    TOPIC_TYPE,
    TOPIC_ORDER,
    ISBLOCK,
    IS_SHOW,
    REPLYER
)
AS
select distinct topic.*, u.nickname as replyer
  from do_bbs_user u,do_bbs_topiclist l, (
    select t.serial_num,sboard_serial,t.TOPIC,u.nickname,APPEAR_DATE,count(*)-
        1 as reply_cnt ,hit_times, max(l.REPLY_DATE) as reply_time,TOPIC_TYPE,
        TOPIC_ORDER,ISBLOCK,t.IS_SHOW
      from do_bbs_user u,do_bbs_topiclist l,do_bbs_topic t
      where t.serial_num = l.topic_num
        and u.bbs_user_code=t.bbs_user_code
      group by t.TOPIC,u.nickname,APPEAR_DATE,t.serial_num,sboard_serial,
          hit_times,TOPIC_TYPE,TOPIC_ORDER,ISBLOCK,t.IS_SHOW ) topic
  where u.bbs_user_code=l.bbs_user_code
    and l.reply_date=topic.reply_time
    and l.topic_num=topic.serial_num
/

