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
  `role` varchar(1) DEFAULT '2' COMMENT '角色 (0:行政,1:老師,2:學生)',
  `status` varchar(1) DEFAULT '1' COMMENT '帳號狀態 (0:停權, 1:使用中)',
  `person_id` varchar(50) NOT NULL COMMENT '會員編號',
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.account 的資料：~2 rows (大約)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
REPLACE INTO `account` (`account`, `password`, `role`, `status`, `person_id`) VALUES
	('000', '000', '1', NULL, '2017090016'),
	('111', '000', '1', NULL, '2017090027'),
	('admin', '123123', '0', '1', '9527'),
	('qwe', 'qwe', '2', '1', '2017100015');
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
  `song` varchar(50) DEFAULT '0' COMMENT '歌曲',
  `date` varchar(50) NOT NULL COMMENT '上課日期 yyyy/MM/dd',
  `time` varchar(2) NOT NULL COMMENT '上課時間 (對照course_time)',
  `ranges` int(11) DEFAULT '0' COMMENT '幾個時段',
  `s_time` varchar(10) DEFAULT '' COMMENT '特殊上課時間(開始)',
  `e_time` varchar(10) DEFAULT '' COMMENT '特殊上課時間(結束)',
  `hw` varchar(1000) DEFAULT '' COMMENT '回家作業',
  `teacher_note` varchar(1000) DEFAULT '' COMMENT '老師筆記',
  `student_note` varchar(1000) DEFAULT '' COMMENT '學生筆記',
  `type` varchar(1) NOT NULL DEFAULT '0' COMMENT '0=個別課，1=阿卡課',
  `finish` varchar(1) NOT NULL DEFAULT '1' COMMENT '0=結束，1=未結束',
  `sign` varchar(1) NOT NULL DEFAULT '1' COMMENT '0=已簽到，1=未簽到',
  `updater` varchar(50) DEFAULT '0',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'yyyy/MM/dd hh:mm:ss',
  UNIQUE KEY `class_detail_id` (`class_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.class_detail 的資料：~4 rows (大約)
/*!40000 ALTER TABLE `class_detail` DISABLE KEYS */;
REPLACE INTO `class_detail` (`class_detail_id`, `class_master_id`, `student_id`, `teacher_id`, `song`, `date`, `time`, `ranges`, `s_time`, `e_time`, `hw`, `teacher_note`, `student_note`, `type`, `finish`, `sign`, `updater`, `update_time`) VALUES
	(1, 1, '1', '2017090016', 'song', '2017/11/09', '8', 4, '0', '0', '0', '0', '3333', '0', '0', '1', 'updater', '2017-11-11 16:43:38'),
	(6, 1112, '123564545', '2017090016', '鬥陣取', '2017/11/09', '7', 0, '0', '0', '大便庫子上', '測試老師2', '測試學生2', '0', '1', '0', 'updater', '2017-11-11 16:43:41'),
	(7, 2, '2', '2017090016', '好習慣', '2017/11/09', '15', 0, '0', '0', '0', '0', '3333', '1', '0', '1', 'updater', '2017-11-11 16:43:40'),
	(8, 1111, '123564545', '2017090016', '鬥陣取', '2017/11/09', '6', 0, '0', '0', '大便庫子上', '測試老師3', '測試學生3', '0', '1', '1', 'updater', '2017-11-11 16:43:42');
/*!40000 ALTER TABLE `class_detail` ENABLE KEYS */;

-- 導出  表 ren-sing.class_level 結構
CREATE TABLE IF NOT EXISTS `class_level` (
  `class_level_id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(2) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`class_level_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.class_level 的資料：~3 rows (大約)
/*!40000 ALTER TABLE `class_level` DISABLE KEYS */;
REPLACE INTO `class_level` (`class_level_id`, `level`, `name`) VALUES
	(1, '1', '入門班'),
	(2, '2', '基礎班'),
	(3, '3', '進階班');
/*!40000 ALTER TABLE `class_level` ENABLE KEYS */;

-- 導出  表 ren-sing.class_master 結構
CREATE TABLE IF NOT EXISTS `class_master` (
  `class_master_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0' COMMENT '課堂名稱',
  `type` varchar(1) NOT NULL DEFAULT '0' COMMENT '課程類型 (0:一對一個別課,1:阿卡團體班)',
  `level` varchar(1) DEFAULT '0' COMMENT '課程等級 (0:入門班,1:基礎班,2:進階班)',
  `status` varchar(50) NOT NULL DEFAULT '0' COMMENT '課程狀態 (0:未結束 1:結束 2:未結束其他理由)',
  `price` varchar(50) NOT NULL DEFAULT '0' COMMENT '課程原始價錢',
  `summary` varchar(5) NOT NULL DEFAULT '0' COMMENT '課堂總數',
  `count` varchar(5) NOT NULL DEFAULT '0' COMMENT '目前上課次數',
  `rest` varchar(5) NOT NULL DEFAULT '0' COMMENT '剩餘課堂數',
  `s_date` varchar(50) DEFAULT NULL COMMENT '課堂起始時間 (預計) yyyy/mm/dd',
  `e_date` varchar(10) DEFAULT '0' COMMENT '課堂結束時間 (預計) yyyy/MM/dd',
  `place` varchar(50) NOT NULL DEFAULT '0' COMMENT '上課地點',
  `teacher_id` varchar(15) NOT NULL DEFAULT '0' COMMENT '老師',
  `student_id` varchar(15) NOT NULL DEFAULT '0' COMMENT '學生',
  `updater` varchar(50) NOT NULL DEFAULT '0',
  `update_time` varchar(50) NOT NULL DEFAULT '0' COMMENT 'yyyy/MM/dd hh:mm:ss',
  PRIMARY KEY (`class_master_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1113 DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.class_master 的資料：~2 rows (大約)
/*!40000 ALTER TABLE `class_master` DISABLE KEYS */;
REPLACE INTO `class_master` (`class_master_id`, `name`, `type`, `level`, `status`, `price`, `summary`, `count`, `rest`, `s_date`, `e_date`, `place`, `teacher_id`, `student_id`, `updater`, `update_time`) VALUES
	(1111, '音樂課', '0', '0', '0', '25000', '24', '6', '18', '2017/07/02', '2017/9/2', '0', '2017090016', '9527', 'updater', '2017/11/07 06:04:11'),
	(1112, '喇叭樂課', '1', '1', '0', '25000', '12', '2', '11', '2017/09/02', '2017/10/2', '1', '2017090027', '9527', 'updater', '2017/11/01 21:10:45');
/*!40000 ALTER TABLE `class_master` ENABLE KEYS */;

-- 導出  表 ren-sing.class_place 結構
CREATE TABLE IF NOT EXISTS `class_place` (
  `class_place_id` int(11) NOT NULL AUTO_INCREMENT,
  `place` varchar(2) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT '0',
  PRIMARY KEY (`class_place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.class_place 的資料：~2 rows (大約)
/*!40000 ALTER TABLE `class_place` DISABLE KEYS */;
REPLACE INTO `class_place` (`class_place_id`, `place`, `name`) VALUES
	(1, '0', '菜寮'),
	(2, '1', '新竹');
/*!40000 ALTER TABLE `class_place` ENABLE KEYS */;

-- 導出  表 ren-sing.class_type 結構
CREATE TABLE IF NOT EXISTS `class_type` (
  `class_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(3) NOT NULL DEFAULT '0' COMMENT '課程類型 (0:一對一個別課,1:阿卡團體班)',
  `name` varchar(50) DEFAULT '0' COMMENT '課程類型名稱',
  UNIQUE KEY `class_type_id` (`class_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.class_type 的資料：~2 rows (大約)
/*!40000 ALTER TABLE `class_type` DISABLE KEYS */;
REPLACE INTO `class_type` (`class_type_id`, `type`, `name`) VALUES
	(1, '0', '一對一個人課'),
	(2, '1', '阿卡基礎班');
/*!40000 ALTER TABLE `class_type` ENABLE KEYS */;

-- 導出  表 ren-sing.courses_time 結構
CREATE TABLE IF NOT EXISTS `courses_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `interval_time` varchar(50) DEFAULT '0',
  `start_time` varchar(50) DEFAULT '0',
  `end_time` varchar(50) DEFAULT '0',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- 正在導出表  ren-sing.courses_time 的資料：~12 rows (大約)
/*!40000 ALTER TABLE `courses_time` DISABLE KEYS */;
REPLACE INTO `courses_time` (`id`, `interval_time`, `start_time`, `end_time`) VALUES
	(1, '1300_1339', '13:00', '13:40'),
	(2, '1340_1420', '13:40', '14:20'),
	(3, '1420_1500', '14:20', '15:00'),
	(4, '1500_1540', '15:00', '15:40'),
	(5, '1620_1700', '16:20', '17:00'),
	(6, '1540_1620', '15:40', '16:20'),
	(7, '1800_1840', '18:00', '18:40'),
	(8, '1840_1920', '18:40', '19:20'),
	(9, '1920_2000', '19:20', '20:00'),
	(10, '2000_2040', '20:00', '20:40'),
	(11, '2040_2120', '20:40', '21:20'),
	(12, '2120_2200', '21:20', '22:00');
/*!40000 ALTER TABLE `courses_time` ENABLE KEYS */;

-- 導出  表 ren-sing.person 結構
CREATE TABLE IF NOT EXISTS `person` (
  `person_id` varchar(15) NOT NULL COMMENT '會員編號',
  `virtual_account` varchar(50) DEFAULT '0' COMMENT '繳費虛擬帳號',
  `name` varchar(50) DEFAULT NULL COMMENT '會員名字',
  `nickname` varchar(50) DEFAULT NULL COMMENT '綽號',
  `balance` varchar(50) DEFAULT '0' COMMENT '餘額',
  `id_number` varchar(10) DEFAULT NULL COMMENT '身分證字號',
  `sex` varchar(1) DEFAULT NULL COMMENT '性別 (0:男,1:女)',
  `birthday` varchar(10) DEFAULT NULL COMMENT '生日',
  `local_calls` varchar(15) DEFAULT NULL COMMENT '市話',
  `phone` varchar(15) DEFAULT NULL COMMENT '電話',
  `email` varchar(50) DEFAULT NULL COMMENT '信箱',
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

-- 正在導出表  ren-sing.person 的資料：~3 rows (大約)
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
REPLACE INTO `person` (`person_id`, `virtual_account`, `name`, `nickname`, `balance`, `id_number`, `sex`, `birthday`, `local_calls`, `phone`, `email`, `skype`, `career`, `recipient`, `rec_add`, `rec_num`, `company_tax_id`, `updater`, `update_time`) VALUES
	('2017090016', '0', '韋小寶', '小寶', '0', '1', '0', '19870806', '22', '11', '1', '11', '11', '11', '11', '11', '11', '11', '11'),
	('2017090027', '0', '滅絕師太', '妖尼姑', '0', '1', '1', '19870806', '33', '22', '22', '2', '2', '22', '2', '2', '22', '2', '2'),
	('2017100015', NULL, 'qwe', NULL, NULL, 'C666666666', NULL, '1989/05/11', '24323772', '24323772', '24323772@aaa.aaa', '24323772@aaa.aaa', '5566', '5566', '5566', '5566', NULL, 'updater', '2017/10/29 17:52:52'),
	('9527', '0', '上帝視角', '勝利組', '0', '9527', '0', '19890513', '02-5566', '09875566', '5566@gmail.com', '5566@gmail.com', '上帝', NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
