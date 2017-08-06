-- --------------------------------------------------------
-- 主機:                           127.0.0.1
-- 服務器版本:                        5.7.18-log - MySQL Community Server (GPL)
-- 服務器操作系統:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 導出 ren-sing 的資料庫結構
CREATE DATABASE IF NOT EXISTS `ren-sing` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ren-sing`;

-- 導出  表 ren-sing.account 結構
CREATE TABLE IF NOT EXISTS `account` (
  `account` varchar(50) NOT NULL COMMENT '帳號',
  `password` varchar(50) NOT NULL COMMENT '密碼',
  `role` varchar(1) NOT NULL COMMENT '角色 (0:行政,1:老師,2:學生)',
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.account 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- 導出  表 ren-sing.achievement 結構
CREATE TABLE IF NOT EXISTS `achievement` (
  `achievement_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(15) NOT NULL DEFAULT '0',
  `grade` varchar(10) NOT NULL DEFAULT '0' COMMENT '分期檢定級別 (目前)',
  `score` varchar(10) NOT NULL DEFAULT '0' COMMENT '分期檢定分數 (目前)',
  `date` varchar(50) NOT NULL DEFAULT '0' COMMENT '分期檢定日期 (目前)',
  `updater` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL COMMENT 'yyyy/MM/dd hh:mm:ss',
  PRIMARY KEY (`achievement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.achievement 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `achievement` DISABLE KEYS */;
/*!40000 ALTER TABLE `achievement` ENABLE KEYS */;

-- 導出  表 ren-sing.achievement_log 結構
CREATE TABLE IF NOT EXISTS `achievement_log` (
  `achievement_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `achievement_id` int(11) NOT NULL DEFAULT '0',
  `student_id` varchar(50) NOT NULL DEFAULT '0' COMMENT '學生',
  `grade` varchar(50) NOT NULL DEFAULT '0' COMMENT '分期檢定級別',
  `score` varchar(50) NOT NULL DEFAULT '0' COMMENT '分期檢定分數',
  `date` varchar(50) NOT NULL DEFAULT '0' COMMENT '分期檢定日期',
  `updater` varchar(50) DEFAULT '0',
  `update_time` varchar(50) DEFAULT '0' COMMENT 'yyyy/MM/dd hh:mm:ss',
  UNIQUE KEY `id` (`achievement_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.achievement_log 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `achievement_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `achievement_log` ENABLE KEYS */;

-- 導出  表 ren-sing.bill 結構
CREATE TABLE IF NOT EXISTS `bill` (
  `bill_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_master_id` int(11) NOT NULL DEFAULT '0',
  `student_id` varchar(15) NOT NULL DEFAULT '0' COMMENT '學生',
  `discount` varchar(15) DEFAULT '0' COMMENT '折扣方案',
  `payment` varchar(15) NOT NULL DEFAULT '0' COMMENT '繳費金額',
  `pay_method` varchar(1) NOT NULL DEFAULT '0',
  `pay_date` varchar(50) NOT NULL DEFAULT '0' COMMENT '繳費日期  yyyy/MM/dd hh:mm:ss',
  `entry_date` varchar(50) NOT NULL DEFAULT '0' COMMENT '入賬日期  yyyy/MM/dd hh:mm:ss',
  `updater` varchar(50) NOT NULL DEFAULT '0',
  `update_time` varchar(50) NOT NULL DEFAULT '0' COMMENT 'yyyy/MM/dd hh:mm:ss',
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.bill 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;

-- 導出  表 ren-sing.bill_log 結構
CREATE TABLE IF NOT EXISTS `bill_log` (
  `bill_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `bill.id` int(11) NOT NULL DEFAULT '0',
  `class_master_id` int(11) NOT NULL DEFAULT '0',
  `student_id` varchar(15) NOT NULL DEFAULT '0' COMMENT '學生',
  `discount` varchar(15) DEFAULT '0' COMMENT '折扣方案',
  `payment` varchar(15) NOT NULL DEFAULT '0' COMMENT '繳費金額',
  `pay_method` varchar(1) NOT NULL DEFAULT '0',
  `pay_date` varchar(50) NOT NULL DEFAULT '0' COMMENT '繳費日期  yyyy/MM/dd hh:mm:ss',
  `entry_date` varchar(50) NOT NULL DEFAULT '0' COMMENT '入賬日期  yyyy/MM/dd hh:mm:ss',
  `updater` varchar(50) NOT NULL DEFAULT '0',
  `update_time` varchar(50) NOT NULL DEFAULT '0' COMMENT 'yyyy/MM/dd hh:mm:ss',
  UNIQUE KEY `bill_log_id` (`bill_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.bill_log 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `bill_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_log` ENABLE KEYS */;

-- 導出  表 ren-sing.class_detail 結構
CREATE TABLE IF NOT EXISTS `class_detail` (
  `class_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_master_id` int(11) NOT NULL DEFAULT '0',
  `student_id` varchar(15) NOT NULL DEFAULT '0' COMMENT '學生',
  `teacher_id` varchar(15) NOT NULL DEFAULT '0' COMMENT '老師',
  `song` varchar(50) NOT NULL DEFAULT '0' COMMENT '歌曲',
  `date` varchar(50) NOT NULL DEFAULT '0' COMMENT '上課日期 yyyy/MM/dd',
  `time` varchar(50) NOT NULL DEFAULT '0' COMMENT '上課時間 hh:mm',
  `hw` varchar(1000) DEFAULT '0' COMMENT '回家作業',
  `teacher_note` varchar(1000) DEFAULT '0' COMMENT '老師筆記',
  `student_note` varchar(1000) DEFAULT '0' COMMENT '學生筆記',
  `type` varchar(1) NOT NULL DEFAULT '0' COMMENT '0=個別課，1=阿卡課',
  `finish` varchar(1) NOT NULL DEFAULT '0' COMMENT '0=結束，1=未結束',
  `updater` varchar(50) DEFAULT '0',
  `update_time` varchar(50) DEFAULT '0' COMMENT 'yyyy/MM/dd hh:mm:ss',
  UNIQUE KEY `class_detail_id` (`class_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.class_detail 的資料：~3 rows (大約)
/*!40000 ALTER TABLE `class_detail` DISABLE KEYS */;
REPLACE INTO `class_detail` (`class_detail_id`, `class_master_id`, `student_id`, `teacher_id`, `song`, `date`, `time`, `hw`, `teacher_note`, `student_note`, `type`, `finish`, `updater`, `update_time`) VALUES
	(1, 1, '1', '1', 'song', '2017/07/09', '18:30', '0', '0', '3333', '0', '0', '0', '0'),
	(5, 1112, '123564545', '1235645456', '鬥陣取', '2017/7/9', '15:40', '大便庫子上', NULL, '', '0', '0', '0', '0'),
	(7, 2, '2', '2', '好習慣', '2017/07/09', '20:00', '0', '0', '3333', '0', '0', NULL, NULL);
/*!40000 ALTER TABLE `class_detail` ENABLE KEYS */;

-- 導出  表 ren-sing.class_master 結構
CREATE TABLE IF NOT EXISTS `class_master` (
  `class_master_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0' COMMENT '課堂名稱',
  `type` varchar(1) NOT NULL DEFAULT '0' COMMENT '課程類型 (0:一對一個別課,1:阿卡團體班)',
  `level` varchar(1) DEFAULT '0' COMMENT '課程等級 (0:入門班,1:基礎班,2:進階班)',
  `status` varchar(50) NOT NULL DEFAULT '0' COMMENT '課程狀態',
  `price` varchar(50) NOT NULL DEFAULT '0' COMMENT '課程原始價錢',
  `summary` varchar(5) NOT NULL DEFAULT '0' COMMENT '課堂總數',
  `count` varchar(5) NOT NULL DEFAULT '0' COMMENT '目前上課次數',
  `rest` varchar(5) NOT NULL DEFAULT '0' COMMENT '剩餘課堂數',
  `s_date` varchar(10) DEFAULT '0' COMMENT '課堂起始時間 (預計) yyyy/mm/dd',
  `e_date` varchar(10) DEFAULT '0' COMMENT '課堂結束時間 (預計) yyyy/MM/dd',
  `place` varchar(50) NOT NULL DEFAULT '0' COMMENT '上課地點',
  `teacher_id` varchar(15) NOT NULL DEFAULT '0' COMMENT '老師',
  `updater` varchar(50) NOT NULL DEFAULT '0',
  `update_time` varchar(50) NOT NULL DEFAULT '0' COMMENT 'yyyy/MM/dd hh:mm:ss',
  PRIMARY KEY (`class_master_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1113 DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.class_master 的資料：~2 rows (大約)
/*!40000 ALTER TABLE `class_master` DISABLE KEYS */;
REPLACE INTO `class_master` (`class_master_id`, `name`, `type`, `level`, `status`, `price`, `summary`, `count`, `rest`, `s_date`, `e_date`, `place`, `teacher_id`, `updater`, `update_time`) VALUES
	(1111, '音樂課', '0', '0', 'Y', '25000', '10', '58', '-4', '2017/7/2', '2017/9/2', '菜寮', '123564545', '0', '0'),
	(1112, '喇叭樂課', '0', '0', 'Y', '25000', '5', '0', '5', '2017/9/2', '2017/10/2', '菜寮', '123564545', '0', '0');
/*!40000 ALTER TABLE `class_master` ENABLE KEYS */;

-- 導出  表 ren-sing.courses_time 結構
CREATE TABLE IF NOT EXISTS `courses_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `interval_time` varchar(50) DEFAULT '0',
  `start_time` varchar(50) DEFAULT '0',
  `end_time` varchar(50) DEFAULT '0',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.courses_time 的資料：~18 rows (大約)
/*!40000 ALTER TABLE `courses_time` DISABLE KEYS */;
REPLACE INTO `courses_time` (`id`, `interval_time`, `start_time`, `end_time`) VALUES
	(1, '1300_1330', '13:00', '13:30'),
	(2, '1330_1400', '13:30', '14:00'),
	(3, '1400_1430', '14:00', '14:30'),
	(4, '1430_1500', '14:30', '15:00'),
	(5, '1500_1530', '15:00', '15:30'),
	(6, '1530_1600', '15:30', '16:00'),
	(7, '1600_1630', '16:00', '16:30'),
	(8, '1630_1700', '16:30', '17:00'),
	(9, '1700_1730', '17:00', '17:30'),
	(10, '1730_1800', '17:30', '18:00'),
	(11, '1800_1830', '18:00', '18:30'),
	(12, '1830_1900', '18:30', '19:00'),
	(13, '1900_1930', '19:00', '19:30'),
	(14, '1930_2000', '19:30', '20:00'),
	(15, '2000_2030', '20:00', '20:30'),
	(16, '2030_2100', '20:30', '21:00'),
	(17, '2100_2130', '21:00', '21:30'),
	(18, '2130_2200', '21:30', '22:00');
/*!40000 ALTER TABLE `courses_time` ENABLE KEYS */;

-- 導出  表 ren-sing.person 結構
CREATE TABLE IF NOT EXISTS `person` (
  `person_id` varchar(15) NOT NULL COMMENT '會員編號',
  `virtual_account` varchar(50) NOT NULL DEFAULT '0' COMMENT '繳費虛擬帳號',
  `name` varchar(50) NOT NULL COMMENT '會員名字',
  `nickname` varchar(50) DEFAULT NULL COMMENT '綽號',
  `balance` varchar(50) DEFAULT '0' COMMENT '餘額',
  `id_number` varchar(10) NOT NULL COMMENT '身分證字號',
  `sex` varchar(1) NOT NULL COMMENT '性別 (0:男,1:女)',
  `birthday` varchar(10) NOT NULL COMMENT '生日',
  `local_calls` varchar(15) NOT NULL COMMENT '市話',
  `phone` varchar(15) NOT NULL COMMENT '電話',
  `email` varchar(50) NOT NULL COMMENT '信箱',
  `skype` varchar(50) NOT NULL COMMENT 'skyoe帳號',
  `career` varchar(50) DEFAULT NULL COMMENT '職業',
  `recipient` varchar(20) DEFAULT NULL COMMENT '收信人',
  `rec_add` varchar(100) DEFAULT NULL COMMENT '收信地址',
  `rec_num` varchar(15) DEFAULT NULL COMMENT '收信人電話',
  `company_tax_id` varchar(30) DEFAULT NULL COMMENT '統一編號',
  `updater` varchar(50) NOT NULL,
  `update_time` varchar(50) NOT NULL COMMENT 'yyyy/MM/dd hh:mm:ss',
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.person 的資料：~2 rows (大約)
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
REPLACE INTO `person` (`person_id`, `virtual_account`, `name`, `nickname`, `balance`, `id_number`, `sex`, `birthday`, `local_calls`, `phone`, `email`, `skype`, `career`, `recipient`, `rec_add`, `rec_num`, `company_tax_id`, `updater`, `update_time`) VALUES
	('123564545', '0', '韋小寶', '小寶', '0', '1', '男', '19870806', '22', '11', '1', '11', '11', '11', '11', '11', '11', '11', '11'),
	('1235645456', '0', '滅絕師太', '妖尼姑', '0', '1', '女', '19870806', '33', '22', '22', '2', '2', '22', '2', '2', '22', '2', '2');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
