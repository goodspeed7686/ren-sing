-- --------------------------------------------------------
-- 主機:                           127.0.0.1
-- 伺服器版本:                        5.7.20-log - MySQL Community Server (GPL)
-- 伺服器操作系統:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 傾印 ren-sing 的資料庫結構
DROP DATABASE IF EXISTS `ren-sing`;
CREATE DATABASE IF NOT EXISTS `ren-sing` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ren-sing`;

-- 傾印  表格 ren-sing.account 結構
DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `account` varchar(50) NOT NULL COMMENT '帳號',
  `password` varchar(50) NOT NULL COMMENT '密碼',
  `role` varchar(1) DEFAULT '2' COMMENT '角色 (0:行政,1:老師,2:學生)',
  `status` varchar(1) DEFAULT '1' COMMENT '帳號狀態 (0:停權, 1:使用中)',
  `person_id` varchar(50) NOT NULL COMMENT '會員編號',
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在傾印表格  ren-sing.account 的資料：~6 rows (大約)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
REPLACE INTO `account` (`account`, `password`, `role`, `status`, `person_id`) VALUES
	('2017120014', '123123', '1', '1', '2017120014'),
	('2017120025', '123123', '1', '1', '2017120025'),
	('2017120036', '123123', '2', '1', '2017120036'),
	('2017120047', '123123', '2', '1', '2017120047'),
	('2017120058', '123123', '2', '1', '2017120058'),
	('admin', '123123', '0', '1', '2017100015');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- 傾印  表格 ren-sing.achievement 結構
DROP TABLE IF EXISTS `achievement`;
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

-- 正在傾印表格  ren-sing.achievement 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `achievement` DISABLE KEYS */;
/*!40000 ALTER TABLE `achievement` ENABLE KEYS */;

-- 傾印  表格 ren-sing.achievement_log 結構
DROP TABLE IF EXISTS `achievement_log`;
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

-- 正在傾印表格  ren-sing.achievement_log 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `achievement_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `achievement_log` ENABLE KEYS */;

-- 傾印  表格 ren-sing.bill 結構
DROP TABLE IF EXISTS `bill`;
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

-- 正在傾印表格  ren-sing.bill 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;

-- 傾印  表格 ren-sing.bill_log 結構
DROP TABLE IF EXISTS `bill_log`;
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

-- 正在傾印表格  ren-sing.bill_log 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `bill_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_log` ENABLE KEYS */;

-- 傾印  表格 ren-sing.class_detail 結構
DROP TABLE IF EXISTS `class_detail`;
CREATE TABLE IF NOT EXISTS `class_detail` (
  `class_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_master_id` int(11) NOT NULL DEFAULT '0',
  `student_id` varchar(15) NOT NULL DEFAULT '0' COMMENT '學生',
  `teacher_id` varchar(15) NOT NULL DEFAULT '0' COMMENT '老師',
  `song` varchar(50) DEFAULT NULL COMMENT '歌曲',
  `date` varchar(50) NOT NULL COMMENT '上課日期 yyyy/MM/dd',
  `time` varchar(2) NOT NULL COMMENT '上課時間 (對照course_time)',
  `ranges` int(11) NOT NULL COMMENT '幾個時段',
  `s_time` varchar(10) DEFAULT '' COMMENT '特殊上課時間(開始)',
  `e_time` varchar(10) DEFAULT '' COMMENT '特殊上課時間(結束)',
  `hw` varchar(1000) DEFAULT '' COMMENT '回家作業',
  `teacher_note` varchar(1000) DEFAULT '' COMMENT '老師筆記',
  `student_note` varchar(1000) DEFAULT '' COMMENT '學生筆記',
  `type` varchar(1) NOT NULL DEFAULT '0' COMMENT '0=個別課，1=阿卡課',
  `finish` varchar(1) NOT NULL DEFAULT '0' COMMENT '0=未結束，1=已結束',
  `sign` varchar(1) NOT NULL DEFAULT '0' COMMENT '0=未簽到，1=已簽到',
  `detail_status` varchar(1) DEFAULT '0' COMMENT '0=不可預約，1=可預約',
  `updater` varchar(50) DEFAULT '0',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'yyyy/MM/dd hh:mm:ss',
  UNIQUE KEY `Index 2` (`date`,`time`),
  UNIQUE KEY `class_detail_id` (`class_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- 正在傾印表格  ren-sing.class_detail 的資料：~25 rows (大約)
/*!40000 ALTER TABLE `class_detail` DISABLE KEYS */;
REPLACE INTO `class_detail` (`class_detail_id`, `class_master_id`, `student_id`, `teacher_id`, `song`, `date`, `time`, `ranges`, `s_time`, `e_time`, `hw`, `teacher_note`, `student_note`, `type`, `finish`, `sign`, `detail_status`, `updater`, `update_time`) VALUES
	(1, 1115, '2017120047', '2017120025', NULL, '2017/12/10', '1', 1, NULL, NULL, NULL, NULL, NULL, '0', '1', '1', '0', 'updater', '2017-12-16 15:04:05'),
	(10, 1115, '2017120047', '2017120025', NULL, '2017/12/10', '10', 1, '', '', '', '', '', '0', '1', '1', '0', '0', '2017-12-17 00:00:09'),
	(9, 1115, '2017120047', '2017120025', NULL, '2017/12/10', '11', 1, '', '', '', '', '', '0', '1', '1', '0', '0', '2017-12-16 23:59:48'),
	(3, 1115, '2017120047', '2017120025', NULL, '2017/12/10', '3', 1, '', '', '', '', '', '0', '1', '1', '0', '0', '2017-12-16 23:50:23'),
	(2, 1115, '2017120047', '2017120025', '0', '2017/12/10', '4', 1, '', '', '', '', '', '0', '1', '1', '0', '0', '2017-12-18 13:30:48'),
	(4, 1115, '2017120047', '2017120025', NULL, '2017/12/10', '5', 1, '', '', '', '', '', '0', '1', '1', '0', '0', '2017-12-16 23:52:35'),
	(7, 1115, '2017120047', '2017120025', NULL, '2017/12/10', '6', 1, '', '', '', '', '', '0', '1', '1', '0', '0', '2017-12-16 23:59:27'),
	(5, 1115, '2017120047', '2017120025', NULL, '2017/12/10', '8', 1, '', '', '', '', '', '0', '1', '1', '0', '0', '2017-12-16 23:55:29'),
	(6, 1115, '2017120047', '2017120025', NULL, '2017/12/10', '9', 1, '', '', '', '', '', '0', '1', '1', '0', '0', '2017-12-16 23:56:40'),
	(11, 1115, '2017120047', '2017120025', NULL, '2017/12/17', '1', 1, '', '', '', '', '', '0', '1', '1', '0', '0', '2017-12-17 00:00:29'),
	(12, 1115, '2017120047', '2017120025', NULL, '2017/12/17', '10', 1, '', '', '', '', '', '0', '1', '1', '0', '0', '2017-12-17 00:00:39'),
	(14, 1115, '2017120047', '2017120025', NULL, '2017/12/17', '5', 1, '', '', '', '', '', '0', '1', '1', '0', '0', '2017-12-17 00:08:10'),
	(13, 1115, '2017120047', '2017120025', NULL, '2017/12/17', '8', 1, '', '', '', '', '', '0', '1', '1', '0', '0', '2017-12-17 00:08:04'),
	(18, 1115, '2017120047', '2017120025', NULL, '2017/12/18', '1', 1, '', '', '', '', '', '0', '0', '0', '0', '0', '2017-12-18 21:08:47'),
	(25, 1115, '2017120047', '2017120025', NULL, '2017/12/18', '13', 1, '', '', '', '', '', '0', '0', '0', '0', '0', '2017-12-18 21:16:50'),
	(17, 1115, '2017120047', '2017120025', NULL, '2017/12/18', '9', 1, '', '', '', '', '', '0', '1', '0', '0', '0', '2017-12-18 23:50:15'),
	(33, 1115, '2017120047', '2017120025', NULL, '2017/12/19', '3', 1, '', '', '', '', '', '0', '0', '1', '0', '0', '2017-12-19 00:28:17'),
	(34, 1115, '2017120047', '2017120025', NULL, '2017/12/19', '6', 1, '', '', '', '', '', '0', '0', '1', '0', '0', '2017-12-19 00:29:38'),
	(35, 1115, '2017120047', '2017120025', NULL, '2017/12/19', '8', 1, '', '', '', '', '', '0', '0', '0', '0', '0', '2017-12-19 00:29:45'),
	(31, 1115, '2017120047', '2017120025', NULL, '2017/12/21', '10', 1, '', '', '', '', '', '0', '0', '0', '0', '0', '2017-12-18 21:25:59'),
	(32, 1115, '2017120047', '2017120025', NULL, '2017/12/21', '12', 1, '', '', '', '', '', '0', '0', '0', '0', '0', '2017-12-18 21:26:01'),
	(27, 1115, '2017120047', '2017120025', NULL, '2017/12/21', '2', 1, '', '', '', '', '', '0', '0', '0', '0', '0', '2017-12-18 21:24:48'),
	(28, 1115, '2017120047', '2017120025', NULL, '2018/01/08', '13', 1, '', '', '', '', '', '0', '0', '0', '0', '0', '2018-01-08 18:32:43'),
	(30, 1115, '2017120058', '2017120025', NULL, '2018/01/08', '5', 2, '14:00', '15:00', '', '', '', '0', '0', '0', '0', '0', '2018-01-08 15:39:38'),
	(29, 1115, '2017120047', '2017120025', NULL, '2018/01/08', '8', 3, '18:00', '20:00', '', '', '', '0', '0', '0', '0', '0', '2018-01-08 18:29:45'),
	(36, 1115, '2017120047', '2017120025', NULL, '2018/1/9', '8', 1, '', '', '', '', '', '0', '0', '0', '0', '0', '2018-01-09 16:46:57');
/*!40000 ALTER TABLE `class_detail` ENABLE KEYS */;

-- 傾印  表格 ren-sing.class_level 結構
DROP TABLE IF EXISTS `class_level`;
CREATE TABLE IF NOT EXISTS `class_level` (
  `class_level_id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(2) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`class_level_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在傾印表格  ren-sing.class_level 的資料：~3 rows (大約)
/*!40000 ALTER TABLE `class_level` DISABLE KEYS */;
REPLACE INTO `class_level` (`class_level_id`, `level`, `name`) VALUES
	(1, '1', '入門班'),
	(2, '2', '基礎班'),
	(3, '3', '進階班');
/*!40000 ALTER TABLE `class_level` ENABLE KEYS */;

-- 傾印  表格 ren-sing.class_master 結構
DROP TABLE IF EXISTS `class_master`;
CREATE TABLE IF NOT EXISTS `class_master` (
  `class_master_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0' COMMENT '課堂名稱',
  `type` varchar(1) NOT NULL DEFAULT '0' COMMENT '課程類型 (0:一對一個別課,1:阿卡團體班)',
  `level` varchar(1) DEFAULT '0' COMMENT '課程等級 (0:入門班,1:基礎班,2:進階班)',
  `status` varchar(50) NOT NULL DEFAULT '0' COMMENT '課程狀態 (0:未結束 1:結束 2:未結束其他理由)',
  `price` varchar(50) DEFAULT '0' COMMENT '課程原始價錢',
  `summary` varchar(5) NOT NULL DEFAULT '0' COMMENT '課堂總數',
  `count` varchar(5) NOT NULL DEFAULT '0' COMMENT '目前上課次數',
  `rest` varchar(5) NOT NULL DEFAULT '0' COMMENT '剩餘課堂數',
  `s_date` varchar(10) NOT NULL COMMENT '課堂起始時間 (預計) yyyy/mm/dd',
  `e_date` varchar(10) NOT NULL COMMENT '課堂結束時間 (預計) yyyy/MM/dd',
  `day` varchar(1) DEFAULT NULL COMMENT '星期幾',
  `time` varchar(2) DEFAULT NULL COMMENT '上課時間 (對照course_time)',
  `place` varchar(50) NOT NULL DEFAULT '0' COMMENT '上課地點',
  `teacher_id` varchar(15) NOT NULL DEFAULT '0' COMMENT '老師',
  `student_id` varchar(15) NOT NULL DEFAULT '0' COMMENT '學生',
  `updater` varchar(50) DEFAULT '0',
  `update_time` varchar(50) DEFAULT '0' COMMENT 'yyyy/MM/dd hh:mm:ss',
  PRIMARY KEY (`class_master_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1116 DEFAULT CHARSET=utf8;

-- 正在傾印表格  ren-sing.class_master 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `class_master` DISABLE KEYS */;
REPLACE INTO `class_master` (`class_master_id`, `name`, `type`, `level`, `status`, `price`, `summary`, `count`, `rest`, `s_date`, `e_date`, `day`, `time`, `place`, `teacher_id`, `student_id`, `updater`, `update_time`) VALUES
	(1115, '臻臻個人課', '0', NULL, '1', NULL, '24', '24', '0', '2017/12/16', '2018/08/16', '', NULL, '0', '2017120025', '2017120047', 'updater', '2018/01/09 16:46:57');
/*!40000 ALTER TABLE `class_master` ENABLE KEYS */;

-- 傾印  表格 ren-sing.class_place 結構
DROP TABLE IF EXISTS `class_place`;
CREATE TABLE IF NOT EXISTS `class_place` (
  `class_place_id` int(11) NOT NULL AUTO_INCREMENT,
  `place` varchar(2) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT '0',
  PRIMARY KEY (`class_place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在傾印表格  ren-sing.class_place 的資料：~2 rows (大約)
/*!40000 ALTER TABLE `class_place` DISABLE KEYS */;
REPLACE INTO `class_place` (`class_place_id`, `place`, `name`) VALUES
	(1, '0', '菜寮'),
	(2, '1', '新竹');
/*!40000 ALTER TABLE `class_place` ENABLE KEYS */;

-- 傾印  表格 ren-sing.class_type 結構
DROP TABLE IF EXISTS `class_type`;
CREATE TABLE IF NOT EXISTS `class_type` (
  `class_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(3) NOT NULL DEFAULT '0' COMMENT '課程類型 (0:一對一個別課,1:阿卡團體班)',
  `name` varchar(50) DEFAULT '0' COMMENT '課程類型名稱',
  UNIQUE KEY `class_type_id` (`class_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在傾印表格  ren-sing.class_type 的資料：~2 rows (大約)
/*!40000 ALTER TABLE `class_type` DISABLE KEYS */;
REPLACE INTO `class_type` (`class_type_id`, `type`, `name`) VALUES
	(1, '0', '一對一個人課'),
	(2, '1', '阿卡基礎班');
/*!40000 ALTER TABLE `class_type` ENABLE KEYS */;

-- 傾印  表格 ren-sing.courses_time 結構
DROP TABLE IF EXISTS `courses_time`;
CREATE TABLE IF NOT EXISTS `courses_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `interval_time` varchar(50) DEFAULT '0',
  `start_time` varchar(50) DEFAULT '0',
  `end_time` varchar(50) DEFAULT '0',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- 正在傾印表格  ren-sing.courses_time 的資料：~13 rows (大約)
/*!40000 ALTER TABLE `courses_time` DISABLE KEYS */;
REPLACE INTO `courses_time` (`id`, `interval_time`, `start_time`, `end_time`) VALUES
	(1, '1300_1340', '13:00', '13:40'),
	(2, '1340_1420', '13:40', '14:20'),
	(3, '1420_1500', '14:20', '15:00'),
	(4, '1500_1540', '15:00', '15:40'),
	(5, '1540_1620', '15:40', '16:20'),
	(6, '1620_1700', '16:20', '17:00'),
	(7, '1700_1800', '17:00', '18:00'),
	(8, '1800_1840', '18:00', '18:40'),
	(9, '1840_1920', '18:40', '19:20'),
	(10, '1920_2000', '19:20', '20:00'),
	(11, '2000_2040', '20:00', '20:40'),
	(12, '2040_2120', '20:40', '21:20'),
	(13, '2120_2200', '21:20', '22:00');
/*!40000 ALTER TABLE `courses_time` ENABLE KEYS */;

-- 傾印  表格 ren-sing.person 結構
DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `person_id` varchar(15) NOT NULL COMMENT '會員編號',
  `virtual_account` varchar(50) DEFAULT '0' COMMENT '繳費虛擬帳號',
  `name` varchar(50) NOT NULL COMMENT '會員名字',
  `nickname` varchar(50) DEFAULT NULL COMMENT '綽號',
  `balance` varchar(50) DEFAULT '0' COMMENT '餘額',
  `id_number` varchar(10) DEFAULT NULL COMMENT '身分證字號',
  `sex` varchar(1) DEFAULT NULL COMMENT '性別 (0:男,1:女)',
  `birthday` varchar(10) NOT NULL COMMENT '生日',
  `local_calls` varchar(15) DEFAULT NULL COMMENT '市話',
  `phone` varchar(15) NOT NULL COMMENT '電話',
  `email` varchar(50) NOT NULL COMMENT '信箱',
  `skype` varchar(50) DEFAULT NULL COMMENT 'skyoe帳號',
  `career` varchar(50) DEFAULT NULL COMMENT '職業',
  `recipient` varchar(20) DEFAULT NULL COMMENT '收信人',
  `rec_add` varchar(100) DEFAULT NULL COMMENT '收信地址',
  `rec_num` varchar(15) DEFAULT NULL COMMENT '收信人電話',
  `company_tax_id` varchar(30) DEFAULT NULL COMMENT '統一編號',
  `updater` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL COMMENT 'yyyy/MM/dd hh:mm:ss',
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在傾印表格  ren-sing.person 的資料：~6 rows (大約)
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
REPLACE INTO `person` (`person_id`, `virtual_account`, `name`, `nickname`, `balance`, `id_number`, `sex`, `birthday`, `local_calls`, `phone`, `email`, `skype`, `career`, `recipient`, `rec_add`, `rec_num`, `company_tax_id`, `updater`, `update_time`) VALUES
	('2017100015', '0', '上帝視角', '勝利組', '0', '9527', '0', '19890513', '02-5566', '09875566', '5566@gmail.com', '5566@gmail.com', '上帝', NULL, NULL, NULL, NULL, NULL, NULL),
	('2017120014', NULL, '顏大', NULL, NULL, 'C121328819', '0', '2017/12/16', NULL, '0916372433', 'aaa@aaa.aaa', NULL, '老師', NULL, NULL, NULL, NULL, 'updater', '2017/12/16 14:34:02'),
	('2017120025', NULL, '安安', NULL, NULL, 'C121328819', '0', '2017/12/16', NULL, '0916372433', 'bbb@bbb.bbb', NULL, '老師', NULL, NULL, NULL, NULL, 'updater', '2017/12/16 14:34:47'),
	('2017120036', NULL, '麻吉', NULL, NULL, 'C121328819', '1', '2017/12/16', NULL, '0916372433', 'ccc@ccc.ccc', NULL, '主播', NULL, NULL, NULL, NULL, 'updater', '2017/12/16 14:38:41'),
	('2017120047', NULL, '臻臻', NULL, NULL, 'C121328819', '1', '2017/12/16', NULL, '0916372433', 'ddd@ddd.ddd', NULL, '天下雜誌企劃', NULL, NULL, NULL, NULL, 'updater', '2017/12/29 14:56:48'),
	('2017120058', NULL, '河神', NULL, NULL, 'C121328819', '0', '2017/12/18', NULL, '0916372433', 'aaa@aaa.aaa', NULL, NULL, NULL, NULL, NULL, NULL, 'updater', '2017/12/18 21:22:34');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
