-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.5-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for gamedatabase
CREATE DATABASE IF NOT EXISTS `gamedatabase` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gamedatabase`;

-- Dumping structure for table gamedatabase.playedgame
CREATE TABLE IF NOT EXISTS `playedgame` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player1` int(11) NOT NULL DEFAULT 0,
  `player2` int(11) NOT NULL DEFAULT 1000,
  `gametype` varchar(50) NOT NULL DEFAULT '0',
  `winner` int(11) NOT NULL DEFAULT 1000,
  `creditChange` float NOT NULL DEFAULT 0,
  `creditAfterPlayer1` float NOT NULL DEFAULT 0,
  `creditAfterPlayer2` float NOT NULL DEFAULT 0,
  `playedOn` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `FK_played_game_player1` (`player1`),
  KEY `FK_played_game_player2` (`player2`),
  KEY `FK_played_game_winner` (`winner`),
  CONSTRAINT `FK_played_game_player1` FOREIGN KEY (`player1`) REFERENCES `player` (`id`),
  CONSTRAINT `FK_played_game_player2` FOREIGN KEY (`player2`) REFERENCES `player` (`id`),
  CONSTRAINT `FK_played_game_winner` FOREIGN KEY (`winner`) REFERENCES `player` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table gamedatabase.playedgame: ~0 rows (approximately)
/*!40000 ALTER TABLE `playedgame` DISABLE KEYS */;
INSERT INTO `playedgame` (`id`, `player1`, `player2`, `gametype`, `winner`, `creditChange`, `creditAfterPlayer1`, `creditAfterPlayer2`, `playedOn`) VALUES
	(1, 1002, 1000, 'poker', 1000, 10, 90, 1000010, '2021-02-16 16:57:01');
/*!40000 ALTER TABLE `playedgame` ENABLE KEYS */;

-- Dumping structure for table gamedatabase.player
CREATE TABLE IF NOT EXISTS `player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL DEFAULT '0',
  `lastName` varchar(50) NOT NULL DEFAULT '0',
  `credits` float NOT NULL DEFAULT 0,
  `profileName` varchar(50) DEFAULT '0',
  `email` varchar(50) NOT NULL DEFAULT '0',
  `password` varchar(50) NOT NULL DEFAULT '0',
  `createdOn` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=latin1;

-- Dumping data for table gamedatabase.player: ~3 rows (approximately)
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` (`id`, `firstName`, `lastName`, `credits`, `profileName`, `email`, `password`, `createdOn`) VALUES
	(1000, 'AI', 'Player', 1000010, 'AI', 'admin@peliymparisto.fi', 'admin123456', '2021-01-29 23:24:52'),
	(1001, 'Tester', 'Player', 100, 'Tester', 'admin2@peliymparisto.fi', 'admin654321', '2021-02-16 16:48:55'),
	(1002, 'Pena', 'Aarnio', 90, 'Penaaa', 'pena.aarnio@korsonkovat.fi', 'liibalaaba', '2021-02-16 16:52:01');
/*!40000 ALTER TABLE `player` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
