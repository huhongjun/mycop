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