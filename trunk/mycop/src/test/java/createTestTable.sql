/*
SQLyog Enterprise v4.07
Host - 4.1.11-nt : Database - cop
*********************************************************************
Server version : 4.1.11-nt
*/

/*Table structure for table `test` - mysql */

drop table if exists `test`;

CREATE TABLE `test` (
  `Username` varchar(20) default NULL,
  `Price` decimal(10,0) default NULL,
  `Amt` decimal(10,0) default NULL,
  `Qty` decimal(10,0) default NULL,
  `Col1` varchar(30) default NULL,
  `Bdate` date default NULL,
  `Etime` time default NULL,
  `id` int(11) NOT NULL auto_increment,
  `userid` varchar(10) default NULL,
  `memo` varchar(30) default NULL,
  `num` decimal(10,0) default NULL,
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
