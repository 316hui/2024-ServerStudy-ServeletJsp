-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.6.11-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- testyj 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `testyj` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci */;
USE `testyj`;

-- 테이블 testyj.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `b_idx` int(10) NOT NULL AUTO_INCREMENT,
  `b_title` varchar(255) NOT NULL,
  `b_content` varchar(255) NOT NULL,
  `b_date` varchar(255) NOT NULL,
  `b_writer` varchar(255) NOT NULL,
  `u_idx` int(10) NOT NULL,
  PRIMARY KEY (`b_idx`),
  KEY `u_idx` (`u_idx`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`u_idx`) REFERENCES `user` (`u_idx`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 testyj.board:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
/*!40000 ALTER TABLE `board` ENABLE KEYS */;

-- 테이블 testyj.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `u_idx` int(10) NOT NULL AUTO_INCREMENT,
  `u_id` varchar(255) NOT NULL,
  `u_pw` varchar(255) NOT NULL,
  `u_name` varchar(255) NOT NULL,
  `u_tel` varchar(255) NOT NULL,
  `u_age` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`u_idx`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 testyj.user:~23 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`u_idx`, `u_id`, `u_pw`, `u_name`, `u_tel`, `u_age`) VALUES
	(2, 'abc', '', 'name1', '010-0000-0111', '55'),
	(3, 'nancy', '7755', 'nancy2', '011-0000-0111', '42'),
	(4, 'cessil', '', 'cessilia', '010-7734-4433', '21'),
	(5, '샘플샐픔', '1145', 'samplegirl', '010-2122-5555', '20'),
	(6, '샘플샐픔', '1145', 'sampleboy', '010-2122-5555', '48'),
	(7, '공룡', '', 'dragon', '010-2133-5555', '32'),
	(11, 'cgram', '05160516', '그램', '222-1111-3232', '22'),
	(12, 'cgram', '05160516', '그램', '222-1111-3232', '22'),
	(13, 'ddd2222222', '', 'yejin', '2222-676-7687', '11'),
	(14, 'ddd', 'sssss', 'yejin', '2222-676-7687', '11'),
	(15, 'sadf', '1111', 'asfsa', '1234-2134-1223', '11'),
	(16, 'cgfg', '', '', '--', ''),
	(17, 'jhjhjfg', '', '', '--', ''),
	(18, 'jhjhjfg', '', '', '--', ''),
	(19, 'jhjhjfg', '', '', '--', ''),
	(20, 'join.do���� �����°�', 'dd', 'dd', 'dd-dd-dd', 'dd'),
	(21, 'ngg', 'dd', 'dd', 'dd-dd-dd', 'dd'),
	(22, 'abc', '1234', 'asfd', '1-11-1', '11'),
	(23, '소희', '222', 'sohee', '939011101111', '33'),
	(24, '희란', '6464', 'hhuiran', '939022', '23'),
	(25, '계란 란', '11111', 'eggman', '7352222', '10'),
	(26, '소라게', '1234311', 'shellcrab', '201950492', '2'),
	(27, '행복이 ', '666', 'happiness', '20768', '28');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
