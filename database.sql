-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.26 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for ftteknoloji
CREATE DATABASE IF NOT EXISTS `ftteknoloji` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ftteknoloji`;

-- Dumping structure for table ftteknoloji.kullanici
CREATE TABLE IF NOT EXISTS `kullanici` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ad` varchar(50) NOT NULL,
  `soyad` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefon` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `telefon_UNIQUE` (`telefon`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table ftteknoloji.kullanici: ~0 rows (approximately)
INSERT INTO `kullanici` (`id`, `ad`, `soyad`, `email`, `telefon`) VALUES
	(1, 'Abdulbaki', 'Ünvanlı', 'abdulbaki.unvanli@hotmail.com', '5073757141'),
	(2, 'Ali', 'Gün', 'aligun@hotmail.com', '5050000000'),
	(5, 'Erdem', 'Taner', 'erdemtaner@hotmail.com', '5070000000');

-- Dumping structure for table ftteknoloji.urun
CREATE TABLE IF NOT EXISTS `urun` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ad` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fiyat` decimal(15,2) unsigned NOT NULL,
  `son_kullanma_tarihi` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table ftteknoloji.urun: ~0 rows (approximately)
INSERT INTO `urun` (`id`, `ad`, `fiyat`, `son_kullanma_tarihi`) VALUES
	(1, 'Peynir', 37.90, '2022-08-31'),
	(2, 'Süt', 19.50, '2022-09-03'),
	(3, 'Masa', 400.00, NULL),
	(4, 'Yumurta', 54.50, '2022-10-05'),
	(5, 'Macbook', 25000.00, NULL),
	(6, 'iPhone 13 Pro Max', 35000.00, NULL),
	(7, 'Domates', 19.60, '2022-11-15'),
	(9, 'Biber', 14.90, '2022-12-08');

-- Dumping structure for table ftteknoloji.urun_yorum
CREATE TABLE IF NOT EXISTS `urun_yorum` (
  `id` int NOT NULL AUTO_INCREMENT,
  `yorum` varchar(500) NOT NULL,
  `yorum_tarihi` date NOT NULL,
  `urun_id` int NOT NULL,
  `kullanici_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_kullanici_urun_yorum` (`kullanici_id`),
  KEY `fk_urun_urun_yorum` (`urun_id`),
  CONSTRAINT `fk_kullanici_urun_yorum` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`id`),
  CONSTRAINT `fk_urun_urun_yorum` FOREIGN KEY (`urun_id`) REFERENCES `urun` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table ftteknoloji.urun_yorum: ~0 rows (approximately)
INSERT INTO `urun_yorum` (`id`, `yorum`, `yorum_tarihi`, `urun_id`, `kullanici_id`) VALUES
	(1, 'Urun mukemmel.performans, ses, kalite, hiz, batarya suresi hersey mukkemmel.', '2022-09-05', 5, 1),
	(2, 'Ürün fiyat performans ürünü', '2022-09-07', 3, 1),
	(3, 'Yaklaşık 2 haftadır kullanıyorum ve memnunum.', '2022-06-05', 6, 2),
	(5, 'Gerçekten çok iyi bir telefon. ', '2022-09-04', 6, 1),
	(6, 'Mükemmel bir bilgisayar. ', '2022-09-10', 5, 2);

-- Create user
CREATE USER IF NOT EXISTS 'ftteknoloji'@'localhost' IDENTIFIED BY '123456';
GRANT SELECT, INSERT, DELETE, UPDATE ON ftteknoloji.* TO 'ftteknoloji'@'localhost';

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
