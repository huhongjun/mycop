drop table if exists qiqu_book;

/*==============================================================*/
/* Table: qiqu_book                                  */
/*==============================================================*/
create table qiqu_book
(
	bookid	 VARCHAR(20)  not null ,
	bookname	 VARCHAR(20) ,
	publisher	 VARCHAR(20) ,
	deptid	 VARCHAR(20) ,
   primary key (bookid)
);


drop table if exists qiqu_user;

/*==============================================================*/
/* Table: qiqu_user                                  */
/*==============================================================*/
create table qiqu_user
(
	id	 INT(11)  not null ,
	name	 VARCHAR(20) ,
	email	 VARCHAR(20) ,
   primary key (id)
);


