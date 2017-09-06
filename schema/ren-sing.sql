-- --------------------------------------------------------
-- 主機:                           127.0.0.1
-- 服務器版本:                        5.7.14-log - MySQL Community Server (GPL)
-- 服務器操作系統:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- 正在導出表  ren-sing.account 的資料：~1 rows (大約)
DELETE FROM `account`;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`account`, `password`, `role`, `status`, `person_id`) VALUES
	('admin', '123123', '0', '1', '123564545');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- 正在導出表  ren-sing.achievement 的資料：~0 rows (大約)
DELETE FROM `achievement`;
/*!40000 ALTER TABLE `achievement` DISABLE KEYS */;
/*!40000 ALTER TABLE `achievement` ENABLE KEYS */;

-- 正在導出表  ren-sing.achievement_log 的資料：~0 rows (大約)
DELETE FROM `achievement_log`;
/*!40000 ALTER TABLE `achievement_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `achievement_log` ENABLE KEYS */;

-- 正在導出表  ren-sing.bill 的資料：~0 rows (大約)
DELETE FROM `bill`;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;

-- 正在導出表  ren-sing.bill_log 的資料：~0 rows (大約)
DELETE FROM `bill_log`;
/*!40000 ALTER TABLE `bill_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_log` ENABLE KEYS */;

-- 正在導出表  ren-sing.class_detail 的資料：~5 rows (大約)
DELETE FROM `class_detail`;
/*!40000 ALTER TABLE `class_detail` DISABLE KEYS */;
INSERT INTO `class_detail` (`class_detail_id`, `class_master_id`, `student_id`, `teacher_id`, `song`, `date`, `time`, `hw`, `teacher_note`, `student_note`, `type`, `finish`, `sign`, `updater`, `update_time`) VALUES
	(1, 1, '1', '1', 'song', '2017/078/09', '18:30', '0', '0', '3333', '0', '0', '1', '0', '0'),
	(5, 1112, '123564545', '1235645456', '鬥陣取', '2017/08/09', '15:30', '大便庫子上', '測試老師1', '測試學生1', '0', '0', '0', 'updater', '2017/08/07 06:26:32'),
	(6, 1112, '123564545', '1235645456', '鬥陣取', '2017/8/16', '15:40', '大便庫子上', '測試老師2', '測試學生2', '0', '0', '0', 'updater', '2017/09/01 01:28:27'),
	(7, 2, '2', '2', '好習慣', '2017/08/09', '20:00', '0', '0', '3333', '0', '0', '1', NULL, NULL),
	(8, 1112, '123564545', '1235645456', '鬥陣取', '2017/08/23', '15:30', '大便庫子上', '測試老師3', '測試學生3', '0', '1', '1', '0', '0');
/*!40000 ALTER TABLE `class_detail` ENABLE KEYS */;

-- 正在導出表  ren-sing.class_master 的資料：~2 rows (大約)
DELETE FROM `class_master`;
/*!40000 ALTER TABLE `class_master` DISABLE KEYS */;
INSERT INTO `class_master` (`class_master_id`, `name`, `type`, `level`, `status`, `price`, `summary`, `count`, `rest`, `s_date`, `e_date`, `place`, `teacher_id`, `updater`, `update_time`) VALUES
	(1111, '音樂課', '0', '0', 'Y', '25000', '10', '58', '-4', '2017/7/2', '2017/9/2', '菜寮', '123564545', '0', '0'),
	(1112, '喇叭樂課', '0', '0', 'Y', '25000', '5', '0', '5', '2017/9/2', '2017/10/2', '菜寮', '123564545', '0', '0');
/*!40000 ALTER TABLE `class_master` ENABLE KEYS */;

-- 正在導出表  ren-sing.courses_time 的資料：~18 rows (大約)
DELETE FROM `courses_time`;
/*!40000 ALTER TABLE `courses_time` DISABLE KEYS */;
INSERT INTO `courses_time` (`id`, `interval_time`, `start_time`, `end_time`) VALUES
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

-- 正在導出表  ren-sing.person 的資料：~2 rows (大約)
DELETE FROM `person`;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`person_id`, `virtual_account`, `name`, `nickname`, `balance`, `id_number`, `sex`, `birthday`, `local_calls`, `phone`, `email`, `skype`, `career`, `recipient`, `rec_add`, `rec_num`, `company_tax_id`, `updater`, `update_time`) VALUES
	('123564545', '0', '韋小寶', '小寶', '0', '1', '男', '19870806', '22', '11', '1', '11', '11', '11', '11', '11', '11', '11', '11'),
	('1235645456', '0', '滅絕師太', '妖尼姑', '0', '1', '女', '19870806', '33', '22', '22', '2', '2', '22', '2', '2', '22', '2', '2');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
