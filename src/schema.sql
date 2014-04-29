CREATE DATABASE  IF NOT EXISTS `h2h` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `h2h`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: localhost    Database: h2h
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(20) NOT NULL,
  `uses_teams` tinyint(4) NOT NULL DEFAULT '0',
  `max_players` smallint(6) DEFAULT NULL,
  `min_players` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `type_UNIQUE` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1,'Ping Pong','PING_PONG',0,2,2),(2,'Ping Pong Doubles','PING_PONG_DOUBLES',1,4,4),(3,'Foosball','FOOSBALL',0,NULL,NULL),(4,'Foosball Doubles','FOOSBALL_DOUBLES',1,4,4);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `league`
--

DROP TABLE IF EXISTS `league`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `league` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `abbr` varchar(10) NOT NULL,
  `is_public` tinyint(4) NOT NULL DEFAULT '0',
  `join_password` varchar(45) DEFAULT NULL,
  `owner_id` int(11) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creator_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `group_owner_to_player_fk_idx` (`owner_id`),
  KEY `group_creator_to_player_fk_idx` (`creator_id`),
  CONSTRAINT `group_creator_to_login_fk` FOREIGN KEY (`creator_id`) REFERENCES `login` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `group_owner_to_login_fk` FOREIGN KEY (`owner_id`) REFERENCES `login` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `league`
--

LOCK TABLES `league` WRITE;
/*!40000 ALTER TABLE `league` DISABLE KEYS */;
INSERT INTO `league` VALUES (1,'Performance Matters','PMI',0,NULL,1,'2014-04-13 01:01:06',1);
/*!40000 ALTER TABLE `league` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `league_games`
--

DROP TABLE IF EXISTS `league_games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `league_games` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `league_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `sort_order` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `league_games_to_game_fk_idx` (`game_id`),
  KEY `leage_games_to_league_fk_idx` (`league_id`),
  CONSTRAINT `leage_games_to_league_fk` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `league_games_to_game_fk` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `league_games`
--

LOCK TABLES `league_games` WRITE;
/*!40000 ALTER TABLE `league_games` DISABLE KEYS */;
INSERT INTO `league_games` VALUES (1,1,1,1),(2,1,2,2),(3,1,3,3);
/*!40000 ALTER TABLE `league_games` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `league_logins`
--

DROP TABLE IF EXISTS `league_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `league_logins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `league_id` int(11) NOT NULL,
  `login_id` int(11) NOT NULL,
  `is_admin` tinyint(4) NOT NULL DEFAULT '0',
  `can_enter_scores` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `leage_players_to_league_fk_idx` (`league_id`),
  KEY `league_players_to_player_fk_idx` (`login_id`),
  CONSTRAINT `league_logins_to_league_fk` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `league_logins_to_login_fk` FOREIGN KEY (`login_id`) REFERENCES `login` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `league_logins`
--

LOCK TABLES `league_logins` WRITE;
/*!40000 ALTER TABLE `league_logins` DISABLE KEYS */;
INSERT INTO `league_logins` VALUES (1,1,1,1,1),(2,1,2,0,1),(3,1,3,1,1),(4,1,4,0,1),(5,1,5,0,1),(6,1,6,0,1),(7,1,7,0,1),(8,1,8,0,1),(9,1,9,0,1),(10,1,10,0,1),(11,1,17,0,1),(12,1,18,0,1),(13,1,19,0,1),(14,1,20,0,1),(15,1,21,0,1);
/*!40000 ALTER TABLE `league_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `login` varchar(100) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `salt` varchar(45) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `active_flag` tinyint(4) NOT NULL DEFAULT '1',
  `super_user_flag` tinyint(4) NOT NULL DEFAULT '0',
  `current_league` int(11) DEFAULT NULL,
  `login_count` int(11) NOT NULL DEFAULT '0',
  `last_login_date` timestamp NULL DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `player_current_group_to_group_fk_idx` (`current_league`),
  CONSTRAINT `player_current_league_to_league_fk` FOREIGN KEY (`current_league`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'Jason','Ergle','ergle545','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','King Dingaling',1,1,1,26,'2014-04-29 01:23:43','2014-04-15 01:28:27'),(2,'Steven','Smith','steven.smith','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','Big Baldy',1,0,1,0,'2014-04-23 20:14:53','2014-04-15 01:28:27'),(3,'Kurt','Davis','kurt.davis','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','Mr Backspin',1,1,1,0,'2014-04-24 11:43:50','2014-04-15 01:28:27'),(4,'Brandon','Stewart','brandon.stewart','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','Little Baldy',1,0,1,0,'2014-04-23 20:15:29','2014-04-15 01:28:27'),(5,'James','Waters','james.waters','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','Boss Man',1,0,1,0,NULL,'2014-04-15 01:28:27'),(6,'Arun','Velgapalli','arun.velgapalli','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','CSX Sux',1,0,1,0,'2014-04-18 13:49:26','2014-04-15 01:28:27'),(7,'Nick','Coniglio','nick.coniglio','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','Data Man',1,0,1,0,'2014-04-15 12:39:17','2014-04-15 01:28:27'),(8,'Todd','Greenberg','todd.greenberg','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','Pony Master',1,0,1,0,'2014-04-23 12:39:38','2014-04-15 01:28:27'),(9,'Alex','Feliciano','alex.feliciano','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','css rulez',1,0,1,0,'2014-04-23 14:59:04','2014-04-15 01:28:27'),(10,'Randall','Stanley','randall.stanley','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','Chipolte',1,0,1,0,NULL,'2014-04-15 01:28:27'),(17,'Woody','Dillaha','woody','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','Big Man on Campus',1,0,1,0,NULL,'2014-04-15 01:28:27'),(18,'Pavan','Kumar','pavan.kumar','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','SELECT *',1,0,1,0,'2014-04-23 20:42:20','2014-04-15 01:28:27'),(19,'Kelly','Morrison','kelly.morrison','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','Athens Correctional League',1,0,1,0,'2014-04-15 13:55:56','2014-04-15 13:55:31'),(20,'Amy','Williamson','amy.williamson','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','Noisy Gal',1,0,1,0,NULL,'2014-04-23 13:48:47'),(21,'Thacienne','Muhorakeye','thacienne.muhorakeye','+pGDMPzktECr6DKjekmxpdAJ2RY=','qEhQ0jdyAxQ=','I <3 Swiss Chocolates',1,0,NULL,0,NULL,'2014-04-23 13:51:15');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `league_id` int(11) NOT NULL,
  `associated_login` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `creator_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `player_to_creator_login_fk_idx` (`creator_id`),
  KEY `player_to_associated_login_fk_idx` (`associated_login`),
  KEY `player_to_league_fk_idx` (`league_id`),
  CONSTRAINT `player_to_creator_login_fk` FOREIGN KEY (`creator_id`) REFERENCES `login` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `player_to_associated_login_fk` FOREIGN KEY (`associated_login`) REFERENCES `login` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `player_to_league_fk` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `league_id` int(11) NOT NULL,
  `associated_login` int(11) DEFAULT NULL,
  `creator_id` int(11) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_single_player_team` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE_per_league` (`name`,`league_id`),
  KEY `participant_to_league_fk_idx` (`league_id`),
  KEY `participant_to_login_fk_idx` (`creator_id`),
  KEY `participant_to_login_assoc_fk_idx` (`associated_login`),
  CONSTRAINT `team_to_league_fk` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `team_to_login_assoc_fk` FOREIGN KEY (`associated_login`) REFERENCES `login` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `team_to_login_creator_fk` FOREIGN KEY (`creator_id`) REFERENCES `login` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,'Jason (Sledgehammer) Ergle',1,1,1,'2014-04-27 17:48:36',1),(2,'Steven (Big Baldy) Smith',1,2,1,'2014-04-27 17:48:36',1),(3,'Kurt (Slim Shady) Davis',1,3,1,'2014-04-27 17:49:47',1),(4,'Brandon (Little Baldy) Stewart',1,NULL,1,'2014-04-27 18:01:54',1);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_players`
--

DROP TABLE IF EXISTS `team_players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_players` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `team_id_UNIQUE` (`team_id`,`player_id`),
  KEY `team_players_to_player_fk_idx` (`player_id`),
  CONSTRAINT `team_players_to_team_fk` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `team_players_to_player_fk` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_players`
--

LOCK TABLES `team_players` WRITE;
/*!40000 ALTER TABLE `team_players` DISABLE KEYS */;
/*!40000 ALTER TABLE `team_players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `versus_entry`
--

DROP TABLE IF EXISTS `versus_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `versus_entry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `match_id` int(11) NOT NULL,
  `login_id` int(11) DEFAULT NULL,
  `score` decimal(6,0) NOT NULL DEFAULT '0',
  `team_id` int(11) NOT NULL,
  `is_winner` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `versus_entry_to_versus_match_idx` (`match_id`),
  KEY `versus_entry_to_player_idx` (`login_id`),
  KEY `versus_entry_to_participant_fk_idx` (`team_id`),
  CONSTRAINT `versus_entry_to_team_fk` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `versus_entry_to_versus_match_fk` FOREIGN KEY (`match_id`) REFERENCES `versus_match` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versus_entry`
--

LOCK TABLES `versus_entry` WRITE;
/*!40000 ALTER TABLE `versus_entry` DISABLE KEYS */;
INSERT INTO `versus_entry` VALUES (202,117,NULL,0,3,0),(203,117,NULL,21,1,0),(204,118,NULL,21,1,0),(205,118,NULL,1,3,0),(206,119,NULL,2,3,0),(207,119,NULL,21,1,0),(208,120,NULL,20,1,0),(209,120,NULL,22,3,0),(210,121,NULL,0,2,0),(211,121,NULL,21,3,0),(212,122,NULL,1,3,0),(213,122,NULL,21,2,0);
/*!40000 ALTER TABLE `versus_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `versus_match`
--

DROP TABLE IF EXISTS `versus_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `versus_match` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) NOT NULL,
  `league_id` int(11) NOT NULL,
  `is_complete` tinyint(4) NOT NULL DEFAULT '1',
  `title` varchar(45) DEFAULT NULL,
  `match_date` timestamp NULL DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creator_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `match_creator_to_player_fk_idx` (`game_id`),
  KEY `match_to_group_fk_idx` (`league_id`),
  KEY `match_player_creator_to_player_fk_idx` (`creator_id`),
  CONSTRAINT `versus_match_to_login_fk` FOREIGN KEY (`creator_id`) REFERENCES `login` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `versus_match_to_game_fk` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `versus_match_to_league_fk` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versus_match`
--

LOCK TABLES `versus_match` WRITE;
/*!40000 ALTER TABLE `versus_match` DISABLE KEYS */;
INSERT INTO `versus_match` VALUES (117,1,1,1,NULL,'2014-04-27 17:57:21','2014-04-27 17:57:21',1),(118,1,1,1,NULL,'2014-04-27 17:57:32','2014-04-27 17:57:32',1),(119,1,1,1,NULL,'2014-04-27 17:57:36','2014-04-27 17:57:36',1),(120,1,1,1,NULL,'2014-04-27 17:57:45','2014-04-27 17:57:45',1),(121,1,1,1,NULL,'2014-04-27 17:59:15','2014-04-27 17:59:15',1),(122,1,1,1,NULL,'2014-04-27 17:59:21','2014-04-27 17:59:21',1);
/*!40000 ALTER TABLE `versus_match` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-28 21:26:54
