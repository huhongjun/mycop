-- MySQL dump 10.9
--
-- Host: localhost    Database: cop
-- ------------------------------------------------------
-- Server version	4.1.11-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_resource`
--

DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `ID` int(11) NOT NULL default '0',
  `NAME` varchar(50) default NULL,
  `DESCRIPTION` varchar(100) default NULL,
  `RESOURCETYPE` varchar(30) default NULL,
  `LINK` varchar(200) default NULL,
  `PARENT_ID` int(11) default NULL,
  `TARGET` char(1) default NULL,
  `CODE` varchar(100) NOT NULL default '',
  `ORDERID` int(11) NOT NULL default '0',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ID` int(11) NOT NULL default '0',
  `NAME` varchar(50) NOT NULL default '',
  `DESCRIPTION` varchar(100) default NULL,
  `ROLETYPE` varchar(30) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `t_level`
--

DROP TABLE IF EXISTS `t_level`;
CREATE TABLE `t_level` (
  `id` int(11) NOT NULL default '0',
  `code` varchar(30) default NULL,
  `name` varchar(30) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `t_job`
--

DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job` (
  `ID` int(11) NOT NULL default '0',
  `NAME` varchar(50) default NULL,
  `DESCRIPTION` varchar(100) default NULL,
  `DEPARTMENT_ID` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

