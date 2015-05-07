# Host: 127.0.0.1  (Version: 5.5.36)
# Date: 2014-11-25 18:11:15
# Generator: MySQL-Front 5.3  (Build 4.170)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "G_CONFIG"
#

DROP TABLE IF EXISTS `G_CONFIG`;
CREATE TABLE `G_CONFIG` (
  `ST_CODE` varchar(24) NOT NULL,
  `G_TABLE` varchar(64) DEFAULT NULL,
  `FILE_NAME` varchar(64) NOT NULL,
  `FILE_PATH` varchar(256) DEFAULT NULL,
  `UPD_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ST_CODE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "G_CONFIG"
#

