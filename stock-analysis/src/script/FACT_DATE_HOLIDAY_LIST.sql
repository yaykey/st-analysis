﻿# Host: 127.0.0.1  (Version: 5.5.36)
# Date: 2014-11-25 18:08:38
# Generator: MySQL-Front 5.3  (Build 4.170)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "FACT_DATE_HOLIDAY_LIST"
#

DROP TABLE IF EXISTS `FACT_DATE_HOLIDAY_LIST`;
CREATE TABLE `FACT_DATE_HOLIDAY_LIST` (
  `FESTIVAL` varchar(10) NOT NULL,
  `DATE` varchar(10) NOT NULL,
  `STATUS` int(10) unsigned NOT NULL,
  PRIMARY KEY (`FESTIVAL`,`DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "FACT_DATE_HOLIDAY_LIST"
#

INSERT INTO `FACT_DATE_HOLIDAY_LIST` VALUES ('2011-01-01','2011-01-01',1),('2011-01-01','2011-01-02',1),('2011-01-01','2011-01-03',1),('2011-02-03','2011-01-30',2),('2011-02-03','2011-02-02',1),('2011-02-03','2011-02-03',1),('2011-02-03','2011-02-04',1),('2011-02-03','2011-02-05',1),('2011-02-03','2011-02-06',1),('2011-02-03','2011-02-07',1),('2011-02-03','2011-02-08',1),('2011-02-03','2011-02-12',2),('2011-04-05','2011-04-02',2),('2011-04-05','2011-04-03',1),('2011-04-05','2011-04-04',1),('2011-04-05','2011-04-05',1),('2011-05-01','2011-04-30',1),('2011-05-01','2011-05-01',1),('2011-05-01','2011-05-02',1),('2011-06-06','2011-06-04',1),('2011-06-06','2011-06-05',1),('2011-06-06','2011-06-06',1),('2011-09-12','2011-09-10',1),('2011-09-12','2011-09-11',1),('2011-09-12','2011-09-12',1),('2011-10-01','2011-10-01',1),('2011-10-01','2011-10-02',1),('2011-10-01','2011-10-03',1),('2011-10-01','2011-10-04',1),('2011-10-01','2011-10-05',1),('2011-10-01','2011-10-06',1),('2011-10-01','2011-10-07',1),('2011-10-01','2011-10-08',2),('2011-10-01','2011-10-09',2),('2012-01-01','2011-12-31',2),('2012-01-01','2012-01-01',1),('2012-01-01','2012-01-02',1),('2012-01-01','2012-01-03',1),('2012-01-23','2012-01-21',2),('2012-01-23','2012-01-22',1),('2012-01-23','2012-01-23',1),('2012-01-23','2012-01-24',1),('2012-01-23','2012-01-25',1),('2012-01-23','2012-01-26',1),('2012-01-23','2012-01-27',1),('2012-01-23','2012-01-28',1),('2012-01-23','2012-01-29',2),('2012-04-04','2012-03-31',2),('2012-04-04','2012-04-01',2),('2012-04-04','2012-04-02',1),('2012-04-04','2012-04-03',1),('2012-04-04','2012-04-04',1),('2012-05-01','2012-04-28',2),('2012-05-01','2012-04-29',1),('2012-05-01','2012-04-30',1),('2012-05-01','2012-05-01',1),('2012-05-01','2012-05-02',2),('2012-06-23','2012-06-22',1),('2012-06-23','2012-06-23',1),('2012-06-23','2012-06-24',1),('2012-09-30','2012-09-29',2),('2012-09-30','2012-09-30',1),('2012-09-30','2012-10-01',1),('2012-09-30','2012-10-02',1),('2012-09-30','2012-10-03',1),('2012-09-30','2012-10-04',1),('2012-09-30','2012-10-05',1),('2012-09-30','2012-10-06',1),('2012-09-30','2012-10-07',1),('2012-09-30','2012-10-08',2),('2012-10-01','2012-09-29',2),('2012-10-01','2012-09-30',1),('2012-10-01','2012-10-01',1),('2012-10-01','2012-10-02',1),('2012-10-01','2012-10-03',1),('2012-10-01','2012-10-04',1),('2012-10-01','2012-10-05',1),('2012-10-01','2012-10-06',1),('2012-10-01','2012-10-07',1),('2012-10-01','2012-10-08',2),('2013-01-01','2013-01-01',1),('2013-01-01','2013-01-02',1),('2013-01-01','2013-01-03',1),('2013-01-01','2013-01-05',2),('2013-01-01','2013-01-06',2),('2013-02-10','2013-02-09',1),('2013-02-10','2013-02-10',1),('2013-02-10','2013-02-11',1),('2013-02-10','2013-02-12',1),('2013-02-10','2013-02-13',1),('2013-02-10','2013-02-14',1),('2013-02-10','2013-02-15',1),('2013-02-10','2013-02-16',2),('2013-02-10','2013-02-17',2),('2013-04-04','2013-04-04',1),('2013-04-04','2013-04-05',1),('2013-04-04','2013-04-06',1),('2013-04-04','2013-04-07',2),('2013-05-01','2013-04-27',2),('2013-05-01','2013-04-28',2),('2013-05-01','2013-04-29',1),('2013-05-01','2013-04-30',1),('2013-05-01','2013-05-01',1),('2013-06-12','2013-06-08',2),('2013-06-12','2013-06-09',2),('2013-06-12','2013-06-10',1),('2013-06-12','2013-06-11',1),('2013-06-12','2013-06-12',1),('2013-09-19','2013-09-19',1),('2013-09-19','2013-09-20',1),('2013-09-19','2013-09-21',1),('2013-09-19','2013-09-22',2),('2013-10-01','2013-09-29',2),('2013-10-01','2013-10-01',1),('2013-10-01','2013-10-02',1),('2013-10-01','2013-10-03',1),('2013-10-01','2013-10-04',1),('2013-10-01','2013-10-05',1),('2013-10-01','2013-10-06',1),('2013-10-01','2013-10-07',1),('2013-10-01','2013-10-12',2),('2014-01-01','2014-01-01',1),('2014-01-30','2014-01-30',0),('2014-01-31','2014-01-26',2),('2014-01-31','2014-01-31',1),('2014-01-31','2014-02-01',1),('2014-01-31','2014-02-02',1),('2014-01-31','2014-02-03',1),('2014-01-31','2014-02-04',1),('2014-01-31','2014-02-05',1),('2014-01-31','2014-02-06',1),('2014-01-31','2014-02-08',2),('2014-04-05','2014-04-05',1),('2014-04-05','2014-04-06',1),('2014-04-05','2014-04-07',1),('2014-05-01','2014-05-01',1),('2014-05-01','2014-05-02',1),('2014-05-01','2014-05-03',1),('2014-05-01','2014-05-04',2),('2014-06-02','2014-05-31',1),('2014-06-02','2014-06-01',1),('2014-06-02','2014-06-02',1),('2014-09-08','2014-09-06',1),('2014-09-08','2014-09-07',1),('2014-09-08','2014-09-08',1),('2014-10-01','2014-09-28',2),('2014-10-01','2014-10-01',1),('2014-10-01','2014-10-02',1),('2014-10-01','2014-10-03',1),('2014-10-01','2014-10-04',1),('2014-10-01','2014-10-05',1),('2014-10-01','2014-10-06',1),('2014-10-01','2014-10-07',1),('2014-10-01','2014-10-11',2);
