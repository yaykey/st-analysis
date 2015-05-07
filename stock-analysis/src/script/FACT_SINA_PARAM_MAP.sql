# Host: 127.0.0.1  (Version: 5.5.36)
# Date: 2014-11-25 18:10:55
# Generator: MySQL-Front 5.3  (Build 4.170)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "FACT_SINA_PARAM_MAP"
#

DROP TABLE IF EXISTS `FACT_SINA_PARAM_MAP`;
CREATE TABLE `FACT_SINA_PARAM_MAP` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `s_i` varchar(20) DEFAULT NULL,
  `s_a` varchar(20) DEFAULT NULL,
  `s_c` varchar(20) DEFAULT NULL,
  `s_t` varchar(20) DEFAULT NULL,
  `p` int(10) unsigned DEFAULT NULL,
  `IS_VALIDITY` tinyint(1) DEFAULT NULL COMMENT '链接是否有效,查看是否有数据',
  `IS_USE` tinyint(1) DEFAULT NULL COMMENT '是否使用',
  `UPD_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `IDX_FACT_SINA_PARAM_MAP_s` (`s_i`,`s_a`,`s_c`,`s_t`),
  UNIQUE KEY `IDX_FACT_SINA_PARAM_MAP_P` (`s_i`,`s_a`,`s_c`,`s_t`,`p`)
) ENGINE=InnoDB AUTO_INCREMENT=8977567 DEFAULT CHARSET=utf8;
