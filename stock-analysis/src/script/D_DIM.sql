﻿# Host: 127.0.0.1  (Version: 5.5.36)
# Date: 2014-11-25 18:06:29
# Generator: MySQL-Front 5.3  (Build 4.170)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "D_DIM"
#

DROP TABLE IF EXISTS `D_DIM`;
CREATE TABLE `D_DIM` (
  `DIMTYPE_ID` int(10) unsigned NOT NULL,
  `DIMTYPE_NAME` varchar(30) NOT NULL,
  `DIM_ID` int(10) unsigned NOT NULL,
  `DIM_CODE` varchar(20) NOT NULL,
  `DIM_NAME` varchar(30) NOT NULL,
  `UPD_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`DIMTYPE_ID`,`DIM_ID`),
  UNIQUE KEY `IDX_D_DIM_DIM_ID` (`DIM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "D_DIM"
#

INSERT INTO `D_DIM` VALUES (1001,'行业',1001001,'new_swzz','生物制药','2014-11-20 13:01:38'),(1001,'行业',1001002,'new_qtxy','其它行业','2014-11-20 13:01:38'),(1001,'行业',1001003,'new_fdc','房地产','2014-11-20 13:01:38'),(1001,'行业',1001004,'new_jrhy','金融行业','2014-11-20 13:01:38'),(1001,'行业',1001005,'new_zhhy','综合行业','2014-11-20 13:01:38'),(1001,'行业',1001006,'new_syhy','石油行业','2014-11-20 13:01:38'),(1001,'行业',1001007,'new_ysjs','有色金属','2014-11-20 13:01:38'),(1001,'行业',1001008,'new_zzhy','造纸行业','2014-11-20 13:01:38'),(1001,'行业',1001009,'new_ysbz','印刷包装','2014-11-20 13:01:38'),(1001,'行业',1001010,'new_yqyb','仪器仪表','2014-11-20 13:01:38'),(1001,'行业',1001011,'new_ylqx','医疗器械','2014-11-20 13:01:38'),(1001,'行业',1001012,'new_wzwm','物资外贸','2014-11-20 13:01:38'),(1001,'行业',1001013,'new_tchy','陶瓷行业','2014-11-20 13:01:38'),(1001,'行业',1001014,'new_slzp','塑料制品','2014-11-20 13:01:38'),(1001,'行业',1001015,'new_snhy','水泥行业','2014-11-20 13:01:38'),(1001,'行业',1001016,'new_sphy','食品行业','2014-11-20 13:01:38'),(1001,'行业',1001017,'new_sybh','商业百货','2014-11-20 13:01:38'),(1001,'行业',1001018,'new_qczz','汽车制造','2014-11-20 13:01:38'),(1001,'行业',1001019,'new_nyhf','农药化肥','2014-11-20 13:01:38'),(1001,'行业',1001020,'new_nlmy','农林牧渔','2014-11-20 13:01:38'),(1001,'行业',1001021,'new_ljhy','酿酒行业','2014-11-20 13:01:38'),(1001,'行业',1001022,'new_mtc','摩托车','2014-11-20 13:01:38'),(1001,'行业',1001023,'new_mthy','煤炭行业','2014-11-20 13:01:38'),(1001,'行业',1001024,'new_kfq','开发区','2014-11-20 13:01:38'),(1001,'行业',1001025,'new_jdly','酒店旅游','2014-11-20 13:01:38'),(1001,'行业',1001026,'new_jtys','交通运输','2014-11-20 13:01:38'),(1001,'行业',1001027,'new_jzjc','建筑建材','2014-11-20 13:01:38'),(1001,'行业',1001028,'new_jjhy','家具行业','2014-11-20 13:01:38'),(1001,'行业',1001029,'new_jdhy','家电行业','2014-11-20 13:01:38'),(1001,'行业',1001030,'new_jxhy','机械行业','2014-11-20 13:01:38'),(1001,'行业',1001031,'new_hbhy','环保行业','2014-11-20 13:01:38'),(1001,'行业',1001032,'new_hqhy','化纤行业','2014-11-20 13:01:38'),(1001,'行业',1001033,'new_hghy','化工行业','2014-11-20 13:01:38'),(1001,'行业',1001034,'new_gsgq','供水供气','2014-11-20 13:01:38'),(1001,'行业',1001035,'new_glql','公路桥梁','2014-11-20 13:01:38'),(1001,'行业',1001036,'new_gthy','钢铁行业','2014-11-20 13:01:38'),(1001,'行业',1001037,'new_fzxl','服装鞋类','2014-11-20 13:01:38'),(1001,'行业',1001038,'new_fjzz','飞机制造','2014-11-20 13:01:38'),(1001,'行业',1001039,'new_fzhy','纺织行业','2014-11-20 13:01:38'),(1001,'行业',1001040,'new_fzjx','纺织机械','2014-11-20 13:01:38'),(1001,'行业',1001041,'new_fdsb','发电设备','2014-11-20 13:01:38'),(1001,'行业',1001042,'new_dzxx','电子信息','2014-11-20 13:01:38'),(1001,'行业',1001043,'new_dzqj','电子器件','2014-11-20 13:01:38'),(1001,'行业',1001044,'new_dqhy','电器行业','2014-11-20 13:01:38'),(1001,'行业',1001045,'new_dlhy','电力行业','2014-11-20 13:01:38'),(1001,'行业',1001046,'new_cbzz','船舶制造','2014-11-20 13:01:38'),(1001,'行业',1001047,'new_cmyl','传媒娱乐','2014-11-20 13:01:38'),(1001,'行业',1001048,'new_blhy','玻璃行业','2014-11-20 13:01:38'),(1002,'地域',1002001,'diyu_1100','北京','2014-11-20 13:01:56'),(1002,'地域',1002002,'diyu_1200','天津','2014-11-20 13:01:56'),(1002,'地域',1002003,'diyu_1300','河北','2014-11-20 13:01:56'),(1002,'地域',1002004,'diyu_1400','山西','2014-11-20 13:01:56'),(1002,'地域',1002005,'diyu_1500','内蒙古','2014-11-20 13:01:56'),(1002,'地域',1002006,'diyu_2100','辽宁','2014-11-20 13:01:56'),(1002,'地域',1002007,'diyu_2200','吉林','2014-11-20 13:01:56'),(1002,'地域',1002008,'diyu_2300','黑龙江','2014-11-20 13:01:56'),(1002,'地域',1002009,'diyu_3100','上海','2014-11-20 13:01:56'),(1002,'地域',1002010,'diyu_3200','江苏','2014-11-20 13:01:56'),(1002,'地域',1002011,'diyu_3300','浙江','2014-11-20 13:01:56'),(1002,'地域',1002012,'diyu_3400','安徽','2014-11-20 13:01:56'),(1002,'地域',1002013,'diyu_3500','福建','2014-11-20 13:01:56'),(1002,'地域',1002014,'diyu_3600','江西','2014-11-20 13:01:56'),(1002,'地域',1002015,'diyu_3700','山东','2014-11-20 13:01:56'),(1002,'地域',1002016,'diyu_4100','河南','2014-11-20 13:01:56'),(1002,'地域',1002017,'diyu_4200','湖北','2014-11-20 13:01:56'),(1002,'地域',1002018,'diyu_4300','湖南','2014-11-20 13:01:56'),(1002,'地域',1002019,'diyu_4400','广东','2014-11-20 13:01:56'),(1002,'地域',1002020,'diyu_4401','广州','2014-11-20 13:01:56'),(1002,'地域',1002021,'diyu_4410','深圳','2014-11-20 13:01:56'),(1002,'地域',1002022,'diyu_4500','广西','2014-11-20 13:01:56'),(1002,'地域',1002023,'diyu_4600','海南','2014-11-20 13:01:56'),(1002,'地域',1002024,'diyu_5100','四川','2014-11-20 13:01:56'),(1002,'地域',1002025,'diyu_5200','贵州','2014-11-20 13:01:56'),(1002,'地域',1002026,'diyu_5300','云南','2014-11-20 13:01:56'),(1002,'地域',1002027,'diyu_5400','西藏','2014-11-20 13:01:56'),(1002,'地域',1002028,'diyu_5500','重庆','2014-11-20 13:01:56'),(1002,'地域',1002029,'diyu_6100','陕西','2014-11-20 13:01:56'),(1002,'地域',1002030,'diyu_6200','甘肃','2014-11-20 13:01:56'),(1002,'地域',1002031,'diyu_6300','青海','2014-11-20 13:01:56'),(1002,'地域',1002032,'diyu_6400','宁夏','2014-11-20 13:01:56'),(1002,'地域',1002033,'diyu_6500','新疆','2014-11-20 13:01:56'),(1003,'概念',1003001,'gn_hssl','海上丝路','2014-11-20 13:02:23'),(1003,'概念',1003002,'gn_algn','阿里概念','2014-11-20 13:02:23'),(1003,'概念',1003003,'gn_jygn','基因概念','2014-11-20 13:02:23'),(1003,'概念',1003004,'gn_yga','粤港澳','2014-11-20 13:02:23'),(1003,'概念',1003005,'gn_gcrj','国产软件','2014-11-20 13:02:23'),(1003,'概念',1003006,'gn_jycx','基因测序','2014-11-20 13:02:23'),(1003,'概念',1003007,'gn_dsgn','电商概念','2014-11-20 13:02:23'),(1003,'概念',1003008,'gn_jyxp','基因芯片','2014-11-20 13:02:23'),(1003,'概念',1003009,'gn_jjj','京津冀','2014-11-20 13:02:23'),(1003,'概念',1003010,'gn_cgl','草甘膦','2014-11-20 13:02:23'),(1003,'概念',1003011,'gn_rldc','燃料电池','2014-11-20 13:02:23'),(1003,'概念',1003012,'gn_sqrl','氢燃料','2014-11-20 13:02:23'),(1003,'概念',1003013,'gn_sgqgg','国企改革','2014-11-20 13:02:23'),(1003,'概念',1003014,'gn_cdgn','超导概念','2014-11-20 13:02:23'),(1003,'概念',1003015,'gn_znjj','智能家居','2014-11-20 13:02:23'),(1003,'概念',1003016,'gn_lbs','蓝宝石','2014-11-20 13:02:23'),(1003,'概念',1003017,'gn_fn','风能','2014-11-20 13:02:23'),(1003,'概念',1003018,'gn_znjq','智能机器','2014-11-20 13:02:23'),(1003,'概念',1003019,'gn_sygg','水域改革','2014-11-20 13:02:23'),(1003,'概念',1003020,'gn_stny','生态农业','2014-11-20 13:02:23'),(1003,'概念',1003021,'gn_tsl','特斯拉','2014-11-20 13:02:23'),(1003,'概念',1003022,'gn_zjqrgn','机器人概念','2014-11-20 13:02:23'),(1003,'概念',1003023,'gn_mtjzm','天津自贸','2014-11-20 13:02:23'),(1003,'概念',1003024,'gn_xxaq','信息安全','2014-11-20 13:02:23'),(1003,'概念',1003025,'gn_yqgg','油气改革','2014-11-20 13:02:23'),(1003,'概念',1003026,'gn_O2Oms','O2O模式','2014-11-20 13:02:23'),(1003,'概念',1003027,'gn_myyy','民营医院','2014-11-20 13:02:23'),(1003,'概念',1003028,'gn_bcgn','博彩概念','2014-11-20 13:02:23'),(1003,'概念',1003029,'gn_ylgn','养老概念','2014-11-20 13:02:23'),(1003,'概念',1003030,'gn_tygn','体育概念','2014-11-20 13:02:23'),(1003,'概念',1003031,'gn_myyx','民营银行','2014-11-20 13:02:23'),(1003,'概念',1003032,'gn_sczl','丝绸之路','2014-11-20 13:02:23'),(1003,'概念',1003033,'gn_ytgn','婴童概念','2014-11-20 13:02:23'),(1003,'概念',1003034,'gn_gzzm','广东自贸','2014-11-20 13:02:23'),(1003,'概念',1003035,'gn_shzm','上海自贸','2014-11-20 13:02:23'),(1003,'概念',1003036,'gn_hljr','互联金融','2014-11-20 13:02:23'),(1003,'概念',1003037,'gn_dyzm','东亚自贸','2014-11-20 13:02:23'),(1003,'概念',1003038,'gn_wlyx','网络游戏','2014-11-20 13:02:23'),(1003,'概念',1003039,'gn_swzn','生物质能','2014-11-20 13:02:23'),(1003,'概念',1003040,'gn_zncd','智能穿戴','2014-11-20 13:02:23'),(1003,'概念',1003041,'gn_qhgn','前海概念','2014-11-20 13:02:23'),(1003,'概念',1003042,'gn_lszm','绿色照明','2014-11-20 13:02:23'),(1003,'概念',1003043,'gn_jaz','聚氨酯','2014-11-20 13:02:24'),(1003,'概念',1003044,'gn_tdlz','土地流转','2014-11-20 13:02:24'),(1003,'概念',1003045,'gn_fszl','风沙治理','2014-11-20 13:02:24'),(1003,'概念',1003046,'gn_smx','石墨烯','2014-11-20 13:02:24'),(1003,'概念',1003047,'gn_4Ggn','4G概念','2014-11-20 13:02:24'),(1003,'概念',1003048,'gn_cdz','充电桩','2014-11-20 13:02:24'),(1003,'概念',1003049,'gn_fngn','风能概念','2014-11-20 13:02:24'),(1003,'概念',1003050,'gn_kqzl','空气治理','2014-11-20 13:02:24'),(1003,'概念',1003051,'gn_znjt','智能交通','2014-11-20 13:02:24'),(1003,'概念',1003052,'gn_jzjn','建筑节能','2014-11-20 13:02:24'),(1003,'概念',1003053,'gn_affw','安防服务','2014-11-20 13:02:24'),(1003,'概念',1003054,'gn_swyz','生物育种','2014-11-20 13:02:24'),(1003,'概念',1003055,'gn_zzgn','重组概念','2014-11-20 13:02:24'),(1003,'概念',1003056,'gn_pggn','苹果概念','2014-11-20 13:02:24'),(1003,'概念',1003057,'gn_zmgn','摘帽概念','2014-11-20 13:02:24'),(1003,'概念',1003058,'gn_drn','地热能','2014-11-20 13:02:24'),(1003,'概念',1003059,'gn_nmgh','内贸规划','2014-11-20 13:02:24'),(1003,'概念',1003060,'gn_txw','碳纤维','2014-11-20 13:02:24'),(1003,'概念',1003061,'gn_hsdh','海水淡化','2014-11-20 13:02:24'),(1003,'概念',1003062,'gn_3Ddy','3D打印','2014-11-20 13:02:24'),(1003,'概念',1003063,'gn_swrl','生物燃料','2014-11-20 13:02:24'),(1003,'概念',1003064,'gn_ssgn','三沙概念','2014-11-20 13:02:24'),(1003,'概念',1003065,'gn_tmj','图们江','2014-11-20 13:02:24'),(1003,'概念',1003066,'gn_zSTg','准ST股','2014-11-20 13:02:24'),(1003,'概念',1003067,'gn_yjyj','业绩预降','2014-11-20 13:02:24'),(1003,'概念',1003068,'gn_yjys','业绩预升','2014-11-20 13:02:24'),(1003,'概念',1003069,'gn_szql','送转潜力','2014-11-20 13:02:24'),(1003,'概念',1003070,'gn_scp','奢侈品','2014-11-20 13:02:24'),(1003,'概念',1003071,'gn_gxbj','高校背景','2014-11-20 13:02:24'),(1003,'概念',1003072,'gn_spaq','食品安全','2014-11-20 13:02:24'),(1003,'概念',1003073,'gn_IPV6gn','IPV6概念','2014-11-20 13:02:24'),(1003,'概念',1003074,'gn_kdts','宽带提速','2014-11-20 13:02:24'),(1003,'概念',1003075,'gn_jnhb','节能环保','2014-11-20 13:02:24'),(1003,'概念',1003076,'gn_whzx','文化振兴','2014-11-20 13:02:24'),(1003,'概念',1003077,'gn_swym','生物疫苗','2014-11-20 13:02:24'),(1003,'概念',1003078,'gn_sgn','陕甘宁','2014-11-20 13:02:24'),(1003,'概念',1003079,'gn_zmq','自贸区','2014-11-20 13:02:24'),(1003,'概念',1003080,'gn_rhmy','日韩贸易','2014-11-20 13:02:24'),(1003,'概念',1003081,'gn_yyq','页岩气','2014-11-20 13:02:24'),(1003,'概念',1003082,'gn_jrgg','金融改革','2014-11-20 13:02:24'),(1003,'概念',1003083,'gn_skgn','涉矿概念','2014-11-20 13:02:24'),(1003,'概念',1003084,'gn_bzf','保障房','2014-11-20 13:02:24'),(1003,'概念',1003085,'gn_hgzb','海工装备','2014-11-20 13:02:24'),(1003,'概念',1003086,'gn_xsb','新三板','2014-11-20 13:02:24'),(1003,'概念',1003087,'gn_dzzf','电子支付','2014-11-20 13:02:24'),(1003,'概念',1003088,'gn_yjs','云计算','2014-11-20 13:02:24'),(1003,'概念',1003089,'gn_wxdh','卫星导航','2014-11-20 13:02:24'),(1003,'概念',1003090,'gn_tyn','太阳能','2014-11-20 13:02:24'),(1003,'概念',1003091,'gn_wzbj','外资背景','2014-11-20 13:02:24'),(1003,'概念',1003092,'gn_wjqy','皖江区域','2014-11-20 13:02:24'),(1003,'概念',1003093,'gn_czt','长株潭','2014-11-20 13:02:24'),(1003,'概念',1003094,'gn_ztss','整体上市','2014-11-20 13:02:24'),(1003,'概念',1003095,'gn_byjj','本月解禁','2014-11-20 13:02:24'),(1003,'概念',1003096,'gn_sljs','水利建设','2014-11-20 13:02:24'),(1003,'概念',1003097,'gn_cmp','触摸屏','2014-11-20 13:02:24'),(1003,'概念',1003098,'gn_jrcg','金融参股','2014-11-20 13:02:24'),(1003,'概念',1003099,'gn_sbzc','社保重仓','2014-11-20 13:02:24'),(1003,'概念',1003100,'gn_bxzc','保险重仓','2014-11-20 13:02:24'),(1003,'概念',1003101,'gn_xtzc','信托重仓','2014-11-20 13:02:24'),(1003,'概念',1003102,'gn_qszc','券商重仓','2014-11-20 13:02:24'),(1003,'概念',1003103,'gn_QFIIzc','QFII重仓','2014-11-20 13:02:24'),(1003,'概念',1003104,'gn_hdhn','核电核能','2014-11-20 13:02:24'),(1003,'概念',1003105,'gn_xtyc','稀土永磁','2014-11-20 13:02:24'),(1003,'概念',1003106,'gn_ldc','锂电池','2014-11-20 13:02:24'),(1003,'概念',1003107,'gn_djg','多晶硅','2014-11-20 13:02:24'),(1003,'概念',1003108,'gn_jxzs','精选指数','2014-11-20 13:02:24'),(1003,'概念',1003109,'gn_fcss','分拆上市','2014-11-20 13:02:24'),(1003,'概念',1003110,'gn_whgh','武汉规划','2014-11-20 13:02:24'),(1003,'概念',1003111,'gn_cjxj','超级细菌','2014-11-20 13:02:24'),(1003,'概念',1003112,'gn_swrh','三网融合','2014-11-20 13:02:24'),(1003,'概念',1003113,'gn_rzrq','融资融券','2014-11-20 13:02:24'),(1003,'概念',1003114,'gn_xqzy','稀缺资源','2014-11-20 13:02:24'),(1003,'概念',1003115,'gn_hkzz','含可转债','2014-11-20 13:02:24'),(1003,'概念',1003116,'gn_cxg','次新股','2014-11-20 13:02:24'),(1003,'概念',1003117,'gn_hBg','含B股','2014-11-20 13:02:24'),(1003,'概念',1003118,'gn_hHg','含H股','2014-11-20 13:02:24'),(1003,'概念',1003119,'gn_qzl','权证类','2014-11-20 13:02:24'),(1003,'概念',1003120,'gn_shbd','上海本地','2014-11-20 13:02:24'),(1003,'概念',1003121,'gn_szbd','深圳本地','2014-11-20 13:02:24'),(1003,'概念',1003122,'gn_zxsy','振兴沈阳','2014-11-20 13:02:24'),(1003,'概念',1003123,'gn_yhfz','沿海发展','2014-11-20 13:02:24'),(1003,'概念',1003124,'gn_yq50','央企50','2014-11-20 13:02:24'),(1003,'概念',1003125,'gn_cdp','超大盘','2014-11-20 13:02:24'),(1003,'概念',1003126,'gn_cgjr','参股金融','2014-11-20 13:02:24'),(1003,'概念',1003127,'gn_jjzc','基金重仓','2014-11-20 13:02:24'),(1003,'概念',1003128,'gn_dtjj','低碳经济','2014-11-20 13:02:24'),(1003,'概念',1003129,'gn_gqgn','股期概念','2014-11-20 13:02:24'),(1003,'概念',1003130,'gn_stbk','ST板块','2014-11-20 13:02:24'),(1003,'概念',1003131,'gn_ctgn','创投概念','2014-11-20 13:02:24'),(1003,'概念',1003132,'gn_gqjl','股权激励','2014-11-20 13:02:24'),(1003,'概念',1003133,'gn_jxlg','甲型流感','2014-11-20 13:02:24'),(1003,'概念',1003134,'gn_hjgn','黄金概念','2014-11-20 13:02:24'),(1003,'概念',1003135,'gn_jght','军工航天','2014-11-20 13:02:24'),(1003,'概念',1003136,'gn_wlw','物联网','2014-11-20 13:02:24'),(1003,'概念',1003137,'gn_dsn','迪士尼','2014-11-20 13:02:24'),(1003,'概念',1003138,'gn_ckts','出口退税','2014-11-20 13:02:24'),(1003,'概念',1003139,'gn_tljj','铁路基建','2014-11-20 13:02:24'),(1003,'概念',1003140,'gn_xny','新能源','2014-11-20 13:02:24'),(1003,'概念',1003141,'gn_cytq','成渝特区','2014-11-20 13:02:24'),(1003,'概念',1003142,'gn_hxxa','海峡西岸','2014-11-20 13:02:24'),(1003,'概念',1003143,'gn_hhsj','黄河三角','2014-11-20 13:02:24'),(1003,'概念',1003144,'gn_wgg','未股改','2014-11-20 13:02:24'),(1003,'概念',1003145,'gn_xhjj','循环经济','2014-11-20 13:02:24'),(1003,'概念',1003146,'gn_zndw','智能电网','2014-11-20 13:02:24'),(1003,'概念',1003147,'gn_zczr','资产注入','2014-11-20 13:02:24'),(1004,'市场',1004001,'sh_a','沪市A股','2014-11-20 12:10:32'),(1004,'市场',1004002,'sh_b','沪市B股','2014-11-20 12:10:32'),(1004,'市场',1004003,'sz_a','深市A股','2014-11-20 12:10:32'),(1004,'市场',1004004,'sz_b','深市B股','2014-11-20 12:10:32'),(1004,'市场',1004005,'cyb','创业板','2014-11-23 18:40:20'),(2001,'农、林、牧、渔业(A)',2001001,'01','农业','2014-11-21 22:41:25'),(2001,'农、林、牧、渔业(A)',2001002,'02','林业','2014-11-21 23:09:21'),(2001,'农、林、牧、渔业(A)',2001003,'03','畜牧业','2014-11-21 23:09:22'),(2001,'农、林、牧、渔业(A)',2001004,'04','渔业','2014-11-21 23:09:24'),(2001,'农、林、牧、渔业(A)',2001005,'05','农、林、牧、渔服务业','2014-11-21 23:09:26'),(2002,'采矿业(B)',2002001,'06','煤炭开采和洗选业','2014-11-21 23:09:26'),(2002,'采矿业(B)',2002002,'07','石油和天然气开采业','2014-11-21 23:09:32'),(2002,'采矿业(B)',2002003,'08','黑色金属矿采选业','2014-11-21 23:09:33'),(2002,'采矿业(B)',2002004,'09','有色金属矿采选业','2014-11-21 23:09:34'),(2002,'采矿业(B)',2002005,'11','开采辅助活动','2014-11-21 23:09:39'),(2003,'制造业(C)',2003001,'13','农副食品加工业','2014-11-21 23:09:40'),(2003,'制造业(C)',2003002,'14','食品制造业','2014-11-21 23:09:48'),(2003,'制造业(C)',2003003,'15','酒、饮料和精制茶制造业','2014-11-21 23:09:52'),(2003,'制造业(C)',2003004,'17','纺织业','2014-11-21 23:09:55'),(2003,'制造业(C)',2003005,'18','纺织服装、服饰业','2014-11-21 23:10:02'),(2003,'制造业(C)',2003006,'19','皮革、毛皮、羽毛及其制品和制鞋业','2014-11-21 23:10:07'),(2003,'制造业(C)',2003007,'20','木材加工及木、竹、藤、棕、草制品业','2014-11-21 23:10:08'),(2003,'制造业(C)',2003008,'21','家具制造业','2014-11-21 23:10:09'),(2003,'制造业(C)',2003009,'22','造纸及纸制品业','2014-11-21 23:10:10'),(2003,'制造业(C)',2003010,'23','印刷和记录媒介复制业','2014-11-21 23:10:14'),(2003,'制造业(C)',2003011,'24','文教、工美、体育和娱乐用品制造业','2014-11-21 23:10:15'),(2003,'制造业(C)',2003012,'25','石油加工、炼焦及核燃料加工业','2014-11-21 23:10:17'),(2003,'制造业(C)',2003013,'26','化学原料及化学制品制造业','2014-11-21 23:10:21'),(2003,'制造业(C)',2003014,'27','医药制造业','2014-11-21 23:10:49'),(2003,'制造业(C)',2003015,'28','化学纤维制造业','2014-11-21 23:11:14'),(2003,'制造业(C)',2003016,'29','橡胶和塑料制品业','2014-11-21 23:11:19'),(2003,'制造业(C)',2003017,'30','非金属矿物制品业','2014-11-21 23:11:25'),(2003,'制造业(C)',2003018,'31','黑色金属冶炼及压延加工业','2014-11-21 23:11:32'),(2003,'制造业(C)',2003019,'32','有色金属冶炼和压延加工业','2014-11-21 23:11:35'),(2003,'制造业(C)',2003020,'33','金属制品业','2014-11-21 23:11:43'),(2003,'制造业(C)',2003021,'34','通用设备制造业','2014-11-21 23:11:45'),(2003,'制造业(C)',2003022,'35','专用设备制造业','2014-11-21 23:11:57'),(2003,'制造业(C)',2003023,'36','汽车制造业','2014-11-21 23:12:17'),(2003,'制造业(C)',2003024,'37','铁路、船舶、航空航天和其它运输设备制造业','2014-11-21 23:12:34'),(2003,'制造业(C)',2003025,'38','电气机械及器材制造业','2014-11-21 23:12:40'),(2003,'制造业(C)',2003026,'39','计算机、通信和其他电子设备制造业','2014-11-21 23:12:59'),(2003,'制造业(C)',2003027,'40','仪器仪表制造业','2014-11-21 23:13:26'),(2003,'制造业(C)',2003028,'41','其他制造业','2014-11-21 23:13:28'),(2003,'制造业(C)',2003029,'42','废弃资源综合利用业','2014-11-21 23:13:30'),(2004,'电力、热力、燃气及水生产和供应业(D)',2004001,'44','电力、热力生产和供应业','2014-11-21 23:13:31'),(2004,'电力、热力、燃气及水生产和供应业(D)',2004002,'45','燃气生产和供应业','2014-11-21 23:13:41'),(2004,'电力、热力、燃气及水生产和供应业(D)',2004003,'46','水的生产和供应业','2014-11-21 23:13:42'),(2005,'建筑业(E)',2005001,'48','土木工程建筑业','2014-11-21 23:13:44'),(2005,'建筑业(E)',2005002,'49','建筑安装业','2014-11-21 23:13:49'),(2005,'建筑业(E)',2005003,'50','建筑装饰和其他建筑业','2014-11-21 23:13:49'),(2006,'批发和零售业(F)',2006001,'51','批发业','2014-11-21 23:13:49'),(2006,'批发和零售业(F)',2006002,'52','零售业','2014-11-21 23:13:52'),(2007,'交通运输、仓储和邮政业(G)',2007001,'53','铁路运输业','2014-11-21 23:14:03'),(2007,'交通运输、仓储和邮政业(G)',2007002,'54','道路运输业','2014-11-21 23:14:04'),(2007,'交通运输、仓储和邮政业(G)',2007003,'55','水上运输业','2014-11-21 23:14:10'),(2007,'交通运输、仓储和邮政业(G)',2007004,'56','航空运输业','2014-11-21 23:14:15'),(2007,'交通运输、仓储和邮政业(G)',2007005,'58','装卸搬运和运输代理业','2014-11-21 23:14:17'),(2007,'交通运输、仓储和邮政业(G)',2007006,'59','仓储业','2014-11-21 23:14:17'),(2008,'住宿和餐饮业(H)',2008001,'61','住宿业','2014-11-21 23:14:19'),(2008,'住宿和餐饮业(H)',2008002,'62','餐饮业','2014-11-21 23:14:21'),(2009,'信息传输、软件和信息技术服务业(I)',2009001,'63','电信、广播电视和卫星传输服务','2014-11-21 23:14:22'),(2009,'信息传输、软件和信息技术服务业(I)',2009002,'64','互联网和相关服务','2014-11-21 23:14:25'),(2009,'信息传输、软件和信息技术服务业(I)',2009003,'65','软件和信息技术服务业','2014-11-21 23:14:27'),(2010,'金融业(J)',2010001,'66','货币金融服务','2014-11-21 23:14:43'),(2010,'金融业(J)',2010002,'67','资本市场服务','2014-11-21 23:14:45'),(2010,'金融业(J)',2010003,'68','保险业','2014-11-21 23:14:48'),(2010,'金融业(J)',2010004,'69','其他金融业','2014-11-21 23:14:49'),(2011,'房地产业(K)',2011001,'70','房地产业','2014-11-21 23:14:49'),(2012,'租赁和商务服务业(L)',2012001,'71','租赁业','2014-11-21 23:15:02'),(2012,'租赁和商务服务业(L)',2012002,'72','商务服务业','2014-11-21 23:15:02'),(2013,'科学研究和技术服务业(M)',2013001,'73','研究和试验发展','2014-11-21 23:15:03'),(2013,'科学研究和技术服务业(M)',2013002,'74','专业技术服务业','2014-11-21 23:15:03'),(2014,'水利、环境和公共设施管理业(N)',2014001,'77','生态保护和环境治理业','2014-11-21 23:15:03'),(2014,'水利、环境和公共设施管理业(N)',2014002,'78','公共设施管理业','2014-11-21 23:15:04'),(2015,'教育(P)',2015001,'82','教育','2014-11-21 23:15:06'),(2016,'卫生和社会工作(Q)',2016001,'83','卫生','2014-11-21 23:15:07'),(2017,'文化、体育和娱乐业(R)',2017001,'85','新闻和出版业','2014-11-21 23:15:07'),(2017,'文化、体育和娱乐业(R)',2017002,'86','广播、电视、电影和影视录音制作业','2014-11-21 23:15:13'),(2017,'文化、体育和娱乐业(R)',2017003,'87','文化艺术业','2014-11-21 23:15:14'),(2018,'综合(S)',2018001,'90','综合','2014-11-21 23:15:14');