# Host: 127.0.0.1  (Version: 5.5.36)
# Date: 2014-11-25 18:06:43
# Generator: MySQL-Front 5.3  (Build 4.170)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "D_DIMTYPE"
#

DROP TABLE IF EXISTS `D_DIMTYPE`;
CREATE TABLE `D_DIMTYPE` (
  `DIMTYPE_ID` int(10) unsigned NOT NULL,
  `DIMTYPE_NAME` varchar(20) NOT NULL,
  `DIMTYPE_CODE` varchar(20) DEFAULT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`DIMTYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "D_DIMTYPE"
#

INSERT INTO `D_DIMTYPE` VALUES (1001,'行业','s_i','新浪数据'),(1002,'地域','s_a','新浪数据'),(1003,'概念','s_c','新浪数据'),(1004,'市场','s_t','新浪数据'),(2001,'农、林、牧、渔业(A)','A',NULL),(2002,'采矿业(B)','B',NULL),(2003,'制造业(C)','C',NULL),(2004,'电力、热力、燃气及水生产和供应业(D)','D',NULL),(2005,'建筑业(E)','E',NULL),(2006,'批发和零售业(F)','F',NULL),(2007,'交通运输、仓储和邮政业(G)','G',NULL),(2008,'住宿和餐饮业(H)','H',NULL),(2009,'信息传输、软件和信息技术服务业(I)','I',NULL),(2010,'金融业(J)','J',NULL),(2011,'房地产业(K)','K',NULL),(2012,'租赁和商务服务业(L)','L',NULL),(2013,'科学研究和技术服务业(M)','M',NULL),(2014,'水利、环境和公共设施管理业(N)','N',NULL),(2015,'教育(P)','P',NULL),(2016,'卫生和社会工作(Q)','Q',NULL),(2017,'文化、体育和娱乐业(R)','R',NULL),(2018,'综合(S)','S',NULL);
